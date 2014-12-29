package com.care.caregiver.util;

import android.app.ActionBar;
import android.content.Context;
import com.care.caregiver.MainConversation;
import com.care.caregiver.conversation.ConversationQuestions;
import com.google.gson.annotations.SerializedName;

/**
 * User: ecsark
 * Date: 10/3/14
 * Time: 22:05
 */
public class Greeting extends ServerResponse{

    @SerializedName("greeting")
    public String greeting;


    @Override
    public void ShowDialog(Context context) {
        MainConversation mQuestions=(MainConversation)context;
        mQuestions.addContentView(new ConversationQuestions(mQuestions),new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
        return;
    }
}
