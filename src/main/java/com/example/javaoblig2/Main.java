package com.example.javaoblig2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Node;

import javax.swing.*;
import java.util.ArrayList;


public class Main extends Application {

    Image rektangelbilde;
    Image sirkelbilde;
    Image linjebilde;
    Image textbilde;
    Image undo;
    Image redo;

    Pane tegnepane;
    VBox vbox;
    BorderPane hovedpane;
    HBox hbox;

    Figur figur;
    String ValgtFigur;

    ColorPicker strekfarge;
    ColorPicker fillfarge;

    TextField strekbreddeinput;

    double klikkmusx;
    double klikkmusy;

    String inputfratext;
    Tekst Tekstskrevet;

    ArrayList<Figur> figurArrayList = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        hovedpane = new BorderPane();
        vbox = Valgpane();
        tegnepane = new Pane();
        hovedpane.setCenter(tegnepane);
        hovedpane.setLeft(vbox);

        klikk(tegnepane);
        dra(tegnepane);
        Scene scene = new Scene(hovedpane, 1500, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private VBox Valgpane() {
        hbox = new HBox();
        VBox vBox = new VBox();

        rektangelbilde = loadImage("Images/Rektangel.png");
        ImageView rektangelview = new ImageView(rektangelbilde);
        Button rektangelvalg = new Button();
        rektangelvalg.setGraphic(rektangelview);
        rektangelvalg.setOnAction(e -> {
            ValgtFigur = "Rektangel";
        });

        sirkelbilde = loadImage("Images/Sirkel.png");
        ImageView sirkelview = new ImageView(sirkelbilde);
        Button sirkelvalg = new Button();
        sirkelvalg.setGraphic(sirkelview);
        sirkelvalg.setOnAction(e -> {
            ValgtFigur = "Sirkel";
        });

        linjebilde = loadImage("Images/Linje.png");
        ImageView linjeview = new ImageView(linjebilde);
        Button linjevalg = new Button();
        linjevalg.setGraphic(linjeview);
        linjevalg.setOnAction(e -> {
            ValgtFigur = "Linje";
        });

        textbilde = loadImage("Images/Text.png");
        ImageView textview = new ImageView(textbilde);
        Button textvalg = new Button();
        textvalg.setGraphic(textview);
        textvalg.setOnAction(e -> {
            inputfratext = JOptionPane.showInputDialog("Hva vil du skrive?");
            ValgtFigur = "Text";
        });

        Label strekfargelabel = new Label("Velg Strek Farge");
        strekfarge = new ColorPicker();

        Label fillFargelabel = new Label("Velg Fyll Farge");
        fillfarge = new ColorPicker();

        Label strekbredde = new Label("Strek Bredde (Pixel)");
        strekbreddeinput = new TextField("1");

        undo = loadImage("Images/UNDO.png");
        ImageView undoview = new ImageView(undo);
        Button undovalg = new Button();
        undovalg.setGraphic(undoview);

        redo = loadImage("Images/REDO.png");
        ImageView redoview = new ImageView(redo);
        Button redovalg = new Button();
        redovalg.setGraphic(redoview);

        hbox.setSpacing(10);
        hbox.setPadding(new Insets(50,0,0,20));
        hbox.getChildren().addAll(undovalg,redovalg);
        vBox.getChildren().addAll(rektangelvalg, sirkelvalg, linjevalg, textvalg, strekfargelabel, strekfarge, fillFargelabel, fillfarge,strekbredde,strekbreddeinput,hbox);
        stilUI(vBox);
        return vBox;
    }
    private boolean breddegyldig() {
        try {
            double inputStrekbredde = Double.parseDouble(strekbreddeinput.getText());
            if (inputStrekbredde < 0 || inputStrekbredde > 20) {
                JOptionPane.showMessageDialog(null, "Strek bredde må være mellom 0 og 20!");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Strek bredde må være et tall!");
            return false;
        }
    }


    private void klikk(Pane tegnepane) {
        tegnepane.setOnMousePressed(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {

                if (ValgtFigur == null) {
                    JOptionPane.showMessageDialog(null, "Du må velge en figur først!");
                    return;
                }

                if (!breddegyldig()) {
                    return;
                }

                double inputStrekbredde = Double.parseDouble(strekbreddeinput.getText());
                klikkmusx = e.getX();
                klikkmusy = e.getY();
                Color valgtstrekfarge = strekfarge.getValue();
                Color valgtfillfarge = fillfarge.getValue();

                switch (ValgtFigur) {
                    case "Rektangel":
                        figur = new Rektangel(klikkmusx, klikkmusy, 0, 0, valgtstrekfarge, valgtfillfarge, inputStrekbredde);
                        tegnepane.getChildren().add(figur.getShape());
                        figur.musklikk(klikkmusx, klikkmusy);

                        break;
                    case "Sirkel":
                        figur = new Sirkel(klikkmusx, klikkmusy, 0, valgtstrekfarge, valgtfillfarge, inputStrekbredde);
                        tegnepane.getChildren().add(figur.getShape());
                        figur.musklikk(klikkmusx, klikkmusy);
                        break;
                    case "Linje":
                        figur = new Linje(klikkmusx, klikkmusy, klikkmusx, klikkmusy, valgtstrekfarge, inputStrekbredde);
                        tegnepane.getChildren().add(figur.getShape());
                        figur.musklikk(klikkmusx, klikkmusy);
                        break;
                    case "Text":
                        Tekstskrevet = new Tekst(inputfratext, klikkmusx, klikkmusy, valgtstrekfarge,inputStrekbredde);
                        tegnepane.getChildren().add(Tekstskrevet.getText());
                        figur = null;
                        break;
                    default:
                        return;
                }
            }
        });
    }


    private void dra(Pane tegnepane) {
        tegnepane.setOnMouseDragged(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                double musx = e.getX();
                double musy = e.getY();

                if (figur != null) {
                    figur.musdra(musx, musy);
                } else if (Tekstskrevet != null) {
                    Tekstskrevet.setPos(musx,musy);
                }
            }
        });
    }




    private void stilUI(Node n){
        n.setStyle("-fx-padding: 10; " +
                "-fx-spacing: 10; " +
                "-fx-background-color: #e0f7fa; " +
                "-fx-border-color: #00796b; " +
                "-fx-border-width: 2; " +
                "-fx-border-radius: 12; " +
                "-fx-font-size: 16px; " +
                "-fx-font-family: 'Arial', sans-serif; " +
                "-fx-text-fill: #004d40; " +
                "-fx-background-radius: 12; " +
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.3), 10, 0.8, 0, 0);");
    }

    private Image loadImage(String path) {
        try {
            return new Image(getClass().getResourceAsStream(path));
        } catch (NullPointerException e) {
            showImageError(path);
            return null;
        }
    }

    private void showImageError(String path) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Image Not Found");
        alert.setContentText("Could not find image at: " + path);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
