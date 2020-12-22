/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author pc
 */

@Entity
@Table(name = "Declaration_Reception")
@NamedQuery(name = "DeclarationReception.findAll", query = "SELECT c FROM DeclarationReception c")
public class DeclarationReception implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    
      @Column(name = "NUM_DOSSIER")
    private String numDossier;
    
    @Column(name = "ETAT")
    private String etat;
      
    @Column(name = "NUM_CONTENEUR")
    private String numConteneur;
    
    @Column(name = "NUM_FACTURE")
    private String numFacture;
    
     @Column(name="DATE_DECLARATION")
    @Temporal(javax.persistence.TemporalType.DATE)
     private Date dateDeclaration;
    
    @ManyToOne
    @JoinColumn(name="ID_DEPOT")
    private Depot depot;
    
    @ManyToOne
    @JoinColumn(name="ID_MAGASIN")
    private Magasin magasin;
    
    @Column(name = "STE_IMPORTATEUR")
    private String steImportateur;

    @Column(name="DATE_CREATION")
    @Temporal(javax.persistence.TemporalType.DATE)
     private Date dateCreation;
      
    @Column(name="DATE_MAJ")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateMaj;
     
    @Column(name="TYPE_RECEPTION")
    private String typeReception;
    
    @Column(name="NUM_RECEPTION")
    private String numReception;
    
    @Column(name="NUM_BL")
    private String numBL;
    
    @ManyToOne
    @JoinColumn(name = "ID_FOURNISSEUR")
    private Fournisseur fournisseur;
    
    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;
         
    @ManyToOne
    @JoinColumn(name="ID_UTIL_maj")
    private Utilisateur utilisateurMAJ;

    @ManyToOne
    @Cascade({CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "ID_TRANSFERT_STOCK")
    private TransfertStock transfertStock;
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumDossier() {
        return numDossier;
    }

    public void setNumDossier(String numDossier) {
        this.numDossier = numDossier;
    }

    public String getNumConteneur() {
        return numConteneur;
    }

    public void setNumConteneur(String numConteneur) {
        this.numConteneur = numConteneur;
    }

    public String getNumFacture() {
        return numFacture;
    }

    public void setNumFacture(String numFacture) {
        this.numFacture = numFacture;
    }

    public Date getDateDeclaration() {
        return dateDeclaration;
    }

    public void setDateDeclaration(Date dateDeclaration) {
        this.dateDeclaration = dateDeclaration;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public TransfertStock getTransfertStock() {
        return transfertStock;
    }

    public void setTransfertStock(TransfertStock transfertStock) {
        this.transfertStock = transfertStock;
    }

    public String getTypeReception() {
        return typeReception;
    }

    public void setTypeReception(String typeReception) {
        this.typeReception = typeReception;
    }

    public String getNumReception() {
        return numReception;
    }

    public void setNumReception(String numReception) {
        this.numReception = numReception;
    }

    public String getNumBL() {
        return numBL;
    }

    public void setNumBL(String numBL) {
        this.numBL = numBL;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    public String getSteImportateur() {
        return steImportateur;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setSteImportateur(String steImportateur) {
        this.steImportateur = steImportateur;
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
