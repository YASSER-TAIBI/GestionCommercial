/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.GestionTournee;

import Utils.Constantes;
import dao.Entity.Depot;
import dao.Entity.DetailTournee;
import dao.Entity.DetailVendeurSecteur;
import dao.Entity.Magasin;
import dao.Entity.Secteur;
import dao.Entity.Vendeur;
import dao.Entity.Ville;
import dao.Manager.DepotDAO;
import dao.Manager.DetailTourneeDAO;
import dao.Manager.DetailVendeurSecteurDAO;
import dao.Manager.SecteurDAO;
import dao.Manager.VendeurDAO;
import dao.Manager.VilleDAO;
import dao.ManagerImpl.DepotDAOImpl;
import dao.ManagerImpl.DetailTourneeDAOImpl;
import dao.ManagerImpl.DetailVendeurSecteurDAOImpl;
import dao.ManagerImpl.SecteurDAOImpl;
import dao.ManagerImpl.VendeurDAOImpl;
import dao.ManagerImpl.VilleDAOImpl;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class SituationTourneeController implements Initializable {

    @FXML
    private TableView<DetailTournee> detailTourneeTable;
    @FXML
    private TableColumn<DetailTournee, String> codeArticleColumn;
    @FXML
    private TableColumn<DetailTournee, String> libelleColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteInitialColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteRecapeColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteChargeColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteRetourColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteOffreColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteVenteColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteFinalColumn;
    @FXML
    private ImageView rechercheBtn;
    @FXML
    private DatePicker dateTournee;
    @FXML
    private ComboBox<String> magasinCombo;
    @FXML
    private ComboBox<String> vendeurCombo;
    @FXML
    private ComboBox<String> secteurCombo;
    
    private Map<String, Magasin> mapMagasin = new HashMap<>();

    private Map<String, DetailVendeurSecteur> mapDetailVendeurSecteur = new HashMap<>();
    DetailVendeurSecteurDAO detailVendeurSecteurDAO = new DetailVendeurSecteurDAOImpl();
    
    private Map<String, Ville> mapVille = new HashMap<>();
    VilleDAO villeDAO = new VilleDAOImpl();
    
    private Map<String, Vendeur> mapVendeur = new HashMap<>();
     VendeurDAO vendeurDAO = new VendeurDAOImpl();
    
    private Map<String, Secteur> mapSecteur = new HashMap<>();
    SecteurDAO secteurDAO = new SecteurDAOImpl();
    
    
       DetailTourneeDAO detailTourneeDAO = new DetailTourneeDAOImpl();
        navigation nav = new navigation();
     
      public ObservableList<DetailTournee> listeDetailTournee = FXCollections.observableArrayList();
    
    
    
    
    /**
     * Initializes the controller class.
     */
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
           
           qteChargeColumn.setCellValueFactory(new PropertyValueFactory<DetailTournee, BigDecimal>("quantiteTotalCharge"));

           qteVenteColumn.setCellValueFactory(new PropertyValueFactory<DetailTournee, BigDecimal>("quantiteVente"));
           
           qteOffreColumn.setCellValueFactory(new PropertyValueFactory<DetailTournee, BigDecimal>("quantiteTotalOffre"));
           
           qteRetourColumn.setCellValueFactory(new PropertyValueFactory<DetailTournee, BigDecimal>("quantiteTotalRetour"));
           
           qteFinalColumn.setCellValueFactory(new PropertyValueFactory<DetailTournee, BigDecimal>("quantiteFinal"));
    
     }
    @FXML
    private void afficherArt(MouseEvent event) {
    }

    @FXML
    private void rechercheVendeur(MouseEvent event) throws ParseException {
        
        if(

          secteurCombo.getSelectionModel().getSelectedIndex()== -1 ||
          magasinCombo.getSelectionModel().getSelectedIndex()== -1 ||
          vendeurCombo.getSelectionModel().getSelectedIndex()== -1 ||
          dateTournee.getValue().equals(null)
          )
          {
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
     }
        else{
            
              listeDetailTournee.clear();
            
              LocalDate localDate=dateTournee.getValue();
                Date dateTour=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());

             
             Magasin magasin  = mapMagasin.get(magasinCombo.getSelectionModel().getSelectedItem());
             DetailVendeurSecteur detailVendeurSecteur  = mapDetailVendeurSecteur.get(secteurCombo.getSelectionModel().getSelectedItem());
          

      listeDetailTournee.addAll( detailTourneeDAO.findDetailTourneeByDateAndVendeurAndSecteurAndVilleAndMagasin(dateTour, nav.getUtilisateur().getDepot().getId(), detailVendeurSecteur.getVendeur().getId(),magasin.getId() ,detailVendeurSecteur.getSecteur().getId()));
      
      if (listeDetailTournee.size()!=0){
        detailTourneeTable.setItems(listeDetailTournee);
        setColumnPropertiesDetailTournee();
      }else {
       nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_VERIFIER_COORDONNEES);
      }
    
        }
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
