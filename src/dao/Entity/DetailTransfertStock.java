/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.persistence.Access;
import javax.persistence.AccessType;
import org.hibernate.annotations.Cascade;
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
import javax.persistence.Transient;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author Hp
 */
@Entity
@Table(name ="DetailTransfertStock")
@NamedQuery(name = "DetailTransfertStock.findAll", query = "SELECT c FROM DetailTransfertStock c")
public class DetailTransfertStock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "ID_ARTICLE")
    private Article article;
    
    @Column(name = "QUANTITE")
    private BigDecimal quantite= BigDecimal.ZERO.setScale(2);
    
    @Column(name = "QUANTITE_RECU")
    private BigDecimal quantiteRecu = BigDecimal.ZERO.setScale(2);
    
    @Column(name = "QUANTITE_CAISSE")
    private BigDecimal quantiteCaisse = BigDecimal.ZERO.setScale(2);

    @Column(name = "QUANTITE_CAISSE_RECU")
    private BigDecimal quantiteCaisseRecu = BigDecimal.ZERO.setScale(2);
     
    @Column(name = "QUANTITE_TOTAL") 
    private BigDecimal quantiteTotal = BigDecimal.ZERO.setScale(2);
    
    @Column(name = "QUANTITE_TOTAL_RECU") 
    private BigDecimal quantiteTotalRecu = BigDecimal.ZERO.setScale(2);
    
    @Column(name = "MONTANT") 
    private BigDecimal montant = BigDecimal.ZERO.setScale(2);
    
    @Column(name = "QUANTITE_STOCK") 
    private BigDecimal quantiteStock = BigDecimal.ZERO.setScale(2);
    
    @ManyToOne
    @Cascade({CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "ID_TRANSFERT_STOCK")
    private TransfertStock transfertStock;
    
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

    @Column(name="STATUT")
    private String statut;
    
    @ManyToOne
    @JoinColumn(name = "ID_TYPE_VENTE") 
    private TypeVente typeVente;
    
    
    public BigDecimal getQuantite() {
        return quantite;
    }

    public void setQuantite(BigDecimal quantite) {
        this.quantite = quantite;
    }

    public BigDecimal getQuantiteRecu() {
        return quantiteRecu;
    }

    public void setQuantiteRecu(BigDecimal quantiteRecu) {
        this.quantiteRecu = quantiteRecu;
    }

    public BigDecimal getQuantiteCaisse() {
        return quantiteCaisse;
    }

    public void setQuantiteCaisse(BigDecimal quantiteCaisse) {
        this.quantiteCaisse = quantiteCaisse;
    }

    public BigDecimal getQuantiteTotal() {
        return quantiteTotal;
    }

    public void setQuantiteTotal(BigDecimal quantiteTotal) {
        this.quantiteTotal = quantiteTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypeVente getTypeVente() {
        return typeVente;
    }

    public BigDecimal getQuantiteTotalRecu() {
        return quantiteTotalRecu;
    }

    public void setQuantiteTotalRecu(BigDecimal quantiteTotalRecu) {
        this.quantiteTotalRecu = quantiteTotalRecu;
    }

    public void setTypeVente(TypeVente typeVente) {
        this.typeVente = typeVente;
    }

    public String getStatut() {
        return statut;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
    
    
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public BigDecimal getQuantiteStock() {
        return quantiteStock;
    }

    public void setQuantiteStock(BigDecimal quantiteStock) {
        this.quantiteStock = quantiteStock;
    }

    public TransfertStock getTransfertStock() {
        return transfertStock;
    }

    public void setTransfertStock(TransfertStock transfertStock) {
        this.transfertStock = transfertStock;
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

    public BigDecimal getQuantiteCaisseRecu() {
        return quantiteCaisseRecu;
    }

    public void setQuantiteCaisseRecu(BigDecimal quantiteCaisseRecu) {
        this.quantiteCaisseRecu = quantiteCaisseRecu;
    }
    
        @Transient
    public BigDecimal getCalculeEcart() {
        BigDecimal result= getQuantiteTotal().subtract(getQuantiteTotalRecu()).setScale(9, RoundingMode.FLOOR);
         return result;
    }
    
    
}
