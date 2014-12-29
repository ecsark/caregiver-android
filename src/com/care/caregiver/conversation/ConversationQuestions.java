package com.care.caregiver.conversation;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.care.caregiver.R;

/**
 * Created by RedAlice64 on 2014/12/25.
 */
public class ConversationQuestions extends LinearLayout{
    private TextView question;
    public ConversationQuestions(Context context) {
        super(context);
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.conversation_questions,this);
        question=(TextView)findViewById(R.id.question_text);
        question.setText("我的问题在这里");

    }
}
