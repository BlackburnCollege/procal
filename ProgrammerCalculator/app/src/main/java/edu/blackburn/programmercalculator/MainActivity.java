package edu.blackburn.programmercalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText input1;
    private EditText input2;
    private EditText output;
    private TextView operationSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.input1 = (EditText) findViewById(R.id.textfield_input1);
        this.input2 = (EditText) findViewById(R.id.textfield_input2);
        this.output = (EditText) findViewById(R.id.textfield_output);
        this.operationSelected = (TextView) findViewById(R.id.textfield_operationSelected);
    }//end onCreate method


    /**
     * Called when the user touches the + button
     */
    public void onAddButtonPress(View view) {
        //access strings.xml resource by using getString(R.string.NAME)
        updateOperationTextfield(getString(R.string.add));
    }//end onAddButtonPress method

    /**
     * Called when the user touches the - button
     */
    public void onSubButtonPress(View view) {
        //access strings.xml resource by using getString(R.string.NAME)
        updateOperationTextfield(getString(R.string.sub));
    }//end onSubButtonPress method

    /**
     * Called when the user touches the * button
     */
    public void onMulButtonPress(View view) {
        //access strings.xml resource by using getString(R.string.NAME)
        updateOperationTextfield(getString(R.string.mul));
    }//end onMulButtonPress method

    /**
     * Called when the user touches the / button
     */
    public void onDivButtonPress(View view) {
        //access strings.xml resource by using getString(R.string.NAME)
        updateOperationTextfield(getString(R.string.div));
    }//end onDivButtonPress method

    /**
     * Called when the user touches the = button
     */
    public void onEqualButtonPress(android.view.View view) {
        //convert to double to avoid division errors
        double num1 = Integer.parseInt(this.input1.getText().toString());
        double num2 = Integer.parseInt(this.input2.getText().toString());

        //get the selected operation
        String operation = this.operationSelected.getText().toString();

        //access strings.xml resource by using getString(R.string.NAME)
        if (operation.equals(getString(R.string.add))) {
            this.output.setText("" + (num1 + num2) + "");
        } else if (operation.equals(getString(R.string.sub))) {
            this.output.setText("" + (num1 - num2) + "");
        } else if (operation.equals(getString(R.string.mul))) {
            this.output.setText("" + (num1 * num2) + "");
        } else if (operation.equals(getString(R.string.div))) {
            this.output.setText("" + (num1 / num2) + "");
        } else {
            this.output.setText("@string/unexpectedErrorMSG");
        }
    }//end onEqualButtonPress method

    /**
     * Called when the user touches the +, -, *, or / button
     */
    private void updateOperationTextfield(String newOperation) {
        this.operationSelected.setText(newOperation);
    }//end updateOperationTextfield

}//end MainActivity class