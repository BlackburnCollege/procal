package edu.blackburn.programmercalculator;

/**
 * [Super] Model(s) Class
 *
 * @author Ashley Holcomb & Drew Hans
 */
public class Model {

    /**
     * Model Constructor - it's here if we need it, but we probably won't :P
     */
    public Model() {

    }//end Model constructor

    /**
     * convertBase10toBase2 Method
     *
     * @param binNumString - assume a String of 1s and 0s with length bitPrecision
     * @param bitPrecision - any bit precision in range 5 - 32 will work for both signed/unsigned
     * @param signed - a boolean that indicates two's compliment conversion
     * @return a string base10 representation of binNum or an error
     */
    public String convertBase2toBase10(String binNumString, int bitPrecision, boolean signed) {
        String buffer = "";

        // check that binary input does not exceed our bitPrecision
        if (binNumString.length() <= bitPrecision) {
            int temp = 0;
            if (signed && binNumString.charAt(0) == '1') {
                // if negative signed

                // pad the signed value with ones up to bitPrecision
                binNumString = String.format("%" + bitPrecision + "s", binNumString).replace(' ', '1');

                // calculate the base 10 value of binNumString
                temp = (-1) * (unsignedBinaryStringToPositiveDecimalInt(flipBits(binNumString)) - 1);

                buffer = Integer.toString(temp);
                
            } else {
                // if positive signed or unsigned

                // pad the signed value with zeros up to bitPrecision
                binNumString = String.format("%" + bitPrecision + "s", binNumString).replace(' ', '0');

                // calculate the unsigned base 10 value of binNumString
                temp = unsignedBinaryStringToPositiveDecimalInt(binNumString);
                buffer = Integer.toString(temp);
            }
        } else {
            buffer = "Err: Number out of bounds";
        }
        return buffer;
    }//end convertBase2toBase10

    private int unsignedBinaryStringToPositiveDecimalInt(String binString) {
        int temp = 0;
        char[] bits = binString.toCharArray();

        for (int i = 0; i < bits.length; i++) {
            if (bits[i] == '1') {
                temp = temp + (int) (Math.pow(2, bits.length - i - 1));
            }
        }
        return temp;
    }//end unsignedBinaryStringToPositiveDecimalInt method

    /**
     * convertBase10toBase2 Method
     *
     * @param decNum - assume a 32bit integer decimal number
     * @param bitPrecision - any bit precision in range 5 - 32 will work for both signed/unsigned
     * @param signed - a boolean that indicates two's compliment conversion
     * @return a string base2 representation of decNum or an error
     */
    public String convertBase10toBase2(int decNum, int bitPrecision, boolean signed) {
        String buffer = "";

        if (isWithinBounds(decNum, bitPrecision, signed)) {
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
            buffer = "Err: Number out of mode bounds";
        }

        return buffer;
    }//end convertBase10toBase2 method

    public String convertBase10toBase2(String decNumString, int bitPrecision, boolean signed) {
        return convertBase10toBase2(Integer.parseInt(decNumString), bitPrecision, signed);
    }//end convertBase10toBase2 method

    /**
     * convertBase10toBase8 Method
     *
     * @param decNum - assume a 32bit integer decimal number
     * @param bitPrecision - any bit precision in range 5 - 32 will work for both signed/unsigned
     * @param signed - a boolean that indicates two's compliment conversion
     * @return a string base8 representation of decNum or an error
     */
    public String convertBase10toBase8(int decNum, int bitPrecision, boolean signed) {
        String buffer = "";
        String base2Value = convertBase10toBase2(decNum, bitPrecision, signed);

        // check for Err msg
        if (base2Value.equalsIgnoreCase("Err: Number out of bounds")) {
            // then store Err msg in buffer and return immediately
            buffer = base2Value;
        } else {
            // convert base2Value to base8 and stuff result in buffer

            // pad with appropriate bit to get a clean base conversion
            while (base2Value.length() % 3 != 0) {
                if (signed && decNum < 0) {
                    base2Value = "1" + base2Value;
                } else {
                    base2Value = "0" + base2Value;
                }
            }

            // initialize variables for the for-loop
            String temp = "";
            char[] bits = base2Value.toCharArray();

            // substitute every 3 bits with their octal value
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
            }//end for loop
        }//end if else statement

        return buffer;
    }//end convertBase10toBase8 method

    public String convertBase10toBase8(String decNumString, int bitPrecision, boolean signed) {
        return convertBase10toBase8(Integer.parseInt(decNumString), bitPrecision, signed);
    }//end convertBase10toBase8 method

    /**
     * convertBase10toBase16 Method
     *
     * @param decNum - assume a 32bit integer decimal number
     * @param bitPrecision - any bit precision in range 5 - 32 will work for both signed/unsigned
     * @param signed - a boolean that indicates two's compliment conversion
     * @return a string base16 representation of decNum or an error
     */
    public String convertBase10toBase16(int decNum, int bitPrecision, boolean signed) {
        String buffer = "";
        String base2Value = convertBase10toBase2(decNum, bitPrecision, signed);

        // check for Err msg
        if (base2Value.equalsIgnoreCase("Err: Number out of bounds")) {
            // then store Err msg in buffer and return immediately
            buffer = base2Value;
        } else {
            // convert base2Value to base16 and stuff result in buffer

            // pad with appropriate bit to get a clean base conversion
            while (base2Value.length() % 4 != 0) {
                if (signed && decNum < 0) {
                    base2Value = "1" + base2Value;
                } else {
                    base2Value = "0" + base2Value;
                }
            }

            // initialize variables for the for-loop
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
            }//end for loop
        }//end if else statement

        return buffer;
    }//end convertBase10toBase16 method

    public String convertBase10toBase16(String decNumString, int bitPrecision, boolean signed) {
        return convertBase10toBase16(Integer.parseInt(decNumString), bitPrecision, signed);
    }//end convertBase10toBase16 method

    /**
     * isWithinBounds Method
     *
     * @param decNum - a base 10 number
     * @param bitprecision - the number of binary bits we have to represent decNum
     * @param signed - true if we want the representation to be signed
     * @return true if decNum is within upper and lower bounds
     */
    private boolean isWithinBounds(int decNum, int bitPrecision, boolean signed) {
        //store upper and lower bounds as 64 bit numbers to avoid overflow
        long upperBound;
        long lowerBound;

        if (signed) {
            upperBound = (long) (Math.pow(2, bitPrecision - 1) - 1);
            lowerBound = (long) (Math.pow(2, bitPrecision - 1) * -1);
        } else {
            upperBound = (long) (Math.pow(2, bitPrecision) - 1);
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
        flipped = flipped.replace(" ", "1"); // set spaces to 1s
        return flipped;
    }//end flipBits method

    /**
     * binaryAddition Method - https://stackoverflow.com/questions/8548586/adding-binary-numbers
     *
     * @param s1
     * @param s2
     * @return
     */
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
    }//end binaryAddition

    /**
     * not Method
     *
     * @param input - assume a String of 1s and 0s
     * @return
     */
    private String not(String input) {

        input = input.replace('1', 'x');
        input = input.replace('0', '1');
        input = input.replace('x', '0');

        return input;
    }//end not class

    /*
    // This block of commented out code is left over from Monday October 23rd's session
    
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
}//end Model class
