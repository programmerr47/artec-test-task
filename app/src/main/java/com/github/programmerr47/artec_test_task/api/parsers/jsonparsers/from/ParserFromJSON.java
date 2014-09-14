package com.github.programmerr47.artec_test_task.api.parsers.jsonparsers.from;

import com.github.programmerr47.artec_test_task.api.objects.ResponseObject;
import com.github.programmerr47.artec_test_task.api.parsers.ParserFrom;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Spitsin
 * @since 2014-09-14
 */
public abstract class ParserFromJSON<ResultObject extends ResponseObject> implements ParserFrom<ResultObject> {

    @Override
    public List<ResultObject> parseToList(String array) {
        if (array == null) {
            return null;
        } else {
            try {
                JSONArray jsonArray = new JSONArray(array);
                List<ResultObject> resultList = new ArrayList<ResultObject>();
                for (int i = 0; i < array.length(); i++) {
                    JSONObject jsonObject = jsonArray.optJSONObject(i);
                    if (jsonObject != null) {
                        resultList.add(parseTo(jsonObject.toString()));
                    }
                }
                return resultList;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    protected static List<String> getStringList(JSONArray jsonArray) {
        if (jsonArray == null) {
            return null;
        }

        List<String> result = new ArrayList<String>();
        for (int i = 0; i < jsonArray.length(); i++) {
            String element = jsonArray.optString(i, null);

            if (element != null) {
                result.add(element);
            }
        }

        return result;
    }
}
