package com.example.utzon.monitoringactivity.Monitors;


import com.example.utzon.monitoringactivity.Gps_Activity;

public class Gps_Monitor {

    Gps_Activity gpsActivity = new Gps_Activity();



    public String getGpsLatitude()  {
        return gpsActivity.getLat();
    }
    public String getGpsLongitude() {
        return gpsActivity.getLng();
    }
    public String getGpsProvider () {
        return  gpsActivity.getProvider().toString();
    }
    public long getGpsTime()    {
        return  gpsActivity.getGpsTime();
    }

}
