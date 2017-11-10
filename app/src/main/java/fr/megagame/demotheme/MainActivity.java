package fr.megagame.demotheme;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends SubActivity {

    private Button btnBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("yolo", "cha main acti");
        btnBrowser = (Button) findViewById(R.id.btnBrowser);

        btnBrowser.setOnClickListener(btnAction);
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
