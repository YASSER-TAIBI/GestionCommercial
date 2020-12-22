/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "Tournee")
@NamedQuery(name = "Tournee.findAll", query = "SELECT c FROM Tournee c")
public class Tournee implements Serializable {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
      
    @Column(name="DATE_TOURNEE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateTournee;

    @Column(name="DATE_DEBIT")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDebit;
       
    @Column(name="DATE_FIN")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFin;
    
    @ManyToOne
    @JoinColumn(name = "ID_DEPOT")
    private Depot depot ;
    
    @Column(name = "TOTAL_VENDUE") 
    private BigDecimal totalVendue ;
    
    @Column(name = "MONTANT_CREDIT") 
    private BigDecimal montantCredit ;
    
    @ManyToOne
    @JoinColumn(name = "ID_MAGASIN")
    private Magasin magasin;
    
    @ManyToOne
    @JoinColumn(name = "ID_VENDEUR")
    private Vendeur vendeur;
    
    @ManyToOne
    @JoinColumn(name = "ID_SECTEUR")
    private Secteur secteur;
    
    @ManyToOne
    @JoinColumn(name = "ID_VILLE")
    private Ville ville;
    
    @Column(name="DATE_DECHARGEMENT_TOURNEE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDechargement;
    
    @Column(name="CODE_CHARGEMENT")
    private String codeChargement;
    
    @Column(name="CODE_RETOUR")
    private String codeRetour;
    
    @Column(name="CODE_VENT")
    private String codeVent;
     
    @Column(name="DESIGNATION")
    private String designation;
      
    @Column(name="ETAT")
    private String etat;
      
    @Column(name="STATUS")
    private String status;
    
    @OneToMany(mappedBy="tournee",cascade={CascadeType.ALL},fetch = FetchType.EAGER)
    private List<DetailTournee> detailTournee = new ArrayList<DetailTournee>();
    
    @Column(name="DATE_CREATION")
    @Temporal(javax.persistence.TemporalType.DATE)
     private Date dateCreation;
      
    @Column(name="DATE_MAJ")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateMaj;
     
    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;
         
    @ManyToOne
    @JoinColumn(name="ID_UTIL_maj")
    private Utilisateur utilisateurMAJ;

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateTournee() {
        return dateTournee;
    }

    public String getDesignation() {
        return designation;
    }

    public BigDecimal getTotalVendue() {
        return totalVendue;
    }

    public void setTotalVendue(BigDecimal totalVendue) {
        this.totalVendue = totalVendue;
    }

    public Date getDateDechargement() {
        return dateDechargement;
    }

    public Ville getVille() {
        return ville;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public void setDateDechargement(Date dateDechargement) {
        this.dateDechargement = dateDechargement;
    }

    public String getCodeChargement() {
        return codeChargement;
    }

    public void setCodeChargement(String codeChargement) {
        this.codeChargement = codeChargement;
    }

    public BigDecimal getMontantCredit() {
        return montantCredit;
    }

    public Secteur getSecteur() {
        return secteur;
    }

    public void setSecteur(Secteur secteur) {
        this.secteur = secteur;
    }

    public void setMontantCredit(BigDecimal montantCredit) {
        this.montantCredit = montantCredit;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public List<DetailTournee> getDetailTournee() {
        return detailTournee;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setDetailTournee(List<DetailTournee> detailTournee) {
        this.detailTournee = detailTournee;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    public String getCodeRetour() {
        return codeRetour;
    }

    public void setCodeRetour(String codeRetour) {
        this.codeRetour = codeRetour;
    }

    public String getCodeVent() {
        return codeVent;
    }

    public void setCodeVent(String codeVent) {
        this.codeVent = codeVent;
    }

    public void setDateTournee(Date dateTournee) {
        this.dateTournee = dateTournee;
    }

    public Date getDateDebit() {
        return dateDebit;
    }

    public void setDateDebit(Date dateDebit) {
        this.dateDebit = dateDebit;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    public Vendeur getVendeur() {
        return vendeur;
    }

    public void setVendeur(Vendeur vendeur) {
        this.vendeur = vendeur;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateMaj() {
        return dateMaj;
    }

    public void setDateMaj(Date dateMaj) {
        this.dateMaj = dateMaj;
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

    
    
    
    
    
}
