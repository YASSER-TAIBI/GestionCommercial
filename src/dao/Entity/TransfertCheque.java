/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Entity;

import java.io.Serializable;
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
@Table(name ="TransfertCheque")
@NamedQuery(name = "TransfertCheque.findAll", query = "SELECT c FROM TransfertCheque c")
public class TransfertCheque  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    
    @Column(name="CODE_TRANSFERT_CHEQUE")
    private String codeTransfertCheque;
    
    @Column(name="DATE_TRANSFERT_CHEQUE")
    @Temporal(javax.persistence.TemporalType.DATE)
     private Date dateTransfCheque;
    
     @ManyToOne
    @JoinColumn(name = "ID_OPERATEUR_SOURCE")
    private Vendeur vendeurSource ;
   
     @ManyToOne
    @JoinColumn(name = "ID_DEPOT_SOURCE")
    private Depot depotSource ;
    
    @ManyToOne
    @JoinColumn(name = "ID_MAGASIN_SOURCE")
    private Magasin magasinSource;
    
    @ManyToOne
    @JoinColumn(name = "ID_OPERATEUR_DESTINATION")
    private Vendeur vendeurDestination ;
    
     @ManyToOne
    @JoinColumn(name = "ID_DEPOT_DESTINATION")
    private Depot depotDestination ;
    
    @ManyToOne
    @JoinColumn(name = "ID_MAGASIN_DESTINATION")
    private Magasin magasinDestination;
    
    @ManyToOne
    @JoinColumn(name = "ID_CHAUFFEUR")
    private Chauffeur chauffeur;
    
    @ManyToOne
    @JoinColumn(name = "ID_VEHICULE")
    private Vehicule vehicule;
    
      @Column(name="DESIGNATION")
    private String designation;
    
    @Column(name="ETAT")
    private String etat;
    
     @Column(name="DATE_RECEPTION_CHEQUE")
    @Temporal(javax.persistence.TemporalType.DATE)
     private Date dateReceptionCheque;

    @OneToMany(mappedBy="transfertCheque",cascade={CascadeType.ALL},fetch = FetchType.EAGER)
    private List<DetailTransfertCheque> detailTransfertCheque = new ArrayList<>();
    
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

    public String getCodeTransfertCheque() {
        return codeTransfertCheque;
    }

    public void setCodeTransfertCheque(String codeTransfertCheque) {
        this.codeTransfertCheque = codeTransfertCheque;
    }

    public Date getDateTransfCheque() {
        return dateTransfCheque;
    }

    public void setDateTransfCheque(Date dateTransfCheque) {
        this.dateTransfCheque = dateTransfCheque;
    }

    public Depot getDepotSource() {
        return depotSource;
    }

    public Vendeur getVendeurSource() {
        return vendeurSource;
    }

    public void setVendeurSource(Vendeur vendeurSource) {
        this.vendeurSource = vendeurSource;
    }

    public Vendeur getVendeurDestination() {
        return vendeurDestination;
    }

    public void setVendeurDestination(Vendeur vendeurDestination) {
        this.vendeurDestination = vendeurDestination;
    }

    public void setDepotSource(Depot depotSource) {
        this.depotSource = depotSource;
    }

    public Magasin getMagasinSource() {
        return magasinSource;
    }

    public void setMagasinSource(Magasin magasinSource) {
        this.magasinSource = magasinSource;
    }

    public Depot getDepotDestination() {
        return depotDestination;
    }

    public void setDepotDestination(Depot depotDestination) {
        this.depotDestination = depotDestination;
    }

    public Magasin getMagasinDestination() {
        return magasinDestination;
    }

    public void setMagasinDestination(Magasin magasinDestination) {
        this.magasinDestination = magasinDestination;
    }

    public Chauffeur getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(Chauffeur chauffeur) {
        this.chauffeur = chauffeur;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDateReceptionCheque() {
        return dateReceptionCheque;
    }

    public void setDateReceptionCheque(Date dateReceptionCheque) {
        this.dateReceptionCheque = dateReceptionCheque;
    }

    public List<DetailTransfertCheque> getDetailTransfertCheque() {
        return detailTransfertCheque;
    }

    public void setDetailTransfertCheque(List<DetailTransfertCheque> detailTransfertCheque) {
        this.detailTransfertCheque = detailTransfertCheque;
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
