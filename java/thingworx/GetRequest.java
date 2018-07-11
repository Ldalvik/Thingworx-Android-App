package thingworx;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetRequest extends AsyncTask<Void, Void, String> {
    private ProgressDialog p;
    private String url;
    private String property;
    private Context ctx;
    private TextView tv;
    private static String appKey = "";


    public GetRequest(Context ctx, String url, String property, TextView tv) {
        this.p = new ProgressDialog(ctx);
        this.url = url;
        this.property = property;
        this.ctx = ctx;
        this.tv = tv;
    }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            p.setMessage("Loading...");
            p.setIndeterminate(false);
            p.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            p.setCancelable(false);
            p.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            StringBuilder response = new StringBuilder();
            try {
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Accept", "application/json");
                con.setRequestProperty("appKey", appKey);
                con.setDoOutput(true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return response.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            p.dismiss();
            Properties pr = new Properties(result, property);
            tv.setText(pr.getValue());
    }
}
