package com.example.javaoblig2;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

public class Tekst {

    private Text tekst;

    public Tekst(String message, double x, double y, Color farge, double pixel) {
        tekst = new Text(x, y, message);
        tekst.setFill(farge);
        tekst.setFont(Font.font("Times New Roman",pixel));
    }

    public Text getText() {
        return tekst;
    }
    public void setPos(double x, double y){
        tekst.setY(y);
        tekst.setX(x);
    }
}
