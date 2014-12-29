package com.care.caregiver.util;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 * User: ecsark
 * Date: 10/3/14
 * Time: 21:45
 */
public class JsonHelper {

    private static CookieStore cStore=null;

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

    public static ServerResponse getJsonFromUrl(String url, String json) throws IOException, JSONException, ClassNotFoundException {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        //if(cStore!=null)httpClient.setCookieStore(cStore);
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(json);
        stringEntity.setContentType("application/json;charset=UTF-8");
        stringEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8"));
        httpPost.setEntity(stringEntity);
        HttpResponse httpResponse = httpClient.execute(httpPost);
        StatusLine status=httpResponse.getStatusLine();
        if(status.getStatusCode()!=200){
            return new Errors();
        }
        HttpEntity httpEntity = httpResponse.getEntity();

        if(cStore==null)cStore=  httpClient.getCookieStore();
        List<Cookie> cookieList=cStore.getCookies();

        Reader reader = new InputStreamReader(httpEntity.getContent());

        JSONObject jObject=new JSONObject(reader.toString());
        String typeName=jObject.getString("type");

        Class<?> classType = Class.forName(typeName);


        ServerResponse jsonClass = (ServerResponse)gson.fromJson(reader, classType);
        return jsonClass;
    }
}
