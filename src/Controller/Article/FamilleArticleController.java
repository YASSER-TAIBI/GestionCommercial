/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Article;

import Utils.Constantes;
import dao.Entity.FamilleArticle;
import dao.Manager.FamilleArticleDAO;
import dao.ManagerImpl.FamilleArticleDAOImpl;
import function.navigation;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class FamilleArticleController implements Initializable {

    
    @FXML
    private TableView<FamilleArticle> familleArticleTable;
    @FXML
    private TableColumn<FamilleArticle, String> codeFamilleArticleColumn;
    @FXML
    private TableColumn<FamilleArticle, String> libellebanqueColumn;
    @FXML
    private TableColumn<FamilleArticle, String> uniteColumn;
    
    @FXML
    private TextField codefield;
    @FXML
    private TextField libellefield;
    @FXML
    private Label codeerreur;
    @FXML
    private Label libelleerreur;
    @FXML
    private Button btnRafraichir;
    @FXML
    private Button btnModifer;
    @FXML
    private TextField uniteField;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Label uniteerreur;
    /**
     * Initializes the controller class.
     */
    
     private final ObservableList<FamilleArticle> listeFamilleArticle = FXCollections.observableArrayList();
     FamilleArticleDAO familleArticleDAO =new FamilleArticleDAOImpl();
     boolean  msgerrue = false;
     navigation nav = new navigation();
    
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            setColumnProperties() ;
         loadDetail();
    }    

        void loadDetail() {

        listeFamilleArticle.clear();
        listeFamilleArticle.addAll(familleArticleDAO.findAll());
        familleArticleTable.setItems(listeFamilleArticle);
        
    }

    void setColumnProperties() {

        codeFamilleArticleColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        libellebanqueColumn.setCellValueFactory(new PropertyValueFactory<>("liblle"));
        uniteColumn.setCellValueFactory(new PropertyValueFactory<>("unite"));
    }
    
      void Verifier()
    {
         msgerrue = false;
          if (codefield.getText().equals(Constantes.CHAMP_VIDE)) {
            codeerreur.setText(Constantes.CHAMP_ETOILE);
            codefield.requestFocus();
            msgerrue = true;

        } else {
            codeerreur.setText(Constantes.CHAMP_VIDE);

        }
           if (libellefield.getText().equals(Constantes.CHAMP_VIDE)) {
            libelleerreur.setText(Constantes.CHAMP_ETOILE);
            libellefield.requestFocus();
            msgerrue = true;

        } else {
            libelleerreur.setText(Constantes.CHAMP_VIDE);

        }
           
            if (uniteField.getText().equals(Constantes.CHAMP_VIDE)) {
            uniteerreur.setText(Constantes.CHAMP_ETOILE);
            uniteField.requestFocus();
            msgerrue = true;

        } else {
            uniteerreur.setText(Constantes.CHAMP_VIDE);

        }
    }

    void vider() {
        codefield.setText(Constantes.CHAMP_VIDE);
        libellefield.setText(Constantes.CHAMP_VIDE);
        uniteField.setText(Constantes.CHAMP_VIDE);
         codefield.requestFocus();

        vider_erreur();
        btnAjouter.setDisable(false);
        loadDetail();
        setColumnProperties();

    }

    void vider_erreur() {
    libelleerreur.setText(Constantes.CHAMP_VIDE);
    codeerreur.setText(Constantes.CHAMP_VIDE);
    uniteerreur.setText(Constantes.CHAMP_VIDE);
         btnAjouter.setDisable(true);
    }

    
    @FXML
    private void afficherFamilleArt(MouseEvent event) {
                     Integer r = familleArticleTable.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            return;
        } else {
            codefield.setText(codeFamilleArticleColumn.getCellData(r));
            libellefield.setText(libellebanqueColumn.getCellData(r));
            uniteField.setText(uniteColumn.getCellData(r));
            vider_erreur();
            btnAjouter.setDisable(true);

        }
    }




    @FXML
    private void ajouterOnAction(ActionEvent event) {
        
          msgerrue = false;
        Verifier();
        if (msgerrue == true) {
            return;
        } else {

            FamilleArticle familleArticle = new FamilleArticle();
            familleArticle.setCode(codefield.getText());
            familleArticle.setLiblle(libellefield.getText());
            familleArticle.setUnite(uniteField.getText());
            familleArticleDAO.add(familleArticle);
            nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_AJOUT);
            setColumnProperties();
            loadDetail();
            vider();

        }
        
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
        
    vider();
    }

    @FXML
    private void supprimerOnAction(ActionEvent event) {
        
        
                 Integer r = familleArticleTable.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            nav.showAlert(Alert.AlertType.WARNING, Constantes.TYPE_ALERT_INFORMATION, null, Constantes.MESSAGE_ALERT_SELECTIONNER);

        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_SUPPRIMER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();


            if (result.get() == ButtonType.OK) {
                FamilleArticle familleArticle = familleArticleTable.getSelectionModel().getSelectedItem();
                familleArticleDAO.delete(familleArticle);
                nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_SUPPRIMER);
                setColumnProperties();
                loadDetail();
                vider();
            }

        }
        
    }

    @FXML
    private void modifierOnAction(ActionEvent event) {
         msgerrue = false;
        Verifier();
        if (msgerrue == true) {
            return;
        } else {
            Integer r = familleArticleTable.getSelectionModel().getSelectedIndex();
            if (r <= -1) {
                nav.showAlert(Alert.AlertType.CONFIRMATION, Constantes.TYPE_ALERT_INFORMATION, null, Constantes.MESSAGE_ALERT_SELECTIONNER);

            } else {
               
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_MODIFICATION);
                alert.setTitle("Confirmation");
                Optional<ButtonType> result = alert.showAndWait();


                if (result.get() == ButtonType.OK) {
                    FamilleArticle familleArticle = familleArticleTable.getSelectionModel().getSelectedItem();
                    familleArticle.setCode(codefield.getText());
                    familleArticle.setLiblle(libellefield.getText());
                    familleArticle.setUnite(uniteField.getText());
                    nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_MODIFICATION);
                    familleArticleDAO.edit(familleArticle);
                    setColumnProperties();
                    loadDetail();
                    vider();
                }

            }

        }
    }
    
}
