package com.example.drawapp;


import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    public DrawView getDrawView() {
        return drawView;
    }

    public LineView getLineView() {
        return lineView;
    }

    public SquareView getSquareView() {
        return squareView;
    }

   static DrawView drawView;
   static LineView lineView;
   static SquareView squareView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        drawView = findViewById(R.id.draw_view);
        lineView = findViewById(R.id.draw_line);
        squareView = findViewById(R.id.draw_square);

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
                lineView.setVisibility(View.GONE);
                squareView.setVisibility(View.GONE);
                drawView.setVisibility(View.VISIBLE);
            }
        });

        lineButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                drawView.setVisibility(View.GONE);
                squareView.setVisibility(View.GONE);
                lineView.setVisibility(View.VISIBLE);
            }
        });


        squareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lineView.setVisibility(View.GONE);
                drawView.setVisibility(View.GONE);
                squareView.setVisibility(View.VISIBLE);
            }
        });


        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lineView.reset();
                drawView.reset();
                squareView.reset();
            }
        });

        redColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lineView.setLineColor(getResources().getColor(R.color.colorRed));
                drawView.setPathColor(getResources().getColor(R.color.colorRed));
                squareView.setSquareColor(getResources().getColor(R.color.colorRed));
            }
        });

        greenColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lineView.setLineColor(getResources().getColor(R.color.colorGreen));
                drawView.setPathColor(getResources().getColor(R.color.colorGreen));
                squareView.setSquareColor(getResources().getColor(R.color.colorGreen));
            }
        });

        blueColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lineView.setLineColor(getResources().getColor(R.color.colorBlue));
                drawView.setPathColor(getResources().getColor(R.color.colorBlue));
                squareView.setSquareColor(getResources().getColor(R.color.colorBlue));
            }
        });
    }
}
