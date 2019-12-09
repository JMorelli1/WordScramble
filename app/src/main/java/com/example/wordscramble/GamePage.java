package com.example.wordscramble;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GamePage extends AppCompatActivity {

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btnRemove;
    private Button btnEnter;
    private TextView txtAnswer;
    private TextView txtScramble;
    private TextView txtMessage;
    private TextView txtScore;
    private ArrayList<String> Letters;
    private String [] listArray;
    private int score = 0;
    private ProgressBar pClock;
    private int time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btnEnter = findViewById(R.id.btnEnter);
        btnRemove = findViewById(R.id.btnRemove);
        txtAnswer = findViewById(R.id.txtAnswer);
        txtMessage = findViewById(R.id.txtMessage);
        txtScramble = findViewById(R.id.txtScramble);
        txtScore = findViewById(R.id.txtScore);
        pClock = findViewById(R.id.pClock);
        Letters = new ArrayList<>();

        startScramble();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtAnswer.append(btn1.getText());
                btn1.setEnabled(false);
                Letters.add(btn1.getText().toString());
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtAnswer.append(btn2.getText());
                btn2.setEnabled(false);
                Letters.add(btn2.getText().toString());
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtAnswer.append(btn3.getText());
                btn3.setEnabled(false);
                Letters.add(btn3.getText().toString());
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtAnswer.append(btn4.getText());
                btn4.setEnabled(false);
                Letters.add(btn3.getText().toString());
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtAnswer.append(btn4.getText());
                btn4.setEnabled(false);
                Letters.add(btn4.getText().toString());
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtAnswer.append(btn5.getText());
                btn5.setEnabled(false);
                Letters.add(btn5.getText().toString());
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtAnswer.append(btn6.getText());
                btn6.setEnabled(false);
                Letters.add(btn6.getText().toString());
            }
        });

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkWord();
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtAnswer.getText().toString().length() > 0) {
                    txtAnswer.setText(txtAnswer.getText().subSequence(0, (txtAnswer.length() - 1)));
                    enableButtons();
                }
            }
        });
    }

    public void startScramble(){
        switch ((int)(Math.random()*6)+ 1){
            case 1:
                listArray = getResources().getStringArray(R.array.list1);
                txtScramble.setText(listArray[6]);
                setButtons();
                break;
            case 2:
                listArray = getResources().getStringArray(R.array.list2);
                txtScramble.setText(listArray[6]);
                setButtons();
                break;
            case 3:
                listArray = getResources().getStringArray(R.array.list3);
                txtScramble.setText(listArray[6]);
                setButtons();
                break;
            case 4:
                listArray = getResources().getStringArray(R.array.list4);
                txtScramble.setText(listArray[6]);
                setButtons();
                break;
            case 5:
                listArray = getResources().getStringArray(R.array.list5);
                txtScramble.setText(listArray[6]);
                setButtons();
                break;
            case 6:
                listArray = getResources().getStringArray(R.array.list6);
                txtScramble.setText(listArray[6]);
                setButtons();
                break;
        }
        gameClock();
    }

    public void setButtons(){
        btn1.setText(listArray[0]);
        btn2.setText(listArray[1]);
        btn3.setText(listArray[2]);
        btn4.setText(listArray[3]);
        btn5.setText(listArray[4]);
        btn6.setText(listArray[5]);
    }

    public void enableButtons() {
        if (Letters.get(Letters.size() - 1).equals(btn1.getText())) {
            Letters.remove(Letters.size() - 1);
            btn1.setEnabled(true);
        } else if (Letters.get(Letters.size() - 1).equals(btn2.getText())) {
            Letters.remove(Letters.size() - 1);
            btn2.setEnabled(true);
        } else if (Letters.get(Letters.size() - 1).equals(btn3.getText())) {
            Letters.remove(Letters.size() - 1);
            btn3.setEnabled(true);
        } else if (Letters.get(Letters.size() - 1).equals(btn4.getText())) {
            Letters.remove(Letters.size() - 1);
            btn4.setEnabled(true);
        } else if (Letters.get(Letters.size() - 1).equals(btn5.getText())) {
            Letters.remove(Letters.size() - 1);
            btn5.setEnabled(true);
        } else if (Letters.get(Letters.size() - 1).equals(btn6.getText())) {
            Letters.remove(Letters.size() - 1);
            btn6.setEnabled(true);
        }
    }

    public void clearAnswer(){
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);
        btn5.setEnabled(true);
        btn6.setEnabled(true);
        txtAnswer.setText("");
    }

    public void checkWord(){
        String answer = txtAnswer.getText().toString().toLowerCase();
        Boolean correct = false;
        for(int i = 6; i < listArray.length; i++){
            if(answer.equals(listArray[i])){
                addScore();
                clearAnswer();
                correct = true;
            }
        }
        if(correct == false) {
            txtMessage.setText("Incorrect answer! Try again!");
            clearAnswer();
        }
    }

    public void addScore(){
        int len = txtAnswer.getText().toString().length();
        switch (len){
            case 2:
                score += 25;
                txtMessage.setText("Correct! You earned 25 points!");
                break;
            case 3:
                score += 50;
                txtMessage.setText("Correct! You earned 50 points!");
                break;
            case 4:
                score += 75;
                txtMessage.setText("Correct! You earned 75 points!");
                break;
            case 5:
                score += 100;
                txtMessage.setText("Correct! You earned 100 points!");
                break;
            case 6:
                score += 150;
                txtMessage.setText("Correct! You earned 150 points!");
                break;
        }
        txtScore.setText(score + "");
    }

    public void gameClock(){
        CountDownTimer gameTimer = new CountDownTimer(20000,1000){
            public void onTick(long millisUntilFinished){
                pClock.setProgress(time);
                time += 1;
            }
            public void onFinish(){
                setScore();
                Intent intent = new Intent(GamePage.this, GameOver.class);
                startActivity(intent);
            }
        };
        gameTimer.start();
    }

    public void setScore(){
        SharedPreferences gameScore = this.getSharedPreferences("scoreKey", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = gameScore.edit();
        editor.putInt("newScore", score);
        editor.commit();
    }
}
