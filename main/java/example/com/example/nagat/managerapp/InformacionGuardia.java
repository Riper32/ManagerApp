package example.com.example.nagat.managerapp;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InformacionGuardia extends AppCompatActivity {

    String nombreAdmin;
    String apellidoAdmin;
    String fecha;
    int id_guardia;

    TextView tvid, tvnom, tvape, tvfec, tvdir, tvidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_guardia);

        tvid = (TextView)findViewById(R.id.tvIdGua);
        tvnom = (TextView)findViewById(R.id.tvNomGua);
        tvape = (TextView)findViewById(R.id.tvApeGua);
        tvfec = (TextView)findViewById(R.id.tvFecNac);
        tvdir = (TextView)findViewById(R.id.tvDir);
        tvidad = (TextView)findViewById(R.id.tvAdm);

        Intent intent = getIntent();
        nombreAdmin = intent.getStringExtra("nombre");
        apellidoAdmin = intent.getStringExtra("apellido");

        tvid.setText(intent.getStringExtra("id_guardia"));
        tvnom.setText(intent.getStringExtra("nombreGuard"));
        tvape.setText(intent.getStringExtra("apellidoGuard"));
        tvfec.setText(intent.getStringExtra("fechaNac"));
        tvdir.setText(intent.getStringExtra("direccion"));
        tvidad.setText(intent.getStringExtra("id_admin"));

        id_guardia = Integer.parseInt(intent.getStringExtra("id_guardia"));

    }

    public void backMenu(View view)
    {
        Intent newActivity = new Intent(this, MenuAdmin.class);
        newActivity.putExtra("nombre", nombreAdmin);
        newActivity.putExtra("apellido", apellidoAdmin);
        startActivity(newActivity);
    }

    public void seeLocation(View view)
    {
        fecha = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean succsess = jsonResponse.getBoolean("success");
                    Log.d("JSON response: ", ""+response);
                    if(succsess)
                    {
                        double latitud = jsonResponse.getDouble("latitud");
                        double longitud = jsonResponse.getDouble("longitud");

                        Intent newActivity = new Intent(InformacionGuardia.this, MapsActivity.class);
                        newActivity.putExtra("latitud", latitud+"");
                        newActivity.putExtra("longitud", longitud+"");
                        InformacionGuardia.this.startActivity(newActivity);
                    }
                    else
                    {
                        Log.d("LOGIN", "Fallo al validar");
                        AlertDialog.Builder builder = new AlertDialog.Builder(InformacionGuardia.this);
                        builder.setMessage("Fallo al validar").setNegativeButton("Vuelva a intentr", null).create().show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Log.d("LOCATION :", "Los datos enviados son: "+fecha+" el id: "+id_guardia+"");

        LocationRequest locationRequest = new LocationRequest(fecha, id_guardia, responseListener);
        RequestQueue queue = Volley.newRequestQueue(InformacionGuardia.this);
        queue.add(locationRequest);


    }
}
