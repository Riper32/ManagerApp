package example.com.example.nagat.managerapp;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoadDBRequest extends StringRequest
{
    private static final String LOADDB_REQUEST_URL = "https://rigobertoperez.com/loadDB.php";
    private Map<String,String> params;

    public  LoadDBRequest(Response.Listener<String> listener)
    {
        super(Method.POST, LOADDB_REQUEST_URL, listener, null);
        params = new HashMap<>();
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
