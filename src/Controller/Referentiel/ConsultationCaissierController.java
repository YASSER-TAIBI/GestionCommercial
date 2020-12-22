/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import static Controller.Referentiel.ConsultationVendeurController.setColumnTextFieldConverter;
import dao.Entity.Caissier;
import dao.Entity.DetailCaissierVendeur;
import dao.Entity.DetailVendeurSecteur;
import dao.Entity.Vendeur;
import dao.Manager.CaissierDAO;
import dao.Manager.DetailCaissierVendeurDAO;
import dao.Manager.DetailVendeurSecteurDAO;
import dao.Manager.VendeurDAO;
import dao.ManagerImpl.CaissierDAOImpl;
import dao.ManagerImpl.DetailCaissierVendeurDAOImpl;
import dao.ManagerImpl.DetailVendeurSecteurDAOImpl;
import dao.ManagerImpl.VendeurDAOImpl;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ConsultationCaissierController implements Initializable {

    @FXML
    private ComboBox<String> caissierCombo;
    @FXML
    private TableView<DetailCaissierVendeur> tableDetailVendeur;
    @FXML
    private TableColumn<DetailCaissierVendeur, String> codeColumn;
    @FXML
    private TableColumn<DetailCaissierVendeur, String> vendeurColumn;
    @FXML
    private Button btnRafraichir;
    @FXML
    private TableView<Caissier> tableCaissier;
    @FXML
    private TableColumn<Caissier, String> codeCaissierColumn;
    @FXML
    private TableColumn<Caissier, String> caissierColumn;
    @FXML
    private TableColumn<Caissier, String> telColumn;
    @FXML
    private TableColumn<Caissier, String> compteColumn;

    /**
     * Initializes the controller class.
     */
    
        navigation nav = new navigation();
    
    CaissierDAO caissierDAO = new CaissierDAOImpl();
    DetailCaissierVendeurDAO detailCaissierVendeurDAO = new DetailCaissierVendeurDAOImpl();
    
    private Map<String, Caissier> mapCaissier = new HashMap<>();
    private final ObservableList<DetailCaissierVendeur> listDetailCaissierVendeur = FXCollections.observableArrayList();
    private final ObservableList<Caissier> listCaissier = FXCollections.observableArrayList();
    
    Caissier caissier ;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
             loadDetail();
        setColumnProperties();
        

              List<Caissier> listCaissier =  caissierDAO.findByCaissier(nav.getUtilisateur().getDepot().getId());  
            listCaissier.stream().map((caissier) -> {
                caissierCombo.getItems().addAll(caissier.getNom());
                return caissier;
            }).forEachOrdered((caissier) -> {
                mapCaissier.put(caissier.getNom(), caissier);
            });
        
              tableCaissier.setEditable(true);
        
        
    }    

    
    void loadDetail() {

        listCaissier.clear();
        listCaissier.addAll(caissierDAO.findByCaissier(nav.getUtilisateur().getDepot().getId()));
        tableCaissier.setItems(listCaissier);
    }

          void setColumnProperties() {

        codeCaissierColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Caissier, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Caissier, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getCode());
            }
        });
        
        caissierColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Caissier, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Caissier, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNom());
            }
        });

          telColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Caissier, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Caissier, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getTelephone());
            }
        });
       
          compteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Caissier, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Caissier, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getCompte().getLibelle());
            }
        });
          
   
          }
    
             void setDetailColumnProperties() {

        codeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCaissierVendeur, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCaissierVendeur, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getVendeur().getCode());
            }
        });
        vendeurColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCaissierVendeur, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCaissierVendeur, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getVendeur().getNom());
            }
        });


    }
          
    
    @FXML
    private void caissierOnAction(ActionEvent event) {
        
        listDetailCaissierVendeur.clear();
        listCaissier.clear();
        
        Caissier caissier  = mapCaissier.get(caissierCombo.getSelectionModel().getSelectedItem());

        if (caissier!= null){
        
        listCaissier.addAll(caissierDAO.findByCaissierAndDepot(caissier.getId(),nav.getUtilisateur().getDepot().getId()));
     
        tableCaissier.setItems(listCaissier);

        setColumnProperties();
        }
        
        
    }

    @FXML
    private void afficherArt(MouseEvent event) {
        
                               setDetailColumnProperties();
        listDetailCaissierVendeur.clear();
        
if (tableCaissier.getSelectionModel().getSelectedIndex()!=-1){
    
  
    
        caissier = tableCaissier.getSelectionModel().getSelectedItem();

      listDetailCaissierVendeur.addAll(detailCaissierVendeurDAO.findByCaissier(caissier.getId()));
     


        tableDetailVendeur.setItems(listDetailCaissierVendeur);

}
        
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
        
                caissierCombo.getSelectionModel().select(-1);
        listDetailCaissierVendeur.clear();
         loadDetail();
        setColumnProperties();
        
    }
    
}
