package com.example.midtermtry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.midtermtry.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ArrayList<com.example.midtermtry.Todo> todoArrayList = new ArrayList<com.example.midtermtry.Todo>();
    private ActivityResultLauncher<Intent> intentActivityResultLanucher;

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

        intentActivityResultLanucher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        if(o.getData() != null && o.getResultCode() == Activity.RESULT_OK){
                            String action = o.getData().getStringExtra("ACTION");

                            if(action.equals("new")){
                                String newTitle = o.getData().getStringExtra("TITLE");
                                String newContent = o.getData().getStringExtra("CONTENT");
                                com.example.midtermtry.Todo newData = new com.example.midtermtry.Todo(newTitle, newContent);
                                todoArrayList.add(newData);
                            } else if (action.equals("edit")) {
                                String index = o.getData().getStringExtra("INDEX");
                                String newTitle = o.getData().getStringExtra("TITLE");
                                String newContent = o.getData().getStringExtra("CONTENT");

                                todoArrayList.get(Integer.parseInt(index)).setTitle(newTitle);
                                todoArrayList.get(Integer.parseInt(index)).setContent(newContent);
                            } else{
                                int removeIndex = o.getData().getIntExtra("INDEX", 0);
                                todoArrayList.remove(removeIndex);
                            }
                        }
                    }
                }
        );

        ListView todoListView = (ListView) findViewById(R.id.todoListView);

        if(todoArrayList.isEmpty()){
            Log.d("Test", "todoArrayList empty.");
            ArrayList<String> empty = new ArrayList<String>();
            empty.add("還沒有待辦事項，趕緊去新增！");
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, empty);
            todoListView.setAdapter(adapter);
        }else{
            com.example.midtermtry.TodoAdapter adapter = new com.example.midtermtry.TodoAdapter(this, todoArrayList);
            todoListView.setAdapter(adapter);
            todoListView.setOnItemClickListener(this);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        ListView todoListView = (ListView) findViewById(R.id.todoListView);
        if(todoArrayList.isEmpty()){
            Log.d("Test", "todoArrayList empty.");
            ArrayList<String> empty = new ArrayList<String>();
            empty.add("還沒有待辦事項，趕緊去新增！");
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, empty);
            todoListView.setAdapter(adapter);
        }else{
            com.example.midtermtry.TodoAdapter adapter = new com.example.midtermtry.TodoAdapter(this, todoArrayList);
            todoListView.setAdapter(adapter);
            todoListView.setOnItemClickListener(this);
        }
    }

    public void newButtonClick(View view){
        Intent intent = new Intent(this, com.example.midtermtry.TodoActivity.class);
        // 設定一個bundle來放資料
        Bundle bundle = new Bundle();
        bundle.putString("ACTION", "new");

        // 利用intent攜帶bundle的資料
        intent.putExtras(bundle);
        intentActivityResultLanucher.launch(intent);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        com.example.midtermtry.Todo item = todoArrayList.get(i);
        String title = item.getTitle();
        String content = item.getContent();

        Intent intent = new Intent(this, com.example.midtermtry.TodoActivity.class);
        // 設定一個bundle來放資料
        Bundle bundle = new Bundle();
        bundle.putString("ACTION", "edit");
        bundle.putString("TITLE", title);
        bundle.putString("CONTENT", content);
        bundle.putInt("INDEX", i);

        // 利用intent攜帶bundle的資料
        intent.putExtras(bundle);
        intentActivityResultLanucher.launch(intent);
    }
}