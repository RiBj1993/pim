package mobile.esprit.pim.AsyncTaskClass;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import mobile.esprit.pim.Entities.Hive;
import mobile.esprit.pim.USER.SessionManager;
import mobile.esprit.pim.wowview.one;

public class PerformBackgroundTask  extends AsyncTask<ArrayList<Hive>, String, ArrayList<Hive>>  {

static public ArrayList<Hive> hives;

private Context mContext;

public PerformBackgroundTask (Context context){
        mContext = context;
        }

static String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
        }

public void readAndParseJSON(String in) {

        try {


        hives = new ArrayList();
        JSONArray list = new JSONArray(in);
        for (int p = 0; p < list.length(); p++) {
        JSONObject hive = list.getJSONObject(p);
        Hive e = new Hive(hive.getInt("id_ruche")
        , hive.getString("ip")
        , hive.getInt("reference")
        , hive.getDouble("latitude")
        , hive.getDouble("longitude")
        , hive.getDouble("weight")
        , hive.getInt("id_user")
        , hive.getDouble("temperature")
        , hive.getDouble("humidity")
        );
         hives.add(e);
        }

        } catch (Exception e) {

        }
        }
@Override
protected ArrayList<Hive> doInBackground(ArrayList<Hive>... params) {
        System.out.println("hives");
        hives = new ArrayList<Hive>();
        try {

     //   URL url = new URL("http://"+ SessionManager.ADDRESS +"/pim/AllHivesByUser.php?user="+ one.current_user.getId_user());
            //"+one.current_user.getId_user()+"
         URL url = new URL(("http://pimcom.000webhostapp.com/pim/AllHivesByUser.php?user="+ one.current_user.getId_user()).trim());
                HttpURLConnection conn = (HttpURLConnection) url
        .openConnection();
        conn.setReadTimeout(100000 /* milliseconds */);
        conn.setConnectTimeout(150000 /* milliseconds */);
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

        return hives;
        }

        }