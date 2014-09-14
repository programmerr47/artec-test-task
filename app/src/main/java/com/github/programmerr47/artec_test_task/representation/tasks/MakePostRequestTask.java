package com.github.programmerr47.artec_test_task.representation.tasks;

import com.github.programmerr47.artec_test_task.api.MakePOSTRequest;
import com.github.programmerr47.artec_test_task.api.objects.RequestObject;
import com.github.programmerr47.artec_test_task.api.objects.ResponseObject;
import com.github.programmerr47.artec_test_task.api.parsers.ParserFrom;
import com.github.programmerr47.artec_test_task.representation.utils.Util;

import org.apache.http.HttpResponse;
import org.json.JSONObject;

import java.io.IOException;

/**
 * @author Michael Spitsin
 * @since 2014-09-13
 */
public class MakePostRequestTask<Obj extends RequestObject, ObjectTag, ArrayTag, RequestResult extends ResponseObject> extends AsyncTaskWithListener<MakePOSTRequest<Obj, ObjectTag, ArrayTag>, Void, RequestResult> {
    private ParserFrom<RequestResult> parser;

    public MakePostRequestTask(ParserFrom<RequestResult> parser) {
        this.parser = parser;
    }

    @Override
    protected RequestResult doInBackground(MakePOSTRequest<Obj, ObjectTag, ArrayTag>... params) {
        if (params.length > 0) {
            MakePOSTRequest request = params[0];
            HttpResponse response = request.execute();


            if (response != null) {
                try {
                    String queryResult = Util.convertStreamToString(response.getEntity().getContent());
                    return parser.parseTo(queryResult);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
