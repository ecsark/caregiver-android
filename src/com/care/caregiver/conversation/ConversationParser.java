package com.care.caregiver.conversation;

import android.content.Context;
import android.os.AsyncTask;
import com.care.caregiver.util.JsonHelper;
import com.google.gson.Gson;
import org.json.JSONException;

import java.io.IOException;

/**
 * Created by RedAlice64 on 2014/12/24.
 */
public class ConversationParser {

    private static final Gson gson=new Gson();
    public static <T> void sendMessage(T messageObject,Context context){
        new AsyncTask<T,Void,Void>(){

            @Override
            protected Void doInBackground(T... ts) {
                String jsonString;
                jsonString=gson.toJson(ts[0]);
                Object result;
                try {
                    result=JsonHelper.getJsonFromUrl("",jsonString);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
    }
}
