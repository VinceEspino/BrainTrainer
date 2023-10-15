package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout gameLayout;
    Button goButton;
    Button answerButton0;
    Button answerButton1;
    Button answerButton2;
    Button answerButton3;
    TextView sumTextView;
    TextView resultTextView;

    TextView scoreTextView;
    TextView timerTextView;

    Button playAgainButton;

    ArrayList<Integer> possibleAnswers = new ArrayList<>();
    int locationOfCorrectAnswer;

    int score = 0;
    int numberOfQuestions = 0;


    public void goOnClick(View view) {
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        newQuestion();
        timerReset();
    }

    @SuppressLint("SetTextI18n")
    public void answerOnClick(View view) {
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            resultTextView.setText("Correct");
            score++;
        } else {
            resultTextView.setText("Wrong");
        }
        numberOfQuestions++;
        resultTextView.setVisibility(View.VISIBLE);
        scoreTextView.setText(score + "/" + numberOfQuestions);
        newQuestion();
    }

    public void timerReset() {
        new CountDownTimer(30000, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(millisUntilFinished / 1000 + "s");
            }
            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                resultTextView.setText("Done");
                playAgainButton.setVisibility(View.VISIBLE);
                answerButton0.setEnabled(false);
                answerButton1.setEnabled(false);
                answerButton2.setEnabled(false);
                answerButton3.setEnabled(false);
            }
        }.start();
    }

    @SuppressLint("SetTextI18n")
    public void newQuestion() {
        Random rand = new Random();
        int numberA = rand.nextInt(21);
        int numberB = rand.nextInt(21);

        sumTextView.setText(numberA + " + " + numberB);

        locationOfCorrectAnswer = rand.nextInt(4);

        possibleAnswers.clear();
        for(int i=0; i<4; i++) {
            if (i == locationOfCorrectAnswer) {
                possibleAnswers.add(numberA + numberB);
            } else {
                int wrongAnswer = rand.nextInt(41);
                while (wrongAnswer == numberA + numberB || Arrays.asList(possibleAnswers).contains(wrongAnswer)) {
                    wrongAnswer = rand.nextInt(41);
                }
                possibleAnswers.add(wrongAnswer);
            }
        }
        answerButton0.setText(Integer.toString(possibleAnswers.get(0)));
        answerButton1.setText(Integer.toString(possibleAnswers.get(1)));
        answerButton2.setText(Integer.toString(possibleAnswers.get(2)));
        answerButton3.setText(Integer.toString(possibleAnswers.get(3)));
    }

    @SuppressLint("SetTextI18n")
    public void playAgainOnClick(View view) {
        score = 0;
        numberOfQuestions = 0;
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.INVISIBLE);
        newQuestion();
        scoreTextView.setText(score + "/" + numberOfQuestions);
        timerReset();
        answerButton0.setEnabled(true);
        answerButton1.setEnabled(true);
        answerButton2.setEnabled(true);
        answerButton3.setEnabled(true);
    }

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = (Button) findViewById(R.id.goButton);

        sumTextView = (TextView) findViewById(R.id.sumTextView);
        answerButton0 = findViewById(R.id.answerButton0);
        answerButton1 = findViewById(R.id.answerButton1);
        answerButton2 = findViewById(R.id.answerButton2);
        answerButton3 = findViewById(R.id.answerButton3);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        gameLayout = findViewById(R.id.gameLayout);
        resultTextView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);
    }
}