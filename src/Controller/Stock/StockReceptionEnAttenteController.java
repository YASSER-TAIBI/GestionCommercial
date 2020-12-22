/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Stock;

import Utils.Constantes;
import dao.Entity.Depot;
import dao.Entity.DetailTransfertStock;
import dao.Entity.Magasin;
import dao.Entity.TransfertStock;
import dao.Manager.DepotDAO;
import dao.Manager.DetailTransfertStockDAO;
import dao.Manager.TransfertStockDAO;
import dao.ManagerImpl.DepotDAOImpl;
import dao.ManagerImpl.DetailTransfertStockDAOImpl;
import dao.ManagerImpl.TransfertStockDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
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
public class StockReceptionEnAttenteController implements Initializable {

    @FXML
    private TableView<DetailTransfertStock> detailTransfertTable;
    @FXML
    private TableColumn<DetailTransfertStock, String> codeArtColumn;
    @FXML
    private TableColumn<DetailTransfertStock, String> designationColumn;
    @FXML
    private TableColumn<DetailTransfertStock, BigDecimal> qteTransfertColumn;
    @FXML
    private TableColumn<DetailTransfertStock, BigDecimal> qteTransfertRecuColumn;
    @FXML
    private ImageView rechercheBtn;
    @FXML
    private Button btnRafraichir;
    @FXML
    private ComboBox<String> magasinCombo;
    @FXML
    private ComboBox<String> depotCombo;

    
    @FXML
    private TableView<TransfertStock> transfertTable;
    @FXML
    private TableColumn<TransfertStock, String> codeTransColumn;
    @FXML
    private TableColumn<TransfertStock, Date> dateTransColumn;
    @FXML
    private TableColumn<TransfertStock, String> depotColumn;
    @FXML
    private TableColumn<TransfertStock, String> magasinColumn;
    @FXML
    private TableColumn<TransfertStock, String> statutColumn;
    
    
    /**
     * Initializes the controller class.
     */
        private Map<String, Depot> mapDepot = new HashMap<>();
    DepotDAO depotDAO = new DepotDAOImpl();
    
     private Map<String, Magasin> mapMagasin = new HashMap<>();
     
     
     DetailTransfertStockDAO detailTransfertStockDAO = new DetailTransfertStockDAOImpl();
     TransfertStockDAO transfertStockDAO = new TransfertStockDAOImpl();
         navigation nav = new navigation();
         
              TransfertStock transfertStock ;
         
        private final ObservableList<TransfertStock> listeTransfertStock=FXCollections.observableArrayList(); 
        private final ObservableList<DetailTransfertStock> listeDetailTransfertStock=FXCollections.observableArrayList(); 
    @FXML
    private Button btnStock;
    
    
    /**
     * Initializes the controller class.
     */
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

       void setColumnDetailProperties(){
   
          codeArtColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailTransfertStock, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailTransfertStock, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getArticle().getCode());
            }
        });
          
          designationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailTransfertStock, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailTransfertStock, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getArticle().getLibelle());
            }
        });
              
           qteTransfertColumn.setCellValueFactory(new PropertyValueFactory<DetailTransfertStock, BigDecimal>("quantiteTotal"));
   
           qteTransfertRecuColumn.setCellValueFactory(new PropertyValueFactory<DetailTransfertStock, BigDecimal>("quantiteTotalRecu"));
           
        
   }
       
         void setColumnProperties(){
   
          codeTransColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TransfertStock, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TransfertStock, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getCodeTransfert());
            }
        });
          
          dateTransColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TransfertStock, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<TransfertStock, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDateTransf());
            }
        });
              
             depotColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TransfertStock, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TransfertStock, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDepotSource().getLibelle1());
            }
        });
             
            magasinColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TransfertStock, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TransfertStock, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getMagasinSource().getLibelle());
            }
        });
        
                 statutColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TransfertStock, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TransfertStock, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getStatut());
            }
        });
   }
       
             void loadDetail(){
        
        listeTransfertStock.clear();
        listeTransfertStock.addAll(transfertStockDAO.findByStatutEnAttenteStockReception());
        transfertTable.setItems(listeTransfertStock);
    }
    
    @FXML
    private void rechercheArticle(MouseEvent event) {
        
         if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
        if(
          depotCombo.getSelectionModel().getSelectedIndex()== -1 ||
          magasinCombo.getSelectionModel().getSelectedIndex()== -1    
          ) 
          {
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
         
         return;
         
     }else{    	
               listeDetailTransfertStock.clear();
                  listeTransfertStock.clear(); 
                  
              Depot depot  = mapDepot.get(depotCombo.getSelectionModel().getSelectedItem());
              Magasin magasin  = mapMagasin.get(magasinCombo.getSelectionModel().getSelectedItem());
            
            
           List<TransfertStock> transfertStock = transfertStockDAO.findByDepotAndMagasinStockReception(depot.getId(), magasin.getId());
              listeTransfertStock.addAll(transfertStock);
        transfertTable.setItems(listeTransfertStock);

        setColumnProperties();
                                    }
 }
        
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
        
                 if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
             listeDetailTransfertStock.clear();         
        depotCombo.getSelectionModel().select(-1);
        magasinCombo.getSelectionModel().select(-1);
        
        setColumnProperties();
            loadDetail();
    }
        
    }

    @FXML
    private void afficherArt(MouseEvent event) {
        
          if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
        
             setColumnDetailProperties();
        listeDetailTransfertStock.clear();
        
if (transfertTable.getSelectionModel().getSelectedIndex()!=-1){
    
        transfertStock = transfertTable.getSelectionModel().getSelectedItem();

      listeDetailTransfertStock.addAll(detailTransfertStockDAO.findByTransfertStock(transfertStock.getId()));

        detailTransfertTable.setItems(listeDetailTransfertStock);
        
    }
          }
        
    }

    @FXML
    private void magasinComboOnAction(ActionEvent event) {
    }

    @FXML
    private void depotComboOnAction(ActionEvent event) {
        
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

    @FXML
    private void stockOnAction(ActionEvent event) {
        
           if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
        
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
        if (transfertTable.getSelectionModel().getSelectedIndex()!=-1){
    
            
            for (int i = 0; i < listeDetailTransfertStock.size(); i++) {
                  
                DetailTransfertStock detailTransfertStock = listeDetailTransfertStock.get(i);
                
                detailTransfertStock.setQuantiteStock(detailTransfertStock.getQuantiteTotalRecu());
                
                detailTransfertStockDAO.edit(detailTransfertStock);

            }
            
        transfertStock = transfertTable.getSelectionModel().getSelectedItem();
        
        transfertStock.setStatut(Constantes.ETAT_STATUT_RECEPTION);
        transfertStock.setDetailTransfertStock(listeDetailTransfertStock);
        
        transfertStockDAO.edit(transfertStock);
        
            rafraichirOnAction(event);
        
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MESSAGE_ALERT_AJOUT); 
                   
        }
    }
           }

}
}