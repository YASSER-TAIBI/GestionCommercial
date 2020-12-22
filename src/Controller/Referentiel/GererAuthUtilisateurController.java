/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import dao.Entity.Habilitation;
import dao.Entity.Utilisateur;
import dao.Manager.HabilitationDAO;
import dao.Manager.UtilisateurDAO;
import dao.ManagerImpl.HabilitationDAOImpl;
import dao.ManagerImpl.UtilisateurDAOImpl;
import function.navigation;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class GererAuthUtilisateurController implements Initializable {

    @FXML
    private TextField loginField;
    @FXML
    private TextField codeField;
    @FXML
    private TextField depotField;
    @FXML
    private ComboBox<String> utilisateurCombo;
    @FXML
    private TableView<Habilitation> tableMenu;
    @FXML
    private TableColumn<Habilitation, String> codeMenuColumn;
    @FXML
    private TableColumn<Habilitation, String> nomMenuColumn;
    @FXML
    private TableColumn<Habilitation, Boolean> actionColumn;
    @FXML
    private ImageView ValiderBtn;

    /**
     * Initializes the controller class.
     */
    
     private final ObservableList<Utilisateur> listeUtilisateur=FXCollections.observableArrayList();
     private ObservableList<Habilitation> listHabilitation=FXCollections.observableArrayList();
     
    UtilisateurDAO utilisateurDAO = new UtilisateurDAOImpl();
    HabilitationDAO habilitationDAO = new HabilitationDAOImpl();
    Utilisateur utilisateur = new Utilisateur();
    navigation nav = new navigation();
    private Map<String,Utilisateur> mapUtilisateur=new HashMap<>();
    private Map< Integer, Boolean> mapAuthorisation = new HashMap<>();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
              List<Utilisateur> listUtilisateur=utilisateurDAO.findAllSaufAdmin();
        
        listUtilisateur.stream().map((utilisateur) -> {
            utilisateurCombo.getItems().addAll(utilisateur.getNom());
            return utilisateur;
        }).forEachOrdered((utilisateur) -> {
            mapUtilisateur.put(utilisateur.getNom(), utilisateur);
        });

    
          actionColumn.setEditable(true);
       
          tableMenu.setEditable(true);
    }    
    
      void setColumnProperties() {

      codeMenuColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
     
        nomMenuColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Habilitation , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Habilitation, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getMenu().getLibelle());
            }

        });
     

      actionColumn.cellValueFactoryProperty();
          actionColumn.setCellValueFactory((cellData) -> {
          Habilitation cellvalue= cellData.getValue();
              BooleanProperty property = new SimpleBooleanProperty();
                      property.set(cellvalue.isAutorise());
                      
                      property.addListener((observabel, oldvalue,newvalue)->cellvalue.setAutorise(newvalue));
              
              return property; 
          });
          actionColumn.setCellFactory(act-> new CheckBoxTableCell<>());
    
          actionColumn.setEditable(true);
      
    }
    

    @FXML
    private void utilisateurComboOnAction(ActionEvent event) {
            listHabilitation.clear();

        utilisateur = mapUtilisateur.get(utilisateurCombo.getSelectionModel().getSelectedItem());
	depotField.setText(utilisateur.getDepot().getCode());
	codeField.setText(utilisateur.getId()+"");
	loginField.setText(utilisateur.getLogin());

        listHabilitation.addAll(habilitationDAO.findHabilitationByUtilisateur(utilisateur.getId()));
         tableMenu.setItems(listHabilitation);
         setColumnProperties();
    }

    @FXML
    private void afficherArt(MouseEvent event) {
    }

    @FXML
    private void ValiderOnAction(MouseEvent event) {
        
        for(int i = 0;i<listHabilitation.size() ; i++ ){

             Habilitation habilitation = listHabilitation.get(i);
       
             habilitationDAO.edit(habilitation); 
        }
        
          nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, "Ajout avec succès");
    }
    
    
}
