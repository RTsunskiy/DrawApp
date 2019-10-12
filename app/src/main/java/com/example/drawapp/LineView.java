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


public class LineView extends View {
    private Box mCurrentBox;
    private List<Box> mBoxes = new ArrayList<>();
    private Paint mBoxPaint;

    SavedFigure figure = MainActivity.getSavedFigure();

    private Paint mBackgroundPaint;

    public LineView(Context context) {
        this(context, null);
    }

    public LineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mBoxPaint = new Paint();
        mBoxPaint.setColor(Color.BLUE);
        mBoxPaint.setStrokeWidth(30f);
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(Color.WHITE);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPaint(mBackgroundPaint);


        if (mBoxes.size() > 0) {
        for (Box box : mBoxes) {
                canvas.drawLine(box.getOrigin().x, box.getOrigin().y, box.getCurrent().x, box.getCurrent().y, mBoxPaint);
            }}

        drawAnotherFigure(canvas);

    }



    private void drawAnotherFigure(Canvas canvas) {
        if (MainActivity.getSavedFigure() != null) {
//            if (figure.getPath() != null && figure.getPaintDraw() != null) {
//                canvas.drawPath((Path) figure.getPath(), figure.getPaintDraw());
//            }
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
}
