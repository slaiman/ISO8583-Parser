package com.parser;

import java.util.HashMap;

public class Initializer {

    public static HashMap<Integer,String> initializeFieldsMapper() {
        HashMap<Integer,String> FieldsMapper = new HashMap<>();
        //FieldsMapper.put(1, "Bitmap");
        int i = 1;
        FieldsMapper.put(i, "Primary account number (PAN)");
        FieldsMapper.put(++i, "Processing Code");
        FieldsMapper.put(++i, "Amount Transaction");
        FieldsMapper.put(++i, "Amount, settlement");
        FieldsMapper.put(++i, "Amount, cardholder billing");
        FieldsMapper.put(++i, "Transmission date & time");
        FieldsMapper.put(++i, "Amount, cardholder billing fee");
        FieldsMapper.put(++i, "Conversion rate, settlement");
        FieldsMapper.put(++i, "Conversion rate, cardholder billing");
        FieldsMapper.put(++i, "System trace audit number (STAN)");
        FieldsMapper.put(++i, "Local transaction time (hhmmss)");
        FieldsMapper.put(++i, "Local transaction date (MMDD)");
        FieldsMapper.put(++i, "Expiration date (YYMM)");
        FieldsMapper.put(++i, "Settlement date");
        FieldsMapper.put(++i, "Currency conversion date");
        FieldsMapper.put(++i, "Capture date");
        FieldsMapper.put(++i, "Merchant type, or merchant category code");
        FieldsMapper.put(++i, "Acquiring institution (country code)");
        FieldsMapper.put(++i, "PAN extended (country code)");
        FieldsMapper.put(++i, "Forwarding institution (country code)");
        FieldsMapper.put(++i, "Point of service entry mode");
        FieldsMapper.put(++i, "Application PAN sequence number");
        FieldsMapper.put(++i, "Function code (ISO 8583:1993), or network international identifier (NII)");
        FieldsMapper.put(++i, "Point of service condition code");
        FieldsMapper.put(++i, "Point of service capture code");
        FieldsMapper.put(++i, "Authorizing identification response length");
        FieldsMapper.put(++i, "Amount, transaction fee");
        FieldsMapper.put(++i, "Amount, settlement fee");
        FieldsMapper.put(++i, "Amount, transaction processing fee");
        FieldsMapper.put(++i, "Amount, settlement processing fee");
        FieldsMapper.put(++i, "Acquiring institution identification code");
        FieldsMapper.put(++i, "Forwarding institution identification code");
        FieldsMapper.put(++i, "Primary account number, extended");
        FieldsMapper.put(++i, "Track 2 data");
        FieldsMapper.put(++i, "Track 3 data");
        FieldsMapper.put(++i, "Retrieval reference number");
        FieldsMapper.put(++i, "Authorization identification response");
        FieldsMapper.put(++i, "Response code");
        FieldsMapper.put(++i, "Service restriction code");
        FieldsMapper.put(++i, "Card acceptor terminal identification");
        FieldsMapper.put(++i, "Card acceptor identification code");
        FieldsMapper.put(++i, "Card acceptor name/location (1–23 street address, –36 city, –38 state, 39–40 country)");
        FieldsMapper.put(++i, "Additional response data");
        FieldsMapper.put(++i, "Track 1 data");
        FieldsMapper.put(++i, "Additional data (ISO)");
        FieldsMapper.put(++i, "Additional data (national)");
        FieldsMapper.put(++i, "Additional data (private)");
        FieldsMapper.put(++i, "Currency code, transaction");
        FieldsMapper.put(++i, "Currency code, settlement");
        FieldsMapper.put(++i, "Currency code, cardholder billing");
        FieldsMapper.put(++i, "Personal identification number data");
        FieldsMapper.put(++i, "Security related control information");
        FieldsMapper.put(++i, "Additional amounts");
        FieldsMapper.put(++i, "ICC data – EMV having multiple tags");
        FieldsMapper.put(++i, "Reserved (ISO)");
        FieldsMapper.put(++i, "Reserved (national)");
        FieldsMapper.put(++i, "Reserved (national)");
        FieldsMapper.put(++i, "Reserved (national)");
        FieldsMapper.put(++i, "Reserved (national)");
        FieldsMapper.put(++i, "Reserved (private)");
        FieldsMapper.put(++i, "Reserved (private)");
        FieldsMapper.put(++i, "Reserved (private)");
        FieldsMapper.put(++i, "Message authentication code (MAC)");
        FieldsMapper.put(++i, "Extended bitmap indicator");
        FieldsMapper.put(++i, "Settlement code");
        FieldsMapper.put(++i, "Extended payment code");
        FieldsMapper.put(++i, "Receiving institution country code");
        FieldsMapper.put(++i, "Settlement institution country code");
        FieldsMapper.put(++i, "Network management information code");
        FieldsMapper.put(++i, "Message number");
        FieldsMapper.put(++i, "Last message's number");
        FieldsMapper.put(++i, "Action date (YYMMDD)");
        FieldsMapper.put(++i, "Number of credits");
        FieldsMapper.put(++i, "Credits, reversal number");
        FieldsMapper.put(++i, "Number of debits");
        FieldsMapper.put(++i, "Debits, reversal number");
        FieldsMapper.put(++i, "Transfer number");
        FieldsMapper.put(++i, "Transfer, reversal number");
        FieldsMapper.put(++i, "Number of inquiries");
        FieldsMapper.put(++i, "Number of authorizations");
        FieldsMapper.put(++i, "Credits, processing fee amount");
        FieldsMapper.put(++i, "Credits, transaction fee amount");
        FieldsMapper.put(++i, "Debits, processing fee amount");
        FieldsMapper.put(++i, "Debits, transaction fee amount");
        FieldsMapper.put(++i, "Total amount of credits");
        FieldsMapper.put(++i, "Credits, reversal amount");
        FieldsMapper.put(++i, "Total amount of debits");
        FieldsMapper.put(++i, "Debits, reversal amount");
        FieldsMapper.put(++i, "Original data elements");
        FieldsMapper.put(++i, "File update code");
        FieldsMapper.put(++i, "File security code");
        FieldsMapper.put(++i, "Response indicator");
        FieldsMapper.put(++i, "Service indicator");
        FieldsMapper.put(++i, "Replacement amounts");
        FieldsMapper.put(++i, "Message security code");
        FieldsMapper.put(++i, "Net settlement amount");
        FieldsMapper.put(++i, "Payee");
        FieldsMapper.put(++i, "Settlement institution identification code");
        FieldsMapper.put(++i, "Receiving institution identification code");
        FieldsMapper.put(++i, "File name");
        FieldsMapper.put(++i, "Account identification 1");
        FieldsMapper.put(++i, "Account identification 2");
        FieldsMapper.put(++i, "Transaction description");
        FieldsMapper.put(++i, "Reserved for ISO use");
        FieldsMapper.put(++i, "Reserved for ISO use");
        FieldsMapper.put(++i, "Reserved for ISO use");
        FieldsMapper.put(++i, "Reserved for ISO use");
        FieldsMapper.put(++i, "Reserved for ISO use");
        FieldsMapper.put(++i, "Reserved for ISO use");
        FieldsMapper.put(++i, "Reserved for ISO use");
        FieldsMapper.put(++i, "Reserved for national use");
        FieldsMapper.put(++i, "Reserved for national use");
        FieldsMapper.put(++i, "Reserved for national use");
        FieldsMapper.put(++i, "Reserved for national use");
        FieldsMapper.put(++i, "Reserved for national use");
        FieldsMapper.put(++i, "Reserved for national use");
        FieldsMapper.put(++i, "Reserved for national use");
        FieldsMapper.put(++i, "Reserved for national use");
        FieldsMapper.put(++i, "Reserved for private use");
        FieldsMapper.put(++i, "Reserved for private use");
        FieldsMapper.put(++i, "Reserved for private use");
        FieldsMapper.put(++i, "Reserved for private use");
        FieldsMapper.put(++i, "Reserved for private use");
        FieldsMapper.put(++i, "Reserved for private use");
        FieldsMapper.put(++i, "Reserved for private use");
        FieldsMapper.put(++i, "Reserved for private use");
        FieldsMapper.put(++i, "Message authentication code");
        return FieldsMapper;
    }

    public static HashMap<Integer,ISOField> initializeFieldProperties() {
        HashMap<Integer,ISOField> FieldsProperties = new HashMap<>();

        /*iso.id = 0;
        iso.length = 4;
        iso.name = "MESSAGE TYPE INDICATOR";
        FieldsProperties.put(0,iso);

        iso.id = 1;
        iso.length = 16;
        iso.name = "BIT MAP";
        FieldsProperties.put(1, iso);*/
        ISOField iso = new ISOField();
        iso.id = 1;
        iso.length = 19;
        iso.name = "SECRET ID";
        iso.type = "IFA_LLNUM";
        FieldsProperties.put(1, iso);

        iso = new ISOField();
        iso.id = 2;
        iso.length = 6;
        iso.type = "IFA_NUMERIC";
        iso.name = "PROCESSING CODE";
        FieldsProperties.put(2, iso);

        iso = new ISOField();
        iso.id = 3;
        iso.length = 12;
        iso.type = "IFA_NUMERIC";
        iso.name = "AMOUNT, TRANSACTION";
        FieldsProperties.put(3, iso);

        iso = new ISOField();
        iso.id = 4;
        iso.length = 12;
        iso.name = "AMOUNT, SETTLEMENT";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(4, iso);

        iso = new ISOField();
        iso.id = 5;
        iso.length = 12;
        iso.name = "AMOUNT, CARDHOLDER BILLING";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(5, iso);

        iso = new ISOField();
        iso.id = 6;
        iso.length = 10;
        iso.name = "TRANSMISSION DATE AND TIME";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(6, iso);

        iso = new ISOField();
        iso.id = 7;
        iso.length = 8;
        iso.name = "AMOUNT, CARDHOLDER BILLING FEE";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(7, iso);

        iso = new ISOField();
        iso.id = 8;
        iso.length = 8;
        iso.name = "CONVERSION RATE, SETTLEMENT";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(8, iso);

        iso = new ISOField();
        iso.id = 9;
        iso.length = 8;
        iso.name = "CONVERSION RATE, CARDHOLDER BILLING";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(9, iso);

        iso = new ISOField();
        iso.id = 10;
        iso.length = 6;
        iso.name = "SYSTEM TRACE AUDIT NUMBER";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(10, iso);

        iso = new ISOField();
        iso.id = 11;
        iso.length = 6;
        iso.name = "TIME, LOCAL TRANSACTION";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(11, iso);

        iso = new ISOField();
        iso.id = 12;
        iso.length = 4;
        iso.name = "DATE, LOCAL TRANSACTION";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(12, iso);

        iso = new ISOField();
        iso.id = 13;
        iso.length = 4;
        iso.name = "DATE, EXPIRATION";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(13, iso);

        iso = new ISOField();
        iso.id = 14;
        iso.length = 4;
        iso.name = "DATE, SETTLEMENT";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(14, iso);

        iso = new ISOField();
        iso.id = 15;
        iso.length = 4;
        iso.name = "DATE, CONVERSION";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(15, iso);

        iso = new ISOField();
        iso.id = 16;
        iso.length = 4;
        iso.name = "DATE, CAPTURE";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(16, iso);

        iso = new ISOField();
        iso.id = 17;
        iso.length = 4;
        iso.name = "MERCHANTS TYPE";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(17, iso);

        iso = new ISOField();
        iso.id = 18;
        iso.length = 3;
        iso.name = "ACQUIRING INSTITUTION COUNTRY CODE";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(18, iso);

        iso = new ISOField();
        iso.id = 19;
        iso.length = 3;
        iso.name = "PAN EXTENDED COUNTRY CODE";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(19, iso);

        iso = new ISOField();
        iso.id = 20;
        iso.length = 3;
        iso.name = "FORWARDING INSTITUTION COUNTRY CODE";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(20, iso);

        iso = new ISOField();
        iso.id = 21;
        iso.length = 3;
        iso.name = "POINT OF SERVICE ENTRY MODE";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(21, iso);

        iso = new ISOField();
        iso.id = 22;
        iso.length = 3;
        iso.name = "CARD SEQUENCE NUMBER";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(22, iso);

        iso = new ISOField();
        iso.id = 23;
        iso.length = 3;
        iso.name = "NETWORK INTERNATIONAL IDENTIFIEER";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(23, iso);

        iso = new ISOField();
        iso.id = 24;
        iso.length = 2;
        iso.name = "POINT OF SERVICE CONDITION CODE";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(24, iso);

        iso = new ISOField();
        iso.id = 25;
        iso.length = 2;
        iso.name = "POINT OF SERVICE PIN CAPTURE CODE";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(25, iso);

        iso = new ISOField();
        iso.id = 26;
        iso.length = 1;
        iso.name = "AUTHORIZATION IDENTIFICATION RESP LEN";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(26, iso);

        iso = new ISOField();
        iso.id = 27;
        iso.length = 9;
        iso.name = "AMOUNT, TRANSACTION FEE";
        iso.type = "IFA_AMOUNT";
        FieldsProperties.put(27, iso);

        iso = new ISOField();
        iso.id = 28;
        iso.length = 9;
        iso.name = "AMOUNT, SETTLEMENT FEE";
        iso.type = "IFA_AMOUNT";
        FieldsProperties.put(28, iso);

        iso = new ISOField();
        iso.id = 29;
        iso.length = 9;
        iso.name = "AMOUNT, TRANSACTION PROCESSING FEE";
        iso.type = "IFA_AMOUNT";
        FieldsProperties.put(29, iso);

        iso = new ISOField();
        iso.id = 30;
        iso.length = 9;
        iso.name = "AMOUNT, SETTLEMENT PROCESSING FEE";
        iso.type = "IFA_AMOUNT";
        FieldsProperties.put(30, iso);

        iso = new ISOField();
        iso.id = 31;
        iso.length = 11;
        iso.name = "ACQUIRING INSTITUTION IDENT CODE";
        iso.type = "IFA_LLNUM";
        FieldsProperties.put(31, iso);

        iso = new ISOField();
        iso.id = 32;
        iso.length = 11;
        iso.name = "FORWARDING INSTITUTION IDENT CODE";
        iso.type = "IFA_LLNUM";
        FieldsProperties.put(32, iso);

        iso = new ISOField();
        iso.id = 33;
        iso.length = 28;
        iso.name = "PAN EXTENDED";
        iso.type = "IFA_LLCHAR";
        FieldsProperties.put(33, iso);

        iso = new ISOField();
        iso.id = 34;
        iso.length = 37;
        iso.name = "TRACK 2 DATA";
        iso.type = "IFA_LLNUM";
        FieldsProperties.put(34, iso);

        iso = new ISOField();
        iso.id = 35;
        iso.length = 104;
        iso.name = "TRACK 3 DATA";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(35, iso);

        iso = new ISOField();
        iso.id = 36;
        iso.length = 12;
        iso.name = "RETRIEVAL REFERENCE NUMBER";
        iso.type = "IF_CHAR";
        FieldsProperties.put(36, iso);

        iso = new ISOField();
        iso.id = 37;
        iso.length = 6;
        iso.name = "AUTHORIZATION IDENTIFICATION RESPONSE";
        iso.type = "IF_CHAR";
        FieldsProperties.put(37, iso);

        iso = new ISOField();
        iso.id = 38;
        iso.length = 2;
        iso.name = "RESPONSE CODE";
        iso.type = "IF_CHAR";
        FieldsProperties.put(38, iso);

        iso = new ISOField();
        iso.id = 39;
        iso.length = 3;
        iso.name = "SERVICE RESTRICTION CODE";
        iso.type = "IF_CHAR";
        FieldsProperties.put(39, iso);

        iso = new ISOField();
        iso.id = 40;
        iso.length = 8;
        iso.name = "CARD ACCEPTOR TERMINAL IDENTIFICACION";
        iso.type = "IF_CHAR";
        FieldsProperties.put(40, iso);

        iso = new ISOField();
        iso.id = 41;
        iso.length = 15;
        iso.name = "CARD ACCEPTOR IDENTIFICATION CODE";
        iso.type = "IF_CHAR";
        FieldsProperties.put(41, iso);

        iso = new ISOField();
        iso.id = 42;
        iso.length = 40;
        iso.name = "CARD ACCEPTOR NAME/LOCATION";
        iso.type = "IF_CHAR";
        FieldsProperties.put(42, iso);

        iso = new ISOField();
        iso.id = 43;
        iso.length = 25;
        iso.name = "ADITIONAL RESPONSE DATA";
        iso.type = "IFA_LLCHAR";
        FieldsProperties.put(43, iso);

        iso = new ISOField();
        iso.id = 44;
        iso.length = 76;
        iso.name = "TRACK 1 DATA";
        iso.type = "IFA_LLCHAR";
        FieldsProperties.put(44, iso);

        iso = new ISOField();
        iso.id = 45;
        iso.length = 999;
        iso.name = "ADITIONAL DATA - ISO";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(45, iso);

        iso = new ISOField();
        iso.id = 46;
        iso.length = 999;
        iso.name = "ADITIONAL DATA - NATIONAL";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(46, iso);

        iso = new ISOField();
        iso.id = 47;
        iso.length = 999;
        iso.name = "ADITIONAL DATA - PRIVATE";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(47, iso);

        iso = new ISOField();
        iso.id = 48;
        iso.length = 3;
        iso.name = "CURRENCY CODE, TRANSACTION";
        iso.type = "IF_CHAR";
        FieldsProperties.put(48, iso);

        iso = new ISOField();
        iso.id = 49;
        iso.length = 3;
        iso.name = "CURRENCY CODE, SETTLEMENT";
        iso.type = "IF_CHAR";
        FieldsProperties.put(49, iso);

        iso = new ISOField();
        iso.id = 50;
        iso.length = 3;
        iso.name = "CURRENCY CODE, CARDHOLDER BILLING";
        iso.type = "IF_CHAR";
        FieldsProperties.put(50, iso);

        iso = new ISOField();
        iso.id = 51;
        iso.length = 16;
        iso.name = "PIN DATA";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(51, iso);

        iso = new ISOField();
        iso.id = 52;
        iso.length = 16;
        iso.name = "SECURITY RELATED CONTROL INFORMATION";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(52, iso);

        iso = new ISOField();
        iso.id = 53;
        iso.length = 120;
        iso.name = "ADDITIONAL AMOUNTS";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(53, iso);

        iso = new ISOField();
        iso.id = 54;
        iso.length = 999;
        iso.name = "RESERVED ISO";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(54, iso);

        iso = new ISOField();
        iso.id = 55;
        iso.length = 999;
        iso.name = "RESERVED ISO";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(55, iso);

        iso = new ISOField();
        iso.id = 56;
        iso.length = 999;
        iso.name = "RESERVED NATIONAL";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(56, iso);

        iso = new ISOField();
        iso.id = 57;
        iso.length = 999;
        iso.name = "RESERVED NATIONAL";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(57, iso);

        iso = new ISOField();
        iso.id = 58;
        iso.length = 999;
        iso.name = "RESERVED NATIONAL";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(58, iso);

        iso = new ISOField();
        iso.id = 59;
        iso.length = 999;
        iso.name = "RESERVED PRIVATE";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(59, iso);

        iso = new ISOField();
        iso.id = 60;
        iso.length = 999;
        iso.name = "RESERVED PRIVATE";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(60, iso);

        iso = new ISOField();
        iso.id = 61;
        iso.length = 999;
        iso.name = "RESERVED PRIVATE";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(61, iso);

        iso = new ISOField();
        iso.id = 62;
        iso.length = 999;
        iso.name = "RESERVED PRIVATE";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(62, iso);

        iso = new ISOField();
        iso.id = 63;
        iso.length = 8;
        iso.name = "MESSAGE AUTHENTICATION CODE FIELD";
        iso.type = "IFA_BINARY";
        FieldsProperties.put(63, iso);

        iso = new ISOField();
        iso.id = 64;
        iso.length = 1;
        iso.name = "BITMAP, EXTENDED";
        iso.type = "IFA_BINARY";
        FieldsProperties.put(64, iso);

        iso = new ISOField();
        iso.id = 65;
        iso.length = 1;
        iso.name = "SETTLEMENT CODE";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(65, iso);

        iso = new ISOField();
        iso.id = 66;
        iso.length = 2;
        iso.name = "EXTENDED PAYMENT CODE";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(66, iso);

        iso = new ISOField();
        iso.id = 67;
        iso.length = 3;
        iso.name = "RECEIVING INSTITUTION COUNTRY CODE";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(67, iso);

        iso = new ISOField();
        iso.id = 68;
        iso.length = 3;
        iso.name = "SETTLEMENT INSTITUTION COUNTRY CODE";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(68, iso);

        iso = new ISOField();
        iso.id = 69;
        iso.length = 3;
        iso.name = "NETWORK MANAGEMENT INFORMATION CODE";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(69, iso);

        iso = new ISOField();
        iso.id = 70;
        iso.length = 4;
        iso.name = "MESSAGE NUMBER";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(70, iso);

        iso = new ISOField();
        iso.id = 71;
        iso.length = 4;
        iso.name = "MESSAGE NUMBER LAST";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(71, iso);

        iso = new ISOField();
        iso.id = 72;
        iso.length = 6;
        iso.name = "DATE ACTION";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(72, iso);

        iso = new ISOField();
        iso.id = 73;
        iso.length = 10;
        iso.name = "CREDITS NUMBER";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(73, iso);

        iso = new ISOField();
        iso.id = 74;
        iso.length = 10;
        iso.name = "CREDITS REVERSAL NUMBER";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(74, iso);

        iso = new ISOField();
        iso.id = 75;
        iso.length = 10;
        iso.name = "DEBITS NUMBER";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(75, iso);

        iso = new ISOField();
        iso.id = 76;
        iso.length = 10;
        iso.name = "DEBITS REVERSAL NUMBER";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(76, iso);

        iso = new ISOField();
        iso.id = 77;
        iso.length = 10;
        iso.name = "TRANSFER NUMBER";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(77, iso);

        iso = new ISOField();
        iso.id = 78;
        iso.length = 10;
        iso.name = "TRANSFER REVERSAL NUMBER";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(78, iso);

        iso = new ISOField();
        iso.id = 79;
        iso.length = 10;
        iso.name = "INQUIRIES NUMBER";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(79, iso);

        iso = new ISOField();
        iso.id = 80;
        iso.length = 10;
        iso.name = "AUTHORIZATION NUMBER";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(80, iso);

        iso = new ISOField();
        iso.id = 81;
        iso.length = 12;
        iso.name = "CREDITS, PROCESSING FEE AMOUNT";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(81, iso);

        iso = new ISOField();
        iso.id = 82;
        iso.length = 12;
        iso.name = "CREDITS, TRANSACTION FEE AMOUNT";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(82, iso);

        iso = new ISOField();
        iso.id = 83;
        iso.length = 12;
        iso.name = "DEBITS, PROCESSING FEE AMOUNT";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(83, iso);

        iso = new ISOField();
        iso.id = 84;
        iso.length = 12;
        iso.name = "DEBITS, TRANSACTION FEE AMOUNT";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(84, iso);

        iso = new ISOField();
        iso.id = 85;
        iso.length = 16;
        iso.name = "CREDITS, AMOUNT";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(85, iso);

        iso = new ISOField();
        iso.id = 86;
        iso.length = 16;
        iso.name = "CREDITS, REVERSAL AMOUNT";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(86, iso);

        iso = new ISOField();
        iso.id = 87;
        iso.length = 16;
        iso.name = "DEBITS, AMOUNT";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(87, iso);

        iso = new ISOField();
        iso.id = 88;
        iso.length = 16;
        iso.name = "DEBITS, REVERSAL AMOUNT";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(88, iso);

        iso = new ISOField();
        iso.id = 89;
        iso.length = 42;
        iso.name = "ORIGINAL DATA ELEMENTS";
        iso.type = "IFA_NUMERIC";
        FieldsProperties.put(89, iso);

        iso = new ISOField();
        iso.id = 90;
        iso.length = 1;
        iso.name = "FILE UPDATE CODE";
        iso.type = "IF_CHAR";
        FieldsProperties.put(90, iso);

        iso = new ISOField();
        iso.id = 91;
        iso.length = 2;
        iso.name = "FILE SECURITY CODE";
        iso.type = "IF_CHAR";
        FieldsProperties.put(91, iso);

        iso = new ISOField();
        iso.id = 92;
        iso.length = 6;
        iso.name = "RESPONSE INDICATOR";
        iso.type = "IF_CHAR";
        FieldsProperties.put(92, iso);

        iso = new ISOField();
        iso.id = 93;
        iso.length = 7;
        iso.name = "SERVICE INDICATOR";
        iso.type = "IF_CHAR";
        FieldsProperties.put(93, iso);

        iso = new ISOField();
        iso.id = 94;
        iso.length = 42;
        iso.name = "REPLACEMENT AMOUNTS";
        iso.type = "IF_CHAR";
        FieldsProperties.put(94, iso);

        iso = new ISOField();
        iso.id = 95;
        iso.length = 16;
        iso.name = "MESSAGE SECURITY CODE";
        iso.type = "IFA_BINARY";
        FieldsProperties.put(95, iso);

        iso = new ISOField();
        iso.id = 96;
        iso.length = 17;
        iso.name = "AMOUNT, NET SETTLEMENT";
        iso.type = "IFA_AMOUNT";
        FieldsProperties.put(96, iso);

        iso = new ISOField();
        iso.id = 97;
        iso.length = 25;
        iso.name = "PAYEE";
        iso.type = "IF_CHAR";
        FieldsProperties.put(97, iso);

        iso = new ISOField();
        iso.id = 98;
        iso.length = 11;
        iso.name = "SETTLEMENT INSTITUTION IDENT CODE";
        iso.type = "IFA_LLNUM";
        FieldsProperties.put(98, iso);

        iso = new ISOField();
        iso.id = 99;
        iso.length = 11;
        iso.name = "RECEIVING INSTITUTION IDENT CODE";
        iso.type = "IFA_LLNUM";
        FieldsProperties.put(99, iso);

        iso = new ISOField();
        iso.id = 100;
        iso.length = 17;
        iso.name = "FILE NAME";
        iso.type = "IFA_LLCHAR";
        FieldsProperties.put(100, iso);

        iso = new ISOField();
        iso.id = 101;
        iso.length = 28;
        iso.name = "FROM ACCOUNT";
        iso.type = "IFA_LLCHAR";
        FieldsProperties.put(101, iso);

        iso = new ISOField();
        iso.id = 102;
        iso.length = 10;
        iso.name = "ACCOUNT IDENTIFICATION 2";
        iso.type = "IFA_LLCHAR";
        FieldsProperties.put(102, iso);

        iso = new ISOField();
        iso.id = 103;
        iso.length = 100;
        iso.name = "TRANSACTION DESCRIPTION";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(103, iso);

        iso = new ISOField();
        iso.id = 104;
        iso.length = 999;
        iso.name = "RESERVED ISO USE";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(104, iso);

        iso = new ISOField();
        iso.id = 105;
        iso.length = 999;
        iso.name = "RESERVED ISO USE";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(105, iso);

        iso = new ISOField();
        iso.id = 106;
        iso.length = 999;
        iso.name = "RESERVED ISO USE";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(106, iso);

        iso = new ISOField();
        iso.id = 107;
        iso.length = 999;
        iso.name = "RESERVED ISO USE";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(107, iso);

        iso = new ISOField();
        iso.id = 108;
        iso.length = 999;
        iso.name = "RESERVED ISO USE";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(108, iso);

        iso = new ISOField();
        iso.id = 109;
        iso.length = 999;
        iso.name = "RESERVED ISO USE";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(109, iso);

        iso = new ISOField();
        iso.id = 110;
        iso.length = 999;
        iso.name = "RESERVED ISO USE";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(110, iso);

        iso = new ISOField();
        iso.id = 111;
        iso.length = 999;
        iso.name = "RESERVED NATIONAL USE";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(111, iso);

        iso = new ISOField();
        iso.id = 112;
        iso.length = 999;
        iso.name = "RESERVED NATIONAL USE";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(112, iso);

        iso = new ISOField();
        iso.id = 113;
        iso.length = 999;
        iso.name = "RESERVED NATIONAL USE";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(113, iso);

        iso = new ISOField();
        iso.id = 114;
        iso.length = 999;
        iso.name = "RESERVED NATIONAL USE";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(114, iso);

        iso = new ISOField();
        iso.id = 115;
        iso.length = 999;
        iso.name = "RESERVED NATIONAL USE";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(115, iso);

        iso = new ISOField();
        iso.id = 116;
        iso.length = 999;
        iso.name = "RESERVED NATIONAL USE";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(116, iso);

        iso = new ISOField();
        iso.id = 117;
        iso.length = 999;
        iso.name = "RESERVED NATIONAL USE";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(117, iso);

        iso = new ISOField();
        iso.id = 118;
        iso.length = 999;
        iso.name = "RESERVED NATIONAL USE";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(118, iso);

        iso = new ISOField();
        iso.id = 119;
        iso.length = 999;
        iso.name = "RESERVED PRIVATE USE";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(119, iso);

        iso = new ISOField();
        iso.id = 120;
        iso.length = 999;
        iso.name = "RESERVED PRIVATE USE";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(120, iso);

        iso = new ISOField();
        iso.id = 121;
        iso.length = 999;
        iso.name = "RESERVED PRIVATE USE";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(121, iso);

        iso = new ISOField();
        iso.id = 122;
        iso.length = 999;
        iso.name = "RESERVED PRIVATE USE";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(122, iso);

        iso = new ISOField();
        iso.id = 123;
        iso.length = 999;
        iso.name = "RESERVED PRIVATE USE";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(123, iso);

        iso = new ISOField();
        iso.id = 124;
        iso.length = 999;
        iso.name = "RESERVED PRIVATE USE";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(124, iso);

        iso = new ISOField();
        iso.id = 125;
        iso.length = 999;
        iso.name = "RESERVED PRIVATE USE";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(125, iso);

        iso = new ISOField();
        iso.id = 126;
        iso.length = 999;
        iso.name = "RESERVED PRIVATE USE";
        iso.type = "IFA_LLLCHAR";
        FieldsProperties.put(126, iso);

        iso = new ISOField();
        iso.id = 127;
        iso.length = 8;
        iso.name = "MAC 2";
        iso.type = "IFA_BINARY";
        FieldsProperties.put(127, iso);

        return FieldsProperties;
    }
}
