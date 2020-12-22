/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.Depot;
import dao.Entity.Magasin;
import dao.Entity.Ville;
import dao.Manager.DepotDAO;
import dao.Manager.VilleDAO;
import dao.ManagerImpl.DepotDAOImpl;
import dao.ManagerImpl.VilleDAOImpl;
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
 * @author Hp
 */
public class VilleController implements Initializable {

    @FXML
    private TextField libellefield;
    @FXML
    private TextField codefield;
    @FXML
    private TableView<Ville> VilleTable;
    @FXML
    private TableColumn<Ville, String> codeVilleColumn;
    @FXML
    private TableColumn<Ville, String> libelleVilleColumn;
     @FXML
    private TableColumn<Ville, String> depotVilleColumn;
    @FXML
    private Label codeerreur;
    @FXML
    private Label libelleerreur;

   
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRafraichir;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnModifer;

    /**
     * Initializes the controller class.
     */
    
    
 private final ObservableList<Ville> listeVille = FXCollections.observableArrayList();
 VilleDAO villeDAO =new VilleDAOImpl();
 boolean  msgerrue = false;
 navigation nav = new navigation();
 private Map<String, Depot> mapDepot = new HashMap<>();
 DepotDAO  depotDAO = new DepotDAOImpl();
    
 
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        setColumnProperties() ;
         loadDetail();
        
    }    

     void loadDetail() {

        listeVille.clear();
        listeVille.addAll(villeDAO.findAll());
        VilleTable.setItems(listeVille);
        
    }

    void setColumnProperties() {

        codeVilleColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        libelleVilleColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));
           depotVilleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Ville, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Ville, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDepot().getLibelle1());
            }
        });

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
        codefield.requestFocus();

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
                Integer r = VilleTable.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            return;
        } else {
            codefield.setText(codeVilleColumn.getCellData(r));
            libellefield.setText(libelleVilleColumn.getCellData(r));
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

            Ville ville = new Ville();
            ville.setCode(codefield.getText());
            ville.setLibelle(libellefield.getText());

            ville.setDepot(nav.getUtilisateur().getDepot());
            villeDAO.add(ville);
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
        
          Integer r = VilleTable.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            nav.showAlert(Alert.AlertType.WARNING, Constantes.TYPE_ALERT_INFORMATION, null, Constantes.MESSAGE_ALERT_SELECTIONNER);

        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_SUPPRIMER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();


            if (result.get() == ButtonType.OK) {
                Ville ville = VilleTable.getSelectionModel().getSelectedItem();
                villeDAO.delete(ville);
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
            Integer r = VilleTable.getSelectionModel().getSelectedIndex();
            if (r <= -1) {
                nav.showAlert(Alert.AlertType.CONFIRMATION, Constantes.TYPE_ALERT_INFORMATION, null, Constantes.MESSAGE_ALERT_SELECTIONNER);

            } else {
               
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_MODIFICATION);
                alert.setTitle("Confirmation");
                Optional<ButtonType> result = alert.showAndWait();


                if (result.get() == ButtonType.OK) {
                    Ville ville = VilleTable.getSelectionModel().getSelectedItem();
                    ville.setCode(codefield.getText());
                    ville.setLibelle(libellefield.getText());
                    ville.setDepot(nav.getUtilisateur().getDepot());
                    nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_MODIFICATION);
                    villeDAO.edit(ville);
                    setColumnProperties();
                    loadDetail();
                    vider();
                }

            }

        }
    }


    
    
}
