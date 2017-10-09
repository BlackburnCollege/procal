package edu.blackburn.programmercalculator;

/**
 * Created by Ashley Holcomb, Braydon Rekart, Jessica Cramer on 10/9/2017.
 */

public class Model {

    public Model () {

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

        }


    }//end convertDecimalToBinary method

    public String and(int dec1, int dec2){
        return Integer.toString(dec1 & dec2);
    }

    public String or(int dec1, int dec2){
        return Integer.toString(dec1 | dec2);
    }

    public String not(int dec1){
        return Integer.toString(~dec1);
    }

    public String xor(int dec1, int dec2){
        return Integer.toString(dec1 ^ dec2);
    }

    public String nor(int dec1, int dec2){
        return this.not(this.or(dec1, dec2).);
    }

}//end Model class
