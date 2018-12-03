package example.com.example.nagat.managerapp;

import com.android.volley.Response;
import com.android.volley.ResponseDelivery;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegistrarGuardia extends StringRequest {

    private static final String REGISTER_REQUEST_URL="https://rigobertoperez.com/registrarGuardia.php";
    private Map<String,String> parametros;

    public RegistrarGuardia(int id_Guard, String nombre, String apellido, String fecha_nac, String direccion, String password, int id_Admin, Response.Listener<String> listener)
    {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);

        parametros = new HashMap<>();
        parametros.put("id_guardia", id_Guard+"");
        parametros.put("nombre", nombre);
        parametros.put("apellido", apellido);
        parametros.put("fecha_nacimiento", fecha_nac);
        parametros.put("direccion", direccion);
        parametros.put("password", password);
        parametros.put("id_admin", id_Admin+"");
    }

    public Map<String, String> getParams() {
        return parametros;
    }
}
