package com.lema.bmi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private String bmiValue;
    private TextView mBmiTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        mBmiTextView = (TextView)findViewById(R.id.textView2);
        Bundle bundle = intent.getExtras();
        if (bundle.get("BmiSend")!=null) {
            bmiValue=bundle.getString("BmiSend");
            mBmiTextView.setText(bmiValue);
        }
    }
}
