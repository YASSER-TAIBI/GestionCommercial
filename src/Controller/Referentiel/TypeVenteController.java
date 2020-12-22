/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.TypeVente;
import dao.Manager.TypeVenteDAO;
import dao.ManagerImpl.TypeVenteDAOImpl;
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
public class TypeVenteController implements Initializable {

    @FXML
    private TextField libellefield;
    @FXML
    private TextField codefield;
    @FXML
    private Label codeerreur;
    @FXML
    private Label libelleerreur;
    @FXML
    private TableView<TypeVente> typeVenteTable;
    @FXML
    private TableColumn<TypeVente, String> codeTypeVenteColumn;
    @FXML
    private TableColumn<TypeVente, String> libelleColumn;

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
        private final ObservableList<TypeVente> listeTypeVente = FXCollections.observableArrayList();
 TypeVenteDAO typeVenteDAO =new TypeVenteDAOImpl();
 boolean  msgerrue = false;
 navigation nav = new navigation();
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        loadDetail();
        setColumnProperties();
    }    
    
 void loadDetail() {

        listeTypeVente.clear();
        listeTypeVente.addAll(typeVenteDAO.findAll());
        typeVenteTable.setItems(listeTypeVente);
        
    }

    void setColumnProperties() {

        codeTypeVenteColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        libelleColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));

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
                Integer r = typeVenteTable.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            return;
        } else {
            codefield.setText(codeTypeVenteColumn.getCellData(r));
            libellefield.setText(libelleColumn.getCellData(r));
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

            TypeVente typeVente = new TypeVente();
            typeVente.setCode(codefield.getText());
            typeVente.setLibelle(libellefield.getText());
            typeVenteDAO.add(typeVente);
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
             Integer r = typeVenteTable.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            nav.showAlert(Alert.AlertType.WARNING, Constantes.TYPE_ALERT_INFORMATION, null, Constantes.MESSAGE_ALERT_SELECTIONNER);

        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_SUPPRIMER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();


            if (result.get() == ButtonType.OK) {
                TypeVente typeVente = typeVenteTable.getSelectionModel().getSelectedItem();
                typeVenteDAO.delete(typeVente);
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
            Integer r = typeVenteTable.getSelectionModel().getSelectedIndex();
            if (r <= -1) {
                nav.showAlert(Alert.AlertType.CONFIRMATION, Constantes.TYPE_ALERT_INFORMATION, null, Constantes.MESSAGE_ALERT_SELECTIONNER);

            } else {
               
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_MODIFICATION);
                alert.setTitle("Confirmation");
                Optional<ButtonType> result = alert.showAndWait();


                if (result.get() == ButtonType.OK) {
                    TypeVente typeVente = typeVenteTable.getSelectionModel().getSelectedItem();
                    typeVente.setCode(codefield.getText());
                    typeVente.setLibelle(libellefield.getText());
                   
                    nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_MODIFICATION);
                    typeVenteDAO.edit(typeVente);
                    setColumnProperties();
                    loadDetail();
                    vider();
                }

            }

        }
    }

 
}
