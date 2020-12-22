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
import dao.Entity.Fournisseur;
import dao.Entity.Magasin;
import dao.Entity.Sequenceur;
import dao.Entity.TransfertStock;
import dao.Entity.Vehicule;
import dao.Manager.ChauffeurDAO;
import dao.Manager.DepotDAO;
import dao.Manager.DetailTransfertStockDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.MagasinDAO;
import dao.Manager.SequenceurDAO;
import dao.Manager.TransfertStockDAO;
import dao.Manager.VehiculeDAO;
import dao.ManagerImpl.ChauffeurDAOImpl;
import dao.ManagerImpl.DepotDAOImpl;
import dao.ManagerImpl.DetailTransfertStockDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
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
public class ReceptionController implements Initializable {

    @FXML
    private TextField libelleField;
    @FXML
    private ComboBox<String> vehiculeCombo;
    @FXML
    private TableView<DetailTransfertStock> receptionTable;
    @FXML
    private TableColumn<DetailTransfertStock, String> qteReceptionGlobalColumn;
    @FXML
    private TableColumn<DetailTransfertStock, BigDecimal> qteColumn;
    @FXML
    private TableColumn<DetailTransfertStock, BigDecimal> qteCaisseColumn;
    @FXML
    private TableColumn<DetailTransfertStock, BigDecimal> qteTotalColumn;
    @FXML
    private TableColumn<DetailTransfertStock, String> codeArticleColumn;
    @FXML
    private TableColumn<DetailTransfertStock, String> libelleColumn;
    @FXML
    private TableColumn<DetailTransfertStock, Integer> conditionnementColumn;
    @FXML
    private ComboBox<String> chauffeurCombo;
    @FXML
    private ImageView rechercheBtn;
    @FXML
    private DatePicker dateReception;
    @FXML
    private TextArea designationTextArea;
    @FXML
    private ComboBox<String> depotSourCombo;
    @FXML
    private ComboBox<String> magasinSourCombo;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRafraichir;
    @FXML
    private ComboBox<String> typeReceptionCombo;
    @FXML
    private TextField numBlField;
    @FXML
    private ComboBox<String> fourCombo;
    @FXML
    private TextField nReceptionUsineField;
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
    
     private Map<String, Fournisseur> mapFournisseur = new HashMap<>();
    FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
    
        public BigDecimal qte = BigDecimal.ZERO;  
        public BigDecimal qteCaisse = BigDecimal.ZERO;  
        public BigDecimal qteTotal = BigDecimal.ZERO;
        public BigDecimal conditionnement= BigDecimal.ZERO;

            TransfertStock transfertStock = new TransfertStock();
            DetailTransfertStock detailTransfertStock = new DetailTransfertStock();


    DetailTransfertStockDAO detailTransfertStockDAO = new DetailTransfertStockDAOImpl();
    TransfertStockDAO tarnsfertStockDAO = new TransfertStockDAOImpl();

    SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();
    
    
    private final ObservableList<DetailTransfertStock> listeDetailTransfertStock = FXCollections.observableArrayList();
    ObservableList<String> typeReception =FXCollections.observableArrayList("Interne","Usine");

   
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
    
//    void loadDetailTmp(){
//        listeDetailTransfertStock.clear();
//        listeDetailTransfertStock.addAll(detailTransfertStockDAO.findAll());
//        receptionTable.setItems(listeDetailTransfertStock);
//    }
    
   void Incrementation (){
       
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.RECEPTION);
          libelleField.setText(sequenceur.getCode()+" "+(sequenceur.getValeur()+1));

   }
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO


       Incrementation();

             typeReceptionCombo.setItems(typeReception);
       
             List<Depot> listDepot = depotDAO.findByDepot(nav.getUtilisateur().getDepot().getId());

        listDepot.stream().map((depot) -> {
            depotSourCombo.getItems().addAll(depot.getLibelle1());
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
        
         List<Fournisseur> listFournisseur = fournisseurDAO.findAll();

        listFournisseur.stream().map((fournisseur) -> {
            fourCombo.getItems().addAll(fournisseur.getNom());
            return fournisseur;
        }).forEachOrdered((fournisseur) -> {
            mapFournisseur.put(fournisseur.getNom(), fournisseur);
        });
        
        receptionTable.setEditable(true); 
        qteColumn.setEditable(true);
        listeDetailTransfertStock.clear();
        receptionTable.setItems(listeDetailTransfertStock);

        numBlField.setDisable(true);
        nReceptionUsineField.setDisable(true);
        fourCombo.setDisable(true);
        
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
 
                 receptionTable.setItems(listeDetailTransfertStock);
            
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


  void viider(){
 
        receptionTable.getItems().clear();
        dateReception.setValue(null);
        depotSourCombo.getSelectionModel().select(-1);
        magasinSourCombo.getSelectionModel().select(-1);
        vehiculeCombo.getSelectionModel().select(-1);
        chauffeurCombo.getSelectionModel().select(-1);
        designationTextArea.clear();
        
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
    private void editCommitQuantiteColumn(TableColumn.CellEditEvent<DetailTransfertStock, BigDecimal> event) {
        
        ((DetailTransfertStock) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setQuantite(event.getNewValue());
        
                receptionTable.refresh();
                
                qte = qteColumn.getCellData(event.getTablePosition().getRow());

                 if(listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Piece")){
                
                
                conditionnement = new BigDecimal(listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).getArticle().getConditionnement());
                
                         qteTotal = (qte.divide(conditionnement,9,RoundingMode.FLOOR)).add(listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).getQuantiteTotal());
                  

                listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).setQuantite(qte);
                listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).setQuantiteTotal(qteTotal);
                listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).setQuantiteStock(qteTotal);
       
                  setColumnProperties();
                  
                }else if(listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Kg")){
                
                    
                   qteTotal = qte.add(listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).getQuantiteTotal());
                  

                listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).setQuantite(qte);
                listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).setQuantiteTotal(qteTotal);
                listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).setQuantiteStock(qteTotal);
       
                  setColumnProperties();
                }


    }
    
    @FXML
    private void editCommitQuantiteCaisseColumn(TableColumn.CellEditEvent<DetailTransfertStock, BigDecimal> event) {
        
           ((DetailTransfertStock) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setQuantiteCaisse(event.getNewValue());
         
                   receptionTable.refresh();
                
                qteCaisse = qteCaisseColumn.getCellData(event.getTablePosition().getRow());

                if(listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Piece")){
                
                
                qteTotal = listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).getQuantiteTotal().add(qteCaisse);
                  

                listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).setQuantiteCaisse(qteCaisse);
                listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).setQuantiteTotal(qteTotal);
                listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).setQuantiteStock(qteTotal);
       
                  setColumnProperties();
                
                }else if(listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Kg")){
                
                listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).setQuantiteCaisse(BigDecimal.ZERO.setScale(2));
                receptionTable.refresh();
                   nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.ERREUR_SAISIE);
                } 
        

    }

    @FXML
    private void codeEntreeTableKeyPressed(KeyEvent event) {
    }

    @FXML
    private void depotSourComboOnAction(ActionEvent event) {
           try {
            magasinSourCombo.getItems().clear();
            Depot depot  = mapDepot.get(depotSourCombo.getSelectionModel().getSelectedItem());
            List<Magasin> listMagasin = depot.getListMagasin();
            listMagasin.stream().map((magasin) -> {
                magasinSourCombo.getItems().addAll(magasin.getLibelle());
                return magasin;
            }).forEachOrdered((magasin) -> {
                mapMagasin.put(magasin.getLibelle(), magasin);
            });
        } catch (Exception e) {
            
        }
    }

    @FXML
    private void ajouterOnAction(ActionEvent event) throws ParseException {
//         
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
//            alert.setTitle("Confirmation");
//            Optional<ButtonType> result = alert.showAndWait();
//
//            if (result.get() == ButtonType.OK) {
//        
//        if(
//        
//          depotSourCombo.getSelectionModel().getSelectedIndex()== -1 ||
//          magasinSourCombo.getSelectionModel().getSelectedIndex()== -1 ||
//          vehiculeCombo.getSelectionModel().getSelectedIndex()== -1 ||
//          chauffeurCombo.getSelectionModel().getSelectedIndex()== -1 ||
//          libelleField.getText().equals("")||
//          dateReception.getValue() == null||
//          receptionTable.getItems().size() == 0 ){
//      
//         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
//     }
//        else {
//      
//                 LocalDate localDate=dateReception.getValue();
//          Date dateTransfert=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
//          
//          
//          
//             Chauffeur chauffeur  = mapChauffeur.get(chauffeurCombo.getSelectionModel().getSelectedItem());
//             Vehicule vehicule  = mapVehicule.get(vehiculeCombo.getSelectionModel().getSelectedItem());
//             Depot depot  = mapDepot.get(depotSourCombo.getSelectionModel().getSelectedItem());
//             Magasin magasin  = mapMagasin.get(magasinSourCombo.getSelectionModel().getSelectedItem());
//             Fournisseur fournisseur  = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
//             
//               for (int i = 0; i < listeDetailTransfertStock.size(); i++) {
//   
//                             DetailTransfertStock detailTransfertStock = listeDetailTransfertStock.get(i);
//               
//              detailTransfertStock.setQuantiteCaisseRecu(BigDecimal.ZERO);
//              detailTransfertStock.setQuantiteRecu(BigDecimal.ZERO);
//              detailTransfertStock.setUtilisateurCreation(nav.getUtilisateur());
//              detailTransfertStock.setDateCreation(new Date() );
//
//             listeDetailTransfertStock.set(i, detailTransfertStock);
//             
//               }
//             
//             
//        transfertStock.setUtilisateurCreation(nav.getUtilisateur());
//        transfertStock.setChauffeur(chauffeur);
//        transfertStock.setCodeTransfert(libelleField.getText());
//        transfertStock.setDateTransf(dateTransfert);
//        transfertStock.setDepotSource(depotSour);
//        Magasin magasin = magasinDAO.findMagasinByDepotUnique(nav.getUtilisateur().getDepot().getId());
//        transfertStock.setMagasinStock(magasin);
//        transfertStock.setMagasinSource(magasinSour);
//            
//        
//        transfertStock.setDetailTransfertStock(listeDetailTransfertStock);
//        transfertStock.setVehicule(vehicule);
//        transfertStock.setDateCreation(new Date());
//        transfertStock.setStatut(Constantes.ETAT_STATUT_RECEPTION);
//        transfertStock.setDesignation(designationTextArea.getText());
//        
//        tarnsfertStockDAO.add(transfertStock);
//        
//
//        
//           nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MESSAGE_ALERT_AJOUT);  
//           
//
//        Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.RECEPTION);
//           sequenceur.setValeur(sequenceur.getValeur()+1);
//           sequenceurDAO.edit(sequenceur);
//           Incrementation ();
//
//          viider();
//        
//          }
//        
//            }
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
        
          viider();
    }

    @FXML
    private void typeReceptionComboOnAction(ActionEvent event) {
        
        
        
        if (typeReceptionCombo.getSelectionModel().getSelectedItem().equals("Interne")){
        numBlField.setDisable(false);
        fourCombo.setDisable(false);
        nReceptionUsineField.setDisable(true);
        }else if  (typeReceptionCombo.getSelectionModel().getSelectedItem().equals("Usine")){
        
        nReceptionUsineField.setDisable(false);
        numBlField.setDisable(true);
        fourCombo.setDisable(true);
        }
        
    }

    @FXML
    private void fourComboOnAction(ActionEvent event) {
    }




}
