package com.example.counter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

        // 初始化時設置計數次數為0，按鈕可用
        Init(null);
    }

    public void Count(View view) {
        TextView timesText = (TextView) findViewById(R.id.times);
        Button countButton = (Button) findViewById(R.id.count);
        EditText limitEditText = (EditText) findViewById(R.id.times);

        int limit = Integer.parseInt(limitEditText.getText().toString());
        int times = Integer.parseInt(timesText.getText().toString());

        if (limit > times) {
            times += 1;
            timesText.setText(String.valueOf(times));
        } else {
            timesText.setText("已達上限");
            countButton.setEnabled(false);
        }
    }

    public void Init(View view) {
        Button countButton = (Button) findViewById(R.id.count);
        TextView timesText = (TextView) findViewById(R.id.times);
        timesText.setText("0");
        countButton.setEnabled(true);
    }
}
