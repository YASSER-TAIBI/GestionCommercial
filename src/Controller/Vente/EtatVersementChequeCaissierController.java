/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Vente;

import Utils.Constantes;
import dao.Entity.Caissier;
import dao.Entity.Depot;
import dao.Entity.SituationVersementChequeCaissier;
import dao.Entity.Versement;
import dao.Manager.CaissierDAO;
import dao.Manager.DepotDAO;
import dao.Manager.VersementDAO;
import dao.ManagerImpl.CaissierDAOImpl;
import dao.ManagerImpl.DepotDAOImpl;
import dao.ManagerImpl.VersementDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class EtatVersementChequeCaissierController implements Initializable {

    @FXML
    private ComboBox<String> depotCombo;
    @FXML
    private TableView<SituationVersementChequeCaissier> versementChequeCaissierTable;
    @FXML
    private TableColumn<SituationVersementChequeCaissier, String> vendeurColumn;
    @FXML
    private TableColumn<SituationVersementChequeCaissier, BigDecimal> montantChequeColumn;
    @FXML
    private TableColumn<SituationVersementChequeCaissier, BigDecimal> montantSaisieColumn;
    @FXML
    private TableColumn<SituationVersementChequeCaissier, BigDecimal> ecartColumn;
    @FXML
    private Button btnRafraichir;
    @FXML
    private ComboBox<String> caissierCombo;
    @FXML
    private ImageView rechercheBtn;

    /**
     * Initializes the controller class.
     * 
     *
     * 
     * 
     */
    private Map<String, Depot> mapDepot = new HashMap<>();
    private Map<String, Caissier> mapCaissier = new HashMap<>();
    
    CaissierDAO caissierDAO = new CaissierDAOImpl();
    
    
    VersementDAO versementDAO = new VersementDAOImpl();
       navigation nav = new navigation();
      DepotDAO depotDAO = new DepotDAOImpl();
    
      private final ObservableList<Versement> listVersement=FXCollections.observableArrayList(); 
      private final ObservableList<SituationVersementChequeCaissier> listSituationVersementChequeCaissier=FXCollections.observableArrayList(); 
      
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

          if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
                  List<Depot> listDepot =  depotDAO.findByDepotSaufSiege();

            listDepot.stream().map((depot) -> {
                depotCombo.getItems().addAll(depot.getLibelle1());
                return depot;
            }).forEachOrdered((depot) -> {
                mapDepot.put(depot.getLibelle1(), depot);
            });
          }
    }    

        void setColumnProperties() {

        vendeurColumn.setCellValueFactory(new PropertyValueFactory<>("vendeur"));
        montantChequeColumn.setCellValueFactory(new PropertyValueFactory<>("montantCheque"));
        montantSaisieColumn.setCellValueFactory(new PropertyValueFactory<>("montantVersementCheque"));
        ecartColumn.setCellValueFactory(new PropertyValueFactory<>("ecart"));

       
    }
    

    @FXML
    private void depotComboOnAction(ActionEvent event) {
        
                if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
                        try {

            Depot depot  = mapDepot.get(depotCombo.getSelectionModel().getSelectedItem());
            
             caissierCombo.getItems().clear();
           
            List<Caissier> listCaissier = caissierDAO.findByCaissier(depot.getId());
            listCaissier.stream().map((caissier) -> {
                caissierCombo.getItems().addAll(caissier.getNom());
                return caissier;
            }).forEachOrdered((caissier) -> {
                mapCaissier.put(caissier.getNom(), caissier);
            });
            
        } catch (Exception e) {
            
        }
               
    }
        
    }

    @FXML
    private void afficherArt(MouseEvent event) {
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {

           if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
        listVersement.clear();
        listSituationVersementChequeCaissier.clear();
        
        depotCombo.getSelectionModel().select(-1);
        caissierCombo.getSelectionModel().select(-1);
           }
    }

    @FXML
    private void caissierComboOnAction(ActionEvent event) {
    }

    @FXML
    private void rechercheArticle(MouseEvent event) {
        
            if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
        if(
          depotCombo.getSelectionModel().getSelectedIndex()== -1  || 
          caissierCombo.getSelectionModel().getSelectedIndex()== -1
          ) 
          {
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
         
         return;
         
     }else{    	
               listVersement.clear();
               listSituationVersementChequeCaissier.clear();
               
              Depot depot  = mapDepot.get(depotCombo.getSelectionModel().getSelectedItem());
              Caissier caissier  = mapCaissier.get(caissierCombo.getSelectionModel().getSelectedItem());

              
           List <Object[]> versement  =versementDAO.findByDepotAndCaissier(depot.getId(), caissier.getId());
         
            for(int i=0; i<versement.size(); i++) {

                    Object[] object=versement.get(i);
                   
                    String vendeur = (String.valueOf(object[0])); 
                    BigDecimal montantCheque = (BigDecimal)object[1]; 
                    BigDecimal montantVersementCheque = (BigDecimal)object[2]; 
                    BigDecimal ecart = (BigDecimal)object[3]; 


               SituationVersementChequeCaissier situationVersementChequeCaissier = new SituationVersementChequeCaissier();
               
               situationVersementChequeCaissier.setVendeur(vendeur);
               situationVersementChequeCaissier.setMontantCheque(montantCheque);
               situationVersementChequeCaissier.setMontantVersementCheque(montantVersementCheque);
               situationVersementChequeCaissier.setEcart(ecart);

                  listSituationVersementChequeCaissier.add(situationVersementChequeCaissier);
  
            }
            
             versementChequeCaissierTable.setItems(listSituationVersementChequeCaissier);
         setColumnProperties();
                                    }
        
    }
        
        
        
    }
    
}
