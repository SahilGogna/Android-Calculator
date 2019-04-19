package com.example.sahilgogna.calculator;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import Model.FinalResult;

public class Result extends AppCompatActivity implements OnClickListener{

    Button backButton;
    TextView dispText;
    ListView listView;
    ArrayList<FinalResult> list;
    ArrayAdapter<FinalResult> resultsArrayAdapter;

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

        listView = findViewById(R.id.listView);

        resultsArrayAdapter = new ArrayAdapter<>(this,R.layout.one_item,list);
        listView.setAdapter(resultsArrayAdapter);
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
