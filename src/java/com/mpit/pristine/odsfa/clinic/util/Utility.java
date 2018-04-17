/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mpit.pristine.odsfa.clinic.util;

import com.mpit.pristine.odsfa.clinic.entity.jaxb.JAXBInvestigationConfig;
import com.mpit.pristine.odsfa.clinic.entity.jaxb.JAXBInvestigationParameterConfig;
import com.mpit.pristine.odsfa.clinic.entity.jpa.BloodSugar;
import com.mpit.pristine.odsfa.clinic.entity.jpa.InvestigationParameter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author neemarh
 */
public class Utility {

    public static List<InvestigationParameter> loadJAXBInvestigationParameterList(String investigationName, JAXBInvestigationConfig jiconfig) {
        List<InvestigationParameter> params = new ArrayList<>();
        List<String> paramNameList = jiconfig.getInvestigationParameterList();
        for (int x = 0; x < jiconfig.getNumberOfParams(); x++) {
            InvestigationParameter param = new InvestigationParameter();
            param.setJipc(Utility.loadJAXBInvestigationParameterConfig(investigationName, paramNameList.get(x)));
            params.add(param);
        }

        return params ;
    }

    
    
    public static JAXBInvestigationConfig loadJAXBInvestigationConfig(String name) {

        JAXBInvestigationConfig jic = null;
        try {
            String path = ODSFAConstant.PATH_TO_JAXB_RESX + name + ODSFAConstant.XML_EXTENSION;
             
            JAXBContext jaxbCon = JAXBContext.newInstance(JAXBInvestigationConfig.class);
            Unmarshaller unmarshaller = jaxbCon.createUnmarshaller();
            jic = (JAXBInvestigationConfig) unmarshaller.unmarshal(JAXBInvestigationParameterConfig.class.getResourceAsStream(path));
        } catch (JAXBException ex) {
            Logger.getLogger(BloodSugar.class.getName()).log(Level.SEVERE, "File Input Exception", "File inputException");
        }
        return jic;
    }

    
    
    public static JAXBInvestigationParameterConfig loadJAXBInvestigationParameterConfig(String investigationName, String parameterName) {

        JAXBInvestigationParameterConfig jipc = null;
        try {
            String path = ODSFAConstant.PATH_TO_JAXB_RESX + investigationName + "__" + parameterName + ODSFAConstant.XML_EXTENSION;
             
            JAXBContext jaxbCon = JAXBContext.newInstance(JAXBInvestigationParameterConfig.class);
            Unmarshaller unmarshaller = jaxbCon.createUnmarshaller();
            
            jipc = (JAXBInvestigationParameterConfig) unmarshaller.unmarshal(JAXBInvestigationConfig.class.getResourceAsStream(path));
            jipc.setParameterNameNoSpace(jipc.getParameterName().replaceAll("\\s", ""));
             
        } catch (JAXBException ex) {
            Logger.getLogger(BloodSugar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jipc;

    }
}
