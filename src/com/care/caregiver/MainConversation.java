package com.care.caregiver;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.care.caregiver.conversation.ConversationListener;
import com.care.caregiver.conversation.ConversationQuestions;
import com.care.caregiver.util.Greeting;
import com.care.caregiver.util.ServerResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RedAlice64 on 2014/12/24.
 */
public class MainConversation extends Activity implements ConversationListener{
    //public static final Type[] types={Greeting.class,};
    private List<CardView> viewList;
    @Override
    public void onCreate(Bundle savedInstances){
        super.onCreate(savedInstances);
        setContentView(R.layout.main_conversation);
        viewList=new ArrayList<CardView>();
        LinearLayout layout=(LinearLayout)findViewById(R.id.main_theme);
        ConversationQuestions cQuestions=new ConversationQuestions(this);
        CardView cardView=new CardView(this);
        TextView tag=new TextView(this);
        tag.setText("提问");
        cardView.setCardBackgroundColor(0xffffff);
        layout.addView(cQuestions);



    }

    @Override
    public void onResponseArrive(Type type, Object object) {
        ServerResponse response=(ServerResponse)object;
        response.ShowDialog(this);
    }


}
