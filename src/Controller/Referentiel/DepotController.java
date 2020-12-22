
package Controller.Referentiel;
import Utils.Constantes;
import dao.Entity.Depot;
import dao.Entity.SequenceurClient;
import dao.Manager.DepotDAO;
import dao.Manager.SequenceurClientDAO;
import dao.ManagerImpl.ClientPFDAOImpl;
import dao.ManagerImpl.DepotDAOImpl;
import dao.ManagerImpl.SequenceurClientDAOImpl;
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
import javafx.scene.control.PasswordField;
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
public class DepotController implements Initializable {



    
    @FXML
    private TableView<Depot> depottable;
    @FXML
    private TableColumn<Depot, String> codeColumn;
    @FXML
    private TableColumn<Depot, String> libelleColumn;
    
    @FXML
    private TextField codeField;
    
    private final ObservableList<Depot> listeDepot = FXCollections.observableArrayList();
    @FXML
    private TextField libelleField;
    @FXML
    private Label codeerreur;
    @FXML
    private Label libelleerreur;
    @FXML
    private Label villeerreur;
    
    @FXML
    private Button btnAjouter1;
    @FXML
    private Button btnRafraichir;
    @FXML
    private Button btnSupprimer1;
    @FXML
    private Button btnModifer;

    
    SequenceurClientDAO sequenceurClientDAO = new SequenceurClientDAOImpl();
    DepotDAO depotdao = new DepotDAOImpl();
    navigation nav = new navigation();
    boolean msgerrue = false;
    /**
     * Initializes the controller class.
     */
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         depottable.setEditable(true);

        loadDetail();
        setColumnProperties();
      

    }


    void loadDetail() {

        listeDepot.clear();
        listeDepot.addAll(depotdao.findAll());
        depottable.setItems(listeDepot);
        
    }

    void setColumnProperties() {

        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        libelleColumn.setCellValueFactory(new PropertyValueFactory<>("libelle1"));

    }


    void vider() {


    }
    void vider_erreur()
    {
        codeerreur.setText(Constantes.CHAMP_VIDE);
        libelleerreur.setText(Constantes.CHAMP_VIDE);
    }

    
    
    void verifier()
    {
         if (codeField.getText().equals(Constantes.CHAMP_VIDE)) {
            codeerreur.setText(Constantes.CHAMP_ETOILE);
            codeField.requestFocus();
            msgerrue = true;

        } else {
            codeerreur.setText(Constantes.CHAMP_VIDE);

        }
        if (libelleField.getText().equals(Constantes.CHAMP_VIDE)) {
            libelleerreur.setText(Constantes.CHAMP_ETOILE);
            libelleField.requestFocus();
            msgerrue = true;

        } else {
            libelleerreur.setText(Constantes.CHAMP_VIDE);

        }
      
        
            
 }

   

    @FXML
    private void afficherArt(MouseEvent event) {
               Integer r = depottable.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            return;
        } else {
            codeField.setText(codeColumn.getCellData(r));
            libelleField.setText(libelleColumn.getCellData(r));
            vider_erreur();
            btnAjouter1.setDisable(true);

        }
    }


    @FXML
    private void ajouterOnAction(ActionEvent event) {
        
             msgerrue = false;
        verifier();
        if (msgerrue == true) {
            return;
        } else {

           
            Depot dep = new Depot();
            dep.setCode(codeField.getText());
            dep.setLibelle1(libelleField.getText());

            depotdao.add(dep);
            
//########################################################## Creation de Sequenceur Client ###############################################################################################################################################################################################             

    SequenceurClient sequenceurClient = new  SequenceurClient();
         
       sequenceurClient.setCode(codeField.getText().substring(2).toUpperCase());
       sequenceurClient.setDepot(dep);
       sequenceurClient.setSeq(0);

          sequenceurClientDAO.add(sequenceurClient);
         
          
            nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_AJOUT);
            setColumnProperties();
            loadDetail();
            vider();

        }
        
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
        
        
        codeField.setText(Constantes.CHAMP_VIDE);
        libelleField.setText(Constantes.CHAMP_VIDE);

        vider_erreur();

        btnAjouter1.setDisable(false);
        loadDetail();
        setColumnProperties();
        
    }

    @FXML
    private void supprimerOnAction(ActionEvent event) {
        
              Integer r = depottable.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            nav.showAlert(Alert.AlertType.WARNING, Constantes.TYPE_ALERT_INFORMATION, null, Constantes.MESSAGE_ALERT_SELECTIONNER);

        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_SUPPRIMER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();


            if (result.get() == ButtonType.OK) {
                Depot dep = depottable.getSelectionModel().getSelectedItem();
                depotdao.delete(dep);
                nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_INFORMATION, null, Constantes.MESSAGE_ALERT_SUPPRIMER);
                setColumnProperties();
                loadDetail();
                vider();
            }

        }
        
    }

    @FXML
    private void modifierOnAction(ActionEvent event) {
        
            msgerrue = false;
        verifier();
        if (msgerrue == true) {
            return;
        } else {
            Integer r = depottable.getSelectionModel().getSelectedIndex();
            if (r <= -1) {
                nav.showAlert(Alert.AlertType.CONFIRMATION, Constantes.TYPE_ALERT_INFORMATION, null, Constantes.MESSAGE_ALERT_SELECTIONNER);

            } else {
               
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_MODIFICATION);
                alert.setTitle("Confirmation");
                Optional<ButtonType> result = alert.showAndWait();


                if (result.get() == ButtonType.OK) {
                    Depot dep = depottable.getSelectionModel().getSelectedItem();
                    dep.setCode(codeField.getText());
                    dep.setLibelle1(libelleField.getText());
                    
                   
                    
                    nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_MODIFICATION);
                    depotdao.edit(dep);

                    setColumnProperties();
                    loadDetail();
                    vider();
                }

            }

        }

        
    }

   

}
