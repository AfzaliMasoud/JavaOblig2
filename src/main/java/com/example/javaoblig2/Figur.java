package com.example.javaoblig2;

import javafx.scene.shape.Shape;

public abstract class Figur {

    public abstract Shape getShape();
    public abstract void musklikk(double x, double y);
    public abstract void musdra(double x, double y);

}