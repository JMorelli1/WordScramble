package com.example.wordscramble;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView txtHighScore;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtHighScore = findViewById(R.id.txtScoreDisplay);
        btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GamePage.class);
                startActivity(intent);
            }
        });

        getHighScore();
    }

    public void getHighScore(){
        SharedPreferences score = this.getSharedPreferences("highScore2", Context.MODE_PRIVATE);
        int highScore = score.getInt("highScore", 0);
        txtHighScore.setText(highScore + "");
    }
}
