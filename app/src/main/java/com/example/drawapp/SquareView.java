package com.example.drawapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class SquareView extends View {

    private Box mCurrentBox;
    private List<Box> mBoxes = new ArrayList<>();
    private Paint mBoxPaint;
    private Paint mBackgroundPaint;

    private int squareColor = Color.BLACK;

    public void setSquareColor(int squareColor) {
        this.squareColor = squareColor;
        setUpSquarePaint();
        invalidate();
    }

    public SquareView(Context context) {
        this(context, null);
    }

    public SquareView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setUpSquarePaint();
    }

    private void setUpSquarePaint() {
        mBoxPaint = new Paint();
        mBoxPaint.setColor(squareColor);
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(Color.WHITE);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawPaint(mBackgroundPaint);

        drawAnotherFigure(canvas);

        for (Box box : mBoxes) {
            float left = Math.min(box.getOrigin().x, box.getCurrent().x);
            float right = Math.max(box.getOrigin().x, box.getCurrent().x);
            float top = Math.min(box.getOrigin().y, box.getCurrent().y);
            float bottom = Math.max(box.getOrigin().y, box.getCurrent().y);
            canvas.drawRect(left, top, right, bottom, mBoxPaint);
        }
    }




    private void drawAnotherFigure(Canvas canvas) {

        Paint drawPint = MainActivity.drawView.getmPaint();
        Path drawPath = MainActivity.drawView.getmPath();

        Paint linePaint = MainActivity.lineView.getmBoxPaint();
        List<Box> lineBox = MainActivity.lineView.getmBoxes();


        if (drawPint != null && drawPath != null) {
            canvas.drawPath(drawPath, drawPint);
        }

        if (lineBox.size() > 0 && linePaint != null) {
            for (Box box : lineBox) {
                    canvas.drawLine(box.getOrigin().x, box.getOrigin().y, box.getCurrent().x, box.getCurrent().y, linePaint);
                }
            }
        }




    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF current = new PointF(event.getX(), event.getY());

        int action = event.getAction();

        switch(action) {
            case MotionEvent.ACTION_DOWN:
                mCurrentBox = new Box(current);
                mBoxes.add(mCurrentBox);
                break;
            case MotionEvent.ACTION_MOVE:
                if (mCurrentBox != null) {
                    mCurrentBox.setCurrent(current);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mCurrentBox = null;
                break;
        }
        return true;
    }

    public List<Box> getmBoxes() {
        return mBoxes;
    }

    public Paint getmBoxPaint() {
        return mBoxPaint;
    }

    public void reset() {
        mBoxes.clear();
        invalidate();
    }
}

