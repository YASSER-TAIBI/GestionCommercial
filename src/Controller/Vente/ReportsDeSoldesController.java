/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Vente;

import Utils.Constantes;
import dao.Entity.Compte;
import dao.Entity.DetailCompte;
import dao.Entity.Vendeur;
import dao.Manager.CompteDAO;
import dao.Manager.DetailCompteDAO;
import dao.Manager.VendeurDAO;
import dao.ManagerImpl.CompteDAOImpl;
import dao.ManagerImpl.DetailCompteDAOImpl;
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
import javafx.scene.control.TextArea;
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
public class ReportsDeSoldesController implements Initializable {

    @FXML
    private TableView<DetailCompte>detailCompteTable;
    @FXML
    private TableColumn<DetailCompte, String> compteOperateurColumn;
    @FXML
    private TableColumn<DetailCompte, String> designationColumn;
    @FXML
    private TableColumn<DetailCompte, BigDecimal> montantDebitColumn;
    @FXML
    private TableColumn<DetailCompte, BigDecimal> montantCreditColumn;
    @FXML
    private ImageView videBtn;
    @FXML
    private ImageView ajouterBtn;
    @FXML
    private TextField montantTotalCredit;
    @FXML
    private TextField montantTotalDebit;
    @FXML
    private TextField montantSolde;
    @FXML
    private TextField montantCredit;
    @FXML
    private TextField montantDebit;
    @FXML
    private DatePicker dateOperation;
    @FXML
    private ComboBox<String> operateurVerseCombo;
    @FXML
    private ComboBox<String> compteVerseCombo;
    @FXML
    private ComboBox<String> operateurFournirCombo;
    @FXML
    private ComboBox<String> compteFournirCombo;
    @FXML
    private ComboBox<String> reglementCombo;
    @FXML
    private TextArea designation;
    @FXML
    private DatePicker dateVersement;
    /**
     * Initializes the controller class.
     */
    
         DetailCompteDAO detailCompteDAO = new DetailCompteDAOImpl();
         VendeurDAO vendeurDAO = new VendeurDAOImpl();
         CompteDAO compteDAO = new CompteDAOImpl();
         private Map<String,Compte> mapCompte=new HashMap<>();
         navigation nav = new navigation();
         private final ObservableList<DetailCompte> listeDetailCompte=FXCollections.observableArrayList();
         ObservableList<String> operateurVerse =FXCollections.observableArrayList("Vendeur","Caissier","Banque");
         ObservableList<String> operateurFournir =FXCollections.observableArrayList("Vendeur","Caissier","Banque");
         ObservableList<String> reglement =FXCollections.observableArrayList("Espece","Cheque");
 
 

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         reglementCombo.setItems(reglement);
         operateurVerseCombo.setItems(operateurVerse);
          operateurFournirCombo.setItems(operateurFournir);
             reglementCombo.setDisable(true);
         
    }    

       void setColumnProperties(){

        designationColumn.setCellValueFactory(new PropertyValueFactory<>("designation"));
        montantDebitColumn.setCellValueFactory(new PropertyValueFactory<>("montantDebit"));
        montantCreditColumn.setCellValueFactory(new PropertyValueFactory<>("montantCredit"));
        
        compteOperateurColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCompte, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCompte, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCompte().getLibelle());
            }
        });
           
    }
    
    void loadDetail(){
        
                
         
        Compte compte  = mapCompte.get(compteVerseCombo.getSelectionModel().getSelectedItem());
        
        listeDetailCompte.clear();
        listeDetailCompte.addAll(detailCompteDAO.findFilterByVendeur(compte.getId()));
        detailCompteTable.setItems(listeDetailCompte);
        
    }
    
    
    @FXML
    private void afficherArt(MouseEvent event) {
    }

       private void clear(){

        compteVerseCombo.getSelectionModel().select(-1);
        compteFournirCombo.getSelectionModel().select(-1);
        
        reglementCombo.getSelectionModel().select(-1);
        operateurVerseCombo.getSelectionModel().select(-1);
        operateurFournirCombo.getSelectionModel().select(-1);
        
        montantCredit.clear();
        montantDebit.clear();
        dateOperation.setValue(null);
        dateVersement.setValue(null);
        designation.clear();
        montantDebit.setDisable(false);
        montantCredit.setDisable(false);
        reglementCombo.setDisable(true);
        
//      loadDetail();
    } 
    
    @FXML
    private void vider(MouseEvent event) {
         clear();
    }

    @FXML
    private void ajouter(MouseEvent event) throws ParseException {
        
             if(compteVerseCombo.getSelectionModel().getSelectedIndex()== -1 ||
                compteVerseCombo.getSelectionModel().getSelectedItem().equals("") ||
                compteFournirCombo.getSelectionModel().getSelectedIndex()== -1 ||
                compteFournirCombo.getSelectionModel().getSelectedItem().equals("") ||
                designation.getText().equals("")||
                dateOperation.getValue().equals(null)||
                dateVersement.getValue().equals(null)
             ){
    
         nav.showAlert(Alert.AlertType.ERROR, "Succès", null,Constantes.REMPLIR_CHAMPS);
     }else {

            DetailCompte  detailCompteV = new DetailCompte();
            
       if(montantCredit.getText().equals("")&& montantDebit.getText().equals("") ){
           nav.showAlert(Alert.AlertType.ERROR, "Succès", null,Constantes.SELECTION_MONTANT);
           return ;
           
           }
       
       else if (!montantCredit.getText().equals("")){
         
             LocalDate localDate=dateOperation.getValue();
        Date dateOpera=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
        
          localDate=dateVersement.getValue();
        Date dateVerse=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
        
        Compte compteV  = mapCompte.get(compteVerseCombo.getSelectionModel().getSelectedItem());

        
       if (operateurVerseCombo.getSelectionModel().getSelectedItem().equals(Constantes.TYPE_VENDEUR_CAISSIER)){
       
            detailCompteV.setEtat(reglementCombo.getSelectionModel().getSelectedItem());
       }

            detailCompteV.setDateOperation(dateOpera);
            detailCompteV.setDesignation(designation.getText());
            detailCompteV.setMontantCredit(new BigDecimal(montantCredit.getText()));
            detailCompteV.setMontantDebit(BigDecimal.ZERO);
            
            detailCompteV.setDateCreation(new Date());
            detailCompteV.setUtilisateurCreation(nav.getUtilisateur());
            detailCompteV.setCompte(compteV);
            
                detailCompteDAO.add(detailCompteV);
                
//                    detailCompteV.getCompte().setCreditClient(detailCompteV.getCompte().getCreditClient().subtract(new BigDecimal(montantCredit.getText())));
//                compteDAO.edit(detailCompteV.getCompte());

                         DetailCompte  detailCompteF = new DetailCompte();
                
        Compte compteF  = mapCompte.get(compteFournirCombo.getSelectionModel().getSelectedItem());
        
            detailCompteF.setDateOperation(dateOpera);
            detailCompteF.setDesignation(designation.getText());
            detailCompteF.setMontantCredit(BigDecimal.ZERO);
            detailCompteF.setMontantDebit(new BigDecimal(montantCredit.getText()));
            detailCompteF.setDateCreation(new Date());
            detailCompteF.setUtilisateurCreation(nav.getUtilisateur());
            detailCompteF.setCompte(compteF);
            
                detailCompteDAO.add(detailCompteF);
                
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MESSAGE_ALERT_AJOUT);
        
        
        
       loadDetail();
       setColumnProperties();
       clear();
       
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

       
       
            }else if (!montantDebit.getText().equals("")){

        LocalDate localDate=dateOperation.getValue();
        Date dateOpera=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
        
         localDate=dateVersement.getValue();
        Date dateVerse=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
        
        Compte compte  = mapCompte.get(compteVerseCombo.getSelectionModel().getSelectedItem());
        
        
          if (operateurVerseCombo.getSelectionModel().getSelectedItem().equals(Constantes.TYPE_VENDEUR_CAISSIER)){
       
            detailCompteV.setEtat(reglementCombo.getSelectionModel().getSelectedItem());
       }

            detailCompteV.setDateOperation(dateOpera);
            detailCompteV.setDesignation(designation.getText());
            detailCompteV.setMontantCredit(BigDecimal.ZERO);
            detailCompteV.setMontantDebit(new BigDecimal(montantDebit.getText()));
            detailCompteV.setDateCreation(new Date());
            detailCompteV.setUtilisateurCreation(nav.getUtilisateur());
            detailCompteV.setCompte(compte);
            
            detailCompteDAO.add(detailCompteV);
            
//                 detailCompteV.getCompte().setCreditClient(detailCompteV.getCompte().getCreditClient().add(new BigDecimal(montantDebit.getText())));
//                compteDAO.edit(detailCompteV.getCompte());
            
             DetailCompte  detailCompteF = new DetailCompte();
                
        Compte compteF  = mapCompte.get(compteFournirCombo.getSelectionModel().getSelectedItem());
        
            detailCompteF.setDateOperation(dateOpera);
            detailCompteF.setDesignation(designation.getText());
            detailCompteF.setMontantCredit(new BigDecimal(montantDebit.getText()));
            detailCompteF.setMontantDebit(BigDecimal.ZERO);
            detailCompteF.setDateCreation(new Date());
            detailCompteF.setUtilisateurCreation(nav.getUtilisateur());
            detailCompteF.setCompte(compteF);
            
                detailCompteDAO.add(detailCompteF);

        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MESSAGE_ALERT_AJOUT);
        
       loadDetail();
       setColumnProperties();
       clear();
       
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
        } 
        
    }

    private void operateurComboOnAction(ActionEvent event) {
   
    }

    @FXML
    private void montantCreditOnMouse(MouseEvent event) {
            montantDebit.setDisable(true);
    }

    @FXML
    private void montantDebitOnMouse(MouseEvent event) {
            montantCredit.setDisable(true);
    }

    @FXML
    private void operateurVerseComboOnAction(ActionEvent event) {
             
        compteVerseCombo.getItems().clear();
        
        List<Vendeur> vendeurs = vendeurDAO.findVendeurByDepot(operateurVerseCombo.getSelectionModel().getSelectedItem());

        vendeurs.stream().map((compte) -> {
            compteVerseCombo.getItems().addAll(compte.getCompte().getLibelle());
            return compte;
        }).forEachOrdered((compte) -> {
            mapCompte.put(compte.getCompte().getLibelle(), compte.getCompte());
        });

          if (operateurVerseCombo.getSelectionModel().getSelectedItem().equals(Constantes.TYPE_VENDEUR_CAISSIER)){
           reglementCombo.setDisable(false);
       }
    }

    @FXML
    private void operateurFournirComboOnAction(ActionEvent event) {
        
            compteFournirCombo.getItems().clear();
        
        List<Vendeur> vendeurs = vendeurDAO.findVendeurByDepot(operateurFournirCombo.getSelectionModel().getSelectedItem());

        vendeurs.stream().map((compte) -> {
            compteFournirCombo.getItems().addAll(compte.getCompte().getLibelle());
            return compte;
        }).forEachOrdered((compte) -> {
            mapCompte.put(compte.getCompte().getLibelle(), compte.getCompte());
        
        });
    }
}
