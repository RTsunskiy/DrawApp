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

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_POINTER_DOWN;

public class DrawView extends View {

    private MainActivity.Figure figureType;
    private int figureColor;


    private static final float STROKE_WIDTH = 10f;
    private Paint mPaintRed = new Paint();
    private Paint mPaintGreen = new Paint();
    private Paint mPaintBlue = new Paint();
    private Path mPathRed = new Path();
    private Path mPathGreen = new Path();
    private Path mPathBlue = new Path();



    private Box mLineCurrentBox;
    private List<Box> mLineBoxesRed = new ArrayList<>();
    private List<Box> mLineBoxesGreen = new ArrayList<>();
    private List<Box> mLineBoxesBlue = new ArrayList<>();
    private Paint mLineBoxPaintRed;
    private Paint mLineBoxPaintGreen;
    private Paint mLineBoxPaintBlue;

    private Box mSquareCurrentBox;
    private List<Box> mSquareBoxesRed = new ArrayList<>();
    private List<Box> mSquareBoxesGreen = new ArrayList<>();
    private List<Box> mSquareBoxesBlue = new ArrayList<>();
    private Paint mSquareBoxPaintRed;
    private Paint mSquareBoxPaintGreen;
    private Paint mSquareBoxPaintBlue;

    private MultiDraw mCurrentMultiFigure;
    private List<MultiDraw> mMultiFigures = new ArrayList<>();
    private int mCurrentColor;

    private Paint mBackgroundPaint;



    public void setFigure(MainActivity.Figure figureType) {
        this.figureType = figureType;
        invalidate();
    }

    public void setFigureColor(int figureColor) {
        this.figureColor = figureColor;
    }


    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setUpPaintRed();
        setUpPaintGreen();
        setUpPaintBlue();
        setUpSquarePaintRed();
        setUpSquarePaintGreen();
        setUpSquarePaintBlue();
        setupLinePaintRed();
        setupLinePaintGreen();
        setupLinePaintBlue();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawPaint(mBackgroundPaint);


                canvas.drawPath(mPathRed, mPaintRed);
                for (Box box : mSquareBoxesRed) {
                    float left = Math.min(box.getOrigin().x, box.getCurrent().x);
                    float right = Math.max(box.getOrigin().x, box.getCurrent().x);
                    float top = Math.min(box.getOrigin().y, box.getCurrent().y);
                    float bottom = Math.max(box.getOrigin().y, box.getCurrent().y);
                    canvas.drawRect(left, top, right, bottom, mSquareBoxPaintRed);
                }
                for (Box box : mLineBoxesRed) {
                    canvas.drawLine(box.getOrigin().x, box.getOrigin().y, box.getCurrent().x, box.getCurrent().y, mLineBoxPaintRed);
                }


                canvas.drawPath(mPathGreen, mPaintGreen);
                for (Box box : mSquareBoxesGreen) {
                    float left = Math.min(box.getOrigin().x, box.getCurrent().x);
                    float right = Math.max(box.getOrigin().x, box.getCurrent().x);
                    float top = Math.min(box.getOrigin().y, box.getCurrent().y);
                    float bottom = Math.max(box.getOrigin().y, box.getCurrent().y);
                    canvas.drawRect(left, top, right, bottom, mSquareBoxPaintGreen);
                }
                for (Box box : mLineBoxesGreen) {
                    canvas.drawLine(box.getOrigin().x, box.getOrigin().y, box.getCurrent().x, box.getCurrent().y, mLineBoxPaintGreen);
                }

                canvas.drawPath(mPathBlue, mPaintBlue);
                for (Box box : mSquareBoxesBlue) {
                    float left = Math.min(box.getOrigin().x, box.getCurrent().x);
                    float right = Math.max(box.getOrigin().x, box.getCurrent().x);
                    float top = Math.min(box.getOrigin().y, box.getCurrent().y);
                    float bottom = Math.max(box.getOrigin().y, box.getCurrent().y);
                    canvas.drawRect(left, top, right, bottom, mSquareBoxPaintBlue);
                }
                for (Box box : mLineBoxesBlue) {
                    canvas.drawLine(box.getOrigin().x, box.getOrigin().y, box.getCurrent().x, box.getCurrent().y, mLineBoxPaintBlue);
                }


        canvas.drawPath(mPathBlue, mPaintBlue);
            for (MultiDraw figure : mMultiFigures) {
                figure.multiFigureDraw(canvas);
            }

            if (mCurrentMultiFigure != null) {
                mCurrentMultiFigure.multiFigureDraw(canvas);
            }

        canvas.drawPath(mPathRed, mPaintRed);
        for (MultiDraw figure : mMultiFigures) {
            figure.multiFigureDraw(canvas);
        }

        if (mCurrentMultiFigure != null) {
            mCurrentMultiFigure.multiFigureDraw(canvas);
        }


        canvas.drawPath(mPathGreen, mPaintGreen);
        for (MultiDraw figure : mMultiFigures) {
            figure.multiFigureDraw(canvas);
        }

        if (mCurrentMultiFigure != null) {
            mCurrentMultiFigure.multiFigureDraw(canvas);
        }
    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {

        PointF current = new PointF(event.getX(), event.getY());

        if (figureType == MainActivity.Figure.PATH && figureColor == Color.RED) {
            float eventX = event.getX();
            float eventY = event.getY();

            switch (event.getAction()) {
                case ACTION_DOWN:
                    mPathRed.moveTo(eventX, eventY);
                    return true;
                case ACTION_MOVE:
                    mPathRed.lineTo(eventX, eventY);
                    break;
                default:
                    return false;
            }
        }

        if (figureType == MainActivity.Figure.PATH && figureColor == Color.GREEN) {
            float eventX = event.getX();
            float eventY = event.getY();

            switch (event.getAction()) {
                case ACTION_DOWN:
                    mPathGreen.moveTo(eventX, eventY);
                    return true;
                case ACTION_MOVE:
                    mPathGreen.lineTo(eventX, eventY);
                    break;
                default:
                    return false;
            }
        }

        if (figureType == MainActivity.Figure.PATH && figureColor == Color.BLUE) {
            float eventX = event.getX();
            float eventY = event.getY();

            switch (event.getAction()) {
                case ACTION_DOWN:
                    mPathBlue.moveTo(eventX, eventY);
                    return true;
                case ACTION_MOVE:
                    mPathBlue.lineTo(eventX, eventY);
                    break;
                default:
                    return false;
            }
        }

        else if (figureType == MainActivity.Figure.SQUARE && figureColor == Color.RED) {
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mSquareCurrentBox = new Box(current);
                    mSquareBoxesRed.add(mSquareCurrentBox);
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (mSquareCurrentBox != null) {
                        mSquareCurrentBox.setCurrent(current);
                        invalidate();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    mSquareCurrentBox = null;
                    break;
            }
        }


        else if (figureType == MainActivity.Figure.SQUARE && figureColor == Color.GREEN) {
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mSquareCurrentBox = new Box(current);
                    mSquareBoxesGreen.add(mSquareCurrentBox);
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (mSquareCurrentBox != null) {
                        mSquareCurrentBox.setCurrent(current);
                        invalidate();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    mSquareCurrentBox = null;
                    break;
            }
        }


        else if (figureType == MainActivity.Figure.SQUARE && figureColor == Color.BLUE) {
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mSquareCurrentBox = new Box(current);
                    mSquareBoxesBlue.add(mSquareCurrentBox);
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (mSquareCurrentBox != null) {
                        mSquareCurrentBox.setCurrent(current);
                        invalidate();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    mSquareCurrentBox = null;
                    break;
            }
        }

        else if (figureType == MainActivity.Figure.LINE && figureColor == Color.RED) {
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mLineCurrentBox = new Box(current);
                    mLineBoxesRed.add(mLineCurrentBox);
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (mLineCurrentBox != null) {
                        mLineCurrentBox.setCurrent(current);
                        invalidate();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:

                    mLineCurrentBox = null;
                    break;
            }
        }

        else if (figureType == MainActivity.Figure.LINE && figureColor == Color.GREEN) {
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mLineCurrentBox = new Box(current);
                    mLineBoxesGreen.add(mLineCurrentBox);
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (mLineCurrentBox != null) {
                        mLineCurrentBox.setCurrent(current);
                        invalidate();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:

                    mLineCurrentBox = null;
                    break;
            }
        }

        else if (figureType == MainActivity.Figure.LINE && figureColor == Color.BLUE) {
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mLineCurrentBox = new Box(current);
                    mLineBoxesBlue.add(mLineCurrentBox);
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (mLineCurrentBox != null) {
                        mLineCurrentBox.setCurrent(current);
                        invalidate();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:

                    mLineCurrentBox = null;
                    break;
            }
        }

       else if (figureType == MainActivity.Figure.MULTI && figureColor == Color.RED) {

            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    mCurrentMultiFigure = new MultiDraw(mCurrentColor);
                    current = mCurrentMultiFigure.getPoint(0);
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    int pointerId = event.getPointerId(event.getActionIndex());
                    current = mCurrentMultiFigure.getPoint(pointerId);
                    break;
                case MotionEvent.ACTION_MOVE:
                    for (int i = 0; i < event.getPointerCount(); i++) {
                        int pointId = event.getPointerId(i);
                        mCurrentMultiFigure.getPoint(pointId).x = event.getX(i);
                        mCurrentMultiFigure.getPoint(pointId).y = event.getY(i);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    mMultiFigures.add(mCurrentMultiFigure);
                    mCurrentMultiFigure = null;
                    break;
            }

            current.x = event.getX(event.getActionIndex());
            current.y = event.getY(event.getActionIndex());
        }

        invalidate();
        return true;
    }

    public void reset() {
        mPathRed.reset();
        mPathGreen.reset();
        mPathBlue.reset();
        mSquareBoxesRed.clear();
        mSquareBoxesGreen.clear();
        mSquareBoxesBlue.clear();
        mLineBoxesRed.clear();
        mLineBoxesGreen.clear();
        mLineBoxesBlue.clear();
        invalidate();
    }

    private void setUpPaintRed() {
        mPaintRed.setAntiAlias(true);
        mPaintRed.setStrokeWidth(STROKE_WIDTH);
        mPaintRed.setColor(getResources().getColor(R.color.colorRed));
        mPaintRed.setStyle(Paint.Style.STROKE);
        mPaintRed.setStrokeJoin(Paint.Join.ROUND);
    }

    private void setUpSquarePaintRed() {
        mSquareBoxPaintRed = new Paint();
        mSquareBoxPaintRed.setColor(getResources().getColor(R.color.colorRed));
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(Color.WHITE);
    }

    private void setupLinePaintRed() {
        mLineBoxPaintRed = new Paint();
        mLineBoxPaintRed.setColor(getResources().getColor(R.color.colorRed));
        mLineBoxPaintRed.setStrokeWidth(30f);
    }



    private void setUpPaintGreen() {
        mPaintGreen.setAntiAlias(true);
        mPaintGreen.setStrokeWidth(STROKE_WIDTH);
        mPaintGreen.setColor(getResources().getColor(R.color.colorGreen));
        mPaintGreen.setStyle(Paint.Style.STROKE);
        mPaintGreen.setStrokeJoin(Paint.Join.ROUND);
    }

    private void setUpSquarePaintGreen() {
        mSquareBoxPaintGreen = new Paint();
        mSquareBoxPaintGreen.setColor(getResources().getColor(R.color.colorGreen));
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(Color.WHITE);
    }

    private void setupLinePaintGreen() {
        mLineBoxPaintGreen = new Paint();
        mLineBoxPaintGreen.setColor(getResources().getColor(R.color.colorGreen));
        mLineBoxPaintGreen.setStrokeWidth(30f);
    }




    private void setUpPaintBlue() {
        mPaintBlue.setAntiAlias(true);
        mPaintBlue.setStrokeWidth(STROKE_WIDTH);
        mPaintBlue.setColor(getResources().getColor(R.color.colorBlue));
        mPaintBlue.setStyle(Paint.Style.STROKE);
        mPaintBlue.setStrokeJoin(Paint.Join.ROUND);
    }

    private void setUpSquarePaintBlue() {
        mSquareBoxPaintBlue = new Paint();
        mSquareBoxPaintBlue.setColor(getResources().getColor(R.color.colorBlue));
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(Color.WHITE);
    }

    private void setupLinePaintBlue() {
        mLineBoxPaintBlue = new Paint();
        mLineBoxPaintBlue.setColor(getResources().getColor(R.color.colorBlue));
        mLineBoxPaintBlue.setStrokeWidth(30f);
    }

}
