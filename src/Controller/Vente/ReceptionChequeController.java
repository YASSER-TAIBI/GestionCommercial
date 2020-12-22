/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Vente;

import Utils.Constantes;
import dao.Entity.Chauffeur;
import dao.Entity.Cheque;
import dao.Entity.Depot;
import dao.Entity.DetailTransfertCheque;
import dao.Entity.Magasin;
import dao.Entity.TransfertCheque;
import dao.Entity.Vehicule;
import dao.Entity.Vendeur;
import dao.Manager.ChauffeurDAO;
import dao.Manager.DepotDAO;
import dao.Manager.TransfertChequeDAO;
import dao.Manager.VehiculeDAO;
import dao.Manager.VendeurDAO;
import dao.ManagerImpl.ChauffeurDAOImpl;
import dao.ManagerImpl.DepotDAOImpl;
import dao.ManagerImpl.TransfertChequeDAOImpl;
import dao.ManagerImpl.VehiculeDAOImpl;
import dao.ManagerImpl.VendeurDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
public class ReceptionChequeController implements Initializable {

    @FXML
    private ImageView ajoutBtn;
    @FXML
    private ImageView videBtn;
    @FXML
    private ImageView rechercheBtn;
    @FXML
    private DatePicker dateReceptionCheque;
    
    
    @FXML
    private TableView<TransfertCheque> transfertChequeTable;
    @FXML
    private TableColumn<TransfertCheque, String> codeTransfertchequeColumn;
    @FXML
    private TableColumn<TransfertCheque, Date> DateTransfertChequeColumn;
    @FXML
    private TableColumn<TransfertCheque, String> operateurSourceColumn;
    @FXML
    private TableColumn<TransfertCheque, String> depotSourceColumn;
    @FXML
    private TableColumn<TransfertCheque, String> magasinSourceColumn;
    @FXML
    private TableColumn<TransfertCheque, String> vehiculeColumn;
    @FXML
    private TableColumn<TransfertCheque, String> chauffeurColumn;
    @FXML
    private TableColumn<TransfertCheque, String> etatColumn;
    
    
    @FXML
    private TableView<DetailTransfertCheque> detailTransfertChequeTable;
    @FXML
    private TableColumn<DetailTransfertCheque, String> detailCodeTransfertchequeColumn;
    @FXML
    private TableColumn<DetailTransfertCheque, String> numChequeColumn;
    @FXML
    private TableColumn<DetailTransfertCheque, String> nomProprietaireColumn;
    @FXML
    private TableColumn<DetailTransfertCheque, String> typeChequeColumn;
    @FXML
    private TableColumn<DetailTransfertCheque, String> banqueColumn;
    @FXML
    private TableColumn<DetailTransfertCheque, Date> dateEcheanceColumn;
    @FXML
    private TableColumn<DetailTransfertCheque, BigDecimal> montantColumn;
    
    
    @FXML
    private ComboBox<String> magasinDestinationCombo;
    @FXML
    private ComboBox<String> depotDestinationCombo;
    @FXML
    private ComboBox<String> OperateurDestinationCombo;

    /**
     * Initializes the controller class.
     */
    
      private Map<String, Vendeur> mapVendeur = new HashMap<>();
    VendeurDAO vendeurDAO = new VendeurDAOImpl();

        private Map<String, Depot> mapDepot = new HashMap<>();
    DepotDAO depotDAO = new DepotDAOImpl();
    
     private Map<String, Magasin> mapMagasin = new HashMap<>();
         navigation nav = new navigation();
    
     private final ObservableList<TransfertCheque> listeTransfertCheque = FXCollections.observableArrayList();
     private final ObservableList<DetailTransfertCheque> listeDetailTransfertCheque = FXCollections.observableArrayList();
     
     
     TransfertChequeDAO transfertChequeDAO = new TransfertChequeDAOImpl();


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//            List<Vendeur> listVendeur = vendeurDAO.findAll();
//
//        listVendeur.stream().map((vendeur) -> {
//            OperateurDestinationCombo.getItems().addAll(vendeur.getTypeVendeur());
//            return vendeur;
//        }).forEachOrdered((vendeur) -> {
//            mapVendeur.put(vendeur.getTypeVendeur(), vendeur);
//        });
        
    }    
        
         void setColumnDetailProperties(){
             
         
              detailCodeTransfertchequeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailTransfertCheque, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailTransfertCheque, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getTransfertCheque().getCodeTransfertCheque());
            }
                });
                
                numChequeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailTransfertCheque, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailTransfertCheque, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getCheque().getNum_cheque());
            }
                });
                
                 nomProprietaireColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailTransfertCheque, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailTransfertCheque, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getCheque().getNom_Proprietaire());
            }
                });
                 
                  typeChequeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailTransfertCheque, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailTransfertCheque, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getCheque().getTypeCheque());
            }
                });
                  
                   banqueColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailTransfertCheque, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailTransfertCheque, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getCheque().getBanque().getLibelle());
            }
                });
                   
                dateEcheanceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailTransfertCheque, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<DetailTransfertCheque, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getCheque().getDateEcheance());
            }
                });
                
                montantColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailTransfertCheque, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailTransfertCheque, BigDecimal> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getCheque().getMontant());
            }
                });
                
                      }
            
    
       void setColumnProperties() {

        codeTransfertchequeColumn.setCellValueFactory(new PropertyValueFactory<>("codeTransfertCheque"));
        
        DateTransfertChequeColumn.setCellValueFactory(new PropertyValueFactory<>("dateTransfCheque"));
        
        etatColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));
        
        depotSourceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TransfertCheque, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TransfertCheque, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDepotSource().getLibelle1());
            }
        });
        magasinSourceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TransfertCheque, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TransfertCheque, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getMagasinSource().getLibelle());
            }
        });
        operateurSourceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TransfertCheque, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TransfertCheque, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getVendeurSource().getNom());
            }
        });
       vehiculeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TransfertCheque, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TransfertCheque, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getVehicule().getNom());
            }
        });
        chauffeurColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TransfertCheque, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TransfertCheque, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getChauffeur().getNom());
            }
        });
     
    }
    
       void loadDetail(){
           
       listeTransfertCheque.clear();
        
            Depot depot  = mapDepot.get(depotDestinationCombo.getSelectionModel().getSelectedItem());
            Magasin magasin  = mapMagasin.get(magasinDestinationCombo.getSelectionModel().getSelectedItem());
            Vendeur vendeur  = mapVendeur.get(OperateurDestinationCombo.getSelectionModel().getSelectedItem());
            
       
        listeTransfertCheque.addAll(transfertChequeDAO.findByListTransfertCheque(depot.getId(), vendeur.getId() ,magasin.getId(), Constantes.ETAT_STATUT_ATTENTE));
        transfertChequeTable.setItems(listeTransfertCheque);

    }
    
    @FXML
    private void ajouter(MouseEvent event) throws ParseException {
        
        
       if (transfertChequeTable.getSelectionModel().getSelectedItem() == null ||
                 dateReceptionCheque.getValue() == null
               ) {
                  nav.showAlert(Alert.AlertType.CONFIRMATION, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);       
       }else {
     
            TransfertCheque transfertCheque =transfertChequeTable.getSelectionModel().getSelectedItem();
            
                         LocalDate localDate=dateReceptionCheque.getValue();
          Date dateReception=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
         
            transfertCheque.setEtat(Constantes.ETAT_RECU);
            transfertCheque.setDateMaj(new Date());
            transfertCheque.setDateReceptionCheque(dateReception);
       
            transfertChequeDAO.edit(transfertCheque);
            
            nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MESSAGE_ALERT_AJOUT); 
               
              detailTransfertChequeTable.getItems().clear();
              setColumnDetailProperties();
              loadDetail();
               vider ();
       }
    }


    @FXML
void vider (){

        OperateurDestinationCombo.getSelectionModel().select(-1);
        depotDestinationCombo.getSelectionModel().select(-1);
        magasinDestinationCombo.getSelectionModel().select(-1);

        transfertChequeTable.getItems().clear();
        detailTransfertChequeTable.getItems().clear();
        dateReceptionCheque.setValue(null);
        
}

    private void vider(MouseEvent event) {
         vider ();
    }

    @FXML
    private void rechercheArticle(MouseEvent event) {
        
           if(OperateurDestinationCombo.getSelectionModel().getSelectedIndex()== -1 ||
              depotDestinationCombo.getSelectionModel().getSelectedIndex()== -1 ||
              magasinDestinationCombo.getSelectionModel().getSelectedIndex()== -1 
                   )
          {
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
          }
          
        else {
               
              loadDetail();
          setColumnProperties();

       transfertChequeTable.setEditable(true);

            }
        
           
    }

    @FXML
    private void afficherArt(MouseEvent event) {
    }

    @FXML
    private void depotDestinationComboOnAction(ActionEvent event) {
                 try {
            magasinDestinationCombo.getItems().clear();
            Depot depot  = mapDepot.get(depotDestinationCombo.getSelectionModel().getSelectedItem());
            List<Magasin> listMagasin = depot.getListMagasin();
            listMagasin.stream().map((magasin) -> {
                magasinDestinationCombo.getItems().addAll(magasin.getLibelle());
                return magasin;
            }).forEachOrdered((magasin) -> {
                mapMagasin.put(magasin.getLibelle(), magasin);
            });
        } catch (Exception e) {
            
        }
    }

    @FXML
    private void OperateurDestinationComboOnAction(ActionEvent event) {
//              try {
//            depotDestinationCombo.getItems().clear();
//            Vendeur vendeur  = mapVendeur.get(OperateurDestinationCombo.getSelectionModel().getSelectedItem());
//            Depot depot = vendeur.getDepot();
//            
//            depotDestinationCombo.getItems().addAll(depot.getLibelle1());
//                mapDepot.put(depot.getLibelle1(), depot);
//           
//        } catch (Exception e) {
//            
//        }
    }

    @FXML
    private void DetailTransfertCheque(MouseEvent event) {
                

        
         setColumnDetailProperties();
         
        listeDetailTransfertCheque.clear();
        
        if (transfertChequeTable.getSelectionModel().getSelectedIndex()!=-1){
    
        TransfertCheque transfertCheque = transfertChequeTable.getSelectionModel().getSelectedItem();

        listeDetailTransfertCheque.addAll(transfertCheque.getDetailTransfertCheque());

        detailTransfertChequeTable.setItems(listeDetailTransfertCheque);

        detailTransfertChequeTable.setEditable(true);


}

    }
    
}
