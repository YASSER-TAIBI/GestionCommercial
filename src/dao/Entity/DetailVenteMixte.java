/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.CascadeType;
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

/**
 *
 * @author Hp
 */
@Entity
@Table(name ="Detail_Vente_Mixte")
@NamedQuery(name = "DetailVenteMixte.findAll", query = "SELECT c FROM DetailVenteMixte c")
public class DetailVenteMixte implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    
     @ManyToOne
    @JoinColumn(name = "ID_ARTICLE")
    private Article article;
    
    @Column(name = "QUANTITE")
    private BigDecimal quantite= BigDecimal.ZERO;
   
    @Column(name = "TOTAL_VENDUE") 
    private BigDecimal totalVendue = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "ID_TYPE_VENTE") 
    private TypeVente typeVente;
    
    @Column(name="DATE_CREATION")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCreation;
     
    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;

     @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="ID_VENTE_MIXTE")
    private VenteMixte venteMixte;
    
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

    public BigDecimal getQuantite() {
        return quantite;
    }

    public VenteMixte getVenteMixte() {
        return venteMixte;
    }

    public void setVenteMixte(VenteMixte venteMixte) {
        this.venteMixte = venteMixte;
    }

    public void setQuantite(BigDecimal quantite) {
        this.quantite= quantite;
    }

    public BigDecimal getTotalVendue() {
        return totalVendue;
    }

    public void setTotalVendue(BigDecimal totalVendue) {
        this.totalVendue = totalVendue;
    }

    public TypeVente getTypeVente() {
        return typeVente;
    }

    public void setTypeVente(TypeVente typeVente) {
        this.typeVente = typeVente;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Utilisateur getUtilisateurCreation() {
        return utilisateurCreation;
    }

    public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
        this.utilisateurCreation = utilisateurCreation;
    }
//         
//          public BigDecimal getPrixG() {
//        BigDecimal result = (getQuantite().multiply(BigDecimal.valueOf(getArticle().getPrixGros())));
//         return result ;
//    }
//          
//                    public BigDecimal getPrixD() {
//        BigDecimal result = (getQuantite().multiply(BigDecimal.valueOf(getArticle().getPrixDetail())));
//         return result ;
//    }
    
}
