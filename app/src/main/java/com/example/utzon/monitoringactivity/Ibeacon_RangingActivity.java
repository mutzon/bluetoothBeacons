package com.example.utzon.monitoringactivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.Collection;

/// test Dennis Commit    ///

public class Ibeacon_RangingActivity extends Activity implements BeaconConsumer {
    private static final String BEACON_MONITORING_ID ="pitlab";
    private static final String BEACON_LAYOUT = "m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24";
 //private static final String BEACON_LAYOUT = "1E 02 01 1A 1A FF 4C 00 02 15 6f 2A d7 1e b7 50 11 e4 89 f3 68 f7 13";
 private static final String BEACONCHEX = "49 54 55 20 34 41 30 35 20 20 20 20 20 20 20 20";
   private static final String BEACONCODE = BEACONCHEX.replace(" ", "");
    protected static final String TAG = "RangingActivity";
    String distance;
    String location;
    BeaconManager beaconManager;
    long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranging);
        verifyBluetooth();
        beaconManager = BeaconManager.getInstanceForApplication(this);
        //addBeacoLayout(BEACON_LAYOUT);
        BeaconParser parser = new BeaconParser();
        parser.setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24");
     //   parser.setBeaconLayout("1E 02 01 1A 1A FF 4C 00 02 15 6f 2A d7 1e b7 50 11 e4 89 f3 68 f7 13");
      //  parser.setBeaconLayout("49 54 55 20 34 41 30 35 20 20 20 20 20 20 20 20");
        beaconManager.getBeaconParsers().add(parser);
        beaconManager.bind(this);
        //      beaconManager.setBackgroundScanPeriod(10);
        beaconManager.setBackgroundBetweenScanPeriod(100);
    }
    public BeaconParser addBeacoLayout(String layout)   {
        BeaconParser parser = new BeaconParser();
        parser.setBeaconLayout(layout);
        return  parser;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        beaconManager.unbind(this);
    }

    @Override
    public void onBeaconServiceConnect() {

        beaconManager.setRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                time = System.currentTimeMillis();
                if (beacons.size() > 0) {
                    Log.i(TAG, "The first beacon I see is about "+beacons.iterator().next().getDistance()+" meters away. It is located at:  " + hexToASCII2(BEACONCODE) + " at" + time );

                   double dist = (double)beacons.iterator().next().getDistance();
                   distance = Double.toString(dist);
                   location = hexToASCII2(BEACONCODE);


                    }

                }


        });





        try {
            beaconManager.startRangingBeaconsInRegion(new org.altbeacon.beacon.Region ("BEACONCODE", null, null, null));
        } catch (RemoteException e) {   }

    }
    public static String hexToASCII2(String BEACONCode){

        StringBuilder sb = new StringBuilder();
        for( int i=0; i < BEACONCODE.length()-1; i+=2 ){
            String output = BEACONCODE.substring(i, (i + 2));
            int decimal = Integer.parseInt(output, 16);
            sb.append((char)decimal);
        }
        return sb.toString();

    }
    public String getLocation() {
        return location.toString();
    }

    public String getDistance() {
        return distance;
    }
    public long getTime()   {
        return time;
    }

    //Verify bluetooth capabilieties
    private void verifyBluetooth() {
        try {
            if (!BeaconManager.getInstanceForApplication(this).checkAvailability()) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Bluetooth not enabled");
                builder.setMessage("Please enable bluetooth in settings and restart this application.");
                builder.setPositiveButton(android.R.string.ok, null);
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        finish();
                        System.exit(0);
                    }
                });
                builder.show();
            }
        }
        catch (RuntimeException e) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Bluetooth LE not available");
            builder.setMessage("Sorry, this device does not support Bluetooth LE.");
            builder.setPositiveButton(android.R.string.ok, null);
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    finish();
                    System.exit(0);
                }
            });
            builder.show();
        }
    }
}