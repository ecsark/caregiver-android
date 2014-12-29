package com.care.caregiver;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by RedAlice64 on 2014/12/22.
 */
public class PastUploader extends Activity {
    private Button submitButton;
    private EditText nameInput;
    private EditText dateInput;
    private ListView diseaseList;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        submitButton=(Button)findViewById(R.id.submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jObject=new JSONObject();
                try {
                    jObject.put("name",nameInput.getText());
                    jObject.put("date",dateInput.getText());
                    jObject.put("list",diseaseList.getItemsCanFocus());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                QueryProcessor qProcessor=new QueryProcessor();
                qProcessor.executePost(jObject.toString());
            }
        });
    }
}
