package fr.megagame.demotheme;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends SubActivity {

    private Button btnBrowser;

    final String TAG = "main_activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnBrowser = (Button) findViewById(R.id.btnBrowser);

        btnBrowser.setOnClickListener(btnAction);
        Log.i(TAG, "name activity de ouf 2");
    }


    /**
     * Lance une application externe
     * exemple avec le navigateur par défaut
     */
    private View.OnClickListener btnAction = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            boolean flag = true;
            Intent intent = new Intent();

            if (flag) {
                intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_APP_BROWSER);
            } else {
                Uri url = Uri.parse("http://www.aston-ecole.com/");
                intent = new Intent(Intent.ACTION_VIEW, url);
            }

            startActivity(intent);
        }
    };
}
