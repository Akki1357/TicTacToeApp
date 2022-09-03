package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean isGameActive = true;

    int player = 0;
    int[] gamestate = {2,2,2,2,2,2,2,2,2};
    int[][] winningPosition =
            {
                    {0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}
            };
    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        gamestate[tappedCounter] = player;

        if(isGameActive) {
            if (player == 0) {
                counter.setImageResource(R.drawable.reddot);
                player = 1;
            } else {
                counter.setImageResource(R.drawable.yellowdot);
                player = 0;
            }
            counter.setTranslationY(-1500);
            counter.animate().translationYBy(1500).setDuration(1000);

            for (int[] winningPosition : winningPosition) {
                if (gamestate[winningPosition[0]] == gamestate[winningPosition[1]] && gamestate[winningPosition[1]] == gamestate[winningPosition[2]] && gamestate[winningPosition[0]] != 2) {
                    //Toast.makeText(this, "someone has won", Toast.LENGTH_SHORT).show();
                    isGameActive = false;
                    if (player == 1) {
                        Toast.makeText(this, "Red has won", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Yellow has won", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(getIntent());
            }
        });

    }
}