package fr.megagame.demotheme;

import android.os.AsyncTask;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by mehdi on 03/08/2017.
 */

public class OpenHttpConnection extends AsyncTask<String, Void, InputStream> {

    @Override
    protected InputStream doInBackground(String... params) {
        String urlstring = params[0];
        InputStream in = null;
        int response = 01;

        URL url = null;
        try {
            url = new URL(urlstring);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URLConnection conn = null;
        try {
            assert url != null;
            conn = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("yolo", "titou");

        if (!(conn instanceof HttpURLConnection)) {
            Log.i("yolo", "etape 1");
            try {
                throw new IOException("Not an HTTP connection");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Log.i("yolo", "etape 2");
        try{
            Log.i("yolo", "etape 3");
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            assert httpConn != null;
            //httpConn.setAllowUserInteraction(false);
            //httpConn.setInstanceFollowRedirects(true);
            //httpConn.setRequestMethod("Get");
            Log.i("yolo", "looool");
            httpConn.connect();
            Log.i("yolo", "etape 4");
            response = httpConn.getResponseCode();
            Log.i("yolo", "http start " + response);

            in = httpConn.getInputStream();

        }
        catch (Exception ex)
        {
            Log.i("yolo", "ERROR");
            try {
                throw new IOException("Error connecting");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.i("yolo", "end");
        return in;

    }

}

