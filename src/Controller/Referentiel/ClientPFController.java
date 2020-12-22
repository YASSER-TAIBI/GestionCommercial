/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.ClientPF;
import dao.Entity.CompteClient;
import dao.Entity.Secteur;
import dao.Entity.SequenceurClient;
import dao.Entity.SequenceurFacture;
import dao.Entity.Ville;
import dao.Manager.ClientPFDAO;
import dao.Manager.CompteClientDAO;
import dao.Manager.SecteurDAO;
import dao.Manager.SequenceurClientDAO;
import dao.Manager.SequenceurFactureDAO;
import dao.Manager.VilleDAO;
import dao.ManagerImpl.ClientPFDAOImpl;
import dao.ManagerImpl.CompteClientDAOImpl;
import dao.ManagerImpl.SecteurDAOImpl;
import dao.ManagerImpl.SequenceurClientDAOImpl;
import dao.ManagerImpl.SequenceurFactureDAOImpl;
import dao.ManagerImpl.VilleDAOImpl;
import function.navigation;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
public class ClientPFController implements Initializable {

    VilleDAO villeDAO = new VilleDAOImpl();
    
    SecteurDAO secteurDAO = new SecteurDAOImpl();
    
    ClientPFDAO clientdao = new ClientPFDAOImpl();
    
    SequenceurClientDAO sequenceurClientDAO = new SequenceurClientDAOImpl();
    
    SequenceurFactureDAO sequenceurFactureDAO = new SequenceurFactureDAOImpl();
    
    navigation nav = new navigation();
    
    Ville ville;
    
    @FXML
    private TableView<ClientPF> clientTable;
    @FXML
    private TableColumn<ClientPF, String> codeclientColumn;
    @FXML
    private TableColumn<ClientPF, String> nomColumn;
    @FXML
    private TableColumn<ClientPF, String> adresseColumn;
    @FXML
    private TableColumn<ClientPF, String> telColumn;
    @FXML
    private TableColumn<ClientPF, String> emailColumn;
    @FXML
    private TableColumn<ClientPF, String> villeColumn;
    @FXML
    private TableColumn<ClientPF, String> secteurColumn;
    @FXML
    private TableColumn<ClientPF, Boolean> clientBloquerColumn;
    
    @FXML
    private TextField codefield;
    @FXML
    private TextField nomfield;
    @FXML
    private TextField adressefield;
    @FXML
    private TextField telfield;
    @FXML
    private TextField emailfield;
    @FXML
    private Label poidserreur;
    @FXML
    private ComboBox<String> villecombo;
    @FXML
    private ComboBox<String> secteurcombo;
    @FXML
    private Label secteurerreur;
    @FXML
    private Label prixgroserreur;
    @FXML
    private Label conditionnementerreur;
    @FXML
    private Label prixdetailerreur;
    @FXML
    private Label villeerreur;
    @FXML
    private Label codeerreur;
    @FXML
    private Label nomerreur;
    @FXML
    private Label adresseerreur;
    @FXML
    private Label telerreur;
    @FXML
    private Label emailerreur;
    @FXML
    private Label bloquerClienterreur;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRafraichir;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnModifer;
    @FXML
    private RadioButton ouiRadio;
    @FXML
    private ToggleGroup clientBloque;
    @FXML
    private RadioButton nonRadio;
    
    private Map<String, Ville> mapVille = new HashMap<>();
    private Map<String, Secteur> mapSecteur = new HashMap<>();
    
    boolean msgerrue = false;
    
    private final ObservableList<ClientPF> listeClient = FXCollections.observableArrayList();
    
     CompteClientDAO compteClientDAO = new CompteClientDAOImpl();
    
       void Incrementation (){
       
        SequenceurClient sequenceurClient = sequenceurClientDAO.findByDepot(nav.getUtilisateur().getDepot().getId());
        codefield.setText(sequenceurClient.getCode()+"00"+(sequenceurClient.getSeq()+1));

   }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        clientTable.setEditable(true);

        Incrementation ();
        
        loadDetail();
        setColumnProperties();
        charger();
    }

    public void charger() {
        List<Ville> listVille = villeDAO.findVilleByDepot(nav.getUtilisateur().getDepot().getId());

        listVille.stream().map((ville) -> {
            villecombo.getItems().addAll(ville.getLibelle());
            return ville;
        }).forEachOrdered((ville) -> {
            mapVille.put(ville.getLibelle(), ville);
        });
      
    }

    void loadDetail() {

        listeClient.clear();
        listeClient.addAll(clientdao.findAll());
        clientTable.setItems(listeClient);
    }

    void setColumnProperties() {

        codeclientColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        telColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        villeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClientPF, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ClientPF, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getVille().getLibelle());
            }
        });
        secteurColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClientPF, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ClientPF, String> param) {
                return  new ReadOnlyObjectWrapper(param.getValue().getSecteur().getLibelle());
            }
        });

        clientBloquerColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClientPF, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<ClientPF, Boolean> param) {
                return  new ReadOnlyObjectWrapper(param.getValue().isBlockClient());
            }
        });
    }


    void vider() {

        nomfield.setText(Constantes.CHAMP_VIDE);
        adressefield.setText(Constantes.CHAMP_VIDE);
        telfield.setText(Constantes.CHAMP_VIDE);
        emailfield.setText(Constantes.CHAMP_VIDE);
        villecombo.getSelectionModel().select(-1);
        secteurcombo.getSelectionModel().select(-1);
        codefield.requestFocus();
        ouiRadio.setSelected(false);
        nonRadio.setSelected(false);
        vider_erreur();


        btnAjouter.setDisable(false);
        loadDetail();
        setColumnProperties();

    }

    void vider_erreur() {
        villeerreur.setText(Constantes.CHAMP_VIDE);
        secteurerreur.setText(Constantes.CHAMP_VIDE);
        codeerreur.setText(Constantes.CHAMP_VIDE);
        nomerreur.setText(Constantes.CHAMP_VIDE);
        adresseerreur.setText(Constantes.CHAMP_VIDE);
        bloquerClienterreur.setText(Constantes.CHAMP_VIDE);
    }

    void verifier() {
        msgerrue = false;
        if (codefield.getText().equals(Constantes.CHAMP_VIDE)) {
            codeerreur.setText(Constantes.CHAMP_ETOILE);
            codefield.requestFocus();
            msgerrue = true;

        } else {
            codeerreur.setText(Constantes.CHAMP_VIDE);

        }
        if (nomfield.getText().equals(Constantes.CHAMP_VIDE)) {
            nomerreur.setText(Constantes.CHAMP_ETOILE);
            nomfield.requestFocus();
            msgerrue = true;

        } else {
            nomerreur.setText(Constantes.CHAMP_VIDE);

        }
        if (adressefield.getText().equals(Constantes.CHAMP_VIDE)) {
            adresseerreur.setText(Constantes.CHAMP_ETOILE);
            adressefield.requestFocus();
            msgerrue = true;
        } else {
            adresseerreur.setText(Constantes.CHAMP_VIDE);

        }
        
//        if (emailfield.getText().equals(Constantes.CHAMP_VIDE)) {
//            emailfield.setText("Email indéfini");
//
//        }
//        if (emailfield.getText() != Constantes.CHAMP_VIDE) {
//
//            if (emailfield.getText().equals("Email indéfini") == false && emailfield.getText().matches(Constantes.CHAMP_VERIFIER_EMAIL) == false) {
//                //System.out.println("email invalide");
//                emailerreur.setText(Constantes.CHAMP_EMAIL_INVALIDE);
//                emailfield.requestFocus();
//                msgerrue = true;
//            } else if (emailfield.getText().matches(Constantes.CHAMP_VERIFIER_EMAIL) == true) {
//                //System.out.println("email valide");
//                emailerreur.setText(Constantes.CHAMP_VIDE);
//
//            }
//            if (emailfield.getText().equals("Email indéfini")) {
//                emailerreur.setText(Constantes.CHAMP_VIDE);
//
//            }
//        }

//        if (telfield.getText().equals(Constantes.CHAMP_VIDE)) {
//            telerreur.setText(Constantes.CHAMP_ETOILE);
//            telfield.requestFocus();
//            msgerrue = true;
//        }
//        if (telfield.getText() != Constantes.CHAMP_VIDE) {
//            if (telfield.getText().matches(Constantes.CHAMP_VERIFIER) == false) {
//                // nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.CHAMP_TELEPHONE_INVALIDE);
//                telerreur.setText(Constantes.CHAMP_TELEPHONE_INVALIDE);
//                telfield.requestFocus();
//                msgerrue = true;
//            } else if (telfield.getText().matches(Constantes.CHAMP_VERIFIER) == true && telfield.getText().length() == 10) {
//                // nav.showAlert(Alert.AlertType.INFORMATION, "Succès", null, "tel invalide");
//                telerreur.setText(Constantes.CHAMP_VIDE);
//
//            } else {
//                telerreur.setText(Constantes.CHAMP_TELEPHONE_INVALIDE);
//                telfield.requestFocus();
//                msgerrue = true;
//            }
//        }
//        if (telfield.getText().length() != 10) {
//            telerreur.setText(Constantes.CHAMP_TELEPHONE_INVALIDE);
//            telfield.requestFocus();
//            msgerrue = true;
//        } else {
//            telerreur.setText(Constantes.CHAMP_VIDE);
//
//        }
            if (villecombo.getSelectionModel().getSelectedIndex() == -1) {
                villeerreur.setText(Constantes.CHAMP_ETOILE);
                msgerrue = true;
            } else {
                villeerreur.setText(Constantes.CHAMP_VIDE);

            }
            
             if (secteurcombo.getSelectionModel().getSelectedIndex() == -1) {
                secteurerreur.setText(Constantes.CHAMP_ETOILE);
                msgerrue = true;
            } else {
                secteurerreur.setText(Constantes.CHAMP_VIDE);

            }

              if (ouiRadio.isSelected()==false && nonRadio.isSelected()==false) {
                bloquerClienterreur.setText(Constantes.CHAMP_ETOILE);
                msgerrue = true;
            } else {
                bloquerClienterreur.setText(Constantes.CHAMP_VIDE);

            }
        }

    @FXML
    private void selectionner(ActionEvent event) {
        
          secteurcombo.getItems().clear();
          
            Ville ville  = mapVille.get(villecombo.getSelectionModel().getSelectedItem());
            
             if(ville!=null){
            List<Secteur> listSecteur = secteurDAO.findSecteurByVille(ville.getId());
            listSecteur.stream().map((secteur) -> {
                secteurcombo.getItems().addAll(secteur.getLibelle());
                return secteur;
            }).forEachOrdered((secteur) -> {
                mapSecteur.put(secteur.getLibelle(), secteur);
            });
             }
    }

    @FXML
    private void afficherArt(MouseEvent event) {
        
        Integer r = clientTable.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            return;
        } else {
            nomfield.setText(nomColumn.getCellData(r));
            adressefield.setText(adresseColumn.getCellData(r));
            telfield.setText(telColumn.getCellData(r));
            emailfield.setText(emailColumn.getCellData(r));
            villecombo.setValue(villeColumn.getCellData(r));
            secteurcombo.setValue(secteurColumn.getCellData(r));
            
            if (clientBloquerColumn.getCellData(r)== Boolean.TRUE){
            ouiRadio.setSelected(true);
            }else{
            nonRadio.setSelected(true);
            }
            
            vider_erreur();
            btnAjouter.setDisable(true);

        }
    }

 

    @FXML
    private void ajouterOnAction(ActionEvent event) {
        
             msgerrue = false;
        verifier();
        if (msgerrue == true) {
            return;
        } else {

 
            
//########################################################## Creation de CompteClient ###############################################################################################################################################################################################             

    CompteClient compteClient = new  CompteClient();
         
       compteClient.setCode(codefield.getText());
       compteClient.setLibelle(nomfield.getText());
       compteClient.setUtilisateurCreation(nav.getUtilisateur());

          compteClientDAO.add(compteClient);
 
//#########################################################################################################################################################################################################################################################             
          
            Date d = new Date();

            ClientPF clientPF = new ClientPF();
            clientPF.setCode(codefield.getText());
            clientPF.setNom(nomfield.getText());
            clientPF.setAdresse(adressefield.getText());
            clientPF.setTelephone(telfield.getText());
            clientPF.setEmail(emailfield.getText());
            clientPF.setCompteClient(compteClient);
            Ville villesel = mapVille.get(villecombo.getSelectionModel().getSelectedItem());
            clientPF.setVille(villesel);
            Secteur secteur=mapSecteur.get(secteurcombo.getSelectionModel().getSelectedItem());
            clientPF.setSecteur(secteur);
            
            if (ouiRadio.isSelected()==true){
            clientPF.setBlockClient(true);
            }else{
            clientPF.setBlockClient(false);
            }
            
            clientPF.setDcreation(d);
            clientPF.setDajours(d);
            clientdao.add(clientPF);

//########################################################## Creation de Sequenceur Facture ###############################################################################################################################################################################################             

    SequenceurFacture sequenceurFacture = new  SequenceurFacture();
    
       sequenceurFacture.setCodeClient(codefield.getText());
       sequenceurFacture.setSeq(1);

          sequenceurFactureDAO.add(sequenceurFacture);
            
            
//########################################################## Creation de Sequenceur Client ###############################################################################################################################################################################################             

            SequenceurClient sequenceurClient = sequenceurClientDAO.findByDepot(nav.getUtilisateur().getDepot().getId());

            sequenceurClient.setSeq(sequenceurClient.getSeq()+1);
            sequenceurClientDAO.edit(sequenceurClient);
            
            Incrementation ();
            
            nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_AJOUT);
            setColumnProperties();
            loadDetail();
            vider();

        }

        
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
        
              vider();
        
    }

    @FXML
    private void supprimerOnAction(ActionEvent event) {
        
               Integer r = clientTable.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            nav.showAlert(Alert.AlertType.WARNING, Constantes.TYPE_ALERT_INFORMATION, null, Constantes.MESSAGE_ALERT_SELECTIONNER);

        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_SUPPRIMER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();
//nav.showAlert(Alert.AlertType.CONFIRMATION, "Information", null, "Voullez vous modifier cet Article ?"

            if (result.get() == ButtonType.OK) {
                ClientPF cl = clientTable.getSelectionModel().getSelectedItem();
                clientdao.delete(cl);
                nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_SUPPRIMER);
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
            Integer r = clientTable.getSelectionModel().getSelectedIndex();
            if (r <= -1) {
                nav.showAlert(Alert.AlertType.CONFIRMATION, Constantes.TYPE_ALERT_INFORMATION, null, Constantes.MESSAGE_ALERT_SELECTIONNER);

            } else {
                Date d = new Date();
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_MODIFICATION);
                alert.setTitle("Confirmation");
                Optional<ButtonType> result = alert.showAndWait();


                if (result.get() == ButtonType.OK) {
                    ClientPF cl = clientTable.getSelectionModel().getSelectedItem();
                    cl.setCode(codefield.getText());
                    cl.setNom(nomfield.getText());
                    cl.setAdresse(adressefield.getText());
                    cl.setTelephone(telfield.getText());
                    cl.setEmail(emailfield.getText());
                    Ville villesel = mapVille.get(villecombo.getSelectionModel().getSelectedItem());
                    cl.setVille(villesel);
                    Secteur secteur = mapSecteur.get(secteurcombo.getSelectionModel().getSelectedItem());
                    cl.setSecteur(secteur);
                    
                    if (ouiRadio.isSelected()==true){
                    cl.setBlockClient(true);
                    }else{
                    cl.setBlockClient(false);
                    }
                    
                    cl.setDajours(d);
                    nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_MODIFICATION);
                    clientdao.edit(cl);
                    //   nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, "Article Modifié avec succès");
                    setColumnProperties();
                    loadDetail();
                    vider();
                }

            }

        }
        
    }

    }
