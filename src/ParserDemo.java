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
import ca.uhn.hl7v2.parser.*;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v22.message.ADT_A01;
import ca.uhn.hl7v2.model.v24.message.ACK;

public class ParserDemo {
    
    public static void main(String args[]) throws HL7Exception {
        //for demo purposes, we just declare a literal message string 
        String ackMessageString  = "MSH|^~\\&|MODemoSoftware|Eli MOA Test Cap^51675B57-9C95-4278-B52E-3FE5EEB6B3EE^GUID|||20121127180300|Eli MOA Test Cap (Capricorn)|ORU^R01|201211271803520050|P|2.3.1|||||||en\r"+
"PID|1|HB117056|ABC123^^^MODemo^MC~401114835T^^^^PEN~401114835T||TEST^Patient||20010101|F||4^Non-indigenous|10/102 Wises Road^^Maroochydore^^4558||0754566000\r"+
"PV1|1||AE\\R\\HBH^^^HBH&Medical Objects Demo Hospital&MODemoSoftware|||||0000000Y^REFERRING^Provider^^^DR^^^AUSHICPR^L^^^UPIN|UP3123000QW^CONSULTING^Provider^^^DR^^^AUSHICPR^L^^^UPIN\r"+
"ORC|RE|589113676^MODemoSoftware|589113676^Eli MOA Test Cap^51675B57-9C95-4278-B52E-3FE5EEB6B3EE^GUID||IP||^^^20121127^^URGENT|||||0000000Y^REFERRING^Provider^^^DR^^^AUSHICPR^L^^^UPIN\r"+
"FHS|Medical-Objects|Eli MOA Test Cap\r"+
"BHS|Medical-Objects|Eli MOA Test Cap\r";
        
        //instantiate a PipeParser, which handles the "traditional encoding" 
        ADT_A01 adt = new ADT_A01();
        adt.parse(ackMessageString);//initQuickstart("ADT", "A01", "P");        
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
        
        
        
        
        
        
        
//        PipeParser pipeParser = new PipeParser();
//        
//        try {
//            //parse the message string into a Message object 
//            Message message = pipeParser.parse(ackMessageString);
//            
//            //if it is an ACK message (as we know it is),  cast it to an 
//            // ACK object so that it is easier to work with, and change a value            
//            if (message instanceof ACK) {
//                ACK ack = (ACK) message;
//                ack.getMSH().getProcessingID().getProcessingMode().setValue("P");
//            }
//            
//            //instantiate an XML parser 
//            XMLParser xmlParser = new DefaultXMLParser();
//            
//            //encode message in XML 
//            String ackMessageInXML = xmlParser.encode(message);
//            
//            //print XML-encoded message to standard out
//            System.out.println(ackMessageInXML);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }       
    }
}
