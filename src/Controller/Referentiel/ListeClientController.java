/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.ClientPF;
import dao.Entity.Secteur;
import dao.Entity.Vendeur;
import dao.Entity.Ville;
import dao.Manager.ClientPFDAO;
import dao.Manager.SecteurDAO;
import dao.Manager.VendeurDAO;
import dao.Manager.VilleDAO;
import dao.ManagerImpl.ClientPFDAOImpl;
import dao.ManagerImpl.SecteurDAOImpl;
import dao.ManagerImpl.VendeurDAOImpl;
import dao.ManagerImpl.VilleDAOImpl;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class ListeClientController implements Initializable {

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
    private Button btnRafraichir;
    @FXML
    private Button btnImprimer;
    
    @FXML
    private ComboBox<String> villecombo;
    @FXML
    private ComboBox<String> secteurcombo;
    
     private Map<String, Ville> mapVille = new HashMap<>();
     private Map<String, Secteur> mapSecteur = new HashMap<>();
     
     private final ObservableList<ClientPF> listeClient = FXCollections.observableArrayList();
       private final ObservableList<Vendeur> listeVendeur = FXCollections.observableArrayList();
       
 ClientPFDAO clientdao = new ClientPFDAOImpl();
 VilleDAO villeDAO = new VilleDAOImpl();
 SecteurDAO secteurDAO = new SecteurDAOImpl();
    VendeurDAO vendeurdao=new VendeurDAOImpl() ;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         clientTable.setEditable(true);

        loadDetail();
        setColumnProperties();
        charger();
        
        
    }    
    
     public void charger() {
        List<Ville> listVille = villeDAO.findByVille(Constantes.ETAT_X);
villecombo.getItems().add(0, "Tous");
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
       

    }



  

    @FXML
    private void selectionnerville(ActionEvent event) {
        
          try {
            secteurcombo.getItems().clear();
          
   
            List<Secteur> listSecteur = secteurDAO.findBySecteur(Constantes.ETAT_X);
            listSecteur.stream().map((secteur) -> {
                secteurcombo.getItems().addAll(secteur.getLibelle());
                return secteur;
            }).forEachOrdered((secteur) -> {
                mapSecteur.put(secteur.getLibelle(), secteur);
            });
                       
        } catch (Exception e) {
        }
          if(villecombo.getSelectionModel().getSelectedIndex()!=-1)
          {
              
          listeClient.clear();
          if(villecombo.getSelectionModel().getSelectedItem().equals("Tous"))
          {
          listeClient.addAll(clientdao.findAll());
          
          }
          else
          {
          Ville ville=mapVille.get(villecombo.getSelectionModel().getSelectedItem());
          listeClient.addAll(clientdao.findClientByVille(ville));
          
          }
          }
        
           if(secteurcombo.getItems().size()!=0)
            {
                secteurcombo.getItems().add(0,"Tous");
            }
           
           if(listeClient.isEmpty())
           {
               btnImprimer.setDisable(true);
           }else
           {
            btnImprimer.setDisable(false);
           }
           
    }

    @FXML
    private void selectionnersecteur(ActionEvent event) {
        if(secteurcombo.getSelectionModel().getSelectedIndex()!=-1)
        {
            listeClient.clear();
             Ville ville=mapVille.get(villecombo.getSelectionModel().getSelectedItem());
            if(secteurcombo.getSelectionModel().getSelectedItem().equals("Tous"))
            {
            listeClient.addAll(clientdao.findClientByVille(ville));
            }else
            {
                       
             Secteur secteur=mapSecteur.get(secteurcombo.getSelectionModel().getSelectedItem());
           
            listeClient.addAll(clientdao.findClientBySecteur(ville, secteur));
            }
            
             if(listeClient.isEmpty())
           {
               btnImprimer.setDisable(true);
           }else
           {
            btnImprimer.setDisable(false);
           }
           
        }

    }


    @FXML
    private void rafraichirOnAction(ActionEvent event) {
    }

    @FXML
    private void imprimerOnAction(ActionEvent event) {
          listeVendeur.clear();
       listeVendeur.addAll(vendeurdao.findBySecteur(secteurcombo.getSelectionModel().getSelectedItem()));
       
         try {
            HashMap para = new HashMap();
          
             para.put("ville", villecombo.getSelectionModel().getSelectedItem());
             
           if(listeVendeur.size()>0)
           {
             if(listeVendeur.size()>=5)
             {
             for(int i=0;i<5;i++)
             {
             para.put(""+i+"", listeVendeur.get(i).getNom()+ " - ");
             }
             
             }else if(listeVendeur.size()<5)
             {
             for(int i=0;i<listeVendeur.size();i++)
             {
              para.put(""+i+"",listeVendeur.get(i).getNom() + " - ");
             
             }
             for(int j=listeVendeur.size();j<5;j++)
             {
              para.put(""+j+""," ");
             
             }
             
             }
             
           }
           else if(listeVendeur.isEmpty()){ for(int i=0;i<5;i++)
             {
             para.put(""+i+""," ");
             }}
            JasperReport report = (JasperReport) JRLoader.loadObject(ListeClientController.class.getResource("/iReport/ReportListClient/ListeClient.jasper"));
            JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(clientTable.getItems(),false));
            JasperViewer.viewReport(jp, false);
            
                    } catch (JRException ex) {
            Logger.getLogger(ListeClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
