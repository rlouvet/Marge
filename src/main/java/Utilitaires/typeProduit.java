package Utilitaires;

/**
 *
 * @author Romain
 */
public enum typeProduit {
    engrais,
    fongicide,
    herbicide,
    insecticide,
    oligos,
    regulateur,
    souffre,
    azote;

     @Override
     public String toString() {
         switch (this) {
             case engrais:
                 return ("engrais");
             case fongicide:
                 return ("fongicide");
             case herbicide:
                 return ("herbicide");
             case insecticide:
                 return ("insecticide");
             case oligos:
                 return ("oligos");
             case regulateur:
                 return ("regulateur");
             case souffre:
                 return ("souffre");
             case azote:
                 return ("azote");

         }
         throw new RuntimeException("Invalid value for this");
     }
}
