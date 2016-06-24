package testtribehired.titinkurniat.com.testtribehired;

/**
 * Created by Titin Kurniati on 23-May-16.
 */
public class Util {
    public static String getFormat(String number) {
        String displayedString = "";
        if (number == null || number.length() == 0 || number.equals("null")) {
            displayedString = "0";
        } else {
            if (number.length() > 3) {
                int length = number.length();

                for (int i = length; i > 0; i -= 3) {
                    if (i > 3) {
                        String myStringPart1 = number.substring(0, i - 3);
                        String myStringPart2 = number.substring(i - 3);

                        String combinedString;

                        combinedString = myStringPart1 + ".";

                        combinedString += myStringPart2;
                        number = combinedString;

                        displayedString = combinedString;
                    }
                }
            } else {
                displayedString = number;
            }
        }
        return displayedString;
    }
}
