/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import Utils.UtilsMenu;
import dao.Entity.Article;
import dao.Entity.Banque;
import dao.Entity.ClientPF;
import dao.Entity.Depot;
import dao.Entity.Utilisateur;
import dao.Manager.DepotDAO;
import dao.Manager.UtilisateurDAO;
import dao.ManagerImpl.DepotDAOImpl;
import dao.ManagerImpl.UtilisateurDAOImpl;
import function.navigation;
import java.net.URL;
import java.util.ArrayList;
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
public class ListeUtilisateurController implements Initializable {


    @FXML
    private TextField nomField;
    @FXML
    private TextField loginField;
    @FXML
    private TextField passwordField;
    @FXML
    private TableView<Utilisateur> tableUtilisateur;
    @FXML
    private TableColumn<Utilisateur, String> codeDepotColumn;
    @FXML
    private TableColumn<Utilisateur, String> nomColumn;
    @FXML
    private TableColumn<Utilisateur, String> loginColumn;
    @FXML
    private TableColumn<Utilisateur, String> passwordColumn;
    @FXML
    private ComboBox<String> depotCombo;
    
     Utilisateur utilisateur;
    
    /**
     * Initializes the controller class.
     */
    
      private final ObservableList<Utilisateur> listeUtilisateur = FXCollections.observableArrayList();

    UtilisateurDAO utilisateurDAO = new UtilisateurDAOImpl();
     private Map<String, Depot> mapDepot = new HashMap<>();
       List<Depot> listDepot = new ArrayList<Depot>();
     DepotDAO  depotDAO = new DepotDAOImpl();
    navigation nav = new navigation();
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRafraichir;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnModifer;
    @FXML
    private Button btnGenerer;
  
   
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            tableUtilisateur.setEditable(true);

        setColumnProperties();

        loadDetail();
             List<Depot> listDepot = depotDAO.findAll();

        listDepot.stream().map((depot) -> {
            depotCombo.getItems().addAll(depot.getLibelle1());
            return depot;
        }).forEachOrdered((depot) -> {
            mapDepot.put(depot.getLibelle1(), depot);
        });

        
    }    

    void loadDetail() {

        listeUtilisateur.clear();
        listeUtilisateur.addAll(utilisateurDAO.findAll());
        tableUtilisateur.setItems(listeUtilisateur);
    }

    void setColumnProperties() {


     codeDepotColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Utilisateur, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Utilisateur, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDepot().getLibelle1());
            }
        });

        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
      
    }
    void vider() {
        btnAjouter.setDisable(false);
        depotCombo.getSelectionModel().select(-1);
        nomField.setText("");
        loginField.setText("");
        passwordField.setText("");



        loadDetail();

    }

    
    
    
    @FXML
    private void afficherArt(MouseEvent event) {
           Integer r = tableUtilisateur.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            return;
        } else {
            depotCombo.setValue(codeDepotColumn.getCellData(r));
            nomField.setText(nomColumn.getCellData(r));
            loginField.setText(loginColumn.getCellData(r));
            passwordField.setText(passwordColumn.getCellData(r));
            btnAjouter.setDisable(true);

        }
    }


    @FXML
    private void depotComboOnAction(ActionEvent event) {
    }

    @FXML
    private void ajouterOnAction(ActionEvent event) {
        
        Utilisateur utilisateur = new Utilisateur();
        if (depotCombo.getSelectionModel().getSelectedIndex()== -1|| nomField.getText().equals("") || loginField.getText().equals("") || passwordField.getText().equals("")) {
            nav.showAlert(Alert.AlertType.CONFIRMATION, "Information", null, "Veuillez entrer tous les champs ");
        } else {
            
                Depot depot = mapDepot.get(depotCombo.getSelectionModel().getSelectedItem());
       
                utilisateur.setDepot(depot);
                utilisateur.setNom(nomField.getText().toUpperCase());
                utilisateur.setLogin(loginField.getText().toUpperCase());
                utilisateur.setPassword(passwordField.getText().toUpperCase());
                utilisateurDAO.add(utilisateur);
                nav.showAlert(Alert.AlertType.INFORMATION, "Succès", null, "Ajout avec succès");
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
        
                  Integer r = tableUtilisateur.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            nav.showAlert(Alert.AlertType.WARNING, "Information", null, "Veuillez selectionner un Utilisateur SVP !!!");

        } else {
  Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setContentText("Voullez vous vraiment Supprimer cet Utilisateur ?");
                    alert.setTitle("Confirmation");
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == ButtonType.OK) {
           Utilisateur utilisateur = tableUtilisateur.getSelectionModel().getSelectedItem();
            utilisateurDAO.delete(utilisateur);
            nav.showAlert(Alert.AlertType.INFORMATION, "Succès", null, "Utilisateur Supprimer avec succès");
            setColumnProperties();
            loadDetail();
            vider();
            }
        }
    }

    @FXML
    private void modifierOnAction(ActionEvent event) {
        
        Integer r = tableUtilisateur.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            nav.showAlert(Alert.AlertType.CONFIRMATION, "Information", null, "Veuillez selectionner un Utilisateur SVP !!!");

        } else {
            if (depotCombo.getSelectionModel().getSelectedIndex()== -1 || nomField.getText().equals("") || loginField.getText().equals("") || passwordField.getText().equals("") ) {
                nav.showAlert(Alert.AlertType.CONFIRMATION, "Information", null, "Veuillez entrer tous les champs ");
           
                } else {
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setContentText("Voullez vous vraiment Modifier cet Utilisateur ?");
                    alert.setTitle("Confirmation");
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == ButtonType.OK) {
                        Utilisateur utilisateur = tableUtilisateur.getSelectionModel().getSelectedItem();
                        
                         Depot depot = mapDepot.get(depotCombo.getSelectionModel().getSelectedItem());
              
                        utilisateur.setDepot(depot);
                        utilisateur.setNom(nomField.getText().toUpperCase());
                        utilisateur.setLogin(loginField.getText().toUpperCase());
                        utilisateur.setPassword(passwordField.getText().toUpperCase());
                      
                        nav.showAlert(Alert.AlertType.INFORMATION, "Succès", null, "Article Modifié avec succès");
                        utilisateurDAO.edit(utilisateur);
                      
                        setColumnProperties();
                        loadDetail();
                        vider();
                    }
                }
            
        }
        
    }

    @FXML
    private void genererOnAction(ActionEvent event) {
        
               UtilsMenu utilsMenu = new UtilsMenu();
        utilsMenu.genererMenuUtilisateur();
    }
    
}
