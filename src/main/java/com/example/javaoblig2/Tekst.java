package com.example.javaoblig2;

import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

public class Tekst extends Figur{

    private Text tekst;

    public Tekst(String message, double x, double y, Color farge,Color strekfarge, double pixel) {
        tekst = new Text(x, y, message);
        tekst.setFill(farge);
        tekst.setStroke(strekfarge);
        tekst.setFont(Font.font("Times New Roman",pixel));
    }


    @Override
    public Shape getShape() {
        return tekst;
    }
    @Override
    public boolean checkarea() {
       return false;
    }
    @Override
    public void setFill(Color nyfillfarge){
        tekst.setFill(nyfillfarge);
    }
    @Override
    public void setStroke(Color nystrekfarge){
        tekst.setStroke(nystrekfarge);
    }

    @Override
    public void musklikk(double x, double y) {
        double startX = x;
        double startY = y;

        tekst.setX(startX);
        tekst.setY(startY);
    }


    @Override
    public void musdra(double x, double y) {
        tekst.setX(x);
        tekst.setY(y);

    }

    @Override
    public String getDetails() {
        return "Tekst: " + tekst.getText();
    }

    @Override
    public void nylayout(double offsetX, double offsetY) {
        tekst.setX(tekst.getX() + offsetX);
        tekst.setY(tekst.getY() + offsetY);
    }

    @Override
    public double getX() {
        return tekst.getX() + tekst.getLayoutBounds().getWidth() / 2;
    }

    @Override
    public double getY() {
        return tekst.getY() + tekst.getLayoutBounds().getHeight() / 2;
    }
    @Override
    public void setNyStrokeBredde(double inx) {
        tekst.setFont(Font.font(inx));
    }

}
