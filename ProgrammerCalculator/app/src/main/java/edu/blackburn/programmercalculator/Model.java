package edu.blackburn.programmercalculator;

/**
 * [Super] Model(s) Class
 *
 * @author Ashley Holcomb
 * @author Braydon Rekart
 * @author Jessica Cramer
 * @author Hannah Goett
 * @author Drew Hans
 */
public class Model {

    private final String ERRMSG;

    /**
     * Model Constructor
     */
    public Model() {
        // storing the errmsg in one place makes debugging easier
        ERRMSG = "Err: Number out of mode bounds";
    }//end Model constructor

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// convertBase2toBase* Methods /////////////////////////////////

    /**
     * convertBase2toBase8 Method
     *
     * @param binString    - assume a String of 1s and 0s with length bitPrecision or less
     * @param bitPrecision - any bit precision in range 5 - 32 will work for both signed/unsigned
     * @param signed       - a boolean that indicates two's compliment conversion
     * @return a string base8 representation of binString or an error message
     */
    public String convertBase2toBase8(String binString, int bitPrecision, boolean signed) {
        String buffer = "";

        // check that binString does not exceed our bitPrecision
        if (binString.length() > bitPrecision) {
            buffer = ERRMSG;
        } else {
            // pad with zeros when necessary (assume user forgot to input zeros for positive signed value)
            binString = String.format("%" + bitPrecision + "s", binString).replace(' ', '0');

            // pad with leftmost bit to get a clean conversion to base 8
            while (binString.length() % 3 != 0) {
                if (signed && binString.charAt(0) == '1') {
                    binString = "1" + binString;
                } else {
                    binString = "0" + binString;
                }
            }

            // initialize variables for the for-loop
            String temp = "";
            char[] bits = binString.toCharArray();

            // substitute every 3 bits with their octal value
            for (char bit : bits) {
                temp = temp + bit; // append next bit

                if (temp.equalsIgnoreCase("111")) {
                    buffer = buffer + "7"; // append 7
                    temp = ""; // clear temp
                } else if (temp.equalsIgnoreCase("110")) {
                    buffer = buffer + "6"; // append 6
                    temp = ""; // clear temp
                } else if (temp.equalsIgnoreCase("101")) {
                    buffer = buffer + "5"; // append 5
                    temp = ""; // clear temp
                } else if (temp.equalsIgnoreCase("100")) {
                    buffer = buffer + "4"; // append 4
                    temp = ""; // clear temp
                } else if (temp.equalsIgnoreCase("011")) {
                    buffer = buffer + "3"; // append 3
                    temp = ""; // clear temp
                } else if (temp.equalsIgnoreCase("010")) {
                    buffer = buffer + "2"; // append 2
                    temp = ""; // clear temp
                } else if (temp.equalsIgnoreCase("001")) {
                    buffer = buffer + "1"; // append 1
                    temp = ""; // clear temp
                } else if (temp.equalsIgnoreCase("000")) {
                    buffer = buffer + "0"; // append 0
                    temp = ""; // clear temp
                }
            }//end for loop
        }//end if else statement

        return buffer;
    }//end convertBase2toBase8

    /**
     * convertBase2toBase10 Method
     *
     * @param binString    - assume a String of 1s and 0s with length bitPrecision or less
     * @param bitPrecision - any bit precision in range 5 - 32 will work for both signed/unsigned
     * @param signed       - a boolean that indicates two's compliment conversion
     * @return a string base10 representation of binString or an error message
     */
    public String convertBase2toBase10(String binString, int bitPrecision, boolean signed) {
        String buffer;
        int temp;

        // check that binString does not exceed our bitPrecision
        if (binString.length() > bitPrecision) {
            buffer = ERRMSG;
        } else {
            // pad with zeros when necessary (assume user forgot to input zeros for positive signed value)
            binString = String.format("%" + bitPrecision + "s", binString).replace(' ', '0');

            if (signed && binString.charAt(0) == '1') {
                // if negative signed
                binString = not(binString); // flip bits

                temp = unsignedBinaryStringToPositiveDecimalInt(binString); // get positive decimal value

                temp = temp + 1; // add one

                temp = temp * (-1); // get the negative

                buffer = Integer.toString(temp); // stuff the base 10 value into buffer
            } else {
                // if positive signed or unsigned
                temp = unsignedBinaryStringToPositiveDecimalInt(binString);  // get positive decimal value
                buffer = Integer.toString(temp); // stuff the base 10 value into buffer
            }
        }//end if else statement

        return buffer;
    }//end convertBase2toBase10

    /**
     * convertBase2toBase16 Method
     *
     * @param binString    - assume a String of 1s and 0s with length bitPrecision or less
     * @param bitPrecision - any bit precision in range 5 - 32 will work for both signed/unsigned
     * @param signed       - a boolean that indicates two's compliment conversion
     * @return a string base16 representation of binString or an error message
     */
    public String convertBase2toBase16(String binString, int bitPrecision, boolean signed) {
        String buffer = "";

        // check that binString does not exceed our bitPrecision
        if (binString.length() > bitPrecision) {
            buffer = ERRMSG;
        } else {
            // pad with zeros when necessary (assume user forgot to input zeros for positive signed value)
            binString = String.format("%" + bitPrecision + "s", binString).replace(' ', '0');

            // pad with leftmost bit to get a clean conversion to base 16
            while (binString.length() % 4 != 0) {
                if (signed && binString.charAt(0) == '1') {
                    binString = "1" + binString;
                } else {
                    binString = "0" + binString;
                }
            }

            // initialize variables for the for-loop
            String temp = "";
            char[] bits = binString.toCharArray();

            // substitute every 3 bits with their octal value
            for (char bit : bits) {
                temp = temp + bit; // append next bit

                if (temp.equalsIgnoreCase("1111")) {
                    buffer = buffer + "F"; // append F
                    temp = ""; // clear temp
                } else if (temp.equalsIgnoreCase("1110")) {
                    buffer = buffer + "E"; // append E
                    temp = ""; // clear temp
                } else if (temp.equalsIgnoreCase("1101")) {
                    buffer = buffer + "D"; // append D
                    temp = ""; // clear temp
                } else if (temp.equalsIgnoreCase("1100")) {
                    buffer = buffer + "C"; // append C
                    temp = ""; // clear temp
                } else if (temp.equalsIgnoreCase("1011")) {
                    buffer = buffer + "B"; // append B
                    temp = ""; // clear temp
                } else if (temp.equalsIgnoreCase("1010")) {
                    buffer = buffer + "A"; // append A
                    temp = ""; // clear temp
                } else if (temp.equalsIgnoreCase("1001")) {
                    buffer = buffer + "9"; // append 9
                    temp = ""; // clear temp
                } else if (temp.equalsIgnoreCase("1000")) {
                    buffer = buffer + "8"; // append 8
                    temp = ""; // clear temp
                } else if (temp.equalsIgnoreCase("0111")) {
                    buffer = buffer + "7"; // append 7
                    temp = ""; // clear temp
                } else if (temp.equalsIgnoreCase("0110")) {
                    buffer = buffer + "6"; // append 6
                    temp = ""; // clear temp
                } else if (temp.equalsIgnoreCase("0101")) {
                    buffer = buffer + "5"; // append 5
                    temp = ""; // clear temp
                } else if (temp.equalsIgnoreCase("0100")) {
                    buffer = buffer + "4"; // append 4
                    temp = ""; // clear temp
                } else if (temp.equalsIgnoreCase("0011")) {
                    buffer = buffer + "3"; // append 3
                    temp = ""; // clear temp
                } else if (temp.equalsIgnoreCase("0010")) {
                    buffer = buffer + "2"; // append 2
                    temp = ""; // clear temp
                } else if (temp.equalsIgnoreCase("0001")) {
                    buffer = buffer + "1"; // append 1
                    temp = ""; // clear temp
                } else if (temp.equalsIgnoreCase("0000")) {
                    buffer = buffer + "0"; // append 0
                    temp = ""; // clear temp
                }
            }//end for loop
        }//end if else statement

        return buffer;
    }//end convertBase2toBase16

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// convertBase8toBase* Methods /////////////////////////////////

    /**
     * convertBase8toBase2 Method
     *
     * @param octString    - assume a String of octal values with bit length of bitPrecision or less
     * @param bitPrecision - any bit precision in range 5 - 32 will work for both signed/unsigned
     * @param signed       - a boolean that indicates two's compliment conversion
     * @return a string base2 representation of octString or an error message
     */
    public String convertBase8toBase2(String octString, int bitPrecision, boolean signed) {
        String buffer = "";
        int octStringBits = (octString.length() * 3); // assume each octal value uses all three bits

        if (octString.charAt(0) == '3' || octString.charAt(0) == '2') {
            octStringBits = octStringBits - 1; // the leftmost bit is unused so ignore it
        } else if (octString.charAt(0) == '1' || octString.charAt(0) == '0') {
            octStringBits = octStringBits - 2; // the two leftmost bits are unused so ignore them
        }

        // check that octString does not exceed our base2 bitPrecision
        if (octStringBits > bitPrecision) {
            buffer = ERRMSG;
        } else {
            // initialize variable for the for-loop
            char[] bits = octString.toCharArray();

            if (bits[0] == '3') {
                buffer = buffer + "11"; // append 11
            } else if (bits[0] == '2') {
                buffer = buffer + "10"; // append 10
            } else if (bits[0] == '1') {
                buffer = buffer + "1"; // append 1
            } else if (bits[0] == '0') {
                buffer = buffer + "0"; // append 0
            }

            // substitute every octal value after index 0 with their 3 bit binary value
            for (int i = 1; i < bits.length; i++) {

                if (bits[i] == '7') {
                    buffer = buffer + "111"; // append 111
                } else if (bits[i] == '6') {
                    buffer = buffer + "110"; // append 110
                } else if (bits[i] == '5') {
                    buffer = buffer + "101"; // append 101
                } else if (bits[i] == '4') {
                    buffer = buffer + "100"; // append 100
                } else if (bits[i] == '3') {
                    buffer = buffer + "011"; // append 011
                } else if (bits[i] == '2') {
                    buffer = buffer + "010"; // append 010
                } else if (bits[i] == '1') {
                    buffer = buffer + "001"; // append 001
                } else if (bits[i] == '0') {
                    buffer = buffer + "000"; // append 000
                }
            }//end for loop

            // pad with leftmost bit
            buffer = String.format("%" + bitPrecision + "s", buffer).replace(' ', buffer.charAt(0));

        }//end if else statement

        return buffer;
    }//end convertBase8toBase2

    /**
     * convertBase8toBase10 Method
     *
     * @param octString    - assume a String of octal values with bit length of bitPrecision or less
     * @param bitPrecision - any bit precision in range 5 - 32 will work for both signed/unsigned
     * @param signed       - a boolean that indicates two's compliment conversion
     * @return a string base10 representation of octString or an error message
     */
    public String convertBase8toBase10(String octString, int bitPrecision, boolean signed) {
        String buffer;
        String binString = convertBase8toBase2(octString, bitPrecision, signed);

        if (binString.equalsIgnoreCase(ERRMSG)) {
            // then store Err msg in buffer and return immediately
            buffer = binString;
        } else {
            buffer = convertBase2toBase10(binString, bitPrecision, signed);
        }

        return buffer;
    }//end convertBase8toBase10

    /**
     * convertBase8toBase10 Method
     *
     * @param octString    - assume a String of octal values with bit length of bitPrecision or less
     * @param bitPrecision - any bit precision in range 5 - 32 will work for both signed/unsigned
     * @param signed       - a boolean that indicates two's compliment conversion
     * @return a string base16 representation of octString or an error message
     */
    public String convertBase8toBase16(String octString, int bitPrecision, boolean signed) {
        String buffer;
        String binString = convertBase8toBase2(octString, bitPrecision, signed);

        if (binString.equalsIgnoreCase(ERRMSG)) {
            // then store Err msg in buffer and return immediately
            buffer = binString;
        } else {
            buffer = convertBase2toBase16(binString, bitPrecision, signed);
        }

        return buffer;
    }//end convertBase8toBase16

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////// convertBase10toBase* Methods /////////////////////////////////

    /**
     * convertBase8toBase2 Method
     *
     * @param decInteger   - assume a standard 32-bit integer decimal number
     * @param bitPrecision - any bit precision in range 5 - 32 will work for both signed/unsigned
     * @param signed       - a boolean that indicates two's compliment conversion
     * @return a string base2 representation of decInteger or an error message
     */
    public String convertBase10toBase2(int decInteger, int bitPrecision, boolean signed) {
        String buffer;

        if (isWithinBounds(decInteger, bitPrecision, signed)) {
            if (decInteger < 0) {
                decInteger = decInteger * (-1);
                decInteger = decInteger - 1;
                buffer = positiveDecimalIntToUnsignedBinaryString(decInteger);
                buffer = not(buffer);

                // pad the signed value with ones up to bitPrecision
                buffer = String.format("%" + bitPrecision + "s", buffer).replace(' ', '1');

            } else {
                // get the unsigned value
                buffer = positiveDecimalIntToUnsignedBinaryString(decInteger);

                // pad the unsigned value with zeros up to bitPrecision
                buffer = String.format("%" + bitPrecision + "s", buffer).replace(' ', '0');
            }

        } else {
            buffer = ERRMSG;
        }

        return buffer;
    }//end convertBase10toBase2 method

    /**
     * convertBase10toBase2 Method
     *
     * @param decString    - assume a standard 32-bit integer decimal number in String form
     * @param bitPrecision - any bit precision in range 5 - 32 will work for both signed/unsigned
     * @param signed       - a boolean that indicates two's compliment conversion
     * @return a string base2 representation of decInteger or an error message
     */
    public String convertBase10toBase2(String decString, int bitPrecision, boolean signed) {
        return convertBase10toBase2(Integer.parseInt(decString), bitPrecision, signed);
    }//end convertBase10toBase2 method

    /**
     * convertBase10toBase8 Method
     *
     * @param decInteger   - assume a standard 32-bit integer decimal number
     * @param bitPrecision - any bit precision in range 5 - 32 will work for both signed/unsigned
     * @param signed       - a boolean that indicates two's compliment conversion
     * @return a string base8 representation of decInteger or an error message
     */
    public String convertBase10toBase8(int decInteger, int bitPrecision, boolean signed) {
        String buffer;
        String binString = convertBase10toBase2(decInteger, bitPrecision, signed);

        if (binString.equalsIgnoreCase(ERRMSG)) {
            // then store Err msg in buffer and return immediately
            buffer = binString;
        } else {
            buffer = convertBase2toBase8(binString, bitPrecision, signed);
        }

        return buffer;
    }//end convertBase10toBase8 method

    /**
     * convertBase10toBase8 Method
     *
     * @param decString    - assume a standard 32-bit integer decimal number in String form
     * @param bitPrecision - any bit precision in range 5 - 32 will work for both signed/unsigned
     * @param signed       - a boolean that indicates two's compliment conversion
     * @return a string base8 representation of decInteger or an error message
     */
    public String convertBase10toBase8(String decString, int bitPrecision, boolean signed) {
        return convertBase10toBase8(Integer.parseInt(decString), bitPrecision, signed);
    }//end convertBase10toBase8 method

    /**
     * convertBase10toBase16 Method
     *
     * @param decInteger   - assume a standard 32-bit integer decimal number
     * @param bitPrecision - any bit precision in range 5 - 32 will work for both signed/unsigned
     * @param signed       - a boolean that indicates two's compliment conversion
     * @return a string base16 representation of decInteger or an error message
     */
    public String convertBase10toBase16(int decInteger, int bitPrecision, boolean signed) {
        String buffer;
        String binString = convertBase10toBase2(decInteger, bitPrecision, signed);

        if (binString.equalsIgnoreCase(ERRMSG)) {
            // then store Err msg in buffer and return immediately
            buffer = binString;
        } else {
            buffer = convertBase2toBase16(binString, bitPrecision, signed);
        }

        return buffer;
    }//end convertBase10toBase16 method

    /**
     * convertBase10toBase16 Method
     *
     * @param decString    - assume a standard 32-bit integer decimal number in String form
     * @param bitPrecision - any bit precision in range 5 - 32 will work for both signed/unsigned
     * @param signed       - a boolean that indicates two's compliment conversion
     * @return a string base16 representation of decInteger or an error message
     */
    public String convertBase10toBase16(String decString, int bitPrecision, boolean signed) {
        return convertBase10toBase16(Integer.parseInt(decString), bitPrecision, signed);
    }//end convertBase10toBase16 method


    ////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////// convertBase16toBase* Methods /////////////////////////////////

    /**
     * convertBase16toBase2 Method
     *
     * @param hexString    - assume a String of hexadecimal values with bit length of bitPrecision or less
     * @param bitPrecision - any bit precision in range 5 - 32 will work for both signed/unsigned
     * @param signed       - a boolean that indicates two's compliment conversion
     * @return a string base2 representation of hexString or an error message
     */
    public String convertBase16toBase2(String hexString, int bitPrecision, boolean signed) {
        String buffer = "";

        // check that hexString does not exceed our base2 bitPrecision
        if ((hexString.length() * 4) > bitPrecision) {
            buffer = ERRMSG;
        } else {
            // initialize variable for the for-loop
            char[] bits = hexString.toCharArray();

            // substitute every hexadecimal value with their 4 bit binary value
            for (char bit : bits) {

                if (bit == 'F') {
                    buffer = buffer + "1111"; // append 1111
                } else if (bit == 'E') {
                    buffer = buffer + "1110"; // append 1110
                } else if (bit == 'D') {
                    buffer = buffer + "1101"; // append 1101
                } else if (bit == 'C') {
                    buffer = buffer + "1100"; // append 1100
                } else if (bit == 'B') {
                    buffer = buffer + "1011"; // append 1011
                } else if (bit == 'A') {
                    buffer = buffer + "1010"; // append 1010
                } else if (bit == '9') {
                    buffer = buffer + "1001"; // append 1001
                } else if (bit == '8') {
                    buffer = buffer + "1000"; // append 1000
                } else if (bit == '7') {
                    buffer = buffer + "0111"; // append 0111
                } else if (bit == '6') {
                    buffer = buffer + "0110"; // append 0110
                } else if (bit == '5') {
                    buffer = buffer + "0101"; // append 0101
                } else if (bit == '4') {
                    buffer = buffer + "0100"; // append 0100
                } else if (bit == '3') {
                    buffer = buffer + "0011"; // append 0011
                } else if (bit == '2') {
                    buffer = buffer + "0010"; // append 0010
                } else if (bit == '1') {
                    buffer = buffer + "0001"; // append 0001
                } else if (bit == '0') {
                    buffer = buffer + "0000"; // append 0000
                }
            }//end for loop

            // pad with zeros when necessary (assume user forgot to input zeros for positive signed value)
            buffer = String.format("%" + bitPrecision + "s", buffer).replace(' ', '0');

        }//end if else statement

        return buffer;
    }//end convertBase16toBase2

    /**
     * convertBase16toBase8 Method
     *
     * @param hexString    - assume a String of hexadecimal values with bit length of bitPrecision or less
     * @param bitPrecision - any bit precision in range 5 - 32 will work for both signed/unsigned
     * @param signed       - a boolean that indicates two's compliment conversion
     * @return a string base8 representation of hexString or an error message
     */
    public String convertBase16toBase8(String hexString, int bitPrecision, boolean signed) {
        String buffer;
        String binString = convertBase16toBase2(hexString, bitPrecision, signed);

        if (binString.equalsIgnoreCase(ERRMSG)) {
            // then store Err msg in buffer and return immediately
            buffer = binString;
        } else {
            buffer = convertBase2toBase8(binString, bitPrecision, signed);
        }

        return buffer;
    }//end convertBase16toBase8

    /**
     * convertBase16toBase8 Method
     *
     * @param hexString    - assume a String of hexadecimal values with bit length of bitPrecision or less
     * @param bitPrecision - any bit precision in range 5 - 32 will work for both signed/unsigned
     * @param signed       - a boolean that indicates two's compliment conversion
     * @return a string base8 representation of hexString or an error message
     */
    public String convertBase16toBase10(String hexString, int bitPrecision, boolean signed) {
        String buffer;
        String binString = convertBase16toBase2(hexString, bitPrecision, signed);

        if (binString.equalsIgnoreCase(ERRMSG)) {
            // then store Err msg in buffer and return immediately
            buffer = binString;
        } else {
            buffer = convertBase2toBase10(binString, bitPrecision, signed);
        }

        return buffer;
    }//end convertBase16toBase10

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////// Operation Methods ///////////////////////////////////////

    /**
     * binaryAddition Method - https://stackoverflow.com/questions/8548586/adding-binary-numbers
     *
     * @param s1 - a string of 1s and 0s
     * @param s2 - a string of 1s and 0s
     * @return the string result of s1 and s2
     */
    public String binaryAddition(String s1, String s2) {
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
     * @param binNum - assume a String of 1s and 0s
     * @return flipped bit version of binNum
     */
    public String not(String binNum) {
        binNum = binNum.replace("0", "x"); // temporarily set 0s to xs
        binNum = binNum.replace("1", "0"); // set 1s to 0s
        binNum = binNum.replace("x", "1"); // set xs to 1s
        return binNum;
    }//end not method

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////// Helper Methods ////////////////////////////////////////
    private String positiveDecimalIntToUnsignedBinaryString(int decNum) {
        String buffer = "";
        int remainder;

        if (decNum == 0) {
            buffer = "0";
        } else {
            while (decNum > 0) {
                remainder = decNum % 2;
                buffer = remainder + buffer;
                decNum = decNum / 2;
            }//end while loop
        }

        return buffer;
    }//end positiveDecimalIntToUnsignedBinaryString method

    /**
     * unsignedBinaryStringToPositiveDecimalInt Method
     *
     * @param binString - assume a String of 1s and 0s
     * @return true if decNum is within upper and lower bounds
     */
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
     * isWithinBounds Method
     *
     * @param decNum       - a normal base 10 integer number
     * @param bitPrecision - the number of binary bits we have to represent decNum
     * @param signed       - true if we want the binary representation to be signed
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
