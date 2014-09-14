package com.github.programmerr47.artec_test_task.api.parsers.jsonparsers.from;

import com.github.programmerr47.artec_test_task.api.objects.Position;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Michael Spitsin
 * @since 2014-09-14
 */
@SuppressWarnings("unused")
public class PositionParserFromJSON extends ParserFromJSON<Position>{
    private static final String LATITUDE_TAG = "lat";
    private static final String LONGITUDE_TAG = "lng";

    @Override
    public Position parseTo(String strObject) {
        if (strObject == null) {
            return null;
        }

        JSONObject object;

        try {
            object = new JSONObject(strObject);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return new Position.Builder()
                .setLatitude(object.optDouble(LATITUDE_TAG, 0.0))
                .setLongitude(object.optDouble(LONGITUDE_TAG, 0.0))
                .build();
    }
}
