package mobile.esprit.pim.AsyncTaskClass;


import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import mobile.esprit.pim.Entities.Hive;
import mobile.esprit.pim.USER.SessionManager;
import mobile.esprit.pim.wowview.one;


public class AddHives {


    public void Addhive(final Hive e, Context c) {


        RequestQueue queue = Volley.newRequestQueue(c);

      /*  String url = "http://" + SessionManager.ADDRESS + "/rihab_hive/api/add_hive.php?id_user=" + one.current_user.getId_user() + "&ref=" + e.getReference() +
                "&ip=" + e.getIp() + "&lat=0&long=0";*/

        String url = ("http://pimcom.000webhostapp.com/rihab_hive/api/add_hive.php?id_user=1&ref=" + e.getReference() +
                "&ip=" + e.getIp() + "&lat=0&long=0").trim();

        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // response
                Log.d("Response", response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.getMessage());
                    }
                }
        ) {
        };
        queue.add(postRequest);

    }


}