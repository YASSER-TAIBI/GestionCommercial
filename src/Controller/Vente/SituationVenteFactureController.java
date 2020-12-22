/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Vente;

import Utils.Constantes;
import dao.Entity.Article;
import dao.Entity.ClientPF;
import dao.Entity.Depot;
import dao.Entity.DetailCompte;
import dao.Entity.DetailFacture;
import dao.Entity.DetailTournee;
import dao.Entity.Facture;
import dao.Entity.Magasin;
import dao.Entity.SequenceurFacture;
import dao.Entity.SituationVenteFacture;
import dao.Entity.Tournee;
import dao.Entity.Vendeur;
import dao.Entity.Versement;
import dao.Manager.ArticleDAO;
import dao.Manager.ClientPFDAO;
import dao.Manager.DetailCompteDAO;
import dao.Manager.DetailFactureDAO;
import dao.Manager.DetailTourneeDAO;
import dao.Manager.FactureDAO;
import dao.Manager.TourneeDAO;
import dao.Manager.VendeurDAO;
import dao.Manager.VersementDAO;
import dao.ManagerImpl.ArticleDAOImpl;
import dao.ManagerImpl.ClientPFDAOImpl;
import dao.ManagerImpl.DetailCompteDAOImpl;
import dao.ManagerImpl.DetailFactureDAOImpl;
import dao.ManagerImpl.DetailTourneeDAOImpl;
import dao.ManagerImpl.FactureDAOImpl;
import dao.ManagerImpl.TourneeDAOImpl;
import dao.ManagerImpl.VendeurDAOImpl;
import dao.ManagerImpl.VersementDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.math.MathContext;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class SituationVenteFactureController implements Initializable {

    @FXML
    private DatePicker dateSituation;
    @FXML
    private ComboBox<String> magasinCombo;
    @FXML
    private ComboBox<String> tourneeCombo;
    @FXML
    private ComboBox<String> vendeurCombo;
    
    @FXML
    private TableView<SituationVenteFacture> venteFactTable;
    @FXML
    private TableColumn<SituationVenteFacture, String> codeArticleColumn;
    @FXML
    private TableColumn<SituationVenteFacture, String> codeFactColumn;
    @FXML
    private TableColumn<SituationVenteFacture, String> libelleColumn;
    @FXML
    private TableColumn<SituationVenteFacture, BigDecimal> venteColumn;
    @FXML
    private TableColumn<SituationVenteFacture, BigDecimal> factureHtColumn;

    @FXML
    private Button btnRafraichir;
    @FXML
    private ImageView rechercheBtn;
    @FXML
    private TextField remiseFactureField;
    @FXML
    private TextField remiseVenteField;
    @FXML
    private ImageView calculeRemiseFactBtn;
    @FXML
    private Button btnValider;
    @FXML
    private Button btnImprimer;
    
    @FXML
    private TableView<DetailFacture> DetailFactTable;
    @FXML
    private TableColumn<DetailFacture, String> codeArticleDetailColumn;
    @FXML
    private TableColumn<DetailFacture, String> codeFactDetailColumn;
    @FXML
    private TableColumn<DetailFacture, String> libelleDetailColumn;
    @FXML
    private TableColumn<DetailFacture, String> clientColumn;
     @FXML
    private TableColumn<DetailFacture, BigDecimal> remiseColumn;
    @FXML
    private TableColumn<DetailFacture, BigDecimal> montFactColumn;
    @FXML
    private TableColumn<DetailFacture, String> categorieColumn;
    @FXML
    private TableColumn<DetailFacture, Boolean> accRemiseColumn;
    /**
     * Initializes the controller class.
     */
    
    DetailTourneeDAO detailTourneeDAO = new DetailTourneeDAOImpl();
    DetailCompteDAO detailCompteDAO = new DetailCompteDAOImpl();
    DetailFactureDAO detailFactureDAO = new DetailFactureDAOImpl();
    FactureDAO factureDAO = new FactureDAOImpl();
    VersementDAO versementDAO = new VersementDAOImpl();
    ArticleDAO articleDAO = new ArticleDAOImpl();
    navigation nav = new navigation(); 
    
    private final  ObservableList<SituationVenteFacture> listSituationVenteFacture = FXCollections.observableArrayList();   
    private final  ObservableList<DetailFacture> listDetailFacture = FXCollections.observableArrayList(); 
          
          
    private Map<String, Vendeur> mapVendeur = new HashMap<>();
    private Map<String, ClientPF> mapNomClient = new HashMap<>();
    private Map<String, ClientPF> mapCodeClient = new HashMap<>();
    
    VendeurDAO vendeurDAO = new VendeurDAOImpl();
    ClientPFDAO clientDAO = new ClientPFDAOImpl();
    
    private Map<String, Magasin> mapMagasin = new HashMap<>();

    TourneeDAO tourneeDAO=new TourneeDAOImpl();
    private Map<String, Tournee> mapTournee= new HashMap<>();
    
  
    SituationVenteFacture situationVenteFacture ;
    
    BigDecimal remiseDebit =BigDecimal.ZERO;
    BigDecimal remiseCredit =BigDecimal.ZERO;
    

            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
            // TODO
         
               List<Magasin> listMagasin = nav.getUtilisateur().getDepot().getListMagasin();
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
        
                 venteFactTable.setEditable(true);
     
    }    

 
        void setColumnProperties() {

    
          codeArticleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationVenteFacture, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<SituationVenteFacture, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getCode());
                }
             });

            codeFactColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationVenteFacture, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<SituationVenteFacture, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getCodeFact());
                }
             });     
          
               libelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationVenteFacture, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<SituationVenteFacture, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getArticle());
                }
             });
               
               venteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationVenteFacture, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationVenteFacture, BigDecimal> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getVente());
                }
             });
      
             factureHtColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationVenteFacture, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationVenteFacture, BigDecimal> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getFactureHt());
                }
             });
     

    }
    
        
         void setDetailColumnProperties() {
         
            codeArticleDetailColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailFacture, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailFacture, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getArticle().getCode());
                }
             });

            codeFactDetailColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailFacture, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailFacture, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getArticle().getCodeFacture());
                }
             });     
          
            libelleDetailColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailFacture, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailFacture, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getArticle().getLibelle());
                }
             });  
             
            categorieColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailFacture, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailFacture, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getCategorie());
                }
             });  
               
            clientColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailFacture, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailFacture, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getFacture().getClient().getNom());
                }
             });

            montFactColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailFacture, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailFacture, BigDecimal> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMontant());
                }
             });     
          
             remiseColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailFacture, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailFacture, BigDecimal> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getRemise());
                }
             }); 
              
              
             
         
          accRemiseColumn.cellValueFactoryProperty();
          accRemiseColumn.setCellValueFactory((cellData) -> {
          DetailFacture cellvalue= cellData.getValue();
              BooleanProperty property = new SimpleBooleanProperty();
                      property.set(cellvalue.getAction());
                      
                      property.addListener((observabel, oldvalue,newvalue)->cellvalue.setAction(newvalue));
              
              return property; 
          });
          accRemiseColumn.setCellFactory(act-> new CheckBoxTableCell<>());
    
          accRemiseColumn.setEditable(true);
      
          DetailFactTable.setEditable(true);
         
         } 
        
    @FXML
    private void dateSituationOnAction(ActionEvent event) {
    }

    @FXML
    private void magasinComboOnAction(ActionEvent event) {
    }

    @FXML
    private void tourneeComboOnAction(ActionEvent event) {
    }

    @FXML
    private void vendeurComboOnAction(ActionEvent event) {
        
          try {
            
             if(vendeurCombo.getSelectionModel().getSelectedIndex()!=-1)
        {
             if(magasinCombo.getSelectionModel().getSelectedIndex()==-1)
        {
              nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_SELECTIONNER_MAGASIN);
           return;
        }
             else if(dateSituation.getValue()==null)
        {
             nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_ENTRER_DATE);
          
           return;   
        }else {
            
 
            tourneeCombo.getItems().clear();

            Vendeur vendeur = mapVendeur.get(vendeurCombo.getSelectionModel().getSelectedItem());
            
                  LocalDate localDate=dateSituation.getValue();
        
           Date dateSituation =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
            
           List<Tournee> listTournee = tourneeDAO.findByVendeurAndDateAndEtat(vendeur.getId(),dateSituation);
  
           if(listTournee!=null)
           {
               listTournee.stream().map((tournee) -> {
                tourneeCombo.getItems().addAll(tournee.getCodeVent());
                return tournee;
            }).forEachOrdered((tournee) -> {
                mapTournee.put(tournee.getCodeVent(), tournee);
            });  
               
           }
           
        }
        
            
        }
            
        } catch (Exception e) {
        }
        
        
    }

    @FXML
    private void DetailTransfertCheque(MouseEvent event) throws ParseException {
        
                setDetailColumnProperties();
        listDetailFacture.clear();
        
if (venteFactTable.getSelectionModel().getSelectedIndex()!=-1){
    
        Vendeur vendeur = mapVendeur.get(vendeurCombo.getSelectionModel().getSelectedItem());
            
        Tournee tournee = mapTournee.get(tourneeCombo.getSelectionModel().getSelectedItem());
        
        Magasin magasin = mapMagasin.get(magasinCombo.getSelectionModel().getSelectedItem());
        
                  LocalDate localDate=dateSituation.getValue();
        
           Date dateSituation =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
            
        situationVenteFacture = venteFactTable.getSelectionModel().getSelectedItem();
        
        Article article = articleDAO.findByArticle(situationVenteFacture.getArticle());

      listDetailFacture.addAll(detailFactureDAO.findByDateAndVendeurAndTourneeAndMagasinAndArticle(dateSituation, vendeur.getId(), tournee.getId(), magasin.getId(),article.getId()));
        
      DetailFactTable.setItems(listDetailFacture);


}
        
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {


        dateSituation.setValue(null);
        vendeurCombo.getSelectionModel().select(-1);
        magasinCombo.getSelectionModel().select(-1);
        tourneeCombo.getSelectionModel().select(-1);
    
        listSituationVenteFacture.clear();   
        listDetailFacture.clear();  
        
        venteFactTable.getItems().clear();
        DetailFactTable.getItems().clear();
        
        remiseFactureField.setText("");
        remiseVenteField.setText("");
    }

    @FXML
    private void rechercheArticle(MouseEvent event) throws ParseException {
        
        if(dateSituation.getValue()==null)
        {
             nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_ENTRER_DATE);
          
           return;   
        }else if(vendeurCombo.getSelectionModel().getSelectedIndex()==-1)
        {
             nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_SELECTIONNER_VENDEUR);
          
           return;
        }else if(magasinCombo.getSelectionModel().getSelectedIndex()==-1)
        {
             nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_SELECTIONNER_MAGASIN);
          
           return;
        }else if(tourneeCombo.getSelectionModel().getSelectedIndex()==-1)
        {
           nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_SELECTIONNER_TOURNEE);
          
           return;   
        }else{
        
       LocalDate localDate=dateSituation.getValue();
        
           Date dateSituation =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
        
            Vendeur vendeur  =mapVendeur.get(vendeurCombo.getSelectionModel().getSelectedItem());
        
             Tournee tournee  =mapTournee.get(tourneeCombo.getSelectionModel().getSelectedItem());
            
         List <Object[]> listeObjectVenteFact =detailTourneeDAO.findBySituationVenteFacture(dateSituation, vendeur.getId(), tournee.getId());

             listSituationVenteFacture.clear();
         
            for(int i=0; i<listeObjectVenteFact.size(); i++) {

                    Object[] object=listeObjectVenteFact.get(i);
                   
                    String codeArt =(String)object[0];
                    String codeFact =(String)object[1];
                    String libelle =(String)object[2];
                    BigDecimal vente = (BigDecimal)object[3]; 
                    BigDecimal facture = (BigDecimal)object[4]; 
                    
                    
                    if(codeArt == null && codeFact == null && libelle == null && vente == null && facture == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
                        break;
                    }else{

               SituationVenteFacture situationVenteFacture = new SituationVenteFacture();
               
                  situationVenteFacture.setCode(codeArt);
                  situationVenteFacture.setCodeFact(codeFact);
                  situationVenteFacture.setArticle(libelle);
                  situationVenteFacture.setVente(vente);
                  situationVenteFacture.setFactureHt(facture);
                
                  listSituationVenteFacture.add(situationVenteFacture);
                    }
            }
            
        venteFactTable.setItems(listSituationVenteFacture);
        setColumnProperties();
      
                venteFactTable.setEditable(true);
                
                if (tournee.getStatus().equals(Constantes.ETAT_OUVERT)){
                
                    btnValider.setDisable(false);

                }else{
                
                    btnValider.setDisable(true);
                
                }
                
//##############################################################################################################################################################################################################################################

            Versement versement = versementDAO.findByTournee(tournee.getId());
                
                    if (versement!= null){
                        
                        remiseVenteField.setText(versement.getMontantRemise().toString()); 
                    }
//###############################################################################################################################################################################################################################################
     

            List<Facture> listFacture = factureDAO.findFactureByTournee(tournee.getId());
           
            BigDecimal remiseTotal =BigDecimal.ZERO;
            
                     for (int i = 0; i < listFacture.size(); i++) {
                        
                        Facture facture =listFacture.get(i);

                        remiseTotal = remiseTotal.add(facture.getRemiseTotal());
                    }
                     
                     
                    remiseFactureField.setText(remiseTotal.toString());
    }

    }  
     
    void calculeRemise(){  
        

        SituationVenteFacture situationVenteFacture =  venteFactTable.getSelectionModel().getSelectedItem();
      
            BigDecimal calculeRemiseDebit=BigDecimal.ZERO;
            BigDecimal calculeRemiseCredit=BigDecimal.ZERO;
        
                    for (int i = 0; i < listDetailFacture.size(); i++) {
                        
                        DetailFacture detailFacture =listDetailFacture.get(i);
                
                         if (detailFacture.getAction()==false && detailFacture.getCategorie().equals(Constantes.CATEGORIE_FACTURE_GRATUITE)){
                             
                             calculeRemiseDebit= calculeRemiseDebit.add(situationVenteFacture.getFactureHt().multiply(detailFacture.getRemise()).divide(new BigDecimal(100), 2, RoundingMode.FLOOR));
                         }
                         else if (detailFacture.getAction()==true && detailFacture.getCategorie().equals(Constantes.CATEGORIE_FACTURE_GRATUITE)){
                         
                             calculeRemiseCredit= calculeRemiseCredit.add(situationVenteFacture.getFactureHt().multiply(detailFacture.getRemise()).divide(new BigDecimal(100), 2, RoundingMode.FLOOR));
                           
                         }
                    }
            

                        remiseFactureField.setText(calculeRemiseDebit.toString());

    }
        
    @FXML
    private void validerOnAction(ActionEvent event) {
        
        
   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                
         if(dateSituation.getValue()==null||
            vendeurCombo.getSelectionModel().getSelectedIndex()==-1||
            magasinCombo.getSelectionModel().getSelectedIndex()==-1||
            tourneeCombo.getSelectionModel().getSelectedIndex()==-1||
              remiseFactureField.getText().equals("")
                 ){
              
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
         return;
     }
        else {

////>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Tournee <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

             Tournee tournee  =mapTournee.get(tourneeCombo.getSelectionModel().getSelectedItem());
         
         List<Facture> listFacture = factureDAO.findFactureByTournee(tournee.getId());
  
           for (int i = 0; i < listFacture.size(); i++) {
                        
                        Facture facture =listFacture.get(i);
                        
                        facture.setDetailFacture(listDetailFacture);
                        
                        factureDAO.edit(facture);  
                    }
         
        tournee.setStatus(Constantes.ETAT_FERMER);
        tourneeDAO.edit(tournee);   
        
          
////>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Vendeur <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


               for (int i = 0; i < listDetailFacture.size(); i++) {
                        
                        DetailFacture detailFacture =listDetailFacture.get(i);
                
                         if (detailFacture.getAction()==false && detailFacture.getCategorie().equals(Constantes.CATEGORIE_FACTURE_GRATUITE)){
                             
                            BigDecimal calculeRemiseDebit= (situationVenteFacture.getFactureHt().multiply(detailFacture.getRemise())).divide(new BigDecimal(100), 2, RoundingMode.FLOOR);
                             

              DetailCompte detailCompteVendeur = new DetailCompte();
 
             detailCompteVendeur.setMontantDebit(calculeRemiseDebit);
             detailCompteVendeur.setMontantCredit(BigDecimal.ZERO);
             detailCompteVendeur.setDateOperation(tournee.getDateTournee());
             detailCompteVendeur.setEtat(Constantes.ETAT_REMISE);
             detailCompteVendeur.setDateCreation(new Date());
             detailCompteVendeur.setUtilisateurCreation(nav.getUtilisateur());
             detailCompteVendeur.setCompte(tournee.getVendeur().getCompte());
             detailCompteVendeur.setDesignation(Constantes.REMISE_FACTURE+tournee.getCodeVent());
 
             detailCompteDAO.add(detailCompteVendeur);

             
                         }
                         else if (detailFacture.getAction()==true && detailFacture.getCategorie().equals(Constantes.CATEGORIE_FACTURE_GRATUITE)){
                         
                            BigDecimal calculeRemiseCredit= (situationVenteFacture.getFactureHt().multiply(detailFacture.getRemise())).divide(new BigDecimal(100), 2, RoundingMode.FLOOR);
                           
                            
             DetailCompte detailCompteVendeur = new DetailCompte();
 
             detailCompteVendeur.setMontantDebit(BigDecimal.ZERO);
             detailCompteVendeur.setMontantCredit(calculeRemiseCredit);
             detailCompteVendeur.setDateOperation(tournee.getDateTournee());
             detailCompteVendeur.setEtat(Constantes.ETAT_VERSEMENT);
             detailCompteVendeur.setDateCreation(new Date());
             detailCompteVendeur.setUtilisateurCreation(nav.getUtilisateur());
             detailCompteVendeur.setCompte(tournee.getVendeur().getCompte());
             detailCompteVendeur.setDesignation(Constantes.REFUS_REMISE_FACTURE+tournee.getCodeVent());
 
             detailCompteDAO.add(detailCompteVendeur);
    
                            
                            
                         }
                    }


nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_AJOUT);
             rafraichirOnAction(event);
}
        
    }
    }

    @FXML
    private void calculeRemiseFact(MouseEvent event) {
        
    
            if(dateSituation.getValue()==null)
        {
             nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_ENTRER_DATE);
          
           return;   
        }else if(vendeurCombo.getSelectionModel().getSelectedIndex()==-1)
        {
             nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_SELECTIONNER_VENDEUR);
          
           return;
        }else if(magasinCombo.getSelectionModel().getSelectedIndex()==-1)
        {
             nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_SELECTIONNER_MAGASIN);
          
           return;
        }else if(tourneeCombo.getSelectionModel().getSelectedIndex()==-1)
        {
           nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_SELECTIONNER_TOURNEE);
          
           return;   
        }else{
        calculeRemise();
            }
          }

    @FXML
    private void imprimerOnAction(ActionEvent event) {
    }

}
