package com.example.javaoblig2;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Sirkel extends Figur{

    private javafx.scene.shape.Circle sirkel;
    double musklikkx;
    double musklikky;


    public Sirkel(double startX, double startY,double radius, Color Strokefarge, Color Fillfarge, double Strokebredde) {
        sirkel = new javafx.scene.shape.Circle(startX, startY, radius);
        sirkel.setStroke(Strokefarge);
        sirkel.setFill(Fillfarge);
        sirkel.setStrokeWidth(Strokebredde);
    }
    public void setRadius(double x){
        sirkel.setRadius(x);
    }

    public void setCenter(double x, double y) {
        sirkel.setCenterX(x);
        sirkel.setCenterY(y);
    }
    @Override
    public Shape getShape() {
        return sirkel;
    }
    @Override
    public void musklikk(double x, double y) {
        musklikkx = x;
        musklikky = y;
        setCenter(x,y);

    }

    @Override
    public void musdra(double x, double y) {
        double radius = Math.hypot(x - musklikkx, y - musklikky);
        setRadius(radius);
    }



}
