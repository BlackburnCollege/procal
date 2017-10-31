package edu.blackburn.programmercalculator;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RelativeLayout;
import android.view.Display;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Model calculatorModel;
    private int bitPrecision;
    private Boolean signed;
    private String inputMode;
    private String operationSelected;
    private int previousDecInput;
    private String previousBinInput;

    private TextView tvDEC; // the textview that displays decimal values
    private TextView tvHEX; // the textview that displays hexadecimal values
    private TextView tvOCT; // the textview that displays octal values
    private TextView tvBIN; // the textview that displays binary values

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // leave everything above this line alone in onCreate method

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
        previousDecInput = 0;
        previousBinInput = "";

    }//end onCreate method

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.mode_8bitsigned) {
            // update global Controller variables
            signed = true;
            bitPrecision = 8;
        } else if (id == R.id.mode_8bitunsigned) {
            // update global Controller variables
            signed = false;
            bitPrecision = 8;
        } else if (id == R.id.mode_16bitsigned) {
            // update global Controller variables
            signed = true;
            bitPrecision = 16;
        } else if (id == R.id.mode_16bitunsigned) {
            // update global Controller variables
            signed = false;
            bitPrecision = 16;
        } else if (id == R.id.mode_32bitsigned) {
            // update global Controller variables
            signed = true;
            bitPrecision = 32;
        } else if (id == R.id.mode_32bitunsigned) {
            // update global Controller variables
            signed = true;
            bitPrecision = 32;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public String truncZero(String string) {
        return string.replaceFirst("^0+(?!$)", "");
    }

    public void onPress0(View view) {
        appendButtonInput(getString(R.string.zero));
    }//end onPress0 method

    public void onPress1(View view) {
        appendButtonInput(getString(R.string.one));
    }//end onPress1 method

    public void onPress2(View view) {
        appendButtonInput(getString(R.string.two));
    }//end onPress2 method

    public void onPress3(View view) {
        appendButtonInput(getString(R.string.three));
    }//end onPress3 method

    public void onPress4(View view) {
        appendButtonInput(getString(R.string.four));
    }//end onPress4 method

    public void onPress5(View view) {
        appendButtonInput(getString(R.string.five));
    }//end onPress5 method

    public void onPress6(View view) {
        appendButtonInput(getString(R.string.six));
    }//end onPress6 method

    public void onPress7(View view) {
        appendButtonInput(getString(R.string.seven));
    }//end onPress7 method

    public void onPress8(View view) {
        appendButtonInput(getString(R.string.eight));
    }//end onPress8 method

    public void onPress9(View view) {
        appendButtonInput(getString(R.string.nine));
    }//end onPress9 method

    public void onPressA(View view) {
        appendButtonInput(getString(R.string.a));
    }//end onPressA method

    public void onPressB(View view) {
        appendButtonInput(getString(R.string.b));
    }//end onPressB method

    public void onPressC(View view) {
        appendButtonInput(getString(R.string.c));
    }//end onPressC method

    public void onPressD(View view) {
        appendButtonInput(getString(R.string.d));
    }//end onPressD method

    public void onPressE(View view) {
        appendButtonInput(getString(R.string.e));
    }//end onPressE method

    public void onPressF(View view) {
        appendButtonInput(getString(R.string.f));
    }//end onPressF method

    private void appendButtonInput(String buttonValue) {
        String legalBinInputs = "01";
        String legalOctInputs = "01234567";
        String legalDecInputs = "0123456789";
        String legalHexInputs = "0123456789ABCDEF";
        String buffer;

        if (inputMode.equalsIgnoreCase(getString(R.string.BIN)) && legalBinInputs.contains(buttonValue)) {
            buffer = tvBIN.getText().toString() + buttonValue;
            tvBIN.setText(buffer);
            updateTextViews(); // update TextViews only on legal button presses

        } else if (inputMode.equalsIgnoreCase(getString(R.string.OCT)) && legalOctInputs.contains(buttonValue)) {
            buffer = tvOCT.getText().toString() + buttonValue;
            tvOCT.setText(buffer);
            updateTextViews(); // update TextViews only on legal button presses

        } else if (inputMode.equalsIgnoreCase(getString(R.string.DEC)) && legalDecInputs.contains(buttonValue)) {
            buffer = tvDEC.getText().toString() + buttonValue;
            tvDEC.setText(buffer);
            updateTextViews(); // update TextViews only on legal button presses

        } else if (inputMode.equalsIgnoreCase(getString(R.string.HEX)) && legalHexInputs.contains(buttonValue)) {
            buffer = tvHEX.getText().toString() + buttonValue;
            tvHEX.setText(buffer);
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
        previousDecInput = Integer.parseInt(tvDEC.getText().toString());
        previousBinInput = tvBIN.getText().toString();
        clearTextViews();
    }//end onPressAnd method

    public void onPressNand(View view) {
        operationSelected = getString(R.string.NAND);
        previousDecInput = Integer.parseInt(tvDEC.getText().toString());
        previousBinInput = tvBIN.getText().toString();
        clearTextViews();
    }//end onPressNand method

    public void onPressOr(View view) {
        operationSelected = getString(R.string.OR);
        previousDecInput = Integer.parseInt(tvDEC.getText().toString());
        previousBinInput = tvBIN.getText().toString();
        clearTextViews();
    }//end onPressOr method

    public void onPressNor(View view) {
        operationSelected = getString(R.string.NOR);
        previousDecInput = Integer.parseInt(tvDEC.getText().toString());
        previousBinInput = tvBIN.getText().toString();
        clearTextViews();
    }//end onPressNor method

    public void onPressXor(View view) {
        operationSelected = getString(R.string.XOR);
        previousDecInput = Integer.parseInt(tvDEC.getText().toString());
        previousBinInput = tvBIN.getText().toString();
        clearTextViews();
    }//end onPressXor method

    public void onPressXnor(View view) {
        operationSelected = getString(R.string.XNOR);
        previousDecInput = Integer.parseInt(tvDEC.getText().toString());
        previousBinInput = tvBIN.getText().toString();
        clearTextViews();
    }//end onPressXnor method

    public void onPressNot(View view) {
        operationSelected = getString(R.string.NOT);
        previousDecInput = Integer.parseInt(tvDEC.getText().toString());
        previousBinInput = tvBIN.getText().toString();
        clearTextViews();
        onPressEqual(view);
    }//end onPressNot method

    public void onPressMod(View view) {
        operationSelected = getString(R.string.mod);
        previousDecInput = Integer.parseInt(tvDEC.getText().toString());
        previousBinInput = tvBIN.getText().toString();
        clearTextViews();
    }//end onPressMod method

    public void onPressDivide(View view) {
        operationSelected = getString(R.string.div);
        previousDecInput = Integer.parseInt(tvDEC.getText().toString());
        previousBinInput = tvBIN.getText().toString();
        clearTextViews();
    }//end onPressDivide method

    public void onPressMultiply(View view) {
        operationSelected = getString(R.string.mul);
        previousDecInput = Integer.parseInt(tvDEC.getText().toString());
        previousBinInput = tvBIN.getText().toString();
        clearTextViews();
    }//end onPressMultiply method

    public void onPressAdd(View view) {
        operationSelected = getString(R.string.add);
        previousDecInput = Integer.parseInt(tvDEC.getText().toString());
        previousBinInput = tvBIN.getText().toString();
        clearTextViews();
    }//end onPressAdd method method

    public void onPressSubtract(View view) {
        operationSelected = getString(R.string.sub);
        previousDecInput = Integer.parseInt(tvDEC.getText().toString());
        previousBinInput = tvBIN.getText().toString();
        clearTextViews();
    }//end onPressSubtract method

    public void onPressEqual(View view) {
        // toast code starts here
        Context context = getApplicationContext();
        CharSequence text = "Tim doesn't know how to use Android Studio!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        // toast code ends here

        String input1;
        String input2;
        String binResult = "";
        String decResult = "";

        previousBinInput = String.format("%" + bitPrecision + "s", previousBinInput).replace(' ', '0');

        if (operationSelected.equalsIgnoreCase(getString(R.string.NOT))) {
            binResult = calculatorModel.not(previousBinInput);
            decResult = calculatorModel.convertBase2toBase10(binResult, bitPrecision, signed);

        } else if (operationSelected.equalsIgnoreCase(getString(R.string.AND))) {
            input1 = previousBinInput;
            input2 = tvBIN.getText().toString();
            input2 = String.format("%" + bitPrecision + "s", input2).replace(' ', '0');
            binResult = calculatorModel.and(input1, input2);
            decResult = calculatorModel.convertBase2toBase10(binResult, bitPrecision, signed);

        } else if (operationSelected.equalsIgnoreCase(getString(R.string.OR))) {
            input1 = previousBinInput;
            input2 = tvBIN.getText().toString();
            input2 = String.format("%" + bitPrecision + "s", input2).replace(' ', '0');
            binResult = calculatorModel.or(input1, input2);
            decResult = calculatorModel.convertBase2toBase10(binResult, bitPrecision, signed);


        } else if (operationSelected.equalsIgnoreCase(getString(R.string.XOR))) {
            input1 = previousBinInput;
            input2 = tvBIN.getText().toString();
            input2 = String.format("%" + bitPrecision + "s", input2).replace(' ', '0');
            binResult = calculatorModel.xor(input1, input2);
            decResult = calculatorModel.convertBase2toBase10(binResult, bitPrecision, signed);


        } else if (operationSelected.equalsIgnoreCase(getString(R.string.NAND))) {
            input1 = previousBinInput;
            input2 = tvBIN.getText().toString();
            input2 = String.format("%" + bitPrecision + "s", input2).replace(' ', '0');
            binResult = calculatorModel.nand(input1, input2);
            decResult = calculatorModel.convertBase2toBase10(binResult, bitPrecision, signed);


        } else if (operationSelected.equalsIgnoreCase(getString(R.string.NOR))) {
            input1 = previousBinInput;
            input2 = tvBIN.getText().toString();
            input2 = String.format("%" + bitPrecision + "s", input2).replace(' ', '0');
            binResult = calculatorModel.nor(input1, input2);
            decResult = calculatorModel.convertBase2toBase10(binResult, bitPrecision, signed);

        } else if (operationSelected.equalsIgnoreCase(getString(R.string.XNOR))) {
            input1 = previousBinInput;
            input2 = tvBIN.getText().toString();
            input2 = String.format("%" + bitPrecision + "s", input2).replace(' ', '0');
            binResult = calculatorModel.xnor(input1, input2);
            decResult = calculatorModel.convertBase2toBase10(binResult, bitPrecision, signed);

        } else if (operationSelected.equalsIgnoreCase(getString(R.string.add))) {
            input1 = String.valueOf(previousDecInput);
            input2 = tvDEC.getText().toString();
            decResult = calculatorModel.add(input1, input2);
            binResult = calculatorModel.convertBase10toBase2(decResult, bitPrecision, signed);

        } else if (operationSelected.equalsIgnoreCase(getString(R.string.sub))) {
            input1 = String.valueOf(previousDecInput);
            input2 = tvDEC.getText().toString();
            decResult = calculatorModel.sub(input1, input2);
            binResult = calculatorModel.convertBase10toBase2(decResult, bitPrecision, signed);

        } else if (operationSelected.equalsIgnoreCase(getString(R.string.mul))) {
            input1 = String.valueOf(previousDecInput);
            input2 = tvDEC.getText().toString();
            decResult = calculatorModel.mul(input1, input2);
            binResult = calculatorModel.convertBase10toBase2(decResult, bitPrecision, signed);

        } else if (operationSelected.equalsIgnoreCase(getString(R.string.div))) {
            input1 = String.valueOf(previousDecInput);
            input2 = tvDEC.getText().toString();
            decResult = calculatorModel.div(input1, input2);
            binResult = calculatorModel.convertBase10toBase2(decResult, bitPrecision, signed);

        } else if (operationSelected.equalsIgnoreCase(getString(R.string.mod))) {
            input1 = String.valueOf(previousDecInput);
            input2 = tvDEC.getText().toString();
            decResult = calculatorModel.mod(input1, input2);
            binResult = calculatorModel.convertBase10toBase2(decResult, bitPrecision, signed);

        } else {
            // some unexpected operation was set
            binResult = "";
            decResult = "";
        }

        clearTextViews(); // before updating

        String previousMode = inputMode; // store current inputMode
        inputMode = getString(R.string.BIN);
        tvBIN.setText(truncZero(binResult));
        updateTextViews(); // update all other TextViews
        inputMode = previousMode; // return to the original inputMode

    }//end onPressEqual method

    public void onPressAc(View view) {
        operationSelected = getString(R.string.NOT);
        previousDecInput = 0;
        clearTextViews();
    }//end onPressAC method

    public void clearTextViews() {
        tvBIN.setText("");
        tvOCT.setText("");
        tvDEC.setText("");
        tvHEX.setText("");
    }//end onPressAC method

    public void onPressDel(View view) {
        String buffer;

        if (inputMode.equalsIgnoreCase(getString(R.string.DEC))) {
            buffer = tvDEC.getText().toString();
            buffer = buffer.substring(0, buffer.length() - 1);
            tvDEC.setText(buffer);
        } else if (inputMode.equalsIgnoreCase(getString(R.string.BIN))) {
            buffer = tvBIN.getText().toString();
            buffer = buffer.substring(0, buffer.length() - 1);
            tvBIN.setText(buffer);
        } else if (inputMode.equalsIgnoreCase(getString(R.string.OCT))) {
            buffer = tvOCT.getText().toString();
            buffer = buffer.substring(0, buffer.length() - 1);
            tvOCT.setText(buffer);
        } else if (inputMode.equalsIgnoreCase(getString(R.string.HEX))) {
            buffer = tvHEX.getText().toString();
            buffer = buffer.substring(0, buffer.length() - 1);
            tvHEX.setText(buffer);
        }

        updateTextViews();
    }//end onPressDel method

    public void updateTextViews() {
        String buffer;

        if (inputMode.equalsIgnoreCase(getString(R.string.DEC))) {
            buffer = tvDEC.getText().toString();

            tvBIN.setText(truncZero(calculatorModel.convertBase10toBase2(buffer, bitPrecision, signed)));
            tvOCT.setText(truncZero(calculatorModel.convertBase10toBase8(buffer, bitPrecision, signed)));
            tvHEX.setText(truncZero(calculatorModel.convertBase10toBase16(buffer, bitPrecision, signed)));

        } else if (inputMode.equalsIgnoreCase(getString(R.string.BIN))) {
            buffer = tvBIN.getText().toString();

            tvOCT.setText(truncZero(calculatorModel.convertBase2toBase8(buffer, bitPrecision, signed)));
            tvDEC.setText(truncZero(calculatorModel.convertBase2toBase10(buffer, bitPrecision, signed)));
            tvHEX.setText(truncZero(calculatorModel.convertBase2toBase16(buffer, bitPrecision, signed)));

        } else if (inputMode.equalsIgnoreCase(getString(R.string.OCT))) {
            buffer = tvOCT.getText().toString();

            tvBIN.setText(truncZero(calculatorModel.convertBase8toBase2(buffer, bitPrecision, signed)));
            tvDEC.setText(truncZero(calculatorModel.convertBase8toBase10(buffer, bitPrecision, signed)));
            tvHEX.setText(truncZero(calculatorModel.convertBase8toBase16(buffer, bitPrecision, signed)));

        } else if (inputMode.equalsIgnoreCase(getString(R.string.HEX))) {
            buffer = tvHEX.getText().toString();

            tvBIN.setText(truncZero(calculatorModel.convertBase16toBase2(buffer, bitPrecision, signed)));
            tvOCT.setText(truncZero(calculatorModel.convertBase16toBase8(buffer, bitPrecision, signed)));
            tvDEC.setText(truncZero(calculatorModel.convertBase16toBase10(buffer, bitPrecision, signed)));
        }
    }//end updateTextViews method

}//end MainActivity class
