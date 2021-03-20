package com.example.midtermspreitzeranna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private TextView answerview;
private EditText questionview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        answerview=findViewById(R.id.textView2);
        questionview= findViewById(R.id.eu);
    }

    public void Conversion(View view) {

        try {
            String euros = questionview.getText().toString();

            //answerview.setText(euros);
            //int x= Integer.getInteger(euros);
            double euroD = Double.parseDouble(euros);

            double dollars = euroD * 1.13;
            //dollars = Math.round(dollars);
            String resultDoll = Double.toString(dollars);

            answerview.setText(resultDoll + " dollars");

        } catch (Exception e) {
            answerview.setText("Please enter how many euros");
        }

        //answerview.setText(y +" euros is " + dollars +"dollars");
    }

}