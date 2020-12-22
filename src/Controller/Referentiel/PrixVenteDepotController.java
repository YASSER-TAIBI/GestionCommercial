/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.Article;
import dao.Entity.Depot;
import dao.Entity.PrixVenteDepot;
import dao.Entity.TypeVente;
import dao.Entity.Secteur;
import dao.Entity.Ville;
import dao.Manager.ArticleDAO;
import dao.Manager.DepotDAO;
import dao.Manager.PrixVenteDepotDAO;
import dao.Manager.TypeVenteDAO;
import dao.Manager.SecteurDAO;
import dao.Manager.VilleDAO;
import dao.ManagerImpl.ArticleDAOImpl;
import dao.ManagerImpl.DepotDAOImpl;
import dao.ManagerImpl.PrixVenteDepotDAOImpl;
import dao.ManagerImpl.TypeVenteDAOImpl;
import dao.ManagerImpl.SecteurDAOImpl;
import dao.ManagerImpl.VilleDAOImpl;
import function.navigation;
import java.math.BigDecimal;
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
public class PrixVenteDepotController implements Initializable {

    @FXML
    private TableView<PrixVenteDepot> prixVenteTable;
    @FXML
    private TableColumn<PrixVenteDepot, String> codeArticleColumn;
    @FXML
    private TableColumn<PrixVenteDepot, String> libelleColumn;
    @FXML
    private TableColumn<PrixVenteDepot, String> secteurColumn;
    @FXML
    private TableColumn<PrixVenteDepot, String> typeVenteColumn;
    @FXML
    private TableColumn<PrixVenteDepot, BigDecimal> prixVenteColumn;
    @FXML
    private TextField prixVenteField;
    @FXML
    private Button btnRafraichir;
    @FXML
    private Button btnModifer;
    @FXML
    private Button btnGenerer;
    /**
     * Initializes the controller class.
     */
        
    private final ObservableList<PrixVenteDepot> listPrixVenteDepot = FXCollections.observableArrayList();
    
    
    PrixVenteDepotDAO prixVenteDepotDAO = new PrixVenteDepotDAOImpl();
 

    navigation nav = new navigation();
        
     
     TypeVenteDAO typeVenteDAO = new TypeVenteDAOImpl();
     SecteurDAO secteurDAO = new SecteurDAOImpl();
     ArticleDAO articleDAO = new ArticleDAOImpl();

    
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setColumnProperties();
        loadDetailTmp();

    }    

     void setColumnProperties(){
   
            codeArticleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixVenteDepot, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PrixVenteDepot, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getArticle().getCode());
            }
        });
          
            libelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixVenteDepot, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PrixVenteDepot, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getArticle().getLibelle());
            }
        });
              
            secteurColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixVenteDepot, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PrixVenteDepot, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getSecteur().getLibelle());
            }
        });  
            
            typeVenteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixVenteDepot, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PrixVenteDepot, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getTypeVente().getLibelle());
            }
        });  
             
            prixVenteColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
   }
    
    void loadDetailTmp(){
        listPrixVenteDepot.clear();
        listPrixVenteDepot.addAll(prixVenteDepotDAO.findAll());
        prixVenteTable.setItems(listPrixVenteDepot);
    }
    
    @FXML
    private void afficherArt(MouseEvent event) {
        
           Integer val= prixVenteTable.getSelectionModel().getSelectedIndex();
          if (val<= -1 ){
          
          return;
          }
          else {
              prixVenteField.setText(prixVenteColumn.getCellData(val)+"");
          }
    }

     void viider(){
 
        prixVenteField.setText("");
        
    }
     



    private void ajouterOnAction(ActionEvent event) {
        
//         if(
//        
//          codeArticleField.getText().equals("")||
//          libelleArticleCombo.getSelectionModel().getSelectedIndex()== -1 ||
//          secteurCombo.getSelectionModel().getSelectedIndex()== -1 ||
//          typeVenteCombo.getSelectionModel().getSelectedIndex()== -1 ||
//          prixVenteField.getText().equals(""))
//                {
//      
//         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
//     }
//        else {
//          
//
//             Secteur secteur  = mapSecteur.get(secteurCombo.getSelectionModel().getSelectedItem());
//             TypeVente  typeVente  = mapTypeVente.get(typeVenteCombo.getSelectionModel().getSelectedItem());
//             Article article  = mapArticle.get(libelleArticleCombo.getSelectionModel().getSelectedItem());
//             
//             
//             
//                 PrixVenteDepot prixVenteDepot = new PrixVenteDepot();
//             
//        prixVenteDepot.setUtilisateurCreation(nav.getUtilisateur());
//        prixVenteDepot.setTypeVente(typeVente);
//        prixVenteDepot.setArticle(article);
//        prixVenteDepot.setPrix(new BigDecimal(prixVenteField.getText()));
//        prixVenteDepot.setSecteur(secteur);
//
//        prixVenteDepotDAO.add(prixVenteDepot);
//
//           nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MESSAGE_ALERT_AJOUT);  
//           setColumnProperties();
//           loadDetailTmp();
//           viider();
//  }
//        
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
             viider();
    }

    @FXML
    private void modifierOnAction(ActionEvent event) {
        
        
            Integer r = prixVenteTable.getSelectionModel().getSelectedIndex();
            if (r <= -1) {
                nav.showAlert(Alert.AlertType.CONFIRMATION, Constantes.TYPE_ALERT_INFORMATION, null, Constantes.MESSAGE_ALERT_SELECTIONNER);

            } else {
               
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_MODIFICATION);
                alert.setTitle("Confirmation");
                Optional<ButtonType> result = alert.showAndWait();


                if (result.get() == ButtonType.OK) {
                    PrixVenteDepot prixVenteDepot  = prixVenteTable.getSelectionModel().getSelectedItem();

        prixVenteDepot.setPrix(new BigDecimal(prixVenteField.getText()));
        
                    nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_MODIFICATION);
                    prixVenteDepotDAO.edit(prixVenteDepot);
   
                    setColumnProperties();
                    loadDetailTmp();
                    viider();
                }

            }

    }

    @FXML
    private void genererOnAction(ActionEvent event) {
        
        List<Article> listArticle = articleDAO.findAll();
        List<Secteur> listSecteur = secteurDAO.findAll();
        List<TypeVente> listTypeVente = typeVenteDAO.findByTypeVenteGrosDetail();
        
        nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_INFORMATION, null, Constantes.MESSAGE_ALERT_PATIENTER);
        
        for (int i = 0; i < listArticle.size(); i++) {
            
            Article article = listArticle.get(i);
            
            for (int j = 0; j <listSecteur.size() ; j++) {
                
               Secteur secteur = listSecteur.get(j);
               
                for (int k = 0; k < listTypeVente.size(); k++) {
                    
                    TypeVente typeVente = listTypeVente.get(k);
                    
                     PrixVenteDepot prixVenteDepot = prixVenteDepotDAO.findPrixBySecteurAndTypeVente(article.getId(), typeVente.getId(), secteur.getId());
      
       if(prixVenteDepot==null){  
                    
                    PrixVenteDepot prixVenteDepotTMP = new PrixVenteDepot();
                    
                    prixVenteDepotTMP.setArticle(article);
                    prixVenteDepotTMP.setSecteur(secteur);
                    prixVenteDepotTMP.setTypeVente(typeVente);
                    prixVenteDepotTMP.setPrix(BigDecimal.ZERO);
                    prixVenteDepotTMP.setUtilisateurCreation(nav.getUtilisateur());
                    
                    prixVenteDepotDAO.add(prixVenteDepotTMP);
                    
                }
                
                }
                
            }
            
        }
 
 
        setColumnProperties();
        loadDetailTmp();
        viider();
    }
    
}
