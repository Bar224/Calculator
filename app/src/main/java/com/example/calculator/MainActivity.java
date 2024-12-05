package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button buttonZero, buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine;
    Button buttonClear, buttonEquals, buttonPlus, buttonMinus, buttonMultiply, buttonDivide;
    TextView resultTextView;
    double num1 = 0, num2 = 0;
    char operator = ' ';
    boolean operatorSelected = false;

    private boolean shouldClearScreen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initializeUI();
    }

    private void initializeUI() {
        resultTextView = findViewById(R.id.textViewResult);

        buttonZero = findViewById(R.id.buttonZero);
        buttonOne = findViewById(R.id.buttonOne);
        buttonTwo = findViewById(R.id.buttonTwo);
        buttonThree = findViewById(R.id.buttonThree);
        buttonFour = findViewById(R.id.buttonFour);
        buttonFive = findViewById(R.id.buttonFive);
        buttonSix = findViewById(R.id.buttonSix);
        buttonSeven = findViewById(R.id.buttonSeven);
        buttonEight = findViewById(R.id.buttonEight);
        buttonNine = findViewById(R.id.buttonNine);

        buttonClear = findViewById(R.id.buttonClear);
        buttonEquals = findViewById(R.id.buttonEquals);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonMultiply = findViewById(R.id.buttonMultiply);
        buttonDivide = findViewById(R.id.buttonDivide);

        /*  // Add number buttons listeners
        View.OnClickListener numberClickListener = v -> {
            Button button = (Button) v;
            String currentText = resultTextView.getText().toString();
            resultTextView.setText(currentText + button.getText().toString());
        };*/

        View.OnClickListener numberClickListener = v -> {
            Button button = (Button) v;
            String buttonText = button.getText().toString();

            if (shouldClearScreen) {
                resultTextView.setText("");
                shouldClearScreen = false;
            }

            String currentText = resultTextView.getText().toString();
            resultTextView.setText(currentText + buttonText);
        };

        buttonZero.setOnClickListener(numberClickListener);
        buttonOne.setOnClickListener(numberClickListener);
        buttonTwo.setOnClickListener(numberClickListener);
        buttonThree.setOnClickListener(numberClickListener);
        buttonFour.setOnClickListener(numberClickListener);
        buttonFive.setOnClickListener(numberClickListener);
        buttonSix.setOnClickListener(numberClickListener);
        buttonSeven.setOnClickListener(numberClickListener);
        buttonEight.setOnClickListener(numberClickListener);
        buttonNine.setOnClickListener(numberClickListener);

        // Add operator buttons listeners
        buttonPlus.setOnClickListener(v -> setOperator('+'));
        buttonMinus.setOnClickListener(v -> setOperator('-'));
        buttonMultiply.setOnClickListener(v -> setOperator('*'));
        buttonDivide.setOnClickListener(v -> setOperator('/'));

        // Equals button
        buttonEquals.setOnClickListener(v -> calculateResult());

        // Clear button
        buttonClear.setOnClickListener(v -> clear());
    }

    private void setOperator(char op) {
        if (!operatorSelected) {
            num1 = Double.parseDouble(resultTextView.getText().toString());
            //num1 = Integer.parseInt(resultTextView.getText().toString());

            operator = op;
            operatorSelected = true;
            resultTextView.setText("");
        }
    }

    private void calculateResult() {
        if (operatorSelected) {
            num2 = Double.parseDouble(resultTextView.getText().toString());
            double result = 0;
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        resultTextView.setText("Error");
                        num1 = 0;
                        num2 = 0;
                        operatorSelected = false;
                        shouldClearScreen = true;
                        return;
                    }
                    break;
            }
            resultTextView.setText(String.valueOf(result));
            num1 = result;
            operatorSelected = false;
            shouldClearScreen = true;
        }
    }

    private void clear() {
        resultTextView.setText("");
        num1 = 0;
        num2 = 0;
        operator = ' ';
        operatorSelected = false;
    }
}
