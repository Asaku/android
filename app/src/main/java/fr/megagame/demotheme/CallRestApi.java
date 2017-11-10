package fr.megagame.demotheme;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.InputStream;
import java.util.concurrent.ExecutionException;

public class CallRestApi extends SubActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("yolo", "CallRestApi");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_rest_api);
    }

    public void callUrl(View view)
    {
        Log.i("yolo", "callUrl here");
        OpenHttpConnection connection = (OpenHttpConnection) new OpenHttpConnection().execute("http://localhost:3000/");
        try {
            InputStream is = connection.get();

            //OpenHttpConnection.readStream(is);
            Log.i("yolo", "hihou" + is.toString());

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
