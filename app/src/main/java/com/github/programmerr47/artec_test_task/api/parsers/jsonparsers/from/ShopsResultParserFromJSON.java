package com.github.programmerr47.artec_test_task.api.parsers.jsonparsers.from;

import com.github.programmerr47.artec_test_task.api.objects.Location;
import com.github.programmerr47.artec_test_task.api.objects.ShopsResult;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Michael Spitsin
 * @since 2014-09-14
 */
public class ShopsResultParserFromJSON extends ParserFromJSON<ShopsResult> {
    private static final String ACTIONS_TAG = "actions";
    private static final String LOCATIONS_TAG = "locations";

    @Override
    public ShopsResult parseTo(String strObject) {
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

        ParserFromJSON<Location> locationParser = new LocationParserFromJSON();
        return new ShopsResult.Builder()
                .setActions(getStringList(object.optJSONArray(ACTIONS_TAG)))
                .setLocations(locationParser.parseToList(object.optJSONArray(LOCATIONS_TAG).toString()))
                .build();
    }
}
