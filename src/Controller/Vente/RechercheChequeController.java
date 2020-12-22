/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Vente;

import dao.Entity.Article;
import dao.Entity.Cheque;
import dao.Manager.ArticleDAO;
import dao.Manager.ChequeDAO;
import dao.ManagerImpl.ArticleDAOImpl;
import dao.ManagerImpl.ChequeDAOImpl;
import function.navigation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public abstract class RechercheChequeController extends AnchorPane implements Initializable {
    private int POUR;
    Cheque cheque;

    
    
    public RechercheChequeController (int POUR, Cheque cheque){
    this.cheque= cheque;
    this.POUR= POUR;
    setAll("/View/Vente/RechercheCheque.fxml", this);
    }
    
    public static void setAll(String path, Object root){
    FXMLLoader fxmlLoader = new FXMLLoader(root.getClass().getResource(path));
        fxmlLoader.setRoot(root);
        fxmlLoader.setController(root);
        try {
            System.out.println(path);
            fxmlLoader.load();
        } catch (IOException exception){
            throw new RuntimeException(exception);
        }

    }
    
    public abstract void refresh();
    
    
    
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private TableView<Cheque> chequeTable;
    @FXML
    private TableColumn<Cheque, String> codeChequeColumn;
    @FXML
    private TableColumn<Cheque, String> numChequeColumn;
    @FXML
    private TableColumn<Cheque, String> nomProprietaireColumn;
    @FXML
    private TableColumn<Cheque, String> banqueColumn;
    @FXML
    private TableColumn<Cheque, Boolean> actionColumn;
    @FXML
    private ImageView ajoutBtn;
    @FXML
    private ImageView videBtn;
    @FXML
    private TextField rechercheCheque;

    
      
          private final ObservableList<Cheque> listeCheque = FXCollections.observableArrayList();
          private final ObservableList<Cheque> listeChequeTmp=FXCollections.observableArrayList(); 
          
            ChequeDAO chequeDAO = new ChequeDAOImpl();
    navigation nav = new navigation();
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    chequeTable.setEditable(true);
          actionColumn.setEditable(true);
          
          setColumnProperties();
          loadDetail();
    }    

       void loadDetail() {

        listeCheque.clear();
        listeCheque.addAll(chequeDAO.findAll());
        chequeTable.setItems(listeCheque);
    }

    void setColumnProperties() {

        codeChequeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        numChequeColumn.setCellValueFactory(new PropertyValueFactory<>("num_cheque"));
        nomProprietaireColumn.setCellValueFactory(new PropertyValueFactory<>("nom_Proprietaire"));
   
        banqueColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Cheque, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Cheque, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getBanque().getLibelle());
            }
        });
        
         actionColumn.cellValueFactoryProperty();
          actionColumn.setCellValueFactory((cellData) -> {
          Cheque cellvalue= cellData.getValue();
              BooleanProperty property = new SimpleBooleanProperty();
                      property.set(cellvalue.isAction());
                      
                      property.addListener((observabel, oldvalue,newvalue)->cellvalue.setAction(newvalue));
              
              return property; 
          });
          actionColumn.setCellFactory(act-> new CheckBoxTableCell<>());
    }

    @FXML
    private void afficherArt(MouseEvent event) {
    }

    @FXML
    private void ajouter(MouseEvent event) {
        
               
  for( int rows = 0;rows<chequeTable.getItems().size() ;rows++ ){
     
         if(actionColumn.getCellData(rows).booleanValue()==true){
        
        Cheque chequeTmp= chequeTable.getItems().get(rows);
        listeChequeTmp.add(chequeTmp);
        
         }

     }
            
            selectedPrixDimFour(listeChequeTmp);
            refresh();
            Stage stage = (Stage) ajoutBtn.getScene().getWindow();
            stage.close();
            loadDetail();

      }

       public abstract void selectedPrixDimFour(ObservableList<Cheque>list);     
    

    

    @FXML
    private void vider(MouseEvent event) {
        
        rechercheCheque.getText().equals("");
        
    }
    
}
