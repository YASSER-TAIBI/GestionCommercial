package function;


import Controller.LoginAppController;
import dao.Entity.Utilisateur;
import java.math.BigDecimal;
import java.math.MathContext;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class navigation {

    private final String loadApp="/View/LoadApp.fxml";
    private final String login="/View/LoginApp.fxml";
    private final String home="/View/Home.fxml";
    
    
    private final String user="/View/User.fxml";
    private final String article="/View/Article/ListeArticle.fxml";
    private final String client="/View/Referentiel/ClientPF.fxml";
        
    private final String depot="/View/Referentiel/Depot.fxml";
    private final String listeville="/View/Referentiel/ListeVille.fxml";
    private final String magasin="/View/Referentiel/Magasin.fxml";
    private final String secteur="/View/Referentiel/Secteur.fxml";
    private final String vendeur="/View/Referentiel/Vendeur.fxml";
    private final String listeclient="/View/Referentiel/ListeClient.fxml";
    private final String Banque="/View/Referentiel/Banque.fxml";
    private final String Cheque="/View/Referentiel/Cheque.fxml";
    private final String Vehicule="/View/Referentiel/Vehicule.fxml";
    private final String TypeVenteMixte="/View/Vente/TypeVenteMixte.fxml";
    private final String chargement="/View/Vente/Chargement.fxml";
    private final String suiviVentes="/View/Vente/SuiviVentes.fxml";
    private final String detailMontant="/View/Vente/DetailMontant.fxml";
  
     
     //iReport
     private final String iReportSituationStock="/iReport/ReportMouvementStock/MouvementStockGlobale.jasper";
     private final String iReportTransfertCheque="/iReport/ReportTransfertCheque/TransfertCheque.jasper";
     private final String iReportStockEnAttente="/iReport/ReportStockEnAttente/StockEnAttente.jasper";
     
     public Utilisateur utilisateur;

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getLoadApp() {
        return loadApp;
    }

    public String getListeclient() {
        return listeclient;
    }

    public String getiReportSituationStock() {
        return iReportSituationStock;
    }

    public String getTypeVenteMixte() {
        return TypeVenteMixte;
    }

    public String getiReportStockEnAttente() {
        return iReportStockEnAttente;
    }

    public String getChargement() {
        return chargement;
    }

    public String getBanque() {
        return Banque;
    }

    public String getSuiviVentes() {
        return suiviVentes;
    }

    public String getiReportTransfertCheque() {
        return iReportTransfertCheque;
    }

   

    public String getLogin() {
        return login;
    }

    public String getHome() {
        return home;
    }

    public String getUser() {
        return user;
    }

    public String getArticle() {
        return article;
    }

    public String getClient() {
        return client;
    }

    public String getDepot() {
        return depot;
    }

    public String getListeville() {
        return listeville;
    }

    public String getMagasin() {
        return magasin;
    }

    public String getDetailMontant() {
        return detailMontant;
    }

    public String getSecteur() {
        return secteur;
    }

    public String getVendeur() {
        return vendeur;
    }

    public String getCheque() {
        return Cheque;
    }

    public String getVehicule() {
        return Vehicule;
    }

     
       public BigDecimal getFinal(BigDecimal initial , BigDecimal charge, BigDecimal chargSup, BigDecimal retour, BigDecimal vendu ){
       
       BigDecimal result = initial.add(charge).add(chargSup).subtract(retour).subtract(vendu);
       
       return result;
       
       } 

    public navigation() {
        this.utilisateur = LoginAppController.utilisateur;
    }

    public String generCode(String b , int a){
 
      String code = b+a;
      return code;
  }
    
    public void showAlert(AlertType type, String title, String header, String text){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();
    }
        
    public void harusAngka(final TextField text){
        text.setOnKeyReleased(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                if (!text.getText().matches("[0-9]*")){
                    showAlert(AlertType.WARNING, "Peringatan", null, "Hanya boleh angka !!");
                    text.setText("");
                    text.requestFocus();
                }
            }
        });
        
    }
            
    public void animationFade(Node e){
        FadeTransition x = new FadeTransition(new Duration(1000), e);
        x.setFromValue(0);
        x.setToValue(100);
        x.setCycleCount(1);
        x.setInterpolator(Interpolator.LINEAR);
        x.play();
    }

    private void Code() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
