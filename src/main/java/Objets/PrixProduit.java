package Objets;

/**
 *
 * @author Romain
 */
public class PrixProduit {

    private Utilisateur user;
    private Produit produit;
    private double quantite, prix;
    private double prixQt; //Représente prix * Quantité

    public PrixProduit(Utilisateur user, Produit produit, double quantite, double prix) {
        this.user = user;
        this.produit = produit;
        this.quantite = quantite;
        this.prix = prix;
        this.prixQt = prix * quantite;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public double getPrixQt() {
        return prixQt;
    }

    public void setPrixQt(double prixQt) {
        this.prixQt = prixQt;
    }
}
