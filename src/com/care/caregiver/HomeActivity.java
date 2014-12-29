package com.care.caregiver;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.care.caregiver.util.Greeting;
import com.care.caregiver.util.JsonHelper;
import com.google.gson.Gson;
import android.graphics.Color;
import android.view.ViewGroup.LayoutParams;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    TextView msg;
    Button getData;

    private static String domain = "http://10.171.6.238:9000/";
    private Gson gson = new Gson();

    private String descriptions[][]={{"腹痛","感冒","发烧","体虚"},{"5分钟","10分钟","30分钟","1小时"},{"轻微","显著","严重","剧烈"},{"持续","阵发","阵发伴随持续","其他"}};
    private String response[]={"你好，有哪里不舒服？","症状持续了多久？","疼痛严重吗？","症状发作时是持续还是阵发的？","用手在腹部顺时针揉动，按摩，多喝热水，或者用热水敷肚都可以缓解疼痛。"};
    private int progress=0;
    LinearLayout downLayout;

    RadioGroup symptoms;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        Intent intent=new Intent(HomeActivity.this,MainConversation.class);
        //if(!savedInstanceState.containsKey("loginsuccess"))
        startActivity(intent);

        msg = (TextView) findViewById(R.id.name);
        getData = (Button) findViewById(R.id.getdata);
        downLayout=(LinearLayout)findViewById(R.id.downlowinterface);
        symptoms=new RadioGroup(this);
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ExchangeGreeting().execute();
            }
        });
        updateView();
        msg.setText(response[progress]);
        /*getData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                downLayout.removeAllViews();
                msg.setText(response[++progress]);
                if(progress<4){
                    updateView();
                }else{
                    LayoutParams param=msg.getLayoutParams();

                    progress=0;
                    downLayout.removeAllViews();
                    updateView();
                }
            }
        });*/
    }

    private void updateView(){
        downLayout.removeAllViews();
        symptoms.removeAllViews();
        symptoms.setPadding(200,20,20,20);
        for(int i=0;i<4;i++){
            RadioButton symptom=new RadioButton(this);
            symptom.setPadding(20,100,20,20);
            symptom.setTextColor(Color.rgb(0,0,0));
            symptom.setText(descriptions[progress][i]);
            symptoms.addView(symptom);
        }
        downLayout.addView(symptoms);
    }

    private class ExchangeGreeting extends AsyncTask<String, Integer, Greeting> {

        private ProgressDialog pDialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(HomeActivity.this);
            pDialog.setMessage("Connecting server");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected Greeting doInBackground(String... params) {
            try {
                Map<String, String> info = new HashMap<>();
                info.put("name","Claire");
                return JsonHelper.getJsonFromUrl(domain+"hello", gson.toJson(info), Greeting.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Greeting greeting) {
            pDialog.dismiss();
            if (greeting == null) {
                msg.setText("Error");
            } else {
                msg.setText(greeting.greeting);
            }
        }
    }
}
