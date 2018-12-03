package example.com.example.nagat.managerapp;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class InfoGuardiRequest extends StringRequest
{
    private static final String INFOGUARD_REQUEST_URL = "https://rigobertoperez.com/selectGuardia.php";
    private Map<String,String> params;

    public InfoGuardiRequest(int id_guardia, Response.Listener<String> listener)
    {
        super(Method.POST, INFOGUARD_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("id_guardia", id_guardia+"");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
