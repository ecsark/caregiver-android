package com.care.caregiver;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.care.caregiver.conversation.ConversationListener;
import com.care.caregiver.conversation.ConversationParser;
import com.care.caregiver.conversation.ConversationQuestions;
import com.care.caregiver.util.Login;
import com.care.caregiver.util.ServerResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RedAlice64 on 2014/12/24.
 */
public class MainConversation extends Activity implements ConversationListener{
    //public static final Type[] types={Greeting.class,};
    private List<CardView> viewList;
    private Context context=this;
    private LinearLayout layout;

    public void addConversation(ConversationQuestions cQuestions){
        layout.addView(cQuestions, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
    @Override
    public void onCreate(Bundle savedInstances){
        super.onCreate(savedInstances);
        setContentView(R.layout.main_conversation);
        viewList=new ArrayList<CardView>();
        layout=(LinearLayout)findViewById(R.id.main_theme);
        ConversationQuestions cQuestions=new ConversationQuestions(this);
        CardView cardView=new CardView(this);
        TextView tag=new TextView(this);
        tag.setText("提问");
        cardView.setCardBackgroundColor(0xffffff);
        //layout.addView(cQuestions, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


/*        try {
            JsonHelper.getJsonFromUrl("http://10.171.6.238:9000/hello","{\"name\":\"Claire\"}");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        Login login=new Login();
        login.usr="aaa";
        login.pwd="bbb";
        ConversationParser.sendMessage(login,this);


    }

    @Override
    public void onResponseArrive(final ServerResponse response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                response.ShowDialog(context);

            }
        });

    }


}
