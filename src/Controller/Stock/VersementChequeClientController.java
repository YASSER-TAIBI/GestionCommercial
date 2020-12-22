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
import dao.Entity.Versement;
import dao.Entity.VersementCheque;
import dao.Entity.VersementChequeClient;
import dao.Entity.VersementClient;
import dao.Manager.BanqueDAO;
import dao.Manager.CaissierDAO;
import dao.Manager.ClientPFDAO;
import dao.Manager.CompteVersementDAO;
import dao.Manager.DepotDAO;
import dao.Manager.DetailCompteClientDAO;
import dao.Manager.DetailCompteDAO;
import dao.Manager.VendeurDAO;
import dao.Manager.VersementChequeClientDAO;
import dao.Manager.VersementChequeDAO;
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
import dao.ManagerImpl.VersementChequeClientDAOImpl;
import dao.ManagerImpl.VersementChequeDAOImpl;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class VersementChequeClientController implements Initializable {

    @FXML
    private ComboBox<String> versementCombo;
    @FXML
    private ComboBox<String> clientCombo;
    @FXML
    private TextField montantChequeField;
    @FXML
    private ComboBox<String> depotCombo;
    @FXML
    private TableView<VersementChequeClient> versementChequeClientTable;
    @FXML
    private TableColumn<VersementChequeClient, String> numChequeColumn;
    @FXML
    private TableColumn<VersementChequeClient, String> nomColumn;
    @FXML
    private TableColumn<VersementChequeClient, Date> dateEcheanceColumn;
    @FXML
    private TableColumn<VersementChequeClient, Date> dateRecuColumn;
    @FXML
    private TableColumn<VersementChequeClient, String> typeChequeColumn;
    @FXML
    private TableColumn<VersementChequeClient, String> banqueColumn;
    @FXML
    private TableColumn<VersementChequeClient, BigDecimal> montantColumn;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRafraichir;
    @FXML
    private TextField numChequeField;
    @FXML
    private DatePicker dateEcheance;
    @FXML
    private DatePicker dateRecu;
    @FXML
    private ComboBox<String> typeChequeCombo;
    @FXML
    private ComboBox<String> banqueCombo;
    @FXML
    private TextField nomProprietaireField;
    @FXML
    private TextField MontantField;
    @FXML
    private ImageView ajoutBtn;
    @FXML
    private ImageView videBtn;
    @FXML
    private Label messageChequeBloque;
    @FXML
    private ImageView supprimerBtn;
    @FXML
    private TextField montantTotalField;
    @FXML
    private TextField nomClientField;

    /**
     * Initializes the controller class.
     */
     private Map<String, Depot> mapDepot = new HashMap<>();
    DepotDAO depotDAO = new DepotDAOImpl();

       private Map<String, CompteVersement> mapCompteVersement = new HashMap<>();
    CompteVersementDAO compteVersementDAO = new CompteVersementDAOImpl();
    
    private Map<String, VersementClient> mapVersementClient = new HashMap<>();
    VersementClientDAO versementClientDAO = new VersementClientDAOImpl();
    
    navigation nav = new navigation();
    
    private Map<String, ClientPF> mapClientPF = new HashMap<>();
    ClientPFDAO clientPFDAO = new ClientPFDAOImpl();
    
    
    private Map<String, Banque> mapBanque = new HashMap<>();
    BanqueDAO banqueDAO = new BanqueDAOImpl();
    
    VersementChequeClientDAO versementChequeClientDAO = new VersementChequeClientDAOImpl();
    
    DetailCompteClientDAO detailCompteClientDAO = new DetailCompteClientDAOImpl();
    DetailCompteDAO detailCompteDAO = new DetailCompteDAOImpl();
    CaissierDAO caissierDAO = new CaissierDAOImpl();
    
    private final ObservableList<VersementChequeClient> listeVersementChequeClient = FXCollections.observableArrayList(); 
    
    ObservableList<String> typeCheque =FXCollections.observableArrayList(Constantes.TYPE_CHEQUE_NON_VERSE,Constantes.TYPE_CHEQUE_RETOUR);
    
    

    /**
     * Initializes the controller class.
     */
    
      void calculMontantTotal(){
    
        BigDecimal valeur = BigDecimal.ZERO;
        
        for (int i = 0; i < listeVersementChequeClient.size(); i++) {
           VersementChequeClient versementChequeClient = listeVersementChequeClient.get(i);
           
           valeur = valeur.add(versementChequeClient.getMontant());
            
            
        }
    
        montantTotalField.setText(valeur+"");
        
    }
    
                void loadDetail() {

        listeVersementChequeClient.clear();
        listeVersementChequeClient.addAll(versementChequeClientDAO.findAll());
        versementChequeClientTable.setItems(listeVersementChequeClient);
    }
    
     void setColumnProperties() {

      numChequeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VersementChequeClient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<VersementChequeClient, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNumCheque());
            }
        });
        
        nomColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VersementChequeClient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<VersementChequeClient, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNomProprietaire());
            }
        });
 
                  dateEcheanceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VersementChequeClient, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<VersementChequeClient, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDateEcheance());
            }
        });  
                  
             dateRecuColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VersementChequeClient, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<VersementChequeClient, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDateRecu());
            }
        });       
             
             typeChequeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VersementChequeClient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<VersementChequeClient, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getTypeCheque());
            }
        });
             
             banqueColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VersementChequeClient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<VersementChequeClient, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getBanque().getLibelle());
            }
        });
                            
                     
                  montantColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VersementChequeClient, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<VersementChequeClient, BigDecimal> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getMontant());
            }
        });
       
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         typeChequeCombo.setItems(typeCheque);
        
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
    
            messageChequeBloque.setVisible(false);
        
    }    

    @FXML
    private void versementComboOnAction(ActionEvent event) {
        
        
           montantChequeField.clear();

            VersementClient versementClient  = mapVersementClient.get(versementCombo.getSelectionModel().getSelectedItem());
            
            
            if(versementClient!=null){
                
            montantChequeField.setText(versementClient.getMontantCheque()+"");
            nomClientField.setText(versementClient.getTransfertStock().getClient().getNom());
            }
          
    }

    @FXML
    private void clientComboOnAction(ActionEvent event) {
        
                   versementCombo.getItems().clear();
          
            ClientPF clientPF  = mapClientPF.get(clientCombo.getSelectionModel().getSelectedItem());
            
             if(clientPF!=null){
                 
            List<VersementClient> listVersementClient = versementClientDAO.findVersementChequeClientByClient(clientPF.getId(),Constantes.ETAT_STATUT_ATTENTE);
            listVersementClient.stream().map((versementClient) -> {
                versementCombo.getItems().addAll(versementClient.getCodeVersement());
                return versementClient;
            }).forEachOrdered((versementClient) -> {
                mapVersementClient.put(versementClient.getCodeVersement(), versementClient);
            });
             }
        
        
    }

    @FXML
    private void depotComboOnAction(ActionEvent event) {
        
                 montantChequeField.clear();
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
    private void afficherArt(MouseEvent event) {
    }

    @FXML
    private void ajouterOnAction(ActionEvent event){
        
        
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

                
                if (new BigDecimal(montantChequeField.getText()).compareTo(new BigDecimal(montantTotalField.getText()))>=0 ){
                    
               VersementClient versementClient = mapVersementClient.get(versementCombo.getSelectionModel().getSelectedItem());
             
                  versementClient.setEtatVersementCheque(true);
        
            if(versementClient.getMontantBanque().compareTo(BigDecimal.ZERO)>0 && versementClient.isEtatVersementBancaire()== true){
            versementClient.setEtat(Constantes.ETAT_STATUT_VALIDER);
            
            }else if (versementClient.getMontantBanque().compareTo(BigDecimal.ZERO)==0){ 
            versementClient.setEtat(Constantes.ETAT_STATUT_VALIDER);
            }
    
                  versementClient.setVersementChequeClient(listeVersementChequeClient);
      

            versementClientDAO.add(versementClient);

   
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Client <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

//Montant Credit Cheque


BigDecimal montantCredit =new BigDecimal(montantChequeField.getText()).subtract(new BigDecimal(montantTotalField.getText()));


if (montantCredit.compareTo(BigDecimal.ZERO)>0){

    DetailCompteClient detailCompteClient = new DetailCompteClient();
 
             detailCompteClient.setMontantDebit(BigDecimal.ZERO);
             detailCompteClient.setMontantCredit(montantCredit);
             detailCompteClient.setDateOperation(versementClient.getTransfertStock().getDateTransf());
             detailCompteClient.setEtat(Constantes.ETAT_VERSEMENT_CHEQUE);
             detailCompteClient.setDateCreation(new Date());
             detailCompteClient.setUtilisateurCreation(nav.getUtilisateur());
             detailCompteClient.setCompteClient(versementClient.getTransfertStock().getClient().getCompteClient());
             detailCompteClient.setDesignation(Constantes.CREDIT_CHEQUE_CLIENT+versementClient.getCodeVersement());
 
             detailCompteClientDAO.add(detailCompteClient);              

}

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Caissier <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


//Montant Cheque
             
                for (int i = 0; i < listeVersementChequeClient.size(); i++) {
                    
                   VersementChequeClient versementChequeClient = listeVersementChequeClient.get(i);
                    
                      DetailCompte detailCompteCheque = new DetailCompte();
 
             detailCompteCheque.setMontantDebit(versementChequeClient.getMontant());
             detailCompteCheque.setMontantCredit(BigDecimal.ZERO);
             detailCompteCheque.setDateOperation(versementChequeClient.getVersementClient().getTransfertStock().getDateTransf());
             detailCompteCheque.setEtat(Constantes.ETAT_VERSEMENT_CHEQUE);
             detailCompteCheque.setDateCreation(new Date());
             detailCompteCheque.setUtilisateurCreation(nav.getUtilisateur());
             detailCompteCheque.setCompte(versementChequeClient.getVersementClient().getCaissier().getCompte());
             detailCompteCheque.setDesignation(Constantes.REGLEMENT_CHEQUE_CLIENT+versementChequeClient.getVersementClient().getCodeVersement());
 
             detailCompteDAO.add(detailCompteCheque);
                    
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
        
                montantTotalField.clear();
        montantChequeField.clear();
        depotCombo.getSelectionModel().select(-1);
        versementCombo.getSelectionModel().select(-1);
        nomClientField.clear();
        numChequeField.clear();
        nomProprietaireField.clear();
        dateEcheance.setValue(null);
        dateRecu.setValue(null);
        clientCombo.getSelectionModel().select(-1);
        banqueCombo.getSelectionModel().select(-1);
        typeChequeCombo.getSelectionModel().select(-1);
        MontantField.clear();
        
        messageChequeBloque.setVisible(false);
        listeVersementChequeClient.clear();
        
    }

    @FXML
    private void codeArticleKeyPressed(KeyEvent event) {
    }

    @FXML
    private void ajouter(MouseEvent event) throws ParseException {
        
           if(clientCombo.getSelectionModel().getSelectedIndex()== -1 || 
          dateEcheance.getValue()==null || 
          dateRecu.getValue()==null || 
          versementCombo.getSelectionModel().getSelectedIndex()== -1 || 
          montantChequeField.getText().isEmpty() ||
          numChequeField.getText().isEmpty() ||
          nomProprietaireField.getText().isEmpty() ||
          typeChequeCombo.getSelectionModel().getSelectedIndex()== -1 ||
          banqueCombo.getSelectionModel().getSelectedIndex()== -1 ||
          depotCombo.getSelectionModel().getSelectedIndex()== -1 ||
          nomClientField.getText().isEmpty() ||     
          MontantField.getText().isEmpty()     
                ){

         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
        }else{
        
                       LocalDate localDate=dateEcheance.getValue();
            Date dateEche =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
            
             localDate=dateRecu.getValue();
             Date dateR =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
            
           
            VersementClient versementClient = mapVersementClient.get(versementCombo.getSelectionModel().getSelectedItem());
            
            Banque banque = mapBanque.get(banqueCombo.getSelectionModel().getSelectedItem());

            String  valeur = typeChequeCombo.getSelectionModel().getSelectedItem();
            
            ClientPF clientPF = mapClientPF.get(clientCombo.getSelectionModel().getSelectedItem());
                 
//############################################################################## Verification Num Cheque 'Liste' ########################################################################################################################################################################################################################################                            
            
                      Boolean exist = false;
            
                       for (int i = 0; i < listeVersementChequeClient.size(); i++) {
                           
                           VersementChequeClient versementChequeClient = listeVersementChequeClient.get(i);
                           
                           if (versementChequeClient.getNumCheque().equals(numChequeField.getText())){
                            
                           exist = true;
                            break;
                           } 
                       }
                       
//############################################################################## Verification Num Cheque 'DataBase' ########################################################################################################################################################################################################################################                  
 

                       List<VersementChequeClient> listVersementChequeClient = versementChequeClientDAO.findAll();

                      for (int i = 0; i < listVersementChequeClient.size(); i++) {
                         
                      VersementChequeClient versementChequeClient = listVersementChequeClient.get(i);
                      
                      if(versementChequeClient.getNumCheque().equals(numChequeField.getText())){
                      
                      exist = true;
                            break;
                      
                      } 
                     }
                       
                       if (exist == true){
                       
                           nav.showAlert(Alert.AlertType.ERROR, "Erreur", null,Constantes.MESSAGE_ALERT_EXISTE);
                            refresh();
                           return;
                           
                       }else{

                 if(clientPF.isBlockClient()==false ){

            VersementChequeClient versementChequeClient = new VersementChequeClient();
            
            versementChequeClient.setNumCheque(numChequeField.getText());
            versementChequeClient.setNomProprietaire(nomProprietaireField.getText());
            versementChequeClient.setDateEcheance(dateEche);
            versementChequeClient.setDateRecu(dateR);
            versementChequeClient.setAction(false);
            versementChequeClient.setTypeCheque(valeur);
            versementChequeClient.setDateCreation(new Date());
            versementChequeClient.setUtilisateurCreation(nav.getUtilisateur());
            versementChequeClient.setMontant(new BigDecimal(MontantField.getText()));
            versementChequeClient.setVersementClient(versementClient);
            versementChequeClient.setBanque(banque);
            
            listeVersementChequeClient.add(versementChequeClient);
 
              setColumnProperties();
            versementChequeClientTable.setItems(listeVersementChequeClient);
            calculMontantTotal();
              refresh();
                     
                 }else{
                 
                 messageChequeBloque.setVisible(true);
                 numChequeField.clear();
                 nomProprietaireField.clear();
                 dateEcheance.setValue(null);
                 dateRecu.setValue(null);
                 banqueCombo.getSelectionModel().select(-1);
                 typeChequeCombo.getSelectionModel().select(-1);
                 MontantField.clear();
                 
                 }

        }
             }
        
    }

    @FXML
    private void vider(MouseEvent event) {
       refresh();
    }

    void refresh(){
        numChequeField.clear();
        nomProprietaireField.clear();
        dateEcheance.setValue(null);
        dateRecu.setValue(null);
        banqueCombo.getSelectionModel().select(-1);
        typeChequeCombo.getSelectionModel().select(-1);
        MontantField.clear();
        messageChequeBloque.setVisible(false);

    }
    
    @FXML
    private void supprimer(MouseEvent event) {
        
                         if(versementChequeClientTable.getSelectionModel().getSelectedIndex()!=-1)
        {
            if(listeVersementChequeClient.size()!=0)
            {
                
                listeVersementChequeClient.remove(versementChequeClientTable.getSelectionModel().getSelectedIndex());

            }
                int i=0;
        BigDecimal montanttotal=BigDecimal.ZERO;
        while(i<listeVersementChequeClient.size())
        {
            VersementChequeClient versementChequeClient =listeVersementChequeClient.get(i);
           montanttotal= montanttotal.add(versementChequeClient.getMontant());
            i++;
        }
        montantTotalField.setText(montanttotal+"");

        }
        
        
    }
    
}
