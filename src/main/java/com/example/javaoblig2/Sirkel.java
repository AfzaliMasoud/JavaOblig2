package com.example.javaoblig2;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Sirkel extends Figur {

    private javafx.scene.shape.Circle sirkel;
    double musklikkx;
    double musklikky;

    private double startX;
    private double startY;
    private Color strokeColor;
    private Color fillColor;
    private double strokeWidth;
    double radius;

    public Sirkel(double startX, double startY, double radius, Color strokeColor, Color fillColor, double strokeWidth) {
        this.startX = startX;
        this.startY = startY;
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
        this.strokeWidth = strokeWidth;

        sirkel = new javafx.scene.shape.Circle(startX, startY, radius);
        sirkel.setStroke(strokeColor);
        sirkel.setFill(fillColor);
        sirkel.setStrokeWidth(strokeWidth);
    }

    public void setRadius(double radius) {
        sirkel.setRadius(radius);
    }
    public double getRadius() {
        return sirkel.getRadius();
    }

    public void setCenter(double x, double y) {
        sirkel.setCenterX(x);
        sirkel.setCenterY(y);
    }
    @Override
    public String getDetails() {
        return "Sirkel";
    }

    @Override
    public Shape getShape() {
        return sirkel;
    }

    @Override
    public void musklikk(double x, double y) {
        musklikkx = x;
        musklikky = y;
        setCenter(x, y);
    }

    @Override
    public void musdra(double x, double y) {
        double radius = Math.hypot(x - musklikkx, y - musklikky);
        setRadius(radius);
    }

    @Override
    public boolean checkarea() {
        double radius = sirkel.getRadius();
        double area = Math.PI * radius * radius;
        return area <= 10;
    }
    @Override
    public void setFill(Color nyfillfarge){
        sirkel.setFill(nyfillfarge);
    }

    @Override
    public void setStroke(Color nyfillfarge){
        sirkel.setStroke(nyfillfarge);
    }



    @Override
    public String toString() {
        return "Sirkel: [StartX: " + startX +
                ", StartY: " + startY +
                ", Radius: " + radius +
                ", Stroke Color: " + strokeColor +
                ", Fill Color: " + fillColor +
                ", Stroke Width: " + strokeWidth + "]";
    }
}
