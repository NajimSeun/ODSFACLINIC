/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mpit.pristine.odsfa.clinic.entity.jaxb;

import com.mpit.pristine.odsfa.clinic.entity.jpa.BloodSugar;
import com.mpit.pristine.odsfa.clinic.entity.jpa.HepatitisPanelTest;
import com.mpit.pristine.odsfa.clinic.entity.jpa.InvestigationParameter;
import com.mpit.pristine.odsfa.clinic.entity.jpa.LipidProfile;
import com.mpit.pristine.odsfa.clinic.entity.jpa.MalariaParasite;
import com.mpit.pristine.odsfa.clinic.entity.jpa.PackedCellVolume;
import com.mpit.pristine.odsfa.clinic.entity.jpa.PregnancyTest;
import com.mpit.pristine.odsfa.clinic.entity.jpa.Requestable;
import com.mpit.pristine.odsfa.clinic.entity.jpa.Widal;

/**
 *
 * @author najim-seun
 */
public class JAXB {
    
    public static void main(String [] args){
       
        BloodSugar bloodSugar = new BloodSugar();
        System.out.print("\n");
        print(bloodSugar);
        System.out.print("\n");
        
        HepatitisPanelTest hpt = new HepatitisPanelTest();
        System.out.print("\n");
        //System.out.print(hpt.getJiConfig().toString());
        print(hpt);
        System.out.print("\n");
        
        LipidProfile lp = new LipidProfile();
        System.out.print("\n");
        //System.out.print(lp.getJiConfig().toString());
        print(lp);
        System.out.print("\n");
        
        MalariaParasite mp = new MalariaParasite();
        System.out.print("\n");
        //System.out.print(mp.getJiConfig().toString());
        print(mp);
        System.out.print("\n");
        
        Widal wi = new Widal();
        System.out.print("\n");
        //System.out.print(wi.getJiConfig().toString());
        print(wi);
        System.out.print("\n");
        
        PregnancyTest pt = new PregnancyTest();
        System.out.print("\n");
        //System.out.print(pt.getJiConfig().toString());
        print(pt);
        System.out.print("\n");
        
        PackedCellVolume pcv = new PackedCellVolume();
        System.out.print("\n");
        //System.out.print(pcv.getJiConfig().toString());
        print(pcv);
        System.out.print("\n");
    }
    
    private static void print(Requestable p){
      for (InvestigationParameter parameter : p.getParameters()) {
            System.out.print(parameter.getJipc().toString());
        }
    }
}
