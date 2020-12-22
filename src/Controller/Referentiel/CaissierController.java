/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.Caissier;
import dao.Entity.Compte;
import dao.Entity.Depot;
import dao.Entity.DetailCaissierVendeur;
import dao.Entity.DetailVendeurSecteur;
import dao.Entity.Secteur;
import dao.Entity.TypeVente;
import dao.Entity.Vendeur;
import dao.Manager.DepotDAO;
import dao.Manager.CaissierDAO;
import dao.Manager.CompteDAO;
import dao.Manager.DetailCaissierVendeurDAO;
import dao.Manager.DetailVendeurSecteurDAO;
import dao.Manager.SecteurDAO;
import dao.Manager.VendeurDAO;
import dao.ManagerImpl.DepotDAOImpl;
import dao.ManagerImpl.CaissierDAOImpl;
import dao.ManagerImpl.CompteDAOImpl;
import dao.ManagerImpl.DetailCaissierVendeurDAOImpl;
import dao.ManagerImpl.DetailVendeurSecteurDAOImpl;
import dao.ManagerImpl.SecteurDAOImpl;
import dao.ManagerImpl.VendeurDAOImpl;
import function.navigation;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class CaissierController implements Initializable {

    @FXML
    private TextField codeField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField telField;
    
    
    
    @FXML
    private TableView<DetailCaissierVendeur> tableVendeur;
    @FXML
    private TableColumn<DetailCaissierVendeur, String> vendeurColumn;
    @FXML
    private TableColumn<DetailCaissierVendeur, Date> dateColumn;
    @FXML
    private TableColumn<DetailCaissierVendeur, String> codeColumn;

    @FXML
    private ImageView ajoutBtn;
    @FXML
    private ImageView videBtn;
    @FXML
    private ComboBox<String> secteurCombo;
    @FXML
    private ComboBox<String> vendeurCombo;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRafraichir;

    /**
     * Initializes the controller class.
     */
    
    private final ObservableList<DetailCaissierVendeur> listeDetailCaissierVendeur = FXCollections.observableArrayList();
    CaissierDAO caissierDAO =new CaissierDAOImpl();
    SecteurDAO secteurDAO = new SecteurDAOImpl();
    VendeurDAO vendeurDAO = new VendeurDAOImpl();
    DetailVendeurSecteurDAO detailVendeurSecteurDAO = new DetailVendeurSecteurDAOImpl();
    DetailCaissierVendeurDAO detailCaissierVendeurDAO = new DetailCaissierVendeurDAOImpl();
    boolean  msgerrue = false;
    navigation nav = new navigation();

    private final Map<String, Secteur> mapSecteur = new HashMap<>();
    private final Map<String, DetailVendeurSecteur> mapDetailVendeurSecteur = new HashMap<>();
    
    CompteDAO  compteDAO = new CompteDAOImpl();

    Caissier caissier = new Caissier();

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        charger();
        
    }    

    
     public void charger() {



        //liste Secteur
        List<Secteur> listSecteur = secteurDAO.findSecteurByDepot(nav.getUtilisateur().getDepot().getId());

        listSecteur.stream().map((secteur) -> {
            secteurCombo.getItems().addAll(secteur.getLibelle());
            return secteur;
        }).forEachOrdered((secteur) -> {
            mapSecteur.put(secteur.getLibelle(), secteur);
        });
        
    }
    


    void loadDetail() {

        listeDetailCaissierVendeur.clear();
        listeDetailCaissierVendeur.addAll(detailCaissierVendeurDAO.findAll());
        tableVendeur.setItems(listeDetailCaissierVendeur);
    }

    void setColumnProperties() {

        codeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCaissierVendeur, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCaissierVendeur, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getVendeur().getCode());
            }
        });
        dateColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCaissierVendeur, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<DetailCaissierVendeur, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDateCreation());
            }
        });

        vendeurColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCaissierVendeur, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCaissierVendeur, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getVendeur().getNom());
            }
        });

          
       
    }
    

    @FXML
    private void afficherArt(MouseEvent event) {  
    }

    @FXML
    private void ajouterOnAction(ActionEvent event) {
        
           if (codeField.getText().isEmpty() || 
            nomField.getText().isEmpty() ||
            telField.getText().isEmpty() 

                ) {
            
              nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
    
        } else {

               
//############################################################### Creation de Compte ##########################################################################################################################################################################################             
  
           Compte compte = new  Compte();
         
       compte.setCode("CMP/C_"+nomField.getText().toUpperCase());
       compte.setLibelle(nomField.getText());
       compte.setUtilisateurCreation(nav.getUtilisateur());

          compteDAO.add(compte);

//############################################################### Creation de Caissier ##########################################################################################################################################################################################             
      
               
            caissier.setCode(codeField.getText());
            caissier.setNom(nomField.getText());
            caissier.setTelephone(telField.getText());
            
            caissier.setDetailCaissierVendeur(listeDetailCaissierVendeur);
            
            caissier.setDepot(nav.getUtilisateur().getDepot());
            caissier.setUtilisateurCreation(nav.getUtilisateur());
            caissier.setDateCreation(new Date());
            caissier.setCompte(compte);
            
            caissierDAO.add(caissier);

            caissier = new Caissier();

            nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_AJOUT);
            setColumnProperties();
            loadDetail();
              rafraichirOnAction(event);

        }
 
        
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
        
        codeField.setText(Constantes.CHAMP_VIDE);
        nomField.setText(Constantes.CHAMP_VIDE);
        telField.setText(Constantes.CHAMP_VIDE);
        
        secteurCombo.getSelectionModel().select(-1);
        vendeurCombo.getSelectionModel().select(-1);


        listeDetailCaissierVendeur.clear();
        
        
    }
    

    @FXML
    private void ajouter(MouseEvent event) {
        
               if (
            codeField.getText().isEmpty() ||
            nomField.getText().isEmpty() ||
            telField.getText().isEmpty() ||
            secteurCombo.getSelectionModel().getSelectedIndex()== -1 ||
            vendeurCombo.getSelectionModel().getSelectedIndex()== -1     
                ) {
            
              nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
    
        } else {

            DetailCaissierVendeur detailCaissierVendeur = new DetailCaissierVendeur();
           
            detailCaissierVendeur.setUtilisateurCreation(nav.getUtilisateur());
            detailCaissierVendeur.setDateCreation(new Date());

            DetailVendeurSecteur detailVendeurSecteur = mapDetailVendeurSecteur.get(vendeurCombo.getSelectionModel().getSelectedItem());
            detailCaissierVendeur.setCaissier(caissier);
            detailCaissierVendeur.setVendeur(detailVendeurSecteur.getVendeur());
            listeDetailCaissierVendeur.add(detailCaissierVendeur);
             
             setColumnProperties();

            tableVendeur.setItems(listeDetailCaissierVendeur);
             vider(event);
             
        }
 
        
    }

    @FXML
    private void vider(MouseEvent event) {
        
                vendeurCombo.getSelectionModel().select(-1);
        secteurCombo.getSelectionModel().select(-1);
        
    }

    @FXML
    private void secteurComboOnAction(ActionEvent event) {
        
                  vendeurCombo.getItems().clear();
          
            Secteur secteur  = mapSecteur.get(secteurCombo.getSelectionModel().getSelectedItem());

             if(secteur!=null){
            List<DetailVendeurSecteur> listDetailVendeurSecteur = detailVendeurSecteurDAO.findDetailVendeurSecteurBySecteur(secteur.getId());
            listDetailVendeurSecteur.stream().map((vendeur) -> {
                vendeurCombo.getItems().addAll(vendeur.getVendeur().getNom());
                return vendeur;
            }).forEachOrdered((vendeur) -> {
                mapDetailVendeurSecteur.put(vendeur.getVendeur().getNom(), vendeur);
            });
             }
        
        
    }

    
}
