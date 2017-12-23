package mobile.esprit.pim.AsyncTaskClass;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import mobile.esprit.pim.Entities.User;
import mobile.esprit.pim.USER.SessionManager;

public class GetUserByMail extends AsyncTask<User, String, User> {

    public static User user;
    private String mail;
    private Context mContext;

    public GetUserByMail(Context context, String mail) {
        mContext = context;
        this.mail = mail;
    }

    static String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public void readAndParseJSON(String in) {

        try {
            JSONArray list = new JSONArray(in);
            for (int p = 0; p < list.length(); p++) {
                JSONObject jsonuser = list.getJSONObject(p);
                user = new User(jsonuser.getInt("id_user")
                        , jsonuser.getString("mail_user")
                        , jsonuser.getString("mdp_user")
                );
                System.out.println(user);
            }

        } catch (Exception e) {

        }
    }

    @Override
    protected User doInBackground(User... params) {
        System.out.println("hives");

        try {
         //   URL url = new URL("http://" + SessionManager.ADDRESS + "/pim/GetUserByMail.php?mail=" + mail);
            URL url = new URL("http://rihabbeji.0fees.us/pim/GetUserByMail.php?mail=" + mail);
            HttpURLConnection conn = (HttpURLConnection) url
                    .openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            InputStream stream = conn.getInputStream();

            String data = convertStreamToString(stream);

            readAndParseJSON(data);
            stream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

}

