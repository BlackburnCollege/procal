package javaapplication2;

/**
 * [Super] Model(s) Class
 *
 * @author Ashley Holcomb & Drew Hans
 */
public class Model {

    /**
    private String calculateConversion(int dec, int bits, int base, boolean signed) {
        JavaApplication2 myProgram = new JavaApplication2();
        double convertTemp;
        StringBuilder buffer = new StringBuilder();
        String stuffing = "";
        double tempPower; //because NetBeans is afraid of lossy conversions

        if (signed) {
            convertTemp = Math.abs(dec);
            if (dec >= 0) {
                stuffing = "0";
            } else {
                stuffing = "1";
            }
        } else {
            convertTemp = dec;
        }
        for (int i = bits - 1; i > -1; i--) {
            tempPower = Math.pow(base, i);

            if (convertTemp < tempPower) {
                buffer.append("0");

            } else {
                buffer.append("1");
                convertTemp = convertTemp - tempPower;
            }
            System.out.println(buffer);
        }

        //If we don't have enough bits...
        if (convertTemp > 0) {
            return "NOPE";
        }

        if (signed) {
            buffer.replace(0, buffer.length() - 1, myProgram.binaryAddition(myProgram.not(String.valueOf(buffer)), "1"));
            //Gotta stuff it, already have appropriate "stuffing", now for bitPrec
            System.out.println(buffer);
            while (buffer.length() <= bits) {
                buffer.append(stuffing);
            }
        }
        return String.valueOf(buffer);
    }

    private String convertDecimalToBinary(int dec, int bitPrec, boolean signed) {
        JavaApplication2 myProgram = new JavaApplication2();

        String converted = myProgram.calculateConversion(dec, bitPrec, 2, signed);

        if (converted.length() > bitPrec) {
            return "NOPE";
        } else {
            return converted;
        }
    }
    
    */

    /**
     * convertBase10toBase2 Method
     *
     * @param decNum - assume a 32bit integer decimal number
     * @param bitPrecision - a bit precision in range 8 - 32
     * @param signed - a boolean that indicates two's compliment conversion
     * @return
     */
    public String convertBase10toBase2(int decNum, int bitPrecision, boolean signed) {
        String buffer = "";

        if (isWithinBounds(decNum, 2, bitPrecision, signed)) {
            if (decNum < 0) {
                // get signed value 
                // subtract one then flip the bits (reverse order of "flip the bits and add one" method we learned)
                buffer = flipBits(Integer.toString(((-1) * decNum) - 1, 2));

                // pad the signed value with ones up to bitPrecision
                buffer = String.format("%" + bitPrecision + "s", buffer).replace(' ', '1');

            } else {
                // get the unsigned value
                buffer = Integer.toString(decNum, 2); // large positive numbers overflow on the leftmost bit so this works

                // pad the unsigned value with zeros up to bitPrecision
                buffer = String.format("%" + bitPrecision + "s", buffer).replace(' ', '0');
            }
        } else {
            buffer = "Err: Number out of bounds";
        }

        return buffer;
    }//end convertBase10toBase2 method

    /**
     * convertBase10toBase8 Method
     *
     * @param decNum - assume a 32bit integer decimal number
     * @param bitPrecision - a bit precision in range 8 - 32
     * @param signed - a boolean that indicates two's compliment conversion
     * @return
     */
    public String convertBase10toBase8(int decNum, int bitPrecision, boolean signed) {
        String buffer = "";
        String base2Value = convertBase10toBase2(decNum, bitPrecision, signed);

        // pad with appropriate bit to get a clean base conversion
        // might cause problems when base2Value is Err
        while (base2Value.length() % 3 != 0) {
            if (signed && decNum < 0) {
                base2Value = "1" + base2Value;
            } else {
                base2Value = "0" + base2Value;
            }
        }

        String temp = "";
        char[] bits = base2Value.toCharArray();

        // substitute every 3 bits with their octal value (note: Err msgs remain unchanged)
        for (int i = 0; i < bits.length; i++) {
            temp = temp + bits[i];

            if (temp.equalsIgnoreCase("111")) {
                buffer = buffer + "7";
                temp = "";
            } else if (temp.equalsIgnoreCase("110")) {
                buffer = buffer + "6";
                temp = "";
            } else if (temp.equalsIgnoreCase("101")) {
                buffer = buffer + "5";
                temp = "";
            } else if (temp.equalsIgnoreCase("100")) {
                buffer = buffer + "4";
                temp = "";
            } else if (temp.equalsIgnoreCase("011")) {
                buffer = buffer + "3";
                temp = "";
            } else if (temp.equalsIgnoreCase("010")) {
                buffer = buffer + "2";
                temp = "";
            } else if (temp.equalsIgnoreCase("001")) {
                buffer = buffer + "1";
                temp = "";
            } else if (temp.equalsIgnoreCase("000")) {
                buffer = buffer + "0";
                temp = "";
            } else {
                // do nothing
            }
        }

        if (buffer.equalsIgnoreCase("")) {
            buffer = base2Value;
        }

        return buffer;
    }//end convertBase10toBase8 method

    /**
     * convertBase10toBase16 Method
     *
     * @param decNum - assume a 32bit integer decimal number
     * @param bitPrecision - a bit precision in range 8 - 32
     * @param signed - a boolean that indicates two's compliment conversion
     * @return
     */
    public String convertBase10toBase16(int decNum, int bitPrecision, boolean signed) {
        String buffer = "";
        String base2Value = convertBase10toBase2(decNum, bitPrecision, signed);

        // pad with appropriate bit to get a clean base conversion
        // might cause problems when base2Value is Err
        while (base2Value.length() % 4 != 0) {
            if (signed && decNum < 0) {
                base2Value = "1" + base2Value;
            } else {
                base2Value = "0" + base2Value;
            }
        }

        String temp = "";
        char[] bits = base2Value.toCharArray();

        // substitute every 4 bits with their octal value (note: Err msgs remain unchanged)
        for (int i = 0; i < bits.length; i++) {
            temp = temp + bits[i];

            if (temp.equalsIgnoreCase("1111")) {
                buffer = buffer + "F";
                temp = "";
            } else if (temp.equalsIgnoreCase("1110")) {
                buffer = buffer + "E";
                temp = "";
            } else if (temp.equalsIgnoreCase("1101")) {
                buffer = buffer + "D";
                temp = "";
            } else if (temp.equalsIgnoreCase("1100")) {
                buffer = buffer + "C";
                temp = "";
            } else if (temp.equalsIgnoreCase("1011")) {
                buffer = buffer + "B";
                temp = "";
            } else if (temp.equalsIgnoreCase("1010")) {
                buffer = buffer + "A";
                temp = "";
            } else if (temp.equalsIgnoreCase("1001")) {
                buffer = buffer + "9";
                temp = "";
            } else if (temp.equalsIgnoreCase("1000")) {
                buffer = buffer + "8";
                temp = "";
            } else if (temp.equalsIgnoreCase("0111")) {
                buffer = buffer + "7";
                temp = "";
            } else if (temp.equalsIgnoreCase("0110")) {
                buffer = buffer + "6";
                temp = "";
            } else if (temp.equalsIgnoreCase("0101")) {
                buffer = buffer + "5";
                temp = "";
            } else if (temp.equalsIgnoreCase("0100")) {
                buffer = buffer + "4";
                temp = "";
            } else if (temp.equalsIgnoreCase("0011")) {
                buffer = buffer + "3";
                temp = "";
            } else if (temp.equalsIgnoreCase("0010")) {
                buffer = buffer + "2";
                temp = "";
            } else if (temp.equalsIgnoreCase("0001")) {
                buffer = buffer + "1";
                temp = "";
            } else if (temp.equalsIgnoreCase("0000")) {
                buffer = buffer + "0";
                temp = "";
            } else {
                // do nothing
            }
        }

        if (buffer.equalsIgnoreCase("")) {
            buffer = base2Value;
        }

        return buffer;
    }//end convertBase10toBase16 method

    /**
     * isWithinBounds Method
     *
     * @param decNum - a base 10 number
     * @param base - the base we want to convert decNum to
     * @param bitprecision - the number of bits we have to represent decNum in new base
     * @param signed - true if we want the representation to be signed
     * @return true if decNum is within upper and lower bounds
     */
    private boolean isWithinBounds(int decNum, int base, int bitprecision, boolean signed) {
        //store upper and lower bounds as 64 bit numbers to avoid overflow
        long upperBound;
        long lowerBound;

        if (signed) {
            upperBound = (long) (Math.pow(base, bitprecision - 1) - 1);
            lowerBound = (long) (Math.pow(base, bitprecision - 1) * -1);
        } else {
            upperBound = (long) (Math.pow(base, bitprecision) - 1);
            lowerBound = 0;
        }

        return (decNum <= upperBound) && (decNum >= lowerBound);
    }//end isWithinBounds method

    /**
     * flipBits Method
     *
     * @param binNum - assume a String of 1s and 0s
     * @return flipped bit version of binNum
     */
    private String flipBits(String binNum) {
        String flipped = binNum;
        flipped = flipped.replace("0", " "); // temporarily set 0s to spaces
        flipped = flipped.replace("1", "0"); // set 1s to 0s
        flipped = flipped.replace(" ", "1"); // set 1s to 0s
        return flipped;
    }//end flipBits method

    //https://stackoverflow.com/questions/8548586/adding-binary-numbers
    public static String binaryAddition(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return "";
        }
        int first = s1.length() - 1;
        int second = s2.length() - 1;
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (first >= 0 || second >= 0) {
            int sum = carry;
            if (first >= 0) {
                sum += s1.charAt(first) - '0';
                first--;
            }
            if (second >= 0) {
                sum += s2.charAt(second) - '0';
                second--;
            }
            carry = sum >> 1;
            sum = sum & 1;
            sb.append(sum == 0 ? '0' : '1');
        }
        if (carry > 0) {
            sb.append('1');
        }

        sb.reverse();
        return String.valueOf(sb);
    }

    private String not(String input) {

        input = input.replace('1', 'x');
        input = input.replace('0', '1');
        input = input.replace('x', '0');

        return input;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JavaApplication2 myProgram = new JavaApplication2();

        System.out.println(myProgram.convertDecimalToBinary(7, 8, true));

    }

}
