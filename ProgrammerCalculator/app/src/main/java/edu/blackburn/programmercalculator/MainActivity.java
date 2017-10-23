package edu.blackburn.programmercalculator;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Model calculatorModel = new Model();
    int bitPrecision;
    Boolean signed;

    TextView tvDEC;
    TextView tvHEX;
    TextView tvOCT;
    TextView tvBIN;
    Button bzero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signed = true;
        bitPrecision = 64;
        tvDEC = (TextView) findViewById((R.id.textViewDEC));
        tvHEX = (TextView) findViewById((R.id.textViewHEX));
        tvOCT = (TextView) findViewById((R.id.textViewOCT));
        tvBIN = (TextView) findViewById((R.id.textViewBIN));

        bzero = (Button) findViewById(R.id.button0);

    }//end onCreate method

    public String getDecTextString(View view) {
        return tvDEC.getText().toString();
    }

    public void getHexTextString() {
    }

    public void getOctTextString() {
    }

    public void getBinTextString() {
    }

    public void onPress0(View view) {
    }

    public void onPress1(View view) {
    }

    public void onPress2(View view) {
    }

    public void onPress3(View view) {
    }

    public void onPress4(View view) {
    }

    public void onPress5(View view) {
    }

    public void onPress6(View view) {
    }

    public void onPress7(View view) {
    }

    public void onPress8(View view) {
    }

    public void onPress9(View view) {
    }

    public void onPressA(View view) {
    }

    public void onPressB(View view) {
    }

    public void onPressC(View view) {
    }

    public void onPressD(View view) {
    }

    public void onPressE(View view) {
    }

    public void onPressF(View view) {
    }

    public void onPressDec(View view) {
    }

    public void onPressHex(View view) {
    }

    public void onPressOct(View view) {
    }

    public void onPressBin(View view) {
    }

    public void onPressAnd(View view) {
    }

    public void onPressNand(View view) {
    }

    public void onPressOr(View view) {
    }

    public void onPressNor(View view) {
    }

    public void onPressXor(View view) {
    }

    public void onPressNxor(View view) {
    }

    public void onPressAc(View view) {
    }

    public void onPressDel(View view) {
    }

    public void onPressNot(View view) {
    }

    public void onPressMod(View view) {
    }

    public void onPressDivide(View view) {
    }

    public void onPressMultiply(View view) {
    }

    public void onPressAdd(View view) {
    }

    public void onPressSubtract(View view) {
    }

    public void onPressEqual(View view) {
        Context context = getApplicationContext();
        CharSequence text = "Tim doesn't know how to use Android Studio!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}//end MainActivity class
