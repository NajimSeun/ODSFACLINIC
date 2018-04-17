/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mpit.pristine.odsfa.clinic.entity.jpa;

import com.mpit.pristine.odsfa.clinic.entity.jaxb.JAXBInvestigationConfig;
import java.util.Date;
import java.util.List;

/**
 *
 * @author neemarh
 */
public interface Requestable {
    
    public  abstract String getName();
    public  abstract void setName(String name);
    public abstract  void setType(String type);
    public abstract String getType();
    public abstract Date getRequestDate();
    public abstract void setRequestDate(Date date);
    public abstract List<InvestigationParameter> getParameters();
    public abstract void setParameters(List<InvestigationParameter> parameter);
    public abstract JAXBInvestigationConfig getJiConfig();
    public abstract void setJiConfig(JAXBInvestigationConfig jiConfig);
    public abstract String getNAME();
    public abstract void loadJAXBInvestigationConfigManually();
    
}
