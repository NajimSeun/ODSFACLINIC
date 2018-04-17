/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mpit.pristine.odsfa.clinic.entity.jpa;

import com.mpit.pristine.odsfa.clinic.entity.jaxb.JAXBInvestigationParameterConfig;
import com.mpit.pristine.odsfa.clinic.util.ODSFAConstant;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

/**
 *
 * @author neemarh
 */
@Entity
public class InvestigationParameter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "invParamVal")
    private String value;

    @Column(nullable = true)
    private String valueInterpretation;

    @Column(nullable = true)
    private String rangeValueIndicator;

    @Transient
    private JAXBInvestigationParameterConfig jipc;

    public InvestigationParameter() {
    }

    public String getRangeValueIndicator() {
        return rangeValueIndicator;
    }

    public void setRangeValueIndicator(String rangeValueIndicator) {
        this.rangeValueIndicator = rangeValueIndicator;
    }

    public String getValueInterpretation() {
        return valueInterpretation;
    }

    public void setValueInterpretation(String valueInterpretation) {
        this.valueInterpretation = valueInterpretation;
    }

    @PrePersist
    public void prePersistHandler() {
        
        if(this.value.isEmpty() || this.value == null)
            return ;
        
        LOG.log(Level.SEVERE, "PrePersist clled");
        if (this.jipc.isHasPredefinedValues()) {
            if (this.jipc.getPredefinedValues().getNormalValues().contains(value)) {
                this.valueInterpretation = ODSFAConstant.NORMAL;
            } else {
                this.valueInterpretation = ODSFAConstant.ABNORMAL;
            }
        } else if (this.jipc.isHasRange()) {
            int intVal = Integer.parseInt(value);

            if (intVal >= this.jipc.getRange().getLowerLimit() && intVal <= this.jipc.getRange().getUpperLimit()) {
                this.valueInterpretation = ODSFAConstant.NORMAL;
                this.rangeValueIndicator = ODSFAConstant.WITHINRANGE;
            } else if (intVal < this.jipc.getRange().getLowerLimit()) {
                this.valueInterpretation = ODSFAConstant.ABNORMAL;
                this.rangeValueIndicator = ODSFAConstant.LOW;
            } else if (intVal > this.jipc.getRange().getLowerLimit()) {
                this.valueInterpretation = ODSFAConstant.ABNORMAL;
                this.rangeValueIndicator = ODSFAConstant.HIGH;
            }

        }

    }

    public JAXBInvestigationParameterConfig getJipc() {
        return jipc;
    }

    public void setJipc(JAXBInvestigationParameterConfig jipc) {
        this.jipc = jipc;
    }

    public String getValue() {
        return value;
    }

    /**
     * Set the value of value
     *
     * @param value new value of value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void loadInvestigationParameterConfig(String parameterJAXBResxName) {

    }
    private static final Logger LOG = Logger.getLogger(InvestigationParameter.class.getName());
    
    
}
