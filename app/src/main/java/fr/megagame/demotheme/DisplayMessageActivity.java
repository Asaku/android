package fr.megagame.demotheme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Map;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        //Intent intent = getIntent();
        //String message = intent.getStringExtra("yolo");

        SharedPreferences sharedPreferences = getSharedPreferences("todo", MODE_PRIVATE);
        String todo = sharedPreferences.getString("text", null);

        String[] myArray = new String[]{"kikou", "salut"};

        Map<String, ?> allEntries = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Log.i("yolo", entry.getKey() + ": " + entry.getValue().toString());
        }

        ListView mlistView = (ListView) findViewById(R.id.listView);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myArray);

        mlistView.setAdapter(adapter);
        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("yolo", "item n° " + i);
            }
        });
    }

    public void addTodo(View view) {
        EditText todo = (EditText) findViewById(R.id.new_todo);
        saveBySharedP(todo.getText().toString());
        //Log.i("yolo", todo.getText().toString());
    }

    private void saveBySharedP(String todo) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("todo", todo);
        editor.apply();
        editor.commit();

        Map<String, ?> allEntries = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Log.i("yolo", entry.getKey() + ": " + entry.getValue().toString());
        }
    }
}
