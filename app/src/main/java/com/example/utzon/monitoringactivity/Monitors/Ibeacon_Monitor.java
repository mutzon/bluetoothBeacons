package com.example.utzon.monitoringactivity.Monitors;

import com.example.utzon.monitoringactivity.Ibeacon_RangingActivity;

/**
 * Created by musikværelset on 15-03-2015.
 */
public class Ibeacon_Monitor {

    Ibeacon_RangingActivity beacon = new Ibeacon_RangingActivity();


    public String getBeaconDistance()   {
        return beacon.getDistance();
    }
    public String getBeaconLocation()   {
        return beacon.getLocation();
    }
    public long getBeaconTime() {
        return beacon.getTime();
    }




}
