package com.github.programmerr47.artec_test_task.api.parsers.jsonparsers;

import com.github.programmerr47.artec_test_task.api.objects.Radius;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * @author Michael Spitsin
 * @since 2014-09-13
 */
@SuppressWarnings("unused")
public class RaduisParserToJSON extends ParserToJSON<Radius>{
    private static final String MIN_TAG = "min";
    private static final String MAX_TAG = "max";

    @Override
    public JSONObject parseFromObject(Radius radius) {
        JSONObject result = new JSONObject();

        try {
            result.put(MIN_TAG, radius.getMinRadius());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            result.put(MAX_TAG, radius.getMaxRaduis());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }
}
