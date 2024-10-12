package com.example.javaoblig2;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import javax.swing.JOptionPane;

public class UserUI {
    private Image rektangelbilde;

    public VBox LagValgPane() {
        VBox vBox = new VBox();

        try {
            rektangelbilde = new Image(getClass().getResourceAsStream("/Images/Rektangel.png"));
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null,
                    "Kunne ikke finne Bilde", "Ikke funnet!!!",
                    JOptionPane.ERROR_MESSAGE);
        }

        ImageView rektangelview = new ImageView(rektangelbilde);
        Button rektangelvalg = new Button();
        rektangelvalg.setGraphic(rektangelview);

        vBox.getChildren().addAll(rektangelvalg);
        return vBox;
    }
}
