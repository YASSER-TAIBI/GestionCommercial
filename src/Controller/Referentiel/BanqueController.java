/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.Banque;
import dao.Entity.Compte;
import dao.Manager.BanqueDAO;
import dao.Manager.CompteDAO;
import dao.ManagerImpl.BanqueDAOImpl;
import dao.ManagerImpl.CompteDAOImpl;
import function.navigation;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author pc
 */

public class BanqueController implements Initializable {


    @FXML
    private TextField codefield;
    @FXML
    private TextField libellefield;
    @FXML
    private Label libelleerreur;
    @FXML
    private Label codeerreur;

    /**
     * Initializes the controller class.
     */
     private final ObservableList<Banque> listeBanque = FXCollections.observableArrayList();
     BanqueDAO banquedao =new BanqueDAOImpl();
     boolean  msgerrue = false;
     navigation nav = new navigation();

    @FXML
    private TableView<Banque> BanqueTable;
    @FXML
    private TableColumn<Banque, String> codebanqueColumn;
    @FXML
    private TableColumn<Banque, String> libellebanqueColumn;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRafraichir;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnModifer;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setColumnProperties();
        loadDetail();

       
    }    
    
    void loadDetail() {

        listeBanque.clear();
        listeBanque.addAll(banquedao.findAll());
        BanqueTable.setItems(listeBanque);
    }

    void setColumnProperties() {

        codebanqueColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        libellebanqueColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));

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
    
    }

    void vider() {
        codefield.setText(Constantes.CHAMP_VIDE);
        libellefield.setText(Constantes.CHAMP_VIDE);

        vider_erreur();

        btnAjouter.setDisable(false);
        loadDetail();
        setColumnProperties();

    }

    void vider_erreur() {
    libelleerreur.setText(Constantes.CHAMP_VIDE);
    codeerreur.setText(Constantes.CHAMP_VIDE);
         btnAjouter.setDisable(true);
    }



    @FXML
    private void afficherArt(MouseEvent event) {
          Integer r = BanqueTable.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            return;
        } else {
            codefield.setText(codebanqueColumn.getCellData(r));
            libellefield.setText(libellebanqueColumn.getCellData(r));
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

            Banque banque = new Banque();
            banque.setCode(codefield.getText());
            banque.setLibelle(libellefield.getText());
            
            banquedao.add(banque);
            
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
    
        
         Integer r = BanqueTable.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            
            nav.showAlert(Alert.AlertType.WARNING, Constantes.TYPE_ALERT_INFORMATION, null, Constantes.MESSAGE_ALERT_SELECTIONNER);

        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_SUPPRIMER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();


            if (result.get() == ButtonType.OK) {
                Banque banque = BanqueTable.getSelectionModel().getSelectedItem();
                banquedao.delete(banque);
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
            Integer r = BanqueTable.getSelectionModel().getSelectedIndex();
            if (r <= -1) {
                nav.showAlert(Alert.AlertType.CONFIRMATION, Constantes.TYPE_ALERT_INFORMATION, null, Constantes.MESSAGE_ALERT_SELECTIONNER);

            } else {
               
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_MODIFICATION);
                alert.setTitle("Confirmation");
                Optional<ButtonType> result = alert.showAndWait();


                if (result.get() == ButtonType.OK) {
                    Banque banque = BanqueTable.getSelectionModel().getSelectedItem();
                    banque.setCode(codefield.getText());
                    banque.setLibelle(libellefield.getText());
                   
                    nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_MODIFICATION);
                    banquedao.edit(banque);
   
                    setColumnProperties();
                    loadDetail();
                    vider();
                }

            }

        }
        
    }

    
}
