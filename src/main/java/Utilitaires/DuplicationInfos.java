/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilitaires;

import Objets.Utilisateur;
import com.mysql.jdbc.Statement;
//import mobileagri_marge.Utilitaires.GestionSQL;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author romain_lou
 */
public class DuplicationInfos {

    private ArrayList<Utilisateur> listeUser;



    public DuplicationInfos(String oldLogin, String newLogin){
        Statement selectInfos;
        try {

            selectInfos = (Statement) GestionSQL.getConnection().getCon().createStatement();
            
            ResultSet rsNew = selectInfos.executeQuery("SELECT `login` FROM `utilisateurs` WHERE `id_user` = "+newLogin);
            rsNew.next();
            System.out.println("AVANT:");
            String loginNew = rsNew.getString(1);
            System.out.println(loginNew);

            ResultSet rsOld = selectInfos.executeQuery("SELECT `login` FROM `utilisateurs` WHERE `id_user` = "+oldLogin);
            rsOld.next();
            String loginOld = rsOld.getString(1);

            ResultSet resultInfos = selectInfos.executeQuery("select * from client where login_user= '"+loginOld+"'");
            System.out.println("A VERIFIER !!!!!!!!!!!!! select * from client where login_user= '"+loginOld+"'");
            resultInfos.next();
            String entreprise = resultInfos.getString("nom_entreprise");
            String adresse  = resultInfos.getString("adresse");
            String code_postal = resultInfos.getString("code_postal");
            String ville = resultInfos.getString("ville");
            String email = resultInfos.getString("email");
            String nom = resultInfos.getString("nom");
            String prenom = resultInfos.getString("prenom");

            String linkSQL = "insert into client(login_user, nom_entreprise, adresse, code_postal, ville, email, nom, prenom) values('"+newLogin+"', '"+entreprise+"', '"+adresse+"', '"+code_postal+"', '"+ville+"', '"+email+"', '"+nom+"', '"+prenom+"')";


            System.out.println(linkSQL);

            selectInfos.executeUpdate(linkSQL);



        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DuplicationInfos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DuplicationInfos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DuplicationInfos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DuplicationInfos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Utilisateur> getListeUser() {
        return listeUser;
    }




}
