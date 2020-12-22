/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.Vente.VersementController;
import Utils.Constantes;
import dao.Entity.Depot;
import dao.Entity.Habilitation;
import dao.Entity.Utilisateur;
import dao.Entity.Vendeur;
import dao.Manager.HabilitationDAO;
import dao.ManagerImpl.HabilitationDAOImpl;
import function.navigation;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class HomeController implements Initializable {

    @FXML
    private AnchorPane basePaneAP;
    @FXML
    private SplitPane SplitPane;
    @FXML
    private Accordion menuAccordion;
    @FXML
    private ListView<String> listeView1;
    @FXML
    public TabPane tbpane;
    @FXML
    private Tab hometab;
    @FXML
    private ImageView fermerBtn;
    @FXML
    private ImageView deconnecterBtn;
    @FXML
    private ImageView homeBtnClicked;
    @FXML
    private ListView<String> listeView2;
    @FXML
    private Label utilisateurConnecte;
    @FXML
    private ListView<String> listeView3;
    @FXML
    private ListView<String> listeView4;
    @FXML
    private ListView<String> listeView5;
    @FXML
    private ListView<String> listeView6;
    
    
    /**
     * Initializes the controller class.
     */
  
    @FXML
    private TitledPane articlePane;
    @FXML
    private TitledPane stockPane;
    @FXML
    private TitledPane facturePane;
    @FXML
    private TitledPane referentielPane;
    @FXML
    private TitledPane parametrePane;
   
    public static HomeController homeControllerRef;
      Utilisateur utilisateur = new Utilisateur();
    navigation nav = new navigation();
    HabilitationDAO habilitationDAO = new HabilitationDAOImpl();

     public Node node ;
     public Tab tab ;
     
    @FXML
    private AnchorPane test;
   

 
    void autoriseMenuUtilisateur(){
	 
	 List<Habilitation> listHabilitation=habilitationDAO.findHabilitationByUtilisateur(utilisateur.getId());
	 
	 for(int i=0;i<listHabilitation.size();i++){
		 Habilitation habilitation =listHabilitation.get(i);
		 
               
                 
		 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_ARTICLE)){
			 articlePane.setVisible(habilitation.isAutorise());
                       
		 }
		 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_STOCK)){
			 stockPane.setVisible(habilitation.isAutorise());
		 }
		 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_FACTURE)){
			 facturePane.setVisible(habilitation.isAutorise());
		 }
		 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_REFERENTIEL)){
			 referentielPane.setVisible(habilitation.isAutorise());
		 }
		 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_PARAMETRE)){
			 parametrePane.setVisible(habilitation.isAutorise());
		 }

	 }
	 
 }
    
    void loadlistview(){
    
        ObservableList<String> ols = FXCollections.observableArrayList();
        
        ols.add(Constantes.ARTICLE);
        ols.add(Constantes.FAMILLE);
        ols.add(Constantes.DETAIL_FAMILLE);
        ols.add(Constantes.SOUS_FAMILLE);
        ols.add(Constantes.DETAIL_SOUS_FAMILLE);


        listeView1.setItems(ols);
        
         ObservableList<String> ols1 = FXCollections.observableArrayList();
        
          ols1.add(Constantes.BANQUE);
          ols1.add(Constantes.CHEQUE);
          ols1.add(Constantes.CLIENT_PF);
          ols1.add(Constantes.DEPOT);
          ols1.add(Constantes.CLIENT);
          ols1.add(Constantes.VILLE);
          ols1.add(Constantes.MAGASIN);
          ols1.add(Constantes.SECTEUR);
          ols1.add(Constantes.VEHICULE);
          ols1.add(Constantes.VENDEUR);
          ols1.add(Constantes.CONSULTATION_VENDEUR);
          ols1.add(Constantes.CAISSIER);
          ols1.add(Constantes.CONSULTATION_CAISSIER);
          ols1.add(Constantes.CHAUFFEUR);
          ols1.add(Constantes.OFFREE);
          ols1.add(Constantes.TYPE_VENTE);
          ols1.add(Constantes.COMPTE_VERSEMENT);
          ols1.add(Constantes.PRIX_VENTE_DEPOT);

        listeView2.setItems(ols1);
        
         ObservableList<String> ols2 = FXCollections.observableArrayList();
        
        ols2.add(Constantes.UTILISATEUR);
        ols2.add(Constantes.AUTHORISATION);
        
        listeView3.setItems(ols2);
     
         ObservableList<String> ols3 = FXCollections.observableArrayList();
        
        ols3.add(Constantes.SITUATION);
        ols3.add(Constantes.SITUATION_SIEGE);
        ols3.add(Constantes.SITUATION_GLOBAL);
        ols3.add(Constantes.INITIAL);
        ols3.add(Constantes.RECEPTIONS);
        ols3.add(Constantes.DECLARATION_RECEPTIONS);
        ols3.add(Constantes.RECEPTIONS_ETRANGERE);
        ols3.add(Constantes.TRANSFERTS);
        ols3.add(Constantes.CORRECTION_TRANSFERTS_SORTIE);
        ols3.add(Constantes.SORTIE);
        ols3.add(Constantes.ETAT_SORTIE);
        ols3.add(Constantes.COMMANDE_CLIENT);
        ols3.add(Constantes.VERSEMENT_CLIENTS);
        ols3.add(Constantes.VERSEMENT_CHEQUE_CLIENTS);
        ols3.add(Constantes.VERSEMENT_BANCAIRE_CLIENTS);
        ols3.add(Constantes.CONSULT_COMPTE_CLIENT);
        ols3.add(Constantes.AVANCE_CHAUFFEUR);
        ols3.add(Constantes.AVANCE_MAGASINIER);
        ols3.add(Constantes.TRANSFERT_EN_ATTENTEE);
        ols3.add(Constantes.RECEPTION_EN_ATTENTEE);
        ols3.add(Constantes.RETOUR_DEFINITIF);
     
      
        listeView4.setItems(ols3);
        
         ObservableList<String> ols4 = FXCollections.observableArrayList();
        
        ols4.add(Constantes.TOURNEE);
        ols4.add(Constantes.SITIAT_INITIAL);
        ols4.add(Constantes.SITUAT_TOURNEE);
 
        listeView5.setItems(ols4);
        
         ObservableList<String> ols5 = FXCollections.observableArrayList();
        
        ols5.add(Constantes.CHARGEMEENT);
        ols5.add(Constantes.RETOUUR);
        ols5.add(Constantes.SUIVI_VENTEES);
        ols5.add(Constantes.VERSEMENT);
        ols5.add(Constantes.VERSEMENT_BANCAIIRE);
        ols5.add(Constantes.VERSEMENT_CHEQUEE);
        ols5.add(Constantes.LISTE_VERSEMENT_CHEQUEE);
        ols5.add(Constantes.LISTE_VERSEMENT_BANCAIIRE);
        ols5.add(Constantes.FACTURES);
        ols5.add(Constantes.SITUATION_VENTEFACTURE);
        ols5.add(Constantes.ETAT_VERSEMENT_CHEQUE_CAISSIER);
        ols5.add(Constantes.TRANS_CHEQUE);
        ols5.add(Constantes.CONSULT_COMPTE_VENTE);
        ols5.add(Constantes.REPO_DE_SOLDES);
        
        
        listeView6.setItems(ols5);
        
    }
    
    
    public void selectmenu(){
    listeView1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
        
        @Override
        public void changed(ObservableValue<? extends java.lang.String> observable, java.lang.String oldValue, java.lang.String newValue) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            int i= listeView1.getSelectionModel().getSelectedIndex();
            
            
            if(i==Constantes.LISTE_ARTICLE){
                try {
                      boolean found= openIfExist(tbpane,Constantes.ARTICLE);
                 if(found){
                      Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Article/ListeArticle.fxml"));
                     tab = new Tab(Constantes.ARTICLE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Article/ListeArticle.fxml"));
                     tab = new Tab(Constantes.ARTICLE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
            
            
            if(i==Constantes.FAMILLE_ARTICLE){
                try {
                      boolean found= openIfExist(tbpane,Constantes.FAMILLE);
                 if(found){
                      Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Article/FamilleArticle.fxml"));
                     tab = new Tab(Constantes.FAMILLE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Article/FamilleArticle.fxml"));
                     tab = new Tab(Constantes.FAMILLE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            
            
            if(i==Constantes.DETAIL_FAMILLE_ARTICLE){
                try {
                      boolean found= openIfExist(tbpane,Constantes.DETAIL_FAMILLE);
                 if(found){
                      Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Article/DetailFamilleArticle.fxml"));
                     tab = new Tab(Constantes.DETAIL_FAMILLE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Article/DetailFamilleArticle.fxml"));
                     tab = new Tab(Constantes.DETAIL_FAMILLE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
            if(i==Constantes.SOUS_FAMILLE_ARTICLE){
                try {
                      boolean found= openIfExist(tbpane,Constantes.SOUS_FAMILLE);
                 if(found){
                      Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Article/SousFamilleArticle.fxml"));
                    tab = new Tab(Constantes.SOUS_FAMILLE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Article/SousFamilleArticle.fxml"));
                     tab = new Tab(Constantes.SOUS_FAMILLE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            
            
            if(i==Constantes.DETAIL_SOUS_FAMILLE_ARTICLE){
                try {
                      boolean found= openIfExist(tbpane,Constantes.DETAIL_SOUS_FAMILLE);
                 if(found){
                       Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Article/DetailSousFamilleArticle.fxml"));
                     tab = new Tab(Constantes.DETAIL_SOUS_FAMILLE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Article/DetailSousFamilleArticle.fxml"));
                     tab = new Tab(Constantes.DETAIL_SOUS_FAMILLE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            

        }
    });
    
    
     listeView2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
        
        @Override
        public void changed(ObservableValue<? extends java.lang.String> observable, java.lang.String oldValue, java.lang.String newValue) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            int i= listeView2.getSelectionModel().getSelectedIndex();
            
            
            if(i==Constantes.LISTE_BANQUE){
                try {
                      boolean found= openIfExist(tbpane,Constantes.BANQUE);
                 if(found){
                       Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/Banque.fxml"));
                    Tab tab = new Tab(Constantes.BANQUE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                    
                    listeView2.getSelectionModel().clearSelection();
                            listeView2.getSelectionModel().select(-1);
                    return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/Banque.fxml"));
                    Tab tab = new Tab(Constantes.BANQUE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                    
                    
                                                 
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
           
                
            }else if(i==Constantes.LISTE_CHEQUE){
              try {
                    boolean found= openIfExist(tbpane,Constantes.CHEQUE);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/Cheque.fxml"));
                    Tab tab = new Tab(Constantes.CHEQUE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/Cheque.fxml"));
                    Tab tab = new Tab(Constantes.CHEQUE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
              
  
              
            }else if(i==Constantes.LISTE_CLIENT_PF){
              try {
                    boolean found= openIfExist(tbpane,Constantes.CLIENT_PF);
                 if(found){
                      Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/ClientPF.fxml"));
                    Tab tab = new Tab(Constantes.CLIENT_PF,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/ClientPF.fxml"));
                    Tab tab = new Tab(Constantes.CLIENT_PF,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
              
              
            }else if(i==Constantes.LISTE_DEPOT){
              try {
                    boolean found= openIfExist(tbpane,Constantes.DEPOT);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/Depot.fxml"));
                    Tab tab = new Tab(Constantes.DEPOT,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/Depot.fxml"));
                    Tab tab = new Tab(Constantes.DEPOT,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
              
              
            }else if(i==Constantes.LISTE_CLIENT){
              try {
                    boolean found= openIfExist(tbpane,Constantes.CLIENT);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/ListeClient.fxml"));
                    Tab tab = new Tab(Constantes.CLIENT,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/ListeClient.fxml"));
                    Tab tab = new Tab(Constantes.CLIENT,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
              
              
            }else if(i==Constantes.LISTE_VILLE){
              try {
                    boolean found= openIfExist(tbpane,Constantes.VILLE);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/Ville.fxml"));
                    Tab tab = new Tab(Constantes.VILLE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/Ville.fxml"));
                    Tab tab = new Tab(Constantes.VILLE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
              
              
            }else if(i==Constantes.LISTE_MAGASIN){
              try {
                    boolean found= openIfExist(tbpane,Constantes.MAGASIN);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/Magasin.fxml"));
                    Tab tab = new Tab(Constantes.MAGASIN,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/Magasin.fxml"));
                    Tab tab = new Tab(Constantes.MAGASIN,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
              
              
            }else if(i==Constantes.LISTE_SECTEUR){
              try {
                    boolean found= openIfExist(tbpane,Constantes.SECTEUR);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/Secteur.fxml"));
                    Tab tab = new Tab(Constantes.SECTEUR,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/Secteur.fxml"));
                    Tab tab = new Tab(Constantes.SECTEUR,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
              
              
            }else if(i==Constantes.LISTE_VEHICULE){
              try {
                    boolean found= openIfExist(tbpane,Constantes.VEHICULE);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/Vehicule.fxml"));
                    Tab tab = new Tab(Constantes.VEHICULE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/Vehicule.fxml"));
                    Tab tab = new Tab(Constantes.VEHICULE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
              
              
            }else if(i==Constantes.LISTE_VENDEUR){
              try {
                 boolean found= openIfExist(tbpane,Constantes.VENDEUR);
                 if(found){
                      Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/Vendeur.fxml"));
                    Tab tab = new Tab(Constantes.VENDEUR,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
              //   Open("/View/Referentiel/Vendeur.fxml","Liste vendeur")
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/Vendeur.fxml"));
                    Tab tab = new Tab(Constantes.VENDEUR,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                        
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
              
              
            }else if(i==Constantes.LISTE_CONSULTATION_VENDEUR){
              try {
                 boolean found= openIfExist(tbpane,Constantes.CONSULTATION_VENDEUR);
                 if(found){
                      Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/ConsultationVendeur.fxml"));
                    Tab tab = new Tab(Constantes.CONSULTATION_VENDEUR,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
              //   Open("/View/Referentiel/Vendeur.fxml","Liste vendeur")
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/ConsultationVendeur.fxml"));
                    Tab tab = new Tab(Constantes.CONSULTATION_VENDEUR,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                        
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
              
              
            }else if(i==Constantes.LISTE_CAISSIER){
              try {
                 boolean found= openIfExist(tbpane,Constantes.CAISSIER);
                 if(found){
                      Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/Caissier.fxml"));
                    Tab tab = new Tab(Constantes.CAISSIER,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
              //   Open("/View/Referentiel/Vendeur.fxml","Liste vendeur")
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/Caissier.fxml"));
                    Tab tab = new Tab(Constantes.CAISSIER,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                        
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
              
              
            }else if(i==Constantes.LISTE_CONSULTATION_CAISSIER){
              try {
                 boolean found= openIfExist(tbpane,Constantes.CONSULTATION_CAISSIER);
                 if(found){
                      Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/ConsultationCaissier.fxml"));
                    Tab tab = new Tab(Constantes.CONSULTATION_CAISSIER,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
              //   Open("/View/Referentiel/Vendeur.fxml","Liste vendeur")
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/ConsultationCaissier.fxml"));
                    Tab tab = new Tab(Constantes.CONSULTATION_CAISSIER,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                        
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
              
              
            }else if(i==Constantes.LISTE_CHAUFFEUR){
              try {
                    boolean found= openIfExist(tbpane,Constantes.CHAUFFEUR);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/Chauffeur.fxml"));
                    Tab tab = new Tab(Constantes.CHAUFFEUR,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/Chauffeur.fxml"));
                    Tab tab = new Tab(Constantes.CHAUFFEUR,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
              
              
            }
            else if(i==Constantes.LISTE_OFFRE){
              try {
                    boolean found= openIfExist(tbpane,Constantes.OFFREE);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/ListeOffre.fxml"));
                    Tab tab = new Tab(Constantes.OFFREE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/ListeOffre.fxml"));
                    Tab tab = new Tab(Constantes.OFFREE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
              
              
            }
            else if(i==Constantes.LISTE_TYPE_VENTE){
              try {
                    boolean found= openIfExist(tbpane,Constantes.TYPE_VENTE);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/TypeVente.fxml"));
                    Tab tab = new Tab(Constantes.TYPE_VENTE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/TypeVente.fxml"));
                    Tab tab = new Tab(Constantes.TYPE_VENTE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
              
            }else if(i==Constantes.LISTE_COMPTE_VERSEMENT){
              try {
                    boolean found= openIfExist(tbpane,Constantes.COMPTE_VERSEMENT);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/CompteVersement.fxml"));
                    Tab tab = new Tab(Constantes.COMPTE_VERSEMENT,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/CompteVersement.fxml"));
                    Tab tab = new Tab(Constantes.COMPTE_VERSEMENT,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
 
              
            }else if(i==Constantes.LISTE_PRIX_VENTE_DEPOT){
              try {
                    boolean found= openIfExist(tbpane,Constantes.PRIX_VENTE_DEPOT);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/PrixVenteDepot.fxml"));
                    Tab tab = new Tab(Constantes.PRIX_VENTE_DEPOT,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/PrixVenteDepot.fxml"));
                    Tab tab = new Tab(Constantes.PRIX_VENTE_DEPOT,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    });
    
     
       listeView3.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
        
        @Override
        public void changed(ObservableValue<? extends java.lang.String> observable, java.lang.String oldValue, java.lang.String newValue) {
//          throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            int i= listeView3.getSelectionModel().getSelectedIndex();
            
            
            if(i==Constantes.LISTE_UTILISATEUR){
                try {
                      boolean found= openIfExist(tbpane,Constantes.UTILISATEUR);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/ListeUtilisateur.fxml"));
                    Tab tab = new Tab(Constantes.UTILISATEUR,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/ListeUtilisateur.fxml"));
                    Tab tab = new Tab(Constantes.UTILISATEUR,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }else if(i==Constantes.GERER_AUTHORISATION){
              try {
                    boolean found= openIfExist(tbpane,Constantes.AUTHORISATION);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/GererAuthUtilisateur.fxml"));
                    Tab tab = new Tab(Constantes.AUTHORISATION,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Referentiel/GererAuthUtilisateur.fxml"));
                    Tab tab = new Tab(Constantes.AUTHORISATION,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  

        }
    });
       
       
      listeView4.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
        
        @Override
        public void changed(ObservableValue<? extends java.lang.String> observable, java.lang.String oldValue, java.lang.String newValue) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            int i= listeView4.getSelectionModel().getSelectedIndex();
            
            
            if(i==Constantes.SITUATION_STOCK){
                try {
                      boolean found= openIfExist(tbpane,Constantes.SITUATION);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/SituationStock.fxml"));
                    Tab tab = new Tab(Constantes.SITUATION,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/SituationStock.fxml"));
                    Tab tab = new Tab(Constantes.SITUATION,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(i==Constantes.SITUATION_STOCK_SIEGE){
                try {
                      boolean found= openIfExist(tbpane,Constantes.SITUATION_SIEGE);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/SituationStockSiege.fxml"));
                    Tab tab = new Tab(Constantes.SITUATION_SIEGE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/SituationStockSiege.fxml"));
                    Tab tab = new Tab(Constantes.SITUATION_SIEGE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            else if(i==Constantes.SITUATION_STOCK_GLOBAL){
              try {
                    boolean found= openIfExist(tbpane,Constantes.SITUATION_GLOBAL);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/SituationStockGlobal.fxml"));
                    Tab tab = new Tab(Constantes.SITUATION_GLOBAL,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/SituationStockGlobal.fxml"));
                    Tab tab = new Tab(Constantes.SITUATION_GLOBAL,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
              else if(i==Constantes.INITIAL_STOCK){
              try {
                    boolean found= openIfExist(tbpane,Constantes.INITIAL);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/Initial.fxml"));
                    Tab tab = new Tab(Constantes.INITIAL,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/Initial.fxml"));
                    Tab tab = new Tab(Constantes.INITIAL,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
              
              
            else if(i==Constantes.RECEPTION_STOCK){
              try {
                    boolean found= openIfExist(tbpane,Constantes.RECEPTIONS);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/Reception.fxml"));
                    Tab tab = new Tab(Constantes.RECEPTIONS,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/Reception.fxml"));
                    Tab tab = new Tab(Constantes.RECEPTIONS,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
              else if(i==Constantes.DECLARATION_RECEPTION){
              try {
                    boolean found= openIfExist(tbpane,Constantes.DECLARATION_RECEPTIONS);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/DeclarationReception.fxml"));
                    Tab tab = new Tab(Constantes.DECLARATION_RECEPTIONS,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/DeclarationReception.fxml"));
                    Tab tab = new Tab(Constantes.DECLARATION_RECEPTIONS,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
              
                else if(i==Constantes.RECEPTION_ETRANGERE){
              try {
                    boolean found= openIfExist(tbpane,Constantes.RECEPTIONS_ETRANGERE);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/ReceptionEtrangere.fxml"));
                    Tab tab = new Tab(Constantes.RECEPTIONS_ETRANGERE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/ReceptionEtrangere.fxml"));
                    Tab tab = new Tab(Constantes.RECEPTIONS_ETRANGERE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
               else if(i==Constantes.TRANSFERT_STOCK){
              try {
                    boolean found= openIfExist(tbpane,Constantes.TRANSFERTS);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/Transfert.fxml"));
                    Tab tab = new Tab(Constantes.TRANSFERTS,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/Transfert.fxml"));
                    Tab tab = new Tab(Constantes.TRANSFERTS,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
               
               else if(i==Constantes.CORRECTION_TRANSFERTS_SORTIE_STOCK){
              try {
                    boolean found= openIfExist(tbpane,Constantes.CORRECTION_TRANSFERTS_SORTIE);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/CorrectionTransfertSorties.fxml"));
                    Tab tab = new Tab(Constantes.CORRECTION_TRANSFERTS_SORTIE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/CorrectionTransfertSorties.fxml"));
                    Tab tab = new Tab(Constantes.CORRECTION_TRANSFERTS_SORTIE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
               
                 else if(i==Constantes.SORTIE_STOCK){
              try {
                    boolean found= openIfExist(tbpane,Constantes.SORTIE);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/Sorties.fxml"));
                    Tab tab = new Tab(Constantes.SORTIE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/Sorties.fxml"));
                    Tab tab = new Tab(Constantes.SORTIE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
                  else if(i==Constantes.ETAT_SORTIES){
              try {
                    boolean found= openIfExist(tbpane,Constantes.ETAT_SORTIE);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/EtatSorties.fxml"));
                    Tab tab = new Tab(Constantes.ETAT_SORTIE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/EtatSorties.fxml"));
                    Tab tab = new Tab(Constantes.ETAT_SORTIE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                 
                    else if(i==Constantes.COMMANDEE_CLIENT){
              try {
                    boolean found= openIfExist(tbpane,Constantes.COMMANDE_CLIENT);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/SortiesClient.fxml"));
                    Tab tab = new Tab(Constantes.COMMANDE_CLIENT,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/SortiesClient.fxml"));
                    Tab tab = new Tab(Constantes.COMMANDE_CLIENT,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                  
                        else if(i==Constantes.VERSEMENT_CLIENT){
              try {
                    boolean found= openIfExist(tbpane,Constantes.VERSEMENT_CLIENTS);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/VersementClient.fxml"));
                    Tab tab = new Tab(Constantes.VERSEMENT_CLIENTS,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/VersementClient.fxml"));
                    Tab tab = new Tab(Constantes.VERSEMENT_CLIENTS,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    
                   else if(i==Constantes.VERSEMENT_CHEQUE_CLIENT){
              try {
                    boolean found= openIfExist(tbpane,Constantes.VERSEMENT_CHEQUE_CLIENTS);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/VersementChequeClient.fxml"));
                    Tab tab = new Tab(Constantes.VERSEMENT_CHEQUE_CLIENTS,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/VersementChequeClient.fxml"));
                    Tab tab = new Tab(Constantes.VERSEMENT_CHEQUE_CLIENTS,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                        
                   else if(i==Constantes.VERSEMENT_BANCAIRE_CLIENT){
              try {
                    boolean found= openIfExist(tbpane,Constantes.VERSEMENT_BANCAIRE_CLIENTS);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/VersementBancaireClient.fxml"));
                    Tab tab = new Tab(Constantes.VERSEMENT_BANCAIRE_CLIENTS,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/VersementBancaireClient.fxml"));
                    Tab tab = new Tab(Constantes.VERSEMENT_BANCAIRE_CLIENTS,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
 
                   else if(i==Constantes.CONSULTATION_COMPTE_CLIENT){
              try {
                    boolean found= openIfExist(tbpane,Constantes.CONSULT_COMPTE_CLIENT);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/ConsultationCompteClient.fxml"));
                    Tab tab = new Tab(Constantes.CONSULT_COMPTE_CLIENT,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/ConsultationCompteClient.fxml"));
                    Tab tab = new Tab(Constantes.CONSULT_COMPTE_CLIENT,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }    
                   
                   else if(i==Constantes.AVANCE_CHAUFFEUUR){
              try {
                    boolean found= openIfExist(tbpane,Constantes.AVANCE_CHAUFFEUR);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/AvanceChauffeur.fxml"));
                    Tab tab = new Tab(Constantes.AVANCE_CHAUFFEUR,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/AvanceChauffeur.fxml"));
                    Tab tab = new Tab(Constantes.AVANCE_CHAUFFEUR,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                   
                    else if(i==Constantes.AVANCE_MAGASINIIER){
              try {
                    boolean found= openIfExist(tbpane,Constantes.AVANCE_MAGASINIER);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/AvanceMagasinier.fxml"));
                    Tab tab = new Tab(Constantes.AVANCE_MAGASINIER,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/AvanceMagasinier.fxml"));
                    Tab tab = new Tab(Constantes.AVANCE_MAGASINIER,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                   
                   else if(i==Constantes.TRANSFERT_EN_ATTENTE){
              try {
                    boolean found= openIfExist(tbpane,Constantes.TRANSFERT_EN_ATTENTEE);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/StockEnAttente.fxml"));
                    Tab tab = new Tab(Constantes.TRANSFERT_EN_ATTENTEE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/StockEnAttente.fxml"));
                    Tab tab = new Tab(Constantes.TRANSFERT_EN_ATTENTEE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
                                 
                   else if(i==Constantes.RECEPTION_EN_ATTENTE){
              try {
                    boolean found= openIfExist(tbpane,Constantes.RECEPTION_EN_ATTENTEE);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/StockReceptionEnAttente.fxml"));
                    Tab tab = new Tab(Constantes.RECEPTION_EN_ATTENTEE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/StockReceptionEnAttente.fxml"));
                    Tab tab = new Tab(Constantes.RECEPTION_EN_ATTENTEE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
                   
                     else if(i==Constantes.RETOUR_DEFINITIFS){
                try {
                      boolean found= openIfExist(tbpane,Constantes.RETOUR_DEFINITIF);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/RetourDefinitif.fxml"));
                    Tab tab = new Tab(Constantes.RETOUR_DEFINITIF,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Stock/RetourDefinitif.fxml"));
                    Tab tab = new Tab(Constantes.RETOUR_DEFINITIF,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
    });
      
      
           listeView5.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
        
        @Override
        public void changed(ObservableValue<? extends java.lang.String> observable, java.lang.String oldValue, java.lang.String newValue) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            int i= listeView5.getSelectionModel().getSelectedIndex();
            
 
            
             if(i==Constantes.CREATION_TOURNEE){
              try {
                    boolean found= openIfExist(tbpane,Constantes.TOURNEE);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/GestionTournee/CreationTournee.fxml"));
                    Tab tab = new Tab(Constantes.TOURNEE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/GestionTournee/CreationTournee.fxml"));
                    Tab tab = new Tab(Constantes.TOURNEE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
             
             else if(i==Constantes.SITUATION_INITIAL){
              try {
                    boolean found= openIfExist(tbpane,Constantes.SITIAT_INITIAL);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/GestionTournee/SituationInitial.fxml"));
                    Tab tab = new Tab(Constantes.SITIAT_INITIAL,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/GestionTournee/SituationInitial.fxml"));
                    Tab tab = new Tab(Constantes.SITIAT_INITIAL,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
             
             else if(i==Constantes.SITUATION_TOURNEE){
              try {
                    boolean found= openIfExist(tbpane,Constantes.SITUAT_TOURNEE);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/GestionTournee/SituationTournee.fxml"));
                    Tab tab = new Tab(Constantes.SITUAT_TOURNEE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/GestionTournee/SituationTournee.fxml"));
                    Tab tab = new Tab(Constantes.SITUAT_TOURNEE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    });
           
           
           listeView6.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
        
        @Override
        public void changed(ObservableValue<? extends java.lang.String> observable, java.lang.String oldValue, java.lang.String newValue) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            int i= listeView6.getSelectionModel().getSelectedIndex();
            
            
            if(i==Constantes.CHARGEMENTS){
                try {
                      boolean found= openIfExist(tbpane,Constantes.CHARGEMEENT);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Vente/Chargement.fxml"));
                    Tab tab = new Tab(Constantes.CHARGEMEENT,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Vente/Chargement.fxml"));
                    Tab tab = new Tab(Constantes.CHARGEMEENT,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            else if(i==Constantes.RETOURS){
                try {
                      boolean found= openIfExist(tbpane,Constantes.RETOUUR);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Vente/Retour.fxml"));
                    Tab tab = new Tab(Constantes.RETOUUR,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Vente/Retour.fxml"));
                    Tab tab = new Tab(Constantes.RETOUUR,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            else if(i==Constantes.SUIVI_VENTES){
                try {
                      boolean found= openIfExist(tbpane,Constantes.SUIVI_VENTEES);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Vente/SuiviVentes.fxml"));
                    Tab tab = new Tab(Constantes.SUIVI_VENTEES,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Vente/SuiviVentes.fxml"));
                    Tab tab = new Tab(Constantes.SUIVI_VENTEES,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            else if(i==Constantes.SAISIE_VERSEMENT){
              try {
                    boolean found= openIfExist(tbpane,Constantes.VERSEMENT);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Vente/Versement.fxml"));
                    Tab tab = new Tab(Constantes.VERSEMENT,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Vente/Versement.fxml"));
                    Tab tab = new Tab(Constantes.VERSEMENT,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
              
            } else if(i==Constantes.VERSEMENT_BANCAIRE){
              try {
                    boolean found= openIfExist(tbpane,Constantes.VERSEMENT_BANCAIIRE);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Vente/VersementBancaire.fxml"));
                    Tab tab = new Tab(Constantes.VERSEMENT_BANCAIIRE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Vente/VersementBancaire.fxml"));
                    Tab tab = new Tab(Constantes.VERSEMENT_BANCAIIRE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
              
            } 
            else if(i==Constantes.VERSEMENT_CHEQUE){
              try {
                    boolean found= openIfExist(tbpane,Constantes.VERSEMENT_CHEQUEE);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Vente/VersementCheque.fxml"));
                    Tab tab = new Tab(Constantes.VERSEMENT_CHEQUEE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Vente/VersementCheque.fxml"));
                    Tab tab = new Tab(Constantes.VERSEMENT_CHEQUEE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
              
            }
            
             else if(i==Constantes.LISTE_VERSEMENT_CHEQUE){
              try {
                    boolean found= openIfExist(tbpane,Constantes.LISTE_VERSEMENT_CHEQUEE);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Vente/ListeVersementCheque.fxml"));
                    Tab tab = new Tab(Constantes.LISTE_VERSEMENT_CHEQUEE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Vente/ListeVersementCheque.fxml"));
                    Tab tab = new Tab(Constantes.LISTE_VERSEMENT_CHEQUEE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
              
            }
            
             else if(i==Constantes.LISTE_VERSEMENT_BANCAIRE){
              try {
                    boolean found= openIfExist(tbpane,Constantes.LISTE_VERSEMENT_BANCAIIRE);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Vente/ListeVersementBancaire.fxml"));
                    Tab tab = new Tab(Constantes.LISTE_VERSEMENT_BANCAIIRE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Vente/ListeVersementBancaire.fxml"));
                    Tab tab = new Tab(Constantes.LISTE_VERSEMENT_BANCAIIRE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
              
            }
             
            else if(i==Constantes.SAISIE_FACTURE){
              try {
                    boolean found= openIfExist(tbpane,Constantes.FACTURES);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Vente/Facture.fxml"));
                    Tab tab = new Tab(Constantes.FACTURES,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Vente/Facture.fxml"));
                    Tab tab = new Tab(Constantes.FACTURES,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            
                        
            else if(i==Constantes.SITUATION_VENTE_FACTURE){
              try {
                    boolean found= openIfExist(tbpane,Constantes.SITUATION_VENTEFACTURE);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Vente/SituationVenteFacture.fxml"));
                    Tab tab = new Tab(Constantes.SITUATION_VENTEFACTURE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Vente/SituationVenteFacture.fxml"));
                    Tab tab = new Tab(Constantes.SITUATION_VENTEFACTURE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            
            else if(i==Constantes.ETAT_VERSEMENTCHEQUE_CAISSIER){
              try {
                    boolean found= openIfExist(tbpane,Constantes.ETAT_VERSEMENT_CHEQUE_CAISSIER);
                 if(found){
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Vente/EtatVersementChequeCaissier.fxml"));
                    Tab tab = new Tab(Constantes.ETAT_VERSEMENT_CHEQUE_CAISSIER,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Vente/EtatVersementChequeCaissier.fxml"));
                    Tab tab = new Tab(Constantes.ETAT_VERSEMENT_CHEQUE_CAISSIER,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            
            else if(i==Constantes.TRANSFERT_CHEQUES){
              try {
                    boolean found= openIfExist(tbpane,Constantes.TRANS_CHEQUE);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Vente/TransfertCheque.fxml"));
                    Tab tab = new Tab(Constantes.TRANS_CHEQUE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Vente/TransfertCheque.fxml"));
                    Tab tab = new Tab(Constantes.TRANS_CHEQUE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
 
             else if(i==Constantes.CONSULTATION_COMPTE_VENTE){
              try {
                    boolean found= openIfExist(tbpane,Constantes.CONSULT_COMPTE_VENTE);
                 if(found){
                     Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Vente/ConsultationCompteVente.fxml"));
                    Tab tab = new Tab(Constantes.CONSULT_COMPTE_VENTE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Vente/ConsultationCompteVente.fxml"));
                    Tab tab = new Tab(Constantes.CONSULT_COMPTE_VENTE,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
              else if(i==Constantes.REPORTS_DE_SOLDES){
              try {
                    boolean found= openIfExist(tbpane,Constantes.REPO_DE_SOLDES);
                 if(found){
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Vente/ReportsDeSoldes.fxml"));
                    Tab tab = new Tab(Constantes.REPO_DE_SOLDES,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                     return;
                 }
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Vente/ReportsDeSoldes.fxml"));
                    Tab tab = new Tab(Constantes.REPO_DE_SOLDES,node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    });
    }
    
    
       private boolean openIfExist(TabPane tbpane, String text) {
            ObservableList<Tab> listTabs = tbpane.getTabs();
            for (Iterator<Tab> iterator = listTabs.iterator(); iterator.hasNext();) {
                Tab next = iterator.next();
                if(next.getText().equals(text)){
                    tbpane.getTabs().remove(next);
                    return true;
                }
            }
            return false;
        }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        homeControllerRef = this;
        
        loadlistview();
        selectmenu();
        
             utilisateur=nav.utilisateur;
             
     utilisateurConnecte.setText (LoginAppController.utilisateur.getNom());
     autoriseMenuUtilisateur();
     
//     test.setStyle("-fx-border-color: black;");
// 
//     Image image = new Image("file:test.jpg");
//     
//     ImageView imageView = new ImageView();
//     imageView.setImage(image);
//
//     test.getChildren().add(imageView);

    }    

    @FXML
    private void fermerBtnClicked(MouseEvent event) throws IOException {
        
               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_QUITTER_APP);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                      System.exit(0);

            } 
    }

    @FXML
    private void deconnecterBtnClicked(MouseEvent event) throws IOException {
        
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_FERMER_SESSION);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                  
                Stage stage = (Stage)
              deconnecterBtn.getScene().getWindow();
              stage.close();
                Parent root = FXMLLoader.load(getClass().getResource(nav.getLogin()));
                Stage stage1 = new Stage();
                Scene scence = new Scene(root);
                stage1.setScene(scence);
                
                 //supprimer la bar fermer reduire agrandir
                stage1.initStyle(StageStyle.UNDECORATED);
                stage1.show();

            } 
    }
    
    
//    public void versement(Depot depot ,Vendeur vendeur){
//        
//            
//     try {
//                     node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Vente/Versement.fxml"));
//                     VersementController.versementControllerRef.chargeDonner(depot, vendeur);
//                     tab = new Tab("Saisie Versement",node);
//                    tbpane.getTabs().add(tab);
//                    tbpane.getSelectionModel().select(tab);
//                } catch (IOException ex) {
//                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//    
//                        System.out.println("Controller.HomeController.versement()");
//    
//    } 

    public void consultationCompteVente (){
        
            
     try {
                     node = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Vente/ConsultationCompteVente.fxml"));
      
                     tab = new Tab("Consultation Compte Vente",node);
                    tbpane.getTabs().add(tab);
                    tbpane.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
    
                        System.out.println("Controller.HomeController.consultationCompteVente()");
    
    } 
    
    
    
    @FXML
    private void homeBtnClicked(MouseEvent event) {
                
        
    }

    @FXML
    private void doubleClickedMax(MouseEvent event) {
  
        if(event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 2){
                   
                
            Stage stage = (Stage) basePaneAP.getScene().getWindow();
            if(stage.isMaximized())
                stage.setMaximized(false);
            else
                stage.setMaximized(true);
            

    } 
        }
    }

    @FXML
    private void doubleClickedMax2(MouseEvent event) {
        
             if(event.getButton().equals(MouseButton.PRIMARY)&& event.getClickCount() == 2){
             
                
                
            Stage stage = (Stage) basePaneAP.getScene().getWindow();
             if(stage.isMaximized()){
                  stage.setMaximized(false);
                 System.out.println("??????????????????????????");
             }
              else
                            displayPrimaryScreen(stage);
               System.out.println("###########################");
    
    }
    }
    
    
           public static void displayPrimaryScreen(Stage stage) {
		   javafx.geometry.Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

		// set Stage boundaries to visible bounds of the main screen
		stage.setX(primaryScreenBounds.getMinX());
		stage.setY(primaryScreenBounds.getMinY());
		stage.setWidth(primaryScreenBounds.getWidth());
		stage.setHeight(primaryScreenBounds.getHeight());
	}


}
    

