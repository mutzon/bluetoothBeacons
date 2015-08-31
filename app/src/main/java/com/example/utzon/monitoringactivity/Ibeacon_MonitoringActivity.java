package com.example.utzon.monitoringactivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.MonitorNotifier;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.MonitorNotifier;

public class Ibeacon_MonitoringActivity extends Activity implements BeaconConsumer {
    protected static final String TAG = "RangingActivity";

    private BeaconManager beaconManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.beaconManager = BeaconManager.getInstanceForApplication(this);
        setContentView(R.layout.activity_ranging);
        BeaconParser parser = new BeaconParser();
        parser.setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24");
        beaconManager.getBeaconParsers().add(parser);
        beaconManager.bind(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        beaconManager.unbind(this);
    }
    @Override
    public void onBeaconServiceConnect() {
        beaconManager.setMonitorNotifier(new MonitorNotifier() {

    /*        @Override
            public void didEnterRegion(Region region) {
                Log.i(TAG, "I just saw an beacon for the first time!");
            }

            @Override
            public void didExitRegion(Region region) {
                Log.i(TAG, "I no longer see an beacon");
            }

            @Override
            public void didDetermineStateForRegion(int state, Region region) {
                Log.i(TAG, "I have just switched from seeing/not seeing beacons: "+state);
            }*/

            @Override
            public void didEnterRegion(org.altbeacon.beacon.Region region) {
                Log.i(TAG, "I just saw an beacon for the first time!");
            }

            @Override
            public void didExitRegion(org.altbeacon.beacon.Region region) {
                Log.i(TAG, "I no longer see an beacon");
            }

            @Override
            public void didDetermineStateForRegion(int i, org.altbeacon.beacon.Region region) {
                Log.i(TAG, "I no longer see an beacon");
            }
        });

        try {
            beaconManager.startMonitoringBeaconsInRegion(new org.altbeacon.beacon.Region ("myMonitoringUniqueId", null, null, null));
        } catch (RemoteException e) {   }
    }

}
