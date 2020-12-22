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
@Table(name ="Versement_Bancaire_Client")
@NamedQuery(name = "VersementBancaireClient.findAll", query = "SELECT c FROM VersementBancaireClient c")
public class VersementBancaireClient implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
     
    @Column(name = "CODE_PROPRIETAIRE")
    private String codeProprietaire;
    
    @Column(name = "NOM_PROPRIETAIRE")
    private String nomProprietaire;
    
    @Column(name = "NUM_VERSEMENT")
    private String numVersement;
     
     @ManyToOne
    @JoinColumn(name="ID_COMPTE_VERSEMENT")
     private CompteVersement compteVersement;
    
    @Column(name="DATE_VERSEMENT")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateVersement;  
     
    @Column(name = "MONTANT")
    private BigDecimal montant; 
    
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
     
    
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeProprietaire() {
        return codeProprietaire;
    }

    public void setCodeProprietaire(String codeProprietaire) {
        this.codeProprietaire = codeProprietaire;
    }

    public String getNomProprietaire() {
        return nomProprietaire;
    }

    public void setNomProprietaire(String nomProprietaire) {
        this.nomProprietaire = nomProprietaire;
    }

    public VersementClient getVersementClient() {
        return versementClient;
    }

    public void setVersementClient(VersementClient versementClient) {
        this.versementClient = versementClient;
    }

    public CompteVersement getCompteVersement() {
        return compteVersement;
    }

    public void setCompteVersement(CompteVersement compteVersement) {
        this.compteVersement = compteVersement;
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

    public String getNumVersement() {
        return numVersement;
    }

    public void setNumVersement(String numVersement) {
        this.numVersement = numVersement;
    }

    public Date getDateVersement() {
        return dateVersement;
    }

    public void setDateVersement(Date dateVersement) {
        this.dateVersement = dateVersement;
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

   

   

   
    
    
    
    
}
