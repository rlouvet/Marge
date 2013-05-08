import Objets.*;
import Utilitaires.*;
import com.mysql.jdbc.PreparedStatement;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Romain
 */
public class Calculs {

    ChargementBddObjet listeObjet;
    ArrayList<Utilisateur> listeUtilisateurMarge;
    ArrayList<Saisie> listeSaisieMarge;
    ArrayList<String> listeTypeProduit;
    ArrayList<Parcelles> listeParcelleBle;
    ArrayList<Parcelles> listeParcelleOrge;
    ArrayList<Parcelles> listeParcellePois;
    ArrayList<Parcelles> listeParcelleColza;
    ArrayList<Parcelles> listeParcelleLin;
    ArrayList<Parcelles> listeParcelleFeveole;
    ArrayList<Parcelles> listeParcelleTournesol;
    ArrayList<Parcelles> listeParcelleEscourgeon;
    int cptSaisie = 1;
    double sommePrixSurfaceAzote = 0, sommeSurfaceAzote = 0, moyenneAzote = 0.0;
    double sommePrixSurfaceEngrais = 0, sommeSurfaceEngrais = 0, moyenneEngrais = 0.0;
    double sommePrixSurfaceFongicide = 0, sommeSurfaceFongicide = 0, moyenneFongicide = 0.0;
    double sommePrixSurfaceHerbicide = 0, sommeSurfaceHerbicide = 0, moyenneHerbicide = 0.0;
    double sommePrixSurfaceInsecticide = 0, sommeSurfaceInsecticide = 0, moyenneInsecticide = 0.0;
    double sommePrixSurfaceOligos = 0, sommeSurfaceOligos = 0, moyenneOligos = 0.0;
    double sommePrixSurfaceRegulateur = 0, sommeSurfaceRegulateur = 0, moyenneRegulateur = 0.0;
    double sommePrixSurfaceSouffre = 0, sommeSurfaceSouffre = 0, moyenneSouffre = 0.0;
    
    public Calculs(ChargementBddObjet ListeObjet) {
        this.listeObjet = ListeObjet;
        listeParcelleBle = new ArrayList<Parcelles>();
        listeParcelleColza = new ArrayList<Parcelles>();
        listeParcelleLin = new ArrayList<Parcelles>();
        listeParcelleOrge = new ArrayList<Parcelles>();
        listeParcelleEscourgeon = new ArrayList<Parcelles>();
        listeParcellePois = new ArrayList<Parcelles>();
        listeParcelleFeveole = new ArrayList<Parcelles>();
        listeParcelleTournesol = new ArrayList<Parcelles>();

        trierUtilisateur();
        trierSaisie();
        trierParcelles();

        for (Utilisateur u : listeUtilisateurMarge) {
            Log.getLog().ecrire("Utilisateur : " + u.getLogin());
            effectuerMoyenne(u);
            effectuerMoyenneGlobal(u);
        }
    }


    private void affecteSommeListeCulture(ArrayList<Parcelles> liste, Utilisateur u) {
        try{
        for (Parcelles p : liste) {
            if (p.getUtilisateur().equals(u)) {
                traitementProduits(p, u);
            }
        }
        for (Parcelles pa : liste) {
            if (pa.getUtilisateur().equals(u)) {
                if (pa.getPrixAzote() != 0.0 && pa.getUtilisateur().equals(u)) {
                    sommePrixSurfaceAzote += pa.getPrixAzote() * pa.getSuperficie();
                    sommeSurfaceAzote += pa.getSuperficie();
                } else if (pa.getPrixEngrais() != 0.0 && pa.getUtilisateur().equals(u)) {
                    sommePrixSurfaceEngrais += pa.getPrixEngrais() * pa.getSuperficie();
                    sommeSurfaceEngrais += pa.getSuperficie();
                } else if (pa.getPrixFongicide() != 0.0 && pa.getUtilisateur().equals(u)) {
                    sommePrixSurfaceFongicide += pa.getPrixFongicide() * pa.getSuperficie();
                    sommeSurfaceFongicide += pa.getSuperficie();
                } else if (pa.getPrixHerbicide() != 0.0 && pa.getUtilisateur().equals(u)) {
                    sommePrixSurfaceHerbicide += pa.getPrixHerbicide() * pa.getSuperficie();
                    sommeSurfaceHerbicide += pa.getSuperficie();
                } else if (pa.getPrixInsecticide() != 0.0 && pa.getUtilisateur().equals(u)) {
                    sommePrixSurfaceInsecticide += pa.getPrixInsecticide() * pa.getSuperficie();
                    sommeSurfaceInsecticide += pa.getSuperficie();
                } else if (pa.getPrixOligos() != 0.0 && pa.getUtilisateur().equals(u)) {
                    sommePrixSurfaceOligos += pa.getPrixOligos() * pa.getSuperficie();
                    sommeSurfaceOligos += pa.getSuperficie();
                } else if (pa.getPrixRegulateur() != 0.0 && pa.getUtilisateur().equals(u)) {
                    sommePrixSurfaceRegulateur += pa.getPrixRegulateur() * pa.getSuperficie();
                    sommeSurfaceRegulateur += pa.getSuperficie();
                } else if (pa.getPrixSouffre() != 0.0 && pa.getUtilisateur().equals(u)) {
                    sommePrixSurfaceSouffre += pa.getPrixSouffre() * pa.getSuperficie();
                    sommeSurfaceSouffre += pa.getSuperficie();
                }
            }
        }
        if (sommeSurfaceAzote != 0.0) {
            moyenneAzote = sommePrixSurfaceAzote / sommeSurfaceAzote;
        } else {
            moyenneAzote = 0.0;
        }
        u.setPrixAzote(moyenneAzote);

        if (sommeSurfaceEngrais != 0.0) {
            moyenneEngrais = sommePrixSurfaceEngrais / sommeSurfaceEngrais;
        } else {
            moyenneEngrais = 0.0;
        }
        u.setPrixEngrais(moyenneEngrais);

        if (sommeSurfaceFongicide != 0.0) {
            moyenneFongicide = sommePrixSurfaceFongicide / sommeSurfaceFongicide;
        } else {
            moyenneFongicide = 0.0;
        }
        u.setPrixFongicide(moyenneFongicide);

        if (sommeSurfaceHerbicide != 0.0) {
            moyenneHerbicide = sommePrixSurfaceHerbicide / sommeSurfaceHerbicide;
        } else {
            moyenneHerbicide = 0.0;
        }
        u.setPrixHerbicide(moyenneHerbicide);

        if (sommeSurfaceInsecticide != 0.0) {
            moyenneInsecticide = sommePrixSurfaceInsecticide / sommeSurfaceInsecticide;
        } else {
            moyenneInsecticide = 0.0;
        }
        u.setPrixInsecticide(moyenneInsecticide);

        if (sommeSurfaceOligos != 0.0) {
            moyenneOligos = sommePrixSurfaceOligos / sommeSurfaceOligos;
        } else {
            moyenneOligos = 0.0;
        }
        u.setPrixOligos(moyenneOligos);

        if (sommeSurfaceRegulateur != 0.0) {
            moyenneRegulateur = sommePrixSurfaceRegulateur / sommeSurfaceRegulateur;
        } else {
            moyenneRegulateur = 0.0;
        }
        u.setPrixRegulateur(moyenneRegulateur);

        if (sommeSurfaceSouffre != 0.0) {
            moyenneSouffre = sommePrixSurfaceSouffre / sommeSurfaceSouffre;
        } else {
            moyenneSouffre = 0.0;
        }
        u.setPrixSouffre(moyenneSouffre);

        }catch(Exception e){
            System.out.println("Exception : "+e);
        }

    }

    /*
     * Remet à 0 les variables pour les changements de culture
     */
    private void miseAZeroMoyennes() {
        sommePrixSurfaceAzote = 0;
        sommeSurfaceAzote = 0;
        moyenneAzote = 0.0;
        sommePrixSurfaceEngrais = 0;
        sommeSurfaceEngrais = 0;
        moyenneEngrais = 0.0;
        sommePrixSurfaceFongicide = 0;
        sommeSurfaceFongicide = 0;
        moyenneFongicide = 0.0;
        sommePrixSurfaceHerbicide = 0;
        sommeSurfaceHerbicide = 0;
        moyenneHerbicide = 0.0;
        sommePrixSurfaceInsecticide = 0;
        sommeSurfaceInsecticide = 0;
        moyenneInsecticide = 0.0;
        sommePrixSurfaceOligos = 0;
        sommeSurfaceOligos = 0;
        moyenneOligos = 0.0;
        sommePrixSurfaceRegulateur = 0;
        sommeSurfaceRegulateur = 0;
        moyenneRegulateur = 0.0;
        sommePrixSurfaceSouffre = 0;
        sommeSurfaceSouffre = 0;
        moyenneSouffre = 0.0;
    }

    private void majBddMoyenneCulture(String culture, Utilisateur u) {
        try {
            String lienSQL = "UPDATE moyenneprixculture SET azote" + culture + " = '" + u.getPrixAzote() + "',engrais" + culture + " = '" + u.getPrixEngrais() + "',fongicide" + culture + " = '" + u.getPrixFongicide() + "',herbicide" + culture + " = '" + u.getPrixHerbicide() + "',insecticide" + culture + " = '" + u.getPrixInsecticide() + "',oligos" + culture + " = '" + u.getPrixOligos() + "',regulateur" + culture + " = '" + u.getPrixRegulateur() + "',souffre" + culture + " = '" + u.getPrixSouffre() + "'  where id_client = " + u.getIdUser() + "";
            PreparedStatement statement = (PreparedStatement) GestionSQL.getConnection().getCon().prepareStatement(lienSQL);
            statement.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Log.getLog().ecrireErreur("'Calculs.majBddMoyenneCulture' ClassNotFound");
        } catch (InstantiationException ex) {
            Log.getLog().ecrireErreur("'Calculs.majBddMoyenneCulture' InstantiationException");
        } catch (IllegalAccessException ex) {
            Log.getLog().ecrireErreur("'Calculs.majBddMoyenneCulture' IllegalAccessException");
        } catch (SQLException ex) {
            Log.getLog().ecrireErreur("'Calculs.majBddMoyenneCulture' SQLException");
        }
    }

    private void effectuerMoyenneGlobal(Utilisateur u) {
        Log.getLog().ecrire("Moyenne BLE");
        affecteSommeListeCulture(listeParcelleBle, u);
        Log.getLog().ecrire("\tAzote :" + u.getPrixAzote());
        Log.getLog().ecrire("\tEngrais :" + u.getPrixEngrais());
        Log.getLog().ecrire("\tFongicide :" + u.getPrixFongicide());
        Log.getLog().ecrire("\tHerbicide :" + u.getPrixHerbicide());
        Log.getLog().ecrire("\tInsecticide :" + u.getPrixInsecticide());
        Log.getLog().ecrire("\tOligos :" + u.getPrixOligos());
        Log.getLog().ecrire("\tRegulateur :" + u.getPrixRegulateur());
        Log.getLog().ecrire("\tSouffre :" + u.getPrixSouffre());
        majBddMoyenneCulture("Ble", u);
        miseAZeroMoyennes();
        Log.getLog().ecrire("Moyenne COLZA");
        affecteSommeListeCulture(listeParcelleColza, u);
        Log.getLog().ecrire("\tAzote :" + u.getPrixAzote());
        Log.getLog().ecrire("\tEngrais :" + u.getPrixEngrais());
        Log.getLog().ecrire("\tFongicide :" + u.getPrixFongicide());
        Log.getLog().ecrire("\tHerbicide :" + u.getPrixHerbicide());
        Log.getLog().ecrire("\tInsecticide :" + u.getPrixInsecticide());
        Log.getLog().ecrire("\tOligos :" + u.getPrixOligos());
        Log.getLog().ecrire("\tRegulateur :" + u.getPrixRegulateur());
        Log.getLog().ecrire("\tSouffre :" + u.getPrixSouffre());
        majBddMoyenneCulture("Colza", u);
        miseAZeroMoyennes();
        Log.getLog().ecrire("Moyenne ORGE");
        affecteSommeListeCulture(listeParcelleOrge, u);
        Log.getLog().ecrire("\tAzote :" + u.getPrixAzote());
        Log.getLog().ecrire("\tEngrais :" + u.getPrixEngrais());
        Log.getLog().ecrire("\tFongicide :" + u.getPrixFongicide());
        Log.getLog().ecrire("\tHerbicide :" + u.getPrixHerbicide());
        Log.getLog().ecrire("\tInsecticide :" + u.getPrixInsecticide());
        Log.getLog().ecrire("\tOligos :" + u.getPrixOligos());
        Log.getLog().ecrire("\tRegulateur :" + u.getPrixRegulateur());
        Log.getLog().ecrire("\tSouffre :" + u.getPrixSouffre());
        majBddMoyenneCulture("Orge", u);
        miseAZeroMoyennes();
        Log.getLog().ecrire("Moyenne ESCOURGEON");
        affecteSommeListeCulture(listeParcelleEscourgeon, u);
        Log.getLog().ecrire("\tAzote :" + u.getPrixAzote());
        Log.getLog().ecrire("\tEngrais :" + u.getPrixEngrais());
        Log.getLog().ecrire("\tFongicide :" + u.getPrixFongicide());
        Log.getLog().ecrire("\tHerbicide :" + u.getPrixHerbicide());
        Log.getLog().ecrire("\tInsecticide :" + u.getPrixInsecticide());
        Log.getLog().ecrire("\tOligos :" + u.getPrixOligos());
        Log.getLog().ecrire("\tRegulateur :" + u.getPrixRegulateur());
        Log.getLog().ecrire("\tSouffre :" + u.getPrixSouffre());
        majBddMoyenneCulture("Orge", u);
        miseAZeroMoyennes();
        Log.getLog().ecrire("Moyenne POIS");
        affecteSommeListeCulture(listeParcellePois, u);
        Log.getLog().ecrire("\tAzote :" + u.getPrixAzote());
        Log.getLog().ecrire("\tEngrais :" + u.getPrixEngrais());
        Log.getLog().ecrire("\tFongicide :" + u.getPrixFongicide());
        Log.getLog().ecrire("\tHerbicide :" + u.getPrixHerbicide());
        Log.getLog().ecrire("\tInsecticide :" + u.getPrixInsecticide());
        Log.getLog().ecrire("\tOligos :" + u.getPrixOligos());
        Log.getLog().ecrire("\tRegulateur :" + u.getPrixRegulateur());
        Log.getLog().ecrire("\tSouffre :" + u.getPrixSouffre());
        majBddMoyenneCulture("Pois", u);
        miseAZeroMoyennes();
        Log.getLog().ecrire("Moyenne LIN");
        affecteSommeListeCulture(listeParcelleLin, u);
        Log.getLog().ecrire("\tAzote :" + u.getPrixAzote());
        Log.getLog().ecrire("\tEngrais :" + u.getPrixEngrais());
        Log.getLog().ecrire("\tFongicide :" + u.getPrixFongicide());
        Log.getLog().ecrire("\tHerbicide :" + u.getPrixHerbicide());
        Log.getLog().ecrire("\tInsecticide :" + u.getPrixInsecticide());
        Log.getLog().ecrire("\tOligos :" + u.getPrixOligos());
        Log.getLog().ecrire("\tRegulateur :" + u.getPrixRegulateur());
        Log.getLog().ecrire("\tSouffre :" + u.getPrixSouffre());
        majBddMoyenneCulture("Lin", u);
        miseAZeroMoyennes();
        Log.getLog().ecrire("Moyenne Feverole");
        affecteSommeListeCulture(listeParcelleFeveole, u);
        Log.getLog().ecrire("\tAzote :" + u.getPrixAzote());
        Log.getLog().ecrire("\tEngrais :" + u.getPrixEngrais());
        Log.getLog().ecrire("\tFongicide :" + u.getPrixFongicide());
        Log.getLog().ecrire("\tHerbicide :" + u.getPrixHerbicide());
        Log.getLog().ecrire("\tInsecticide :" + u.getPrixInsecticide());
        Log.getLog().ecrire("\tOligos :" + u.getPrixOligos());
        Log.getLog().ecrire("\tRegulateur :" + u.getPrixRegulateur());
        Log.getLog().ecrire("\tSouffre :" + u.getPrixSouffre());
        majBddMoyenneCulture("Feverole", u);
        Log.getLog().ecrire("Moyenne Tournesol");
        affecteSommeListeCulture(listeParcelleTournesol, u);
        Log.getLog().ecrire("\tAzote :" + u.getPrixAzote());
        Log.getLog().ecrire("\tEngrais :" + u.getPrixEngrais());
        Log.getLog().ecrire("\tFongicide :" + u.getPrixFongicide());
        Log.getLog().ecrire("\tHerbicide :" + u.getPrixHerbicide());
        Log.getLog().ecrire("\tInsecticide :" + u.getPrixInsecticide());
        Log.getLog().ecrire("\tOligos :" + u.getPrixOligos());
        Log.getLog().ecrire("\tRegulateur :" + u.getPrixRegulateur());
        Log.getLog().ecrire("\tSouffre :" + u.getPrixSouffre());
        majBddMoyenneCulture("Feverole", u);

    }

    private void trierUtilisateur() {
        this.listeUtilisateurMarge = new ArrayList<Utilisateur>();
        for (Utilisateur u : this.listeObjet.getListeUtilisateur()) {
            if (u.getOptionPrix() == 1) {
                listeUtilisateurMarge.add(u);
            }
        }
        Log.getLog().ecrire("trie des Utilisateurs " + listeUtilisateurMarge.size());
    }

    private void trierSaisie() {
        this.listeSaisieMarge = new ArrayList<Saisie>();
        for (Saisie s : this.listeObjet.getListeSaisie()) {
            for (Utilisateur u : listeUtilisateurMarge) {
                if (u.equals(s.getUser())) {
                    listeSaisieMarge.add(s);
                }
            }
        }
        Log.getLog().ecrire("trie des Saisies " + listeSaisieMarge.size());
    }

    private void effectuerMoyenne(Utilisateur u) {
        ArrayList<PrixProduit> listeProduits = new ArrayList<PrixProduit>();
        double sommeQt, sommeQtPrix, moyenne;
        sommeQt = 0.0;
        sommeQtPrix = 0.0;
        moyenne = 0.0;

        for (Saisie s : listeSaisieMarge) {
            if (s.getUser().equals(u)) {
                listeProduits = getProduits(listeObjet.getProduit(s.getProduit(), s.getTypeProduit()), u);
                for (PrixProduit prixProduit : listeProduits) {
                    sommeQtPrix += prixProduit.getPrixQt();
                    sommeQt += prixProduit.getQuantite();
                }

                if (sommeQt == 0.0) {
                    moyenne = 0.0;
                    Log.getLog().ecrireErreur("Division par 0 pour ID Saisie:" + s.getIdSaisie());
                } else {
                    moyenne = sommeQtPrix / sommeQt;
                }
                Double quantiteSaisie = StringExtractor.getDouble(s.getQuantite());

                //Modif que s'il y a besoin
                if (s.getPrix() != moyenne) {
                    if (s.getTypeProduit().toLowerCase().equals("azote")) {
                        s.setPrix(moyenne * quantiteSaisie * StringExtractor.getDouble(Log.getLog().getPropertie("mult_azote")));
                    } else {
                        s.setPrix(moyenne * quantiteSaisie);
                    }
//A REMETTRE
                   MajBddMoyenne(s);
                }
                sommeQt = 0.0;
                sommeQtPrix = 0.0;
            }
        }
    }

    public ArrayList<PrixProduit> getProduits(Produit p, Utilisateur u) {
        ArrayList<PrixProduit> listeProduits = new ArrayList<PrixProduit>();
        for (PrixProduit prixProduit : listeObjet.getListePrixProduit()) {
            if (prixProduit.getUser().equals(u) && prixProduit.getProduit().equals(p)) {
                listeProduits.add(prixProduit);
            }
        }
        return listeProduits;
    }

    private void MajBddMoyenne(Saisie s) {
        try {
            String lienSQL = "";
            PreparedStatement statement = (PreparedStatement) GestionSQL.getConnection().getCon().prepareStatement("UPDATE donnees SET prix = ? where id_donnees = ?");
            statement.setDouble(1, s.getPrix());
            statement.setInt(2, s.getIdSaisie());
            statement.executeUpdate();

            Log.getLog().ecrire("MAJ BDD : " + cptSaisie + "/" + listeSaisieMarge.size() + "\tidSaisie:" + s.getIdSaisie());
            cptSaisie++;
        } catch (ClassNotFoundException ex) {
            Log.getLog().ecrireErreur("'Calculs.MAJBddMoyenne' ClassNotFound");
        } catch (InstantiationException ex) {
            Log.getLog().ecrireErreur("'Calculs.MAJBddMoyenne' InstantiationException");
        } catch (IllegalAccessException ex) {
            Log.getLog().ecrireErreur("'Calculs.MAJBddMoyenne' IllegalAccessException");
        } catch (SQLException ex) {
            Log.getLog().ecrireErreur("'Calculs.MAJBddMoyenne' SQLException");
        }
    }

    private void traitementProduits(Parcelles p, Utilisateur u) {


System.out.println("11");

        for (Saisie s : listeSaisieMarge) {


System.out.println("2");
            if (s.getUser().equals(u) && s.getParcelle().equals(p.getNomParcelle())) {
System.out.println(s.getUser());
System.out.println(s.getParcelle());
System.out.println(s.getTypeProduit());
System.out.println(s.getPrix());
System.out.println(listeObjet.getParcelle(s.getParcelle()).getSuperficie() * s.getPrix());

                if (s.getTypeProduit().toUpperCase().equals(typeProduit.azote.toString().toUpperCase())) {
                    sommePrixSurfaceAzote += listeObjet.getParcelle(s.getParcelle()).getSuperficie() * s.getPrix();
                    sommeSurfaceAzote += listeObjet.getParcelle(s.getParcelle()).getSuperficie();
                } else if (s.getTypeProduit().toUpperCase().equals(typeProduit.engrais.toString().toUpperCase())) {
                    sommePrixSurfaceEngrais += listeObjet.getParcelle(s.getParcelle()).getSuperficie() * s.getPrix();
                    sommeSurfaceEngrais += listeObjet.getParcelle(s.getParcelle()).getSuperficie();
                } else if (s.getTypeProduit().toUpperCase().equals(typeProduit.fongicide.toString().toUpperCase())) {
                    sommePrixSurfaceFongicide += listeObjet.getParcelle(s.getParcelle()).getSuperficie() * s.getPrix();
                    sommeSurfaceFongicide += listeObjet.getParcelle(s.getParcelle()).getSuperficie();
                } else if (s.getTypeProduit().toUpperCase().equals(typeProduit.herbicide.toString().toUpperCase())) {
                    sommePrixSurfaceHerbicide += listeObjet.getParcelle(s.getParcelle()).getSuperficie() * s.getPrix();
                    sommeSurfaceHerbicide += listeObjet.getParcelle(s.getParcelle()).getSuperficie();
                } else if (s.getTypeProduit().toUpperCase().equals(typeProduit.insecticide.toString().toUpperCase())) {
                    sommePrixSurfaceInsecticide += listeObjet.getParcelle(s.getParcelle()).getSuperficie() * s.getPrix();
                    sommeSurfaceInsecticide += listeObjet.getParcelle(s.getParcelle()).getSuperficie();
                } else if (s.getTypeProduit().toUpperCase().equals(typeProduit.oligos.toString().toUpperCase())) {
                    sommePrixSurfaceOligos += listeObjet.getParcelle(s.getParcelle()).getSuperficie() * s.getPrix();
                    sommeSurfaceOligos += listeObjet.getParcelle(s.getParcelle()).getSuperficie();
                } else if (s.getTypeProduit().toUpperCase().equals(typeProduit.regulateur.toString().toUpperCase())) {
                    sommePrixSurfaceRegulateur += listeObjet.getParcelle(s.getParcelle()).getSuperficie() * s.getPrix();
                    sommeSurfaceRegulateur += listeObjet.getParcelle(s.getParcelle()).getSuperficie();
                } else if (s.getTypeProduit().toUpperCase().equals(typeProduit.souffre.toString().toUpperCase())) {
                    sommePrixSurfaceSouffre += listeObjet.getParcelle(s.getParcelle()).getSuperficie() * s.getPrix();
                    sommeSurfaceSouffre += listeObjet.getParcelle(s.getParcelle()).getSuperficie();
                }
            }
        }

        if (sommeSurfaceAzote != 0.0) {
            moyenneAzote = sommePrixSurfaceAzote / sommeSurfaceAzote;
        }
        if (sommeSurfaceEngrais != 0.0) {
            moyenneEngrais = sommePrixSurfaceEngrais / sommeSurfaceEngrais;
        }
        if (sommeSurfaceFongicide != 0.0) {
            moyenneFongicide = sommePrixSurfaceFongicide / sommeSurfaceFongicide;
        }
        if (sommeSurfaceHerbicide != 0.0) {
            moyenneHerbicide = sommePrixSurfaceHerbicide / sommeSurfaceHerbicide;
        }
        if (sommeSurfaceInsecticide != 0.0) {
            moyenneInsecticide = sommePrixSurfaceInsecticide / sommeSurfaceInsecticide;
        }
        if (sommeSurfaceOligos != 0.0) {
            moyenneOligos = sommePrixSurfaceOligos / sommeSurfaceOligos;
        }
        if (sommeSurfaceRegulateur != 0.0) {
            moyenneRegulateur = sommePrixSurfaceRegulateur / sommeSurfaceRegulateur;
        }
        if (sommeSurfaceSouffre != 0.0) {
            moyenneSouffre = sommePrixSurfaceSouffre / sommeSurfaceSouffre;
        }
        p.setPrixAzote(moyenneAzote);
        p.setPrixEngrais(moyenneEngrais);
        p.setPrixFongicide(moyenneFongicide);
        p.setPrixHerbicide(moyenneHerbicide);
        p.setPrixInsecticide(moyenneInsecticide);
        p.setPrixOligos(moyenneOligos);
        p.setPrixRegulateur(moyenneRegulateur);
        p.setPrixSouffre(moyenneSouffre);
    }

    private void trierParcelles() {
        for (Parcelles p : listeObjet.getListeParcelle()) {
            if(null != p.getCulture()){
                if (p.getCulture().toUpperCase().equals(typeCulture.BLE.toString())) {
                    listeParcelleBle.add(p);
                } else if (p.getCulture().toUpperCase().equals(typeCulture.COLZA.toString())) {
                    listeParcelleColza.add(p);
                } else if (p.getCulture().toUpperCase().equals(typeCulture.LIN.toString())) {
                    listeParcelleLin.add(p);
                } else if (p.getCulture().toUpperCase().equals(typeCulture.ORGE.toString())) {
                    listeParcelleOrge.add(p);
                }else if (p.getCulture().toUpperCase().equals(typeCulture.ESCOURGEON.toString())) {
                    listeParcelleEscourgeon.add(p);
                } else if (p.getCulture().toUpperCase().equals(typeCulture.POIS.toString())) {
                    listeParcellePois.add(p);
                } else if (p.getCulture().toUpperCase().equals(typeCulture.FEVEROLE.toString())) {
                    listeParcelleFeveole.add(p);
                } else if (p.getCulture().toUpperCase().equals(typeCulture.TOURNESOL.toString())) {
                    listeParcelleTournesol.add(p);
                }else if (p.getCulture().equals("")) {
                    System.out.println("Parcelle non remplie !");
                }
                else{
                    System.out.println("Type de culture inconnue !"+p.getCulture());
                }
            }
        }
    }

}
