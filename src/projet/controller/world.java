/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import projet.models.Utilisateur;

/**
 *
 * @author youssef
 */
public class world {

    public static Utilisateur recupererUtilisateurConnecte;

    public void setUser(Utilisateur user) {
        System.out.println("ppppppppppppppp"+user.getEmail());
        recupererUtilisateurConnecte = user;
    }
}
