/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Stock;

import Utils.Constantes;
import dao.Entity.Banque;
import dao.Entity.ClientPF;
import dao.Entity.CompteVersement;
import dao.Entity.Depot;
import dao.Entity.DetailCompte;
import dao.Entity.DetailCompteClient;
import dao.Entity.Vendeur;
import dao.Entity.Versement;
import dao.Entity.VersementBancaire;
import dao.Entity.VersementBancaireClient;
import dao.Entity.VersementClient;
import dao.Manager.BanqueDAO;
import dao.Manager.CaissierDAO;
import dao.Manager.ClientPFDAO;
import dao.Manager.CompteVersementDAO;
import dao.Manager.DepotDAO;
import dao.Manager.DetailCompteClientDAO;
import dao.Manager.DetailCompteDAO;
import dao.Manager.VendeurDAO;
import dao.Manager.VersementBancaireClientDAO;
import dao.Manager.VersementBancaireDAO;
import dao.Manager.VersementClientDAO;
import dao.Manager.VersementDAO;
import dao.ManagerImpl.BanqueDAOImpl;
import dao.ManagerImpl.CaissierDAOImpl;
import dao.ManagerImpl.ClientPFDAOImpl;
import dao.ManagerImpl.CompteVersementDAOImpl;
import dao.ManagerImpl.DepotDAOImpl;
import dao.ManagerImpl.DetailCompteClientDAOImpl;
import dao.ManagerImpl.DetailCompteDAOImpl;
import dao.ManagerImpl.VendeurDAOImpl;
import dao.ManagerImpl.VersementBancaireClientDAOImpl;
import dao.ManagerImpl.VersementBancaireDAOImpl;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class VersementBancaireClientController implements Initializable {

    @FXML
    private ComboBox<String> versementCombo;
    @FXML
    private TextField montantBancaireField;
    @FXML
    private ComboBox<String> depotCombo;
    @FXML
    private ComboBox<String> numCompteCombo;
    @FXML
    private ComboBox<String> clientCombo;
     @FXML
    private TableView<VersementBancaireClient> versementBanqueTable;
    @FXML
    private TableColumn<VersementBancaireClient, String> codeColumn;
    @FXML
    private TableColumn<VersementBancaireClient, String> nomColumn;
    @FXML
    private TableColumn<VersementBancaireClient, String> numVersementColumn;
    @FXML
    private TableColumn<VersementBancaireClient, Date> dateVersementColumn;
    @FXML
    private TableColumn<VersementBancaireClient, BigDecimal> montantColumn;
    @FXML
    private TableColumn<VersementBancaireClient, String> numCompteColumn;
    @FXML
    private TableColumn<VersementBancaireClient, String> BanqueColumn;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRafraichir;
    @FXML
    private TextField codeVendeurField;
    @FXML
    private TextField numVersementField;
    @FXML
    private DatePicker dateVersement;
    @FXML
    private TextField nomVendeurField;
    @FXML
    private TextField MontantField;
    @FXML
    private ComboBox<String> banqueCombo;
    @FXML
    private ImageView ajoutBtn;
    @FXML
    private ImageView videBtn;
    @FXML
    private ImageView supprimerBtn;
    @FXML
    private TextField montantTotalField;

    
    
    private Map<String, ClientPF> mapClientPF = new HashMap<>();
    ClientPFDAO clientPFDAO = new ClientPFDAOImpl();
    
    private Map<String, Depot> mapDepot = new HashMap<>();
    DepotDAO depotDAO = new DepotDAOImpl();

    private Map<String, VersementClient> mapVersementClient = new HashMap<>();
    VersementClientDAO versementClientDAO = new VersementClientDAOImpl();
    
    private Map<String, Banque> mapBanque = new HashMap<>();
    BanqueDAO banqueDAO = new BanqueDAOImpl();
    
    navigation nav = new navigation();

    VersementBancaireClient versementBancaireClient = new VersementBancaireClient();
    
    VersementBancaireClientDAO versementBancaireClientDAO = new VersementBancaireClientDAOImpl();
    
      private Map<String, CompteVersement> mapCompteVersement = new HashMap<>();
    CompteVersementDAO compteVersementDAO = new CompteVersementDAOImpl();
    
    DetailCompteDAO detailCompteDAO = new DetailCompteDAOImpl();
    DetailCompteClientDAO detailCompteClientDAO = new DetailCompteClientDAOImpl();
    
    
    CaissierDAO caissierDAO = new CaissierDAOImpl();
    
    private final ObservableList<VersementBancaireClient> listeVersementBancaireClient = FXCollections.observableArrayList(); 
    
    
    /**
     * Initializes the controller class.
     */
    
    void calculMontantTotal(){
    
        BigDecimal valeur = BigDecimal.ZERO;
        
        for (int i = 0; i < listeVersementBancaireClient.size(); i++) {
           VersementBancaireClient versementBancaireClient = listeVersementBancaireClient.get(i);
           
           valeur = valeur.add(versementBancaireClient.getMontant());
            
            
        }
    
        montantTotalField.setText(valeur+"");
        
    }
    
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
        
            
                List<Banque> listBanque = banqueDAO.findAll();
            listBanque.stream().map((banque) -> {
                banqueCombo.getItems().addAll(banque.getLibelle());
                return banque;
            }).forEachOrdered((banque) -> {
                mapBanque.put(banque.getLibelle(), banque);
            });
    
            
                      List<CompteVersement> listCompteVersement = compteVersementDAO.findAll();
            listCompteVersement.stream().map((compteVersement) -> {
                numCompteCombo.getItems().addAll(compteVersement.getLibelle());
                return compteVersement;
            }).forEachOrdered((compteVersement) -> {
                mapCompteVersement.put(compteVersement.getLibelle(), compteVersement);
            });
        
    }    
    
       void setColumnProperties() {

        codeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VersementBancaireClient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<VersementBancaireClient, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getCodeProprietaire());
            }
        });
        
        nomColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VersementBancaireClient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<VersementBancaireClient, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNomProprietaire());
            }
        });

        numVersementColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VersementBancaireClient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<VersementBancaireClient, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNumVersement());
            }
        });

        numCompteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VersementBancaireClient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<VersementBancaireClient, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getCompteVersement().getLibelle());
            }
        });
        
        BanqueColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VersementBancaireClient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<VersementBancaireClient, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getBanque().getLibelle());
            }
        });
        
          dateVersementColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VersementBancaireClient, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<VersementBancaireClient, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDateVersement());
            }
        });
                  
                  
                  montantColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VersementBancaireClient, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<VersementBancaireClient, BigDecimal> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getMontant());
            }
        });
       
    }

              void loadDetail() {

        listeVersementBancaireClient.clear();
        listeVersementBancaireClient.addAll(versementBancaireClientDAO.findAll());
        versementBanqueTable.setItems(listeVersementBancaireClient);
    }
       
    @FXML
    private void versementComboOnAction(ActionEvent event) {
        
           montantBancaireField.clear();

            VersementClient versementClient  = mapVersementClient.get(versementCombo.getSelectionModel().getSelectedItem());
            
            
            if(versementClient!=null){
                
            montantBancaireField.setText(versementClient.getMontantBanque()+"");
            codeVendeurField.setText(versementClient.getTransfertStock().getClient().getCode());
            nomVendeurField.setText(versementClient.getTransfertStock().getClient().getNom());
            }
        
    }

    @FXML
    private void depotComboOnAction(ActionEvent event) {
        
                      montantBancaireField.clear();
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

    @FXML
    private void clientComboOnAction(ActionEvent event) {
        
             versementCombo.getItems().clear();
          
            ClientPF clientPF  = mapClientPF.get(clientCombo.getSelectionModel().getSelectedItem());
            
             if(clientPF!=null){
                 
            List<VersementClient> listVersementClient = versementClientDAO.findVersemenBancairetClientByClient(clientPF.getId(),Constantes.ETAT_STATUT_ATTENTE);
            listVersementClient.stream().map((versementClient) -> {
                versementCombo.getItems().addAll(versementClient.getCodeVersement());
                return versementClient;
            }).forEachOrdered((versementClient) -> {
                mapVersementClient.put(versementClient.getCodeVersement(), versementClient);
            });
             }
        
        
    }

    @FXML
    private void afficherArt(MouseEvent event) {
    }

    @FXML
    private void ajouterOnAction(ActionEvent event) {
        
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
         if(clientCombo.getSelectionModel().getSelectedIndex()== -1 || 
          versementCombo.getSelectionModel().getSelectedIndex()== -1 
  
                ){
             
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
         return;
        }else{
                if (new BigDecimal(montantBancaireField.getText()).compareTo(new BigDecimal(montantTotalField.getText()))==0 ){

             
               VersementClient versementClient = mapVersementClient.get(versementCombo.getSelectionModel().getSelectedItem());

                versementClient.setEtatVersementBancaire(true);
        
            if(versementClient.getMontantCheque().compareTo(BigDecimal.ZERO)>0 && versementClient.isEtatVersementCheque()== true){
            versementClient.setEtat(Constantes.ETAT_STATUT_VALIDER);
            
            }else if (versementClient.getMontantCheque().compareTo(BigDecimal.ZERO)==0){ 
            versementClient.setEtat(Constantes.ETAT_STATUT_VALIDER);
            }
            
                    
      versementClient.setVersementBancairesClient(listeVersementBancaireClient);
      

            versementClientDAO.add(versementClient);

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Caissier <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

//Montant Credit Banque

BigDecimal montantCredit =new BigDecimal(montantBancaireField.getText()).subtract(new BigDecimal(montantTotalField.getText()));


if (montantCredit.compareTo(BigDecimal.ZERO)>0){

    DetailCompteClient detailCompteClient = new DetailCompteClient();
 
             detailCompteClient.setMontantDebit(BigDecimal.ZERO);
             detailCompteClient.setMontantCredit(montantCredit);
             detailCompteClient.setDateOperation(versementClient.getTransfertStock().getDateTransf());
             detailCompteClient.setEtat(Constantes.ETAT_VERSEMENT_BANCAIRE);
             detailCompteClient.setDateCreation(new Date());
             detailCompteClient.setUtilisateurCreation(nav.getUtilisateur());
             detailCompteClient.setCompteClient(versementClient.getTransfertStock().getClient().getCompteClient());
             detailCompteClient.setDesignation(Constantes.CREDIT_BANCAIRE_CLIENT+versementClient.getCodeVersement());
 
             detailCompteClientDAO.add(detailCompteClient);              

}

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Caissier <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


//Montant Bancaire
             
                for (int i = 0; i < listeVersementBancaireClient.size(); i++) {
                    
                   VersementBancaireClient versementBancaireClient = listeVersementBancaireClient.get(i);
                    
                      DetailCompte detailCompteBanque = new DetailCompte();
 
             detailCompteBanque.setMontantDebit(versementBancaireClient.getMontant());
             detailCompteBanque.setMontantCredit(BigDecimal.ZERO);
             detailCompteBanque.setDateOperation(versementBancaireClient.getVersementClient().getTransfertStock().getDateTransf());
             detailCompteBanque.setEtat(Constantes.ETAT_VERSEMENT_BANCAIRE);
             detailCompteBanque.setDateCreation(new Date());
             detailCompteBanque.setUtilisateurCreation(nav.getUtilisateur());
             detailCompteBanque.setCompte(versementBancaireClient.getVersementClient().getCaissier().getCompte());
             detailCompteBanque.setDesignation(Constantes.REGLEMENT_BANCAIRE_CLIENT+versementBancaireClient.getVersementClient().getCodeVersement());
 
             detailCompteDAO.add(detailCompteBanque);
                    
                }         
            
            versementClient = new VersementClient();
            
            setColumnProperties();
            loadDetail();
              rafraichirOnAction(event);

        }else{
                 nav.showAlert(Alert.AlertType.ERROR, "Erreur", null,Constantes.MESSAGE_ALERT_VERIFIER_MONTANT);
                 return;
                }
            }
            }
        
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
        
         depotCombo.getSelectionModel().select(-1);
        dateVersement.setValue(null);
        versementCombo.getSelectionModel().select(-1);
        montantBancaireField.clear();
        codeVendeurField.clear();
        nomVendeurField.clear();
        numVersementField.clear();
        MontantField.clear();
        clientCombo.getSelectionModel().select(-1);
        numCompteCombo.getSelectionModel().select(-1);
        banqueCombo.getSelectionModel().select(-1);
        montantTotalField.clear();
        listeVersementBancaireClient.clear();
        versementBanqueTable.setItems(listeVersementBancaireClient);
        
    }

    @FXML
    private void codeArticleKeyPressed(KeyEvent event) {
    }

    @FXML
    private void ajouter(MouseEvent event) throws ParseException {
        
        
                   if(depotCombo.getSelectionModel().getSelectedIndex()== -1 || 
          dateVersement.getValue().equals(null) || 
          versementCombo.getSelectionModel().getSelectedIndex()== -1 || 
          numCompteCombo.getSelectionModel().getSelectedIndex()== -1 || 
           montantBancaireField.getText().isEmpty() ||
                codeVendeurField.getText().isEmpty() ||
              nomVendeurField.getText().isEmpty() ||     
                numVersementField.getText().isEmpty() ||
                    MontantField.getText().isEmpty()     
                ){

         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
        }else{
        
                       LocalDate localDate=dateVersement.getValue();
            Date dateVers =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
            
             CompteVersement CompteVersement = mapCompteVersement.get(numCompteCombo.getSelectionModel().getSelectedItem());
            
            VersementClient versementClient = mapVersementClient.get(versementCombo.getSelectionModel().getSelectedItem());
            Banque banque = mapBanque.get(banqueCombo.getSelectionModel().getSelectedItem());
             
//############################################################################## Verification Num Versement 'Liste' ########################################################################################################################################################################################################################################                            
      
                       Boolean exist = false;
            
                       for (int i = 0; i < listeVersementBancaireClient.size(); i++) {
                           
                           VersementBancaireClient versementBancaireClient = listeVersementBancaireClient.get(i);
                           
                           if (versementBancaireClient.getNumVersement().equals(numVersementField.getText())){
                            
                           exist = true;
                            break;
                           } 
                       }
//############################################################################## Verification Num Versement 'DataBase' ########################################################################################################################################################################################################################################                  
 

                       List<VersementBancaireClient> listVersementBancaireClient = versementBancaireClientDAO.findAll();

                      for (int i = 0; i < listVersementBancaireClient.size(); i++) {
                         
                      VersementBancaireClient versementBancaireClient = listVersementBancaireClient.get(i);
                      
                      if(versementBancaireClient.getNumVersement().equals(numVersementField.getText())){
                      
                      exist = true;
                            break;
                      
                      } 
                     }
                                              
                       
                       if (exist == true){
                       
                           nav.showAlert(Alert.AlertType.ERROR, "Erreur", null,Constantes.MESSAGE_ALERT_EXISTE);
                            refresh();
                           return;
                           
                       }else{

            VersementBancaireClient versementBancaireClient = new VersementBancaireClient();
            
            versementBancaireClient.setCodeProprietaire(codeVendeurField.getText());
            versementBancaireClient.setNomProprietaire(nomVendeurField.getText());
            versementBancaireClient.setNumVersement(numVersementField.getText());
            versementBancaireClient.setDateVersement(dateVers);
            versementBancaireClient.setVersementClient(versementClient);
            versementBancaireClient.setUtilisateurCreation(nav.getUtilisateur());
            versementBancaireClient.setDateCreation(new Date());
            versementBancaireClient.setBanque(banque);
            versementBancaireClient.setCompteVersement(CompteVersement);
            versementBancaireClient.setMontant(new BigDecimal(MontantField.getText()));
            
            listeVersementBancaireClient.add(versementBancaireClient);
           
              setColumnProperties();
            versementBanqueTable.setItems(listeVersementBancaireClient);
            calculMontantTotal();
               refresh();
                       }
        }
        
        
        
    }

    @FXML
    private void vider(MouseEvent event) {
        refresh();
        
    }
    
    void refresh(){

        numVersementField.clear();
        numCompteCombo.getSelectionModel().select(-1);
        banqueCombo.getSelectionModel().select(-1);
        dateVersement.setValue(null);
        MontantField.clear();
    }
    @FXML
    private void supprimer(MouseEvent event) {
        
                if(versementBanqueTable.getSelectionModel().getSelectedIndex()!=-1)
        {
            if(listeVersementBancaireClient.size()!=0)
            {
                
                listeVersementBancaireClient.remove(versementBanqueTable.getSelectionModel().getSelectedIndex());

            }
                int i=0;
        BigDecimal montanttotal=BigDecimal.ZERO;
        while(i<listeVersementBancaireClient.size())
        {
            VersementBancaireClient versementBancaireClient =listeVersementBancaireClient.get(i);
           montanttotal= montanttotal.add(versementBancaireClient.getMontant());
            i++;
        }
        montantTotalField.setText(montanttotal+"");

        }
        
    }
    
}
