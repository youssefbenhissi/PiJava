/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import animatefx.animation.BounceIn;
import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import animatefx.animation.Flash;
import animatefx.animation.Pulse;
import animatefx.animation.Swing;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import projet.service.CategorieClubService;
import projet.service.ClubService;

/**
 * FXML Controller class
 *
 * @author youssef
 */
public class StatistiquesCategorieEtClubController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label label;
    @FXML
    private Label countCategorie;
    @FXML
    private Label countClubs;
    @FXML
    private VBox pnl_scroll;
    @FXML
    private Pane conteneurCategories;
    @FXML
    private Pane conteneurClubs;
    @FXML
    private BarChart bar_chart;
    CategorieClubService categoriesClubService = new CategorieClubService();
    @FXML
    private StackedAreaChart area_chart;
    @FXML
    private BarChart<?, ?> vente;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    int counter = 0;
    int counterCat = 0;
    List<Integer> listdd = new ArrayList<Integer>();
    List<String> listddd = new ArrayList<String>();
    Boolean isIt = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Node[] nodes = new Node[15];

        plotBarChart();
        plotChart();
        compteurClub();
        compteurCategorie();
    }

    private void plotChart() {
        XYChart.Series seriesApril = new XYChart.Series();
        listdd = categoriesClubService.getState();
        for (int i = 0; i < listdd.size(); i++) {
            String g = categoriesClubService.getState1(listdd.get(i));
            int gg = categoriesClubService.getState12(listdd.get(i));
            seriesApril.getData().add(new XYChart.Data(g, gg));

        }

        vente.getData().addAll(seriesApril);
    }

    private void plotBarChart() {

        String austria = "Austria";
        String brazil = "Brazil";
        String france = "France";
        String italy = "Italy";
        String usa = "USA";

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2015");
        series1.getData().add(new XYChart.Data(austria, 25601.34));
        series1.getData().add(new XYChart.Data(brazil, 20148.82));
        series1.getData().add(new XYChart.Data(france, 10000));
        series1.getData().add(new XYChart.Data(italy, 35407.15));
        series1.getData().add(new XYChart.Data(usa, 12000));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("2016");
        series2.getData().add(new XYChart.Data(austria, 57401.85));
        series2.getData().add(new XYChart.Data(brazil, 41941.19));
        series2.getData().add(new XYChart.Data(france, 45263.37));
        series2.getData().add(new XYChart.Data(italy, 117320.16));
        series2.getData().add(new XYChart.Data(usa, 14845.27));

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("2017");
        series3.getData().add(new XYChart.Data(austria, 45000.65));
        series3.getData().add(new XYChart.Data(brazil, 44835.76));
        series3.getData().add(new XYChart.Data(france, 18722.18));
        series3.getData().add(new XYChart.Data(italy, 17557.31));
        series3.getData().add(new XYChart.Data(usa, 92633.68));

        bar_chart.getData().addAll(series1, series2, series3);

    }

    public void compteurClub() {
        Timer timer = new Timer(); //new timer
        counter = 0; //setting the counter to 10 sec
        ClubService cc = new ClubService();
        countClubs.setText(String.valueOf(0));
        if (cc.selectAllClubs().size() == 0) {
            countClubs.setText(String.valueOf(0));
        } else {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
                new Pulse(conteneurClubs).play();
                counter++;
                countClubs.setText(String.valueOf(counter));
                int nbrClub = cc.selectAllClubs().size();
                if (counter == nbrClub) {

                } else if (isIt) {
                    timer.cancel();
                    isIt = false;
                }
            }));
            timeline.setCycleCount(cc.selectAllClubs().size());
            timeline.play();
        }
    }

    public void compteurCategorie() {
        Timer timer = new Timer();
        CategorieClubService cs = new CategorieClubService();
        if (cs.selectAll().size() == 0) {
            countCategorie.setText(String.valueOf(0));
        } else {
            Timeline timelineCat = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
                new Pulse(conteneurCategories).play();

                counterCat++;
                countCategorie.setText(String.valueOf(counterCat));
                int nbrClub = cs.selectAll().size();
                if (counterCat == nbrClub) {
                } else if (isIt) {
                    timer.cancel();
                    isIt = false;
                }
            }));
            timelineCat.setCycleCount(cs.selectAll().size());
            timelineCat.play();
        }
    }
}
