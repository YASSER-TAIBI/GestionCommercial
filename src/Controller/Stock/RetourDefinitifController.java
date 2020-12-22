/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Stock;

import Controller.GestionTournee.RechercheArticleController;
import static Controller.Vente.ChargementController.setColumnTextFieldConverter;
import Utils.Constantes;
import dao.Entity.Article;
import dao.Entity.Depot;
import dao.Entity.DetailTournee;
import dao.Entity.DetailTransfertStock;
import dao.Entity.DetailVendeurSecteur;
import dao.Entity.Magasin;
import dao.Entity.Secteur;
import dao.Entity.Sequenceur;
import dao.Entity.Tournee;
import dao.Entity.TransfertStock;
import dao.Entity.TypeVente;
import dao.Entity.Vendeur;
import dao.Manager.DepotDAO;
import dao.Manager.DetailTourneeDAO;
import dao.Manager.DetailVendeurSecteurDAO;
import dao.Manager.DetailVenteMixteDAO;
import dao.Manager.SequenceurDAO;
import dao.Manager.TourneeDAO;
import dao.Manager.TransfertStockDAO;
import dao.Manager.TypeVenteDAO;
import dao.Manager.VendeurDAO;
import dao.Manager.VenteMixteDAO;
import dao.ManagerImpl.DepotDAOImpl;
import dao.ManagerImpl.DetailTourneeDAOImpl;
import dao.ManagerImpl.DetailVendeurSecteurDAOImpl;
import dao.ManagerImpl.DetailVenteMixteDAOImpl;
import dao.ManagerImpl.SequenceurDAOImpl;
import dao.ManagerImpl.TourneeDAOImpl;
import dao.ManagerImpl.TransfertStockDAOImpl;
import dao.ManagerImpl.TypeVenteDAOImpl;
import dao.ManagerImpl.VendeurDAOImpl;
import dao.ManagerImpl.VenteMixteDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class RetourDefinitifController implements Initializable {

    @FXML
    private TableView<DetailTournee> retourDefinitifTable;
    @FXML
    private TableColumn<DetailTournee, String> codeArticleColumn;
    @FXML
    private TableColumn<DetailTournee, String> libelleColumn;
       @FXML
    private TableColumn<DetailTournee, String> qteRetourDefGlobalColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteInitialColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteCaisseColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteTotalColumn;
    @FXML
    private ImageView rechercheBtn;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRafraichir;
    @FXML
    private ComboBox<String> magasinCombo;
    @FXML
    private ComboBox<String> vendeurCombo;
    @FXML
    private DatePicker dateRetour;
    @FXML
    private RadioButton completifRadio;
    @FXML
    private ToggleGroup retour;
    @FXML
    private RadioButton ordinaireRadio;
    @FXML
    private ComboBox<String> secteurCombo;
    @FXML
    private TextArea designationTextArea;
    @FXML
    private DatePicker dateTournee;
    /**
     * Initializes the controller class.
     */

        public BigDecimal qteRetour = BigDecimal.ZERO;
  
    TourneeDAO tourneeDAO = new TourneeDAOImpl();
      
    private Map<String, Vendeur> mapVendeur = new HashMap<>();
    VendeurDAO vendeurDAO = new VendeurDAOImpl();
    
    
    navigation nav = new navigation();
    DetailTourneeDAO detailTourneeDAO = new DetailTourneeDAOImpl();
                        
    public ObservableList<DetailTournee> listeDetailTournee = FXCollections.observableArrayList();
    public ObservableList<DetailTransfertStock> listeDetailTransfertStock = FXCollections.observableArrayList();
    
    
    private Map<String, Depot> mapDepot = new HashMap<>();
    DepotDAO depotDAO = new DepotDAOImpl();
    
    private Map<String, Magasin> mapMagasin = new HashMap<>();

    private Map<String, Tournee> mapTournee = new HashMap<>();
    TourneeDAO tourneeDao = new TourneeDAOImpl();
    
    private Map<String, DetailVendeurSecteur> mapDetailVendeurSecteur = new HashMap<>();
    DetailVendeurSecteurDAO detailVendeurSecteurDAO = new DetailVendeurSecteurDAOImpl();
    
    Tournee tournee = new Tournee();
    TransfertStock transfertStock = new  TransfertStock();
    TransfertStockDAO tarnsfertStockDAO = new TransfertStockDAOImpl();
    
        public BigDecimal qte = BigDecimal.ZERO;  
        public BigDecimal qteCaisse = BigDecimal.ZERO;  
        public BigDecimal qteTotal = BigDecimal.ZERO;
        public BigDecimal conditionnement= BigDecimal.ZERO;
 
  

    
    
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
            
            setColumnProperties();
        
        retourDefinitifTable.setEditable(true); 

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
              
           qteInitialColumn.setCellValueFactory(new PropertyValueFactory<DetailTournee, BigDecimal>("quantiteFinal"));
              
           qteColumn.setCellValueFactory(new PropertyValueFactory<DetailTournee, BigDecimal>("quantiteRecape"));
           setColumnTextFieldConverter(getConverter(), qteColumn);
           
           qteCaisseColumn.setCellValueFactory(new PropertyValueFactory<DetailTournee, BigDecimal>("quantiteCaisseRecape"));
           setColumnTextFieldConverter(getConverter(), qteCaisseColumn);
           
           qteTotalColumn.setCellValueFactory(new PropertyValueFactory<DetailTournee, BigDecimal>("quantiteTotalRecape"));
       
       
       }
    
         void loadDetailTmp(){
        listeDetailTournee.clear();
        listeDetailTournee.addAll(detailTourneeDAO.findAll());
        retourDefinitifTable.setItems(listeDetailTournee);
     
    }


    @FXML
    private void afficherArt(MouseEvent event) {
    }


    void viider(){
    
        retourDefinitifTable.getItems().clear();
        dateRetour.setValue(null);
        dateTournee.setValue(null);
        vendeurCombo.getSelectionModel().select(-1);
        magasinCombo.getSelectionModel().select(-1);

    
    }


    @FXML
    private void rechercheArticle(MouseEvent event) throws ParseException {
        
            listeDetailTournee.clear();

        if(
          magasinCombo.getSelectionModel().getSelectedIndex()== -1 ||
          secteurCombo.getSelectionModel().getSelectedIndex()== -1 ||
          vendeurCombo.getSelectionModel().getSelectedIndex()== -1 ||
          dateTournee.getValue()==null
          ) 
          {
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
     }
        else{
            

            LocalDate localDate=dateTournee.getValue();
            Date dateTour=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
            Depot depot  =nav.getUtilisateur().getDepot();
            DetailVendeurSecteur detailVendeurSecteur  = mapDetailVendeurSecteur.get(secteurCombo.getSelectionModel().getSelectedItem());
            Magasin  magasin = mapMagasin.get(magasinCombo.getSelectionModel().getSelectedItem());
        
       
         tournee = tourneeDAO.findDetailTrnByTrn(dateTour,detailVendeurSecteur.getSecteur().getId(), depot.getId(), magasin.getId(), detailVendeurSecteur.getVendeur().getId());
             
        if (tournee!=null){
        
        listeDetailTournee.addAll(tournee.getDetailTournee());

                retourDefinitifTable.setItems(listeDetailTournee);
                
            setColumnProperties();

        }else {
          nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MESSAGE_ALERT_VERIFICATION_TOURNEE);
        
        }
        }

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
          secteurCombo.getSelectionModel().getSelectedIndex()== -1 ||
          magasinCombo.getSelectionModel().getSelectedIndex()== -1 ||
          vendeurCombo.getSelectionModel().getSelectedIndex()== -1 ||
          dateRetour.getValue()==null ||
          dateTournee.getValue()==null ||
          retourDefinitifTable.getItems().size() == 0 ){
              
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
     }
        else {
  
  
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Qte Vente / Qte Final <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<   
        

               for(int j =0;j<listeDetailTournee.size();j++){
              
                       BigDecimal qteRecape = BigDecimal.ZERO;
                       BigDecimal qteVente = BigDecimal.ZERO;
                       BigDecimal qteFinal = BigDecimal.ZERO;
                       
                   DetailTournee detailTournee = listeDetailTournee.get(j);
                   
           qteRecape = detailTournee.getQuantiteTotalRecape();
           qteVente = detailTournee.getQuantiteVente().subtract(qteRecape);
           qteFinal = detailTournee.getQuantiteFinal().subtract(qteRecape);
           
           if (detailTournee.getTournee().getEtat().equals(Constantes.ETAT_INITIAL) || detailTournee.getTournee().getEtat().equals(Constantes.ETAT_OUVERT) || detailTournee.getTournee().getEtat().equals(Constantes.ETAT_EN_COURS)){
            
            listeDetailTournee.get(j).setQuantiteVente(qteVente);
            listeDetailTournee.get(j).setQuantiteFinal(qteFinal);
           }else{
               
           listeDetailTournee.get(j).setQuantiteFinal(qteFinal);
           }
           
            listeDetailTournee.set(j,detailTournee);
            
            
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> DetailTransfertStock <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<             
         
                    DetailTransfertStock detailTransfertStock = new DetailTransfertStock();
                    
                    if(detailTournee.getQuantiteTotalRecape().compareTo(BigDecimal.ZERO)>0){
                    
                    detailTransfertStock.setArticle(detailTournee.getArticle());
                    detailTransfertStock.setQuantite(detailTournee.getQuantiteRecape());
                    detailTransfertStock.setQuantiteCaisse(detailTournee.getQuantiteCaisseRecape());
                    detailTransfertStock.setQuantiteTotal(detailTournee.getQuantiteTotalRecape());
                    detailTransfertStock.setQuantiteStock(detailTournee.getQuantiteTotalRecape());
                    detailTransfertStock.setDateCreation(new Date());
                    detailTransfertStock.setTransfertStock(transfertStock);
                    detailTransfertStock.setUtilisateurCreation(nav.getUtilisateur());
                    
                    listeDetailTransfertStock.add(detailTransfertStock);
                    }
               }

 //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Tournee <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<               
             
        tournee.setDetailTournee(listeDetailTournee);
        
         if(completifRadio.isSelected()==true){
        tournee.setEtat(Constantes.ETAT_SUSPENDU);
        }
         
        tourneeDAO.edit(tournee);            
               
 //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> TransfertStock <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<               
     
          LocalDate localDate=dateRetour.getValue();
          Date dateRet=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
          Magasin  magasin = mapMagasin.get(magasinCombo.getSelectionModel().getSelectedItem());
        
          
        transfertStock.setDateTransf(dateRet);
        transfertStock.setUtilisateurCreation(nav.getUtilisateur());
        transfertStock.setMagasinStock(magasin);
        transfertStock.setDetailTransfertStock(listeDetailTransfertStock);
        transfertStock.setDateCreation(new Date());
        transfertStock.setStatut(Constantes.ETAT_STATUT_RETOUR_DEFINITIF);
        
        if(ordinaireRadio.isSelected()==true){
        transfertStock.setEtat(Constantes.ETAT_STATUT_ORDINAIRE);
        }else{
        transfertStock.setEtat(Constantes.ETAT_STATUT_COMPLETIF);
        }
        
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
    @FXML
    private void editCommitQuantiteRetourColumn(TableColumn.CellEditEvent<DetailTournee, BigDecimal> event) {
    
((DetailTournee) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setQuantiteRecape(event.getNewValue());
         
                retourDefinitifTable.refresh();
                
                qte = qteColumn.getCellData(event.getTablePosition().getRow());

                if(listeDetailTournee.get(retourDefinitifTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Piece")){
                
                
                conditionnement = new BigDecimal(listeDetailTournee.get(retourDefinitifTable.getSelectionModel().getSelectedIndex()).getArticle().getConditionnement());
                
                         qteTotal = (qte.divide(conditionnement,9,RoundingMode.FLOOR)).add(listeDetailTournee.get(retourDefinitifTable.getSelectionModel().getSelectedIndex()).getQuantiteTotalRecape());
                  

                listeDetailTournee.get(retourDefinitifTable.getSelectionModel().getSelectedIndex()).setQuantiteRecape(qte);
                listeDetailTournee.get(retourDefinitifTable.getSelectionModel().getSelectedIndex()).setQuantiteTotalRecape(qteTotal);
                
                  setColumnProperties();
                  
                }else if(listeDetailTournee.get(retourDefinitifTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Kg")){
                
                    
                   qteTotal = qte.add(listeDetailTournee.get(retourDefinitifTable.getSelectionModel().getSelectedIndex()).getQuantiteTotalRecape());
                  

                listeDetailTournee.get(retourDefinitifTable.getSelectionModel().getSelectedIndex()).setQuantiteRecape(qte);
                listeDetailTournee.get(retourDefinitifTable.getSelectionModel().getSelectedIndex()).setQuantiteTotalRecape(qteTotal);
       
                  setColumnProperties();
                }
                
    }

    @FXML
    private void editCommitQuantiteCaisseRetourColumn(TableColumn.CellEditEvent<DetailTournee, BigDecimal> event) {
    
     ((DetailTournee) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setQuantiteCaisseRecape(event.getNewValue());
         
                retourDefinitifTable.refresh();
                
                qteCaisse = qteCaisseColumn.getCellData(event.getTablePosition().getRow());

                if(listeDetailTournee.get(retourDefinitifTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Piece")){
                
                
                qteTotal = listeDetailTournee.get(retourDefinitifTable.getSelectionModel().getSelectedIndex()).getQuantiteTotalRecape().add(qteCaisse);
                  

                listeDetailTournee.get(retourDefinitifTable.getSelectionModel().getSelectedIndex()).setQuantiteCaisseRecape(qteCaisse);
                listeDetailTournee.get(retourDefinitifTable.getSelectionModel().getSelectedIndex()).setQuantiteTotalRecape(qteTotal);
       
                  setColumnProperties();
                
                }else if(listeDetailTournee.get(retourDefinitifTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Kg")){
                
                listeDetailTournee.get(retourDefinitifTable.getSelectionModel().getSelectedIndex()).setQuantiteCaisseRecape(BigDecimal.ZERO.setScale(2));
                retourDefinitifTable.refresh();
                   nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.ERREUR_SAISIE);
                }
    
    }
    
    @FXML
    private void retourCompletifOnAction(ActionEvent event) {
    }

    @FXML
    private void retourOrdinaireOnAction(ActionEvent event) {
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
