package Objets;

/**
 *
 * @author Romain
 */
public class Parcelles {

    private String nomParcelle, culture, semence;

    private int ilot;
    private double superficie;
    private Utilisateur u;
    private double prixAzote, prixEngrais, prixFongicide, prixHerbicide, prixInsecticide, prixOligos, prixRegulateur, prixSouffre;

    public Parcelles(String nomParcelle, String culture, int ilot, double superficie, Utilisateur u, String semence) {
        this.nomParcelle = nomParcelle;
        this.culture = culture;
        this.ilot = ilot;
        this.superficie = superficie;
        this.u = u;
        prixAzote = 0.0;
        prixEngrais = 0.0;
        prixFongicide = 0.0;
        prixHerbicide = 0.0;
        prixInsecticide = 0.0;
        prixOligos = 0.0;
        prixRegulateur = 0.0;
        prixSouffre = 0.0;
        this.semence = semence;
    }

    public Parcelles(String nomParcelle, String culture, int ilot, double superficie, String semence) {
        this.nomParcelle = nomParcelle;
        this.culture = culture;
        this.ilot = ilot;
        this.superficie = superficie;
        prixAzote = 0.0;
        prixEngrais = 0.0;
        prixFongicide = 0.0;
        prixHerbicide = 0.0;
        prixInsecticide = 0.0;
        prixOligos = 0.0;
        prixRegulateur = 0.0;
        prixSouffre = 0.0;
        this.semence = semence;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public int getIlot() {
        return ilot;
    }

    public void setIlot(int ilot) {
        this.ilot = ilot;
    }

    public String getNomParcelle() {
        return nomParcelle;
    }

    public void setNomParcelle(String nomParcelle) {
        this.nomParcelle = nomParcelle;
    }

    public double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

    public Utilisateur getUtilisateur() {
        return u;
    }

    public void setUtilisateur(Utilisateur u) {
        this.u = u;
    }

    public double getPrixAzote() {
        return prixAzote;
    }

    public void setPrixAzote(double prixAzote) {
        this.prixAzote = prixAzote;
    }

    public double getPrixEngrais() {
        return prixEngrais;
    }

    public void setPrixEngrais(double prixEngrais) {
        this.prixEngrais = prixEngrais;
    }

    public double getPrixFongicide() {
        return prixFongicide;
    }

    public void setPrixFongicide(double prixFongicide) {
        this.prixFongicide = prixFongicide;
    }

    public double getPrixHerbicide() {
        return prixHerbicide;
    }

    public void setPrixHerbicide(double prixHerbicide) {
        this.prixHerbicide = prixHerbicide;
    }

    public double getPrixInsecticide() {
        return prixInsecticide;
    }

    public void setPrixInsecticide(double prixInsecticide) {
        this.prixInsecticide = prixInsecticide;
    }

    public double getPrixOligos() {
        return prixOligos;
    }

    public void setPrixOligos(double prixOligos) {
        this.prixOligos = prixOligos;
    }

    public double getPrixRegulateur() {
        return prixRegulateur;
    }

    public void setPrixRegulateur(double prixRegulateur) {
        this.prixRegulateur = prixRegulateur;
    }

    public double getPrixSouffre() {
        return prixSouffre;
    }

    public void setPrixSouffre(double prixSouffre) {
        this.prixSouffre = prixSouffre;
    }

    public Utilisateur getU() {
        return u;
    }

    public void setU(Utilisateur u) {
        this.u = u;
    }


    public String getSemence() {
        return semence;
    }

    public void setSemence(String semence) {
        this.semence = semence;
    }


    
}
