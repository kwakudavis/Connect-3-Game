package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.BatchUpdateException;

public class MainActivity extends AppCompatActivity {




    //0:yellow, 1:red, 2:empty
    int [] gameState = {2,2,2,2,2,2,2,2,2};

    int [][] winningPositions ={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};


    int activePlayer = 0;

    boolean gameActive = true;



        //Detect which of the images was tapped on
        public  void dropIn(View view) {

            int tappedCounter;
            //sets the image of the active view when clicked
            ImageView counter = (ImageView) view;

            tappedCounter = Integer.parseInt(counter.getTag().toString());



            if (gameState[tappedCounter] == 2 && gameActive ) {


                gameState[tappedCounter] = activePlayer;
                counter.setTranslationY(-1500);

                if (activePlayer == 0) {
                    counter.setImageResource(R.drawable.yellow);
                    activePlayer = 1;
                } else {

                    counter.setImageResource(R.drawable.red);
                    activePlayer = 0;
                }


                counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

                for (int[] winningPosition : winningPositions) {

                    if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[2]] != 2) {

                        String winner = "";
                        //There is a winner

                        gameActive = false;
                        if (activePlayer == 0) {//then 1 has won, because activeplayer has been changed

                            winner = "Red Won!";
                        } else {
                            winner = "Yellow Won!";
                        }


                        TextView winningText = (TextView)findViewById(R.id.winnerTextView);
                        winningText.setVisibility(View.VISIBLE);
                        winningText.setText(winner);

                        Button replayButton= (Button)findViewById(R.id.replayButton);

                        replayButton.setVisibility(View.VISIBLE);

                    }


                }

            }

        }

        public void playAgain(View view){

            Button replayButton= (Button)findViewById(R.id.replayButton);
            replayButton.setVisibility(View.INVISIBLE);


            TextView winningText = (TextView)findViewById(R.id.winnerTextView);
            winningText.setVisibility(View.INVISIBLE);


            //Get The Table
            TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayout);

          for (int i=0 ; i<tableLayout.getChildCount();i++){ //loop through tableRows

              TableRow row = (TableRow) tableLayout.getChildAt(i); //For each row,get the images within it and set it to null

              for (int j=0; j<row.getChildCount();j++){

                    ImageView counter = (ImageView)row.getChildAt(j);
                    counter.setImageDrawable(null);

              }


             }

            for(int i =0; i<gameState.length; i++){

                gameState[i] = 2;

            }

            activePlayer = 0;

            gameActive = true;



        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
