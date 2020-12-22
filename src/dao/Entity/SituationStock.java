/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.persistence.Access;
import javax.persistence.AccessType;
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
import javax.persistence.Transient;

/**
 *
 * @author Hp
 */

public class SituationStock implements Serializable {

 
    private String code;
    private String article;
    private BigDecimal initial;
    private BigDecimal reception;
    private BigDecimal charge;
    private BigDecimal chargeSupp;
    private BigDecimal retour;
    private BigDecimal transfertSortie;
    private BigDecimal transfertEntree;
    private BigDecimal sorties;
    private BigDecimal Final;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }
    
    
    

    
    
    
    
    



    public BigDecimal getInitial() {
        return initial;
    }

    public void setInitial(BigDecimal initial) {
        this.initial = initial;
    }

    public BigDecimal getReception() {
        return reception;
    }

    public void setReception(BigDecimal reception) {
        this.reception = reception;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }

    public BigDecimal getChargeSupp() {
        return chargeSupp;
    }

    public void setChargeSupp(BigDecimal chargeSupp) {
        this.chargeSupp = chargeSupp;
    }

    public BigDecimal getRetour() {
        return retour;
    }

    public void setRetour(BigDecimal retour) {
        this.retour = retour;
    }

    public BigDecimal getTransfertSortie() {
        return transfertSortie;
    }

    public void setTransfertSortie(BigDecimal transfertSortie) {
        this.transfertSortie = transfertSortie;
    }

    public BigDecimal getTransfertEntree() {
        return transfertEntree;
    }

    public void setTransfertEntree(BigDecimal transfertEntree) {
        this.transfertEntree = transfertEntree;
    }

    public BigDecimal getSorties() {
        return sorties;
    }

    public void setSorties(BigDecimal sorties) {
        this.sorties = sorties;
    }

  

    public BigDecimal getFinal() {
        return Final;
    }

    public void setFinal(BigDecimal Final) {
        this.Final = Final;
    }
    
   

 
}
