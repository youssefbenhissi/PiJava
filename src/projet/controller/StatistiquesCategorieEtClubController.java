/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import animatefx.animation.Pulse;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import projet.service.CategorieClubService;
import projet.service.ClubService;
import projet.service.InscriptionService;

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
    private Pane conteneurInscrip;
    @FXML
    private Pane conteneurClubs;
    @FXML
    private BarChart bar_chart;
    CategorieClubService categoriesClubService = new CategorieClubService();
    InscriptionService inscrservice = new InscriptionService();
    ClubService clubServ = new ClubService();
    @FXML
    private StackedAreaChart area_chart;
    @FXML
    private BarChart<?, ?> vente;
    @FXML
    private BarChart<?, ?> statsInscri;
    @FXML
    private BarChart<?, ?> statsMoyenne;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    int counter = 0;
    int counterCat = 0;

    int counterIns = 0;
    List<Integer> listdd = new ArrayList<Integer>();
    List<String> listddd = new ArrayList<String>();
    List<Integer> listIdClubIn = new ArrayList<Integer>();
    List<Integer> listIdClub = new ArrayList<Integer>();

    Boolean isIt = false;
    @FXML
    private Label countInscrisp;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Node[] nodes = new Node[15];
        statsMoyenneLike();
        plotChart();
        statsInscri();
        compteurClub();
        compteurCategorie();
        compteurInscription();
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

    private void statsInscri() {
        XYChart.Series seriesApril = new XYChart.Series();
        listIdClub = inscrservice.getState();
        for (int i = 0; i < listIdClub.size(); i++) {
            String g = inscrservice.getState1(listIdClub.get(i));
            int gg = inscrservice.getState12(listIdClub.get(i));
            seriesApril.getData().add(new XYChart.Data(g, gg));

        }

        statsInscri.getData().addAll(seriesApril);
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

    public void compteurInscription() {
        Timer timer = new Timer();
        InscriptionService cs = new InscriptionService();
        if (cs.selectAllInscris().size() == 0) {
            countInscrisp.setText(String.valueOf(0));
        } else {

            Timeline timelineCat = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
                new Pulse(conteneurInscrip).play();
                counterIns++;
                countInscrisp.setText(String.valueOf(counterIns));
                int nbrClub = cs.selectAllInscris().size();
                if (counterCat == nbrClub) {
                } else if (isIt) {
                    timer.cancel();
                    isIt = false;
                }
            }));
            timelineCat.setCycleCount(cs.selectAllInscris().size());
            timelineCat.play();
        }
    }

    private void statsMoyenneLike() {
        XYChart.Series seriesApril = new XYChart.Series();
        listIdClubIn = clubServ.getState();
        for (int i = 0; i < listIdClubIn.size(); i++) {
            String g = clubServ.getState1(listIdClubIn.get(i));

            float gg = clubServ.getState12(listIdClubIn.get(i));

            seriesApril.getData().add(new XYChart.Data(g, gg));

        }

        statsMoyenne.getData().addAll(seriesApril);
    }
}
