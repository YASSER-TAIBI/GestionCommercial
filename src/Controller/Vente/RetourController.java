/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Vente;

import Utils.Constantes;
import dao.Entity.Article;
import dao.Entity.Depot;
import dao.Entity.DetailCompte;
//import com.sun.corba.se.impl.oa.toa.TOA;
import dao.Entity.DetailTournee;
import dao.Entity.Magasin;
import dao.Entity.PrixVenteDepot;
import dao.Entity.Secteur;
import dao.Entity.Sequenceur;
import dao.Entity.Tournee;
import dao.Entity.TypeVente;
import dao.Entity.Vendeur;
import dao.Entity.VenteMixte;
import dao.Manager.CompteDAO;
import dao.Manager.DetailCompteDAO;
import dao.Manager.DetailTourneeDAO;
import dao.Manager.DetailVenteMixteDAO;
import dao.Manager.PrixVenteDepotDAO;
import dao.Manager.SequenceurDAO;
import dao.Manager.TourneeDAO;
import dao.Manager.TypeVenteDAO;
import dao.Manager.VendeurDAO;
import dao.Manager.VenteMixteDAO;
import dao.ManagerImpl.CompteDAOImpl;
import dao.ManagerImpl.DetailCompteDAOImpl;
import dao.ManagerImpl.DetailTourneeDAOImpl;
import dao.ManagerImpl.DetailVenteMixteDAOImpl;
import dao.ManagerImpl.PrixVenteDepotDAOImpl;
import dao.ManagerImpl.SequenceurDAOImpl;
import dao.ManagerImpl.TourneeDAOImpl;
import dao.ManagerImpl.TypeVenteDAOImpl;
import dao.ManagerImpl.VendeurDAOImpl;
import dao.ManagerImpl.VenteMixteDAOImpl;
import function.navigation;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;



/**
 * FXML Controller class
 *
 * @author Hp
 */



public class RetourController implements Initializable {

    @FXML
    private TableView<DetailTournee> detailTourneeTable;
    @FXML
    private TableColumn<DetailTournee, String> codeArticleColumn;
    @FXML
    private TableColumn<DetailTournee, String> libelleColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteTotalChargeColumn;
    @FXML
    private TableColumn<DetailTournee, String> qteRetourGlobalColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteRetourCaisseColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteRecapeColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteTotalRetourColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteRetourColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteInitialColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteTotalColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteVenteColumn;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRafraichir;
    
    @FXML
    private TableView<Tournee> tourneeTable;
    @FXML
    private TableColumn<Tournee, Date> dateTourneeColumn;
    @FXML
    private TableColumn<Tournee, String> vendeurColumn;
    @FXML
    private TableColumn<Tournee, String> secteurColumn;
    @FXML
    private TableColumn<Tournee, String> magasinColumn;
    @FXML
    private TableColumn<Tournee, String> etatColumn;
    

    @FXML
    private TextField libelleField;
    @FXML
    private TextArea designationTextArea;
    @FXML
    private Label codeerreur;
    @FXML
    private ComboBox<String> vendeurCombo;
    /**
     * Initializes the controller class.
     */


      Tournee tournee ;
    TourneeDAO tourneeDAO = new TourneeDAOImpl();
    DetailCompteDAO detailCompteDAO = new DetailCompteDAOImpl();
    PrixVenteDepotDAO prixVenteDepotDAO = new PrixVenteDepotDAOImpl();
   
    private Map<String, Vendeur> mapVendeur = new HashMap<>();
    VendeurDAO vendeurDAO = new VendeurDAOImpl();
    TypeVenteDAO typeVenteDAO = new TypeVenteDAOImpl();
    DetailVenteMixteDAO detailVenteMixteDAO = new DetailVenteMixteDAOImpl();
    
    
    
    CompteDAO compteDAO = new CompteDAOImpl();
  
     navigation nav = new navigation();
     DetailTourneeDAO detailTourneeDAO = new DetailTourneeDAOImpl();
     VenteMixteDAO venteMixteDAO = new VenteMixteDAOImpl();
     SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();  
     
      private final ObservableList<DetailTournee> listeDetailTournee = FXCollections.observableArrayList();
      private final ObservableList<PrixVenteDepot> listePrixVenteDepot = FXCollections.observableArrayList();
      private final ObservableList<Tournee> listeTournee = FXCollections.observableArrayList();
    
    
     public BigDecimal qteRetour = BigDecimal.ZERO;  
     public BigDecimal qteRetourCaisse = BigDecimal.ZERO;  
     public BigDecimal qteRetourTotal = BigDecimal.ZERO;
     public BigDecimal conditionnement= BigDecimal.ZERO;
     public BigDecimal qteTotalInitialCharge =  BigDecimal.ZERO;    
     public BigDecimal qteVente = BigDecimal.ZERO;
    

    

  void Incrementation (){
       
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.RETOUR);
          libelleField.setText(sequenceur.getCode()+" "+(sequenceur.getValeur()+1));

   }
    
  
       void loadDetail() {

        listeTournee.clear();
        listeTournee.addAll(tourneeDAO.findTourneeByEtat(Constantes.ETAT_EN_COURS, nav.getUtilisateur().getDepot().getId()));
        tourneeTable.setItems(listeTournee);
    }
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        loadDetail();
        setColumnPropertiesTournee();

        Incrementation ();
         
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
        
        
        detailTourneeTable.setEditable(true); 

    }    

    void setColumnPropertiesTournee(){
   
        dateTourneeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Tournee, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<Tournee, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDateTournee());
            }
        });
          
        vendeurColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Tournee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Tournee, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getVendeur().getNom());
            }
        });
           
        secteurColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Tournee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Tournee, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getSecteur().getLibelle());
            }
        });
        
        magasinColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Tournee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Tournee, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getMagasin().getLibelle());
            }
        });
        
        etatColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Tournee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Tournee, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getEtat());
            }
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
           
           qteTotalChargeColumn.setCellValueFactory(new PropertyValueFactory<DetailTournee, BigDecimal>("quantiteTotalCharge"));

           qteTotalColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailTournee, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailTournee, BigDecimal> param) {
                return new ReadOnlyObjectWrapper(param.getValue().qteTotalInitialCharge());
            }
        });

           qteRetourColumn.setCellValueFactory(new PropertyValueFactory<DetailTournee, BigDecimal>("quantiteRetour"));
           setColumnTextFieldConverter(getConverter(), qteRetourColumn);
           
           qteRetourCaisseColumn.setCellValueFactory(new PropertyValueFactory<DetailTournee, BigDecimal>("quantiteRetourCaisse"));
           setColumnTextFieldConverter(getConverter(), qteRetourCaisseColumn);
           
           qteTotalRetourColumn.setCellValueFactory(new PropertyValueFactory<DetailTournee, BigDecimal>("quantiteTotalRetour"));
           
           qteVenteColumn.setCellValueFactory(new PropertyValueFactory<DetailTournee, BigDecimal>("quantiteVente"));
           
         
   }
   
  
    @FXML
    private void afficherArt(MouseEvent event) {
        
                setColumnPropertiesDetailTournee();
        listeDetailTournee.clear();
        
if (tourneeTable.getSelectionModel().getSelectedIndex()!=-1){
    
  
    
        tournee = tourneeTable.getSelectionModel().getSelectedItem();

      listeDetailTournee.addAll(detailTourneeDAO.findDetailTourneeByTournee(tournee));
     
//           for (int i = 0; i < listeDetailTournee.size() ; i++) {
//               
//               DetailTournee detailTournee = listeDetailTournee.get(i);
//               
//               detailTournee.setQuantiteFinal(nav.getFinal(detailTournee.getQuantiteInitial(), detailTournee.getQuantiteCharge(), detailTournee.getQuantiteChargeSupp(), detailTournee.getQuantiteRetour(), detailTournee.getQuantiteVente()));
//     
//               listeDetailTournee.set(i, detailTournee);
//               
//               detailTourneeDAO.edit(detailTournee);
//                       
//    }

        detailTourneeTable.setItems(listeDetailTournee);

}
 
    }


   void viider(){
    
        detailTourneeTable.getItems().clear();
        designationTextArea.clear();
                 loadDetail();
        setColumnPropertiesTournee();
     
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
    private void ajouterOnAction(ActionEvent event) {

                         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
        if(
       
          libelleField.getText().isEmpty() ||
          tourneeTable.getItems().isEmpty() ||
          detailTourneeTable.getItems().isEmpty()){
              
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
         return;
     }
        else {
//####################################################### Calcule Total Vente ################################################################################################################################################################################################################
                       
                        DetailTournee detailTournee = null;
                        BigDecimal prixTotal = BigDecimal.ZERO;
                        String articleMessage = "";
                        
                            for (int i = 0; i < listeDetailTournee.size(); i++) {
                                
                                detailTournee = listeDetailTournee.get(i);
                                
                               Article article = detailTournee.getArticle();
                               TypeVente typeVente = detailTournee.getTypeVente();
                               Secteur secteur = detailTournee.getTournee().getSecteur();
                               
    if(typeVente.getCode().equals(Constantes.ETAT_TYPE_VENTE_D)|| typeVente.getCode().equals(Constantes.ETAT_TYPE_VENTE_G)){
                            
              PrixVenteDepot prixVenteDepot = prixVenteDepotDAO.findPrixBySecteurAndTypeVente(article.getId(), typeVente.getId(), secteur.getId());
      
       if(prixVenteDepot!=null && prixVenteDepot.getPrix().compareTo(BigDecimal.ZERO)>0){  

                 prixTotal =detailTournee.getQuantiteVente().multiply(prixVenteDepot.getPrix());
                 
                 detailTournee.setTotalVendue(prixTotal);
                 detailTournee.setQuantiteFinal(detailTournee.getQuantiteTotalRetour());
                 
            }else{
            articleMessage = articleMessage+article.getLibelle()+" || "; 
           }
       } 
                            }
               if (articleMessage != ""){
                
                   
                   detailTourneeTable.refresh();
            detailTourneeTable.getItems().clear();
//         listeDetailTournee.clear();
//      listeDetailTournee.addAll(detailTourneeDAO.findDetailTourneeByTournee(tournee));
//      detailTourneeTable.setItems(listeDetailTournee); 
        nav.showAlert(Alert.AlertType.WARNING, "Attention", null,"Veuillez s'il vous plaît crées les prix des article suivant: "+articleMessage);
                    return;
      
               }
                 
//#############################################################################################################################################################################################################################################################################################################################################################################################################

              String CodeRetour= libelleField.getText();
             
              SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
          
              String dateFormat2=dateFormat.format(tournee.getDateTournee());
              

        tournee.setCodeRetour(CodeRetour+"/"+tournee.getVendeur().getNom()+"/"+dateFormat2);
        tournee.setDetailTournee(listeDetailTournee);
        tournee.setEtat(Constantes.ETAT_VENTE);
        tournee.setDesignation(designationTextArea.getText());
        tourneeDAO.edit(tournee);
                        

           Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.RETOUR);
           sequenceur.setValeur(sequenceur.getValeur()+1);
           sequenceurDAO.edit(sequenceur);
           Incrementation();
    
              viider();         
                        
  }
            }
    } 

    

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
        
            viider();
    }

    @FXML
    private void editCommitQuantiteRetourColumn(CellEditEvent<DetailTournee, BigDecimal> event) {

        BigDecimal valeur = BigDecimal.ZERO;
        BigDecimal QteTotalInitialCharge = BigDecimal.ZERO;
        BigDecimal QtePiece = BigDecimal.ZERO;

        
              ((DetailTournee) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setQuantiteRetour(event.getNewValue());
              
      if(listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Piece")){            
              
          conditionnement = new BigDecimal(listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getArticle().getConditionnement());
          
          valeur = listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).qteTotalInitialCharge().setScale(0, RoundingMode.DOWN);
          
          QteTotalInitialCharge= listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).qteTotalInitialCharge().subtract(valeur);
                  
          QtePiece= QteTotalInitialCharge.multiply(conditionnement).setScale(0, RoundingMode.UP);
          
                     if (event.getNewValue().compareTo(QtePiece)>0){
 
                         
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(Constantes.MESSAGE_ALERT_saisir_Quantite);
            alert.setTitle("ERROR");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
   
             listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteRetour(BigDecimal.ZERO.setScale(2));
             detailTourneeTable.refresh();
             return;
             
            }


                     }else{

                   qteRetour = qteRetourColumn.getCellData(event.getTablePosition().getRow());
                  
                
                detailTourneeTable.refresh();

                          qteRetourTotal = (qteRetour.divide(conditionnement,9,RoundingMode.FLOOR)).add(listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getQuantiteRetourCaisse());
                  

                  qteTotalInitialCharge = listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).qteTotalInitialCharge();
                   
                  qteVente = qteTotalInitialCharge.subtract(qteRetourTotal);
               
                  String val = String.valueOf(qteVente);
                  
               String[] qte = val.split("\\.");
                            
                        System.out.println("qteVente.toString() "+qteVente.toString());
                        System.out.println("val "+val);
                         System.out.println("Qte Parte 1 "+qte[0]);
                         System.out.println("Qte Parte 2 "+qte[1]);
                  
                         int j = 0;
                         for (int i = 0; i <2; i++) {
                             if (qte[1].charAt(i)=='0'){
                             j = j+1;
                             }
                         }

                         if (j==2){
                         qte[1] = "000000000";
                         String qte2 = qte[0]+"."+qte[1];
                         qteVente = new BigDecimal(qte2);
                         
                          System.out.println("qteVente "+qteVente);
                         }
                         
                         
                         
                listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteRetour(qteRetour);
                listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteTotalRetour(qteRetourTotal);
                listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteVente(qteVente);
                
                setColumnPropertiesDetailTournee();
                     }
                     
                }else if(listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Kg")){
                
                    
                     if (event.getNewValue().compareTo(listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).qteTotalInitialCharge())>0){
 
                         
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(Constantes.MESSAGE_ALERT_saisir_Quantite);
            alert.setTitle("ERROR");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
   
             listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteRetour(BigDecimal.ZERO.setScale(2));
             detailTourneeTable.refresh();
             return;
            }


                     }else{
                    
                          qteRetour = qteRetourColumn.getCellData(event.getTablePosition().getRow());
                    
                   qteRetourTotal = qteRetour.add(listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getQuantiteRetourCaisse());
                  
                    qteTotalInitialCharge = listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).qteTotalInitialCharge();
                   
                    qteVente = qteTotalInitialCharge.subtract(qteRetourTotal);


                listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteRetour(qteRetour);
                listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteTotalRetour(qteRetourTotal);
                listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteVente(qteVente);
       
                  setColumnPropertiesDetailTournee();
                }
                
                }     
    }


    @FXML
    private void vendeurComboOnAction(ActionEvent event) {
        
        listeDetailTournee.clear();
        listeTournee.clear();
        
        
        Vendeur vendeur  = mapVendeur.get(vendeurCombo.getSelectionModel().getSelectedItem());

        listeTournee.addAll(tourneeDAO.findTourneeByEtatAndVendeur(Constantes.ETAT_EN_COURS, nav.getUtilisateur().getDepot().getId(),vendeur.getId()));
     
        tourneeTable.setItems(listeTournee);

        setColumnPropertiesTournee();
        
}


 

    @FXML
    private void editCommitQuantiteRetourCaisseColumn(CellEditEvent<DetailTournee, BigDecimal> event) {
        
          ((DetailTournee) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setQuantiteRetourCaisse(event.getNewValue());
              
             if(listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Piece")){
          
                     if (event.getNewValue().compareTo(listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).qteTotalInitialCharge().setScale(0, RoundingMode.DOWN))>0){
 
                         
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(Constantes.MESSAGE_ALERT_saisir_Quantite);
            alert.setTitle("ERROR");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
   
             listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteRetourCaisse(BigDecimal.ZERO.setScale(2));
             detailTourneeTable.refresh();
             return;
            }


                     }else{

                   qteRetourCaisse = qteRetourCaisseColumn.getCellData(event.getTablePosition().getRow());
                  
                
                detailTourneeTable.refresh();
           
               
                
                    conditionnement = new BigDecimal(listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getArticle().getConditionnement());
                      
                          qteRetourTotal = (listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getQuantiteRetour().divide(conditionnement,9,RoundingMode.FLOOR)).add(qteRetourCaisse);
                  

                  qteTotalInitialCharge = listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).qteTotalInitialCharge();
                   
                  qteVente = qteTotalInitialCharge.subtract(qteRetourTotal);
              

//                     String val = String.valueOf(qteVente);
//                  
//               String[] qte = val.split("\\.");
//                            
//                        System.out.println("qteVente.toString() "+qteVente.toString());
//                        System.out.println("val "+val);
//                         System.out.println("Qte Parte 1 "+qte[0]);
//                         
//                         System.out.println("Qte Parte 2 "+qte[1]);
//                  
//                         int j = 0;
//                         for (int i = 0; i <2; i++) {
//                             if (qte[1].charAt(i)=='0'){
//                             j = j+1;
//                             }
//                         }
//
//                         if (j==2){
//                         qte[1] = "000000000";
//                         String qte2 = qte[0]+"."+qte[1];
//                         qteVente = new BigDecimal(qte2);
//                         
//                          System.out.println("qteVente "+qteVente);
//                         }
                  
                listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteRetourCaisse(qteRetourCaisse);
                listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteTotalRetour(qteRetourTotal);
                listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteVente(qteVente);
                
                setColumnPropertiesDetailTournee();
                
                }
             }else if(listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Kg")){
                
                 listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteRetourCaisse(BigDecimal.ZERO.setScale(2));
                detailTourneeTable.refresh();
                   nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.ERREUR_SAISIE);
                }
                
                     }
        
    

   
        
    }

 
