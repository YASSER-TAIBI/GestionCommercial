/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Vente;

import Utils.Constantes;
import com.sun.net.httpserver.Filter;
import dao.Entity.Chauffeur;
import dao.Entity.Cheque;
import dao.Entity.Depot;
import dao.Entity.DetailTransfertCheque;
import dao.Entity.Magasin;
import dao.Entity.Sequenceur;
import dao.Entity.TransfertCheque;
import dao.Entity.Vehicule;
import dao.Entity.Vendeur;
import dao.Manager.ChauffeurDAO;
import dao.Manager.ChequeDAO;
import dao.Manager.DepotDAO;
import dao.Manager.SequenceurDAO;
import dao.Manager.TransfertChequeDAO;
import dao.Manager.VehiculeDAO;
import dao.Manager.VendeurDAO;
import dao.ManagerImpl.ChauffeurDAOImpl;
import dao.ManagerImpl.ChequeDAOImpl;
import dao.ManagerImpl.DepotDAOImpl;
import dao.ManagerImpl.SequenceurDAOImpl;
import dao.ManagerImpl.TransfertChequeDAOImpl;
import dao.ManagerImpl.VehiculeDAOImpl;
import dao.ManagerImpl.VendeurDAOImpl;
import function.navigation;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;



/**
 * FXML Controller class
 *
 * @author Hp
 */
public class TransfertChequeController implements Initializable {

    @FXML
    private ImageView ajoutBtn;
    @FXML
    private ImageView videBtn;
    @FXML
    private ImageView rechercheBtn;
    @FXML
    private TableView<Cheque> chequeTable;
    @FXML
    private TableColumn<Cheque, String> numchequeColumn;
    @FXML
    private TableColumn<Cheque, String> nomProprietaireColumn;
    @FXML
    private TableColumn<Cheque, String> TypeChequeColumn;
    @FXML
    private TableColumn<Cheque, String> banqueColumn;
    @FXML
    private TableColumn<Cheque, String> clientColumn;
    @FXML
    private TableColumn<Cheque, String> vendeurColumn;
    @FXML
    private TableColumn<Cheque, Date> dateEcheanceColumn;
    @FXML
    private TableColumn<Cheque, String> montantchequeColumn;
    @FXML
    private TableColumn<Cheque, Boolean> actionChequeColumn;
    @FXML
    private TextField montantCheque;
    @FXML
    private Label nombreCheque;
    @FXML
    private ImageView calculeCheque;
    @FXML
    private ComboBox<String> magasinSorCombo;
    @FXML
    private ComboBox<String> depotSorCombo;
    @FXML
    private ComboBox<String> vehiculeCombo;
    @FXML
    private ComboBox<String> chauffeurCombo;
    @FXML
    private ComboBox<String> OperateurSourceCombo;
    @FXML
    private ComboBox<String> depotDesCombo;
    @FXML
    private ComboBox<String> magasinDesCombo;
    @FXML
    private ComboBox<String> OperateurDestinationCombo;
    @FXML
    private DatePicker dateTransfertCheque;
    @FXML
    private TextField libelleField;
    @FXML
    private ImageView imprimerBtn;
    
     private Map<String, Vendeur> mapVendeur = new HashMap<>();
    VendeurDAO vendeurDAO = new VendeurDAOImpl();

        private Map<String, Depot> mapDepot = new HashMap<>();
    DepotDAO depotDAO = new DepotDAOImpl();
    
     private Map<String, Vehicule> mapVehicule = new HashMap<>();
    VehiculeDAO vehiculeDAO = new VehiculeDAOImpl();
    
     private Map<String, Magasin> mapMagasin = new HashMap<>();
         navigation nav = new navigation();
         
     private Map<String, Chauffeur> mapChauffeur = new HashMap<>();
    ChauffeurDAO chauffeurDAO = new ChauffeurDAOImpl();
    
     private final ObservableList<Cheque> listeCheque = FXCollections.observableArrayList();
      private final ObservableList<DetailTransfertCheque > listeDetailTransfertCheques = FXCollections.observableArrayList();
      
      
      ChequeDAO chequeDAO = new ChequeDAOImpl();
     TransfertChequeDAO transfertChequeDAO = new TransfertChequeDAOImpl();
      SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();
      
    /**
     * Initializes the controller class.
     */
            TransfertCheque transfertCheque = new TransfertCheque();
          int valeur;
  
       void Incrementation (){
       
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.TRANSFERT_CHEQUE);
      libelleField.setText(sequenceur.getCode()+" "+(sequenceur.getValeur()+1));

   }
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
//           int Maxid = transfertChequeDAO.getMaxId();
//        libelleField.setText(nav.generCode(Constantes.TRANSFERT_CHEQUE, Maxid));
        
                 Incrementation();


//             List<Vendeur> listVendeur = vendeurDAO.findAll();
//
//        listVendeur.stream().map((vendeur) -> {
//            OperateurSourceCombo.getItems().addAll(vendeur.getTypeVendeur());
//            return vendeur;
//        }).forEachOrdered((vendeur) -> {
//            mapVendeur.put(vendeur.getTypeVendeur(), vendeur);
//        });
//        
//           List<Vendeur> listVendeur1 = vendeurDAO.findAll();
//
//        listVendeur1.stream().map((vendeur) -> {
//            OperateurDestinationCombo.getItems().addAll(vendeur.getTypeVendeur());
//            return vendeur;
//        }).forEachOrdered((vendeur) -> {
//            mapVendeur.put(vendeur.getTypeVendeur(), vendeur);
//        });
        
             List<Vehicule> listVehicule = vehiculeDAO.findAll();

        listVehicule.stream().map((vehicule) -> {
            vehiculeCombo.getItems().addAll(vehicule.getMatricule());
            return vehicule;
        }).forEachOrdered((vehicule) -> {
            mapVehicule.put(vehicule.getMatricule(), vehicule);
        });
        
           List<Chauffeur> listChauffeur = chauffeurDAO.findAll();

        listChauffeur.stream().map((chauffeur) -> {
            chauffeurCombo.getItems().addAll(chauffeur.getNom());
            return chauffeur;
        }).forEachOrdered((chauffeur) -> {
            mapChauffeur.put(chauffeur.getNom(), chauffeur);
        });
        
    }    

     void loadDetail(){
//       listeCheque.clear();
//        
//         Vendeur vendeur  = mapVendeur.get(OperateurSourceCombo.getSelectionModel().getSelectedItem());
//        if (vendeur.getTypeVendeur().equals(Constantes.TYPE_VENDEUR_CAISSIER)){
//
//        listeCheque.addAll(chequeDAO.findChequeByVendeur(Constantes.TYPE_VENDEUR_CAISSIER, Constantes.ETAT_STATUT_DEPOSE));
//        chequeTable.setItems(listeCheque);
//        }
//        else if (vendeur.getTypeVendeur().equals(Constantes.TYPE_VENDEUR_VENDEUR)){
//            
//         listeCheque.addAll(chequeDAO.findChequeByVendeur(Constantes.TYPE_VENDEUR_VENDEUR, Constantes.ETAT_STATUT_DEPOSE));
//        chequeTable.setItems(listeCheque);
//        }
//    
    }
    
     void setColumnProperties() {

        numchequeColumn.setCellValueFactory(new PropertyValueFactory<>("num_cheque"));
        montantchequeColumn.setCellValueFactory(new PropertyValueFactory<>("montant"));
        nomProprietaireColumn.setCellValueFactory(new PropertyValueFactory<>("nom_Proprietaire"));
        dateEcheanceColumn.setCellValueFactory(new PropertyValueFactory<>("dateEcheance"));
        TypeChequeColumn.setCellValueFactory(new PropertyValueFactory<>("typeCheque"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        
        banqueColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Cheque, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Cheque, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getBanque().getLibelle());
            }
        });
        
        vendeurColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Cheque, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Cheque, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getVendeur().getNom());
            }
        });
        
          actionChequeColumn.cellValueFactoryProperty();
          actionChequeColumn.setCellValueFactory((cellData) -> {
          Cheque cellvalue= cellData.getValue();
              BooleanProperty property = new SimpleBooleanProperty();
                      property.set(cellvalue.isAction());
                      
                      property.addListener((observabel, oldvalue,newvalue)->cellvalue.setAction(newvalue));
              
              return property; 
          });
          actionChequeColumn.setCellFactory(act-> new CheckBoxTableCell<>());
    }
    
    @FXML
    private void ajouter(MouseEvent event) throws ParseException {
        
         if(
        
          OperateurSourceCombo.getSelectionModel().getSelectedIndex()== -1 ||
          depotSorCombo.getSelectionModel().getSelectedIndex()== -1 ||
          magasinSorCombo.getSelectionModel().getSelectedIndex()== -1 ||
          OperateurDestinationCombo.getSelectionModel().getSelectedIndex()== -1 ||
          depotDesCombo.getSelectionModel().getSelectedIndex()== -1 ||
          magasinDesCombo.getSelectionModel().getSelectedIndex()== -1 ||
          vehiculeCombo.getSelectionModel().getSelectedIndex()== -1 ||
          chauffeurCombo.getSelectionModel().getSelectedIndex()== -1 ||
          libelleField.getText().equals("")||
          dateTransfertCheque.getValue().equals(null)||
          chequeTable.getItems().size() == 0 ){
      
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
     }
        else {
      
                 LocalDate localDate=dateTransfertCheque.getValue();
          Date dateTransfert=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
          
             Chauffeur chauffeur  = mapChauffeur.get(chauffeurCombo.getSelectionModel().getSelectedItem());
             Vehicule vehicule  = mapVehicule.get(vehiculeCombo.getSelectionModel().getSelectedItem());
             Depot depotDes  = mapDepot.get(depotDesCombo.getSelectionModel().getSelectedItem());
             Depot depotSou  = mapDepot.get(depotSorCombo.getSelectionModel().getSelectedItem());
             Magasin magasinDes  = mapMagasin.get(magasinDesCombo.getSelectionModel().getSelectedItem());
             Magasin magasinSou  = mapMagasin.get(magasinSorCombo.getSelectionModel().getSelectedItem());
             Vendeur vendeurSou = mapVendeur.get(OperateurSourceCombo.getSelectionModel().getSelectedItem());        
             Vendeur vendeurDes = mapVendeur.get(OperateurDestinationCombo.getSelectionModel().getSelectedItem());  
             
               for( int rows = 0;rows<chequeTable.getItems().size() ;rows++ ){
     
         if(actionChequeColumn.getCellData(rows).booleanValue()==true){

            Cheque  cheque = chequeTable.getItems().get(rows);
            
            DetailTransfertCheque detailTransfertCheque = new DetailTransfertCheque();
            
            detailTransfertCheque.setCheque(cheque);
            detailTransfertCheque.setTransfertCheque(transfertCheque);
            detailTransfertCheque.setDateCreation(new Date());
            detailTransfertCheque.setUtilisateurCreation(nav.getUtilisateur());
            
            listeDetailTransfertCheques.add(detailTransfertCheque);

         }
               }
               
        transfertCheque.setUtilisateurCreation(nav.getUtilisateur());
        transfertCheque.setChauffeur(chauffeur);
        transfertCheque.setCodeTransfertCheque(libelleField.getText());
        transfertCheque.setDateTransfCheque(dateTransfert);
        transfertCheque.setVendeurSource(vendeurSou);
        transfertCheque.setVendeurDestination(vendeurDes);
        transfertCheque.setDepotSource(depotSou);
        transfertCheque.setDepotDestination(depotDes);
        transfertCheque.setMagasinSource(magasinSou);
        transfertCheque.setMagasinDestination(magasinDes);
        transfertCheque.setVehicule(vehicule);
        transfertCheque.setDateCreation(new Date());
        transfertCheque.setEtat(Constantes.ETAT_STATUT_ATTENTE);
        transfertCheque.setDetailTransfertCheque(listeDetailTransfertCheques);
  
        transfertChequeDAO.add(transfertCheque);
               

        
         Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.TRANSFERT_CHEQUE);
           sequenceur.setValeur(sequenceur.getValeur()+1);
           sequenceurDAO.edit(sequenceur);
           Incrementation();
            
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MESSAGE_ALERT_AJOUT);
        setColumnProperties();
         Vider();
         chequeTable.getItems().clear();
         
         }
    }


    void Vider(){
     dateTransfertCheque.setValue(null);
                
        OperateurSourceCombo.getSelectionModel().select(-1);
        depotSorCombo.getSelectionModel().select(-1);
        magasinSorCombo.getSelectionModel().select(-1);
        
        OperateurDestinationCombo.getSelectionModel().select(-1);
        depotDesCombo.getSelectionModel().select(-1);
        magasinDesCombo.getSelectionModel().select(-1);
        
        vehiculeCombo.getSelectionModel().select(-1);
        chauffeurCombo.getSelectionModel().select(-1);
        
        montantCheque.setText("");

        dateTransfertCheque.setValue(null);
        
         chequeTable.getItems().clear();
        
        ajoutBtn.setDisable(false);
//        loadDetail();
        setColumnProperties();
    
    
    }
    
    @FXML
    private void vider(MouseEvent event) {
        
       Vider();
    }


   
    
    
    @FXML
    private void rechercheArticle(MouseEvent event) {
        
          if(OperateurSourceCombo.getSelectionModel().getSelectedIndex()== -1)
          {
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
          }
          
        else {
              loadDetail();
          setColumnProperties();
          chequeTable.setEditable(true);
          actionChequeColumn.setEditable(true);
          
            }
    }

    @FXML
    private void afficherArt(MouseEvent event) {
   
    }

    @FXML
    private void calculeCheque(MouseEvent event) {
          valeur=0;
           for( int rows = 0; rows<chequeTable.getItems().size(); rows++ ){
     
         if(actionChequeColumn.getCellData(rows).booleanValue()==true){
          valeur = valeur+1;
         }
         
             }
              montantCheque.setText(String.valueOf(valeur));
    }

    @FXML
    private void depotSorComboOnAction(ActionEvent event) {
           try {
            magasinSorCombo.getItems().clear();
            Depot depot  = mapDepot.get(depotSorCombo.getSelectionModel().getSelectedItem());
            List<Magasin> listMagasin = depot.getListMagasin();
            listMagasin.stream().map((magasin) -> {
                magasinSorCombo.getItems().addAll(magasin.getLibelle());
                return magasin;
            }).forEachOrdered((magasin) -> {
                mapMagasin.put(magasin.getLibelle(), magasin);
            });
        } catch (Exception e) {
            
        }
    }

    @FXML
    private void OperateurSourceComboOnAction(ActionEvent event) {
//         try {
//            depotSorCombo.getItems().clear();
//            Vendeur vendeur  = mapVendeur.get(OperateurSourceCombo.getSelectionModel().getSelectedItem());
//            Depot depot = vendeur.getDepot();
//            
//            depotSorCombo.getItems().addAll(depot.getLibelle1());
//                mapDepot.put(depot.getLibelle1(), depot);
//           
//        } catch (Exception e) {
//            
//        }
    }

    @FXML
    private void depotDesComboOnAction(ActionEvent event) {
          try {
            magasinDesCombo.getItems().clear();
            Depot depot  = mapDepot.get(depotDesCombo.getSelectionModel().getSelectedItem());
            List<Magasin> listMagasin = depot.getListMagasin();
            listMagasin.stream().map((magasin) -> {
                magasinDesCombo.getItems().addAll(magasin.getLibelle());
                return magasin;
            }).forEachOrdered((magasin) -> {
                mapMagasin.put(magasin.getLibelle(), magasin);
            });
        } catch (Exception e) {
            
        }
    }

    @FXML
    private void OperateurDestinationComboOnAction(ActionEvent event) {
//            try {
//            depotDesCombo.getItems().clear();
//            Vendeur vendeur  = mapVendeur.get(OperateurDestinationCombo.getSelectionModel().getSelectedItem());
//            Depot depot = vendeur.getDepot();
//            
//            depotDesCombo.getItems().addAll(depot.getLibelle1());
//                mapDepot.put(depot.getLibelle1(), depot);
//           
//        } catch (Exception e) {
//            
//        }
    }

    @FXML
    private void codeEntreeTableKeyPressed(KeyEvent event) {
    }

    @FXML
    private void imprimer(MouseEvent event) {
        List<Cheque> listCheque = new ArrayList<>();
        
          for( int rows = 0;rows<chequeTable.getItems().size() ;rows++ ){
     
         if(actionChequeColumn.getCellData(rows).booleanValue()==true){
             
             Cheque  cheque = chequeTable.getItems().get(rows);
             listCheque.add(cheque);
         }
          }
          
          if( listCheque.size() != 0 ){
               try {
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(TransfertChequeController.class.getResource(nav.getiReportTransfertCheque()));

             para.put("codeTransfert",String.valueOf(libelleField.getText()));
             para.put("nombreCheque",String.valueOf(montantCheque.getText()));

             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listCheque));
               JasperViewer.viewReport(jp, false);
            
        } catch (JRException ex) {
            Logger.getLogger(TransfertChequeController.class.getName()).log(Level.SEVERE, null, ex);
        }
          }
          else{
              
            nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_SELECTIONNER_CHEQUE);
            
          }
    }
    
}
