package com.example.myfavoriteplace;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myfavplace.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends ComponentActivity {

    private static final int REQUEST_CALL_PHONE = 1;
    private final String phoneNumber = "tel:0422585006";

    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    makePhoneCall();
                } else {
                    // Permission denied
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        ViewPager2 viewPager2 = findViewById(R.id.viewPager);
        int[] images = {R.drawable.pm1, R.drawable.pm2, R.drawable.pm3, R.drawable.pm5, R.drawable.pm4}; //圖片
        com.example.myfavoriteplace.ImageSliderAdapter adapter = new com.example.myfavoriteplace.ImageSliderAdapter(this, images);
        viewPager2.setAdapter(adapter);

        FloatingActionButton floatingButton = findViewById(R.id.floatingButton);
        floatingButton.setOnClickListener(v -> {
            // 浮動按鈕
            String url = "https://www.5pmtwcaudex.com/";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        ImageButton buttonCall = findViewById(R.id.buttonCall);
        ImageButton buttonMap = findViewById(R.id.buttonMap);
        Button buttonEmail = findViewById(R.id.buttonEmail);
        TextView placeMap = findViewById(R.id.placeMap);

        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE);
                } else {
                    makePhoneCall();
                }
            }
        });

        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap(placeMap.getText().toString());
            }
        });

        buttonEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }

    private void makePhoneCall() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(phoneNumber));
        startActivity(callIntent);
    }

    private void openMap(String place) {
        String geoUri = "geo:0,0?q=" + place;
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
        startActivity(mapIntent);
    }

    private void sendEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:example@example.com"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Hello from MyFavoritePlace");
        startActivity(emailIntent);
    }
}