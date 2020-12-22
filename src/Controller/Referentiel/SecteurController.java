/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.Secteur;
import dao.Entity.Ville;
import dao.Manager.SecteurDAO;
import dao.Manager.VilleDAO;
import dao.ManagerImpl.SecteurDAOImpl;
import dao.ManagerImpl.VilleDAOImpl;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class SecteurController implements Initializable {

    SecteurDAO secteurdao = new SecteurDAOImpl();
    VilleDAO villeDAO = new VilleDAOImpl();
    navigation nav = new navigation();
    Ville ville;
    private final ObservableList<Secteur> listeSecteur = FXCollections.observableArrayList();
    @FXML
    private TextField libellesecteurfield;
    @FXML
    private TextField codesecteurField;
    @FXML
    private ComboBox<String> villecombo;

    private Map<String, Ville> mapVille = new HashMap<>();
    @FXML
    private TableView<Secteur> secteurtable;
    @FXML
    private TableColumn<Secteur, String> codecolumn;
    @FXML
    private TableColumn<Secteur, String> libellecolumn;
    @FXML
    private TableColumn<Secteur, String> villecolumn;

    @FXML
    private Label codeerreur;
    @FXML
    private Label libelleerreur;
    @FXML
    private Label villeerreur;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRafraichir;
    @FXML
    private Button btnModifer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       loadDetail();
       setColumnProperties();

        chargerville();
    }

    void loadDetail() {

        listeSecteur.clear();
        listeSecteur.addAll(secteurdao.findAll());
        secteurtable.setItems(listeSecteur);
    }

    void chargerville() {

        List<Ville> listVille = villeDAO.findVilleByDepot(nav.getUtilisateur().getDepot().getId());

        listVille.stream().map((ville) -> {
            villecombo.getItems().addAll(ville.getLibelle());
            return ville;
        }).forEachOrdered((ville) -> {
            mapVille.put(ville.getLibelle(), ville);
        });
    }

    void setColumnProperties() {

        codecolumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        libellecolumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));
  
        villecolumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Secteur, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Secteur, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getVille().getLibelle());
            }
        });
    }

 

    void vider() {
        loadDetail();
        codesecteurField.setText(Constantes.CHAMP_VIDE);
        libellesecteurfield.setText(Constantes.CHAMP_VIDE);
        villecombo.getSelectionModel().select(-1);
   

        btnAjouter.setDisable(false);


    }


 
    @FXML
    private void afficherArt(MouseEvent event) {
              Integer r = secteurtable.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            return;
        } else {
            codesecteurField.setText(codecolumn.getCellData(r));
            libellesecteurfield.setText(libellecolumn.getCellData(r));
            villecombo.setValue(villecolumn.getCellData(r));
            btnAjouter.setDisable(true);

        }
    }

    @FXML
    private void ajouterOnAction(ActionEvent event) {
               if (codesecteurField.getText().equals(Constantes.CHAMP_VIDE) || libellesecteurfield.getText().equals(Constantes.CHAMP_VIDE)) {
            nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_ERRUR, null, Constantes.MESSAGE_ALERT_saisir_secteur);

            return;

        } else {
            if (villecombo.getSelectionModel().getSelectedIndex() == -1) {
                nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_ERRUR, null, Constantes.MESSAGE_ALERT_saisir_ville);

                return;
            } else {
                Secteur secteur = new Secteur();
                secteur.setCode(codesecteurField.getText());
                secteur.setLibelle(libellesecteurfield.getText());
                Ville villesel = mapVille.get(villecombo.getSelectionModel().getSelectedItem());
                secteur.setVille(villesel);
                secteurdao.add(secteur);
                nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_AJOUT);
                vider();

            }

        }

    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
        vider();
    }

    @FXML
    private void supprimerOnAction(ActionEvent event) {
          Integer r = secteurtable.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            nav.showAlert(Alert.AlertType.WARNING, Constantes.TYPE_ALERT_INFORMATION, null, Constantes.MESSAGE_ALERT_SELECTIONNER);

        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_SUPPRIMER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();


            if (result.get() == ButtonType.OK) {
                Secteur secteur = secteurtable.getSelectionModel().getSelectedItem();
                secteurdao.delete(secteur);
                nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_SUPPRIMER);
                setColumnProperties();
                loadDetail();
                vider();
            }

        }
    }

    @FXML
    private void modifierOnAction(ActionEvent event) {
          Integer r = secteurtable.getSelectionModel().getSelectedIndex();
            if (r <= -1) {
                nav.showAlert(Alert.AlertType.CONFIRMATION, Constantes.TYPE_ALERT_INFORMATION, null, Constantes.MESSAGE_ALERT_SELECTIONNER);

            } else {
               
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_MODIFICATION);
                alert.setTitle("Confirmation");
                Optional<ButtonType> result = alert.showAndWait();


                if (result.get() == ButtonType.OK) {
                    Secteur secteur = secteurtable.getSelectionModel().getSelectedItem();
                   secteur.setCode(codesecteurField.getText());
                   secteur.setLibelle(libellesecteurfield.getText());
                    Ville villesel = mapVille.get(villecombo.getSelectionModel().getSelectedItem());
                    secteur.setVille(villesel);
                    
                    nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_MODIFICATION);
                    secteurdao.edit(secteur);

                    setColumnProperties();
                    loadDetail();
                    vider();
                }

            }
    }

}
