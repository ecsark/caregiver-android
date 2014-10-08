package com.care.caregiver;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.care.caregiver.util.Greeting;
import com.care.caregiver.util.JsonHelper;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    TextView question;
    Button getData;

    private static String domain = "http://10.0.2.2:9000/";
    private Gson gson = new Gson();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        question = (TextView) findViewById(R.id.question);
        getData = (Button) findViewById(R.id.getdata);
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ExchangeGreeting().execute("hello", "name", "Claire");
            }
        });
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
                for (int i=1; i<params.length; i+=2) {
                    info.put(params[i],params[i+1]);
                }
                return JsonHelper.getJsonFromUrl(domain+params[0], gson.toJson(info), Greeting.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Greeting greeting) {
            pDialog.dismiss();
            if (greeting == null) {
                question.setText("Error");
            } else {
                question.setText(greeting.greeting);
            }
        }
    }
}
