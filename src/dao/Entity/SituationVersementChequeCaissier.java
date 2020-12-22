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

public class SituationVersementChequeCaissier implements Serializable {

 
    private String vendeur;
    private BigDecimal montantCheque;
    private BigDecimal montantVersementCheque;
    private BigDecimal ecart;

    public String getVendeur() {
        return vendeur;
    }

    public void setVendeur(String vendeur) {
        this.vendeur = vendeur;
    }

    public BigDecimal getMontantCheque() {
        return montantCheque;
    }

    public void setMontantCheque(BigDecimal montantCheque) {
        this.montantCheque = montantCheque;
    }

    public BigDecimal getMontantVersementCheque() {
        return montantVersementCheque;
    }

    public void setMontantVersementCheque(BigDecimal montantVersementCheque) {
        this.montantVersementCheque = montantVersementCheque;
    }

    public BigDecimal getEcart() {
        return ecart;
    }

    public void setEcart(BigDecimal ecart) {
        this.ecart = ecart;
    }

    
    

    
  
   

 
}
