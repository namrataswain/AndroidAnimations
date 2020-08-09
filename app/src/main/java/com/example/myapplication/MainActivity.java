package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2}; //2 is empty

    int[][]  winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7},{2,5,8}, {0,4,8}, {2,4,6}};

    boolean gameActive = true;


    int activePLayer = 0 ; //0 for yellow and 1 for Red
   public void dropIn (View view) {
       Log.i("info", "clicked");
       ImageView counter = (ImageView) view;

       int tappedCounter = Integer.parseInt(counter.getTag().toString());

       if (gameState[tappedCounter] == 2 && gameActive) {


           gameState[tappedCounter] = activePLayer;

           counter.setTranslationY(-1500);
           if (activePLayer == 0) {
               counter.setImageResource(R.drawable.yellow);
               activePLayer = 1;
           } else {
               counter.setImageResource(R.drawable.red);
               activePLayer = 0;
           }

           counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

           for (int[] winPosition : winningPositions) {
               if (gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]] && gameState[winPosition[0]] != 2) {
                   gameActive = false;
                   String winner = "";
                   if (activePLayer == 1) {
                       winner = "Yellow";
                   } else {
                       winner = "red";
                   }


                   Button playAgainButton = findViewById(R.id.playAgainButton);
                   TextView winnerTextView = findViewById(R.id.winnerTextView);

                   winnerTextView.setText(winner+" has won");
                   playAgainButton.setVisibility(View.VISIBLE);
                   winnerTextView.setVisibility(View.VISIBLE);
               }

           }
       }
   }

   public void playAgain(View view) {

       Button playAgainButton = findViewById(R.id.playAgainButton);
       TextView winnerTextView = findViewById(R.id.winnerTextView);


       playAgainButton.setVisibility(View.INVISIBLE);
       winnerTextView.setVisibility(View.INVISIBLE);

       //how to set all the images back to empty cell without any red or yellow background

       GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

       for(int i = 0; i < gridLayout.getChildCount(); i++){
           ImageView child = (ImageView) gridLayout.getChildAt(i);

           child.setImageDrawable(null);

       }

       gameActive = true;
       Arrays.fill(gameState, 2);
       activePLayer = 0; //yellow Again

   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
