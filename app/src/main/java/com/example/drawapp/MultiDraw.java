package com.example.drawapp;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Parcel;

import java.util.ArrayList;
import java.util.List;

public class MultiDraw {

    private Paint mPaint;
    private Paint mMultiPaint;
    private Path mMultiPath;
    private int mColor;
    private List<PointF> mMultiPoints = new ArrayList<>();

    public MultiDraw(int color) {
        mColor = color;
        initPaint();
    }

    public MultiDraw(Parcel item) {
        mColor = item.readInt();
        mMultiPoints = item.createTypedArrayList(PointF.CREATOR);
    }

    public void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(mColor);
        mMultiPaint = new Paint(mPaint);
    }

    public PointF getPoint(int index) {
        while (index >= mMultiPoints.size()) {
            mMultiPoints.add(new PointF());
        }

        return mMultiPoints.get(index);
    }

    public void multiFigureDraw(Canvas canvas) {
        if (mMultiPath == null) {
            mMultiPath = new Path();
        }

        mMultiPath.reset();

        for (PointF point : mMultiPoints) {
            if (mMultiPath.isEmpty()) {
                mMultiPath.moveTo(point.x, point.y);
            } else {
                mMultiPath.lineTo(point.x, point.y);
            }
        }

        mMultiPath.close();
        canvas.drawPath(mMultiPath, mMultiPaint);
    }
}
