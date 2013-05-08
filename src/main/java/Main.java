/*
 * Application développée du à la lenteur des calculs instantannés
 * de PHP qui étaitent fait directement sur http://mobileagri.homeip.net
 * Application qui doit avoir le repertoire C:\LogsJava avec un fichier
 * prop.properties
 */

import Utilitaires.GestionSQL;
import Utilitaires.Log;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Romain
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Log.getLog().afficheEntete();
        Log.getLog().ecrire("############### Lancement du programme ################");
        ChargementBddObjet chargement = new ChargementBddObjet();
        Calculs calculs = new Calculs(chargement);

        /*
         * On duplique les parcelle pour le nouveau client
         
        System.out.println("Avant duplication");
        System.out.println("Old Login: ");
            String oldLogin = sc.next();

            System.out.println("New Login: ");
            String newLogin = sc.next();

        DuplicationParcelles d = new DuplicationParcelles(oldLogin, newLogin);
        //DuplicationInfos dI = new DuplicationInfos(oldLogin, newLogin);
        d.remplirParcelle();
        System.out.println("Après duplication");*/

        try {
            GestionSQL.getConnection().fermerConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        Log.getLog().ecrire("############### Fin du programme ################");
        
    }

}
