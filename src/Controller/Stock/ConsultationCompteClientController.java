/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Stock;

import Utils.Constantes;
import dao.Entity.ClientPF;
import dao.Entity.Depot;
import dao.Entity.DetailCompteClient;
import dao.Manager.ClientPFDAO;
import dao.Manager.DepotDAO;
import dao.Manager.DetailCompteClientDAO;
import dao.ManagerImpl.ClientPFDAOImpl;
import dao.ManagerImpl.DepotDAOImpl;
import dao.ManagerImpl.DetailCompteClientDAOImpl;
import function.navigation;
import java.math.BigDecimal;
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
public class ConsultationCompteClientController implements Initializable {

    @FXML
    private TableView<DetailCompteClient> detailCompteTable;
    @FXML
    private TableColumn<DetailCompteClient, String> compteClientColumn;
    @FXML
    private TableColumn<DetailCompteClient, String> designationColumn;
    @FXML
    private TableColumn<DetailCompteClient, BigDecimal> montantDebitColumn;
    @FXML
    private TableColumn<DetailCompteClient, BigDecimal> montantCreditColumn;
    @FXML
    private ImageView rechercheBtn;
    @FXML
    private TextField montantTotalCredit;
    @FXML
    private TextField montantTotalDebit;
    @FXML
    private TextField montantSolde;
    @FXML
    private ComboBox<String> clientCombo;
    @FXML
    private ComboBox<String> depotCombo;

    /**
     * Initializes the controller class.
     */
    
       private Map<String, ClientPF> mapClientPF = new HashMap<>();
    ClientPFDAO clientPFDAO = new ClientPFDAOImpl();
    
    private Map<String, Depot> mapDepot = new HashMap<>();
    DepotDAO depotDAO = new DepotDAOImpl();
    
        DetailCompteClientDAO detailCompteClientDAO = new DetailCompteClientDAOImpl();

        navigation nav = new navigation();
    
         private final ObservableList<DetailCompteClient> listeDetailCompteClient=FXCollections.observableArrayList(); 
        
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
              List<Depot> listDepot =  depotDAO.findAll();

            listDepot.stream().map((depot) -> {
                depotCombo.getItems().addAll(depot.getLibelle1());
                return depot;
            }).forEachOrdered((depot) -> {
                mapDepot.put(depot.getLibelle1(), depot);
            });
        
            loadDetail();
            setColumnProperties();
            calculeCreditDebit();
            
    }    

          void setColumnProperties(){

        designationColumn.setCellValueFactory(new PropertyValueFactory<>("designation"));
        montantDebitColumn.setCellValueFactory(new PropertyValueFactory<>("montantDebit"));
        montantCreditColumn.setCellValueFactory(new PropertyValueFactory<>("montantCredit"));
        
        compteClientColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCompteClient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCompteClient, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCompteClient().getLibelle());
            }
        });
           
    }
    
              void loadDetail(){
        
        listeDetailCompteClient.clear();
        listeDetailCompteClient.addAll(detailCompteClientDAO.findAll());
        detailCompteTable.setItems(listeDetailCompteClient);
    }
          
          void vide(){
    clientCombo.getSelectionModel().select(-1);    
    depotCombo.getSelectionModel().select(-1);    
    } 
         
          void calculeCreditDebit(){
          
             BigDecimal calculCreditTotal=BigDecimal.ZERO;
          BigDecimal calculDebitTotal=BigDecimal.ZERO;
          BigDecimal somme=BigDecimal.ZERO;
        
           for( int rows = 0;rows<detailCompteTable.getItems().size() ;rows++ ){

            calculCreditTotal = calculCreditTotal.add(montantCreditColumn.getCellData(rows));  
        
    }
         montantTotalCredit.setText(calculCreditTotal+"");
        
         
           for( int rows = 0;rows<detailCompteTable.getItems().size() ;rows++ ){

            calculDebitTotal = calculDebitTotal.add(montantDebitColumn.getCellData(rows));  
        
    }
         montantTotalDebit.setText(calculDebitTotal+"");  

         somme = calculCreditTotal.subtract(calculDebitTotal) ;
          
         montantSolde.setText(somme+"");
        
          }
          
    @FXML
    private void afficherArt(MouseEvent event) {
    }

    @FXML
    private void rechercheArticle(MouseEvent event) {
        
             listeDetailCompteClient.clear();
                   

        if(
          depotCombo.getSelectionModel().getSelectedIndex()== -1 ||
          clientCombo.getSelectionModel().getSelectedIndex()== -1    
          ) 
          {
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
         
         return;
         
     }else{
		    	
            
              ClientPF clientPF  = mapClientPF.get(clientCombo.getSelectionModel().getSelectedItem());
            
           List<DetailCompteClient> detailCompteClient = detailCompteClientDAO.findByCompteClient(clientPF.getCompteClient().getId());
              listeDetailCompteClient.addAll(detailCompteClient);
        detailCompteTable.setItems(listeDetailCompteClient);
        setColumnProperties();
        calculeCreditDebit();
        vide();
                                    }
    }

    @FXML
    private void clientComboOnAction(ActionEvent event) {
    }

    @FXML
    private void depotComboOnAction(ActionEvent event) {
        
        
               clientCombo.getItems().clear();

            Depot depot  = mapDepot.get(depotCombo.getSelectionModel().getSelectedItem());
            
            
            if(depot!=null){

            List<ClientPF> listClientPF = clientPFDAO.findClientByDepot(depot.getId());
            listClientPF.stream().map((clientPF) -> {
                clientCombo.getItems().addAll(clientPF.getNom());
                return clientPF;
            }).forEachOrdered((clientPF) -> {
                mapClientPF.put(clientPF.getNom(), clientPF);
            });
            
             }
        
    }
    
}
