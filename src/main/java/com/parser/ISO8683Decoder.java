package com.parser;

import com.entities.ISO8583Entity;
import com.entities.ISOField;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ISO8683Decoder {

    private Map<Integer, ISOField> dataElements;
    private HashMap<String,String> mti;
    private HashMap<Integer,Integer> bitMapper;
    private HashMap<Integer,String> fieldsDescriptionMapper;
    private HashMap<Integer,ISOField> fieldsProperties;
    private HashMap<Integer,String> messageTypesMapper;
    private ISO8583Entity entity = new ISO8583Entity();


    public void parseMessage(String message) {

        // Initialize empty maps to store data and meta-data
        mti = new HashMap<>();
        bitMapper = new HashMap<>();
        fieldsDescriptionMapper = new HashMap<>();
        fieldsProperties = new HashMap<>();
        dataElements = new TreeMap<>();
        messageTypesMapper = new HashMap<>();

        // First, extract the message header and the bitmap from the message
        String MTI = message.substring(0, 4);
        parseMTI(MTI);

        String bitMap = message.substring(4, 20);

        String binaryString = strToBinary(bitMap);

        if(binaryString.charAt(0) == '1'){
            bitMap += message.substring(20,36);
            binaryString = strToBinary(bitMap);
        }

        fieldsProperties = Initializer.initializeFieldProperties();
        messageTypesMapper = Initializer.initializeMessageTypes();

        parseBitMap(binaryString);

        int index = 20;

        decodeMessage(message,index);
    }

    private void decodeMessage(String msg, int index){

        //filter key-value mappers according to value = 1
        Map<Integer,Integer> filtered = bitMapper.entrySet().stream().filter(a->a.getValue().intValue() == 1).collect(Collectors.toMap(a-> a.getKey(), a -> a.getValue()));
        Map<Integer, Integer> sortedFiltered = new TreeMap<>(filtered);

        for(Integer id : sortedFiltered.keySet()) {
            ISOField field = fieldsProperties.get(id - 1);
            if(id == 1){
                int length = 16;
                String value = msg.substring(index, index + length);
                index += length;
                field.actualLength = length;
                field.value = value;
                dataElements.put(id, field);
            }
            else {
                int length = field.length;
                String type = field.type;
                String value;
                if(index == msg.length()) {
                    dataElements.put(id, null);
                    continue;
                }
                if (type.contains("IFA_LLL") || type.contains("IFA_LL")) {
                    String prefix = "";
                    int prefixLength = 0;
                    if(type.contains("IFA_LLL")) {
                       prefixLength = 3;
                    }
                    else if(type.contains("IFA_LL")){
                        prefixLength = 2;
                    }
                    prefix = msg.substring(index, index + prefixLength);
                    index = index + prefixLength;
                    Integer prefixVal = Integer.parseInt(prefix);
                    //Integer prefixVal = calculateLength(prefix,prefixLength);

                    if(index + prefixVal > msg.length()){
                        value = msg.substring(index, msg.length());
                        field.actualLength = msg.length() - index;
                        index = msg.length();
                    }
                    else {
                        value = msg.substring(index, index + prefixVal);
                        field.actualLength = prefixVal;
                        index += prefixVal;
                    }
                }
                else {
                    if(index + length > msg.length()){
                        value = msg.substring(index,msg.length());
                        field.actualLength = msg.length() - index;
                        index = msg.length();
                    }
                    else {
                        value = msg.substring(index, index + length);
                        field.actualLength = length;
                        index += length;
                    }
                }
                field.value = value;
                dataElements.put(id, field);
            }
        }
    }

    private int calculateLength(String prefix, int length) {
        if(length == 3) {
            String part1 = prefix.substring(0, 2);
            String part2 = prefix.substring(2, prefix.length());

            int val1 = Integer.parseInt(part1);
            int val2 = Integer.parseInt(part2);

            return val1 + val2;
        }
        else{
            return Integer.parseInt(prefix);
        }
    }

    private void parseBitMap(String binaryBitMap) {

       for(int i = 1; i < binaryBitMap.length(); i++) {
            if(binaryBitMap.charAt(i-1) == '1') bitMapper.put(i,1);
            else bitMapper.put(i,0);
       }
    }

    private String strToBinary(String s)
    {
        // variable to store the converted
        // Binary Sequence
        String binary = "";

        // converting the accepted Hexadecimal
        // string to upper case
        s = s.toUpperCase();

        // initializing the HashMap class
        HashMap<Character, String> hashMap
                = new HashMap<>();

        // storing the key value pairs
        hashMap.put('0', "0000");
        hashMap.put('1', "0001");
        hashMap.put('2', "0010");
        hashMap.put('3', "0011");
        hashMap.put('4', "0100");
        hashMap.put('5', "0101");
        hashMap.put('6', "0110");
        hashMap.put('7', "0111");
        hashMap.put('8', "1000");
        hashMap.put('9', "1001");
        hashMap.put('A', "1010");
        hashMap.put('B', "1011");
        hashMap.put('C', "1100");
        hashMap.put('D', "1101");
        hashMap.put('E', "1110");
        hashMap.put('F', "1111");

        int i;
        char ch;

        // loop to iterate through the length
        // of the Hexadecimal String
        for (i = 0; i < s.length(); i++) {
            // extracting each character
            ch = s.charAt(i);

            // checking if the character is
            // present in the keys
            if (hashMap.containsKey(ch))

                // adding to the Binary Sequence
                // the corresponding value of
                // the key
                binary += hashMap.get(ch);

                // returning Invalid Hexadecimal
                // String if the character is
                // not present in the keys
            else {
                binary = "Invalid Hexadecimal String";
                return binary;
            }
        }

        // returning the converted Binary
        return binary;
    }

    private void parseMTI(String MTI) {
        if (MTI.charAt(0) == '0')  mti.put("ISO Version","ISO 8583:1987");
        else if(MTI.charAt(0) == '1') mti.put("ISO Version","ISO 8583:1993");
        else if(MTI.charAt(0) == '2') mti.put("ISO Version","ISO 8583:2003");
        else if(MTI.charAt(0) == '3') mti.put("ISO Version","Reserved by ISO");
        else if(MTI.charAt(0) == '4') mti.put("ISO Version","Reserved by ISO");
        else if(MTI.charAt(0) == '5') mti.put("ISO Version","Reserved by ISO");
        else if(MTI.charAt(0) == '6') mti.put("ISO Version","Reserved by ISO");
        else if(MTI.charAt(0) == '7') mti.put("ISO Version","Reserved by ISO");
        else if(MTI.charAt(0) == '8') mti.put("ISO Version","National use");
        else if(MTI.charAt(0) == '9') mti.put("ISO Version","Private use");

        if(MTI.charAt(1) == '0') mti.put("Message class","Reserved by ISO");
        else if(MTI.charAt(1) == '1') mti.put("Message class","Authorization message");
        else if(MTI.charAt(1) == '2') mti.put("Message class","Financial messages");
        else if(MTI.charAt(1) == '3') mti.put("Message class","File actions message");
        else if(MTI.charAt(1) == '4') mti.put("Message class","Reversal and chargeback messages");
        else if(MTI.charAt(1) == '5') mti.put("Message class","Reconciliation message");
        else if(MTI.charAt(1) == '6') mti.put("Message class","Administrative message");
        else if(MTI.charAt(1) == '7') mti.put("Message class","Fee collection messages");
        else if(MTI.charAt(1) == '8') mti.put("Message class","Network management message");
        else if(MTI.charAt(1) == '9') mti.put("Message class","Reserved by ISO");

        if(MTI.charAt(2) == '0') mti.put("Message function","Request");
        else if(MTI.charAt(2) == '1') mti.put("Message function","Request response");
        else if(MTI.charAt(2) == '2') mti.put("Message function","Advice");
        else if(MTI.charAt(2) == '3') mti.put("Message function","Advice response");
        else if(MTI.charAt(2) == '4') mti.put("Message function","Notification");
        else if(MTI.charAt(2) == '5') mti.put("Message function","Notification acknowledgement");
        else if(MTI.charAt(2) == '6') mti.put("Message function","Instruction");
        else if(MTI.charAt(2) == '7') mti.put("Message function","Instruction acknowledgement");
        else if(MTI.charAt(2) == '8') mti.put("Message function","Reserved by ISO");
        else if(MTI.charAt(2) == '9') mti.put("Message function","Reserved by ISO");

        if(MTI.charAt(3) == '0') mti.put("Message origin","Acquirer");
        else if(MTI.charAt(3) == '1') mti.put("Message origin","Acquirer repeat");
        else if(MTI.charAt(3) == '2') mti.put("Message origin","Issuer");
        else if(MTI.charAt(3) == '3') mti.put("Message origin","Issuer repeat");
        else if(MTI.charAt(3) == '4') mti.put("Message origin","Other");
        else if(MTI.charAt(3) == '5') mti.put("Message origin","Reserved by ISO");
        else if(MTI.charAt(3) == '6') mti.put("Message origin","Reserved by ISO");
        else if(MTI.charAt(3) == '7') mti.put("Message origin","Reserved by ISO");
        else if(MTI.charAt(3) == '8') mti.put("Message origin","Reserved by ISO");
        else if(MTI.charAt(3) == '9') mti.put("Message origin","Reserved by ISO");
    }
    public void parseMessageValues(ISO8683Decoder isoParser){
        Map<Integer,ISOField> data = isoParser.getDataElements();

        if(data.get(1) != null) {
            entity.setBitmap2(data.get(1).getValue());
        }
        if(data.get(2) != null){
            entity.setPAN(Long.parseLong(data.get(2).getValue()));
        }
        if(data.get(3) != null){
            entity.setProcessingCode(Long.parseLong(data.get(3).getValue()));
        }
        if(data.get(4) != null){
            entity.setAmountTransaction(Long.parseLong(data.get(4).getValue()));
        }
        if(data.get(5) != null) {
            entity.setAmountSettlement(Long.parseLong(data.get(5).getValue()));
        }
        if(data.get(6) != null) {
            entity.setAmountCardHolderBilling(Long.parseLong(data.get(6).getValue()));
        }
        if(data.get(7) != null) {
            Timestamp ts=new Timestamp(Long.parseLong(data.get(7).getValue()));
            entity.setTransmissionDateTime(new Date(ts.getTime()));
        }
        if(data.get(8) != null) {
            entity.setCardHolderBillingFee(Long.parseLong(data.get(8).getValue()));
        }
        if(data.get(9) != null) {
            entity.setConversionRateSettlement(Long.parseLong(data.get(9).getValue()));
        }
        if(data.get(10) != null) {
            entity.setConversionRateCardHolderBilling(Long.parseLong(data.get(10).getValue()));
        }
        if(data.get(11) != null) {
            entity.setSystemTraceAuditNumber(Long.parseLong(data.get(11).getValue()));
        }
        if(data.get(12) != null) {
            entity.setLocalTransactionTime(new Time(Long.parseLong(data.get(12).getValue())));
        }
        if(data.get(13) != null) {
            Timestamp ts=new Timestamp(Long.parseLong(data.get(13).getValue()));
            entity.setLocalTransactionDate(new Date(ts.getTime()));
        }
        if(data.get(14) != null) {
            Timestamp ts=new Timestamp(Long.parseLong(data.get(14).getValue()));
            entity.setExpirationDate(new Date(ts.getTime()));
        }
        if(data.get(15) != null) {
            Timestamp ts=new Timestamp(Long.parseLong(data.get(15).getValue()));
            entity.setSettlementDate(new Date(ts.getTime()));
        }
        if(data.get(16) != null) {
            Timestamp ts=new Timestamp(Long.parseLong(data.get(16).getValue()));
            entity.setCurrencyConversionDate(new Date(ts.getTime()));
        }
        if(data.get(17) != null) {
            Timestamp ts=new Timestamp(Long.parseLong(data.get(17).getValue()));
            entity.setCaptureDate(new Date(ts.getTime()));
        }
        if(data.get(18) != null) {
            entity.setMerchantCategoryCode(data.get(18).getValue());
        }
        if(data.get(19) != null) {
            entity.setAcquiringInetitution(data.get(19).getValue());
        }
        if(data.get(20) != null) {
            entity.setPANExtended(data.get(20).getValue());
        }
        if(data.get(21) != null) {
            entity.setForwardingInstitution(data.get(21).getValue());
        }
        if(data.get(22) != null) {
            entity.setPointOfServiceEntryMode(data.get(22).getValue());
        }
        if(data.get(23) != null) {
            entity.setApplicationPANSequenceNumber(data.get(23).getValue());
        }
        if(data.get(24) != null) {
            entity.setNetworkInternationalIdentifier(data.get(24).getValue());
        }
        if(data.get(25) != null) {
            entity.setPointOfServiceConditionCode(data.get(25).getValue());
        }
        if(data.get(26) != null) {
            entity.setPointOfServiceCaptureCode(data.get(26).getValue());
        }
        if(data.get(27) != null) {
            entity.setAuthorizingIdentificationResponseLength(Long.parseLong(data.get(27).getValue()));
        }
        if(data.get(28) != null) {
            entity.setAmountTransactionFee(Long.parseLong(data.get(28).getValue()));
        }
        if(data.get(29) != null) {
            entity.setAmountSettlementFee(Long.parseLong(data.get(29).getValue()));
        }
        if(data.get(30) != null) {
            entity.setAmountTransactionProcessingFee(Long.parseLong(data.get(30).getValue()));
        }
        if(data.get(31) != null) {
            entity.setAmountSettlementProcessingFee(Long.parseLong(data.get(31).getValue()));
        }
        if(data.get(32) != null) {
            entity.setAcquiringInstitutionIdentificationCode(Long.parseLong(data.get(32).getValue()));
        }
        if(data.get(33) != null) {
            entity.setForwardingInstitutionIdentificationCode(Long.parseLong(data.get(33).getValue()));
        }
        if(data.get(34) != null) {
            entity.setPrimaryAccountNumberExtended(Long.parseLong(data.get(34).getValue()));
        }
        if(data.get(35) != null) {
            entity.setTrack2Data(data.get(35).getValue());
        }
        if(data.get(36) != null) {
            entity.setTrack3Data(data.get(36).getValue());
        }
        if(data.get(37)!= null) {
            entity.setRetrievalReferenceNumber(data.get(37).getValue());
        }
        if(data.get(38) != null) {
            entity.setAuthorizationIdentificationResponse(data.get(38).getValue());
        }
        if(data.get(39) != null) {
            entity.setResponseCode(data.get(39).getValue());
        }
        if(data.get(40) != null) {
            entity.setServiceRestrictionCode(data.get(40).getValue());
        }
        if(data.get(41) != null) {
            entity.setCardAcceptorTerminalIdentification(data.get(41).getValue());
        }
        if(data.get(42) != null) {
            entity.setCardAcceptorIdentificationCode(data.get(42).getValue());
        }
        if(data.get(43) != null) {
            entity.setCardAcceptorNameLocation(data.get(43).getValue());
        }
        if(data.get(44) != null) {
            entity.setAdditionalResponseData(data.get(44).getValue());
        }
        if(data.get(45) != null) {
            entity.setTrack1Data(data.get(45).getValue());
        }
        if(data.get(46) != null) {
            entity.setAdditionalDataISO(data.get(46).getValue());
        }
        if(data.get(47) != null) {
            entity.setAdditionalDataNational(data.get(47).getValue());
        }
        if(data.get(48) != null) {
            entity.setAdditionalDataPrivate(data.get(48).getValue());
        }
        if(data.get(49) != null) {
            entity.setCurrencyCodeTransaction(data.get(49).getValue());
        }
        if(data.get(50) != null) {
            entity.setCurrencyCodeSettlement(data.get(50).getValue());
        }
        if(data.get(51) != null) {
            entity.setCurrencyCodeCardHolderBilling(data.get(51).getValue());
        }
        if(data.get(52) != null) {
            entity.setPersonalIdentificationNumberData(data.get(52).getValue());
        }
        if(data.get(53) != null) {
            entity.setSecurityRelatedControlInformation(data.get(53).getValue());
        }
        if(data.get(54) != null) {
            entity.setAdditionalAmounts(data.get(54).getValue());
        }
        if(data.get(55) != null) {
            entity.setICCData(data.get(55).getValue());
        }
        if(data.get(56) != null) {
            entity.setReservedISO(data.get(56).getValue());
        }
        if(data.get(57) != null) {
            String[] strs = new String[4];
            strs[0] = data.get(57).getValue();
            strs[1] = data.get(58).getValue();
            strs[2] = data.get(59).getValue();
            strs[3] = data.get(60).getValue();
            entity.setReservedNational(strs);
        }
        if(data.get(61) != null) {
            String[] strs = new String[3];
            strs[0] = data.get(61).getValue();
            strs[1] = data.get(62).getValue();
            strs[2] = data.get(63).getValue();
            entity.setReservedPrivate(strs);
        }
        if(data.get(64) != null) {
            entity.setMessageAuthenticationCode1(data.get(64).getValue());
        }
        if(data.get(65) != null) {
            entity.setExtendedBitmapIndicator(data.get(65).getValue());
        }
        if(data.get(66) != null) {
            entity.setSettlementCode(data.get(66).getValue());
        }
        if(data.get(67) != null) {
            entity.setExtendedPaymentCode(data.get(67).getValue());
        }
        if(data.get(68) != null) {
            entity.setReceivingInstitutionCountryCode(data.get(68).getValue());
        }
        if(data.get(69) != null) {
            entity.setSettlementInstitutionCountryCode(data.get(69).getValue());
        }
        if(data.get(70) != null) {
            entity.setNetworkManagementInformationCode(data.get(70).getValue());
        }
        if(data.get(71) != null) {
            entity.setMessageNumber(data.get(71).getValue());
        }
        if(data.get(72) != null) {
            entity.setLastMessageNumber(data.get(72).getValue());
        }
        if(data.get(73) != null) {
            entity.setActionDate(data.get(73).getValue());
        }
        if(data.get(74) != null) {
            entity.setNumberOfCredits(Long.parseLong(data.get(74).getValue()));
        }
        if(data.get(75) != null) {
            entity.setCreditsReversalNumber(data.get(75).getValue());
        }
        if(data.get(76) != null) {
            entity.setNumberOfDebits(data.get(76).getValue());
        }
        if(data.get(77) != null) {
            entity.setDebitsReversalNumber(data.get(77).getValue());
        }
        if(data.get(78) != null) {
            entity.setTransferNumber(data.get(78).getValue());
        }
        if(data.get(79) != null) {
            entity.setTransferReversalNumber(data.get(79).getValue());
        }
        if(data.get(80) != null) {
            entity.setNumberOfInquiries(data.get(80).getValue());
        }
        if(data.get(81) != null) {
            entity.setNumberOfAuthorizations(data.get(81).getValue());
        }
        if(data.get(82) != null) {
            entity.setCreditsProcessingFeeAmount(data.get(82).getValue());
        }
        if(data.get(83) != null) {
            entity.setCreditsTransactionFeeAmount(data.get(83).getValue());
        }
        if(data.get(84) != null) {
            entity.setDebitsProcessingFeeAmount(data.get(84).getValue());
        }
        if(data.get(85) != null) {
            entity.setDebitsTransactionFeeAmount(data.get(85).getValue());
        }
        if(data.get(86) != null) {
            entity.setTotalAmountOfCredits(data.get(86).getValue());
        }
        if(data.get(87) != null) {
            entity.setCreditsReversalAmount(data.get(87).getValue());
        }
        if(data.get(88) != null) {
            entity.setTotalAmountOfDebits(data.get(88).getValue());
        }
        if(data.get(89) != null) {
            entity.setDebitsReversalAmount(data.get(89).getValue());
        }
        if(data.get(90) != null) {
            entity.setOriginalDataElements(data.get(90).getValue());
        }
        if(data.get(91) != null) {
            entity.setFileUpdateCode(data.get(91).getValue());
        }
        if(data.get(92) != null) {
            entity.setFileSecurityCode(data.get(92).getValue());
        }
        if(data.get(93) != null) {
            entity.setResponseIndicator(data.get(93).getValue());
        }
        if(data.get(94) != null) {
            entity.setServiceIndicator(data.get(94).getValue());
        }
        if(data.get(95) != null) {
            entity.setReplacementAmounts(data.get(95).getValue());
        }
        if(data.get(96) != null) {
            entity.setMessageSecurityCode(data.get(96).getValue());
        }
        if(data.get(97) != null) {
            entity.setNetSettlementAmount(data.get(97).getValue());
        }
        if(data.get(98) != null) {
            entity.setPayee(data.get(98).getValue());
        }
        if(data.get(99) != null) {
            entity.setSettlementInstitutionIdentificationCode(data.get(99).getValue());
        }
        if(data.get(100) != null) {
            entity.setReceivingInstitutionIdentificationCode(data.get(100).getValue());
        }
        if(data.get(101) != null) {
            entity.setFileName(data.get(101).getValue());
        }
        if(data.get(102) != null) {
            entity.setAccountIdentification1(data.get(102).getValue());
        }
        if(data.get(103) != null) {
            entity.setTransactionDescription(data.get(103).getValue());
        }
        if(data.get(104) != null) {
            String[] strs = new String[7];
            strs[0] = data.get(104).getValue();
            strs[1] = data.get(105).getValue();
            strs[2] = data.get(106).getValue();
            strs[3] = data.get(107).getValue();
            strs[4] = data.get(108).getValue();
            strs[5] = data.get(109).getValue();
            strs[6] = data.get(110).getValue();
            entity.setReservedForISOUse(strs);
        }
        if(data.get(111) != null) {
            String[] strs = new String[8];
            strs[0] = data.get(111).getValue();
            strs[1] = data.get(112).getValue();
            strs[2] = data.get(113).getValue();
            strs[3] = data.get(114).getValue();
            strs[4] = data.get(115).getValue();
            strs[5] = data.get(116).getValue();
            strs[6] = data.get(117).getValue();
            strs[7] = data.get(118).getValue();
            entity.setReservedForNationalUse(strs);
        }
        if(data.get(119) != null) {
            String[] strs = new String[8];
            strs[0] = data.get(119).getValue();
            strs[1] = data.get(120).getValue();
            strs[2] = data.get(121).getValue();
            strs[3] = data.get(122).getValue();
            strs[4] = data.get(123).getValue();
            strs[5] = data.get(124).getValue();
            strs[6] = data.get(125).getValue();
            strs[7] = data.get(126).getValue();
            entity.setReservedForPrivateUse(strs);
        }
        if(data.get(127) != null) {
            entity.setMessageAuthenticationCode2(data.get(127).getValue());
        }
    }

    public Map<Integer, ISOField> getDataElements() {
        return dataElements;
    }

    public void setDataElements(Map<Integer, ISOField> dataElements) {
        this.dataElements = dataElements;
    }

    public HashMap<String, String> getMti() {
        return mti;
    }

    public void setMti(HashMap<String, String> mti) {
        this.mti = mti;
    }

    public HashMap<Integer, Integer> getBitMapper() {
        return bitMapper;
    }

    public void setBitMapper(HashMap<Integer, Integer> bitMapper) {
        this.bitMapper = bitMapper;
    }

    public HashMap<Integer, String> getFieldsDescriptionMapper() {
        return fieldsDescriptionMapper;
    }

    public void setFieldsDescriptionMapper(HashMap<Integer, String> fieldsDescriptionMapper) {
        this.fieldsDescriptionMapper = fieldsDescriptionMapper;
    }

    public HashMap<Integer, ISOField> getFieldsProperties() {
        return fieldsProperties;
    }

    public void setFieldsProperties(HashMap<Integer, ISOField> fieldsProperties) {
        this.fieldsProperties = fieldsProperties;
    }

    public ISO8583Entity getEntity() {
        return entity;
    }

    public void setEntity(ISO8583Entity entity) {
        this.entity = entity;
    }

    public HashMap<Integer, String> getMessageTypesMapper() {
        return messageTypesMapper;
    }

    public void setMessageTypesMapper(HashMap<Integer, String> messageTypesMapper) {
        this.messageTypesMapper = messageTypesMapper;
    }
}
