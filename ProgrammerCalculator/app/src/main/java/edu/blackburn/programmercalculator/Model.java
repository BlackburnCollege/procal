/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

/**
 *
 * @author ashley.holcomb
 */
public class JavaApplication2 {

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
