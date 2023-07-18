package com.misterj.appofsecrets.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

public class GeocodeUtils {

    Context context;

    public GeocodeUtils(Context context) {
        this.context = context;
    }

    public String getCountry(LatLng location)
    {
        String country_name = null;
        LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        Geocoder geocoder = new Geocoder(context);
        for(String provider: lm.getAllProviders()) {
            if(location!=null) {
                try {
                    List<Address> addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1);
                    if(addresses != null && addresses.size() > 0) {
                        country_name = addresses.get(0).getCountryName();
                        break;
                    }
                } catch (IOException e) {
                    country_name = null;
                }
            }
        }
        return country_name;
    }

    public String getState(LatLng location)
    {
        String state_name = null;
        LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        Geocoder geocoder = new Geocoder(context);
        for(String provider: lm.getAllProviders()) {
            if(location!=null) {
                try {
                    List<Address> addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1);
                    if(addresses != null && addresses.size() > 0) {
                        state_name = addresses.get(0).getLocality();
                        break;
                    }
                } catch (IOException e) {
                    state_name = null;
                }
            }
        }
        return state_name;
    }

}
