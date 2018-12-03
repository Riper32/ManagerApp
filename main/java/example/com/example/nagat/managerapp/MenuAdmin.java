package example.com.example.nagat.managerapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;

public class MenuAdmin extends AppCompatActivity {

    DBController dbController;

    TextView tvDatosAdmin;
    String nombreAdmin;
    String apellidoAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);

        tvDatosAdmin = (TextView)findViewById(R.id.datosAdmin);
        Intent intent = getIntent();
        nombreAdmin = intent.getStringExtra("nombre");
        apellidoAdmin = intent.getStringExtra("apellido");

        tvDatosAdmin.setText(nombreAdmin+" "+apellidoAdmin);

        dbController = new DBController(this.getBaseContext());

    }

    public void addGuar(View view)
    {
        Intent newActivity = new Intent(this, AddGuardia.class);
        newActivity.putExtra("nombre", nombreAdmin);
        newActivity.putExtra("apellido", apellidoAdmin);
        startActivity(newActivity);
    }

    public void insertarGuardia(int id_G, String nomb_G)
    {
        GuardiaEntry guardiaEntry = new GuardiaEntry();

        /*guardiaEntry.setIdgua(id_G);
        guardiaEntry.setNamegua(nomb_G);

        long inserted = dbController.insert(guardiaEntry);*/

        guardiaEntry.setIdgua(id_G);
        guardiaEntry.setNamegua(nomb_G);

        long inserted = dbController.insert(guardiaEntry);

        Log.d("DATABASE","INSERTION " + inserted);
    }

    public void loadDB(View view)
    {
        /*final ViewGroup table = findViewById(R.id.guardTable);

        final Cursor cursor = dbController.selectGuardia("", null);*/

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    //boolean succsess = false;
                    Log.d("JSON response: ", ""+response);

                    JSONArray myArray = jsonResponse.getJSONArray("response");
                    int len = myArray.length();

                    Log.d("JSON ARRAY: ", ""+myArray);
                    Log.d("JSON ARRAY LENGTH", ""+len);

                    for (int i = 0; i<len; i++)
                    {
                        JSONObject arreglo = myArray.getJSONObject(i);
                        Log.d("JSON object: ", ""+arreglo);
                        String id_guardia = arreglo.getString("guard");
                        String nombreGuar = arreglo.getString("id");
                        Log.d("POSICION: ", ""+i);
                        Log.d("REGISTRO:  ", "ID = "+id_guardia+" NOMBRE = "+nombreGuar);

                        //insertarGuardia(Integer.parseInt(id_guardia), nombreGuar);
                        GuardiaEntry guardiaEntry = new GuardiaEntry();

                        guardiaEntry.setIdgua(Integer.parseInt(id_guardia));
                        guardiaEntry.setNamegua(nombreGuar);

                        long inserted = dbController.insert(guardiaEntry);

                        Log.d("DATABASE","INSERTION " + inserted);


                    }

                    final ViewGroup table = findViewById(R.id.guardTable);

                    final Cursor cursor = dbController.selectGuardia("", null);

                    while (cursor.moveToNext())
                    {
                        String idG = String.valueOf(cursor.getInt(cursor.getColumnIndex(GuardiaEntry.COLUMN_ID_GUARDIA)));
                        String nomG = cursor.getString(cursor.getColumnIndex(GuardiaEntry.COLUMN_NAME));

                        View row = getLayoutInflater().inflate(R.layout.activity_guardia_row, null);
                        TextView tvidG = row.findViewById(R.id.idGuarView);
                        TextView tvnomG = row.findViewById(R.id.nomGuarView);

                        tvidG.setText(idG);
                        tvnomG.setText(nomG);

                        table.addView(row);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        LoadDBRequest loadDBRequest = new LoadDBRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(MenuAdmin.this);
        queue.add(loadDBRequest);



    }

    public void infoGuar(View view)
    {
        //TextView tvidGua = findViewById(R.id.idGuarView);
        TextView tvidGua = ((View)view.getParent()).findViewById(R.id.idGuarView);

        String stidGua = ""+tvidGua.getText();

        Log.d("DATABASE: ", "ID del GUARDIA seleccionado = "+stidGua);

        int idGua = Integer.parseInt(stidGua);

        //idGua = ((View).view.getParent()).getId;
        //int id = 1;
        //Log.d("Reception", "Emp id: " + id);


        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean succsess = jsonResponse.getBoolean("success");
                    Log.d("JSON response: ", ""+response);
                    if(succsess)
                    {
                        String id_guardia = jsonResponse.getString("id_guardia");
                        String nombreGuar = jsonResponse.getString("nombre");
                        String apellidoGuard = jsonResponse.getString("apellido");
                        String fechaNac = jsonResponse.getString("fecha_nacimiento");
                        String direccion = jsonResponse.getString("direccion");
                        String id_admin = jsonResponse.getString("id_admin");

                        Intent newActivity = new Intent(MenuAdmin.this, InformacionGuardia.class);
                        newActivity.putExtra("nombre", nombreAdmin);
                        newActivity.putExtra("apellido", apellidoAdmin);
                        newActivity.putExtra("id_guardia", id_guardia);
                        newActivity.putExtra("nombreGuard", nombreGuar);
                        newActivity.putExtra("apellidoGuard", apellidoGuard);
                        newActivity.putExtra("fechaNac", fechaNac);
                        newActivity.putExtra("direccion", direccion);
                        newActivity.putExtra("id_admin", id_admin);
                        MenuAdmin.this.startActivity(newActivity);
                    }
                    else
                    {
                        Log.d("LOGIN", "Fallo al validar");
                        AlertDialog.Builder builder = new AlertDialog.Builder(MenuAdmin.this);
                        builder.setMessage("Fallo al validar").setNegativeButton("Vuelva a intentr", null).create().show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        InfoGuardiRequest infoGuardiRequest = new InfoGuardiRequest(idGua, responseListener);
        RequestQueue queue = Volley.newRequestQueue(MenuAdmin.this);
        queue.add(infoGuardiRequest);

    }
}
