/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import projet.service.CategorieClubService;

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
    private BarChart<?, ?> vente;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
  List<Integer> listdd = new ArrayList <Integer>();
   List<String> listddd = new ArrayList <String>();
   CategorieClubService cp = new CategorieClubService();
    @FXML
    private Button retour;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        XYChart.Series series1 = new XYChart.Series();
         listdd=cp.getState();
        for(int i=0;i<listdd.size();i++)
        {
          String g= cp.getState1(listdd.get(i));
          int gg=cp.getState12(listdd.get(i));
          series1.getData().add(new XYChart.Data(g,gg));
               
        }
      vente.getData().addAll(series1);
    }    
    
}
