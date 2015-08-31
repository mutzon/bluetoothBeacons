package com.example.utzon.monitoringactivity;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.startup.BootstrapNotifier;
import org.altbeacon.beacon.startup.RegionBootstrap;
import org.altbeacon.beacon.Region;

public class Ibeacon_MyApplicationName extends Application implements BootstrapNotifier {
    private static final String TAG = ".RangingActivity";
    private RegionBootstrap regionBootstrap;
    private BeaconManager beaconManager;
    private static final String BEACON_LAYOUT = "m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24";

    @Override
    public void onCreate() {
        super.onCreate();
        beaconManager = BeaconManager.getInstanceForApplication(this);

        beaconManager.getInstanceForApplication(this).getBeaconParsers().add(new BeaconParser().setBeaconLayout(BEACON_LAYOUT));

  //      beaconManager.setBackgroundScanPeriod(10);
        beaconManager.setBackgroundBetweenScanPeriod(100);


        Log.d(TAG, "App started up");
        // wake up the app when any beacon is seen (you can specify specific id filers in the parameters below)
       // Region region = new Region("com.example.myapp.boostrapRegion", null, null, null);
        Region region = new Region("com.example.utzon.monitoringactivity", null, null, null);
        regionBootstrap = new RegionBootstrap(this, region);
    }

    @Override
    public void didDetermineStateForRegion(int arg0, Region arg1) {
        // Don't care
    }

    @Override
    public void didEnterRegion(Region arg0) {
        Log.d(TAG, "Got a didEnterRegion call");
        Toast.makeText(getApplicationContext(), "Enter beacon region", Toast.LENGTH_SHORT).show();
        // This call to disable will make it so the activity below only gets launched the first time a beacon is seen (until the next time the app is launched)
        // if you want the Activity to launch every single time beacons come into view, remove this call.
       regionBootstrap.disable();
        Intent intent = new Intent(this, Ibeacon_RangingActivity.class);
        // IMPORTANT: in the AndroidManifest.xml definition of this activity, you must set android:launchMode="singleInstance" or you will get two instances
        // created when a user launches the activity manually and it gets launched from here.
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
    }

    @Override
    public void didExitRegion(Region arg0) {
        Log.d(TAG, "Got a didExitRegion call");
        Toast.makeText(getApplicationContext(), "Exit beacon region", Toast.LENGTH_SHORT).show();
    }
}
