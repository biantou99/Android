package com.example.temperatureconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputTemperature;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputTemperature = findViewById(R.id.inputTemperature);
        resultText = findViewById(R.id.resultText);
    }

    public void convertCelsiusToFahrenheit(View view) {
        String input = inputTemperature.getText().toString();
        if (!input.isEmpty()) {
            double celsius = Double.parseDouble(input);
            double fahrenheit = celsius * 9 / 5 + 32;
            resultText.setText(String.format("%.2f °F", fahrenheit));
        } else {
            resultText.setText("請輸入溫度");
        }
    }

    public void convertFahrenheitToCelsius(View view) {
        String input = inputTemperature.getText().toString();
        if (!input.isEmpty()) {
            double fahrenheit = Double.parseDouble(input);
            double celsius = (fahrenheit - 32) * 5 / 9;
            resultText.setText(String.format("%.2f °C", celsius));
        } else {
            resultText.setText("請輸入溫度");
        }
    }
}
