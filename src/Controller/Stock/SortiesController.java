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
import dao.Entity.Sequenceur;
import dao.Entity.TransfertStock;
import dao.Entity.Vehicule;
import dao.Manager.ChauffeurDAO;
import dao.Manager.DepotDAO;
import dao.Manager.DetailTransfertStockDAO;
import dao.Manager.MagasinDAO;
import dao.Manager.SequenceurDAO;
import dao.Manager.TransfertStockDAO;
import dao.Manager.VehiculeDAO;
import dao.ManagerImpl.ChauffeurDAOImpl;
import dao.ManagerImpl.DepotDAOImpl;
import dao.ManagerImpl.DetailTransfertStockDAOImpl;
import dao.ManagerImpl.MagasinDAOImpl;
import dao.ManagerImpl.SequenceurDAOImpl;
import dao.ManagerImpl.TransfertStockDAOImpl;
import dao.ManagerImpl.VehiculeDAOImpl;
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
public class SortiesController implements Initializable {

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
    private ImageView rechercheBtn;
    @FXML
    private TextField libelleField;
    @FXML
    private DatePicker dateSorties;
    @FXML
    private ComboBox<String> magasinDesCombo;
    @FXML
    private TextArea designationTextArea;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRafraichir;
    
    /**
     * Initializes the controller class.
     */
       private Map<String, Depot> mapDepot = new HashMap<>();
    DepotDAO depotDAO = new DepotDAOImpl();
    
     private Map<String, Vehicule> mapVehicule = new HashMap<>();
    VehiculeDAO vehiculeDAO = new VehiculeDAOImpl();
    
     private Map<String, Magasin> mapMagasin = new HashMap<>();
         navigation nav = new navigation();
         
     private Map<String, Chauffeur> mapChauffeur = new HashMap<>();
    ChauffeurDAO chauffeurDAO = new ChauffeurDAOImpl();
    
        public BigDecimal qte = BigDecimal.ZERO;  
        public BigDecimal qteCaisse = BigDecimal.ZERO;  
        public BigDecimal qteTotal = BigDecimal.ZERO;
        public BigDecimal conditionnement= BigDecimal.ZERO;
  

            TransfertStock transfertStock = new TransfertStock();
            DetailTransfertStock detailTransfertStock = new DetailTransfertStock();

      MagasinDAO magasinDAO = new MagasinDAOImpl();        
             

    DetailTransfertStockDAO detailTransfertStockDAO = new DetailTransfertStockDAOImpl();
    TransfertStockDAO tarnsfertStockDAO = new TransfertStockDAOImpl();
    SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();

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

           qteColumn.setCellValueFactory(new PropertyValueFactory<DetailTransfertStock, BigDecimal>("Quantite"));
           setColumnTextFieldConverter(getConverter(), qteColumn);
           
           qteCaisseColumn.setCellValueFactory(new PropertyValueFactory<DetailTransfertStock, BigDecimal>("QuantiteCaisse"));
           setColumnTextFieldConverter(getConverter(), qteCaisseColumn);
           
           qteTotalColumn.setCellValueFactory(new PropertyValueFactory<DetailTransfertStock, BigDecimal>("quantiteTotal"));

   
   }
    
    void loadDetailTmp(){
        listeDetailTransfertStock.clear();
        listeDetailTransfertStock.addAll(detailTransfertStockDAO.findAll());
        sortiesTable.setItems(listeDetailTransfertStock);
     
    }
    
              void Incrementation (){
       
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.SORTIES);
          libelleField.setText(sequenceur.getCode()+" "+(sequenceur.getValeur()+1));

   }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
             

           Incrementation ();
        
               List<Magasin> listMagasin = nav.getUtilisateur().getDepot().getListMagasin();
            listMagasin.stream().map((magasin) -> {
                magasinDesCombo.getItems().addAll(magasin.getLibelle());
                return magasin;
            }).forEachOrdered((magasin) -> {
                mapMagasin.put(magasin.getLibelle(), magasin);
            });


        
        
        sortiesTable.setEditable(true); 
        qteColumn.setEditable(true);
        listeDetailTransfertStock.clear();
        sortiesTable.setItems(listeDetailTransfertStock);
        libelleField.setDisable(true);
    }    

    @FXML
    private void editCommitQuantiteColumn(TableColumn.CellEditEvent<DetailTransfertStock, BigDecimal> event) {
             
         ((DetailTransfertStock) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setQuantite(event.getNewValue());
        
                sortiesTable.refresh();
                
                qte = qteColumn.getCellData(event.getTablePosition().getRow());

                 if(listeDetailTransfertStock.get(sortiesTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Piece")){
                
                
                conditionnement = new BigDecimal(listeDetailTransfertStock.get(sortiesTable.getSelectionModel().getSelectedIndex()).getArticle().getConditionnement());
                
                         qteTotal = (qte.divide(conditionnement,9,RoundingMode.FLOOR)).add(listeDetailTransfertStock.get(sortiesTable.getSelectionModel().getSelectedIndex()).getQuantiteTotal());
                  

                listeDetailTransfertStock.get(sortiesTable.getSelectionModel().getSelectedIndex()).setQuantite(qte);
                listeDetailTransfertStock.get(sortiesTable.getSelectionModel().getSelectedIndex()).setQuantiteTotal(qteTotal);
                listeDetailTransfertStock.get(sortiesTable.getSelectionModel().getSelectedIndex()).setQuantiteStock(qteTotal);
       
                  setColumnProperties();
                  
                }else if(listeDetailTransfertStock.get(sortiesTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Kg")){
                
                    
                   qteTotal = qte.add(listeDetailTransfertStock.get(sortiesTable.getSelectionModel().getSelectedIndex()).getQuantiteTotal());
                  

                listeDetailTransfertStock.get(sortiesTable.getSelectionModel().getSelectedIndex()).setQuantite(qte);
                listeDetailTransfertStock.get(sortiesTable.getSelectionModel().getSelectedIndex()).setQuantiteTotal(qteTotal);
                listeDetailTransfertStock.get(sortiesTable.getSelectionModel().getSelectedIndex()).setQuantiteStock(qteTotal);
       
                  setColumnProperties();
                }


    }

    @FXML
    private void editCommitQuantiteCaisseColumn(TableColumn.CellEditEvent<DetailTransfertStock, BigDecimal> event) {
       
      ((DetailTransfertStock) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setQuantiteCaisse(event.getNewValue());
         
                   sortiesTable.refresh();
                
                qteCaisse = qteCaisseColumn.getCellData(event.getTablePosition().getRow());

                if(listeDetailTransfertStock.get(sortiesTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Piece")){
                
                
                qteTotal = listeDetailTransfertStock.get(sortiesTable.getSelectionModel().getSelectedIndex()).getQuantiteTotal().add(qteCaisse);
                  

                listeDetailTransfertStock.get(sortiesTable.getSelectionModel().getSelectedIndex()).setQuantiteCaisse(qteCaisse);
                listeDetailTransfertStock.get(sortiesTable.getSelectionModel().getSelectedIndex()).setQuantiteTotal(qteTotal);
                listeDetailTransfertStock.get(sortiesTable.getSelectionModel().getSelectedIndex()).setQuantiteStock(qteTotal);
       
                  setColumnProperties();
                
                }else if(listeDetailTransfertStock.get(sortiesTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Kg")){
                
                listeDetailTransfertStock.get(sortiesTable.getSelectionModel().getSelectedIndex()).setQuantiteCaisse(BigDecimal.ZERO.setScale(2));
                sortiesTable.refresh();
                   nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.ERREUR_SAISIE);
                } 

    }

    @FXML
    private void afficherArt(MouseEvent event) {
    }

    
    

    void vider(){
 
        sortiesTable.getItems().clear();
        dateSorties.setValue(null);
        magasinDesCombo.getSelectionModel().select(-1);
        designationTextArea.clear();
        
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
                     sortiesTable.setItems(listeDetailTransfertStock);
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

    @FXML
    private void codeEntreeTableKeyPressed(KeyEvent event) {
        
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
    private void ajouterOnAction(ActionEvent event) throws ParseException {
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
  if(

          magasinDesCombo.getSelectionModel().getSelectedIndex()== -1 ||
          libelleField.getText().equals("")||
          dateSorties.getValue()== null||
          sortiesTable.getItems().size() == 0 ){
      
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
     }
        else {
      
                 LocalDate localDate=dateSorties.getValue();
          Date dateSorties=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());

             Magasin magasin  = mapMagasin.get(magasinDesCombo.getSelectionModel().getSelectedItem());

             
             String articleMessage = "";
                for (int i = 0; i < listeDetailTransfertStock.size(); i++) {
   
                             DetailTransfertStock detailTransfertStock = listeDetailTransfertStock.get(i);
               
                                List <Object[]> objectDetailTransfert =detailTransfertStockDAO.findByQteFinal(detailTransfertStock.getArticle().getId(),magasin.getId(),nav.getUtilisateur().getDepot().getId());

            System.out.println("objectDetailTransfert "+objectDetailTransfert.size());
         
            BigDecimal Final = BigDecimal.ZERO;
            
            for(int j=0; j<objectDetailTransfert.size(); j++) {

                    Object[] object= objectDetailTransfert.get(j);
                   
              Final = (BigDecimal)object[1]; 
              
            }
                       if(Final.compareTo(detailTransfertStock.getQuantiteTotal())>=0){      
                             
              detailTransfertStock.setQuantiteCaisseRecu(BigDecimal.ZERO);
              detailTransfertStock.setQuantiteRecu(BigDecimal.ZERO);
              detailTransfertStock.setUtilisateurCreation(nav.getUtilisateur());
              detailTransfertStock.setDateCreation(new Date() );

             listeDetailTransfertStock.set(i, detailTransfertStock);
             
                }else{
                       
                    articleMessage = articleMessage+detailTransfertStock.getArticle().getLibelle()+" || "; 
                       
                       }
                     }
              if (articleMessage != ""){
                
                   
                   sortiesTable.refresh();
            sortiesTable.getItems().clear();

        nav.showAlert(Alert.AlertType.WARNING, "Attention", null,"Veuillez s'il vous plaît charger au stock les articles suivant: "+articleMessage);
                    return;
      
               }
             
        transfertStock.setUtilisateurCreation(nav.getUtilisateur());
        transfertStock.setCodeTransfert(libelleField.getText());
        transfertStock.setDateTransf(dateSorties);
        transfertStock.setDepotSource(nav.getUtilisateur().getDepot());
        transfertStock.setMagasinSource(magasin);
        transfertStock.setMagasinStock(magasin);
        transfertStock.setDetailTransfertStock(listeDetailTransfertStock);
        transfertStock.setDateCreation(new Date());
        transfertStock.setAction(false);
        transfertStock.setEtat(Constantes.ETAT_STATUT_ATTENTE);
        transfertStock.setStatut(Constantes.ETAT_STATUT_SORTIES);
        transfertStock.setDesignation(designationTextArea.getText());
        
        tarnsfertStockDAO.add(transfertStock);
 
          transfertStock = new TransfertStock();
          
          nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MESSAGE_ALERT_AJOUT);    


 Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.SORTIES);
           sequenceur.setValeur(sequenceur.getValeur()+1);
           sequenceurDAO.edit(sequenceur);
           Incrementation ();

         vider();

         
         } 
       
            }
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
            vider();
    }




 
  
    
}
