/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Stock;

import Utils.Constantes;
import dao.Entity.Article;
import dao.Entity.Cheque;
import dao.Entity.Depot;
import dao.Entity.DetailTransfertStock;
import dao.Entity.Magasin;
import dao.Entity.SituationStock;
//import dao.Entity.MouvementStockGlobal;
import dao.Manager.DepotDAO;
import dao.Manager.DetailTransfertStockDAO;
import dao.ManagerImpl.DepotDAOImpl;
import dao.ManagerImpl.DetailTransfertStockDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class SituationStockController implements Initializable {

     @FXML
    private TableView<SituationStock> DetailMouvementStockTable;
    @FXML
    private TableColumn<SituationStock, String> codeColumn;
    @FXML
    private TableColumn<SituationStock, String> articleColumn;
    @FXML
    private TableColumn<SituationStock, BigDecimal> initialColumn;
    @FXML
    private TableColumn<SituationStock, BigDecimal> receptionColumn;
    @FXML
    private TableColumn<SituationStock, BigDecimal> chargeColumn;
    @FXML
    private TableColumn<SituationStock, BigDecimal> chargeSuppColumn;
    @FXML
    private TableColumn<SituationStock, BigDecimal> retourDefColumn;
    @FXML
    private TableColumn<SituationStock, BigDecimal> transfertEntreesColumn;
    @FXML
    private TableColumn<SituationStock, BigDecimal> transfertSortiesColumn;
    @FXML
    private TableColumn<SituationStock, BigDecimal> sortiesColumn;
    @FXML
    private TableColumn<SituationStock, BigDecimal> finalColumn;
    
    
    @FXML
    private Label prixgroserreur;
    @FXML
    private ComboBox<String> magasinCombo;
    @FXML
    private ImageView rechercheBtn;
    @FXML
    private Button btnImprimer;
    @FXML
    private Button btnRafraichir;
    @FXML
    private DatePicker dateTransfert;
    @FXML
    private ComboBox<String> stockCombo;
    /**
     * Initializes the controller class.
     */
      private Map<String, Depot> mapDepot = new HashMap<>();
    DepotDAO depotDAO = new DepotDAOImpl();

    
    private final ObservableList<SituationStock> listeSituationStock = FXCollections.observableArrayList();
    private final ObservableList<SituationStock> listeSituationStockDate = FXCollections.observableArrayList();
    private final ObservableList<SituationStock> listeSituationStockDateMin = FXCollections.observableArrayList();
    
    ObservableList<String> stock =FXCollections.observableArrayList("Stock Depot","Stock Journée");
    
    
    DetailTransfertStockDAO detailTransfertStockDAO = new DetailTransfertStockDAOImpl();
    
    private Map<String, Magasin> mapMagasin = new HashMap<>();
         navigation nav = new navigation();

         
         
         
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        stockCombo.setItems(stock);
        
        
                    List<Magasin> listMagasin = nav.getUtilisateur().getDepot().getListMagasin();
            listMagasin.stream().map((magasin) -> {
                magasinCombo.getItems().addAll(magasin.getLibelle());
                return magasin;
            }).forEachOrdered((magasin) -> {
                mapMagasin.put(magasin.getLibelle(), magasin);
            });
        
            magasinCombo.setDisable(true);
            dateTransfert.setDisable(true);
            
            
    }    



    void setColumnProperties() {

            
           codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
          
              articleColumn.setCellValueFactory(new PropertyValueFactory<>("article"));
        initialColumn.setCellValueFactory(new PropertyValueFactory<>("initial"));
        receptionColumn.setCellValueFactory(new PropertyValueFactory<>("reception"));
        chargeColumn.setCellValueFactory(new PropertyValueFactory<>("charge"));
        chargeSuppColumn.setCellValueFactory(new PropertyValueFactory<>("chargeSupp"));
        transfertEntreesColumn.setCellValueFactory(new PropertyValueFactory<>("transfertEntree"));
        transfertSortiesColumn.setCellValueFactory(new PropertyValueFactory<>("transfertSortie"));
        sortiesColumn.setCellValueFactory(new PropertyValueFactory<>("sorties"));
        finalColumn.setCellValueFactory(new PropertyValueFactory<>("Final"));
        retourDefColumn.setCellValueFactory(new PropertyValueFactory<>("retour"));
             
      
    }
    
    


    @FXML
    private void magasinCombo(ActionEvent event) {
    }

    @FXML
    private void afficherArt(MouseEvent event) {

    }


    private void viderStock(MouseEvent event) {
        
      magasinCombo.getSelectionModel().select(-1);

    }


    @FXML
    private void rechercheArticle(MouseEvent event) throws ParseException {

        if (  magasinCombo.getSelectionModel().getSelectedIndex()== -1)
        {
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
         
     }else{
                   if (stockCombo.getSelectionModel().getSelectedItem().equals("Stock Depot")){
                       
            listeSituationStock.clear();
            
         Magasin magasin  = mapMagasin.get(magasinCombo.getSelectionModel().getSelectedItem());

          
           List <Object[]> detailTransfertStock =detailTransfertStockDAO.findBySituationStock(magasin.getId());
         
            for(int i=0; i<detailTransfertStock.size(); i++) {

                    Object[] object=detailTransfertStock.get(i);
                   
                    String code = (String.valueOf(object[0])); 
                    String article = (String.valueOf(object[1])); 
                    BigDecimal initial = (BigDecimal)object[2]; 
                    BigDecimal reception = (BigDecimal)object[3]; 
                    BigDecimal charge = (BigDecimal)object[4]; 
                    BigDecimal chargeSupp = (BigDecimal)object[5]; 
                    BigDecimal retour = (BigDecimal)object[6]; 
                    BigDecimal transfertSortie = (BigDecimal)object[7]; 
                    BigDecimal transfertEntree = (BigDecimal)object[8]; 
                    BigDecimal sorties = (BigDecimal)object[9];
                    BigDecimal Final = (BigDecimal)object[10]; 

               SituationStock situationStock = new SituationStock();
               
                  situationStock.setCode(code);  
                  situationStock.setArticle(article);
                  situationStock.setInitial(initial);
                  situationStock.setReception(reception);
                  situationStock.setCharge(charge);
                  situationStock.setChargeSupp(chargeSupp);
                  situationStock.setRetour(retour);
                  situationStock.setTransfertSortie(transfertSortie);
                  situationStock.setTransfertEntree(transfertEntree);
                  situationStock.setSorties(sorties);
                  situationStock.setFinal(Final);
                                                                                  

                  listeSituationStock.add(situationStock);
  
            }
            
             DetailMouvementStockTable.setItems(listeSituationStock);
         setColumnProperties();
         
        }else if (stockCombo.getSelectionModel().getSelectedItem().equals("Stock Journée")){
        
          listeSituationStockDate.clear();
          listeSituationStockDateMin.clear();
            
         Magasin magasin  = mapMagasin.get(magasinCombo.getSelectionModel().getSelectedItem());

         LocalDate localDate=dateTransfert.getValue();
         Date dateTransf=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
          
          
//############################################################################### Date Transfert Min ##############################################################################################################################################################################################      
       
            List <Object[]> detailTransfertStockDateMin =detailTransfertStockDAO.findBySituationStockAndDateMin(magasin.getId(), dateTransf);

           
            for(int i=0; i<detailTransfertStockDateMin.size(); i++) {

                    Object[] object=detailTransfertStockDateMin.get(i);
                   
                    String code = (String.valueOf(object[0])); 
                    String article = (String.valueOf(object[1])); 
                    BigDecimal initial = (BigDecimal)object[2]; 
                    BigDecimal reception = (BigDecimal)object[3]; 
                    BigDecimal charge = (BigDecimal)object[4]; 
                    BigDecimal chargeSupp = (BigDecimal)object[5]; 
                    BigDecimal retour = (BigDecimal)object[6]; 
                    BigDecimal transfertSortie = (BigDecimal)object[7]; 
                    BigDecimal transfertEntree = (BigDecimal)object[8]; 
                    BigDecimal sorties = (BigDecimal)object[9];
                    BigDecimal Final = (BigDecimal)object[10]; 

               SituationStock situationStock = new SituationStock();
               
                  situationStock.setCode(code);  
                  situationStock.setArticle(article);
                  situationStock.setInitial(initial);
                  situationStock.setReception(reception);
                  situationStock.setCharge(charge);
                  situationStock.setChargeSupp(chargeSupp);
                  situationStock.setRetour(retour);
                  situationStock.setTransfertSortie(transfertSortie);
                  situationStock.setTransfertEntree(transfertEntree);
                  situationStock.setSorties(sorties);
                  situationStock.setFinal(Final);
                                                                                  

                  listeSituationStockDateMin.add(situationStock);
  
            }
//############################################################################### Date Transfert ##############################################################################################################################################################################################      
       
            List <Object[]> detailTransfertStockDate =detailTransfertStockDAO.findBySituationStockAndDate(magasin.getId(), dateTransf);

            

            for(int i=0; i<detailTransfertStockDate.size(); i++) {

              boolean valeur = false;
              
                    Object[] object=detailTransfertStockDate.get(i);
                   
                    String code = (String.valueOf(object[0])); 
                    String article = (String.valueOf(object[1])); 
                    BigDecimal initial = (BigDecimal)object[2]; 
                    BigDecimal reception = (BigDecimal)object[3]; 
                    BigDecimal charge = (BigDecimal)object[4]; 
                    BigDecimal chargeSupp = (BigDecimal)object[5]; 
                    BigDecimal retour = (BigDecimal)object[6]; 
                    BigDecimal transfertSortie = (BigDecimal)object[7]; 
                    BigDecimal transfertEntree = (BigDecimal)object[8]; 
                    BigDecimal sorties = (BigDecimal)object[9];
                    BigDecimal Final = (BigDecimal)object[10]; 

      for(int j=0; j<listeSituationStockDateMin.size(); j++) {
                     
                   SituationStock situationStock = listeSituationStockDateMin.get(j);
                     
                     if(situationStock.getCode().equals(code) && situationStock.getArticle().equals(article)){
    
                         valeur = true;
                         
                  situationStock.setCode(code);  
                  situationStock.setArticle(article);
                  situationStock.setInitial(situationStock.getFinal().add(initial));
                  situationStock.setReception(reception);
                  situationStock.setCharge(charge);
                  situationStock.setChargeSupp(chargeSupp);
                  situationStock.setRetour(retour);
                  situationStock.setTransfertSortie(transfertSortie);
                  situationStock.setTransfertEntree(transfertEntree);
                  situationStock.setSorties(sorties);
                  situationStock.setFinal(situationStock.getFinal().add(initial).add(reception).add(transfertEntree).add(retour).subtract(charge).subtract(chargeSupp).subtract(transfertSortie).subtract(sorties));
                                                                                  
                  listeSituationStockDateMin.set(j,situationStock);
                  
            }   
        }
  
      if (valeur== false){
      
          SituationStock situationStock = new SituationStock();
          
                  situationStock.setCode(code);  
                  situationStock.setArticle(article);
                  situationStock.setInitial(initial);
                  situationStock.setReception(reception);
                  situationStock.setCharge(charge);
                  situationStock.setChargeSupp(chargeSupp);
                  situationStock.setRetour(retour);
                  situationStock.setTransfertSortie(transfertSortie);
                  situationStock.setTransfertEntree(transfertEntree);
                  situationStock.setSorties(sorties);
                  situationStock.setFinal(Final);
                                                                                  
                  listeSituationStockDateMin.add(situationStock);
      
      
      }

    }
         DetailMouvementStockTable.setItems(listeSituationStockDateMin);
         setColumnProperties();
        }
        }}
    @FXML
    private void imprimerOnAction(ActionEvent event) {
        
        //                   try {
//          HashMap para = new HashMap();
//            JasperReport report = (JasperReport) JRLoader.loadObject(SituationStockController.class.getResource(nav.getiReportSituationStock()));
//
//            
//              LocalDate localDate=dateStockPicher.getValue();
//            
//          Date dateStock=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());                    
//          
//            Depot depot = mapDepot.get(depotCombo.getSelectionModel().getSelectedItem());
//            
//                 Magasin magasin = mapMagasin.get(magasinCombo.getSelectionModel().getSelectedItem());
//
//            
//            para.put("datemouvement", dateStock);
//            para.put("depot",depot.getLibelle1());
//            para.put("magasin",magasin.getLibelle());
//
//
//             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(DetailMouvementStockTable.getItems()));
//               JasperViewer.viewReport(jp, false);
//            
//        } catch (JRException ex) {
//            Logger.getLogger(SituationStockController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
    }

    @FXML
    private void stockCombo(ActionEvent event) {
        
           if (stockCombo.getSelectionModel().getSelectedItem().equals("Stock Depot")){
        
            dateTransfert.setValue(null);
            magasinCombo.getSelectionModel().select(-1);
               
            magasinCombo.setDisable(false);
            dateTransfert.setDisable(true);
               
               
           }else if (stockCombo.getSelectionModel().getSelectedItem().equals("Stock Journée")){
           
           dateTransfert.setValue(null);
           magasinCombo.getSelectionModel().select(-1);    
               
           magasinCombo.setDisable(false);
           dateTransfert.setDisable(false);
           
           }
    }

}
