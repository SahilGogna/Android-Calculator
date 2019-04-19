package com.example.sahilgogna.calculator;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import Model.FinalResult;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText resultInput;
    Button clear, quit;
    Button one, two, three, four, five, six, seven, eight, nine, zero, dot, generateBtn, showAll, equals, minus, save;
    String displayNumber = "";
    TextView generatedNumber;
    String[] charcters = {"+", "-", "*"};
    int number1, number2;
    String operation;
    ArrayList<FinalResult> list = new ArrayList<>();
    Spinner spinner;
    ArrayAdapter adapter;
    ArrayList<String> difficultyLevels;
    String selectedLevel;
    int start;
    TextView timerView;
    int count;
    boolean generateClicked = false;
    CountDownTimer countDownTimer; // timer object
    FinalResult resultObj; // this object is stored in ArrayList if user presses save

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intialize();
        setOnClickListeners();
    }

    private void intialize() {
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
        showAll = findViewById(R.id.showAll);
        equals = findViewById(R.id.equals);
        minus = findViewById(R.id.dash);

        spinner = findViewById(R.id.spinner);
        difficultyLevels = new ArrayList<>();
        difficultyLevels.add("Easy");
        difficultyLevels.add("Medium");
        difficultyLevels.add("Hard");
        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, difficultyLevels);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        timerView = findViewById(R.id.timerView);
        save = findViewById(R.id.save);
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
        showAll.setOnClickListener(this);
        equals.setOnClickListener(this);
        minus.setOnClickListener(this);
        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clear:
                resultInput.setText(null);
                displayNumber = "";
                generatedNumber.setText(null);
                break;

            case R.id.quit:
                finish();
                break;

            case R.id.dot:
                if (!displayNumber.contains(".")) {
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
                resultInput.setText(null);
                displayNumber = "";
                generateClicked = !generateClicked;
                if (generateClicked == true) {
                    generateBtn.setText("Stop");
                    save.setEnabled(false);
                    showAll.setEnabled(false);
                    clear.setEnabled(true);
                    equals.setEnabled(true);
                    generateRandomNumber();

                } else {
                    save.setEnabled(true);
                    showAll.setEnabled(true);
                    clear.setEnabled(false);
                    equals.setEnabled(false);
                    generateBtn.setText("Play");
                    countDownTimer.cancel();
                    calculateResult();
                }

                break;

            case R.id.showAll:
                Intent intent = new Intent(this, Result.class);
                intent.putExtra("Result List", list);
                startActivity(intent);
                break;

            case R.id.equals:
                if (!resultInput.getText().toString().equals("")) {
                    countDownTimer.cancel();
                    validateAnswer();
                } else {
                    Toast.makeText(this, "Please enter a value", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.dash:
                if (displayNumber.equals("")) {
                    displayNumber = displayNumber.concat("-");
                    resultInput.setText(displayNumber);
                }
                break;

            case R.id.save:
                list.add(resultObj);
                Toast.makeText(this, "Result Saved!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void validateAnswer() {
        timerView.setText("-");
        save.setEnabled(true);
        showAll.setEnabled(true);
        clear.setEnabled(false);
        equals.setEnabled(false);
        generateClicked = false;
        generateBtn.setText("Play");
        calculateResult();
    }

    private void calculateResult() {
        int timeElapsed = 10 - count;
        int userValue;
        if (count == 0 || resultInput.getText().toString().equals("")) {// player fails to answer
            userValue = Integer.MAX_VALUE;
        }else{
            userValue = Integer.parseInt(resultInput.getText().toString());
        }
        CalculateResult calc = new CalculateResult();
        resultObj = calc.calculate(number1, number2, userValue, operation, timeElapsed);
        Boolean result = resultObj.isCorrect();
        Toast.makeText(this, result + "", Toast.LENGTH_SHORT).show();
    }

    private void startTimer() {
        count = 10;
        countDownTimer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                count = (int) millisUntilFinished / 1000;
                timerView.setText(String.valueOf(count));
                if (count <= 6 && count >= 4) {
                    timerView.setTextColor(Color.parseColor("#ff9900"));
                } else if (count <= 3) {
                    timerView.setTextColor(Color.parseColor("#ff3300"));
                } else {
                    timerView.setTextColor(Color.parseColor("#000000"));
                }
            }

            @Override
            public void onFinish() {
                count = 0;
                validateAnswer();
            }
        };
        countDownTimer.start();
    }

    private void generateRandomNumber() {
        startTimer();
        //this part generates random number
        switch (selectedLevel) {
            case "Easy":
                start = 10;
                break;
            case "Medium":
                start = 20;
                break;
            case "Hard":
                start = 40;
                break;
        }
        resultInput.setText(null);
        Random random = new Random();
        number1 = random.nextInt(start);
        number2 = random.nextInt(start);
        int index = random.nextInt(3);
        operation = charcters[index];
        generatedNumber.setText(number1 + operation + number2);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedLevel = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
