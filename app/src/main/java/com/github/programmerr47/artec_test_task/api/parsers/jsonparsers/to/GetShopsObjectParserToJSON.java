package com.github.programmerr47.artec_test_task.api.parsers.jsonparsers.to;

import com.github.programmerr47.artec_test_task.api.objects.GetShopsObject;
import com.github.programmerr47.artec_test_task.api.objects.Position;
import com.github.programmerr47.artec_test_task.api.objects.Radius;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Michael Spitsin
 * @since 2014-09-13
 */
@SuppressWarnings("unused")
public class GetShopsObjectParserToJSON extends ParserToJSON<GetShopsObject> {
    private static final String TYPE_TAG = "type";
    private static final String POSITION_TAG = "position";
    private static final String ONLY_IDS_TAG = "onlyIds";
    private static final String PROVIDER_FILTER_TAG = "providerFilter";
    private static final String SKIP_TAG = "skip";
    private static final String TOP_TAG = "top";
    private static final String TYPE_FILTER_TAG = "typeFilter";
    private static final String RADIUS_TAG = "radius";

    @Override
    public JSONObject parseFromObject(GetShopsObject getShopsObject) {
        JSONObject result = new JSONObject();

        try {
            result.put(TYPE_TAG, getShopsObject.getType());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ParserToJSON<Position> positionParserTo = new PositionParserToJSON();
        try {
            result.put(POSITION_TAG, positionParserTo.parseFromObject(getShopsObject.getPosition()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            result.put(ONLY_IDS_TAG, getShopsObject.isOnlyIds());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            result.put(PROVIDER_FILTER_TAG, getArrayOfInts(getShopsObject.getProviderFilter()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            result.put(SKIP_TAG, getShopsObject.getSkip());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            result.put(TOP_TAG, getShopsObject.getTop());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            result.put(TYPE_FILTER_TAG, getArrayOfInts(getShopsObject.getTypeFilter()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ParserToJSON<Radius> radiusParserTo = new RaduisParserToJSON();
        try {
            result.put(RADIUS_TAG, radiusParserTo.parseFromObject(getShopsObject.getRadius()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }
}
