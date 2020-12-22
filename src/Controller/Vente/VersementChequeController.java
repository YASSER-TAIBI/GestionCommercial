/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Vente;

import Utils.Constantes;
import dao.Entity.Banque;
import dao.Entity.Caissier;
import dao.Entity.ClientPF;
import dao.Entity.CompteVersement;
import dao.Entity.DetailCompte;
import dao.Entity.Vendeur;
import dao.Entity.Versement;
import dao.Entity.VersementBancaire;
import dao.Entity.VersementCheque;
import dao.Manager.BanqueDAO;
import dao.Manager.CaissierDAO;
import dao.Manager.ClientPFDAO;
import dao.Manager.CompteVersementDAO;
import dao.Manager.DetailCompteDAO;
import dao.Manager.VendeurDAO;
import dao.Manager.VersementBancaireDAO;
import dao.Manager.VersementChequeDAO;
import dao.Manager.VersementDAO;
import dao.ManagerImpl.BanqueDAOImpl;
import dao.ManagerImpl.CaissierDAOImpl;
import dao.ManagerImpl.ClientPFDAOImpl;
import dao.ManagerImpl.CompteVersementDAOImpl;
import dao.ManagerImpl.DetailCompteDAOImpl;
import dao.ManagerImpl.VendeurDAOImpl;
import dao.ManagerImpl.VersementBancaireDAOImpl;
import dao.ManagerImpl.VersementChequeDAOImpl;
import dao.ManagerImpl.VersementDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
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
public class VersementChequeController implements Initializable {

    @FXML
    private ComboBox<String> versementCombo;
    @FXML
    private ComboBox<String> vendeurCombo;
    @FXML
    private TextField montantChequeField;
    @FXML
    private TableView<VersementCheque> versementChequeTable;
    @FXML
    private TableColumn<VersementCheque, String> numChequeColumn;
    @FXML
    private TableColumn<VersementCheque, String> nomColumn;
    @FXML
    private TableColumn<VersementCheque, Date> dateEcheanceColumn;
    @FXML
    private TableColumn<VersementCheque, Date> dateRecuColumn;
    @FXML
    private TableColumn<VersementCheque, String> typeChequeColumn;
    @FXML
    private TableColumn<VersementCheque, String> banqueColumn;
    @FXML
    private TableColumn<VersementCheque, String> clientColumn;
    @FXML
    private TableColumn<VersementCheque, BigDecimal> montantColumn;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRafraichir;
    @FXML
    private TextField numChequeField;
    @FXML
    private DatePicker dateEcheance;
    @FXML
    private TextField nomVendeurField;
    @FXML
    private DatePicker dateRecu;

    @FXML
    private ComboBox<String> typeChequeCombo;
    @FXML
    private ComboBox<String> banqueCombo;
    @FXML
    private ComboBox<String> clientCombo;
    @FXML
    private TextField nomProprietaireField;
    @FXML
    private TextField MontantField;
    @FXML
    private ImageView ajoutBtn;
    @FXML
    private ImageView videBtn;
    @FXML
    private TextField montantTotalField;
    @FXML
    private Label messageChequeBloque;
    @FXML
    private ImageView supprimerBtn;
    
    
    private Map<String, Vendeur> mapVendeur = new HashMap<>();
    VendeurDAO vendeurDAO = new VendeurDAOImpl();

    private Map<String, CompteVersement> mapCompteVersement = new HashMap<>();
    CompteVersementDAO compteVersementDAO = new CompteVersementDAOImpl();
    
    private Map<String, Versement> mapVersement = new HashMap<>();
    VersementDAO versementDAO = new VersementDAOImpl();
    
    navigation nav = new navigation();
    
    private Map<String, ClientPF> mapClientPF = new HashMap<>();
    ClientPFDAO clientPFDAO = new ClientPFDAOImpl();
    
    private Map<String, Banque> mapBanque = new HashMap<>();
    BanqueDAO banqueDAO = new BanqueDAOImpl();
    
    VersementChequeDAO versementChequeDAO = new VersementChequeDAOImpl();
    
    DetailCompteDAO detailCompteDAO = new DetailCompteDAOImpl();
    
    CaissierDAO caissierDAO = new CaissierDAOImpl();
    
    private final ObservableList<VersementCheque> listeVersementCheque = FXCollections.observableArrayList(); 
    
    ObservableList<String> typeCheque =FXCollections.observableArrayList(Constantes.TYPE_CHEQUE_NON_VERSE,Constantes.TYPE_CHEQUE_RETOUR);
  
    

    /**
     * Initializes the controller class.
     */
    
      void calculMontantTotal(){
    
        BigDecimal valeur = BigDecimal.ZERO;
        
        for (int i = 0; i < listeVersementCheque.size(); i++) {
           VersementCheque versementCheque = listeVersementCheque.get(i);
           
           valeur = valeur.add(versementCheque.getMontant());
            
            
        }
    
        montantTotalField.setText(valeur+"");
        
    }
    
                void loadDetail() {

        listeVersementCheque.clear();
        listeVersementCheque.addAll(versementChequeDAO.findAll());
        versementChequeTable.setItems(listeVersementCheque);
    }
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         typeChequeCombo.setItems(typeCheque);
        
          List<Vendeur> listVendeurTMP = new ArrayList<Vendeur>();
              List<Vendeur> listVendeur =  vendeurDAO.findByDepot(nav.getUtilisateur().getDepot().getId());
              
              boolean exist= false;
              
                for(int i=0;i<listVendeur.size();i++){
            exist= false;
                Vendeur vendeur = listVendeur.get(i);
                
                for(int j=0;j<listVendeurTMP.size();j++){
                 Vendeur vendeurTMP = listVendeur.get(j);
                 
                if (vendeur.getNom().equals(vendeurTMP.getNom()))
                {
               exist= true;
                }
            }
                if(exist==false){
                listVendeurTMP.add(vendeur);
                }
                }
                
            listVendeurTMP.stream().map((vendeur) -> {
                vendeurCombo.getItems().addAll(vendeur.getNom());
                return vendeur;
            }).forEachOrdered((vendeur) -> {
                mapVendeur.put(vendeur.getNom(), vendeur);
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
    

     void setColumnProperties() {

      numChequeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VersementCheque, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<VersementCheque, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNumCheque());
            }
        });
        
        nomColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VersementCheque, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<VersementCheque, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNomProprietaire());
            }
        });
                  
                  dateEcheanceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VersementCheque, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<VersementCheque, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDateEcheance());
            }
        });  
                  
             dateRecuColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VersementCheque, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<VersementCheque, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDateRecu());
            }
        });       
             
             typeChequeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VersementCheque, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<VersementCheque, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getTypeCheque());
            }
        });
             
             banqueColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VersementCheque, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<VersementCheque, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getBanque().getLibelle());
            }
        });
                     
             clientColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VersementCheque, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<VersementCheque, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getClient().getNom());
            }
        });        
                     
                  montantColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VersementCheque, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<VersementCheque, BigDecimal> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getMontant());
            }
        });
       
    }
    
    
    @FXML
    private void versementComboOnAction(ActionEvent event) {
        
           montantChequeField.clear();
           clientCombo.getItems().clear();

            Versement versement  = mapVersement.get(versementCombo.getSelectionModel().getSelectedItem());
            
            
            if(versement!=null){
                
            montantChequeField.setText(versement.getMontantCheque()+"");
            nomVendeurField.setText(versement.getTournee().getVendeur().getNom());

            
            List<ClientPF> listClientPF = clientPFDAO.findClientPFBySecteur(versement.getTournee().getSecteur().getId());
            listClientPF.stream().map((clientPF) -> {
                clientCombo.getItems().addAll(clientPF.getNom());
                return clientPF;
            }).forEachOrdered((clientPF) -> {
                mapClientPF.put(clientPF.getNom(), clientPF);
            });
            
             }

    }

    @FXML
    private void vendeurComboOnAction(ActionEvent event) {
        
                versementCombo.getItems().clear();
          
            Vendeur vendeur  = mapVendeur.get(vendeurCombo.getSelectionModel().getSelectedItem());
            
             if(vendeur!=null){
            List<Versement> listVersement = versementDAO.findVersementChequeByTournee(vendeur.getId(),Constantes.ETAT_STATUT_ATTENTE);
            listVersement.stream().map((versement) -> {
                versementCombo.getItems().addAll(versement.getCodeVersement());
                return versement;
            }).forEachOrdered((versement) -> {
                mapVersement.put(versement.getCodeVersement(), versement);
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
        
            if(vendeurCombo.getSelectionModel().getSelectedIndex()== -1 || 
          versementCombo.getSelectionModel().getSelectedIndex()== -1 
         
                ){
             
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
         return;
        }else{

                
                if (new BigDecimal(montantChequeField.getText()).compareTo(new BigDecimal(montantTotalField.getText()))==0 ){
                    
               Versement versement = mapVersement.get(versementCombo.getSelectionModel().getSelectedItem());
             
                  versement.setEtatVersementCheque(true);
        
            if(versement.getMontantBanque().compareTo(BigDecimal.ZERO)>0 && versement.isEtatVersementBancaire()== true){
            versement.setEtat(Constantes.ETAT_STATUT_VALIDER);
            
            }else if (versement.getMontantBanque().compareTo(BigDecimal.ZERO)==0){ 
            versement.setEtat(Constantes.ETAT_STATUT_VALIDER);
            }
    
                  versement.setVersementCheque(listeVersementCheque);
                  versement.setMontantVersementCheque(new BigDecimal(montantTotalField.getText()));

            versementDAO.edit(versement);

   
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Vendeur <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

//Montant Credit Cheque


BigDecimal montantCredit =new BigDecimal(montantChequeField.getText()).subtract(new BigDecimal(montantTotalField.getText()));


if (montantCredit.compareTo(BigDecimal.ZERO)>0){

   DetailCompte detailCompte = new DetailCompte();
 
             detailCompte.setMontantDebit(BigDecimal.ZERO);
             detailCompte.setMontantCredit(montantCredit);
             detailCompte.setDateOperation(versement.getTournee().getDateTournee());
             detailCompte.setEtat(Constantes.ETAT_VERSEMENT_CHEQUE);
             detailCompte.setDateCreation(new Date());
             detailCompte.setUtilisateurCreation(nav.getUtilisateur());
             detailCompte.setCompte(versement.getTournee().getVendeur().getCompte());
             detailCompte.setDesignation(Constantes.CREDIT_CHEQUE_SUR_TOURNEE+versement.getCodeVersement());
 
             detailCompteDAO.add(detailCompte);              

}

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Caissier <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


//Montant Cheque
             
                for (int i = 0; i < listeVersementCheque.size(); i++) {
                    
                   VersementCheque versementCheque = listeVersementCheque.get(i);
                    
                      DetailCompte detailCompteCheque = new DetailCompte();
 
             detailCompteCheque.setMontantDebit(versementCheque.getMontant());
             detailCompteCheque.setMontantCredit(BigDecimal.ZERO);
             detailCompteCheque.setDateOperation(versementCheque.getVersement().getTournee().getDateTournee());
             detailCompteCheque.setEtat(Constantes.ETAT_VERSEMENT_CHEQUE);
             detailCompteCheque.setDateCreation(new Date());
             detailCompteCheque.setUtilisateurCreation(nav.getUtilisateur());
             detailCompteCheque.setCompte(versementCheque.getVersement().getCaissier().getCompte());
             detailCompteCheque.setDesignation(Constantes.REGLEMENT_CHEQUE_SUR_TOURNEE+versementCheque.getVersement().getCodeVersement());
 
             detailCompteDAO.add(detailCompteCheque);
                    
                }
             
            
             versement = new Versement();
            
    
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
        vendeurCombo.getSelectionModel().select(-1);
        versementCombo.getSelectionModel().select(-1);
        nomVendeurField.clear();
        numChequeField.clear();
        nomProprietaireField.clear();
        dateEcheance.setValue(null);
        dateRecu.setValue(null);
        clientCombo.getSelectionModel().select(-1);
        banqueCombo.getSelectionModel().select(-1);
        typeChequeCombo.getSelectionModel().select(-1);
        MontantField.clear();
        
        messageChequeBloque.setVisible(false);
        listeVersementCheque.clear();
        
    }

    @FXML
    private void codeArticleKeyPressed(KeyEvent event) {
    }

    @FXML
    private void ajouter(MouseEvent event) throws ParseException {
        
             if(vendeurCombo.getSelectionModel().getSelectedIndex()== -1 || 
          dateEcheance.getValue()==null || 
          dateRecu.getValue()==null || 
          versementCombo.getSelectionModel().getSelectedIndex()== -1 || 
          montantChequeField.getText().isEmpty() ||
          numChequeField.getText().isEmpty() ||
          nomProprietaireField.getText().isEmpty() ||
          typeChequeCombo.getSelectionModel().getSelectedIndex()== -1 ||
          banqueCombo.getSelectionModel().getSelectedIndex()== -1 ||
          clientCombo.getSelectionModel().getSelectedIndex()== -1 ||
          nomVendeurField.getText().isEmpty() ||     
          MontantField.getText().isEmpty()     
                ){

         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
        }else{
        
                       LocalDate localDate=dateEcheance.getValue();
            Date dateEche =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
            
             localDate=dateRecu.getValue();
             Date dateR =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
            
            
            Versement versement = mapVersement.get(versementCombo.getSelectionModel().getSelectedItem());
            
            Banque banque = mapBanque.get(banqueCombo.getSelectionModel().getSelectedItem());

            String  valeur = typeChequeCombo.getSelectionModel().getSelectedItem();
            
            ClientPF clientPF = mapClientPF.get(clientCombo.getSelectionModel().getSelectedItem());
            
            
//############################################################################## Verification Num Cheque 'Liste' ########################################################################################################################################################################################################################################                  

                      Boolean exist = false;
            
                       for (int i = 0; i < listeVersementCheque.size(); i++) {
                           
                           VersementCheque versementCheque = listeVersementCheque.get(i);
                           
                           if (versementCheque.getNumCheque().equals(numChequeField.getText())){
                            
                           exist = true;
                            break;
                           } 
                       }
                       
//############################################################################## Verification Num Cheque 'DataBase' ########################################################################################################################################################################################################################################                  
 

                       List<VersementCheque> listVersementCheque = versementChequeDAO.findAll();

                      for (int i = 0; i < listVersementCheque.size(); i++) {
                         
                      VersementCheque versementCheque = listVersementCheque.get(i);
                      
                      if(versementCheque.getNumCheque().equals(numChequeField.getText())){
                      
                      exist = true;
                            break;
                      
                      } 
                     }
                       
                       
                       if (exist == true){
                       
                           nav.showAlert(Alert.AlertType.ERROR, "Erreur", null,Constantes.MESSAGE_ALERT_EXISTE);
                           vider(event);
                           return;
                           
            


                                }else{

                 if(clientPF.isBlockClient()==false ){
            VersementCheque versementCheque = new VersementCheque();
            
            versementCheque.setNumCheque(numChequeField.getText());
            versementCheque.setNomProprietaire(nomProprietaireField.getText());
            versementCheque.setDateEcheance(dateEche);
            versementCheque.setDateRecu(dateR);
            versementCheque.setAction(false);
            versementCheque.setTypeCheque(valeur);
            versementCheque.setDateCreation(new Date());
            versementCheque.setUtilisateurCreation(nav.getUtilisateur());
            versementCheque.setMontant(new BigDecimal(MontantField.getText()));
            versementCheque.setEtat(Constantes.ETAT_STATUT_LANCE);
            versementCheque.setVersement(versement);
            versementCheque.setBanque(banque);
            versementCheque.setClient(clientPF);
            
            listeVersementCheque.add(versementCheque);
 
              setColumnProperties();
            versementChequeTable.setItems(listeVersementCheque);
            calculMontantTotal();
             vider(event);
                     
                 }else{
                 
                 messageChequeBloque.setVisible(true);
                 numChequeField.clear();
                 nomProprietaireField.clear();
                 dateEcheance.setValue(null);
                 dateRecu.setValue(null);
                 clientCombo.getSelectionModel().select(-1);
                 banqueCombo.getSelectionModel().select(-1);
                 typeChequeCombo.getSelectionModel().select(-1);
                 MontantField.clear();
                 
                 }

        }
             }
        
    }

    @FXML
    private void vider(MouseEvent event) {
        
        numChequeField.clear();
        nomProprietaireField.clear();
        dateEcheance.setValue(null);
        dateRecu.setValue(null);
        clientCombo.getSelectionModel().select(-1);
        banqueCombo.getSelectionModel().select(-1);
        typeChequeCombo.getSelectionModel().select(-1);
        MontantField.clear();
        messageChequeBloque.setVisible(false);
    }

    @FXML
    private void supprimer(MouseEvent event) {
        
            if(versementChequeTable.getSelectionModel().getSelectedIndex()!=-1)
            {
            if(listeVersementCheque.size()!=0)
            {
                
                listeVersementCheque.remove(versementChequeTable.getSelectionModel().getSelectedIndex());

            }
                int i=0;
        BigDecimal montanttotal=BigDecimal.ZERO;
        while(i<listeVersementCheque.size())
        {
            VersementCheque versementCheque =listeVersementCheque.get(i);
           montanttotal= montanttotal.add(versementCheque.getMontant());
            i++;
        }
        montantTotalField.setText(montanttotal+"");

        }
        
    }
    
}
