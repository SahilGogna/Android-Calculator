package com.example.sahilgogna.calculator;

import java.util.ArrayList;

import Model.FinalResult;

public class CalculateResult {

    public boolean calculate(int number1, int number2, int userAnswer, String operation, ArrayList<FinalResult> list){
        boolean isCorrect=false;
        int result = 0;
        FinalResult calculatedResultObj = new FinalResult();
        calculatedResultObj.setNumber1(number1);
        calculatedResultObj.setNumber2(number2);
        calculatedResultObj.setOperation(operation);
        calculatedResultObj.setUserAnswer(userAnswer);
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
        list.add(calculatedResultObj);
        return isCorrect;
    }

    public String getDisplayString(ArrayList<FinalResult> list){
        String disp = "";
        float correct = 0;
        float incorrect = 0;
        for(FinalResult element: list){
            if(element.isCorrect()){
                correct++;
                disp = disp.concat("\n" + element.getNumber1() + element.getOperation() + element.getNumber2() + " = "+ element.getCorrectAnswer()+"\n" + "Your answer is correct" + "\n" + "----------------");
            }else{
                incorrect++;
                disp = disp.concat("\n" +  element.getNumber1() + element.getOperation() + element.getNumber2() + " = "+ element.getUserAnswer()+"\n"+ "Your answer is Incorrect" + "\n" + "Correct answer is "+element.getCorrectAnswer()+"\n"+"----------------");
            }
        }
        String correctString = "\n " + (correct/list.size())*100+" %"+ "correct answer" +"\n";
        String incorrectString = "\n " + (incorrect/list.size())*100+" %"+ "correct answer" +"\n";
        disp = disp.concat(correctString+incorrectString);
        return disp;
    }
}
