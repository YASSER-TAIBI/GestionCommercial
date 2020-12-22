/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Entity;

import Utils.Constantes;
import dao.Manager.TypeVenteDAO;
import dao.ManagerImpl.TypeVenteDAOImpl;
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
@Table(name ="Detail_Charge_Supp")
@NamedQuery(name = "DetailChargeSupp.findAll", query = "SELECT c FROM DetailChargeSupp c")
public class DetailChargeSupp implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "ID_DETAIL_TOURNEE")
    private DetailTournee detailTournee;
    
    @Column(name="DATE_CREATION")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCreation= new Date();
      
    @Column(name="DATE_MAJ")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateMaj;
     
    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;
         
    @ManyToOne
    @JoinColumn(name="ID_UTIL_maj")
    private Utilisateur utilisateurMAJ;
   
    @Column(name = "QUANTITE_CHARGES_SUPP")
    private BigDecimal quantiteChargeSupp = BigDecimal.ZERO.setScale(2);
    
    @Column(name = "QUANTITE_CHARGES_SUPP_CAISSE")
    private BigDecimal quantiteChargeSuppCaisse = BigDecimal.ZERO.setScale(2);
    
    @Column(name="DATE_CHARGE_SUPP")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateChargeSupp;
 
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

       public BigDecimal getQuantiteChargeSupp() {
        return quantiteChargeSupp;
    }

    public void setQuantiteChargeSupp(BigDecimal quantiteChargeSupp) {
        this.quantiteChargeSupp = quantiteChargeSupp;
    }

    public DetailTournee getDetailTournee() {
        return detailTournee;
    }

    public void setDetailTournee(DetailTournee detailTournee) {
        this.detailTournee = detailTournee;
    }

    public BigDecimal getQuantiteChargeSuppCaisse() {
        return quantiteChargeSuppCaisse;
    }

    public void setQuantiteChargeSuppCaisse(BigDecimal quantiteChargeSuppCaisse) {
        this.quantiteChargeSuppCaisse = quantiteChargeSuppCaisse;
    }

    public Date getDateChargeSupp() {
        return dateChargeSupp;
    }

    public void setDateChargeSupp(Date dateChargeSupp) {
        this.dateChargeSupp = dateChargeSupp;
    }

 
}
