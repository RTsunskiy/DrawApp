package com.example.drawapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.List;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_POINTER_DOWN;

public class DrawView extends View {


    private static final float STROKE_WIDTH = 10f;

    private Paint mPaint = new Paint();
    private Path mPath = new Path();

    public void setPathColor(int pathColor) {
        this.pathColor = pathColor;
        setUpPaint();
        invalidate();
    }

    private int pathColor;

    private Paint mBackgroundPaint;

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setUpPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawPaint(mBackgroundPaint);

        drawAnotherFigure(canvas);
        canvas.drawPath(mPath, mPaint);


    }

    private void drawAnotherFigure(Canvas canvas) {

        Paint squarePaint = MainActivity.squareView.getmBoxPaint();
        List<Box> squareBox = MainActivity.squareView.getmBoxes();

        Paint linePaint = MainActivity.lineView.getmBoxPaint();
        List<Box> lineBox = MainActivity.lineView.getmBoxes();

        if (squareBox.size() > 0 && squarePaint != null) {
            for (Box box : squareBox) {
                float left = Math.min(box.getOrigin().x, box.getCurrent().x);
                float right = Math.max(box.getOrigin().x, box.getCurrent().x);
                float top = Math.min(box.getOrigin().y, box.getCurrent().y);
                float bottom = Math.max(box.getOrigin().y, box.getCurrent().y);
                canvas.drawRect(left, top, right, bottom, MainActivity.squareView.getmBoxPaint());
            }
        }

        if (lineBox.size() > 0 && linePaint != null) {
            for (Box box : lineBox) {
                canvas.drawLine(box.getOrigin().x, box.getOrigin().y, box.getCurrent().x, box.getCurrent().y, linePaint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();

        switch (event.getAction()) {
            case ACTION_DOWN:
                mPath.moveTo(eventX, eventY);
                return true;
            case ACTION_MOVE:
                mPath.lineTo(eventX, eventY);
                break;
            default:
                return false;
        }

        invalidate();
        return true;
    }

    public void reset() {
        mPath.reset();
        invalidate();
    }

    private void setUpPaint() {
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(STROKE_WIDTH);
        mPaint.setColor(pathColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(Color.WHITE);
        invalidate();
    }

    public Path getmPath() {
        return mPath;
    }

    public Paint getmPaint() {
        return mPaint;
    }
}
