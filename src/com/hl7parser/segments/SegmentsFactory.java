/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hl7parser.segments;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v24.group.ADT_A01_INSURANCE;
import ca.uhn.hl7v2.model.v24.group.ADT_A01_PROCEDURE;
import ca.uhn.hl7v2.model.v24.segment.ACC;
import ca.uhn.hl7v2.model.v24.segment.AL1;
import ca.uhn.hl7v2.model.v24.segment.DB1;
import ca.uhn.hl7v2.model.v24.segment.DG1;
import ca.uhn.hl7v2.model.v24.segment.DRG;
import ca.uhn.hl7v2.model.v24.segment.EVN;
import ca.uhn.hl7v2.model.v24.segment.GT1;
import ca.uhn.hl7v2.model.v24.segment.IN1;
import ca.uhn.hl7v2.model.v24.segment.MSH;
import ca.uhn.hl7v2.model.v24.segment.NK1;
import ca.uhn.hl7v2.model.v24.segment.OBX;
import ca.uhn.hl7v2.model.v24.segment.PD1;
import ca.uhn.hl7v2.model.v24.segment.PID;
import ca.uhn.hl7v2.model.v24.segment.PV1;
import ca.uhn.hl7v2.model.v24.segment.ROL;
import ca.uhn.hl7v2.model.v24.segment.UB1;
import com.hl7.main.PatientDetails;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vmanchala
 */
public class SegmentsFactory {
    
    private static PatientDetails pt;

    public static void getMSHSegment(MSH msh) {
        try {
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
        } catch (DataTypeException ex) {
            Logger.getLogger(SegmentsFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void getEVNSegment(EVN evn) {        
        try {
            evn.getEventTypeCode().setValue("A01");
            evn.getRecordedDateTime().getTimeOfAnEvent().setValue("2015-02-12");
            evn.getDateTimePlannedEvent().getTimeOfAnEvent().setValue(pt.getEventDate().toString());
            evn.getEventReasonCode().setValue("Reason Code");
            evn.getOperatorID(0).getIDNumber().setValue("12345");
            evn.getEventOccurred().getTimeOfAnEvent().setValue("12:00PM");
            evn.getEventFacility().getNamespaceID().setValue("Event Facility");
        } catch (DataTypeException ex) {
            Logger.getLogger(SegmentsFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HL7Exception ex) {
            Logger.getLogger(SegmentsFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void getPIDSegment(PID pid) {
        try {
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
        } catch (DataTypeException ex) {
            Logger.getLogger(SegmentsFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HL7Exception ex) {
            Logger.getLogger(SegmentsFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public static void getPD1Segment(PD1 pd1) {
        try {
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
        } catch (HL7Exception ex) {
            Logger.getLogger(SegmentsFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public static void getNK1Segment(NK1 nk1) {        
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
    }
     
    public static void getPV1Segment(PV1 pv1) {
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
    }
    
    public static void getDB1Segment(DB1 db1) {
        try {
            db1.getSetIDDB1().setValue("");
            db1.getDisabledPersonCode().setValue("");
            db1.getDisabledPersonIdentifier(0).getID().setValue("");
            db1.getDisabilityIndicator().setValue("");
            db1.getDisabilityStartDate().setValue("");
            db1.getDisabilityEndDate().setValue("");
            db1.getDisabilityReturnToWorkDate().setValue("");
            db1.getDisabilityUnableToWorkDate().setValue("");
        } catch (DataTypeException ex) {
            Logger.getLogger(SegmentsFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HL7Exception ex) {
            Logger.getLogger(SegmentsFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void getOBXSegment(OBX obx) {
        try {
            obx.getSetIDOBX().setValue("OBX");
            obx.getValueType().setValue("");
            obx.getObservationIdentifier().getIdentifier().setValue("");
            obx.getObservationSubId().setValue("");
            obx.getObservationValue();
            obx.getUnits().getText().setValue("");
            obx.getReferencesRange().setValue("");
            obx.getAbnormalFlags().setValue("");
            obx.getProbability();
            obx.getNatureOfAbnormalTest().setValue("");
            obx.getObservationResultStatus().setValue("");
            obx.getDateLastObservationNormalValue().getTimeOfAnEvent().setValue("");
            obx.getUserDefinedAccessChecks().setValue("");
            obx.getDateTimeOfTheObservation().getTimeOfAnEvent().setValue("");
            obx.getProducerSID().getText().setValue("");
            obx.getResponsibleObserver();
            obx.getObservationMethod(0).getIdentifier().setValue("");
        } catch (DataTypeException ex) {
            Logger.getLogger(SegmentsFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HL7Exception ex) {
            Logger.getLogger(SegmentsFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void getAL1Segment(AL1 al1) {
        try {
           al1.getSetIDAL1().getIdentifier().setValue("AL1");
           al1.getAllergenTypeCode().getIdentifier().setValue("");
           al1.getAllergySeverityCode().getIdentifier().setValue("");
           al1.getAllergyReactionCode(0).setValue("");
           al1.getIdentificationDate().setValue("");
        } catch (DataTypeException ex) {
            Logger.getLogger(SegmentsFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HL7Exception ex) {
            Logger.getLogger(SegmentsFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public static void getDG1Segment(DG1 dg1) {
        try {
           dg1.getSetIDDG1().setValue("venu");
           dg1.getDiagnosisCodingMethod().setValue("");           
           dg1.getDiagnosisCodeDG1().getNameOfCodingSystem().setValue(pt.getDiagnosisCode().isEmpty() ? "" : pt.getDiagnosisCode());
           dg1.getDiagnosisDateTime().getTimeOfAnEvent().setValue(pt.getDiagnosisStartDate().toString());
        } catch (DataTypeException ex) {
            Logger.getLogger(SegmentsFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HL7Exception ex) {
            Logger.getLogger(SegmentsFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public static void getDRGSegment(DRG drg) {
        try {
           drg.getDiagnosticRelatedGroup().getIdentifier().setValue("DGR");
           drg.getDRGAssignedDateTime().getTimeOfAnEvent().setValue("");
           drg.getDRGApprovalIndicator().setValue("");
           drg.getDRGGrouperReviewCode().setValue("");
           drg.getOutlierType().getIdentifier().setValue("");
           drg.getOutlierDays().setValue("");
           drg.getOutlierCost().getPriceType().setValue("");
           drg.getDRGPayor().setValue("");
           drg.getOutlierReimbursement().getPriceType().setValue("");
           drg.getConfidentialIndicator().setValue("");
        } catch (DataTypeException ex) {
            Logger.getLogger(SegmentsFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HL7Exception ex) {
            Logger.getLogger(SegmentsFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public static void getADT_A01_PROCEDURESegment(ADT_A01_PROCEDURE pr1) {
        try {
          pr1.getPR1().getSetIDPR1().setValue("");
          pr1.getPR1().getProcedureCode().getIdentifier().setValue("");
          pr1.getPR1().getProcedureCodingMethod().setValue("");
          pr1.getPR1().getProcedureDescription().setValue("");
          pr1.getPR1().getProcedureDateTime().getTimeOfAnEvent().setValue("");
          pr1.getPR1().getProcedureFunctionalType().setValue("");
          pr1.getPR1().getProcedureMinutes().setValue("");
          pr1.getPR1().getAnesthesiologist(0).getFamilyName().getSurname().setValue("");
          pr1.getPR1().getAnesthesiaMinutes().setValue("");
          pr1.getPR1().getAnesthesiaCode().setValue("");
          pr1.getPR1().getSurgeon(0).getFamilyName().getSurname().setValue("");
          pr1.getPR1().getProcedurePractitioner(0).getFamilyName().getSurname().setValue("");
          pr1.getPR1().getConsentCode().getIdentifier().setValue("");
          pr1.getPR1().getProcedurePriority().setValue("");
          pr1.getPR1().getAssociatedDiagnosisCode().getIdentifier().setValue("");
          
        } catch (DataTypeException ex) {
            Logger.getLogger(SegmentsFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HL7Exception ex) {
            Logger.getLogger(SegmentsFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public static void getROLSegment(ROL rol) {
        try {
           rol.getRoleInstanceID().getEntityIdentifier().setValue("");
           rol.getActionCode().setValue("");
           rol.getRoleROL().getIdentifier().setValue("");
           rol.getRolePerson(0).getFamilyName().getSurname().setValue("");
           rol.getRoleBeginDateTime().getTimeOfAnEvent().setValue("");
           rol.getRoleEndDateTime().getTimeOfAnEvent().setValue("");
           rol.getRoleDuration().getIdentifier().setValue("");
           rol.getRoleActionReason().getIdentifier().setValue("");
           
        } catch (DataTypeException ex) {
            Logger.getLogger(SegmentsFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HL7Exception ex) {
            Logger.getLogger(SegmentsFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public static void getGT1Segment(GT1 gt1) {
        try {
      gt1.getSetIDGT1().setValue("");
      gt1.getGuarantorNumber(0).getIdentifierTypeCode().setValue("");
      gt1.getGuarantorName(0).getFamilyName().getSurname().setValue("");
      gt1.getGuarantorSpouseName(0).getFamilyName().getSurname().setValue("");
      gt1.getGuarantorAddress(0).getStreetAddress().getStreetName().setValue("");
      gt1.getGuarantorPhNumHome(0).getTelecommunicationUseCode().setValue("");
      gt1.getGuarantorPhNumBusiness(0).getTelecommunicationUseCode().setValue("");
      gt1.getGuarantorDateTimeOfBirth().getTimeOfAnEvent().setValue("");
      
      gt1.getGuarantorType().setValue("");
      gt1.getGuarantorRelationship().getIdentifier().setValue("");
      gt1.getGuarantorSSN().setValue("");
      gt1.getGuarantorDateBegin().setValue("");
      gt1.getGuarantorDateEnd().setValue("");
      gt1.getGuarantorPriority().setValue("");
      gt1.getGuarantorEmployerAddress(0).getStreetAddress().getStreetName().setValue("");
      gt1.getGuarantorEmployerPhoneNumber(0).getTelecommunicationUseCode().setValue("");
      gt1.getGuarantorEmployeeIDNumber(0).getCheckDigit().setValue("");
      gt1.getGuarantorEmploymentStatus().setValue("");
      gt1.getGuarantorOrganizationName(0).getOrganizationNameTypeCode().setValue("");
      gt1.getGuarantorBillingHoldFlag().setValue("");
      gt1.getGuarantorCreditRatingCode().getIdentifier().setValue("");
      gt1.getGuarantorDeathDateAndTime().getTimeOfAnEvent().setValue("");
      gt1.getGuarantorDeathFlag().setValue("");
      gt1.getGuarantorChargeAdjustmentCode().getIdentifier().setValue("");
      gt1.getGuarantorHouseholdAnnualIncome().getPriceType().setValue("");
      gt1.getGuarantorHouseholdSize().setValue("");
      gt1.getGuarantorEmployeeIDNumber(0).getCheckDigit().setValue("");
      gt1.getGuarantorMaritalStatusCode().getIdentifier().setValue("");
      gt1.getGuarantorHireEffectiveDate().setValue("");
      //gt1.getGuarantorEmploymentStopDate().setValue("");
      gt1.getLivingDependency().setValue("");
      gt1.getAmbulatoryStatus(0).setValue("");
      gt1.getCitizenship(0).getIdentifier().setValue("");
      gt1.getPrimaryLanguage().getIdentifier().setValue("");
      gt1.getLivingArrangement().setValue("");
      gt1.getPublicityCode().getIdentifier().setValue("");
      gt1.getProtectionIndicator().setValue("");
      gt1.getStudentIndicator().setValue("");
      gt1.getReligion().getIdentifier().setValue("");
      gt1.getMotherSMaidenName(0).getFamilyName().getSurname().setValue("");
      gt1.getNationality().getIdentifier().setValue("");
      gt1.getEthnicGroup(0).getIdentifier().setValue("");
      gt1.getContactPersonSName(0).getFamilyName().getSurname().setValue("");
      gt1.getContactPersonSTelephoneNumber(0).getTelecommunicationUseCode().setValue("");
      gt1.getContactReason().getIdentifier().setValue("");
      gt1.getContactRelationship().setValue("");
      gt1.getJobTitle().setValue("");
      gt1.getJobCodeClass().getJobCode().setValue("");
      gt1.getGuarantorEmployerSOrganizationName(0).getIdentifierTypeCode().setValue("");
      gt1.getHandicap().setValue("");
      gt1.getJobStatus().setValue("");
      gt1.getGuarantorFinancialClass().getFinancialClass().setValue("");
      gt1.getGuarantorRace(0).getIdentifier().setValue("");
           
        } catch (DataTypeException ex) {
            Logger.getLogger(SegmentsFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HL7Exception ex) {
            Logger.getLogger(SegmentsFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public static void getACCSegment(ACC acc) {
        try {
           
           acc.getAccidentDateTime().getTimeOfAnEvent().setValue("");
           acc.getAccidentCode().getIdentifier().setValue("");
           acc.getAccidentLocation().setValue("");
           acc.getAutoAccidentState().getIdentifier().setValue("");
           acc.getAccidentJobRelatedIndicator().setValue("");
           acc.getAccidentDeathIndicator().setValue("");
        } catch (DataTypeException ex) {
            Logger.getLogger(SegmentsFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HL7Exception ex) {
            Logger.getLogger(SegmentsFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public static void getUB1Segment(UB1 ub1) {
        try {
           
           ub1.getSetIDUB1().setValue("");
           ub1.getBloodDeductible().setValue("");
           ub1.getBloodFurnishedPintsOf().setValue("");
           ub1.getBloodReplacedPints().setValue("");
           ub1.getBloodNotReplacedPints().setValue("");
           ub1.getCoInsuranceDays().setValue("");
           ub1.getConditionCode3539(0).setValue("");
           ub1.getCoveredDays().setValue("");
           ub1.getNonCoveredDays().setValue("");
           ub1.getValueAmountCode4649(0).getValueCode().setValue("");
           ub1.getNumberOfGraceDays().setValue("");
           ub1.getSpecialProgramIndicator().getIdentifier().setValue("");
           ub1.getPSROURApprovalIndicator().getIdentifier().setValue("");
           ub1.getPSROURApprovedStayFm().setValue("");
           ub1.getPSROURApprovedStayTo().setValue("");
           ub1.getOccurrenceSpan().getIdentifier().setValue("");
           ub1.getOccurSpanStartDate().setValue("");
           ub1.getOccurSpanEndDate().setValue("");
           ub1.getUB82Locator2().setValue("");
           ub1.getUB82Locator9().setValue("");
           ub1.getUB82Locator27().setValue("");
           ub1.getUB82Locator45().setValue("");           
        } catch (DataTypeException ex) {
            Logger.getLogger(SegmentsFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HL7Exception ex) {
            Logger.getLogger(SegmentsFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void getADT_A01_INSURANCESegment(ADT_A01_INSURANCE in1) {
        try {
          
           in1.getIN1().getSetIDIN1().setValue("");
           in1.getIN1().getInsurancePlanID().getIdentifier().setValue("");
           in1.getIN1().getInsuranceCompanyID(0).getCheckDigit().setValue("");
           in1.getIN1().getInsuranceCompanyName(0).getOrganizationName().setValue("");
           in1.getIN1().getInsuranceCompanyAddress(0).getStreetAddress().getStreetName().setValue("");
           in1.getIN1().getInsuranceCoContactPerson(0).getFamilyName().getSurname().setValue("");
           in1.getIN1().getInsuranceCoPhoneNumber(0).getTelecommunicationUseCode().setValue("");
           in1.getIN1().getGroupNumber().setValue("");
           in1.getIN1().getGroupName(0).getOrganizationName().setValue("");
           in1.getIN1().getGroupName(0).getOrganizationName().setValue("");
           in1.getIN1().getInsuredSGroupEmpID(0).getCheckDigit().setValue("");
           in1.getIN1().getInsuredSGroupEmpName(0).getOrganizationName().setValue("");
           in1.getIN1().getPlanEffectiveDate().setValue("");
           in1.getIN1().getPlanExpirationDate().setValue("");
           in1.getIN1().getAuthorizationInformation().getAuthorizationNumber().setValue("");
           in1.getIN1().getPlanType().setValue("");
           in1.getIN1().getNameOfInsured(0).getFamilyName().getSurname().setValue("");
           in1.getIN1().getInsuredSRelationshipToPatient().getIdentifier().setValue("");
           in1.getIN1().getInsuredSDateOfBirth().getTimeOfAnEvent().setValue("");
           in1.getIN1().getInsuredSAddress(0).getStreetAddress().getStreetName().setValue("");
           in1.getIN1().getAssignmentOfBenefits().setValue("");
           in1.getIN1().getCoordinationOfBenefits().setValue("");
           in1.getIN1().getCoordOfBenPriority().setValue("");
           in1.getIN1().getNoticeOfAdmissionFlag().setValue("");
           in1.getIN1().getNoticeOfAdmissionDate().setValue("");
           in1.getIN1().getReportOfEligibilityFlag().setValue("");
           in1.getIN1().getReportOfEligibilityDate().setValue("");
           in1.getIN1().getReleaseInformationCode().setValue("");
           in1.getIN1().getPreAdmitCert().setValue("");
           in1.getIN1().getVerificationDateTime().getTimeOfAnEvent().setValue("");
           in1.getIN1().getVerificationBy(0).getIDNumber().setValue("");
           in1.getIN1().getTypeOfAgreementCode().setValue("");
           in1.getIN1() .getBillingStatus().setValue("");
           in1.getIN1().getLifetimeReserveDays().setValue("");
           in1.getIN1().getDelayBeforeLRDay().setValue("");
           in1.getIN1().getCompanyPlanCode().setValue("");
           in1.getIN1().getPolicyNumber().setValue("");
           in1.getIN1().getPolicyDeductible().getPriceType().setValue("");
           in1.getIN1().getPolicyLimitAmount().getPriceType().setValue("");
           in1.getIN1().getPolicyLimitDays().setValue("");
           in1.getIN1().getRoomRateSemiPrivate().getPriceType().setValue("");
           in1.getIN1().getRoomRatePrivate().getPriceType().setValue("");
           in1.getIN1().getInsuredSEmploymentStatus().getIdentifier().setValue("");
           
           in1.getIN1().getInsuredSEmployerSAddress(0).getStreetAddress().getStreetName().setValue("");
           in1.getIN1().getVerificationStatus().setValue("");
           in1.getIN1().getPriorInsurancePlanID().setValue("");
           in1.getIN1().getCoverageType().setValue("");
           
        } catch (DataTypeException ex) {
            Logger.getLogger(SegmentsFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HL7Exception ex) {
            Logger.getLogger(SegmentsFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    /**
     *
     * @param in2
     */
    public static void getADT_A02_INSURANCESegment(ADT_A01_INSURANCE in2) {
        try {
          
           in2.getIN2().getInsuredSEmployeeID(0).getCheckDigit().setValue("");
           in2.getIN2().getInsuredSSocialSecurityNumber().setValue("");
           in2.getIN2().getInsuredSEmployerSNameAndID(0).getFamilyName().getSurname().setValue("");
           in2.getIN2().getEmployerInformationData().setValue("");
           in2.getIN2().getMailClaimParty(0).setValue("");
           in2.getIN2().getMedicareHealthInsCardNumber().setValue("");
           in2.getIN2().getMedicaidCaseName(0).getFamilyName().getSurname().setValue("");
           in2.getIN2().getMedicaidCaseNumber().setValue("");
           
           in2.getIN2().getBabyCoverage().setValue("");
           in2.getIN2().getCombineBabyBill().setValue("");
           in2.getIN2().getBloodDeductible().setValue("");
           in2.getIN2().getSpecialCoverageApprovalName(0).getFamilyName().getSurname().setValue("");
           in2.getIN2().getSpecialCoverageApprovalTitle().setValue("");
           in2.getIN2().getNonCoveredInsuranceCode(0).setValue("");
           in2.getIN2().getPayorID(0).getCheckDigit().setValue("");
           in2.getIN2().getPayorSubscriberID(0).getCheckDigit().setValue("");
           in2.getIN2().getEligibilitySource().setValue("");
           in2.getIN2().getRoomCoverageTypeAmount(0).getRoomType().setValue("");
           in2.getIN2().getPolicyTypeAmount(0).getPolicyType().setValue("");
           in2.getIN2().getDailyDeductible().getDelayDays().setValue("");
           in2.getIN2().getLivingDependency().setValue("");
           in2.getIN2().getAmbulatoryStatus(0).setValue("");
           in2.getIN2().getCitizenship(0).getIdentifier().setValue("");
           in2.getIN2().getPrimaryLanguage().getIdentifier().setValue("");
           in2.getIN2().getLivingArrangement().setValue("");
           in2.getIN2().getProtectionIndicator().setValue("");
           in2.getIN2().getStudentIndicator().setValue("");
           in2.getIN2().getReligion( ).getIdentifier().setValue("");
           in2.getIN2().getMotherSMaidenName(0).getFamilyName().getSurname().setValue("");
           in2.getIN2().getNationality().getIdentifier().setValue("");
           in2.getIN2().getEthnicGroup(0).getIdentifier().setValue("");
           in2.getIN2().getMaritalStatus(0).getIdentifier().setValue("");
           in2.getIN2().getInsuredSEmploymentStartDate().setValue("");
           in2.getIN2().getJobTitle().setValue("");
           in2.getIN2().getJobCodeClass().getJobCode().setValue("");
           in2.getIN2().getJobStatus().setValue("");
           in2.getIN2().getEmployerContactPersonName(0).getFamilyName().getSurname().setValue("");
           in2.getIN2().getEmployerContactPersonPhoneNumber(0).getTelecommunicationUseCode().setValue("");
           in2.getIN2().getEmployerContactReason().setValue("");
           in2.getIN2().getInsuredSContactPersonPhoneNumber(0).getTelecommunicationUseCode().setValue("");
           in2.getIN2().getInsuredSContactPersonReason(0).setValue("");
           in2.getIN2().getRelationshipToThePatientStartDate().setValue("");
           in2.getIN2().getRelationshipToThePatientStopDate(0).setValue("");        
           in2.getIN2().getInsuredSContactPersonReason(0).setValue("");
           in2.getIN2().getInsuredSContactPersonPhoneNumber(0).getTelecommunicationUseCode().setValue("");
           in2.getIN2().getPolicyScope().setValue("");
            in2.getIN2().getPolicySource().setValue("");
            in2.getIN2().getPatientMemberNumber().getCheckDigit().setValue("");
            in2.getIN2().getGuarantorSRelationshipToInsured().getIdentifier().setValue("");
            in2.getIN2().getInsuredSPhoneNumberHome(0).getTelecommunicationUseCode().setValue("");
            in2.getIN2().getInsuredSEmployerPhoneNumber(0).getTelecommunicationUseCode().setValue("");
            in2.getIN2().getMilitaryHandicappedProgram().getIdentifier().setValue("");
         in2.getIN2().getSuspendFlag().setValue("");
         in2.getIN2().getCopayLimitFlag().setValue("");
         in2.getIN2().getStoplossLimitFlag().setValue("");
         in2.getIN2().getInsuredOrganizationNameAndID(0).getOrganizationName().setValue("");
         in2.getIN2().getInsuredEmployerOrganizationNameAndID(0).getOrganizationName().setValue("");
         in2.getIN2().getRace(0).getIdentifier().setValue("");
         in2.getIN2().getHCFAPatientSRelationshipToInsured().getIdentifier().setValue("");
         
           
        } catch (DataTypeException ex) {
            Logger.getLogger(SegmentsFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HL7Exception ex) {
            Logger.getLogger(SegmentsFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public static void setPatientDetails(PatientDetails pt) {
        SegmentsFactory.pt = pt;
    }
    
    private static String isEmpty(String str) {
        if (str.isEmpty()) {
            return "";
        } else {
            return str;
        }
    }

}
