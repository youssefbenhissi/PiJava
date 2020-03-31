/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import projet.models.Eleve;
import projet.models.Parent;
import projet.models.Personnel;
import projet.service.EleveService;
import projet.service.ParentService;
import projet.service.PersonnelService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author user
 */
public class Pidev_java {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
      // EleveService service=new EleveService();
      //  service.supprimerEleve(8459);
      // ParentService service=new ParentService();
        
       // System.out.println(service.selectAllEleve());
       //ParentService serviceP=new ParentService();
       // serviceP.supprimerParents(35);
        //System.out.println(serviceP.selectAllParent());
      //  Eleve e=new Eleve(10, "youssef", "benhissi", "youse", "hhhh", "hhh", 0);
       // service.ajouterEleve(e);
         //Parent p=new Parent(39, "zied", "hlel", 25415458, "123.jpeg");
       // service.ajouterParent(p);
        EleveService servicePe=new EleveService();
       // Eleve e=new Eleve(8467, "mustapha", "feriani", "youse", "hhhh", "hhh", 0);
       Eleve e=new Eleve(8474,"youssef" ,"benhissi","youssef.benhissi", "image", "Sexe", 0);
       e.setParent_id(34);
       servicePe.modifierE(e);
        // ParentService serviceP=new ParentService();
        //Parent p=new Parent(38, "slim", "hamdi", 55600320, "jjj.jpeg");
       // serviceP.modifierP(p);
     /*   EleveService servicePe=new EleveService();
        //Personnel pe=new Personnel(45686, "ali", "ben hamouda",5 , "up", "ok", "yu.jpeg");
List<Eleve> liste=servicePe.selectAllEleve();
for(Eleve e :liste){
    System.out.println("le nom de l'eleve: "+e.getNom());
    System.out.println("l'age de l'eleve: "+e.getAge());
    System.out.println("le nom du parent est : "+e.getNomParent());*/
    SimpleDateFormat formater = null;
    Date aujourdhui = new Date();
    formater = new SimpleDateFormat("dd-MM-yy");
    System.out.println(formater.format(aujourdhui));
    
 // System.out.println(Calendar.getlnstance().getTimelnMillis() );
  //System.out.println(Calendar.getlnstance().getTime().getTime ())


}


//        Personnel pe=new Personnel(8754, "anis", "selmi", 30, "22/06/2006", "jjj", "ffff", "kk.jpeg");
  //      service.ajouterPersonnel(pe);
       // servicePe.supprimerPersonnel(8654);
       //System.out.println(servicePe.selectAllPersonnel());
    }
    
}
