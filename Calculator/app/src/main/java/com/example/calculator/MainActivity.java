package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.calculator.R;

import org.w3c.dom.Text;
public class MainActivity extends AppCompatActivity {

    private EditText etFirstNumber, etSecondNumber;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFirstNumber = findViewById(R.id.et_first_number);
        etSecondNumber = findViewById(R.id.et_second_number);
        tvResult = findViewById(R.id.tv_result);

        findViewById(R.id.btn_add).setOnClickListener(v -> calculate('+'));
        findViewById(R.id.btn_subtract).setOnClickListener(v -> calculate('-'));
        findViewById(R.id.btn_multiply).setOnClickListener(v -> calculate('*'));
        findViewById(R.id.btn_divide).setOnClickListener(v -> calculate('/'));
    }

    private void calculate(char operator) {
        String num1 = etFirstNumber.getText().toString();
        String num2 = etSecondNumber.getText().toString();

        if (num1.isEmpty() || num2.isEmpty()) {
            tvResult.setText("Please enter both numbers");
            return;
        }

        //浮點數
        double number1 = Double.parseDouble(num1);
        double number2 = Double.parseDouble(num2);
        double result = 0;

        switch (operator) {
            case '+':
                result = number1 + number2;
                break;
            case '-':
                result = number1 - number2;
                break;
            case '*':
                result = number1 * number2;
                break;
            case '/':
                if (number2 != 0) {
                    result = number1 / number2;
                } else {
                    tvResult.setText("Cannot divide by zero");
                    return;
                }
                break;
        }

        tvResult.setText(String.valueOf(result));
    }
}