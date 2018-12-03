package example.com.example.nagat.managerapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "guardiasDB";

    private static final String DESTROY ="DROP TABLE IF EXISTS " + GuardiaEntry.TABLE_NAME;

    private static final String SQL_CREATE = "CREATE TABLE " + GuardiaEntry.TABLE_NAME + "("+ GuardiaEntry.COLUMN_ID_GUARDIA + " INTEGER PRIMARY KEY, "+ GuardiaEntry.COLUMN_NAME + " TEXT)";

    private static int DATABASE_VERSION = 2;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE);
        Log.d("QUERY CREATE: ", ""+SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(DESTROY);
        onCreate(db);
    }
}
