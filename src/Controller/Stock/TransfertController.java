/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Stock;

import Controller.GestionTournee.RechercheArticleController;
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
import dao.Manager.DetailTransfertStockDAO;
import dao.Manager.SequenceurDAO;
import dao.Manager.TransfertStockDAO;
import dao.Manager.DepotDAO;
import dao.Manager.MagasinDAO;
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
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class TransfertController implements Initializable {

    @FXML
    private TextField libelleField;
    @FXML
    private DatePicker dateTransfert;
    @FXML
    private ComboBox<String> vehiculeCombo;
    @FXML
    private TextArea designationTextArea;
    @FXML
    private ComboBox<String> chauffeurCombo;
    @FXML
    private ComboBox<String> transfertCombo;
    @FXML
    private ComboBox<String> depotCombo;
    @FXML
    private ComboBox<String> magasinCombo;
    @FXML
    private Label textDateTransfert;
    @FXML
    private DatePicker dateTransfertSortie;
    @FXML
    private Button btnImprimer;
    
    
    @FXML
    private TableView<DetailTransfertStock> TransfertTable;
    @FXML
    private TableColumn<DetailTransfertStock, String> codeArticleColumn;
     @FXML
    private TableColumn<DetailTransfertStock, String> qteTransfertGlobalColumn;
    @FXML
    private TableColumn<DetailTransfertStock, String> qteTransfertRecuGlobalColumn;
    @FXML
    private TableColumn<DetailTransfertStock, String> libelleColumn;
    @FXML
    private TableColumn<DetailTransfertStock, BigDecimal> qteColumn;
    @FXML
    private TableColumn<DetailTransfertStock, BigDecimal> qteRecuColumn;
    @FXML
    private TableColumn<DetailTransfertStock, BigDecimal> qteCaisseColumn;
    @FXML
    private TableColumn<DetailTransfertStock, BigDecimal> qteCaisseRecuColumn;
    @FXML
    private TableColumn<DetailTransfertStock, Integer> conditionnementColumn;
    @FXML
    private TableColumn<DetailTransfertStock, BigDecimal> qteTotalColumn;
    @FXML
    private TableColumn<DetailTransfertStock, BigDecimal> qteTotalRecuColumn;
    
    @FXML
    private ImageView rechercheBtn;
    @FXML
    private Label rechercheArt;
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
    
    MagasinDAO magasinDAO = new MagasinDAOImpl();
    
        public BigDecimal qte = BigDecimal.ZERO;  
        public BigDecimal qteCaisse = BigDecimal.ZERO;  
        public BigDecimal qteTotal = BigDecimal.ZERO;
        public BigDecimal conditionnement= BigDecimal.ZERO;
        public BigDecimal qteRecu =BigDecimal.ZERO;
        public BigDecimal qteCaisseRecu =BigDecimal.ZERO;

        

            TransfertStock transfertStock = new TransfertStock();
            TransfertStock transfertStockENT = new TransfertStock();
            TransfertStock transfertStockTmp ;
            DetailTransfertStock detailTransfertStock = new DetailTransfertStock();
            

    DetailTransfertStockDAO detailTransfertStockDAO = new DetailTransfertStockDAOImpl();
    TransfertStockDAO tarnsfertStockDAO = new TransfertStockDAOImpl();
    SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();
  
//               Date dateTranfertSortie=null;
    
   ObservableList<String> listTypeTransfert =FXCollections.observableArrayList("Transfert Entrées","Transfert Sorties"); 
   private final ObservableList<DetailTransfertStock> listeDetailTransfertStock = FXCollections.observableArrayList();
   private final ObservableList<DetailTransfertStock> listeDetailTransfertStockEntree = FXCollections.observableArrayList();
    
   
   
   
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

           qteColumn.setCellValueFactory(new PropertyValueFactory<DetailTransfertStock, BigDecimal>("quantite"));
           setColumnTextFieldConverter(getConverter(), qteColumn);
           
           qteRecuColumn.setCellValueFactory(new PropertyValueFactory<DetailTransfertStock, BigDecimal>("quantiteRecu"));
           setColumnTextFieldConverter(getConverter(), qteRecuColumn);
           
           qteCaisseColumn.setCellValueFactory(new PropertyValueFactory<DetailTransfertStock, BigDecimal>("quantiteCaisse"));
           setColumnTextFieldConverter(getConverter(), qteCaisseColumn);
           
           qteCaisseRecuColumn.setCellValueFactory(new PropertyValueFactory<DetailTransfertStock, BigDecimal>("quantiteCaisseRecu"));
           setColumnTextFieldConverter(getConverter(), qteCaisseRecuColumn);
           
           qteTotalColumn.setCellValueFactory(new PropertyValueFactory<DetailTransfertStock, BigDecimal>("quantiteTotal"));
   
           qteTotalRecuColumn.setCellValueFactory(new PropertyValueFactory<DetailTransfertStock, BigDecimal>("quantiteTotalRecu"));
   }
//    
//    void loadDetailTmp(){
//        listeDetailTransfertStock.clear();
//        listeDetailTransfertStock.addAll(detailTransfertStockDAO.findAll());
//        TransfertTable.setItems(listeDetailTransfertStock);
//    }
    
           void Incrementation (){
       
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.TRANSFERT);
          libelleField.setText(sequenceur.getCode()+" "+(sequenceur.getValeur()+1));

   }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           dateTransfert.setVisible(false);
           textDateTransfert.setVisible(false);
        
           rechercheBtn.setVisible(false);
           rechercheArt.setVisible(false);
           
         transfertCombo.setItems(listTypeTransfert);
         
             List<Depot> listDepot = depotDAO.findByDepot(nav.getUtilisateur().getDepot().getId());

        listDepot.stream().map((depot) -> {
            depotCombo.getItems().addAll(depot.getLibelle1());
            return depot;
        }).forEachOrdered((depot) -> {
            mapDepot.put(depot.getLibelle1(), depot);
        });
        
             List<Vehicule> listVehicule = vehiculeDAO.findAll();

        listVehicule.stream().map((vehicule) -> {
            vehiculeCombo.getItems().addAll(vehicule.getMatricule());
            return vehicule;
        }).forEachOrdered((vehicule) -> {
            mapVehicule.put(vehicule.getMatricule(), vehicule);
        });
        
           List<Chauffeur> listChauffeur = chauffeurDAO.findAll();

        listChauffeur.stream().map((chauffeur) -> {
            chauffeurCombo.getItems().addAll(chauffeur.getNom());
            return chauffeur;
        }).forEachOrdered((chauffeur) -> {
            mapChauffeur.put(chauffeur.getNom(), chauffeur);
        });
        
        
        listeDetailTransfertStock.clear();
        TransfertTable.setItems(listeDetailTransfertStock);
        
        libelleField.setDisable(true);
        btnImprimer.setDisable(true);
        
        
    }    



    @FXML
    private void transfertComboOnAction(ActionEvent event) {
           if (transfertCombo.getSelectionModel().getSelectedItem().equals("Transfert Sorties")){
               
               viderSortie();
               
               libelleField.setDisable(true);
               btnImprimer.setDisable(true);
               dateTransfertSortie.setDisable(false);
               depotCombo.setDisable(false);
               magasinCombo.setDisable(false);
               vehiculeCombo.setDisable(false);
               chauffeurCombo.setDisable(false);
               designationTextArea.setDisable(false);
               rechercheBtn.setDisable(false);
               
               TransfertTable.setEditable(true); 
               qteColumn.setEditable(true);
               qteCaisseColumn.setEditable(true);
               qteRecuColumn.setEditable(false);
               qteCaisseRecuColumn.setEditable(false);
               
               rechercheBtn.setVisible(true);
               rechercheArt.setVisible(true);
               
               dateTransfert.setVisible(false);
               textDateTransfert.setVisible(false);
               
//             int Maxid = tarnsfertStockDAO.getMaxIdTransfert();
//        libelleField.setText(nav.generCode(Constantes.TRANSFERT, Maxid));

         Incrementation();
        
        }else {
               
               viderEntree();
               
               libelleField.setDisable(false);
               btnImprimer.setDisable(false);
               dateTransfertSortie.setDisable(true);
               depotCombo.setDisable(true);
               magasinCombo.setDisable(true);
               vehiculeCombo.setDisable(true);
               chauffeurCombo.setDisable(true);
               designationTextArea.setDisable(true);
               rechercheBtn.setDisable(true);
               
               TransfertTable.setEditable(true); 
               qteColumn.setEditable(false);
               qteCaisseColumn.setEditable(false);
               qteRecuColumn.setEditable(true);
               qteCaisseRecuColumn.setEditable(true);
               
               dateTransfert.setVisible(true);
               textDateTransfert.setVisible(true);
               
               rechercheBtn.setVisible(false);
               rechercheArt.setVisible(false);
               
               libelleField.clear();
        }
    }

    @FXML
    private void depotComboOnAction(ActionEvent event) {
        
                try {
            magasinCombo.getItems().clear();
            Depot depot  = mapDepot.get(depotCombo.getSelectionModel().getSelectedItem());
            List<Magasin> listMagasin = depot.getListMagasin();
            listMagasin.stream().map((magasin) -> {
                magasinCombo.getItems().addAll(magasin.getLibelle());
                return magasin;
            }).forEachOrdered((magasin) -> {
                mapMagasin.put(magasin.getLibelle(), magasin);
            });
        } catch (Exception e) {
            
        }
    }


    @FXML
    private void afficherArt(MouseEvent event) {
    }


 
   
    void viderSortie(){
 
        TransfertTable.getItems().clear();

        dateTransfert.setValue(null);
        dateTransfertSortie.setValue(null);
        depotCombo.getSelectionModel().select(-1);
        magasinCombo.getSelectionModel().select(-1);
        vehiculeCombo.getSelectionModel().select(-1);
        chauffeurCombo.getSelectionModel().select(-1);
        designationTextArea.clear();

        
        libelleField.setDisable(true);
        dateTransfertSortie.setDisable(false);
        depotCombo.setDisable(false);
        magasinCombo.setDisable(false);
        vehiculeCombo.setDisable(false);
        chauffeurCombo.setDisable(false);
        designationTextArea.setDisable(false);
        rechercheBtn.setDisable(false);
        
        TransfertTable.setEditable(true); 
        
        qteColumn.setEditable(true);
        qteCaisseColumn.setEditable(true);
        
        qteRecuColumn.setEditable(false);
        qteCaisseRecuColumn.setEditable(false);
               
       
        dateTransfert.setVisible(false);
        textDateTransfert.setVisible(false);

        rechercheBtn.setVisible(true);
        rechercheArt.setVisible(true);
        
    }
    
     void viderEntree(){
 
        TransfertTable.getItems().clear();

        dateTransfert.setValue(null);
        dateTransfertSortie.setValue(null);
        depotCombo.getSelectionModel().select(-1);
        magasinCombo.getSelectionModel().select(-1);
        vehiculeCombo.getSelectionModel().select(-1);
        chauffeurCombo.getSelectionModel().select(-1);
        designationTextArea.clear();
        
        
        
        libelleField.setDisable(false);
        btnImprimer.setDisable(true);
        dateTransfertSortie.setDisable(true);
        depotCombo.setDisable(true);
        magasinCombo.setDisable(true);
        vehiculeCombo.setDisable(true);
        chauffeurCombo.setDisable(true);
        designationTextArea.setDisable(true);
        rechercheBtn.setDisable(true);
               
        TransfertTable.setEditable(true); 
       
        qteColumn.setEditable(false);
        qteCaisseColumn.setEditable(false);
       
        qteRecuColumn.setEditable(true);
        qteCaisseRecuColumn.setEditable(true);
               
        dateTransfert.setVisible(true);
        textDateTransfert.setVisible(true);
        
        rechercheBtn.setVisible(false);
        rechercheArt.setVisible(false);
               
        libelleField.clear();
        
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
               
                 TransfertTable.setItems(listeDetailTransfertStock);
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
    private void codeEntreeTableKeyPressed(KeyEvent event) {
        
        listeDetailTransfertStockEntree.clear();         
        depotCombo.getSelectionModel().select(-1);
        magasinCombo.getSelectionModel().select(-1);
        vehiculeCombo.getSelectionModel().select(-1);
        chauffeurCombo.getSelectionModel().select(-1);
        dateTransfert.setValue(null);
        

        
        if (event.getCode().equals(KeyCode.ENTER))
            {
                
             String  codetransfert = libelleField.getText();
            transfertStockTmp = tarnsfertStockDAO.findTransfertStockByCodeTransfert(codetransfert, nav.getUtilisateur().getDepot().getId()); 
           
            if (transfertStockTmp == null){
            
              nav.showAlert(Alert.AlertType.WARNING, "Attention", null, Constantes.MESSAGE_ALERT_FAUX_CODE);
            
            }else{

                listeDetailTransfertStock.clear();
                
               dateTransfertSortie.setDisable(false);
               depotCombo.setDisable(false);
               magasinCombo.setDisable(false);
               vehiculeCombo.setDisable(false);
               chauffeurCombo.setDisable(false);
               designationTextArea.setDisable(false);
                
               
            
//                dateTranfertSortie=transfertStockTmp.getDateTransf();
             
               
                depotCombo.getSelectionModel().select(transfertStockTmp.getDepotSource().getLibelle1());
                magasinCombo.getSelectionModel().select(transfertStockTmp.getMagasinSource().getLibelle());
                vehiculeCombo.getSelectionModel().select(transfertStockTmp.getVehicule().getMatricule());
                chauffeurCombo.getSelectionModel().select(transfertStockTmp.getChauffeur().getNom());
                designationTextArea.setText(transfertStockTmp.getDesignation());
                
                LocalDate date = new java.util.Date(transfertStockTmp.getDateTransf().getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                dateTransfertSortie.setValue(date);
               
                listeDetailTransfertStock.addAll(transfertStockTmp.getDetailTransfertStock());
                
                for (int i = 0; i < listeDetailTransfertStock.size(); i++) {
                    
                    DetailTransfertStock detailTransfertStock = listeDetailTransfertStock.get(i);
                    
                    DetailTransfertStock detailTransfertStockNew = new DetailTransfertStock();
                    
                    detailTransfertStockNew.setArticle(detailTransfertStock.getArticle());
                    detailTransfertStockNew.setQuantite(detailTransfertStock.getQuantite());
                    detailTransfertStockNew.setQuantiteCaisse(detailTransfertStock.getQuantiteCaisse());
                    detailTransfertStockNew.setTransfertStock(transfertStockENT);
                    detailTransfertStockNew.setUtilisateurCreation(nav.getUtilisateur());
                    detailTransfertStockNew.setDateCreation(new Date());
                    detailTransfertStockNew.setQuantiteTotal(detailTransfertStock.getQuantiteTotal());
                    detailTransfertStockNew.setQuantiteTotalRecu(BigDecimal.ZERO);
                    detailTransfertStockNew.setQuantiteCaisseRecu(BigDecimal.ZERO);
                    detailTransfertStockNew.setQuantiteRecu(BigDecimal.ZERO);
                    
                    listeDetailTransfertStockEntree.add(detailTransfertStockNew);
                }

                TransfertTable.setItems(listeDetailTransfertStockEntree);
                setColumnProperties();
            
            }
            
            }

    }
    
    @FXML
    private void editCommitQuantiteColumn(TableColumn.CellEditEvent<DetailTransfertStock, BigDecimal> event) {
        
                         ((DetailTransfertStock) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setQuantite(event.getNewValue());
        
                TransfertTable.refresh();
                
                qte = qteColumn.getCellData(event.getTablePosition().getRow());

                 if(listeDetailTransfertStock.get(TransfertTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Piece")){
                
                
                conditionnement = new BigDecimal(listeDetailTransfertStock.get(TransfertTable.getSelectionModel().getSelectedIndex()).getArticle().getConditionnement());
                
                         qteTotal = (qte.divide(conditionnement,9,RoundingMode.FLOOR)).add(listeDetailTransfertStock.get(TransfertTable.getSelectionModel().getSelectedIndex()).getQuantiteTotal());
                  

                listeDetailTransfertStock.get(TransfertTable.getSelectionModel().getSelectedIndex()).setQuantite(qte);
                listeDetailTransfertStock.get(TransfertTable.getSelectionModel().getSelectedIndex()).setQuantiteTotal(qteTotal);
                listeDetailTransfertStock.get(TransfertTable.getSelectionModel().getSelectedIndex()).setQuantiteStock(qteTotal);
       
                  setColumnProperties();
                  
                }else if(listeDetailTransfertStock.get(TransfertTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Kg")){
                
                    
                   qteTotal = qte.add(listeDetailTransfertStock.get(TransfertTable.getSelectionModel().getSelectedIndex()).getQuantiteTotal());
                  

                listeDetailTransfertStock.get(TransfertTable.getSelectionModel().getSelectedIndex()).setQuantite(qte);
                listeDetailTransfertStock.get(TransfertTable.getSelectionModel().getSelectedIndex()).setQuantiteTotal(qteTotal);
                listeDetailTransfertStock.get(TransfertTable.getSelectionModel().getSelectedIndex()).setQuantiteStock(qteTotal);
       
                  setColumnProperties();
                }

    }
    
    @FXML
    private void editCommitQuantiteCaisseColumn(TableColumn.CellEditEvent<DetailTransfertStock, BigDecimal> event) {
        
                
         ((DetailTransfertStock) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setQuantiteCaisse(event.getNewValue());
         
                   TransfertTable.refresh();
                
                qteCaisse = qteCaisseColumn.getCellData(event.getTablePosition().getRow());

                if(listeDetailTransfertStock.get(TransfertTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Piece")){
                
                
                qteTotal = listeDetailTransfertStock.get(TransfertTable.getSelectionModel().getSelectedIndex()).getQuantiteTotal().add(qteCaisse);
                  

                listeDetailTransfertStock.get(TransfertTable.getSelectionModel().getSelectedIndex()).setQuantiteCaisse(qteCaisse);
                listeDetailTransfertStock.get(TransfertTable.getSelectionModel().getSelectedIndex()).setQuantiteTotal(qteTotal);
                listeDetailTransfertStock.get(TransfertTable.getSelectionModel().getSelectedIndex()).setQuantiteStock(qteTotal);
       
                  setColumnProperties();
                
                }else if(listeDetailTransfertStock.get(TransfertTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Kg")){
                
                listeDetailTransfertStock.get(TransfertTable.getSelectionModel().getSelectedIndex()).setQuantiteCaisse(BigDecimal.ZERO.setScale(2));
                TransfertTable.refresh();
                   nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.ERREUR_SAISIE);
                } 
        
        
    }

    @FXML
    private void editCommitQuantiteRecuColumn(TableColumn.CellEditEvent<DetailTransfertStock, BigDecimal> event) {
          ((DetailTransfertStock) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setQuantiteRecu(event.getNewValue());
        
                TransfertTable.refresh();
                
                qteRecu = qteRecuColumn.getCellData(event.getTablePosition().getRow());

                 if(listeDetailTransfertStockEntree.get(TransfertTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Piece")){
                
                
                conditionnement = new BigDecimal(listeDetailTransfertStockEntree.get(TransfertTable.getSelectionModel().getSelectedIndex()).getArticle().getConditionnement());
                
                         qteTotal = (qteRecu.divide(conditionnement,9,RoundingMode.FLOOR)).add(listeDetailTransfertStockEntree.get(TransfertTable.getSelectionModel().getSelectedIndex()).getQuantiteTotalRecu());
                  

                listeDetailTransfertStockEntree.get(TransfertTable.getSelectionModel().getSelectedIndex()).setQuantiteRecu(qteRecu);
                listeDetailTransfertStockEntree.get(TransfertTable.getSelectionModel().getSelectedIndex()).setQuantiteTotalRecu(qteTotal.setScale(9));
                listeDetailTransfertStockEntree.get(TransfertTable.getSelectionModel().getSelectedIndex()).setQuantiteStock(qteTotal.setScale(9));
       
                  setColumnProperties();
                  
                }else if(listeDetailTransfertStockEntree.get(TransfertTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Kg")){
                
                    
                   qteTotal = qteRecu.add(listeDetailTransfertStockEntree.get(TransfertTable.getSelectionModel().getSelectedIndex()).getQuantiteTotalRecu());
                  

                listeDetailTransfertStockEntree.get(TransfertTable.getSelectionModel().getSelectedIndex()).setQuantiteRecu(qteRecu);
                listeDetailTransfertStockEntree.get(TransfertTable.getSelectionModel().getSelectedIndex()).setQuantiteTotalRecu(qteTotal.setScale(9));
                listeDetailTransfertStockEntree.get(TransfertTable.getSelectionModel().getSelectedIndex()).setQuantiteStock(qteTotal.setScale(9));
                
                  setColumnProperties();
                }

    }
    
    @FXML
    private void editCommitQuantiteCaisseRecuColumn(TableColumn.CellEditEvent<DetailTransfertStock, BigDecimal> event) {
        
                    ((DetailTransfertStock) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setQuantiteCaisseRecu(event.getNewValue());
         
                   TransfertTable.refresh();
                
                qteCaisseRecu = qteCaisseRecuColumn.getCellData(event.getTablePosition().getRow());

                if(listeDetailTransfertStockEntree.get(TransfertTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Piece")){
                
                
                qteTotal = listeDetailTransfertStockEntree.get(TransfertTable.getSelectionModel().getSelectedIndex()).getQuantiteTotalRecu().add(qteCaisseRecu);
                  

                listeDetailTransfertStockEntree.get(TransfertTable.getSelectionModel().getSelectedIndex()).setQuantiteCaisseRecu(qteCaisseRecu);
                listeDetailTransfertStockEntree.get(TransfertTable.getSelectionModel().getSelectedIndex()).setQuantiteTotalRecu(qteTotal.setScale(9));
                listeDetailTransfertStockEntree.get(TransfertTable.getSelectionModel().getSelectedIndex()).setQuantiteStock(qteTotal.setScale(9));
                  setColumnProperties();
                
                }else if(listeDetailTransfertStockEntree.get(TransfertTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Kg")){
                
                listeDetailTransfertStockEntree.get(TransfertTable.getSelectionModel().getSelectedIndex()).setQuantiteCaisseRecu(BigDecimal.ZERO.setScale(2));
                TransfertTable.refresh();
                   nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.ERREUR_SAISIE);
                } 
        
    }

    @FXML
    private void ajouterOnAction(ActionEvent event) throws ParseException {
        
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
        if (transfertCombo.getSelectionModel().getSelectedIndex()== -1){

          nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_SELECTIONNER_TRANSFERT);

        }

//___________________________________________________________ Transfert Sorties __________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________
          
        else if (transfertCombo.getSelectionModel().getSelectedItem().equals("Transfert Sorties")) {
       
  if(
          depotCombo.getSelectionModel().getSelectedIndex()== -1 ||
          magasinCombo.getSelectionModel().getSelectedIndex()== -1 ||
          vehiculeCombo.getSelectionModel().getSelectedIndex()== -1 ||
          chauffeurCombo.getSelectionModel().getSelectedIndex()== -1 ||
          libelleField.getText().equals("")||
          dateTransfertSortie.getValue()== null ||
          TransfertTable.getItems().size() == 0 ){
      
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
     }
        else {
      
                 LocalDate localDate=dateTransfertSortie.getValue();
               Date dateSortie=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
              

             Chauffeur chauffeur  = mapChauffeur.get(chauffeurCombo.getSelectionModel().getSelectedItem());
             Depot depot  = mapDepot.get(depotCombo.getSelectionModel().getSelectedItem());
             Magasin magasin  = mapMagasin.get(magasinCombo.getSelectionModel().getSelectedItem());
             Vehicule vehicule  = mapVehicule.get(vehiculeCombo.getSelectionModel().getSelectedItem());
             Magasin magasinStock = magasinDAO.findMagasinByDepotUnique(nav.getUtilisateur().getDepot().getId());
             
             String articleMessage = "";
                     for (int i = 0; i < listeDetailTransfertStock.size(); i++) {
   
                             DetailTransfertStock detailTransfertStock = listeDetailTransfertStock.get(i);
                             
            List <Object[]> objectDetailTransfert =detailTransfertStockDAO.findByQteFinal(detailTransfertStock.getArticle().getId(),magasinStock.getId(),nav.getUtilisateur().getDepot().getId());

            System.out.println("objectDetailTransfert "+objectDetailTransfert.size());
         
            BigDecimal Final = BigDecimal.ZERO;
            
            for(int j=0; j<objectDetailTransfert.size(); j++) {

                    Object[] object= objectDetailTransfert.get(j);
                   
              Final = (BigDecimal)object[1]; 
              
            }
                       if(Final.compareTo(detailTransfertStock.getQuantiteTotal())>=0){      
            
              detailTransfertStock.setQuantiteCaisseRecu(BigDecimal.ZERO.setScale(2));
              detailTransfertStock.setQuantiteRecu(BigDecimal.ZERO.setScale(2));
              detailTransfertStock.setUtilisateurCreation(nav.getUtilisateur());
              detailTransfertStock.setDateCreation(new Date() );

             listeDetailTransfertStock.set(i,detailTransfertStock);
             
               }else{
                       
                    articleMessage = articleMessage+detailTransfertStock.getArticle().getLibelle()+" || "; 
                       detailTransfertStock.setQuantite(BigDecimal.ZERO.setScale(2));
                       detailTransfertStock.setQuantiteCaisse(BigDecimal.ZERO.setScale(2));
                       detailTransfertStock.setQuantiteTotal(BigDecimal.ZERO.setScale(2));
                       detailTransfertStock.setQuantiteStock(BigDecimal.ZERO.setScale(2));
                       listeDetailTransfertStock.set(i,detailTransfertStock);
                       }
                     }
              if (articleMessage != ""){
                
                   
                   TransfertTable.refresh();

        nav.showAlert(Alert.AlertType.WARNING, "Attention", null,"Veuillez s'il vous plaît charger au stock les articles suivant: "+articleMessage);
                    return;
      
               }
              
//#############################################################################################################################################################################################################################################################################################################################################################################################################
          
        transfertStock.setUtilisateurCreation(nav.getUtilisateur());
        transfertStock.setChauffeur(chauffeur);
        transfertStock.setCodeTransfert(libelleField.getText());
        transfertStock.setDateTransf(dateSortie);
        transfertStock.setDepotSource(nav.getUtilisateur().getDepot());
        transfertStock.setMagasinSource(magasinStock);
        transfertStock.setMagasinStock(magasinStock);
        transfertStock.setDepotDestination(depot);
        transfertStock.setMagasinDestination(magasin);
        transfertStock.setDetailTransfertStock(listeDetailTransfertStock);
        transfertStock.setVehicule(vehicule);
        transfertStock.setDesignation(designationTextArea.getText());
        transfertStock.setDateCreation(new Date());
        transfertStock.setStatut(Constantes.ETAT_STATUT_TRANSFERT_SORTIES);
        transfertStock.setEtat(Constantes.ETAT_ENVOYER);
        
        tarnsfertStockDAO.add(transfertStock);
        
      transfertStock = new TransfertStock();
        
          nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MESSAGE_ALERT_AJOUT);  
          
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.TRANSFERT);
           sequenceur.setValeur(sequenceur.getValeur()+1);
           sequenceurDAO.edit(sequenceur);
           Incrementation ();

        
          viderSortie();
          
          }
  } 
 
//___________________________________________________________ Transfert Entrées ___________________________________________________________________________________________________________________________________________________________________________________________________________________________
        
        else if  (transfertCombo.getSelectionModel().getSelectedItem().equals("Transfert Entrées")) {
                
              if(
          depotCombo.getSelectionModel().getSelectedIndex()== -1 ||
          magasinCombo.getSelectionModel().getSelectedIndex()== -1 ||
          vehiculeCombo.getSelectionModel().getSelectedIndex()== -1 ||
          chauffeurCombo.getSelectionModel().getSelectedIndex()== -1 ||
          libelleField.getText().equals("")||
          dateTransfert.getValue()== null ||
          dateTransfertSortie.getValue()== null ||
          TransfertTable.getItems().size() == 0 ){
      
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
         
     }else{
         
             LocalDate localDate=dateTransfert.getValue();
             Date dateTransfEntree=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
               
             Chauffeur chauffeur  = mapChauffeur.get(chauffeurCombo.getSelectionModel().getSelectedItem());
             Depot depot  = transfertStockTmp.getDepotDestination();
             Magasin magasin  = transfertStockTmp.getMagasinDestination();
             Vehicule vehicule  = mapVehicule.get(vehiculeCombo.getSelectionModel().getSelectedItem());
                  

             boolean valeur = false;
        for (int i = 0; i < listeDetailTransfertStockEntree.size(); i++) {
   
                             DetailTransfertStock detailTransfertStock = listeDetailTransfertStockEntree.get(i);
                             

                             
                             if (detailTransfertStock.getQuantiteTotal().compareTo(detailTransfertStock.getQuantiteTotalRecu())!=0){
                             detailTransfertStock.setDateCreation(new Date());
                             detailTransfertStock.setUtilisateurCreation(nav.getUtilisateur());
                             
                             listeDetailTransfertStockEntree.set(i,detailTransfertStock);
                             valeur = true;
                             break;
                             
                             }else{
                             detailTransfertStock.setDateCreation(new Date());
                             detailTransfertStock.setUtilisateurCreation(nav.getUtilisateur());

                             listeDetailTransfertStockEntree.set(i,detailTransfertStock);
                             }
               }

        
        if(valeur == true){
        
        transfertStockENT.setUtilisateurCreation(nav.getUtilisateur());
        transfertStockENT.setChauffeur(chauffeur);
        transfertStockENT.setDepotSource(depot);
        transfertStockENT.setMagasinSource(magasin);
        
        Magasin magasinDes = magasinDAO.findMagasinByDepotUnique(nav.getUtilisateur().getDepot().getId());
        transfertStockENT.setMagasinStock(magasinDes);
        transfertStockENT.setDateTransf(dateTransfEntree);
        transfertStockENT.setVehicule(vehicule);
        transfertStockENT.setCodeTransfert(libelleField.getText().toUpperCase());
        transfertStockENT.setDetailTransfertStock(listeDetailTransfertStockEntree);
        transfertStockENT.setStatut(Constantes.ETAT_STATUT_ATTENTE);
        transfertStockENT.setDesignation(designationTextArea.getText());
        transfertStockENT.setDateCreation(new Date());
        
        tarnsfertStockDAO.add(transfertStockENT);
        
          transfertStockENT = new TransfertStock();
          
//######################################################## Modification d'etat de transfert ##########################################################################################################################################
        
        transfertStockTmp.setEtat(Constantes.ETAT_EN_COURS);
        transfertStockTmp.setDateMaj(new Date());
        transfertStockTmp.setUtilisateurMAJ(nav.getUtilisateur());
        
        tarnsfertStockDAO.edit(transfertStockTmp);
        
        
        
        
        }else{
        transfertStockENT.setUtilisateurCreation(nav.getUtilisateur());
        transfertStockENT.setChauffeur(chauffeur);
        transfertStockENT.setDepotSource(depot);
        transfertStockENT.setMagasinSource(magasin);
        
        Magasin magasinDes = magasinDAO.findMagasinByDepotUnique(nav.getUtilisateur().getDepot().getId());
        transfertStockENT.setMagasinStock(magasinDes);
        transfertStockENT.setDateTransf(dateTransfEntree);
        transfertStockENT.setVehicule(vehicule);
        transfertStockENT.setCodeTransfert(libelleField.getText().toUpperCase());
        transfertStockENT.setDetailTransfertStock(listeDetailTransfertStockEntree);
        transfertStockENT.setStatut(Constantes.ETAT_STATUT_TRANSFERT_ENTREES);
        transfertStockENT.setDesignation(designationTextArea.getText());
        transfertStockENT.setDateCreation(new Date());
        
        tarnsfertStockDAO.add(transfertStockENT);
        
          transfertStockENT = new TransfertStock();
          
//######################################################## Modification d'etat de transfert ##########################################################################################################################################
        
        transfertStockTmp.setEtat(Constantes.ETAT_RECU);
        transfertStockTmp.setDateMaj(new Date());
        transfertStockTmp.setUtilisateurMAJ(nav.getUtilisateur());
        
        tarnsfertStockDAO.edit(transfertStockTmp);
        
        }

        
        
         nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MESSAGE_ALERT_AJOUT);  
         
          viderEntree();
          
               libelleField.setDisable(false);
               dateTransfertSortie.setDisable(true);
               depotCombo.setDisable(true);
               magasinCombo.setDisable(true);
               vehiculeCombo.setDisable(true);
               chauffeurCombo.setDisable(true);
               designationTextArea.setDisable(true);
               rechercheBtn.setDisable(true);
               
               TransfertTable.setEditable(true); 
               qteColumn.setEditable(false);
               qteCaisseColumn.setEditable(false);
               qteRecuColumn.setEditable(true);
               qteCaisseRecuColumn.setEditable(true);
               
               dateTransfert.setVisible(true);
               textDateTransfert.setVisible(true);
               
               libelleField.clear();
              
          
        }
        }     
            }
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
    }

    @FXML
    private void imprimerOnAction(ActionEvent event) throws ParseException {
        
          if(
          TransfertTable.getItems().size() == 0 ){
      
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
         
     }else{
        
                              try {
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(TransfertController.class.getResource(nav.getiReportStockEnAttente()));

                    LocalDate localDate=dateTransfertSortie.getValue();
            
          Date dateSortie=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());                    
          
            Depot depot = mapDepot.get(depotCombo.getSelectionModel().getSelectedItem());
            
                 Magasin magasin = mapMagasin.get(magasinCombo.getSelectionModel().getSelectedItem());

             Vehicule vehicule = mapVehicule.get(vehiculeCombo.getSelectionModel().getSelectedItem());
                 
                  Chauffeur chauffeur = mapChauffeur.get(chauffeurCombo.getSelectionModel().getSelectedItem());
          
                  
            para.put("codeTransfert", libelleField.getText().toUpperCase());     
            para.put("dateTransfert", dateSortie);
            para.put("depot",depot.getLibelle1());
            para.put("magasin",magasin.getLibelle());
            para.put("chauffeur",chauffeur.getNom());
            para.put("vehicule",vehicule.getNom());

             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeDetailTransfertStockEntree));
               JasperViewer.viewReport(jp, false);
            
        } catch (JRException ex) {
            Logger.getLogger(TransfertController.class.getName()).log(Level.SEVERE, null, ex);
        }  
          }
    }


}
