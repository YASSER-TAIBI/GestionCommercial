/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Vente;

import Utils.Constantes;
import dao.Entity.DetailCompte;
//import com.sun.corba.se.impl.oa.toa.TOA;
import dao.Entity.DetailTournee;
import dao.Entity.PrixVenteDepot;
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
import java.math.RoundingMode;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
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



public class SuiviVentesController implements Initializable {

    @FXML
    private TableView<DetailTournee> detailTourneeTable;
    @FXML
    private TableColumn<DetailTournee, String> codeArticleColumn;
    @FXML
    private TableColumn<DetailTournee, String> libelleColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteChargeColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> prixVenteColumn;
    @FXML
    private TableColumn<DetailTournee, String> typeVenteColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteInitialColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteRetourColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteOffreColumn;
    @FXML
    private TableColumn<DetailTournee, String> qteOffreGlobalColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteCaisseOffreColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteVenteColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteTotalColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteTotalOffreColumn;
    @FXML
    private TableColumn<DetailTournee, String> qteVenteGlobalColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteRecapeColumn;
    
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
    private ImageView calculeCheque;

    @FXML
    public TextField montantTotal;
    @FXML
    private TextField libelleField;
    @FXML
    private TextArea designationTextArea;
    @FXML
    private Label codeerreur;
    
     @FXML
    private ToggleGroup groupOffre;
    @FXML
    private RadioButton offreOuiRadio;
    @FXML
    private RadioButton offreNonRadio;
    /**
     * Initializes the controller class.
     */
 


     public BigDecimal qteOffre = BigDecimal.ZERO;  
     public BigDecimal qteOffreCaisse = BigDecimal.ZERO;  
     public BigDecimal qteOffreTotal = BigDecimal.ZERO;
     public BigDecimal conditionnement= BigDecimal.ZERO;
     public BigDecimal qteVente = BigDecimal.ZERO;
     public BigDecimal qteVendu = BigDecimal.ZERO;
     public BigDecimal qteVenteNew = BigDecimal.ZERO;
     public BigDecimal prixTotal = BigDecimal.ZERO;
     
     public BigDecimal venteOld = BigDecimal.ZERO;
     
     
      TypeVente typeVente;
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
     
      public ObservableList<DetailTournee> listeDetailTournee = FXCollections.observableArrayList();
      public ObservableList<BigDecimal> listeVente = FXCollections.observableArrayList();
      private final ObservableList<PrixVenteDepot> listePrixVenteDepot = FXCollections.observableArrayList();
      private final ObservableList<Tournee> listeTournee = FXCollections.observableArrayList();
   

    
  void Incrementation (){
       
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.VENTE);
          libelleField.setText(sequenceur.getCode()+" "+(sequenceur.getValeur()+1));

   }
    
  
       void loadDetail() {

        listeTournee.clear();
        listeTournee.addAll(tourneeDAO.findTourneeByEtat(Constantes.ETAT_VENTE, nav.getUtilisateur().getDepot().getId()));
        tourneeTable.setItems(listeTournee);
    }
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        
        loadDetail();
        setColumnPropertiesTournee();
        
        montantTotal.setText(new BigDecimal(0).toString());

        Incrementation ();
         
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
           
           qteChargeColumn.setCellValueFactory(new PropertyValueFactory<DetailTournee, BigDecimal>("quantiteTotalCharge"));
           
           qteTotalColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailTournee, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailTournee, BigDecimal> param) {
                return new ReadOnlyObjectWrapper(param.getValue().qteTotalInitialCharge());
            }
        });
              
           qteRetourColumn.setCellValueFactory(new PropertyValueFactory<DetailTournee, BigDecimal>("quantiteTotalRetour"));
           
           qteOffreColumn.setCellValueFactory(new PropertyValueFactory<DetailTournee, BigDecimal>("quantiteOffre"));
           setColumnTextFieldConverter(getConverter(), qteOffreColumn);
           
           qteCaisseOffreColumn.setCellValueFactory(new PropertyValueFactory<DetailTournee, BigDecimal>("quantiteOffreCaisse"));
           setColumnTextFieldConverter(getConverter(), qteCaisseOffreColumn);
           
           qteTotalOffreColumn.setCellValueFactory(new PropertyValueFactory<DetailTournee, BigDecimal>("quantiteTotalOffre"));
           
           qteVenteColumn.setCellValueFactory(new PropertyValueFactory<DetailTournee, BigDecimal>("quantiteVente"));
           
              typeVenteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailTournee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailTournee, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getTypeVente().getCode());
            }
        });
            
           prixVenteColumn.setCellValueFactory(new PropertyValueFactory<DetailTournee, BigDecimal>("totalVendue"));

 // ___________________________________________ Charge ComboBox in TableView _______________________________________________________

//         ObservableList<TypeVente> listTypeVenteTmp = FXCollections.observableArrayList();
//           listTypeVenteTmp.addAll(typeVenteDAO.findTypeVenteByCodeTypeVente());
//           
//        ObservableList<String> listTypeVente = FXCollections.observableArrayList();
//        
//        listTypeVente.addAll(typeVenteDAO.findTypeVenteByCodeType());
//       
//  
//
//        typeVenteColumn.setCellFactory(ComboBoxTableCell.forTableColumn(listTypeVente));
// 
//        
//        typeVenteColumn.setOnEditCommit(
//            new EventHandler<TableColumn.CellEditEvent<DetailTournee, String>>() {
//                @Override
//                public void handle(TableColumn.CellEditEvent<DetailTournee, String> t) {
//                    System.out.println("ON edit commit" + t);
//                     typeVente=typeVenteDAO.findTypeVenteByCodeTypeVente(t.getNewValue());
//
//       listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setTypeVente(typeVente);
       
       
            
//            if (typeVente.getCode().equals(Constantes.ETAT_TYPE_VENTE_G)){
//                
//                gideImage.setVisible(true);
//                
//           DetailTournee detailTournee= listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex());
//             System.out.println("detailTournee " + detailTournee);
//                VenteMixte venteMixte = venteMixteDAO.findVenteMixteByDetailTournee(detailTournee.getId());
//                
//if (venteMixte == null){
//    
//       listePrixVenteDepot.addAll(prixVenteDepotDAO.findAll());
//       qteVendu =listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getQuantiteVente();
//       qteUnite =listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getQteUnite();
//
//       PrixVenteDepot prixVenteDepot = prixVenteDepotDAO.findPrixBySecteurAndTypeVente(detailTournee.getArticle().getId(), detailTournee.getTypeVente().getId(), detailTournee.getTournee().getSecteur().getId());
//      
//       if(prixVenteDepot==null){
//               
//                 nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.MESSAGE_ALERT_CREER_PRIX);  
//                 
//                return;
//                
//            }else{
//           
//           prixTotal =listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getQuantiteVente().multiply(prixVenteDepot.getPrix());
//               
//
//               listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setTotalVendue(prixTotal);
//               listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteVendue(qteVendu);
//               listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteUnite(qteUnite);
//       }
//               
//}else {
//
//    venteMixteDAO.delete(venteMixte);
//    
//    qteVendu =listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getQuantiteVente();
//    qteUnite = listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getQteUnite(); 
//    
//      PrixVenteDepot prixVenteDepot = prixVenteDepotDAO.findPrixBySecteurAndTypeVente(detailTournee.getArticle().getId(), detailTournee.getTypeVente().getId(), detailTournee.getTournee().getSecteur().getId());
//      
//       if(prixVenteDepot==null){
//               
//                 nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.MESSAGE_ALERT_CREER_PRIX);  
//                return;
//                
//            }else{
//    prixTotal =listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getQuantiteVente().multiply(prixVenteDepot.getPrix());
//   
//
//               listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setTotalVendue(prixTotal);
//                  listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteVendue(qteVendu);
//                     listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteUnite(qteUnite);
//    
//}
//}
//           
//       }
//       
//            if (typeVente.getCode().equals(Constantes.ETAT_TYPE_VENTE_D) ){
//           
//                gideImage.setVisible(true);
//                
//                    DetailTournee detailTournee= listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex());
//      
//                VenteMixte venteMixte = venteMixteDAO.findVenteMixteByDetailTournee(detailTournee.getId());
//                
//if (venteMixte == null){
//    
//            qteVendu =listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getQuantiteVente();
//            qteUnite = listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getQteUnite();
//            
//              PrixVenteDepot prixVenteDepot = prixVenteDepotDAO.findPrixBySecteurAndTypeVente(detailTournee.getArticle().getId(), detailTournee.getTypeVente().getId(), detailTournee.getTournee().getSecteur().getId());
//      
//       if(prixVenteDepot==null){
//               
//                 nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.MESSAGE_ALERT_CREER_PRIX);  
//                return;
//                
//            }else{
//            
//            prixTotal =listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getQuantiteVente().multiply(prixVenteDepot.getPrix());
//
//                 
//
//               listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setTotalVendue(prixTotal);
//               listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteVendue(qteVendu);
//               listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteUnite(qteUnite);
//               
//       }
//
//        }else {
//
//    venteMixteDAO.delete(venteMixte);
//    qteVendu =listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getQuantiteVente();
//    qteUnite = listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getQteUnite();
//    
//    
//      PrixVenteDepot prixVenteDepot = prixVenteDepotDAO.findPrixBySecteurAndTypeVente(detailTournee.getArticle().getId(), detailTournee.getTypeVente().getId(), detailTournee.getTournee().getSecteur().getId());
//      
//       if(prixVenteDepot==null){
//               
//                 nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.MESSAGE_ALERT_CREER_PRIX);  
//                return;
//                
//            }else{
//    
//    prixTotal =listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getQuantiteVente().multiply(prixVenteDepot.getPrix());
//
//                 
//
//               listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setTotalVendue(prixTotal);
//               listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteVendue(qteVendu);
//               listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteUnite(qteUnite);
//              
//       }      
//}
//   
//       }  
//            if (typeVente.getCode().equals(Constantes.ETAT_TYPE_VENTE_M) ){
//                
//                gideImage.setVisible(true);
//                
//             qteVendu =listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getQuantiteVente();
//              qteUnite = listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getQteUnite();
//              
//                    if (detailTourneeTable.getSelectionModel().getSelectedItem() != null) {
//              
//                        DetailTournee detailTournee= detailTourneeTable.getSelectionModel().getSelectedItem();
//                        int pos = detailTourneeTable.getSelectionModel().getSelectedIndex();
//                        
//            FXMLLoader fXMLLoader = new FXMLLoader();
//            fXMLLoader.setLocation(getClass().getResource(nav.getTypeVenteMixte()));
//            try {
//                fXMLLoader.load();
//                Parent parent = fXMLLoader.getRoot();
//                Scene scene = new Scene(parent);
//
//                TypeVenteMixteController typeVenteMixteController = fXMLLoader.getController();
//               
//               
//             
//                typeVenteMixteController.detailTournee = detailTournee;
//                typeVenteMixteController.chargerLesDonnees();
//                typeVenteMixteController.listeDetailTourneeTMP = listeDetailTournee;
//                typeVenteMixteController.pos=pos;
//                typeVenteMixteController.loadDetail();
//               
//                
//                
//                Stage stage = new Stage();
//                stage.setScene(scene);
//                stage.initModality(Modality.APPLICATION_MODAL);
//    
//                stage.show();
//                
//                
//                
//            } catch (IOException ex) {
//              
//                System.err.println("!!!!!!!!" +ex);
//            }
//              
//                 listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteVendue(qteVendu);
//                 listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteUnite(qteUnite);
//            
//                 VenteMixte venteMixte = venteMixteDAO.findVenteMixteByDetailTournee(detailTournee.getId());
//              
//              if (venteMixte != null){
//                  
//              
//                  detailTournee.setTotalVendue(venteMixte.getPrixTotalVendue());
//              listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setTotalVendue(venteMixte.getPrixTotalVendue());
//           
//             
//    
//              }
//         
//              
//        } else {
//             nav.showAlert(Alert.AlertType.ERROR, "Errreur", Constantes.CHAMP_INVALIDE, Constantes.CHAMP_INVALIDE);
//        }
//
//       }  
//                }
//            }
//                
//    );
                
   }
   
//    void loadDetailTmp(){
//        listeDetailTournee.clear();
//        listeDetailTournee.addAll(detailTourneeDAO.findAll());
//        detailTourneeTable.setItems(listeDetailTournee);
//     
//    }
    
     public void calculePrixTotal (){

                BigDecimal  prixTotal= BigDecimal.ZERO;
           for( int rows = 0;rows<listeDetailTournee.size() ;rows++ ){
          DetailTournee detailTournee = listeDetailTournee.get(rows);
            prixTotal = prixTotal.add(detailTournee.getTotalVendue());  
        
    }
         montantTotal.setText(prixTotal+"");  
    
    }

    public void refrech(ObservableList<DetailTournee> listdetailtournee){
     detailTourneeTable.setItems(listdetailtournee);
     detailTourneeTable.refresh();
    }

    @FXML
    private void afficherArt(MouseEvent event) {

                setColumnPropertiesDetailTournee();
        listeDetailTournee.clear();
        
if (tourneeTable.getSelectionModel().getSelectedIndex()!=-1){
    
          
        tournee = tourneeTable.getSelectionModel().getSelectedItem();

      listeDetailTournee.addAll(detailTourneeDAO.findDetailTourneeByTournee(tournee));

      for (int i = 0; i < listeDetailTournee.size(); i++) {
        
          listeVente.add(listeDetailTournee.get(i).getQuantiteVente());
          
          
          
    }
     
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
        montantTotal.clear();
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
    private void calculeTotalPrixVente(MouseEvent event) {
        calculePrixTotal ();
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
          detailTourneeTable.getItems().isEmpty() ||
          new BigDecimal(montantTotal.getText()).compareTo(BigDecimal.ZERO)==0
          
          )
  {
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
         return;
     }
        else {
      
              tournee=tourneeTable.getSelectionModel().getSelectedItem();

              String CodeVente= libelleField.getText();
             
              SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
          
              String dateFormat2=dateFormat.format(tournee.getDateTournee());
              
              
            
        tournee.setCodeVent(CodeVente+"/"+tournee.getVendeur().getNom()+"/"+dateFormat2);
        tournee.setDetailTournee(listeDetailTournee);
        tournee.setTotalVendue(new BigDecimal(montantTotal.getText()));
        tournee.setEtat(Constantes.ETAT_VERSEMENT);
        tournee.setDesignation(designationTextArea.getText());
        tourneeDAO.edit(tournee);
                        
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Vente / "MontantCredit" <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<            
         
            DetailCompte  detailCompte = new DetailCompte();
                
            detailCompte.setDateOperation(tournee.getDateTournee());
            detailCompte.setDesignation(Constantes.VENTE_SUR_TOURNEE+CodeVente+"/"+tournee.getVendeur().getNom()+"/"+dateFormat2);
            detailCompte.setMontantDebit(BigDecimal.ZERO);
            detailCompte.setEtat(Constantes.ETAT_VENTE);
            detailCompte.setMontantCredit(new BigDecimal(montantTotal.getText())); 
            detailCompte.setDateCreation(new Date());
            detailCompte.setUtilisateurCreation(nav.getUtilisateur());
            detailCompte.setCompte(tournee.getVendeur().getCompte());
            
                detailCompteDAO.add(detailCompte);
              
       
           Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.VENTE);
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
    private void afficherDetailArt(MouseEvent event) {
        
        if (detailTourneeTable.getSelectionModel().getSelectedIndex()!=-1){
    
          
       DetailTournee detailTournee = detailTourneeTable.getSelectionModel().getSelectedItem();

             if (detailTournee.getTypeVente().getCode().equals(Constantes.ETAT_TYPE_VENTE_M)){

                 
             qteVendu =listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getQuantiteVente();
              
                        
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.setLocation(getClass().getResource(nav.getTypeVenteMixte()));
            try {
                fXMLLoader.load();
                Parent parent = fXMLLoader.getRoot();
                Scene scene = new Scene(parent);

                TypeVenteMixteController typeVenteMixteController = fXMLLoader.getController();
               
               
             
                typeVenteMixteController.detailTournee = detailTournee;
                typeVenteMixteController.chargerLesDonnees();
                typeVenteMixteController.listeDetailTourneeTMP = listeDetailTournee;
                typeVenteMixteController.loadDetail();
               
                
                
                Stage stage = new Stage();
                
                Image image= new Image(getClass().getResourceAsStream("/Styles/img/logo.png"));
                stage.getIcons().add(image);
                stage.setTitle("Prix Vente Mixte ");
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
    
                stage.show();
                
                
                
            } catch (IOException ex) {
              
                System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" +ex);
            }
              
                 listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteVendue(qteVendu);
            
                 VenteMixte venteMixte = venteMixteDAO.findVenteMixteByDetailTournee(detailTournee.getId());
              
              if (venteMixte != null){
                  
              
                  detailTournee.setTotalVendue(venteMixte.getPrixTotalVendue());
              listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setTotalVendue(venteMixte.getPrixTotalVendue());
           
             
    
              }
         
              
//        } else {
//             nav.showAlert(Alert.AlertType.ERROR, "Errreur", Constantes.CHAMP_INVALIDE, Constantes.CHAMP_INVALIDE);
//        }

        
             
             }
       

}
        
    }

    @FXML
    private void offreOui(ActionEvent event) {
        
         detailTourneeTable.setEditable(true); 
         qteOffreColumn.setEditable(true); 
        qteCaisseOffreColumn.setEditable(true); 
    }

    @FXML
    private void offreNon(ActionEvent event) {
        
         detailTourneeTable.setEditable(false); 
        qteOffreColumn.setEditable(false);
        qteCaisseOffreColumn.setEditable(false);
    }

    @FXML
    private void editCommitQuantiteOffreColumn(CellEditEvent<DetailTournee, BigDecimal> event) {
        

     ((DetailTournee) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setQuantiteOffre(event.getNewValue());
              
                     if (event.getNewValue().compareTo(listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getQuantiteVente())>0){
 
                         
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(Constantes.MESSAGE_ALERT_saisir_Quantite);
            alert.setTitle("ERROR");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
   
             listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteOffre(BigDecimal.ZERO.setScale(2));
             detailTourneeTable.refresh();
             return;
             
            }
                     }else{

                         
                   qteOffre = qteOffreColumn.getCellData(event.getTablePosition().getRow());
                  
                
                detailTourneeTable.refresh();
           
                  if(listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Piece")){
                
                
                 conditionnement = new BigDecimal(listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getArticle().getConditionnement());
                
                 qteOffreTotal = (qteOffre.divide(conditionnement,9,RoundingMode.FLOOR)).add(listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getQuantiteOffreCaisse());
  
                 qteVente = listeVente.get(detailTourneeTable.getSelectionModel().getSelectedIndex());

                 qteVenteNew = qteVente.subtract(qteOffreTotal);
               
                 
                 if (listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getTypeVente().getCode().equals(Constantes.ETAT_TYPE_VENTE_D) || listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getTypeVente().getCode().equals(Constantes.ETAT_TYPE_VENTE_G)){
                     
                       PrixVenteDepot prixVenteDepot = prixVenteDepotDAO.findPrixBySecteurAndTypeVente(listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getArticle().getId(), listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getTypeVente().getId(), listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getTournee().getSecteur().getId());
  
                 prixTotal =qteVenteNew.multiply(prixVenteDepot.getPrix());
                 
                 listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setTotalVendue(prixTotal);
                     
                 }else{
                 
                     Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(Constantes.MESSAGE_ALERT_TRAITEMENT_MIXTE);
            alert.setTitle("WARNING");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
   
             listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteOffre(BigDecimal.ZERO.setScale(2));
             detailTourneeTable.refresh();
             return;
             
            }
                 
                 }

                listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteOffre(qteOffre);
                listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteTotalOffre(qteOffreTotal);
                listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteVente(qteVenteNew);
                
                setColumnPropertiesDetailTournee();
                
                }else if(listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Kg")){
                
                    
                   qteOffreTotal = qteOffre.add(listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getQuantiteOffreCaisse());
                  
                      qteVente = listeVente.get(detailTourneeTable.getSelectionModel().getSelectedIndex());

                 qteVenteNew = qteVente.subtract(qteOffreTotal);

                 if (listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getTypeVente().getCode().equals(Constantes.ETAT_TYPE_VENTE_D) || listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getTypeVente().getCode().equals(Constantes.ETAT_TYPE_VENTE_G)){
                     
                       PrixVenteDepot prixVenteDepot = prixVenteDepotDAO.findPrixBySecteurAndTypeVente(listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getArticle().getId(), listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getTypeVente().getId(), listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getTournee().getSecteur().getId());
  
                 prixTotal =qteVenteNew.multiply(prixVenteDepot.getPrix());
                 
                 listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setTotalVendue(prixTotal);
                     
                 }else{
                 
                     Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(Constantes.MESSAGE_ALERT_TRAITEMENT_MIXTE);
            alert.setTitle("WARNING");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
   
             listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteOffre(BigDecimal.ZERO.setScale(2));
             detailTourneeTable.refresh();
             return;
             
            }
                 
                 }

                listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteOffre(qteOffre);
                listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteTotalOffre(qteOffreTotal);
                listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteVente(qteVenteNew);
       
                  setColumnPropertiesDetailTournee();
                }
                
                     }
    }

    @FXML
    private void editCommitQuantiteOffreCaisseColumn(CellEditEvent<DetailTournee, BigDecimal> event) {
        
        
        ((DetailTournee) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setQuantiteOffreCaisse(event.getNewValue());
              
                      if (event.getNewValue().compareTo(listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getQuantiteVente())>0){
 
                         
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(Constantes.MESSAGE_ALERT_saisir_Quantite);
            alert.setTitle("ERROR");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
   
             listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteOffreCaisse(BigDecimal.ZERO.setScale(2));
             detailTourneeTable.refresh();
             return;
            }


                     }else{

                   qteOffreCaisse = qteCaisseOffreColumn.getCellData(event.getTablePosition().getRow());
                  
                
                detailTourneeTable.refresh();
           
                  if(listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Piece")){
                
                           qteOffreTotal = (listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getQuantiteOffre().divide(conditionnement,9,RoundingMode.FLOOR)).add(qteOffreCaisse);
                  
                      qteVente = listeVente.get(detailTourneeTable.getSelectionModel().getSelectedIndex());
   
                 qteVenteNew = qteVente.subtract(qteOffreTotal);
               
                 
                 if (listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getTypeVente().getCode().equals(Constantes.ETAT_TYPE_VENTE_D) || listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getTypeVente().getCode().equals(Constantes.ETAT_TYPE_VENTE_G)){
                     
                       PrixVenteDepot prixVenteDepot = prixVenteDepotDAO.findPrixBySecteurAndTypeVente(listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getArticle().getId(), listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getTypeVente().getId(), listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getTournee().getSecteur().getId());
  
                 prixTotal =qteVenteNew.multiply(prixVenteDepot.getPrix());
                 
                 listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setTotalVendue(prixTotal);
                     
                 }else{
                 
                     Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(Constantes.MESSAGE_ALERT_TRAITEMENT_MIXTE);
            alert.setTitle("WARNING");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
   
             listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteOffreCaisse(BigDecimal.ZERO.setScale(2));
             detailTourneeTable.refresh();
             return;
             
            }
                 
                 }

                listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteOffreCaisse(qteOffreCaisse);
                listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteTotalOffre(qteOffreTotal);
                listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteVente(qteVenteNew);
                
                setColumnPropertiesDetailTournee();
                
                }else if(listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).getArticle().getDetailSousFamilleArticle().getSousFamileArticle().getDetailFamileArticle().getFamilleArticle().getUnite().equals("Kg")){
                
                 listeDetailTournee.get(detailTourneeTable.getSelectionModel().getSelectedIndex()).setQuantiteOffreCaisse(BigDecimal.ZERO.setScale(2));
                detailTourneeTable.refresh();
                   nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.ERREUR_SAISIE);
                }
                
                     }
        
        
        
        
    }




}