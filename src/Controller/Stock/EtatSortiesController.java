/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Stock;

import Utils.Constantes;
import dao.Entity.AvanceChauffeur;
import dao.Entity.AvanceMagasinier;
import dao.Entity.Depot;
import dao.Entity.DetailAvanceChauffeur;
import dao.Entity.DetailAvanceMagasinier;
import dao.Entity.DetailTransfertStock;
import dao.Entity.Magasin;
import dao.Entity.PrixVenteDepot;
import dao.Entity.Secteur;
import dao.Entity.TransfertStock;
import dao.Entity.TypeVente;
import dao.Manager.AvanceChauffeurDAO;
import dao.Manager.AvanceMagasinierDAO;
import dao.Manager.DepotDAO;
import dao.Manager.DetailAvanceChauffeurDAO;
import dao.Manager.DetailAvanceMagasinierDAO;
import dao.Manager.DetailTransfertStockDAO;
import dao.Manager.PrixVenteDepotDAO;
import dao.Manager.SecteurDAO;
import dao.Manager.TransfertStockDAO;
import dao.Manager.TypeVenteDAO;
import dao.ManagerImpl.AvanceChauffeurDAOImpl;
import dao.ManagerImpl.AvanceMagasinierDAOImpl;
import dao.ManagerImpl.DepotDAOImpl;
import dao.ManagerImpl.DetailAvanceChauffeurDAOImpl;
import dao.ManagerImpl.DetailAvanceMagasinierDAOImpl;
import dao.ManagerImpl.DetailTransfertStockDAOImpl;
import dao.ManagerImpl.PrixVenteDepotDAOImpl;
import dao.ManagerImpl.SecteurDAOImpl;
import dao.ManagerImpl.TransfertStockDAOImpl;
import dao.ManagerImpl.TypeVenteDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class EtatSortiesController implements Initializable {

    @FXML
    private TextField codeSortieField;
    @FXML
    private TableView<DetailTransfertStock> detailTransfertStockTable;
    @FXML
    private TableColumn<DetailTransfertStock, String> codeArticleColumn;
    @FXML
    private TableColumn<DetailTransfertStock, String> libelleColumn;
    @FXML
    private TableColumn<DetailTransfertStock, String> qteSortiesGlobalColumn;
    @FXML
    private TableColumn<DetailTransfertStock, BigDecimal> qteColumn;
    @FXML
    private TableColumn<DetailTransfertStock, BigDecimal> qteCaisseColumn;
    @FXML
    private TableColumn<DetailTransfertStock, Integer> conditionnementColumn;
    @FXML
    private TableColumn<DetailTransfertStock, BigDecimal> qteTotalColumn;
    
    @FXML
    private TableView<TransfertStock> transfertStockTable;
    @FXML
    private TableColumn<TransfertStock, String> codeSortieColumn;
    @FXML
    private TableColumn<TransfertStock, Date> dateSortieColumn;
    @FXML
    private TableColumn<TransfertStock, String> depotColumn;
    @FXML
    private TableColumn<TransfertStock, String> magasinColumn;
    @FXML
    private TableColumn<TransfertStock, String> etatColumn;
     
     
    @FXML
    private Button btnValider;
    @FXML
    private Button btnRafraichir;
    @FXML
    private TextArea designationTextArea;
    /**
     * Initializes the controller class.
     */
    
    private final ObservableList<TransfertStock> listeTransfertStock = FXCollections.observableArrayList();
    private final ObservableList<DetailTransfertStock> listeDetailTransfertStock = FXCollections.observableArrayList();
     private final ObservableList<DetailAvanceMagasinier> listeDetailAvanceMagasinier=FXCollections.observableArrayList(); 
     
    DetailTransfertStockDAO detailTransfertStockDAO = new DetailTransfertStockDAOImpl();
    TransfertStockDAO tarnsfertStockDAO = new TransfertStockDAOImpl();
    
      private Map<String, Depot> mapDepot = new HashMap<>();
    DepotDAO depotDAO = new DepotDAOImpl();
    TypeVenteDAO typeVenteDAO = new TypeVenteDAOImpl(); 
     private Map<String, Magasin> mapMagasin = new HashMap<>();
      SecteurDAO secteurDAO = new SecteurDAOImpl();
    
     TransfertStockDAO transfertStockDAO = new TransfertStockDAOImpl();
     DetailAvanceMagasinierDAO detailAvanceMagasinierDAO = new DetailAvanceMagasinierDAOImpl();
     AvanceMagasinierDAO avanceMagasinierDAO = new AvanceMagasinierDAOImpl();

     PrixVenteDepotDAO prixVenteDepotDAO = new PrixVenteDepotDAOImpl();
     private final Map<String, TypeVente> mapTypeVente = new HashMap<>();
     private final Map<String, Secteur> mapSecteur = new HashMap<>();
     
      AvanceMagasinier avanceMagasinier = new AvanceMagasinier();
         navigation nav = new navigation();
         
              TransfertStock transfertStock ;

 
    @FXML
    private ComboBox<String> depotCombo;
    @FXML
    private ComboBox<String> magasinCombo;
    @FXML
    private ImageView rechercheBtn;
    @FXML
    private RadioButton sortieStockRadio;
    @FXML
    private ToggleGroup etatGroupe;
    @FXML
    private RadioButton avanceMagasinierRadio;
    @FXML
    private Button btnImprimer;
    @FXML
    private ComboBox<String> typeVenteCombo;
    @FXML
    private ComboBox<String> secteurCombo;
    
    
    
    
     void setColumnProperties(){
   
        codeSortieColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TransfertStock, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TransfertStock, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getCodeTransfert());
            }
        });
    
        dateSortieColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TransfertStock, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<TransfertStock, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDateTransf());
            }
        });
        
        depotColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TransfertStock, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TransfertStock, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getUtilisateurCreation().getDepot().getLibelle1());
            }
        });
        
         magasinColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TransfertStock, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TransfertStock, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getMagasinStock().getLibelle());
            }
        });
         
          magasinColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TransfertStock, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TransfertStock, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getMagasinStock().getLibelle());
            }
        });
         
          etatColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TransfertStock, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TransfertStock, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getEtat());
            }
        });

     }
    void setDetailColumnProperties(){
   

        
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

        
           qteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailTransfertStock, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailTransfertStock, BigDecimal> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getQuantite());
            }
        });  
           
           qteCaisseColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailTransfertStock, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailTransfertStock, BigDecimal> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getQuantiteCaisse());
            }
        });
           
           
           qteTotalColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailTransfertStock, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailTransfertStock, BigDecimal> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getQuantiteTotal());
            }
        });

           
   }
    
    void loadDetailTmp(){
        
        listeTransfertStock.clear();
        listeTransfertStock.addAll(tarnsfertStockDAO.findByEtat(Constantes.ETAT_STATUT_ATTENTE));
        transfertStockTable.setItems(listeTransfertStock);
        
    }
    
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
                    
        loadDetailTmp();
        setColumnProperties();
        
         secteurCombo.setVisible(false);
        typeVenteCombo.setVisible(false);
  
                }
    }    

    @FXML
    private void codeEntreeTableKeyPressed(KeyEvent event) {
                if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
          if (event.getCode().equals(KeyCode.ENTER))
            {
              
                          listeTransfertStock.clear();
                          listeTransfertStock.addAll(tarnsfertStockDAO.findTransfertStockByCodeSortie(codeSortieField.getText()));
                          transfertStockTable.setItems(listeTransfertStock);
        
                listeDetailTransfertStock.clear();
    }
                }
    }

    
    @FXML
    private void afficherArt(MouseEvent event) {
        
                if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
        
                  setDetailColumnProperties();
        listeDetailTransfertStock.clear();
        
if (transfertStockTable.getSelectionModel().getSelectedIndex()!=-1){
    
        transfertStock = transfertStockTable.getSelectionModel().getSelectedItem();

      listeDetailTransfertStock.addAll(detailTransfertStockDAO.findByTransfertStock(transfertStock.getId()));

        detailTransfertStockTable.setItems(listeDetailTransfertStock);
        
        designationTextArea.setText(transfertStock.getDesignation());
        codeSortieField.setText(transfertStock.getCodeTransfert());
    }
                }
    }

    @FXML
    private void validerOnAction(ActionEvent event) {
        
        if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
        
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
                     if (
              sortieStockRadio.isSelected()== false &&
              avanceMagasinierRadio.isSelected()== false
                  ){

          nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_SELECTIONNER_INITIAL);

        }else{
                  if (transfertStockTable.getSelectionModel().getSelectedIndex()!=-1){
 
              if (sortieStockRadio.isSelected()== true){
                  
                    transfertStock.setEtat(Constantes.ETAT_STATUT_VALIDER);
                    
                    tarnsfertStockDAO.edit(transfertStock);
                        
   nav.showAlert(Alert.AlertType.CONFIRMATION,"Succès", null,Constantes.MESSAGE_ALERT_CHARGE_SUPP);
   vider();
   return; 
                 }else if (avanceMagasinierRadio.isSelected()== true){
                 
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
                      
                   
                      
                        DetailAvanceMagasinier detailAvanceMagasinier = new DetailAvanceMagasinier();
                          
                        PrixVenteDepot prixVenteDepot = prixVenteDepotDAO.findPrixBySecteurAndTypeVente(detailTransfertStock.getArticle().getId(), typeVente.getId(), secteur.getId());
      
       if(prixVenteDepot!=null && prixVenteDepot.getPrix().compareTo(BigDecimal.ZERO)>0){  

                 prixTotal =detailTransfertStock.getQuantiteTotal().multiply(prixVenteDepot.getPrix());

                          detailAvanceMagasinier.setArticle(detailTransfertStock.getArticle());
                          detailAvanceMagasinier.setPrix(prixVenteDepot.getPrix());
                          detailAvanceMagasinier.setMontant(prixTotal);
                          detailAvanceMagasinier.setQuantiteSortie(detailTransfertStock.getQuantiteTotal());

                          detailAvanceMagasinier.setAvanceMagasinier(avanceMagasinier);
                          detailAvanceMagasinier.setUtilisateurCreation(nav.getUtilisateur());
                          detailAvanceMagasinier.setDateCreation(new Date());
                          
                          listeDetailAvanceMagasinier.add(detailAvanceMagasinier);
                          valeur = true;
                          
                          }else{
            articleMessage = articleMessage+detailTransfertStock.getArticle().getLibelle()+" || "; 
           }     
                      
                  }
                  
                    if (articleMessage != ""){
                    detailTransfertStockTable.refresh();
                    nav.showAlert(Alert.AlertType.WARNING, "Attention", null,"Veuillez s'il vous plaît crées les prix des article suivant: "+articleMessage);
                    return;
                    }
                
                    if(valeur == true){
        

                         avanceMagasinier.setMagasin(transfertStock.getMagasinSource());
                         avanceMagasinier.setDepot(transfertStock.getDepotSource());
                         avanceMagasinier.setCodeTransf(transfertStock.getCodeTransfert());
                         avanceMagasinier.setDateTransfert(transfertStock.getDateTransf());
                         avanceMagasinier.setDateCreation(new Date());
                         avanceMagasinier.setDetailAvanceMagasinier(listeDetailAvanceMagasinier);
                         avanceMagasinier.setUtilisateurCreation(nav.getUtilisateur());
                         
                         avanceMagasinierDAO.add(avanceMagasinier);
                         
                     }
//##################################################################################################################################################################################################################################################################################################################################
                    
                    transfertStock.setEtat(Constantes.ETAT_STATUT_VALIDER);
                    
                    tarnsfertStockDAO.edit(transfertStock);
                        
                     vider(); 
                   nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MESSAGE_ALERT_AJOUT);  
                    
               }
                 return; 
              }
               }
          } 
            }
         }
                 }

    
    
    void vider(){
    
        codeSortieField.clear();
         listeDetailTransfertStock.clear();
         
        depotCombo.getSelectionModel().select(-1);
        magasinCombo.getSelectionModel().select(-1);
         
        loadDetailTmp();
        setColumnProperties();
        
       secteurCombo.getItems().clear();
            typeVenteCombo.getItems().clear();
        
        secteurCombo.setVisible(false);
        typeVenteCombo.setVisible(false);
        
        sortieStockRadio.setSelected(false);
        avanceMagasinierRadio.setSelected(false);
        
       
    
    
    }
    

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
        if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
      vider();
        }
        
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
    }
        
    }

    @FXML
    private void magasinComboOnAction(ActionEvent event) {
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
            
            
           List<TransfertStock> transfertStock = tarnsfertStockDAO.findTransfertStockByDepotAndMagasin(depot.getId(), magasin.getId(), Constantes.ETAT_STATUT_ATTENTE, Constantes.ETAT_STATUT_SORTIES);
              listeTransfertStock.addAll(transfertStock);
        transfertStockTable.setItems(listeTransfertStock);

        setColumnProperties();
                                    }
 }
        
    }

    @FXML
    private void sortieStockRadioOnAction(ActionEvent event) {
        
            if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
        if (transfertStockTable.getSelectionModel().getSelectedIndex()!=-1){
        
            secteurCombo.getItems().clear();
            typeVenteCombo.getItems().clear();


        
        secteurCombo.setVisible(false);
        typeVenteCombo.setVisible(false);
        
        }}
        
    }

    @FXML
    private void avanceMagasinierRadioOnAction(ActionEvent event) {
        
            if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
        if (transfertStockTable.getSelectionModel().getSelectedIndex()!=-1){
        
        //Combo typeVente
              List<TypeVente> listTypeVente = typeVenteDAO.findByTypeVenteGrosDetail();

        listTypeVente.stream().map((typeVente) -> {
            typeVenteCombo.getItems().addAll(typeVente.getLibelle());
            return typeVente;
        }).forEachOrdered((typeVente) -> {
            mapTypeVente.put(typeVente.getLibelle(), typeVente);
        });
        
         
        //Combo Secteur
        
            TransfertStock  transfertStockTmp = transfertStockDAO.findTransfertStockByCodeTransAndStatut(transfertStock.getCodeTransfert(),Constantes.ETAT_STATUT_SORTIES);
        
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
    private void imprimerOnAction(ActionEvent event) {
    }

    @FXML
    private void typeVenteComboOnAction(ActionEvent event) {
        
    }

    @FXML
    private void secteurComboOnAction(ActionEvent event) {
    }
    
}
