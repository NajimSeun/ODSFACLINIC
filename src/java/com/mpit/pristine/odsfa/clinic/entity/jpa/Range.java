/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mpit.pristine.odsfa.clinic.entity.jpa;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author neemarh
 */
@XmlRootElement
public class Range {

    private int upperLimit;

    private int lowerLimit;

    private int rangeVariation;

    public Range() {
    }

    
    public Range(int upperLimit, int lowerLimit, int rangeVariation) {
        this.upperLimit = upperLimit;
        this.lowerLimit = lowerLimit;
        this.rangeVariation = rangeVariation;
    }

    public int getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(int upperLimit) {
        this.upperLimit = upperLimit;
    }

    public int getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(int lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public int getRangeVariation() {
        return rangeVariation;
    }

    public void setRangeVariation(int rangeVariation) {
        this.rangeVariation = rangeVariation;
    }

    @Override
    public String toString() {
        return "Range{" + "upperLimit=" + upperLimit + ", lowerLimit=" + lowerLimit + ", rangeVariation=" + rangeVariation + '}';
    }

    
    
    
}
