package Objets;

import java.util.Date;

/**
 *
 * @author Romain
 */
public class Saisie {

    private int idSaisie;
    private String parcelle, typeProduit, produit, quantite;
    private double prix;
    private Date date;
    private Utilisateur user;

    public Saisie(int idSaisie, String parcelle, String typeProduit, String produit, String quantite, Date date, Utilisateur user) {
        this.idSaisie = idSaisie;
        this.parcelle = parcelle;
        this.typeProduit = typeProduit;
        this.produit = produit;
        this.quantite = quantite;
        this.prix = 0.0;
        this.date = date;
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdSaisie() {
        return idSaisie;
    }

    public void setIdSaisie(int idSaisie) {
        this.idSaisie = idSaisie;
    }

    public String getParcelle() {
        return parcelle;
    }

    public void setParcelle(String parcelle) {
        this.parcelle = parcelle;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getTypeProduit() {
        return typeProduit;
    }

    public void setTypeProduit(String typeProduit) {
        this.typeProduit = typeProduit;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }
}
