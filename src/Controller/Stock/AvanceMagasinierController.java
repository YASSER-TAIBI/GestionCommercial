/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Stock;


import Utils.Constantes;
import dao.Entity.AvanceMagasinier;
import dao.Entity.Depot;
import dao.Entity.DetailAvanceMagasinier;
import dao.Entity.Magasin;
import dao.Manager.AvanceMagasinierDAO;
import dao.Manager.DepotDAO;
import dao.Manager.DetailAvanceMagasinierDAO;
import dao.Manager.MagasinDAO;
import dao.ManagerImpl.AvanceMagasinierDAOImpl;
import dao.ManagerImpl.DepotDAOImpl;
import dao.ManagerImpl.DetailAvanceMagasinierDAOImpl;
import dao.ManagerImpl.MagasinDAOImpl;
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
public class AvanceMagasinierController implements Initializable {

    @FXML
    private ComboBox<String> magasinCombo;
    @FXML
    private ComboBox<String> depotCombo;
    @FXML
    private ImageView rechercheBtn;
    @FXML
    private TableView<AvanceMagasinier> avanceMagasTable;
    @FXML
    private TableColumn<AvanceMagasinier, String> codeTransColumn;
    @FXML
    private TableColumn<AvanceMagasinier, Date> dateTransColumn;
    @FXML
    private TableColumn<AvanceMagasinier, String> depotColumn;
    @FXML
    private TableColumn<AvanceMagasinier, String> magasinColumn;
    @FXML
    private Button btnImprimer;
    @FXML
    private Button btnRafraichir;
    @FXML
    private TableView<DetailAvanceMagasinier> detailAvenceMagasTable;
    @FXML
    private TableColumn<DetailAvanceMagasinier, String> codeArticleColumn;
    @FXML
    private TableColumn<DetailAvanceMagasinier, String> libelleColumn;
    @FXML
    private TableColumn<DetailAvanceMagasinier, BigDecimal> qteSortieColumn;
    @FXML
    private TableColumn<DetailAvanceMagasinier, BigDecimal> PrixColumn;
    @FXML
    private TableColumn<DetailAvanceMagasinier, BigDecimal> MontantColumn;

    /**
     * Initializes the controller class.
     */
      navigation nav = new navigation();
      AvanceMagasinier avanceMagasinier = new AvanceMagasinier();
     
   private final ObservableList<AvanceMagasinier> listeAvanceMagasinier=FXCollections.observableArrayList(); 
   private final ObservableList<DetailAvanceMagasinier> listeDetailAvanceMagasinier = FXCollections.observableArrayList();
    
   DepotDAO depotDAO = new DepotDAOImpl();
    MagasinDAO magasinDAO = new MagasinDAOImpl();
   AvanceMagasinierDAO avanceMagasinierDAO = new AvanceMagasinierDAOImpl();
   DetailAvanceMagasinierDAO detailAvanceMagasinierDAO = new DetailAvanceMagasinierDAOImpl();
   
   private Map<String, Depot> mapDepot = new HashMap<>();
    private Map<String, Magasin> mapMagasin = new HashMap<>();
    
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
           
             setColumnProperties();
            loadDetail();
           }
    }    

      void loadDetail(){
        
              
                
        listeAvanceMagasinier.clear();
        listeAvanceMagasinier.addAll(avanceMagasinierDAO.findAll());
        avanceMagasTable.setItems(listeAvanceMagasinier);
    }
    
             void setColumnProperties(){
   
          codeTransColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvanceMagasinier, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<AvanceMagasinier, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getCodeTransf());
            }
        });
          
          dateTransColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvanceMagasinier, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<AvanceMagasinier, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDateTransfert());
            }
        });
              
             depotColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvanceMagasinier, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<AvanceMagasinier, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDepot().getLibelle1());
            }
        });
             
              magasinColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvanceMagasinier, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<AvanceMagasinier, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getMagasin().getLibelle());
            }
        });
             
   }
            
     void setDetailColumnProperties(){
   
          codeArticleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvanceMagasinier, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailAvanceMagasinier, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getArticle().getCode());
            }
        });
              libelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvanceMagasinier, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailAvanceMagasinier, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getArticle().getLibelle());
            }
        });
              
           qteSortieColumn.setCellValueFactory(new PropertyValueFactory<DetailAvanceMagasinier, BigDecimal>("quantiteSortie"));

   
           PrixColumn.setCellValueFactory(new PropertyValueFactory<DetailAvanceMagasinier, BigDecimal>("prix"));
           
           MontantColumn.setCellValueFactory(new PropertyValueFactory<DetailAvanceMagasinier, BigDecimal>("montant"));
           
   }
    
    @FXML
    private void magasinComboOnAction(ActionEvent event) {
    }

    @FXML
    private void depotComboOnAction(ActionEvent event) {
        
             if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
                        try {
            magasinCombo.getItems().clear();
            Depot depot  = mapDepot.get(depotCombo.getSelectionModel().getSelectedItem());
            List<Magasin> listMagasin = depot.getListMagasin();
            listMagasin.stream().map((magasin) -> {
                magasinCombo.getItems().addAll(magasin.getLibelle());
                return magasin;
            }).forEachOrdered((magasin) -> {
                mapMagasin.put(magasin.getLibelle(), magasin);
            });
        } catch (Exception e) {
            
        }
    }
        
        
    }

    @FXML
    private void rechercheArticle(MouseEvent event) {
        
           if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
         
                if(
      
          depotCombo.getSelectionModel().getSelectedIndex()== -1 &&
          magasinCombo.getSelectionModel().getSelectedIndex()== -1
          ) 
          {
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
         
         return;
         
     }else{    	
               listeDetailAvanceMagasinier.clear();
                  listeAvanceMagasinier.clear(); 
                  
              Depot depot  = mapDepot.get(depotCombo.getSelectionModel().getSelectedItem());
              Magasin magasin  = mapMagasin.get(magasinCombo.getSelectionModel().getSelectedItem());
            
           List<AvanceMagasinier> avanceMagasinier = avanceMagasinierDAO.findByDepotAndMagasin(depot.getId(), magasin.getId());
              listeAvanceMagasinier.addAll(avanceMagasinier);
        avanceMagasTable.setItems(listeAvanceMagasinier);

        setColumnProperties();
                                    }
 
             
         
         }
        
    }

    @FXML
    private void afficherArt(MouseEvent event) {
        
               if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
                 
        setDetailColumnProperties();
        listeDetailAvanceMagasinier.clear();
        
if (avanceMagasTable.getSelectionModel().getSelectedIndex()!=-1){
    
        avanceMagasinier = avanceMagasTable.getSelectionModel().getSelectedItem();

      listeDetailAvanceMagasinier.addAll(detailAvanceMagasinierDAO.findByAvanceMagasiniers(avanceMagasinier.getId()));

        detailAvenceMagasTable.setItems(listeDetailAvanceMagasinier);
    }
    }
        
    }

    @FXML
    private void imprimerOnAction(ActionEvent event) {
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
        
                  if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
             listeDetailAvanceMagasinier.clear();         
        depotCombo.getSelectionModel().select(-1);
        magasinCombo.getSelectionModel().select(-1);
        
        setColumnProperties();
            loadDetail();
    }
        
    }
    
}
