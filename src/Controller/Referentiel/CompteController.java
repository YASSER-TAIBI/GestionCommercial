/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.Compte;
import dao.ManagerImpl.CompteDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import dao.Manager.CompteDAO;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class CompteController implements Initializable {

    @FXML
    private TextField libellefield;
    @FXML
    private TextField codefield;
    @FXML
    private Label codeerreur;
    @FXML
    private Label libelleerreur;
    @FXML
    private TableView<Compte> compteTable;
    @FXML
    private TableColumn<Compte, String> codeCompteColumn;
    @FXML
    private TableColumn<Compte, String> libelleColumn;
    
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
    private final ObservableList<Compte> listeCompte=FXCollections.observableArrayList();
  
     

     CompteDAO compteVendeurDAO = new CompteDAOImpl();
     navigation nav = new navigation();
   

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           
        setColumnProperties();
        loadDetail();
    }    

        void setColumnProperties(){
        
        codeCompteColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        libelleColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));

     }
    
     void loadDetail(){
        
        listeCompte.clear();
        listeCompte.addAll(compteVendeurDAO.findAll());
        compteTable.setItems(listeCompte);
    }
    
       private void clear(){
        
        btnAjouter.setDisable(false);
        codefield.clear();
        libellefield.clear();
    } 
     
    @FXML
    private void afficherArt(MouseEvent event) {
          Integer val= compteTable.getSelectionModel().getSelectedIndex();
          if (val<= -1 ){
          
          return;
          }
          else {
          
              codefield.setText(codeCompteColumn.getCellData(val));
              libellefield.setText(libelleColumn.getCellData(val));
              btnAjouter.setDisable(true);
          }
    }

    @FXML
    private void ajouterOnAction(ActionEvent event) {
        
         if(codefield.getText().equalsIgnoreCase("") && libellefield.getText().equalsIgnoreCase("") ){
  
    
         nav.showAlert(Alert.AlertType.ERROR, "Succès", null, Constantes.REMPLIR_CHAMPS);
         
             }else{
        Compte compteVendeur = new  Compte();
         
       compteVendeur.setCode(codefield.getText());
       compteVendeur.setLibelle(libellefield.getText());
       compteVendeur.setUtilisateurCreation(nav.getUtilisateur());

          compteVendeurDAO.add(compteVendeur);
                clear();
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MESSAGE_ALERT_AJOUT);
             setColumnProperties();
      loadDetail();
    }
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
        
             clear();
    }

    @FXML
    private void supprimerOnAction(ActionEvent event) {
        
                     if(compteTable.getSelectionModel().getSelectedItem()==null){
         
    
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null,Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
       Compte compte=compteTable.getSelectionModel().getSelectedItem();
        compteVendeurDAO.delete(compte);
 
    clear();
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MESSAGE_ALERT_VALIDER_SUPPRIMER);
        
        setColumnProperties();
      loadDetail();  
    }
    }

    @FXML
    private void modifierOnAction(ActionEvent event) {
        
            
         if (compteTable.getSelectionModel().getSelectedItem() != null) {
        
         Compte compte=compteTable.getSelectionModel().getSelectedItem();
         
       compte.setCode(codefield.getText());
       compte.setLibelle(libellefield.getText());

          compteVendeurDAO.edit(compte);

      clear();
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MESSAGE_ALERT_VALIDER_MODIFICATION);
             setColumnProperties();
      loadDetail();
        }else{
             nav.showAlert(Alert.AlertType.ERROR, "Errreur",  Constantes.SELECTION_ERREUR, Constantes.SELECTION_LIGNE_MODIFIER);
         }
        
    }
    
}
