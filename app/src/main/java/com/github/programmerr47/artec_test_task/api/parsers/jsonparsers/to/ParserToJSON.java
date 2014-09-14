package com.github.programmerr47.artec_test_task.api.parsers.jsonparsers.to;

import com.github.programmerr47.artec_test_task.api.objects.RequestObject;
import com.github.programmerr47.artec_test_task.api.parsers.ParserTo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * @author Michael Spitsin
 * @since 2014-09-13
 */
public abstract class ParserToJSON<Obj extends RequestObject> implements ParserTo<Obj, JSONObject, JSONArray> {

    @Override
    public JSONArray parseFromList(List<Obj> requestObjects) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    protected JSONArray getArrayOfInts(List<Integer> integers) {
        JSONArray result = new JSONArray();

        for(Integer integer : integers) {
            result.put(integer);
        }

        return result;
    }
}
