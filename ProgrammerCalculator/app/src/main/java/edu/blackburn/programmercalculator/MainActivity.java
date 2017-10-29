package edu.blackburn.programmercalculator;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Model calculatorModel;
    int bitPrecision;
    Boolean signed;
    String inputMode;
    String operationSelected;
    int previousInput;

    TextView tvDEC; // the textview that displays decimal values
    TextView tvHEX; // the textview that displays hexadecimal values
    TextView tvOCT; // the textview that displays octal values
    TextView tvBIN; // the textview that displays binary values

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize our TextView GUI objects
        tvDEC = (TextView) findViewById((R.id.textViewDEC));
        tvHEX = (TextView) findViewById((R.id.textViewHEX));
        tvOCT = (TextView) findViewById((R.id.textViewOCT));
        tvBIN = (TextView) findViewById((R.id.textViewBIN));

        // initialize our calculator Model object
        calculatorModel = new Model();

        // set default values for our global Controller variables
        inputMode = getString(R.string.DEC);
        signed = true;
        bitPrecision = 64;
        operationSelected = getString(R.string.NOT);
        previousInput = 0;

    }//end onCreate method

    public void onPress0(View view) {
        appendButtonInput("0");
    }//end onPress0 method

    public void onPress1(View view) {
        appendButtonInput("1");
    }//end onPress1 method

    public void onPress2(View view) {
        appendButtonInput("2");
    }//end onPress2 method

    public void onPress3(View view) {
        appendButtonInput("3");
    }//end onPress3 method

    public void onPress4(View view) {
        appendButtonInput("4");
    }//end onPress4 method

    public void onPress5(View view) {
        appendButtonInput("5");
    }//end onPress5 method

    public void onPress6(View view) {
        appendButtonInput("6");
    }//end onPress6 method

    public void onPress7(View view) {
        appendButtonInput("7");
    }//end onPress7 method

    public void onPress8(View view) {
        appendButtonInput("8");
    }//end onPress8 method

    public void onPress9(View view) {
        appendButtonInput("9");
    }//end onPress9 method

    public void onPressA(View view) {
        appendButtonInput("A");
    }//end onPressA method

    public void onPressB(View view) {
        appendButtonInput("B");
    }//end onPressB method

    public void onPressC(View view) {
        appendButtonInput("C");
    }//end onPressC method

    public void onPressD(View view) {
        appendButtonInput("D");
    }//end onPressD method

    public void onPressE(View view) {
        appendButtonInput("E");
    }//end onPressE method

    public void onPressF(View view) {
        appendButtonInput("F");
    }//end onPressF method

    private void appendButtonInput(String buttonValue) {
        String legalBinInputs = "01";
        String legalOctInputs = "01234567";
        String legalDecInputs = "0123456789";
        String legalHexInputs = "0123456789ABCDEF";

        if (inputMode == getString(R.string.BIN) && legalBinInputs.contains(buttonValue)) {
            tvBIN.setText(tvBIN.getText().toString() + buttonValue);
            updateTextViews(); // update TextViews only on legal button presses

        } else if (inputMode == getString(R.string.OCT) && legalOctInputs.contains(buttonValue)) {
            tvOCT.setText(tvBIN.getText().toString() + buttonValue);
            updateTextViews(); // update TextViews only on legal button presses

        } else if (inputMode == getString(R.string.DEC) && legalDecInputs.contains(buttonValue)) {
            tvDEC.setText(tvDEC.getText().toString() + buttonValue);
            updateTextViews(); // update TextViews only on legal button presses

        } else if (inputMode == getString(R.string.HEX) && legalHexInputs.contains(buttonValue)) {
            tvHEX.setText(tvHEX.getText().toString() + buttonValue);
            updateTextViews(); // update TextViews only on legal button presses

        }
    }//end appendButtonInput method

    public void onPressDec(View view) {
        inputMode = getString(R.string.DEC);
    }//end onPressDec method

    public void onPressHex(View view) {
        inputMode = getString(R.string.HEX);
    }//end onPressHex method

    public void onPressOct(View view) {
        inputMode = getString(R.string.OCT);
    }//end onPressOct method

    public void onPressBin(View view) {
        inputMode = getString(R.string.BIN);
    }//end onPressBin method

    public void onPressAnd(View view) {
        operationSelected = getString(R.string.AND);
        previousInput = Integer.parseInt(tvDEC.getText().toString());
    }//end onPressAnd method

    public void onPressNand(View view) {
        operationSelected = getString(R.string.NAND);
        previousInput = Integer.parseInt(tvDEC.getText().toString());
    }//end onPressNand method

    public void onPressOr(View view) {
        operationSelected = getString(R.string.OR);
        previousInput = Integer.parseInt(tvDEC.getText().toString());
    }//end onPressOr method

    public void onPressNor(View view) {
        operationSelected = getString(R.string.NOR);
        previousInput = Integer.parseInt(tvDEC.getText().toString());
    }//end onPressNor method

    public void onPressXor(View view) {
        operationSelected = getString(R.string.XOR);
        previousInput = Integer.parseInt(tvDEC.getText().toString());
    }//end onPressXor method

    public void onPressXnor(View view) {
        operationSelected = getString(R.string.XNOR);
        previousInput = Integer.parseInt(tvDEC.getText().toString());
    }//end onPressXnor method

    public void onPressNot(View view) {
        operationSelected = getString(R.string.NOT);
        previousInput = Integer.parseInt(tvDEC.getText().toString());
    }//end onPressNot method

    public void onPressMod(View view) {
        operationSelected = getString(R.string.MOD);
        previousInput = Integer.parseInt(tvDEC.getText().toString());
    }//end onPressMod method

    public void onPressDivide(View view) {
        operationSelected = getString(R.string.div);
        previousInput = Integer.parseInt(tvDEC.getText().toString());
    }//end onPressDivide method

    public void onPressMultiply(View view) {
        operationSelected = getString(R.string.mul);
        previousInput = Integer.parseInt(tvDEC.getText().toString());
    }//end onPressMultiply method

    public void onPressAdd(View view) {
        operationSelected = getString(R.string.add);
        previousInput = Integer.parseInt(tvDEC.getText().toString());
    }//end onPressAdd method method

    public void onPressSubtract(View view) {
        operationSelected = getString(R.string.sub);
        previousInput = Integer.parseInt(tvDEC.getText().toString());
    }//end onPressSubtract method

    public void onPressEqual(View view) {
        // toast code starts here
        Context context = getApplicationContext();
        CharSequence text = "Tim doesn't know how to use Android Studio!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        // toast code ends here

        String input1 = String.valueOf(previousInput);
        String input2 = tvDEC.getText().toString();
        String result = "";

        if (operationSelected == getString(R.string.NOT)) {
            result = calculatorModel.not(input2);
        } else if (operationSelected == getString(R.string.mod)) {
            //result = calculatorModel.mod(input1, input2);
        } else if (operationSelected == getString(R.string.AND)) {
            //result = calculatorModel.and(input1, input2);
        } else if (operationSelected == getString(R.string.OR)) {
            //result = calculatorModel.or(input1, input2);
        } else if (operationSelected == getString(R.string.XOR)) {
            //result = calculatorModel.xor(input1, input2);
        } else if (operationSelected == getString(R.string.NAND)) {
            //result = calculatorModel.nand(input1, input2);
        } else if (operationSelected == getString(R.string.NOR)) {
            //result = calculatorModel.nor(input1, input2);
        } else if (operationSelected == getString(R.string.XNOR)) {
            //result = calculatorModel.xnor(input1, input2);
        } else if (operationSelected == getString(R.string.add)) {
            //result = calculatorModel.add(input1, input2);
        } else if (operationSelected == getString(R.string.sub)) {
            //result = calculatorModel.sub(input1, input2);
        } else if (operationSelected == getString(R.string.mul)) {
            //result = calculatorModel.mul(input1, input2);
        } else if (operationSelected == getString(R.string.div)) {
            //result = calculatorModel.div(input1, input2);
        } else {
            // some unexpected operation was set
            result = "";
        }

        String previousMode = inputMode; // store current inputMode
        inputMode = getString(R.string.DEC); // set to DEC temporarily
        tvDEC.setText(result); // update tvDEC with result
        updateTextViews(); // update all other TextViews
        inputMode = previousMode; // return to the original inputMode
    }//end onPressEqual method

    public void onPressAc(View view) {
        operationSelected = getString(R.string.NOT);
        previousInput = 0;
        tvBIN.setText("");
        tvOCT.setText("");
        tvDEC.setText("");
        tvHEX.setText("");
    }//end onPressAC method

    public void onPressDel(View view) {
        String buffer = "";

        if (inputMode == getString(R.string.DEC)) {
            buffer = tvDEC.getText().toString();
            buffer = buffer.substring(0, buffer.length() - 1);
            tvDEC.setText(buffer);
        } else if (inputMode == getString(R.string.BIN)) {
            buffer = tvBIN.getText().toString();
            buffer = buffer.substring(0, buffer.length() - 1);
            tvBIN.setText(buffer);
        } else if (inputMode == getString(R.string.OCT)) {
            buffer = tvOCT.getText().toString();
            buffer = buffer.substring(0, buffer.length() - 1);
            tvOCT.setText(buffer);
        } else if (inputMode == getString(R.string.HEX)) {
            buffer = tvHEX.getText().toString();
            buffer = buffer.substring(0, buffer.length() - 1);
            tvHEX.setText(buffer);
        }

        updateTextViews();
    }//end onPressDel method

    public void updateTextViews() {
        String buffer = "";

        if (inputMode == getString(R.string.DEC)) {
            buffer = tvDEC.getText().toString();

            tvBIN.setText(calculatorModel.convertBase10toBase2(buffer, bitPrecision, signed));
            tvOCT.setText(calculatorModel.convertBase10toBase8(buffer, bitPrecision, signed));
            tvHEX.setText(calculatorModel.convertBase10toBase16(buffer, bitPrecision, signed));

        } else if (inputMode == getString(R.string.BIN)) {
            buffer = tvBIN.getText().toString();

            tvOCT.setText(calculatorModel.convertBase2toBase8(buffer, bitPrecision, signed));
            tvDEC.setText(calculatorModel.convertBase2toBase10(buffer, bitPrecision, signed));
            tvHEX.setText(calculatorModel.convertBase2toBase16(buffer, bitPrecision, signed));

        } else if (inputMode == getString(R.string.OCT)) {
            buffer = tvOCT.getText().toString();

            tvBIN.setText(calculatorModel.convertBase8toBase2(buffer, bitPrecision, signed));
            tvDEC.setText(calculatorModel.convertBase8toBase10(buffer, bitPrecision, signed));
            tvHEX.setText(calculatorModel.convertBase8toBase16(buffer, bitPrecision, signed));

        } else if (inputMode == getString(R.string.HEX)) {
            buffer = tvHEX.getText().toString();

            tvBIN.setText(calculatorModel.convertBase16toBase2(buffer, bitPrecision, signed));
            tvOCT.setText(calculatorModel.convertBase16toBase8(buffer, bitPrecision, signed));
            tvDEC.setText(calculatorModel.convertBase16toBase10(buffer, bitPrecision, signed));
        }
    }//end updateTextViews method

}//end MainActivity class
