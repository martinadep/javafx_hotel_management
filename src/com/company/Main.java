package com.company;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main extends Application {
    public static final double SPACING = 10.0;

    Button btnNomi = new Button("Nomi");
    Button btnPersone = new Button("Persone");
    Button btnNext = new Button(">");
    Button btnPrev = new Button("<");
    Button btnServizi = new Button("Servizi");
    Button btnNoleggi = new Button("Noleggi");

    Text txtPrenotazione = new Text ("Nome:\nPersone:\nNotti:\n\nCosto base:");
    Text txtExtra = new Text("linea1\nlinea2\nlinea3");
    Text txtCosto = new Text("Costo totale");
    Text txtSconto = new Text("Sconto");
    Text txtPrezzo = new Text("Da pagare");

    List<Prenotazione> prenotazioni = new ArrayList<>();
    int currentPren = 0;

    public Main() {
        Prenotazione prenRossi = new Prenotazione("Rossi", 2, 1);
        prenRossi.getExtras().add(new Servizio("Skipass", "giornaliero",  45.0,2 ));
        prenRossi.getExtras().add(new Noleggio("Snowboard", 130, 25.0, 1));
        prenRossi.getExtras().add(new Noleggio("Snowboard", 150,25.0,1));
        prenotazioni.add(prenRossi);

        Prenotazione prenBianchi = new Prenotazione("Bianchi",4,3);
        prenBianchi.getExtras().add(new Servizio("Skipass","mezza giornata", 80.0, 2));
        prenBianchi.getExtras().add(new Servizio("Spa", "con sauna", 80.0,2));
        prenBianchi.getExtras().add(new Servizio("Gita ai mercatini", "", 25.0, 2));
        prenBianchi.getExtras().add(new Noleggio("Completo sci", 180,25.0,2));
        prenBianchi.getExtras().add(new Noleggio("Completo sci",100,18.0,2));
        prenotazioni.add(prenBianchi);

        Prenotazione prenVerdi = new Prenotazione("Verdi", 3,7);
        prenVerdi.getExtras().add(new Servizio("Spa", "total relax", 250.0, 3));
        prenVerdi.getExtras().add(new Noleggio("Ciaspole",38,10.0,6));
        prenVerdi.getExtras().add(new Noleggio("Ciaspole",40,10.0,6));
        prenVerdi.getExtras().add(new Noleggio("Ciaspole",37,10.0,6));
        prenotazioni.add(prenVerdi);

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primarystage) throws Exception {
        update();

        btnNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                currentPren++;
                update();
            }
        });
        btnPrev.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                currentPren--;
                update();
            }
        });

        btnPersone.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                btnPersone.setDisable(true);
                btnNomi.setDisable(false);
                Collections.sort(prenotazioni);
                currentPren = 0;
                System.out.println("ordered by number:");
                stampaPren();
                update();

            }
        });
        btnNomi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                btnPersone.setDisable(false);
                btnNomi.setDisable(true);
                prenotazioni.sort(new ComparatrorByName());
                currentPren = 0;
                System.out.println("orderd by name");
                stampaPren();
                update();
            }
        });

        btnServizi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                btnServizi.setDisable(true);
                btnNoleggi.setDisable(false);
                update();

            }
        });
        btnNoleggi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                btnNoleggi.setDisable(true);
                btnServizi.setDisable(false);
                update();
            }
        });

        primarystage.setTitle("Gestione albergo - Inverno");
        primarystage.setScene(new Scene(root(), 300, 360));
        primarystage.show();
    }

    public VBox root(){
        HBox topBtns = new HBox(btnNomi, btnPersone);
        Pane detailsPrenotazione = new Pane(txtPrenotazione);
        detailsPrenotazione.setMinHeight(SPACING*10);
        VBox top = new VBox(topBtns, detailsPrenotazione);
        top.setSpacing(SPACING*2);

        HBox centerBtns = new HBox(btnServizi, btnNoleggi);
        Pane detailsExtra = new Pane(txtExtra);
        detailsExtra.setMinHeight(SPACING*10);
        VBox center = new VBox(centerBtns, detailsExtra);
        center.setSpacing(SPACING*2);

        HBox bottomBtns = new HBox(btnPrev, btnNext);
        VBox testo = new VBox(txtCosto,txtSconto,txtPrezzo);
        AnchorPane bottom = new AnchorPane(testo , bottomBtns);
        AnchorPane.setLeftAnchor(testo, 0.0);
        AnchorPane.setRightAnchor(bottomBtns, 0.0);

        VBox layout = new VBox(top, center, bottom);
        layout.setPadding(new Insets(SPACING));
        return layout;
    }

    public void update(){
        btnPrev.setDisable(currentPren == 0);
        btnNext.setDisable(currentPren == prenotazioni.size() - 1);

        Prenotazione tmp = prenotazioni.get(currentPren);
        txtPrenotazione.setText("Nome: " + tmp.cognome + "\nPersone: " + tmp.persone + "\nNotti: " + tmp.notti + "\n\nCosto base:" + String.format("%.2f $", tmp.getCostoNotti()));

        String tmpExtra = "";
        for(Extra e : tmp.getExtras()){
            if(btnServizi.isDisabled() && e instanceof Servizio){
                tmpExtra += e.descrizione();
            }
            if(btnNoleggi.isDisabled() && e instanceof Noleggio){
                tmpExtra += e.descrizione();
            }
        }
        txtExtra.setText(tmpExtra);

        txtCosto.setText(String.format("Costo totale con extra: %.2f $", tmp.getCostoConExtra()));
        double sconto = tmp.getScontoExtra();
        if(sconto > 0.0){
            txtSconto.setText(String.format("Sconto: %.2f $", sconto));
            txtSconto.setFill(Color.GREEN);
        }
        else {
            txtSconto.setText("Sconto: -");
        }
        txtPrezzo.setText(String.format("Da pagare: %.2f $", tmp.getPrezzoFinale()));
    }

    void stampaPren(){
        for(Prenotazione p : prenotazioni){
            System.out.println("- " + p.cognome + " pp. " + p.persone);

        }
        System.out.println();
    }


}
