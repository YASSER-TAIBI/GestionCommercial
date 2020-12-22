/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Stock;

import Utils.Constantes;
import dao.Entity.Depot;
import dao.Entity.Magasin;
import dao.Entity.SituationStock;
import dao.Entity.SituationStockGlobal;
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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class SituationStockGlobalController implements Initializable {

     @FXML
    private TableView<SituationStockGlobal> DetailMouvementStockTable;
    @FXML
    private TableColumn<SituationStockGlobal, String> codeColumn;
    @FXML
    private TableColumn<SituationStockGlobal, String> articleColumn;
    @FXML
    private TableColumn<SituationStockGlobal, BigDecimal> initialColumn;
    @FXML
    private TableColumn<SituationStockGlobal, BigDecimal> receptionColumn;
    @FXML
    private TableColumn<SituationStockGlobal, BigDecimal> retourDefColumn;
    @FXML
    private TableColumn<SituationStockGlobal, BigDecimal> venteColumn;
    @FXML
    private TableColumn<SituationStockGlobal, BigDecimal> sortiesColumn;
    @FXML
    private TableColumn<SituationStockGlobal, BigDecimal> finalColumn;
    
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

    
    
       private Map<String, Depot> mapDepot = new HashMap<>();
    DepotDAO depotDAO = new DepotDAOImpl();

    
    private final ObservableList<SituationStockGlobal> listeSituationStockGlobal = FXCollections.observableArrayList();
    private final ObservableList<SituationStockGlobal> listeSituationStockDateGlobal = FXCollections.observableArrayList();
    private final ObservableList<SituationStockGlobal> listeSituationStockDateMinGlobal = FXCollections.observableArrayList();
    
    ObservableList<String> stock =FXCollections.observableArrayList("Stock Depot","Stock Journée");
    
    
    DetailTransfertStockDAO detailTransfertStockDAO = new DetailTransfertStockDAOImpl();
    
    private Map<String, Magasin> mapMagasin = new HashMap<>();
         navigation nav = new navigation();

         

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
           stockCombo.setItems(stock);
        
           dateTransfert.setDisable(true);
         }
        
    }    

      void setColumnProperties() {

        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        articleColumn.setCellValueFactory(new PropertyValueFactory<>("article"));
        initialColumn.setCellValueFactory(new PropertyValueFactory<>("initial"));
        receptionColumn.setCellValueFactory(new PropertyValueFactory<>("reception"));
        venteColumn.setCellValueFactory(new PropertyValueFactory<>("vente"));
        sortiesColumn.setCellValueFactory(new PropertyValueFactory<>("sorties"));
        finalColumn.setCellValueFactory(new PropertyValueFactory<>("Final"));
        retourDefColumn.setCellValueFactory(new PropertyValueFactory<>("retour"));
       
    }
    

    @FXML
    private void stockCombo(ActionEvent event) {
        
            if (stockCombo.getSelectionModel().getSelectedItem().equals("Stock Depot")){
        
            dateTransfert.setValue(null);
               
            dateTransfert.setDisable(true);
               
               
           }else if (stockCombo.getSelectionModel().getSelectedItem().equals("Stock Journée")){
           

           dateTransfert.setValue(null);

           dateTransfert.setDisable(false);
           
           }
        
    }



    @FXML
    private void afficherArt(MouseEvent event) {
    }

    @FXML
    private void rechercheArticle(MouseEvent event) throws ParseException {
        
          if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){

                   if (stockCombo.getSelectionModel().getSelectedItem().equals("Stock Depot")){
                       
            listeSituationStockGlobal.clear();
          

          
           List <Object[]> detailTransfertStockGlobal =detailTransfertStockDAO.findBySituationStockAndDepot();
         
            for(int i=0; i<detailTransfertStockGlobal.size(); i++) {

                    Object[] object=detailTransfertStockGlobal.get(i);
                   
                    String code = (String.valueOf(object[0])); 
                    String article = (String.valueOf(object[1])); 
                    BigDecimal initial = (BigDecimal)object[2]; 
                    BigDecimal reception = (BigDecimal)object[3]; 
                    BigDecimal vente = (BigDecimal)object[4]; 
                    BigDecimal retour = (BigDecimal)object[5]; 
                    BigDecimal sorties = (BigDecimal)object[6];
                    BigDecimal Final = (BigDecimal)object[7]; 

               SituationStockGlobal situationStockGlobal = new SituationStockGlobal();
               
                  situationStockGlobal.setCode(code);  
                  situationStockGlobal.setArticle(article);
                  situationStockGlobal.setInitial(initial);
                  situationStockGlobal.setReception(reception);
                  situationStockGlobal.setVente(vente);
                  situationStockGlobal.setRetour(retour);
                  situationStockGlobal.setSorties(sorties);
                  situationStockGlobal.setFinal(Final);
                                                                                  

                  listeSituationStockGlobal.add(situationStockGlobal);
  
            }
            
             DetailMouvementStockTable.setItems(listeSituationStockGlobal);
         setColumnProperties();
         
        }else if (stockCombo.getSelectionModel().getSelectedItem().equals("Stock Journée")){
        
          listeSituationStockDateGlobal.clear();
          listeSituationStockDateMinGlobal.clear();
  
         
         LocalDate localDate=dateTransfert.getValue();
         Date dateTransf=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
          
          
//############################################################################### Date Transfert Min ##############################################################################################################################################################################################      
       
            List <Object[]> detailTransfertStockDateMinGlobal =detailTransfertStockDAO.findBySituationStockAndDateMinAndDepot(dateTransf);

           
            for(int i=0; i<detailTransfertStockDateMinGlobal.size(); i++) {

                    Object[] object=detailTransfertStockDateMinGlobal.get(i);
                   
                    String code = (String.valueOf(object[0])); 
                    String article = (String.valueOf(object[1])); 
                    BigDecimal initial = (BigDecimal)object[2]; 
                    BigDecimal reception = (BigDecimal)object[3]; 
                    BigDecimal vente = (BigDecimal)object[4]; 
                    BigDecimal retour = (BigDecimal)object[5]; 
                    BigDecimal sorties = (BigDecimal)object[6];
                    BigDecimal Final = (BigDecimal)object[7]; 

               SituationStockGlobal situationStockGlobal = new SituationStockGlobal();
               
                  situationStockGlobal.setCode(code);  
                  situationStockGlobal.setArticle(article);
                  situationStockGlobal.setInitial(initial);
                  situationStockGlobal.setReception(reception);
                  situationStockGlobal.setVente(vente);
                  situationStockGlobal.setRetour(retour);
                  situationStockGlobal.setSorties(sorties);
                  situationStockGlobal.setFinal(Final);
                                                                                  

                  listeSituationStockDateMinGlobal.add(situationStockGlobal);
  
            }
//############################################################################### Date Transfert ##############################################################################################################################################################################################      
       
            List <Object[]> detailTransfertStockDateGlobal =detailTransfertStockDAO.findBySituationStockAndDateAndDepot(dateTransf);

            

            for(int i=0; i<detailTransfertStockDateGlobal.size(); i++) {

              boolean valeur = false;
              
                    Object[] object=detailTransfertStockDateGlobal.get(i);
                   
                    String code = (String.valueOf(object[0])); 
                    String article = (String.valueOf(object[1])); 
                    BigDecimal initial = (BigDecimal)object[2]; 
                    BigDecimal reception = (BigDecimal)object[3]; 
                    BigDecimal vente = (BigDecimal)object[4]; 
                    BigDecimal retour = (BigDecimal)object[5]; 
                    BigDecimal sorties = (BigDecimal)object[6];
                    BigDecimal Final = (BigDecimal)object[7]; 

      for(int j=0; j<listeSituationStockDateMinGlobal.size(); j++) {
                     
                   SituationStockGlobal situationStockGlobal = listeSituationStockDateMinGlobal.get(j);
                     
                     if(situationStockGlobal.getCode().equals(code) && situationStockGlobal.getArticle().equals(article)){
    
                         valeur = true;
                         
                  situationStockGlobal.setCode(code);  
                  situationStockGlobal.setArticle(article);
                  situationStockGlobal.setInitial(situationStockGlobal.getFinal().add(initial));
                  situationStockGlobal.setReception(reception);
                  situationStockGlobal.setVente(vente);

                  situationStockGlobal.setRetour(retour);

                  situationStockGlobal.setSorties(sorties);
                  situationStockGlobal.setFinal(situationStockGlobal.getFinal().add(initial).add(reception).add(retour).subtract(vente).subtract(sorties));
                                                                                  
                  listeSituationStockDateMinGlobal.set(j,situationStockGlobal);
                  
            }   
        }
  
      if (valeur== false){
      
          SituationStockGlobal situationStockGlobal = new SituationStockGlobal();
          
                  situationStockGlobal.setCode(code);  
                  situationStockGlobal.setArticle(article);
                  situationStockGlobal.setInitial(initial);
                  situationStockGlobal.setReception(reception);
                  situationStockGlobal.setVente(vente);
                  situationStockGlobal.setRetour(retour);
                  situationStockGlobal.setSorties(sorties);
                  situationStockGlobal.setFinal(Final);
                                                                                  
                  listeSituationStockDateMinGlobal.add(situationStockGlobal);
      
      
      }

    }
         DetailMouvementStockTable.setItems(listeSituationStockDateMinGlobal);
         setColumnProperties();
        }
        }
        
    }

    @FXML
    private void imprimerOnAction(ActionEvent event) {
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
    }
    
}
