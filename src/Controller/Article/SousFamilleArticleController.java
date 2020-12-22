/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Article;

import Utils.Constantes;
import dao.Entity.DetailFamilleArticle;
import dao.Entity.SousFamilleArticle;
import dao.Manager.DetailFamilleArticleDAO;
import dao.Manager.SousFamilleArticleDAO;
import dao.ManagerImpl.DetailFamilleArticleDAOImpl;
import dao.ManagerImpl.SousFamilleArticleDAOImpl;
import function.navigation;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class SousFamilleArticleController implements Initializable {

    @FXML
    private TextField codefield;
    @FXML
    private TextField libellefield;
    @FXML
    private Label detailfamilleAerreur;
    @FXML
    private Label codeerreur;
    @FXML
    private Label libelleerreur;
    @FXML
    private ComboBox<String> DetailFamilleCombo;
    @FXML
    private TableView<SousFamilleArticle> sousFamilleTable;
    @FXML
    private TableColumn<SousFamilleArticle, String> codeColumn;
    @FXML
    private TableColumn<SousFamilleArticle, String> libelleColumn;
    @FXML
    private TableColumn<SousFamilleArticle, String> detailFamilleArticleColumn;
   @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRafraichir;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnModifer;
    /**
     * Initializes the controller class.
     */
    
      private final ObservableList<SousFamilleArticle> listeSousFamilleArticle = FXCollections.observableArrayList();
     SousFamilleArticleDAO sousFamilleArticledao =new SousFamilleArticleDAOImpl();
     boolean  msgerrue = false;
     navigation nav = new navigation();
     
     private Map<String, DetailFamilleArticle> mapDetailFamilleArticle = new HashMap<>();
     DetailFamilleArticleDAO detailFamilleArticleDAO = new DetailFamilleArticleDAOImpl();
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        setColumnProperties();
        loadDetail();
        
         
        List<DetailFamilleArticle> listDetailFamilleArticle = detailFamilleArticleDAO.findAll();

        listDetailFamilleArticle.stream().map((familleArticle) -> {
            DetailFamilleCombo.getItems().addAll(familleArticle.getLiblle());
            return familleArticle;
        }).forEachOrdered((familleArticle) -> {
            mapDetailFamilleArticle.put(familleArticle.getLiblle(), familleArticle);
        });
        
    }    

    
     void loadDetail() {

        listeSousFamilleArticle.clear();
        listeSousFamilleArticle.addAll(sousFamilleArticledao.findAll());
        sousFamilleTable.setItems(listeSousFamilleArticle);
    }

    void setColumnProperties() {

        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        
        libelleColumn.setCellValueFactory(new PropertyValueFactory<>("liblle"));
        
        detailFamilleArticleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SousFamilleArticle, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<SousFamilleArticle, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDetailFamileArticle().getLiblle());
            }
        });
          
    }

    
    void Verifier()
    {
         msgerrue = false;
          if (codefield.getText().equals(Constantes.CHAMP_VIDE)) {
            codeerreur.setText(Constantes.CHAMP_ETOILE);
            codefield.requestFocus();
            msgerrue = true;

        } else {
            codeerreur.setText(Constantes.CHAMP_VIDE);

        }
           if (libellefield.getText().equals(Constantes.CHAMP_VIDE)) {
            libelleerreur.setText(Constantes.CHAMP_ETOILE);
            libellefield.requestFocus();
            msgerrue = true;

        } else {
            libelleerreur.setText(Constantes.CHAMP_VIDE);

        }
           
         if (DetailFamilleCombo.getSelectionModel().getSelectedIndex() == -1) {
            codeerreur.setText(Constantes.CHAMP_ETOILE);
            msgerrue = true;
        } else {
            detailfamilleAerreur.setText(Constantes.CHAMP_VIDE);

        }
         
        
    }

    void vider() {
        codefield.setText(Constantes.CHAMP_VIDE);
        libellefield.setText(Constantes.CHAMP_VIDE);
        DetailFamilleCombo.getSelectionModel().select(-1);

        vider_erreur();

        btnAjouter.setDisable(false);
        loadDetail();
        setColumnProperties();

    }

    void vider_erreur() {
    libelleerreur.setText(Constantes.CHAMP_VIDE);
    codeerreur.setText(Constantes.CHAMP_VIDE);
    detailfamilleAerreur.setText(Constantes.CHAMP_VIDE);
         btnAjouter.setDisable(true);
    }
    
    
    @FXML
    private void afficherArt(MouseEvent event) {
                  Integer r = sousFamilleTable.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            return;
        } else {
            codefield.setText(codeColumn.getCellData(r));
            libellefield.setText(libelleColumn.getCellData(r));
            DetailFamilleCombo.setValue(detailFamilleArticleColumn.getCellData(r));
            vider_erreur();
            btnAjouter.setDisable(true);

        }
    }


    private void vider(MouseEvent event) {
       
    }


    @FXML
    private void ajouterOnAction(ActionEvent event) {
        
             
          msgerrue = false;
        Verifier();
        if (msgerrue == true) {
            return;
        } else {

            SousFamilleArticle sousFamilleArticle = new SousFamilleArticle();
            sousFamilleArticle.setCode(codefield.getText());
            sousFamilleArticle.setLiblle(libellefield.getText());
            DetailFamilleArticle detailFamilleArticle = mapDetailFamilleArticle.get(DetailFamilleCombo.getSelectionModel().getSelectedItem());
            sousFamilleArticle.setDetailFamileArticle(detailFamilleArticle);
            
            sousFamilleArticledao.add(sousFamilleArticle);
            
            nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_AJOUT);
            setColumnProperties();
            loadDetail();
            vider();

        }
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
        
                vider();
    }

    @FXML
    private void supprimerOnAction(ActionEvent event) {
        
          
          
         Integer r = sousFamilleTable.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            nav.showAlert(Alert.AlertType.WARNING, Constantes.TYPE_ALERT_INFORMATION, null, Constantes.MESSAGE_ALERT_SELECTIONNER);

        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_SUPPRIMER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();


            if (result.get() == ButtonType.OK) {
                SousFamilleArticle sousFamilleArticle = sousFamilleTable.getSelectionModel().getSelectedItem();
                sousFamilleArticledao.delete(sousFamilleArticle);
                nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_SUPPRIMER);
                setColumnProperties();
                loadDetail();
                vider();
            }
        }
        
    }

    @FXML
    private void modifierOnAction(ActionEvent event) {
        
          msgerrue = false;
        Verifier();
        if (msgerrue == true) {
            return;
        } else {
            Integer r = sousFamilleTable.getSelectionModel().getSelectedIndex();
            if (r <= -1) {
                nav.showAlert(Alert.AlertType.CONFIRMATION, Constantes.TYPE_ALERT_INFORMATION, null, Constantes.MESSAGE_ALERT_SELECTIONNER);

            } else {
               
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_MODIFICATION);
                alert.setTitle("Confirmation");
                Optional<ButtonType> result = alert.showAndWait();


                if (result.get() == ButtonType.OK) {
                    SousFamilleArticle sousFamilleArticle = sousFamilleTable.getSelectionModel().getSelectedItem();
                    sousFamilleArticle.setCode(codefield.getText());
                    sousFamilleArticle.setLiblle(libellefield.getText());
                    DetailFamilleArticle detailFamilleArticle = mapDetailFamilleArticle.get(DetailFamilleCombo.getSelectionModel().getSelectedItem());
                    sousFamilleArticle.setDetailFamileArticle(detailFamilleArticle);
                   
                    nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_MODIFICATION);
                    sousFamilleArticledao.edit(sousFamilleArticle);
   
                    setColumnProperties();
                    loadDetail();
                    vider();
                }

            }

        }
//        
        
    }
}
