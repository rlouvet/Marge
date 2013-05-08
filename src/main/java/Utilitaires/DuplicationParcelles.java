package Utilitaires;

import Objets.Parcelles;
import com.mysql.jdbc.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Romain
 */
public class DuplicationParcelles {

    private ArrayList<Parcelles> listeParcelle;
    private String oldLogin, newLogin;
    public ArrayList<Parcelles> getListeParcelle() {
        return listeParcelle;
    }

    public DuplicationParcelles(String oldLogin, String newLogin){
        this.oldLogin = oldLogin;
        this.newLogin = newLogin;
    }

    public void remplirParcelle() {
        try {

            
            
 

            Statement selectParcelle = (Statement) GestionSQL.getConnection().getCon().createStatement();
            ResultSet resultParcelle = selectParcelle.executeQuery("select * from nomparcelle where id_client = "+oldLogin);

            Parcelles p;
            int i = 0;
            String linkSQL;
            Statement s;
            while (resultParcelle.next()) {

                p = new Parcelles(resultParcelle.getString("nomParcelle"), resultParcelle.getString("culture"), resultParcelle.getInt("ilot"), resultParcelle.getDouble("superficie"), resultParcelle.getString("semence"));

                linkSQL = "insert into nomparcelle(nomParcelle,id_client, precedent, ilot, superficie) values('" + p.getNomParcelle().replace("'", "\\'") + "', '"+newLogin+"',  '" + p.getCulture() + "', '" + p.getIlot() + "', '" + p.getSuperficie() + "' )";
                System.out.println(linkSQL);
                s = (Statement) GestionSQL.getConnection().getCon().createStatement();
                try {
                    s.executeUpdate(linkSQL);
                } catch (Exception e) {
                    System.out.println("erreur à l'execution:" + e.getMessage());
                }
                i++;
            }
        } catch (ClassNotFoundException ex) {
            Log.getLog().ecrireErreur("DuplicationParcelles.remplirParcelle ClassNotFoundException");
        } catch (InstantiationException ex) {
            Log.getLog().ecrireErreur("DuplicationParcelles.remplirParcelle InstanciationException");
        } catch (IllegalAccessException ex) {
            Log.getLog().ecrireErreur("DuplicationParcelles.remplirParcelle IllegalAccessException");
        } catch (SQLException ex) {
            Log.getLog().ecrireErreur("DuplicationParcelles.remplirParcelle SQLdException");
        }
    }
}