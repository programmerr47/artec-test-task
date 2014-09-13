package com.github.programmerr47.artec_test_task.api.util;

/**
 * @author Michael Spitsin
 * @since 2014-09-2013
 */
public enum ContentType {
    APPLICATION_JSON("application/json");

    private String mStringRepresentation;

    private ContentType(String stringRepresentation) {
        mStringRepresentation = stringRepresentation;
    }

    @Override
    public String toString() {
        return mStringRepresentation;
    }
}
