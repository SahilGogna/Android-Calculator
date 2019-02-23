package com.example.sahilgogna.calculator;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText resultInput;
    Button clear, quit;
    Button one,two,three,four,five,six,seven,eight,nine,zero,dot,generateBtn;
    String displayNumber="";
    TextView generatedNumber;
    String[] charcters = {"+","-","*"};
    int number1, number2;
    String operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intialize();
        setOnClickListeners();
    }

    private void intialize(){
        //editText
        resultInput = findViewById(R.id.inputText);
        resultInput.setRawInputType(InputType.TYPE_NULL); // to disable the default keyboard that appears when edit text is clicked
        resultInput.setFocusable(true); // edit text is highlighted

        //textView
        generatedNumber = findViewById(R.id.textView);
        generatedNumber.setText(null);

        //functional buttons
        clear = findViewById(R.id.clear);
        quit = findViewById(R.id.quit);
        dot = findViewById(R.id.dot);

        //numeric buttons
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        zero = findViewById(R.id.zero);

        //function buttons
        generateBtn = findViewById(R.id.generate);
    }

    private void setOnClickListeners() {
        clear.setOnClickListener(this);
        quit.setOnClickListener(this);
        dot.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);
        generateBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.clear:
                resultInput.setText(null);
                displayNumber ="";
                generatedNumber.setText(null);
                break;

            case R.id.quit:
                finish();
                break;

            case R.id.dot:
                if(!displayNumber.contains(".")){
                    displayNumber = displayNumber.concat(".");
                    resultInput.setText(displayNumber);
                }
                break;

            case R.id.one:
                displayNumber = displayNumber.concat("1");
                resultInput.setText(displayNumber);
                break;

            case R.id.two:
                displayNumber = displayNumber.concat("2");
                resultInput.setText(displayNumber);
                break;

            case R.id.three:
                displayNumber = displayNumber.concat("3");
                resultInput.setText(displayNumber);
                break;

            case R.id.four:
                displayNumber = displayNumber.concat("4");
                resultInput.setText(displayNumber);
                break;

            case R.id.five:
                displayNumber = displayNumber.concat("5");
                resultInput.setText(displayNumber);
                break;

            case R.id.six:
                displayNumber = displayNumber.concat("6");
                resultInput.setText(displayNumber);
                break;

            case R.id.seven:
                displayNumber = displayNumber.concat("7");
                resultInput.setText(displayNumber);
                break;

            case R.id.eight:
                displayNumber = displayNumber.concat("8");
                resultInput.setText(displayNumber);
                break;

            case R.id.nine:
                displayNumber = displayNumber.concat("9");
                resultInput.setText(displayNumber);
                break;

            case R.id.zero:
                displayNumber = displayNumber.concat("0");
                resultInput.setText(displayNumber);
                break;

            case R.id.generate:
                startTimer();
                break;
        }
    }

    private void startTimer(){
        CountDownTimer countDownTimer = new CountDownTimer(3000,500) {
            @Override
            public void onTick(long millisUntilFinished) {
                resultInput.setText(null);
                Random random = new Random();
                number1 = random.nextInt(100);
                number2 = random.nextInt(100);
                int index = random.nextInt(3);
                operation = charcters[index];
                generatedNumber.setText(number1+operation+number2);
            }

            @Override
            public void onFinish() {

            }
        };
        countDownTimer.start();
    }
}
