package thingworx;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostRequest extends AsyncTask<Void, Void, String> {
    private ProgressDialog p;
    private String url;
    private String data;
    private static String appKey = "f76e9513-0bbc-4b33-af7f-09e5ea959504";


    public PostRequest(Context ctx, String url, String data) {
        this.p = new ProgressDialog(ctx);
        this.url = url;
        this.data = data;
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
        final StringBuilder response = new StringBuilder();
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("appKey", appKey);
            con.setDoOutput(true);
            DataOutputStream dos = new DataOutputStream(con.getOutputStream());
            dos.writeBytes(data);
            dos.flush();
            dos.close();
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
    }
}
