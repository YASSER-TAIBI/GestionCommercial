/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Stock;

import Controller.GestionTournee.RechercheArticleController;
import static Controller.Stock.ReceptionController.setColumnTextFieldConverter;
import Utils.Constantes;
import dao.Entity.Article;
import dao.Entity.DeclarationReception;
import dao.Entity.DetailTransfertStock;
import dao.Entity.Magasin;
import dao.Entity.Sequenceur;
import dao.Entity.TransfertStock;
import dao.Entity.Ville;
import dao.Manager.DeclarationReceptionDAO;
import dao.Manager.DetailTransfertStockDAO;
import dao.Manager.MagasinDAO;
import dao.Manager.SequenceurDAO;
import dao.Manager.TransfertStockDAO;
import dao.Manager.VilleDAO;
import dao.ManagerImpl.DeclarationReceptionDAOImpl;
import dao.ManagerImpl.DetailTransfertStockDAOImpl;
import dao.ManagerImpl.MagasinDAOImpl;
import dao.ManagerImpl.SequenceurDAOImpl;
import dao.ManagerImpl.TransfertStockDAOImpl;
import dao.ManagerImpl.VilleDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ReceptionEtrangereController implements Initializable {

    @FXML
    private TextField libelleField;
    @FXML
    private DatePicker dateReception;
    @FXML
    private TableView<DetailTransfertStock> receptionTable;
    @FXML
    private TableColumn<DetailTransfertStock, String> codeArticleColumn;
    @FXML
    private TableColumn<DetailTransfertStock, String> libelleColumn;
    @FXML
    private TableColumn<DetailTransfertStock, String> qteReceptionGlobalColumn;
    @FXML
    private TableColumn<DetailTransfertStock, BigDecimal> qteColumn;
    @FXML
    private TableColumn<DetailTransfertStock, BigDecimal> qteCaisseColumn;
    @FXML
    private TableColumn<DetailTransfertStock, Integer> conditionnementColumn;
    @FXML
    private TableColumn<DetailTransfertStock, BigDecimal> qteTotalColumn;
    @FXML
    private TableColumn<DetailTransfertStock, String> qteReceptionRecuGlobalColumn;
    @FXML
    private TableColumn<DetailTransfertStock, BigDecimal> qteRecuColumn;
    @FXML
    private TableColumn<DetailTransfertStock, BigDecimal> qteCaisseRecuColumn;
    @FXML
    private TableColumn<DetailTransfertStock, BigDecimal> qteTotalRecuColumn;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRafraichir;
    @FXML
    private TableView<DeclarationReception> declarationReceptionTable;
    @FXML
    private TableColumn<DeclarationReception, String> numDossierColumn;
    @FXML
    private TableColumn<DeclarationReception, Date> dateDeclarationColumn;


    /**
     * Initializes the controller class.
     */
    
    private final ObservableList<DeclarationReception> listeDeclarationReception = FXCollections.observableArrayList();
    DeclarationReceptionDAO declarationReceptiondao =new DeclarationReceptionDAOImpl();
      
    private final ObservableList<DetailTransfertStock> listeDetailTransfertStock = FXCollections.observableArrayList();
    
    navigation nav = new navigation();

     SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();
     MagasinDAO magasinDAO = new MagasinDAOImpl();
      DetailTransfertStockDAO detailTransfertStockDAO = new DetailTransfertStockDAOImpl();
       TransfertStockDAO transfertStockDAO = new TransfertStockDAOImpl();
     
        public BigDecimal qte = BigDecimal.ZERO;  
        public BigDecimal qteCaisse = BigDecimal.ZERO;  
        public BigDecimal qteTotal = BigDecimal.ZERO;
        public BigDecimal conditionnement= BigDecimal.ZERO;
        public BigDecimal qteRecu =BigDecimal.ZERO;
        public BigDecimal qteCaisseRecu =BigDecimal.ZERO;
    
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
   
        loadDetail();
        setColumnPropertiesDeclaration();

        
         receptionTable.setEditable(true); 
        qteColumn.setEditable(true);
//        listeDetailTransfertStock.clear();
//        receptionTable.setItems(listeDetailTransfertStock);
    }    


       void loadDetail() {

        listeDeclarationReception.clear();
        listeDeclarationReception.addAll(declarationReceptiondao.findByDeclarationReceptionAndEtat(nav.getUtilisateur().getDepot().getId(),Constantes.ETAT_STATUT_LANCE));
        declarationReceptionTable.setItems(listeDeclarationReception);
    }

    void setColumnPropertiesDeclaration() {

        numDossierColumn.setCellValueFactory(new PropertyValueFactory<>("numDossier"));
        

        dateDeclarationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DeclarationReception, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<DeclarationReception, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDateDeclaration());
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

           
           qteTotalColumn.setCellValueFactory(new PropertyValueFactory<DetailTransfertStock, BigDecimal>("quantiteTotal"));

           
           qteRecuColumn.setCellValueFactory(new PropertyValueFactory<DetailTransfertStock, BigDecimal>("quantiteRecu"));
           setColumnTextFieldConverter(getConverter(), qteRecuColumn);
           
           qteCaisseRecuColumn.setCellValueFactory(new PropertyValueFactory<DetailTransfertStock, BigDecimal>("QuantiteCaisseRecu"));
           setColumnTextFieldConverter(getConverter(), qteCaisseRecuColumn);
           
           qteTotalRecuColumn.setCellValueFactory(new PropertyValueFactory<DetailTransfertStock, BigDecimal>("quantiteTotalRecu"));
   
   }
    
     public static void setColumnTextFieldConverter(StringConverter converter, TableColumn... columns) {

        for (TableColumn tableColumn : columns) {

            tableColumn.setCellFactory(TextFieldTableCell.forTableColumn(converter));

        }
    }

    private StringConverter getConverter() {
        StringConverter<BigDecimal> converter = new StringConverter<BigDecimal>(){
            @Override
            public BigDecimal fromString(String string) {

                try {
                    BigDecimal valeur;
                    valeur= new BigDecimal(string);
                    
                    return valeur;
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            public String toString(BigDecimal object) {

                if (object == null) {
                    return null;
                }
                return  String.valueOf(object);
            }
        };

        return converter;
    }
    
     void viider(){
 
        receptionTable.getItems().clear();
        dateReception.setValue(null);
        libelleField.clear();
        
        loadDetail();
        setColumnPropertiesDeclaration();
    }
    
    @FXML
    private void codeEntreeTableKeyPressed(KeyEvent event) {
    }


    @FXML
    private void afficherArt(MouseEvent event) {
        
          setColumnProperties();
        listeDetailTransfertStock.clear();
        
if (declarationReceptionTable.getSelectionModel().getSelectedIndex()!=-1){
    
        DeclarationReception declarationReception = declarationReceptionTable.getSelectionModel().getSelectedItem();
        
      listeDetailTransfertStock.addAll(detailTransfertStockDAO.findByTransfertStock(declarationReception.getTransfertStock().getId()));
     

        receptionTable.setItems(listeDetailTransfertStock);
        
        libelleField.setText(declarationReception.getTransfertStock().getCodeTransfert());
       
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
          dateReception.getValue() == null||
          declarationReceptionTable.getSelectionModel().getSelectedIndex()==-1||
          receptionTable.getItems().size() == 0 ){
      
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
     }
        else {
      
                 LocalDate localDate=dateReception.getValue();
          Date dateTransfert=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());

          DeclarationReception declarationReception = declarationReceptionTable.getSelectionModel().getSelectedItem();
          
               boolean valeur = false;
               for (int i = 0; i < listeDetailTransfertStock.size(); i++) {
   
              DetailTransfertStock detailTransfertStock = listeDetailTransfertStock.get(i);
               
                if (detailTransfertStock.getQuantiteTotal().compareTo(detailTransfertStock.getQuantiteTotalRecu())!=0){
                    
              detailTransfertStock.setDateCreation(new Date() );

             detailTransfertStockDAO.edit(detailTransfertStock);
                valeur = true;
                             break;
               }else{
                detailTransfertStock.setDateCreation(new Date() );

                detailTransfertStockDAO.edit(detailTransfertStock);
                             }
               }
             
                if(valeur == true){
        
        declarationReception.getTransfertStock().setDateTransf(dateTransfert);

        declarationReception.getTransfertStock().setMagasinStock(declarationReception.getMagasin());
        declarationReception.getTransfertStock().setMagasinSource(declarationReception.getMagasin());
        declarationReception.getTransfertStock().setDepotSource(nav.getUtilisateur().getDepot());
        declarationReception.getTransfertStock().setStatut(Constantes.ETAT_STATUT_ATTENTE);
        declarationReception.getTransfertStock().setDateCreation(new Date());
         
        transfertStockDAO.edit(declarationReception.getTransfertStock());
        
//        transfertStock.getDeclarationReception().setEtat(Constantes.ETAT_STATUT_VALIDER);
        
        declarationReception.setEtat(Constantes.ETAT_STATUT_VALIDER);
        declarationReceptiondao.edit(declarationReception);
        
        
        
           nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MESSAGE_ALERT_AJOUT);  
 
          viider();
        
          }else{
                    
                      declarationReception.getTransfertStock().setDateTransf(dateTransfert);


        declarationReception.getTransfertStock().setMagasinStock(declarationReception.getMagasin());
        declarationReception.getTransfertStock().setMagasinSource(declarationReception.getMagasin());
        declarationReception.getTransfertStock().setDateCreation(new Date());
        declarationReception.getTransfertStock().setDepotSource(nav.getUtilisateur().getDepot());
        transfertStockDAO.edit(declarationReception.getTransfertStock());
        
//        transfertStock.getDeclarationReception().setEtat(Constantes.ETAT_STATUT_VALIDER);
        
        declarationReception.setEtat(Constantes.ETAT_STATUT_VALIDER);
        declarationReceptiondao.edit(declarationReception);
        
        
        
           nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MESSAGE_ALERT_AJOUT);  
 
          viider();
                    
                }
            }
            }
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
        
        viider();
    }

    @FXML
    private void editCommitQuantiteRecuColumn(TableColumn.CellEditEvent<DetailTransfertStock, BigDecimal> event) {
        
             ((DetailTransfertStock) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setQuantiteRecu(event.getNewValue());
        
                receptionTable.refresh();
                
                qteRecu = qteRecuColumn.getCellData(event.getTablePosition().getRow());

                 if(listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Piece")){
                
                
                conditionnement = new BigDecimal(listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).getArticle().getConditionnement());
                
                         qteTotal = (qteRecu.divide(conditionnement,9,RoundingMode.FLOOR)).add(listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).getQuantiteTotalRecu());
                  

                listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).setQuantiteRecu(qteRecu);
                listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).setQuantiteTotalRecu(qteTotal.setScale(9));
       
                  setColumnProperties();
                  
                }else if(listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Kg")){
                
                    
                   qteTotal = qteRecu.add(listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).getQuantiteTotalRecu());
                  

                listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).setQuantiteRecu(qteRecu);
                listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).setQuantiteTotalRecu(qteTotal.setScale(9));
       
                  setColumnProperties();
                }
        
    }

    @FXML
    private void editCommitQuantiteCaisseRecuColumn(TableColumn.CellEditEvent<DetailTransfertStock, BigDecimal> event) {
        
            ((DetailTransfertStock) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setQuantiteCaisseRecu(event.getNewValue());
         
                   receptionTable.refresh();
                
                qteCaisseRecu = qteCaisseRecuColumn.getCellData(event.getTablePosition().getRow());

                if(listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Piece")){
                
                
                qteTotal = listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).getQuantiteTotalRecu().add(qteCaisseRecu);
                  

                listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).setQuantiteCaisseRecu(qteCaisseRecu);
                listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).setQuantiteTotalRecu(qteTotal.setScale(9));
       
                  setColumnProperties();
                
                }else if(listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Kg")){
                
                listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).setQuantiteCaisseRecu(BigDecimal.ZERO.setScale(2));
                receptionTable.refresh();
                   nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.ERREUR_SAISIE);
                } 
        
    }

    @FXML
    private void afficherArtTransf(MouseEvent event) {
    }
    
}
