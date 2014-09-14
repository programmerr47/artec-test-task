package com.github.programmerr47.artec_test_task.api.parsers;

import com.github.programmerr47.artec_test_task.api.objects.ResponseObject;

import java.util.List;

/**
 * Interface that describes parsing from some {@link ResponseObject}
 * to entity of request.
 *
 * @author Michael Spitsin
 * @since 2014-09-13
 */
public interface ParserFrom<Result extends ResponseObject> {
    Result parseTo(String object);
    List<Result> parseToList(String array);
}
