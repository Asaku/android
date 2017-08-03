package fr.megagame.demotheme;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class TodoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        TodoBDD todoBDD = new TodoBDD(this);

        Todo todo = new Todo("Prendre les enfants à l'école");

        todoBDD.open();
        //todoBDD.insertTodo(todo);

        Todo TodoFromBDD = todoBDD.getTodoByTodo(todo.getTodo());

        if (TodoFromBDD != null) {
            Log.i("yolo", "cha marche");
            Log.i("yolo", "ma todo: " + TodoFromBDD.toString());
            Toast.makeText(this, TodoFromBDD.toString(), Toast.LENGTH_SHORT).show();
        }
    }

}
