package com.myandroid.user.myapplication;

import android.os.AsyncTask;
import android.view.ViewDebug;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by User on 20/11/2018.
 */

public class fetchData extends AsyncTask<Void,Void,Void> {
    String data = "";
    String dataParsed = "";
    String singleParsed = "";
    @Override
    protected Void doInBackground(Void... params) {
        try {
            URL url = new URL("https://api.icndb.com/jokes/random/10-");
            HttpURLConnection HttpURLConnection = (java.net.HttpURLConnection) url.openConnection();
            InputStream inputStream = HttpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line ="";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }
            JSONArray JA = new JSONArray(data);
            for (int i=0;i<JA.length();i++){
                JSONObject JO = (JSONObject) JA.get(i);
                singleParsed =  "ID:" + JO.get("id")+"\n"+
                                "Joke:" + JO.get("joke")+"\n"+
                                "Category:" + JO.get("categories")+"\n";
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity.data.setText(this.data);
    }
}