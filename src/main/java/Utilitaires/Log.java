package Utilitaires;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Romain
 */
public class Log {

    //private static final String CHEMIN = "C:\\\\LogsJava";
    private static final String CHEMIN = "/home/romain/LogsJava";
    private static Log log;
    private String nomFichier;

    private Log() {
        log = this;
        nomFichier = "";
    }

    public static synchronized Log getLog() {
        if (log == null) {
            log = new Log();
        }
        return log;
    }

    public void ecrire(String message) {
        if (verifDispo()) {
            FileWriter writer = null;
            BufferedWriter bWritter = null;
            try {
                writer = new FileWriter(nomFichier, true);

                writer.write(this.getHeure() + " " + message + "\n");

                /* Ecrire sur le terminal */
                System.out.println(this.getHeure() + " " + message + "\n");

            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    public void afficheEntete() {
        /* Généré avec http://www.ascii-fr.com/Generateur-de-texte.html */

        ecrire("     ___  ___   _____   _____   _   _       _____       ___   _____   _____    _            ___  ___       ___   _____    _____   _____  ");
        ecrire("    /   |/   | /  _  \\ |  _  \\ | | | |     | ____|     /   | /  ___| |  _  \\  | |          /   |/   |     /   | |  _  \\  /  ___| | ____|");
        ecrire("   / /|   /| | | | | | | |_| | | | | |     | |__      / /| | | |     | |_| |  | |         / /|   /| |    / /| | | |_| |  | |     | |__   ");
        ecrire("  / / |__/ | | | | | | |  _  { | | | |     |  __|    / / | | | |  _  |  _  /  | |        / / |__/ | |   / / | | |  _  /  | |  _  |  __|  ");
        ecrire(" / /       | | | |_| | | |_| | | | | |___  | |___   / /  | | | |_| | | | \\ \\  | |       / /       | |  / /  | | | | \\ \\  | |_| | | |___  ");
        ecrire("/_/        |_| \\_____/ |_____/ |_| |_____| |_____| /_/   |_| \\_____/ |_|  \\_\\ |_|      /_/        |_| /_/   |_| |_|  \\_\\ \\_____/ |_____| ");


    }

    public void ecrireErreur(String message) {
        message = "#######ERREUR####### " + message;
        ecrire(message);
    }

    private String getHeure() {
        Locale locale = Locale.getDefault();
        Date actuelle = new Date();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String dat = dateFormat.format(actuelle);
        return dat;
    }

    private Boolean verifDispoRepertoire() {
        File f = new File(CHEMIN);
        if (f.exists()) {
            return true;
        }
        Log.getLog().ecrireErreur("Log.verifDispoRepertoire le repertoire " + CHEMIN + " n'existe pas");
        return false;
    }

    private Boolean verifDispo() {
        if (verifDispoRepertoire()) {
            Date myDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat(Log.getLog().getPropertie("typeDateFichierLog"));
            String sDate = sdf.format(myDate); // convert date to string
            nomFichier = CHEMIN + "/" + sDate + ".txt";
            File f = new File(nomFichier);
            if (f.exists()) {
                return true;
            } else {
                File fichier = new File(nomFichier);
                try {
                    FileWriter fileWriter = new FileWriter(fichier);
                    PrintWriter out = new PrintWriter(fileWriter);

                    return true;
                } catch (IOException ex) {
                    Log.getLog().ecrireErreur("Log.verifDispo IOException ");
                }

            }
        }
        return false;
    }

    public String getPropertie(String key) {
        File file = new File(CHEMIN + "/prop.properties");
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(file));
            return properties.getProperty(key);
        } catch (IOException ex) {
            Log.getLog().ecrireErreur("Log.getPropertie IOException");
        }
        return null;
    }
}
