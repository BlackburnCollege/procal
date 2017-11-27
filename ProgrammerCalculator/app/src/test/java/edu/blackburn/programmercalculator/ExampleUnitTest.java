package edu.blackburn.programmercalculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private Model calculator = new Model();
    boolean signed = true;
    boolean unsigned = false;
    int bp8 = 8;
    int bp16 = 16;
    int bp32 = 32;


    @Test
    public void add_isCorrect() throws Exception {
        assertEquals("10",calculator.add("8", "2"));
        assertEquals("8", calculator.add("12", "-4"));
        assertEquals("8", calculator.add("-4", "12"));
        assertEquals("9", calculator.add("0", "9"));
        assertEquals("9", calculator.add("9", "0"));
        assertEquals("-12", calculator.add("-5", "-7"));
        assertNotEquals("2147483648", calculator.add("2147483647", "1"));
        assertNotEquals("-2147483649", calculator.add("-2147483648", "-1"));
    }

    @Test
    public void sub_isCorrect() throws Exception {
        assertEquals("6",calculator.sub("8", "2"));
        assertEquals("16", calculator.sub("12", "-4"));
        assertEquals("-16", calculator.sub("-4", "12"));
        assertEquals("-9", calculator.sub("0", "9"));
        assertEquals("9", calculator.sub("9", "0"));
        assertEquals("2", calculator.sub("-5", "-7"));
        assertNotEquals("2147483648", calculator.sub("2147483647", "-1"));
        assertNotEquals("-2147483649", calculator.sub("-2147483648", "1"));
    }

    @Test
    public void div_isCorrect() throws Exception {
        assertEquals("10",calculator.div("80", "8"));
        assertEquals("-8", calculator.div("-80", "10"));
        assertEquals("-8", calculator.div("80", "-10"));
        assertEquals("0", calculator.div("0", "9"));
        //assertEquals("9", calculator.add("9", "0"));
        assertEquals("5", calculator.div("-50", "-10"));
    }

    @Test
    public void mul_isCorrect() throws Exception {
        assertEquals("80",calculator.mul("10", "8"));
        assertEquals("-80", calculator.mul("-8", "10"));
        assertEquals("-80", calculator.mul("8", "-10"));
        assertEquals("0", calculator.mul("0", "9"));
        assertEquals("0", calculator.mul("9", "0"));
        assertEquals("50", calculator.mul("-5", "-10"));
    }

    @Test
    public void mod_isCorrect() throws Exception {
        assertEquals("2",calculator.mod("10", "8"));
        assertEquals("-2", calculator.mod("-10", "8"));
        assertEquals("2", calculator.mod("10", "-8"));
        assertEquals("0", calculator.mod("0", "9"));
        //assertEquals("0", calculator.mod("9", "0"));
        assertEquals("-5", calculator.mod("-5", "-10"));
    }

    @Test
    public void or_isCorrect() throws Exception {
        assertEquals("011", calculator.or("010", "011"));
        assertEquals("111", calculator.or("110", "111"));
        assertEquals("111", calculator.or("110", "011"));
        assertEquals("111", calculator.or("010", "111"));
        assertEquals("0000", calculator.or("000", "0000"));
        assertEquals("0111", calculator.or("0111", "000"));
    }

    @Test
    public void xor_isCorrect() throws Exception {
        assertEquals("001", calculator.xor("010", "011"));
        assertEquals("001", calculator.xor("110", "111"));
        assertEquals("101", calculator.xor("110", "011"));
        assertEquals("101", calculator.xor("010", "111"));
        assertEquals("0000", calculator.xor("000", "0000"));
        assertEquals("0111", calculator.xor("0111", "000"));
    }

    @Test
    public void not_isCorrect() throws Exception {
        assertEquals("11101", calculator.not("00010"));
        assertEquals("00010", calculator.not("11101"));
        assertEquals("11111", calculator.not("00000"));
        assertEquals("00000", calculator.not("11111"));
    }

    @Test
    public void and_isCorrect() throws Exception {
        assertEquals("010", calculator.and("010", "011"));
        assertEquals("110", calculator.and("110", "111"));
        assertEquals("010", calculator.and("110", "011"));
        assertEquals("010", calculator.and("010", "111"));
        assertEquals("0000", calculator.and("000", "0000"));
        assertEquals("0000", calculator.and("0111", "000"));
    }
    @Test
    public void nor_isCorrect() throws Exception {
        assertEquals("100", calculator.nor("010", "011"));
        assertEquals("000", calculator.nor("110", "111"));
        assertEquals("000", calculator.nor("110", "011"));
        assertEquals("000", calculator.nor("010", "111"));
        assertEquals("1111", calculator.nor("000", "0000"));
        assertEquals("1000", calculator.nor("0111", "000"));
    }

    @Test
    public void xnor_isCorrect() throws Exception {
        assertEquals("110", calculator.xnor("010", "011"));
        assertEquals("110", calculator.xnor("110", "111"));
        assertEquals("010", calculator.xnor("110", "011"));
        assertEquals("010", calculator.xnor("010", "111"));
        assertEquals("1111", calculator.xnor("000", "0000"));
        assertEquals("1000", calculator.xnor("0111", "000"));
    }

    @Test
    public void nand_isCorrect() throws Exception {
        assertEquals("101", calculator.nand("010", "011"));
        assertEquals("001", calculator.nand("110", "111"));
        assertEquals("101", calculator.nand("110", "011"));
        assertEquals("101", calculator.nand("010", "111"));
        assertEquals("1111", calculator.nand("000", "0000"));
        assertEquals("1111", calculator.nand("0111", "000"));
    }

    @Test
    public void base2to8_isCorrect() throws Exception {
        //4 bit unsigned
        assertEquals("11", calculator.convertBase2toBase8("1001", 4, false));
        assertEquals("01", calculator.convertBase2toBase8("0001", 4, false));
        assertEquals("00", calculator.convertBase2toBase8("0000", 4, false));
        //8 bit unsigned
        assertEquals("211", calculator.convertBase2toBase8("10001001", 8, false));
        assertEquals("001", calculator.convertBase2toBase8("00000001", 8, false));
        assertEquals("000", calculator.convertBase2toBase8("00000000", 8, false));
        //8 bit signed
        assertEquals("611", calculator.convertBase2toBase8("10001001", 8, true));
        assertEquals("001", calculator.convertBase2toBase8("00000001", 8, true));
        assertEquals("000", calculator.convertBase2toBase8("00000000", 8, true));
        //16 But unsigned
        assertEquals("177771", calculator.convertBase2toBase8("1111111111111001", 16, false));
        assertEquals("000001", calculator.convertBase2toBase8("0000000000000001", 16, false));
        assertEquals("000000", calculator.convertBase2toBase8("0000000000000000", 16, false));
        //16 bit signed
        assertEquals("777771", calculator.convertBase2toBase8("1111111111111001", 16, true));
        assertEquals("000001", calculator.convertBase2toBase8("0000000000000001", 16, true));
        assertEquals("000000", calculator.convertBase2toBase8("0000000000000000", 16, true));
        //32 bit signed
        assertEquals("77777777771", calculator.convertBase2toBase8("11111111111111111111111111111001", 32, true));
        assertEquals("00000000001", calculator.convertBase2toBase8("0000000000000001", 32, true));
        assertEquals("00000000000", calculator.convertBase2toBase8("0000000000000000", 32, true));
    }

    @Test
    public void base2to10_isCorrect() throws Exception {
        //4 bit unsigned
        assertEquals("9", calculator.convertBase2toBase10("1001", 4, false));
        assertEquals("1", calculator.convertBase2toBase10("0001", 4, false));
        assertEquals("0", calculator.convertBase2toBase10("0000", 4, false));
        //8 bit unsigned
        assertEquals("137", calculator.convertBase2toBase10("10001001", 8, false));
        assertEquals("1", calculator.convertBase2toBase10("00000001", 8, false));
        assertEquals("0", calculator.convertBase2toBase10("00000000", 8, false));
        //8 bit signed
        assertEquals("-9", calculator.convertBase2toBase10("11110111", 8, true));
        assertEquals("1", calculator.convertBase2toBase10("00000001", 8, true));
        assertEquals("0", calculator.convertBase2toBase10("00000000", 8, true));
        //16 But unsigned
        assertEquals("32777", calculator.convertBase2toBase10("1000000000001001", 16, false));
        assertEquals("1", calculator.convertBase2toBase10("0000000000000001", 16, false));
        assertEquals("0", calculator.convertBase2toBase10("0000000000000000", 16, false));
        //16 bit signed
        assertEquals("-7", calculator.convertBase2toBase10("1111111111111001", 16, true));
        assertEquals("1", calculator.convertBase2toBase10("0000000000000001", 16, true));
        assertEquals("0", calculator.convertBase2toBase10("0000000000000000", 16, true));
        //32 bit signed
        assertEquals("-7", calculator.convertBase2toBase10("11111111111111111111111111111001", 32, true));
        assertEquals("1", calculator.convertBase2toBase10("0000000000000001", 32, true));
        assertEquals("0", calculator.convertBase2toBase10("0000000000000000", 32, true));
    }

    @Test
    public void base2to16_isCorrect() throws Exception {
        //4 bit unsigned
        //assertEquals("f", calculator.convertBase2toBase16("1111", 4, false));
        //assertEquals("0001", calculator.convertBase2toBase16("01", 4, false));
        //assertEquals("0", calculator.convertBase2toBase16("0", 4, false));
        //8 bit unsigned
        //assertEquals("255", calculator.convertBase2toBase16("377", 8, false));
        //assertEquals("1", calculator.convertBase2toBase16("01", 8, false));
        //assertEquals("0", calculator.convertBase2toBase16("0", 8, false));
        //8 bit signed
        //assertEquals("-9", calculator.convertBase2toBase16("11110111", 8, true));
        //assertEquals("1", calculator.convertBase2toBase16("01", 8, true));
        //assertEquals("0", calculator.convertBase2toBase16("0", 8, true));
        //16 But unsigned
        //assertEquals("65535", calculator.convertBase2toBase16("177777", 16, false));
        //assertEquals("1", calculator.convertBase2toBase16("01", 16, false));
        //assertEquals("0", calculator.convertBase2toBase16("0", 16, false));
        //16 bit signed
        //assertEquals("-7", calculator.convertBase2toBase16("1111111111111001", 16, true));
        //assertEquals("1", calculator.convertBase2toBase16("01", 16, true));
        //assertEquals("0", calculator.convertBase2toBase16("0", 16, true));
        //32 bit signed
        //assertEquals("-7", calculator.convertBase2toBase16("11111111111111111111111111111001", 32, true));
        //assertEquals("1", calculator.convertBase2toBase16("01", 32, true));
        //assertEquals("0", calculator.convertBase2toBase16("0", 32, true));
    }

    //Probably good?
    @Test
    public void base8to2_isCorrect() throws Exception {
        //4 bit unsigned
        //assertEquals("1001", calculator.convertBase8toBase2("11", 4, false));
        //assertEquals("1", calculator.convertBase8toBase2("01", 4, false));
        //assertEquals("0", calculator.convertBase8toBase2("0", 4, false));
        //8 bit unsigned
        //assertEquals("10001001", calculator.convertBase8toBase2("211", 8, false));
        //assertEquals("00000001", calculator.convertBase8toBase2("01", 8, false));
        //assertEquals("0", calculator.convertBase8toBase2("0", 8, false));
        //8 bit signed
        //assertEquals("10001001", calculator.convertBase8toBase2("611", 8, true));
        //assertEquals("1", calculator.convertBase8toBase2("01", 8, true));
        //assertEquals("0", calculator.convertBase8toBase2("0", 8, true));
        //16 But unsigned
        //assertEquals("1111111111111001", calculator.convertBase8toBase2("177771", 16, false));
        //assertEquals("1", calculator.convertBase8toBase2("01", 16, false));
        //assertEquals("0", calculator.convertBase8toBase2("0", 16, false));
        //16 bit signed
        //assertEquals("1111111111111001", calculator.convertBase8toBase2("177771", 16, true));
        //assertEquals("1", calculator.convertBase8toBase2("01", 16, true));
        //assertEquals("0", calculator.convertBase8toBase2("0", 16, true));
        //32 bit signed
        //assertEquals("11111111111111111111111111111001", calculator.convertBase8toBase2("77777777771", 32, true));
        //assertEquals("1", calculator.convertBase8toBase2("01", 32, true));
        //assertEquals("0", calculator.convertBase8toBase2("0", 32, true));
    }

    @Test
    public void base8to10_isCorrect() throws Exception {
        //4 bit unsigned
        //assertEquals("15", calculator.convertBase8toBase10("17", 4, false));
        assertEquals("1", calculator.convertBase8toBase10("01", 4, false));
        assertEquals("0", calculator.convertBase8toBase10("0", 4, false));
        //8 bit unsigned
        //assertEquals("255", calculator.convertBase8toBase10("377", 8, false));
        assertEquals("1", calculator.convertBase8toBase10("01", 8, false));
        assertEquals("0", calculator.convertBase8toBase10("0", 8, false));
        //8 bit signed
        //assertEquals("-9", calculator.convertBase2toBase10("11110111", 8, true));
        assertEquals("1", calculator.convertBase8toBase10("01", 8, true));
        assertEquals("0", calculator.convertBase8toBase10("0", 8, true));
        //16 But unsigned
       // assertEquals("65535", calculator.convertBase8toBase10("177777", 16, false));
        assertEquals("1", calculator.convertBase8toBase10("01", 16, false));
        assertEquals("0", calculator.convertBase8toBase10("0", 16, false));
        //16 bit signed
       // assertEquals("-7", calculator.convertBase8toBase10("1111111111111001", 16, true));
        assertEquals("1", calculator.convertBase8toBase10("01", 16, true));
        assertEquals("0", calculator.convertBase8toBase10("0", 16, true));
        //32 bit signed
       // assertEquals("-7", calculator.convertBase8toBase10("11111111111111111111111111111001", 32, true));
        assertEquals("1", calculator.convertBase8toBase10("01", 32, true));
        assertEquals("0", calculator.convertBase8toBase10("0", 32, true));
    }

    @Test
    public void base8to16_isCorrect() throws Exception {
        //4 bit unsigned
        //assertEquals("15", calculator.convertBase8toBase16("17", 4, false));
        //assertEquals("1", calculator.convertBase8toBase16("01", 4, false));
        //assertEquals("0", calculator.convertBase8toBase16("0", 4, false));
        //8 bit unsigned
        //assertEquals("255", calculator.convertBase8toBase16("377", 8, false));
        //assertEquals("1", calculator.convertBase8toBase16("01", 8, false));
        //assertEquals("0", calculator.convertBase8toBase16("0", 8, false));
        //8 bit signed
        //assertEquals("-9", calculator.convertBase8toBase16("11110111", 8, true));
        //assertEquals("1", calculator.convertBase8toBase16("01", 8, true));
        //assertEquals("0", calculator.convertBase8toBase16("0", 8, true));
        //16 But unsigned
        //assertEquals("65535", calculator.convertBase8toBase16("177777", 16, false));
        //assertEquals("1", calculator.convertBase8toBase16("01", 16, false));
        //assertEquals("0", calculator.convertBase8toBase16("0", 16, false));
        //16 bit signed
        //assertEquals("-7", calculator.convertBase8toBase16("1111111111111001", 16, true));
        //assertEquals("1", calculator.convertBase8toBase16("01", 16, true));
        //assertEquals("0", calculator.convertBase8toBase16("0", 16, true));
        //32 bit signed
        //assertEquals("-7", calculator.convertBase8toBase16("11111111111111111111111111111001", 32, true));
        //assertEquals("1", calculator.convertBase8toBase16("01", 32, true));
        //assertEquals("0", calculator.convertBase8toBase16("0", 32, true));
    }

    @Test
    public void base10to2_isCorrect() throws Exception {
        //4 bit unsigned
        //assertEquals("1001", calculator.convertBase10toBase2("9", 4, false));
        //assertEquals("1", calculator.convertBase10toBase2("01", 4, false));
        //assertEquals("0", calculator.convertBase10toBase2("0", 4, false));
        //8 bit unsigned
        //assertEquals("1001001", calculator.convertBase10toBase2("137", 8, false));
        //assertEquals("1", calculator.convertBase10toBase2("01", 8, false));
        //assertEquals("0", calculator.convertBase10toBase2("0", 8, false));
        //8 bit signed
        //assertEquals("11110111", calculator.convertBase10toBase2("-9", 8, true));
        //assertEquals("1", calculator.convertBase10toBase2("01", 8, true));
        //assertEquals("0", calculator.convertBase10toBase2("0", 8, true));
        //16 But unsigned
        //assertEquals("1000000000001001", calculator.convertBase10toBase2("32777", 16, false));
        //assertEquals("1", calculator.convertBase10toBase2("01", 16, false));
        //assertEquals("0", calculator.convertBase10toBase2("0", 16, false));
        //16 bit signed
        //assertEquals("1111111111111001", calculator.convertBase10toBase2("-7", 16, true));
        //assertEquals("1", calculator.convertBase10toBase2("01", 16, true));
        //assertEquals("0", calculator.convertBase10toBase2("0", 16, true));
        //32 bit signed
        //assertEquals("11111111111111111111111111111001", calculator.convertBase10toBase2("-7", 32, true));
        //assertEquals("1", calculator.convertBase10toBase2("01", 32, true));
        //assertEquals("0", calculator.convertBase10toBase2("0", 32, true));
    }

    @Test
    public void base10to8_isCorrect() throws Exception {
        //4 bit unsigned
        //assertEquals("15", calculator.convertBase10toBase8("17", 4, false));
        //assertEquals("1", calculator.convertBase10toBase8("01", 4, false));
        //assertEquals("0", calculator.convertBase10toBase8("0", 4, false));
        //8 bit unsigned
        //assertEquals("255", calculator.convertBase10toBase8("377", 8, false));
        //assertEquals("1", calculator.convertBase10toBase8("01", 8, false));
        //assertEquals("0", calculator.convertBase10toBase8("0", 8, false));
        //8 bit signed
        //assertEquals("-9", calculator.convertBase10toBase8("11110111", 8, true));
        //assertEquals("1", calculator.convertBase10toBase8("01", 8, true));
        //assertEquals("0", calculator.convertBase10toBase8("0", 8, true));
        //16 But unsigned
        //assertEquals("65535", calculator.convertBase10toBase8("177777", 16, false));
        //assertEquals("1", calculator.convertBase10toBase8("01", 16, false));
        //assertEquals("0", calculator.convertBase10toBase8("0", 16, false));
        //16 bit signed
        //assertEquals("-7", calculator.convertBase10toBase8("1111111111111001", 16, true));
        //assertEquals("1", calculator.convertBase10toBase8("01", 16, true));
        //assertEquals("0", calculator.convertBase10toBase8("0", 16, true));
        //32 bit signed
        //assertEquals("-7", calculator.convertBase10toBase8("11111111111111111111111111111001", 32, true));
        //assertEquals("1", calculator.convertBase10toBase8("01", 32, true));
        //assertEquals("0", calculator.convertBase10toBase8("0", 32, true));
    }

    @Test
    public void base10to16_isCorrect() throws Exception {
        //4 bit unsigned
        //assertEquals("15", calculator.convertBase10toBase16("17", 4, false));
        //assertEquals("1", calculator.convertBase10toBase16("01", 4, false));
        //assertEquals("0", calculator.convertBase10toBase16("0", 4, false));
        //8 bit unsigned
        //assertEquals("255", calculator.convertBase10toBase16("377", 8, false));
        //assertEquals("1", calculator.convertBase10toBase16("01", 8, false));
        //assertEquals("0", calculator.convertBase10toBase16("0", 8, false));
        //8 bit signed
        //assertEquals("-9", calculator.convertBase10toBase16("11110111", 8, true));
        //assertEquals("1", calculator.convertBase10toBase16("01", 8, true));
        //assertEquals("0", calculator.convertBase10toBase16("0", 8, true));
        //16 But unsigned
        //assertEquals("65535", calculator.convertBase10toBase16("177777", 16, false));
        //assertEquals("1", calculator.convertBase10toBase16("01", 16, false));
        //assertEquals("0", calculator.convertBase10toBase16("0", 16, false));
        //16 bit signed
        //assertEquals("-7", calculator.convertBase10toBase16("1111111111111001", 16, true));
        //assertEquals("1", calculator.convertBase10toBase16("01", 16, true));
        //assertEquals("0", calculator.convertBase10toBase16("0", 16, true));
        //32 bit signed
        //assertEquals("-7", calculator.convertBase10toBase16("11111111111111111111111111111001", 32, true));
        //assertEquals("1", calculator.convertBase10toBase16("01", 32, true));
        //assertEquals("0", calculator.convertBase10toBase16("0", 32, true));
    }

    @Test
    public void base16to2_isCorrect() throws Exception {
        //4 bit unsigned
        //assertEquals("15", calculator.convertBase16toBase2("17", 4, false));
        //assertEquals("1", calculator.convertBase16toBase2("01", 4, false));
        //assertEquals("0", calculator.convertBase16toBase2("0", 4, false));
        //8 bit unsigned
        //assertEquals("255", calculator.convertBase16toBase2("377", 8, false));
        //assertEquals("1", calculator.convertBase16toBase2("01", 8, false));
        //assertEquals("0", calculator.convertBase16toBase2("0", 8, false));
        //8 bit signed
        //assertEquals("-9", calculator.convertBase16toBase2("11110111", 8, true));
        //assertEquals("1", calculator.convertBase16toBase2("01", 8, true));
        //assertEquals("0", calculator.convertBase16toBase2("0", 8, true));
        //16 But unsigned
        //assertEquals("65535", calculator.convertBase16toBase2("177777", 16, false));
        //assertEquals("1", calculator.convertBase16toBase2("01", 16, false));
        //assertEquals("0", calculator.convertBase16toBase2("0", 16, false));
        //16 bit signed
        //assertEquals("-7", calculator.convertBase16toBase2("1111111111111001", 16, true));
        //assertEquals("1", calculator.convertBase16toBase2("01", 16, true));
        //assertEquals("0", calculator.convertBase16toBase2("0", 16, true));
        //32 bit signed
        //assertEquals("-7", calculator.convertBase16toBase2("11111111111111111111111111111001", 32, true));
        //assertEquals("1", calculator.convertBase16toBase2("01", 32, true));
        //assertEquals("0", calculator.convertBase16toBase2("0", 32, true));
    }

    @Test
    public void base16to8_isCorrect() throws Exception {
        //4 bit unsigned
        //assertEquals("15", calculator.convertBase16toBase8("17", 4, false));
        //assertEquals("1", calculator.convertBase16toBase8("01", 4, false));
        //assertEquals("0", calculator.convertBase16toBase8("0", 4, false));
        //8 bit unsigned
        //assertEquals("255", calculator.convertBase16toBase8("377", 8, false));
        //assertEquals("1", calculator.convertBase16toBase8("01", 8, false));
        //assertEquals("0", calculator.convertBase16toBase8("0", 8, false));
        //8 bit signed
        //assertEquals("-9", calculator.convertBase16toBase8("11110111", 8, true));
        //assertEquals("1", calculator.convertBase16toBase8("01", 8, true));
        //assertEquals("0", calculator.convertBase16toBase8("0", 8, true));
        //16 But unsigned
        //assertEquals("65535", calculator.convertBase16toBase8("177777", 16, false));
        //assertEquals("1", calculator.convertBase16toBase8("01", 16, false));
        //assertEquals("0", calculator.convertBase16toBase8("0", 16, false));
        //16 bit signed
        //assertEquals("-7", calculator.convertBase16toBase8("1111111111111001", 16, true));
        //assertEquals("1", calculator.convertBase16toBase8("01", 16, true));
        //assertEquals("0", calculator.convertBase16toBase8("0", 16, true));
        //32 bit signed
        //assertEquals("-7", calculator.convertBase16toBase8("11111111111111111111111111111001", 32, true));
        //assertEquals("1", calculator.convertBase16toBase8("01", 32, true));
        //assertEquals("0", calculator.convertBase16toBase8("0", 32, true));
    }

    @Test
    public void base16to10_isCorrect() throws Exception {
        //4 bit unsigned
        //assertEquals("9", calculator.convertBase8toBase10("1001", 4, false));
        assertEquals("1", calculator.convertBase16toBase10("1", 4, false));
        assertEquals("0", calculator.convertBase16toBase10("0", 4, false));
        //8 bit unsigned
        //assertEquals("137", calculator.convertBase8toBase10("10001001", 8, false));
        assertEquals("1", calculator.convertBase16toBase10("1", 8, false));
        assertEquals("0", calculator.convertBase16toBase10("0", 8, false));
        //8 bit signed
        //assertEquals("-9", calculator.convertBase2toBase10("11110111", 8, true));
        assertEquals("1", calculator.convertBase16toBase10("1", 8, true));
        assertEquals("0", calculator.convertBase16toBase10("0", 8, true));
        //16 But unsigned
        // assertEquals("32777", calculator.convertBase8toBase10("1000000000001001", 16, false));
        assertEquals("1", calculator.convertBase16toBase10("1", 16, false));
        assertEquals("0", calculator.convertBase16toBase10("0", 16, false));
        //16 bit signed
        // assertEquals("-7", calculator.convertBase8toBase10("1111111111111001", 16, true));
        assertEquals("1", calculator.convertBase16toBase10("1", 16, true));
        assertEquals("0", calculator.convertBase16toBase10("0", 16, true));
        //32 bit signed
        // assertEquals("-7", calculator.convertBase8toBase10("11111111111111111111111111111001", 32, true));
        assertEquals("1", calculator.convertBase16toBase10("1", 32, true));
        assertEquals("0", calculator.convertBase16toBase10("0", 32, true));
    }

    @Test
    public void binaryAddition_isCorrect() throws Exception {

    }

}
