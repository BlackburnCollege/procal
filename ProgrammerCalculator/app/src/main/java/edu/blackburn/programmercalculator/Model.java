package edu.blackburn.programmercalculator;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Ashley Holcomb, Braydon Rekart, Jessica Cramer, Hannah Goett on 10/9/2017.
 */
public class Model {

    public Model() {

    }//end Model constructor

    public String convertDecimalToBinary(int dec, int bitPrec, boolean signed) {
        String buffer = Integer.toBinaryString(dec);

        if (!signed) {

            if (buffer.length() > bitPrec) {
                return getString(R.string.outOfBoundsError) + ": " + bitPrec;
            } else {
                return buffer;
            }
        } else {
            //buffer = the signed version of dec
            int integer = Integer.parseInt(buffer, 2);
            return Integer.toBinaryString(Integer.parseInt(this.not((integer + Integer.parseInt("1", 2)))));
        }


    }//end convertDecimalToBinary method

    public String and(int dec1, int dec2) {
        return Integer.toString(dec1 & dec2);
    }

    public String or(int dec1, int dec2) {
        return Integer.toString(dec1 | dec2);
    }

    public String not(int dec1) {
        return Integer.toString(~dec1);
    }

    public String not(String num) {
        String notNum = "";
        for (int i = num.length() - 1; i < 0; i++) {
            if (num.charAt(i) == '0') {
                notNum += '1';
            } else {
                notNum += '0';
            }
        }
        return notNum;
    }

    public String xor(int dec1, int dec2) {
        return Integer.toString(dec1 ^ dec2);
    }

    public String nor(int dec1, int dec2) {
        return this.not(this.or(dec1, dec2));
    }

    private String getString(int stringID) {
        return Resources.getSystem().getString(stringID);
    }


}//end Model class
