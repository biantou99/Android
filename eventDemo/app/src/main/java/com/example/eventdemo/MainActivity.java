package com.example.eventdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View
        .OnClickListener, View .OnLongClickListener, View .OnTouchListener{

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
        Button b = (Button) findViewById(R.id.button);
        Button b2 = (Button) findViewById(R.id.button2);
        b.setOnClickListener(this);
        b.setOnLongClickListener(this);
        ConstraintLayout main = (ConstraintLayout) findViewById(R.id.main)
    }
    @Override
    public void onClick(View view){
        TextView tv_text = (TextView) findViewById(R.id.tv_text);
        TextView tv_size = (TextView) findViewById(R.id.tv_size);

        float currentSizeSp = tv_text.getTextSize() +5;
        if(view.getId() == R.id.button){
        tv_text.setTextSize(currentSizeSp +5);
        tv_size.setText(String.valueOf(currentSizeSp+5);
    }

    @Override
    public boolean onLongClick(View view) {
        TextView tv_text = (TextView) findViewById(R.id.tv_text);
        TextView tv_size = (TextView) findViewById(R.id.tv_size);

        tv_text.setTextSize(30);
        tv_size.setText("30");
        return true;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        TextView tv_action = (TextView) findViewById(R.id.action);
        TextView tv_position = (TextView) findViewById(R.id.position);
         int act = motionEvent.getAction();
         switch (act){
             case MotionEvent.ACTION_DOWN:
                 tv_action.setText("ACTION_DOWN");
                 tv_action.setTextColor(Color.parseColor());
                 break;
             case MotionEvent.ACTION_MOVE:
                 break;
             case MotionEvent.ACTION_UP:
                 break;
         }

         tv_position.setText("X."+ motionEvent.get());
        return true;
    }
}