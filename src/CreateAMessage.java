/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vmanchala
 */
import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.v24.message.ADT_A01;
import ca.uhn.hl7v2.model.v24.segment.EVN;
import ca.uhn.hl7v2.model.v24.segment.MSH;
import ca.uhn.hl7v2.model.v24.segment.PID;
import ca.uhn.hl7v2.parser.Parser;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Example transmitting a message
 *
 * @author <a href="mailto:jamesagnew@sourceforge.net">James Agnew</a>
 * @version $Revision: 1.4 $ updated on $Date: 2009-10-03 15:29:05 $ by $Author:
 * jamesagnew $
 */
public class CreateAMessage {
static Properties prop = new Properties();
    public void getReadFile() throws IOException {
         String result = "";
        
        String propFileName = "Property_File.txt";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null) {
                prop.load(inputStream);
        } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }
    }
    /**
     * @param args
     * @throws HL7Exception
     */
    public static void main(String[] args) throws Exception {

//        CreateAMessage cr = new CreateAMessage();
//        cr.getReadFile();
        
        ADT_A01 adt = new ADT_A01();
        adt.initQuickstart("ADT", "A01", "P");

        // Populate the MSH Segment
        MSH mshSegment = adt.getMSH();
        mshSegment.getSendingApplication().getNamespaceID().setValue("TestSendingSystem");
        mshSegment.getSequenceNumber().setValue("123");

        // Populate the PID Segment
        PID pid = adt.getPID();        
        pid.getPatientName(0).getFamilyName().getSurname().setValue(prop.getProperty("FirstName"));//"Doe");
        
        pid.getPatientName(0).getGivenName().setValue(prop.getProperty("LastName"));//"Jhon";
        pid.getPatientIdentifierList(0).getID().setValue("123456");
        
        EVN evn = adt.getEVN();
        evn.getEventTypeCode().setValue("Venu001");
        evn.getEventReasonCode().setValue("0001234");
        
        
        /*
         * In a real situation, of course, many more segments and fields would be populated
         */
        // Now, let's encode the message and look at the output
        HapiContext context = new DefaultHapiContext();
        Parser parser = context.getPipeParser();
        String encodedMessage = parser.encode(adt);
        System.out.println("Printing ER7 Encoded Message:"+parser.encode(adt));
        System.out.println(encodedMessage);

        /*
         * Prints:
         * 
         * MSH|^~\&|TestSendingSystem||||200701011539||ADT^A01^ADT A01||||123
         * PID|||123456||Doe^John
         */
        // Next, let's use the XML parser to encode as XML
        parser = context.getXMLParser();
        encodedMessage = parser.encode(adt);
        System.out.println("Printing XML Encoded Message:");
        System.out.println(encodedMessage);

        /*
         * Prints:
         * 
         * <?xml version="1.0" encoding="UTF-8"?>
         <ADT_A01 xmlns="urn:hl7-org:v2xml">
         <MSH>
         <MSH.1>|</MSH.1>
         <MSH.2>^~\&amp;</MSH.2>
         <MSH.3>
         <HD.1>TestSendingSystem</HD.1>
         </MSH.3>
         <MSH.7>
         <TS.1>200701011539</TS.1>
         </MSH.7>
         <MSH.9>
         <MSG.1>ADT</MSG.1>
         <MSG.2>A01</MSG.2>
         <MSG.3>ADT A01</MSG.3>
         </MSH.9>
         <MSH.13>123</MSH.13>
         </MSH>
         <PID>
         <PID.3>
         <CX.1>123456</CX.1>
         </PID.3>
         <PID.5>
         <XPN.1>
         <FN.1>Doe</FN.1>
         </XPN.1>
         <XPN.2>John</XPN.2>
         </PID.5>
         </PID>
         </ADT_A01>
         */
    }

}
