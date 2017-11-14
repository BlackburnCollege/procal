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
    }

    @Test
    public void xor_isCorrect() throws Exception {

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

    }
    @Test
    public void nor_isCorrect() throws Exception {

    }

    @Test
    public void xnor_isCorrect() throws Exception {

    }

    @Test
    public void nand_isCorrect() throws Exception {

    }

    @Test
    public void base2to8_isCorrect() throws Exception {

    }

    @Test
    public void base2to10_isCorrect() throws Exception {

    }

    @Test
    public void base2to16_isCorrect() throws Exception {

    }

    @Test
    public void base8to2_isCorrect() throws Exception {

    }

    @Test
    public void base8to10_isCorrect() throws Exception {

    }

    @Test
    public void base8to16_isCorrect() throws Exception {

    }

    @Test
    public void base10to2_isCorrect() throws Exception {

    }

    @Test
    public void base10to8_isCorrect() throws Exception {

    }

    @Test
    public void base10to16_isCorrect() throws Exception {

    }

    @Test
    public void base16to2_isCorrect() throws Exception {

    }

    @Test
    public void base16to8_isCorrect() throws Exception {

    }

    @Test
    public void base16to10_isCorrect() throws Exception {

    }

    @Test
    public void binaryAddition_isCorrect() throws Exception {

    }

}
