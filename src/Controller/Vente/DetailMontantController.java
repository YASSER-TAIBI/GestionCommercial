/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Vente;

import Utils.Constantes;
import dao.Entity.Compte;
import dao.Entity.DetailCompte;
import dao.Manager.CompteDAO;
import dao.Manager.DetailCompteDAO;
import dao.ManagerImpl.CompteDAOImpl;
import dao.ManagerImpl.DetailCompteDAOImpl;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class DetailMontantController implements Initializable {

    @FXML
    private TableView<DetailCompte> DetailCompteTable;
    @FXML
    private TableColumn<DetailCompte, String> compteVendeurColumn;
    @FXML
    private TableColumn<DetailCompte, String> designationColumn;
    @FXML
    private TableColumn<DetailCompte, BigDecimal> montantDebitColumn;
    @FXML
    private TableColumn<DetailCompte, BigDecimal> montantCreditColumn;
    @FXML
    private ImageView validerBtn;
    @FXML
    private ImageView RefrechBtn;
    @FXML
    private ImageView fermerBtn;
    @FXML
    private ComboBox<String> compteVendeurCombo;
    @FXML
    private DatePicker dateOperation;
    @FXML
    private TextArea designation;
    @FXML
    private TextField montantDebit;
    @FXML
    private TextField montantCredit;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ComboBox<String> etatCombo;

    /**
     * Initializes the controller class.
     */
        DetailCompteDAO detailCompteDAO = new DetailCompteDAOImpl();
        CompteDAO compteDAO = new CompteDAOImpl();
        private Map<String,Compte> mapCompte=new HashMap<>();
        navigation nav = new navigation();
         private final ObservableList<DetailCompte> listeDetailCompte=FXCollections.observableArrayList();
         ObservableList<String> etat =FXCollections.observableArrayList("Credit Client","Avence");

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        etatCombo.setItems(etat);
        
          List<Compte> listCompte=compteDAO.findAll();
        
        listCompte.stream().map((compte) -> {
            compteVendeurCombo.getItems().addAll(compte.getLibelle());
            return compte;
        }).forEachOrdered((compte) -> {
            mapCompte.put(compte.getLibelle(), compte);
        });
        // TODO
    }    

      void setColumnProperties(){
        
    
        
        designationColumn.setCellValueFactory(new PropertyValueFactory<>("designation"));
        montantDebitColumn.setCellValueFactory(new PropertyValueFactory<>("montantDebit"));
        montantCreditColumn.setCellValueFactory(new PropertyValueFactory<>("montantCredit"));
        
        compteVendeurColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCompte, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCompte, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCompte().getLibelle());
            }
        });
           
    }
    
    void loadDetail(){
        
        listeDetailCompte.clear();
        listeDetailCompte.addAll(detailCompteDAO.findAll());
        DetailCompteTable.setItems(listeDetailCompte);
    }
    
    
    
    
    @FXML
    private void afficherArt(MouseEvent event) {
    }

    @FXML
    private void valider(MouseEvent event) throws ParseException {
        
        if(compteVendeurCombo.getSelectionModel().getSelectedIndex()== -1 ||
                compteVendeurCombo.getSelectionModel().getSelectedItem().equals("") ||
                designation.getText().equals("")||
                dateOperation.getValue().equals(null)
             ){
    
         nav.showAlert(Alert.AlertType.ERROR, "Succès", null,Constantes.REMPLIR_CHAMPS);
     }else {

            DetailCompte  detailCompte = new DetailCompte();
            
       if(montantCredit.getText().equals("")&& montantDebit.getText().equals("") ){
           nav.showAlert(Alert.AlertType.ERROR, "Succès", null,Constantes.SELECTION_MONTANT);
           return ;
           }
       
       else if (!montantCredit.getText().equals("")){
         
       
       
        LocalDate localDate=dateOperation.getValue();
        Date dateOpera=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
        
        Compte compte  = mapCompte.get(compteVendeurCombo.getSelectionModel().getSelectedItem());
        
            detailCompte.setDateOperation(dateOpera);
            detailCompte.setDesignation(designation.getText());
            detailCompte.setMontantCredit(new BigDecimal(montantCredit.getText()));
            detailCompte.setMontantDebit(BigDecimal.ZERO);
            detailCompte.setEtat(etatCombo.getSelectionModel().getSelectedItem());
            detailCompte.setDateCreation(new Date());
            detailCompte.setUtilisateurCreation(nav.getUtilisateur());
            detailCompte.setCompte(compte);
            
                detailCompteDAO.add(detailCompte);

        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MESSAGE_ALERT_AJOUT);
        
           clear();
           
       setColumnProperties();
       loadDetail();

            }else if (!montantDebit.getText().equals("")){

        LocalDate localDate=dateOperation.getValue();
        Date dateOpera=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
        
        Compte compte  = mapCompte.get(compteVendeurCombo.getSelectionModel().getSelectedItem());
        
            detailCompte.setDateOperation(dateOpera);
            detailCompte.setDesignation(designation.getText());
            detailCompte.setMontantCredit(BigDecimal.ZERO);
            detailCompte.setEtat(etatCombo.getSelectionModel().getSelectedItem());
            detailCompte.setMontantDebit(new BigDecimal(montantDebit.getText()));
            detailCompte.setDateCreation(new Date());
            detailCompte.setUtilisateurCreation(nav.getUtilisateur());
            detailCompte.setCompte(compte);
            
            detailCompteDAO.add(detailCompte);

        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MESSAGE_ALERT_AJOUT);
        
           clear();
           
       setColumnProperties();
       loadDetail();
    
         
       }

             
        
        } 
    }

    @FXML
    private void refrech(MouseEvent event) {
        clear();
    }

    @FXML
    private void fermer(MouseEvent event) {
        
         Stage stage = (Stage) anchorPane.getScene().getWindow();     
                stage.close();
        
    }

      private void clear(){

        compteVendeurCombo.getSelectionModel().select(-1);
        montantCredit.clear();
        montantDebit.clear();
        dateOperation.setValue(null);
        designation.clear();
        montantDebit.setDisable(false);
        montantCredit.setDisable(false);
        
        loadDetail();
    } 
    
      
    @FXML
    private void montantDebitOnMouse(MouseEvent event) {
             montantCredit.setDisable(true);
    }

    @FXML
    private void montantCreditOnMouse(MouseEvent event) {
             montantDebit.setDisable(true);
    }
    
}
