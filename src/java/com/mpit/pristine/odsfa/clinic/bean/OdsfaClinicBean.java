/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mpit.pristine.odsfa.clinic.bean;

import com.mpit.pristine.odsfa.clinic.ejb.OdsfaPatientManagerEJB;
import com.mpit.pristine.odsfa.clinic.entity.jpa.Investigation;
import com.mpit.pristine.odsfa.clinic.entity.jpa.InvestigationParameter;
import com.mpit.pristine.odsfa.clinic.entity.jpa.Patient;
import com.mpit.pristine.odsfa.clinic.entity.jpa.Requestable;
import com.mpit.pristine.odsfa.clinic.util.ODSFAConstant;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject; 
import javax.inject.Named;


/**
 *
 * @author neemarh
 */
@Named
@SessionScoped
public class OdsfaClinicBean implements Serializable {

    /**
     * Creates a new instance of OdsfaClinicBean
     */
    private Requestable requestable;
    private String enteredId = "";
    private Patient patient;
    private String templateName = "default";
    private String templateName_2 = "patientInfo";
    private boolean noInvestigationsRendered = false;
    private List<Requestable> allInvestigation;
    private List<Patient> allPatient;
    private boolean isEdit = false;
    private String edit = "";

    public boolean isIsEdit() {
        return isEdit;
    }

    public void setIsEdit(boolean isEdit) {
        this.isEdit = isEdit;
    }

    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }

    public String getTemplateName_2() {
        return templateName_2;
    }

    public void setTemplateName_2(String templateName_2) {
        this.templateName_2 = templateName_2;
    }

    public List<Patient> getAllPatient() {
        return allPatient;
    }

    public void setAllPatient(List<Patient> allPatient) {
        this.allPatient = allPatient;
    }

    public List<Requestable> getAllInvestigation() {
        return allInvestigation;
    }

    public void setAllInvestigation(List<Requestable> allInvestigation) {
        this.allInvestigation = allInvestigation;
    }

    public boolean isNoInvestigationsRendered() {
        return noInvestigationsRendered;
    }

    public void setNoInvestigationsRendered(boolean noInvestigationsRendered) {
        this.noInvestigationsRendered = noInvestigationsRendered;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    @Inject
    private OdsfaPatientManagerEJB opmEjb;

    public OdsfaClinicBean() {
        //this.patient = new Patient();
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public OdsfaPatientManagerEJB getOpmEjb() {
        return opmEjb;
    }

    public void setOpmEjb(OdsfaPatientManagerEJB opmEjb) {
        this.opmEjb = opmEjb;
    }

    public String getEnteredId() {
        return enteredId;
    }

    public void setEnteredId(String enteredId) {
        this.enteredId = enteredId;
    }

    public Requestable getRequestable() {
        return requestable;
    }

    public void setRequestable(Requestable requestable) {
        this.requestable = requestable;
    }

    public void odsfaOpenPatientButtonListener(AjaxBehaviorEvent evt) {
        if (this.patient != null) {
            FacesContext fcon = FacesContext.getCurrentInstance();
            fcon.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Close opened patient then try again", "Close opeded patient then try again"));

            return;

        }
        if (this.enteredId == null || this.enteredId.isEmpty()) {

            return;

        }

        patient = this.opmEjb.findPatient(enteredId);

        if (patient == null) {
            FacesContext fcon = FacesContext.getCurrentInstance();
            fcon.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No such patient", "No such patient"));
            return;
        }
        this.templateName_2 = ODSFAConstant.PATIENTINFOVIEW;
        this.templateName = ODSFAConstant.DEFAULTVIEW;

    }

    public void odsfaClosePatientButtonListener(AjaxBehaviorEvent evt) {

        if (this.patient == null) {
            return;
        }
         FacesContext fcon = FacesContext.getCurrentInstance();
        fcon.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, this.patient.getName() + " " + patient.getSurname() + " " + "closed", this.patient.getName() + " " + patient.getSurname() + " " + "closed"));
        this.patient = null;
        this.enteredId = "";
        this.templateName_2 = ODSFAConstant.PATIENTINFOVIEW;
        this.templateName = ODSFAConstant.DEFAULTVIEW;
        this.requestable = null;
       

    }

    public void odsfaMenuLinkAjaxListener(AjaxBehaviorEvent evt) {
        
        this.noInvestigationsRendered = false;
        if (this.patient == null) {
            System.out.print("Null Patient Called");
            return;
        }

        FacesContext fcon = FacesContext.getCurrentInstance();
        ExternalContext ec = fcon.getExternalContext();
        String paramName = ec.getRequestParameterMap().get("paramName");
        String paramType = ec.getRequestParameterMap().get("requestType");

        this.templateName_2 = ODSFAConstant.PATIENTINFOVIEW;

        if (paramType.equals("register")) {
            this.templateName = ODSFAConstant.RESULTENTRY;
            try {
                Class<?> className = Class.forName(ODSFAConstant.nameClassNameMap.get(paramName));
                requestable = (Requestable) className.newInstance();

            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(OdsfaClinicBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (paramType.equals("view")) {
            System.out.print("View Called");
            if (this.patient.getInvestigationsMap() == null) {
                fcon.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No investigations to view","No investigations to view"));
                System.out.print("View Called no investigations");
                return;
            }
            

            List<Investigation> invs = this.patient.getInvestigationsMap().get(paramName);
            if (invs == null || invs.isEmpty()) {
                fcon.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No " + paramName +" investigations to view","No investigations to view"));
                this.noInvestigationsRendered = false;
                System.out.print("Called inv is null");
                return;
            }
            this.requestable = (Requestable) invs.get(invs.size() - 1);
            if (this.requestable != null) {
                this.requestable.loadJAXBInvestigationConfigManually();
                System.out.print("Called investigation loaded");
                this.templateName = ODSFAConstant.RESULTVIEW;

            }
            
           
        } else if (paramType.equals("view All")) {
            

            if (this.patient.getInvestigationsMap() == null) {
                fcon.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No investigations to view" , "No investigations to view"));
                return;
            }

            List<Investigation> get = this.patient.getInvestigationsMap().get(paramName);
            if (get == null || get.isEmpty()) {
                fcon.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No" + " " + paramName + " " + "investigations to view" , "No" + " " + paramName + " " + "investigations to view"));
                this.noInvestigationsRendered = true;
                return;
            }

            this.allInvestigation = new ArrayList<>();
            for (Investigation in : get) {
                Requestable r = (Requestable) in;
                r.loadJAXBInvestigationConfigManually();
                this.allInvestigation.add(r);
            }

            this.templateName = ODSFAConstant.ALLRESULTVIEW;
            
             

        }

    }
    

    public void odsfaAllPatientAjaxListener(AjaxBehaviorEvent evt) {

        if (this.patient != null) {
            FacesContext fcon = FacesContext.getCurrentInstance();
            fcon.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Be done with opened patient close then try again", "Be done with openedpatient close then try again"));
            return;
        }
        allPatient = this.opmEjb.getAllPatient();
        this.templateName_2 = ODSFAConstant.ALLPATIENTSVIEW;
        this.templateName = ODSFAConstant.DUMMYVIEW;
    }

    public void odsfaResultSubmitButtonClickedListener(AjaxBehaviorEvent evt) {

        List<InvestigationParameter> parameters = this.requestable.getParameters();
        int badParamNum = 0;

        for (InvestigationParameter ip : parameters) {

            ip.setName(ip.getJipc().getParameterName());
            if (ip.getJipc().isHasPredefinedValues() && ip.getValue().equals("Select")) {
                ip.setValue("");
                badParamNum++;
            } else if (ip.getJipc().isHasRange() && (ip.getValue().equals("0") || ip.getValue().equals("0.0"))) {
                ip.setValue("");
                badParamNum++;
            }

        }
        FacesContext con = FacesContext.getCurrentInstance();

        if (badParamNum < this.requestable.getJiConfig().getNumberOfParams()) {
            this.requestable.setRequestDate(new Date());
            this.patient = this.opmEjb.mergeRequestable(patient, requestable);
            // this.patient.sortInvestigations();
            con.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Investigation saved sucessfully", "Investigation saved successfully"));
            return;
        }

        con.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Investigation not saved", "Investigation not saved"));

    }

    public void odsfaPatientPanelRequestListener(AjaxBehaviorEvent evt) {

        if (this.patient != null) {
            FacesContext fcon = FacesContext.getCurrentInstance();
            fcon.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Be done with opened patient, close then try again", "Be done with openedpatient close then try again"));
            return;
        }

        this.templateName_2 = ODSFAConstant.PATIENTREGVIEW;
        this.templateName = ODSFAConstant.DUMMYVIEW;
    }

    public void odsfaPatientEditRequestListener(AjaxBehaviorEvent evt) {

        if (this.patient == null) {
            return;
        }
        this.templateName_2 = ODSFAConstant.PATIENTREGVIEW;
        this.templateName = ODSFAConstant.DUMMYVIEW;
        this.edit = "Edit";
        this.isEdit = true;

    }

     public void odsfaPatientEditRequestListener() {

        if (this.patient == null) {
            return;
        }
        this.templateName_2 = ODSFAConstant.PATIENTREGVIEW;
        this.templateName = ODSFAConstant.DUMMYVIEW;
        this.edit = "Edit";
        this.isEdit = true;

    }

    public void odsfaAboutMenuLinkAjaxListener(AjaxBehaviorEvent evt) {
        this.templateName_2 = ODSFAConstant.ABOUTVIEW;
        this.templateName = ODSFAConstant.DUMMYVIEW;
    }

    public void odsfaAboutButtonAjaxListener(AjaxBehaviorEvent evt) {
        this.templateName_2 = ODSFAConstant.PATIENTINFOVIEW;
        this.templateName = ODSFAConstant.DEFAULTVIEW;
    }

    public void odsfaPatientRefreshRequestAjaxListener(AjaxBehaviorEvent evt) {

        if (this.patient == null) {
            return;
        }

        FacesContext fcon = FacesContext.getCurrentInstance();
        fcon.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Patient refreshed", "Patient refreshed"));

        this.patient = this.opmEjb.findPatient(this.patient.getCardNumber());
    }
}
