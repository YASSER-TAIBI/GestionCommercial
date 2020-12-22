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
import dao.Entity.Chauffeur;
import dao.Entity.Depot;
import dao.Entity.DetailTransfertStock;
import dao.Entity.Magasin;
import dao.Entity.TransfertStock;
import dao.Entity.Vehicule;
import dao.Manager.DepotDAO;
import dao.Manager.DetailTransfertStockDAO;
import dao.Manager.MagasinDAO;
import dao.Manager.TransfertStockDAO;
import dao.ManagerImpl.DepotDAOImpl;
import dao.ManagerImpl.DetailTransfertStockDAOImpl;
import dao.ManagerImpl.MagasinDAOImpl;
import dao.ManagerImpl.TransfertStockDAOImpl;
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
public class InitialController implements Initializable {

    @FXML
    private TableView<DetailTransfertStock> initialTable;
    @FXML
    private TableColumn<DetailTransfertStock, String> codeArticleColumn;
    @FXML
    private TableColumn<DetailTransfertStock, String> libelleColumn;
    @FXML
    private TableColumn<DetailTransfertStock, String> qteInitialGlobalColumn;
    @FXML
    private TableColumn<DetailTransfertStock, BigDecimal> qteInitialColumn;
    @FXML
    private TableColumn<DetailTransfertStock, BigDecimal> qteInitialCaisseColumn;
    @FXML
    private TableColumn<DetailTransfertStock, Integer> conditionnementColumn;
    @FXML
    private TableColumn<DetailTransfertStock, BigDecimal> qteTotalColumn;
    @FXML
    private ImageView rechercheBtn;
    @FXML
    private DatePicker dateInitial;
    @FXML
    private ComboBox<String> magasinCombo;
    @FXML
    private TextArea designationTextArea;
    @FXML
    private TextField articleRechField;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRafraichir;
    /**
     * Initializes the controller class.
     */
    
    private Map<String, Depot> mapDepot = new HashMap<>();
    DepotDAO depotDAO = new DepotDAOImpl();
    
    private Map<String, Magasin> mapMagasin = new HashMap<>();
    MagasinDAO magasinDAO = new MagasinDAOImpl();
        
         navigation nav = new navigation();
    
        
        public BigDecimal qteInitial = BigDecimal.ZERO;  
        public BigDecimal qteInitialCaisse = BigDecimal.ZERO;  
        public BigDecimal qteInitialTotal = BigDecimal.ZERO;
        public BigDecimal conditionnement= BigDecimal.ZERO;
        
        

            TransfertStock transfertStock = new TransfertStock();
            DetailTransfertStock detailTransfertStock = new DetailTransfertStock();

             
             
    DetailTransfertStockDAO detailTransfertStockDAO = new DetailTransfertStockDAOImpl();
    TransfertStockDAO tarnsfertStockDAO = new TransfertStockDAOImpl();

    
      private final ObservableList<DetailTransfertStock> listeDetailTransfertStock = FXCollections.observableArrayList();
    


    
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
              
           qteInitialColumn.setCellValueFactory(new PropertyValueFactory<DetailTransfertStock, BigDecimal>("quantite"));
           setColumnTextFieldConverter(getConverter(), qteInitialColumn);
           
           qteInitialCaisseColumn.setCellValueFactory(new PropertyValueFactory<DetailTransfertStock, BigDecimal>("quantiteCaisse"));
           setColumnTextFieldConverter(getConverter(), qteInitialCaisseColumn);
           
            qteTotalColumn.setCellValueFactory(new PropertyValueFactory<DetailTransfertStock, BigDecimal>("quantiteTotal"));
           
           
   }
    
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
            List<Magasin> listMagasin = magasinDAO.findMagasinByDepot(nav.getUtilisateur().getDepot().getId());
            listMagasin.stream().map((magasin) -> {
                magasinCombo.getItems().addAll(magasin.getLibelle());
                return magasin;
            }).forEachOrdered((magasin) -> {
                mapMagasin.put(magasin.getLibelle(), magasin);
            });
        
        initialTable.setEditable(true); 

        
    }    

    
     @FXML
    private void editCommitQuantiteColumn(TableColumn.CellEditEvent<DetailTransfertStock, BigDecimal> event) {
        
           ((DetailTransfertStock) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setQuantite(event.getNewValue());
           
                      initialTable.refresh();
              
                qteInitial = qteInitialColumn.getCellData(event.getTablePosition().getRow());
 
                 if(listeDetailTransfertStock.get(initialTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Piece")){
                
                
                conditionnement = new BigDecimal(listeDetailTransfertStock.get(initialTable.getSelectionModel().getSelectedIndex()).getArticle().getConditionnement());
                
                         qteInitialTotal = (qteInitial.divide(conditionnement,9,RoundingMode.FLOOR)).add(listeDetailTransfertStock.get(initialTable.getSelectionModel().getSelectedIndex()).getQuantiteTotal());
                  

                listeDetailTransfertStock.get(initialTable.getSelectionModel().getSelectedIndex()).setQuantite(qteInitial);
                listeDetailTransfertStock.get(initialTable.getSelectionModel().getSelectedIndex()).setQuantiteTotal(qteInitialTotal);
                listeDetailTransfertStock.get(initialTable.getSelectionModel().getSelectedIndex()).setQuantiteStock(qteInitialTotal);
       
                  setColumnProperties();
                  
                }else if(listeDetailTransfertStock.get(initialTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Kg")){
                
                    
                   qteInitialTotal = qteInitial.add(listeDetailTransfertStock.get(initialTable.getSelectionModel().getSelectedIndex()).getQuantiteTotal());
                  

                listeDetailTransfertStock.get(initialTable.getSelectionModel().getSelectedIndex()).setQuantite(qteInitial);
                listeDetailTransfertStock.get(initialTable.getSelectionModel().getSelectedIndex()).setQuantiteTotal(qteInitialTotal);
                listeDetailTransfertStock.get(initialTable.getSelectionModel().getSelectedIndex()).setQuantiteStock(qteInitialTotal);
       
                  setColumnProperties();
                }        
                        
    }

    @FXML
    private void editCommitQuantiteCaisseColumn(TableColumn.CellEditEvent<DetailTransfertStock, BigDecimal> event) {
        
        ((DetailTransfertStock) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setQuantiteCaisse(event.getNewValue());
         
                initialTable.refresh();
                
                qteInitialCaisse = qteInitialCaisseColumn.getCellData(event.getTablePosition().getRow());

                if(listeDetailTransfertStock.get(initialTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Piece")){
                
                
                qteInitialTotal = listeDetailTransfertStock.get(initialTable.getSelectionModel().getSelectedIndex()).getQuantiteTotal().add(qteInitialCaisse);
                  

                listeDetailTransfertStock.get(initialTable.getSelectionModel().getSelectedIndex()).setQuantiteCaisse(qteInitialCaisse);
                listeDetailTransfertStock.get(initialTable.getSelectionModel().getSelectedIndex()).setQuantiteTotal(qteInitialTotal);
                listeDetailTransfertStock.get(initialTable.getSelectionModel().getSelectedIndex()).setQuantiteStock(qteInitialTotal);
                  setColumnProperties();
                
                }else if(listeDetailTransfertStock.get(initialTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Kg")){
                
                listeDetailTransfertStock.get(initialTable.getSelectionModel().getSelectedIndex()).setQuantiteCaisse(BigDecimal.ZERO.setScale(2));
                initialTable.refresh();
                   nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.ERREUR_SAISIE);
                } 
        
        
    }

    void viider(){
 
        initialTable.getItems().clear();
        dateInitial.setValue(null);
        magasinCombo.getSelectionModel().select(-1);
        designationTextArea.clear();
        
    }
    
    @FXML
    private void afficherArt(MouseEvent event) {
    }



    @FXML
    private void rechercheArticle(MouseEvent event) {
        
 
        
             RechercheArticleController root = new RechercheArticleController(Constantes.POUR_RECHERCHER,new Article()) {
            @Override
            public void refresh() {
              
    
            }

            @Override
            public void selectedArticle(ObservableList<Article> list) {
              
         
                for (Article article : list) {

                        Boolean valeur = false;
                    for (int i = 0; i <listeDetailTransfertStock.size(); i++) {
                   DetailTransfertStock detailTransfertStock = listeDetailTransfertStock.get(i);
                        
                   if (article.getId()==detailTransfertStock.getArticle().getId() ){
                   valeur = true;
                   }
                        
                    }
                    if (valeur == false){
                     DetailTransfertStock detailTransfertStock =new DetailTransfertStock();
                     detailTransfertStock.setArticle(article);
                     detailTransfertStock.setTransfertStock(transfertStock);
                     
                    listeDetailTransfertStock.add(detailTransfertStock);
                         }
                }
 
                 initialTable.setItems(listeDetailTransfertStock);
            
            }
            
        };
       
      Image image= new Image(getClass().getResourceAsStream("/Styles/img/logo.png"));
      Stage stage = new Stage(); 
      stage.getIcons().add(image);
      stage.setTitle("Recherche Article");
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
      setColumnProperties();

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

  


    @FXML
    private void rechLibKeyPressed(KeyEvent event) {
        
                        ObservableList<DetailTransfertStock> listeDetailTransfertStock=FXCollections.observableArrayList();
    listeDetailTransfertStock.clear();
   
   listeDetailTransfertStock.addAll(detailTransfertStockDAO.findByRechercheLibelle(articleRechField.getText()));
   
   initialTable.setItems(listeDetailTransfertStock);
        
    }


    @FXML
    private void ajouterOnAction(ActionEvent event) throws ParseException {
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
          if (
              magasinCombo.getSelectionModel().getSelectedIndex()== -1 ||
              dateInitial.getValue()== null ||
              initialTable.getItems().size() == 0 ){

          nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_SELECTIONNER_INITIAL);

        }else{
          
             LocalDate localDate=dateInitial.getValue();
          Date dateInit=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
          
          
           
             Magasin magasin  = mapMagasin.get(magasinCombo.getSelectionModel().getSelectedItem());
           

                      for (int i = 0; i < listeDetailTransfertStock.size(); i++) {
   
                             DetailTransfertStock detailTransfertStock = listeDetailTransfertStock.get(i);
               

              detailTransfertStock.setQuantiteCaisseRecu(BigDecimal.ZERO);
              detailTransfertStock.setQuantiteRecu(BigDecimal.ZERO);
              detailTransfertStock.setUtilisateurCreation(nav.getUtilisateur());
              detailTransfertStock.setDateCreation(new Date() );

             listeDetailTransfertStock.set(i, detailTransfertStock);
             
               }
                  
        transfertStock.setUtilisateurCreation(nav.getUtilisateur());
        transfertStock.setDateTransf(dateInit);
        transfertStock.setMagasinStock(magasin);
        transfertStock.setDetailTransfertStock(listeDetailTransfertStock);
        transfertStock.setDateCreation(new Date());
        transfertStock.setStatut(Constantes.ETAT_STATUT_INITIAL);
        transfertStock.setDesignation(designationTextArea.getText());
        
        tarnsfertStockDAO.add(transfertStock);
        
   
          nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MESSAGE_ALERT_AJOUT);

          viider();
        
        }
            }
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
              viider();
    }

   



}
