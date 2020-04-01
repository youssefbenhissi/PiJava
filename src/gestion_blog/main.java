/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_blog;

import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author geek alaa
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Tags tag = new Tags(1 ,"TAG-2222222-TEST");
       GestionTags gestag = new GestionTags();
       
     /**if(gestag.AjouterTag(tag2)){
           System.out.println("Une nouvelle tag a été insérée avec succès ! ");
       }else{
           System.out.println("Une erreur est survenue ! ");
       }    **/
       //gestag.AfficherTags();
       
      /** if(gestag.ModifierTag(tag1)){
           System.out.println("Une tag existante a été mise à jour avec succès!");
       }else{
           System.out.println("Une erreur est survenue ! "); 
       }**/
           
       
       /** if(gestag.SupprimerTag(tag2)){
           System.out.println("Une tag a été supprimée avec succès!");
       }else{
           System.out.println("Une erreur est survenue ! "); 
       }
       gestag.AfficherTags(); **/
       
     
        
        Categories cat = new Categories(10, "test99", "treeeee");
        GestionCategories gstcat = new GestionCategories();
        
      //  gstcat.AfficherCat();
        
         /** if(gstcat.AjouterCat(cat)){
            System.out.println("Une nouvelle catégorie a été insérée avec succès ! ");
        }else{
            System.out.println("Une erreur est survenue ! ");
        } **/
        
        /** if(gstcat.SupprimerCat(cat)){
             System.out.println("Une catégorie a été supprimée avec succès!");
        }else{
            System.out.println("Une erreur est survenue ! ");
        } **/
        
     /**  if(gstcat.ModifierCat(cat)){
           System.out.println("Une catégorie existante a été mise à jour avec succès!");
       }else{
           System.out.println("Une erreur est survenue ! "); 
       } **/
        
        
               // gstcat.AfficherCat();
List<Tags> listags = new ArrayList<Tags>();
listags.add(tag);
        Articles arti = new Articles("tesss", "teeee", "tesss", "teeeeee", "2020-04-02", 65 , 0, 0, 3, listags);
       GestionArticles gestarti = new GestionArticles();
       
       
       /** if(gestarti.AjouterArticle(arti)){
           System.out.println("POST a été insérée avec succès ! ");
       }else{
           System.out.println("Une erreur est survenue ! ");
       } **/
       
       /** if(gestarti.ModifierArticle(arti)){
           System.out.println("POST a été mise à jour avec succès!");
       }else{
           System.out.println("Une erreur est survenue ! "); 
       } **/
     
       
       gestarti.AfficherArticle();
       
      /** if(gestarti.SupprimerArticle(arti)){
             System.out.println("POST a été supprimée avec succès!");
        }else{
            System.out.println("Une erreur est survenue ! ");
        }**/
       
    }
    
}
