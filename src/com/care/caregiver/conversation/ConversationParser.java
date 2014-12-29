package com.care.caregiver.conversation;

import android.os.AsyncTask;
import com.care.caregiver.MainConversation;
import com.care.caregiver.util.JsonHelper;
import com.care.caregiver.util.ServerResponse;
import com.google.gson.Gson;
import org.json.JSONException;

import java.io.IOException;

/**
 * Created by RedAlice64 on 2014/12/24.
 */
public class ConversationParser {
    private static final String urlRoot="http://10.171.6.238:9000/";
    private static final Gson gson=new Gson();
    public static <T> void sendMessage(T messageObject, final MainConversation listener){
        new AsyncTask<T,Void,Void>(){

            @Override
            protected Void doInBackground(T... ts) {
                String jsonString;
                jsonString=gson.toJson(ts[0]);
                ServerResponse result=null;
                try {
                    result=JsonHelper.getJsonFromUrl(urlRoot+"users/auth",jsonString);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                ConversationListener delegate=listener;
                delegate.onResponseArrive(result);
                return null;
            }
        }.execute(messageObject);
    }
}
