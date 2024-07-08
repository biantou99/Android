package com.example.temperatureconverter;

import android.health.connect.datatypes.units.Temperature;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.temperature.R;

import java.text.BreakIterator;

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

    public void CtoF(View view) {
        EditText temperature = (EditText)findViewById(R.id.temp);
        TextView result = (TextView) findViewById(R.id.result);

        int t = Integer.parseInt(temperature.getText().toString());
        double fTemperature = t* 9 / 5 + 32;

            resultText.setText("請輸入溫度");
        }
    }

    public void FtoC(View view) {
        EditText temperature = (EditText)findViewById(R.id.temp);
        TextView result = (TextView) findViewById(R.id.result);

        int t = Integer.parseInt(temperature.getText().toString());
        double cTemperature = (t - 32) * 5 / 9;

        resultText.setText("請輸入溫度");
        }
    }
}
