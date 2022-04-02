package com.example.projet_calculatrice;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

public class Calculatrice extends Application {
    Text text = new Text(""); //create a new text

    public void start(Stage stage) throws Exception {


        text.setFill(Color.BLACK); // define the text color
        text.setFont(Font.font("", 30));  // define the text size
        String[] number = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" }; //String array
        Button[] b = new Button[10]; // create a new Button array

        String[] operate= {"/","X","-","+","=",}; //String array
        Button[] o=new Button[5]; // create a new Button array
        GridPane gdpane = new GridPane(); // create a new gridpane

        for(int i=0;i<5;i++) {
            o[i]=new Button(operate[i]);
            o[i].setPrefSize(80, 40);
            String s=operate[i].toString();     //String array to string
            o[i].setOnAction(e->add(s));  //to produce an action when it is clicked
        }

        // create Buttons
        Button zc = new Button("%");

        Button ce = new Button("ce");
        ce.setOnAction(e->add("ce"));  //to produce an action when it is clicked
        Button yes = new Button("π");
        yes.setOnAction(e->add("3.14"));
        Button x2 = new Button("x²");
        x2.setOnAction(e->add("²"));
        Button x1 = new Button("1/x");
        x1.setOnAction(e->add("1/"));
        Button c = new Button("C");
        c.setOnAction(e->add(null));
        Button del = new Button("←");
        //del.setOnAction(e->getResult()-(int)getResult());
        Button addandmin = new Button("±");
        Button point = new Button(".");
        for (int i = 0; i < 10; i++) {
            b[i] = new Button(number[i]); //button array 0-9
            b[i].setPrefSize(80, 40); // b(10) buttons size et color
            b[i].setStyle("-fx-base:DARKSALMON");
        }
        // button size
        ce.setPrefSize(80, 40);
        zc.setPrefSize(80, 40);
        yes.setPrefSize(80, 40);
        x2.setPrefSize(80, 40);
        x1.setPrefSize(80, 40);
        c.setPrefSize(80, 40);
        del.setPrefSize(80, 40);
        addandmin.setPrefSize(80, 40);
        point.setPrefSize(80, 40);

        //buttons color
        ce.setStyle("-fx-base: DARKSALMON");
        zc.setStyle("-fx-base: DARKSALMON");
        yes.setStyle("-fx-base:DARKSALMON");
        x2.setStyle("-fx-base: DARKSALMON");
        x1.setStyle("-fx-base: DARKSALMON");
        c.setStyle("-fx-base: DARKSALMON");
        del.setStyle("-fx-base: DARKSALMON");
        o[0].setStyle("-fx-base: DARKSALMON");
        o[1].setStyle("-fx-base: DARKSALMON");
        o[2].setStyle("-fx-base: DARKSALMON");
        o[3].setStyle("-fx-base: DARKSALMON");
        o[4].setStyle("-fx-base: DARKSALMON");
        addandmin.setStyle("-fx-base: DARKSALMON");
        point.setStyle("-fx-base: DARKSALMON");

        // Borderpane divides its layout area
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(8, 8, 8, 8));

        //HBox lays out its children in a single horizontal row.
        HBox hbox = new HBox();

        BorderPane pane1 = new BorderPane();
        pane1.setRight(text);
        pane1.setBottom(hbox);
        pane.setTop(pane1);

        gdpane.add(zc, 0, 0);
        gdpane.add(yes, 1, 0);
        gdpane.add(x2, 2, 0);
        gdpane.add(x1, 3, 0);
        gdpane.add(ce, 0, 1);
        gdpane.add(c, 1, 1);
        gdpane.add(del, 2, 1);
        for(int i=0;i<5;i++)
            gdpane.add(o[i], 3, i+1);

        for (int i = 2, count = 7; i < 5; i++, count = count - 3)
            for (int j = 0; j < 3; j++)
                gdpane.add(b[count + j], j, i);
        gdpane.add(b[0], 1, 5);
        gdpane.add(addandmin, 0, 5);
        gdpane.add(point, 2, 5);

        pane.setCenter(gdpane);
        for (int i = 0; i < 10; i++) {
            String carriage = String.valueOf(i);
            b[i].setOnAction(e -> add(carriage));
        }
        //title

        Scene scene = new Scene(pane);
        stage.setTitle("Nijat_Calculatrice");
        stage.setScene(scene);
        stage.show();

    }
    //fonction
    private void add(String s) {
        String out = "";
        if (text.getText().equals("0") || text.getText().equals("Erreur"))
            text.setText("");

        switch (s) {
            case "ce":
                out = "0";
                break;
            case "C":
                out = null;
                break;



            case "%" :

            case "+":
            case "-":
            case "X":
            case "/":
                out = text.getText() + " " + s + " ";
                break;


            case "=":
                out = getResult();
                break;
            default:
                out = text.getText() + s;
                break;
        }
        text.setText(out);
    }
    private String getResult() {
        try {
            String[] textbox = text.getText().split(" ");
            float result = Float.parseFloat(textbox[0]);
            for (int i = 2; i < textbox.length; i += 2) {
                float num = Float.parseFloat(textbox[i]);

              //  if(Objects.equals(textbox[i], "²"))


                switch (textbox[i - 1]) {
                    case "+":
                        result += num;
                        break;
                    case "x²" :
                        result *=result;
                        break;

                    case "-":
                        result -= num;
                        break;
                    case "/":
                        result = Float.parseFloat(textbox[i - 2]) / num;
                        break;
                    case "X":
                        result *= num;
                        break;

                }
            }
            if (result % 1 == 0)
                return String.valueOf((int) result);
            else
                return String.valueOf(result);
        } catch (NumberFormatException e) {
            System.err.println("mauvaise entrée || null String");
            return "Erreur";
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}