package com.entities;

import java.sql.Time;
import java.util.Date;

public class ISO8583Entity {

    private String bitmap2;
    private String PAN;
    private long ProcessingCode;
    private long AmountTransaction;
    private long AmountSettlement;
    private long AmountCardHolderBilling;
    private Date TransmissionDateTime;
    private long CardHolderBillingFee;
    private long ConversionRateSettlement;
    private long ConversionRateCardHolderBilling;
    private long SystemTraceAuditNumber;
    private Time LocalTransactionTime;
    private Date LocalTransactionDate;
    private Date ExpirationDate;
    private Date SettlementDate;
    private Date CurrencyConversionDate;
    private Date CaptureDate;
    private String MerchantCategoryCode;
    private String AcquiringInetitution;
    private String PANExtended;
    private String ForwardingInstitution;
    private String PointOfServiceEntryMode;
    private String ApplicationPANSequenceNumber;
    private String NetworkInternationalIdentifier;
    private String PointOfServiceConditionCode;
    private String PointOfServiceCaptureCode;
    private long AuthorizingIdentificationResponseLength;
    private long AmountTransactionFee;
    private long AmountSettlementFee;
    private long AmountTransactionProcessingFee;
    private long AmountSettlementProcessingFee;
    private long AcquiringInstitutionIdentificationCode;
    private long ForwardingInstitutionIdentificationCode;
    private long PrimaryAccountNumberExtended;
    private String Track2Data;
    private String Track3Data;
    private String RetrievalReferenceNumber;
    private String AuthorizationIdentificationResponse;
    private String ResponseCode;
    private String ServiceRestrictionCode;
    private String CardAcceptorTerminalIdentification;
    private String CardAcceptorIdentificationCode;
    private String CardAcceptorNameLocation;
    private String AdditionalResponseData;
    private String Track1Data;
    private String AdditionalDataISO;
    private String AdditionalDataNational;
    private String AdditionalDataPrivate;
    private String CurrencyCodeTransaction;
    private String CurrencyCodeSettlement;
    private String CurrencyCodeCardHolderBilling;
    private String PersonalIdentificationNumberData;
    private String SecurityRelatedControlInformation;
    private String AdditionalAmounts;
    private String ICCData;
    private String ReservedISO;
    private String[] ReservedNational = new String[4];
    private String[] ReservedPrivate = new String[3];
    private String MessageAuthenticationCode1;
    private String ExtendedBitmapIndicator;
    private String SettlementCode;
    private String ExtendedPaymentCode;
    private String ReceivingInstitutionCountryCode;
    private String SettlementInstitutionCountryCode;
    private String NetworkManagementInformationCode;
    private String MessageNumber;
    private String LastMessageNumber;
    private String ActionDate;
    private long NumberOfCredits;
    private String CreditsReversalNumber;
    private String NumberOfDebits;
    private String DebitsReversalNumber;
    private String TransferNumber;
    private String TransferReversalNumber;
    private String NumberOfInquiries;
    private String NumberOfAuthorizations;
    private String CreditsProcessingFeeAmount;
    private String CreditsTransactionFeeAmount;
    private String DebitsProcessingFeeAmount;
    private String DebitsTransactionFeeAmount;
    private String TotalAmountOfCredits;
    private String CreditsReversalAmount;
    private String TotalAmountOfDebits;
    private String DebitsReversalAmount;
    private String OriginalDataElements;
    private String FileUpdateCode;
    private String FileSecurityCode;
    private String ResponseIndicator;
    private String ServiceIndicator;
    private String ReplacementAmounts;
    private String MessageSecurityCode;
    private String NetSettlementAmount;
    private String Payee;
    private String SettlementInstitutionIdentificationCode;
    private String ReceivingInstitutionIdentificationCode;
    private String FileName;
    private String AccountIdentification1;
    private String TransactionDescription;
    private String[] ReservedForISOUse = new String[7];
    private String[] ReservedForNationalUse = new String[8];
    private String[] ReservedForPrivateUse = new String[8];
    private String MessageAuthenticationCode2;

    public String getBitmap2() {
        return bitmap2;
    }

    public void setBitmap2(String bitmap2) {
        this.bitmap2 = bitmap2;
    }

    public String getPAN() {
        return PAN;
    }

    public void setPAN(String PAN) {
        this.PAN = PAN;
    }

    public long getProcessingCode() {
        return ProcessingCode;
    }

    public void setProcessingCode(long processingCode) {
        ProcessingCode = processingCode;
    }

    public long getAmountTransaction() {
        return AmountTransaction;
    }

    public void setAmountTransaction(long amountTransaction) {
        AmountTransaction = amountTransaction;
    }

    public long getAmountSettlement() {
        return AmountSettlement;
    }

    public void setAmountSettlement(long amountSettlement) {
        AmountSettlement = amountSettlement;
    }

    public long getAmountCardHolderBilling() {
        return AmountCardHolderBilling;
    }

    public void setAmountCardHolderBilling(long amountCardHolderBilling) {
        AmountCardHolderBilling = amountCardHolderBilling;
    }

    public Date getTransmissionDateTime() {
        return TransmissionDateTime;
    }

    public void setTransmissionDateTime(Date transmissionDateTime) {
        TransmissionDateTime = transmissionDateTime;
    }

    public long getCardHolderBillingFee() {
        return CardHolderBillingFee;
    }

    public void setCardHolderBillingFee(long cardHolderBillingFee) {
        CardHolderBillingFee = cardHolderBillingFee;
    }

    public long getConversionRateSettlement() {
        return ConversionRateSettlement;
    }

    public void setConversionRateSettlement(long conversionRateSettlement) {
        ConversionRateSettlement = conversionRateSettlement;
    }

    public long getConversionRateCardHolderBilling() {
        return ConversionRateCardHolderBilling;
    }

    public void setConversionRateCardHolderBilling(long conversionRateCardHolderBilling) {
        ConversionRateCardHolderBilling = conversionRateCardHolderBilling;
    }

    public long getSystemTraceAuditNumber() {
        return SystemTraceAuditNumber;
    }

    public void setSystemTraceAuditNumber(long systemTraceAuditNumber) {
        SystemTraceAuditNumber = systemTraceAuditNumber;
    }

    public Time getLocalTransactionTime() {
        return LocalTransactionTime;
    }

    public void setLocalTransactionTime(Time localTransactionTime) {
        LocalTransactionTime = localTransactionTime;
    }

    public Date getLocalTransactionDate() {
        return LocalTransactionDate;
    }

    public void setLocalTransactionDate(Date localTransactionDate) {
        LocalTransactionDate = localTransactionDate;
    }

    public Date getExpirationDate() {
        return ExpirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        ExpirationDate = expirationDate;
    }

    public Date getSettlementDate() {
        return SettlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        SettlementDate = settlementDate;
    }

    public Date getCurrencyConversionDate() {
        return CurrencyConversionDate;
    }

    public void setCurrencyConversionDate(Date currencyConversionDate) {
        CurrencyConversionDate = currencyConversionDate;
    }

    public Date getCaptureDate() {
        return CaptureDate;
    }

    public void setCaptureDate(Date captureDate) {
        CaptureDate = captureDate;
    }

    public String getMerchantCategoryCode() {
        return MerchantCategoryCode;
    }

    public void setMerchantCategoryCode(String merchantCategoryCode) {
        MerchantCategoryCode = merchantCategoryCode;
    }

    public String getAcquiringInetitution() {
        return AcquiringInetitution;
    }

    public void setAcquiringInetitution(String acquiringInetitution) {
        AcquiringInetitution = acquiringInetitution;
    }

    public String getPANExtended() {
        return PANExtended;
    }

    public void setPANExtended(String PANExtended) {
        this.PANExtended = PANExtended;
    }

    public String getForwardingInstitution() {
        return ForwardingInstitution;
    }

    public void setForwardingInstitution(String forwardingInstitution) {
        ForwardingInstitution = forwardingInstitution;
    }

    public String getPointOfServiceEntryMode() {
        return PointOfServiceEntryMode;
    }

    public void setPointOfServiceEntryMode(String pointOfServiceEntryMode) {
        PointOfServiceEntryMode = pointOfServiceEntryMode;
    }

    public String getApplicationPANSequenceNumber() {
        return ApplicationPANSequenceNumber;
    }

    public void setApplicationPANSequenceNumber(String applicationPANSequenceNumber) {
        ApplicationPANSequenceNumber = applicationPANSequenceNumber;
    }

    public String getNetworkInternationalIdentifier() {
        return NetworkInternationalIdentifier;
    }

    public void setNetworkInternationalIdentifier(String networkInternationalIdentifier) {
        NetworkInternationalIdentifier = networkInternationalIdentifier;
    }

    public String getPointOfServiceConditionCode() {
        return PointOfServiceConditionCode;
    }

    public void setPointOfServiceConditionCode(String pointOfServiceConditionCode) {
        PointOfServiceConditionCode = pointOfServiceConditionCode;
    }

    public String getPointOfServiceCaptureCode() {
        return PointOfServiceCaptureCode;
    }

    public void setPointOfServiceCaptureCode(String pointOfServiceCaptureCode) {
        PointOfServiceCaptureCode = pointOfServiceCaptureCode;
    }

    public long getAuthorizingIdentificationResponseLength() {
        return AuthorizingIdentificationResponseLength;
    }

    public void setAuthorizingIdentificationResponseLength(long authorizingIdentificationResponseLength) {
        AuthorizingIdentificationResponseLength = authorizingIdentificationResponseLength;
    }

    public long getAmountTransactionFee() {
        return AmountTransactionFee;
    }

    public void setAmountTransactionFee(long amountTransactionFee) {
        AmountTransactionFee = amountTransactionFee;
    }

    public long getAmountSettlementFee() {
        return AmountSettlementFee;
    }

    public void setAmountSettlementFee(long amountSettlementFee) {
        AmountSettlementFee = amountSettlementFee;
    }

    public long getAmountTransactionProcessingFee() {
        return AmountTransactionProcessingFee;
    }

    public void setAmountTransactionProcessingFee(long amountTransactionProcessingFee) {
        AmountTransactionProcessingFee = amountTransactionProcessingFee;
    }

    public long getAmountSettlementProcessingFee() {
        return AmountSettlementProcessingFee;
    }

    public void setAmountSettlementProcessingFee(long amountSettlementProcessingFee) {
        AmountSettlementProcessingFee = amountSettlementProcessingFee;
    }

    public long getAcquiringInstitutionIdentificationCode() {
        return AcquiringInstitutionIdentificationCode;
    }

    public void setAcquiringInstitutionIdentificationCode(long acquiringInstitutionIdentificationCode) {
        AcquiringInstitutionIdentificationCode = acquiringInstitutionIdentificationCode;
    }

    public long getForwardingInstitutionIdentificationCode() {
        return ForwardingInstitutionIdentificationCode;
    }

    public void setForwardingInstitutionIdentificationCode(long forwardingInstitutionIdentificationCode) {
        ForwardingInstitutionIdentificationCode = forwardingInstitutionIdentificationCode;
    }

    public long getPrimaryAccountNumberExtended() {
        return PrimaryAccountNumberExtended;
    }

    public void setPrimaryAccountNumberExtended(long primaryAccountNumberExtended) {
        PrimaryAccountNumberExtended = primaryAccountNumberExtended;
    }

    public String getTrack2Data() {
        return Track2Data;
    }

    public void setTrack2Data(String track2Data) {
        Track2Data = track2Data;
    }

    public String getTrack3Data() {
        return Track3Data;
    }

    public void setTrack3Data(String track3Data) {
        Track3Data = track3Data;
    }

    public String getRetrievalReferenceNumber() {
        return RetrievalReferenceNumber;
    }

    public void setRetrievalReferenceNumber(String retrievalReferenceNumber) {
        RetrievalReferenceNumber = retrievalReferenceNumber;
    }

    public String getAuthorizationIdentificationResponse() {
        return AuthorizationIdentificationResponse;
    }

    public void setAuthorizationIdentificationResponse(String authorizationIdentificationResponse) {
        AuthorizationIdentificationResponse = authorizationIdentificationResponse;
    }

    public String getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(String responseCode) {
        ResponseCode = responseCode;
    }

    public String getServiceRestrictionCode() {
        return ServiceRestrictionCode;
    }

    public void setServiceRestrictionCode(String serviceRestrictionCode) {
        ServiceRestrictionCode = serviceRestrictionCode;
    }

    public String getCardAcceptorTerminalIdentification() {
        return CardAcceptorTerminalIdentification;
    }

    public void setCardAcceptorTerminalIdentification(String cardAcceptorTerminalIdentification) {
        CardAcceptorTerminalIdentification = cardAcceptorTerminalIdentification;
    }

    public String getCardAcceptorIdentificationCode() {
        return CardAcceptorIdentificationCode;
    }

    public void setCardAcceptorIdentificationCode(String cardAcceptorIdentificationCode) {
        CardAcceptorIdentificationCode = cardAcceptorIdentificationCode;
    }

    public String getCardAcceptorNameLocation() {
        return CardAcceptorNameLocation;
    }

    public void setCardAcceptorNameLocation(String cardAcceptorNameLocation) {
        CardAcceptorNameLocation = cardAcceptorNameLocation;
    }

    public String getAdditionalResponseData() {
        return AdditionalResponseData;
    }

    public void setAdditionalResponseData(String additionalResponseData) {
        AdditionalResponseData = additionalResponseData;
    }

    public String getTrack1Data() {
        return Track1Data;
    }

    public void setTrack1Data(String track1Data) {
        Track1Data = track1Data;
    }

    public String getAdditionalDataISO() {
        return AdditionalDataISO;
    }

    public void setAdditionalDataISO(String additionalDataISO) {
        AdditionalDataISO = additionalDataISO;
    }

    public String getAdditionalDataNational() {
        return AdditionalDataNational;
    }

    public void setAdditionalDataNational(String additionalDataNational) {
        AdditionalDataNational = additionalDataNational;
    }

    public String getAdditionalDataPrivate() {
        return AdditionalDataPrivate;
    }

    public void setAdditionalDataPrivate(String additionalDataPrivate) {
        AdditionalDataPrivate = additionalDataPrivate;
    }

    public String getCurrencyCodeTransaction() {
        return CurrencyCodeTransaction;
    }

    public void setCurrencyCodeTransaction(String currencyCodeTransaction) {
        CurrencyCodeTransaction = currencyCodeTransaction;
    }

    public String getCurrencyCodeSettlement() {
        return CurrencyCodeSettlement;
    }

    public void setCurrencyCodeSettlement(String currencyCodeSettlement) {
        CurrencyCodeSettlement = currencyCodeSettlement;
    }

    public String getCurrencyCodeCardHolderBilling() {
        return CurrencyCodeCardHolderBilling;
    }

    public void setCurrencyCodeCardHolderBilling(String currencyCodeCardHolderBilling) {
        CurrencyCodeCardHolderBilling = currencyCodeCardHolderBilling;
    }

    public String getPersonalIdentificationNumberData() {
        return PersonalIdentificationNumberData;
    }

    public void setPersonalIdentificationNumberData(String personalIdentificationNumberData) {
        PersonalIdentificationNumberData = personalIdentificationNumberData;
    }

    public String getSecurityRelatedControlInformation() {
        return SecurityRelatedControlInformation;
    }

    public void setSecurityRelatedControlInformation(String securityRelatedControlInformation) {
        SecurityRelatedControlInformation = securityRelatedControlInformation;
    }

    public String getAdditionalAmounts() {
        return AdditionalAmounts;
    }

    public void setAdditionalAmounts(String additionalAmounts) {
        AdditionalAmounts = additionalAmounts;
    }

    public String getICCData() {
        return ICCData;
    }

    public void setICCData(String ICCData) {
        this.ICCData = ICCData;
    }

    public String getReservedISO() {
        return ReservedISO;
    }

    public void setReservedISO(String reservedISO) {
        ReservedISO = reservedISO;
    }

    public String[] getReservedNational() {
        return ReservedNational;
    }

    public void setReservedNational(String[] reservedNational) {
        ReservedNational = reservedNational;
    }

    public String[] getReservedPrivate() {
        return ReservedPrivate;
    }

    public void setReservedPrivate(String[] reservedPrivate) {
        ReservedPrivate = reservedPrivate;
    }

    public String getMessageAuthenticationCode1() {
        return MessageAuthenticationCode1;
    }

    public void setMessageAuthenticationCode1(String messageAuthenticationCode1) {
        MessageAuthenticationCode1 = messageAuthenticationCode1;
    }

    public String getExtendedBitmapIndicator() {
        return ExtendedBitmapIndicator;
    }

    public void setExtendedBitmapIndicator(String extendedBitmapIndicator) {
        ExtendedBitmapIndicator = extendedBitmapIndicator;
    }

    public String getSettlementCode() {
        return SettlementCode;
    }

    public void setSettlementCode(String settlementCode) {
        SettlementCode = settlementCode;
    }

    public String getExtendedPaymentCode() {
        return ExtendedPaymentCode;
    }

    public void setExtendedPaymentCode(String extendedPaymentCode) {
        ExtendedPaymentCode = extendedPaymentCode;
    }

    public String getReceivingInstitutionCountryCode() {
        return ReceivingInstitutionCountryCode;
    }

    public void setReceivingInstitutionCountryCode(String receivingInstitutionCountryCode) {
        ReceivingInstitutionCountryCode = receivingInstitutionCountryCode;
    }

    public String getSettlementInstitutionCountryCode() {
        return SettlementInstitutionCountryCode;
    }

    public void setSettlementInstitutionCountryCode(String settlementInstitutionCountryCode) {
        SettlementInstitutionCountryCode = settlementInstitutionCountryCode;
    }

    public String getNetworkManagementInformationCode() {
        return NetworkManagementInformationCode;
    }

    public void setNetworkManagementInformationCode(String networkManagementInformationCode) {
        NetworkManagementInformationCode = networkManagementInformationCode;
    }

    public String getMessageNumber() {
        return MessageNumber;
    }

    public void setMessageNumber(String messageNumber) {
        MessageNumber = messageNumber;
    }

    public String getLastMessageNumber() {
        return LastMessageNumber;
    }

    public void setLastMessageNumber(String lastMessageNumber) {
        LastMessageNumber = lastMessageNumber;
    }

    public String getActionDate() {
        return ActionDate;
    }

    public void setActionDate(String actionDate) {
        ActionDate = actionDate;
    }

    public long getNumberOfCredits() {
        return NumberOfCredits;
    }

    public void setNumberOfCredits(long numberOfCredits) {
        NumberOfCredits = numberOfCredits;
    }

    public String getCreditsReversalNumber() {
        return CreditsReversalNumber;
    }

    public void setCreditsReversalNumber(String creditsReversalNumber) {
        CreditsReversalNumber = creditsReversalNumber;
    }

    public String getNumberOfDebits() {
        return NumberOfDebits;
    }

    public void setNumberOfDebits(String numberOfDebits) {
        NumberOfDebits = numberOfDebits;
    }

    public String getDebitsReversalNumber() {
        return DebitsReversalNumber;
    }

    public void setDebitsReversalNumber(String debitsReversalNumber) {
        DebitsReversalNumber = debitsReversalNumber;
    }

    public String getTransferNumber() {
        return TransferNumber;
    }

    public void setTransferNumber(String transferNumber) {
        TransferNumber = transferNumber;
    }

    public String getTransferReversalNumber() {
        return TransferReversalNumber;
    }

    public void setTransferReversalNumber(String transferReversalNumber) {
        TransferReversalNumber = transferReversalNumber;
    }

    public String getNumberOfInquiries() {
        return NumberOfInquiries;
    }

    public void setNumberOfInquiries(String numberOfInquiries) {
        NumberOfInquiries = numberOfInquiries;
    }

    public String getNumberOfAuthorizations() {
        return NumberOfAuthorizations;
    }

    public void setNumberOfAuthorizations(String numberOfAuthorizations) {
        NumberOfAuthorizations = numberOfAuthorizations;
    }

    public String getCreditsProcessingFeeAmount() {
        return CreditsProcessingFeeAmount;
    }

    public void setCreditsProcessingFeeAmount(String creditsProcessingFeeAmount) {
        CreditsProcessingFeeAmount = creditsProcessingFeeAmount;
    }

    public String getCreditsTransactionFeeAmount() {
        return CreditsTransactionFeeAmount;
    }

    public void setCreditsTransactionFeeAmount(String creditsTransactionFeeAmount) {
        CreditsTransactionFeeAmount = creditsTransactionFeeAmount;
    }

    public String getDebitsProcessingFeeAmount() {
        return DebitsProcessingFeeAmount;
    }

    public void setDebitsProcessingFeeAmount(String debitsProcessingFeeAmount) {
        DebitsProcessingFeeAmount = debitsProcessingFeeAmount;
    }

    public String getDebitsTransactionFeeAmount() {
        return DebitsTransactionFeeAmount;
    }

    public void setDebitsTransactionFeeAmount(String debitsTransactionFeeAmount) {
        DebitsTransactionFeeAmount = debitsTransactionFeeAmount;
    }

    public String getTotalAmountOfCredits() {
        return TotalAmountOfCredits;
    }

    public void setTotalAmountOfCredits(String totalAmountOfCredits) {
        TotalAmountOfCredits = totalAmountOfCredits;
    }

    public String getCreditsReversalAmount() {
        return CreditsReversalAmount;
    }

    public void setCreditsReversalAmount(String creditsReversalAmount) {
        CreditsReversalAmount = creditsReversalAmount;
    }

    public String getTotalAmountOfDebits() {
        return TotalAmountOfDebits;
    }

    public void setTotalAmountOfDebits(String totalAmountOfDebits) {
        TotalAmountOfDebits = totalAmountOfDebits;
    }

    public String getDebitsReversalAmount() {
        return DebitsReversalAmount;
    }

    public void setDebitsReversalAmount(String debitsReversalAmount) {
        DebitsReversalAmount = debitsReversalAmount;
    }

    public String getOriginalDataElements() {
        return OriginalDataElements;
    }

    public void setOriginalDataElements(String originalDataElements) {
        OriginalDataElements = originalDataElements;
    }

    public String getFileUpdateCode() {
        return FileUpdateCode;
    }

    public void setFileUpdateCode(String fileUpdateCode) {
        FileUpdateCode = fileUpdateCode;
    }

    public String getFileSecurityCode() {
        return FileSecurityCode;
    }

    public void setFileSecurityCode(String fileSecurityCode) {
        FileSecurityCode = fileSecurityCode;
    }

    public String getResponseIndicator() {
        return ResponseIndicator;
    }

    public void setResponseIndicator(String responseIndicator) {
        ResponseIndicator = responseIndicator;
    }

    public String getServiceIndicator() {
        return ServiceIndicator;
    }

    public void setServiceIndicator(String serviceIndicator) {
        ServiceIndicator = serviceIndicator;
    }

    public String getReplacementAmounts() {
        return ReplacementAmounts;
    }

    public void setReplacementAmounts(String replacementAmounts) {
        ReplacementAmounts = replacementAmounts;
    }

    public String getMessageSecurityCode() {
        return MessageSecurityCode;
    }

    public void setMessageSecurityCode(String messageSecurityCode) {
        MessageSecurityCode = messageSecurityCode;
    }

    public String getNetSettlementAmount() {
        return NetSettlementAmount;
    }

    public void setNetSettlementAmount(String netSettlementAmount) {
        NetSettlementAmount = netSettlementAmount;
    }

    public String getPayee() {
        return Payee;
    }

    public void setPayee(String payee) {
        Payee = payee;
    }

    public String getSettlementInstitutionIdentificationCode() {
        return SettlementInstitutionIdentificationCode;
    }

    public void setSettlementInstitutionIdentificationCode(String settlementInstitutionIdentificationCode) {
        SettlementInstitutionIdentificationCode = settlementInstitutionIdentificationCode;
    }

    public String getReceivingInstitutionIdentificationCode() {
        return ReceivingInstitutionIdentificationCode;
    }

    public void setReceivingInstitutionIdentificationCode(String receivingInstitutionIdentificationCode) {
        ReceivingInstitutionIdentificationCode = receivingInstitutionIdentificationCode;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getAccountIdentification1() {
        return AccountIdentification1;
    }

    public void setAccountIdentification1(String accountIdentification1) {
        AccountIdentification1 = accountIdentification1;
    }

    public String getTransactionDescription() {
        return TransactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        TransactionDescription = transactionDescription;
    }

    public String[] getReservedForISOUse() {
        return ReservedForISOUse;
    }

    public void setReservedForISOUse(String[] reservedForISOUse) {
        ReservedForISOUse = reservedForISOUse;
    }

    public String[] getReservedForNationalUse() {
        return ReservedForNationalUse;
    }

    public void setReservedForNationalUse(String[] reservedForNationalUse) {
        ReservedForNationalUse = reservedForNationalUse;
    }

    public String[] getReservedForPrivateUse() {
        return ReservedForPrivateUse;
    }

    public void setReservedForPrivateUse(String[] reservedForPrivateUse) {
        ReservedForPrivateUse = reservedForPrivateUse;
    }

    public String getMessageAuthenticationCode2() {
        return MessageAuthenticationCode2;
    }

    public void setMessageAuthenticationCode2(String messageAuthenticationCode2) {
        MessageAuthenticationCode2 = messageAuthenticationCode2;
    }
}
