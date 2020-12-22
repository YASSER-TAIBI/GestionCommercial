/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Hp
 */

@Entity
@Table(name ="Vente_Mixte")
@NamedQuery(name = "VenteMixte.findAll", query = "SELECT c FROM VenteMixte c")
public class VenteMixte implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    
    @Column(name = "PRIX_TOTAL_VENDUE") 
    private BigDecimal prixTotalVendue;
    
         @OneToMany(mappedBy="venteMixte",cascade={CascadeType.ALL})
    private List<DetailVenteMixte> listDetailVenteMixte  = new ArrayList<>();

        @ManyToOne
    @JoinColumn(name = "ID_DETAIL_TOURNEE")
    private DetailTournee detailTournee;   
         
         
         
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<DetailVenteMixte> getListDetailVenteMixte() {
        return listDetailVenteMixte;
    }

    public DetailTournee getDetailTournee() {
        return detailTournee;
    }

    public void setDetailTournee(DetailTournee detailTournee) {
        this.detailTournee = detailTournee;
    }

    public void setListDetailVenteMixte(List<DetailVenteMixte> listDetailVenteMixte) {
        this.listDetailVenteMixte = listDetailVenteMixte;
    }

    public BigDecimal getPrixTotalVendue() {
        return prixTotalVendue;
    }

    public void setPrixTotalVendue(BigDecimal prixTotalVendue) {
        this.prixTotalVendue = prixTotalVendue;
    }
    
    
}
