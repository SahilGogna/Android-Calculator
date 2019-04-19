package com.example.sahilgogna.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import Model.FinalResult;

public class Result extends AppCompatActivity implements OnClickListener{

    Button backButton;
    TextView dispText;
    ArrayList<FinalResult> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Bundle b  = getIntent().getExtras();
        list = (ArrayList<FinalResult>) b.getSerializable("Result List");
        initialize();
        setOnclickListeners();
        load();
    }

    private void initialize(){
        backButton = findViewById(R.id.backButton);
        dispText = findViewById(R.id.displayText);
    }

    private void setOnclickListeners(){
        backButton.setOnClickListener(this);
    }

    private void load(){
        if(list.isEmpty()) {
            dispText.setText("You have not saved any result till now");
        }
       else{
            CalculateResult result = new CalculateResult();
            dispText.setText(result.getDisplayString(list));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backButton:
                finish();
        }
    }
}
