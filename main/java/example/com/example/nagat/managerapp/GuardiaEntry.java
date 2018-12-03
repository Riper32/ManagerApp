package example.com.example.nagat.managerapp;

import android.provider.BaseColumns;

public class GuardiaEntry implements BaseColumns
{
    public static final String TABLE_NAME = "guardias";
    public static final String COLUMN_ID_GUARDIA = "id";
    public static final String COLUMN_NAME = "nombre";

    private int idgua;
    private String namegua;

    public int getIdgua() {
        return idgua;
    }

    public void setIdgua(int idgua) {
        this.idgua = idgua;
    }

    public String getNamegua() {
        return namegua;
    }

    public void setNamegua(String namegua) {
        this.namegua = namegua;
    }
}
