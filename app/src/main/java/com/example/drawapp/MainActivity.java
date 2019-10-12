package com.example.drawapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }
    private static SavedFigure savedFigure;

    static SavedFigure getSavedFigure() {
        return savedFigure;
    }
    private void initViews() {
        final DrawView drawView = findViewById(R.id.draw_view);
        final LineView lineView = findViewById(R.id.draw_line);
        final SquareView squareView = findViewById(R.id.draw_square);

        Button resetButton = findViewById(R.id.btn_reset);
        Button drawButton = findViewById(R.id.btn_path);
        Button lineButton = findViewById(R.id.btn_line);
        Button squareButton = findViewById(R.id.btn_square);

         savedFigure = new SavedFigure();

        drawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (savedFigure != null) {
                    savedFigure.setSquareBoxes(squareView.getmBoxes());
                    savedFigure.setLineBoxes(lineView.getmBoxes());
                    savedFigure.setPaintLine(lineView.getmBoxPaint());
                    savedFigure.setPaintSquare(squareView.getmBoxPaint());
                }

                lineView.setVisibility(View.GONE);
                squareView.setVisibility(View.GONE);
                drawView.setVisibility(View.VISIBLE);
            }
        });

        lineButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               //  savedFigure.setPath((Path) drawView.getmPath());
                if (savedFigure != null) {
                    savedFigure.setSquareBoxes(squareView.getmBoxes());
                    savedFigure.setPaintSquare(squareView.getmBoxPaint());
                }



                drawView.setVisibility(View.GONE);
                squareView.setVisibility(View.GONE);
                lineView.setVisibility(View.VISIBLE);
            }
        });


        squareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (savedFigure != null) {
                    savedFigure.setLineBoxes(lineView.getmBoxes());
                    savedFigure.setPaintLine(lineView.getmBoxPaint());
                }

                lineView.setVisibility(View.GONE);
                drawView.setVisibility(View.GONE);
                squareView.setVisibility(View.VISIBLE);
            }
        });



//        resetButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawView.reset();
//            }
//        });
    }
}
