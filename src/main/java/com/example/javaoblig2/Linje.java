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
        linje = new javafx.scene.shape.Line(startx, starty, endx, endy);
        linje.setStroke(strokeColor);
        linje.setStrokeWidth(strokeWidth);
    }

    @Override
    public Shape getShape() {
        return linje;
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
        endx = x;
        endy = y;
        sluttpunkt(startx, starty, endx, endy);
    }

    @Override
    public boolean checkarea() {
        double length = Math.sqrt(Math.pow(endx - startx, 2) + Math.pow(endy - starty, 2));
        return length < 5;
    }

    @Override
    public void nylayout(double offsetX, double offsetY) {
        startx += offsetX;
        starty += offsetY;
        endx += offsetX;
        endy += offsetY;

        linje.setStartX(startx);
        linje.setStartY(starty);
        linje.setEndX(endx);
        linje.setEndY(endy);
    }
    @Override
    public String getDetails() {

        return "Linje";
    }
    @Override
    public void setFill(Color nyfillfarge){
        return;
    }
    @Override
    public void setStroke(Color nyfillfarge){
        linje.setStroke(nyfillfarge);
    }
    @Override
    public double getX() {
        return (startx + endx) / 2;
    }
    @Override
    public double getY(){
        return (starty + endy) / 2;
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