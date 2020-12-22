/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Article;

import Utils.Constantes;
import dao.Entity.DetailFamilleArticle;
import dao.Entity.FamilleArticle;
import dao.Manager.DetailFamilleArticleDAO;
import dao.Manager.FamilleArticleDAO;
import dao.ManagerImpl.DetailFamilleArticleDAOImpl;
import dao.ManagerImpl.FamilleArticleDAOImpl;
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
public class DetailFamilleArticleController implements Initializable {

    
    @FXML
    private TableView<DetailFamilleArticle> detailFamilleTable;
    @FXML
    private TableColumn<DetailFamilleArticle, String> codeColumn;
    @FXML
    private TableColumn<DetailFamilleArticle, String> libelleColumn;
    @FXML
    private TableColumn<DetailFamilleArticle, String> familleColumn;
    @FXML
    private TextField codefield;
    @FXML
    private TextField libellefield;
    @FXML
    private Label familleAerreur;
    @FXML
    private Label codeerreur;
    @FXML
    private Label libelleerreur;
    @FXML
    private ComboBox<String> familleCombo;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRafraichir;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnModifer;
    
     private final ObservableList<DetailFamilleArticle> listeDetailFamilleArticle = FXCollections.observableArrayList();
     DetailFamilleArticleDAO detailFamilleArticledao =new DetailFamilleArticleDAOImpl();
     boolean  msgerrue = false;
     navigation nav = new navigation();
     
     private Map<String, FamilleArticle> mapFamilleArticle = new HashMap<>();
     FamilleArticleDAO familleArticleDAO = new FamilleArticleDAOImpl();
 
    
    /**
     * Initializes the controller class.
     */
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setColumnProperties();
        loadDetail();
        
         
        List<FamilleArticle> listFamilleArticle = familleArticleDAO.findAll();

        listFamilleArticle.stream().map((familleArticle) -> {
            familleCombo.getItems().addAll(familleArticle.getLiblle());
            return familleArticle;
        }).forEachOrdered((familleArticle) -> {
            mapFamilleArticle.put(familleArticle.getLiblle(), familleArticle);
        });
    }

  void loadDetail() {

        listeDetailFamilleArticle.clear();
        listeDetailFamilleArticle.addAll(detailFamilleArticledao.findAll());
        detailFamilleTable.setItems(listeDetailFamilleArticle);
    }

    void setColumnProperties() {

        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        
        libelleColumn.setCellValueFactory(new PropertyValueFactory<>("liblle"));
        
        familleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailFamilleArticle, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailFamilleArticle, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getFamilleArticle().getLiblle());
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
           
         if (familleCombo.getSelectionModel().getSelectedIndex() == -1) {
            codeerreur.setText(Constantes.CHAMP_ETOILE);
            msgerrue = true;
        } else {
            familleAerreur.setText(Constantes.CHAMP_VIDE);

        }
         
        
    }

    void vider() {
        codefield.setText(Constantes.CHAMP_VIDE);
        libellefield.setText(Constantes.CHAMP_VIDE);
        familleCombo.getSelectionModel().select(-1);

        vider_erreur();

        btnAjouter.setDisable(false);
        loadDetail();
        setColumnProperties();

    }

    void vider_erreur() {
    libelleerreur.setText(Constantes.CHAMP_VIDE);
    codeerreur.setText(Constantes.CHAMP_VIDE);
    familleAerreur.setText(Constantes.CHAMP_VIDE);
         btnAjouter.setDisable(true);
    }
    

    @FXML
    private void afficherArt(MouseEvent event) {
              Integer r = detailFamilleTable.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            return;
        } else {
            codefield.setText(codeColumn.getCellData(r));
            libellefield.setText(libelleColumn.getCellData(r));
            familleCombo.setValue(familleColumn.getCellData(r));
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

            DetailFamilleArticle detailFamilleArticle = new DetailFamilleArticle();
            detailFamilleArticle.setCode(codefield.getText());
            detailFamilleArticle.setLiblle(libellefield.getText());
            FamilleArticle familleArticle = mapFamilleArticle.get(familleCombo.getSelectionModel().getSelectedItem());
            detailFamilleArticle.setFamilleArticle(familleArticle);
            
            detailFamilleArticledao.add(detailFamilleArticle);
            
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
        
         Integer r = detailFamilleTable.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            nav.showAlert(Alert.AlertType.WARNING, Constantes.TYPE_ALERT_INFORMATION, null, Constantes.MESSAGE_ALERT_SELECTIONNER);

        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_SUPPRIMER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();


            if (result.get() == ButtonType.OK) {
                DetailFamilleArticle detailFamilleArticle = detailFamilleTable.getSelectionModel().getSelectedItem();
                detailFamilleArticledao.delete(detailFamilleArticle);
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
            Integer r = detailFamilleTable.getSelectionModel().getSelectedIndex();
            if (r <= -1) {
                nav.showAlert(Alert.AlertType.CONFIRMATION, Constantes.TYPE_ALERT_INFORMATION, null, Constantes.MESSAGE_ALERT_SELECTIONNER);

            } else {
               
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_MODIFICATION);
                alert.setTitle("Confirmation");
                Optional<ButtonType> result = alert.showAndWait();


                if (result.get() == ButtonType.OK) {
                    DetailFamilleArticle detailFamilleArticle = detailFamilleTable.getSelectionModel().getSelectedItem();
                    detailFamilleArticle.setCode(codefield.getText());
                    detailFamilleArticle.setLiblle(libellefield.getText());
                    FamilleArticle familleArticle = mapFamilleArticle.get(familleCombo.getSelectionModel().getSelectedItem());
                          detailFamilleArticle.setFamilleArticle(familleArticle);
                   
                    nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_MODIFICATION);
                    detailFamilleArticledao.edit(detailFamilleArticle);
   
                    setColumnProperties();
                    loadDetail();
                    vider();
                }

            }

        }
        
    }
    
}
