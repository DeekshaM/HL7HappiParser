package com.hl7.main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vmanchala
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public class ParseCSVFileExample {
    
    static SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");
    static SimpleDateFormat sdft = new SimpleDateFormat("yyyyMMddHHmmss");
    public ParseCSVFileExample() {
        
    }
    public static List<PatientDetails> getPatientDetailsFromXML() {
        
        List<PatientDetails> lstPatientDetails = new ArrayList<>();
        try {
            //csv file containing data
            String strFile = "C:\\Users\\vmanchala\\Desktop\\Patient Demographics.csv";

            //create BufferedReader to read csv file
            BufferedReader br = new BufferedReader(new FileReader(strFile));
            String strLine = "";
            StringTokenizer st = null;                        
            //read comma separated file line by line
            String strHeader = br.readLine();
            while ((strLine = br.readLine()) != null) {                
                PatientDetails patientDetails = new PatientDetails();
                //break comma separated line using ","
                st = new StringTokenizer(strLine, ",");
                patientDetails.setFirstName(st.nextToken());
                patientDetails.setLastName(st.nextToken());
                patientDetails.setDob(sdft.format(sdf.parse(st.nextToken())));
                patientDetails.setGender(st.nextToken().charAt(0));
                patientDetails.setAddress(st.nextToken());
                patientDetails.setCity(st.nextToken());
                patientDetails.setState(st.nextToken());
                patientDetails.setZip(st.nextToken());
                patientDetails.setCountry(st.nextToken());
                patientDetails.setEventDate(sdft.format(sdf.parse(st.nextToken())));
                patientDetails.setPatientLanguage(st.nextToken());
                patientDetails.setSsn(st.nextToken());
                patientDetails.setPatientLocation(st.nextToken());
                patientDetails.setAttendingDoctor(st.nextToken());
                patientDetails.setAdmitDateTime(sdft.format(sdf.parse(st.nextToken())));
                patientDetails.setDischargeDateTime(sdft.format(sdf.parse(st.nextToken())));
                patientDetails.setDiagnosisCode(st.nextToken());
                patientDetails.setDiagnosisStartDate(sdft.format(sdf.parse(st.nextToken())));
                lstPatientDetails.add(patientDetails);
            }           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstPatientDetails;
    }
    

    public static void main(String[] args) {
        
        ParseCSVFileExample prcvs = new ParseCSVFileExample();
        prcvs.getPatientDetailsFromXML();
    }

    
}
