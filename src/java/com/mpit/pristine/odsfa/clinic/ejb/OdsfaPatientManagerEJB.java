/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package com.mpit.pristine.odsfa.clinic.ejb;

import com.mpit.pristine.odsfa.clinic.entity.jpa.Investigation;
import com.mpit.pristine.odsfa.clinic.entity.jpa.Patient;
import com.mpit.pristine.odsfa.clinic.entity.jpa.Requestable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author neemarh
 */
@Stateless
public class OdsfaPatientManagerEJB {
    @PersistenceContext(unitName = "OSDFACLINICPU")
    private EntityManager em;

    
    public Patient findPatient(String id) {
       return em.find(Patient.class, id) ;
    }

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void persist(Patient p) {
        em.persist(p);
    }
    
    public List<Patient> getAllPatient(){
        Query nq = em.createNamedQuery("allPatients");
       return (List<Patient>) nq.getResultList() ;
    }
    
    public Patient  mergeRequestable(Patient p , Requestable r){
    
        
        
       Investigation inv = (Investigation) r ;
        Patient findPatient = this.findPatient(p.getCardNumber());
        System.out.print("jpa call 1");
        if(p == findPatient)
        {
         System.out.print("They are equal");
        }
        List<Investigation> investigations = findPatient.getInvestigations();
        if(investigations == null || investigations.isEmpty()){
            investigations  = new ArrayList<>();
            investigations.add(inv);
            findPatient.setInvestigations(investigations);
        }
        else{
           investigations.add(inv);
           
        }
        
       em.merge(findPatient);
        
       return p;
       
    }
    
    public void mergePatient(Patient p){
      em.merge(p);
    }
}
