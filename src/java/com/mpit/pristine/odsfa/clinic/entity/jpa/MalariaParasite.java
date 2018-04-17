/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mpit.pristine.odsfa.clinic.entity.jpa;

import com.mpit.pristine.odsfa.clinic.entity.jaxb.JAXBInvestigationConfig;
import static com.mpit.pristine.odsfa.clinic.entity.jpa.BloodSugar.NAME;
import com.mpit.pristine.odsfa.clinic.util.Utility;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 *
 * @author neemarh
 */
@Entity
@DiscriminatorValue("mp")
public class MalariaParasite  extends Investigation implements Serializable , Requestable{

    private static String NAME = "MalariaParasite" ;
    
     
     

    public MalariaParasite() {
        this.setJiConfigBase(Utility.loadJAXBInvestigationConfig(NAME));
        this.setParameters(Utility.loadJAXBInvestigationParameterList(NAME, this.getJiConfigBase()));
    }

     @Override
    public List<InvestigationParameter> getParameters() {
        return this.getParametersBase();
}

    /**
     * Set the value of parameters
     *
     * @param parameters new value of parameters
     */
    @Override
    public void setParameters(List<InvestigationParameter> parameters) {
        this.setParametersBase(parameters);
    }

    @Override
    public String getName() {
        return this.getNameBase() ;
    }

    @Override
    public void setName(String name) {
        this.setNameBase(name);
    }

    @Override
    public void setType(String type) {
        this.setTypeBase(type);
    }

    @Override
    public String getType() {
       return  this.getTypeBase();
    }

    @Override
    public Date getRequestDate() {
        return this.getRequestDateBase();
    }

    @Override
    public void setRequestDate(Date date) {
        this.setRequestDateBase(date);
    }
    
    @Override
    public JAXBInvestigationConfig getJiConfig() {
        return this.getJiConfigBase() ;
    }

    @Override
    public void setJiConfig(JAXBInvestigationConfig jiConfig) {
        this.setJiConfigBase(jiConfig);
    }

    @Override
    public String getNAME() {
        return NAME ;
    }
    
    @Override
    public void loadJAXBInvestigationConfigManually() {
        
        if(this.getParameters().isEmpty() || this.getParameters() == null){
             return ;
        }
        
        for(InvestigationParameter ip : this.getParameters()){
          ip.setJipc(Utility.loadJAXBInvestigationParameterConfig(NAME, ip.getName()));
        }
    }
}
