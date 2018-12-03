package example.com.example.nagat.managerapp;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AddGuardia extends AppCompatActivity {
    String nombreAdmin;
    String apellidoAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_guardia);

        Intent intent = getIntent();
        nombreAdmin = intent.getStringExtra("nombre");
        apellidoAdmin = intent.getStringExtra("apellido");
    }

    public void addGuartoBD(View view)
    {
        EditText etidGuard = (EditText)findViewById(R.id.idGuard);
        EditText etnomGuard = (EditText)findViewById(R.id.nomGuard);
        EditText etapeGuard = (EditText)findViewById(R.id.apeGuard);
        EditText etnacGuard = (EditText)findViewById(R.id.nacGuard);
        EditText etdirGuard = (EditText)findViewById(R.id.dirGuard);
        EditText etpassGuard = (EditText)findViewById(R.id.passGuard);
        EditText etidAdmGua = (EditText)findViewById(R.id.idAdmGua);

        final int idGuard = Integer.parseInt(etidGuard.getText().toString());
        final String nomGuard = etnomGuard.getText().toString();
        final String apeGuard = etapeGuard.getText().toString();
        final String nacGuard = etnacGuard.getText().toString();
        final String dirGuard = etdirGuard.getText().toString();
        final String passGuard = etpassGuard.getText().toString();
        final int idAdmGua = Integer.parseInt(etidAdmGua.getText().toString());

        Response.Listener<String> respoListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean succsess = jsonResponse.getBoolean("success");

                    if(succsess)
                    {
                        Log.d("DATABASE", "Exito en el registro");
                        Intent newActivity = new Intent(AddGuardia.this, MenuAdmin.class);
                        newActivity.putExtra("nombre", nombreAdmin);
                        newActivity.putExtra("apellido", apellidoAdmin);
                        AddGuardia.this.startActivity(newActivity);
                    }
                    else
                    {
                        Log.d("DATABASE", "Fallo en el registro");
                        AlertDialog.Builder builder = new AlertDialog.Builder(AddGuardia.this);
                        builder.setMessage("Error en el registro").setNegativeButton("Vuelva a intentr", null).create().show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        RegistrarGuardia registrarGuardia = new RegistrarGuardia(idGuard, nomGuard, apeGuard, nacGuard, dirGuard, passGuard, idAdmGua, respoListener);

        RequestQueue queue = Volley.newRequestQueue(AddGuardia.this);
        queue.add(registrarGuardia);


    }

    public void cancelGua(View view)
    {
        Intent newActivity = new Intent(this, MenuAdmin.class);
        startActivity(newActivity);
    }
}
