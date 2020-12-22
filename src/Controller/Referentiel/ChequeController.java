/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.Banque;
import dao.Entity.Cheque;
import dao.Entity.ClientPF;
import dao.Entity.DetailVendeurSecteur;
import dao.Entity.Secteur;
import dao.Entity.Vendeur;
import dao.Entity.Ville;
import dao.Manager.BanqueDAO;
import dao.Manager.ChequeDAO;
import dao.Manager.ClientPFDAO;
import dao.Manager.DetailVendeurSecteurDAO;
import dao.Manager.SecteurDAO;
import dao.Manager.VendeurDAO;
import dao.Manager.VilleDAO;
import dao.ManagerImpl.BanqueDAOImpl;
import dao.ManagerImpl.ChequeDAOImpl;
import dao.ManagerImpl.ClientPFDAOImpl;
import dao.ManagerImpl.DetailVendeurSecteurDAOImpl;
import dao.ManagerImpl.SecteurDAOImpl;
import dao.ManagerImpl.VendeurDAOImpl;
import dao.ManagerImpl.VilleDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
import javafx.scene.control.Alert.AlertType;
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
import javafx.scene.control.cell.CheckBoxTableCell;
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
public class ChequeController implements Initializable {

    @FXML
    private TableView<Cheque> chequeTable;
    @FXML
    private TableColumn<Cheque, String> numchequeColumn;
    @FXML
    private TableColumn<Cheque, BigDecimal> montantchequeColumn;
     @FXML
    private TableColumn<Cheque, String> nomProprietaireColumn;
    @FXML
    private TableColumn<Cheque, String> banqueColumn;
    @FXML
    private TableColumn<Cheque, String> secteurColumn;
    @FXML
    private TableColumn<Cheque, String> clientColumn;
    @FXML
    private TableColumn<Cheque, Date> dateEcheanceColumn;
    @FXML
    private TableColumn<Cheque, String> vendeurColumn;
    @FXML
    private TableColumn<Cheque, String> TypeChequeColumn;
    @FXML
    private TableColumn<Cheque,Boolean> actionChequeColumn;
    @FXML
    private DatePicker dateEcheance;
    @FXML
    private TextField numchequefield;
    @FXML
    private Label codeerreur;
    @FXML
    private TextField montantfield;
    @FXML
    private Label montanterreur;
    @FXML
    private TextField nomfield;
    @FXML
    private ComboBox<String> combobanque;
    @FXML
    private ComboBox<String> comboClient;
    @FXML
    private ComboBox<String> comboTypeCheque;
    @FXML
    private ComboBox<String> comboVendeur;
    @FXML
    private ComboBox<String> comboVille;
    @FXML
    private ComboBox<String> comboSecteur;
    @FXML
    private RadioButton remiseRadio;
    @FXML
    private ToggleGroup etatGroupe;
    @FXML
    private CheckBox versementCheck;
    @FXML
    private CheckBox paiementCheck;
    @FXML
    private CheckBox impayeCheck;
    @FXML
    private RadioButton retourRadio;
    @FXML
    private CheckBox especeCheck;
    @FXML
    private CheckBox chequeImpayeCheck;
    @FXML
    private RadioButton retourChequeRadio;
    @FXML
    private ToggleGroup impayeEtatGroupe;
    @FXML
    private RadioButton avocatChequeRadio;
    @FXML
    private TextField montantCheque;
    @FXML
    private ImageView imprimer;
    @FXML
    private ImageView rechercheBtn;
    @FXML
    private ImageView validerCheque;
    @FXML
    private ImageView retourListeCheque;
    @FXML
    private Label labelCheque;
    @FXML
    private Label nombreCheque;
    @FXML
    private ImageView calculeCheque;
    @FXML
    private Label nombreCheque1;
    @FXML
    private TextField montantTotal;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRafraichir;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnModifer;
  
     int valeur;
    
    BanqueDAO banquedao = new BanqueDAOImpl();
    ChequeDAO chequedao = new ChequeDAOImpl();
    VendeurDAO vendeurdao = new VendeurDAOImpl();
    ClientPFDAO clientDAO = new ClientPFDAOImpl();
    
    boolean msgerrue = false;
    navigation nav = new navigation();

    private final ObservableList<Cheque> listeCheque = FXCollections.observableArrayList();
    ObservableList<String> typeCheque =FXCollections.observableArrayList(Constantes.TYPE_CHEQUE_REMISE,Constantes.TYPE_CHEQUE_RETOUR);
    private Map<String, Banque> mapBanque = new HashMap<>();
    private Map<String, ClientPF> mapClient = new HashMap<>();
    private Map<String, ClientPF> mapcodeClient = new HashMap<>();
   
    private Map<String, DetailVendeurSecteur> mapDetailVendeurSecteur = new HashMap<>();
    DetailVendeurSecteurDAO detailVendeurSecteurDAO = new DetailVendeurSecteurDAOImpl();
    
    private Map<String, Ville> mapVille = new HashMap<>();
    VilleDAO villeDAO = new VilleDAOImpl();
    
    private Map<String, Secteur> mapSecteur = new HashMap<>();
    SecteurDAO secteurDAO = new SecteurDAOImpl();
    

    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         comboTypeCheque.setItems(typeCheque);

        charger();
        loadDetail();
        setColumnProperties();
        
        // Type Cheque
        remiseRadio.setVisible(false);
        retourRadio.setVisible(false);
        
        // Etat Remise
        versementCheck.setVisible(false);
        paiementCheck.setVisible(false);
        impayeCheck.setVisible(false);
        chequeImpayeCheck.setVisible(false);
        
        // Etat Cheque impaye
        retourChequeRadio.setVisible(false);
        avocatChequeRadio.setVisible(false);        
        
        // Etat Retour
        especeCheck.setVisible(false);
        
        // Column Action
        actionChequeColumn.setEditable(false);
        
        chequeTable.setEditable(true);
        
        // Retour Liste Cheque
        labelCheque.setVisible(false);
        retourListeCheque.setVisible(false);
        nombreCheque.setVisible(false);
        calculeCheque.setVisible(false);
        montantCheque.setVisible(false);
        validerCheque.setVisible(false);
        imprimer.setVisible(false);
        
        
        montantCheque();
        
   }

    public void montantCheque(){
    
               BigDecimal calculTotal=BigDecimal.ZERO;

           for( int rows = 0;rows<chequeTable.getItems().size() ;rows++ ){

            calculTotal = calculTotal.add(montantchequeColumn.getCellData(rows));  
        
    }
         montantTotal.setText(calculTotal+"");
    }
    
    public void charger() {
        
              List<Ville> listVille =  villeDAO.findVilleByDepot(nav.getUtilisateur().getDepot().getId());
            listVille.stream().map((ville) -> {
                comboVille.getItems().addAll(ville.getLibelle());
                return ville;
            }).forEachOrdered((ville) -> {
                mapVille.put(ville.getLibelle(), ville);
            });

        
        //liste Banque
        List<Banque> listBanque = banquedao.findAll();

        listBanque.stream().map((Banque) -> {
            combobanque.getItems().addAll(Banque.getLibelle());
            return Banque;
        }).forEachOrdered((Banque) -> {
            mapBanque.put(Banque.getLibelle(), Banque);
        });

    }

    void loadDetail() {

        listeCheque.clear();
        listeCheque.addAll(chequedao.findAll());
        chequeTable.setItems(listeCheque);
    }

    void setColumnProperties() {

        numchequeColumn.setCellValueFactory(new PropertyValueFactory<>("num_cheque"));
        montantchequeColumn.setCellValueFactory(new PropertyValueFactory<>("montant"));
        nomProprietaireColumn.setCellValueFactory(new PropertyValueFactory<>("nom_Proprietaire"));
        dateEcheanceColumn.setCellValueFactory(new PropertyValueFactory<>("dateEcheance"));
        TypeChequeColumn.setCellValueFactory(new PropertyValueFactory<>("typeCheque"));
        
        banqueColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Cheque, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Cheque, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getBanque().getLibelle());
            }
        });
        
        clientColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Cheque, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Cheque, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getClient().getNom());
            }
        });
        
        vendeurColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Cheque, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Cheque, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getVendeur().getNom());
            }
        });
        
        secteurColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Cheque, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Cheque, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getSecteur().getLibelle());
            }
        });
        
          actionChequeColumn.cellValueFactoryProperty();
          actionChequeColumn.setCellValueFactory((cellData) -> {
          Cheque cellvalue= cellData.getValue();
              BooleanProperty property = new SimpleBooleanProperty();
                      property.set(cellvalue.isAction());
                      
                      property.addListener((observabel, oldvalue,newvalue)->cellvalue.setAction(newvalue));
              
              return property; 
          });
          actionChequeColumn.setCellFactory(act-> new CheckBoxTableCell<>());
    }




    @FXML
    private void afficherArt(MouseEvent event) {
            Integer r = chequeTable.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            return;
        } else {
        
            numchequefield.setText(numchequeColumn.getCellData(r));
            nomfield.setText(nomProprietaireColumn.getCellData(r));
            montantfield.setText(String.valueOf(montantchequeColumn.getCellData(r)));
            combobanque.setValue(banqueColumn.getCellData(r));
            comboClient.setValue(clientColumn.getCellData(r));
            comboSecteur.setValue(secteurColumn.getCellData(r));
            comboVendeur.setValue(vendeurColumn.getCellData(r));
            comboTypeCheque.setValue(TypeChequeColumn.getCellData(r));
            
            LocalDate date = new java.util.Date( dateEcheanceColumn.getCellData(r).getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            
            dateEcheance.setValue(date) ;
            
//            LocalDate date = new java.util.Date( transfertStockTmp.getDateTransf().getTime()).toInstant()
         
           
            btnAjouter.setDisable(true);

           
        }
        
          
    }


    void vider(){
    
        numchequefield.setText(Constantes.CHAMP_VIDE);
        montantfield.setText(Constantes.CHAMP_VIDE);
        nomfield.setText(Constantes.CHAMP_VIDE);
        comboSecteur.getSelectionModel().select(-1);
        dateEcheance.setValue(null);
        comboSecteur.getSelectionModel().select(-1);
        combobanque.getSelectionModel().select(-1);
        comboVille.getSelectionModel().select(-1);
        comboClient.getSelectionModel().select(-1);
        comboVendeur.getSelectionModel().select(-1);
        comboTypeCheque.getSelectionModel().select(-1);
        

        btnAjouter.setDisable(false);
        loadDetail();
        setColumnProperties();
    
    }




    @FXML
    private void imprimerCheque(MouseEvent event) {
    }

    @FXML
    private void suiviCheque(MouseEvent event) {
       
         // Retour Liste Cheque
        labelCheque.setVisible(true);
        retourListeCheque.setVisible(true);
        nombreCheque.setVisible(true);
        calculeCheque.setVisible(true);
        montantCheque.setVisible(true);
        validerCheque.setVisible(true);
        imprimer.setVisible(true);
        
        chequeTable.getItems().clear();
        
      
        
         // Type Cheque
        remiseRadio.setVisible(true);
        retourRadio.setVisible(true);
        
        //les champt texte & Button
        numchequefield.setDisable(true);
        nomfield.setDisable(true);
        dateEcheance.setDisable(true);
        comboTypeCheque.setDisable(true);
        combobanque.setDisable(true);
        comboClient.setDisable(true);
        comboVendeur.setDisable(true);
        montantfield.setDisable(true);
        
        btnAjouter.setDisable(true);
        btnRafraichir.setDisable(true);
        btnModifer.setDisable(true);
        btnSupprimer.setDisable(true);
        
         montantCheque(); 
        
    }

    @FXML
    private void remiseRadioOnAction(ActionEvent event) {
        
         // Etat Remise
        versementCheck.setVisible(true);
        paiementCheck.setVisible(true);
        impayeCheck.setVisible(true);
        chequeImpayeCheck.setVisible(true);
        
         especeCheck.setVisible(false);
         
          especeCheck.setSelected(false);
 
           for (int i =0;i<listeCheque.size();i++)
               {            
                   listeCheque.get(i).setAction(false);      
        }
            montantCheque();
    }

    @FXML
    private void chequeImpayeCheckOnAction(ActionEvent event) {
                // Etat Cheque impaye
                   if (chequeImpayeCheck.isSelected()==true){
        retourChequeRadio.setVisible(true);
        avocatChequeRadio.setVisible(true);
        
         versementCheck.setDisable(true);
          paiementCheck.setDisable(true);
        impayeCheck.setDisable(true);
         
        listeCheque.clear();
        listeCheque.addAll(chequedao.findChequeByEtat(Constantes.TYPE_CHEQUE_REMISE,Constantes.ETAT_STATUT_IMPAYE)); 
        chequeTable.setItems(listeCheque);
        montantCheque();
                // Column Action
        actionChequeColumn.setEditable(true);
        
                   }else if (chequeImpayeCheck.isSelected()==false) {
        retourChequeRadio.setVisible(false);
        avocatChequeRadio.setVisible(false); 
        
          versementCheck.setDisable(false);
          paiementCheck.setDisable(false);
        impayeCheck.setDisable(false);
        
         for (int i =0;i<listeCheque.size();i++)
               {            
                   listeCheque.get(i).setAction(false);      
        }
                   }
    }

    @FXML
    private void retourRadioOnAction(ActionEvent event) {
           // Etat Retour
   
        especeCheck.setVisible(true);
        
        versementCheck.setVisible(false);
        paiementCheck.setVisible(false);
        impayeCheck.setVisible(false);
        chequeImpayeCheck.setVisible(false);
        
        versementCheck.setDisable(false);
        paiementCheck.setDisable(false);
        impayeCheck.setDisable(false);
        chequeImpayeCheck.setDisable(false);
        
        versementCheck.setSelected(false);
        paiementCheck.setSelected(false);
        impayeCheck.setSelected(false);
        chequeImpayeCheck.setSelected(false);
        
        retourChequeRadio.setVisible(false);
        avocatChequeRadio.setVisible(false); 
        
        retourChequeRadio.setSelected(false);
        avocatChequeRadio.setSelected(false);
        
         for (int i =0;i<listeCheque.size();i++)
               {            
                   listeCheque.get(i).setAction(false);      
        }
          montantCheque();
    }

    @FXML
    private void versementCheckOnAction(ActionEvent event) {
        
        
         if (versementCheck.isSelected()==true){ 
              
        chequeImpayeCheck.setDisable(true);
        paiementCheck.setDisable(true);
        impayeCheck.setDisable(true);
        
        
            listeCheque.clear();
            listeCheque.addAll(chequedao.findChequeByEtat(Constantes.TYPE_CHEQUE_REMISE,Constantes.ETAT_STATUT_DEPOSE)); 
            chequeTable.setItems(listeCheque);
              montantCheque();
            
                    // Column Action
        actionChequeColumn.setEditable(true);
        
            
        }  else if (versementCheck.isSelected()==false){    
        chequeImpayeCheck.setDisable(false);
        paiementCheck.setDisable(false);
        impayeCheck.setDisable(false);
        
         for (int i =0;i<listeCheque.size();i++)
               {            
                   listeCheque.get(i).setAction(false);      
        }
        
        }
        
        
    }

    @FXML
    private void paiementCheckOnAction(ActionEvent event) {

        if (paiementCheck.isSelected()==true){  
  
        versementCheck.setDisable(true);
        chequeImpayeCheck.setDisable(true);
        impayeCheck.setDisable(true);    
        
        listeCheque.clear();
        listeCheque.addAll(chequedao.findChequeByEtat(Constantes.TYPE_CHEQUE_REMISE,Constantes.ETAT_STATUT_VERSE)); 
        chequeTable.setItems(listeCheque);
          montantCheque();
        // Column Action
        actionChequeColumn.setEditable(true);
            
        } else if (paiementCheck.isSelected()==false){  
        versementCheck.setDisable(false);
        chequeImpayeCheck.setDisable(false);
        impayeCheck.setDisable(false);    
        
         for (int i =0;i<listeCheque.size();i++)
               {            
                   listeCheque.get(i).setAction(false);      
        }
            
        }
        
    }

    @FXML
    private void impayeCheckOnAction(ActionEvent event) {
        
         if (impayeCheck.isSelected()==true){
        versementCheck.setDisable(true);
        paiementCheck.setDisable(true);
        chequeImpayeCheck.setDisable(true); 
        
        listeCheque.clear();
        listeCheque.addAll(chequedao.findChequeByEtat(Constantes.TYPE_CHEQUE_REMISE,Constantes.ETAT_STATUT_IMPAYE)); 
        chequeTable.setItems(listeCheque);
          montantCheque();
        // Column Action
        actionChequeColumn.setEditable(true);
        
        }else if (impayeCheck.isSelected()==false){
        versementCheck.setDisable(false);
        paiementCheck.setDisable(false);
        chequeImpayeCheck.setDisable(false); 
        
         for (int i =0;i<listeCheque.size();i++)
               {            
                   listeCheque.get(i).setAction(false);      
        }
        
        }
        
    }

    @FXML
    private void especeCheckOnAction(ActionEvent event) {

          if (especeCheck.isSelected()==true){
        
              listeCheque.clear();
            listeCheque.addAll(chequedao.findChequeByEtat(Constantes.TYPE_CHEQUE_RETOUR,Constantes.ETAT_STATUT_DEPOSE)); 
            chequeTable.setItems(listeCheque);
               montantCheque(); 
        // Column Action
        actionChequeColumn.setEditable(true);
        
              
        }else if (especeCheck.isSelected()==false) {
           for (int i =0;i<listeCheque.size();i++)
               {            
                   listeCheque.get(i).setAction(false);      
        }
          } 
    }

    @FXML
    private void validerCheque(MouseEvent event) {

          for( int rows = 0;rows<chequeTable.getItems().size() ;rows++ ){
     
         if(actionChequeColumn.getCellData(rows).booleanValue()==true){
             

             if (remiseRadio.isSelected()==true && versementCheck.isSelected()==true){
        Cheque chequeTmp= chequeTable.getItems().get(rows);
       chequeTmp.setEtat(Constantes.ETAT_STATUT_VERSE);
       chequeTmp.setAction(false);
        chequedao.edit(chequeTmp);
         nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_AJOUT);
          chequeTable.getItems().clear();
                 calculeCheque(event);
             }
             else if (remiseRadio.isSelected()==true && paiementCheck.isSelected()==true){
        Cheque chequeTmp= chequeTable.getItems().get(rows);
       chequeTmp.setEtat(Constantes.ETAT_STATUT_PAYE);
       chequeTmp.setAction(false);
        chequedao.edit(chequeTmp);
         nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_AJOUT);
          chequeTable.getItems().clear();
                 calculeCheque(event);    
             } 
             else if (retourRadio.isSelected()==true && especeCheck.isSelected()==true){
        Cheque chequeTmp= chequeTable.getItems().get(rows);
       chequeTmp.setEtat(Constantes.ETAT_STATUT_ESPECE);
       chequeTmp.setAction(false);
        chequedao.edit(chequeTmp);
         nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_AJOUT);
          chequeTable.getItems().clear();
                 calculeCheque(event);
             }
             
         }

     }

    }

    @FXML
    private void calculeCheque(MouseEvent event) {
          valeur=0;
           for( int rows = 0; rows<chequeTable.getItems().size(); rows++ ){
     
         if(actionChequeColumn.getCellData(rows).booleanValue()==true){
          valeur = valeur+1;
         }
         
             }
              montantCheque.setText(String.valueOf(valeur));
    }

    @FXML
    private void ListeCheque(MouseEvent event) {
        
         for (int i =0;i<listeCheque.size();i++)
               {            
                   listeCheque.get(i).setAction(false);      
        }

        remiseRadio.setVisible(false);
        retourRadio.setVisible(false);
     
        remiseRadio.setSelected(false);
        retourRadio.setSelected(false);
        
        versementCheck.setVisible(false);
        paiementCheck.setVisible(false);
        impayeCheck.setVisible(false);
        retourChequeRadio.setVisible(false);
        avocatChequeRadio.setVisible(false); 
        chequeImpayeCheck.setVisible(false);
        especeCheck.setVisible(false);
        
        
        versementCheck.setSelected(false);
        paiementCheck.setSelected(false);
        impayeCheck.setSelected(false);
        chequeImpayeCheck.setSelected(false);
        retourChequeRadio.setSelected(false);
        avocatChequeRadio.setSelected(false);
        especeCheck.setSelected(false);
        
        versementCheck.setDisable(false);
        paiementCheck.setDisable(false);
        impayeCheck.setDisable(false);
        chequeImpayeCheck.setDisable(false);
        especeCheck.setDisable(false);
        
        actionChequeColumn.setEditable(false);
        
        // Retour Liste Cheque
        labelCheque.setVisible(false);
        retourListeCheque.setVisible(false);
        nombreCheque.setVisible(false);
        calculeCheque.setVisible(false);
        montantCheque.setVisible(false);
        validerCheque.setVisible(false);
        imprimer.setVisible(false);
        
        montantCheque.setText("");
    
         //les champt texte & Button
        numchequefield.setDisable(false);
        nomfield.setDisable(false);
        dateEcheance.setDisable(false);
        comboTypeCheque.setDisable(false);
        combobanque.setDisable(false);
        comboClient.setDisable(false);
        comboVendeur.setDisable(false);
        montantfield.setDisable(false);
        
        btnAjouter.setDisable(false);
        btnRafraichir.setDisable(false);
        btnModifer.setDisable(false);
        btnSupprimer.setDisable(false);
          
         loadDetail();
        setColumnProperties();
          montantCheque();
        
    }

 



    @FXML
    private void ajouterOnAction(ActionEvent event) throws ParseException {
        
      
 
        if (numchequefield.getText().isEmpty() ||
                nomfield.getText().isEmpty() ||
                dateEcheance.getValue()== null ||
                comboTypeCheque.getSelectionModel().getSelectedIndex()==-1 ||
                combobanque.getSelectionModel().getSelectedIndex()==-1 ||
                comboVille.getSelectionModel().getSelectedIndex()==-1 ||
                comboSecteur.getSelectionModel().getSelectedIndex()==-1 ||
                comboVendeur.getSelectionModel().getSelectedIndex()==-1 ||       
                comboClient.getSelectionModel().getSelectedIndex()==-1 ||        
                montantfield.getText().isEmpty()
                ) {
            nav.showAlert(Alert.AlertType.WARNING, Constantes.TYPE_ALERT_ATTENTION, null, Constantes.REMPLIR_CHAMPS);
        } else {

              LocalDate localDate=dateEcheance.getValue();
                Date dtEcheance=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
            
            
            Cheque cheque = new Cheque();

            cheque.setNum_cheque(numchequefield.getText());
            cheque.setMontant(new BigDecimal(montantfield.getText()));
            cheque.setDateEcheance(dtEcheance);
            cheque.setNom_Proprietaire(nomfield.getText());
            cheque.setUtilisateurCreation(nav.getUtilisateur());
            cheque.setEtat(Constantes.ETAT_STATUT_DEPOSE);
            cheque.setDateCreation(new Date());
           
            DetailVendeurSecteur detailVendeurSecteur = mapDetailVendeurSecteur.get(comboVendeur.getSelectionModel().getSelectedItem());
            cheque.setVendeur(detailVendeurSecteur.getVendeur());
            
            Ville ville = mapVille.get(comboVille.getSelectionModel().getSelectedItem());
            cheque.setVille(ville);
            
            Secteur secteur = mapSecteur.get(comboSecteur.getSelectionModel().getSelectedItem());
            cheque.setSecteur(secteur);
            
            ClientPF client = mapClient.get(comboClient.getSelectionModel().getSelectedItem());
            cheque.setClient(client);
            
             Banque banque = mapBanque.get(combobanque.getSelectionModel().getSelectedItem());
            cheque.setBanque(banque);

            String  valeur= comboTypeCheque.getSelectionModel().getSelectedItem();
            cheque.setTypeCheque(valeur);
            
            chequedao.add(cheque);

            nav.showAlert(Alert.AlertType.CONFIRMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_AJOUT);
            setColumnProperties();
            loadDetail();
            vider();
            montantCheque();
        }
        
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
        
             vider();
    }

    @FXML
    private void supprimerOnAction(ActionEvent event) {
        
                 
         Integer r = chequeTable.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            nav.showAlert(Alert.AlertType.WARNING, Constantes.TYPE_ALERT_INFORMATION, null, Constantes.MESSAGE_ALERT_SELECTIONNER);

        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_SUPPRIMER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();
            
//nav.showAlert(Alert.AlertType.CONFIRMATION, "Information", null, "Voullez vous modifier cet Article ?"

            if (result.get() == ButtonType.OK) {
                Cheque cheque = chequeTable.getSelectionModel().getSelectedItem();
                chequedao.delete(cheque);
                nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_SUPPRIMER);
                setColumnProperties();
                loadDetail();
                vider();
                  montantCheque();
            }

        }
    }

    @FXML
    private void modifierOnAction(ActionEvent event) throws ParseException {
        
           if (numchequefield.getText().isEmpty() ||
                nomfield.getText().isEmpty() ||
                dateEcheance.getValue()== null ||
                comboTypeCheque.getSelectionModel().getSelectedIndex()==-1 ||
                combobanque.getSelectionModel().getSelectedIndex()==-1 ||
                comboVille.getSelectionModel().getSelectedIndex()==-1 ||
                comboSecteur.getSelectionModel().getSelectedIndex()==-1 ||
                comboVendeur.getSelectionModel().getSelectedIndex()==-1 ||       
                comboClient.getSelectionModel().getSelectedIndex()==-1 ||        
                montantfield.getText().isEmpty()
                ) {
            nav.showAlert(Alert.AlertType.WARNING, Constantes.TYPE_ALERT_ATTENTION, null, Constantes.REMPLIR_CHAMPS);
        } else {

                
              LocalDate localDate=dateEcheance.getValue();
                Date dtEcheance=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
                
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_MODIFICATION);
                alert.setTitle("Confirmation");
                Optional<ButtonType> result = alert.showAndWait();


                if (result.get() == ButtonType.OK) {
                    Cheque cheque = chequeTable.getSelectionModel().getSelectedItem();
                    cheque.setNum_cheque(numchequefield.getText());
                    cheque.setMontant(new BigDecimal(montantfield.getText()));
                    cheque.setNom_Proprietaire(nomfield.getText());
                    cheque.setDateEcheance(dtEcheance);
                    
                   DetailVendeurSecteur detailVendeurSecteur = mapDetailVendeurSecteur.get(comboVendeur.getSelectionModel().getSelectedItem());
            cheque.setVendeur(detailVendeurSecteur.getVendeur());
            
            Ville ville = mapVille.get(comboVille.getSelectionModel().getSelectedItem());
            cheque.setVille(ville);
            
            Secteur secteur = mapSecteur.get(comboSecteur.getSelectionModel().getSelectedItem());
            cheque.setSecteur(secteur);
            
            ClientPF client = mapClient.get(comboClient.getSelectionModel().getSelectedItem());
            cheque.setClient(client);
            
             Banque banque = mapBanque.get(combobanque.getSelectionModel().getSelectedItem());
            cheque.setBanque(banque);

            String  valeur= comboTypeCheque.getSelectionModel().getSelectedItem();
            cheque.setTypeCheque(valeur);
                            
                         
                    chequedao.edit(cheque);
                    nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_MODIFICATION);
                    setColumnProperties();
                    loadDetail();
                    vider();
                      montantCheque();
                }

            }

        }
    

    @FXML
    private void comboVendeur(ActionEvent event) {
    }

    @FXML
    private void villeOnAction(ActionEvent event) {
        
                   comboSecteur.getItems().clear();
          
            Ville ville  = mapVille.get(comboVille.getSelectionModel().getSelectedItem());
            
             if(ville!=null){
            List<Secteur> listSecteur = secteurDAO.findSecteurByVille(ville.getId());
            listSecteur.stream().map((secteur) -> {
                comboSecteur.getItems().addAll(secteur.getLibelle());
                return secteur;
            }).forEachOrdered((secteur) -> {
                mapSecteur.put(secteur.getLibelle(), secteur);
            });
             }
        
    }

    @FXML
    private void secteurOnAction(ActionEvent event) {
        
               comboVendeur.getItems().clear();
               comboClient.getItems().clear();
               
            Secteur secteur  = mapSecteur.get(comboSecteur.getSelectionModel().getSelectedItem());
            
             if(secteur!=null){
                 
            List<DetailVendeurSecteur> listDetailVendeurSecteur = detailVendeurSecteurDAO.findDetailVendeurSecteurBySecteur(secteur.getId());
            listDetailVendeurSecteur.stream().map((detailVendeurSecteur) -> {
                comboVendeur.getItems().addAll(detailVendeurSecteur.getVendeur().getNom());
                return detailVendeurSecteur;
            }).forEachOrdered((detailVendeurSecteur) -> {
                mapDetailVendeurSecteur.put(detailVendeurSecteur.getVendeur().getNom(), detailVendeurSecteur);
            });
            
             List<ClientPF> listClientPF= clientDAO.findClientPFBySecteur(secteur.getId());
            listClientPF.stream().map((client) -> {
                comboClient.getItems().addAll(client.getNom());
                return client;
            }).forEachOrdered((client) -> {
                mapClient.put(client.getNom(), client);
            });
            
             }
        
        
    }

}
