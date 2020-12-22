/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Stock;

import static Controller.Stock.SortiesClientController.setColumnTextFieldConverter;
import static Controller.Vente.VersementController.versementControllerRef;
import Utils.Constantes;
import dao.Entity.Caissier;
import dao.Entity.CompteClient;
import dao.Entity.DetailCompte;
import dao.Entity.DetailCompteClient;
import dao.Entity.DetailTransfertStock;
import dao.Entity.DetailVendeurSecteur;
import dao.Entity.Sequenceur;
import dao.Entity.Tournee;
import dao.Entity.TransfertStock;
import dao.Entity.Versement;
import dao.Entity.VersementClient;
import dao.Manager.CaissierDAO;
import dao.Manager.CompteClientDAO;
import dao.Manager.CompteDAO;
import dao.Manager.DetailCompteClientDAO;
import dao.Manager.DetailCompteDAO;
import dao.Manager.DetailTransfertStockDAO;
import dao.Manager.DetailVendeurSecteurDAO;
import dao.Manager.SequenceurDAO;
import dao.Manager.TourneeDAO;
import dao.Manager.TransfertStockDAO;
import dao.Manager.VendeurDAO;
import dao.Manager.VersementClientDAO;
import dao.Manager.VersementDAO;
import dao.ManagerImpl.CaissierDAOImpl;
import dao.ManagerImpl.CompteClientDAOImpl;
import dao.ManagerImpl.CompteDAOImpl;
import dao.ManagerImpl.DetailCompteClientDAOImpl;
import dao.ManagerImpl.DetailCompteDAOImpl;
import dao.ManagerImpl.DetailTransfertStockDAOImpl;
import dao.ManagerImpl.DetailVendeurSecteurDAOImpl;
import dao.ManagerImpl.SequenceurDAOImpl;
import dao.ManagerImpl.TourneeDAOImpl;
import dao.ManagerImpl.TransfertStockDAOImpl;
import dao.ManagerImpl.VendeurDAOImpl;
import dao.ManagerImpl.VersementClientDAOImpl;
import dao.ManagerImpl.VersementDAOImpl;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class VersementClientController implements Initializable {

    @FXML
    private TextField montantCheque;
    @FXML
    private TextField montantEspece;
    @FXML
    private TextField codeSortieField;
    @FXML
    private TextField prixTotalField;
    @FXML
    private TextField montantCredit;
    @FXML
    private TextField montantBanque;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRafraichir;
    @FXML
    private TableView<TransfertStock> transfertTable;
    @FXML
    private TableColumn<TransfertStock, Date> dateSortieColumn;
    @FXML
    private TableColumn<TransfertStock, String> clientColumn;
    @FXML
    private TableColumn<TransfertStock, String> secteurColumn;
    @FXML
    private TableColumn<TransfertStock, String> magasinColumn;
    @FXML
    private TableColumn<TransfertStock, String> etatColumn;
    
     @FXML
    private TableView<DetailTransfertStock> sortiesTable;
    @FXML
    private TableColumn<DetailTransfertStock, String> codeArticleColumn;
    @FXML
    private TableColumn<DetailTransfertStock, String> libelleColumn;
    @FXML
    private TableColumn<DetailTransfertStock, BigDecimal> qteColumn;
    @FXML
    private TableColumn<DetailTransfertStock, BigDecimal> qteCaisseColumn;
    @FXML
    private TableColumn<DetailTransfertStock, Integer> conditionnementColumn;
    @FXML
    private TableColumn<DetailTransfertStock, BigDecimal> qteTotalColumn;
    @FXML
    private TableColumn<DetailTransfertStock, String> qteSortiesGlobalColumn;
    @FXML
    private TableColumn<DetailTransfertStock, String> typeVenteColumn;
    @FXML
    private TableColumn<DetailTransfertStock, BigDecimal> montantColumn;
    
    @FXML
    private TextField vercementCredit;
    @FXML
    private TextField totalCredit;
    @FXML
    private TextField remise;
    @FXML
    private DatePicker dateReception;
    @FXML
    private TextField libelleField;
    @FXML
    private ComboBox<String> caissierCombo;

    /**
     * Initializes the controller class.
     */
    
    public  DetailCompteClient detailCompteClient ;
    public  TransfertStock transfertStock;
    /**
     * Initializes the controller class.
     */
    navigation nav = new navigation();
    
    private Map<String, DetailVendeurSecteur> mapDetailVendeurSecteur = new HashMap<>();
    DetailVendeurSecteurDAO detailVendeurSecteurDAO = new DetailVendeurSecteurDAOImpl();
    
    private Map<String, TransfertStock> mapTransfertStock = new HashMap<>();
    TransfertStockDAO  transfertStockDAO = new TransfertStockDAOImpl();
    
     DetailTransfertStockDAO detailTransfertStockDAO = new DetailTransfertStockDAOImpl();
    DetailCompteClientDAO detailCompteClientDAO = new DetailCompteClientDAOImpl();
    DetailCompteDAO detailCompteDAO = new DetailCompteDAOImpl();
    
    private Map<String,CompteClient> mapCompteClient=new HashMap<>();
    
    VersementClientDAO  versementClientDAO = new VersementClientDAOImpl();
    CompteClientDAO compteClientDAO = new  CompteClientDAOImpl();
    
    CaissierDAO caissierDAO = new CaissierDAOImpl();
    
    SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();  

    private Map<String, Caissier> mapCaissier = new HashMap<>();

    
    private final ObservableList<TransfertStock> listeTransfertStock = FXCollections.observableArrayList();
      private final ObservableList<DetailTransfertStock> listeDetailTransfertStock = FXCollections.observableArrayList();
    
    BigDecimal cheque = BigDecimal.ZERO;
    BigDecimal remiseMt = BigDecimal.ZERO;
    BigDecimal espece = BigDecimal.ZERO;
    BigDecimal recuBanque = BigDecimal.ZERO;
    BigDecimal creditVercement = BigDecimal.ZERO;
    BigDecimal creditTotal = BigDecimal.ZERO;
   
    
    
   
       
        void Incrementation (){
       
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.VERSEMENNT_C);
          libelleField.setText(sequenceur.getCode()+" "+(sequenceur.getValeur()+1));

   }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         Incrementation ();
        
               List<Caissier> listCaissier =  caissierDAO.findByCaissier(nav.getUtilisateur().getDepot().getId());
              
                    listCaissier.stream().map((caissier) -> {
                caissierCombo.getItems().addAll(caissier.getNom());
                return caissier;
            }).forEachOrdered((caissier) -> {
                mapCaissier.put(caissier.getNom(), caissier);
            });

        loadDetail();
        setColumnPropertiesTournee();

        
        
        
    }    

      void setColumnPropertiesTournee(){
   
          dateSortieColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TransfertStock, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<TransfertStock, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDateTransf());
            }
        });
          
              clientColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TransfertStock, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TransfertStock, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getClient().getNom());
            }
        });
           
        secteurColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TransfertStock, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TransfertStock, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getClient().getSecteur().getLibelle());
            }
        });
        
        magasinColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TransfertStock, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TransfertStock, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getMagasinStock().getLibelle());
            }
        });
        
        etatColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TransfertStock, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TransfertStock, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getEtat());
            }
        });
         
    }
    
      void setColumnProperties(){
   
          codeArticleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailTransfertStock, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailTransfertStock, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getArticle().getCode());
            }
        });
          
              libelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailTransfertStock, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailTransfertStock, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getArticle().getLibelle());
            }
        });
              
            conditionnementColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailTransfertStock, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<DetailTransfertStock, Integer> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getArticle().getConditionnement());
            }
        });  

           qteColumn.setCellValueFactory(new PropertyValueFactory<DetailTransfertStock, BigDecimal>("Quantite"));

           
           qteCaisseColumn.setCellValueFactory(new PropertyValueFactory<DetailTransfertStock, BigDecimal>("QuantiteCaisse"));

           
           typeVenteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailTransfertStock, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailTransfertStock, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getTypeVente().getCode());
            }
        });  
           
           montantColumn.setCellValueFactory(new PropertyValueFactory<DetailTransfertStock, BigDecimal>("montant"));
           
           qteTotalColumn.setCellValueFactory(new PropertyValueFactory<DetailTransfertStock, BigDecimal>("quantiteTotal"));
           
           

   
   }
      
       void loadDetail() {

        listeTransfertStock.clear();
        listeTransfertStock.addAll(transfertStockDAO.findByEtatSortie(Constantes.ETAT_VERSEMENT_CLIENT,Constantes.ETAT_STATUT_SORTIES));
        transfertTable.setItems(listeTransfertStock);
    }
    
    
    @FXML
    private void chequeOnKeyPressed(KeyEvent event) {
        
           if (event.getCode().equals(KeyCode.ENTER))
            {
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
            try {

          cheque = new BigDecimal(montantCheque.getText());

          creditVercement=  new BigDecimal(vercementCredit.getText());

          BigDecimal valeur = creditVercement.subtract(cheque);

          vercementCredit.setText(valeur+"");
          totalCredit.setText(valeur+"");
          
          montantCheque.setDisable(true);
          
                  } catch (NumberFormatException e) {
                                        nav.showAlert(Alert.AlertType.ERROR, "Erreur", null,Constantes.MESSAGE_SAISIES_CHAMPS);
				}
            }else if (result.get() == ButtonType.CANCEL) {
                
                montantCheque.setText( BigDecimal.ZERO+"");
            }
        
    }
        
    }

    @FXML
    private void especeOnKeyPressed(KeyEvent event) {
        
               if (event.getCode().equals(KeyCode.ENTER))
            {
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
            try {

          espece = new BigDecimal(montantEspece.getText());

          creditVercement=  new BigDecimal(vercementCredit.getText());

         BigDecimal valeur = creditVercement.subtract(espece);

          vercementCredit.setText(valeur+"");
          totalCredit.setText(valeur+"");
          
          montantEspece.setDisable(true);
          
                  } catch (NumberFormatException e) {
                                        nav.showAlert(Alert.AlertType.ERROR, "Erreur", null,Constantes.MESSAGE_SAISIES_CHAMPS);
				}
            }else if (result.get() == ButtonType.CANCEL) {
                
                montantEspece.setText( BigDecimal.ZERO+"");
            }
        
    }
        
    }

    @FXML
    private void banqueOnKeyPressed(KeyEvent event) {
        
         if (event.getCode().equals(KeyCode.ENTER))
            {
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
            try {

          recuBanque = new BigDecimal(montantBanque.getText());

          creditVercement=  new BigDecimal(vercementCredit.getText());

         BigDecimal valeur = creditVercement.subtract(recuBanque);

          vercementCredit.setText(valeur+"");
          totalCredit.setText(valeur+"");
          
          montantBanque.setDisable(true);
          
                  } catch (NumberFormatException e) {
                                        nav.showAlert(Alert.AlertType.ERROR, "Erreur", null,Constantes.MESSAGE_SAISIES_CHAMPS);
				}
            }else if (result.get() == ButtonType.CANCEL) {
                
                montantBanque.setText( BigDecimal.ZERO+"");
            }
        
    }
    }

    @FXML
    private void banqueRefrech(MouseEvent event) {
        
              try {
         BigDecimal newBanque=  new BigDecimal(montantBanque.getText());

         creditVercement=  new BigDecimal(vercementCredit.getText());
         
          BigDecimal newValeur = creditVercement.add(newBanque);

          vercementCredit.setText(newValeur+"");
          totalCredit.setText(newValeur+"");
          montantBanque.setText(BigDecimal.ZERO+"");
           
          montantBanque.setDisable(false);
           
              } catch (NumberFormatException e) {
                        nav.showAlert(Alert.AlertType.ERROR, "Erreur", null,Constantes.MESSAGE_SAISIES_CHAMPS); 
				}
        
    }

    @FXML
    private void chequeRefrech(MouseEvent event) {
        
                 try {
         BigDecimal newCheque=  new BigDecimal(montantCheque.getText());

         creditVercement=  new BigDecimal(vercementCredit.getText());
         
          BigDecimal newValeur = creditVercement.add(newCheque);

          vercementCredit.setText(newValeur+"");
          totalCredit.setText(newValeur+"");
           montantCheque.setText( BigDecimal.ZERO+"");
           montantCheque.setDisable(false);
           
              } catch (NumberFormatException e) {
                                        nav.showAlert(Alert.AlertType.ERROR, "Erreur", null,Constantes.MESSAGE_SAISIES_CHAMPS); 
				}
        
    }

    @FXML
    private void especeRefrech(MouseEvent event) {
        
                 try {
         BigDecimal newEspece=  new BigDecimal(montantEspece.getText());

         creditVercement=  new BigDecimal(vercementCredit.getText());
         
          BigDecimal newValeur = creditVercement.add(newEspece);

          vercementCredit.setText(newValeur+"");
          totalCredit.setText(newValeur+"");
           montantEspece.setText( BigDecimal.ZERO+"");
            montantEspece.setDisable(false);
              } catch (NumberFormatException e) {
                                        nav.showAlert(Alert.AlertType.ERROR, "Erreur", null,Constantes.MESSAGE_SAISIES_CHAMPS); 
				}
        
    }

    @FXML
    private void ajouterOnAction(ActionEvent event) throws ParseException {
        
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
          if(
          libelleField.getText().equals("")||
          codeSortieField.getText().equals("")|| 
          montantCredit.getText().equals("")||        
          prixTotalField.getText().equals("")||
          caissierCombo.getSelectionModel().getSelectedIndex()== -1 ||
          dateReception.getValue()==null)
          {
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
         return;
     }
        else {
              
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Versement Caisse <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

              VersementClient versementClient = new VersementClient();
              
              LocalDate localDate=dateReception.getValue();
              Date dateVersement=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
       
              String CodeVersement= libelleField.getText();
              SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
              String dateFormat2=dateFormat.format(transfertStock.getDateTransf());
              
           Caissier caissier  = mapCaissier.get(caissierCombo.getSelectionModel().getSelectedItem());

        versementClient.setDateVersement(dateVersement);
        versementClient.setCodeVersement(CodeVersement+"/"+transfertStock.getClient().getNom()+"/"+dateFormat2);
        
        if(new BigDecimal(montantEspece.getText()).compareTo(BigDecimal.ZERO)>0 && new BigDecimal(montantCheque.getText()).compareTo(BigDecimal.ZERO)==0 && new BigDecimal(montantBanque.getText()).compareTo(BigDecimal.ZERO)==0){
        
            versementClient.setEtat(Constantes.ETAT_STATUT_VALIDER);
            
        }else{
            
            versementClient.setEtat(Constantes.ETAT_STATUT_ATTENTE);
            
        }
        
        versementClient.setMontantRemise(new BigDecimal(remise.getText()));
        versementClient.setMontantCheque(new BigDecimal(montantCheque.getText()));
        versementClient.setMontantEspece(new BigDecimal(montantEspece.getText()));
        versementClient.setMontantCredit(new BigDecimal(montantCredit.getText()));
        versementClient.setMontantBanque(new BigDecimal(montantBanque.getText()));
        versementClient.setVersementCredit(new BigDecimal(vercementCredit.getText()));
        versementClient.setTotalCredit(new BigDecimal(totalCredit.getText()));
        versementClient.setTransfertStock(transfertStock);
        versementClient.setCaissier(caissier);
        

        versementClientDAO.add(versementClient);
        
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Tournee <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

  
        transfertStock.setEtat(Constantes.ETAT_STATUT_PAYE);
        transfertStockDAO.edit(transfertStock);   
        
          
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Client <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<




if (new BigDecimal(montantEspece.getText()).compareTo(BigDecimal.ZERO)!=0){

              DetailCompteClient detailCompteClient = new DetailCompteClient();
 
             detailCompteClient.setMontantDebit(new BigDecimal(montantEspece.getText()));
             detailCompteClient.setMontantCredit(BigDecimal.ZERO);
             detailCompteClient.setDateOperation(transfertStock.getDateTransf());
             detailCompteClient.setEtat(Constantes.ETAT_VERSEMENT_CLIENT);
             detailCompteClient.setDateCreation(new Date());
             detailCompteClient.setUtilisateurCreation(nav.getUtilisateur());
             detailCompteClient.setCompteClient(transfertStock.getClient().getCompteClient());
             detailCompteClient.setDesignation(Constantes.VERSEMENT_CLIENT_ESPECE_N+libelleField.getText());
 
             detailCompteClientDAO.add(detailCompteClient);
    
}

if (new BigDecimal(montantCheque.getText()).compareTo(BigDecimal.ZERO)!=0){

DetailCompteClient detailCompteClient = new DetailCompteClient();
 
             detailCompteClient.setMontantDebit(new BigDecimal(montantCheque.getText()));
             detailCompteClient.setMontantCredit(BigDecimal.ZERO);
             detailCompteClient.setDateOperation(transfertStock.getDateTransf());
             detailCompteClient.setEtat(Constantes.ETAT_VERSEMENT_CLIENT);
             detailCompteClient.setDateCreation(new Date());
             detailCompteClient.setUtilisateurCreation(nav.getUtilisateur());
             detailCompteClient.setCompteClient(transfertStock.getClient().getCompteClient());
             detailCompteClient.setDesignation(Constantes.VERSEMENT_CLIENT_CHEQUE_N+libelleField.getText());
 
             detailCompteClientDAO.add(detailCompteClient);
    
}

if (new BigDecimal(montantBanque.getText()).compareTo(BigDecimal.ZERO)!=0){

    
    DetailCompteClient detailCompteClient = new DetailCompteClient();
 
             detailCompteClient.setMontantDebit(new BigDecimal(montantBanque.getText()));
             detailCompteClient.setMontantCredit(BigDecimal.ZERO);
             detailCompteClient.setDateOperation(transfertStock.getDateTransf());
             detailCompteClient.setEtat(Constantes.ETAT_VERSEMENT_CLIENT);
             detailCompteClient.setDateCreation(new Date());
             detailCompteClient.setUtilisateurCreation(nav.getUtilisateur());
             detailCompteClient.setCompteClient(transfertStock.getClient().getCompteClient());
             detailCompteClient.setDesignation(Constantes.VERSEMENT_CLIENT_BANCAIRE_N+libelleField.getText());
 
             detailCompteClientDAO.add(detailCompteClient);

}
             
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Caissier <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

              if (new BigDecimal(montantEspece.getText()).compareTo(BigDecimal.ZERO)!=0){

                         DetailCompte detailCompteCaissier = new DetailCompte();
 
             detailCompteCaissier.setMontantDebit(new BigDecimal(montantEspece.getText()));
             detailCompteCaissier.setMontantCredit(BigDecimal.ZERO);
             detailCompteCaissier.setDateOperation(transfertStock.getDateTransf());
             detailCompteCaissier.setEtat(Constantes.ETAT_VERSEMENT_CLIENT);
             detailCompteCaissier.setDateCreation(new Date());
             detailCompteCaissier.setUtilisateurCreation(nav.getUtilisateur());
             detailCompteCaissier.setCompte(caissier.getCompte());
             detailCompteCaissier.setDesignation(Constantes.VERSEMENT_CLIENT_ESPECE_N+libelleField.getText());
 
            detailCompteDAO.add(detailCompteCaissier);
    
}
              if (new BigDecimal(montantCheque.getText()).compareTo(BigDecimal.ZERO)!=0){

             DetailCompte detailCompteCaissier = new DetailCompte();
 
             detailCompteCaissier.setMontantDebit(BigDecimal.ZERO);
             detailCompteCaissier.setMontantCredit(new BigDecimal(montantCheque.getText()));
             detailCompteCaissier.setDateOperation(transfertStock.getDateTransf());
             detailCompteCaissier.setEtat(Constantes.ETAT_VERSEMENT_CLIENT);
             detailCompteCaissier.setDateCreation(new Date());
             detailCompteCaissier.setUtilisateurCreation(nav.getUtilisateur());
             detailCompteCaissier.setCompte(caissier.getCompte());
             detailCompteCaissier.setDesignation(Constantes.VERSEMENT_CLIENT_CHEQUE_N+libelleField.getText());
 
            detailCompteDAO.add(detailCompteCaissier);
    
}
              if (new BigDecimal(montantBanque.getText()).compareTo(BigDecimal.ZERO)!=0){

    
                DetailCompte detailCompteCaissier = new DetailCompte();
 
             detailCompteCaissier.setMontantDebit(BigDecimal.ZERO);
             detailCompteCaissier.setMontantCredit(new BigDecimal(montantBanque.getText()));
             detailCompteCaissier.setDateOperation(transfertStock.getDateTransf());
             detailCompteCaissier.setEtat(Constantes.ETAT_VERSEMENT_CLIENT);
             detailCompteCaissier.setDateCreation(new Date());
             detailCompteCaissier.setUtilisateurCreation(nav.getUtilisateur());
             detailCompteCaissier.setCompte(caissier.getCompte());
             detailCompteCaissier.setDesignation(Constantes.VERSEMENT_CLIENT_BANCAIRE_N+libelleField.getText());
 
            detailCompteDAO.add(detailCompteCaissier);

}

                  

           Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.VERSEMENNT_C);
           sequenceur.setValeur(sequenceur.getValeur()+1);
           sequenceurDAO.edit(sequenceur);
           Incrementation();  
           
           loadDetail();
           setColumnPropertiesTournee();
           
           rafraichirOnAction(event);
           
   
          }
            }
        
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
        
        dateReception.setValue(null);
        codeSortieField.clear();
        prixTotalField.clear();
        montantCredit.clear();
        montantEspece.setText("");
        montantCheque.setText("");
        montantBanque.setText("");
        vercementCredit.setText("");
        totalCredit.setText("");
        caissierCombo.getSelectionModel().select(-1);
        remise.setText("");

        caissierCombo.getSelectionModel().select(-1);
        
        montantEspece.setDisable(false);
        montantBanque.setDisable(false);
        montantCheque.setDisable(false);
        remise.setDisable(false);
        
        listeDetailTransfertStock.clear();    
        
        loadDetail();
        setColumnPropertiesTournee();
        
    }

    @FXML
    private void afficherArt(MouseEvent event) {
        
        if (transfertTable.getSelectionModel().getSelectedIndex()!=-1){
          
            setColumnProperties();
    listeDetailTransfertStock.clear();     
      transfertStock = transfertTable.getSelectionModel().getSelectedItem();

      listeDetailTransfertStock.addAll(detailTransfertStockDAO.findByTransfertStock(transfertStock.getId()));

        sortiesTable.setItems(listeDetailTransfertStock);
            
//################################# calcule montant Credit ####################################################################################################################################################################

BigDecimal MontantCreditClient = BigDecimal.ZERO;

        transfertStock = transfertTable.getSelectionModel().getSelectedItem();
            
List <Object[]> listedetailCompteClient =detailCompteClientDAO.findByMontantCredit(transfertStock.getClient().getCompteClient().getId());
            
   
                                if (listedetailCompteClient.size()!= 0){
            
                                    for(int j=0; j<listedetailCompteClient.size(); j++) {

                                        Object[] objectTMP=listedetailCompteClient.get(j);
                   
                                        BigDecimal montantCredit =(BigDecimal)objectTMP[0];
                                        BigDecimal montantDebit = (BigDecimal)objectTMP[1]; 
                                        
                                if(montantCredit == null && montantDebit == null){
                    
                                    MontantCreditClient = BigDecimal.ZERO;
                        
                                }else{

                                    MontantCreditClient = montantCredit.subtract(montantDebit); 
                                }
                                    }
            
//############################################################################################################################################################################################################################|            


        codeSortieField.setText(transfertStock.getCodeTransfert());
        prixTotalField.setText(transfertStock.getMontantTotal()+"");
        montantCredit.setText(MontantCreditClient.subtract(transfertStock.getMontantTotal())+"");
        vercementCredit.setText(transfertStock.getMontantTotal().add(new BigDecimal(montantCredit.getText()))+"");
        montantEspece.setText(new BigDecimal(0).toString());
        montantCheque.setText(new BigDecimal(0).toString());
        montantBanque.setText(new BigDecimal(0).toString());
        totalCredit.setText(new BigDecimal(vercementCredit.getText())+"");
        remise.setText(new BigDecimal(0).toString());
         
        montantEspece.setDisable(false);
        montantBanque.setDisable(false);
        montantCheque.setDisable(false);
        remise.setDisable(false);
            
                                }
}
        
    }

    @FXML
    private void remiseOnKeyPressed(KeyEvent event) {
         
         if (event.getCode().equals(KeyCode.ENTER))
            {
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
            try {

          remiseMt = new BigDecimal(remise.getText());

          creditTotal=  new BigDecimal(totalCredit.getText());

         BigDecimal valeur = creditTotal.add(remiseMt);

          totalCredit.setText(valeur+"");
          
          remise.setDisable(true);
          
                  } catch (NumberFormatException e) {
                                        nav.showAlert(Alert.AlertType.ERROR, "Erreur", null,Constantes.MESSAGE_SAISIES_CHAMPS);
				}
            }else if (result.get() == ButtonType.CANCEL) {
                
                remise.setText( BigDecimal.ZERO+"");
            }
        
    }
        
    }

    @FXML
    private void remiseRefrech(MouseEvent event) {
        
             try {
         BigDecimal newRemise=  new BigDecimal(remise.getText());

         creditTotal=  new BigDecimal(totalCredit.getText());
         
          BigDecimal newValeur = creditTotal.subtract(newRemise);

          totalCredit.setText(newValeur+"");
           remise.setText( BigDecimal.ZERO+"");
           remise.setDisable(false);
              } catch (NumberFormatException e) {
                                        nav.showAlert(Alert.AlertType.ERROR, "Erreur", null,Constantes.MESSAGE_SAISIES_CHAMPS); 
				}
        
        
    }

    @FXML
    private void afficherDetailArt(MouseEvent event) { 
    }

    
}
