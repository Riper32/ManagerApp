package example.com.example.nagat.managerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class    DBController
{
    DBHelper dbHelper;
    SQLiteDatabase database;

    public DBController(Context context)
    {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public void close()
    {
        database.close();
    }

    public long insert(GuardiaEntry guardia)
    {

        Log.d("DBController :", "Llego al controler para insertar: "+guardia);
        ContentValues values = new ContentValues();

        values.put(GuardiaEntry.COLUMN_ID_GUARDIA, guardia.getIdgua());
        values.put(GuardiaEntry.COLUMN_NAME, guardia.getNamegua());

        long inserted = database.insert(GuardiaEntry.TABLE_NAME, null, values);
        return inserted;
    }

    public Cursor selectGuardia(String selection, String[] selectionArgs)
    {
        String columns[] =
                {
                        GuardiaEntry.COLUMN_ID_GUARDIA,
                        GuardiaEntry.COLUMN_NAME
                };
        Cursor cursor = database.query(GuardiaEntry.TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        return cursor;
    }
}
