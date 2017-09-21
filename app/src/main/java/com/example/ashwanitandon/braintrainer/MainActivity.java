package com.example.ashwanitandon.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton,answer1,answer2,answer3,answer4,playAgainButton;
    TextView ques,answerCorrect,score,time;
    int locationofCorrectAnswer=0;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    int scores=0;
    int numberOfQuestions=0;
    RelativeLayout relative;
    GridLayout grid;

    public void first(View view){
        startButton.setVisibility(View.INVISIBLE);
        relative.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
    }

    public void playAgain(View view){
        scores=0;
        time.setText("30s");
        numberOfQuestions=0;
        answerCorrect.setText("");
        score.setText("0/0");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();
        answer1.setEnabled(true);
        answer2.setEnabled(true);
        answer3.setEnabled(true);
        answer4.setEnabled(true);

        new CountDownTimer(30100,1000) {

            @Override
            public void onTick(long l) {
                time.setText(String.valueOf(l/1000)+"s");

            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                time.setText("0s");
                answerCorrect.setText("Your Score-:"+Integer.toString(scores)+" / "+Integer.toString(numberOfQuestions));
                generateQuestion();
                answer1.setEnabled(false);
                answer2.setEnabled(false);
                answer3.setEnabled(false);
                answer4.setEnabled(false);



            }
        }.start();
    }

    public void generateQuestion(){

        Random rand=new Random();
        int a=rand.nextInt(101);
        int b=rand.nextInt(101);

        ques.setText(Integer.toString(a)+" + " +Integer.toString(b));

        locationofCorrectAnswer=rand.nextInt(4);
        answers.clear();
        int inCorrectAnswer=0;
        for(int i=0;i<4;i++){

            if(i==locationofCorrectAnswer){
                answers.add(a+b);
            }
            else{
                inCorrectAnswer=rand.nextInt(201);

                while (inCorrectAnswer==a+b){
                    inCorrectAnswer=rand.nextInt(201);
                }

                answers.add(inCorrectAnswer);
            }
        }

        answer1.setText(Integer.toString((answers.get(0))));
        answer2.setText(Integer.toString((answers.get(1))));
        answer3.setText(Integer.toString((answers.get(2))));
        answer4.setText(Integer.toString((answers.get(3))));
    }

    public void getAnswer(View view){

        if(view.getTag().toString().equals(Integer.toString(locationofCorrectAnswer))){
            answerCorrect.setText("Correct");
            scores++;
        }
        else{
            answerCorrect.setText("WRONG!!!");
        }
        numberOfQuestions++;
        score.setText(Integer.toString(scores)+" / "+Integer.toString(numberOfQuestions));
        generateQuestion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton=(Button)findViewById(R.id.startButton);
        answer1=(Button)findViewById(R.id.answer1);
        answer2=(Button)findViewById(R.id.answer2);
        answer3=(Button)findViewById(R.id.answer3);
        answer4=(Button)findViewById(R.id.answer4);
        ques=(TextView)findViewById(R.id.ques);
        answerCorrect=(TextView)findViewById(R.id.answeCorrect);
        score=(TextView)findViewById(R.id.score);
        time=(TextView) findViewById(R.id.time);
        playAgainButton=(Button) findViewById(R.id.playAgainButton);
        relative=(RelativeLayout)findViewById(R.id.relative);



    }

}
