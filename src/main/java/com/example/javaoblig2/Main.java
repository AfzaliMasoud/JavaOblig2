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
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.Node;
import org.w3c.dom.Text;

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
    VBox vboxedit;
    VBox vboxeditvar;
    BorderPane hovedpane;
    HBox hbox;
    HBox hbox2;
    HBox hbox3;
    HBox hbox4;
    HBox hboxedit1;
    HBox hboxedit2;
    HBox hboxedit3;


    Label Figurvalgtedit = new Label("Valgt Figur : ");
    TextField figurvalgtedittext = new TextField();

    Label strekfargeny = new Label("Strek Farge : ");
    ColorPicker fargegitt = new ColorPicker();

    Label fillfargeny = new Label("Fill Farge : ");
    ColorPicker fargeFill = new ColorPicker();

    Button buttonslett = new Button("Slett Figur");

    Figur figur;
    String ValgtFigur ="Rektangel";

    ColorPicker strekfarge;
    ColorPicker fillfarge;

    TextField strekbreddeinput;

    Label fillFargelabel;

    double klikkmusx;
    double klikkmusy;

    double mousex;
    double mousey;

    double forskjellx;
    double forskjelly;

    boolean tegn = true;
    boolean isValid;

    String inputfratext;
    Tekst Tekstskrevet;
    Button flyttebutton;
    Button tegnbutton;


    ArrayList<Figur> figurArrayList = new ArrayList<>();
    ArrayList<Figur> tempArrayList = new ArrayList<>();

    ArrayList<Tekst> figurTextlist = new ArrayList<>();


    @Override
    public void start(Stage primaryStage) {
        figurvalgtedittext.setEditable(false);

        hovedpane = new BorderPane();
        vboxedit = editpane();
        vbox = Valgpane();
        tegnepane = new Pane();
        hovedpane.setCenter(tegnepane);
        hovedpane.setLeft(vbox);
        hovedpane.setRight(vboxedit);


        klikk(tegnepane);
        dra(tegnepane);
        lagrerelease(tegnepane);
        flyttklikk(tegnepane);


        Scene scene = new Scene(hovedpane, 1500, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private VBox Valgpane() {
        hbox = new HBox();
        hbox2 = new HBox();
        hbox3 = new HBox();
        hbox4 = new HBox();
        VBox vBox = new VBox();

        rektangelbilde = loadImage("Images/Rektangel.png");
        ImageView rektangelview = new ImageView(rektangelbilde);
        Button rektangelvalg = new Button();
        rektangelvalg.setGraphic(rektangelview);
        rektangelvalg.setOnAction(e -> {
            ValgtFigur = "Rektangel";
            tegn = true;
            fillFargelabel.setVisible(true);
            fillfarge.setVisible(true);
            vboxeditvar.setVisible(false);
        });

        sirkelbilde = loadImage("Images/Sirkel.png");
        ImageView sirkelview = new ImageView(sirkelbilde);
        Button sirkelvalg = new Button();
        sirkelvalg.setGraphic(sirkelview);
        sirkelvalg.setOnAction(e -> {
            ValgtFigur = "Sirkel";
            tegn = true;
            fillFargelabel.setVisible(true);
            fillfarge.setVisible(true);
            vboxeditvar.setVisible(false);

        });

        linjebilde = loadImage("Images/Linje.png");
        ImageView linjeview = new ImageView(linjebilde);
        Button linjevalg = new Button();
        linjevalg.setGraphic(linjeview);
        linjevalg.setOnAction(e -> {
            ValgtFigur = "Linje";
            tegn = true;
            fillFargelabel.setVisible(false);
            fillfarge.setVisible(false);
            vboxeditvar.setVisible(false);
        });

        textbilde = loadImage("Images/Text.png");
        ImageView textview = new ImageView(textbilde);
        Button textvalg = new Button();
        textvalg.setGraphic(textview);
        textvalg.setOnAction(e -> {
            inputfratext = JOptionPane.showInputDialog("Hva vil du skrive?");
            ValgtFigur = "Text";
            tegn = true;
            fillFargelabel.setVisible(false);
            fillfarge.setVisible(false );
            vboxeditvar.setVisible(false);
        });

        Label strekfargelabel = new Label("Velg Strek Farge");
        strekfarge = new ColorPicker();
        strekfarge.setValue(Color.BLACK);

        fillFargelabel = new Label("Velg Fyll Farge");
        fillfarge = new ColorPicker();
        fillfarge.setValue(Color.RED);

        Label strekbredde = new Label("Strek Bredde (Pixel)");
        strekbreddeinput = new TextField("5");

        undo = loadImage("Images/UNDO.png");
        ImageView undoview = new ImageView(undo);
        Button undovalg = new Button();
        undovalg.setGraphic(undoview);

        redo = loadImage("Images/REDO.png");
        ImageView redoview = new ImageView(redo);
        Button redovalg = new Button();
        redovalg.setGraphic(redoview);

        flyttebutton = new Button("Edit");
        tegnbutton = new Button("Tegn");

        undovalg.setOnAction(e ->{
            undomethod();
        });

        redovalg.setOnAction(e -> {
            redomethod();
        });

        Button Nyfil = new Button("Ny fil");

        Nyfil.setOnAction(e-> {
           nyFil();
        });

        Button savefil = new Button("Lagre fil");

        savefil.setOnAction(e -> {

        });

        flyttebutton.setOnAction(e ->{
            tegn = false;
        });
        tegnbutton.setOnAction(e->{
            tegn = true;
            vboxeditvar.setVisible(false);
        });

        hbox.setSpacing(10);
        hbox.setPadding(new Insets(50,0,40,20));
        hbox.getChildren().addAll(undovalg,redovalg);

        hbox2.setSpacing(10);
        hbox2.setPadding(new Insets(0,0,0,22));
        hbox2.getChildren().addAll(Nyfil,savefil);

        hbox3.setSpacing(30);
        hbox3.getChildren().addAll(sirkelvalg, flyttebutton);

        hbox4.setSpacing(30);
        hbox4.getChildren().addAll(rektangelvalg,tegnbutton);

        vBox.getChildren().addAll(hbox4,hbox3, linjevalg, textvalg,strekfargelabel, strekfarge, fillFargelabel, fillfarge,strekbredde,strekbreddeinput,hbox, hbox2);
        stilUI(vBox);
        return vBox;
    }



    private void nyFil() {
        int sikker = JOptionPane.showConfirmDialog(
                null,
                "Er du sikker at du vil lage en ny fil? Denne filen blir ikke lagret!",
                "Bekreftelse",
                JOptionPane.YES_NO_OPTION
        );

        if (sikker == 0) {
            for (Figur figur : figurArrayList) {
                tegnepane.getChildren().remove(figur.getShape());
            }
            for (Tekst tekst : figurTextlist) {
                tegnepane.getChildren().remove(tekst.getText());
            }
            figurTextlist.clear();
            figurArrayList.clear();
        } else {
            return;
        }
    }

    private void undomethod() {
        if (!figurArrayList.isEmpty()) {
            Figur sistefigur = figurArrayList.remove(figurArrayList.size() - 1);
            tempArrayList.add(sistefigur);
            tegnepane.getChildren().remove(sistefigur.getShape());
        }else if (figurArrayList.isEmpty()){
           return;
        }

    }
    private void redomethod() {
        if (!tempArrayList.isEmpty()) {
            Figur sistefigur = tempArrayList.remove(tempArrayList.size() - 1);
            figurArrayList.add(sistefigur);
            if (sistefigur.getShape() != null) {
                tegnepane.getChildren().add(sistefigur.getShape());
            }
        }
    }


    private boolean breddegyldig() {
        try {
            double inputStrekbredde = Double.parseDouble(strekbreddeinput.getText());
            if (inputStrekbredde < 0 || inputStrekbredde > 100) {
                JOptionPane.showMessageDialog(null, "Strek bredde må være mellom 0 og 100!");
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

            if (tegn == true){
                if (e.getButton() == MouseButton.PRIMARY) {
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
                            figurArrayList.add(figur);
                            break;
                        case "Sirkel":
                            figur = new Sirkel(klikkmusx, klikkmusy, 0, valgtstrekfarge, valgtfillfarge, inputStrekbredde);
                            tegnepane.getChildren().add(figur.getShape());
                            figur.musklikk(klikkmusx, klikkmusy);
                            figurArrayList.add(figur);
                            break;
                        case "Linje":
                            figur = new Linje(klikkmusx, klikkmusy, klikkmusx, klikkmusy, valgtstrekfarge, inputStrekbredde);
                            tegnepane.getChildren().add(figur.getShape());
                            figur.musklikk(klikkmusx, klikkmusy);
                            figurArrayList.add(figur);
                            break;
                        case "Text":
                            Tekstskrevet = new Tekst(inputfratext, klikkmusx, klikkmusy, valgtstrekfarge,inputStrekbredde);
                            tegnepane.getChildren().add(Tekstskrevet.getText());
                            figurTextlist.add(Tekstskrevet);
                            figur = null;
                            break;
                        default:
                            ValgtFigur = "Rektangel";
                            figur = new Rektangel(klikkmusx, klikkmusy, 0, 0, valgtstrekfarge, valgtfillfarge, inputStrekbredde);
                            tegnepane.getChildren().add(figur.getShape());
                            figur.musklikk(klikkmusx, klikkmusy);
                            figurArrayList.add(figur);
                            break;
                    }
                }
            }
        });
    }


    private void dra(Pane tegnepane) {
        tegnepane.setOnMouseDragged(e -> {
            if (tegn == true){
                if (e.getButton() == MouseButton.PRIMARY) {
                    double musx = e.getX();
                    double musy = e.getY();

                    if (figur != null) {
                        figur.musdra(musx, musy);
                    } else if (Tekstskrevet != null) {
                        Tekstskrevet.setPos(musx,musy);
                    }
                }

            }
        });
    }

    private void lagrerelease(Pane tegnepane){
        tegnepane.setOnMouseReleased(e ->{
            if (tegn == true && figur != null){
                isValid = figur.checkarea();
            }
            if(isValid){
                tegnepane.getChildren().remove(figur.getShape());
                figurArrayList.remove(figur);
            }

        });
    }


    private void flyttklikk(Pane tegnpane){
        tegnpane.setOnMouseClicked(e->{
            if (!tegn) {


                mousex = e.getX();
                mousey = e.getY();

                figur = null;

                for (int i = figurArrayList.size() - 1; i >= 0; i--) {
                    Figur figurloop = figurArrayList.get(i);

                    if (figurloop.getShape().contains(mousex, mousey)) {
                        figur = figurloop;
                        if (figur.getShape() instanceof Line ){
                            hboxedit3.setVisible(false);
                        }else {
                            hboxedit3.setVisible(true);
                        }
                        vboxeditvar.setVisible(true);
                        figurvalgtedittext.setText(figur.getDetails());

                        forskjellx = mousex - figur.getShape().getBoundsInParent().getMinX();
                        forskjelly = mousey - figur.getShape().getBoundsInParent().getMinY();
                        

                        break;
                    } else if (!figurloop.getShape().contains(mousex, mousey)) {
                        vboxeditvar.setVisible(false);
                    }

                }
            }
        });

        tegnepane.setOnMouseDragged(e->{

        });

        fargegitt.setOnAction(event -> {
            if (figur != null) {
                Color fargevalg = fargegitt.getValue();
                figur.setStroke(fargevalg);
            }
        });

        fargeFill.setOnAction(event -> {
            if (figur != null) {
                Color fargevalg = fargeFill.getValue();
                figur.setFill(fargevalg);
            }
        });

        buttonslett.setOnAction(e2 -> {
            tegnepane.getChildren().remove(figur.getShape());
            figurArrayList.remove(figur);
        });

    }



    private VBox editpane(){
        vboxeditvar = new VBox();
        stilUI(vboxeditvar);
        hboxedit1 = new HBox();
        hboxedit2 = new HBox();
        hboxedit3 = new HBox();


        hboxedit1.getChildren().addAll(Figurvalgtedit,figurvalgtedittext);
        hboxedit2.getChildren().addAll(strekfargeny,fargegitt);
        hboxedit3.getChildren().addAll(fillfargeny,fargeFill );
        vboxeditvar.getChildren().addAll(hboxedit1,hboxedit2,hboxedit3,buttonslett);

        hboxedit1.setPadding(new Insets(100,0,50,0));
        hboxedit2.setPadding(new Insets(0,0,50,0));
        hboxedit3.setPadding(new Insets(0,0,50,0));

        styleButton(buttonslett);

        vboxeditvar.setVisible(false);
        return vboxeditvar;
    }
    private void styleButton(Button button) {
        button.setStyle(
                "-fx-background-color: #00796b;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-border-radius: 10;" +
                        "-fx-padding: 10 20;"
        );
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
