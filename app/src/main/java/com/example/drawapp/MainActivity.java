package com.example.drawapp;


import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    public DrawView getDrawView() {
        return drawView;
    }





   static DrawView drawView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        drawView = findViewById(R.id.draw_view);

        Button resetButton = findViewById(R.id.btn_reset);
        Button drawButton = findViewById(R.id.btn_path);
        Button lineButton = findViewById(R.id.btn_line);
        Button squareButton = findViewById(R.id.btn_square);

        Button redColorButton = findViewById(R.id.btn_red);
        Button greenColorButton = findViewById(R.id.btn_green);
        Button blueColorButton = findViewById(R.id.btn_blue);


        drawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawView.setFigure(0);
            }
        });

        lineButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                drawView.setFigure(2);
            }
        });


        squareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawView.setFigure(1);
            }
        });


        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawView.reset();
            }
        });

        redColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawView.setFigureColor(0);
            }
        });

        greenColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawView.setFigureColor(1);
            }
        });

        blueColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawView.setFigureColor(2);
            }
        });
    }
}
