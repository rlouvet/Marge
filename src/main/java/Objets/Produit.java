package Objets;

/**
 *
 * @author Romain
 */
public class Produit {

    private int idProduit;
    private String nom, type;

    public Produit(int idProduit, String nom, String type) {
        this.idProduit = idProduit;
        this.nom = nom;
        this.type = type;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
