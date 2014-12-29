package com.care.caregiver.util;

import android.content.Context;
import com.care.caregiver.MainConversation;
import com.care.caregiver.conversation.ConversationQuestions;

/**
 * Created by RedAlice64 on 2014/12/29.
 */
public class Errors extends ServerResponse{
    @Override
    public void ShowDialog(Context context) {
        MainConversation mQuestions=(MainConversation)context;
        mQuestions.addConversation(new ConversationQuestions(context));

    }
}
