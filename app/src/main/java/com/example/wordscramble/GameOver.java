package com.example.wordscramble;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {

    int score = 0;
    TextView txtNewHigh;
    TextView txtScore;
    TextView txtHighScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        txtScore = findViewById(R.id.txtFinalScore);
        txtNewHigh = findViewById(R.id.txtNewHigh);
        txtHighScore = findViewById(R.id.txtHighscore);
        getScore();
        setHighScore();
        txtScore.setText(score + "");
        endScreen();
    }

    public void getScore(){
        SharedPreferences gameScore = this.getSharedPreferences("scoreKey", Context.MODE_PRIVATE);
        score = gameScore.getInt("newScore", 0);
    }

    public void setHighScore(){
        SharedPreferences highscore = this.getSharedPreferences("highScore2", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = highscore.edit();
        int currentHighscore = highscore.getInt("highScore", 0);
        if(score > currentHighscore){
            txtNewHigh.setText("New High Score!");
            txtHighScore.setText(score + "");
            editor.putInt("highScore", score);
            editor.commit();
        }
        else{
            txtHighScore.setText(currentHighscore+"");
        }
    }

    public void endScreen(){
        new CountDownTimer(10000, 1000){
            public void onTick(long millisUntilFinished){

            }
            public void onFinish(){
                Intent intent = new Intent(GameOver.this, MainActivity.class);
                startActivity(intent);
            }
        }.start();
    }
}
