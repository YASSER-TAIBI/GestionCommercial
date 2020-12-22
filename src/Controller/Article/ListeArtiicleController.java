/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Article;

import Utils.Constantes;
import dao.Entity.Article;
import dao.Entity.DetailFamilleArticle;
import dao.Entity.DetailSousFamilleArticle;
import dao.Entity.FamilleArticle;
import dao.Entity.SousFamilleArticle;

import dao.Manager.ArticleDAO;
import dao.Manager.DetailFamilleArticleDAO;
import dao.Manager.DetailSousFamilleArticleDAO;
import dao.Manager.FamilleArticleDAO;
import dao.Manager.SousFamilleArticleDAO;

import dao.ManagerImpl.ArticleDAOImpl;
import dao.ManagerImpl.DetailFamilleArticleDAOImpl;
import dao.ManagerImpl.DetailSousFamilleArticleDAOImpl;
import dao.ManagerImpl.FamilleArticleDAOImpl;
import dao.ManagerImpl.SousFamilleArticleDAOImpl;

import function.navigation;
import java.io.IOException;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import jdk.nashorn.internal.ir.BreakNode;

public class ListeArtiicleController implements Initializable {

    @FXML
    private TextField codeField;
    @FXML
    private TextField libelleField;
    @FXML
    private TextField conditionnementField;
    @FXML
    private TextField poidsField;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRafraichir;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnModifer;
    @FXML
    private TextField codeFactureField;
    
    @FXML
    private TableView<Article> articleTable;
    @FXML
    private TableColumn<Article, String> codeArticleColumn;
    @FXML
    private TableColumn<Article, String> libelleColumn;
    @FXML
    private TableColumn<Article, Integer> conditionnementColumn;
    @FXML
    private TableColumn<Article, Integer> poidsColumn;
    @FXML
    private TableColumn<Article, String> categorieArticleColumn;
    @FXML
    private TableColumn<Article, String> codeFactureColumn;
    
    @FXML
    private ComboBox<String> familleCombo;
    @FXML
    private ComboBox<String> detailFamilleCombo;
    @FXML
    private ComboBox<String> sousFamilleCombo;
    @FXML
    private ComboBox<String> detailSousFamilleCombo;
   
    @FXML
    private Label conditionnementerreur;
    @FXML
    private Label poidserreur;

        private final ObservableList<Article> listeArticle = FXCollections.observableArrayList();

    ArticleDAO articleDAO = new ArticleDAOImpl();
    FamilleArticleDAO familleArticleDAO = new FamilleArticleDAOImpl();
    DetailFamilleArticleDAO detailFamilleArticleDAO = new DetailFamilleArticleDAOImpl();
    SousFamilleArticleDAO sousFamilleArticleDAO = new SousFamilleArticleDAOImpl();
    DetailSousFamilleArticleDAO detailSousFamilleArticleDAO = new DetailSousFamilleArticleDAOImpl();

    
    navigation nav = new navigation();
    
    private Map<String, FamilleArticle> mapFamille = new HashMap<>();
    private Map<String, DetailFamilleArticle> mapDetailFamille = new HashMap<>();
    private Map<String, SousFamilleArticle> mapSousFamille = new HashMap<>();
    private Map<String, DetailSousFamilleArticle> mapDetailSousFamille = new HashMap<>();

    
    Article article;
      boolean erreur = false;
   
 
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        articleTable.setEditable(true);

           List<FamilleArticle> listFamilleArticle = familleArticleDAO.findAll();

        listFamilleArticle.stream().map((famille) -> {
            familleCombo.getItems().addAll(famille.getLiblle());
            return famille;
        }).forEachOrdered((famille) -> {
            mapFamille.put(famille.getLiblle(), famille);
        });

        setColumnProperties();
  
        loadDetail();

    }

    void loadDetail() {

        listeArticle.clear();
        listeArticle.addAll(articleDAO.findAll());
        articleTable.setItems(listeArticle);
    }

    void setColumnProperties() {

        codeArticleColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        codeFactureColumn.setCellValueFactory(new PropertyValueFactory<>("codeFacture"));
        libelleColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        categorieArticleColumn.setCellValueFactory(new PropertyValueFactory<>("categorieArticle"));
        conditionnementColumn.setCellValueFactory(new PropertyValueFactory<>("conditionnement"));
        poidsColumn.setCellValueFactory(new PropertyValueFactory<>("poids"));
        categorieArticleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Article, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Article, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDetailSousFamilleArticle().getLiblle());
            }
        });
    }

 

  
    void vider() {
        btnAjouter.setDisable(false);
        codeField.clear();
        codeFactureField.clear();
        libelleField.clear();
        conditionnementField.clear();
        poidsField.clear();
        codeField.requestFocus();
        familleCombo.getSelectionModel().select(-1);
        detailFamilleCombo.getSelectionModel().select(-1);
        sousFamilleCombo.getSelectionModel().select(-1);
        detailSousFamilleCombo.getSelectionModel().select(-1);

        conditionnementerreur.setText("");
        poidserreur.setText("");
        loadDetail();

    }

    @FXML
    private void afficherArt(MouseEvent event) {

        Integer r = articleTable.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            return;
        } else {
            codeField.setText(codeArticleColumn.getCellData(r));
            codeFactureField.setText(codeFactureColumn.getCellData(r));
            libelleField.setText(libelleColumn.getCellData(r));
            conditionnementField.setText(String.valueOf(conditionnementColumn.getCellData(r)));
            poidsField.setText(String.valueOf(poidsColumn.getCellData(r)));
            
            btnAjouter.setDisable(true);
        }
    }




    void verifier() {
        if (conditionnementField.getText().matches(Constantes.CHAMP_VERIFIER) == false) {
            conditionnementerreur.setText(Constantes.CHAMP_INVALIDE);
            erreur = true;
        } else {
            if (Double.valueOf(conditionnementField.getText()) <= 0) {
                conditionnementerreur.setText(Constantes.CHAMP_INVALIDE);
                erreur = true;
            }
        }


        if (poidsField.getText().matches(Constantes.CHAMP_VERIFIER) == false) {
            poidserreur.setText(Constantes.CHAMP_INVALIDE);
            erreur = true;
        } else {
            if (Integer.valueOf(poidsField.getText()) < 0) {
                poidserreur.setText(Constantes.CHAMP_INVALIDE);
                erreur = true;
            }
        }

    }


    @FXML
    private void familleComboOnAction(ActionEvent event) {
         detailFamilleCombo.getItems().clear();
            FamilleArticle familleArticle  = mapFamille.get(familleCombo.getSelectionModel().getSelectedItem());
              if(familleArticle!=null){
            List<DetailFamilleArticle> listDetailFamilleArticle = detailFamilleArticleDAO.findByFamille(familleArticle.getId());

              if(listDetailFamilleArticle!=null){
            listDetailFamilleArticle.stream().map((detailFamille) -> {
              detailFamilleCombo.getItems().addAll(detailFamille.getLiblle());
                return detailFamille;
            }).forEachOrdered((detailFamille) -> {
                mapDetailFamille.put(detailFamille.getLiblle(), detailFamille);
            });
            }
    }}

    @FXML
    private void detailFamilleOnAction(ActionEvent event) {
          sousFamilleCombo.getItems().clear();
            DetailFamilleArticle detailFamilleArticle  = mapDetailFamille.get(detailFamilleCombo.getSelectionModel().getSelectedItem());
             if(detailFamilleArticle!=null){
            List<SousFamilleArticle> listSousFamilleArticle = sousFamilleArticleDAO.findByDetailFamille(detailFamilleArticle.getId());
            
              if(listSousFamilleArticle!=null){
            listSousFamilleArticle.stream().map((sousFamille) -> {
              sousFamilleCombo.getItems().addAll(sousFamille.getLiblle());
                return sousFamille;
            }).forEachOrdered((sousFamille) -> {
                mapSousFamille.put(sousFamille.getLiblle(), sousFamille);
            });
              }}
    }

    @FXML
    private void sousFamilleOnAction(ActionEvent event) {
        
           detailSousFamilleCombo.getItems().clear();
            SousFamilleArticle sousFamilleArticle  = mapSousFamille.get(sousFamilleCombo.getSelectionModel().getSelectedItem());
             if(sousFamilleArticle!=null){
            List<DetailSousFamilleArticle> listDetailSousFamilleArticle = detailSousFamilleArticleDAO.findByDetailSousFamille(sousFamilleArticle.getId());
            
            if(listDetailSousFamilleArticle!=null){
            listDetailSousFamilleArticle.stream().map((detailSousFamille) -> {
              detailSousFamilleCombo.getItems().addAll(detailSousFamille.getLiblle());
                return detailSousFamille;
            }).forEachOrdered((detailSousFamille) -> {
                mapDetailSousFamille.put(detailSousFamille.getLiblle(), detailSousFamille);
            });
            }
             }
    }

    @FXML
    private void detailSousFamilleOnAction(ActionEvent event) {
    }

    @FXML
    private void ajouterOnAction(ActionEvent event) {
        
          erreur = false;
        conditionnementerreur.setText("");
        poidserreur.setText("");

        Article art = new Article();
        if (codeField.getText().equals("") || codeFactureField.getText().equals("") || libelleField.getText().equals("") || conditionnementField.getText().equals("")|| poidsField.getText().equals("") || detailSousFamilleCombo.getSelectionModel().getSelectedIndex()==-1) {
            nav.showAlert(Alert.AlertType.CONFIRMATION, "Information", null, "Veuillez entrer tous les champs ");
        } else {
            verifier();
            if (erreur == true) {
                return;
            } else {
                art.setCode(codeField.getText());
                art.setCodeFacture(codeFactureColumn.getText());
                art.setLibelle(libelleField.getText());
                art.setConditionnement(Integer.valueOf(conditionnementField.getText()));
                art.setPoids(Integer.parseInt(poidsField.getText()));
                DetailSousFamilleArticle detailSousFamilleArticle = mapDetailSousFamille.get(detailSousFamilleCombo.getSelectionModel().getSelectedItem());
                art.setDetailSousFamilleArticle(detailSousFamilleArticle);
                art.setAction(false);
                articleDAO.add(art);
                nav.showAlert(Alert.AlertType.INFORMATION, "Succès", null, "Ajout avec succès");
                setColumnProperties();
                loadDetail();
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
        
                 Integer r = articleTable.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            nav.showAlert(Alert.AlertType.WARNING, "Information", null, "Veuillez selectionner un article SVP !!!");

        } else {
  Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setContentText("Voullez vous vraiment Supprimer cet Article ?");
                    alert.setTitle("Confirmation");
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == ButtonType.OK) {
            Article article = articleTable.getSelectionModel().getSelectedItem();
            articleDAO.delete(article);
            nav.showAlert(Alert.AlertType.INFORMATION, "Succès", null, "Article Supprimer avec succès");
            setColumnProperties();
            loadDetail();
            vider();
            }
        }
        
    }

    @FXML
    private void modifierOnAction(ActionEvent event) {
        
          erreur = false;
        conditionnementerreur.setText("");
        poidserreur.setText("");

        Integer r = articleTable.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            nav.showAlert(Alert.AlertType.CONFIRMATION, "Information", null, "Veuillez selectionner un article SVP !!!");

        } else {
            if (codeField.getText().equals("")|| codeFactureField.getText().equals("") || libelleField.getText().equals("")|| poidsField.getText().equals("") || conditionnementField.getText().equals("")|| detailSousFamilleCombo.getSelectionModel().getSelectedIndex()==-1 ) {
                nav.showAlert(Alert.AlertType.CONFIRMATION, "Information", null, "Veuillez entrer tous les champs ");
            } else {
                verifier();
                if (erreur == true) {

                    return;

                } else {
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setContentText("Voullez vous vraiment Modifier cet Article ?");
                    alert.setTitle("Confirmation");
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == ButtonType.OK) {
                        Article article = articleTable.getSelectionModel().getSelectedItem();
                        article.setCode(codeField.getText());
                        article.setCodeFacture(codeFactureField.getText());
                        article.setLibelle(libelleField.getText());
                        DetailSousFamilleArticle detailSousFamilleArticle = mapDetailSousFamille.get(detailSousFamilleCombo.getSelectionModel().getSelectedItem());
                        article.setDetailSousFamilleArticle(detailSousFamilleArticle);
                        article.setConditionnement(Integer.valueOf(conditionnementField.getText()));
                        
                        article.setPoids(Integer.valueOf(poidsField.getText()));
                        nav.showAlert(Alert.AlertType.INFORMATION, "Succès", null, "Article Modifié avec succès");
                        articleDAO.edit(article);
                      
                        setColumnProperties();
                        loadDetail();
                        vider();
                    }
                }
            }
        }
    }
}
