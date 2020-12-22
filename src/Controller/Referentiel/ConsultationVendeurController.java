/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.DetailVendeurSecteur;
import dao.Entity.Vendeur;
import dao.Manager.DetailVendeurSecteurDAO;
import dao.Manager.VendeurDAO;
import dao.ManagerImpl.DetailVendeurSecteurDAOImpl;
import dao.ManagerImpl.VendeurDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ConsultationVendeurController implements Initializable {

    @FXML
    private ComboBox<String> vendeurCombo;
    @FXML
    private TableView<DetailVendeurSecteur> tableDetailVendeur;
    @FXML
    private TableColumn<DetailVendeurSecteur, String> codeColumn;
    @FXML
    private TableColumn<DetailVendeurSecteur, String> secteurColumn;
    @FXML
    private TableColumn<DetailVendeurSecteur, String> villeColumn;
    @FXML
    private Button btnRafraichir;
    @FXML
    private TableView<Vendeur> tableVendeur;
    @FXML
    private TableColumn<Vendeur, String> codeVendeurColumn;
    @FXML
    private TableColumn<Vendeur, String> vendeurColumn;
    @FXML
    private TableColumn<Vendeur, String> telColumn;
    @FXML
    private TableColumn<Vendeur, String> compteColumn;
    @FXML
    private TableColumn<Vendeur, String> vehiculeColumn;
    @FXML
    private TableColumn<Vendeur, String> typeVendeurColumn;
    @FXML
    private TableColumn<Vendeur, BigDecimal> montantPlafondColumn;

    /**
     * Initializes the controller class.
     */
    navigation nav = new navigation();
    
    VendeurDAO vendeurdao = new VendeurDAOImpl();
    DetailVendeurSecteurDAO detailVendeurSecteurDAO = new DetailVendeurSecteurDAOImpl();
    
    private Map<String, Vendeur> mapVendeur = new HashMap<>();
    private final ObservableList<DetailVendeurSecteur> listeVendeurSecteur = FXCollections.observableArrayList();
    private final ObservableList<Vendeur> listeVendeur = FXCollections.observableArrayList();
    
    Vendeur vendeur ;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDetail();
        setColumnProperties();
        

              List<Vendeur> listVendeur =  vendeurdao.findByDepotV2(nav.getUtilisateur().getDepot().getId());  
            listVendeur.stream().map((vendeur) -> {
                vendeurCombo.getItems().addAll(vendeur.getNom());
                return vendeur;
            }).forEachOrdered((vendeur) -> {
                mapVendeur.put(vendeur.getNom(), vendeur);
            });
        
              tableVendeur.setEditable(true);
              montantPlafondColumn.setEditable(true);
        
    }    

        void loadDetail() {

        listeVendeur.clear();
        listeVendeur.addAll(vendeurdao.findByDepotV2(nav.getUtilisateur().getDepot().getId()));
        tableVendeur.setItems(listeVendeur);
    }

          void setColumnProperties() {

        codeVendeurColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vendeur, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Vendeur, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getCode());
            }
        });
        
        vendeurColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vendeur, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Vendeur, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNom());
            }
        });

          telColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vendeur, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Vendeur, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getTelephone());
            }
        });
       
          compteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vendeur, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Vendeur, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getCompte().getLibelle());
            }
        });
          
          vehiculeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vendeur, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Vendeur, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getVehicule().getNom());
            }
        });
    
          typeVendeurColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vendeur, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Vendeur, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getTypeVente().getLibelle());
            }
        });
          
//          montantPlafondColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vendeur, BigDecimal>, ObservableValue<BigDecimal>>() {
//            @Override
//            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<Vendeur, BigDecimal> param) {
//                return new ReadOnlyObjectWrapper(param.getValue().getMontantPlafond());
//            }
//        });
          
          montantPlafondColumn.setCellValueFactory(new PropertyValueFactory<Vendeur, BigDecimal>("montantPlafond"));
        setColumnTextFieldConverter(getConverter(),montantPlafondColumn);
          
          }
        
               public static void setColumnTextFieldConverter(StringConverter converter, TableColumn... columns) {

        for (TableColumn tableColumn : columns) {

            tableColumn.setCellFactory(TextFieldTableCell.forTableColumn(converter));

        }
    }

        
    private StringConverter getConverter() {
        StringConverter<BigDecimal> converter = new StringConverter<BigDecimal>() {
            @Override
            public BigDecimal fromString(String string) {

                try {
                    
             BigDecimal valeur;      
             valeur = new BigDecimal(string);
                    return valeur;
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            public String toString(BigDecimal object) {

                if (object == null) {
                    return null;
                }
                return String.valueOf(object);
            }
        };

        return converter;
    }
        
    void setDetailColumnProperties() {

        codeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailVendeurSecteur, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailVendeurSecteur, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getSecteur().getCode());
            }
        });
        villeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailVendeurSecteur, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailVendeurSecteur, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getSecteur().getVille().getLibelle());
            }
        });

        secteurColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailVendeurSecteur, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailVendeurSecteur, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getSecteur().getLibelle());
            }
        });

          
       
    }
    
    
    
    
    @FXML
    private void vendeurOnAction(ActionEvent event) {
        
        listeVendeurSecteur.clear();
        listeVendeur.clear();
        
        Vendeur vendeur  = mapVendeur.get(vendeurCombo.getSelectionModel().getSelectedItem());

        if (vendeur!= null){
        
        listeVendeur.addAll(vendeurdao.findByVandeurAndDepotV2(vendeur.getId(), nav.getUtilisateur().getDepot().getId()));
     
        tableVendeur.setItems(listeVendeur);

        setColumnProperties();
        }
    }

    @FXML
    private void afficherArt(MouseEvent event) {
        
                        setDetailColumnProperties();
        listeVendeurSecteur.clear();
        
if (tableVendeur.getSelectionModel().getSelectedIndex()!=-1){
    
  
    
        vendeur = tableVendeur.getSelectionModel().getSelectedItem();

      listeVendeurSecteur.addAll(detailVendeurSecteurDAO.findDetailVendeurSecteurByVendeur(vendeur.getId()));
     


        tableDetailVendeur.setItems(listeVendeurSecteur);

}
        
    }


    @FXML
    private void rafraichirOnAction(ActionEvent event) {
        
        vendeurCombo.getSelectionModel().select(-1);
        listeVendeurSecteur.clear();
         loadDetail();
        setColumnProperties();
    }

    @FXML
    private void montantPlafondOnEditCommit(TableColumn.CellEditEvent<Vendeur, BigDecimal> event) {
        
           System.out.println("event.getNewValue() "+event.getNewValue());
        
            ((Vendeur) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setMontantPlafond(event.getNewValue());
            
            
            
            
              BigDecimal  montantPlafond = montantPlafondColumn.getCellData(event.getTablePosition().getRow());

              
              if (montantPlafond== null){
                  
                nav.showAlert(Alert.AlertType.ERROR, "Erreur", Constantes.ERREUR , Constantes.ERREUR_SAISIE );
                
              }else{
              
              tableVendeur.refresh();
              Vendeur vendeur = listeVendeur.get(event.getTablePosition().getRow());
              vendeur.setMontantPlafond(montantPlafond);
              listeVendeur.set(event.getTablePosition().getRow(), vendeur);
                
              vendeurdao.edit(vendeur);

              
              }
      
        
        
    }
    
}
