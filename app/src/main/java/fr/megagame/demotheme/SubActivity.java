package fr.megagame.demotheme;

import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;

public class SubActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, MainActivity.class);
        switch(item.getItemId()) {
            case R.id.home:
                startActivity(intent);
                return true;
            case R.id.game:
                intent = new Intent(this, GameActivity.class);
                startActivity(intent);
                return true;
            case R.id.rest_api:
                intent = new Intent(this, CallRestApi.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        int action = MotionEventCompat.getActionMasked(event);

        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                Toast.makeText(getApplicationContext(), "en bas", Toast.LENGTH_SHORT).show();
                return true;
            case (MotionEvent.ACTION_MOVE) :
                //Toast.makeText(getApplicationContext(), "haha bouge", Toast.LENGTH_SHORT).show();
                return true;
            case (MotionEvent.ACTION_UP) :
                Toast.makeText(getApplicationContext(), "en haut", Toast.LENGTH_SHORT).show();
                return true;
            case (MotionEvent.ACTION_CANCEL) :
                Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
                return true;
            case (MotionEvent.ACTION_OUTSIDE) :
                Toast.makeText(getApplicationContext(), "exterieur", Toast.LENGTH_SHORT).show();
                return true;
            default :
                return super.onTouchEvent(event);
        }
    }
}
