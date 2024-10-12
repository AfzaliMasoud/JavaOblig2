package com.example.javaoblig2;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Rektangel extends Figur {

    private Rectangle rektangel;
    private double startX;
    private double startY;

    public Rektangel(double x, double y, double bredde, double høyde, Color Strokefarge, Color Fillfarge, double Strokebredde) {
        rektangel = new Rectangle(x, y, bredde, høyde);
        rektangel.setStroke(Strokefarge);
        rektangel.setFill(Fillfarge);
        rektangel.setStrokeWidth(Strokebredde);
        this.startX = x;
        this.startY = y;
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

}
