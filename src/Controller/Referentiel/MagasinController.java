/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import com.lowagie.text.Row;
import dao.Entity.Depot;
import dao.Entity.Magasin;
import dao.Entity.TypeMagasin;
import dao.Entity.Ville;
import dao.Manager.DepotDAO;
import dao.Manager.MagasinDAO;
import dao.Manager.TypeMagasinDAO;
import dao.Manager.VilleDAO;
import dao.ManagerImpl.DepotDAOImpl;
import dao.ManagerImpl.MagasinDAOImpl;
import dao.ManagerImpl.TypeMagasinDAOImpl;
import dao.ManagerImpl.VilleDAOImpl;
import function.navigation;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.oasis.CellStyle;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import org.hibernate.hql.internal.antlr.HqlSqlTokenTypes;

public class MagasinController implements Initializable {

    MagasinDAO magasindao = new MagasinDAOImpl();
    navigation nav = new navigation();
    TypeMagasinDAO typeMagasindao = new TypeMagasinDAOImpl();
    DepotDAO  depotDAO = new DepotDAOImpl();

    Magasin magasin;
    
    @FXML
    private TableView<Magasin> magasintable;
    @FXML
    private TableColumn<Magasin, String> codeColumn;
    @FXML
    private TableColumn<Magasin, String> libelleColumn;
    @FXML
    private TableColumn<Magasin, String> magasinColumn;
     @FXML
    private TableColumn<Magasin, String> depotColumn;
    
    @FXML
    private TextField codeField;
    @FXML
    private TextField libelleField;

    @FXML
    private Label codeerreur;
    @FXML
    private Label libelleerreur;

    @FXML
    private Button btnAjouter1;
    @FXML
    private Button btnRafraichir;
    @FXML
    private Button btnSupprimer1;
    @FXML
    private Button btnModifer;
    @FXML
    private Button btnImprimer;
    @FXML
    private Button btnExcel;
    @FXML
    private ComboBox<String> typecombo;
    @FXML
    private Label typeerreur;

    
    private final ObservableList<Magasin> listeMagasin = FXCollections.observableArrayList();
    private Map<String, TypeMagasin> maptypemagasin = new HashMap<>();
    private Map<String, Depot> mapDepot = new HashMap<>();
    
    boolean msgerrue = false;

    Workbook workbook = new HSSFWorkbook();
    
  
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        charger();
        loadDetail();
        setColumnProperties();

    }

    public void charger() {

        List<TypeMagasin> listTypeMagasin = typeMagasindao.findAll();

        listTypeMagasin.stream().map((TypeMagasin) -> {
            typecombo.getItems().addAll(TypeMagasin.getLibelle());
            return TypeMagasin;
        }).forEachOrdered((typemagasin) -> {
            maptypemagasin.put(typemagasin.getLibelle(), typemagasin);
        });

    }

    void loadDetail() {

        listeMagasin.clear();
        listeMagasin.addAll(magasindao.findAll());
        magasintable.setItems(listeMagasin);
    }

    void setColumnProperties() {

        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        libelleColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        magasinColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Magasin, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Magasin, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getTypeMagasin().getLibelle());
            }
        });
         depotColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Magasin, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Magasin, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDepot().getLibelle1());
            }
        });
    }


    void verifier() {
        if (codeField.getText().equals(Constantes.CHAMP_VIDE)) {
            codeerreur.setText(Constantes.CHAMP_ETOILE);
            codeField.requestFocus();
            msgerrue = true;

        } else {
            codeerreur.setText(Constantes.CHAMP_VIDE);

        }
        if (libelleField.getText().equals(Constantes.CHAMP_VIDE)) {
            libelleerreur.setText(Constantes.CHAMP_ETOILE);
            libelleField.requestFocus();
            msgerrue = true;

        } else {
            libelleerreur.setText(Constantes.CHAMP_VIDE);

        }

        if (typecombo.getSelectionModel().getSelectedIndex() == -1) {
            typeerreur.setText(Constantes.CHAMP_ETOILE);
            typecombo.requestFocus();
            msgerrue = true;
        }else {
            typeerreur.setText(Constantes.CHAMP_ETOILE);

        }

    }

      void vider_erreur() {
        codeerreur.setText(Constantes.CHAMP_VIDE);
        libelleerreur.setText(Constantes.CHAMP_VIDE);
        typeerreur.setText(Constantes.CHAMP_VIDE);
    }


    private void exporter_Vers_Excel(ActionEvent event) throws FileNotFoundException, IOException {

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet("simple");

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 13);
        font.setBold(true);
        font.setColor((short) HSSFColor.GREEN.index);

        Font font1 = workbook.createFont();
        font1.setFontHeightInPoints((short) 10);
        font1.setBold(false);
        font1.setColor((short) HSSFColor.BLACK.index);

        for (int j = 0; j < magasintable.getColumns().size(); j++) {
            row.createCell(j).setCellValue(magasintable.getColumns().get(j).getText());
   

        }

        for (int i = 0; i < magasintable.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < magasintable.getColumns().size(); j++) {
                if (magasintable.getColumns().get(j).getCellData(i) != null) {
                    row.createCell(j).setCellValue(magasintable.getColumns().get(j).getCellData(i).toString());
   

                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream("workbook.xls")) {

            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File("workbook.xls"));

        }

    }


    private void imprimer() {
        try {
            HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(MagasinController.class.getResource("/iReport/ReportMagasin/Magasin.jasper"));
            JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeMagasin));
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            Logger.getLogger(MagasinController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void afficherArt(MouseEvent event) {
          Integer r = magasintable.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            return;
        } else {
            codeField.setText(codeColumn.getCellData(r));
            libelleField.setText(libelleColumn.getCellData(r));

            typecombo.setValue(magasinColumn.getCellData(r));
            vider_erreur();
            btnAjouter1.setDisable(true);

        }

    }


    @FXML
    private void ajouterOnAction(ActionEvent event) {
        
                msgerrue = false;
        verifier();
        if (msgerrue == true) {
            return;
        } else {

            Magasin mag = new Magasin();
            mag.setCode(codeField.getText());
            mag.setLibelle(libelleField.getText());
            TypeMagasin typemagasin = maptypemagasin.get(typecombo.getSelectionModel().getSelectedItem());
            mag.setTypeMagasin(typemagasin);
            mag.setDepot(nav.getUtilisateur().getDepot());
            magasindao.add(mag);
            nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_AJOUT);
            setColumnProperties();
            loadDetail();
            rafraichirOnAction(event);

        }
        
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {
        
            codeField.setText(Constantes.CHAMP_VIDE);
        libelleField.setText(Constantes.CHAMP_VIDE);

        typecombo.getSelectionModel().select(-1);
        vider_erreur();

        btnAjouter1.setDisable(false);
        loadDetail();
        setColumnProperties();
    }

    @FXML
    private void supprimerOnAction(ActionEvent event) {
        
          Integer r = magasintable.getSelectionModel().getSelectedIndex();
        if (r <= -1) {
            nav.showAlert(Alert.AlertType.WARNING, Constantes.TYPE_ALERT_INFORMATION, null, Constantes.MESSAGE_ALERT_SELECTIONNER);

        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_SUPPRIMER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();


            if (result.get() == ButtonType.OK) {
                Magasin mag = magasintable.getSelectionModel().getSelectedItem();
                magasindao.delete(mag);
                nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_SUPPRIMER);
                setColumnProperties();
                loadDetail();
                rafraichirOnAction(event);
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
            Integer r = magasintable.getSelectionModel().getSelectedIndex();
            if (r <= -1) {
                nav.showAlert(Alert.AlertType.CONFIRMATION, Constantes.TYPE_ALERT_INFORMATION, null, Constantes.MESSAGE_ALERT_SELECTIONNER);

            } else {

                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_MODIFICATION);
                alert.setTitle("Confirmation");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    Magasin mag = magasintable.getSelectionModel().getSelectedItem();
                    mag.setCode(codeField.getText());
                    mag.setLibelle(libelleField.getText());

                    TypeMagasin typemagasin = maptypemagasin.get(typecombo.getSelectionModel().getSelectedItem());
                    mag.setTypeMagasin(typemagasin);

                    mag.setDepot(nav.getUtilisateur().getDepot());
                    
                    nav.showAlert(Alert.AlertType.INFORMATION, Constantes.TYPE_ALERT_SUCCEE, null, Constantes.MESSAGE_ALERT_MODIFICATION);
                    magasindao.edit(mag);
  
                    setColumnProperties();
                    loadDetail();
                    rafraichirOnAction(event);
                }

            }

        }
        
    }

    @FXML
    private void imprimerOnAction(ActionEvent event) {
         imprimer();
    }

    @FXML
    private void excelOnAction(ActionEvent event) throws IOException {
          exporter_Vers_Excel(event);
    }

}
