package Objets;

/**
 *
 * @author Romain
 */
public class Utilisateur {

    private int idUser;
    private String login;
    private int optionPrix;
    private double prixAzote, prixOligos, prixFongicide, prixHerbicide, prixEngrais, prixInsecticide, prixSouffre, prixRegulateur;

    public Utilisateur(int idUser, String login, int optionPrix) {
        this.idUser = idUser;
        this.login = login;
        this.optionPrix = optionPrix;
        prixAzote = 0.0;
        prixOligos = 0.0;
        prixFongicide = 0.0;
        prixHerbicide = 0.0;
        prixEngrais = 0.0;
        prixInsecticide = 0.0;
        prixSouffre = 0.0;
        prixRegulateur = 0.0;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getOptionPrix() {
        return optionPrix;
    }

    public void setOptionPrix(int optionPrix) {
        this.optionPrix = optionPrix;
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

    
}