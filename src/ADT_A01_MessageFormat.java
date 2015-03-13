/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.v24.group.ADT_A01_INSURANCE;
import ca.uhn.hl7v2.model.v24.group.ADT_A01_PROCEDURE;
import ca.uhn.hl7v2.model.v24.segment.PV1;
import ca.uhn.hl7v2.model.v24.message.ADT_A01;
import ca.uhn.hl7v2.model.v24.segment.ACC;
import ca.uhn.hl7v2.model.v24.segment.AL1;
import ca.uhn.hl7v2.model.v24.segment.DB1;
import ca.uhn.hl7v2.model.v24.segment.DG1;
import ca.uhn.hl7v2.model.v24.segment.DRG;
import ca.uhn.hl7v2.model.v24.segment.EVN;
import ca.uhn.hl7v2.model.v24.segment.GT1;
import ca.uhn.hl7v2.model.v24.segment.MSH;
import ca.uhn.hl7v2.model.v24.segment.NK1;
import ca.uhn.hl7v2.model.v24.segment.OBX;
import ca.uhn.hl7v2.model.v24.segment.PD1;
import ca.uhn.hl7v2.model.v24.segment.PID;
import ca.uhn.hl7v2.model.v24.segment.PV2;
import ca.uhn.hl7v2.model.v24.segment.ROL;
import ca.uhn.hl7v2.model.v24.segment.UB1;
import ca.uhn.hl7v2.parser.Parser;
import static java.awt.SystemColor.text;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
            MSH msh = adt.getMSH();
            msh.getFieldSeparator().setValue("|");
            msh.getEncodingCharacters().setValue("^~\\&");
            msh.getSendingApplication().getNamespaceID().setValue("Sending Application");
            msh.getSendingFacility().getNamespaceID().setValue("Sending Facility");
            msh.getReceivingApplication().getNamespaceID().setValue("ReceivingApplication");
            msh.getReceivingFacility().getNamespaceID().setValue("ReceivingFacility");
            msh.getDateTimeOfMessage().getTimeOfAnEvent().setValue("2014-02-03");
//        msh.getMessageType().getTriggerEvent().setValue("ADT^A01^");
            msh.getMessageControlID().setValue("Message Control Id");
            msh.getVersionID().getVersionID().setValue("2.x");
            msh.getSequenceNumber().setValue("20");

            EVN evn = adt.getEVN();
            evn.getEventTypeCode().setValue("A01");
            evn.getRecordedDateTime().getTimeOfAnEvent().setValue("2015-02-12");
            evn.getDateTimePlannedEvent().getTimeOfAnEvent().setValue(pt.getEventDate().toString());
            evn.getEventReasonCode().setValue("Reason Code");
            evn.getOperatorID(0).getIDNumber().setValue("12345");
            evn.getEventOccurred().getTimeOfAnEvent().setValue("12:00PM");
            evn.getEventFacility().getNamespaceID().setValue("Event Facility");

            PID pid = adt.getPID();
            pid.getSetIDPID().setValue("setIDPID");
            pid.getPatientID().getID().setValue("PatientId");
            pid.getPatientIdentifierList(0).getIdentifierTypeCode().setValue("PatientIdentifierList");
            pid.getAlternatePatientIDPID(0).getID().setValue("AlternativeId");
            pid.getPatientName(0).getFamilyName().getSurname().setValue(isEmpty(pt.getFirstName()));
            pid.getPatientName(0).getGivenName().setValue(isEmpty(pt.getLastName()));
            pid.getMotherSMaidenName();
            pid.getDateTimeOfBirth().getTimeOfAnEvent().setValue(isEmpty(pt.getDob()));
            pid.getAdministrativeSex();
            pid.getPatientAlias();
            pid.getRace();
            pid.getPatientAddress(0).getCity().setValue(isEmpty(pt.getCity()));
            pid.getPatientAddress(0).getCountry().setValue(isEmpty(pt.getCountry()));
            pid.getPatientAddress(0).getStateOrProvince().setValue(isEmpty(pt.getState()));
            pid.getPatientAddress(0).getZipOrPostalCode().setValue(isEmpty(pt.getZip()));
            pid.getPatientAddress(0).getAddressType().setValue(pt.getAddress());
            pid.getCountyCode();
            pid.getPhoneNumberHome();
            pid.getPhoneNumberBusiness();
            pid.getPrimaryLanguage().getText().setValue(isEmpty(pt.getPatientLanguage()));
            pid.getMaritalStatus();
            pid.getReligion();
            pid.getPatientAccountNumber();
            pid.getSSNNumberPatient().setValue(isEmpty(pt.getSsn()));
            pid.getDriverSLicenseNumberPatient();
            pid.getMotherSIdentifier();
            pid.getEthnicGroup();
            pid.getBirthPlace();
            pid.getMultipleBirthIndicator();
            pid.getBirthOrder();
            pid.getCitizenship();
            pid.getVeteransMilitaryStatus();
            pid.getNationality();
            pid.getPatientDeathDateAndTime();
            pid.getPatientDeathIndicator();
            pid.getIdentityUnknownIndicator();
            pid.getIdentityReliabilityCode();
            pid.getLastUpdateDateTime();
            pid.getLastUpdateFacility();
            pid.getSpeciesCode();
            pid.getBreedCode();
            pid.getStrain();
            pid.getProductionClassCode();
        // Tribal citizenship

            PD1 pd1 = adt.getPD1();
            pd1.getLivingDependency(0).setValue("LivingDependency");
            pd1.getLivingArrangement().setValue("LivingArrangment");
            pd1.getPatientPrimaryFacility(0);
            pd1.getPatientPrimaryCareProviderNameIDNo();
            pd1.getStudentIndicator().setValue("Student Indicator");
            pd1.getHandicap().setValue("Handicap");
            pd1.getLivingWillCode().setValue("LivingWillCode");
            pd1.getOrganDonorCode().setValue("OrganDonorCode");
            pd1.getSeparateBill().setValue("SeparateBill");
            pd1.getDuplicatePatient(0);
            pd1.getPublicityCode().getNameOfCodingSystem().setValue("PublicityCode");
            pd1.getProtectionIndicator().setValue("ProtectionIndicator");

            NK1 nk1 = adt.getNK1();
            nk1.getSetIDNK1();
            nk1.getName();
            nk1.getRelationship();
            nk1.getAddress();
            nk1.getPhoneNumber();
            nk1.getBusinessPhoneNumber();
            nk1.getContactRole();
            nk1.getStartDate();
            nk1.getEndDate();
            nk1.getNextOfKinAssociatedPartiesJobTitle();
            nk1.getNextOfKinAssociatedPartiesJobCodeClass();
            nk1.getNextOfKinAssociatedPartiesEmployeeNumber();
            nk1.getOrganizationNameNK1();
            nk1.getMaritalStatus();
            nk1.getAdministrativeSex();
            nk1.getDateTimeOfBirth();
            nk1.getLivingDependency();
            nk1.getAmbulatoryStatus();
            nk1.getCitizenship();
            nk1.getPrimaryLanguage();
            nk1.getLivingArrangement();
            nk1.getPublicityCode();
            nk1.getProtectionIndicator();
            nk1.getReligion();
            nk1.getMotherSMaidenName();
            nk1.getNationality();
            nk1.getEthnicGroup();
            nk1.getContactReason();
            nk1.getContactPersonSName();
            nk1.getContactPersonSTelephoneNumber();
            nk1.getContactPersonSAddress();
            nk1.getNextOfKinAssociatedPartySIdentifiers();
            nk1.getJobStatus();
            nk1.getRace();
            nk1.getHandicap();
            nk1.getContactPersonSocialSecurityNumber();
        //next of kin birth place
            // VIP Indicator

            PV1 pv1 = adt.getPV1();
            pv1.getSetIDPV1();
            pv1.getPatientClass();
            pv1.getAssignedPatientLocation();
            pv1.getAdmissionType();
            pv1.getPreadmitNumber();
            pv1.getPriorPatientLocation();
            pv1.getAttendingDoctor();
            pv1.getReferringDoctor();
            pv1.getConsultingDoctor();
            pv1.getHospitalService();
            pv1.getTemporaryLocation();
            pv1.getPreadmitTestIndicator();
            pv1.getReAdmissionIndicator();
            pv1.getAdmitSource();
            pv1.getAmbulatoryStatus();
            pv1.getVIPIndicator();
            pv1.getAdmittingDoctor();
            pv1.getPatientType();
            pv1.getVisitNumber();
            pv1.getFinancialClass();
            pv1.getChargePriceIndicator();
            pv1.getCourtesyCode();
            pv1.getCreditRating();
            pv1.getContractCode();
            pv1.getContractEffectiveDate();
            pv1.getContractAmount();
            pv1.getContractPeriod();
            pv1.getInterestCode();
            pv1.getTransferToBadDebtCode();
            pv1.getTransferToBadDebtDate();
            pv1.getBadDebtAgencyCode();
            pv1.getBadDebtTransferAmount();
            pv1.getBadDebtRecoveryAmount();
            pv1.getDeleteAccountIndicator();
            pv1.getDeleteAccountDate();
            pv1.getDischargeDisposition();
            pv1.getDischargedToLocation();
            pv1.getDietType();
            pv1.getServicingFacility();
            pv1.getBedStatus();
            pv1.getAccountStatus();
            pv1.getPendingLocation();
            pv1.getPriorTemporaryLocation();
            pv1.getAdmitDateTime();
            pv1.getDischargeDateTime();
            pv1.getCurrentPatientBalance();
            pv1.getTotalCharges();
            pv1.getTotalAdjustments();
            pv1.getTotalPayments();
            pv1.getAlternateVisitID();
            pv1.getVisitIndicator();
            pv1.getOtherHealthcareProvider();

            PV2 pv2 = adt.getPV2();

            pv2.getPriorPendingLocation();
            pv2.getAccommodationCode();
            pv2.getAdmitReason();
            pv2.getTransferReason();
            pv2.getPatientValuables();
            pv2.getPatientValuablesLocation();
            pv2.getVisitUserCode();
            pv2.getExpectedAdmitDateTime();
            pv2.getExpectedDischargeDateTime();
            pv2.getEstimatedLengthOfInpatientStay();
            pv2.getActualLengthOfInpatientStay();
            pv2.getVisitDescription();
            pv2.getReferralSourceCode();
            pv2.getPreviousServiceDate();
            pv2.getEmploymentIllnessRelatedIndicator();
            pv2.getPurgeStatusCode();
            pv2.getPurgeStatusDate();
            pv2.getSpecialProgramCode();
            pv2.getRetentionIndicator();
            pv2.getExpectedNumberOfInsurancePlans();
            pv2.getVisitPublicityCode();
            pv2.getVisitProtectionIndicator();
            pv2.getClinicOrganizationName();
            pv2.getPatientStatusCode();
            pv2.getVisitPriorityCode();
            pv2.getPreviousTreatmentDate();
            pv2.getExpectedDischargeDisposition();
            pv2.getSignatureOnFileDate();
            pv2.getFirstSimilarIllnessDate();
            pv2.getPatientChargeAdjustmentCode();
            pv2.getRecurringServiceCode();
            pv2.getBillingMediaCode();
            pv2.getExpectedSurgeryDateAndTime();
            pv2.getMilitaryPartnershipCode();
            pv2.getMilitaryNonAvailabilityCode();
            pv2.getNewbornBabyIndicator();
            pv2.getBabyDetainedIndicator();

            DB1 db1 = adt.getDB1();
            OBX obx = adt.getOBX();
            AL1 al1 = adt.getAL1();
            //NTE nte = adt.getNTE();
            DG1 dg1 = adt.getDG1();
            dg1.getDiagnosisCodeDG1().getNameOfCodingSystem().setValue(pt.getDiagnosisCode().isEmpty() ? "" : pt.getDiagnosisCode());
            dg1.getDiagnosisDateTime().getTimeOfAnEvent().setValue(pt.getDiagnosisStartDate().toString());

            DRG drg = adt.getDRG();
            ADT_A01_PROCEDURE pr1 = adt.getPROCEDURE();
            ROL rol = adt.getROL();
            GT1 gt1 = adt.getGT1();
            ADT_A01_INSURANCE in1 = adt.getINSURANCE();
            ADT_A01_INSURANCE in2 = adt.getINSURANCE(0);
            ACC acc = adt.getACC();
            UB1 ub1 = adt.getUB1();

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
