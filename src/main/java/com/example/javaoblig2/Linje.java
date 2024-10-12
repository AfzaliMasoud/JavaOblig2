package com.example.javaoblig2;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Linje extends Figur{
    private javafx.scene.shape.Line linje;
    double endx;
    double endy;
    double startx;
    double starty;

    public Linje(double startx, double starty, double endx, double endy, Color Strokefarge, double Strokebredde) {
        this.startx = startx;
        this.starty = starty;
        this.endx = endx;
        this.endy = endy;
        linje = new javafx.scene.shape.Line(startx, starty, endx, endy);
        linje.setStroke(Strokefarge);
        linje.setStrokeWidth(Strokebredde);
    }


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
        sluttpunkt(startx, starty, x, y);
    }
}
