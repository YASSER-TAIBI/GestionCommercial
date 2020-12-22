/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Utils.Constantes;
import function.navigation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author user
 */

public class LoadAppController implements Initializable {

    @FXML
    private ProgressBar pbar;
    @FXML
    private ProgressIndicator pindicator;
    @FXML
    private AnchorPane anchorPane;

    /**
     * Initializes the controller class.
     */
    
    navigation nav = new navigation();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Task task = taskWorker(10);
        pbar.progressProperty().unbind();
        pindicator.progressProperty().unbind();
        pbar.progressProperty().bind(task.progressProperty());
        pindicator.progressProperty().bind(task.progressProperty());
        task.setOnSucceeded(e->{
         Stage stage = (Stage) anchorPane.getScene().getWindow();
                
                stage.close();
            try {
               
                Parent root = FXMLLoader.load(getClass().getResource(nav.getLogin()));
                Stage stage1 = new Stage();
                Scene scence = new Scene(root);
                
                Image image= new Image(getClass().getResourceAsStream("/Styles/img/logo.png"));
                stage1.getIcons().add(image);
                stage1.setScene(scence);
                
                //supprimer la bar fermer reduire agrandir
                stage1.initStyle(StageStyle.UNDECORATED);
               
                stage1.show();
            } catch (IOException ex) {
                Logger.getLogger(LoadAppController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        });
        Thread thread = new Thread(task);
        thread.start();
    }    
    private Task taskWorker(int seconds){
    return new Task(){
        @Override
        protected Object call() throws Exception {
            for(int i=0; i<seconds; i++){
                updateProgress(i+1, seconds);
                   //deactivate load de depart 10s 
//                if(!Constantes.DeveloppingMode){
                Thread.sleep(500);
//                }
            }
            return true;
        };
    };
           
        
    }
}
