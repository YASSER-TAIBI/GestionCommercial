/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Vente;

import Controller.GestionTournee.RechercheArticleController;
import Utils.Constantes;
import dao.Entity.Article;
import dao.Entity.Depot;
import dao.Entity.DetailChargeSupp;
import dao.Entity.DetailCompte;
//import com.sun.corba.se.impl.oa.toa.TOA;
import dao.Entity.DetailTournee;
import dao.Entity.DetailTransfertStock;
import dao.Entity.Magasin;
import dao.Entity.PrixVenteDepot;
import dao.Entity.Sequenceur;
import dao.Entity.Tournee;
import dao.Entity.TransfertStock;
import dao.Entity.TypeVente;
import dao.Entity.Vendeur;
import dao.Entity.VenteMixte;
import dao.Manager.CompteDAO;
import dao.Manager.DetailChargeSuppDAO;
import dao.Manager.DetailCompteDAO;
import dao.Manager.DetailTourneeDAO;
import dao.Manager.DetailTransfertStockDAO;
import dao.Manager.DetailVenteMixteDAO;
import dao.Manager.MagasinDAO;
import dao.Manager.PrixVenteDepotDAO;
import dao.Manager.SequenceurDAO;
import dao.Manager.TourneeDAO;
import dao.Manager.TransfertStockDAO;
import dao.Manager.TypeVenteDAO;
import dao.Manager.VendeurDAO;
import dao.Manager.VenteMixteDAO;
import dao.ManagerImpl.CompteDAOImpl;
import dao.ManagerImpl.DetailChargeSuppDAOImpl;
import dao.ManagerImpl.DetailCompteDAOImpl;
import dao.ManagerImpl.DetailTourneeDAOImpl;
import dao.ManagerImpl.DetailTransfertStockDAOImpl;
import dao.ManagerImpl.DetailVenteMixteDAOImpl;
import dao.ManagerImpl.MagasinDAOImpl;
import dao.ManagerImpl.PrixVenteDepotDAOImpl;
import dao.ManagerImpl.SequenceurDAOImpl;
import dao.ManagerImpl.TourneeDAOImpl;
import dao.ManagerImpl.TransfertStockDAOImpl;
import dao.ManagerImpl.TypeVenteDAOImpl;
import dao.ManagerImpl.VendeurDAOImpl;
import dao.ManagerImpl.VenteMixteDAOImpl;
import function.navigation;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Calendar;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;



/**
 * FXML Controller class
 *
 * @author Hp
 */



public class ChargementController implements Initializable {

    @FXML
    private TableView<DetailTournee> detailTourneeTable;
    @FXML
    private TableColumn<DetailTournee, String> codeArticleColumn;
    @FXML
    private TableColumn<DetailTournee, String> libelleColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteChargeColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteChargeSuppColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteInitialColumn;
     @FXML
    private TableColumn<DetailTournee, BigDecimal> qteRecapeColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteChargeComptColumn;
    @FXML
    private TableColumn<DetailTournee, String> qteChargeGlobalColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteChargeCaisseColumn;
    @FXML
    private TableColumn<DetailTournee, String> qteChargeSuppGlobalColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteChargeSuppCaisseColumn;
    @FXML
    private TableColumn<DetailTournee, String> qteChargeComptGlobalColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteChargeComptCaisseColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteTotalChargeColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteTotalColumn;
    @FXML
    private ImageView ajouterArtBtn;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRafraichir;
    
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
    private DatePicker dateChargeSupp;
    @FXML
    private TextField libelleField;
    @FXML
    private TextArea designationTextArea;
    @FXML
    private Label codeerreur;
     @FXML
    private ImageView ajouterChargeBtn;
    /**
     * Initializes the controller class.
     */
 
    TypeVente typeVente;
    Tournee tournee ;
    TourneeDAO tourneeDAO = new TourneeDAOImpl();
    DetailCompteDAO detailCompteDAO = new DetailCompteDAOImpl();
    PrixVenteDepotDAO prixVenteDepotDAO = new PrixVenteDepotDAOImpl();
   
    private Map<String, Vendeur> mapVendeur = new HashMap<>();
    VendeurDAO vendeurDAO = new VendeurDAOImpl();
    TypeVenteDAO typeVenteDAO = new TypeVenteDAOImpl();
    DetailVenteMixteDAO detailVenteMixteDAO = new DetailVenteMixteDAOImpl();
    
    CompteDAO compteDAO = new CompteDAOImpl();
  
     navigation nav = new navigation();
     
     TransfertStock transfertStockCharge = new TransfertStock();
     TransfertStock transfertStockChargeSupp = new TransfertStock();
     TransfertStockDAO tarnsfertStockDAO = new TransfertStockDAOImpl();
     DetailTourneeDAO detailTourneeDAO = new DetailTourneeDAOImpl();
     DetailChargeSuppDAO detailChargeSuppDAO = new DetailChargeSuppDAOImpl();
     VenteMixteDAO venteMixteDAO = new VenteMixteDAOImpl();
     SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();  
     MagasinDAO magasinDAO = new MagasinDAOImpl();
     DetailTransfertStockDAO detailTransfertStockDAO = new DetailTransfertStockDAOImpl();
     
      public ObservableList<DetailTournee> listeDetailTournee = FXCollections.observableArrayList();
      private final ObservableList<DetailTransfertStock> listeDetailTransfertStockCharge = FXCollections.observableArrayList();
      private final ObservableList<DetailTransfertStock> listeDetailTransfertStockChargeSupp = FXCollections.observableArrayList();
      
      private final ObservableList<Tournee> listeTournee = FXCollections.observableArrayList();
   
   
    
    
 

    
  void Incrementation (){
       
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.CHARGEMENT);
          libelleField.setText(sequenceur.getCode()+" "+(sequenceur.getValeur()+1));

          btnAjouter.setDisable(true);
          
   }
    
  
       void loadDetail() {

        listeTournee.clear();
        listeTournee.addAll(tourneeDAO.findTourneeByEtat(Constantes.ETAT_OUVERT,nav.getUtilisateur().getDepot().getId()));
        tourneeTable.setItems(listeTournee);
    }
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        loadDetail();
        setColumnPropertiesTournee();

        Incrementation ();
         
        detailTourneeTable.setEditable(true); 
        qteChargeColumn.setEditable(true);
        qteChargeSuppColumn.setEditable(true);

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
    
    
     void setColumnPropertiesDetailTournee(){
   
            codeArticleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailTournee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailTournee, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getArticle().getCode());
            }
        });
          
            libelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailTournee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailTournee, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getArticle().getLibelle());
            }
        });
           
           qteInitialColumn.setCellValueFactory(new PropertyValueFactory<DetailTournee, BigDecimal>("quantiteTotalInitial"));

           qteRecapeColumn.setCellValueFactory(new PropertyValueFactory<DetailTournee, BigDecimal>("quantiteTotalRecape"));
           
           qteChargeColumn.setCellValueFactory(new PropertyValueFactory<DetailTournee, BigDecimal>("quantiteCharge"));
           setColumnTextFieldConverter(getConverter(), qteChargeColumn);
           
           qteChargeCaisseColumn.setCellValueFactory(new PropertyValueFactory<DetailTournee, BigDecimal>("quantiteChargeCaisse"));
           setColumnTextFieldConverter(getConverter(), qteChargeCaisseColumn);
           
           qteChargeSuppColumn.setCellValueFactory(new PropertyValueFactory<DetailTournee, BigDecimal>("quantiteChargeSupp"));
      
           qteChargeSuppCaisseColumn.setCellValueFactory(new PropertyValueFactory<DetailTournee, BigDecimal>("quantiteChargeSuppCaisse"));
           
           qteChargeComptColumn.setCellValueFactory(new PropertyValueFactory<DetailTournee, BigDecimal>("quantiteChargeCompt"));
           setColumnTextFieldConverter(getConverter(), qteChargeComptColumn);
           
           qteChargeComptCaisseColumn.setCellValueFactory(new PropertyValueFactory<DetailTournee, BigDecimal>("quantiteChargeComptCaisse"));
           setColumnTextFieldConverter(getConverter(), qteChargeComptCaisseColumn);
           
           qteTotalChargeColumn.setCellValueFactory(new PropertyValueFactory<DetailTournee, BigDecimal>("quantiteTotalCharge"));
           
           qteTotalColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailTournee, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailTournee, BigDecimal> param) {
                return new ReadOnlyObjectWrapper(param.getValue().qteTotalInitialCharge());
            }
        });
           
   }

    
    @FXML
    private void afficherArt(MouseEvent event) {
        
                setColumnPropertiesDetailTournee();
        listeDetailTournee.clear();
        
if (tourneeTable.getSelectionModel().getSelectedIndex()!=-1){
    
        tournee = tourneeTable.getSelectionModel().getSelectedItem();

      listeDetailTournee.addAll(detailTourneeDAO.findDetailTourneeByTournee(tournee));
     
//           for (int i = 0; i < listeDetailTournee.size() ; i++) {
//               
//               DetailTournee detailTournee = listeDetailTournee.get(i);
//               
//               detailTournee.setQuantiteFinal(nav.getFinal(detailTournee.getQuantiteInitial(), detailTournee.getQuantiteCharge(), detailTournee.getQuantiteChargeSupp(), detailTournee.getQuantiteRetour(), detailTournee.getQuantiteVente()));
//
//               listeDetailTournee.set(i, detailTournee);
//               
//               detailTourneeDAO.edit(detailTournee);
//                       
//    }

        detailTourneeTable.setItems(listeDetailTournee);

}
 
    }


   void viider(){
    
        detailTourneeTable.getItems().clear();
        designationTextArea.clear();
        dateChargeSupp.setValue(null);
                loadDetail();
        setColumnPropertiesTournee();

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
    private void editCommitQuantiteChargeColumn(CellEditEvent<DetailTournee, BigDecimal> event) {
         
         ((DetailTournee) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setQuantiteCharge(event.getNewValue());
         
      
             detailTourneeTable.refresh();
           
             BigDecimal qteCharge = qteChargeColumn.getCellData(event.getTablePosition().getRow());
                
             listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteCharge(qteCharge);
                
    }

    @FXML
    private void ajouterOnAction(ActionEvent event) {

        
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        if(
       
          libelleField.getText().isEmpty() ||
          tourneeTable.getItems().isEmpty() ||
             detailTourneeTable.getItems().isEmpty()){
              
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
         return;
     }
        else {
      
              tournee=tourneeTable.getSelectionModel().getSelectedItem();

              String CodeCharge= libelleField.getText();
             
              SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
          
              String dateFormat2=dateFormat.format(tournee.getDateTournee());
              
              
            
        tournee.setCodeChargement(CodeCharge+"/"+tournee.getVendeur().getNom()+"/"+dateFormat2);
//        tournee.setDetailTournee(listeDetailTournee);
        tournee.setEtat(Constantes.ETAT_EN_COURS);
        tournee.setDesignation(designationTextArea.getText());
        tourneeDAO.edit(tournee);
                        
//==========================================================================================================================================================================================================================================================
        
           for (int i = 0; i < listeDetailTournee.size(); i++) {
   
               BigDecimal conditionnement = BigDecimal.ZERO;
               
                             DetailTournee detailTournee = listeDetailTournee.get(i);
               
                             if(detailTournee.getQuantiteCharge().compareTo(BigDecimal.ZERO)>0){
                             
                              conditionnement =new  BigDecimal(detailTournee.getArticle().getConditionnement()) ;
                                 
                              DetailTransfertStock detailTransfertStock = new DetailTransfertStock();
                             
                             detailTransfertStock.setArticle(detailTournee.getArticle());
                             detailTransfertStock.setQuantite(detailTournee.getQuantiteCharge());
                             detailTransfertStock.setQuantiteCaisse(detailTournee.getQuantiteChargeCaisse());
                             detailTransfertStock.setQuantiteCaisseRecu(BigDecimal.ZERO);
                             detailTransfertStock.setQuantiteRecu(BigDecimal.ZERO);
                             
                             if (detailTournee.getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Piece")){
                             
                             detailTransfertStock.setQuantiteTotal(detailTournee.getQuantiteChargeCaisse().add(detailTournee.getQuantiteCharge().divide(conditionnement,9,RoundingMode.FLOOR)));
                             detailTransfertStock.setQuantiteStock(detailTournee.getQuantiteChargeCaisse().add(detailTournee.getQuantiteCharge().divide(conditionnement,9,RoundingMode.FLOOR)));
                             
                             }else if (detailTournee.getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Kg")){
                              
                             detailTransfertStock.setQuantiteTotal(detailTournee.getQuantiteCharge());
                             detailTransfertStock.setQuantiteStock(detailTournee.getQuantiteCharge());
                             }
                             detailTransfertStock.setTransfertStock(transfertStockCharge);
                             detailTransfertStock.setUtilisateurCreation(nav.getUtilisateur());
                             detailTransfertStock.setDateCreation(new Date());

             listeDetailTransfertStockCharge.add(detailTransfertStock);
                             }
             
               }
            System.out.println("listeDetailTransfertStockCharge "+listeDetailTransfertStockCharge.size());
           
           for (int i = 0; i < listeDetailTournee.size(); i++) {
   
                 BigDecimal conditionnement = BigDecimal.ZERO;
               
                             DetailTournee detailTournee = listeDetailTournee.get(i);
                             
                             if (detailTournee.getQuantiteChargeSupp().compareTo(BigDecimal.ZERO)>0){
                             
                              conditionnement =new  BigDecimal(detailTournee.getArticle().getConditionnement()) ;
                                 
                            DetailTransfertStock detailTransfertStock = new DetailTransfertStock();
                             
                             detailTransfertStock.setArticle(detailTournee.getArticle());
                             detailTransfertStock.setQuantite(detailTournee.getQuantiteChargeSupp());
                             detailTransfertStock.setQuantiteCaisse(detailTournee.getQuantiteChargeSuppCaisse());
                             detailTransfertStock.setQuantiteCaisseRecu(BigDecimal.ZERO.setScale(2));
                             detailTransfertStock.setQuantiteRecu(BigDecimal.ZERO.setScale(2));
                             
                             if (detailTournee.getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Piece")){
                             
                             detailTransfertStock.setQuantiteTotal(detailTournee.getQuantiteChargeSuppCaisse().add(detailTournee.getQuantiteChargeSupp().divide(conditionnement,9,RoundingMode.FLOOR)));
                             detailTransfertStock.setQuantiteStock(detailTournee.getQuantiteChargeSuppCaisse().add(detailTournee.getQuantiteChargeSupp().divide(conditionnement,9,RoundingMode.FLOOR)));
                             
                             }else if (detailTournee.getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Kg")){
                              
                             detailTransfertStock.setQuantiteTotal(detailTournee.getQuantiteChargeSupp());
                             detailTransfertStock.setQuantiteStock(detailTournee.getQuantiteChargeSupp());
                             }

                             detailTransfertStock.setTransfertStock(transfertStockChargeSupp);
                             detailTransfertStock.setUtilisateurCreation(nav.getUtilisateur());
                             detailTransfertStock.setDateCreation(new Date());

             listeDetailTransfertStockChargeSupp.add(detailTransfertStock);       
                             
                             
                             }
           }

           System.out.println("listeDetailTransfertStockChargeSupp "+listeDetailTransfertStockChargeSupp.size());
           
                             if (listeDetailTransfertStockCharge.size()!=0 ){
                                 
        transfertStockCharge.setUtilisateurCreation(nav.getUtilisateur());
        transfertStockCharge.setCodeTransfert(libelleField.getText());
        transfertStockCharge.setDateTransf(tournee.getDateTournee());
        transfertStockCharge.setMagasinStock(tournee.getMagasin());
        transfertStockCharge.setDetailTransfertStock(listeDetailTransfertStockCharge);
        transfertStockCharge.setDateCreation(new Date());
        transfertStockCharge.setStatut(Constantes.ETAT_STATUT_CHARGEMENT);
        transfertStockCharge.setDesignation(designationTextArea.getText());
        
        tarnsfertStockDAO.add(transfertStockCharge);
                             
        
                             }
                             
                             
                             
                             if (listeDetailTransfertStockChargeSupp.size()!=0 ){
                             
        transfertStockChargeSupp.setUtilisateurCreation(nav.getUtilisateur());
        transfertStockChargeSupp.setCodeTransfert(libelleField.getText());
        transfertStockChargeSupp.setDateTransf(tournee.getDateTournee());
        transfertStockChargeSupp.setMagasinStock(tournee.getMagasin());
        transfertStockChargeSupp.setDetailTransfertStock(listeDetailTransfertStockChargeSupp);
        transfertStockChargeSupp.setDateCreation(new Date());
        transfertStockChargeSupp.setStatut(Constantes.ETAT_STATUT_CHARGEMENT_SUPP);
        transfertStockChargeSupp.setDesignation(designationTextArea.getText());
        
        tarnsfertStockDAO.add(transfertStockChargeSupp);
                             
                             }

//==================================================================================================================================================================================================================================================================   
        
        

           Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.CHARGEMENT);
           sequenceur.setValeur(sequenceur.getValeur()+1);
           sequenceurDAO.edit(sequenceur);
           Incrementation ();
    
             btnAjouter.setDisable(true);
              viider();         

  } 
            }
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
        
            viider();
    }

    @FXML
    private void ajouterArticle(MouseEvent event) {

       if (tourneeTable.getSelectionModel().getSelectedIndex()!=-1){
   
             RechercheArticleController root = new RechercheArticleController(Constantes.POUR_RECHERCHER,new Article()) {
            @Override
            public void refresh() {
              
    
            }

            @Override
            public void selectedArticle(ObservableList<Article> list) {
              
      
           
                for (Article article : list) {

                        Boolean valeur = false;
                    for (int i = 0; i <listeDetailTournee.size(); i++) {
                   DetailTournee detailTournee = listeDetailTournee.get(i);
                        
                   if (article.getId()==detailTournee.getArticle().getId() ){
                   valeur = true;
                   }
                        
                    }
                    if (valeur == false){
                    
                        DetailTournee detailTournee =new DetailTournee();
                     detailTournee.setArticle(article);
                     detailTournee.setTournee(tournee);
                     detailTournee.setTypeVente(tournee.getVendeur().getTypeVente());
                     
                    listeDetailTournee.add(detailTournee);
                }
                }      
                
                       detailTourneeTable.setItems(listeDetailTournee);
            }
            
        };
       
      Image image= new Image(getClass().getResourceAsStream("/Styles/img/logo.png"));
      Stage stage = new Stage(); 
      stage.getIcons().add(image);
      stage.setTitle("Recherche Article");
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
      setColumnPropertiesDetailTournee();
           
    
     }
        else{
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
  }
        
    }

     @FXML
    private void editCommitQuantiteChargeComptColumn(CellEditEvent<DetailTournee, BigDecimal> event) {

        ((DetailTournee) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setQuantiteChargeCompt(event.getNewValue());
          
                BigDecimal qteChargeCompt = qteChargeComptColumn.getCellData(event.getTablePosition().getRow());
                
                detailTourneeTable.refresh();               

               listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteChargeCompt(qteChargeCompt);
        
        
    }


    @FXML
    private void ajouterCharge(MouseEvent event) throws ParseException {
        
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
         if(
       
          libelleField.getText().isEmpty() ||
          tourneeTable.getItems().isEmpty() ||
          detailTourneeTable.getItems().isEmpty()||
          dateChargeSupp.getValue()== null
                 
                 ){
              
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
         return;
     }
        else {
         
                LocalDate localDate=dateChargeSupp.getValue();
              Date dateCharg =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
             
              Magasin magasinStock = magasinDAO.findMagasinByDepotUnique(nav.getUtilisateur().getDepot().getId());
             
              String articleMessage = "";
              
             for (int i = 0; i < listeDetailTournee.size(); i++) {
                 
                  BigDecimal qteInitial = BigDecimal.ZERO;
                  BigDecimal qteCharge = BigDecimal.ZERO;
                  BigDecimal qteChargeCaisse = BigDecimal.ZERO;
                  BigDecimal qteChargeSupp = BigDecimal.ZERO;
                  BigDecimal qteChargeSuppCaisse = BigDecimal.ZERO;
                  BigDecimal qteChargeCompt = BigDecimal.ZERO;
                  BigDecimal qteChargeComptCaisse = BigDecimal.ZERO;
                  BigDecimal qteTotalCharge = BigDecimal.ZERO;
                  BigDecimal qteTotalChargeSupp = BigDecimal.ZERO;
                  BigDecimal qteTotalChargeCompt = BigDecimal.ZERO;
                  BigDecimal qteTotalChargeGlobal = BigDecimal.ZERO;
                  BigDecimal conditionnement = BigDecimal.ZERO;
                 
                 DetailTournee detailTournee = listeDetailTournee.get(i);
                 

                    // Qte Champs & Conditionnement
                   conditionnement =new  BigDecimal(detailTournee.getArticle().getConditionnement()) ;
                   qteInitial = detailTournee.getQuantiteTotalInitial();
                   
                   qteCharge = detailTournee.getQuantiteCharge();
                   qteChargeCaisse = detailTournee.getQuantiteChargeCaisse();
                   
                   qteChargeSupp = detailTournee.getQuantiteChargeSupp();
                   qteChargeSuppCaisse = detailTournee.getQuantiteChargeSuppCaisse();

                   qteChargeCompt = detailTournee.getQuantiteChargeCompt();
                   qteChargeComptCaisse = detailTournee.getQuantiteChargeComptCaisse();
                   
                     if (detailTournee.getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Piece")){
                   
                         
                   // Qte Total Charge & Qte Total Charge Supp & Qte Total Charge Supp & Qte Total Charge Global
                   qteTotalCharge = qteChargeCaisse.add(qteCharge.divide(conditionnement,9,RoundingMode.FLOOR)); 
                   qteTotalChargeSupp = qteChargeSuppCaisse.add(qteChargeSupp.divide(conditionnement,9,RoundingMode.FLOOR));
                   qteTotalChargeCompt = qteChargeComptCaisse.add(qteChargeCompt.divide(conditionnement,9,RoundingMode.FLOOR));
                   qteTotalChargeGlobal =  qteTotalCharge.add(qteTotalChargeSupp).add(qteTotalChargeCompt);
                 
                
            List <Object[]> objectDetailTransfert =detailTransfertStockDAO.findByQteFinal(detailTournee.getArticle().getId(),magasinStock.getId(),nav.getUtilisateur().getDepot().getId());

            System.out.println("objectDetailTransfert "+objectDetailTransfert.size());
         
            BigDecimal Final = BigDecimal.ZERO;
            
            for(int j=0; j<objectDetailTransfert.size(); j++) {

                    Object[] object= objectDetailTransfert.get(j);
                   
              Final = (BigDecimal)object[1]; 
              
            }
                       if(Final.compareTo(qteTotalChargeGlobal)>=0){      
            
                   
                detailTournee.setQuantiteChargeSupp(qteChargeSupp.add(qteChargeCompt));
                detailTournee.setQuantiteChargeSuppCaisse(qteChargeSuppCaisse.add(qteChargeComptCaisse));
                detailTournee.setQuantiteTotalCharge(qteTotalChargeGlobal);
                detailTournee.setQuantiteVente(qteTotalChargeGlobal.add(qteInitial));
                detailTournee.setQuantiteFinal(qteTotalChargeGlobal.add(qteInitial));
                detailTournee.setQuantiteChargeCompt(BigDecimal.ZERO.setScale(2));
                detailTournee.setQuantiteChargeComptCaisse(BigDecimal.ZERO.setScale(2));
                
                listeDetailTournee.set(i,detailTournee);
                             
                  }else{
                       
                    articleMessage = articleMessage+detailTournee.getArticle().getLibelle()+" || "; 
                       
                       }
                     }
           
                
        else if (detailTournee.getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Kg")){
                              
                  
                   // Qte Total Charge & Qte Total Charge Supp & Qte Total Charge Supp & Qte Total Charge Global
                   qteTotalCharge = qteCharge; 
                   qteTotalChargeSupp = qteChargeSupp;
                   qteTotalChargeCompt = qteChargeCompt;
                   qteTotalChargeGlobal =  qteTotalCharge.add(qteTotalChargeSupp).add(qteChargeCompt);
                 
              
                
            List <Object[]> objectDetailTransfert =detailTransfertStockDAO.findByQteFinal(detailTournee.getArticle().getId(),magasinStock.getId(),nav.getUtilisateur().getDepot().getId());

            System.out.println("objectDetailTransfert "+objectDetailTransfert.size());
         
            BigDecimal Final = BigDecimal.ZERO;
            
            for(int j=0; j<objectDetailTransfert.size(); j++) {

                    Object[] object= objectDetailTransfert.get(j);
                   
              Final = (BigDecimal)object[1]; 
              
            }
                       if(Final.compareTo(qteTotalChargeGlobal)>=0){  
                   
                detailTournee.setQuantiteChargeSupp(qteChargeSupp.add(qteChargeCompt));
                detailTournee.setQuantiteChargeSuppCaisse(qteChargeSuppCaisse.add(qteChargeComptCaisse));
                detailTournee.setQuantiteTotalCharge(qteTotalChargeGlobal);
                detailTournee.setQuantiteVente(qteTotalChargeGlobal.add(qteInitial));
                detailTournee.setQuantiteFinal(qteTotalChargeGlobal.add(qteInitial));
                detailTournee.setQuantiteChargeCompt(BigDecimal.ZERO.setScale(2));
                detailTournee.setQuantiteChargeComptCaisse(BigDecimal.ZERO.setScale(2));
                
                listeDetailTournee.set(i,detailTournee);
                
                            }else{
                       
                articleMessage = articleMessage+detailTournee.getArticle().getLibelle()+" || "; 
                       
                       }     
                             }
                     
                     
                       if (detailTournee.getQuantiteChargeCompt().compareTo(BigDecimal.ZERO)!=0 || detailTournee.getQuantiteChargeComptCaisse().compareTo(BigDecimal.ZERO)!=0){
                 
              DetailChargeSupp detailChargeSupp = new DetailChargeSupp();
             
              detailChargeSupp.setDateChargeSupp(dateCharg);
              detailChargeSupp.setDateCreation(new Date());
              detailChargeSupp.setQuantiteChargeSupp(qteChargeCompt);
              detailChargeSupp.setQuantiteChargeSuppCaisse(qteChargeComptCaisse);
              detailChargeSupp.setUtilisateurCreation(nav.getUtilisateur());
              detailChargeSupp.setDetailTournee(detailTournee);
              
              detailChargeSuppDAO.add(detailChargeSupp);
                 
                 }
   
             }

                 if (articleMessage != ""){
                
                   
                   detailTourneeTable.refresh();
            detailTourneeTable.getItems().clear();

        nav.showAlert(Alert.AlertType.WARNING, "Attention", null,"Veuillez s'il vous plaît charger au stock les articles suivant: "+articleMessage);
                    return;
      
               }
 
//#############################################################################################################################################################################################################################################################################################################################################################################################################
          
        tournee=tourneeTable.getSelectionModel().getSelectedItem();
                 
        tournee.setDetailTournee(listeDetailTournee);

        tourneeDAO.edit(tournee);
             
             
             detailTourneeTable.setItems(listeDetailTournee);
              setColumnPropertiesDetailTournee();
              nav.showAlert(Alert.AlertType.CONFIRMATION,"Succès", null,Constantes.MESSAGE_ALERT_CHARGE_SUPP);
               dateChargeSupp.setValue(null);
                 btnAjouter.setDisable(false);
         }
        
            }
    }

     @FXML
    private void editCommitQuantiteChargeCaisseColumn(CellEditEvent<DetailTournee, BigDecimal> event) {
        
         ((DetailTournee) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setQuantiteChargeCaisse(event.getNewValue());
         
      
             detailTourneeTable.refresh();
           
             BigDecimal qteChargeCaisse = qteChargeCaisseColumn.getCellData(event.getTablePosition().getRow());
                
            if(listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Piece")){
             
                 listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteChargeCaisse(qteChargeCaisse);
             
              }else if(listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Kg")){
    
                   listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteChargeCaisse(BigDecimal.ZERO.setScale(2));
                detailTourneeTable.refresh();
                   nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.ERREUR_SAISIE);
                  
              }
             
    }

     @FXML
    private void editCommitQuantiteChargeComptCaisseColumn(CellEditEvent<DetailTournee, BigDecimal> event) {
        
                 ((DetailTournee) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                                .setQuantiteChargeComptCaisse(event.getNewValue());
         
                 
             detailTourneeTable.refresh();
           
             BigDecimal qteChargeComptCaisse = qteChargeComptCaisseColumn.getCellData(event.getTablePosition().getRow());
            
                if(listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Piece")){
             
             listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteChargeComptCaisse(qteChargeComptCaisse);
                
           }else if(listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Kg")){
           
            listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteChargeComptCaisse(BigDecimal.ZERO.setScale(2));
                detailTourneeTable.refresh();
                   nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.ERREUR_SAISIE);
               
           }
    }




}