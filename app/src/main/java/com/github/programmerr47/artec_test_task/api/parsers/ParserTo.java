package com.github.programmerr47.artec_test_task.api.parsers;

import com.github.programmerr47.artec_test_task.api.objects.RequestObject;

import java.util.List;

/**
 * Interface that describes parsing from some {@link RequestObject}
 * to entity of request.
 *
 * @author Michael Spitsin
 * @since 2014-09-13
 */
public interface ParserTo<Obj extends RequestObject, ObjectTag, ArrayTag> {
    ObjectTag parseFromObject(Obj requestObject);
    ArrayTag parseFromList(List<Obj> requestObjects);
}
