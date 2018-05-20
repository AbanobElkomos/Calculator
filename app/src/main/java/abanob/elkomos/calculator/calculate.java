package abanob.elkomos.calculator;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class calculate extends Activity {
    EditText editText;
    TextView infoTextView;
    Button buttonDot, buttonZero, buttonOne, buttonTwo, buttonThree, buttonClear,
            buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine, buttonAdd, buttonSubtract, buttonMultiply, buttonDivide, buttonEqual;
     private char CURRENT_ACTION;


    float mValueOne, mValueTwo;
    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        editText = (EditText) findViewById(R.id.editText);
        infoTextView = (TextView) findViewById(R.id.infoTextView);

        Typeface type = Typeface.createFromAsset(getAssets(),"Calculator.ttf");
        editText.setTypeface(type);
        infoTextView.setTypeface(type);


        buttonDot = (Button) findViewById(R.id.buttonDot);
        buttonZero = (Button) findViewById(R.id.buttonZero);
        buttonOne = (Button) findViewById(R.id.buttonOne);
        buttonTwo = (Button) findViewById(R.id.buttonTwo);
        buttonThree = (Button) findViewById(R.id.buttonThree);
        buttonFour = (Button) findViewById(R.id.buttonFour);
        buttonFive = (Button) findViewById(R.id.buttonFive);
        buttonSix = (Button) findViewById(R.id.buttonSix);
        buttonSeven = (Button) findViewById(R.id.buttonSeven);
        buttonEight = (Button) findViewById(R.id.buttonEight);
        buttonNine = (Button) findViewById(R.id.buttonNine);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonSubtract = (Button) findViewById(R.id.buttonSubtract);
        buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
        buttonDivide = (Button) findViewById(R.id.buttonDivide);
        buttonEqual = (Button) findViewById(R.id.buttonEqual);
        buttonClear = (Button) findViewById(R.id.buttonClear);


        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(editText.getText() + ".");
            }
        });

        buttonZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(editText.getText() + "0");
            }
        });

        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(editText.getText() + "1");
            }
        });

        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(editText.getText() + "2");
            }
        });

        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(editText.getText() + "3");
            }
        });

        buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(editText.getText() + "4");
            }
        });

        buttonFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(editText.getText() + "5");
            }
        });

        buttonSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(editText.getText() + "6");
            }
        });

        buttonSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(editText.getText() + "7");
            }
        });

        buttonEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(editText.getText() + "8");
            }
        });

        buttonNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(editText.getText() + "9");
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = ADDITION;
               // infoTextView.setText(decimalFormat.format(mValueOne) + "+");
                infoTextView.setText(mValueOne+ "+");
                editText.setText(null);
            }
        });

        buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = SUBTRACTION;
                infoTextView.setText(mValueOne + "-");
               // infoTextView.setText(decimalFormat.format(mValueOne) + "-");
                editText.setText(null);
            }
        });

        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = MULTIPLICATION;
                infoTextView.setText(mValueOne + "*");
                //infoTextView.setText(decimalFormat.format(mValueOne) + "*");
                editText.setText(null);
            }
        });

        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = DIVISION;

                infoTextView.setText(mValueOne + "/");
              //  infoTextView.setText(decimalFormat.format(mValueOne) + "/");
                editText.setText(null);
            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();

                infoTextView.setText(infoTextView.getText().toString() + mValueTwo + " = " +mValueOne);
               // infoTextView.setText(infoTextView.getText().toString() + decimalFormat.format(mValueOne) + " = " + decimalFormat.format(mValueOne));
                mValueOne = (float) Double.NaN;
                CURRENT_ACTION = '0';
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().length() > 0) {
                    CharSequence currentText = editText.getText();
                    editText.setText(currentText.subSequence(0, currentText.length() - 1));
                } else {
                    mValueOne = (float) Double.NaN;
                    mValueTwo = (float) Double.NaN;
                    editText.setText("");
                    infoTextView.setText("");
                }
            }
        });
    }


    private void computeCalculation() {
        if (!Double.isNaN(mValueOne)) {
            mValueTwo = (float) Double.parseDouble(editText.getText().toString());
            editText.setText(null);

            if (CURRENT_ACTION == ADDITION)
                mValueOne = (float) (this.mValueOne + mValueTwo);
            else if (CURRENT_ACTION == SUBTRACTION)
                mValueOne = this.mValueOne - mValueTwo;
            else if (CURRENT_ACTION == MULTIPLICATION)
                mValueOne = this.mValueOne * mValueTwo;
            else if (CURRENT_ACTION == DIVISION)
                mValueOne = this.mValueOne / mValueTwo;
        } else {
            try {
                mValueOne = (float) Double.parseDouble(editText.getText().toString());
            } catch (Exception e) {
                Log.e("error", String.valueOf(e));
            }
        }
    }


}