/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import dao.ManagerImpl.UtilisateurDAOImpl;
import dao.Manager.UtilisateurDAO;
import dao.Entity.Utilisateur;
import function.navigation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import eu.hansolo.enzo.notification.Notification.Notifier;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sun.plugin.util.NotifierObject;

/**
 *
 * @author khatari-pc
 */
public class LoginAppController implements Initializable {
    
    public  Stage stage = new Stage();
    navigation nav = new navigation();
    
   @FXML private Label labelChampObligatoire;
    
    @FXML private TextField login;
    
    @FXML private PasswordField password;
    
    private ImageView button;

    UtilisateurDAO utilisateurDAO =new UtilisateurDAOImpl();
    
    public static Utilisateur utilisateur;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnQuitter;
  
    
    private void handleButtonAction(ActionEvent event) {
             
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public TextField getLogin() {
        return login;
    }

    public PasswordField getPassword() {
        return password;
    }

    public void setLogin(TextField login) {
        this.login = login;
    }

    public void setPassword(PasswordField password) {
        this.password = password;
    }
      public Label getLabel() {
        return labelChampObligatoire;
    }

    public void setLabel(Label label) {
        this.labelChampObligatoire = label;
    }

    public ImageView getButton() {
        return button;
    }

    public void setButton(ImageView button) {
        this.button = button;
    }


    @FXML
    private void ajouterOnAction(ActionEvent event) {
        
            // Utilisateur utilisateur = new Utilisateur ();
        labelChampObligatoire.setText("");
        
        if(login.getText().equalsIgnoreCase("") || password.getText().equalsIgnoreCase("")){
            labelChampObligatoire.setText("Champ Obligatoire");
        }else {
             utilisateur=utilisateurDAO.findUtilisateurByLoginMotPasse(login.getText().toLowerCase(), password.getText().toLowerCase());
             
             if(utilisateur==null){
                 labelChampObligatoire.setText("Login/Mot de passe Incorrect");
                 
             }else {
                 nav.setUtilisateur(utilisateur);
                    Stage stage2 = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    stage2.hide();
                    

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource(nav.getHome()));
                    
                    try {
                        loader.load();
                    } catch (Exception e) {
                    }
                    
                    Parent p = loader.getRoot();
//                    Stage stage = new Stage();
                    Scene pp = new Scene(p);
                   
                    
                    Notifier.INSTANCE.notifySuccess("Bienvenu "+login.getText().toLowerCase(), "Connexion effectuée avec succès\n votre session a été chargée Convenablement");
                              
                    
                    Image image= new Image(getClass().getResourceAsStream("/Styles/img/logo.png"));
                    stage.getIcons().add(image);
                    stage.setScene(pp);
                    stage.setTitle("Home");
                    stage.initStyle(StageStyle.DECORATED);
                     stage.show();
                     
                     
                 
             }
            
        }
        
    }

    @FXML
    private void quitterOnAction(ActionEvent event) {
           System.exit(0);
    }
}
