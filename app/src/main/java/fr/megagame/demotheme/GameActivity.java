package fr.megagame.demotheme;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GameActivity extends SubActivity {

    private EditText number;
    private TextView result;
    private Button button;
    private double valueToFind = Math.floor(Math.random() * 11);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        number = (EditText) findViewById(R.id.number);
        result = (TextView) findViewById(R.id.result);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(btnAction);
    }

    private View.OnClickListener btnAction = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            int res = Integer.parseInt(number.getText().toString());

            if (res == valueToFind) {
                result.setText("Nice");
            } else {
                result.setText("Not Nice");
            }

            number.setText(null);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        menu.removeItem(R.id.game);
        return true;
    }

}
