/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Vente;

import Utils.Constantes;
import dao.Entity.Article;
import dao.Entity.ClientPF;
import dao.Entity.ConditionOffre;
import dao.Entity.Depot;
import dao.Entity.DetailFacture;
import dao.Entity.DetailTournee;
import dao.Entity.Facture;
import dao.Entity.Magasin;
import dao.Entity.Offre;
import dao.Entity.PrixVenteDepot;
import dao.Entity.Sequenceur;
import dao.Entity.SequenceurFacture;
import dao.Entity.Tournee;
import dao.Entity.TypeVente;
import dao.Entity.Utilisateur;
import dao.Entity.Vendeur;
import dao.Manager.ArticleDAO;
import dao.Manager.ClientPFDAO;
import dao.Manager.ConditionOffreDAO;
import dao.Manager.DepotDAO;
import dao.Manager.DetailTourneeDAO;
import dao.Manager.FactureDAO;
import dao.Manager.OffreDAO;
import dao.Manager.PrixVenteDepotDAO;
import dao.Manager.SequenceurDAO;
import dao.Manager.SequenceurFactureDAO;
import dao.Manager.TourneeDAO;
import dao.Manager.TypeVenteDAO;
import dao.Manager.VendeurDAO;
import dao.ManagerImpl.ArticleDAOImpl;
import dao.ManagerImpl.ClientPFDAOImpl;
import dao.ManagerImpl.ConditionOffreDAOImpl;
import dao.ManagerImpl.DepotDAOImpl;
import dao.ManagerImpl.DetailTourneeDAOImpl;
import dao.ManagerImpl.FactureDAOImpl;
import dao.ManagerImpl.OffreDAOImpl;
import dao.ManagerImpl.PrixVenteDepotDAOImpl;
import dao.ManagerImpl.SequenceurDAOImpl;
import dao.ManagerImpl.SequenceurFactureDAOImpl;
import dao.ManagerImpl.TourneeDAOImpl;
import dao.ManagerImpl.TypeVenteDAOImpl;
import dao.ManagerImpl.VendeurDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
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
import javafx.util.Callback;


/**
 * FXML Controller class
 *
 * @author pc
 */
public class FacturationController implements Initializable {

    @FXML
    private TableView<DetailFacture> DetailFactureTable;
    @FXML
    private TableColumn<DetailFacture, String> codeArticleColumn;
    @FXML
    private TableColumn<DetailFacture, String> codeFactureColumn;
    @FXML
    private TableColumn<DetailFacture, String> libelleColumn;
    @FXML
    private TableColumn<DetailFacture, String> typeVenteColumn;
    @FXML
    private TableColumn<DetailFacture, String> categorieColumn;
    @FXML
    private TableColumn<DetailFacture, BigDecimal> prixUnitaireColumn;
    @FXML
    private TableColumn<DetailFacture, BigDecimal> quantiteColumn;
    @FXML
    private TableColumn<DetailFacture, BigDecimal> montantColumn;
    @FXML
    private TableColumn<DetailFacture, BigDecimal> remiseColumn;
    
    @FXML
    private TextField codeArticleField;
    @FXML
    private TextField quantiteField;
    @FXML
    private TextField prixUnitaireField;
    @FXML
    private TextField codeFactureField;
    @FXML
    private TextField MontantField;
    @FXML
    private TextField montantTotalField;
    @FXML
    private TextField sommeQuantiteField;
    @FXML
    private TextField numFactureField;
    @FXML
    private TextField remiseVarField;
    @FXML
    private TextField sommeRemiseField;
    @FXML
    private RadioButton categorieNormalRadio;
    @FXML
    private RadioButton categorieGratuiteRadio;

    @FXML
    private ImageView ajoutBtn;
    @FXML
    private ImageView supprimerBtn;
    @FXML
    private ImageView videBtn;

    @FXML
    private DatePicker dateFacture;

    @FXML
    private ComboBox<String> vendeurCombo;
    @FXML
    private ComboBox<String> clientCombo;
    @FXML
    private ComboBox<String> magasinCombo;
    @FXML
    private ComboBox<String> tourneeCombo;
    @FXML
    private ComboBox<String> ArticleCombo;
    @FXML
    private ComboBox<String> typeVenteCombo;
    @FXML
    private ComboBox<String> typeRemiseCombo;
    @FXML
    private ComboBox<BigDecimal> remiseConstCombo;
    
    @FXML
    private ToggleGroup categorie;
    @FXML
    private TextField numClientField;
    @FXML
    private Label remiseTexte;
    @FXML
    private RadioButton categorieOffreRadio;
    
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRafraichir;
    
    @FXML
    private RadioButton remiseSpecialeRadio;
    @FXML
    private ToggleGroup remise;
    @FXML
    private RadioButton remiseEspeceRadio;
    @FXML
    private CheckBox sansArtCheckBox;
    
    
    private final ObservableList<DetailFacture> listeDetailFacture = FXCollections.observableArrayList();
    ObservableList<String> TypeRemise =FXCollections.observableArrayList(Constantes.TYPE_REMISE_COMSTANT,Constantes.TYPE_REMISE_VARIABLE,Constantes.TYPE_REMISE_SANS);
    ObservableList<BigDecimal> RemiseConstant =FXCollections.observableArrayList(new BigDecimal(1),new BigDecimal(0.75));
    
    private Map<String, Vendeur> mapVendeur = new HashMap<>();
    private Map<String, ClientPF> mapNomClient = new HashMap<>();
    private Map<String, ClientPF> mapCodeClient = new HashMap<>();
    private Map<String, Article> mapLibelleArticle = new HashMap<>();
    private Map<String, Article> mapcodeArticle = new HashMap<>();
    private Map<String, Article> mapcodeFacture = new HashMap<>();
    
    VendeurDAO vendeurDAO = new VendeurDAOImpl();
    ClientPFDAO clientDAO = new ClientPFDAOImpl();
    
    
    ArticleDAO articleDAO = new ArticleDAOImpl();
    private Map<String, Depot> mapDepot = new HashMap<>();
    DepotDAO depotDAO = new DepotDAOImpl();
    private Map<String, Magasin> mapMagasin = new HashMap<>();
    navigation nav = new navigation();
    FactureDAO factureDAO = new FactureDAOImpl();
    private Map<String, TypeVente> mapTypeVente= new HashMap<>();
    TypeVenteDAO typeventeDAO=new TypeVenteDAOImpl();
    TourneeDAO tourneeDAO=new TourneeDAOImpl();
    private Map<String, Tournee> mapTournee= new HashMap<>();
    OffreDAO offreDAO = new OffreDAOImpl();
    ConditionOffreDAO conditionOffreDAO = new ConditionOffreDAOImpl(); 
     DetailTourneeDAO detailtourneeDAO=new DetailTourneeDAOImpl();
      SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();
     PrixVenteDepotDAO prixVenteDepotDAO = new PrixVenteDepotDAOImpl();
    SequenceurFactureDAO sequenceurFactureDAO = new SequenceurFactureDAOImpl();
     
    String  categorieValue;
    Facture facture=new Facture();
    Date datefacturation;
    Utilisateur utilisateur=nav.getUtilisateur();
    Tournee tournee= null;
   

//    BigDecimal valeur=BigDecimal.ZERO;
    @FXML
    private ImageView validerOffreBtn;

    
    
   
   

   

    void setColumnProperties() {

        codeArticleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailFacture, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailFacture, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getArticle().getCode());
            }
        });

            codeFactureColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailFacture, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailFacture, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getArticle().getCodeFacture());
            }
        });
        
        libelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailFacture, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailFacture, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getArticle().getLibelle());
            }
        });

        typeVenteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailFacture, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailFacture, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getTypeVente().getLibelle());
            }
        });

        categorieColumn.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        
        prixUnitaireColumn.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
        
        quantiteColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        
        montantColumn.setCellValueFactory(new PropertyValueFactory<>("montant"));
        
        remiseColumn.setCellValueFactory(new PropertyValueFactory<>("remise"));
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         sommeQuantiteField.setText(new BigDecimal(0).toString());
         montantTotalField.setText(new BigDecimal(0).toString());
         sommeRemiseField.setText(new BigDecimal(0).toString());
         
         
        categorieValue= Constantes.CATEGORIE_FACTURE_NORMAL;
//      Incrementation ();

            List<Magasin> listMagasin = nav.getUtilisateur().getDepot().getListMagasin();
            listMagasin.stream().map((magasin) -> {
                magasinCombo.getItems().addAll(magasin.getLibelle());
                return magasin;
            }).forEachOrdered((magasin) -> {
                mapMagasin.put(magasin.getLibelle(), magasin);
            });
             
            
            List<Vendeur> listVendeurTMP = new ArrayList<Vendeur>();
            List<Vendeur> listVendeur =  vendeurDAO.findByDepot(nav.getUtilisateur().getDepot().getId());
              
              boolean exist= false;
              
                for(int i=0;i<listVendeur. size();i++){
            exist= false;
                Vendeur vendeur = listVendeur.get(i);
                
                for(int j=0;j<listVendeurTMP.size();j++){
                 Vendeur vendeurTMP = listVendeur.get(j);
                 
                if (vendeur.getNom().equals(vendeurTMP.getNom()))
                {
               exist= true;
                }
            }
                if(exist==false){
                listVendeurTMP.add(vendeur);
                }
                }
                
            listVendeurTMP.stream().map((vendeur) -> {
                vendeurCombo.getItems().addAll(vendeur.getNom());
                return vendeur;
            }).forEachOrdered((vendeur) -> {
                mapVendeur.put(vendeur.getNom(), vendeur);
            });
        
       
   
         
       
        List<Article> listArticle = articleDAO.findAll();

        listArticle.stream().map((article) -> {
            ArticleCombo.getItems().addAll(article.getLibelle());
            return article;
        }).forEachOrdered((Article) -> {
            mapLibelleArticle.put(Article.getLibelle(), Article);
            mapcodeArticle.put(Article.getCode(), Article);
            mapcodeFacture.put(Article.getCodeFacture(), Article);
        });
        
      List<TypeVente> listTypeVente = typeventeDAO.findAll();

        listTypeVente.stream().map((typevente) -> {
            if(typevente.getLibelle()!=null && !typevente.getCode().equals(Constantes.ETAT_TYPE_VENTE_M))
            {
                typeVenteCombo.getItems().addAll(typevente.getLibelle());
            }
            
            return typevente;
        }).forEachOrdered((TypeVente) -> {
            mapTypeVente.put(TypeVente.getLibelle(), TypeVente);
           
        });
        
          typeRemiseCombo.setItems(TypeRemise);
          remiseConstCombo.setItems(RemiseConstant);
          
          remiseTexte.setVisible(Boolean.FALSE);
          remiseSpecialeRadio.setVisible(Boolean.FALSE);
          remiseEspeceRadio.setVisible(Boolean.FALSE);
    }
    




    @FXML
    private void codeArticleKeyPressed(KeyEvent event) {
        
         if (event.getCode().equals(KeyCode.ENTER))
            {
         try {
          Article article = mapcodeArticle.get(codeArticleField.getText());
           if(article!=null)

           {
               
              ArticleCombo.getSelectionModel().select(article.getLibelle());
              codeFactureField.setText(article.getCodeFacture());
           }

        } catch (Exception e) {
        }

            }
    }

    @FXML
    private void ArticleComboOnAction(ActionEvent event) {
             try {
          if(ArticleCombo.getSelectionModel().getSelectedIndex()!=-1)
          {
                  Article article = mapLibelleArticle.get(ArticleCombo.getSelectionModel().getSelectedItem());
           if(article!=null)

           {
               codeArticleField.setText(article.getCode());
               typeVenteCombo.getSelectionModel().select(-1);
               codeFactureField.setText(article.getCodeFacture());
           }

              
          }

        
        } catch (Exception e) {
        }

        
    }

    @FXML
    private void vendeurComboOnAction(ActionEvent event) {
        
           
        try {
            
             if(vendeurCombo.getSelectionModel().getSelectedIndex()!=-1)
        {
             if(magasinCombo.getSelectionModel().getSelectedIndex()==-1)
        {
              nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_SELECTIONNER_MAGASIN);
           return;
        }else{
            
                 
            tourneeCombo.getItems().clear();

            Vendeur vendeur = mapVendeur.get(vendeurCombo.getSelectionModel().getSelectedItem());
            
                 System.out.println("vendeur "+vendeur.getId());
            
           List<Tournee> listTournee = tourneeDAO.findByVendeurAndCodeVentAndEtat(vendeur.getId());
           
             System.out.println("listTournee "+listTournee.size());
           
           if(listTournee!=null)
           {
               listTournee.stream().map((tournee) -> {
                tourneeCombo.getItems().addAll(tournee.getCodeVent());
                return tournee;
            }).forEachOrdered((tournee) -> {
                mapTournee.put(tournee.getCodeVent(), tournee);
            });  
               
           }
           
        }
        
            
        }
            
        } catch (Exception e) {
        }
        

    }

    @FXML
    private void ajouter(MouseEvent event) throws ParseException {
        
  
           if (sansArtCheckBox.isSelected()== false){
               
        if(ArticleCombo.getSelectionModel().getSelectedIndex()==-1)
        {
             nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_SELECTIONNER_ARTICLE);
           return; 
        }else if(codeArticleField.getText().equals(""))
        {
              nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_ENTRER_CODE_ARTICLE);
           return; 
       }else if(codeFactureField.getText().equals(""))
        {
              nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_ENTRER_CODE_FACTURE);
           return; 
       }else if(quantiteField.getText().equals(""))
       {
           nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_ENTRER_QUANTITE);
           
           return; 
       }else if((new BigDecimal(quantiteField.getText())).compareTo(BigDecimal.ZERO)==0 || (new BigDecimal(quantiteField.getText())).compareTo(BigDecimal.ZERO)<0)
       {
            nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, "la quantité doit etre supérieur à 0 SVP");
           
           return; 
           
           
       }else if(typeVenteCombo.getSelectionModel().getSelectedIndex()==-1)
       {
             nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_SELECTIONNER_TYPE_VENTE);
         
           return;  
       }else if(typeRemiseCombo.getSelectionModel().getSelectedIndex()==-1)
       {
             nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_SELECTIONNER_TYPE_REMISE);
         
           return;  
       }else if(MontantField.getText().equals(""))
       {
            nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, "Erreur de prix");
          
           return; 
       }else
       {
        
           String typeRemise = typeRemiseCombo.getSelectionModel().getSelectedItem();
        
           BigDecimal remise = BigDecimal.ZERO;
           String remiseSpecial = "";
           String remiseEspece = "";
           
        if (typeRemise!=null){
            
        if(typeRemise.equals(Constantes.TYPE_REMISE_COMSTANT) && remiseSpecialeRadio.isSelected()== true){
            
           remise = remiseConstCombo.getSelectionModel().getSelectedItem();
           
           remiseSpecial = Constantes.REMISE_SP;

           remiseEspece = Constantes.SANS_REMISE_ES;
           
        }else if(typeRemise.equals(Constantes.TYPE_REMISE_COMSTANT) && remiseSpecialeRadio.isSelected()== false){
            
           remise = remiseConstCombo.getSelectionModel().getSelectedItem();
           
           remiseSpecial = Constantes.SANS_REMISE_SP;
           
           remiseEspece = Constantes.SANS_REMISE_ES;
           
        }else if(typeRemise.equals(Constantes.TYPE_REMISE_VARIABLE) && remiseSpecialeRadio.isSelected()== true){
        
           remise = new BigDecimal(remiseVarField.getText());
            
           remiseSpecial = Constantes.REMISE_SP;

           remiseEspece = Constantes.SANS_REMISE_ES;
           
        }else if(typeRemise.equals(Constantes.TYPE_REMISE_VARIABLE) && remiseSpecialeRadio.isSelected()== false){
        
           remise = new BigDecimal(remiseVarField.getText());
            
           remiseSpecial = Constantes.SANS_REMISE_SP;

           remiseEspece = Constantes.SANS_REMISE_ES;
           
        }else {
        
           remise = BigDecimal.ZERO;
           
           remiseSpecial = Constantes.SANS_REMISE_SP;
           
           remiseEspece = Constantes.SANS_REMISE_ES;
           
        }
        
        }
        
           Article article=mapLibelleArticle.get(ArticleCombo.getSelectionModel().getSelectedItem());
           TypeVente typevente=mapTypeVente.get(typeVenteCombo.getSelectionModel().getSelectedItem());
            
            
        BigDecimal montant = (new BigDecimal(quantiteField.getText()).multiply(new BigDecimal(prixUnitaireField.getText())));
            
               boolean trouve=false;
        
           for(int i=0;i<listeDetailFacture.size();i++)
           {
             if(listeDetailFacture.get(i).getArticle().equals(article) && listeDetailFacture.get(i).getRemise().compareTo(remise)==0)
             {
                 trouve=true;
             }
           }
           if(trouve==false)
           {
               
               
                DetailFacture detailFacture=new DetailFacture();
         
           detailFacture.setArticle(article);
           detailFacture.setQuantite(new BigDecimal(quantiteField.getText()));
           detailFacture.setPrixUnitaire(new BigDecimal(prixUnitaireField.getText()));
           detailFacture.setMontant(montant.setScale(2));
           detailFacture.setRemise(remise);
           detailFacture.setRemiseSpeciale(Constantes.SANS_REMISE_SP);
           detailFacture.setRemiseEspece(Constantes.SANS_REMISE_SP);
           detailFacture.setAction(false);
           detailFacture.setTypeVente(typevente);
           detailFacture.setCategorie(categorieValue);
           detailFacture.setFacture(facture);
           detailFacture.setDateCreation(new Date());
           
           detailFacture.setUtilisateurCreation(utilisateur);
           
           listeDetailFacture.add(detailFacture);
           
           
              BigDecimal valeur = BigDecimal.ZERO; 
                BigDecimal montantVente = BigDecimal.ZERO; 
                BigDecimal qte = BigDecimal.ZERO; 
      
                    
               if (remiseSpecial.equals(Constantes.REMISE_SP)){
           

                   valeur = new BigDecimal(montantTotalField.getText()).multiply(remise.divide(new BigDecimal(100),6,RoundingMode.CEILING));
   
                   montantVente = new BigDecimal(MontantField.getText()).subtract(valeur);
 
                   qte = montantVente.divide(new BigDecimal(prixUnitaireField.getText()),2,RoundingMode.FLOOR);
                    
                    DetailFacture detailFactureTMP=new DetailFacture();
         
           detailFactureTMP.setArticle(article);
           detailFactureTMP.setQuantite(qte);
           detailFactureTMP.setPrixUnitaire(new BigDecimal(prixUnitaireField.getText()));
           detailFactureTMP.setMontant(montantVente.setScale(2));
           detailFactureTMP.setRemise(BigDecimal.ZERO);
           detailFactureTMP.setRemiseSpeciale(remiseSpecial);
           detailFactureTMP.setRemiseEspece(remiseEspece);
           detailFactureTMP.setAction(false);
           detailFactureTMP.setTypeVente(typevente);
           detailFactureTMP.setCategorie(Constantes.CATEGORIE_FACTURE_NORMAL);
           detailFactureTMP.setFacture(facture);
           detailFactureTMP.setDateCreation(new Date());
           
           detailFactureTMP.setUtilisateurCreation(utilisateur);
           
           listeDetailFacture.add(detailFactureTMP);
                   
               }
               
                 
              viderDetailFacture();
           afficher_detailFacture();
           
           calculeQteAndMontantAndRemise();
               
           }else
           {
              nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, "Article deja existant");
           return;  
           }
           }
           }else if (sansArtCheckBox.isSelected()==true){
               
             if(typeRemiseCombo.getSelectionModel().getSelectedIndex()==-1)
       {
             nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_SELECTIONNER_TYPE_REMISE);
         
           return;  
       }else if(remiseEspeceRadio.isSelected()== false)
       {
            nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.REMPLIR_CHAMPS);
          
           return; 
       }else if(categorieGratuiteRadio.isSelected()== false)
       {
            nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.REMPLIR_CHAMPS);
          
           return; 
       }else
       { 
      
           String typeRemise = typeRemiseCombo.getSelectionModel().getSelectedItem();
        
           BigDecimal remise = BigDecimal.ZERO;
           String remiseSpecial = "";
           String remiseEspece = "";
           
        if (typeRemise!=null){
            
         if(typeRemise.equals(Constantes.TYPE_REMISE_COMSTANT) && remiseEspeceRadio.isSelected()== true){
            
           remise = remiseConstCombo.getSelectionModel().getSelectedItem();
           
           remiseEspece = Constantes.REMISE_ES;
           
           remiseSpecial = Constantes.SANS_REMISE_SP;
           
        }else if(typeRemise.equals(Constantes.TYPE_REMISE_VARIABLE) && remiseEspeceRadio.isSelected()== true){
        
           remise = new BigDecimal(remiseVarField.getText());
            
           remiseEspece = Constantes.REMISE_ES;
           
           remiseSpecial = Constantes.SANS_REMISE_SP;
           
        }
        
        }
           
               Article article = articleDAO.findBycodeArticle("XXX");
               TypeVente typeVente = typeventeDAO.findTypeVenteByCodeTypeVente("XXX");

                    DetailFacture detailFactureTMP=new DetailFacture();
         
           detailFactureTMP.setArticle(article);
           detailFactureTMP.setQuantite(BigDecimal.ZERO);
           detailFactureTMP.setPrixUnitaire(BigDecimal.ZERO);
           detailFactureTMP.setMontant(BigDecimal.ZERO);
           detailFactureTMP.setRemise(remise);
           detailFactureTMP.setRemiseSpeciale(remiseSpecial);
           detailFactureTMP.setRemiseEspece(remiseEspece);
           detailFactureTMP.setAction(false);
           detailFactureTMP.setTypeVente(typeVente);
           detailFactureTMP.setCategorie(categorieValue);
           detailFactureTMP.setFacture(facture);
           detailFactureTMP.setDateCreation(new Date());
           
           detailFactureTMP.setUtilisateurCreation(utilisateur);
           
           listeDetailFacture.add(detailFactureTMP);
                   
           
                  viderDetailFacture();
           afficher_detailFacture();
           
           calculeQteAndMontantAndRemise();
           
       }
           }
        
    }

    @FXML
    private void Supprimer(MouseEvent event) {
        
        
         if(DetailFactureTable.getSelectionModel().getSelectedIndex()!=-1)
        {
            if(listeDetailFacture.size()!=0)
            {
                
                listeDetailFacture.remove(DetailFactureTable.getSelectionModel().getSelectedIndex());
                viderDetailFacture();
                afficher_detailFacture();
                
            }
            
          calculeQteAndMontantAndRemise ();
           
        
        }
        
        
    }

    @FXML
    private void vider(MouseEvent event) {
        viderDetailFacture();
    }

    
    
    @FXML
    private void codeEntreeTableKeyPressed(KeyEvent event) {

               if (event.getCode().equals(KeyCode.ENTER))
            {
                
               try {
                   
             if(!numClientField.getText().equals(""))
        {

             if(magasinCombo.getSelectionModel().getSelectedIndex()==-1)
        {
           
           nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_SELECTIONNER_MAGASIN);
           return;
           
        }else if(vendeurCombo.getSelectionModel().getSelectedIndex()==-1)
        {
           nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_SELECTIONNER_VENDEUR);
           return;
        }else if(tourneeCombo.getSelectionModel().getSelectedIndex()==-1)
        {
            
           nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_SELECTIONNER_TOURNEE);
           return;
        }else{
       
            
           ClientPF clientPF = mapCodeClient.get(numClientField.getText().toUpperCase());
           
           if (clientPF!= null){
           
           SequenceurFacture sequenceurFacture = sequenceurFactureDAO.findByCodeClient(clientPF.getCode());   
               
           clientCombo.getSelectionModel().select(clientPF.getNom());
           numFactureField.setText(clientPF.getCode()+"-"+sequenceurFacture.getSeq());
           
           
           }else{
           
              numClientField.clear();
              numFactureField.clear();
              clientCombo.getSelectionModel().select(-1);

              nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_VERIFICATION_CLIENT);
           }
        }  
        }
            
        } catch (Exception e) {
            
        }
               
            }
    }


    @FXML
    private void TypeVenteComboOnAction(ActionEvent event) {
        
        BigDecimal prixunitaire=BigDecimal.ZERO;
        
        if(typeVenteCombo.getSelectionModel().getSelectedIndex()!=-1)
        {

              try {
            
                   if(tourneeCombo.getSelectionModel().getSelectedIndex()==-1) 
       {
           typeVenteCombo.getSelectionModel().select(-1);
          nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_SELECTIONNER_TOURNEE);
           return;
           
           
       }else if(ArticleCombo.getSelectionModel().getSelectedIndex()==-1) 
       {
           typeVenteCombo.getSelectionModel().select(-1);
          nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_SELECTIONNER_ARTICLE);
           return;
           
           
       }else if(quantiteField.getText().equals(""))
       {
           typeVenteCombo.getSelectionModel().select(-1);
           nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_ENTRER_QUANTITE);
           return; 
       }else if((new BigDecimal(quantiteField.getText())).compareTo(BigDecimal.ZERO)==0 || (new BigDecimal(quantiteField.getText())).compareTo(BigDecimal.ZERO)<0)
       {
           typeVenteCombo.getSelectionModel().select(-1);
            nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, "la quantité doit etre supérieur à 0 SVP");
           
           return; 
           
           
       }else
       {
         Article article=mapLibelleArticle.get(ArticleCombo.getSelectionModel().getSelectedItem());
         TypeVente typevente=mapTypeVente.get(typeVenteCombo.getSelectionModel().getSelectedItem());
         Tournee tournee = mapTournee.get(tourneeCombo.getSelectionModel().getSelectedItem());
         

          
               
                if(typevente.getCode().equals(Constantes.ETAT_TYPE_VENTE_D)|| typevente.getCode().equals(Constantes.ETAT_TYPE_VENTE_G)){
                    
                PrixVenteDepot prixVenteDepot = prixVenteDepotDAO.findPrixBySecteurAndTypeVente(article.getId(),typevente.getId(),tournee.getSecteur().getId());
      
         if(prixVenteDepot!=null && prixVenteDepot.getPrix().compareTo(BigDecimal.ZERO)>0){  
               
                    prixunitaire=prixVenteDepot.getPrix();
               prixUnitaireField.setText(prixunitaire+"");
            }else{
               
                  nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.MESSAGE_ALERT_CREER_PRIX);  
                return;
               
               }
           
                }
          MontantField.setText((new BigDecimal(quantiteField.getText())).multiply(prixunitaire)+"");
           
       }
        } catch (NumberFormatException e) { 
            
            nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, "la quantité doit etre en chiffre SVP");
        
        }
            
        }
        
    }

    @FXML
    private void categorieNormale(ActionEvent event) {
        categorieValue=Constantes.CATEGORIE_FACTURE_NORMAL;
        
    }

    @FXML
    private void categorieOffre(ActionEvent event) {
          categorieValue=Constantes.CATEGORIE_FACTURE_OFFRE;
    }

    @FXML
    private void categorieGratuite(ActionEvent event) {
        categorieValue=Constantes.CATEGORIE_FACTURE_GRATUITE;
     
        
    }

   private void afficher_detailFacture() 
   {
      
    DetailFactureTable.setItems(listeDetailFacture);
    setColumnProperties();
       
   } 
    
    private void viderDetailFacture()
    {
        codeArticleField.setText("");
        codeFactureField.setText("");
        ArticleCombo.getSelectionModel().select(-1);
        quantiteField.setText("");
        prixUnitaireField.setText("");
        MontantField.setText("");
        typeRemiseCombo.getSelectionModel().select(-1);
        remiseConstCombo.getSelectionModel().select(-1);
        remiseVarField.setText("");
        typeVenteCombo.getSelectionModel().select(-1);
        
            codeArticleField.setDisable(false);
            codeFactureField.setDisable(false);
            ArticleCombo.setDisable(false);
            quantiteField.setDisable(false);
            typeVenteCombo.setDisable(false);
        
        
        sansArtCheckBox.setSelected(false);
        
        categorieNormalRadio.setSelected(true);
         categorieValue= Constantes.CATEGORIE_FACTURE_NORMAL;
         
          remiseTexte.setVisible(Boolean.FALSE);
          remiseSpecialeRadio.setVisible(Boolean.FALSE);
          remiseSpecialeRadio.setSelected(Boolean.FALSE);
          remiseEspeceRadio.setVisible(Boolean.FALSE);
          remiseEspeceRadio.setSelected(Boolean.FALSE);
    }
    private void viderFacture()
    {
        dateFacture.setValue(null);
        montantTotalField.setText("");
        numClientField.setText("");
        numFactureField.setText("");
        sommeQuantiteField.setText("");
        sommeRemiseField.setText("");
        vendeurCombo.getSelectionModel().select(-1);
        clientCombo.getSelectionModel().select(-1);
        magasinCombo.getSelectionModel().select(-1);
        tourneeCombo.getSelectionModel().select(-1);
    }

    @FXML
    private void magasinComboOnAction(ActionEvent event) {
        
     
       
     
    }

    @FXML
    private void clientComboOnAction(ActionEvent event) {
       
           try {
            
             if(clientCombo.getSelectionModel().getSelectedIndex()!=-1)
        {
             if(magasinCombo.getSelectionModel().getSelectedIndex()==-1)
        {
              nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_SELECTIONNER_MAGASIN);
           return;
        }else if(vendeurCombo.getSelectionModel().getSelectedIndex()==-1)
        {
              nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_SELECTIONNER_VENDEUR);
           return;
        }else if(tourneeCombo.getSelectionModel().getSelectedIndex()==-1)
        {
              nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_SELECTIONNER_TOURNEE);
           return;
        }else{
            
                 
            numClientField.clear();
            numFactureField.clear();
            
            ClientPF clientPF = mapNomClient.get(clientCombo.getSelectionModel().getSelectedItem());
            SequenceurFacture sequenceurFacture = sequenceurFactureDAO.findByCodeClient(clientPF.getCode());

                numClientField.setText(clientPF.getCode());
                numFactureField.setText(clientPF.getCode()+"-"+sequenceurFacture.getSeq());

           
        }
        
            
        }
            
        } catch (Exception e) {
        }
        
        
        
        
        
    }

    @FXML
    private void ajouterOnAction(ActionEvent event) throws ParseException {
        

        Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Message de Confirmation");
alert.setHeaderText("Confirmation");
alert.setContentText("Voulez-vous vraiment arrêter la tournée directement après la validation de cette facture ?");

ButtonType buttonOui = new ButtonType("Oui");
ButtonType buttonNon = new ButtonType("Non");
ButtonType buttonAnnuler = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);

alert.getButtonTypes().setAll(buttonOui, buttonNon, buttonAnnuler);

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == buttonOui){
    
          if(numFactureField.getText().equals(""))
        {
             nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, "Erreur Num Facture !!");
          
           return;   
        }else if(dateFacture.getValue().equals(null))
        {
             nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_ENTRER_DATE);
          
           return;   
        }else if(vendeurCombo.getSelectionModel().getSelectedIndex()==-1)
        {
             nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_SELECTIONNER_VENDEUR);
          
           return;
        }else if(clientCombo.getSelectionModel().getSelectedIndex()==-1)
        {
              nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_SELECTIONNER_CLIENT);
           
           return;
        }else if(magasinCombo.getSelectionModel().getSelectedIndex()==-1)
        {
             nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_SELECTIONNER_MAGASIN);
          
           return;
        }else if(tourneeCombo.getSelectionModel().getSelectedIndex()==-1)
        {
           nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_SELECTIONNER_TOURNEE);
          
           return;   
        }else if(listeDetailFacture.size()==0)
        {
           nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_SELECTIONNER_TABLE);
          
           return;   
        }
        else
        {
            
             LocalDate localDate=dateFacture.getValue();
            
         datefacturation=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
            
         
         BigDecimal remiseTotal = new BigDecimal(montantTotalField.getText()).multiply(new BigDecimal(sommeRemiseField.getText())).divide(new BigDecimal(100), 2, RoundingMode.FLOOR);


         Vendeur vendeur=mapVendeur.get(vendeurCombo.getSelectionModel().getSelectedItem());
         ClientPF client=mapNomClient.get(clientCombo.getSelectionModel().getSelectedItem());
         Depot depot=nav.getUtilisateur().getDepot();
         Magasin magasin=mapMagasin.get(magasinCombo.getSelectionModel().getSelectedItem());
         tournee=mapTournee.get(tourneeCombo.getSelectionModel().getSelectedItem());
         
            facture.setNumFacture(numFactureField.getText());
            facture.setDateFacture(datefacturation);
            facture.setMontantTotal(new BigDecimal(montantTotalField.getText()));
            facture.setVendeur(vendeur);
            facture.setDepot(depot);
            facture.setMagasin(magasin);
            facture.setTournee(tournee);
            facture.setClient(client); 
            facture.setRemiseTotal(remiseTotal);
            facture.setDateCreation(new Date());
            facture.setDetailFacture(listeDetailFacture);
            facture.setUtilisateurCreation(utilisateur);
            factureDAO.add(facture);
            

           SequenceurFacture sequenceurFacture = sequenceurFactureDAO.findByCodeClient(client.getCode());
           sequenceurFacture.setSeq(sequenceurFacture.getSeq()+1);
           sequenceurFactureDAO.edit(sequenceurFacture);
             
            facture=new Facture();
            

        }
 //######################################################################################################################################################################################################################################################################################################################################################
 
            tournee.setEtat(Constantes.ETAT_FACTURER);
            tourneeDAO.edit(tournee);
            
            nav.showAlert(Alert.AlertType.INFORMATION, "Succée" , null, Constantes.MESSAGE_ALERT_AJOUT);
            
            viderFacture();
            viderDetailFacture();
            listeDetailFacture.clear();
            DetailFactureTable.getItems().clear();    
             
    
    
} else if (result.get() == buttonNon) {
    
        if(numFactureField.getText().equals(""))
        {
             nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, "Erreur Num Facture !!");
          
           return;   
        }else if(dateFacture.getValue().equals(null))
        {
             nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_ENTRER_DATE);
          
           return;   
        }else if(vendeurCombo.getSelectionModel().getSelectedIndex()==-1)
        {
             nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_SELECTIONNER_VENDEUR);
          
           return;
        }else if(clientCombo.getSelectionModel().getSelectedIndex()==-1)
        {
              nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_SELECTIONNER_CLIENT);
           
           return;
        }else if(magasinCombo.getSelectionModel().getSelectedIndex()==-1)
        {
             nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_SELECTIONNER_MAGASIN);
          
           return;
        }else if(tourneeCombo.getSelectionModel().getSelectedIndex()==-1)
        {
           nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_SELECTIONNER_TOURNEE);
          
           return;   
        }else if(listeDetailFacture.size()==0)
        {
           nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_SELECTIONNER_TABLE);
          
           return;   
        }
        else
        {
            
             LocalDate localDate=dateFacture.getValue();
            
         datefacturation=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
            
            BigDecimal remiseTotal = new BigDecimal(montantTotalField.getText()).multiply(new BigDecimal(sommeRemiseField.getText())).divide(new BigDecimal(100), 2, RoundingMode.FLOOR);
         
         Vendeur vendeur=mapVendeur.get(vendeurCombo.getSelectionModel().getSelectedItem());
         ClientPF client=mapNomClient.get(clientCombo.getSelectionModel().getSelectedItem());
         Depot depot=nav.getUtilisateur().getDepot();
         Magasin magasin=mapMagasin.get(magasinCombo.getSelectionModel().getSelectedItem());
         tournee=mapTournee.get(tourneeCombo.getSelectionModel().getSelectedItem());
         
            facture.setNumFacture(numFactureField.getText());
            facture.setDateFacture(datefacturation);
            facture.setMontantTotal(new BigDecimal(montantTotalField.getText()));
            facture.setVendeur(vendeur);
            facture.setDepot(depot);
            facture.setMagasin(magasin);
            facture.setTournee(tournee);
            facture.setClient(client);    
            facture.setRemiseTotal(remiseTotal);
            facture.setDateCreation(new Date());
            facture.setDetailFacture(listeDetailFacture);
            facture.setUtilisateurCreation(utilisateur);
            factureDAO.add(facture);
            

           SequenceurFacture sequenceurFacture = sequenceurFactureDAO.findByCodeClient(client.getCode());
           sequenceurFacture.setSeq(sequenceurFacture.getSeq()+1);
           sequenceurFactureDAO.edit(sequenceurFacture);
             
            facture=new Facture();
            

        }

                nav.showAlert(Alert.AlertType.INFORMATION, "Succée" , null, Constantes.MESSAGE_ALERT_AJOUT);
                
                 viderFacture();
            viderDetailFacture();
            listeDetailFacture.clear();
            DetailFactureTable.getItems().clear(); 
    
} else {
    
}
        
        
    }

    
    
    void calculeQteAndMontantAndRemise (){
    
            BigDecimal montantTotal=BigDecimal.ZERO;
            BigDecimal sommequantite=BigDecimal.ZERO;
            BigDecimal sommeRemise=BigDecimal.ZERO;
        
     int i=0;
   
        while(i<listeDetailFacture.size())
        {
            DetailFacture detailFacturetmp=listeDetailFacture.get(i);
            
            if (detailFacturetmp.getCategorie().equals(Constantes.CATEGORIE_FACTURE_NORMAL)){
            
              montantTotal=  montantTotal.add(detailFacturetmp.getMontant());
                
            }else if(detailFacturetmp.getCategorie().equals(Constantes.CATEGORIE_FACTURE_GRATUITE)){
            
            sommeRemise = sommeRemise.add(detailFacturetmp.getRemise());
            
            }
            
            if (detailFacturetmp.getCategorie().equals(Constantes.CATEGORIE_FACTURE_NORMAL) || detailFacturetmp.getCategorie().equals(Constantes.CATEGORIE_FACTURE_GRATUITE)){
            
                if (detailFacturetmp.getRemiseSpeciale().equals(Constantes.SANS_REMISE_SP)){
                 
                sommequantite= sommequantite.add(detailFacturetmp.getQuantite());
                   
                 }
            }

            i++;
        }
        montantTotalField.setText(montantTotal+"");
        sommeQuantiteField.setText(sommequantite+"");
        sommeRemiseField.setText(sommeRemise+"");
    
    }
    
    @FXML
    private void rafraichirOnAction(ActionEvent event) {
        
           viderDetailFacture();
           afficher_detailFacture();
           calculeQteAndMontantAndRemise();

    }

    @FXML
    private void TypeRemiseComboOnAction(ActionEvent event) {
        
        String typeRemise = typeRemiseCombo.getSelectionModel().getSelectedItem();
        
        if (typeRemise!=null){
            
        if(typeRemise.equals(Constantes.TYPE_REMISE_COMSTANT)){
            
            remiseConstCombo.setDisable(false);
            remiseVarField.setDisable(true);
            remiseVarField.clear();
            
          remiseTexte.setVisible(Boolean.TRUE);
          remiseSpecialeRadio.setVisible(Boolean.TRUE);
          remiseSpecialeRadio.setSelected(Boolean.FALSE);
          remiseEspeceRadio.setVisible(Boolean.TRUE);
          remiseEspeceRadio.setSelected(Boolean.FALSE);
          
          
        }else if(typeRemise.equals(Constantes.TYPE_REMISE_VARIABLE)) {
        
        
            remiseConstCombo.setDisable(true);
            remiseVarField.setDisable(false);
            remiseConstCombo.getSelectionModel().select(-1);
            
             remiseTexte.setVisible(Boolean.TRUE);
          remiseSpecialeRadio.setVisible(Boolean.TRUE);
          remiseSpecialeRadio.setSelected(Boolean.FALSE);
          remiseEspeceRadio.setVisible(Boolean.TRUE);
          remiseEspeceRadio.setSelected(Boolean.FALSE);
            
        }else {
        
            remiseConstCombo.setDisable(true);
            remiseVarField.setDisable(true);
            remiseConstCombo.getSelectionModel().select(-1);
            remiseVarField.clear();
            
             remiseTexte.setVisible(Boolean.FALSE);
          remiseSpecialeRadio.setVisible(Boolean.FALSE);
          remiseSpecialeRadio.setSelected(Boolean.FALSE);
          remiseEspeceRadio.setVisible(Boolean.FALSE);
          remiseEspeceRadio.setSelected(Boolean.FALSE);
        }
        
        }  
        
    }

    @FXML
    private void remiseConstComboOnAction(ActionEvent event) {
    }

    @FXML
    private void codeFactureKeyPressed(KeyEvent event) {
        
         if (event.getCode().equals(KeyCode.ENTER))
            {
                 try {
          Article article = mapcodeFacture.get(codeFactureField.getText());
           if(article!=null)

           {
               
              ArticleCombo.getSelectionModel().select(article.getLibelle());
              codeArticleField.setText(article.getCode());
              
           }

        } catch (Exception e) {
        }

            }
    }

    @FXML
    private void tourneeComboOnAction(ActionEvent event) {
        
              
        try {
            
             if(tourneeCombo.getSelectionModel().getSelectedIndex()!=-1)
        {
             if(magasinCombo.getSelectionModel().getSelectedIndex()==-1)
        {
              nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_SELECTIONNER_MAGASIN);
           return;
        }else if (vendeurCombo.getSelectionModel().getSelectedIndex()==-1){
            
             nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_SELECTIONNER_VENDEUR);
           return;
            
        }else{
            clientCombo.getItems().clear();

            Tournee tournee = mapTournee.get(tourneeCombo.getSelectionModel().getSelectedItem());
           List<ClientPF> listClient = clientDAO.findClientPFBySecteur(tournee.getSecteur().getId());
           
           
           if(listClient!=null)
           {
               listClient.stream().map((client) -> {
                clientCombo.getItems().addAll(client.getNom());
                return client;
            }).forEachOrdered((client) -> {
                mapNomClient.put(client.getNom(), client);
                mapCodeClient.put(client.getCode(), client);
            });  
               
           }
           
        }
        
            
        }
            
        } catch (Exception e) {
        }
        
        
    }

    @FXML
    private void sansArtCheckBoxOnAction(ActionEvent event) {
        
       if(sansArtCheckBox.isSelected()== true)
        {

            codeArticleField.setDisable(true);
            codeFactureField.setDisable(true);
            ArticleCombo.setDisable(true);
            quantiteField.setDisable(true);
            typeVenteCombo.setDisable(true);
            
             codeArticleField.setText("");
        codeFactureField.setText("");
        ArticleCombo.getSelectionModel().select(-1);
        quantiteField.setText("");
        prixUnitaireField.setText("");
        MontantField.setText("");
        typeVenteCombo.getSelectionModel().select(-1);
            
            
        }else if(sansArtCheckBox.isSelected()== false)
        {
                      codeArticleField.setDisable(false);
            codeFactureField.setDisable(false);
            ArticleCombo.setDisable(false);
            quantiteField.setDisable(false);
            typeVenteCombo.setDisable(false);
            
             codeArticleField.setText("");
        codeFactureField.setText("");
        ArticleCombo.getSelectionModel().select(-1);
        quantiteField.setText("");
        prixUnitaireField.setText("");
        MontantField.setText("");
        typeVenteCombo.getSelectionModel().select(-1);
        }
        
        
    }

    @FXML
    private void validerOffre(MouseEvent event) {
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                
                Boolean offre = false;
                
                for (int i = 0; i < listeDetailFacture.size(); i++) {
                    
                    DetailFacture detailFacture = listeDetailFacture.get(i);
                    
                    if(detailFacture.getCategorie().equals(Constantes.CATEGORIE_FACTURE_OFFRE) && detailFacture.getCategorie().equals(Constantes.CATEGORIE_FACTURE_NORMAL) ){
           offre = true;    
     }
                }
              if (offre == false){
              
              nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_VERIFICATION_OFFRE);
              return;
              
              }else{
             
                  
                  BigDecimal tableOffre = BigDecimal.ZERO;
                  BigDecimal offreCondition = BigDecimal.ZERO;
                  BigDecimal calculeOffreCondition = BigDecimal.ZERO;
                  BigDecimal valeurOffre = BigDecimal.ZERO;
                  BigDecimal resultaOffre = BigDecimal.ZERO;
                  
                  
              for (int i = 0; i < listeDetailFacture.size(); i++) {
                    
                    DetailFacture detailFactureOffre = listeDetailFacture.get(i);
                    
                    if(detailFactureOffre.getCategorie().equals(Constantes.CATEGORIE_FACTURE_OFFRE)){
  
               List<Offre> listOffre = offreDAO.findByDateAndArticleAndSecteur(datefacturation, detailFactureOffre.getArticle().getId(), detailFactureOffre.getFacture().getTournee().getSecteur().getId());
               
               if (listOffre!=null){
               
                   for (int j = 0; j < listOffre.size(); j++) {
                       
                       Offre offre1 = listOffre.get(j);
                       
                       for (int k = 0; k < listeDetailFacture.size(); k++) {
                       
                       DetailFacture detailFactureVente = listeDetailFacture.get(k);
                       
                        if(detailFactureVente.getCategorie().equals(Constantes.CATEGORIE_FACTURE_NORMAL)){
                       
                       ConditionOffre conditionOffre = conditionOffreDAO.findByDateAndArticleAndSecteur(datefacturation, detailFactureVente.getArticle().getId(), detailFactureVente.getFacture().getTournee().getSecteur().getId()); 
                       
                          if (conditionOffre!=null){
                       
                       if (offre1.getRefOffre().equals(conditionOffre.getRefCondition())){
                           
                           if(offre1.getCreationOffre().getTypeOu().equals(Constantes.INDIVIDUEL) && conditionOffre.getCreationOffre().getTypeOu().equals(Constantes.INDIVIDUEL)){
                               
                              tableOffre = detailFactureOffre.getQuantite();
                              
                              offreCondition = conditionOffre.getQuantite();
                              
                              calculeOffreCondition =  tableOffre.divide(offreCondition,RoundingMode.DOWN);
                              
                              valeurOffre = offre1.getQuantite();
                              
                              resultaOffre = calculeOffreCondition.multiply(valeurOffre);
                              
                              
                           }else if(offre1.getCreationOffre().getTypeOu().equals(Constantes.GROUPE) && conditionOffre.getCreationOffre().getTypeOu().equals(Constantes.GROUPE)){
                           
                           // la partie leur ce que l'offre et partie du groupe des offre a propose dans ce cas la en doit calculer la somme des articles propose et l'offre sera parmi les articles qui a le miniment prix  
                               
                           }
                       }
                       
                   }
                       }  
                   }

                 
                
               }
                       
                       
                       
     }else {
               
               nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, Constantes.MESSAGE_ALERT_OFFRE_EXISTE_PAS);
              return;
                   
               }
                }
                  
                  
                  
                  
              }  
                
         }
        
            }}




    }
