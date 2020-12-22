/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @author Hp
 */
@Entity
@Table(name ="DetailAvanceChauffeur")
@NamedQuery(name = "DetailAvanceChauffeur.findAll", query = "SELECT c FROM DetailAvanceChauffeur c")
public class DetailAvanceChauffeur implements Serializable{
    
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @ManyToOne
    @JoinColumn(name="ID_ARTICLE")
    private Article article;
    
    @Column(name = "QUANTITE_MANQUE")
    private BigDecimal quantiteManque;
    
    @Column(name = "QUANTITE_TRANSFERT")
    private BigDecimal quantiteTransfert;
    
    @Column(name = "QUANTITE_TRANSFERT_RECU")
    private BigDecimal quantiteTransfertRecu;
    
    @Column(name = "PRIX")
    private BigDecimal prix;
    
    @Column(name = "MONTANT")
    private BigDecimal montant;
    
    @ManyToOne
    @Cascade({CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "ID_AVANCE_CHAUFFEUR")
    private AvanceChauffeur avanceChauffeur;
    
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

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public BigDecimal getQuantiteManque() {
        return quantiteManque;
    }

    public void setQuantiteManque(BigDecimal quantiteManque) {
        this.quantiteManque = quantiteManque;
    }

    public BigDecimal getQuantiteTransfert() {
        return quantiteTransfert;
    }

    public void setQuantiteTransfert(BigDecimal quantiteTransfert) {
        this.quantiteTransfert = quantiteTransfert;
    }

    public BigDecimal getQuantiteTransfertRecu() {
        return quantiteTransfertRecu;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public void setQuantiteTransfertRecu(BigDecimal quantiteTransfertRecu) {
        this.quantiteTransfertRecu = quantiteTransfertRecu;
    }

    public AvanceChauffeur getAvanceChauffeur() {
        return avanceChauffeur;
    }

    public void setAvanceChauffeur(AvanceChauffeur avanceChauffeur) {
        this.avanceChauffeur = avanceChauffeur;
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

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
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
