package com.github.programmerr47.artec_test_task.api.parsers.jsonparsers;

import com.github.programmerr47.artec_test_task.api.objects.Position;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Michael Spitsin
 * @since 2014-09-13
 */
@SuppressWarnings("unused")
public class PositionParserToJSON extends ParserToJSON<Position> {
    private static final String LATITUDE_TAG = "lat";
    private static final String LONGITUDE_TAG = "lng";

    @Override
    public JSONObject parseFromObject(Position position) {
        JSONObject result = new JSONObject();

        try {
            result.put(LATITUDE_TAG, position.getLatitude());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            result.put(LONGITUDE_TAG, position.getLongitude());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }
}
