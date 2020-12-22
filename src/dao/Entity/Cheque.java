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
@Table(name = "Cheque")
@NamedQuery(name = "Cheque.findAll", query = "SELECT c FROM Cheque c")
public class Cheque implements Serializable{
    
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    
    @Column(name = "NUMCHEQUE")
    private String num_cheque;
    
    @Column(name = "MONTANT")
    private BigDecimal montant;
    
    @Column(name = "NOM_PROPRIETAIRE")
    private String nom_Proprietaire;
   
    @Column(name = "ETAT")
    private String etat;
    
     @Column(name = "TYPE_CHEQUE")
    private String typeCheque;
    
    @Column(name="DATE_ECHEANCE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEcheance;
    
    @ManyToOne
    @JoinColumn(name="ID_BANQUE")
     private Banque banque;
    
    @ManyToOne
    @JoinColumn(name="ID_VENDEUR")
    private Vendeur vendeur;
    
    @ManyToOne
    @JoinColumn(name="id_Secteur")
    private Secteur secteur;
     
    @ManyToOne
    @JoinColumn(name="ID_CLIENT")
     private ClientPF Client;
    
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
    
    @ManyToOne
    @JoinColumn(name="ID_VILLE")
     private Ville ville;
    
    @ManyToOne
    @JoinColumn(name = "ID_DETAIL_COMPTE")
    private DetailCompte detailCompte;

    
    
    
    
    public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public String getNom_Proprietaire() {
        return nom_Proprietaire;
    }

    public void setNom_Proprietaire(String nom_Proprietaire) {
        this.nom_Proprietaire = nom_Proprietaire;
    }
    
    public String getTypeCheque() {
        return typeCheque;
    }

    public boolean isAction() {
        return action;
    }

    public void setAction(boolean action) {
        this.action = action;
    }

    public Vendeur getVendeur() {
        return vendeur;
    }

    public void setVendeur(Vendeur vendeur) {
        this.vendeur = vendeur;
    }

    public void setTypeCheque(String typeCheque) {
        this.typeCheque = typeCheque;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public DetailCompte getDetailCompte() {
        return detailCompte;
    }

    public void setDetailCompte(DetailCompte detailCompte) {
        this.detailCompte = detailCompte;
    }

    public Date getDateEcheance() {
        return dateEcheance;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public void setDateEcheance(Date dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public Secteur getSecteur() {
        return secteur;
    }

    public void setSecteur(Secteur secteur) {
        this.secteur = secteur;
    }

    public ClientPF getClient() {
        return Client;
    }

    public void setClient(ClientPF Client) {
        this.Client = Client;
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

    public String getNum_cheque() {
        return num_cheque;
    }

    public void setNum_cheque(String num_cheque) {
        this.num_cheque = num_cheque;
    }
    
  
}
