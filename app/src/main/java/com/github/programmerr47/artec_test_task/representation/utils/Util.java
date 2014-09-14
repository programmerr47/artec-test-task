package com.github.programmerr47.artec_test_task.representation.utils;

import android.location.Location;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Michael Spitin
 * @since 2014-08-
 */
public class Util {

    public static String convertStreamToString(InputStream is) {
        if (is == null) {
            return null;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static double getDistance(double x1, double y1, double x2, double y2) {
        Location locationA = new Location("");
        locationA.setLatitude(x1);
        locationA.setLongitude(y1);
        Location locationB = new Location("");
        locationB.setLatitude(x2);
        locationB.setLongitude(y2);
        return locationA.distanceTo(locationB) ;
    }
}
