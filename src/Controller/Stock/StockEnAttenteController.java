/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Stock;

import static Controller.Stock.TransfertController.setColumnTextFieldConverter;
import Utils.Constantes;
import dao.Entity.AvanceChauffeur;
import dao.Entity.Depot;
import dao.Entity.DetailAvanceChauffeur;
import dao.Entity.DetailTransfertStock;
import dao.Entity.Magasin;
import dao.Entity.PrixVenteDepot;
import dao.Entity.Secteur;
import dao.Entity.TransfertStock;
import dao.Entity.TypeVente;
import dao.Manager.AvanceChauffeurDAO;
import dao.Manager.DepotDAO;
import dao.Manager.DetailAvanceChauffeurDAO;
import dao.Manager.DetailTransfertStockDAO;
import dao.Manager.PrixVenteDepotDAO;
import dao.Manager.SecteurDAO;
import dao.Manager.TransfertStockDAO;
import dao.Manager.TypeVenteDAO;
import dao.ManagerImpl.AvanceChauffeurDAOImpl;
import dao.ManagerImpl.DepotDAOImpl;
import dao.ManagerImpl.DetailAvanceChauffeurDAOImpl;
import dao.ManagerImpl.DetailTransfertStockDAOImpl;
import dao.ManagerImpl.PrixVenteDepotDAOImpl;
import dao.ManagerImpl.SecteurDAOImpl;
import dao.ManagerImpl.TransfertStockDAOImpl;
import dao.ManagerImpl.TypeVenteDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class StockEnAttenteController implements Initializable {

    @FXML
    private TableView<DetailTransfertStock> detailTransfertTable;
    @FXML
    private TableColumn<DetailTransfertStock, String> codeArtColumn;
    @FXML
    private TableColumn<DetailTransfertStock, String> designationColumn;
    @FXML
    private TableColumn<DetailTransfertStock, BigDecimal> qteTransfertColumn;
    @FXML
    private TableColumn<DetailTransfertStock, BigDecimal> qteTransfertRecuColumn;
    @FXML
    private TableColumn<DetailTransfertStock, BigDecimal> ecartColumn;
    @FXML
    private ImageView rechercheBtn;
    @FXML
    private Button btnRafraichir;
    @FXML
    private ComboBox<String> magasinCombo;
    @FXML
    private ComboBox<String> depotCombo;
    @FXML
    private Button btnValider;
    @FXML
    private RadioButton erreurSaisieRadio;
    @FXML
    private ToggleGroup etatGroupe;
    @FXML
    private RadioButton quantiteStockRadio;
    @FXML
    private RadioButton avanceChauffeurRadio;
    @FXML
    private RadioButton perteQuantiteRadio;
    
    @FXML
    private TableView<TransfertStock> transfertTable;
    @FXML
    private TableColumn<TransfertStock, String> codeTransColumn;
    @FXML
    private TableColumn<TransfertStock, Date> dateTransColumn;
    @FXML
    private TableColumn<TransfertStock, String> depotColumn;
    @FXML
    private TableColumn<TransfertStock, String> magasinColumn;
    @FXML
    private TableColumn<TransfertStock, String> statutColumn;
    @FXML
    private ComboBox<String> secteurCombo;
    @FXML
    private ComboBox<String> typeVenteCombo;
    
    /**
     * Initializes the controller class.
     */
        private Map<String, Depot> mapDepot = new HashMap<>();
    DepotDAO depotDAO = new DepotDAOImpl();
    TypeVenteDAO typeVenteDAO = new TypeVenteDAOImpl(); 
     private Map<String, Magasin> mapMagasin = new HashMap<>();
      SecteurDAO secteurDAO = new SecteurDAOImpl();
     
     DetailTransfertStockDAO detailTransfertStockDAO = new DetailTransfertStockDAOImpl();
     TransfertStockDAO transfertStockDAO = new TransfertStockDAOImpl();
     DetailAvanceChauffeurDAO detailAvanceChauffeurDAO = new DetailAvanceChauffeurDAOImpl();
     AvanceChauffeurDAO avanceChauffeurDAO = new AvanceChauffeurDAOImpl();

     PrixVenteDepotDAO prixVenteDepotDAO = new PrixVenteDepotDAOImpl();
     private final Map<String, TypeVente> mapTypeVente = new HashMap<>();
     private final Map<String, Secteur> mapSecteur = new HashMap<>();
     
      AvanceChauffeur avanceChauffeur = new AvanceChauffeur();
         navigation nav = new navigation();
         
              TransfertStock transfertStock ;
         
        private final ObservableList<TransfertStock> listeTransfertStock=FXCollections.observableArrayList(); 
        private final ObservableList<DetailTransfertStock> listeDetailTransfertStock=FXCollections.observableArrayList(); 
        private final ObservableList<DetailAvanceChauffeur> listeDetailAvanceChauffeur=FXCollections.observableArrayList(); 
    
    
    
    
    
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

        secteurCombo.setVisible(false);
        typeVenteCombo.setVisible(false);
            
            setColumnProperties();
            loadDetail();
     
                }
    }    

 
       void setColumnDetailProperties(){
   
          codeArtColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailTransfertStock, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailTransfertStock, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getArticle().getCode());
            }
        });
          
          designationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailTransfertStock, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailTransfertStock, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getArticle().getLibelle());
            }
        });
              
           qteTransfertColumn.setCellValueFactory(new PropertyValueFactory<DetailTransfertStock, BigDecimal>("quantiteTotal"));
   
           qteTransfertRecuColumn.setCellValueFactory(new PropertyValueFactory<DetailTransfertStock, BigDecimal>("quantiteTotalRecu"));
           
           ecartColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailTransfertStock, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailTransfertStock, BigDecimal> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getCalculeEcart());
            }
        });
        
   }
       
         void setColumnProperties(){
   
          codeTransColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TransfertStock, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TransfertStock, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getCodeTransfert());
            }
        });
          
          dateTransColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TransfertStock, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<TransfertStock, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDateTransf());
            }
        });
              
             depotColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TransfertStock, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TransfertStock, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDepotSource().getLibelle1());
            }
        });
             
            magasinColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TransfertStock, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TransfertStock, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getMagasinSource().getLibelle());
            }
        });
        
                 statutColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TransfertStock, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TransfertStock, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getStatut());
            }
        });
   }
       
             void loadDetail(){
        
        listeTransfertStock.clear();
        listeTransfertStock.addAll(transfertStockDAO.findByStatutEnAttenteStockTransfert());
        transfertTable.setItems(listeTransfertStock);
    }
       
    
    @FXML
    private void afficherArt(MouseEvent event) {
        
                          setColumnDetailProperties();
        listeDetailTransfertStock.clear();
        
if (transfertTable.getSelectionModel().getSelectedIndex()!=-1){
    
        transfertStock = transfertTable.getSelectionModel().getSelectedItem();

      listeDetailTransfertStock.addAll(detailTransfertStockDAO.findByTransfertStock(transfertStock.getId()));

        detailTransfertTable.setItems(listeDetailTransfertStock);
    
    }
        
        
    }

    @FXML
    private void rechercheArticle(MouseEvent event) {
        
 if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
        if(
          depotCombo.getSelectionModel().getSelectedIndex()== -1 ||
          magasinCombo.getSelectionModel().getSelectedIndex()== -1    
          ) 
          {
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
         
         return;
         
     }else{    	
               listeDetailTransfertStock.clear();
                  listeTransfertStock.clear(); 
                  
              Depot depot  = mapDepot.get(depotCombo.getSelectionModel().getSelectedItem());
              Magasin magasin  = mapMagasin.get(magasinCombo.getSelectionModel().getSelectedItem());
            
            
           List<TransfertStock> transfertStock = transfertStockDAO.findByDepotAndMagasinStockReception(depot.getId(), magasin.getId());
              listeTransfertStock.addAll(transfertStock);
        transfertTable.setItems(listeTransfertStock);

        setColumnProperties();
                                    }
 }
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
         if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
             listeDetailTransfertStock.clear();         
        depotCombo.getSelectionModel().select(-1);
        magasinCombo.getSelectionModel().select(-1);
        
        secteurCombo.getSelectionModel().select(-1);
        typeVenteCombo.getSelectionModel().select(-1);
        
        secteurCombo.setVisible(false);
        typeVenteCombo.setVisible(false);
        
        erreurSaisieRadio.setSelected(false);
        quantiteStockRadio.setSelected(false);
        avanceChauffeurRadio.setSelected(false);
        perteQuantiteRadio.setSelected(false);
        
        setColumnProperties();
            loadDetail();
    }}

    @FXML
    private void magasinComboOnAction(ActionEvent event) {
    }

    @FXML
    private void depotComboOnAction(ActionEvent event) {
         if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
                        try {
            magasinCombo.getItems().clear();
            Depot depot  = mapDepot.get(depotCombo.getSelectionModel().getSelectedItem());
            List<Magasin> listMagasin = depot.getListMagasin();
            listMagasin.stream().map((magasin) -> {
                magasinCombo.getItems().addAll(magasin.getLibelle());
                return magasin;
            }).forEachOrdered((magasin) -> {
                mapMagasin.put(magasin.getLibelle(), magasin);
            });
        } catch (Exception e) {
            
        }
    }}

    @FXML
    private void validerOnAction(ActionEvent event) {
         if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
          if (
              erreurSaisieRadio.isSelected()== false &&
              quantiteStockRadio.isSelected()== false &&
              avanceChauffeurRadio.isSelected()== false &&
              perteQuantiteRadio.isSelected()== false  
                  ){

          nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_SELECTIONNER_INITIAL);

        }else{
               if (transfertTable.getSelectionModel().getSelectedIndex()!=-1){
 
              if (erreurSaisieRadio.isSelected()== true){
                 
                TransfertStock  transfertStockTmp = transfertStockDAO.findTransfertStockByCodeTransAndStatut(transfertStock.getCodeTransfert(),Constantes.ETAT_STATUT_TRANSFERT_SORTIES);
                  
 //################################################################################## Suppresion ==> Transfert Entree ########################################################################################################################################################################################              
 
                  for (int i = 0; i < listeDetailTransfertStock.size(); i++) {
                      
                      DetailTransfertStock detailTransfertStock = listeDetailTransfertStock.get(i);
                      
                       detailTransfertStockDAO.delete(detailTransfertStock);
                  }
               
                  transfertStockDAO.delete(transfertStock);
                  
//############################################################################################################################################################################################################################################################################################################                 
             
               transfertStockTmp.setEtat(Constantes.ETAT_REPRISE);
               transfertStockDAO.edit(transfertStockTmp);
 
                  rafraichirOnAction(event);
                   nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MESSAGE_ALERT_AJOUT);  
                 
              
              return;
              }else if (quantiteStockRadio.isSelected()== true){
                 
                     TransfertStock  transfertStockTmp = transfertStockDAO.findTransfertStockByCodeTransAndStatut(transfertStock.getCodeTransfert(),Constantes.ETAT_STATUT_ATTENTE);
                  
               transfertStockTmp.setStatut(Constantes.ETAT_STATUT_TRANSFERT_ENTREES);
               transfertStockDAO.edit(transfertStockTmp);
 
                  rafraichirOnAction(event);
                   nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MESSAGE_ALERT_AJOUT);  
                  
              return;
              }else if (avanceChauffeurRadio.isSelected()== true){
               if (
               secteurCombo.getSelectionModel().getSelectedIndex()== -1 &&
               typeVenteCombo.getSelectionModel().getSelectedIndex()== -1
                  ){

          nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_SELECTIONNER_INITIAL);
          return; 
        }else{
                   
                   Secteur secteur  = mapSecteur.get(secteurCombo.getSelectionModel().getSelectedItem());
                   TypeVente typeVente  = mapTypeVente.get(typeVenteCombo.getSelectionModel().getSelectedItem());
                   
                    DetailTransfertStock detailTransfertStock = null;
                    boolean valeur = false;
                    BigDecimal prixTotal = BigDecimal.ZERO;
                    String articleMessage = "";
                    
                for (int i = 0; i < listeDetailTransfertStock.size(); i++) {
                      
                       detailTransfertStock = listeDetailTransfertStock.get(i);
                      
                      if (detailTransfertStock.getCalculeEcart().compareTo(BigDecimal.ZERO)!=0){
                      
                        DetailAvanceChauffeur detailAvanceChauffeur = new DetailAvanceChauffeur();
                          
                        PrixVenteDepot prixVenteDepot = prixVenteDepotDAO.findPrixBySecteurAndTypeVente(detailTransfertStock.getArticle().getId(), typeVente.getId(), secteur.getId());
      
       if(prixVenteDepot!=null && prixVenteDepot.getPrix().compareTo(BigDecimal.ZERO)>0){  

                 prixTotal =detailTransfertStock.getCalculeEcart().multiply(prixVenteDepot.getPrix());

                          detailAvanceChauffeur.setArticle(detailTransfertStock.getArticle());
                          detailAvanceChauffeur.setPrix(prixVenteDepot.getPrix());
                          detailAvanceChauffeur.setMontant(prixTotal);
                          detailAvanceChauffeur.setQuantiteTransfert(detailTransfertStock.getQuantiteTotal());
                          detailAvanceChauffeur.setQuantiteTransfertRecu(detailTransfertStock.getQuantiteTotalRecu());
                          detailAvanceChauffeur.setQuantiteManque(detailTransfertStock.getCalculeEcart());
                          detailAvanceChauffeur.setAvanceChauffeur(avanceChauffeur);
                          detailAvanceChauffeur.setUtilisateurCreation(nav.getUtilisateur());
                          detailAvanceChauffeur.setDateCreation(new Date());
                          
                          listeDetailAvanceChauffeur.add(detailAvanceChauffeur);
                          valeur = true;
                          
                          }else{
            articleMessage = articleMessage+detailTransfertStock.getArticle().getLibelle()+" || "; 
           }     
                      }
                  }
                  
                    if (articleMessage != ""){
                    detailTransfertTable.refresh();
                    nav.showAlert(Alert.AlertType.WARNING, "Attention", null,"Veuillez s'il vous plaît crées les prix des article suivant: "+articleMessage);
                    return;
                    }
                
                    if(valeur == true){
        

                         avanceChauffeur.setChauffeur(transfertStock.getChauffeur());
                         avanceChauffeur.setCodeTransf(transfertStock.getCodeTransfert());
                         avanceChauffeur.setDateTransfert(transfertStock.getDateTransf());
                         avanceChauffeur.setDateCreation(new Date());
                         avanceChauffeur.setDetailAvanceChauffeur(listeDetailAvanceChauffeur);
                         avanceChauffeur.setUtilisateurCreation(nav.getUtilisateur());
                         
                         avanceChauffeurDAO.add(avanceChauffeur);
                         
                     }
//##################################################################################################################################################################################################################################################################################################################################
                    
                         TransfertStock  transfertStockTmp = transfertStockDAO.findTransfertStockByCodeTransAndStatut(transfertStock.getCodeTransfert(),Constantes.ETAT_STATUT_ATTENTE);
                  
               transfertStockTmp.setStatut(Constantes.ETAT_STATUT_TRANSFERT_ENTREES);
               transfertStockDAO.edit(transfertStockTmp);
 
                  rafraichirOnAction(event);
                   nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MESSAGE_ALERT_AJOUT);  
                    
               }
                 return; 
              }else if (perteQuantiteRadio.isSelected()== true){
              
                  
                     TransfertStock  transfertStockTmp = transfertStockDAO.findTransfertStockByCodeTransAndStatut(transfertStock.getCodeTransfert(),Constantes.ETAT_STATUT_ATTENTE);
                  
               transfertStockTmp.setStatut(Constantes.ETAT_STATUT_TRANSFERT_ENTREES);
               transfertStockTmp.setEtat(Constantes.ETAT_PERTE);
               transfertStockDAO.edit(transfertStockTmp);
 
                  rafraichirOnAction(event);
                   nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MESSAGE_ALERT_AJOUT);  
                  
                  
              return;
              }
               }
          } 
            }
         }
    }

    @FXML
    private void erreurSaisieRadioOnAction(ActionEvent event) {
         if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
        if (transfertTable.getSelectionModel().getSelectedIndex()!=-1){
        
         secteurCombo.getSelectionModel().select(-1);
        typeVenteCombo.getSelectionModel().select(-1);
        
        secteurCombo.setVisible(false);
        typeVenteCombo.setVisible(false);
        
        }}
    }

    @FXML
    private void quantiteStockRadioOnAction(ActionEvent event) {
         if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
        if (transfertTable.getSelectionModel().getSelectedIndex()!=-1){
        
        secteurCombo.getSelectionModel().select(-1);
        typeVenteCombo.getSelectionModel().select(-1);
        
        secteurCombo.setVisible(false);
        typeVenteCombo.setVisible(false);
        }}
    }

    @FXML
    private void avanceChauffeurRadioOnAction(ActionEvent event) {
        
         if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
        if (transfertTable.getSelectionModel().getSelectedIndex()!=-1){
        
        //Combo typeVente
              List<TypeVente> listTypeVente = typeVenteDAO.findByTypeVenteGrosDetail();

        listTypeVente.stream().map((typeVente) -> {
            typeVenteCombo.getItems().addAll(typeVente.getLibelle());
            return typeVente;
        }).forEachOrdered((typeVente) -> {
            mapTypeVente.put(typeVente.getLibelle(), typeVente);
        });
        
         
        //Combo Secteur
        
            TransfertStock  transfertStockTmp = transfertStockDAO.findTransfertStockByCodeTransAndStatut(transfertStock.getCodeTransfert(),Constantes.ETAT_STATUT_TRANSFERT_SORTIES);
        
            Depot depot  = transfertStockTmp.getDepotSource();
            
             if(depot!=null){
            List<Secteur> listSecteur = secteurDAO.findSecteurByDepot(depot.getId());
            listSecteur.stream().map((secteur) -> {
                secteurCombo.getItems().addAll(secteur.getLibelle());
                return secteur;
            }).forEachOrdered((secteur) -> {
                mapSecteur.put(secteur.getLibelle(), secteur);
            });
             }
        
      
        secteurCombo.setVisible(true);
        typeVenteCombo.setVisible(true);
        }}
    }

    @FXML
    private void perteQuantiteRadioOnAction(ActionEvent event) {
         if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
        if (transfertTable.getSelectionModel().getSelectedIndex()!=-1){
        
         secteurCombo.getSelectionModel().select(-1);
        typeVenteCombo.getSelectionModel().select(-1);
        
        secteurCombo.setVisible(false);
        typeVenteCombo.setVisible(false);
        }}
    }

    @FXML
    private void secteurComboOnAction(ActionEvent event) {
        
        
    }

    @FXML
    private void typeVenteComboOnAction(ActionEvent event) {
        
        
    }
    
}
