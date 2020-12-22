/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.GestionTournee;

import Utils.Constantes;
import dao.Entity.Article;
import dao.Entity.Sequenceur;
import dao.Manager.ArticleDAO;
import dao.ManagerImpl.ArticleDAOImpl;
import function.navigation;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/** 
 * FXML Controller class
 * abstract
 * @author Hp
 * 
 */


public abstract class RechercheArticleController extends AnchorPane implements Initializable {
     private int POUR;
    Article article;

    
    
    public RechercheArticleController (int POUR, Article article){
    this.article= article;
    this.POUR= POUR;
    setAll("/View/GestionTournee/RechercheArticle.fxml", this);
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
    private TableView<Article> acticleTable;
    @FXML
    private TableColumn<Article, String> codeArticleColumn;
    @FXML
    private TableColumn<Article, String> libelleColumn;
    @FXML
    private TableColumn<Article, Integer> conditionnementColumn;
    @FXML
    private TableColumn<Article, Integer> poidsColumn;
    @FXML
    private TableColumn<Article, Boolean> actionColumn;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRafraichir;
    @FXML
    private TextField rechercheArticleField;
    @FXML
    private ImageView selectionnerTout;
    
          private final ObservableList<Article> listeArticle = FXCollections.observableArrayList();
          private final ObservableList<Article> listeArticleTmp=FXCollections.observableArrayList(); 

    ArticleDAO articleDAO = new ArticleDAOImpl();
    navigation nav = new navigation();
    @FXML
    private ImageView selectionnerTout1;

  
   
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
          acticleTable.setEditable(true);
          actionColumn.setEditable(true);
          
          setColumnProperties();
          loadDetail();
    }    

       void loadDetail() {

        listeArticle.clear();
        listeArticle.addAll(articleDAO.findAll());
        acticleTable.setItems(listeArticle);
    }

    void setColumnProperties() {

        codeArticleColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        libelleColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        conditionnementColumn.setCellValueFactory(new PropertyValueFactory<>("conditionnement"));
        poidsColumn.setCellValueFactory(new PropertyValueFactory<>("poids"));
        
         actionColumn.cellValueFactoryProperty();
          actionColumn.setCellValueFactory((cellData) -> {
          Article cellvalue= cellData.getValue();
              BooleanProperty property = new SimpleBooleanProperty();
                      property.set(cellvalue.getAction());
                      
                      property.addListener((observabel, oldvalue,newvalue)->cellvalue.setAction(newvalue));
              
              return property; 
          });
          actionColumn.setCellFactory(act-> new CheckBoxTableCell<>());
    }
    

    
    @FXML
    private void afficherArt(MouseEvent event) {
    }

  

       public abstract void selectedArticle(ObservableList<Article>list);     
    


    @FXML
    private void rechercheArticleOnKeyPressed(KeyEvent event) {
          ObservableList<Article> listeArticle=FXCollections.observableArrayList();
    listeArticle.clear();
   
   listeArticle.addAll(articleDAO.findArticleByRecherche(rechercheArticleField.getText()));
   
   acticleTable.setItems(listeArticle);
    }

    @FXML
    private void selectionnerToutMouseClicked(MouseEvent event) {
        
        ObservableList<Article> list=acticleTable.getItems();
        
        for (Iterator<Article> iterator = list.iterator(); iterator.hasNext();) {

            iterator.next().setAction(true);
        }

        acticleTable.refresh(); 
        
    }

    @FXML
    private void deselectionnerToutMouseClicked(MouseEvent event) {
        
           ObservableList<Article> list=acticleTable.getItems();
        
        for (Iterator<Article> iterator = list.iterator(); iterator.hasNext();) {

            iterator.next().setAction(false);
        }

        acticleTable.refresh(); 
        
        
    }
    
     @FXML
    private void ajouterOnAction(ActionEvent event) {  
        
       for( int rows = 0;rows<acticleTable.getItems().size() ;rows++ ){
     
         if(actionColumn.getCellData(rows).booleanValue()==true){
        
        Article articleTmp= acticleTable.getItems().get(rows);
        listeArticleTmp.add(articleTmp);
        
        System.out.println("listeArticleTmp "+listeArticleTmp.get(listeArticleTmp.size()-1));
        
         }

     }
            
         System.out.println("listeArticleTmp "+listeArticleTmp.size());
       
            selectedArticle(listeArticleTmp);
            refresh();
            Stage stage = (Stage) btnAjouter.getScene().getWindow();
            stage.close();
            loadDetail();

    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
     
    }
    
}
