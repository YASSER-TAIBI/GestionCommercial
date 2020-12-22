/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.Banque;
import dao.Entity.Compte;
import dao.Entity.CompteVersement;
import dao.Entity.VersementChequeClient;
import dao.Manager.BanqueDAO;
import dao.Manager.CompteVersementDAO;
import dao.ManagerImpl.BanqueDAOImpl;
import dao.ManagerImpl.CompteVersementDAOImpl;
import function.navigation;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class CompteVersementController implements Initializable {

    @FXML
    private TextField libellefield;
    @FXML
    private TextField codefield;
    @FXML
    private Label codeerreur;
    @FXML
    private Label libelleerreur;
    @FXML
    private ComboBox<String> banqueCombo;
    @FXML
    private Label banqueerreur;
    @FXML
    private TableView<CompteVersement> compteTable;
    @FXML
    private TableColumn<CompteVersement, String> codeCompteColumn;
    @FXML
    private TableColumn<CompteVersement, String> libelleColumn;
    @FXML
    private TableColumn<CompteVersement, String> banqueColumn;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRafraichir;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnModifer;

    private final ObservableList<CompteVersement> listeCompteVersement=FXCollections.observableArrayList();
  
     
    
    private Map<String, Banque> mapBanque = new HashMap<>();
    BanqueDAO banqueDAO = new BanqueDAOImpl();
    
     CompteVersementDAO compteVersementDAO = new CompteVersementDAOImpl();
     navigation nav = new navigation();
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
           setColumnProperties();
        loadDetail();
        
                        List<Banque> listBanque = banqueDAO.findAll();
            listBanque.stream().map((banque) -> {
                banqueCombo.getItems().addAll(banque.getLibelle());
                return banque;
            }).forEachOrdered((banque) -> {
                mapBanque.put(banque.getLibelle(), banque);
            });
        
    }    

       void setColumnProperties(){
        
        codeCompteColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        libelleColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));

         banqueColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CompteVersement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CompteVersement, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getBanque().getLibelle());
            }
        });
     }
    
     void loadDetail(){
        
        listeCompteVersement.clear();
        listeCompteVersement.addAll(compteVersementDAO.findAll());
        compteTable.setItems(listeCompteVersement);
    }
     
     
            private void clear(){
        
        btnAjouter.setDisable(false);
        codefield.clear();
        banqueCombo.getSelectionModel().select(-1);
        libellefield.clear();
    } 
    
    @FXML
    private void banqueComboOnAction(ActionEvent event) {
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
              banqueCombo.setValue(banqueColumn.getCellData(val));
              btnAjouter.setDisable(true);
          }
    }

    @FXML
    private void ajouterOnAction(ActionEvent event) {
    
          if(codefield.getText().equalsIgnoreCase("") || libellefield.getText().equalsIgnoreCase("") || banqueCombo.getItems().isEmpty() ){
  
    
         nav.showAlert(Alert.AlertType.ERROR, "Succès", null, Constantes.REMPLIR_CHAMPS);
         
             }else{
              
              Banque banque  = mapBanque.get(banqueCombo.getSelectionModel().getSelectedItem());
              
        CompteVersement compteVersement = new  CompteVersement();
         
       compteVersement.setCode(codefield.getText());
       compteVersement.setLibelle(libellefield.getText());
       compteVersement.setBanque(banque);
       compteVersement.setUtilisateurCreation(nav.getUtilisateur());

          compteVersementDAO.add(compteVersement);
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
       CompteVersement compteVersement=compteTable.getSelectionModel().getSelectedItem();
        compteVersementDAO.delete(compteVersement);
 
    clear();
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MESSAGE_ALERT_VALIDER_SUPPRIMER);
        
        setColumnProperties();
      loadDetail();  
    } 
        
    }

    @FXML
    private void modifierOnAction(ActionEvent event) {
        
            if (compteTable.getSelectionModel().getSelectedItem() != null) {
        
                     Banque banque  = mapBanque.get(banqueCombo.getSelectionModel().getSelectedItem());
                
         CompteVersement compteVersement=compteTable.getSelectionModel().getSelectedItem();
         
       compteVersement.setCode(codefield.getText());
       compteVersement.setLibelle(libellefield.getText());
       compteVersement.setBanque(banque);
          compteVersementDAO.edit(compteVersement);

      clear();
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MESSAGE_ALERT_VALIDER_MODIFICATION);
             setColumnProperties();
      loadDetail();
        }else{
             nav.showAlert(Alert.AlertType.ERROR, "Errreur",  Constantes.SELECTION_ERREUR, Constantes.SELECTION_LIGNE_MODIFIER);
         } 
        
    }
    
}
