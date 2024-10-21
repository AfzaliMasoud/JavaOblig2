package com.example.javaoblig2;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Rektangel extends Figur {

    private Rectangle rektangel;
    private double startX;
    private double startY;
    private double bredde;
    private double høyde;
    private Color strokeColor;
    private Color fillColor;
    private double strokeWidth;


    public Rektangel(double x, double y, double bredde, double høyde, Color strokeColor, Color fillColor, double strokeWidth) {
        this.startX = x;
        this.startY = y;
        this.bredde = bredde;
        this.høyde = høyde;
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
        this.strokeWidth = strokeWidth;
        rektangel = new Rectangle(x, y, bredde, høyde);
        rektangel.setStroke(strokeColor);
        rektangel.setFill(fillColor);
        rektangel.setStrokeWidth(strokeWidth);
    }
    public double getWidth() {
        return rektangel.getWidth();
    }

    public double getHeight() {
        return rektangel.getHeight();
    }

    @Override
    public Shape getShape() {
        return rektangel;
    }

    @Override
    public void musklikk(double x, double y) {
        startX = x;
        startY = y;
        setPosition(startX, startY);
    }

    @Override
    public void musdra(double x, double y) {
        double bredde = Math.abs(x - startX);
        double høyde = Math.abs(y - startY);
        setPosition(Math.min(startX, x), Math.min(startY, y));
        setSize(bredde, høyde);
    }

    public void setPosition(double x, double y) {
        rektangel.setX(x);
        rektangel.setY(y);
    }


    public void setSize(double bredde, double høyde) {
        rektangel.setWidth(bredde);
        rektangel.setHeight(høyde);
    }

    @Override
    public void setFill(Color nyfillfarge){
        rektangel.setFill(nyfillfarge);
    }


    @Override
    public boolean checkarea() {
        double bredde = rektangel.getWidth();
        double høyde = rektangel.getHeight();
        double area = bredde * høyde;

        return area <= 10;
    }



    @Override
    public String getDetails() {
        return "Rektangel";
    }

    @Override
    public String toString() {
        return "Rektangel: [StartX: " + startX +
                ", StartY: " + startY +
                ", Bredde: " + bredde +
                ", Høyde: " + høyde +
                ", Stroke Color: " + strokeColor +
                ", Fill Color: " + fillColor +
                ", Stroke Width: " + strokeWidth + "]";
    }
}
