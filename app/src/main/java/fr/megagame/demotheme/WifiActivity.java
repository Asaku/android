package fr.megagame.demotheme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WifiActivity extends AppCompatActivity
{
    TextView mainText;
    WifiManager mainWifi;
    WifiReceiver receiverWifi;
    List<ScanResult> wifiList;
    StringBuilder sb = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);

        mainText = (TextView) findViewById(R.id.textView);

        // Initiate wifi service manager
        mainWifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        // Check for wifi is disabled
        if (!mainWifi.isWifiEnabled())
        {
            Toast.makeText(getApplicationContext(), "wifi is disabled..making it enabled", Toast.LENGTH_LONG).show();

            // If wifi disabled then enable it
            mainWifi.setWifiEnabled(true);
        }

        Log.i("yolo", "hellloooo");

        // wifi scaned value broadcast receiver
        receiverWifi = new WifiReceiver();

        // Register broadcast receiver
        // Broacast receiver will automatically call when number of wifi connections changed
        registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        mainWifi.startScan();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        menu.add(0, 0, 0, "Refresh");
        return super.onCreateOptionsMenu(menu);
    }

    protected void onPause()
    {
        unregisterReceiver(receiverWifi);
        super.onPause();
    }

    protected void onResume()
    {
        registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }

    public void connectTo(String ssid)
    {

        //Log.i("yolo", ssid);
        WifiConfiguration wifiConfig = new WifiConfiguration();
        wifiConfig.SSID = String.format("\"%s\"", ssid);
        wifiConfig.preSharedKey = String.format("\"%s\"", "password");

        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        //remember id
        int netId = wifiManager.addNetwork(wifiConfig);
        wifiManager.disconnect();
        wifiManager.enableNetwork(netId, true);
        wifiManager.reconnect();
    }

    // Broadcast receiver class called its receive method
    // when number of wifi connections changed

    class WifiReceiver extends BroadcastReceiver
    {
        // This method call when number of wifi connections changed
        public void onReceive(Context c, Intent intent)
        {
            WifiManager manager = (WifiManager) c.getSystemService(Context.WIFI_SERVICE);

            if (manager.isWifiEnabled()) {
                Log.i("yolo", "etape 1");
                WifiInfo wifiInfo = manager.getConnectionInfo();
                if (wifiInfo != null) {
                    Log.i("yolo", "etape 2");
                    NetworkInfo.DetailedState state = WifiInfo.getDetailedStateOf(wifiInfo.getSupplicantState());
                    if (state == NetworkInfo.DetailedState.CONNECTED || state == NetworkInfo.DetailedState.OBTAINING_IPADDR) {
                        wifiInfo.getSSID();
                        Log.i("yolo", "ssid connecté: " + wifiInfo.getSSID());
                    }
                }
            }

            Log.i("yolo", "fin");
            sb = new StringBuilder();
            wifiList = mainWifi.getScanResults();
            sb.append("\n Number Of Wifi connections :"+wifiList.size()+"\n\n");

            for(int i = 0; i < wifiList.size(); i++){

                sb.append(new Integer(i+1).toString() + ". ");
                sb.append((wifiList.get(i)).toString());
                String beforeFirstDot = (wifiList.get(i)).toString().split("\\,")[0];
                //Log.i("yolo", beforeFirstDot.replace("SSID", "").replace(":", "").trim());

                connectTo(beforeFirstDot.replace("SSID", "").replace(":", "").trim());

                sb.append("\n\n");
            }

            mainText.setText(sb);
        }
    }
}
