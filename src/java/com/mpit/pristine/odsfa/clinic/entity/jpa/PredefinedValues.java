/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mpit.pristine.odsfa.clinic.entity.jpa;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author neemarh
 */
@XmlRootElement
public class PredefinedValues {

    private List<String> predefinedValues;
    private List<String> normalValues;

    public PredefinedValues() {
    }

    
    public PredefinedValues(List<String> predefinedValues, List<String> normalValues) {
        this.predefinedValues = predefinedValues;
        this.normalValues = normalValues;
    }

    
    /**
     * Get the value of normalValues
     *
     * @return the value of normalValues
     */
    public List<String> getNormalValues() {
        return normalValues;
    }

    /**
     * Set the value of normalValues
     *
     * @param normalValues new value of normalValues
     */
    public void setNormalValues(List<String> normalValues) {
        this.normalValues = normalValues;
    }

    /**
     * Get the value of predefinedValues
     *
     * @return the value of predefinedValues
     */
    public List<String> getPredefinedValues() {
        return predefinedValues;
    }

    /**
     * Set the value of predefinedValues
     *
     * @param predefinedValues new value of predefinedValues
     */
    public void setPredefinedValues(List<String> predefinedValues) {
        this.predefinedValues = predefinedValues;
    }

    @Override
    public String toString() {
        return "PredefinedValues{" + "predefinedValues=" + predefinedValues + ", normalValues=" + normalValues + '}';
    }

    
}
