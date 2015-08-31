package com.example.utzon.monitoringactivity.Aggregators;

import com.example.utzon.monitoringactivity.Monitors.Gps_Monitor;
import com.example.utzon.monitoringactivity.Monitors.Ibeacon_Monitor;

/**
 * Created by musikværelset on 15-03-2015.
 */
public class Aggregator {
    Gps_Monitor gps = new Gps_Monitor();
    Ibeacon_Monitor iBeacon = new Ibeacon_Monitor();


    public void retriveData() {
        gps.getGpsLatitude();
        gps.getGpsLongitude();
        gps.getGpsProvider();
        gps.getGpsTime();
        iBeacon.getBeaconDistance();
        iBeacon.getBeaconLocation();
        iBeacon.getBeaconTime();


    }


}
