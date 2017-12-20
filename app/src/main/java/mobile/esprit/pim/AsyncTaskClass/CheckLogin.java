package mobile.esprit.pim.AsyncTaskClass;

import android.os.AsyncTask;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class CheckLogin extends AsyncTask<String, String, String> {
    static public String response;

    static String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection conn = null;
        String dsds = null;
        try {
            URL url;
            url = new URL(params[0]);
            System.out.println(url);
            conn = (HttpURLConnection) url.openConnection();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = conn.getInputStream();
                response = convertStreamToString(is);
                System.out.println("r" + response);
            } else {
                InputStream err = conn.getErrorStream();
            }
            System.out.println("Done");
            return dsds;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return response;
    }
}