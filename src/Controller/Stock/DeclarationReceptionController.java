/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Stock;

import Controller.GestionTournee.RechercheArticleController;
import static Controller.Stock.ReceptionController.setColumnTextFieldConverter;
import static Controller.Stock.ReceptionEtrangereController.setColumnTextFieldConverter;
import Utils.Constantes;
import dao.Entity.Article;
import dao.Entity.Banque;
import dao.Entity.Chauffeur;
import dao.Entity.DeclarationReception;
import dao.Entity.Depot;
import dao.Entity.DetailTransfertStock;
import dao.Entity.Fournisseur;
import dao.Entity.Magasin;
import dao.Entity.Sequenceur;
import dao.Entity.TransfertStock;
import dao.Entity.Utilisateur;
import dao.Entity.Vehicule;
import dao.Manager.ChauffeurDAO;
import dao.Manager.DeclarationReceptionDAO;
import dao.Manager.DepotDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.MagasinDAO;
import dao.Manager.SequenceurDAO;
import dao.Manager.TransfertStockDAO;
import dao.Manager.UtilisateurDAO;
import dao.Manager.VehiculeDAO;
import dao.ManagerImpl.ChauffeurDAOImpl;
import dao.ManagerImpl.DeclarationReceptionDAOImpl;
import dao.ManagerImpl.DepotDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.MagasinDAOImpl;
import dao.ManagerImpl.SequenceurDAOImpl;
import dao.ManagerImpl.TransfertStockDAOImpl;
import dao.ManagerImpl.UtilisateurDAOImpl;
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
public class DeclarationReceptionController implements Initializable {

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
    @FXML
    private TableColumn<DeclarationReception, String> depotColumn;
    @FXML
    private TableColumn<DeclarationReception, String> magasinColumn;
    @FXML
    private ComboBox<String> depotCombo;
    @FXML
    private DatePicker dateDeclaration;
    @FXML
    private TextField numDossierField;
    @FXML
    private TextField numConteneurField;
    @FXML
    private TextField steImportateurField;
    @FXML
    private ImageView rechercheBtn;
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
    private ImageView ajoutBtn;
    @FXML
    private ImageView supprimerBtn;
    @FXML
    private ImageView videBtn;
    @FXML
    private ComboBox<String> magasinCombo;
    @FXML
    private ComboBox<String> typeReceptionCombo;
    @FXML
    private TextField numBlField;
    @FXML
    private ComboBox<String> fourCombo;
    @FXML
    private TextField nReceptionUsineField;
    @FXML
    private TextArea designationTextArea;
    @FXML
    private ComboBox<String> vehiculeCombo;
    @FXML
    private ComboBox<String> chauffeurCombo;
    @FXML
    private TextField nFactField;
    
    /**
     * Initializes the controller class.
     */
    
       ObservableList<String> typeReception =FXCollections.observableArrayList("Interne","Usine","Etrangère");
    
    private final ObservableList<DeclarationReception> listeDeclarationReception = FXCollections.observableArrayList();
    private final ObservableList<DetailTransfertStock> listeDetailTransfertStock = FXCollections.observableArrayList();
    DeclarationReceptionDAO declarationReceptiondao =new DeclarationReceptionDAOImpl();
    boolean  msgerrue = false;
    navigation nav = new navigation();
    private Map<String, Depot> mapDepot = new HashMap<>();
    DepotDAO depotDAO = new DepotDAOImpl();
    SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();
    MagasinDAO magasinDAO = new MagasinDAOImpl();
    TransfertStockDAO tarnsfertStockDAO = new TransfertStockDAOImpl();
    TransfertStock transfertStock = new TransfertStock();
    private Map<String, Magasin> mapMagasin = new HashMap<>();
         
       private Map<String, Vehicule> mapVehicule = new HashMap<>();
    VehiculeDAO vehiculeDAO = new VehiculeDAOImpl();

     private Map<String, Chauffeur> mapChauffeur = new HashMap<>();
    ChauffeurDAO chauffeurDAO = new ChauffeurDAOImpl();

     private Map<String, Fournisseur> mapFournisseur = new HashMap<>();
    FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
    
    UtilisateurDAO utilisateurDAO = new UtilisateurDAOImpl();
    
        public BigDecimal qte = BigDecimal.ZERO;  
        public BigDecimal qteCaisse = BigDecimal.ZERO;  
        public BigDecimal qteTotal = BigDecimal.ZERO;
        public BigDecimal conditionnement= BigDecimal.ZERO;
        String codeReception = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
               if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
                   
                      List<Depot> listDepot =  depotDAO.findByDepotSaufSiege();

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
        
         List<Fournisseur> listFournisseur = fournisseurDAO.findAll();

        listFournisseur.stream().map((fournisseur) -> {
            fourCombo.getItems().addAll(fournisseur.getNom());
            return fournisseur;
        }).forEachOrdered((fournisseur) -> {
            mapFournisseur.put(fournisseur.getNom(), fournisseur);
        });

        typeReceptionCombo.setItems(typeReception);
        
        numBlField.setDisable(true);
        fourCombo.setDisable(true);
        dateDeclaration.setDisable(true);
        numConteneurField.setDisable(true);
        steImportateurField.setDisable(true);
        nFactField.setDisable(true);
        
          Incrementation();   

               }
    }    

         void Incrementation (){
       
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.RECEPTION);
          codeReception = (sequenceur.getCode()+" "+(sequenceur.getValeur()+1));

   }
    
       void loadDetail() {
  
        declarationReceptionTable.setItems(listeDeclarationReception);
    }

    void setColumnProperties() {

        numDossierColumn.setCellValueFactory(new PropertyValueFactory<>("numDossier"));
        

        dateDeclarationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DeclarationReception, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<DeclarationReception, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDateDeclaration());
            }
        });  
          
        
        depotColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DeclarationReception, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DeclarationReception, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDepot().getLibelle1());
            }
        });  
    
         magasinColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DeclarationReception, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DeclarationReception, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getMagasin().getLibelle());
            }
        });  
        
    }
      void setColumnPropertiesTransfert(){
   
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
      
   void refrech(){
   
        numDossierField.clear();
        numConteneurField.clear();
        steImportateurField.clear();
        dateDeclaration.setValue(null);
        dateReception.setValue(null);
        depotCombo.getSelectionModel().select(-1);
        magasinCombo.getSelectionModel().select(-1);
        vehiculeCombo.getSelectionModel().select(-1);
        chauffeurCombo.getSelectionModel().select(-1);
        designationTextArea.clear();
        typeReceptionCombo.getSelectionModel().select(-1);
        fourCombo.getSelectionModel().select(-1);
        numBlField.clear();
        nReceptionUsineField.clear();
        nFactField.clear();
        
        numBlField.setDisable(true);
        fourCombo.setDisable(true);
        dateDeclaration.setDisable(true);
        numConteneurField.setDisable(true);
        steImportateurField.setDisable(true);
        nFactField.setDisable(true);
   
   }
    
    @FXML
      void vider() {
          
        numConteneurField.clear();
        dateDeclaration.setValue(null);
        typeReceptionCombo.getSelectionModel().select(-1);
        fourCombo.getSelectionModel().select(-1);
        numBlField.clear();
        steImportateurField.clear();
        nFactField.clear();
        
         numBlField.setDisable(true);
        fourCombo.setDisable(true);
        dateDeclaration.setDisable(true);
        numConteneurField.setDisable(true);
        steImportateurField.setDisable(true);
        nFactField.setDisable(true);
        
    }
    

    @FXML
    private void ajouterOnAction(ActionEvent event) throws ParseException {
        
           if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
        
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
        if(

          declarationReceptionTable.getSelectionModel().getSelectedIndex()==-1||
          receptionTable.getItems().size() == 0 ){
      
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
     }
        else {
      
                 LocalDate localDate=dateReception.getValue();
          Date dateTransfert=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());

          Depot depot  = mapDepot.get(depotCombo.getSelectionModel().getSelectedItem());
          Chauffeur chauffeur  = mapChauffeur.get(chauffeurCombo.getSelectionModel().getSelectedItem());
          Vehicule vehicule  = mapVehicule.get(vehiculeCombo.getSelectionModel().getSelectedItem());
          Magasin magasin  = mapMagasin.get(magasinCombo.getSelectionModel().getSelectedItem());
          
          Utilisateur utilisateur = utilisateurDAO.findUtiliByRechercheCodeDepot(depot.getId());
          
//          DeclarationReception declarationReception = declarationReceptionTable.getSelectionModel().getSelectedItem();
             
               for (int i = 0; i < listeDetailTransfertStock.size(); i++) {
   
                             DetailTransfertStock detailTransfertStock = listeDetailTransfertStock.get(i);
               
              detailTransfertStock.setQuantiteCaisseRecu(BigDecimal.ZERO);
              detailTransfertStock.setQuantiteRecu(BigDecimal.ZERO);
              detailTransfertStock.setUtilisateurCreation(utilisateur);
              detailTransfertStock.setDateCreation(new Date() );

             listeDetailTransfertStock.set(i, detailTransfertStock);
             
               }
             
             
        transfertStock.setUtilisateurCreation(utilisateur);
        transfertStock.setCodeTransfert(codeReception);
        transfertStock.setDateTransf(dateTransfert);
        transfertStock.setDepotDestination(depot);
        transfertStock.setMagasinDestination(magasin);
        transfertStock.setMagasinStock(magasin);
        transfertStock.setChauffeur(chauffeur);
        transfertStock.setVehicule(vehicule);
        transfertStock.setDeclarationReception(listeDeclarationReception);
        transfertStock.setDetailTransfertStock(listeDetailTransfertStock);
        transfertStock.setDateCreation(new Date());
        transfertStock.setStatut(Constantes.ETAT_STATUT_RECEPTION);
        
        tarnsfertStockDAO.add(transfertStock);
        
           transfertStock = new TransfertStock();
        
           nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MESSAGE_ALERT_AJOUT);  
           
            rafraichirOnAction(event);
           
        Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.RECEPTION);
           sequenceur.setValeur(sequenceur.getValeur()+1);
           sequenceurDAO.edit(sequenceur);
           Incrementation ();

    
        
          }
            }
            }
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
    
               if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
        
                   listeDetailTransfertStock.clear();
                   listeDeclarationReception.clear();
                   
                   refrech();
               }
    }


    @FXML
    private void afficherArt(MouseEvent event) {
        
    }

    @FXML
    private void depotComboOnAction(ActionEvent event) {
        
            if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
          try {
            magasinCombo.getItems().clear();
            Depot depot  = mapDepot.get(depotCombo.getSelectionModel().getSelectedItem());
            
            if (depot!=null){
            List<Magasin> listMagasin = depot.getListMagasin();
            listMagasin.stream().map((magasin) -> {
                magasinCombo.getItems().addAll(magasin.getLibelle());
                return magasin;
            }).forEachOrdered((magasin) -> {
                mapMagasin.put(magasin.getLibelle(), magasin);
            });
            
            }
        } catch (Exception e) {
            
        }
            }
    }

    @FXML
    private void codeEntreeTableKeyPressed(KeyEvent event) {
    }

    @FXML
    private void rechercheArticle(MouseEvent event) {
        
                 if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
                 Integer r = declarationReceptionTable.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.SELECTION_DOSSIER);
        } else {
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
      setColumnPropertiesTransfert();

         receptionTable.setEditable(true); 
        qteColumn.setEditable(true);
      
        }
    }
          
        
    }

    @FXML
    private void editCommitQuantiteColumn(TableColumn.CellEditEvent<DetailTransfertStock, BigDecimal> event) {
        
         if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
     ((DetailTransfertStock) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setQuantite(event.getNewValue());
        
                receptionTable.refresh();
                
                qte = qteColumn.getCellData(event.getTablePosition().getRow());

                 if(listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Piece")){
                
                
                conditionnement = new BigDecimal(listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).getArticle().getConditionnement());
                
                         qteTotal = (qte.divide(conditionnement,9,RoundingMode.FLOOR)).add(listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).getQuantiteTotal());
                  

                listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).setQuantite(qte);
                listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).setQuantiteTotal(qteTotal);
       
                  setColumnProperties();
                  
                }else if(listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Kg")){
                
                    
                   qteTotal = qte.add(listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).getQuantiteTotal());
                  

                listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).setQuantite(qte);
                listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).setQuantiteTotal(qteTotal);
       
                  setColumnProperties();
                }


         }
        
    }

    @FXML
    private void editCommitQuantiteCaisseColumn(TableColumn.CellEditEvent<DetailTransfertStock, BigDecimal> event) {
        
         if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
           ((DetailTransfertStock) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setQuantiteCaisse(event.getNewValue());
         
                   receptionTable.refresh();
                
                qteCaisse = qteCaisseColumn.getCellData(event.getTablePosition().getRow());

                if(listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Piece")){
                
                
                qteTotal = listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).getQuantiteTotal().add(qteCaisse);
                  

                listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).setQuantiteCaisse(qteCaisse);
                listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).setQuantiteTotal(qteTotal);
       
                  setColumnProperties();
                
                }else if(listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Kg")){
                
                listeDetailTransfertStock.get(receptionTable.getSelectionModel().getSelectedIndex()).setQuantiteCaisse(BigDecimal.ZERO.setScale(2));
                receptionTable.refresh();
                   nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.ERREUR_SAISIE);
                } 
         }
    }

    @FXML
    private void ajouter(MouseEvent event) throws ParseException {
        
          if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
           
        if (
                dateReception.getValue()==null ||
                depotCombo.getSelectionModel().getSelectedIndex()== -1 ||
                magasinCombo.getSelectionModel().getSelectedIndex()== -1 ||
                numDossierField.getText().equals("")||
                  vehiculeCombo.getSelectionModel().getSelectedIndex()== -1 ||
                chauffeurCombo.getSelectionModel().getSelectedIndex()== -1 ||
                typeReceptionCombo.getSelectionModel().getSelectedIndex()== -1

                ) {
            
            nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
            return;
            
        } else {

            if(listeDeclarationReception.size()==0){
            
             LocalDate localDate=dateDeclaration.getValue();
             Date dtDeclaration=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());

             Depot depot  = mapDepot.get(depotCombo.getSelectionModel().getSelectedItem());
             Magasin magasin  = mapMagasin.get(magasinCombo.getSelectionModel().getSelectedItem());
             Fournisseur fournisseur  = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
                  
             DeclarationReception declarationReception = new DeclarationReception();
             
        if (typeReceptionCombo.getSelectionModel().getSelectedItem().equals("Interne")){
 
            declarationReception.setNumBL(numBlField.getText());
            declarationReception.setFournisseur(fournisseur);
            declarationReception.setNumFacture(nFactField.getText());
            
            
        }else if  (typeReceptionCombo.getSelectionModel().getSelectedItem().equals("Usine")){
        
            System.out.println("Pour l'instant,Il n'y a aucun Traitement sur la partie 'Usine'");

        }else if  (typeReceptionCombo.getSelectionModel().getSelectedItem().equals("Etrangère")){
            
            declarationReception.setNumConteneur(numConteneurField.getText());
            declarationReception.setSteImportateur(steImportateurField.getText());
              
        }     
            declarationReception.setNumReception(nReceptionUsineField.getText());
            declarationReception.setDateDeclaration(dtDeclaration);
            declarationReception.setTypeReception(typeReceptionCombo.getSelectionModel().getSelectedItem());
            declarationReception.setNumDossier(numDossierField.getText());
            declarationReception.setDateCreation(new Date());
            declarationReception.setUtilisateurCreation(nav.getUtilisateur());
            declarationReception.setEtat(Constantes.ETAT_STATUT_LANCE);
            declarationReception.setTransfertStock(transfertStock);
            declarationReception.setDepot(depot);
            declarationReception.setMagasin(magasin);
            
            listeDeclarationReception.add(declarationReception);
            
            nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_AJOUT);
            setColumnProperties();
            loadDetail();
            vider();

               }else {
            nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DOSSIER_EXISTE);
            return;
            }
        }
          }
    }


    @FXML
    private void Supprimer(MouseEvent event) {
    
       if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
          Integer r = declarationReceptionTable.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            
            nav.showAlert(Alert.AlertType.WARNING, Constantes.TYPE_ALERT_INFORMATION, null, Constantes.MESSAGE_ALERT_SELECTIONNER);

        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_SUPPRIMER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();


            if (result.get() == ButtonType.OK) {
                DeclarationReception declarationReception = declarationReceptionTable.getSelectionModel().getSelectedItem();
                listeDeclarationReception.remove(declarationReception);
                nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_SUPPRIMER);
         
            }

        }
               }
        
    }


    @FXML
    private void afficherTransfertArt(MouseEvent event) {
    }

    @FXML
    private void typeReceptionComboOnAction(ActionEvent event) {
        
           if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
        
               if (typeReceptionCombo.getSelectionModel().getSelectedItem()!=null){
               
        if (typeReceptionCombo.getSelectionModel().getSelectedItem().equals("Interne")){
            
        numBlField.setDisable(false);
        fourCombo.setDisable(false);
        nFactField.setDisable(false);
        dateDeclaration.setDisable(false);
        numConteneurField.setDisable(true);
        steImportateurField.setDisable(true);
        
        numConteneurField.clear();
        dateDeclaration.setValue(null);
        fourCombo.getSelectionModel().select(-1);
        numBlField.clear();
        steImportateurField.clear();
        nFactField.clear();
        
        }else if  (typeReceptionCombo.getSelectionModel().getSelectedItem().equals("Usine")){
        
        numBlField.setDisable(true);
        fourCombo.setDisable(true);
        dateDeclaration.setDisable(false);
        numConteneurField.setDisable(true);
        steImportateurField.setDisable(true);
        nFactField.setDisable(true);
        
        numConteneurField.clear();
        dateDeclaration.setValue(null);
        fourCombo.getSelectionModel().select(-1);
        numBlField.clear();
        steImportateurField.clear();
        nFactField.clear();
        
        } else if  (typeReceptionCombo.getSelectionModel().getSelectedItem().equals("Etrangère")){
            
        numBlField.setDisable(true);
        fourCombo.setDisable(true);
        dateDeclaration.setDisable(false);
        numConteneurField.setDisable(false);
        steImportateurField.setDisable(false);
        nFactField.setDisable(true);
        
        numConteneurField.clear();
        dateDeclaration.setValue(null);
        fourCombo.getSelectionModel().select(-1);
        numBlField.clear();
        steImportateurField.clear();
        nFactField.clear();
        
        }
           }
           }
    }

    @FXML
    private void fourComboOnAction(ActionEvent event) {
        
    }

}
