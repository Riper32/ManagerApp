package example.com.example.nagat.managerapp;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LocationRequest extends StringRequest
{
    private static final String SELECTJORNADA_REQUEST_URL="https://rigobertoperez.com/selectJornada.php";
    private Map<String,String> params;

    public LocationRequest(String fecha, int id_guardia, Response.Listener<String> listener)
    {
        super(Method.POST, SELECTJORNADA_REQUEST_URL, listener, null);

        params = new HashMap<>();
        params.put("fecha", fecha);
        params.put("id_guardia", id_guardia+"");
    }

    @Override
    public Map<String, String> getParams() {

        return params;
    }
}
