package example.com.example.nagat.managerapp;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    private static final String LOGINADMIN_REQUEST_URL="https://rigobertoperez.com/loginAdmin.php";
    private Map<String,String> parametros;

    public LoginRequest(int id_Admin, String password, Response.Listener<String> listener)
    {
        super(Method.POST, LOGINADMIN_REQUEST_URL, listener, null);

        parametros = new HashMap<>();
        parametros.put("id_admin", id_Admin+"");
        parametros.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        Log.d("POST BODY", "CALLED!!!" +  parametros);
        return parametros;
    }
}
