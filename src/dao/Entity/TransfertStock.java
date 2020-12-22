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
import org.hibernate.annotations.Cascade;
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
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author Hp
 */
@Entity
@Table(name ="TransfertStock")
@NamedQuery(name = "TransfertStock.findAll", query = "SELECT c FROM TransfertStock c")
public class TransfertStock  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    
    @Column(name="CODE_TRANSFERT")
    private String codeTransfert;
    
    @Column(name="DATE_TRANSFERT")
    @Temporal(javax.persistence.TemporalType.DATE)
     private Date dateTransf;
    
    @ManyToOne
    @JoinColumn(name = "ID_DEPOT_SOURCE")
    private Depot depotSource ;
    
    @ManyToOne
    @JoinColumn(name = "ID_MAGASIN_SOURCE")
    private Magasin magasinSource;
    
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
    
    @ManyToOne
    @JoinColumn(name = "ID_CLIENT")
    private ClientPF client;
    
    @Column(name = "MONTANT_TOTAL")
    private BigDecimal montantTotal ;
    
    @ManyToOne
    @JoinColumn(name = "ID_MAGASIN_STOCK")
    private Magasin magasinStock;
    
    @Column(name="DESIGNATION")
    private String designation;
    
    @Column(name="STATUT")
    private String statut;
    
    @Column(name="ETAT")
    private String etat;

    @OneToMany(mappedBy="transfertStock")
    @Cascade({CascadeType.SAVE_UPDATE})
    private List<DeclarationReception> declarationReception;
    
    @OneToMany(mappedBy="transfertStock")
    @Cascade({CascadeType.SAVE_UPDATE})
    private List<DetailTransfertStock> detailTransfertStock;
    
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
    
    @Column(name = "ACTION")
    private boolean action;
    
    
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeTransfert() {
        return codeTransfert;
    }

    public void setCodeTransfert(String codeTransfert) {
        this.codeTransfert = codeTransfert;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }

    public List<DeclarationReception> getDeclarationReception() {
        return declarationReception;
    }

    public void setDeclarationReception(List<DeclarationReception> declarationReception) {
        this.declarationReception = declarationReception;
    }

    public boolean isAction() {
        return action;
    }

    public void setAction(boolean action) {
        this.action = action;
    }

    public ClientPF getClient() {
        return client;
    }

    public void setClient(ClientPF client) {
        this.client = client;
    }

    public Magasin getMagasinStock() {
        return magasinStock;
    }

    public void setMagasinStock(Magasin magasinStock) {
        this.magasinStock = magasinStock;
    }

    public Date getDateTransf() {
        return dateTransf;
    }

    public void setDateTransf(Date dateTransf) {
        this.dateTransf = dateTransf;
    }

    public Depot getDepotSource() {
        return depotSource;
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

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
    public List<DetailTransfertStock> getDetailTransfertStock() {
        return detailTransfertStock;
    }

    public void setDetailTransfertStock(List<DetailTransfertStock> detailTransfertStock) {
        this.detailTransfertStock = detailTransfertStock;
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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
    
    
}
