/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mpit.pristine.odsfa.clinic.entity.jpa;

import com.mpit.pristine.odsfa.clinic.util.ODSFAConstant;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

/**
 *
 * @author neemarh
 */
@Entity
@NamedQueries(
        @NamedQuery(name = "allPatients", query = "select p from Patient p"))
public class Patient implements Serializable {

    @Transient
    private Integer objId = null ;
    
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String cardNumber;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(nullable = false)
    private String address;

    @Column(nullable = true)
    private String maritalStatus;

    @Column(nullable = false)
    private String sex;

    @Column(nullable = true)
    private String ethnicity;

    @Column(nullable = true)
    private String bloodGroup;

    @Column(nullable = true)
    private String genotype;

    @Column(nullable = false)
    private String phoneNumber;

    @Transient
    private String age;

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Investigation> investigations;

    @Transient
    private HashMap<String, List<Investigation>> investigationsMap ;

    public HashMap<String, List<Investigation>> getInvestigationsMap() {
        return investigationsMap;
    }

    public void setInvestigationsMap(HashMap<String, List<Investigation>> investigationsMap) {
        this.investigationsMap = investigationsMap;
    }

    public int getObjId() {
        return objId;
    }

    public void setObjId(int objId) {
        this.objId = objId;
    }

    
    public String getAge() {
        
        return age;
    }

    public Patient() {
        if(this.objId == null){
          Random rand = new Random(new Date().getTime());
          this.objId = rand.nextInt(1000000);
        }
    }

    
    @PostLoad
    public void postLoadHandler() {
        
        this.setAgeUp();
        this.sortInvestigations();
    }

    private void setAgeUp() {
        if (this.dateOfBirth == null) {
            this.age = ODSFAConstant.ADULT;
        } else {
            LocalDate dobLd = new LocalDate(this.dateOfBirth.getTime());
            LocalDate tdLd = new LocalDate(new Date().getTime());

            Period p = new Period(dobLd, tdLd, PeriodType.years());
            age = p.getYears() + ODSFAConstant.YEARS;
        }
    }

    public void sortInvestigations() {
        
        if(this.investigations == null ||  this.investigations.isEmpty())
            return ;
        
        this.investigationsMap = new HashMap<>();
        for (Investigation inv : investigations) {
            String nameLoc = inv.getJiConfigBase().getName();
            if (!this.investigationsMap.containsKey(nameLoc)) {
                ArrayList<Investigation> newList = new ArrayList<>();
                newList.add(inv);
                this.investigationsMap.put(nameLoc, newList);
            } else {
                List<Investigation> oldMap = this.investigationsMap.get(nameLoc);
                oldMap.add(inv);
                Collections.sort(oldMap);
            }
        }
        
        
    }

    public void setAge(String age) {
        this.age = age;
    }

    public List<Investigation> getInvestigations() {
        return investigations;
    }

    public void setInvestigations(List<Investigation> investigations) {
        this.investigations = investigations;
    }

    /**
     * Get the value of phoneNumber
     *
     * @return the value of phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set the value of phoneNumber
     *
     * @param phoneNumber new value of phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get the value of genotype
     *
     * @return the value of genotype
     */
    public String getGenotype() {
        return genotype;
    }

    /**
     * Set the value of genotype
     *
     * @param genotype new value of genotype
     */
    public void setGenotype(String genotype) {
        this.genotype = genotype;
    }

    /**
     * Get the value of bloodGroup
     *
     * @return the value of bloodGroup
     */
    public String getBloodGroup() {
        return bloodGroup;
    }

    /**
     * Set the value of bloodGroup
     *
     * @param bloodGroup new value of bloodGroup
     */
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    /**
     * Get the value of ethnicity
     *
     * @return the value of ethnicity
     */
    public String getEthnicity() {
        return ethnicity;
    }

    /**
     * Set the value of ethnicity
     *
     * @param ethnicity new value of ethnicity
     */
    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    /**
     * Get the value of sex
     *
     * @return the value of sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * Set the value of sex
     *
     * @param sex new value of sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * Get the value of maritalStatus
     *
     * @return the value of maritalStatus
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * Set the value of maritalStatus
     *
     * @param maritalStatus new value of maritalStatus
     */
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    /**
     * Get the value of address
     *
     * @return the value of address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set the value of address
     *
     * @param address new value of address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get the value of cardNumber
     *
     * @return the value of cardNumber
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Set the value of cardNumber
     *
     * @param cardNumber new value of cardNumber
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Get the value of surname
     *
     * @return the value of surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Set the value of surname
     *
     * @param surname new value of surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.name);
        hash = 43 * hash + Objects.hashCode(this.surname);
        hash = 43 * hash + Objects.hashCode(this.cardNumber);
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
        final Patient other = (Patient) obj;
        if (!Objects.equals(this.cardNumber, other.cardNumber)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Patient{" + "name=" + name + ", surname=" + surname + ", cardNumber=" + cardNumber + ", Age=" + this.dateOfBirth + ", address=" + address + ", maritalStatus=" + maritalStatus + ", sex=" + sex + ", ethnicity=" + ethnicity + ", bloodGroup=" + bloodGroup + ", genotype=" + genotype + ", phoneNumber=" + phoneNumber + '}';
    }

    public void nullify(){
      this.cardNumber = null ;
      this.name= null ;
      this.surname = null ;
      this.address = null ;
      this.bloodGroup = null ;
      this.age = null ;
      this.dateOfBirth = null ;
      this.phoneNumber = null ;
      this.genotype = null ;
      this.bloodGroup = null ;
    }
}
