/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mpit.pristine.odsfa.clinic.util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author najim-seun
 */
public class ODSFAConstant {

    public static final String PATH_TO_JAXB_RESX = "/com/mpit/pristine/odsfa/clinic/jaxb/resx/";
    public static final String XML_EXTENSION = ".xml";
    public static final String ADULT = "Ad";
    public static final String YEARS = "years";
    public static final String EXIST_CARD_NUM = "Card number exists";
    public static final String REG_SUCCESS = "Patient Registration Successful";
    public static final String NORMAL = "Normal";
    public static final String ABNORMAL = "Abnormal";
    public static final String HIGH = "High";
    public static final String LOW = "Low";
    public static final String WITHINRANGE = "within_range";
    public static final String RESULTENTRY = "resultEntry";
    public static final String RESULTVIEW = "resultView";
    public static final String ALLRESULTVIEW = "allResultView";
    public static final String DEFAULTVIEW = "default" ;
    public static final String DUMMYVIEW = "dummy" ;
    public static final String PATIENTREGVIEW = "patientReg" ;
    public static final String PATIENTINFOVIEW = "patientInfo" ;
    public static final String ALLPATIENTSVIEW = "allPatients" ;
    public static final String PATIENTUPDATED = "Patient updated successfully" ;
    public static final String INVALIDIDNOTUPDTED = "Invalid number patient not updated";
    public static final String ABOUTVIEW = "about" ;
   public static final Map<String, String> nameClassNameMap = new HashMap<>();
    static {
       
        nameClassNameMap.put("Blood Sugar", "com.mpit.pristine.odsfa.clinic.entity.jpa.BloodSugar");
        nameClassNameMap.put("Malaria Parasite", "com.mpit.pristine.odsfa.clinic.entity.jpa.MalariaParasite");
        nameClassNameMap.put("Lipid Profile", "com.mpit.pristine.odsfa.clinic.entity.jpa.LipidProfile");
        nameClassNameMap.put("Packed Cell Volume", "com.mpit.pristine.odsfa.clinic.entity.jpa.PackedCellVolume");
        nameClassNameMap.put("Heptitis Panel Test", "com.mpit.pristine.odsfa.clinic.entity.jpa.HepatitisPanelTest");
        nameClassNameMap.put("Pregnancy Test", "com.mpit.pristine.odsfa.clinic.entity.jpa.PregnancyTest");
        nameClassNameMap.put("Widal Test", "com.mpit.pristine.odsfa.clinic.entity.jpa.Widal");
    }
}
