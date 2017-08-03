package fr.megagame.demotheme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

/**
 * Created by mehdi on 30/07/2017.
 */

public class TodoBDD {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "yolo.db";

    private static final String TABLE_TODO = "table_todo";
    private static final String COL_ID = "ID";
    private static final String COL_TODO = "TODO";
    private static final String COL_DATECREATED = "DATECREATED";

    private static final int NUM_COL_ID = 0;
    private static final int NUM_COL_TODO = 1;
    private static final int NUM_COL_DATECREATED = 2;

    private SQLiteDatabase bdd;
    private MySQLite mySQLite;

    public TodoBDD(Context context) {
        mySQLite = new MySQLite(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open() {
        bdd = mySQLite.getWritableDatabase();
    }

    public void close() {
        bdd.close();
    }

    public SQLiteDatabase getBdd() {
        return bdd;
    }

    public long insertTodo(Todo todo) {
        ContentValues values = new ContentValues();

        values.put(COL_TODO, todo.getTodo());
        values.put(COL_DATECREATED, todo.getDateCreated());

        return bdd.insert(TABLE_TODO, null, values);
    }

    public int updateTodo(int id, Todo todo) {
        ContentValues values = new ContentValues();

        values.put(COL_TODO, todo.getTodo());
        values.put(COL_DATECREATED, todo.getDateCreated());
        return bdd.update(TABLE_TODO, values, COL_ID + " = " +id, null);
    }

    public int removeTodo(int id) {
        return bdd.delete(TABLE_TODO, COL_ID + " = " +id, null);
    }

    public Todo getTodoByTodo(String todo) {
        Cursor c = bdd.query(
                TABLE_TODO, new String[] {COL_ID, COL_TODO, COL_DATECREATED}, COL_TODO +" LIKE\"" + todo + "\"", null, null, null, null
        );

        return cursorToTodo(c);
    }

    private Todo cursorToTodo(Cursor c) {
        if (c.getCount() == 0)
            return null;

        c.moveToFirst();

        Todo todo = new Todo();

        todo.setId(c.getInt(NUM_COL_ID));
        todo.setTodo(c.getString(NUM_COL_TODO));
        todo.setDateCreated(c.getString(NUM_COL_DATECREATED));

        c.close();

        Log.i("yolo", "todo content: " + todo.getTodo());

        return todo;
    }

}
