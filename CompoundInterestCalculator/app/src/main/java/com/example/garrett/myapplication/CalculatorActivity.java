/***************************************************************************************************
This is the main file for the compound interest calculator
 **************************************************************************************************/
package com.example.garrett.myapplication;

import android.annotation.TargetApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.annotation.Target;
import java.text.DecimalFormat;

/**
 * Contains important text fields from the activity view.
 * As far as I could find Java does not support structs.
 */
class CalculatorActivityIOStruct {
    EditText principalText;
    EditText interestText;
    EditText compoundText;
    EditText timeText;

    TextView amountText;
}

/**
 * This class maintains the calculator window. Its job is to maintain the input/output from the Gui
 * and apply changes in the GUI to the calculator.
 *
 * Relies on Calculator and CalculatorActivityIOStruct to function
 */
public class CalculatorActivity extends AppCompatActivity {
    private CalculatorActivityIOStruct io = new CalculatorActivityIOStruct();
    private Calculator calculator = new Calculator();

    /**
     * Initializes the window.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        PopulateIO();
        ApplyListeners();
    }

    /**
     * Initializes the edit text and text boxes as variables
     */
    private void PopulateIO() {
        io.principalText = (EditText) findViewById(R.id.Principal);
        io.interestText = (EditText) findViewById(R.id.Interest);
        io.compoundText = (EditText) findViewById(R.id.NumberOfCompounds);
        io.timeText = (EditText) findViewById(R.id.Time);
        io.amountText = (TextView) findViewById(R.id.Amount);
    }

    /**
     * Attempts to calculate and display the amount over time.
     * TargetApi(24) is used to allow DecimalFormat
     */
    @TargetApi(24)
    private void AttemptToDisplayAmount(){
        DecimalFormat format = new DecimalFormat("$###,###,###,###.##");
        Double amount = calculator.getAmount();
        if (amount == null) {
            io.amountText.setText("");
        }
        else {
            String text = format.format(amount);
            io.amountText.setText(text);
        }
    }

    /**
     * Initializes all listeners
     */
    private void ApplyListeners() {
        ApplyListenerPrincipal();
        ApplyListenerInterest();
        ApplyListenerCompound();
        ApplyListenerTime();
    }

    /***********************************************************************************************
                                            Listeners.
     When text is edited for a field, each listener will apply its associated value in the calculator
     (example: the interest listener will apply the input value to the interest field in the calculator)
     and then attempt to display the full amount to the amount text box.

     beforeTextChanged and afterTextChanged are technically required to create a TextWatcher, but
     only onTextChanged is used here.
     **********************************************************************************************/
    private void ApplyListenerCompound() {
        io.compoundText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try
                {
                    calculator.setCompounds(Integer.parseInt(io.compoundText.getText().toString()));
                }
                catch (Exception ex) {
                    System.out.print(ex);
                    calculator.setCompounds(null);
                }

                AttemptToDisplayAmount();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //Do nothing
            }
        });
    }

    private void ApplyListenerInterest() {
        io.interestText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try
                {
                    calculator.setInterest(Double.parseDouble(io.interestText.getText().toString()));
                }
                catch (Exception ex) {
                    System.out.print(ex);
                    calculator.setInterest(null);
                }

                AttemptToDisplayAmount();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //Do nothing
            }
        });
    }

    private void ApplyListenerPrincipal() {
        io.principalText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try
                {
                    calculator.setPrincipal(Double.parseDouble(io.principalText.getText().toString()));
                }
                catch (Exception ex)
                {
                    System.out.print(ex);
                    calculator.setPrincipal(null);
                }

                AttemptToDisplayAmount();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //Do nothing
            }
        });
    }

    private void ApplyListenerTime() {
        io.timeText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try
                {
                    calculator.setTime(Double.parseDouble(io.timeText.getText().toString()));
                }
                catch (Exception ex) {
                    System.out.print(ex);
                    calculator.setTime(null);
                }

                AttemptToDisplayAmount();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //Do nothing
            }
        });
    }
}
