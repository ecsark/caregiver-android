package com.care.caregiver;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity{
    private Button login;
    private EditText username;
    private EditText password;
    private QueryProcessor processor;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        login=(Button)findViewById(R.id.login);
        username=(EditText)findViewById(R.id.editUsername);
        password=(EditText)findViewById(R.id.editPassword);
        setContentView(R.layout.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirstLogin fLogin=new FirstLogin();
                fLogin.doInBackground("{usr:xxx,pwd:yyy}");
            }
        });
    }
    private class FirstLogin extends AsyncTask<String,Void,String>{
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pDialog=new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Logging in...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            QueryProcessor qProcessor=new QueryProcessor();
            return qProcessor.executePost(strings[0]);
        }
        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            pDialog.dismiss();
            Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(intent);
        }
    }
}
