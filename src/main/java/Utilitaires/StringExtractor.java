package Utilitaires;

/**
 *
 * @author Romain
 */
/**
 *
 * Class: StringExtractor.java
 *
 * Description: StringExtractor extract an substring from a given String and may
 * convert that String in a particular format.
 *
 * @author HackTrack
 *
 * Created on Jul 25, 2007
 *
 */
public class StringExtractor {

    /**
     * Extract a long from another String
     *
     * @param text
     *            The source String
     * @param afterText
     *            The String after which the value has to be retrieved
     * @param untilText
     *            The String where the extractor must stop for substring
     *            retrieve
     * @return The long extracted from String
     * @throws NumberFormatException
     *             If String 'afterstring' hasn't been found in the 'source
     *             String' or if the returned String can't be converted into a
     *             long
     */
    public static long extractLong(String text, String afterText, String untilText) throws NumberFormatException {
        return Long.parseLong(extractString(text, afterText, untilText));
    }

    //--- Utility function to get int using a dialog.
    public static Double getDouble(String mess) {
        Double val = 0.0;


        String s = mess;
        s = s.replace('L', ' ');
        s = s.replace('l', ' ');
        s = s.replace(',', '.');
        try {
            val = Double.parseDouble(s);
        } catch (NumberFormatException nx) {
            Log.getLog().ecrire("Erreur dans parseDouble(StringExtractor) " + mess);
        }

        return val;
    }

    /**
     * Extract a double from another String
     *
     * @param text
     *            The source String
     * @param afterText
     *            The String after which the value has to be retrieved
     * @param untilText
     *            The String where the extractor must stop for substring
     *            retrieve
     * @return The double extracted from String
     * @throws NumberFormatException
     *             If String 'afterstring' hasn't been found in the 'source
     *             String' or if the returned String can't be converted into a
     *             double
     */
    public static double extractDouble(String text, String afterText, String untilText) throws NumberFormatException {
        return Double.parseDouble(extractString(text, afterText, untilText));
    }

    /**
     * Extract a int from another String
     *
     * @param text
     *            The source String
     * @param afterText
     *            The String after which the value has to be retrieved
     * @param untilText
     *            The String where the extractor must stop for substring
     *            retrieve
     * @return The int extracted from String
     * @throws NumberFormatException
     *             If String 'afterstring' hasn't been found in the 'source
     *             String' or if the returned String can't be converted into a
     *             int
     */
    public static int extractInt(String text, String afterText, String untilText) throws NumberFormatException {
        return Integer.parseInt(extractString(text, afterText, untilText));
    }

    /**
     * Extract a String from another String
     *
     * @param text
     *            The source String
     * @param afterText
     *            The String after which the value has to be retrieved
     * @param untilText
     *            The String where the extractor must stop for substring
     *            retrieve
     * @return The found substring or null if not found
     */
    public static String extractString(String text, String afterText, String untilText) {
        int afterTextIndex = text.indexOf(afterText);
        String tempStr = text.substring(afterTextIndex + afterText.length());
        int untilTextIndex = tempStr.indexOf(untilText);
        tempStr = tempStr.substring(0, untilTextIndex);
        return tempStr;
    }

    public static void main(String[] args) {
        String text = "At 5.0043376036883105 : MAC addr:1--- received DATA frame { sz570(MAC-802.11_Data_Frame)sz58--Data Frame--duration:218--Address1:1--address2:101--Address3:0--Address4:-2--forcedError:false--__<sz512(INET)sz20--src:0--dest:1--prot:17--TTL:6/255--ToS:#0--label:0--nexthop:1__<sz492(UDP)sz8--s:50--d:1050__<id:1 generation time:5.000372 delay bound:0.03 src: 0>__>__>__ } ";
        String afterText = "generation time:";
        String untilText = " ";
        System.out.println(StringExtractor.extractDouble(text, afterText, untilText));
    }
}
