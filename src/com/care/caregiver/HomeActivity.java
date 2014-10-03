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
    TextView msg;
    Button getData;

    private static String domain = "http://10.0.2.2:9000/";
    private Gson gson = new Gson();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        msg = (TextView) findViewById(R.id.name);
        getData = (Button) findViewById(R.id.getdata);
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ExchangeGreeting().execute();
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
