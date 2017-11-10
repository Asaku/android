package fr.megagame.demotheme;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import static android.R.layout.simple_list_item_1;

public class CallRestApi extends SubActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_rest_api);
        final ListView list = (ListView) findViewById(R.id.listview);

        new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL("http://192.168.1.24:3000/");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                HttpURLConnection urlConnection = null;
                try {
                    assert url != null;
                    urlConnection = (HttpURLConnection) url.openConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    assert urlConnection != null;
                    urlConnection.setRequestMethod("GET");
                } catch (ProtocolException e) {
                    e.printStackTrace();
                }

                try {
                    urlConnection.getInputStream();
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    ArrayList result = readStream(in);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(CallRestApi.this, simple_list_item_1, result);
                    list.setAdapter(adapter);

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                } finally {
                    urlConnection.disconnect();
                }
            }
        }).start();
    }



    private ArrayList readStream(InputStream is) throws JSONException {
        Scanner scanner = new Scanner(is);
        ArrayList arrayList = new ArrayList<String>();

        JSONObject jsonObj = null;
        jsonObj = new JSONObject(scanner.nextLine());

        for(int i = 0; i<jsonObj.names().length(); i++){
            arrayList.add(i,  jsonObj.get(jsonObj.names().getString(i)));
        }

        return arrayList;
    }
}
