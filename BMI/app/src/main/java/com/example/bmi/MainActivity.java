package com.example.bmi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private double bmi;
    private ActivityResultLauncher<Intent> intentActivityResultLauncher;
    private TextView


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

        intentActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        //寫另一個Activity回傳後，得到回傳的資料的做法
                        if(o.getData() !=null && o.getResultCode() == ActivityResult.RESULT_OK){
                            bmi = o.getData().getDoubleExtra(BMI,-1);
                    }
                }
    }
        )
    }
    public void GotoCalBMI(View view){
        Intent intent = new Intent(this,CalBMIActivity.class);

        intentActivityResultLauncher.launch(intent);

    }
    public void updateUI(){
        TextView tv_result = (TextView) findViewById(R.id.thin_value);

    }
}