/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import function.navigation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Hp
 */
public class MainApp extends Application {
    
      navigation nav =new navigation();
    
    
    @Override
    public void start(Stage stage) throws Exception {
           Utils.HibernateUtil.openSession();
        Parent root = FXMLLoader.load(getClass().getResource(nav.getLoadApp()));
        Scene scene = new Scene(root);
//        String css = MainApp.class.getResource("/Styles/css/FigureDeStyle.css").toExternalForm();
//        scene.getStylesheets().add(css);
        Image image= new Image(getClass().getResourceAsStream("/Styles/img/logo.png"));
        stage.getIcons().add(image);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
