/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mpit.pristine.odsfa.clinic.entity.jaxb;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author najim-seun
 */
@XmlRootElement
@XmlType(propOrder = {"name", "type", "numberOfParams", "investigationParameterList"})
public class JAXBInvestigationConfig {

    private String name;

    private String type;

    //@XmlElementWrapper(name = "myList")
    //@XmlElement(name = "parameterName")
    private List<String> investigationParameterList;

    private int numberOfParams;

    public int getNumberOfParams() {
        return numberOfParams;
    }

    public void setNumberOfParams(int numberOfParams) {
        this.numberOfParams = numberOfParams;
    }

    public List<String> getInvestigationParameterList() {
        return investigationParameterList;
    }

    public void setInvestigationParameterList(List<String> investigationParameterList) {
        this.investigationParameterList = investigationParameterList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "JAXBInvestigationConfig{" + "name=" + name + ", type=" + type + ", investigationParameterList=" + investigationParameterList + ", numberOfParams=" + numberOfParams + '}';
    }

    
}
