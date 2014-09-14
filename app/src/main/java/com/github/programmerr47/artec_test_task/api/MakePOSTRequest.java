package com.github.programmerr47.artec_test_task.api;

import com.github.programmerr47.artec_test_task.api.objects.RequestObject;
import com.github.programmerr47.artec_test_task.api.parsers.ParserTo;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author Michael Spitsin
 * @since 2014-09-13
 */
public class MakePOSTRequest<Obj extends RequestObject, ObjectTag, ArrayTag> {
    private static final int TIMEOUT_MILLIS = 10000;

    private String contentType;
    private ParserTo<Obj, ObjectTag, ArrayTag> parser;
    private Obj object;
    private String url;

    public MakePOSTRequest setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public MakePOSTRequest setParser(ParserTo<Obj, ObjectTag, ArrayTag> parser) {
        this.parser = parser;
        return this;
    }

    public MakePOSTRequest setObject(Obj object) {
        this.object = object;
        return this;
    }

    public MakePOSTRequest setUrl(String url) {
        this.url = url;
        return this;
    }

    public HttpResponse execute() {
        HttpClient client = new DefaultHttpClient(new BasicHttpParams());
        HttpConnectionParams.setConnectionTimeout(client.getParams(), TIMEOUT_MILLIS);
        HttpConnectionParams.setSoTimeout(client.getParams(), 2 * TIMEOUT_MILLIS);

        if (url != null) {
            HttpPost post = new HttpPost(url);
            post.setHeader(HTTP.CONTENT_TYPE, contentType);

            if ((parser != null) && (object != null)) {
                try {
                    Object entityObject = parser.parseFromObject(object);
                    StringEntity stringEntity = new StringEntity(entityObject.toString());
                    stringEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, contentType));
                    post.setEntity(stringEntity);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            HttpResponse response = null;
            try {
                response = client.execute(post);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return response;
        }

        return null;
    }
}
