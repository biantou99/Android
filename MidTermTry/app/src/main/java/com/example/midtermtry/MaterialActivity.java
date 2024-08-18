package com.example.midtermtry;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.midtermtry.R;
import com.google.android.material.textfield.TextInputLayout;

public class MaterialActivity extends AppCompatActivity {
    String materialName, materialNote, materialImageUrl, action;
    int quantity = 0, index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_material);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle bundle = this.getIntent().getExtras();
        action = bundle.getString("ACTION");

        if(bundle != null && action.equals("edit")){
            materialName = bundle.getString("MATERIAL_NAME");
            quantity = bundle.getInt("QUANTITY");
            materialImageUrl = bundle.getString("MATERIAL_IMAGE_URL");
            materialNote = bundle.getString("MATERIAL_NOTE");
            index = bundle.getInt("INDEX");

            EditText etMaterialName = findViewById(R.id.materialName);
            etMaterialName.setText(materialName);
            EditText etQuantity = findViewById(R.id.quantity);
            etQuantity.setText(String.valueOf(quantity));
            EditText etMaterialImageUrl = findViewById(R.id.materialImageUrl);
            etMaterialImageUrl.setText(materialImageUrl);
            TextInputLayout textInputLayout = findViewById(R.id.noteTextInputLayout);
            textInputLayout.getEditText().setText(materialNote);
        }
    }

    public void saveButtonClick(View view){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        String titleText = "確定新增？";
        String contentText = "確定內容並儲存？";
        if(action.equals("edit")){
            titleText = "確定修改？";
            contentText = "確定修改的內容並儲存？";
        }
        dialog.setTitle(titleText);
        dialog.setMessage(contentText);
        dialog.setCancelable(true);
        dialog.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String materialName = ((EditText)findViewById(R.id.materialName)).getText().toString();
                int quantity = Integer.parseInt(((EditText)findViewById(R.id.quantity)).getText().toString());
                String materialImageUrl = ((EditText)findViewById(R.id.materialImageUrl)).getText().toString();
                TextInputLayout textInputLayout = findViewById(R.id.noteTextInputLayout);
                String materialNote = textInputLayout.getEditText().getText().toString();

                Intent intent = new Intent();
                intent.putExtra("MATERIAL_NAME", materialName);
                intent.putExtra("QUANTITY", quantity);
                intent.putExtra("MATERIAL_IMAGE_URL", materialImageUrl);
                intent.putExtra("MATERIAL_NOTE", materialNote);
                intent.putExtra("ACTION", action);

                if(action.equals("edit")){
                    intent.putExtra("INDEX", index);
                }
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        dialog.setNeutralButton("取消", null);
        dialog.show();
    }

    public void giveUpButtonClick(View view){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("確定放棄？");
        dialog.setMessage("確定放棄並返回主頁面？");
        dialog.setCancelable(true);
        dialog.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        dialog.setNeutralButton("取消", null);
        dialog.show();
    }
}
