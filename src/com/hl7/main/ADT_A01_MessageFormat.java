package com.hl7.main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.v24.message.ADT_A01;
import ca.uhn.hl7v2.parser.Parser;
import com.hl7parser.segments.SegmentsFactory;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author vmanchala
 */
public class ADT_A01_MessageFormat {

    static Properties prop = new Properties();

    /**
     * @param args
     * @throws HL7Exception
     */
    public static void main(String[] args) throws Exception {
        String str = "";
        File file = new File("D:\\example.txt");
        BufferedWriter output = new BufferedWriter(new FileWriter(file));
        List<PatientDetails> lstPatientDetails = ParseCSVFileExample.getPatientDetailsFromXML();
        for (PatientDetails pt : lstPatientDetails) {
            // MSH, EVN, PID, PD1, NK1, PV1, PV2, DB1, OBX, AL1, NTE, DG1, DRG, PR1, ROL, GT1, IN1, IN2, ACC, UB1
            ADT_A01 adt = new ADT_A01();
            adt.initQuickstart("ADT", "A01", "P");
            SegmentsFactory.setPatientDetails(pt);
            SegmentsFactory.getMSHSegment(adt.getMSH());  
            SegmentsFactory.getEVNSegment(adt.getEVN());
            SegmentsFactory.getPIDSegment(adt.getPID());
            SegmentsFactory.getPD1Segment(adt.getPD1());
            SegmentsFactory.getNK1Segment(adt.getNK1());
            SegmentsFactory.getPV1Segment(adt.getPV1());
            SegmentsFactory.getDB1Segment(adt.getDB1());
            SegmentsFactory.getOBXSegment(adt.getOBX());
            SegmentsFactory.getAL1Segment(adt.getAL1());
            SegmentsFactory.getDG1Segment(adt.getDG1());
            SegmentsFactory.getDRGSegment(adt.getDRG());
            SegmentsFactory.getADT_A01_PROCEDURESegment(adt.getPROCEDURE());
            SegmentsFactory.getROLSegment(adt.getROL());
            SegmentsFactory.getGT1Segment(adt.getGT1());
            SegmentsFactory.getACCSegment(adt.getACC());
            SegmentsFactory.getUB1Segment(adt.getUB1());
            SegmentsFactory.getADT_A01_INSURANCESegment(adt.getINSURANCE());
            SegmentsFactory.getADT_A02_INSURANCESegment(adt.getINSURANCE());
            //NTE nte = adt.getNTE();
//            DG1 dg1 = adt.getDG1();
//            dg1.getDiagnosisCodeDG1().getNameOfCodingSystem().setValue(pt.getDiagnosisCode().isEmpty() ? "" : pt.getDiagnosisCode());
//            dg1.getDiagnosisDateTime().getTimeOfAnEvent().setValue(pt.getDiagnosisStartDate().toString());

           // DRG drg = adt.getDRG();
           // ADT_A01_PROCEDURE pr1 = adt.getPROCEDURE();
            //ROL rol = adt.getROL();
           // GT1 gt1 = adt.getGT1();
           // ADT_A01_INSURANCE in1 = adt.getINSURANCE();
           // ADT_A01_INSURANCE in2 = adt.getINSURANCE(0);
           // ACC acc = adt.getACC();
           // UB1 ub1 = adt.getUB1();

            HapiContext context = new DefaultHapiContext();
            Parser parser = context.getPipeParser();
            output.write(parser.encode(adt) + "\n");
            output.write("\n");
//        String encodedMessage = parser.encode(adt);

        }

        output.close();

//        ADT_A01 adt = new ADT_A01();
//        adt.initQuickstart("ADT", "A01", "P");
//
//        // Populate the MSH Segment
//        MSH mshSegment = adt.getMSH();
//        mshSegment.getSendingApplication().getNamespaceID().setValue("TestSendingSystem");
//        mshSegment.getSequenceNumber().setValue("123");
//
//        // Populate the PID Segment
//        PID pid = adt.getPID();        
//        pid.getPatientName(0).getFamilyName().getSurname().setValue(prop.getProperty("FirstName"));//"Doe");
//        
//        pid.getPatientName(0).getGivenName().setValue(prop.getProperty("LastName"));//"Jhon";
//        pid.getPatientIdentifierList(0).getID().setValue("123456");
//        
//        EVN evn = adt.getEVN();
//        evn.getEventTypeCode().setValue("Venu001");
//        evn.getEventReasonCode().setValue("0001234");
//        
//        
//        /*
//         * In a real situation, of course, many more segments and fields would be populated
//         */
//        // Now, let's encode the message and look at the output
//        HapiContext context = new DefaultHapiContext();
//        Parser parser = context.getPipeParser();
//        String encodedMessage = parser.encode(adt);
//        System.out.println("Printing ER7 Encoded Message:"+parser.encode(adt));
//        System.out.println(encodedMessage);
//
//        /*
//         * Prints:
//         * 
//         * MSH|^~\&|TestSendingSystem||||200701011539||ADT^A01^ADT A01||||123
//         * PID|||123456||Doe^John
//         */
//        // Next, let's use the XML parser to encode as XML
//        parser = context.getXMLParser();
//        encodedMessage = parser.encode(adt);
//        System.out.println("Printing XML Encoded Message:");
//        System.out.println(encodedMessage);
    }

    private static String isEmpty(String str) {
        if (str.isEmpty()) {
            return "";
        } else {
            return str;
        }
    }

}
