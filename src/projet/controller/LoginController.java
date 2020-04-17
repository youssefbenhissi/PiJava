package projet.controller;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import projet.models.Utilisateur;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.mindrot.jbcrypt.BCrypt;
import projet.service.ParentService;
import projet.service.ServiceLogin;

public class LoginController {

    @FXML
    private AnchorPane GUI;
    /* utilisateur inscri + login */
    @FXML
    private TextField inscription_nom_utilisateur_fx;

    @FXML
    private PasswordField inscription_mot_de_passe_utilisateur_fx;

    @FXML
    private TextField inscription_email_utilisateur_fx;

    @FXML
    private TextField login_nom_utilisateur_fx;

    @FXML
    private PasswordField login_mot_de_passe_utilisateur_fx;

    /* end utilisateur */
    /**
     * design login *
     */
    double x = 0;
    double y = 0;

    @FXML
    void dragged(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);

    }

    @FXML
    void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    /**
     * end design login *
     */
    @FXML
    void Inscription(ActionEvent event) throws IOException {

        int status = 0;

        String nomUtilisateur = inscription_nom_utilisateur_fx.getText();
        String motDePasseUtilisateur = inscription_mot_de_passe_utilisateur_fx.getText();

        String emailUtilisateur = inscription_email_utilisateur_fx.getText();

        Utilisateur Uti = new Utilisateur();
        Uti.setNom_Utilisateur(nomUtilisateur);
        Uti.setMotDePasse_Utilisateur(motDePasseUtilisateur);
        Uti.setEmail(emailUtilisateur);

        Utilisateur existenceUtilisateur = new Utilisateur();
        existenceUtilisateur = ServiceLogin.getUtilisateur(nomUtilisateur);

        if (existenceUtilisateur == null) {
            status = ServiceLogin.Inscription(Uti);

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("le nom est déja utilisé");
            alert.showAndWait();
            return;

        }

        if (status > 0) {

            Stage stage = (Stage) GUI.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projet/views/Dashboard.fxml"));
            Parent root = loader.load();
//            WorldfriendshipController controller = (WorldfriendshipController) loader.getController();
            //          existenceUtilisateur = ServiceLogin.getUtilisateur(nomUtilisateur);
            //        controller.setUser(existenceUtilisateur);
            //          InscriptionClub controller = new InscriptionClub();
//            existenceUtilisateur = ServiceLogin.getUtilisateur(nomUtilisateur);
            //        controller.idUtilistaeur=existenceUtilisateur.getId_Utilisateur();
            Stage primaryStage = new Stage();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            return;

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("ERREUR D'inscription");
            alert.showAndWait();
        }
    }

    @FXML
    void connexionUtilisateur(ActionEvent event) throws IOException {

        if (login_nom_utilisateur_fx.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("nom d'utilisateur est vide");
            alert.showAndWait();
            return;

        } else if (login_mot_de_passe_utilisateur_fx.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("mot de passe est vide");
            alert.showAndWait();
        }

        List<Utilisateur> listUtilisateur = ServiceLogin.getTtUtilisateur();
        HashMap<String, String> hashmapUtilisateur = new HashMap<>();

        for (Utilisateur utilisateur : listUtilisateur) {
            hashmapUtilisateur.put(utilisateur.getNom_Utilisateur(), utilisateur.getMotDePasse_Utilisateur());
        }

        String nomUtilisater = login_nom_utilisateur_fx.getText();
        String motDePasseUtilisateur = login_mot_de_passe_utilisateur_fx.getText();
        for (Map.Entry<String, String> Uti : hashmapUtilisateur.entrySet()) {
            if (nomUtilisater.equals(Uti.getKey())) {
                if (ServiceLogin.testMotDePasse(motDePasseUtilisateur, Uti.getValue())) {
                    Utilisateur utilisateur = ServiceLogin.getUtilisateur(Uti.getKey());
                    if (utilisateur.getRole_Utilisateur().equals("a:0:{}")) {
                        System.out.println(utilisateur.getId_Utilisateur());
                        //InscriptionClub controller = new InscriptionClub();
//                        controller.idUtilisateur.setText("kkkk");
//                        controller.idUtilisateur.setText(Integer.toString(utilisateur.getId_Utilisateur()));
                        //controller.setIdUtilisateur(utilisateur.getId_Utilisateur());
                        //InscriptionClub con =new InscriptionClub();
                       // con.sIdUtilisateur(utilisateur.getId_Utilisateur());
                        writeUsingFileWriter(Integer.toString(utilisateur.getId_Utilisateur()));
//            existenceUtilisateur = ServiceLogin.getUtilisateur(nomUtilisateur);
                        //controller.idUtilistaeur.setText(Integer.toString(utilisateur.getId_Utilisateur()));
                        //controller.idClub.setText(Integer.toString(utilisateur.getId_Utilisateur()));
                        Stage stage = (Stage) GUI.getScene().getWindow();
                        stage.close();

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/projet/views/Dashboard.fxml"));
                        Parent root = loader.load();
                        // WorldfriendshipController controller = (WorldfriendshipController) loader.getController();
                        //controller.setUser(ServiceLogin.getUtilisateur(Uti.getKey()));

                        Stage primaryStage = new Stage();
                        Scene scene = new Scene(root);
                        primaryStage.setScene(scene);
                        primaryStage.show();
                        return;
                    } else {

                        Stage stage = (Stage) GUI.getScene().getWindow();
                        stage.close();

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/projet/views/sample.fxml"));
                        Parent root = loader.load();
                        // DashboardController controller = (DashboardController) loader.getController();
                        //controller.setUser(ServiceLogin.getUtilisateur(Uti.getKey()));

                        Stage primaryStage = new Stage();
                        Scene scene = new Scene(root);
                        primaryStage.setScene(scene);
                        primaryStage.show();
                        return;
                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("mot de passe incorrect");
                    alert.showAndWait();
                    return;
                }
            }
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("nom incorrect");
        alert.showAndWait();

    }

    @FXML
    public void connexionLoad(ActionEvent even) throws IOException {
        try {

            Stage stage = (Stage) GUI.getScene().getWindow();
            stage.close();

            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/projet/views/LoginGUI.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            primaryStage.initStyle(StageStyle.UNDECORATED);

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void CreerCompte(ActionEvent even) throws IOException {
        try {

            Stage stage = (Stage) GUI.getScene().getWindow();
            stage.close();

            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/projet/views/InscriptionGUI.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);

            primaryStage.initStyle(StageStyle.UNDECORATED);

            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void quitter() {
        // get a handle to the stage
        Stage stage = (Stage) GUI.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    private static void writeUsingFileWriter(String data) {
        File file = new File("C:\\Users\\youssef\\Desktop\\PiJava-master (2)\\FileWriter.txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            //close resources
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
     @FXML
    void MotDePasseOublie(ActionEvent event) throws IOException {
        
         Utilisateur existenceUtilisateur = new Utilisateur();
         existenceUtilisateur = ServiceLogin.getUtilisateur(login_nom_utilisateur_fx.getText());
         if(existenceUtilisateur == null)
         {
         
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Erreur !");
            alert.setContentText("Pas d'Utilisateur de ce pesudo !");
            alert.showAndWait();
         }
         else
         {
            
               String mdp = BCrypt.hashpw("1234", BCrypt.gensalt(13));
               mdp =   mdp.replaceFirst("2a", "2y");
               existenceUtilisateur.setMotDePasse_Utilisateur(mdp);
               ParentService ps = new ParentService();
               ps.modifierP(existenceUtilisateur);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Succée !");
            alert.setContentText("un email a été envoyé a l'utilisateur "+login_nom_utilisateur_fx.getText()+" contenant le nouveau mot de passe !");
            alert.showAndWait();
             try {
                 sendMail.sendMailPass(existenceUtilisateur.getEmail(), existenceUtilisateur);
             } catch (Exception ex) {
                 Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
        
       
    }
}
