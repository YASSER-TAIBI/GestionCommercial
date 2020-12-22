/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.Compte;
import dao.Entity.Secteur;
import dao.Entity.DetailVendeurSecteur;
import dao.Entity.TypeVente;
import dao.Entity.Vehicule;
import dao.Entity.Vendeur;
import dao.Entity.Ville;
import dao.Manager.VendeurDAO;
import dao.ManagerImpl.CompteDAOImpl;
import dao.ManagerImpl.VendeurDAOImpl;
import function.navigation;
import java.net.URL;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import dao.Manager.CompteDAO;
import dao.Manager.DetailVendeurSecteurDAO;
import dao.Manager.SecteurDAO;
import dao.Manager.TypeVenteDAO;
import dao.ManagerImpl.VehiculeDAOImpl;
import dao.Manager.VehiculeDAO;
import dao.Manager.VilleDAO;
import dao.ManagerImpl.DetailVendeurSecteurDAOImpl;
import dao.ManagerImpl.SecteurDAOImpl;
import dao.ManagerImpl.TypeVenteDAOImpl;
import dao.ManagerImpl.VilleDAOImpl;
import java.math.BigDecimal;
import java.util.Date;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class VendeurController implements Initializable {

   
    @FXML
    private TableView<DetailVendeurSecteur> tableVendeur;
    @FXML
    private TableColumn<DetailVendeurSecteur, String> codeColumn;
    @FXML
    private TableColumn<DetailVendeurSecteur, String> secteurColumn;
    @FXML
    private TableColumn<DetailVendeurSecteur, Date> dateColumn;
    
    @FXML
    private Button btnAjouter;
    @FXML
    private TextField codeField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField telField;
    @FXML
    private Button btnRafraichir;
    
    @FXML
    private ComboBox<String> vehiculeCombo;
    @FXML
    private ComboBox<String> typeVentecombo;
    @FXML
    private ComboBox<String> secteurCombo;
    @FXML
    private ComboBox<String> villeCombo;
    
    @FXML
    private ImageView ajoutBtn;
    @FXML
    private ImageView videBtn;
    @FXML
    private TextField montantPlafondField;

    VendeurDAO vendeurdao = new VendeurDAOImpl();
    DetailVendeurSecteurDAO detailVendeurSecteurDAO = new DetailVendeurSecteurDAOImpl();
    CompteDAO compteDAO = new CompteDAOImpl();
    VilleDAO villeDAO = new VilleDAOImpl();
    VehiculeDAO vehiculeDAO = new VehiculeDAOImpl();
    SecteurDAO secteurDAO = new SecteurDAOImpl();
    TypeVenteDAO typeVenteDAO = new TypeVenteDAOImpl(); 
    
    navigation nav = new navigation();
    
    boolean msgerrue = false;
    
    private final ObservableList<DetailVendeurSecteur> listeVendeurSecteur = FXCollections.observableArrayList();
    
    
    private final Map<String, Compte> mapCompte = new HashMap<>();
    private final Map<String, Vehicule> mapVehicule = new HashMap<>();
    private final Map<String, TypeVente> mapTypeVente = new HashMap<>();
    private final Map<String, Secteur> mapSecteur = new HashMap<>();
    private final Map<String, Ville> mapVille = new HashMap<>();
    
    

    Vendeur vendeur = new Vendeur();
    
    
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

            
        charger();
        

        
    }

    public void charger() {
        
        //liste typeVente
        List<TypeVente> listTypeVente = typeVenteDAO.findAll();

        listTypeVente.stream().map((typeVente) -> {
            typeVentecombo.getItems().addAll(typeVente.getLibelle());
            return typeVente;
        }).forEachOrdered((typeVente) -> {
            mapTypeVente.put(typeVente.getLibelle(), typeVente);
        });
        
        //liste vehicule
        List<Vehicule> listVehicule = vehiculeDAO.findByVehiculeAndDisponibilities();

        listVehicule.stream().map((vehicule) -> {
            vehiculeCombo.getItems().addAll(vehicule.getMatricule());
            return vehicule;
        }).forEachOrdered((vehicule) -> {
            mapVehicule.put(vehicule.getMatricule(), vehicule);
        });


        //liste Ville
        List<Ville> listVille = villeDAO.findVilleByDepot(nav.getUtilisateur().getDepot().getId());

        listVille.stream().map((ville) -> {
            villeCombo.getItems().addAll(ville.getLibelle());
            return ville;
        }).forEachOrdered((ville) -> {
            mapVille.put(ville.getLibelle(), ville);
        });
        
    }

    void loadDetail() {

        listeVendeurSecteur.clear();
        listeVendeurSecteur.addAll(detailVendeurSecteurDAO.findAll());
        tableVendeur.setItems(listeVendeurSecteur);
    }

    void setColumnProperties() {

        codeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailVendeurSecteur, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailVendeurSecteur, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getSecteur().getCode());
            }
        });
        dateColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailVendeurSecteur, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<DetailVendeurSecteur, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDateCreation());
            }
        });

        secteurColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailVendeurSecteur, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailVendeurSecteur, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getSecteur().getLibelle());
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
            telField.getText().isEmpty() ||
            montantPlafondField.getText().isEmpty()
                ) {
            
              nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
    
        } else {
//########################################################## Creation de Compte ###############################################################################################################################################################################################             

    Compte compte = new  Compte();
         
       compte.setCode("CMP/V_"+nomField.getText().toUpperCase());
       compte.setLibelle(nomField.getText());
       compte.setUtilisateurCreation(nav.getUtilisateur());

          compteDAO.add(compte);

//########################################################## Creation de Vendeur ###############################################################################################################################################################################################             

            vendeur.setCode(codeField.getText());
            vendeur.setNom(nomField.getText());
            vendeur.setTelephone(telField.getText());
          
            Vehicule vehicule = mapVehicule.get(vehiculeCombo.getSelectionModel().getSelectedItem());
            vendeur.setVehicule(vehicule);

            TypeVente typeVente = mapTypeVente.get(typeVentecombo.getSelectionModel().getSelectedItem());
            vendeur.setTypeVente(typeVente);
        
            vendeur.setDetailVendeurSecteurs(listeVendeurSecteur);
            vendeur.setMontantPlafond(new BigDecimal(montantPlafondField.getText()));
            vendeur.setCompte(compte);
            vendeurdao.add(vendeur);

            vendeur = new Vendeur();
//#########################################################################################################################################################################################################################################################             

              vehicule.setOccupeDisponible(true);
              vehiculeDAO.edit(vehicule);

              
              //Charge Combo Vehicule
              vehiculeCombo.getItems().clear();
               List<Vehicule> listVehicule = vehiculeDAO.findByVehiculeAndDisponibilities();

        listVehicule.stream().map((vehiculeTMP) -> {
            vehiculeCombo.getItems().addAll(vehiculeTMP.getMatricule());
            return vehiculeTMP;
        }).forEachOrdered((vehiculeTMP) -> {
            mapVehicule.put(vehiculeTMP.getMatricule(), vehiculeTMP);
        });
//#########################################################################################################################################################################################################################################################             

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
        montantPlafondField.setText(Constantes.CHAMP_VIDE);
        
        vehiculeCombo.getSelectionModel().select(-1);
        typeVentecombo.getSelectionModel().select(-1);
        villeCombo.getSelectionModel().select(-1);
        secteurCombo.getSelectionModel().select(-1);

        listeVendeurSecteur.clear();
        
    }

    @FXML
    private void ajouter(MouseEvent event) {
        
         if (codeField.getText().isEmpty() ||
            nomField.getText().isEmpty() ||
            telField.getText().isEmpty() ||
            secteurCombo.getSelectionModel().getSelectedIndex()== -1 ||
            typeVentecombo.getSelectionModel().getSelectedIndex()== -1 ||     
            vehiculeCombo.getSelectionModel().getSelectedIndex()== -1 ||
            villeCombo.getSelectionModel().getSelectedIndex()== -1 ||
            montantPlafondField.getText().isEmpty()
                ) {
            
              nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
    
        } else {

            DetailVendeurSecteur detailVendeurSecteur = new DetailVendeurSecteur();
           
            detailVendeurSecteur.setUtilisateurCreation(nav.getUtilisateur());
            detailVendeurSecteur.setDateCreation(new Date());

            Secteur secteur = mapSecteur.get(secteurCombo.getSelectionModel().getSelectedItem());
            detailVendeurSecteur.setSecteur(secteur);
            detailVendeurSecteur.setVendeur(vendeur);
            listeVendeurSecteur.add(detailVendeurSecteur);
             
             setColumnProperties();

            tableVendeur.setItems(listeVendeurSecteur);
             vider(event);
             
        }
 
        
    }

    @FXML
    private void vider(MouseEvent event) {
        
        villeCombo.getSelectionModel().select(-1);
        secteurCombo.getSelectionModel().select(-1);
        
    }

   

    @FXML
    private void secteurComboOnAction(ActionEvent event) {

    }

    @FXML
    private void villeComboOnAction(ActionEvent event) {
        
          secteurCombo.getItems().clear();
          
            Ville ville  = mapVille.get(villeCombo.getSelectionModel().getSelectedItem());

             if(ville!=null){
            List<Secteur> listSecteur = secteurDAO.findSecteurByVille(ville.getId());
            listSecteur.stream().map((secteur) -> {
                secteurCombo.getItems().addAll(secteur.getLibelle());
                return secteur;
            }).forEachOrdered((secteur) -> {
                mapSecteur.put(secteur.getLibelle(), secteur);
            });
             }
        
    }
}