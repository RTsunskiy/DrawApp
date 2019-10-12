package com.example.drawapp;

import android.graphics.Paint;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.nio.file.Path;
import java.util.List;

public class SavedFigure {
    private List<Box> lineBoxes;
    private List<Box> squareBoxes;
    private Path path;

    private Paint paintDraw;
    private Paint paintLine;
    private Paint paintSquare;

    public Paint getPaintDraw() {
        return paintDraw;
    }

    public void setPaintDraw(Paint paintDraw) {
        this.paintDraw = paintDraw;
    }

    public Paint getPaintLine() {
        return paintLine;
    }

    public void setPaintLine(Paint paintLine) {
        this.paintLine = paintLine;
    }

    public Paint getPaintSquare() {
        return paintSquare;
    }

    public void setPaintSquare(Paint paintSquare) {
        this.paintSquare = paintSquare;
    }

    public List<Box> getLineBoxes() {
        return lineBoxes;
    }

    public void setLineBoxes(List<Box> lineBoxes) {
        this.lineBoxes = lineBoxes;
    }

    public List<Box> getSquareBoxes() {
        return squareBoxes;
    }

    public void setSquareBoxes(List<Box> squareBoxes) {
        this.squareBoxes = squareBoxes;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }


}
