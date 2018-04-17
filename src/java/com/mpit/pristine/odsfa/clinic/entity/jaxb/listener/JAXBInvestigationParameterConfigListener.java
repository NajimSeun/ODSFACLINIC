/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mpit.pristine.odsfa.clinic.entity.jaxb.listener;

import com.mpit.pristine.odsfa.clinic.entity.jaxb.JAXBInvestigationParameterConfig;
import javax.xml.bind.Unmarshaller.Listener;

/**
 *
 * @author neemarh
 */
public class JAXBInvestigationParameterConfigListener extends Listener{

    @Override
    public void afterUnmarshal(Object target, Object parent) {
        super.afterUnmarshal(target, parent); //To change body of generated methods, choose Tools | Templates.
      JAXBInvestigationParameterConfig jipc =  (JAXBInvestigationParameterConfig) target ;
      
    }

    @Override
    public void beforeUnmarshal(Object target, Object parent) {
        super.beforeUnmarshal(target, parent); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
