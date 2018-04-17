/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mpit.pristine.odsfa.clinic.bean;

import com.mpit.pristine.odsfa.clinic.ejb.OdsfaPatientManagerEJB;
import com.mpit.pristine.odsfa.clinic.entity.jpa.Patient;
import com.mpit.pristine.odsfa.clinic.util.ODSFAConstant;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author neemarh
 */
@Named
@RequestScoped
public class OdsfaPatientRegistrationBean {

    /**
     * 
     * Creates a new instance of OdsfaPatientRegistrationBean
     */
    private Patient patient;
    @Inject
    private OdsfaPatientManagerEJB pmEjb;

    private boolean cardNumberFieldDisabled = false ;
    
    @Inject
    private OdsfaClinicBean ocb;

    @PostConstruct
    public void postConstruct() {
        if (ocb.isIsEdit()) {
            this.cardNumberFieldDisabled = true ;
            this.patient = ocb.getPatient();
        }
    }

    public boolean isCardNumberFieldDisabled() {
        return cardNumberFieldDisabled;
    }

    public void setCardNumberFieldDisabled(boolean cardNumberFieldDisabled) {
        this.cardNumberFieldDisabled = cardNumberFieldDisabled;
    }

    
    
    public OdsfaPatientManagerEJB getPmEjb() {
        return pmEjb; 
    }

    public void setPmEjb(OdsfaPatientManagerEJB pmEjb) {
        this.pmEjb = pmEjb;
    }

    public OdsfaClinicBean getOcb() {
        return ocb;
    }

    public void setOcb(OdsfaClinicBean ocb) {
        this.ocb = ocb;
    }

    public OdsfaPatientRegistrationBean() {
        this.patient = new Patient();
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

     
    public void OdsfaRegistrationButtonClickedListener(AjaxBehaviorEvent evt) {

        FacesContext fc = FacesContext.getCurrentInstance();
        if (this.ocb.isIsEdit()) {
            this.ocb.setIsEdit(false);
            if (this.pmEjb.findPatient(patient.getCardNumber()) != null) {
                this.pmEjb.mergePatient(patient);
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ODSFAConstant.PATIENTUPDATED, ODSFAConstant.PATIENTUPDATED));
            } else {
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ODSFAConstant.INVALIDIDNOTUPDTED, ODSFAConstant.INVALIDIDNOTUPDTED));
            }
        } else if (this.pmEjb.findPatient(patient.getCardNumber()) != null) {

            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ODSFAConstant.EXIST_CARD_NUM, ODSFAConstant.EXIST_CARD_NUM));
        } else {
            this.pmEjb.persist(patient);
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ODSFAConstant.REG_SUCCESS, ODSFAConstant.REG_SUCCESS));
            patient.nullify();
        }

    }
}
