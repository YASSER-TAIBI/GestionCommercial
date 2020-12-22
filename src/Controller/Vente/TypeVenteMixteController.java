/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Vente;

import static Controller.Vente.ChargementController.setColumnTextFieldConverter;
import Utils.Constantes;
import dao.Entity.Article;
import dao.Entity.DetailTournee;
import dao.Entity.DetailVenteMixte;
import dao.Entity.PrixVenteDepot;
import dao.Entity.TypeVente;
import dao.Entity.Vendeur;
import dao.Entity.VenteMixte;
import dao.Manager.DetailTourneeDAO;
import dao.Manager.PrixVenteDepotDAO;
import dao.Manager.TypeVenteDAO;
import dao.Manager.VenteMixteDAO;
import dao.ManagerImpl.DetailTourneeDAOImpl;
import dao.ManagerImpl.PrixVenteDepotDAOImpl;
import dao.ManagerImpl.TypeVenteDAOImpl;
import dao.ManagerImpl.VenteMixteDAOImpl;
import function.navigation;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class TypeVenteMixteController implements Initializable {

    @FXML
    private TableColumn<DetailVenteMixte, String> codeArticleColumn;
    @FXML
    private TableColumn<DetailVenteMixte, String> libelleColumn;
    @FXML
    private ImageView ajoutBtn;
    @FXML
    private ImageView videBtn;
    @FXML
    private TableView<DetailVenteMixte> acticleMixteTable;
    @FXML
    private TableColumn<DetailVenteMixte, BigDecimal> qteColumn;
    @FXML
    private TableColumn<DetailVenteMixte, String> typeVenteColumn;
    @FXML
    private TableColumn<DetailVenteMixte, BigDecimal> prixColumn;
    @FXML
    private TextField codeArtField;
    @FXML
    private TextField libelleArtField;
    @FXML
    private TextField qteVendu;
    @FXML
    private ComboBox<String> typeVenteCombo;
    @FXML
    private TextField totalPrixField;
    @FXML
    private ImageView modifierBtn;
    @FXML
    private Button btnAjouter;
    @FXML
    private TextField qteVente;
    
    
    
    /**
     * Initializes the controller class.
     */
    
    
    
     public BigDecimal  prixTotal= BigDecimal.ZERO;
     public DetailTournee detailTournee;

     
     
TypeVenteDAO typeVenteDAO = new TypeVenteDAOImpl();
PrixVenteDepotDAO prixVenteDepotDAO = new PrixVenteDepotDAOImpl();
  private Map<String, TypeVente> mapTypeVente = new HashMap<>();
     navigation nav = new navigation();   
  private final ObservableList<DetailVenteMixte> listeDetailVenteMixte=FXCollections.observableArrayList();
  public ObservableList<DetailTournee> listeDetailTourneeTMP=FXCollections.observableArrayList();
  
    VenteMixte venteMixte = new VenteMixte();
    VenteMixte venteMixteTmp;
    VenteMixteDAO venteMixteDAO = new VenteMixteDAOImpl();
    DetailTourneeDAO detailTourneeDAO = new DetailTourneeDAOImpl();
    
    
    
         public  void chargerLesDonnees(){

        codeArtField.setText(detailTournee.getArticle().getCode());
        libelleArtField.setText(detailTournee.getArticle().getLibelle());
        qteVente.setText(detailTournee.getQuantiteVente()+"");
 
    }
     
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
           List<TypeVente> listTypeVente =  typeVenteDAO.findAll();
            listTypeVente.stream().map((typeVente) -> {
                if(typeVente.getCode().equals(Constantes.ETAT_TYPE_VENTE_G)|| typeVente.getCode().equals(Constantes.ETAT_TYPE_VENTE_D)){
                typeVenteCombo.getItems().addAll(typeVente.getCode());
                }
                return typeVente;
            }).forEachOrdered((typeVente) -> {
                 if(typeVente.getCode().equals(Constantes.ETAT_TYPE_VENTE_G)|| typeVente.getCode().equals(Constantes.ETAT_TYPE_VENTE_D)){
                      mapTypeVente.put(typeVente.getCode(), typeVente);
                }
               
            });
            
//           loadDetail();
//            setColumnProperties();
    }    

 
      void setColumnProperties(){
   
              codeArticleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailVenteMixte, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailVenteMixte, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getArticle().getCode());
            }
        });
          
              libelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailVenteMixte, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailVenteMixte, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getArticle().getLibelle());
            }
        });
             
           qteColumn.setCellValueFactory(new PropertyValueFactory<DetailVenteMixte, BigDecimal>("quantite"));
           
           typeVenteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailVenteMixte, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailVenteMixte, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getTypeVente().getCode());
            }
        });
           
           prixColumn.setCellValueFactory(new PropertyValueFactory<DetailVenteMixte, BigDecimal>("totalVendue"));
        
     }
     
        public void loadDetail(){
             
             
                 venteMixteTmp = venteMixteDAO.findVenteMixteByDetailTournee(detailTournee.getId());
                    if (venteMixteTmp != null ){
                            listeDetailVenteMixte.addAll(venteMixteTmp.getListDetailVenteMixte());
                            acticleMixteTable.setItems(listeDetailVenteMixte);
                             setColumnProperties();
                             calculePrixTotalType ();
                    }else {
                      acticleMixteTable.setItems(listeDetailVenteMixte);
                    }
         
          
             
    }
    
    @FXML
    private void afficherArt(MouseEvent event) {
        
           Integer r = acticleMixteTable.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            return;
        } else {
            qteVendu.setText(qteColumn.getCellData(r)+"");
            typeVenteCombo.setValue(typeVenteColumn.getCellData(r));
 
            ajoutBtn.setDisable(true);

        }
    }

    @FXML
    private void ajouter(MouseEvent event) {
        
           DetailVenteMixte detailVenteMixte = new DetailVenteMixte();

       
       if(codeArtField.getText().equalsIgnoreCase("")|| libelleArtField.getText().equalsIgnoreCase("") || qteVendu.getText().equalsIgnoreCase("")|| typeVenteCombo.getSelectionModel().getSelectedItem()==null ){

         nav.showAlert(Alert.AlertType.WARNING, "Attention", null, "Vous devez remplir les champs !!");
     }else {
           
           
           TypeVente typeVente=mapTypeVente.get(typeVenteCombo.getSelectionModel().getSelectedItem());
         

             detailVenteMixte.setTypeVente(typeVente);
             detailVenteMixte.setArticle(detailTournee.getArticle());
             detailVenteMixte.setVenteMixte(venteMixte);
             detailVenteMixte.setQuantite(new BigDecimal(qteVendu.getText()));
                 
                 PrixVenteDepot prixVenteDepot = prixVenteDepotDAO.findPrixBySecteurAndTypeVente(detailTournee.getArticle().getId(), typeVente.getId(), detailTournee.getTournee().getSecteur().getId());
      
                                                                   
       if(prixVenteDepot.getPrix().compareTo(BigDecimal.ZERO)==0 || prixVenteDepot==null){
               
                 nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.MESSAGE_ALERT_CREER_PRIX);  
                return;
                
            }else{
           
            BigDecimal  prix = prixVenteDepot.getPrix().multiply(new BigDecimal(qteVendu.getText()));
            detailVenteMixte.setTotalVendue(prix);

             }

                listeDetailVenteMixte.add(detailVenteMixte);
                
             loadDetail();
            setColumnProperties();
            calculePrixTotalType ();
            calculeQteVente();

    }
         
    }
    
    public void calculeQteVente(){
        
      BigDecimal  valeurVente = BigDecimal.ZERO;
    
        valeurVente= new BigDecimal(qteVente.getText()).subtract(new BigDecimal(qteVendu.getText()));
        
        qteVente.setText(valeurVente+"");
        
    }
    
    public void calculePrixTotalType (){
    
                BigDecimal  prixTotal= BigDecimal.ZERO;
           for( int rows = 0;rows<acticleMixteTable.getItems().size() ;rows++ ){

            prixTotal = prixTotal.add(prixColumn.getCellData(rows));  
        
    }
         totalPrixField.setText(prixTotal+"");  
    
    }
    
    
    @FXML
    private void vider(MouseEvent event) {
    }

    
    
 public void calculePrixTotal (){

            
           for( int rows = 0;rows<listeDetailTourneeTMP.size() ;rows++ ){
DetailTournee detailTournee = listeDetailTourneeTMP.get(rows);
            prixTotal = prixTotal.add(detailTournee.getTotalVendue());  
        
    }
       
    
    }
 
 
    @FXML
    private void Modifier(MouseEvent event) {
        
//        if(listeDetailVenteMixte.size() != 0){
//        
//            DetailVenteMixte  detailVenteMixte = listeDetailVenteMixte.get(acticleMixteTable.getSelectionModel().getSelectedIndex());
//            
//                         TypeVente typeVente=mapTypeVente.get(typeVenteCombo.getSelectionModel().getSelectedItem());
//                         
//            detailVenteMixte.setQuantite(new BigDecimal(qteVendu.getText()));
//            detailVenteMixte.setTypeVente(typeVente);
//             
////_____________________________________ calcule champ prix dans la table ________________________________________________________________________________________________________
//
//            BigDecimal prix = BigDecimal.ZERO;
//            
//            if(typeVenteCombo.getSelectionModel().getSelectedItem().equals(Constantes.ETAT_TYPE_VENTE_G))
//            {
//                  PrixVenteDepot prixVenteDepot = prixVenteDepotDAO.findPrixBySecteurAndTypeVente(detailTournee.getArticle().getId(), typeVente.getId(), detailTournee.getTournee().getSecteur().getId());
//      
//       if(prixVenteDepot==null){
//               
//                 nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.MESSAGE_ALERT_CREER_PRIX);  
//                return;
//                
//            }else{
//                
//                prix=prixVenteDepot.getPrix().multiply(new BigDecimal(qteVendu.getText()));
//       }
//            } else if (typeVenteCombo.getSelectionModel().getSelectedItem().equals(Constantes.ETAT_TYPE_VENTE_D))
//            {
//                
//                  PrixVenteDepot prixVenteDepot = prixVenteDepotDAO.findPrixBySecteurAndTypeVente(detailTournee.getArticle().getId(), typeVente.getId(), detailTournee.getTournee().getSecteur().getId());
//      
//       if(prixVenteDepot==null){
//               
//                 nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.MESSAGE_ALERT_CREER_PRIX);  
//                return;
//                
//            }else{
//               prix= prixVenteDepot.getPrix().multiply(new BigDecimal(qteVendu.getText()));
//       }
//               
//            }
//            
//            detailVenteMixte.setTotalVendue(prix);
//            
//            listeDetailVenteMixte.set(acticleMixteTable.getSelectionModel().getSelectedIndex(), detailVenteMixte);
//            
//            
////_____________________________________ calcule Column prix ________________________________________________________________________________________________________
//
//
//              BigDecimal  prixTotal= BigDecimal.ZERO;
//              
//           for( int rows = 0;rows<acticleMixteTable.getItems().size() ;rows++ ){
//
//            prixTotal = prixTotal.add(prixColumn.getCellData(rows));  
//        
//    }
//         totalPrixField.setText(prixTotal+"");  
//            
//             acticleMixteTable.setItems(listeDetailVenteMixte);
//             setColumnProperties();
//        }
        
    }

    @FXML
    private void ajouterOnAction(ActionEvent event) {
        
           if( acticleMixteTable.getItems().size() ==0 || totalPrixField.getText().equals("")){
         nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, "Vous devez remplir les champs Svp !!");
     }
        else {

       
          if (venteMixteTmp == null ){
              
              
              venteMixte.setListDetailVenteMixte(listeDetailVenteMixte);
              venteMixte.setPrixTotalVendue(new BigDecimal(totalPrixField.getText()));
              venteMixte.setDetailTournee(detailTournee);
              venteMixteDAO.add(venteMixte);
          }
          else{
              
              venteMixteTmp.setListDetailVenteMixte(listeDetailVenteMixte);
              venteMixteTmp.setPrixTotalVendue(new BigDecimal(totalPrixField.getText()));
              

              venteMixteDAO.edit(venteMixteTmp);
              
          }
              detailTournee.setTotalVendue(new BigDecimal(totalPrixField.getText()));
              
               detailTourneeDAO.edit(detailTournee);
               
               
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.setLocation(getClass().getResource(nav.getSuiviVentes()));
               
               listeDetailTourneeTMP.clear();
               listeDetailTourneeTMP.addAll(detailTourneeDAO.findDetailTourneeByTournee(detailTournee.getTournee()));
               
               calculePrixTotal();
               

         nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, "Ajout avec succès");
         
     Stage stage = (Stage) ajoutBtn.getScene().getWindow();
            stage.close();
     
  
        }
        

    }
    
}
