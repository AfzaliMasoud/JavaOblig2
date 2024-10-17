package com.example.javaoblig2;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Linje extends Figur {
    private javafx.scene.shape.Line linje;
    double endx;
    double endy;
    double startx;
    double starty;

    private Color strokeColor;
    private double strokeWidth;

    public Linje(double startx, double starty, double endx, double endy, Color strokeColor, double strokeWidth) {
        this.startx = startx;
        this.starty = starty;
        this.endx = endx;
        this.endy = endy;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;

        linje = new javafx.scene.shape.Line(startx, starty, endx, endy);
        linje.setStroke(strokeColor);
        linje.setStrokeWidth(strokeWidth);
    }

    @Override
    public Shape getShape() {
        return linje;
    }
    @Override
    public String getDetails() {
        double startX = linje.getStartX();
        double startY = linje.getStartY();
        double endX = linje.getEndX();
        double endY = linje.getEndY();

        double length = Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2));

        return "Lengde: " + String.format("%.2f", length);
    }


    public void sluttpunkt(double startx, double starty, double endx, double endy) {
        linje.setStartX(startx);
        linje.setStartY(starty);
        linje.setEndX(endx);
        linje.setEndY(endy);
    }

    @Override
    public void musklikk(double x, double y) {
        startx = x;
        starty = y;
        sluttpunkt(startx, starty, endx, endy);
    }

    @Override
    public void musdra(double x, double y) {
        sluttpunkt(startx, starty, x, y);
    }
    @Override
    public void move(double deltaX, double deltaY) {
        startx += deltaX;
        starty += deltaY;
        endx += deltaX;
        endy += deltaY;
        linje.setStartX(startx);
        linje.setStartY(starty);
        linje.setEndX(endx);
        linje.setEndY(endy);


    }

    @Override
    public String toString() {
        return "Linje: [StartX: " + startx +
                ", StartY: " + starty +
                ", EndX: " + endx +
                ", EndY: " + endy +
                ", Stroke Color: " + strokeColor +
                ", Stroke Width: " + strokeWidth + "]";
    }
}
