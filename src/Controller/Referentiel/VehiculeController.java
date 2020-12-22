/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.Vehicule;
import dao.Manager.VehiculeDAO;
import dao.ManagerImpl.VehiculeDAOImpl;
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


public class VehiculeController implements Initializable {

    @FXML
    private TableView<Vehicule> vehiculeTable;
    @FXML
    private TableColumn<Vehicule, String> matriculeColumn;
    @FXML
    private TableColumn<Vehicule, String> nomColumn;
    @FXML
    private TableColumn<Vehicule, String> typeVehiculeColumn;
    
    @FXML
    private TextField matriculefield;
    @FXML
    private TextField nomfield;
    @FXML
    private ComboBox<String> typecombo;
    @FXML
    private Label nomerreur;
    @FXML
    private Label matriculeerreur;
    @FXML
    private Label typeVehiculeereur;
   
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRafraichir;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnModifer;
    
    private final ObservableList<Vehicule> listeVehicule = FXCollections.observableArrayList();
    
      ObservableList<String> typeVehicule =FXCollections.observableArrayList("Remorque","Camion");
    boolean  msgerrue = false;
 navigation nav = new navigation();
 VehiculeDAO vehiculedao=new VehiculeDAOImpl();
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        typecombo.setItems(typeVehicule);
        
        setColumnProperties();
        loadDetail();
        
        
        // TODO
    }    
    
     void loadDetail() {

        listeVehicule.clear();
        listeVehicule.addAll(vehiculedao.findAll());
        vehiculeTable.setItems(listeVehicule);
    }

    void setColumnProperties() {

        matriculeColumn.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typeVehiculeColumn.setCellValueFactory(new PropertyValueFactory<>("typeVehicule"));

    }

      
     void vider_erreur() {
    matriculeerreur.setText(Constantes.CHAMP_VIDE);
        nomerreur.setText(Constantes.CHAMP_VIDE);
         btnAjouter.setDisable(true);
    }
     
      void Verifier()
    {
         msgerrue = false;
          if (matriculefield.getText().equals(Constantes.CHAMP_VIDE)) {
            matriculeerreur.setText(Constantes.CHAMP_ETOILE);
            matriculefield.requestFocus();
            msgerrue = true;

        } else {
            matriculeerreur.setText(Constantes.CHAMP_VIDE);

        }
           if (nomfield.getText().equals(Constantes.CHAMP_VIDE)) {
            nomerreur.setText(Constantes.CHAMP_ETOILE);
            nomfield.requestFocus();
            msgerrue = true;

        } else {
            nomerreur.setText(Constantes.CHAMP_VIDE);

        }
          if (typecombo.getSelectionModel().getSelectedIndex() == -1) {
            typeVehiculeereur.setText(Constantes.CHAMP_ETOILE);
            typecombo.requestFocus();
            msgerrue = true;

        }else {
            typeVehiculeereur.setText(Constantes.CHAMP_ETOILE);

        }
        
    }

    @FXML
    private void afficherArt(MouseEvent event) {
          Integer r = vehiculeTable.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            return;
        } else {
            matriculefield.setText(matriculeColumn.getCellData(r));
            nomfield.setText(nomColumn.getCellData(r));
            typecombo.setValue(typeVehiculeColumn.getCellData(r));
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

            Vehicule vehicule = new Vehicule();
            vehicule.setMatricule(matriculefield.getText());
            vehicule.setNom(nomfield.getText());
            String  valeur = typecombo.getSelectionModel().getSelectedItem();
            vehicule.setTypeVehicule(valeur);
            vehiculedao.add(vehicule);
            nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_AJOUT);
            setColumnProperties();
            loadDetail();
            rafraichirOnAction(event);

        }
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
                    matriculefield.setText(Constantes.CHAMP_VIDE);
        nomfield.setText(Constantes.CHAMP_VIDE);
        typecombo.getSelectionModel().select(-1);
         matriculefield.requestFocus();

        vider_erreur();
        btnAjouter.setDisable(false);
        loadDetail();
        setColumnProperties();
    }

    @FXML
    private void supprimerOnAction(ActionEvent event) {
               Integer r = vehiculeTable.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            nav.showAlert(Alert.AlertType.WARNING, Constantes.TYPE_ALERT_INFORMATION, null, Constantes.MESSAGE_ALERT_SELECTIONNER);

        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_SUPPRIMER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                Vehicule vehicule = vehiculeTable.getSelectionModel().getSelectedItem();
                vehiculedao.delete(vehicule);
                nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_SUPPRIMER);
                setColumnProperties();
                loadDetail();
                rafraichirOnAction(event);
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
            Integer r = vehiculeTable.getSelectionModel().getSelectedIndex();
            if (r <= -1) {
                nav.showAlert(Alert.AlertType.CONFIRMATION, Constantes.TYPE_ALERT_INFORMATION, null, Constantes.MESSAGE_ALERT_SELECTIONNER);

            } else {
               
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_MODIFICATION);
                alert.setTitle("Confirmation");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    Vehicule vehicule = vehiculeTable.getSelectionModel().getSelectedItem();
                    vehicule.setMatricule(matriculefield.getText());
                    vehicule.setNom(nomfield.getText());
                    String  valeur = typecombo.getSelectionModel().getSelectedItem();
                    vehicule.setTypeVehicule(valeur);
                    nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_MODIFICATION);
                    vehiculedao.edit(vehicule);
                    setColumnProperties();
                    loadDetail();
                    rafraichirOnAction(event);
                }

            }

        }
    }
     
     
    
}
