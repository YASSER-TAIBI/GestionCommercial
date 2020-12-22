/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Vente;

import Utils.Constantes;
import dao.Entity.Banque;
import dao.Entity.Vendeur;
import dao.Entity.Versement;
import dao.Entity.VersementBancaire;
import dao.Manager.BanqueDAO;
import dao.Manager.CaissierDAO;
import dao.Manager.DetailCompteDAO;
import dao.Manager.VendeurDAO;
import dao.Manager.VersementBancaireDAO;
import dao.Manager.VersementDAO;
import dao.ManagerImpl.BanqueDAOImpl;
import dao.ManagerImpl.CaissierDAOImpl;
import dao.ManagerImpl.DetailCompteDAOImpl;
import dao.ManagerImpl.VendeurDAOImpl;
import dao.ManagerImpl.VersementBancaireDAOImpl;
import dao.ManagerImpl.VersementDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ListeVersementBancaireController implements Initializable {

    @FXML
    private ComboBox<String> vendeurCombo;
    @FXML
    private TableView<VersementBancaire> versementBanqueTable;
    @FXML
    private TableColumn<VersementBancaire, String> codeColumn;
    @FXML
    private TableColumn<VersementBancaire, String> nomColumn;
    @FXML
    private TableColumn<VersementBancaire, String> numVersementColumn;
    @FXML
    private TableColumn<VersementBancaire, Date> dateVersementColumn;
    @FXML
    private TableColumn<VersementBancaire, BigDecimal> montantColumn;
    @FXML
    private TableColumn<VersementBancaire, String> numCompteColumn;
    @FXML
    private TableColumn<VersementBancaire, String> BanqueColumn;
    @FXML
    private Button btnRafraichir;
    @FXML
    private TextField montantTotalField;
    @FXML
    private ImageView rechercheBtn;
    @FXML
    private ComboBox<String> banqueCombo;
    @FXML
    private DatePicker dateVersement;

    /**
     * Initializes the controller class.
     */
    private Map<String, Vendeur> mapVendeur = new HashMap<>();
    VendeurDAO vendeurDAO = new VendeurDAOImpl();
    
     private Map<String, Versement> mapVersement = new HashMap<>();
    VersementDAO versementDAO = new VersementDAOImpl();
    
    private Map<String, Banque> mapBanque = new HashMap<>();
    BanqueDAO banqueDAO = new BanqueDAOImpl();
    
        navigation nav = new navigation();

    VersementBancaire versementBancaire = new VersementBancaire();
    
    VersementBancaireDAO versementBancaireDAO = new VersementBancaireDAOImpl();
       
    private final ObservableList<VersementBancaire> listeVersementBancaire = FXCollections.observableArrayList(); 
    
    
     void calculMontantTotal(){
    
        BigDecimal valeur = BigDecimal.ZERO;
        
        for (int i = 0; i < versementBanqueTable.getItems().size() ; i++) {
           VersementBancaire versementBancaire = versementBanqueTable.getItems().get(i);
           
           valeur = valeur.add(versementBancaire.getMontant());
            
            
        }
    
        montantTotalField.setText(valeur+"");
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
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
        
            
                         List<Banque> listBanque = banqueDAO.findAll();
            listBanque.stream().map((banque) -> {
                banqueCombo.getItems().addAll(banque.getLibelle());
                return banque;
            }).forEachOrdered((banque) -> {
                mapBanque.put(banque.getLibelle(), banque);
            });
    
            loadDetail();
            setColumnProperties();
    
            calculMontantTotal();
    }    
    
    

      void setColumnProperties() {

        codeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VersementBancaire, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<VersementBancaire, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getCodeProprietaire());
            }
        });
        
        nomColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VersementBancaire, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<VersementBancaire, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNomProprietaire());
            }
        });

        numVersementColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VersementBancaire, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<VersementBancaire, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNumVersement());
            }
        });

        numCompteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VersementBancaire, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<VersementBancaire, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getCompteVersement().getLibelle());
            }
        });
        
        BanqueColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VersementBancaire, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<VersementBancaire, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getBanque().getLibelle());
            }
        });
        
          dateVersementColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VersementBancaire, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<VersementBancaire, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDateVersement());
            }
        });
                  
                  
                  montantColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VersementBancaire, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<VersementBancaire, BigDecimal> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getMontant());
            }
        });
       
    }
    
          void loadDetail() {

        listeVersementBancaire.clear();
        listeVersementBancaire.addAll(versementBancaireDAO.findAll());
        versementBanqueTable.setItems(listeVersementBancaire);
    }  

    @FXML
    private void vendeurComboOnAction(ActionEvent event) {
    }

    @FXML
    private void afficherArt(MouseEvent event) {
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
        
         vendeurCombo.getSelectionModel().select(-1);
        dateVersement.setValue(null);
        banqueCombo.getSelectionModel().select(-1);
        
        loadDetail();
        setColumnProperties();  
        
        calculMontantTotal();
        
    }

    @FXML
    private void rechercheArticle(MouseEvent event) throws ParseException {
        
           
        Date dt = null;
        
          LocalDate localDate=dateVersement.getValue();
          if (localDate!= null){
           dt=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
          }
               
        
        Vendeur vendeur = mapVendeur.get(vendeurCombo.getSelectionModel().getSelectedItem());
        
        Banque banque = mapBanque.get(banqueCombo.getSelectionModel().getSelectedItem());

        
        if( vendeur !=null && dt == null && banque == null ){
        
            listeVersementBancaire.clear();
         listeVersementBancaire.addAll(versementBancaireDAO.findByVendeur(vendeur.getId(), nav.getUtilisateur().getDepot().getId()));
        versementBanqueTable.setItems(listeVersementBancaire);
        calculMontantTotal();
        
        }else if(vendeur == null && dt != null && banque == null){
        
            listeVersementBancaire.clear();
        listeVersementBancaire.addAll(versementBancaireDAO.findByDate(dt, nav.getUtilisateur().getDepot().getId()));
        versementBanqueTable.setItems(listeVersementBancaire);
        calculMontantTotal();
        
        }else if(vendeur==null && dt == null && banque!=null ){
        
            listeVersementBancaire.clear();
        listeVersementBancaire.addAll(versementBancaireDAO.findByBanque(banque.getId(), nav.getUtilisateur().getDepot().getId()));
        versementBanqueTable.setItems(listeVersementBancaire);
        calculMontantTotal();
        
        }else if(vendeur!=null && dt != null && banque==null){
        
            listeVersementBancaire.clear();
        listeVersementBancaire.addAll(versementBancaireDAO.findByVendeurAndDate(vendeur.getId(),dt ,nav.getUtilisateur().getDepot().getId()));
        versementBanqueTable.setItems(listeVersementBancaire);
        calculMontantTotal();
        
        }else if(vendeur!=null && dt == null && banque!=null){
        
            listeVersementBancaire.clear();
        listeVersementBancaire.addAll(versementBancaireDAO.findByVendeurAndBanque(vendeur.getId(),banque.getId() ,nav.getUtilisateur().getDepot().getId()));
        versementBanqueTable.setItems(listeVersementBancaire);
        calculMontantTotal();
        
        }else if(vendeur==null && dt != null && banque!=null){
        
            listeVersementBancaire.clear();
        listeVersementBancaire.addAll(versementBancaireDAO.findByDateAndBanque(dt,banque.getId() ,nav.getUtilisateur().getDepot().getId()));
        versementBanqueTable.setItems(listeVersementBancaire);
        calculMontantTotal();
        
        }else if(vendeur!=null && dt != null && banque!=null){
        
            listeVersementBancaire.clear();
        listeVersementBancaire.addAll(versementBancaireDAO.findByVendeurAndDateAndBanque(vendeur.getId(),dt ,banque.getId(),nav.getUtilisateur().getDepot().getId()));
        versementBanqueTable.setItems(listeVersementBancaire);
        calculMontantTotal();
        
        }else{
        
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null,Constantes.MESSAGE_ALERT_VERIFIER_COORDONNEES);
            calculMontantTotal();
        }
        
    }

    @FXML
    private void banqueComboOnAction(ActionEvent event) {
    }
    
}
