/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Article;

import Utils.Constantes;
import dao.Entity.DetailSousFamilleArticle;
import dao.Entity.SousFamilleArticle;
import dao.Manager.DetailSousFamilleArticleDAO;
import dao.Manager.SousFamilleArticleDAO;
import dao.ManagerImpl.DetailSousFamilleArticleDAOImpl;
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
public class DetailSousFamilleArticleController implements Initializable {

    @FXML
    private TextField codefield;
    @FXML
    private TextField libellefield;
    @FXML
    private Label sousFamilleAerreur;
    @FXML
    private Label codeerreur;
    @FXML
    private Label libelleerreur;
    @FXML
    private ComboBox<String> sousFamilleCombo;
    @FXML
    private TableView<DetailSousFamilleArticle> detailSousFamilleTable;
    @FXML
    private TableColumn<DetailSousFamilleArticle, String> codeColumn;
    @FXML
    private TableColumn<DetailSousFamilleArticle, String> libelleColumn;
    @FXML
    private TableColumn<DetailSousFamilleArticle, String> sousFamilleColumn;
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
    
     private final ObservableList<DetailSousFamilleArticle> listeDetailSousFamilleArticle = FXCollections.observableArrayList();
     DetailSousFamilleArticleDAO detailSousFamilleArticledao =new DetailSousFamilleArticleDAOImpl();
     boolean  msgerrue = false;
     navigation nav = new navigation();
     
     private Map<String, SousFamilleArticle> mapSousFamilleArticle = new HashMap<>();
     SousFamilleArticleDAO sousFamilleArticleDAO = new SousFamilleArticleDAOImpl();
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            // TODO
        setColumnProperties();
        loadDetail();
        
         
        List<SousFamilleArticle> listSousFamilleArticle = sousFamilleArticleDAO.findAll();

        listSousFamilleArticle.stream().map((familleArticle) -> {
            sousFamilleCombo.getItems().addAll(familleArticle.getLiblle());
            return familleArticle;
        }).forEachOrdered((familleArticle) -> {
            mapSousFamilleArticle.put(familleArticle.getLiblle(), familleArticle);
        });
        
    }    

     void loadDetail() {

        listeDetailSousFamilleArticle.clear();
        listeDetailSousFamilleArticle.addAll(detailSousFamilleArticledao.findAll());
        detailSousFamilleTable.setItems(listeDetailSousFamilleArticle);
    }

    void setColumnProperties() {

        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        
        libelleColumn.setCellValueFactory(new PropertyValueFactory<>("liblle"));
        
        sousFamilleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailSousFamilleArticle, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailSousFamilleArticle, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getSousFamileArticle().getLiblle());
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
           
         if (sousFamilleCombo.getSelectionModel().getSelectedIndex() == -1) {
            codeerreur.setText(Constantes.CHAMP_ETOILE);
            msgerrue = true;
        } else {
            sousFamilleAerreur.setText(Constantes.CHAMP_VIDE);

        }

    }

    void vider() {
        codefield.setText(Constantes.CHAMP_VIDE);
        libellefield.setText(Constantes.CHAMP_VIDE);
        sousFamilleCombo.getSelectionModel().select(-1);

        vider_erreur();

        btnAjouter.setDisable(false);
        loadDetail();
        setColumnProperties();

    }

    void vider_erreur() {
    libelleerreur.setText(Constantes.CHAMP_VIDE);
    codeerreur.setText(Constantes.CHAMP_VIDE);
    sousFamilleAerreur.setText(Constantes.CHAMP_VIDE);
         btnAjouter.setDisable(true);
    }
    
    @FXML
    private void afficherArt(MouseEvent event) {
              Integer r = detailSousFamilleTable.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            return;
        } else {
            codefield.setText(codeColumn.getCellData(r));
            libellefield.setText(libelleColumn.getCellData(r));
            sousFamilleCombo.setValue(sousFamilleColumn.getCellData(r));
            vider_erreur();
            btnAjouter.setDisable(true);

        }
    }


    @FXML
    private void ajouterOnAction(ActionEvent event) {
        
        
          msgerrue = false;
        Verifier();
        if (msgerrue == true) {
            return;
        } else {

            DetailSousFamilleArticle detailSousFamilleArticle = new DetailSousFamilleArticle();
            detailSousFamilleArticle.setCode(codefield.getText());
            detailSousFamilleArticle.setLiblle(libellefield.getText());
            SousFamilleArticle sousFamilleArticle = mapSousFamilleArticle.get(sousFamilleCombo.getSelectionModel().getSelectedItem());
            detailSousFamilleArticle.setSousFamileArticle(sousFamilleArticle);
            
            detailSousFamilleArticledao.add(detailSousFamilleArticle);
            
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
            
         Integer r = detailSousFamilleTable.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            nav.showAlert(Alert.AlertType.WARNING, Constantes.TYPE_ALERT_INFORMATION, null, Constantes.MESSAGE_ALERT_SELECTIONNER);

        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_SUPPRIMER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();


            if (result.get() == ButtonType.OK) {
                DetailSousFamilleArticle detailSousFamilleArticle = detailSousFamilleTable.getSelectionModel().getSelectedItem();
                detailSousFamilleArticledao.delete(detailSousFamilleArticle);
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
            Integer r = detailSousFamilleTable.getSelectionModel().getSelectedIndex();
            if (r <= -1) {
                nav.showAlert(Alert.AlertType.CONFIRMATION, Constantes.TYPE_ALERT_INFORMATION, null, Constantes.MESSAGE_ALERT_SELECTIONNER);

            } else {
               
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_MODIFICATION);
                alert.setTitle("Confirmation");
                Optional<ButtonType> result = alert.showAndWait();


                if (result.get() == ButtonType.OK) {
                    DetailSousFamilleArticle detailSousFamilleArticle = detailSousFamilleTable.getSelectionModel().getSelectedItem();
                    detailSousFamilleArticle.setCode(codefield.getText());
                    detailSousFamilleArticle.setLiblle(libellefield.getText());
                    SousFamilleArticle sousfamilleArticle = mapSousFamilleArticle.get(sousFamilleCombo.getSelectionModel().getSelectedItem());
                          detailSousFamilleArticle.setSousFamileArticle(sousfamilleArticle);
                   
                    nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_MODIFICATION);
                    detailSousFamilleArticledao.edit(detailSousFamilleArticle);
   
                    setColumnProperties();
                    loadDetail();
                    vider();
                }

            }

        }
    }
    
}
