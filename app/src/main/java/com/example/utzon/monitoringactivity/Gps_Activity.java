package com.example.utzon.monitoringactivity;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


public class Gps_Activity extends Activity implements LocationListener{

        private TextView latituteField;
        private TextView longitudeField;
        private LocationManager locationManager;
        private String provider;
        double lat,lng;
    long GpsTime;


        /** Called when the activity is first created. */

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_gps_);
            latituteField = (TextView) findViewById(R.id.TextView02);
            longitudeField = (TextView) findViewById(R.id.TextView04);

            // Get the location manager
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            // Define the criteria how to select the locatioin provider -> use
            // default
            Criteria criteria = new Criteria();
            provider = locationManager.getBestProvider(criteria, false);
            Location location = locationManager.getLastKnownLocation(provider);

            // Initialize the location fields
            if (location != null) {
                System.out.println("Provider " + provider + " has been selected.");
                GpsTime = System.currentTimeMillis();
                onLocationChanged(location);
            } else {
                latituteField.setText("Location not available");
                longitudeField.setText("Location not available");
            }
        }

        /* Request updates at startup */
        @Override
        protected void onResume() {
            super.onResume();
            locationManager.requestLocationUpdates(provider, 400, 1, this);
        }

        /* Remove the locationlistener updates when Activity is paused */
        @Override
        protected void onPause() {
            super.onPause();
            locationManager.removeUpdates(this);
        }

        @Override
        public void onLocationChanged(Location location) {
            double lat =  (location.getLatitude());
            double lng =  (location.getLongitude());
            latituteField.setText(String.valueOf(lat));
            longitudeField.setText(String.valueOf(lng));
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderEnabled(String provider) {
            Toast.makeText(this, "Enabled new provider " + provider,
                    Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onProviderDisabled(String provider) {
            Toast.makeText(this, "Disabled provider " + provider,
                    Toast.LENGTH_SHORT).show();
        }

        public String getLat()  {
            return String.valueOf(lat);
        }
        public String getLng()  {
            return String.valueOf(lng);
        }
    public String getProvider() {
        return String.valueOf(provider);
    }
    public long getGpsTime()    {
        return GpsTime;
    }

/*    public void setLat(double lat)  {

    }
    public void setLng(double lng)  {

    }*/

    }


