/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mpit.pristine.odsfa.clinic.entity.jaxb;

import com.mpit.pristine.odsfa.clinic.entity.jpa.PredefinedValues;
import com.mpit.pristine.odsfa.clinic.entity.jpa.Range;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author najim-seun
 */
@XmlRootElement
@XmlType(propOrder = {"parameterName" , "range" ,"hasRange","hasPredefinedValues","predefinedValues","paramUnit"})
@XmlAccessorType(XmlAccessType.FIELD)
public class JAXBInvestigationParameterConfig   {
    
    private String parameterName ;
    
    private Range range;

    private PredefinedValues predefinedValues;

    private boolean hasRange;

    private boolean hasPredefinedValues;

    private String paramUnit ;

    @XmlTransient
    private String parameterNameNoSpace ;
    
    
    public String getParameterNameNoSpace() {
        return parameterNameNoSpace;
    }

    
    public void setParameterNameNoSpace(String parameterNameNoSpace) {
        this.parameterNameNoSpace = parameterNameNoSpace;
    }
    
     
    
    public String getParamUnit() {
        return paramUnit;
    }

    public void setParamUnit(String paramUnit) {
        this.paramUnit = paramUnit;
    }
    
    
    public boolean isHasRange() {
        return hasRange;
    }

    public void setHasRange(boolean hasRange) {
        this.hasRange = hasRange;
    }

    public boolean isHasPredefinedValues() {
        return hasPredefinedValues;
    }

    public void setHasPredefinedValues(boolean hasPredefinedValues) {
        this.hasPredefinedValues = hasPredefinedValues;
    }

    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    public PredefinedValues getPredefinedValues() {
        return predefinedValues;
    }

    public void setPredefinedValues(PredefinedValues predefinedValues) {
        this.predefinedValues = predefinedValues;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    @Override
    public String toString() {
        return "JAXBInvestigationParameterConfig{" + "parameterName=" + parameterName + ", range=" + range + ", predefinedValues=" + predefinedValues + ", hasRange=" + hasRange + ", hasPredefinedValues=" + hasPredefinedValues + ", paramUnit=" + paramUnit + '}';
    }
    
    
}
