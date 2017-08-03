package fr.megagame.demotheme;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mehdi on 30/07/2017.
 */

public class MySQLite extends SQLiteOpenHelper {

    private static final String TABLE_TODO = "table_todo";
    private static final String COL_ID = "ID";
    private static final String COL_TODO = "TODO";
    private static final String COL_DATECREATED = "DATECREATED";

    private static final String CREATED_BDD = "CREATE TABLE " + TABLE_TODO + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_TODO + " TEXT NOT NULL, "
            + COL_DATECREATED + " TEXT NOT NULL);";

    public MySQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATED_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_TODO + ";");
        onCreate(sqLiteDatabase);
    }
}
