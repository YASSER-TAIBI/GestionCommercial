/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Entity;

import Utils.Constantes;
import dao.Manager.TypeVenteDAO;
import dao.ManagerImpl.TypeVenteDAOImpl;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Hp
 */



@Entity
@Table(name ="Detail_Tournee")
@NamedQuery(name = "DetailTournee.findAll", query = "SELECT c FROM DetailTournee c")
public class DetailTournee implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    
     @ManyToOne
    @JoinColumn(name = "ID_ARTICLE")
    private Article article;
    
     
    //CHARGE CHARGE_SUPP
    @Column(name = "QUANTITE_CHARGE")
    private BigDecimal quantiteCharge= BigDecimal.ZERO.setScale(2);
    
    @Column(name = "QUANTITE_CHARGE_CAISSE")
    private BigDecimal quantiteChargeCaisse= BigDecimal.ZERO.setScale(2);
    
    @Column(name = "QUANTITE_CHARGES_SUPP")
    private BigDecimal quantiteChargeSupp = BigDecimal.ZERO.setScale(2);
    
    @Column(name = "QUANTITE_CHARGES_SUPP_CAISSE")
    private BigDecimal quantiteChargeSuppCaisse = BigDecimal.ZERO.setScale(2);
    
    @Column(name = "QUANTITE_CHARGES_COMPT")
    private BigDecimal quantiteChargeCompt = BigDecimal.ZERO.setScale(2);
    
    @Column(name = "QUANTITE_CHARGES_COMPT_CAISSE")
    private BigDecimal quantiteChargeComptCaisse = BigDecimal.ZERO.setScale(2);
    
    @Column(name = "QUANTITE_TOTAL_CHARGE")
    private BigDecimal quantiteTotalCharge = BigDecimal.ZERO.setScale(2);
    
    
    //INITIAL
    @Column(name = "QUANTITE_INITIAL")
    private BigDecimal quantiteInitial = BigDecimal.ZERO.setScale(2);
    
    @Column(name = "QUANTITE_INITIAL_CAISSE")
    private BigDecimal quantiteInitialCaisse= BigDecimal.ZERO.setScale(2);
    
    @Column(name = "QUANTITE_TOTAL_INITIAL")
    private BigDecimal quantiteTotalInitial = BigDecimal.ZERO.setScale(2);
    
    
    //RETOUR
    @Column(name = "QUANTITE_RETOUR")
    private BigDecimal quantiteRetour = BigDecimal.ZERO.setScale(2);
    
    @Column(name = "QUANTITE_RETOUR_CAISSE")
    private BigDecimal quantiteRetourCaisse= BigDecimal.ZERO.setScale(2);
    
    @Column(name = "QUANTITE_TOTAL_RETOUR")
    private BigDecimal quantiteTotalRetour = BigDecimal.ZERO.setScale(2);
    
    //OFFRE
    @Column(name = "QUANTITE_OFFRE")
    private BigDecimal quantiteOffre = BigDecimal.ZERO.setScale(2);
    
    @Column(name = "QUANTITE_OFFRE_CAISSE")
    private BigDecimal quantiteOffreCaisse= BigDecimal.ZERO.setScale(2);
    
    @Column(name = "QUANTITE_TOTAL_OFFRE")
    private BigDecimal quantiteTotalOffre = BigDecimal.ZERO.setScale(2);
    
    //RECAPE
    @Column(name = "QUANTITE_RECAPE")
    private BigDecimal quantiteRecape = BigDecimal.ZERO.setScale(2);
    
    @Column(name = "QUANTITE_CAISSE_RECAPE")
    private BigDecimal quantiteCaisseRecape = BigDecimal.ZERO.setScale(2);
    
    @Column(name = "QUANTITE_TOTAL_RECAPE")
    private BigDecimal quantiteTotalRecape = BigDecimal.ZERO.setScale(2);
    
    
    //FINAL
    @Column(name = "QUANTITE_FINAL")
    private BigDecimal quantiteFinal = BigDecimal.ZERO.setScale(2);
    
    
    //VENTE
    @Column(name = "QUANTITE_VENTE")
    private BigDecimal quantiteVente = BigDecimal.ZERO.setScale(2);
    
    
    @Column(name = "QUANTITE_VENDUE") 
    private BigDecimal quantiteVendue = BigDecimal.ZERO.setScale(2);
    
    @Column(name = "PRIX_VENDUE") 
    private BigDecimal totalVendue = BigDecimal.ZERO.setScale(2);
    
    @Column(name = "TOTAL_FACTURE") 
    private BigDecimal totalFacture = BigDecimal.ZERO.setScale(2);
    
    @ManyToOne
    @JoinColumn(name = "ID_TYPE_VENTE") 
    private TypeVente typeVente;
    
    @ManyToOne
    @JoinColumn(name = "ID_TOURNEE")
    private Tournee tournee;
    
    @Column(name="DATE_CREATION")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCreation= new Date();
      
    @Column(name="DATE_MAJ")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateMaj;
     
    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;
         
    @ManyToOne
    @JoinColumn(name="ID_UTIL_maj")
    private Utilisateur utilisateurMAJ;
   
     @OneToMany(mappedBy="detailTournee",cascade={CascadeType.ALL},fetch = FetchType.EAGER)
    private List<DetailChargeSupp> detailChargeSupp = new ArrayList<DetailChargeSupp>();
 
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public Tournee getTournee() {
        return tournee;
    }

    public void setTournee(Tournee tournee) {
        this.tournee = tournee;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public TypeVente getTypeVente() {
        return typeVente;
    }

    public void setTypeVente(TypeVente typeVente) {
        this.typeVente = typeVente;
    }

    public BigDecimal getQuantiteTotalCharge() {
        return quantiteTotalCharge;
    }

    public void setQuantiteTotalCharge(BigDecimal quantiteTotalCharge) {
        this.quantiteTotalCharge = quantiteTotalCharge;
    }

    public BigDecimal getQuantiteTotalInitial() {
        return quantiteTotalInitial;
    }

    public BigDecimal getQuantiteRetourCaisse() {
        return quantiteRetourCaisse;
    }

    public void setQuantiteRetourCaisse(BigDecimal quantiteRetourCaisse) {
        this.quantiteRetourCaisse = quantiteRetourCaisse;
    }

    public BigDecimal getQuantiteTotalRetour() {
        return quantiteTotalRetour;
    }

    public void setQuantiteTotalRetour(BigDecimal quantiteTotalRetour) {
        this.quantiteTotalRetour = quantiteTotalRetour;
    }

    public void setQuantiteTotalInitial(BigDecimal quantiteTotalInitial) {
        this.quantiteTotalInitial = quantiteTotalInitial;
    }

    public BigDecimal getQuantiteInitialCaisse() {
        return quantiteInitialCaisse;
    }

    public void setQuantiteInitialCaisse(BigDecimal quantiteInitialCaisse) {
        this.quantiteInitialCaisse = quantiteInitialCaisse;
    }

    public Date getDateMaj() {
        return dateMaj;
    }

    public void setDateMaj(Date dateMaj) {
        this.dateMaj = dateMaj;
    }


    public BigDecimal getQuantiteCaisseRecape() {
        return quantiteCaisseRecape;
    }

    public void setQuantiteCaisseRecape(BigDecimal quantiteCaisseRecape) {
        this.quantiteCaisseRecape = quantiteCaisseRecape;
    }

    public BigDecimal getQuantiteChargeCaisse() {
        return quantiteChargeCaisse;
    }

    public void setQuantiteChargeCaisse(BigDecimal quantiteChargeCaisse) {
        this.quantiteChargeCaisse = quantiteChargeCaisse;
    }

    public BigDecimal getQuantiteChargeSuppCaisse() {
        return quantiteChargeSuppCaisse;
    }

    public void setQuantiteChargeSuppCaisse(BigDecimal quantiteChargeSuppCaisse) {
        this.quantiteChargeSuppCaisse = quantiteChargeSuppCaisse;
    }

    public BigDecimal getQuantiteChargeComptCaisse() {
        return quantiteChargeComptCaisse;
    }

    public void setQuantiteChargeComptCaisse(BigDecimal quantiteChargeComptCaisse) {
        this.quantiteChargeComptCaisse = quantiteChargeComptCaisse;
    }

    public BigDecimal getQuantiteTotalRecape() {
        return quantiteTotalRecape;
    }

    public void setQuantiteTotalRecape(BigDecimal quantiteTotalRecape) {
        this.quantiteTotalRecape = quantiteTotalRecape;
    }

    public List<DetailChargeSupp> getDetailChargeSupp() {
        return detailChargeSupp;
    }

    public void setDetailChargeSupp(List<DetailChargeSupp> detailChargeSupp) {
        this.detailChargeSupp = detailChargeSupp;
    }

    public BigDecimal getQuantiteInitial() {
        return quantiteInitial;
    }

    public BigDecimal getQuantiteRecape() {
        return quantiteRecape;
    }

    public void setQuantiteRecape(BigDecimal quantiteRecape) {
        this.quantiteRecape = quantiteRecape;
    }

    public BigDecimal getQuantiteOffre() {
        return quantiteOffre;
    }

    public void setQuantiteOffre(BigDecimal quantiteOffre) {
        this.quantiteOffre = quantiteOffre;
    }

    public BigDecimal getQuantiteOffreCaisse() {
        return quantiteOffreCaisse;
    }

    public void setQuantiteOffreCaisse(BigDecimal quantiteOffreCaisse) {
        this.quantiteOffreCaisse = quantiteOffreCaisse;
    }

    public BigDecimal getQuantiteTotalOffre() {
        return quantiteTotalOffre;
    }

    public void setQuantiteTotalOffre(BigDecimal quantiteTotalOffre) {
        this.quantiteTotalOffre = quantiteTotalOffre;
    }

    public void setQuantiteInitial(BigDecimal quantiteInitial) {
        this.quantiteInitial = quantiteInitial;
    }

    public BigDecimal getQuantiteFinal() {
        return quantiteFinal;
    }

    public BigDecimal getQuantiteChargeCompt() {
        return quantiteChargeCompt;
    }

    public void setQuantiteChargeCompt(BigDecimal quantiteChargeCompt) {
        this.quantiteChargeCompt = quantiteChargeCompt;
    }

    public void setQuantiteFinal(BigDecimal quantiteFinal) {
        this.quantiteFinal = quantiteFinal;
    }

    public BigDecimal getQuantiteVente() {
        return quantiteVente;
    }

    public void setQuantiteVente(BigDecimal quantiteVente) {
        this.quantiteVente = quantiteVente;
    }

    public BigDecimal getTotalFacture() {
        return totalFacture;
    }

    public void setTotalFacture(BigDecimal totalFacture) {
        this.totalFacture = totalFacture;
    }

    public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
    }

    public Utilisateur getUtilisateurMAJ() {
        return utilisateurMAJ;
    }

    public void setUtilisateurMAJ(Utilisateur utilisateurMAJ) {
        this.utilisateurMAJ = utilisateurMAJ;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public BigDecimal getQuantiteCharge() {
        return quantiteCharge;
    }

    public void setQuantiteCharge(BigDecimal quantiteCharge) {
        this.quantiteCharge = quantiteCharge;
    }

    public BigDecimal getQuantiteChargeSupp() {
        return quantiteChargeSupp;
    }

    public void setQuantiteChargeSupp(BigDecimal quantiteChargeSupp) {
        this.quantiteChargeSupp = quantiteChargeSupp;
    }

    public BigDecimal getQuantiteRetour() {
        return quantiteRetour;
    }

    public void setQuantiteRetour(BigDecimal quantiteRetour) {
        this.quantiteRetour = quantiteRetour;
    }

    public BigDecimal getQuantiteVendue() {
        return quantiteVendue;
    }

    public void setQuantiteVendue(BigDecimal quantiteVendue) {
        this.quantiteVendue = quantiteVendue;
    }

    public BigDecimal getTotalVendue() {
        return totalVendue;
    }

    public void setTotalVendue(BigDecimal totalVendue) {
        this.totalVendue = totalVendue;
    }
    
      
   public BigDecimal qteTotalInitialCharge() {
        BigDecimal result = (getQuantiteTotalCharge().add(getQuantiteTotalInitial()).subtract(getQuantiteTotalRecape())).setScale(9, RoundingMode.FLOOR);
         return result ;
    }

}
