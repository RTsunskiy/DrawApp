package com.example.drawapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class LineView extends View {
    private Box mCurrentBox;
    private List<Box> mBoxes = new ArrayList<>();
    private Paint mBoxPaint;

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
        setupLinePaint();
        invalidate();
    }

    private int lineColor;



    private Paint mBackgroundPaint;

    public LineView(Context context) {
        this(context, null);
    }

    public LineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setupLinePaint();
    }

    private void setupLinePaint() {
        mBoxPaint = new Paint();
        mBoxPaint.setColor(lineColor);
        mBoxPaint.setStrokeWidth(30f);
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(Color.WHITE);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPaint(mBackgroundPaint);

        drawAnotherFigure(canvas);

        if (mBoxes.size() > 0) {
        for (Box box : mBoxes) {
                canvas.drawLine(box.getOrigin().x, box.getOrigin().y, box.getCurrent().x, box.getCurrent().y, mBoxPaint);
            }}
    }



    private void drawAnotherFigure(Canvas canvas) {

        Paint drawPint = MainActivity.drawView.getmPaint();
        Path drawPath = MainActivity.drawView.getmPath();

        Paint squarePaint = MainActivity.squareView.getmBoxPaint();
        List<Box> squareBox = MainActivity.squareView.getmBoxes();

        if (drawPint != null && drawPath != null) {
            canvas.drawPath(drawPath, drawPint);
        }

        if (squareBox.size() > 0 && squarePaint != null) {
            for (Box box : squareBox) {
                        float left = Math.min(box.getOrigin().x, box.getCurrent().x);
                        float right = Math.max(box.getOrigin().x, box.getCurrent().x);
                        float top = Math.min(box.getOrigin().y, box.getCurrent().y);
                        float bottom = Math.max(box.getOrigin().y, box.getCurrent().y);
                        canvas.drawRect(left, top, right, bottom, MainActivity.squareView.getmBoxPaint());
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
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (mCurrentBox != null) {
                    mCurrentBox.setCurrent(current);
                    invalidate();
                }
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
