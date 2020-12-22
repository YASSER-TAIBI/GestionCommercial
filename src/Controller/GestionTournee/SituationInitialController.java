/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.GestionTournee;

import Utils.Constantes;
import dao.Entity.Article;
import dao.Entity.Depot;
import dao.Entity.DetailTournee;
import dao.Entity.DetailTransfertStock;
import dao.Entity.DetailVendeurSecteur;
import dao.Entity.Magasin;
import dao.Entity.Secteur;
import dao.Entity.Tournee;
import dao.Entity.TypeVente;
import dao.Entity.Vendeur;
import dao.Entity.Ville;
import dao.Manager.DepotDAO;
import dao.Manager.DetailTourneeDAO;
import dao.Manager.DetailVendeurSecteurDAO;
import dao.Manager.MagasinDAO;
import dao.Manager.SecteurDAO;
import dao.Manager.TourneeDAO;
import dao.Manager.TypeVenteDAO;
import dao.Manager.VendeurDAO;
import dao.Manager.VilleDAO;
import dao.ManagerImpl.DepotDAOImpl;
import dao.ManagerImpl.DetailTourneeDAOImpl;
import dao.ManagerImpl.DetailVendeurSecteurDAOImpl;
import dao.ManagerImpl.MagasinDAOImpl;
import dao.ManagerImpl.SecteurDAOImpl;
import dao.ManagerImpl.TourneeDAOImpl;
import dao.ManagerImpl.TypeVenteDAOImpl;
import dao.ManagerImpl.VendeurDAOImpl;
import dao.ManagerImpl.VilleDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class SituationInitialController implements Initializable {

    @FXML
    private TableView<DetailTournee> initialTable;
    @FXML
    private TableColumn<DetailTournee, String> codeArticleColumn;
    @FXML
    private TableColumn<DetailTournee, String> libelleColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteInitialColumn;
    @FXML
    private TableColumn<DetailTournee, String> qteInitialGlobalColumn;
    @FXML
    private TableColumn<DetailTournee, Integer> conditionnementColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteInitialCaisseColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteTotalColumn;
    @FXML
    private ImageView rechercheBtn;
    @FXML
    private DatePicker dateChargement;
    @FXML
    private ComboBox<String> magasinCombo;
    @FXML
    private ComboBox<String> vendeurCombo;
    @FXML
    private ComboBox<String> secteurCombo;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRafraichir;
    
    /**
     * Initializes the controller class.
     */
    
        public ObservableList<DetailTournee> listeDetailTournee = FXCollections.observableArrayList();
    

        private Map<String, Magasin> mapMagasin = new HashMap<>();
        MagasinDAO magasinDAO = new MagasinDAOImpl();
        
        private Map<String, DetailVendeurSecteur> mapDetailVendeurSecteur = new HashMap<>();
        DetailVendeurSecteurDAO detailVendeurSecteurDAO = new DetailVendeurSecteurDAOImpl();
        
        private Map<String, Vendeur> mapVendeur = new HashMap<>();
        VendeurDAO vendeurDAO = new VendeurDAOImpl();

        private Map<String, Tournee> mapTournee = new HashMap<>();
       
        TourneeDAO tourneeDao = new TourneeDAOImpl();

         navigation nav = new navigation();
         Tournee tournee = new Tournee();
      
        DetailTourneeDAO detailTourneeDAO = new DetailTourneeDAOImpl();
        TourneeDAO tourneeDAO = new TourneeDAOImpl();
        TypeVenteDAO typeVenteDAO = new TypeVenteDAOImpl();
      
        public BigDecimal qteInitial = BigDecimal.ZERO;  
        public BigDecimal qteInitialCaisse = BigDecimal.ZERO;  
        public BigDecimal qteInitialTotal = BigDecimal.ZERO;
        public BigDecimal conditionnement= BigDecimal.ZERO;
    
    
    
     
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
            
              List<Vendeur> listVendeurTMP = new ArrayList<Vendeur>();
              List<Vendeur> listVendeur =  vendeurDAO.findByDepot(nav.getUtilisateur().getDepot().getId());
              
              boolean exist= false;
              
                for(int i=0;i<listVendeur.size();i++){
            exist= false;
                Vendeur vendeur = listVendeur.get(i);
                
                for(int j=0;j<listVendeurTMP.size();j++){
                 Vendeur vendeurTMP = listVendeur.get(j);
                 
                if (vendeur.getNom().equals(vendeurTMP.getNom()))
                {
               exist= true;
                }
            }
                if(exist==false){
                listVendeurTMP.add(vendeur);
                }
                }
            listVendeurTMP.stream().map((vendeur) -> {
                vendeurCombo.getItems().addAll(vendeur.getNom());
                return vendeur;
            }).forEachOrdered((vendeur) -> {
                mapVendeur.put(vendeur.getNom(), vendeur);
            });
        
        initialTable.setEditable(true); 
        qteInitialColumn.setEditable(true);
        
    }    
    
   void setColumnProperties(){
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
             
                conditionnementColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailTournee, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<DetailTournee, Integer> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getArticle().getConditionnement());
            }
        });  
              
           qteInitialColumn.setCellValueFactory(new PropertyValueFactory<DetailTournee, BigDecimal>("quantiteInitial"));
           setColumnTextFieldConverter(getConverter(), qteInitialColumn);
           
           qteInitialCaisseColumn.setCellValueFactory(new PropertyValueFactory<DetailTournee, BigDecimal>("quantiteInitialCaisse"));
           setColumnTextFieldConverter(getConverter(), qteInitialCaisseColumn);
           
            qteTotalColumn.setCellValueFactory(new PropertyValueFactory<DetailTournee, BigDecimal>("quantiteTotalInitial"));
           
           
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
    private void afficherArt(MouseEvent event) {
    }


      void viider(){
    
        dateChargement.setValue(null);
        vendeurCombo.getSelectionModel().select(-1);
        secteurCombo.getSelectionModel().select(-1);
        magasinCombo.getSelectionModel().select(-1);
        initialTable.getItems().clear();
        listeDetailTournee.clear();
        
    }
    

    @FXML
    private void rechercheArticle(MouseEvent event) throws ParseException {
      

        if(
          magasinCombo.getSelectionModel().getSelectedIndex()== -1 ||
          vendeurCombo.getSelectionModel().getSelectedIndex()== -1 ||
          dateChargement.getValue().equals(null)
          )
          {
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
     }
        else{
            
 
        
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
                     
                    listeDetailTournee.add(detailTournee);
                    }
                }      
                
                       initialTable.setItems(listeDetailTournee);
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
    }

    @FXML
    private void editCommitQuantiteInitialColumn(TableColumn.CellEditEvent<DetailTournee, BigDecimal> event) {
        
         ((DetailTournee) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setQuantiteInitial(event.getNewValue());
         
                initialTable.refresh();
                
                qteInitial = qteInitialColumn.getCellData(event.getTablePosition().getRow());

                if(listeDetailTournee.get(initialTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Piece")){
                
                
                conditionnement = new BigDecimal(listeDetailTournee.get(initialTable.getSelectionModel().getSelectedIndex()).getArticle().getConditionnement());
                
                         qteInitialTotal = (qteInitial.divide(conditionnement,9,RoundingMode.FLOOR)).add(listeDetailTournee.get(initialTable.getSelectionModel().getSelectedIndex()).getQuantiteTotalInitial());
                  

                listeDetailTournee.get(initialTable.getSelectionModel().getSelectedIndex()).setQuantiteInitial(qteInitial);
                listeDetailTournee.get(initialTable.getSelectionModel().getSelectedIndex()).setQuantiteTotalInitial(qteInitialTotal);
       
                  setColumnProperties();
                  
                }else if(listeDetailTournee.get(initialTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Kg")){
                
                    
                   qteInitialTotal = qteInitial.add(listeDetailTournee.get(initialTable.getSelectionModel().getSelectedIndex()).getQuantiteTotalInitial());
                  

                listeDetailTournee.get(initialTable.getSelectionModel().getSelectedIndex()).setQuantiteInitial(qteInitial);
                listeDetailTournee.get(initialTable.getSelectionModel().getSelectedIndex()).setQuantiteTotalInitial(qteInitialTotal);
       
                  setColumnProperties();
                }
                
              }

    @FXML
    private void editCommitQuantiteInitialCaisseColumn(TableColumn.CellEditEvent<DetailTournee, BigDecimal> event) {
    
    ((DetailTournee) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setQuantiteInitialCaisse(event.getNewValue());
         
                initialTable.refresh();
                
                qteInitialCaisse = qteInitialCaisseColumn.getCellData(event.getTablePosition().getRow());

                if(listeDetailTournee.get(initialTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Piece")){
                
                
                qteInitialTotal = listeDetailTournee.get(initialTable.getSelectionModel().getSelectedIndex()).getQuantiteTotalInitial().add(qteInitialCaisse);
                  

                listeDetailTournee.get(initialTable.getSelectionModel().getSelectedIndex()).setQuantiteInitialCaisse(qteInitialCaisse);
                listeDetailTournee.get(initialTable.getSelectionModel().getSelectedIndex()).setQuantiteTotalInitial(qteInitialTotal);
       
                  setColumnProperties();
                
                }else if(listeDetailTournee.get(initialTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Kg")){
                
                listeDetailTournee.get(initialTable.getSelectionModel().getSelectedIndex()).setQuantiteInitialCaisse(BigDecimal.ZERO.setScale(2));
                initialTable.refresh();
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
        
                    if(
          magasinCombo.getSelectionModel().getSelectedIndex()== -1 ||
          secteurCombo.getSelectionModel().getSelectedIndex()== -1 ||
          vendeurCombo.getSelectionModel().getSelectedIndex()== -1 || 
          dateChargement.getValue().equals(null) ||
          initialTable.getItems().size() == 0 ){
              
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
         return;
     }
        else {
      
                 LocalDate localDate=dateChargement.getValue();
                Date dateCharge=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());

             Magasin magasin  = mapMagasin.get(magasinCombo.getSelectionModel().getSelectedItem());
             DetailVendeurSecteur detailVendeurSecteur  = mapDetailVendeurSecteur.get(secteurCombo.getSelectionModel().getSelectedItem());
             
                        for (int i = 0; i < listeDetailTournee.size(); i++) {
   
                             DetailTournee detailTournee = listeDetailTournee.get(i);
                            
             detailTournee.setQuantiteCharge(BigDecimal.ZERO);
             detailTournee.setQuantiteChargeCaisse(BigDecimal.ZERO);
             detailTournee.setQuantiteChargeComptCaisse(BigDecimal.ZERO);
             detailTournee.setQuantiteChargeSupp(BigDecimal.ZERO);
             detailTournee.setQuantiteChargeSuppCaisse(BigDecimal.ZERO);
             detailTournee.setQuantiteChargeCompt(BigDecimal.ZERO);
             detailTournee.setQuantiteTotalCharge(BigDecimal.ZERO);
             detailTournee.setQuantiteRetourCaisse(BigDecimal.ZERO);
             detailTournee.setQuantiteTotalRetour(BigDecimal.ZERO);
             detailTournee.setQuantiteOffre(BigDecimal.ZERO);
             detailTournee.setQuantiteOffreCaisse(BigDecimal.ZERO);
             detailTournee.setQuantiteTotalOffre(BigDecimal.ZERO);
             detailTournee.setQuantiteVente(detailTournee.getQuantiteTotalInitial());
             detailTournee.setQuantiteFinal(detailTournee.getQuantiteTotalInitial());
             detailTournee.setQuantiteRetour(BigDecimal.ZERO);
             detailTournee.setQuantiteRecape(BigDecimal.ZERO);
             detailTournee.setQuantiteCaisseRecape(BigDecimal.ZERO);
             detailTournee.setQuantiteTotalRecape(BigDecimal.ZERO);
             detailTournee.setQuantiteVendue(BigDecimal.ZERO);
             detailTournee.setTypeVente(detailVendeurSecteur.getVendeur().getTypeVente());
             detailTournee.setTotalFacture(BigDecimal.ZERO);
             detailTournee.setTotalVendue(BigDecimal.ZERO);
             detailTournee.setDateCreation(new Date() );

             listeDetailTournee.set(i, detailTournee);
             
               }
                        
           tournee.setDateCreation(new Date());
           tournee.setDepot(nav.getUtilisateur().getDepot());
           tournee.setMagasin(magasin);
           tournee.setSecteur(detailVendeurSecteur.getSecteur());
           tournee.setVille(detailVendeurSecteur.getSecteur().getVille());
           tournee.setVendeur(detailVendeurSecteur.getVendeur());
           tournee.setEtat(Constantes.ETAT_INITIAL);
           tournee.setStatus(Constantes.ETAT_OUVERT);
           tournee.setMontantCredit(BigDecimal.ZERO);
           tournee.setDateTournee(dateCharge);
           tournee.setDetailTournee(listeDetailTournee);

           tourneeDAO.add(tournee);



              nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MESSAGE_ALERT_AJOUT); 
              viider();         
             tournee = new Tournee();    
                    }
  } 
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
        
           viider();
    }

    @FXML
    private void vendeurOnAction(ActionEvent event) {
        
                                                          secteurCombo.getItems().clear();
          
            Vendeur vendeur  = mapVendeur.get(vendeurCombo.getSelectionModel().getSelectedItem());
            
             if(vendeur!=null){
            List<DetailVendeurSecteur> listDetailVendeurSecteur = detailVendeurSecteurDAO.findDetailVendeurSecteurByVendeur(vendeur.getId());
            listDetailVendeurSecteur.stream().map((detailVendeurSecteurTMP) -> {
                secteurCombo.getItems().addAll(detailVendeurSecteurTMP.getSecteur().getLibelle());
                return detailVendeurSecteurTMP;
            }).forEachOrdered((detailVendeurSecteurTMP) -> {
                mapDetailVendeurSecteur.put(detailVendeurSecteurTMP.getSecteur().getLibelle(), detailVendeurSecteurTMP);
            });
             }
        
        
    }


 

   

   



}
