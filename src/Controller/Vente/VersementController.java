 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Vente;

import Controller.HomeController;
import Utils.Constantes;
import dao.Entity.Caissier;
import dao.Entity.Compte;
import dao.Entity.DetailCaissierVendeur;
import dao.Entity.DetailCompte;
import dao.Entity.Sequenceur;
import dao.Entity.Tournee;
import dao.Entity.Versement;
import dao.Manager.CaissierDAO;
import dao.Manager.CompteDAO;
import dao.Manager.DetailCaissierVendeurDAO;
import dao.Manager.TourneeDAO;
import dao.Manager.VendeurDAO;
import dao.Manager.VersementDAO;
import dao.ManagerImpl.CompteDAOImpl;
import dao.ManagerImpl.DetailCompteDAOImpl;
import dao.ManagerImpl.TourneeDAOImpl;
import dao.ManagerImpl.VendeurDAOImpl;
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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import dao.Manager.DetailCompteDAO;
import dao.Manager.DetailVendeurSecteurDAO;
import dao.Manager.SequenceurDAO;
import dao.ManagerImpl.CaissierDAOImpl;
import dao.ManagerImpl.DetailCaissierVendeurDAOImpl;
import dao.ManagerImpl.DetailVendeurSecteurDAOImpl;
import dao.ManagerImpl.SequenceurDAOImpl;
import java.util.Optional;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class VersementController implements Initializable {

  
     @FXML
    private TableView<Tournee> tourneeTable;
    @FXML
    private TableColumn<Tournee, Date> dateTourneeColumn;
    @FXML
    private TableColumn<Tournee, String> vendeurColumn;
    @FXML
    private TableColumn<Tournee, String> secteurColumn;
    @FXML
    private TableColumn<Tournee, String> magasinColumn;
    @FXML
    private TableColumn<Tournee, String> etatColumn;
    
    @FXML
    private TextField montantEspece;
    @FXML
    private TextField montantCheque;
    @FXML
    private DatePicker dateReception;
    @FXML
    private TextField prixVersementField;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRafraichir;
    @FXML
    private TextField codeVersementField;
    @FXML
    private TextField montantCredit;
    @FXML
    private TextField remise;
    @FXML
    private TextField libelleField;
    @FXML
    private TextField montantBanque;
    @FXML
    private TextField totalCredit;
    @FXML
    private TextField vercementCredit;
    @FXML
    private ComboBox<String> caissierCombo;
    @FXML
    private Label messageChequeBloque;
    
    public static VersementController versementControllerRef;
    
    public  DetailCompte detailCompte ;
    public  Tournee tournee;
    /**
     * Initializes the controller class.
     */
      navigation nav = new navigation();
    
     private Map<String, DetailCaissierVendeur> mapDetailCaissierVendeur = new HashMap<>();
    DetailVendeurSecteurDAO detailVendeurSecteurDAO = new DetailVendeurSecteurDAOImpl();
    
    private Map<String, Tournee> mapTournee = new HashMap<>();
    TourneeDAO  tourneeDAO = new TourneeDAOImpl();
    
    DetailCompteDAO detailCompteDAO = new DetailCompteDAOImpl();
    private Map<String,Compte> mapCompte=new HashMap<>();
    
    VersementDAO  versementDAO = new VersementDAOImpl();
    CompteDAO compteDAO = new  CompteDAOImpl();
    CaissierDAO caissierDAO = new CaissierDAOImpl();
    VendeurDAO vendeurDAO = new VendeurDAOImpl();
    
    DetailCaissierVendeurDAO detailCaissierVendeurDAO = new DetailCaissierVendeurDAOImpl();
    
    SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();  
//
//    private Map<String, Caissier> mapCaissier = new HashMap<>();

    
    private final ObservableList<Tournee> listeTournee = FXCollections.observableArrayList();
    
    
    BigDecimal cheque = BigDecimal.ZERO;
    BigDecimal remiseMt = BigDecimal.ZERO;
    BigDecimal espece = BigDecimal.ZERO;
    BigDecimal recuBanque = BigDecimal.ZERO;
    BigDecimal creditVercement = BigDecimal.ZERO;
    BigDecimal creditTotal = BigDecimal.ZERO;
    
    
   
       
        void Incrementation (){
       
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.VERSEMENNT);
          libelleField.setText(sequenceur.getCode()+" "+(sequenceur.getValeur()+1));

   }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Incrementation ();
        
       
        loadDetail();
        setColumnPropertiesTournee();
        
        versementControllerRef = this;
        
         messageChequeBloque.setVisible(false);
        
  
    }

      void setColumnPropertiesTournee(){
   
          dateTourneeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Tournee, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<Tournee, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDateTournee());
            }
        });
          
              vendeurColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Tournee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Tournee, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getVendeur().getNom());
            }
        });
           
        secteurColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Tournee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Tournee, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getSecteur().getLibelle());
            }
        });
        
        magasinColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Tournee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Tournee, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getMagasin().getLibelle());
            }
        });
        
        etatColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Tournee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Tournee, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getEtat());
            }
        });
         
    }
    
       void loadDetail() {

        listeTournee.clear();
        listeTournee.addAll(tourneeDAO.findTourneeByEtat(Constantes.ETAT_VERSEMENT, nav.getUtilisateur().getDepot().getId()));
        tourneeTable.setItems(listeTournee);
    }
    
    
//    public void chargeDonner(Depot depot, Vendeur vendeurTmp){
//      
//        if (depot != null && vendeurTmp!= null)
//        {
//
//          vendeurTMP = vendeurTmp;
//        depotCombo.getSelectionModel().select(depot.getLibelle1());
//        vendeurCombo.getSelectionModel().select(vendeurTmp.getNom());
//     
//            codeVersementCombo.getItems().clear();
//
//               tournee = tourneeDAO.findTourneeByVendeur(depot.getId(),Constantes.ETAT_STATUT_VALIDER, vendeurTmp.getId());
//
//              if (tournee != null){
//               
//              codeVersementCombo.getSelectionModel().select(tournee.getCodeChargement());
//                prixVersementField.setText(tournee.getTotalVendue()+"");
//              }
//
//        }
//
//    }

 
    @FXML
    private void afficherArt(MouseEvent event) {
        
if (tourneeTable.getSelectionModel().getSelectedIndex()!=-1){
          
        tournee = tourneeTable.getSelectionModel().getSelectedItem();

        codeVersementField.setText(tournee.getCodeVent());
        prixVersementField.setText(tournee.getTotalVendue()+"");
        montantCredit.setText(tournee.getMontantCredit()+"");
        vercementCredit.setText(tournee.getTotalVendue().add(tournee.getMontantCredit())+"");
        montantEspece.setText(new BigDecimal(0).toString());
        montantCheque.setText(new BigDecimal(0).toString());
        montantBanque.setText(new BigDecimal(0).toString());
        totalCredit.setText(new BigDecimal(vercementCredit.getText())+"");
        remise.setText(new BigDecimal(0).toString());
         messageChequeBloque.setVisible(false);
         
         montantEspece.setDisable(false);
          montantBanque.setDisable(false);
           montantCheque.setDisable(false);
            remise.setDisable(false);
            
//############################################################################# Caissier ################################################################################################################################################################################
 
                  caissierCombo.getItems().clear();
          

             if(tournee!=null){
            List<DetailCaissierVendeur> listDetailCaissierVendeur = detailCaissierVendeurDAO.findByDepotAndVendeur(nav.getUtilisateur().getDepot().getId(),tournee.getVendeur().getId());
            listDetailCaissierVendeur.stream().map((caissier) -> {
                caissierCombo.getItems().addAll(caissier.getCaissier().getNom());
                return caissier;
            }).forEachOrdered((caissier) -> {
                mapDetailCaissierVendeur.put(caissier.getCaissier().getNom(), caissier);
            });
             }


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
          codeVersementField.getText().equals("")|| 
          montantCredit.getText().equals("")||        
          prixVersementField.getText().equals("")||
          caissierCombo.getSelectionModel().getSelectedIndex()== -1 ||
          dateReception.getValue()==null)
          {
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
         return;
     }
        else {
              
                if (tournee.getVendeur().getMontantPlafond().compareTo(new BigDecimal(totalCredit.getText()))<0){
              
                     messageChequeBloque.setVisible(true);
                    return;
                    
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Versement Caisse <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
                }else{
              Versement versement = new Versement();
              
              LocalDate localDate=dateReception.getValue();
              Date dateVersement=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
       
              String CodeVersement= libelleField.getText();
              SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
              String dateFormat2=dateFormat.format(tournee.getDateTournee());
              
           Caissier caissier  = caissierDAO.findByNom(caissierCombo.getSelectionModel().getSelectedItem());
           
        versement.setDateVersement(dateVersement);
        versement.setCodeVersement(CodeVersement+"/"+tournee.getVendeur().getNom()+"/"+dateFormat2);
        
        if(new BigDecimal(montantEspece.getText()).compareTo(BigDecimal.ZERO)>0 && new BigDecimal(montantCheque.getText()).compareTo(BigDecimal.ZERO)==0 && new BigDecimal(montantBanque.getText()).compareTo(BigDecimal.ZERO)==0){
        
            versement.setEtat(Constantes.ETAT_STATUT_VALIDER);
            
        }else{
            
            versement.setEtat(Constantes.ETAT_STATUT_ATTENTE);
            
        }
        
        versement.setMontantRemise(new BigDecimal(remise.getText()));
        versement.setMontantCheque(new BigDecimal(montantCheque.getText()));
        versement.setMontantEspece(new BigDecimal(montantEspece.getText()));
        versement.setMontantCredit(new BigDecimal(montantCredit.getText()));
        versement.setMontantBanque(new BigDecimal(montantBanque.getText()));
        versement.setVersementCredit(new BigDecimal(vercementCredit.getText()));
        versement.setTotalCredit(new BigDecimal(totalCredit.getText()));
        versement.setTournee(tournee);
        versement.setCaissier(caissier);
        

        versementDAO.add(versement);
        
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Tournee <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

  
        tournee.setEtat(Constantes.ETAT_TRAITE);
        tourneeDAO.edit(tournee);   
        
          
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Vendeur <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

if (new BigDecimal(montantEspece.getText()).compareTo(BigDecimal.ZERO)!=0){

              DetailCompte detailCompteVendeur = new DetailCompte();
 
             detailCompteVendeur.setMontantDebit(new BigDecimal(montantEspece.getText()));
             detailCompteVendeur.setMontantCredit(BigDecimal.ZERO);
             detailCompteVendeur.setDateOperation(tournee.getDateTournee());
             detailCompteVendeur.setEtat(Constantes.ETAT_VERSEMENT);
             detailCompteVendeur.setDateCreation(new Date());
             detailCompteVendeur.setUtilisateurCreation(nav.getUtilisateur());
             detailCompteVendeur.setCompte(tournee.getVendeur().getCompte());
             detailCompteVendeur.setDesignation(Constantes.VERSEMENT_ESPECE_N+libelleField.getText());
 
             detailCompteDAO.add(detailCompteVendeur);
    
}

if (new BigDecimal(montantCheque.getText()).compareTo(BigDecimal.ZERO)!=0){

             DetailCompte detailCompteVendeur = new DetailCompte();
 
             detailCompteVendeur.setMontantDebit(new BigDecimal(montantCheque.getText()));
             detailCompteVendeur.setMontantCredit(BigDecimal.ZERO);
             detailCompteVendeur.setDateOperation(tournee.getDateTournee());
             detailCompteVendeur.setEtat(Constantes.ETAT_VERSEMENT);
             detailCompteVendeur.setDateCreation(new Date());
             detailCompteVendeur.setUtilisateurCreation(nav.getUtilisateur());
             detailCompteVendeur.setCompte(tournee.getVendeur().getCompte());
             detailCompteVendeur.setDesignation(Constantes.VERSEMENT_CHEQUE_N+libelleField.getText());
 
             detailCompteDAO.add(detailCompteVendeur);
    
}

if (new BigDecimal(montantBanque.getText()).compareTo(BigDecimal.ZERO)!=0){

    
             DetailCompte detailCompteVendeur = new DetailCompte();
 
             detailCompteVendeur.setMontantDebit(new BigDecimal(montantBanque.getText()));
             detailCompteVendeur.setMontantCredit(BigDecimal.ZERO);
             detailCompteVendeur.setDateOperation(tournee.getDateTournee());
             detailCompteVendeur.setEtat(Constantes.ETAT_VERSEMENT);
             detailCompteVendeur.setDateCreation(new Date());
             detailCompteVendeur.setUtilisateurCreation(nav.getUtilisateur());
             detailCompteVendeur.setCompte(tournee.getVendeur().getCompte());
             detailCompteVendeur.setDesignation(Constantes.VERSEMENT_BANCAIRE_N+libelleField.getText());
 
             detailCompteDAO.add(detailCompteVendeur);
}

if (new BigDecimal(totalCredit.getText()).compareTo(BigDecimal.ZERO)!=0){

              DetailCompte detailCompteVendeur = new DetailCompte();
 
             detailCompteVendeur.setMontantDebit(BigDecimal.ZERO);
             detailCompteVendeur.setMontantCredit(new BigDecimal(totalCredit.getText()));
             detailCompteVendeur.setDateOperation(tournee.getDateTournee());
             detailCompteVendeur.setEtat(Constantes.ETAT_VERSEMENT);
             detailCompteVendeur.setDateCreation(new Date());
             detailCompteVendeur.setUtilisateurCreation(nav.getUtilisateur());
             detailCompteVendeur.setCompte(tournee.getVendeur().getCompte());
             detailCompteVendeur.setDesignation(Constantes.CREDIT_VERSEMENT+libelleField.getText());
 
             detailCompteDAO.add(detailCompteVendeur);
}

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Caissier <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

              
  if (new BigDecimal(montantEspece.getText()).compareTo(BigDecimal.ZERO)!=0){

                         DetailCompte detailCompteCaissier = new DetailCompte();
 
             detailCompteCaissier.setMontantDebit(new BigDecimal(montantEspece.getText()));
             detailCompteCaissier.setMontantCredit(BigDecimal.ZERO);
             detailCompteCaissier.setDateOperation(tournee.getDateTournee());
             detailCompteCaissier.setEtat(Constantes.ETAT_VERSEMENT);
             detailCompteCaissier.setDateCreation(new Date());
             detailCompteCaissier.setUtilisateurCreation(nav.getUtilisateur());
             detailCompteCaissier.setCompte(caissier.getCompte());
             detailCompteCaissier.setDesignation(Constantes.VERSEMENT_ESPECE_N+libelleField.getText());
 
            detailCompteDAO.add(detailCompteCaissier);
    
}
              if (new BigDecimal(montantCheque.getText()).compareTo(BigDecimal.ZERO)!=0){

             DetailCompte detailCompteCaissier = new DetailCompte();
 
             detailCompteCaissier.setMontantDebit(BigDecimal.ZERO);
             detailCompteCaissier.setMontantCredit(new BigDecimal(montantCheque.getText()));
             detailCompteCaissier.setDateOperation(tournee.getDateTournee());
             detailCompteCaissier.setEtat(Constantes.ETAT_VERSEMENT);
             detailCompteCaissier.setDateCreation(new Date());
             detailCompteCaissier.setUtilisateurCreation(nav.getUtilisateur());
             detailCompteCaissier.setCompte(caissier.getCompte());
             detailCompteCaissier.setDesignation(Constantes.VERSEMENT_CHEQUE_N+libelleField.getText());
 
            detailCompteDAO.add(detailCompteCaissier);
    
}
              if (new BigDecimal(montantBanque.getText()).compareTo(BigDecimal.ZERO)!=0){

    
                DetailCompte detailCompteCaissier = new DetailCompte();
 
             detailCompteCaissier.setMontantDebit(BigDecimal.ZERO);
             detailCompteCaissier.setMontantCredit(new BigDecimal(montantBanque.getText()));
             detailCompteCaissier.setDateOperation(tournee.getDateTournee());
             detailCompteCaissier.setEtat(Constantes.ETAT_VERSEMENT);
             detailCompteCaissier.setDateCreation(new Date());
             detailCompteCaissier.setUtilisateurCreation(nav.getUtilisateur());
             detailCompteCaissier.setCompte(caissier.getCompte());
             detailCompteCaissier.setDesignation(Constantes.VERSEMENT_BANCAIRE_N+libelleField.getText());
 
            detailCompteDAO.add(detailCompteCaissier);

}
                  

           Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.VERSEMENNT);
           sequenceur.setValeur(sequenceur.getValeur()+1);
           sequenceurDAO.edit(sequenceur);
           Incrementation();  
           
           loadDetail();
           setColumnPropertiesTournee();
           
           rafraichirOnAction(event);
           
   }
          }
            }
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
        
        dateReception.setValue(null);
        codeVersementField.clear();
        prixVersementField.clear();
        montantCredit.clear();
        montantEspece.setText("");
        montantCheque.setText("");
        montantBanque.setText("");
        vercementCredit.setText("");
        totalCredit.setText("");
        remise.setText("");
        messageChequeBloque.setVisible(false);
        caissierCombo.getSelectionModel().select(-1);
        
         montantEspece.setDisable(false);
          montantBanque.setDisable(false);
           montantCheque.setDisable(false);
            remise.setDisable(false);
        
        loadDetail();
        setColumnPropertiesTournee();
    }

    private void consultationCompteVenteOnAction(ActionEvent event) {
        
             HomeController.homeControllerRef.consultationCompteVente();
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

          creditVercement=  new BigDecimal(vercementCredit.getText());

         BigDecimal valeur = creditVercement.subtract(remiseMt);

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

  

        
    }
