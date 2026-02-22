package com.example.firstclass;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Screen
    TextView value;

    // Numbers
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;

    // Operators
    Button btnPlus, btnMinus, btnMul, btnDiv, btnEqual;

    // Clear & delete
    Button btnC, btnCE;
    ImageButton btnDel;

    double firstNumber = 0;
    double secondNumber = 0;
    String operator = "";
    boolean isNewInput = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Display
        value = findViewById(R.id.value);

        // Numbers
        btn0 = findViewById(R.id.button35);
        btn1 = findViewById(R.id.button26);
        btn2 = findViewById(R.id.button28);
        btn3 = findViewById(R.id.button29);
        btn4 = findViewById(R.id.button22);
        btn5 = findViewById(R.id.button23);
        btn6 = findViewById(R.id.button24);
        btn7 = findViewById(R.id.button15);
        btn8 = findViewById(R.id.button19);
        btn9 = findViewById(R.id.button20);

        // Operators
        btnPlus = findViewById(R.id.button30);
        btnMinus = findViewById(R.id.button27);
        btnMul = findViewById(R.id.button21);
        btnDiv = findViewById(R.id.button14);
        btnEqual = findViewById(R.id.button37);

        // Clear & delete
        btnC = findViewById(R.id.button9);
        btnCE = findViewById(R.id.button8);
        btnDel = findViewById(R.id.imageButton);

        // Number clicks
        setNumberClick(btn0);
        setNumberClick(btn1);
        setNumberClick(btn2);
        setNumberClick(btn3);
        setNumberClick(btn4);
        setNumberClick(btn5);
        setNumberClick(btn6);
        setNumberClick(btn7);
        setNumberClick(btn8);
        setNumberClick(btn9);

        // Operator clicks
        btnPlus.setOnClickListener(operatorClick("+"));
        btnMinus.setOnClickListener(operatorClick("-"));
        btnMul.setOnClickListener(operatorClick("*"));
        btnDiv.setOnClickListener(operatorClick("/"));

        // Equal
        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });

        // Clear all
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value.setText("0");
                firstNumber = 0;
                secondNumber = 0;
                operator = "";
                isNewInput = true;
            }
        });

        // Clear entry
        btnCE.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                value.setText("0");
                isNewInput = true;
            }
        });

        // Delete last digit
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = value.getText().toString();
                if (text.length() > 1) {
                    value.setText(text.substring(0, text.length() - 1));
                } else {
                    value.setText("0");
                }
            }
        });
    }



    private void setNumberClick(final Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNewInput) {
                    value.setText(button.getText().toString());
                    isNewInput = false;
                } else {
                    value.setText(value.getText().toString() + button.getText().toString());
                }
            }
        });
    }

    private View.OnClickListener operatorClick(final String op) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstNumber = Double.parseDouble(value.getText().toString());
                operator = op;
                isNewInput = true;
            }
        };
    }

    private void calculate() {
        secondNumber = Double.parseDouble(value.getText().toString());
        double result = 0;

        if (operator.equals("+")) {
            result = firstNumber + secondNumber;
        } else if (operator.equals("-")) {
            result = firstNumber - secondNumber;
        } else if (operator.equals("*")) {
            result = firstNumber * secondNumber;
        } else if (operator.equals("/")) {
            if (secondNumber != 0) {
                result = firstNumber / secondNumber;
            } else {
                value.setText("Error");
                return;
            }
        }

        value.setText(String.valueOf(result));
        isNewInput = true;
    }
}