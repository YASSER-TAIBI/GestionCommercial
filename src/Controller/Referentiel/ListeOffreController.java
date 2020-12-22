/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.Article;
import dao.Entity.ConditionOffre;
import dao.Entity.CreationOffre;
import dao.Entity.Depot;
import dao.Entity.DetailFacture;
import dao.Entity.DetailFamilleArticle;
import dao.Entity.DetailSousFamilleArticle;
import dao.Entity.FamilleArticle;
import dao.Entity.Offre;
import dao.Entity.Secteur;
import dao.Entity.Sequenceur;
import dao.Entity.SousFamilleArticle;
import dao.Entity.TypeVente;
import dao.Manager.ArticleDAO;
import dao.Manager.CreationOffreDAO;
import dao.Manager.DepotDAO;
import dao.Manager.DetailFamilleArticleDAO;
import dao.Manager.DetailSousFamilleArticleDAO;
import dao.Manager.FamilleArticleDAO;
import dao.Manager.OffreDAO;
import dao.Manager.SecteurDAO;
import dao.Manager.SequenceurDAO;
import dao.Manager.SequenceurFactureDAO;
import dao.Manager.SousFamilleArticleDAO;
import dao.ManagerImpl.ArticleDAOImpl;
import dao.ManagerImpl.CreationOffreDAOImpl;
import dao.ManagerImpl.DepotDAOImpl;
import dao.ManagerImpl.DetailFamilleArticleDAOImpl;
import dao.ManagerImpl.DetailSousFamilleArticleDAOImpl;
import dao.ManagerImpl.FamilleArticleDAOImpl;
import dao.ManagerImpl.OffreDAOImpl;
import dao.ManagerImpl.SecteurDAOImpl;
import dao.ManagerImpl.SequenceurDAOImpl;
import dao.ManagerImpl.SequenceurFactureDAOImpl;
import dao.ManagerImpl.SousFamilleArticleDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ListeOffreController implements Initializable {

    @FXML
    private TextField codeOffreField;
    @FXML
    private ComboBox<String> depotCombo;
    @FXML
    private DatePicker dateDebutPicker;
    @FXML
    private DatePicker dateFinPicker;
    @FXML
    private ComboBox<String> typeOffreCombo;
    @FXML
    private TextField qteOffreField;
    @FXML
    private ComboBox<String> secteurCombo;
    @FXML
    private ImageView ajoutSecteurBtn;
    @FXML
    private ImageView supprimerSecteurBtn;
    @FXML
    private ImageView videSecteurBtn;
    @FXML
    private TableView<Secteur> tableSecteur;
    @FXML
    private TableColumn<Secteur, String> codeColumn;
    @FXML
    private TableColumn<Secteur, String> secteurColumn;
    @FXML
    private TextField quantiteCondField;
    @FXML
    private ImageView ajoutCondBtn;
    @FXML
    private ImageView supprimerCondBtn;
    @FXML
    private ImageView videCondBtn;
    @FXML
    private TableView<ConditionOffre> tableConditionOffre;
    @FXML
    private TableColumn<ConditionOffre, BigDecimal> qteCondColumn;
    @FXML
    private TextField quantiteOffreField;
    @FXML
    private ImageView ajoutOffreBtn;
    @FXML
    private ImageView supprimerOffreBtn;
    @FXML
    private ImageView videOffreBtn;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRafraichir;
    @FXML
    private TableView<Offre> tableOffre;
    @FXML
    private TableColumn<Offre, BigDecimal> qteOffreColumn;
    @FXML
    private HBox hBoxCRUD;
    @FXML
    private Label secteurLabel;
    @FXML
    private RadioButton regionRadio;
    @FXML
    private ToggleGroup categorie;
    @FXML
    private RadioButton secteurRadio;
    
    @FXML
    private TextField codeFactureCondField;
    @FXML
    private TextField codeArticleCondField;
    @FXML
    private ComboBox<String> ArticleCondCombo;
    @FXML
    private TextField codeFactureOffreField;
    @FXML
    private TextField codeArticleOffreField;
    @FXML
    private ComboBox<String> ArticleOffreCombo;
    @FXML
    private TextField refCondField;
    @FXML
    private TableColumn<ConditionOffre, String> codeFactCondColumn;
    @FXML
    private TableColumn<ConditionOffre, String> codeArtCondColumn;
    @FXML
    private TableColumn<ConditionOffre, String> ArtCondColumn;
    @FXML
    private TableColumn<Offre, String> codeFactOffreColumn;
    @FXML
    private TableColumn<Offre, String> codeArtOffreColumn;
    @FXML
    private TableColumn<Offre, String> ArtOffreColumn;
    @FXML
    private TableColumn<ConditionOffre, String> refCondColumn;
    @FXML
    private TableColumn<Offre, String> refOffreColumn;
    @FXML
    private RadioButton groupeRadio;
    @FXML
    private ToggleGroup typeOu;
    @FXML
    private RadioButton individuelRadio;
    @FXML
    private TextField refOffreField;
    
      SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();
      navigation nav = new navigation();
      DepotDAO depotDAO = new DepotDAOImpl();
      SecteurDAO secteurDAO = new SecteurDAOImpl();
      ArticleDAO articleDAO = new ArticleDAOImpl();
      CreationOffreDAO creationOffreDAO = new  CreationOffreDAOImpl();
      
      OffreDAO offreDAO = new OffreDAOImpl();
      
    private Map<String, Depot> mapDepot = new HashMap<>();
    private Map<String, Secteur> mapSecteur = new HashMap<>();  
    
    FamilleArticleDAO familleArticleDAO = new FamilleArticleDAOImpl();
    DetailFamilleArticleDAO detailFamilleArticleDAO = new DetailFamilleArticleDAOImpl();
    SousFamilleArticleDAO sousFamilleArticleDAO = new SousFamilleArticleDAOImpl();
    DetailSousFamilleArticleDAO detailSousFamilleArticleDAO = new DetailSousFamilleArticleDAOImpl();
    
    private Map<String, Article> mapLibelleArtCond = new HashMap<>();
    private Map<String, Article> mapcodeArticleCond = new HashMap<>();
    private Map<String, Article> mapcodeFactureCond = new HashMap<>();
    
    private Map<String, Article> mapLibelleArtOffre = new HashMap<>();
    private Map<String, Article> mapcodeArticleOffre = new HashMap<>();
    private Map<String, Article> mapcodeFactureOffre = new HashMap<>();
    
      private final ObservableList<Secteur> listSecteur =FXCollections.observableArrayList(); 
      private final ObservableList<ConditionOffre> listConditionOffre=FXCollections.observableArrayList(); 
      private final ObservableList<ConditionOffre> listConditionOffreTMP=FXCollections.observableArrayList(); 
      private final ObservableList<Offre> listOffreTMP=FXCollections.observableArrayList(); 
      private final ObservableList<Offre> listOffre=FXCollections.observableArrayList(); 
      
      ObservableList<String> TypeOffre =FXCollections.observableArrayList("OU","ET");
      
      CreationOffre creationOffre = new CreationOffre();
    
    
    
    
    

      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
 
           if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
                  List<Depot> listDepot =  depotDAO.findByDepotSaufSiege();

            listDepot.stream().map((depot) -> {
                depotCombo.getItems().addAll(depot.getLibelle1());
                return depot;
            }).forEachOrdered((depot) -> {
                mapDepot.put(depot.getLibelle1(), depot);
            });
       
        
             List<Article> listArticle = articleDAO.findAll();

        listArticle.stream().map((article) -> {
            ArticleCondCombo.getItems().addAll(article.getLibelle());
            return article;
        }).forEachOrdered((Article) -> {
            mapLibelleArtCond.put(Article.getLibelle(), Article);
            mapcodeArticleCond.put(Article.getCode(), Article);
            mapcodeFactureCond.put(Article.getCodeFacture(), Article);
        });
            
             listArticle.stream().map((article) -> {
            ArticleOffreCombo.getItems().addAll(article.getLibelle());
            return article;
        }).forEachOrdered((Article) -> {
            mapLibelleArtOffre.put(Article.getLibelle(), Article);
            mapcodeArticleOffre.put(Article.getCode(), Article);
            mapcodeFactureOffre.put(Article.getCodeFacture(), Article);
        });
             
             
            typeOffreCombo.setItems(TypeOffre);
            
        Incrementation();
        IncrementationArtOff();
        refOffreField.setText(refCondField.getText());
        VisibleFalse();
           }
    }    

   void VisibleFalse (){
   
   tableSecteur.setVisible(Boolean.FALSE);
   hBoxCRUD.setVisible(Boolean.FALSE);
   secteurCombo.setVisible(Boolean.FALSE);
   secteurLabel.setVisible(Boolean.FALSE);
   }
   
    void VisibleTrue (){
   
   tableSecteur.setVisible(Boolean.TRUE);
   hBoxCRUD.setVisible(Boolean.TRUE);
   secteurCombo.setVisible(Boolean.TRUE);
   secteurLabel.setVisible(Boolean.TRUE);
   }
    
           void Incrementation (){
       
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.OFFRE);
codeOffreField.setText(sequenceur.getCode()+" "+(sequenceur.getValeur()+1));

   }
    
         void IncrementationArtOff(){
       
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.ARTICLE_OFFRE);
refCondField.setText(sequenceur.getCode()+" "+(sequenceur.getValeur()+1));

   }   
           
           void setColumnPropertiesSecteur() {

        codeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Secteur, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Secteur, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getCode());
            }
        });

            secteurColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Secteur, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Secteur, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getLibelle());
            }
        });
        

    }
           
      void setColumnPropertiesCond() {

           codeArtCondColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ConditionOffre, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ConditionOffre, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getArticle().getCode());
            }
        });
          
        codeFactCondColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ConditionOffre, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ConditionOffre, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getArticle().getCodeFacture());
            }
        });

        ArtCondColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ConditionOffre, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ConditionOffre, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getArticle().getLibelle());
            }
        });

        qteCondColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ConditionOffre, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<ConditionOffre, BigDecimal> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getQuantite());
            }
        });

        refCondColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ConditionOffre, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ConditionOffre, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getRefCondition());
            }
        });

    }

      void setColumnPropertiesOffre() {

           codeArtOffreColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Offre, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Offre, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getArticle().getCode());
            }
        });
          
        codeFactOffreColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Offre, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Offre, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getArticle().getCodeFacture());
            }
        });

        ArtOffreColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Offre, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Offre, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getArticle().getLibelle());
            }
        });

        qteOffreColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Offre, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<Offre, BigDecimal> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getQuantite());
            }
        });


         refOffreColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Offre, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Offre, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getRefOffre());
            }
        });
        
    }
      
    @FXML
    private void secteurComboOnAction(ActionEvent event) {
    }


    @FXML
    private void afficherArt(MouseEvent event) {
    }


    @FXML
    private void ajouterOnAction(ActionEvent event) throws ParseException {
        
    if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
                       
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                
          if(depotCombo.getSelectionModel().getSelectedIndex()==-1 ||
                  codeOffreField.getText().isEmpty() ||
                  dateDebutPicker.getValue()==null ||
                  dateFinPicker.getValue()==null||
                  typeOffreCombo.getSelectionModel().getSelectedIndex()==-1 ||
                  qteOffreField.getText().isEmpty() ||
                  tableConditionOffre.getItems().size()==0 ||
                  tableOffre.getItems().size()==0
                  )
        {
             nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
           return; 
        }else
       {
               
              Date dtDebut =new SimpleDateFormat("yyyy-MM-dd").parse(dateDebutPicker.getValue().toString());
               Date dtFin =new SimpleDateFormat("yyyy-MM-dd").parse(dateFinPicker.getValue().toString());
                     Depot depot=mapDepot.get(depotCombo.getSelectionModel().getSelectedItem());
               
           if (secteurRadio.isSelected()== true){
           
               for (int i = 0; i < listSecteur.size(); i++) {
                   
                   Secteur secteur = listSecteur.get(i);
                 
                          for (int j = 0; j < listConditionOffre.size(); j++) {
                       
                    ConditionOffre conditionOffre = listConditionOffre.get(j);
                    
                    ConditionOffre conditionOffreTMP = new ConditionOffre();
                    
                    conditionOffreTMP.setRefCondition(conditionOffre.getRefCondition());
                    conditionOffreTMP.setCreationOffre(conditionOffre.getCreationOffre());
                    conditionOffreTMP.setDateCreation(conditionOffre.getDateCreation());
                    conditionOffreTMP.setArticle(conditionOffre.getArticle());
                    conditionOffreTMP.setQuantite(conditionOffre.getQuantite());
                    conditionOffreTMP.setUtilisateurCreation(conditionOffre.getUtilisateurCreation());
                    conditionOffreTMP.setSecteur(secteur);
                    
                    listConditionOffreTMP.add(conditionOffreTMP);
                }
                   
                            for (int k = 0; k < listOffre.size(); k++) {
                       
                    Offre offre = listOffre.get(k);
                    
                      Offre offreTMP = new Offre();
                    
                    offreTMP.setRefOffre(offre.getRefOffre());
                    offreTMP.setCreationOffre(offre.getCreationOffre());
                    offreTMP.setDateCreation(offre.getDateCreation());
                    offreTMP.setArticle(offre.getArticle());
                    offreTMP.setQuantite(offre.getQuantite());
                    offreTMP.setUtilisateurCreation(offre.getUtilisateurCreation());
                    offreTMP.setSecteur(secteur);
                    
                    listOffreTMP.add(offreTMP);
                }
                          
               }
                   creationOffre.setCodeOffre(codeOffreField.getText());
                   creationOffre.setCreationOffre(listConditionOffreTMP);
                   creationOffre.setOffre(listOffreTMP);
                   creationOffre.setDateCreation(new Date());
                   creationOffre.setDateDebut(dtDebut);
                   creationOffre.setDateFin(dtFin);
                   creationOffre.setTypeOffre(typeOffreCombo.getSelectionModel().getSelectedItem());
                   
                   if (groupeRadio.isSelected()==true){
                   creationOffre.setTypeOu(Constantes.GROUPE);
                   }else{
                   creationOffre.setTypeOu(Constantes.INDIVIDUEL);
                   }
                   creationOffre.setQteOffre(new BigDecimal(qteOffreField.getText()));
                   
                   creationOffreDAO.add(creationOffre);

                   creationOffre=new CreationOffre();

                Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.OFFRE);
           sequenceur.setValeur(sequenceur.getValeur()+1);
           sequenceurDAO.edit(sequenceur);
             
           if (groupeRadio.isSelected()==true){
               
           Sequenceur sequenceurRef = sequenceurDAO.findByCode(Constantes.ARTICLE_OFFRE);
           sequenceurRef.setValeur(sequenceurRef.getValeur()+1);
           sequenceurDAO.edit(sequenceurRef);
           
           
           }
           
           
           Incrementation ();
           
            nav.showAlert(Alert.AlertType.INFORMATION, "Succée" , null, Constantes.MESSAGE_ALERT_AJOUT);
            
             listSecteur.clear();
            listConditionOffre.clear();
            listConditionOffreTMP.clear();
            listOffre.clear();
            listOffreTMP.clear();
            
            regionRadio.setSelected(true);
            VisibleFalse();
            
            rafraichirOnAction(event);
               
            groupeRadio.setSelected(true);
               IncrementationArtOff();
              refOffreField.setText(refCondField.getText());
               
           }else{
           
           List<Secteur> listSecteurParRegion = secteurDAO.findSecteurByDepot(depot.getId());
           
           
            for (int i = 0; i < listSecteurParRegion.size(); i++) {
                   
                   Secteur secteur = listSecteurParRegion.get(i);
           
                   for (int j = 0; j < listConditionOffre.size(); j++) {
                       
                    ConditionOffre conditionOffre = listConditionOffre.get(j);
                    
                    ConditionOffre conditionOffreTMP = new ConditionOffre();
                    
                    conditionOffreTMP.setRefCondition(conditionOffre.getRefCondition());
                    conditionOffreTMP.setCreationOffre(conditionOffre.getCreationOffre());
                    conditionOffreTMP.setDateCreation(conditionOffre.getDateCreation());
                    conditionOffreTMP.setArticle(conditionOffre.getArticle());
                    conditionOffreTMP.setQuantite(conditionOffre.getQuantite());
                    conditionOffreTMP.setUtilisateurCreation(conditionOffre.getUtilisateurCreation());
                    conditionOffreTMP.setSecteur(secteur);
                    
                    listConditionOffreTMP.add(conditionOffreTMP);
                }
                   
                      for (int k = 0; k < listOffre.size(); k++) {
                       
                    Offre offre = listOffre.get(k);
                    
                      Offre offreTMP = new Offre();
                    
                    offreTMP.setRefOffre(offre.getRefOffre());
                    offreTMP.setCreationOffre(offre.getCreationOffre());
                    offreTMP.setDateCreation(offre.getDateCreation());
                    offreTMP.setArticle(offre.getArticle());
                    offreTMP.setQuantite(offre.getQuantite());
                    offreTMP.setUtilisateurCreation(offre.getUtilisateurCreation());
                    offreTMP.setSecteur(secteur);
                    
                    listOffreTMP.add(offreTMP);
                }
            }
                   creationOffre.setCodeOffre(codeOffreField.getText());
                   creationOffre.setCreationOffre(listConditionOffreTMP);
                   creationOffre.setOffre(listOffreTMP);
                   creationOffre.setDateCreation(new Date());
                   creationOffre.setDateDebut(dtDebut);
                   creationOffre.setDateFin(dtFin);
                   if (groupeRadio.isSelected()==true){
                   creationOffre.setTypeOu(Constantes.GROUPE);
                   }else{
                   creationOffre.setTypeOu(Constantes.INDIVIDUEL);
                   }
                   creationOffre.setTypeOffre(typeOffreCombo.getSelectionModel().getSelectedItem());
                   creationOffre.setQteOffre(new BigDecimal(qteOffreField.getText()));
                   
                   creationOffreDAO.add(creationOffre);
                   
                   creationOffre=new CreationOffre();
           
            
                  Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.OFFRE);
           sequenceur.setValeur(sequenceur.getValeur()+1);
           sequenceurDAO.edit(sequenceur);
             
           
             if (groupeRadio.isSelected()==true){
               
           Sequenceur sequenceurRef = sequenceurDAO.findByCode(Constantes.ARTICLE_OFFRE);
           sequenceurRef.setValeur(sequenceurRef.getValeur()+1);
           sequenceurDAO.edit(sequenceurRef);
           
           }
           
              Incrementation();
             
            nav.showAlert(Alert.AlertType.INFORMATION, "Succée" , null, Constantes.MESSAGE_ALERT_AJOUT);
            
            listSecteur.clear();
            listConditionOffre.clear();
            listConditionOffreTMP.clear();
            listOffre.clear();
            listOffreTMP.clear();
            
            
            regionRadio.setSelected(true);
            VisibleFalse();

               rafraichirOnAction(event);
            
               groupeRadio.setSelected(true);
               IncrementationArtOff();
               refOffreField.setText(refCondField.getText());
           } 
           
       }   
            
          
    }
    }   
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
        
            if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
                
                depotCombo.getSelectionModel().select(-1);
                dateDebutPicker.setValue(null);
                dateFinPicker.setValue(null);
                typeOffreCombo.getSelectionModel().select(-1);
                qteOffreField.clear();
               
           
              secteurCombo.getSelectionModel().select(-1);
              
              ArticleCondCombo.getSelectionModel().select(-1);
              codeArticleCondField.clear();
              codeFactureCondField.clear();
              quantiteCondField.clear();
           
              ArticleOffreCombo.getSelectionModel().select(-1);
              codeArticleOffreField.clear();
              codeFactureOffreField.clear();
              quantiteOffreField.clear();
           
 
  
            }
    }

    @FXML
    private void regionRadioOnAction(ActionEvent event) {
                   if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
 
                VisibleFalse();
            secteurCombo.getSelectionModel().select(-1);
            listSecteur.clear();
          }

    }

    @FXML
    private void secteurRadioOnAction(ActionEvent event) {
        
        
                        if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
 
                VisibleTrue ();
                secteurCombo.getSelectionModel().select(-1);
            listSecteur.clear();
          }
            
    }

    @FXML
    private void ajouterSec(MouseEvent event) {
        
                   if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
        if(secteurCombo.getSelectionModel().getSelectedIndex()==-1)
        {
             nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
           return; 
        }else
       {
            Secteur secteur=mapSecteur.get(secteurCombo.getSelectionModel().getSelectedItem());

           secteur.setCode(secteur.getCode());
           secteur.setLibelle(secteur.getLibelle());
           secteur.setVille(secteur.getVille());
           
           listSecteur.add(secteur);
           
           viderSec(event);
           tableSecteur.setItems(listSecteur);
           setColumnPropertiesSecteur();
    }
    }
    }

    @FXML
    private void SupprimerSec(MouseEvent event) {
        
    
         if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
         if(tableSecteur.getSelectionModel().getSelectedIndex()!=-1)
        {
            if(listSecteur.size()!=0)
            {
                
                listSecteur.remove(tableSecteur.getSelectionModel().getSelectedIndex());
                viderSec(event);

            }
        }
         }
    }

    @FXML
    private void viderSec(MouseEvent event) {
        
           if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
                    secteurCombo.getSelectionModel().select(-1);

    }
    }





    @FXML
    private void ajouterCond(MouseEvent event) {
        
         
                   if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
        if(ArticleCondCombo.getSelectionModel().getSelectedIndex()==-1 ||
                codeArticleCondField.equals("") ||
                codeFactureCondField.equals("") ||
                refCondField.equals("") ||
                quantiteCondField.getText().equals(""))
        {
             nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
           return; 
        }else
       {
              Article article=mapLibelleArtCond.get(ArticleCondCombo.getSelectionModel().getSelectedItem());

              ConditionOffre conditionOffre = new ConditionOffre();
              
              conditionOffre.setArticle(article);
              conditionOffre.setQuantite(new BigDecimal(quantiteCondField.getText()));
              conditionOffre.setCreationOffre(creationOffre);
              conditionOffre.setRefCondition(refCondField.getText());
              conditionOffre.setDateCreation(new Date());
              conditionOffre.setUtilisateurCreation(nav.getUtilisateur());
              
           listConditionOffre.add(conditionOffre);
           
                if (individuelRadio.isSelected()==true){
               
           Sequenceur sequenceurRef = sequenceurDAO.findByCode(Constantes.ARTICLE_OFFRE);
           sequenceurRef.setValeur(sequenceurRef.getValeur()+1);
           sequenceurDAO.edit(sequenceurRef);
           
           IncrementationArtOff();
           }
           
           viderCond(event);

           tableConditionOffre.setItems(listConditionOffre);
           setColumnPropertiesCond();
    }
    }
        
    }

    @FXML
    private void SupprimerCond(MouseEvent event) {
        
                 if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
         if(tableConditionOffre.getSelectionModel().getSelectedIndex()!=-1)
        {
            if(listConditionOffre.size()!=0)
            {
                
                listConditionOffre.remove(tableConditionOffre.getSelectionModel().getSelectedIndex());
                viderCond(event);

            }
        }
         }
        
    }

    @FXML
    private void viderCond(MouseEvent event) {
        
               if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
        ArticleCondCombo.getSelectionModel().select(-1);
              codeArticleCondField.clear();
              codeFactureCondField.clear();
           quantiteCondField.clear();
               }
    }


    @FXML
    private void ajouterOff(MouseEvent event) {
        
                    if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
        if(ArticleOffreCombo.getSelectionModel().getSelectedIndex()==-1 ||
                codeArticleOffreField.equals("") ||
                codeFactureOffreField.equals("") ||
                quantiteOffreField.getText().equals(""))
        {
             nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
           return; 
        }else
       {
           
              Article article=mapLibelleArtOffre.get(ArticleOffreCombo.getSelectionModel().getSelectedItem());

              Offre offre = new Offre();
              
              offre.setArticle(article);
              offre.setQuantite(new BigDecimal(quantiteOffreField.getText()));
              offre.setRefOffre(refOffreField.getText());
              offre.setDateCreation(new Date());
              offre.setUtilisateurCreation(nav.getUtilisateur());
              
           listOffre.add(offre);
           
           viderOff(event);
           
           
        tableOffre.setItems(listOffre);
        setColumnPropertiesOffre();
    }
    }
        
        
    }


    
    
    @FXML
    private void SupprimerOff(MouseEvent event) {
        
                      if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
         if(tableOffre.getSelectionModel().getSelectedIndex()!=-1)
        {
            if(listOffre.size()!=0)
            {

                listOffre.remove(tableOffre.getSelectionModel().getSelectedIndex());
                viderOff(event);


            }
        }
         }
        
        
    }

    @FXML
    private void viderOff(MouseEvent event) {
        
                       if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
              ArticleOffreCombo.getSelectionModel().select(-1);
              codeArticleOffreField.clear();
              codeFactureOffreField.clear();
           quantiteOffreField.clear();
           
           if (individuelRadio.isSelected()==true){
           refOffreField.clear();
           }
           
               }
    }


    @FXML
    private void depotComboOnAction(ActionEvent event) {
        
        
                                if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
                            
                                    
                                    secteurCombo.getItems().clear();
                                    
                              Depot depot = mapDepot.get(depotCombo.getSelectionModel().getSelectedItem());
                            
                              if (depot!= null){
                                  
                  List<Secteur> listSecteur =  secteurDAO.findSecteurByDepot(depot.getId());

            listSecteur.stream().map((secteur) -> {
                secteurCombo.getItems().addAll(secteur.getLibelle());
                return secteur;
            }).forEachOrdered((secteur) -> {
                mapSecteur.put(secteur.getLibelle(), secteur);
            });
                               
                              }
                                }
        
    }

    @FXML
    private void codeFactureCondKeyPressed(KeyEvent event) {
           if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
        
          if (event.getCode().equals(KeyCode.ENTER))
            {
                 try {
          Article article = mapcodeFactureCond.get(codeFactureCondField.getText());
           if(article!=null)

           {
               
              ArticleCondCombo.getSelectionModel().select(article.getLibelle());
              codeArticleCondField.setText(article.getCode());
              
           }

        } catch (Exception e) {
        }

            }
               
        
           }
    }

    @FXML
    private void codeArticleCondKeyPressed(KeyEvent event) {
        
           if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
               
                   if (event.getCode().equals(KeyCode.ENTER))
            {
         try {
          Article article = mapcodeArticleCond.get(codeArticleCondField.getText());
           if(article!=null)

           {
               
              ArticleCondCombo.getSelectionModel().select(article.getLibelle());
              codeFactureCondField.setText(article.getCodeFacture());
           }

        } catch (Exception e) {
        }

            }
               
           }
        
    }

    @FXML
    private void ArticleCondComboOnAction(ActionEvent event) {
        
           if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
               
                    try {
          if(ArticleCondCombo.getSelectionModel().getSelectedIndex()!=-1)
          {
                  Article article = mapLibelleArtCond.get(ArticleCondCombo.getSelectionModel().getSelectedItem());
           if(article!=null)

           {
               codeArticleCondField.setText(article.getCode());
               codeFactureCondField.setText(article.getCodeFacture());
           }

              
          }

        
        } catch (Exception e) {
        }

               
           }
    }

    @FXML
    private void codeFactureOffreKeyPressed(KeyEvent event) {
        
           if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
               
                 if (event.getCode().equals(KeyCode.ENTER))
            {
                 try {
          Article article = mapcodeFactureOffre.get(codeFactureOffreField.getText());
           if(article!=null)

           {
               
              ArticleOffreCombo.getSelectionModel().select(article.getLibelle());
              codeArticleOffreField.setText(article.getCode());
              
           }

        } catch (Exception e) {
        }

            }
               
           }
    }

    @FXML
    private void codeArticleOffreKeyPressed(KeyEvent event) {
        
           if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
               
                   if (event.getCode().equals(KeyCode.ENTER))
            {
         try {
          Article article = mapcodeArticleOffre.get(codeArticleOffreField.getText());
           if(article!=null)

           {
               
              ArticleOffreCombo.getSelectionModel().select(article.getLibelle());
              codeFactureOffreField.setText(article.getCodeFacture());
           }

        } catch (Exception e) {
        }

            }
               
               
           }
    }

    @FXML
    private void ArticleOffreComboOnAction(ActionEvent event) {
        
           if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
               
                   try {
          if(ArticleOffreCombo.getSelectionModel().getSelectedIndex()!=-1)
          {
                  Article article = mapLibelleArtOffre.get(ArticleOffreCombo.getSelectionModel().getSelectedItem());
           if(article!=null)

           {
               codeArticleOffreField.setText(article.getCode());
               codeFactureOffreField.setText(article.getCodeFacture());
           }

              
          }

        
        } catch (Exception e) {
        }
               
               
           }
    }

    @FXML
    private void groupeRadioOnAction(ActionEvent event) {
        
               if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
                   
                            refOffreField.clear();
                            refCondField.clear();
                            
                           IncrementationArtOff();
                           refOffreField.setText(refCondField.getText());
                   
               }
        
    }

    @FXML
    private void individuelRadioOnAction(ActionEvent event) {
        
               if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
                   
                    refCondField.clear();
                   
                    IncrementationArtOff();
                    refOffreField.clear();
                   
               }
        
        
    }

    @FXML
    private void affichageConditionOnMouse(MouseEvent event) {
        
               if (nav.getUtilisateur().getDepot().getLibelle1().equals("D_SIEGE")){
                   
                   
               if(tableConditionOffre.getSelectionModel().getSelectedIndex()!=-1)
           {   
        
    if (individuelRadio.isSelected()==true){
        
      ConditionOffre conditionOffre = tableConditionOffre.getSelectionModel().getSelectedItem();

    refOffreField.setText(conditionOffre.getRefCondition());

    }
              
          }
                   
               }
    }
    
  
    
}
