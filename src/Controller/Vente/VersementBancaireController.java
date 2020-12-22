/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Vente;

import Utils.Constantes;
import dao.Entity.Banque;
import dao.Entity.Caissier;
import dao.Entity.CompteVersement;
import dao.Entity.DetailCompte;
import dao.Entity.Tournee;
import dao.Entity.Vendeur;
import dao.Entity.Versement;
import dao.Entity.VersementBancaire;
import dao.Entity.VersementCheque;
import dao.Manager.BanqueDAO;
import dao.Manager.CaissierDAO;
import dao.Manager.CompteVersementDAO;
import dao.Manager.DetailCompteDAO;
import dao.Manager.VendeurDAO;
import dao.Manager.VersementBancaireDAO;
import dao.Manager.VersementDAO;
import dao.ManagerImpl.BanqueDAOImpl;
import dao.ManagerImpl.CaissierDAOImpl;
import dao.ManagerImpl.CompteVersementDAOImpl;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class VersementBancaireController implements Initializable {

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
    private Button btnAjouter;
    @FXML
    private Button btnRafraichir;
    @FXML
    private TextField codeVendeurField;
    @FXML
    private TextField numVersementField;
    @FXML
    private TextField MontantField;
    @FXML
    private DatePicker dateVersement;
    @FXML
    private TextField nomVendeurField;
    @FXML
    private ImageView ajoutBtn;
    @FXML
    private ImageView videBtn;
    @FXML
    private TextField montantTotalField;
    @FXML
    private TextField montantBancaireField;
    @FXML
    private ComboBox<String> versementCombo;
    @FXML
    private ComboBox<String> banqueCombo;
    @FXML
    private ComboBox<String> numCompteCombo;
    @FXML
    private ImageView supprimerBtn;
    
    
    private Map<String, Vendeur> mapVendeur = new HashMap<>();
    VendeurDAO vendeurDAO = new VendeurDAOImpl();

    private Map<String, Versement> mapVersement = new HashMap<>();
    VersementDAO versementDAO = new VersementDAOImpl();
    
          private Map<String, CompteVersement> mapCompteVersement = new HashMap<>();
    CompteVersementDAO compteVersementDAO = new CompteVersementDAOImpl();
    
    private Map<String, Banque> mapBanque = new HashMap<>();
    BanqueDAO banqueDAO = new BanqueDAOImpl();
    
    navigation nav = new navigation();

    VersementBancaire versementBancaire = new VersementBancaire();
    
    VersementBancaireDAO versementBancaireDAO = new VersementBancaireDAOImpl();
    
    DetailCompteDAO detailCompteDAO = new DetailCompteDAOImpl();
    
    CaissierDAO caissierDAO = new CaissierDAOImpl();
    
    private final ObservableList<VersementBancaire> listeVersementBancaire = FXCollections.observableArrayList(); 
    
    
    
    
    
    /**
     * Initializes the controller class.
     */
    
    void calculMontantTotal(){
    
        BigDecimal valeur = BigDecimal.ZERO;
        
        for (int i = 0; i < listeVersementBancaire.size(); i++) {
           VersementBancaire versementBancaire = listeVersementBancaire.get(i);
           
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
            
                                  List<CompteVersement> listCompteVersement = compteVersementDAO.findAll();
            listCompteVersement.stream().map((compteVersement) -> {
                numCompteCombo.getItems().addAll(compteVersement.getLibelle());
                return compteVersement;
            }).forEachOrdered((compteVersement) -> {
                mapCompteVersement.put(compteVersement.getLibelle(), compteVersement);
            });
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
        
            versementCombo.getItems().clear();
          
            Vendeur vendeur  = mapVendeur.get(vendeurCombo.getSelectionModel().getSelectedItem());
            
             if(vendeur!=null){
            List<Versement> listVersement = versementDAO.findVersemenBancairetByTournee(vendeur.getId(),Constantes.ETAT_STATUT_ATTENTE);
            listVersement.stream().map((versement) -> {
                versementCombo.getItems().addAll(versement.getCodeVersement());
                return versement;
            }).forEachOrdered((versement) -> {
                mapVersement.put(versement.getCodeVersement(), versement);
            });
             }
    }


    @FXML
    private void afficherArt(MouseEvent event) {
    }

    @FXML
    private void ajouterOnAction(ActionEvent event) {
        
        
                         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
         if(vendeurCombo.getSelectionModel().getSelectedIndex()== -1 || 
          versementCombo.getSelectionModel().getSelectedIndex()== -1 
  
                ){
             
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
         return;
        }else{
                if (new BigDecimal(montantBancaireField.getText()).compareTo(new BigDecimal(montantTotalField.getText()))==0 ){

             
               Versement versement = mapVersement.get(versementCombo.getSelectionModel().getSelectedItem());

                versement.setEtatVersementBancaire(true);
        
            if(versement.getMontantCheque().compareTo(BigDecimal.ZERO)>0 && versement.isEtatVersementCheque()== true){
            versement.setEtat(Constantes.ETAT_STATUT_VALIDER);
            
            }else if (versement.getMontantCheque().compareTo(BigDecimal.ZERO)==0){ 
            versement.setEtat(Constantes.ETAT_STATUT_VALIDER);
            }
            
                    
      versement.setVersementBancaires(listeVersementBancaire);
      versement.setMontantVersementBancaire(new BigDecimal(montantTotalField.getText()));

            versementDAO.edit(versement);

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Caissier <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


//Montant Bancaire

                for (int i = 0; i < listeVersementBancaire.size(); i++) {
                    
                   VersementBancaire versementBancaire = listeVersementBancaire.get(i);
                    
                      DetailCompte detailCompteBanque = new DetailCompte();
 
             detailCompteBanque.setMontantDebit(versementBancaire.getMontant());
             detailCompteBanque.setMontantCredit(BigDecimal.ZERO);
             detailCompteBanque.setDateOperation(versementBancaire.getVersement().getTournee().getDateTournee());
             detailCompteBanque.setEtat(Constantes.ETAT_VERSEMENT_BANCAIRE);
             detailCompteBanque.setDateCreation(new Date());
             detailCompteBanque.setUtilisateurCreation(nav.getUtilisateur());
             detailCompteBanque.setCompte(versementBancaire.getVersement().getCaissier().getCompte());
             detailCompteBanque.setDesignation(Constantes.REGLEMENT_RECU_BANQUE_SUR_TOURNEE+versementBancaire.getVersement().getCodeVersement());
 
             detailCompteDAO.add(detailCompteBanque);
                    
                }           
            
            versement = new Versement();
            
            setColumnProperties();
            loadDetail();
              rafraichirOnAction(event);

        }else{
                 nav.showAlert(Alert.AlertType.ERROR, "Erreur", null,Constantes.MESSAGE_ALERT_VERIFIER_MONTANT);
                 return;
                }
            }
            }
        
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
        
        vendeurCombo.getSelectionModel().select(-1);
        dateVersement.setValue(null);
        versementCombo.getSelectionModel().select(-1);
        montantBancaireField.clear();
        codeVendeurField.clear();
        nomVendeurField.clear();
        numVersementField.clear();
        MontantField.clear();
        numCompteCombo.getSelectionModel().select(-1);
        banqueCombo.getSelectionModel().select(-1);
        montantTotalField.clear();
        listeVersementBancaire.clear();
        versementBanqueTable.setItems(listeVersementBancaire);
    }

    @FXML
    private void codeArticleKeyPressed(KeyEvent event) {
    }

    @FXML
    private void ajouter(MouseEvent event) throws ParseException {
        
                   if(vendeurCombo.getSelectionModel().getSelectedIndex()== -1 || 
          dateVersement.getValue()==null || 
          versementCombo.getSelectionModel().getSelectedIndex()== -1 || 
          numCompteCombo.getSelectionModel().getSelectedIndex()== -1 || 
          montantBancaireField.getText().isEmpty() ||
                codeVendeurField.getText().isEmpty() ||
              nomVendeurField.getText().isEmpty() ||     
                numVersementField.getText().isEmpty() ||
                    MontantField.getText().isEmpty()     
                ){

         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
        }else{
        
                       LocalDate localDate=dateVersement.getValue();
            Date dateVers =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
            
            CompteVersement CompteVersement = mapCompteVersement.get(numCompteCombo.getSelectionModel().getSelectedItem());
            
            Versement versement = mapVersement.get(versementCombo.getSelectionModel().getSelectedItem());
            Banque banque = mapBanque.get(banqueCombo.getSelectionModel().getSelectedItem());
             
//############################################################################## Verification Num Versement 'Liste' ########################################################################################################################################################################################################################################                            
                  
            
                       Boolean exist = false;
            
                       for (int i = 0; i < listeVersementBancaire.size(); i++) {
                           
                           VersementBancaire versementBancaire = listeVersementBancaire.get(i);
                           
                           if (versementBancaire.getNumVersement().equals(numVersementField.getText())){
                            
                           exist = true;
                            break;
                           } 
                       }
 //############################################################################## Verification Num Versement 'DataBase' ########################################################################################################################################################################################################################################                  
 

                       List<VersementBancaire> listVersementBancaire = versementBancaireDAO.findAll();

                      for (int i = 0; i < listVersementBancaire.size(); i++) {
                         
                      VersementBancaire versementBancaire = listVersementBancaire.get(i);
                      
                      if(versementBancaire.getNumVersement().equals(numVersementField.getText())){
                      
                      exist = true;
                            break;
                      
                      } 
                     }                      
                       
                       if (exist == true){
                       
                           nav.showAlert(Alert.AlertType.ERROR, "Erreur", null,Constantes.MESSAGE_ALERT_EXISTE);
                           vider(event);
                           return;
                           
                       }else{

            VersementBancaire versementBancaire = new VersementBancaire();
            
            versementBancaire.setCodeProprietaire(codeVendeurField.getText());
            versementBancaire.setNomProprietaire(nomVendeurField.getText());
            versementBancaire.setNumVersement(numVersementField.getText());
            versementBancaire.setDateVersement(dateVers);
            versementBancaire.setVersement(versement);
            versementBancaire.setUtilisateurCreation(nav.getUtilisateur());
            versementBancaire.setDateCreation(new Date());
            versementBancaire.setBanque(banque);
            versementBancaire.setCompteVersement(CompteVersement);
            versementBancaire.setMontant(new BigDecimal(MontantField.getText()));
            
            listeVersementBancaire.add(versementBancaire);
           
              setColumnProperties();
            versementBanqueTable.setItems(listeVersementBancaire);
            calculMontantTotal();
             vider(event);
                       }
        }
        
        
        
    }

    @FXML
    private void vider(MouseEvent event) {
 
        numVersementField.clear();
        numCompteCombo.getSelectionModel().select(-1);
        banqueCombo.getSelectionModel().select(-1);
        dateVersement.setValue(null);
        MontantField.clear();
        
    }

    @FXML
    private void versementComboOnAction(ActionEvent event) {
        
        montantBancaireField.clear();
          
            Versement versement  = mapVersement.get(versementCombo.getSelectionModel().getSelectedItem());

            
            if(versement!= null){
                
            montantBancaireField.setText(versement.getMontantBanque()+"");
            codeVendeurField.setText(versement.getTournee().getVendeur().getCode());
            nomVendeurField.setText(versement.getTournee().getVendeur().getNom());
            }
                    
        
    }

    @FXML
    private void supprimer(MouseEvent event) {
        
            if(versementBanqueTable.getSelectionModel().getSelectedIndex()!=-1)
        {
            if(listeVersementBancaire.size()!=0)
            {
                
                listeVersementBancaire.remove(versementBanqueTable.getSelectionModel().getSelectedIndex());

            }
                int i=0;
        BigDecimal montanttotal=BigDecimal.ZERO;
        while(i<listeVersementBancaire.size())
        {
            VersementBancaire versementBancaire =listeVersementBancaire.get(i);
           montanttotal= montanttotal.add(versementBancaire.getMontant());
            i++;
        }
        montantTotalField.setText(montanttotal+"");

        }
        
    }
    
}
