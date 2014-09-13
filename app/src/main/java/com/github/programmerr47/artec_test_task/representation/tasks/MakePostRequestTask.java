package com.github.programmerr47.artec_test_task.representation.tasks;

import com.github.programmerr47.artec_test_task.api.MakePOSTRequest;
import com.github.programmerr47.artec_test_task.api.objects.RequestObject;

import org.apache.http.HttpResponse;

/**
 * @author Michael Spitsin
 * @since 2014-09-13
 */
public class MakePostRequestTask<Obj extends RequestObject, ParseResult> extends AsyncTaskWithListener<MakePOSTRequest<Obj, ParseResult>, Void, String> {
    public static final String ERROR_NO_PARAMS = "ERROR_NO_PARAMS";
    public static final String ERROR_SOMETHING_IN_QUERY = "ERROR_SOMETHING_IN_QUERY";

    @Override
    protected String doInBackground(MakePOSTRequest<Obj, ParseResult>... params) {
        if (params.length > 0) {
            MakePOSTRequest request = params[0];
            HttpResponse response = request.execute();

            if (response != null) {
                return response.getEntity().toString();
            } else {
                return ERROR_SOMETHING_IN_QUERY;
            }
        } else {
            return ERROR_NO_PARAMS;
        }
    }
}
