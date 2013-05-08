package Utilitaires;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author SILO
 */
public class GestionSQL {

    private static String url, login, pass;
    private Connection con = null;
    private static GestionSQL GestionSQL;

    private GestionSQL() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {




        Class.forName("org.gjt.mm.mysql.Driver").newInstance();
        con = (Connection) DriverManager.getConnection(url, login, pass);
        GestionSQL = this;


    }

    public static GestionSQL getConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        if (GestionSQL == null) {
                url = Log.getLog().getPropertie("url_mysql");
                login = Log.getLog().getPropertie("login_mysql");
                pass = Log.getLog().getPropertie("pass_mysql");
            
            GestionSQL c = new GestionSQL();
            return c;
        }
        return GestionSQL;
    }

    public Connection getCon() {
        return con;
    }

    public void fermerConnection(){
        try {
            this.con.close();
        } catch (SQLException ex) {
            Log.getLog().ecrireErreur(GestionSQL.class.getName() + " " + ex + " " + ex.getLocalizedMessage());
        }
    }
}
