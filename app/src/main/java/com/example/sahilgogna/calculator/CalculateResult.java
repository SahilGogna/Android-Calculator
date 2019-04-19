package com.example.sahilgogna.calculator;

import java.math.BigDecimal;
import java.util.ArrayList;

import Model.FinalResult;

public class CalculateResult {

    public FinalResult calculate(int number1, int number2, int userAnswer, String operation, int time){
        boolean isCorrect=false;
        int result = 0;
        FinalResult calculatedResultObj = new FinalResult();
        calculatedResultObj.setNumber1(number1);
        calculatedResultObj.setNumber2(number2);
        calculatedResultObj.setOperation(operation);
        calculatedResultObj.setUserAnswer(userAnswer);
        calculatedResultObj.setTime(time);
        switch (operation){
            case "+":
                result = number1 + number2;
                if (result == userAnswer)
                    isCorrect = true;
                break;
            case "-":
                result = number1 - number2;
                if (result == userAnswer)
                    isCorrect = true;
                break;
            case "*":
                result = number1 * number2;
                if (result == userAnswer)
                    isCorrect = true;
                break;
        }
        calculatedResultObj.setCorrectAnswer(result);
        calculatedResultObj.setCorrect(isCorrect);
//        list.add(calculatedResultObj);
        return calculatedResultObj;
    }

    public String getDisplayString(ArrayList<FinalResult> list){
        String disp = "";
        float correct = 0;
        float incorrect = 0;
        for(FinalResult element: list){
            String userResult;
            if(element.getUserAnswer() == Integer.MAX_VALUE){
                userResult = "";
            }else{
                userResult = String.valueOf(element.getUserAnswer());
            }
            if(element.isCorrect()){
                correct++;
                disp = disp.concat("\n" + element.getNumber1() + element.getOperation() + element.getNumber2() + " = "+ element.getCorrectAnswer()+"\n" + "Your answer is correct" + "\n" + "----------------");
            }else{
                incorrect++;
                disp = disp.concat("\n" +  element.getNumber1() + element.getOperation() + element.getNumber2() + " = "+ userResult +"\n"+ "Your answer is Incorrect" + "\n" + "Correct answer is "+element.getCorrectAnswer()+"\n"+"----------------");
            }
        }
        String correctString = "\n " + round((correct/list.size()),2)*100+" %"+ " Correct answers" +"\n";
        String incorrectString = "\n " + (incorrect/list.size())*100+" %"+ " Incorrect answers" +"\n";
        disp = disp.concat(correctString+incorrectString);
        return disp;
    }

    public static float round(float number, int decimalPlace) {
        BigDecimal bd = new BigDecimal(number);
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
}
