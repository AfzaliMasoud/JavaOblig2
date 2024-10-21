package com.example.javaoblig2;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public abstract class Figur {

    public abstract Shape getShape();
    public abstract void musklikk(double x, double y);
    public abstract void musdra(double x, double y);
    public abstract String getDetails();
    public abstract boolean checkarea();
    public abstract void setFill(Color nyfillfarge);


}
