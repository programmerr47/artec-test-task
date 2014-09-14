package com.github.programmerr47.artec_test_task.api.parsers.jsonparsers.from;

import com.github.programmerr47.artec_test_task.api.objects.Location;
import com.github.programmerr47.artec_test_task.api.objects.Position;
import com.github.programmerr47.artec_test_task.api.parsers.ParserFrom;
import com.github.programmerr47.artec_test_task.api.parsers.jsonparsers.to.ParserToJSON;
import com.github.programmerr47.artec_test_task.api.parsers.jsonparsers.to.PositionParserToJSON;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Michael Spitsin
 * @since 2014-09-14
 */
@SuppressWarnings("unused")
public class LocationParserFromJSON extends ParserFromJSON<Location>{
    private static final String ACTIONS_TAG = "actions";
    private static final String ADDRESS_TAG = "address";
    private static final String COORDS_TAG = "coords";
    private static final String ID_TAG = "id";
    private static final String NAME_TAG = "name";
    private static final String PHONE_TAG = "phone";
    private static final String SERVICES_TAG = "services";
    private static final String TYPE_TAG = "type";
    private static final String WORKING_TIME_TAG = "workingTime";

    @Override
    public Location parseTo(String strObject) {
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

        ParserFromJSON<Position> positionParser = new PositionParserFromJSON();
        return new Location.Builder()
                .setActions(getStringList(object.optJSONArray(ACTIONS_TAG)))
                .setAddress(object.optString(ADDRESS_TAG, null))
                .setCoordinates(positionParser.parseTo(object.optJSONObject(COORDS_TAG).toString()))
                .setId(object.optString(ID_TAG, null))
                .setName(object.optString(NAME_TAG, null))
                .setPhone(object.optString(PHONE_TAG, null))
                .setServices(getStringList(object.optJSONArray(SERVICES_TAG)))
                .setType(object.optInt(TYPE_TAG, 0))
                .setWorkingTime(object.optString(WORKING_TIME_TAG, null))
                .build();
    }
}
