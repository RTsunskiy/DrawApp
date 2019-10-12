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

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_POINTER_DOWN;

public class DrawView extends View {

    SavedFigure figure = MainActivity.getSavedFigure();

    private static final float STROKE_WIDTH = 10f;

    private Paint mPaint = new Paint();
    private Path mPath = new Path();

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setUpPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawPath(mPath, mPaint);

        drawAnotherFigure(canvas);
    }

    private void drawAnotherFigure(Canvas canvas) {
        if (MainActivity.getSavedFigure() != null) {
            if (figure.getLineBoxes() != null && figure.getPaintLine() != null) {
                if (figure.getLineBoxes().size() > 0) {
                    for (Box box : figure.getLineBoxes()) {
                        canvas.drawLine(box.getOrigin().x, box.getOrigin().y, box.getCurrent().x, box.getCurrent().y, figure.getPaintLine());
                    }}
            }
            if (figure.getSquareBoxes() != null && figure.getPaintSquare() != null) {
                if (figure.getSquareBoxes().size() > 0) {
                    for (Box box : figure.getSquareBoxes()) {
                        float left = Math.min(box.getOrigin().x, box.getCurrent().x);
                        float right = Math.max(box.getOrigin().x, box.getCurrent().x);
                        float top = Math.min(box.getOrigin().y, box.getCurrent().y);
                        float bottom = Math.max(box.getOrigin().y, box.getCurrent().y);
                        canvas.drawRect(left, top, right, bottom, figure.getPaintSquare());
                    }}
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
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
    }

    public Path getmPath() {
        return mPath;
    }

    public Paint getmPaint() {
        return mPaint;
    }
}
