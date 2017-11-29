package edu.blackburn.programmercalculator;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RelativeLayout;
import android.view.Display;

/**
 * MainActivity Class - Controller
 *
 * @author Dakota Tebbe
 * @author Riley Judd
 * @author Tim Francis
 * @author Arthur LeVan
 * @author Samuel Lomanto
 * @author Drew Hans
 */
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

    /**
     * onCreate Method - initializes our activity
     *
     * @param savedInstanceState
     */
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

        resizeGUI();

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
        bitPrecision = 32;
        operationSelected = null;
        previousDecInput = 0;
        previousBinInput = "";

        updateAppTitle(getString(R.string.signed32bit));
    }//end onCreate method

    /**
     * onBackPressed Method - called when the activity has detected the user's press of the back key
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }//end onBackPressed method

    /**
     * onCreateOptionsMenu Method - initializes the contents of the Activity's standard options menu
     *
     * @param menu
     * @return true on success
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }//end onCreateOptionsMenu

    /**
     * onOptionsItemSelected Method - called whenever an item in your options menu is selected
     *
     * @param item
     * @return
     */
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
    }//end onOptionsItemSelected method

    /**
     * onNavigationItemSelected Method - called whenever an item in our Navigation menu is selected
     *
     * @param item - the MenuItem selected
     * @return true on success
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.mode_4bitunsigned) {
            // update global Controller variables
            signed = false;
            bitPrecision = 4;
            setTitle(getString(R.string.unsigned4bit));
        } else if (id == R.id.mode_8bitsigned) {
            // update global Controller variables
            signed = true;
            bitPrecision = 8;
            setTitle(getString(R.string.signed8bit));
        } else if (id == R.id.mode_8bitunsigned) {
            // update global Controller variables
            signed = false;
            bitPrecision = 8;
            setTitle(getString(R.string.unsigned8bit));
        } else if (id == R.id.mode_16bitsigned) {
            // update global Controller variables
            signed = true;
            bitPrecision = 16;
            setTitle(getString(R.string.signed16bit));
        } else if (id == R.id.mode_16bitunsigned) {
            // update global Controller variables
            signed = false;
            bitPrecision = 16;
            setTitle(getString(R.string.unsigned16bit));
        } else if (id == R.id.mode_32bitsigned) {
            // update global Controller variables
            signed = true;
            bitPrecision = 32;
            setTitle(getString(R.string.signed32bit));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }//end onNavigationItemSelected method

    /**
     * resizeGUI Method - manually scales GUI elements to match device's height and width
     */
    private void resizeGUI() {
        // get the device's height and width in pixels
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int heightPxs = metrics.heightPixels;
        int widthPxs = metrics.widthPixels;

        // manually resize the outter LinearLayout
        LinearLayout linearLayoutContainer = (LinearLayout) findViewById(R.id.linearLayoutContainer);
        LayoutParams linearLayoutParams = linearLayoutContainer.getLayoutParams();
        linearLayoutParams.width = widthPxs;
        linearLayoutParams.height = heightPxs;

        /*
        // manually resize each LinearLayout button row so that each button's height = width (square buttons look better)
        int buttonWidth = widthPxs / 4; //4 buttons per row => each button has widthPxs/4 of width (except for the equal button)

        LinearLayout linearLayoutButtonRow1 = (LinearLayout) findViewById(R.id.linearLayoutButtonRow1);
        LayoutParams linearLayoutBR1Params = linearLayoutButtonRow1.getLayoutParams();
        linearLayoutBR1Params.height = buttonWidth;

        LinearLayout linearLayoutButtonRow2 = (LinearLayout) findViewById(R.id.linearLayoutButtonRow2);
        LayoutParams linearLayoutBR2Params = linearLayoutButtonRow2.getLayoutParams();
        linearLayoutBR2Params.height = buttonWidth;

        LinearLayout linearLayoutButtonRow3 = (LinearLayout) findViewById(R.id.linearLayoutButtonRow3);
        LayoutParams linearLayoutBR3Params = linearLayoutButtonRow3.getLayoutParams();
        linearLayoutBR3Params.height = buttonWidth;

        LinearLayout linearLayoutButtonRow4 = (LinearLayout) findViewById(R.id.linearLayoutButtonRow4);
        LayoutParams linearLayoutBR4Params = linearLayoutButtonRow4.getLayoutParams();
        linearLayoutBR4Params.height = buttonWidth;

        LinearLayout linearLayoutButtonRow5 = (LinearLayout) findViewById(R.id.linearLayoutButtonRow5);
        LayoutParams linearLayoutBR5Params = linearLayoutButtonRow5.getLayoutParams();
        linearLayoutBR5Params.height = buttonWidth;

        LinearLayout linearLayoutButtonRow6 = (LinearLayout) findViewById(R.id.linearLayoutButtonRow6);
        LayoutParams linearLayoutBR6Params = linearLayoutButtonRow6.getLayoutParams();
        linearLayoutBR6Params.height = buttonWidth;

        LinearLayout linearLayoutButtonRow7 = (LinearLayout) findViewById(R.id.linearLayoutButtonRow7);
        LayoutParams linearLayoutBR7Params = linearLayoutButtonRow7.getLayoutParams();
        linearLayoutBR7Params.height = buttonWidth;

        LinearLayout linearLayoutButtonRow8 = (LinearLayout) findViewById(R.id.linearLayoutButtonRow8);
        LayoutParams linearLayoutBR8Params = linearLayoutButtonRow8.getLayoutParams();
        linearLayoutBR8Params.height = buttonWidth;
        */
    }//end resizeGUI method

    /**
     * updateAppTitle Method - updates the title text at the top of our app to reflect mode
     *
     * @param s - the text to display
     */
    private void updateAppTitle(String s) {
        setTitle(s);
    }//end updateAppTitle method

    /**
     * truncZero Method - removes any leading zeros from a string. Written by Dakota Tebbe (credit where credit is due, because I would have never figured this out - Drew)
     *
     * @param string - a string with zeros in it... probably
     * @return the string without any zeros
     */
    public String truncZero(String string) {
        return string.replaceFirst("^0+(?!$)", "");
    }//end truncZero method

    /**
     * onPress0 - called when the user presses the 0 button
     *
     * @param view - View object that gets passed in on call
     */
    public void onPress0(View view) {
        appendButtonInput(getString(R.string.zero));
    }//end onPress0 method

    /**
     * onPress1 - called when the user presses the 1 button
     *
     * @param view - View object that gets passed in on call
     */
    public void onPress1(View view) {
        appendButtonInput(getString(R.string.one));
    }//end onPress1 method

    /**
     * onPress2 - called when the user presses the 2 button
     *
     * @param view - View object that gets passed in on call
     */
    public void onPress2(View view) {
        appendButtonInput(getString(R.string.two));
    }//end onPress2 method

    /**
     * onPress3 - called when the user presses the 3 button
     *
     * @param view - View object that gets passed in on call
     */
    public void onPress3(View view) {
        appendButtonInput(getString(R.string.three));
    }//end onPress3 method

    /**
     * onPress4 - called when the user presses the 4 button
     *
     * @param view - View object that gets passed in on call
     */
    public void onPress4(View view) {
        appendButtonInput(getString(R.string.four));
    }//end onPress4 method

    /**
     * onPress5 - called when the user presses the 5 button
     *
     * @param view - View object that gets passed in on call
     */
    public void onPress5(View view) {
        appendButtonInput(getString(R.string.five));
    }//end onPress5 method

    /**
     * onPress6 - called when the user presses the 6 button
     *
     * @param view - View object that gets passed in on call
     */
    public void onPress6(View view) {
        appendButtonInput(getString(R.string.six));
    }//end onPress6 method

    /**
     * onPress7 - called when the user presses the 7 button
     *
     * @param view - View object that gets passed in on call
     */
    public void onPress7(View view) {
        appendButtonInput(getString(R.string.seven));
    }//end onPress7 method

    /**
     * onPress8 - called when the user presses the 8 button
     *
     * @param view - View object that gets passed in on call
     */
    public void onPress8(View view) {
        appendButtonInput(getString(R.string.eight));
    }//end onPress8 method

    /**
     * onPress9 - called when the user presses the 9 button
     *
     * @param view - View object that gets passed in on call
     */
    public void onPress9(View view) {
        appendButtonInput(getString(R.string.nine));
    }//end onPress9 method

    /**
     * onPressA - called when the user presses the A button
     *
     * @param view - View object that gets passed in on call
     */
    public void onPressA(View view) {
        appendButtonInput(getString(R.string.a));
    }//end onPressA method

    /**
     * onPressB - called when the user presses the B button
     *
     * @param view - View object that gets passed in on call
     */
    public void onPressB(View view) {
        appendButtonInput(getString(R.string.b));
    }//end onPressB method

    /**
     * onPressC - called when the user presses the C button
     *
     * @param view - View object that gets passed in on call
     */
    public void onPressC(View view) {
        appendButtonInput(getString(R.string.c));
    }//end onPressC method

    /**
     * onPressD - called when the user presses the D button
     *
     * @param view - View object that gets passed in on call
     */
    public void onPressD(View view) {
        appendButtonInput(getString(R.string.d));
    }//end onPressD method

    /**
     * onPressE - called when the user presses the E button
     *
     * @param view - View object that gets passed in on call
     */
    public void onPressE(View view) {
        appendButtonInput(getString(R.string.e));
    }//end onPressE method

    /**
     * onPressF - called when the user presses the F button
     *
     * @param view - View object that gets passed in on call
     */
    public void onPressF(View view) {
        appendButtonInput(getString(R.string.f));
    }//end onPressF method

    /**
     * appendButtonInput Method - called by every onPress button method, appends input if legal
     *
     * @param buttonValue - string value of the pressed button
     */
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

    /**
     * onPressDec Method - called when user pressed the DEC TextView
     *
     * @param view - View object that gets passed in on call
     */
    public void onPressDec(View view) {
        inputMode = getString(R.string.DEC);
    }//end onPressDec method

    /**
     * onPressHex Method - called when user pressed the HEX TextView
     *
     * @param view - View object that gets passed in on call
     */
    public void onPressHex(View view) {
        inputMode = getString(R.string.HEX);
    }//end onPressHex method

    /**
     * onPressOct Method - called when user pressed the OCT TextView
     *
     * @param view - View object that gets passed in on call
     */
    public void onPressOct(View view) {
        inputMode = getString(R.string.OCT);
    }//end onPressOct method

    /**
     * onPressBin Method - called when user pressed the BIN TextView
     *
     * @param view - View object that gets passed in on call
     */
    public void onPressBin(View view) {
        inputMode = getString(R.string.BIN);
    }//end onPressBin method

    /**
     * onPressAnd Method - called when the user presses the AND button
     *
     * @param view - View object that gets passed in on call
     */
    public void onPressAnd(View view) {
        operationSelected = getString(R.string.AND);
        setPreviousInput(view);
    }//end onPressAnd method

    /**
     * onPressNand Method - called when the user presses the NAND button
     *
     * @param view - View object that gets passed in on call
     */
    public void onPressNand(View view) {
        operationSelected = getString(R.string.NAND);
        setPreviousInput(view);
    }//end onPressNand method

    /**
     * onPressOr Method - called when the user presses the OR button
     *
     * @param view - View object that gets passed in on call
     */
    public void onPressOr(View view) {
        operationSelected = getString(R.string.OR);
        setPreviousInput(view);
    }//end onPressOr method

    /**
     * onPressNor Method - called when the user presses the NOR button
     *
     * @param view - View object that gets passed in on call
     */
    public void onPressNor(View view) {
        operationSelected = getString(R.string.NOR);
        setPreviousInput(view);
    }//end onPressNor method

    /**
     * onPressXor Method - called when the user presses the XOR button
     *
     * @param view - View object that gets passed in on call
     */
    public void onPressXor(View view) {
        operationSelected = getString(R.string.XOR);
        setPreviousInput(view);
    }//end onPressXor method

    /**
     * onPressXnor Method - called when the user presses the XNOR button
     *
     * @param view - View object that gets passed in on call
     */
    public void onPressXnor(View view) {
        operationSelected = getString(R.string.XNOR);
        setPreviousInput(view);
    }//end onPressXnor method

    /**
     * onPressNot Method - called when the user presses the NOT button
     *
     * @param view - View object that gets passed in on call
     */
    public void onPressNot(View view) {
        operationSelected = getString(R.string.NOT);
        setPreviousInput(view);
        onPressEqual(view);
    }//end onPressNot method

    /**
     * onPressMod Method - called when the user presses the MOD button
     *
     * @param view - View object that gets passed in on call
     */
    public void onPressMod(View view) {
        operationSelected = getString(R.string.mod);
        setPreviousInput(view);
    }//end onPressMod method

    /**
     * onPressDivide Method - called when the user presses the / button
     *
     * @param view - View object that gets passed in on call
     */
    public void onPressDivide(View view) {
        operationSelected = getString(R.string.div);
        setPreviousInput(view);
    }//end onPressDivide method

    /**
     * onPressMultiply Method - called when the user presses the * button
     *
     * @param view - View object that gets passed in on call
     */
    public void onPressMultiply(View view) {
        operationSelected = getString(R.string.mul);
        setPreviousInput(view);
    }//end onPressMultiply method

    /**
     * onPressAdd Method - called when the user presses the + button
     *
     * @param view - View object that gets passed in on call
     */
    public void onPressAdd(View view) {
        operationSelected = getString(R.string.add);
        setPreviousInput(view);
    }//end onPressAdd method method

    /**
     * onPressSubtract Method - called when the user presses the - button
     *
     * @param view - View object that gets passed in on call
     */
    public void onPressSubtract(View view) {
        operationSelected = getString(R.string.sub);
        setPreviousInput(view);
    }//end onPressSubtract method

    /**
     * setPreviousInput Method - called whenever we need to store user input
     *
     * @param view - View object that gets passed in on call
     */
    public void setPreviousInput(View view) {
        try {
            previousDecInput = Integer.parseInt(tvDEC.getText().toString());
            previousBinInput = tvBIN.getText().toString();
        } catch (Exception e) {
            showInputErrToast();
            operationSelected = null;
            previousDecInput = 0;
            previousBinInput = "";
        }
        clearTextViews();
    }//end setPreviousInput method

    /**
     * showInputErrToast Method - called whenever an exception is caught in setPreviousInput
     */
    public void showInputErrToast() {
        Context context = getApplicationContext();
        CharSequence text = "Invalid input. Calculator will now be reset.";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }//end showPreviousInputErrToast method

    /**
     * onPressEqual Method - called when the user presses the = button
     *
     * @param view - View object that gets passed in on call
     */
    public void onPressEqual(View view) {
        String input1;
        String input2;
        String binResult = "";
        String decResult = "";

        previousBinInput = String.format("%" + bitPrecision + "s", previousBinInput).replace(' ', '0');

        if (operationSelected != null) {
            if (operationSelected.equalsIgnoreCase(getString(R.string.NOT))) {
                input1 = previousBinInput;
                input2 = tvBIN.getText().toString();
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
        }

    }//end onPressEqual method

    /**
     * onPressAc Method - called when the user presses the AC button
     *
     * @param view - View object that gets passed in on call
     */
    public void onPressAc(View view) {
        operationSelected = null;
        previousDecInput = 0;
        previousBinInput = "";
        clearTextViews();
        showCalculatorResetToast();
    }//end onPressAC method

    /**
     * showCalculatorResetToast Method - called after onPressAC is called
     */
    public void showCalculatorResetToast() {
        Context context = getApplicationContext();
        CharSequence text = "The calculator has been reset.";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }//end showPreviousInputErrToast method


    /**
     * clearTextViews Method - clears all of our TextViews
     */
    public void clearTextViews() {
        tvBIN.setText("");
        tvOCT.setText("");
        tvDEC.setText("");
        tvHEX.setText("");
    }//end onPressAC method

    /**
     * onPressDel Method - called when the user presses the DEL button
     *
     * @param view - View object that gets passed in on call
     */
    public void onPressDel(View view) {
        try {
            String buffer;

            if (inputMode.equalsIgnoreCase(getString(R.string.DEC)) && tvDEC.getText().toString().length() != 0) {
                buffer = tvDEC.getText().toString();
                buffer = buffer.substring(0, buffer.length() - 1);
                tvDEC.setText(buffer);
                updateTextViews(); //only update when change
            } else if (inputMode.equalsIgnoreCase(getString(R.string.BIN)) && tvBIN.getText().toString().length() != 0) {
                buffer = tvBIN.getText().toString();
                buffer = buffer.substring(0, buffer.length() - 1);
                tvBIN.setText(buffer);
                updateTextViews(); //only update when change
            } else if (inputMode.equalsIgnoreCase(getString(R.string.OCT)) && tvOCT.getText().toString().length() != 0) {
                buffer = tvOCT.getText().toString();
                buffer = buffer.substring(0, buffer.length() - 1);
                tvOCT.setText(buffer);
                updateTextViews(); //only update when change
            } else if (inputMode.equalsIgnoreCase(getString(R.string.HEX)) && tvHEX.getText().toString().length() != 0) {
                buffer = tvHEX.getText().toString();
                buffer = buffer.substring(0, buffer.length() - 1);
                tvHEX.setText(buffer);
                updateTextViews(); //only update when change
            }
        } catch (Exception e) {
            showInputErrToast();
            operationSelected = null;
            previousDecInput = 0;
            previousBinInput = "";
            clearTextViews();
        }
    }//end onPressDel method

    /**
     * updateTextViews Method - grabs TextView value of current mode and updates the other TextViews
     */
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
