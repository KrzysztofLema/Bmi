package com.lema.bmi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private String bmiValue;
    private String bmiValueString;
    private ImageView mImageView;
    private Button mCountButton;
    private EditText mWeightEditText;
    private EditText mHeightEditText;
    private TextView mBmiValueTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView)findViewById(R.id.imageView);
        mBmiValueTextView = (TextView)findViewById(R.id.textView) ;
        mWeightEditText = (EditText)findViewById(R.id.weightEditText);
        mWeightEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (mWeightEditText.getText().length()<=0 || Double.parseDouble(mWeightEditText.getText().toString())>=250){
                    mWeightEditText.setError("Proszę podać poprawną wagę");
                }
            }
        });
        mHeightEditText = (EditText) findViewById(R.id.heightEditText);
        mHeightEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (mHeightEditText.getText().length()<=0 || Double.parseDouble(mHeightEditText.getText().toString())>=250 ||Double.parseDouble(mHeightEditText.getText().toString())<=15){
                    mHeightEditText.setError("Proszę podać poprawny wzrost w centymetrach");
                }
            }
        });
        mCountButton = (Button)findViewById(R.id.button);
        mCountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    bmiValueString = bmiCount(Double.parseDouble(mWeightEditText.getText().toString()),Double.parseDouble(mHeightEditText.getText().toString()));
                    imageChange();

                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(),"Proszę podać poprawne dane", Toast.LENGTH_SHORT).show();
                    mBmiValueTextView.setText("");
                }
                mBmiValueTextView.setText(bmiValueString);
            }
        });

        mBmiValueTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("BmiSend",bmiValueString);
                startActivity(intent);
            }
        });
    }

    private void imageChange() {
        if (Double.parseDouble(bmiValueString)>0||Double.parseDouble(bmiValueString)<18.5){
            mImageView.setImageResource(R.drawable.underweight);
        }else if (Double.parseDouble(bmiValueString)>18.5||Double.parseDouble(bmiValueString)<24.99){
            mImageView.setImageResource(R.drawable.healthyweight);
        }else if (Double.parseDouble(bmiValueString)>25.00||Double.parseDouble(bmiValueString)<29.99){
            mImageView.setImageResource(R.drawable.overweight);
        }else {
            mImageView.setImageResource(R.drawable.obese);
        }
    }
    public String bmiCount(double weight, double height) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        height = height/100;
        bmiValue = decimalFormat.format(weight / Math.pow(height,2));
        return bmiValue;
    }
}
