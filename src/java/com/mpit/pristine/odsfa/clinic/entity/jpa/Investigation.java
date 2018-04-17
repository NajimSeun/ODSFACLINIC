/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mpit.pristine.odsfa.clinic.entity.jpa;

import com.mpit.pristine.odsfa.clinic.entity.jaxb.JAXBInvestigationConfig;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author neemarh
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "INV_TYPE", discriminatorType = DiscriminatorType.STRING)
public class Investigation implements Serializable, Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Transient
    private String name;

    @Transient
    private String type;

    @Transient
    private JAXBInvestigationConfig jiConfig;

    @OneToMany
    private List<InvestigationParameter> parameters;

    @Temporal(TemporalType.DATE)
    @Column(nullable= false)
    private Date requestDate;

    public Date getRequestDateBase() {
        return requestDate;
    }

    public void setRequestDateBase(Date requestDate) {
        this.requestDate = requestDate;
    }

    public List<InvestigationParameter> getParametersBase() {
        return parameters;
    }

    public void setParametersBase(List<InvestigationParameter> parameters) {
        this.parameters = parameters;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getNameBase() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setNameBase(String name) {
        this.name = name;
    }

    /**
     * Get the value of type
     *
     * @return the value of type
     */
    public String getTypeBase() {
        return type;
    }

    /**
     * Set the value of type
     *
     * @param type new value of type
     */
    public void setTypeBase(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.type);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Investigation other = (Investigation) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }

    public JAXBInvestigationConfig getJiConfigBase() {
        return jiConfig;
    }

    public void setJiConfigBase(JAXBInvestigationConfig jiConfig) {
        this.jiConfig = jiConfig;
    }

    @Override
    public int compareTo(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
        if (!(o instanceof Investigation)) {
            throw new IllegalArgumentException();
        }

        Investigation otherInv = (Investigation) o;

        int comp = this.requestDate.compareTo(otherInv.getRequestDateBase());
        return (comp != 0) ? comp : this.id.compareTo(otherInv.id);
    }

}
