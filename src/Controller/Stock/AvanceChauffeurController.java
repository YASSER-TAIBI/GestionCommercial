/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Stock;

import Utils.Constantes;
import dao.Entity.AvanceChauffeur;
import dao.Entity.Chauffeur;
import dao.Entity.DetailAvanceChauffeur;
import dao.Manager.AvanceChauffeurDAO;
import dao.Manager.ChauffeurDAO;
import dao.Manager.DetailAvanceChauffeurDAO;
import dao.ManagerImpl.AvanceChauffeurDAOImpl;
import dao.ManagerImpl.ChauffeurDAOImpl;
import dao.ManagerImpl.DetailAvanceChauffeurDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class AvanceChauffeurController implements Initializable {

    @FXML
    private ComboBox<String> chauffeurCombo;
    @FXML
    private ImageView rechercheBtn;
    @FXML
    private TableView<AvanceChauffeur> avanceChauffTable;
    @FXML
    private TableColumn<AvanceChauffeur, String> codeTransColumn;
    @FXML
    private TableColumn<AvanceChauffeur, Date> dateTransColumn;
    @FXML
    private TableColumn<AvanceChauffeur, String> chauffColumn;
    @FXML
    private Button btnImprimer;
    @FXML
    private Button btnRafraichir;
    @FXML
    private TableView<DetailAvanceChauffeur> detailAvenceChauffTable;
    @FXML
    private TableColumn<DetailAvanceChauffeur, String> codeArticleColumn;
    @FXML
    private TableColumn<DetailAvanceChauffeur, String> libelleColumn;
    @FXML
    private TableColumn<DetailAvanceChauffeur, String> qteTransfertGlobalColumn;
    @FXML
    private TableColumn<DetailAvanceChauffeur, BigDecimal> qteColumn;
    @FXML
    private TableColumn<DetailAvanceChauffeur, BigDecimal> qteRecuColumn;
    @FXML
    private TableColumn<DetailAvanceChauffeur, BigDecimal> qteManqueColumn;
    @FXML
    private TableColumn<DetailAvanceChauffeur, BigDecimal> PrixColumn;
    @FXML
    private TableColumn<DetailAvanceChauffeur, BigDecimal> MontantColumn;

    /**
     * Initializes the controller class.
     */
    
     navigation nav = new navigation();
      AvanceChauffeur avanceChauffeur = new AvanceChauffeur();
     
   private final ObservableList<AvanceChauffeur> listeAvanceChauffeur=FXCollections.observableArrayList(); 
   private final ObservableList<DetailAvanceChauffeur> listeDetailAvanceChauffeur = FXCollections.observableArrayList();
    
   ChauffeurDAO chauffeurDAO = new ChauffeurDAOImpl();
   AvanceChauffeurDAO avanceChauffeurDAO = new AvanceChauffeurDAOImpl();
   DetailAvanceChauffeurDAO detailAvanceChauffeurDAO = new DetailAvanceChauffeurDAOImpl();
   
   private Map<String, Chauffeur> mapChauffeur = new HashMap<>();

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
               
                 List<Chauffeur> listChauffeur =  chauffeurDAO.findAll();

            listChauffeur.stream().map((chauffeur) -> {
                chauffeurCombo.getItems().addAll(chauffeur.getNom());
                return chauffeur;
            }).forEachOrdered((chauffeur) -> {
                mapChauffeur.put(chauffeur.getNom(), chauffeur);
            });
               
          setColumnProperties();
            loadDetail();
           }
        // TODO
    }    

       void loadDetail(){
        
              
                
        listeAvanceChauffeur.clear();
        listeAvanceChauffeur.addAll(avanceChauffeurDAO.findAll());
        avanceChauffTable.setItems(listeAvanceChauffeur);
    }
    
             void setColumnProperties(){
   
          codeTransColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvanceChauffeur, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<AvanceChauffeur, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getCodeTransf());
            }
        });
          
          dateTransColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvanceChauffeur, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<AvanceChauffeur, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDateTransfert());
            }
        });
              
             chauffColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvanceChauffeur, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<AvanceChauffeur, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getChauffeur().getNom());
            }
        });
             
   }
            
     void setDetailColumnProperties(){
   
          codeArticleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvanceChauffeur, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailAvanceChauffeur, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getArticle().getCode());
            }
        });
              libelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvanceChauffeur, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailAvanceChauffeur, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getArticle().getLibelle());
            }
        });
              
           qteColumn.setCellValueFactory(new PropertyValueFactory<DetailAvanceChauffeur, BigDecimal>("quantiteTransfert"));
           
           qteRecuColumn.setCellValueFactory(new PropertyValueFactory<DetailAvanceChauffeur, BigDecimal>("quantiteTransfertRecu"));
           
           qteManqueColumn.setCellValueFactory(new PropertyValueFactory<DetailAvanceChauffeur, BigDecimal>("quantiteManque"));
   
           PrixColumn.setCellValueFactory(new PropertyValueFactory<DetailAvanceChauffeur, BigDecimal>("prix"));
           
           MontantColumn.setCellValueFactory(new PropertyValueFactory<DetailAvanceChauffeur, BigDecimal>("montant"));
           
   }
    
    @FXML
    private void chauffeurComboOnAction(ActionEvent event) {
    }

    @FXML
    private void rechercheArticle(MouseEvent event) {
        
         if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
         
                if(
      
          chauffeurCombo.getSelectionModel().getSelectedIndex()== -1    
          ) 
          {
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
         
         return;
         
     }else{    	
               listeDetailAvanceChauffeur.clear();
                  listeAvanceChauffeur.clear(); 
                  
              Chauffeur chauffeur  = mapChauffeur.get(chauffeurCombo.getSelectionModel().getSelectedItem());
            
            
           List<AvanceChauffeur> avanceChauffeur = avanceChauffeurDAO.findByChauffeur(chauffeur.getId());
              listeAvanceChauffeur.addAll(avanceChauffeur);
        avanceChauffTable.setItems(listeAvanceChauffeur);

        setColumnProperties();
                                    }
 
             
         
         }
    }

    @FXML
    private void afficherArt(MouseEvent event) {
        
             if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
                 
        setDetailColumnProperties();
        listeDetailAvanceChauffeur.clear();
        
if (avanceChauffTable.getSelectionModel().getSelectedIndex()!=-1){
    
        avanceChauffeur = avanceChauffTable.getSelectionModel().getSelectedItem();

      listeDetailAvanceChauffeur.addAll(detailAvanceChauffeurDAO.findByAvanceChauffeur(avanceChauffeur.getId()));

        detailAvenceChauffTable.setItems(listeDetailAvanceChauffeur);
    }
    }
        
    }

    @FXML
    private void imprimerOnAction(ActionEvent event) {
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
        
              if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
             listeDetailAvanceChauffeur.clear();         
        chauffeurCombo.getSelectionModel().select(-1);

        setColumnProperties();
            loadDetail();
    }
        
    }
    
}
