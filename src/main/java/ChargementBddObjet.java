/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Objets.*;
import Utilitaires.GestionSQL;
import Utilitaires.Log;
import Utilitaires.typeProduit;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Romain
 */
public class ChargementBddObjet {

    private ArrayList<PrixProduit> listePrixProduit;
    private ArrayList<Produit> listeProduit;
    private ArrayList<Saisie> listeSaisie;
    private ArrayList<Utilisateur> listeUtilisateur;
    private ArrayList<Parcelles> listeParcelle;
    private Connection connection;

    public ChargementBddObjet() {
        try {
            listePrixProduit = new ArrayList<PrixProduit>();
            listeProduit = new ArrayList<Produit>();
            listeSaisie = new ArrayList<Saisie>();
            listeUtilisateur = new ArrayList<Utilisateur>();
            listeParcelle = new ArrayList<Parcelles>();

            connection = GestionSQL.getConnection().getCon();
            Log.getLog().ecrire("Chargement Utilisateurs...");
            chargerUtilisateur();
            Log.getLog().ecrire("Chargement " + listeUtilisateur.size() + " Utilisateurs:OK");

            Log.getLog().ecrire("Chargement Produits...");
            chargerProduit();
            Log.getLog().ecrire("Chargement " + listeProduit.size() + " Produits:OK");

            Log.getLog().ecrire("Chargement Saisies...");
            chargerSaisie();
            Log.getLog().ecrire("Chargement " + listeSaisie.size() + " Saisies:OK");

            Log.getLog().ecrire("Chargement Prix Produit...");
            chargerPrixProduit();
            Log.getLog().ecrire("Chargement " + listePrixProduit.size() + " Prix Produit:OK");

            Log.getLog().ecrire("Chargement Parcelles...");
            chargerParcelle();
            Log.getLog().ecrire("Chargement " + listeParcelle.size() + " Parcelles:OK");



        } catch (ClassNotFoundException ex) {
            Log.getLog().ecrireErreur(ChargementBddObjet.class.getName() + " " + ex + " " + ex.getLocalizedMessage());
        } catch (InstantiationException ex) {
            Log.getLog().ecrireErreur(ChargementBddObjet.class.getName() + " " + ex + " " + ex.getLocalizedMessage());
        } catch (IllegalAccessException ex) {
            Log.getLog().ecrireErreur(ChargementBddObjet.class.getName() + " " + ex + " " + ex.getLocalizedMessage());
        } catch (SQLException ex) {
            Log.getLog().ecrireErreur(ChargementBddObjet.class.getName() + " " + ex + " " + ex.getLocalizedMessage());
        } catch (Exception ex) {
            Log.getLog().ecrireErreur(ChargementBddObjet.class.getName() + " " + ex + " " + ex.getLocalizedMessage());
        }


    }

    private void chargerUtilisateur() {
        try {
            Utilisateur utilisateur;
            Statement select = null;
            select = (Statement) connection.createStatement();
            String requete = "select * from utilisateurs";
            ResultSet result = null;
            result = select.executeQuery(requete);

            while (result.next()) {
                utilisateur = new Utilisateur(result.getInt(4), result.getString(1), result.getInt(5));
                listeUtilisateur.add(utilisateur);
            }
        } catch (SQLException ex) {
            Log.getLog().ecrire("ERREUR:" + ChargementBddObjet.class.getName() + " " + ex + " " + ex.getLocalizedMessage());

        }

    }

    private void chargerProduit() {
        try {
            typeProduit t[] = typeProduit.values();
            Produit produit;
            for (int i = 0; i < typeProduit.values().length; i++) {
                Statement select = null;
                select = (Statement) connection.createStatement();
                String requete = "select * from " + t[i];
                ResultSet result = null;
                result = select.executeQuery(requete);
                while (result.next()) {
                    produit = new Produit(result.getInt(1), result.getString(2), t[i].toString());
                    listeProduit.add(produit);
                }
            }
        } catch (SQLException ex) {
            Log.getLog().ecrireErreur("ChargementBddObjet.chargerProduit SQLException");
        }

    }

    private void chargerSaisie() {
        try {
            Saisie saisie;
            Statement select = null;
            select = (Statement) connection.createStatement();
            String requete = "select * from donnees";
            ResultSet result = null;
            result = select.executeQuery(requete);

            Utilisateur user = null;

            while (result.next()) {
                user = getUser(result.getString(7));
                saisie = new Saisie(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getDate(6), user);
                listeSaisie.add(saisie);

            }
        } catch (SQLException ex) {
            Log.getLog().ecrire("ERREUR:" + ChargementBddObjet.class.getName() + " " + ex + " " + ex.getLocalizedMessage());

        }

    }

    private void chargerPrixProduit() {
        try {
            PrixProduit prixProduit;
            Statement select = null;
            select = (Statement) connection.createStatement();
            String requete = "select * from prix_produit";
            ResultSet result = null;
            result = select.executeQuery(requete);

            Utilisateur user = null;
            Produit produit = null;

            while (result.next()) {
                /*26-10-2010
                 *Ajout de cette restriction suite aux bug lors de l'ajout des Semences
                 * qui ne sont pas présente dans Type Produit
                 */
                if (result.getString(7).equals("Semences")){}
                else {
                    user = getUser(result.getInt(3));
                    produit = getProduit(result.getInt(2), result.getString(7));
                    prixProduit = new PrixProduit(user, produit, result.getDouble(5), result.getDouble(4));
                    listePrixProduit.add(prixProduit);
                }
            }
        } catch (SQLException ex) {
            Log.getLog().ecrireErreur("ChargementBddObjet.chargerPrixProduit SQLException");

        }

    }

    private void chargerParcelle() {
        try {
            Parcelles parcelle;
            Statement select = null;
            select = (Statement) connection.createStatement();
            String requete = "select * from nomparcelle";
            ResultSet result = null;
            result = select.executeQuery(requete);
            Utilisateur u;

            while (result.next()) {
                u = getUser(result.getInt(3));
                parcelle = new Parcelles(result.getString(2), result.getString(7), result.getInt(5), result.getDouble(6), u, result.getString(20));
                listeParcelle.add(parcelle);
            }
        } catch (SQLException ex) {
            Log.getLog().ecrireErreur("ChargementBddObjet.chargerParcelles SQLException");

        }
    }

    /*
     * Méthode permettant de trouver l'objet User
     * avec son id
     */
    private Utilisateur getUser(String nomUser) {
        for (Utilisateur u : listeUtilisateur) {
            if (u.getLogin().toLowerCase().equals(nomUser.toLowerCase())) {
                return u;
            }
        }
        return null;
    }

    private Utilisateur getUser(int idUser) {
        for (Utilisateur u : listeUtilisateur) {
            if (u.getIdUser() == idUser) {
                return u;
            }
        }
        return null;
    }

    public Produit getProduit(int idProduit, String typeProduit) {
        for (Produit p : listeProduit) {
            if (p.getIdProduit() == idProduit && p.getType().toUpperCase().equals(typeProduit.toUpperCase())) {
                return p;
            }
        }
        return null;
    }

    public Produit getProduit(String nomProduit, String typeProduit) {
        for (Produit p : listeProduit) {
            if (p.getNom().toUpperCase().equals(nomProduit.toUpperCase()) && p.getType().toUpperCase().equals(typeProduit.toUpperCase())) {
                return p;
            }
        }
        return null;
    }

    public Saisie getSaisie(int id) {
        for (Saisie s : listeSaisie) {
            if (s.getIdSaisie() == id) {
                return s;
            }
        }
        return null;
    }

    public Parcelles getParcelle(String parcelle) {
        for (Parcelles p : listeParcelle) {
            if (p.getNomParcelle().equals(parcelle)) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<PrixProduit> getListePrixProduit() {
        return listePrixProduit;
    }

    public void setListePrixProduit(ArrayList<PrixProduit> listePrixProduit) {
        this.listePrixProduit = listePrixProduit;
    }

    public ArrayList<Produit> getListeProduit() {
        return listeProduit;
    }

    public void setListeProduit(ArrayList<Produit> listeProduit) {
        this.listeProduit = listeProduit;
    }

    public ArrayList<Saisie> getListeSaisie() {
        return listeSaisie;
    }

    public void setListeSaisie(ArrayList<Saisie> listeSaisie) {
        this.listeSaisie = listeSaisie;
    }

    public ArrayList<Utilisateur> getListeUtilisateur() {
        return listeUtilisateur;
    }

    public void setListeUtilisateur(ArrayList<Utilisateur> listeUtilisateur) {
        this.listeUtilisateur = listeUtilisateur;
    }

    public ArrayList<Parcelles> getListeParcelle() {
        return listeParcelle;
    }

    public void setListeParcelle(ArrayList<Parcelles> listeParcelle) {
        this.listeParcelle = listeParcelle;
    }
}
