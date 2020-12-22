/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author pc
 */

@Entity
@Table(name = "article_mp")
@NamedQuery(name = "Article.findAll", query = "SELECT c FROM Article c")
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
      @Column(name = "CODE")
    private String code;
    
    @Column(name = "CODE_FACTURE")
    private String codeFacture;
    
    @Column(name = "LIBELLE")
    private String libelle;
    
    @ManyToOne
    @JoinColumn(name="ID_DETAIL_SOUS_FAMILLE_ARTICLE")
    private DetailSousFamilleArticle detailSousFamilleArticle;
    
    @Column(name = "CONDITIONNEMENT")
    private Integer conditionnement;
  
    @Column(name = "POIDS")
    private Integer poids;
    
    @Column(name = "ACTION")
    private Boolean action;

    
    
    
    
    public Boolean getAction() {
        return action;
    }

    public void setAction(Boolean action) {
        this.action = action;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getConditionnement() {
        return conditionnement;
    }

    public void setConditionnement(Integer conditionnement) {
        this.conditionnement = conditionnement;
    }

    public Integer getPoids() {
        return poids;
    }

    public void setPoids(Integer poids) {
        this.poids = poids;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeFacture() {
        return codeFacture;
    }

    public void setCodeFacture(String codeFacture) {
        this.codeFacture = codeFacture;
    }

    public DetailSousFamilleArticle getDetailSousFamilleArticle() {
        return detailSousFamilleArticle;
    }

    public void setDetailSousFamilleArticle(DetailSousFamilleArticle detailSousFamilleArticle) {
        this.detailSousFamilleArticle = detailSousFamilleArticle;
    }



    
    
}
