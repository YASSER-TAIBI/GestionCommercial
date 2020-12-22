/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Stock;

import static Controller.Stock.TransfertController.setColumnTextFieldConverter;
import Utils.Constantes;
import dao.Entity.Depot;
import dao.Entity.DetailTransfertStock;
import dao.Entity.Magasin;
import dao.Entity.Sequenceur;
import dao.Entity.TransfertStock;
import dao.Manager.DepotDAO;
import dao.Manager.DetailTransfertStockDAO;
import dao.Manager.MagasinDAO;
import dao.Manager.SequenceurDAO;
import dao.Manager.TransfertStockDAO;
import dao.ManagerImpl.DepotDAOImpl;
import dao.ManagerImpl.DetailTransfertStockDAOImpl;
import dao.ManagerImpl.MagasinDAOImpl;
import dao.ManagerImpl.SequenceurDAOImpl;
import dao.ManagerImpl.TransfertStockDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class CorrectionTransfertSortiesController implements Initializable {

    @FXML
    private ComboBox<String> magasinCombo;
    @FXML
    private ImageView rechercheBtn;
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
    private Button btnValider;
    @FXML
    private Button btnRafraichir;
    @FXML
    private TableView<DetailTransfertStock> detailTransfertTable;
    @FXML
    private TableColumn<DetailTransfertStock, String> codeArticleColumn;
    @FXML
    private TableColumn<DetailTransfertStock, String> libelleColumn;
    @FXML
    private TableColumn<DetailTransfertStock, String> qteTransfertGlobalColumn;
    @FXML
    private TableColumn<DetailTransfertStock, BigDecimal> qteColumn;
    @FXML
    private TableColumn<DetailTransfertStock, BigDecimal> qteCaisseColumn;
    @FXML
    private TableColumn<DetailTransfertStock, BigDecimal> qteTotalColumn;
  
    @FXML
    private ComboBox<String> depotCombo;
    
      private Map<String, Magasin> mapMagasin = new HashMap<>();
      private Map<String, Depot> mapDepot = new HashMap<>();
    MagasinDAO magasinDAO = new MagasinDAOImpl();
    
        public BigDecimal qte = BigDecimal.ZERO;  
        public BigDecimal qteCaisse = BigDecimal.ZERO;  
        public BigDecimal qteTotal = BigDecimal.ZERO;
        public BigDecimal conditionnement= BigDecimal.ZERO;
        public BigDecimal qteRecu =BigDecimal.ZERO;
        public BigDecimal qteCaisseRecu =BigDecimal.ZERO;


            TransfertStock transfertStock = new TransfertStock();
            DetailTransfertStock detailTransfertStock = new DetailTransfertStock();
            
    DepotDAO depotDAO = new DepotDAOImpl();
    DetailTransfertStockDAO detailTransfertStockDAO = new DetailTransfertStockDAOImpl();
     TransfertStockDAO transfertStockDAO = new TransfertStockDAOImpl();
         
    navigation nav = new navigation();
    
   private final ObservableList<TransfertStock> listeTransfertStock=FXCollections.observableArrayList(); 
   private final ObservableList<DetailTransfertStock> listeDetailTransfertStock = FXCollections.observableArrayList();
    
    

    /**
     * Initializes the controller class.
     */
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
            
            
            setColumnProperties();
            loadDetail();
                
                 detailTransfertTable.setEditable(true); 
        
    }    }
    
            void loadDetail(){
        
              
                
        listeTransfertStock.clear();
        listeTransfertStock.addAll(transfertStockDAO.findByEtatTransfertSortie(Constantes.ETAT_REPRISE, Constantes.ETAT_STATUT_TRANSFERT_SORTIES));
        transfertTable.setItems(listeTransfertStock);
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
              
           qteColumn.setCellValueFactory(new PropertyValueFactory<DetailTransfertStock, BigDecimal>("quantite"));
           setColumnTextFieldConverter(getConverter(), qteColumn);
           
           qteCaisseColumn.setCellValueFactory(new PropertyValueFactory<DetailTransfertStock, BigDecimal>("quantiteCaisse"));
           setColumnTextFieldConverter(getConverter(), qteCaisseColumn);
           
           qteTotalColumn.setCellValueFactory(new PropertyValueFactory<DetailTransfertStock, BigDecimal>("quantiteTotal"));
   
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
    private void magasinComboOnAction(ActionEvent event) {
    }

    @FXML
    private void rechercheArticle(MouseEvent event) {
         if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
        if(
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
            
            
           List<TransfertStock> transfertStock = transfertStockDAO.findTransfertStockByDepotAndMagasin(depot.getId(), magasin.getId(), Constantes.ETAT_REPRISE ,Constantes.ETAT_STATUT_TRANSFERT_SORTIES);
              listeTransfertStock.addAll(transfertStock);
        transfertTable.setItems(listeTransfertStock);

        setColumnProperties();
                                    }
        
    }
    }

    @FXML
    private void afficherArt(MouseEvent event) {
        
         if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
        setDetailColumnProperties();
        listeDetailTransfertStock.clear();
        
if (transfertTable.getSelectionModel().getSelectedIndex()!=-1){
    
        transfertStock = transfertTable.getSelectionModel().getSelectedItem();

      listeDetailTransfertStock.addAll(detailTransfertStockDAO.findByTransfertStock(transfertStock.getId()));

        detailTransfertTable.setItems(listeDetailTransfertStock);

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
        
        if (transfertTable.getSelectionModel().getSelectedIndex()== -1){

          nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_SELECTIONNER_TRANSFERT_MODIFIER);

        }else{
        
          String articleMessage = "";
                     for (int i = 0; i < listeDetailTransfertStock.size(); i++) {
   
                             DetailTransfertStock detailTransfertStock = listeDetailTransfertStock.get(i);
                             
            List <Object[]> objectDetailTransfert =detailTransfertStockDAO.findByQteFinal(detailTransfertStock.getArticle().getId(),transfertStock.getMagasinStock().getId(),transfertStock.getDepotSource().getId());

            System.out.println("objectDetailTransfert "+objectDetailTransfert.size());
         
            BigDecimal Final = BigDecimal.ZERO;
            
            for(int j=0; j<objectDetailTransfert.size(); j++) {

                    Object[] object= objectDetailTransfert.get(j);
                   
              Final = (BigDecimal)object[1]; 
              
            }
                       if(Final.compareTo(detailTransfertStock.getQuantiteTotal())>=0){      
            
              detailTransfertStock.setQuantiteCaisseRecu(BigDecimal.ZERO.setScale(2));
              detailTransfertStock.setQuantiteRecu(BigDecimal.ZERO.setScale(2));
              detailTransfertStock.setUtilisateurCreation(nav.getUtilisateur());
              detailTransfertStock.setDateCreation(new Date() );

             detailTransfertStockDAO.edit(detailTransfertStock);
             
               }else{
                       
                    articleMessage = articleMessage+detailTransfertStock.getArticle().getLibelle()+" || "; 
                       detailTransfertStock.setQuantite(BigDecimal.ZERO.setScale(2));
                       detailTransfertStock.setQuantiteCaisse(BigDecimal.ZERO.setScale(2));
                       detailTransfertStock.setQuantiteTotal(BigDecimal.ZERO.setScale(2));
                       detailTransfertStock.setQuantiteStock(BigDecimal.ZERO.setScale(2));
                        detailTransfertStockDAO.edit(detailTransfertStock);
                       }
                     }
              if (articleMessage != ""){
                
                   
                   detailTransfertTable.refresh();

        nav.showAlert(Alert.AlertType.WARNING, "Attention", null,"Veuillez s'il vous plaît charger au stock les articles suivant: "+articleMessage);
                    return;
      
               }
              
//#############################################################################################################################################################################################################################################################################################################################################################################################################
          
        
        transfertStock.setEtat(Constantes.ETAT_ENVOYER);
        transfertStockDAO.edit(transfertStock);

            rafraichirOnAction(event);
          nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MESSAGE_ALERT_AJOUT);  
          }
  } }
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
         if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
        listeDetailTransfertStock.clear();         
        magasinCombo.getSelectionModel().select(-1);
        depotCombo.getSelectionModel().select(-1);
        setColumnProperties();
            loadDetail();
    }
    }

    @FXML
    private void editCommitQuantiteColumn(TableColumn.CellEditEvent<DetailTransfertStock, BigDecimal> event) {
        
                if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
                    
         BigDecimal OldValeurQte = listeDetailTransfertStock.get(detailTransfertTable.getSelectionModel().getSelectedIndex()).getQuantite();
        
          ((DetailTransfertStock) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setQuantite(event.getNewValue());
        
                detailTransfertTable.refresh();
                
                qte = qteColumn.getCellData(event.getTablePosition().getRow());

                 if(listeDetailTransfertStock.get(detailTransfertTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Piece")){
                
                
                conditionnement = new BigDecimal(listeDetailTransfertStock.get(detailTransfertTable.getSelectionModel().getSelectedIndex()).getArticle().getConditionnement());
                
                         qteTotal = (qte.divide(conditionnement,9,RoundingMode.FLOOR)).add(listeDetailTransfertStock.get(detailTransfertTable.getSelectionModel().getSelectedIndex()).getQuantiteTotal().subtract(OldValeurQte.divide(conditionnement,9,RoundingMode.FLOOR)));
                  

                listeDetailTransfertStock.get(detailTransfertTable.getSelectionModel().getSelectedIndex()).setQuantite(qte);
                listeDetailTransfertStock.get(detailTransfertTable.getSelectionModel().getSelectedIndex()).setQuantiteTotal(qteTotal);
                listeDetailTransfertStock.get(detailTransfertTable.getSelectionModel().getSelectedIndex()).setQuantiteStock(qteTotal);
       
                  setColumnProperties();
                  
                }else if(listeDetailTransfertStock.get(detailTransfertTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Kg")){
                
                    
                   qteTotal = qte.add(listeDetailTransfertStock.get(detailTransfertTable.getSelectionModel().getSelectedIndex()).getQuantiteTotal().subtract(OldValeurQte));
                  

                listeDetailTransfertStock.get(detailTransfertTable.getSelectionModel().getSelectedIndex()).setQuantite(qte);
                listeDetailTransfertStock.get(detailTransfertTable.getSelectionModel().getSelectedIndex()).setQuantiteTotal(qteTotal);
                listeDetailTransfertStock.get(detailTransfertTable.getSelectionModel().getSelectedIndex()).setQuantiteStock(qteTotal);
       
                  setColumnProperties();
                }
    }
    }

    @FXML
    private void editCommitQuantiteCaisseColumn(TableColumn.CellEditEvent<DetailTransfertStock, BigDecimal> event) {
        
     if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
         
         BigDecimal OldValeurQteCaisse = listeDetailTransfertStock.get(detailTransfertTable.getSelectionModel().getSelectedIndex()).getQuantiteCaisse();    
        
         ((DetailTransfertStock) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setQuantiteCaisse(event.getNewValue());
         
                   detailTransfertTable.refresh();
                
                qteCaisse = qteCaisseColumn.getCellData(event.getTablePosition().getRow());

                if(listeDetailTransfertStock.get(detailTransfertTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Piece")){
                
                
                qteTotal = listeDetailTransfertStock.get(detailTransfertTable.getSelectionModel().getSelectedIndex()).getQuantiteTotal().subtract(OldValeurQteCaisse).add(qteCaisse);
                  

                listeDetailTransfertStock.get(detailTransfertTable.getSelectionModel().getSelectedIndex()).setQuantiteCaisse(qteCaisse);
                listeDetailTransfertStock.get(detailTransfertTable.getSelectionModel().getSelectedIndex()).setQuantiteTotal(qteTotal);
                listeDetailTransfertStock.get(detailTransfertTable.getSelectionModel().getSelectedIndex()).setQuantiteStock(qteTotal);
       
                  setColumnProperties();
                
                }else if(listeDetailTransfertStock.get(detailTransfertTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Kg")){
                
                listeDetailTransfertStock.get(detailTransfertTable.getSelectionModel().getSelectedIndex()).setQuantiteCaisse(BigDecimal.ZERO.setScale(2));
                detailTransfertTable.refresh();
                   nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.ERREUR_SAISIE);
                } 
        
        
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

 

    
}
