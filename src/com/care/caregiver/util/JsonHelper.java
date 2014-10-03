package com.care.caregiver.util;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * User: ecsark
 * Date: 10/3/14
 * Time: 21:45
 */
public class JsonHelper {

    private static final Gson gson = new Gson();

    public static <T> T getJsonFromUrl(String url, Class<T> clazz) throws IOException {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();

        Reader reader = new InputStreamReader(httpEntity.getContent());

        T jsonClass = gson.fromJson(reader, clazz);
        return jsonClass;
    }

    public static <T> T getJsonFromUrl(String url, String json, Class<T> clazz) throws IOException {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(json);
        stringEntity.setContentType("application/json;charset=UTF-8");
        stringEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8"));
        httpPost.setEntity(stringEntity);
        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();

        Reader reader = new InputStreamReader(httpEntity.getContent());

        T jsonClass = gson.fromJson(reader, clazz);
        return jsonClass;
    }
}
