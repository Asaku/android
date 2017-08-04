package fr.megagame.demotheme;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ConfigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        EditText userName = (EditText) findViewById(R.id.user_name);
        EditText userBirthDate = (EditText) findViewById(R.id.user_birthdate);
        EditText userEmail = (EditText) findViewById(R.id.user_email);

        String name = sharedPreferences.getString("user_name", "");
        String email = sharedPreferences.getString("user_email", "");
        String birthDate = sharedPreferences.getString("user_birthdate", "");
        //Log.i("yolo", "mavulue: " + email);

        userName.setText(name);
        userEmail.setText(email);
        userBirthDate.setText(birthDate);

        Map<String, ?> allEntries = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Log.i("yolo", entry.getKey() + ": " + entry.getValue().toString());
        }
    }

    /**
     * Set parameters global user
     * @param view The view
     */
    public void saveConfig(View view) {
        EditText userName = (EditText) findViewById(R.id.user_name);
        EditText userBirthDate = (EditText) findViewById(R.id.user_birthdate);
        EditText userEmail = (EditText) findViewById(R.id.user_email);

        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("user_name", userName.getText().toString());
        editor.putString("user_email", userEmail.getText().toString());
        editor.putString("user_birthdate", userBirthDate.getText().toString());

        editor.apply();
        editor.commit();

        OpenHttpConnection connection = (OpenHttpConnection) new OpenHttpConnection().execute("http://www.joueurdugrenier.fr/feed/");
        try {
            InputStream is = connection.get();

            readStream(is);
            Log.i("yolo", "hihou" + is.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                Log.i("yolo", "cha marche");
                i = is.read();
                Log.i("yolo", "result: " + bo.toString());
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }
}
