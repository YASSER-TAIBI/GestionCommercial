/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author pc
 */
@Entity
@Table(name = "Versement_Cheque_Client")
@NamedQuery(name = "VersementChequeClient.findAll", query = "SELECT c FROM VersementChequeClient c")
public class VersementChequeClient implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    
    @Column(name = "NUMCHEQUE")
    private String numCheque;
    
    @Column(name = "MONTANT")
    private BigDecimal montant;
    
    @Column(name = "NOM_PROPRIETAIRE")
    private String nomProprietaire;
    
    @Column(name = "TYPE_CHEQUE")
    private String typeCheque;
 
    @Column(name="DATE_ECHEANCE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEcheance;
    
    @Column(name="DATE_RECU")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateRecu;
    
    @ManyToOne
    @JoinColumn(name="ID_COMPTE_VERSEMENT")
     private CompteVersement compteVersement;
    
    @ManyToOne
    @JoinColumn(name="ID_BANQUE")
     private Banque banque;
    
    @ManyToOne
    @JoinColumn(name = "ID_VERSEMENT_CLIENT")
    private VersementClient versementClient;
    
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

    
    
    
    
    public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public String getNomProprietaire() {
        return nomProprietaire;
    }

    public void setNomProprietaire(String nomProprietaire) {
        this.nomProprietaire = nomProprietaire;
    }
    
    public String getTypeCheque() {
        return typeCheque;
    }

    public void setTypeCheque(String typeCheque) {
        this.typeCheque = typeCheque;
    }

    public VersementClient getVersementClient() {
        return versementClient;
    }

    public void setVersementClient(VersementClient versementClient) {
        this.versementClient = versementClient;
    }

    public Date getDateRecu() {
        return dateRecu;
    }

    public void setDateRecu(Date dateRecu) {
        this.dateRecu = dateRecu;
    }

    public CompteVersement getCompteVersement() {
        return compteVersement;
    }

    public void setCompteVersement(CompteVersement compteVersement) {
        this.compteVersement = compteVersement;
    }
    
    public Date getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(Date dateEcheance) {
        this.dateEcheance = dateEcheance;
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

    public Date getDateCreation() {
        return dateCreation;
    }

    public boolean isAction() {
        return action;
    }

    public void setAction(boolean action) {
        this.action = action;
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

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Banque getBanque() {
        return banque;
    }

    public void setBanque(Banque banque) {
        this.banque = banque;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumCheque() {
        return numCheque;
    }

    public void setNumCheque(String numCheque) {
        this.numCheque = numCheque;
    }
    
    
    
  
}
