/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Vente;

import Utils.Constantes;
import dao.Entity.Caissier;
import dao.Entity.Compte;
import dao.Entity.DetailCompte;
import dao.Entity.DetailVendeurSecteur;
import dao.Entity.Secteur;
import dao.Entity.Vendeur;
import dao.Entity.Ville;
import dao.Manager.CaissierDAO;
import dao.Manager.CompteDAO;
import dao.Manager.DetailCompteDAO;
import dao.Manager.DetailVendeurSecteurDAO;
import dao.Manager.SecteurDAO;

import dao.Manager.VendeurDAO;
import dao.Manager.VilleDAO;
import dao.ManagerImpl.CaissierDAOImpl;
import dao.ManagerImpl.CompteDAOImpl;
import dao.ManagerImpl.DetailCompteDAOImpl;
import dao.ManagerImpl.DetailVendeurSecteurDAOImpl;
import dao.ManagerImpl.SecteurDAOImpl;
import dao.ManagerImpl.VendeurDAOImpl;
import dao.ManagerImpl.VilleDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import static jdk.nashorn.tools.ShellFunctions.input;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ConsultationCompteVenteController implements Initializable {

    @FXML
    private TableView<DetailCompte> detailCompteTable;
    @FXML
    private TableColumn<DetailCompte, String> compteVendeurColumn;
    @FXML
    private TableColumn<DetailCompte, String> designationColumn;
    @FXML
    private TableColumn<DetailCompte, BigDecimal> montantDebitColumn;
    @FXML
    private TableColumn<DetailCompte, BigDecimal> montantCreditColumn;
    @FXML
    private TextField montantTotalCredit;
    @FXML
    private TextField montantTotalDebit;
    @FXML
    private TextField montantSolde;
    @FXML
    private DatePicker dateOperationFin;
    @FXML
    private DatePicker dateOperationDebut;
    @FXML
    private ImageView rechercheBtn;
    @FXML
    private ComboBox<String> operateurCombo;
    @FXML
    private ComboBox<String> vendeurCombo;
    @FXML
    private ComboBox<String> secteurCombo;
    @FXML
    private ComboBox<String> caissierCombo;
    /**
     * Initializes the controller class.
     */
    
            private final ObservableList<DetailCompte> listeDetailCompte=FXCollections.observableArrayList();
            ObservableList<String> operateur =FXCollections.observableArrayList("Vendeur","Caissier");
            
            
            DetailCompteDAO detailCompteDAO = new DetailCompteDAOImpl();
            DetailCompte detailCompte = new DetailCompte();
          
            private Map<String, Caissier> mapCaissier = new HashMap<>();
            CaissierDAO caissierDAO = new CaissierDAOImpl();
            
            private Map<String,Compte> mapCompte=new HashMap<>();
            CompteDAO compteDAO = new CompteDAOImpl();
            
            
            navigation nav = new navigation();
//          VendeurDAO vendeurDAO = new VendeurDAOImpl();
            
            
    private Map<String, DetailVendeurSecteur> mapDetailVendeurSecteur = new HashMap<>();
    DetailVendeurSecteurDAO detailVendeurSecteurDAO = new DetailVendeurSecteurDAOImpl();
    
    private Map<String, Vendeur> mapVendeur = new HashMap<>();
    VendeurDAO vendeurDAO = new VendeurDAOImpl();
    
    private Map<String, Ville> mapVille = new HashMap<>();
    VilleDAO villeDAO = new VilleDAOImpl();
    
    private Map<String, Secteur> mapSecteur = new HashMap<>();
    SecteurDAO secteurDAO = new SecteurDAOImpl();
   
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    
          
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                   operateurCombo.setItems(operateur);
                   
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
                   

                    List<Caissier> listCaissier =  caissierDAO.findByCaissier(nav.getUtilisateur().getDepot().getId());
              
                    listCaissier.stream().map((caissier) -> {
                caissierCombo.getItems().addAll(caissier.getNom());
                return caissier;
            }).forEachOrdered((caissier) -> {
                mapCaissier.put(caissier.getNom(), caissier);
            });
  
                    caissierCombo.setDisable(true);
                    vendeurCombo.setDisable(true);
                    secteurCombo.setDisable(true);
                    dateOperationDebut.setDisable(true);
                    dateOperationFin.setDisable(true);
                    rechercheBtn.setDisable(true);
                    
    }    
 
      void setColumnProperties(){

        designationColumn.setCellValueFactory(new PropertyValueFactory<>("designation"));
        montantDebitColumn.setCellValueFactory(new PropertyValueFactory<>("montantDebit"));
        montantCreditColumn.setCellValueFactory(new PropertyValueFactory<>("montantCredit"));
        
        compteVendeurColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCompte, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCompte, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCompte().getLibelle());
            }
        });
           
    }
    
    void loadDetail(){
        
        listeDetailCompte.clear();
        listeDetailCompte.addAll(detailCompteDAO.findAll());
        detailCompteTable.setItems(listeDetailCompte);
    }
    
    @FXML
    private void afficherArt(MouseEvent event) {
    }


    void vide(){
    
//  operateurCombo.getSelectionModel().select(-1); 
    secteurCombo.getSelectionModel().select(-1);    
    vendeurCombo.getSelectionModel().select(-1);    
    dateOperationDebut.setValue(null);
    dateOperationFin.setValue(null);
       
    }
    
    private void vider(MouseEvent event) {
         vide();
    detailCompteTable.getItems().clear();  
    montantTotalCredit.setText("");
    montantTotalDebit.setText("");
    montantSolde.setText("");
        
    }

    @FXML
    private void rechercheArticle(MouseEvent event) throws ParseException {
        
                   listeDetailCompte.clear();
                   
             Vendeur vendeur  = mapVendeur.get(vendeurCombo.getSelectionModel().getSelectedItem());

            Caissier caissier  = mapCaissier.get(caissierCombo.getSelectionModel().getSelectedItem());
        if(
          dateOperationDebut.getValue()== null||
          dateOperationFin.getValue()== null||
          operateurCombo.getSelectionModel().getSelectedIndex()== -1 
                
          ) 
          {
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_REMPLIR_CHAMPS);
         
         return;
         
     }else if(dateOperationDebut.getValue()!=null && dateOperationFin.getValue()!=null)
		    		{
		    		if(!dateOperationDebut.getValue().equals(dateOperationFin.getValue()))
		    		{
		    			if(dateOperationFin.getValue().compareTo(dateOperationDebut.getValue())<0)
		    			{
		    				  nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
		    				return;
		    			}
		    			
		    		}

		    		}

        else if (dateOperationDebut.getValue()==null){
        
            dateOperationFin.setValue(null);
            return;
            
        }

        
           LocalDate localDate=dateOperationDebut.getValue();
           Date dateOperaDebut =null;
           if(localDate!=null)
           {
          dateOperaDebut =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
                 localDate=dateOperationFin.getValue();
                  Date dateOperaFin =null;
              if(localDate!=null)
           {     
          dateOperaFin =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           } 
         
 
            if (operateurCombo.getSelectionModel().getSelectedItem().equals(Constantes.TYPE_VENDEUR_CAISSIER)){
        List<DetailCompte> detailCompte1 = detailCompteDAO.findFilterByVendeurAndDate(caissier.getCompte() ,dateOperaDebut, dateOperaFin);
          listeDetailCompte.addAll(detailCompte1);
        detailCompteTable.setItems(listeDetailCompte);
        setColumnProperties();
        
            }else{
                
              List<DetailCompte> detailCompte1 = detailCompteDAO.findFilterByVendeurAndDate(vendeur.getCompte() ,dateOperaDebut, dateOperaFin);
              listeDetailCompte.addAll(detailCompte1);
        detailCompteTable.setItems(listeDetailCompte);
        setColumnProperties();
              
            }

        
          BigDecimal calculCreditTotal=BigDecimal.ZERO;
          BigDecimal calculDebitTotal=BigDecimal.ZERO;
          BigDecimal somme=BigDecimal.ZERO;
        
           for( int rows = 0;rows<detailCompteTable.getItems().size() ;rows++ ){

            calculCreditTotal = calculCreditTotal.add(montantCreditColumn.getCellData(rows));  
        
    }
         montantTotalCredit.setText(calculCreditTotal+"");
        
         
           for( int rows = 0;rows<detailCompteTable.getItems().size() ;rows++ ){

            calculDebitTotal = calculDebitTotal.add(montantDebitColumn.getCellData(rows));  
        
    }
         montantTotalDebit.setText(calculDebitTotal+"");  

         somme = calculCreditTotal.subtract(calculDebitTotal) ;
          
         montantSolde.setText(somme+"");
        
         
         vide();
    }


    @FXML
    private void operateurComboOnAction(ActionEvent event) {
        

          if (operateurCombo.getSelectionModel().getSelectedItem().compareTo(Constantes.TYPE_VENDEUR_CAISSIER)==0){
              
          secteurCombo.setDisable(true);
          vendeurCombo.setDisable(true);
          
                    caissierCombo.setDisable(false);
                    dateOperationDebut.setDisable(false);
                    dateOperationFin.setDisable(false);
                    rechercheBtn.setDisable(false);
          
          vendeurCombo.getSelectionModel().select(-1);
          caissierCombo.getSelectionModel().select(-1);
          secteurCombo.getSelectionModel().select(-1);
          
          }else if(operateurCombo.getSelectionModel().getSelectedItem().compareTo(Constantes.TYPE_VENDEUR_VENDEUR)==0){
            
          caissierCombo.setDisable(true);
              
          
                    secteurCombo.setDisable(false);
                    vendeurCombo.setDisable(false);
                    dateOperationDebut.setDisable(false);
                    dateOperationFin.setDisable(false);
                    rechercheBtn.setDisable(false);
          
          vendeurCombo.getSelectionModel().select(-1);
          caissierCombo.getSelectionModel().select(-1);
          secteurCombo.getSelectionModel().select(-1);
          }
    }


    @FXML
    private void vendeuurComboOnAction(ActionEvent event) {

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



    @FXML
    private void secteurComboOnAction(ActionEvent event) {
  
    }


    
}
