/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.Chauffeur;
import dao.Entity.Depot;
import dao.Entity.Utilisateur;
import dao.Entity.Vendeur;
import dao.Manager.ChauffeurDAO;
import dao.ManagerImpl.ChauffeurDAOImpl;
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
public class ChauffeurController implements Initializable {

    @FXML
    private TextField codeField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField telField;
    @FXML
    private Label prixdetailerreur;
    @FXML
    private TextField adresseField;
    @FXML
    private Button btnRafraichir;
    @FXML
    private Button btnModifer;
    @FXML
    private TableView<Chauffeur> tableChauffeur;
    @FXML
    private TableColumn<Chauffeur, String> codeColumn;
    @FXML
    private TableColumn<Chauffeur, String> nomColumn;
    @FXML
    private TableColumn<Chauffeur, String> telColumn;
    @FXML
    private TableColumn<Chauffeur, String> adresseColumn;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnSupprimer;

    private final ObservableList<Chauffeur> listeChauffeur = FXCollections.observableArrayList();
      ChauffeurDAO chauffeurDAO = new ChauffeurDAOImpl();
    navigation nav = new navigation();
    Chauffeur chauffeur;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDetail() ;
         setColumnProperties();
    }    
    
      void loadDetail() {

        listeChauffeur.clear();
        listeChauffeur.addAll(chauffeurDAO.findAll());
        tableChauffeur.setItems(listeChauffeur);
    }

    void setColumnProperties() {

        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        telColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        
    }

    @FXML
    private void afficherArt(MouseEvent event) {
           
        Integer r = tableChauffeur.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            return;
        } else {
            codeField.setText(codeColumn.getCellData(r));
            nomField.setText(nomColumn.getCellData(r));
            telField.setText(telColumn.getCellData(r));
            adresseField.setText(adresseColumn.getCellData(r));
          
            btnAjouter.setDisable(true);
            
        }
    }

    @FXML
    private void ajouterOnAction(ActionEvent event) {
        
                  Chauffeur chauffeur = new Chauffeur();
        if (codeField.getText().equals("") || nomField.getText().equals("") || telField.getText().equals("") || adresseField.getText().equals("")) {
            nav.showAlert(Alert.AlertType.CONFIRMATION, "Information", null, "Veuillez entrer tous les champs ");
        } else {
//          


                chauffeur.setNom(nomField.getText());
                chauffeur.setAdresse(adresseField.getText());
                chauffeur.setTelephone(telField.getText());
                chauffeur.setCode(codeField.getText());
                chauffeur.setUtilisateurCreation(nav.getUtilisateur());
                
                chauffeurDAO.add(chauffeur);
                nav.showAlert(Alert.AlertType.INFORMATION, "Succès", null, Constantes.MESSAGE_ALERT_AJOUT);
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
        
                Integer r = tableChauffeur.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            nav.showAlert(Alert.AlertType.WARNING, "Information", null, "Veuillez selectionner un Utilisateur SVP !!!");

        } else {
  Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setContentText("Voullez vous vraiment Supprimer cet Utilisateur ?");
                    alert.setTitle("Confirmation");
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == ButtonType.OK) {
           Chauffeur chauffeur = tableChauffeur.getSelectionModel().getSelectedItem();
            chauffeurDAO.delete(chauffeur);
            nav.showAlert(Alert.AlertType.INFORMATION, "Succès", null, "Utilisateur Supprimer avec succès");
            setColumnProperties();
            loadDetail();
            vider();
            }
        }
    }

    void vider(){
        
          btnAjouter.setDisable(false);
            codeField.setText("");
        nomField.setText("");
        telField.setText("");
        adresseField.setText("");

        loadDetail();
    
    }
    


    @FXML
    private void modifierOnAction(ActionEvent event) {
           Integer r = tableChauffeur.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            nav.showAlert(Alert.AlertType.CONFIRMATION, "Information", null, "Veuillez selectionner un Utilisateur SVP !!!");

        } else {
            if (codeField.getText().equals("") || nomField.getText().equals("") || telField.getText().equals("") || adresseField.getText().equals("") ) {
                nav.showAlert(Alert.AlertType.CONFIRMATION, "Information", null, "Veuillez entrer tous les champs ");
           
                } else {
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setContentText("Voullez vous vraiment Modifier cet Utilisateur ?");
                    alert.setTitle("Confirmation");
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == ButtonType.OK) {
                        
                        Chauffeur chauffeur = tableChauffeur.getSelectionModel().getSelectedItem();
                        
                      
                        chauffeur.setCode(codeField.getText());
                        chauffeur.setNom(nomField.getText());
                        chauffeur.setTelephone(telField.getText());
                        chauffeur.setAdresse(adresseField.getText());
                      
                        nav.showAlert(Alert.AlertType.INFORMATION, "Succès", null, "Article Modifié avec succès");
                        chauffeurDAO.edit(chauffeur);
                      
                        setColumnProperties();
                        loadDetail();
                         vider();
                    }
                }
            
        }
    }
    
}
