package com.example.minibank;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NTDActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ntdactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void GoBack(View view){
        double amount = Double.parseDouble(((EditText)findViewById(R.id.et_amount)).getText().toString());

        Intent intent = new Intent();

        if (view.getId() == R.id.tontd){
            intent.putExtra("ACTION", "deposit");
        }else{
            intent.putExtra("ACTION", "withdraw");
        }

        intent.putExtra("AMOUNT", amount);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}