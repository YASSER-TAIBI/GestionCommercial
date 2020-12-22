/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.GestionTournee;

import Utils.Constantes;
import dao.Entity.Article;
import dao.Entity.Depot;
import dao.Entity.DetailChargeSupp;
import dao.Entity.DetailCompte;
import dao.Entity.DetailTournee;
import dao.Entity.DetailVendeurSecteur;
import dao.Entity.Magasin;
import dao.Entity.Secteur;
import dao.Entity.Tournee;
import dao.Entity.TypeVente;
import dao.Entity.Vehicule;
import dao.Entity.Vendeur;
import dao.Entity.Ville;
import dao.Manager.ArticleDAO;
import dao.Manager.DetailChargeSuppDAO;
import dao.Manager.DetailCompteDAO;
import dao.Manager.SecteurDAO;
import dao.Manager.DetailTourneeDAO;
import dao.Manager.DetailVendeurSecteurDAO;
import dao.Manager.TourneeDAO;
import dao.Manager.TypeVenteDAO;
import dao.Manager.VehiculeDAO;
import dao.Manager.VendeurDAO;
import dao.Manager.VilleDAO;
import dao.ManagerImpl.ArticleDAOImpl;
import dao.ManagerImpl.DetailChargeSuppDAOImpl;
import dao.ManagerImpl.DetailCompteDAOImpl;
import dao.ManagerImpl.SecteurDAOImpl;
import dao.ManagerImpl.DetailTourneeDAOImpl;
import dao.ManagerImpl.DetailVendeurSecteurDAOImpl;
import dao.ManagerImpl.TourneeDAOImpl;
import dao.ManagerImpl.TypeVenteDAOImpl;
import dao.ManagerImpl.VehiculeDAOImpl;
import dao.ManagerImpl.VendeurDAOImpl;
import dao.ManagerImpl.VilleDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.AbstractList;
import java.util.ArrayList;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class CreationTourneeController implements Initializable {

    
    @FXML
    private TableView<DetailTournee> detailTourneeTable;
    @FXML
    private TableColumn<DetailTournee, String> codeArticleColumn;
    @FXML
    private TableColumn<DetailTournee, String> libelleColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteInitialColumn;
    @FXML
    private TableColumn<DetailTournee, BigDecimal> qteFinalColumn;
    
    @FXML
    private ComboBox<String> vendeurCombo;
    @FXML
    private ComboBox<String> magasinCombo;
    @FXML
    private ComboBox<String> secteurCombo;
    @FXML
    private ImageView initialiserBtn;
    @FXML
    private DatePicker dateJourneePicher;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRafraichir;
    /**
     * Initializes the controller class.
     */
    
    private Map<String, DetailVendeurSecteur> mapDetailVendeurSecteur = new HashMap<>();
        DetailVendeurSecteurDAO detailVendeurSecteurDAO = new DetailVendeurSecteurDAOImpl();

     private Map<String, Vendeur> mapVendeur = new HashMap<>();
     VendeurDAO vendeurDAO = new VendeurDAOImpl();
    
     private Map<String, Magasin> mapMagasin = new HashMap<>();
       
    private final ObservableList<DetailTournee> listeDetailTournee = FXCollections.observableArrayList();

    DetailTourneeDAO detailTourneeDAO = new DetailTourneeDAOImpl();
    DetailCompteDAO detailCompteDAO = new DetailCompteDAOImpl();
    TourneeDAO tourneeDAO = new TourneeDAOImpl();
    navigation nav = new navigation();
    Tournee tournee = new Tournee();
    ArticleDAO articleDAO = new ArticleDAOImpl();
    TypeVenteDAO typeVenteDAO = new TypeVenteDAOImpl();
    DetailChargeSuppDAO detailChargeSuppDAO = new DetailChargeSuppDAOImpl();
    
    
     String valeur = "";
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        valeur = "";
        
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
              
                for(int i=0;i<listVendeur.size();i++){
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
            
            setColumnProperties();
         
    }    


    void setColumnProperties() {

       codeArticleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailTournee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailTournee, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getArticle().getCode());
            }
        });
       
        libelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailTournee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailTournee, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getArticle().getLibelle());
            }
        });
        
         qteInitialColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailTournee, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailTournee, BigDecimal> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getQuantiteTotalInitial());
            }
        });

             qteFinalColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailTournee, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailTournee, BigDecimal> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getQuantiteFinal());
            }
        });
 
    }

   
     void videer(){
     
       vendeurCombo.getSelectionModel().select(-1);
       magasinCombo.getSelectionModel().select(-1);
       secteurCombo.getSelectionModel().select(-1);
       dateJourneePicher.setValue(null);
       detailTourneeTable.getItems().clear();
     
     }
 



    @FXML
    private void rechercheArticle(MouseEvent event) throws ParseException {
        
              if(
                  
          magasinCombo.getSelectionModel().getSelectedIndex()== -1 ||
          secteurCombo.getSelectionModel().getSelectedIndex()== -1 ||
          vendeurCombo.getSelectionModel().getSelectedIndex()== -1 || 
          dateJourneePicher.getValue().equals(null)){
              
          nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
         
        }
        else {
      
            LocalDate localDate=dateJourneePicher.getValue();
            Date dateCharg =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
            Depot depot  =nav.getUtilisateur().getDepot();
            DetailVendeurSecteur detailVendeurSecteur  = mapDetailVendeurSecteur.get(secteurCombo.getSelectionModel().getSelectedItem());
            Magasin  magasin = mapMagasin.get(magasinCombo.getSelectionModel().getSelectedItem());
            
            
            
            List <Object[]> listeDetailTourneeTMP =detailTourneeDAO.findDetailTourneeByVendeurAndDepotAndMagasinAndEtat(detailVendeurSecteur.getVendeur().getNom(),depot.getLibelle1(), magasin.getLibelle(),detailVendeurSecteur.getSecteur().getLibelle());

                  System.out.println("listeDetailTourneeTMP "+listeDetailTourneeTMP.size());
            
             if (listeDetailTourneeTMP.size()!= 0){
            
            for(int i=0; i<listeDetailTourneeTMP.size(); i++) {

                    Object[] object=listeDetailTourneeTMP.get(i);
                   
                    Date dateTournee =(Date)object[0];
                    Article article = articleDAO.findById((int)object[1]);
                    BigDecimal qteInitial = (BigDecimal)object[2]; 
                    BigDecimal qteFinal = (BigDecimal)object[3]; 
                    String etat = (String)object[4];

                     System.out.println("dateTournee "+dateTournee);
                      System.out.println("article "+article);
                       System.out.println("qteInitial "+qteInitial);
                        System.out.println("qteFinal "+qteFinal);
                        System.out.println("etat "+etat);
                        
                    
                    if(dateTournee == null && article == null && qteInitial == null && qteFinal == null && etat == null){
                    
                        listeDetailTournee.clear();
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_VERIFIER_COORDONNEES);
                        break;
                        
                    }else{

                        if (dateCharg.compareTo(dateTournee)==0 && etat.equals(Constantes.ETAT_INITIAL)){
                        
                            //Récupérer l'initiale
                            valeur = "RI";
                              listeDetailTournee.clear();
                              listeDetailTournee.addAll(detailTourneeDAO.findDetailTourneeByDateTournee(dateCharg,detailVendeurSecteur.getVendeur().getId(), depot.getId(), magasin.getId(), detailVendeurSecteur.getSecteur().getId()));
                            break; 
                            
                        }else if (dateCharg.compareTo(dateTournee)==0 && !etat.equals(Constantes.ETAT_INITIAL)){
                        
                            //La tournée est deja Ouverte
                        
                            listeDetailTournee.clear();
                            nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_TOURNEE_OUVERTE); 
                            break;
                             
                        }else if (dateCharg.compareTo(dateTournee)>0 && ( etat.equals(Constantes.ETAT_FACTURER) ||  etat.equals(Constantes.ETAT_SUSPENDU))){
                        
                            //Creation d'un nouveau Tournee
                            
                            valeur = "CT";
 
                            
                                DetailTournee detailTournee = new DetailTournee();
                                
                                detailTournee.setArticle(article);
                                detailTournee.setQuantiteInitial(BigDecimal.ZERO);
                                detailTournee.setQuantiteInitialCaisse(BigDecimal.ZERO);
                                detailTournee.setQuantiteTotalInitial(qteFinal);
                                detailTournee.setQuantiteFinal(qteFinal);
                                detailTournee.setTournee(tournee);
                                detailTournee.setQuantiteCharge(BigDecimal.ZERO);
                                detailTournee.setQuantiteChargeCaisse(BigDecimal.ZERO);
                                detailTournee.setQuantiteChargeComptCaisse(BigDecimal.ZERO);
                                detailTournee.setQuantiteChargeSupp(BigDecimal.ZERO);
                                detailTournee.setQuantiteOffre(BigDecimal.ZERO);
                                detailTournee.setQuantiteOffreCaisse(BigDecimal.ZERO);
                                detailTournee.setQuantiteTotalOffre(BigDecimal.ZERO);
                                detailTournee.setQuantiteChargeSuppCaisse(BigDecimal.ZERO);
                                detailTournee.setQuantiteChargeCompt(BigDecimal.ZERO);
                                detailTournee.setQuantiteTotalCharge(BigDecimal.ZERO);
                                detailTournee.setQuantiteRecape(BigDecimal.ZERO);
                                detailTournee.setQuantiteCaisseRecape(BigDecimal.ZERO);
                                detailTournee.setQuantiteTotalRecape(BigDecimal.ZERO);
                                detailTournee.setQuantiteRetour(BigDecimal.ZERO);
                                detailTournee.setQuantiteRetourCaisse(BigDecimal.ZERO);
                                detailTournee.setQuantiteTotalRetour(BigDecimal.ZERO);
                                detailTournee.setQuantiteVendue(BigDecimal.ZERO);
                                detailTournee.setTypeVente(detailVendeurSecteur.getVendeur().getTypeVente());
                                detailTournee.setQuantiteVente(qteFinal);
                                detailTournee.setTotalFacture(BigDecimal.ZERO);
                                detailTournee.setTotalVendue(BigDecimal.ZERO);
                                detailTournee.setDateCreation(new Date() );

                                listeDetailTournee.add(detailTournee);
                                
                        }else if (dateCharg.compareTo(dateTournee)>0 && (etat.equals(Constantes.ETAT_INITIAL) || etat.equals(Constantes.ETAT_OUVERT) || etat.equals(Constantes.ETAT_EN_COURS) || etat.equals(Constantes.ETAT_VERSEMENT) || etat.equals(Constantes.ETAT_VERSEMENT_CHEQUE) || etat.equals(Constantes.ETAT_VERSEMENT_BANCAIRE) ||etat.equals(Constantes.ETAT_TRAITE))){
                        
                            listeDetailTournee.clear();
                            nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_ATTENTION_EN_COURS_TRAITEMENT);
                            break;
                        }
                        
                    }
            }
         
             detailTourneeTable.setItems(listeDetailTournee); 
             setColumnProperties();
            
             
             }else if (listeDetailTourneeTMP.size()== 0){
             
                  List <Object[]> listeObjectDetailTournee =detailTourneeDAO.findDetailTourneeByVendeurAndDepotAndMagasinAndEtatVersementOrFactureOrVente(detailVendeurSecteur.getVendeur().getNom(),depot.getLibelle1(), magasin.getLibelle(),detailVendeurSecteur.getSecteur().getLibelle());

                  if (listeObjectDetailTournee.size()!=0){
                  
                         for(int i=0; i<listeObjectDetailTournee.size(); i++) {

                    Object[] object=listeObjectDetailTournee.get(i);
                   
                    Date dateTournee =(Date)object[0];
                    Article article = articleDAO.findById((int)object[1]);
                    BigDecimal qteInitial = (BigDecimal)object[2]; 
                    BigDecimal qteFinal = (BigDecimal)object[3]; 
                    String etat = (String)object[4];
                  
                    if (dateCharg.compareTo(dateTournee)==0 && etat.equals(Constantes.ETAT_INITIAL)){
                        
                            //Récupérer l'initiale
                            valeur = "RI";
                              listeDetailTournee.clear();
                              listeDetailTournee.addAll(detailTourneeDAO.findDetailTourneeByDateTournee(dateCharg,detailVendeurSecteur.getVendeur().getId(), depot.getId(), magasin.getId(), detailVendeurSecteur.getSecteur().getId()));
                            break; 
                            
                    }else if (dateCharg.compareTo(dateTournee)>0 && ( etat.equals(Constantes.ETAT_FACTURER) ||  etat.equals(Constantes.ETAT_SUSPENDU))){
                        
                            //Creation d'un nouveau Tournee
                            
                            valeur = "CT";
 
                                DetailTournee detailTournee = new DetailTournee();
                                
                                detailTournee.setArticle(article);
                                detailTournee.setQuantiteInitial(BigDecimal.ZERO);
                                detailTournee.setQuantiteInitialCaisse(BigDecimal.ZERO);
                                detailTournee.setQuantiteTotalInitial(qteFinal);
                                detailTournee.setQuantiteFinal(qteFinal);
                                detailTournee.setTournee(tournee);
                                detailTournee.setQuantiteCharge(BigDecimal.ZERO);
                                detailTournee.setQuantiteChargeCaisse(BigDecimal.ZERO);
                                detailTournee.setQuantiteChargeComptCaisse(BigDecimal.ZERO);
                                detailTournee.setQuantiteChargeSupp(BigDecimal.ZERO);
                                detailTournee.setQuantiteChargeSuppCaisse(BigDecimal.ZERO);
                                detailTournee.setQuantiteChargeCompt(BigDecimal.ZERO);
                                detailTournee.setQuantiteTotalCharge(BigDecimal.ZERO);
                                detailTournee.setQuantiteOffre(BigDecimal.ZERO);
                                detailTournee.setQuantiteOffreCaisse(BigDecimal.ZERO);
                                detailTournee.setQuantiteTotalOffre(BigDecimal.ZERO);
                                detailTournee.setQuantiteRecape(BigDecimal.ZERO);
                                detailTournee.setQuantiteCaisseRecape(BigDecimal.ZERO);
                                detailTournee.setQuantiteTotalRecape(BigDecimal.ZERO);
                                detailTournee.setQuantiteRetour(BigDecimal.ZERO);
                                detailTournee.setQuantiteRetourCaisse(BigDecimal.ZERO);
                                detailTournee.setQuantiteTotalRetour(BigDecimal.ZERO);
                                detailTournee.setQuantiteVendue(BigDecimal.ZERO);
                                detailTournee.setTypeVente(detailVendeurSecteur.getVendeur().getTypeVente());
                                detailTournee.setQuantiteVente(qteFinal);
                                detailTournee.setTotalFacture(BigDecimal.ZERO);
                                detailTournee.setTotalVendue(BigDecimal.ZERO);
                                detailTournee.setDateCreation(new Date() );

                                listeDetailTournee.add(detailTournee);
                                
                    }else if (dateCharg.compareTo(dateTournee)>0 && (etat.equals(Constantes.ETAT_INITIAL) || etat.equals(Constantes.ETAT_OUVERT) || etat.equals(Constantes.ETAT_EN_COURS) || etat.equals(Constantes.ETAT_VERSEMENT) || etat.equals(Constantes.ETAT_VERSEMENT_CHEQUE) || etat.equals(Constantes.ETAT_VERSEMENT_BANCAIRE) || etat.equals(Constantes.ETAT_TRAITE))){
                        
                            listeDetailTournee.clear();
                            nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_ATTENTION_EN_COURS_TRAITEMENT);
                            break;
                        
                    }else{
                     
                            listeDetailTournee.clear();
                            nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_VERIFIER_COORDONNEES);
                            break; 
                    }
                         }
                         
             detailTourneeTable.setItems(listeDetailTournee); 
             setColumnProperties();
             
                  }else{
                     listeDetailTournee.clear();
              nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_VERIFIER_COORDONNEES);
                          
             }
          }
    }
    }
    
    @FXML
    private void ajouterOnAction(ActionEvent event) throws ParseException {
        
  
                         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

          LocalDate localDate=dateJourneePicher.getValue();
            Date dateCharg =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
            Depot depot  =nav.getUtilisateur().getDepot();
            DetailVendeurSecteur detailVendeurSecteur  = mapDetailVendeurSecteur.get(secteurCombo.getSelectionModel().getSelectedItem());
            Magasin  magasin = mapMagasin.get(magasinCombo.getSelectionModel().getSelectedItem());
            
                
              if (valeur == "CT"){
              
                  BigDecimal MontantCreditVendeur = BigDecimal.ZERO;
                  
                  List <DetailCompte> listedetailCompteDate =detailCompteDAO.findByDateOperation(detailVendeurSecteur.getVendeur().getCompte().getId());

                  DetailCompte detailCompte = listedetailCompteDate.get(listedetailCompteDate.size()-1);
     
                  List <Object[]> listedetailCompte =detailCompteDAO.findByMontantCredit(detailVendeurSecteur.getVendeur().getCompte().getId(),detailCompte.getDateOperation());
            
   
                                if (listedetailCompte.size()!= 0){
            
                                    for(int j=0; j<listedetailCompte.size(); j++) {

                                        Object[] objectTMP=listedetailCompte.get(j);
                   
                                        BigDecimal montantCredit =(BigDecimal)objectTMP[0];
                                        BigDecimal montantDebit = (BigDecimal)objectTMP[1]; 
                                        
                                if(montantCredit == null && montantDebit == null){
                    
                                    MontantCreditVendeur = BigDecimal.ZERO;
                        
                                }else{

                                    MontantCreditVendeur = montantCredit.subtract(montantDebit); 
                                }
                                    }
                                
                    tournee.setMontantCredit(MontantCreditVendeur);
                    tournee.setDateCreation(new Date());
                    tournee.setDepot(depot);
                    tournee.setMagasin(magasin);
                    tournee.setVendeur(detailVendeurSecteur.getVendeur());
                    tournee.setEtat(Constantes.ETAT_OUVERT);
                    tournee.setStatus(Constantes.ETAT_OUVERT);
                    tournee.setSecteur(detailVendeurSecteur.getSecteur());
                    tournee.setVille(detailVendeurSecteur.getSecteur().getVille());
                    tournee.setDateTournee(dateCharg);
                    tournee.setDetailTournee(listeDetailTournee);

                    tourneeDAO.add(tournee);

                    tournee = new Tournee();
                    
                    nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_AJOUT);
                    videer(); 
                    return;
                                
                                }
                  
              

              }else if (valeur == "RI"){
              
                  for (int i = 0; i < listeDetailTournee.size(); i++) {
                      
                      DetailTournee detailTournee = listeDetailTournee.get(i);
                      detailTournee.getTournee().setEtat(Constantes.ETAT_OUVERT);
                      
                      detailTourneeDAO.edit(detailTournee);
                      
                      nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_AJOUT);
                      videer();
                      return;
                  }

              }else {
               nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_VERIFIER_COORDONNEES);
               return; 
              }
            }
    }


    @FXML
    private void rafraichirOnAction(ActionEvent event) {
           videer();
    }

    @FXML
    private void vendeurOnAction(ActionEvent event) {
        
                                                     secteurCombo.getItems().clear();
          
            Vendeur vendeur  = mapVendeur.get(vendeurCombo.getSelectionModel().getSelectedItem());
            
             if(vendeur!=null){
            List<DetailVendeurSecteur> listDetailVendeurSecteur = detailVendeurSecteurDAO.findDetailVendeurSecteurByVendeur(vendeur.getId());
            listDetailVendeurSecteur.stream().map((detailVendeurSecteurTMP) -> {
                secteurCombo.getItems().addAll(detailVendeurSecteurTMP.getSecteur().getLibelle());
                return detailVendeurSecteurTMP;
            }).forEachOrdered((detailVendeurSecteurTMP) -> {
                mapDetailVendeurSecteur.put(detailVendeurSecteurTMP.getSecteur().getLibelle(), detailVendeurSecteurTMP);
            });
             }
        
    }

}
