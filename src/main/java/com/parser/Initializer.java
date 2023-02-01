package com.parser;

import com.entities.ISOField;

import java.util.HashMap;
import java.util.TreeSet;

public class Initializer {

    public static HashMap<Integer, ISOField> initializeFieldProperties() {
        HashMap<Integer,ISOField> FieldsProperties = new HashMap<>();

        /*iso.id = 0;
        iso.length = 4;
        iso.name = "MESSAGE TYPE INDICATOR";
        FieldsProperties.put(0,iso);*/
        ISOField iso = new ISOField();
        iso.id = 0;
        iso.length = 16;
        iso.name = "BIT MAP";
        iso.type = "IFA_BITMAP";
        FieldsProperties.put(0, iso);

        iso = new ISOField();
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
        iso.length = 8;
        iso.name = "AMOUNT, TRANSACTION FEE";
        iso.type = "IFA_AMOUNT";
        FieldsProperties.put(27, iso);

        iso = new ISOField();
        iso.id = 28;
        iso.length = 8;
        iso.name = "AMOUNT, SETTLEMENT FEE";
        iso.type = "IFA_AMOUNT";
        FieldsProperties.put(28, iso);

        iso = new ISOField();
        iso.id = 29;
        iso.length = 8;
        iso.name = "AMOUNT, TRANSACTION PROCESSING FEE";
        iso.type = "IFA_AMOUNT";
        FieldsProperties.put(29, iso);

        iso = new ISOField();
        iso.id = 30;
        iso.length = 8;
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
        iso.length = 16;
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
        iso.length = 5;
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
        iso.length = 16;
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
        iso.length = 28;
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
        iso.length = 16;
        iso.name = "MAC 2";
        iso.type = "IFA_BINARY";
        FieldsProperties.put(127, iso);

        return FieldsProperties;
    }

    public static HashMap<Integer, String> initializeMessageTypes(){
        HashMap<Integer,String> messageTypes = new HashMap<>();
        messageTypes.put(100,	"Authorisation request");
        messageTypes.put(101,	"Authorisation request repeat");
        messageTypes.put(102,	"Authorisation completion confirmation");
        messageTypes.put(103,	"Authorisation completion confirmation repeat");
        messageTypes.put(110,	"Authorisation request response");
        messageTypes.put(112,	"Authorisation completion response");
        messageTypes.put(120,	"Authorisation advice");
        messageTypes.put(121,	"Authorisation advice repeat");
        messageTypes.put(122,	"Authorisation advice completion confirmation");
        messageTypes.put(123,	"Authorisation advice completion confirmation rpt");
        messageTypes.put(130,	"Authorisation advice response");
        messageTypes.put(132,	"Authorisation advice completion response");
        messageTypes.put(200,	"Financial Transaction request");
        messageTypes.put(201,	"Financial Transaction request repeat");
        messageTypes.put(202,	"Financial transaction completion confirmation");
        messageTypes.put(203,	"Financial transaction completion confirmation rpt");
        messageTypes.put(210,	"Financial transaction request response");
        messageTypes.put(212,	"Financial transaction completion response");
        messageTypes.put(220,	"Financial transaction advice");
        messageTypes.put(221,	"Financial transaction advice repeat");
        messageTypes.put(222,	"Financial transaction advice completion confirmation");
        messageTypes.put(223,	"Financial txn advice completion confirmation rpt");
        messageTypes.put(230,	"Financial txn advice response");
        messageTypes.put(232,	"Financial txn advice completion response");
        messageTypes.put(300,	"Acquirer file update request");
        messageTypes.put(302,	"Card issuer file update request");
        messageTypes.put(310,	"Acquirer file update request response");
        messageTypes.put(312,	"Card issuer file update request response");
        messageTypes.put(320,	"Acquirer file update advice");
        messageTypes.put(322,	"Card issuer file update advice");
        messageTypes.put(330,	"Acquirer file update advice response");
        messageTypes.put(332,	"Card issuer file update advice response");
        messageTypes.put(400,	"Acquirer reversal request");
        messageTypes.put(401,	"Acquirer reversal request response");
        messageTypes.put(402,	"Card issuer reversal request");
        messageTypes.put(403,	"Card issuer reversal request repeat");
        messageTypes.put(410,	"Acquirer reversal request response");
        messageTypes.put(412,	"Card issuer reversal request response");
        messageTypes.put(420,	"Acquirer reversal advice");
        messageTypes.put(421,	"Acquirer reversal advice repeat");
        messageTypes.put(422,	"Card issuer reversal advice");
        messageTypes.put(423,	"Card issuer reversal advice repeat");
        messageTypes.put(430,	"Acquirer reversal advice response");
        messageTypes.put(432,	"Card issuer reversal advice response");
        messageTypes.put(500,	"Acquirer reconciliation request");
        messageTypes.put(501,	"Acquirer reconciliation request repeat");
        messageTypes.put(502,	"Card issuer reconciliation request");
        messageTypes.put(503,	"Card issuer reconciliation request repeat");
        messageTypes.put(510,	"Acquirer reconcilaition request response");
        messageTypes.put(512,	"Card issuer reconciliation request response");
        messageTypes.put(520,	"Acquirer reconciliation advice");
        messageTypes.put(521,	"Acquirer reconciliation advice repeat");
        messageTypes.put(522,	"Card issuer reconciliation advice");
        messageTypes.put(523,	"Card issuer reconciliation advice repeat");
        messageTypes.put(530,	"Acquirer reconciliation advice response");
        messageTypes.put(532,	"Card issuer reconciliation advice response");
        messageTypes.put(600,	"Administrative request");
        messageTypes.put(601,	"Administrative request repeat");
        messageTypes.put(610,	"Administrative request response");
        messageTypes.put(620,	"Administrative advice");
        messageTypes.put(621,	"Administrative advice repeat");
        messageTypes.put(630,	"Administrative advice response");
        messageTypes.put(800,	"Network management request");
        messageTypes.put(801,	"Network management request repeat");
        messageTypes.put(810,	"Network management request response");
        messageTypes.put(820,	"Network management advice");
        messageTypes.put(821,	"Network management advice repeat");
        messageTypes.put(830,	"Network management advice response");
        return messageTypes;
    }

    public static TreeSet<Integer> initializeData100() {
        TreeSet<Integer> data = new TreeSet<>();
        data.add(1);
        data.add(2);
        data.add(3);
        data.add(4);
        data.add(5);
        data.add(6);
        data.add(7);
        data.add(8);
        data.add(9);
        data.add(10);
        data.add(11);
        data.add(12);
        data.add(13);
        data.add(14);
        data.add(15);
        data.add(16);
        data.add(18);
        data.add(19);
        data.add(20);
        data.add(21);
        data.add(22);
        data.add(23);
        data.add(25);
        data.add(28);
        data.add(29);
        data.add(30);
        data.add(31);
        data.add(32);
        data.add(33);
        data.add(34);
        data.add(35);
        data.add(36);
        data.add(37);
        data.add(38);
        data.add(39);
        data.add(40);
        data.add(41);
        data.add(42);
        data.add(43);
        data.add(44);
        data.add(45);
        data.add(46);
        data.add(47);
        data.add(48);
        data.add(49);
        data.add(50);
        data.add(51);
        data.add(53);
        data.add(54);
        data.add(55);
        data.add(57);
        data.add(58);
        data.add(59);
        data.add(61);
        data.add(63);
        data.add(67);
        data.add(98);
        data.add(100);
        data.add(102);
        data.add(103);
        data.add(104);
        data.add(105);
        data.add(106);
        data.add(107);
        data.add(108);
        data.add(109);
        data.add(110);
        data.add(111);
        data.add(113);
        data.add(114);
        data.add(120);
        data.add(121);
        data.add(122);
        data.add(123);
        data.add(124);
        data.add(125);
        data.add(126);
        data.add(127);
        data.add(128);

        return data;
    }
    public static TreeSet<Integer> initializeData101() {
        TreeSet<Integer> data = new TreeSet<>();
        data.add(1);
        data.add(3);
        data.add(4);
        data.add(7);
        data.add(8);
        data.add(11);
        data.add(12);
        data.add(13);
        data.add(28);
        data.add(30);
        data.add(37);
        data.add(38);
        data.add(39);
        data.add(41);
        data.add(44);
        data.add(46);
        data.add(47);
        data.add(49);
        data.add(53);
        data.add(55);
        data.add(61);
        data.add(63);
        data.add(124);
        data.add(125);
        data.add(126);
        data.add(127);
        data.add(128);

        return data;
    }
    public static TreeSet<Integer> initializeData120() {
        TreeSet<Integer> data = new TreeSet<>();
        data.add(3);
        data.add(4);
        data.add(5);
        data.add(7);
        data.add(9);
        data.add(11);
        data.add(12);
        data.add(13);
        data.add(15);
        data.add(28);
        data.add(29);
        data.add(37);
        data.add(38);
        data.add(39);
        data.add(44);
        data.add(46);
        data.add(49);
        data.add(50);
        data.add(53);
        data.add(55);
        data.add(58);
        data.add(61);
        data.add(63);
        data.add(64);
        data.add(128);

        return data;
    }
    public static TreeSet<Integer> initializeData200() {
        TreeSet<Integer> data = new TreeSet<>();
        data.add(1);
        data.add(2);
        data.add(3);
        data.add(4);
        data.add(5);
        data.add(6);
        data.add(7);
        data.add(8);
        data.add(9);
        data.add(10);
        data.add(11);
        data.add(12);
        data.add(13);
        data.add(14);
        data.add(15);
        data.add(16);
        data.add(18);
        data.add(19);
        data.add(20);
        data.add(21);
        data.add(22);
        data.add(23);
        data.add(25);
        data.add(28);
        data.add(29);
        data.add(30);
        data.add(31);
        data.add(32);
        data.add(33);
        data.add(34);
        data.add(35);
        data.add(36);
        data.add(37);
        data.add(38);
        data.add(39);
        data.add(40);
        data.add(41);
        data.add(42);
        data.add(43);
        data.add(44);
        data.add(45);
        data.add(46);
        data.add(47);
        data.add(48);
        data.add(49);
        data.add(50);
        data.add(51);
        data.add(53);
        data.add(54);
        data.add(55);
        data.add(58);
        data.add(59);
        data.add(61);
        data.add(63);
        data.add(67);
        data.add(98);
        data.add(100);
        data.add(102);
        data.add(103);
        data.add(104);
        data.add(105);
        data.add(106);
        data.add(107);
        data.add(108);
        data.add(109);
        data.add(110);
        data.add(111);
        data.add(113);
        data.add(114);
        data.add(120);
        data.add(122);
        data.add(123);
        data.add(124);
        data.add(125);
        data.add(126);
        data.add(127);
        data.add(128);
        return data;
    }
    public static TreeSet<Integer> initializeData220() {
        TreeSet<Integer> data = new TreeSet<>();
        data.add(3);
        data.add(4);
        data.add(5);
        data.add(7);
        data.add(9);
        data.add(11);
        data.add(12);
        data.add(13);
        data.add(14);
        data.add(15);
        data.add(28);
        data.add(29);
        data.add(37);
        data.add(38);
        data.add(39);
        data.add(44);
        data.add(46);
        data.add(49);
        data.add(50);
        data.add(53);
        data.add(55);
        data.add(58);
        data.add(61);
        data.add(63);
        data.add(64);
        data.add(128);
        return data;
    }
    public static TreeSet<Integer> initializeData300() {
        TreeSet<Integer> data = new TreeSet<>();
        data.add(1);
        data.add(2);
        data.add(7);
        data.add(11);
        data.add(14);
        data.add(23);
        data.add(34);
        data.add(39);
        data.add(44);
        data.add(53);
        data.add(61);
        data.add(64);
        data.add(91);
        data.add(128);
        return data;
    }
    public static TreeSet<Integer> initializeData302() {
        TreeSet<Integer> data = new TreeSet<>();
        data.add(1);
        data.add(2);
        data.add(7);
        data.add(11);
        data.add(14);
        data.add(23);
        data.add(34);
        data.add(39);
        data.add(44);
        data.add(53);
        data.add(62);
        data.add(64);
        data.add(91);
        data.add(128);
        return data;
    }
    public static TreeSet<Integer> initializeData382() {
        TreeSet<Integer> data = new TreeSet<>();
        data.add(1);
        data.add(7);
        data.add(11);
        data.add(39);
        data.add(53);
        data.add(62);
        data.add(64);
        data.add(91);
        data.add(101);
        data.add(113);
        data.add(124);
        data.add(128);
        return data;
    }
    public static TreeSet<Integer> initializeData420() {
        TreeSet<Integer> data = new TreeSet<>();
        data.add(1);
        data.add(3);
        data.add(4);
        data.add(5);
        data.add(7);
        data.add(9);
        data.add(11);
        data.add(12);
        data.add(13);
        data.add(15);
        data.add(28);
        data.add(29);
        data.add(37);
        data.add(38);
        data.add(39);
        data.add(44);
        data.add(46);
        data.add(49);
        data.add(50);
        data.add(53);
        data.add(55);
        data.add(56);
        data.add(58);
        data.add(61);
        data.add(63);
        data.add(64);
        data.add(90);
        data.add(95);
        data.add(128);
        return data;
    }
    public static TreeSet<Integer> initializeData520() {
        TreeSet<Integer> data = new TreeSet<>();
        data.add(1);
        data.add(7);
        data.add(11);
        data.add(15);
        data.add(50);
        data.add(53);
        data.add(64);
        data.add(66);
        data.add(97);
        data.add(99);
        data.add(128);
        return data;
    }
    public static TreeSet<Integer> initializeData522() {
        TreeSet<Integer> data = new TreeSet<>();
        data.add(1);
        data.add(7);
        data.add(11);
        data.add(15);
        data.add(50);
        data.add(53);
        data.add(64);
        data.add(66);
        data.add(97);
        data.add(99);
        data.add(128);
        return data;
    }
    public static TreeSet<Integer> initializeData600601() {
        TreeSet<Integer> data = new TreeSet<>();
        data.add(1);
        data.add(7);
        data.add(11);
        data.add(39);
        data.add(70);
        data.add(128);
        return data;
    }
    public static TreeSet<Integer> initializeData600800() {
        TreeSet<Integer> data = new TreeSet<>();
        data.add(1);
        data.add(7);
        data.add(11);
        data.add(39);
        data.add(53);
        data.add(70);
        data.add(124);
        data.add(128);
        return data;
    }
    public static TreeSet<Integer> initializeData600810() {
        TreeSet<Integer> data = new TreeSet<>();
        data.add(1);
        data.add(7);
        data.add(11);
        data.add(39);
        data.add(44);
        data.add(53);
        data.add(70);
        data.add(128);
        return data;
    }
    public static TreeSet<Integer> initializeData800() {
        TreeSet<Integer> data = new TreeSet<>();
        data.add(1);
        data.add(7);
        data.add(11);
        data.add(39);
        data.add(53);
        data.add(70);
        return data;
    }
}
