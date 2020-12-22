/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Vente;

import Utils.Constantes;
import dao.Entity.Cheque;
import dao.Entity.CompteVersement;
import dao.Entity.DetailCompte;
import dao.Entity.Vendeur;
import dao.Entity.Versement;
import dao.Entity.VersementBancaire;
import dao.Entity.VersementCheque;
import dao.Manager.CompteVersementDAO;
import dao.Manager.DetailCompteDAO;
import dao.Manager.VendeurDAO;
import dao.Manager.VersementBancaireDAO;
import dao.Manager.VersementChequeDAO;
import dao.Manager.VersementDAO;
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
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ListeVersementChequeController implements Initializable {

    @FXML
    private ComboBox<String> vendeurCombo;
    @FXML
    private DatePicker dateEchance;
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
    private TableColumn<VersementCheque, Boolean> ActionColumn;
    @FXML
    private Button btnValider;
    @FXML
    private Button btnRafraichir;
    @FXML
    private TextField montantTotalField;
    @FXML
    private RadioButton impayeRadio;
    @FXML
    private RadioButton payerRadio;
    @FXML
    private ImageView rechercheBtn;
    @FXML
    private ComboBox<String> typeChequeCombo;
    @FXML
    private ToggleGroup payeImpaye;
    @FXML
    private RadioButton verseRadio;
    @FXML
    private ToggleGroup payeImpaye1;
    @FXML
    private DatePicker dateVersement;
    @FXML
    private ComboBox<String> etatCombo;
    @FXML
    private TextField numRemiseField;
    @FXML
    private TextField numVersementField;
    @FXML
    private ComboBox<String> compteCombo;
   
    /**
     * Initializes the controller class.
     */
    
    private Map<String, Vendeur> mapVendeur = new HashMap<>();
    VendeurDAO vendeurDAO = new VendeurDAOImpl();
    
      private Map<String, CompteVersement> mapCompteVersement = new HashMap<>();
    CompteVersementDAO compteVersementDAO = new CompteVersementDAOImpl();
    
     DetailCompteDAO detailCompteDAO = new DetailCompteDAOImpl();
    
    navigation nav = new navigation();
    
    VersementChequeDAO versementChequedao = new VersementChequeDAOImpl();
    VersementBancaireDAO versementBancairedao = new VersementBancaireDAOImpl();
    VersementDAO versementdao = new VersementDAOImpl();
    
    private final ObservableList<VersementCheque> listeVersementCheque = FXCollections.observableArrayList();
    
    ObservableList<String> typeCheque =FXCollections.observableArrayList(Constantes.TYPE_CHEQUE_NON_VERSE,Constantes.TYPE_CHEQUE_RETOUR,Constantes.ETAT_STATUT_ATTENTE,Constantes.ETAT_STATUT_IMPAYE);

    ObservableList<String> etat =FXCollections.observableArrayList(Constantes.ETAT_RETOUR_VENDEUR,Constantes.ETAT_ESPECE,Constantes.ETAT_VERSEMENT_BANCAIRE,Constantes.SYSTEM_SUIVI_CHEQUE);

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        typeChequeCombo.setItems(typeCheque);
        etatCombo.setItems(etat);
        
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
        
        
            List<CompteVersement> listCompteVersement = compteVersementDAO.findAll();
            listCompteVersement.stream().map((compteVersement) -> {
                compteCombo.getItems().addAll(compteVersement.getLibelle());
                return compteVersement;
            }).forEachOrdered((compteVersement) -> {
                mapCompteVersement.put(compteVersement.getLibelle(), compteVersement);
            });
            
            
        loadDetail();
        setColumnProperties();  
        
        calculMontantTotal();
        
        versementChequeTable.setEditable(true);
        ActionColumn.setEditable(true);
              
           etatCombo.setDisable(true);
           numRemiseField.setDisable(true);        
           numVersementField.setDisable(true);
           dateVersement.setDisable(true);
           compteCombo.setDisable(true);
              
    }    

           void calculMontantTotal(){
    
        BigDecimal valeur = BigDecimal.ZERO;
        
        for (int i = 0; i < versementChequeTable.getItems().size() ; i++) {
           VersementCheque versementCheque = versementChequeTable.getItems().get(i);
           
           valeur = valeur.add(versementCheque.getMontant());
            
            
        }
    
        montantTotalField.setText(valeur+"");
        
    }
    
      void loadDetail() {

        listeVersementCheque.clear();
        listeVersementCheque.addAll(versementChequedao.findAll());
        versementChequeTable.setItems(listeVersementCheque);
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
                  
              ActionColumn.cellValueFactoryProperty();
          ActionColumn.setCellValueFactory((cellData) -> {
          VersementCheque cellvalue= cellData.getValue();
              BooleanProperty property = new SimpleBooleanProperty();
                      property.set(cellvalue.isAction());
                      
                      property.addListener((observabel, oldvalue,newvalue)->cellvalue.setAction(newvalue));
              
              return property; 
          });
          ActionColumn.setCellFactory(act-> new CheckBoxTableCell<>());
       
    }
    
    
    @FXML
    private void vendeurComboOnAction(ActionEvent event) {
    }

    @FXML
    private void afficherArt(MouseEvent event) {
    }

    @FXML
    private void validerOnAction(ActionEvent event) throws ParseException {
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_MODIFICATION);
                alert.setTitle("Confirmation");
                Optional<ButtonType> result = alert.showAndWait();


                if (result.get() == ButtonType.OK) {
                    
   
                    if (verseRadio.isSelected()==true){
                    
                       if(
          dateVersement.getValue()==null || 
          numRemiseField.getText().isEmpty() ||
          compteCombo.getSelectionModel().getSelectedIndex()== -1
  
                       ){

         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
        }else{
                        
                       LocalDate localDate=dateVersement.getValue();
             Date dateVers =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
            
            
            CompteVersement compteVersement = mapCompteVersement.get(compteCombo.getSelectionModel().getSelectedItem());
            
                        
                        
                        for (int i = 0; i < listeVersementCheque.size(); i++) {
                            
                            VersementCheque versementCheque = listeVersementCheque.get(i);
                            
                            if (versementCheque.isAction() == true && versementCheque.getTypeCheque().equals(Constantes.TYPE_CHEQUE_NON_VERSE)){
                            
                                versementCheque.setTypeCheque(Constantes.ETAT_STATUT_ATTENTE);
                                versementCheque.setNumRemise(numRemiseField.getText());
                                versementCheque.setDateVersement(dateVers);
                                versementCheque.setCompteVersement(compteVersement);
                                versementCheque.setEtat(Constantes.ETAT_STATUT_LANCE);
                                versementCheque.setAction(false);
                                
                                versementChequedao.edit(versementCheque);
                                
                            }
                            
                        }
                        
                              nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_AJOUT);
                            rafraichirOnAction(event);   
                       }
                    }else if (payerRadio.isSelected()==true){
                    
                        
                             for (int i = 0; i < listeVersementCheque.size(); i++) {
                            
                            VersementCheque versementCheque = listeVersementCheque.get(i);
                            
                            if (versementCheque.isAction() == true && versementCheque.getTypeCheque().equals(Constantes.ETAT_STATUT_ATTENTE)){
                            
                                versementCheque.setTypeCheque(Constantes.ETAT_STATUT_PAYE);
                                versementCheque.setEtat(Constantes.ETAT_STATUT_VALIDER);
                                versementCheque.setAction(false);
                                
                                versementChequedao.edit(versementCheque);
                                
                            }
                            
                        }
                        
                            nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_AJOUT);
                            rafraichirOnAction(event);  
                              
                    }else if (impayeRadio.isSelected()==true){
                
                                  if(
          dateVersement.getValue()==null || 
          numVersementField.getText().isEmpty() ||
          compteCombo.getSelectionModel().getSelectedIndex()== -1
  
                       ){

         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
        }else{
                                      
                     if (etatCombo.getSelectionModel().getSelectedItem().equals(Constantes.ETAT_RETOUR_VENDEUR)){             
                                      
                                        for (int i = 0; i < listeVersementCheque.size(); i++) {
                            
                            VersementCheque versementCheque = listeVersementCheque.get(i);
                            
                            if (versementCheque.isAction() == true && versementCheque.getTypeCheque().equals(Constantes.ETAT_STATUT_ATTENTE)){
                            
                                versementCheque.setTypeCheque(Constantes.ETAT_STATUT_IMPAYE);
                                versementCheque.setEtat(Constantes.ETAT_RETOUR_VENDEUR);
                                versementCheque.setAction(false);
                                
                                versementChequedao.edit(versementCheque);
                                
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Vendeur <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

//Montant Credit Cheque


   DetailCompte detailCompte = new DetailCompte();
 
             detailCompte.setMontantDebit(BigDecimal.ZERO);
             detailCompte.setMontantCredit(versementCheque.getMontant());
             detailCompte.setDateOperation(versementCheque.getVersement().getTournee().getDateTournee());
             detailCompte.setEtat(Constantes.ETAT_VERSEMENT_CHEQUE_RETOUR);
             detailCompte.setDateCreation(new Date());
             detailCompte.setUtilisateurCreation(nav.getUtilisateur());
             detailCompte.setCompte(versementCheque.getVersement().getTournee().getVendeur().getCompte());
             detailCompte.setDesignation(Constantes.PAIEMENT_CHEQUE_N+versementCheque.getNumCheque());
 
             detailCompteDAO.add(detailCompte);              

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Caissier <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

//Montant Debit Cheque
             
                    
                      DetailCompte detailCompteCheque = new DetailCompte();
 
             detailCompteCheque.setMontantDebit(versementCheque.getMontant());
             detailCompteCheque.setMontantCredit(BigDecimal.ZERO);
             detailCompteCheque.setDateOperation(versementCheque.getVersement().getTournee().getDateTournee());
             detailCompteCheque.setEtat(Constantes.ETAT_VERSEMENT_CHEQUE_RETOUR);
             detailCompteCheque.setDateCreation(new Date());
             detailCompteCheque.setUtilisateurCreation(nav.getUtilisateur());
             detailCompteCheque.setCompte(versementCheque.getVersement().getCaissier().getCompte());
             detailCompteCheque.setDesignation(Constantes.RETRAIT_CHEQUE_N+versementCheque.getNumCheque());
 
             detailCompteDAO.add(detailCompteCheque);
                    
                            }
                            
                        }
                            
                        nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_AJOUT);
                            rafraichirOnAction(event);              
   
                                  }
                     else if (etatCombo.getSelectionModel().getSelectedItem().equals(Constantes.ETAT_ESPECE)){             
                                      
                                      for (int i = 0; i < listeVersementCheque.size(); i++) {
                            
                            VersementCheque versementCheque = listeVersementCheque.get(i);
                            
                            if (versementCheque.isAction() == true && versementCheque.getTypeCheque().equals(Constantes.ETAT_STATUT_ATTENTE)){
                            
                                versementCheque.setTypeCheque(Constantes.ETAT_STATUT_IMPAYE);
                                versementCheque.setEtat(Constantes.ETAT_ESPECE);
                                versementCheque.setAction(false);
                                
                                versementChequedao.edit(versementCheque);
                                
                            }
                            
                        }
                         nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_AJOUT);
                            rafraichirOnAction(event);  
                                  }         
                     else if (etatCombo.getSelectionModel().getSelectedItem().equals(Constantes.ETAT_VERSEMENT_BANCAIRE)){             
                                   
                                        LocalDate localDate=dateVersement.getValue();
             Date dateVers =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
            
            
            CompteVersement compteVersement = mapCompteVersement.get(compteCombo.getSelectionModel().getSelectedItem());
            
//############################################################################## Versement Cheque ########################################################################################################################################################################################################################################                  
                         
                         
                         VersementBancaire versementBancaire = new VersementBancaire();
                         
                                             for (int i = 0; i < listeVersementCheque.size(); i++) {
                            
                            VersementCheque versementCheque = listeVersementCheque.get(i);
                            
                            if (versementCheque.isAction() == true && versementCheque.getTypeCheque().equals(Constantes.ETAT_STATUT_ATTENTE)){
                            
                                versementCheque.setTypeCheque(Constantes.ETAT_STATUT_IMPAYE);
                                versementCheque.setEtat(Constantes.ETAT_VERSEMENT_BANCAIRE);
                                versementCheque.setAction(false);
                                
                                versementChequedao.edit(versementCheque);
                                
//############################################################################## Versement Bancaire ########################################################################################################################################################################################################################################                  
  
                          versementBancaire.setBanque(versementCheque.getBanque());
                          versementBancaire.setCodeProprietaire(versementCheque.getVersement().getTournee().getVendeur().getCode());
                          versementBancaire.setNomProprietaire(versementCheque.getVersement().getTournee().getVendeur().getNom());   
                          versementBancaire.setDateVersement(dateVers);
                          versementBancaire.setMontant(versementCheque.getMontant());
                          versementBancaire.setNumVersement(numVersementField.getText());
                          versementBancaire.setUtilisateurCreation(nav.getUtilisateur());
                          versementBancaire.setVersement(versementCheque.getVersement());
                          versementBancaire.setDateCreation(new Date());
                          versementBancaire.setCompteVersement(compteVersement);
                          
                                versementBancairedao.add(versementBancaire);
                                
//############################################################################## Versement ########################################################################################################################################################################################################################################                  
                              
//                                Versement versement = versementdao.findById(versementCheque.getVersement().getId());
//                                
//                                versement.setEtat(Constantes.ETAT_STATUT_ATTENTE);
//                                versement.setEtatVersementBancaire(false);
//                                
//                                versementdao.edit(versement);
                              
                            }
                            
                        }
                         
                            nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_AJOUT);
                            rafraichirOnAction(event);  
                         
                    }else if (etatCombo.getSelectionModel().getSelectedItem().equals(Constantes.SYSTEM_SUIVI_CHEQUE)){                     
                    
                                       for (int i = 0; i < listeVersementCheque.size(); i++) {
                            
                            VersementCheque versementCheque = listeVersementCheque.get(i);
                            
                            if (versementCheque.isAction() == true && versementCheque.getTypeCheque().equals(Constantes.ETAT_STATUT_ATTENTE)){
                            
                                versementCheque.setTypeCheque(Constantes.ETAT_STATUT_IMPAYE);
                                versementCheque.setEtat(Constantes.SYSTEM_SUIVI_CHEQUE);
                                versementCheque.setAction(false);
                                
                                versementChequedao.edit(versementCheque);
                                
                            }
                            
                        }
                         nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_AJOUT);
                            rafraichirOnAction(event);  
                    
                    }
                                  }
                                  
    }
    }
    }              
             
 
    @FXML
    private void rafraichirOnAction(ActionEvent event) {
        
        vendeurCombo.getSelectionModel().select(-1);
        dateEchance.setValue(null);
        typeChequeCombo.getSelectionModel().select(-1);
        
        impayeRadio.setSelected(false);
        payerRadio.setSelected(false);
        verseRadio.setSelected(false);
        
         etatCombo.getSelectionModel().select(-1);
          numRemiseField.clear();
          numVersementField.clear();
          dateVersement.setValue(null);
          compteCombo.getSelectionModel().select(-1);
        
            etatCombo.setDisable(true);
           numRemiseField.setDisable(true);        
           numVersementField.setDisable(true);
           dateVersement.setDisable(true);
           compteCombo.setDisable(true);
        
        loadDetail();
        setColumnProperties();  
        
        calculMontantTotal();
        
    }

    @FXML
    private void impayeOnAction(ActionEvent event) {
        
        etatCombo.setDisable(false);
           numRemiseField.setDisable(true);        
           numVersementField.setDisable(true);
           dateVersement.setDisable(true);
           compteCombo.setDisable(true);
        
          etatCombo.getSelectionModel().select(-1);
          numRemiseField.clear();
          numVersementField.clear();
          dateVersement.setValue(null);
          compteCombo.getSelectionModel().select(-1);
           
           
    }

    @FXML
    private void payerOnAction(ActionEvent event) {
        

          etatCombo.setDisable(true);
          numRemiseField.setDisable(true);        
          numVersementField.setDisable(true);
          dateVersement.setDisable(true);
          compteCombo.setDisable(true);
          
          etatCombo.getSelectionModel().select(-1);
          numRemiseField.clear();
          numVersementField.clear();
          dateVersement.setValue(null);
          compteCombo.getSelectionModel().select(-1);
         
    }

    @FXML
    private void rechercheArticle(MouseEvent event) throws ParseException {
        
        Date dtEcheance = null;
        
          LocalDate localDate=dateEchance.getValue();
          if (localDate!= null){
           dtEcheance=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
          }
               
        
        Vendeur vendeur = mapVendeur.get(vendeurCombo.getSelectionModel().getSelectedItem());
        String typeCheque = typeChequeCombo.getSelectionModel().getSelectedItem();
        
        
        if( vendeur !=null && dtEcheance == null && typeCheque == null ){
        
            listeVersementCheque.clear();
         listeVersementCheque.addAll(versementChequedao.findByVendeur(vendeur.getId(), nav.getUtilisateur().getDepot().getId()));
        versementChequeTable.setItems(listeVersementCheque);
        calculMontantTotal();
        
        }else if(vendeur == null && dtEcheance != null && typeCheque == null){
        
            listeVersementCheque.clear();
        listeVersementCheque.addAll(versementChequedao.findByDate(dtEcheance, nav.getUtilisateur().getDepot().getId()));
        versementChequeTable.setItems(listeVersementCheque);
        calculMontantTotal();
        
        }else if(vendeur==null && dtEcheance == null && typeCheque!=null ){
        
            listeVersementCheque.clear();
        listeVersementCheque.addAll(versementChequedao.findByTypeCheque(typeCheque, nav.getUtilisateur().getDepot().getId()));
        versementChequeTable.setItems(listeVersementCheque);
        calculMontantTotal();
        
        }else if(vendeur!=null && dtEcheance != null && typeCheque==null){
        
            listeVersementCheque.clear();
        listeVersementCheque.addAll(versementChequedao.findByVendeurAndDateEch(vendeur.getId(),dtEcheance ,nav.getUtilisateur().getDepot().getId()));
        versementChequeTable.setItems(listeVersementCheque);
        calculMontantTotal();
        
        }else if(vendeur!=null && dtEcheance == null && typeCheque!=null){
        
            listeVersementCheque.clear();
        listeVersementCheque.addAll(versementChequedao.findByVendeurAndTypeCheque(vendeur.getId(),typeCheque ,nav.getUtilisateur().getDepot().getId()));
        versementChequeTable.setItems(listeVersementCheque);
        calculMontantTotal();
        
        }else if(vendeur==null && dtEcheance != null && typeCheque!=null){
        
            listeVersementCheque.clear();
        listeVersementCheque.addAll(versementChequedao.findByDateEchAndTypeCheque(dtEcheance,typeCheque ,nav.getUtilisateur().getDepot().getId()));
        versementChequeTable.setItems(listeVersementCheque);
        calculMontantTotal();
        
        }else if(vendeur!=null && dtEcheance != null && typeCheque!=null){
        
            listeVersementCheque.clear();
        listeVersementCheque.addAll(versementChequedao.findByVendeurAndDateEchAndTypeCheque(vendeur.getId(),dtEcheance ,typeCheque,nav.getUtilisateur().getDepot().getId()));
        versementChequeTable.setItems(listeVersementCheque);
        calculMontantTotal();
        
        }else{
        
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null,Constantes.MESSAGE_ALERT_VERIFIER_COORDONNEES);
        calculMontantTotal();    
        }
    }

    @FXML
    private void TypeChequeComboOnAction(ActionEvent event) {
    }

    @FXML
    private void verseOnAction(ActionEvent event) {
        
          etatCombo.setDisable(true);
          numRemiseField.setDisable(false);        
          numVersementField.setDisable(true);
          dateVersement.setDisable(false);
          compteCombo.setDisable(false);
          
          etatCombo.getSelectionModel().select(-1);
          numRemiseField.clear();
          numVersementField.clear();
          dateVersement.setValue(null);
          compteCombo.getSelectionModel().select(-1);
        

          
    }

    @FXML
    private void etatOnAction(ActionEvent event) {
        
            String etat = etatCombo.getSelectionModel().getSelectedItem();
        
         
         if(etat!=null){
        
        if(etatCombo.getSelectionModel().getSelectedItem().equals(Constantes.ETAT_RETOUR_VENDEUR) ){
        
           numRemiseField.setDisable(true);        
           numVersementField.setDisable(true);
           dateVersement.setDisable(true);
           compteCombo.setDisable(true);
           
          numRemiseField.clear();
          numVersementField.clear();
          dateVersement.setValue(null);
          compteCombo.getSelectionModel().select(-1);
            
            
        }else if (etatCombo.getSelectionModel().getSelectedItem().equals(Constantes.ETAT_ESPECE) ){
        
           numRemiseField.setDisable(true);        
           numVersementField.setDisable(true);
           dateVersement.setDisable(true);
           compteCombo.setDisable(true);
            
          numRemiseField.clear();
          numVersementField.clear();
          dateVersement.setValue(null);
          compteCombo.getSelectionModel().select(-1);
            
        }else if (etatCombo.getSelectionModel().getSelectedItem().equals(Constantes.ETAT_VERSEMENT_BANCAIRE) ){
        
           numRemiseField.setDisable(true);        
           numVersementField.setDisable(false);
           dateVersement.setDisable(false);
           compteCombo.setDisable(false);

          numRemiseField.clear();
          numVersementField.clear();
          dateVersement.setValue(null);
          compteCombo.getSelectionModel().select(-1);
           
        }
        
         }
        
    }

    
}
