/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mpit.pristine.odsfa.clinic.entity.jaxb;

import com.mpit.pristine.odsfa.clinic.entity.jpa.PredefinedValues;
import com.mpit.pristine.odsfa.clinic.entity.jpa.Range;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author najim-seun
 */
public class JAXBTest {

    private static final Logger LOG = Logger.getLogger(JAXBTest.class.getName());

    private static final String REACTIVE = "Reactive" ;
    private static final String NON_REACTIVE = "Non Reactive" ;
    private static final String POSITIVE = "Positive" ;
    private static final String NEGATIVE = "Negative" ;
    private static final String ONETWENTTY = "1/20" ;
    private static final String ONEFOURTY = "1/40" ;
    private static final String ONESIXTY = "1/60" ;
    private static final String ONEEIGHTY = "1/80";
    private static final String ONEONEHUNDRED = "1/100" ;
    private static final String ONEONETWENTY = "1/120" ;
    
    public static void main(String[] args) {

        JAXBTest test = new JAXBTest();
        List<String> list = new ArrayList<>();
        List<String> predefVals = new ArrayList<>();
        List<String> normalVals = new ArrayList<>();
        
        
        list.add("Random Blood Sugar");
        list.add("Fasting Blood Sugar");
        test.createJAXBInvCofig("Blood Sugar", "Biochemical Investigation", list,2,"BloodSugar");
        
        String fileName = "BloodSugar" + "__" + "Random Blood Sugar" + ".xml" ;
        test.createInvestigationConfig("Random Blood Sugar", new Range(70,140,0), null, true, false, fileName,"mg/dl");
        
        fileName = "BloodSugar" + "__" + "Fasting Blood Sugar" + ".xml" ;
        test.createInvestigationConfig("Fasting Blood Sugar", new Range(70,100,0), null, true, false, fileName,"mg/dl");
        
        
        
        //HEPATITIS PANEL TEST
        
        
        list.clear();
        list.add("HbsAg");
        list.add("Anti HCV");
        test.createJAXBInvCofig("Heptitisa Panel Test", "Hematological Investigation", list , 2 , "HepatitisPanelTest");
        
        fileName = "HepatitisPanelTest" + "__" + "HbsAg" + ".xml" ;
        predefVals.add(JAXBTest.REACTIVE);
        predefVals.add(JAXBTest.NON_REACTIVE);
        normalVals.add(JAXBTest.NON_REACTIVE);
        test.createInvestigationConfig("HbsAg" , null, new PredefinedValues(predefVals , normalVals), false, true, fileName,"");
        fileName = "HepatitisPanelTest" + "__" + "Anti HCV" + ".xml" ;
        test.createInvestigationConfig("Anti HCV" , null, new PredefinedValues(predefVals , normalVals), false, true,fileName,"");
        
        normalVals.clear();
        predefVals.clear();
        list.clear();
        
        
        list.add("LDL");
        list.add("HDL");
        list.add("Triglycerides");
        list.add("Total cholesterol");
         
        test.createJAXBInvCofig("lipid Profile", "Biochemical Investigation", list, 4, "LipidProfile");
        
        
        fileName = "LipidProfile" + "__" + "LDL" + ".xml";
        test.createInvestigationConfig("LDL", new Range(100,0,29), null, true, false, fileName, "mg/dl");
        fileName = "LipidProfile" + "__" + "HDL" + ".xml";
        test.createInvestigationConfig("HDL", new Range(60,40,0), null, true, false, fileName, "mg/dl");
        fileName = "LipidProfile" + "__" + "Triglycerides" + ".xml";
        test.createInvestigationConfig("Triglycerides", new Range(100,0,0), null, true, true, fileName, "mg/dl");
        fileName = "LipidProfile" + "__" + "Total cholesterol" + ".xml";
        test.createInvestigationConfig("Total cholesterol", new Range(200,0,0), null, true, false, fileName, "mg/dl");
        
        list.clear();
        
        
        list.add("Malaria Parasite");
        test.createJAXBInvCofig("Malaria Parasite", "Microbiological/Parasitological Investigation", list, 1, "MalariaParasite");
        
        predefVals.add(JAXBTest.NEGATIVE);
        predefVals.add(JAXBTest.POSITIVE);
        normalVals.add(JAXBTest.NEGATIVE) ;
        
        fileName = "MalariaParasite" + "__" + "Malaria Parasite" + ".xml";
        test.createInvestigationConfig("Malaria Parasite", null, new PredefinedValues(predefVals , normalVals), false, true, fileName, "");
        
        normalVals.clear();
        predefVals.clear();
        list.clear();
        
        
        list.add("Packed Cell Volume");
        test.createJAXBInvCofig("Packed Cell Volume", "Hematological Investigation" , list, 1, "PackedCellVolume");
        
        fileName = "PackedCellVolume" + "__" + "Packed Cell Volume" + ".xml";
        test.createInvestigationConfig("Packed Cell Volume", new Range(), null, true, false, fileName, "%");
        
        list.clear();
        
        
        list.add("Pregnancy Test");
        test.createJAXBInvCofig("Pregnancy Test", "Biochemical Investigation", list, 1, "PregnancyTest");
        
        fileName = "PregnancyTest" + "__" + "Pregnancy Test" + ".xml";
        test.createInvestigationConfig("Pregnancy Test", new Range(), null, true, false, fileName, "");
        
        
        list.clear();
        
        
        list.add("S Paratyphi A OAg");
        list.add("S Paratyphi B OAg");
        list.add("S Paratyphi C OAg");
        list.add("S Typhi OAg");
        list.add("S Paratyphi A HAg");
        list.add("S Paratyphi B HAg");
        list.add("S Paratyphi C HAg");
        list.add("S Typhi HAg");
        test.createJAXBInvCofig("Widal Test", "Microbiological/Parasitological Investigation", list, 8, "WidalTest");
        
        predefVals.add(JAXBTest.ONETWENTTY);
        predefVals.add(JAXBTest.ONEFOURTY);
        predefVals.add(JAXBTest.ONESIXTY);
        predefVals.add(JAXBTest.ONEEIGHTY);
        predefVals.add(JAXBTest.ONEONEHUNDRED);
        predefVals.add(JAXBTest.ONEONETWENTY);
        
        normalVals.add(JAXBTest.ONETWENTTY) ;
        normalVals.add(JAXBTest.ONEFOURTY);
        normalVals.add(JAXBTest.ONESIXTY);
        
        fileName =  "WidalTest" + "__" + "S Paratyphi A OAg" + ".xml";
        test.createInvestigationConfig("S Paratyphi A OAg", null, new PredefinedValues(predefVals , normalVals), false, true, fileName, "");
        
        fileName =  "WidalTest" + "__" + "S Paratyphi B OAg" + ".xml";
        test.createInvestigationConfig("S Paratyphi B OAg", null, new PredefinedValues(predefVals , normalVals), false, true, fileName, "");
        
        fileName =  "WidalTest" + "__" + "S Paratyphi C OAg" + ".xml";
        test.createInvestigationConfig("S Paratyphi C OAg", null, new PredefinedValues(predefVals , normalVals), false, true, fileName, "");
        
        fileName =  "WidalTest" + "__" + "S Typhi OAg" + ".xml";
        test.createInvestigationConfig("S Typhi OAg", null, new PredefinedValues(predefVals , normalVals), false, true, fileName, "");
        
        fileName =  "WidalTest" + "__" + "S Paratyphi A HAg" + ".xml";
        test.createInvestigationConfig("S Paratyphi A HAg", null, new PredefinedValues(predefVals , normalVals), false, true, fileName, "");
        
        fileName =  "WidalTest" + "__" + "S Paratyphi B HAg" + ".xml";
        test.createInvestigationConfig("S Paratyphi B HAg", null, new PredefinedValues(predefVals , normalVals), false, true, fileName, "");
        
        fileName =  "WidalTest" + "__" + "S Paratyphi C HAg" + ".xml";
        test.createInvestigationConfig("S Paratyphi C HAg", null, new PredefinedValues(predefVals , normalVals), false, true, fileName, "");
        
        fileName =  "WidalTest" + "__" + "S Typhi HAg" + ".xml";
        test.createInvestigationConfig("S Typhi HAg", null, new PredefinedValues(predefVals , normalVals), false, true, fileName, "");
        
        
        list.clear();
        normalVals.clear();
        predefVals.clear();
    }

    private void createJAXBInvCofig(String name, String type, List<String> list , int numParam ,String outFileName) {
        JAXBInvestigationConfig jic = new JAXBInvestigationConfig();
        jic.setName(name);
        jic.setType(type);
        jic.setNumberOfParams(numParam);
        jic.setInvestigationParameterList(list);

        try {
            JAXBContext jc = JAXBContext.newInstance(JAXBInvestigationConfig.class);
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            String fname = outFileName + ".xml" ;
            m.marshal(jic, new File(fname));
        } catch (JAXBException ex) {
            Logger.getLogger(JAXBTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
         
    }


    private  void createInvestigationConfig(String parameterName  , Range range,PredefinedValues predefinedValues,boolean hasRange,boolean hasPredefinedValues, String outFileName , String paramUnit){
        
        JAXBInvestigationParameterConfig jic = new JAXBInvestigationParameterConfig();
        jic.setParameterName(parameterName);
        jic.setHasRange(hasRange);
        jic.setRange(range);
        jic.setHasPredefinedValues(hasPredefinedValues);
        jic.setPredefinedValues(predefinedValues);
        jic.setParamUnit(paramUnit);
        try {
            JAXBContext jc = JAXBContext.newInstance(JAXBInvestigationParameterConfig.class);
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            String fname = "./params/" + outFileName  ;
            m.marshal(jic, new File(fname));
        } catch (JAXBException ex) {
            Logger.getLogger(JAXBTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
