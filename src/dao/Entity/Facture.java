/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;

/**
 *
 * @author Hp
 */
@Entity
@Table(name ="Facture")
@NamedQuery(name = "Facture.findAll", query = "SELECT c FROM Facture c")
public class Facture implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    
    @Column(name = "NUM_FACTURE")
    private String numFacture;
    
    @Column(name="DATE_FACTURE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFacture;
    
    @Column(name = "MONTANT_TOTALE") 
    private BigDecimal montantTotal;
    
    @Column(name = "REMISE_TOTAL") 
    private BigDecimal remiseTotal;
    
    @ManyToOne
    @JoinColumn(name = "ID_VENDEUR")
    private Vendeur vendeur;
     
    @ManyToOne
    @JoinColumn(name = "ID_CLIENT")
    private ClientPF client;

    @ManyToOne
    @JoinColumn(name = "ID_DEPOT")
    private Depot depot;
    
    @ManyToOne
    @JoinColumn(name = "ID_MAGASIN")
    private Magasin magasin;

    @ManyToOne
    @JoinColumn(name = "ID_TOURNEE")
    private Tournee tournee;
    
    @OneToMany(mappedBy="facture",cascade={CascadeType.ALL},fetch = FetchType.EAGER)
    private List<DetailFacture> detailFacture= new ArrayList<DetailFacture>();
    
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

    public String getNumFacture() {
        return numFacture;
    }

    public void setNumFacture(String numFacture) {
        this.numFacture = numFacture;
    }

    public Date getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(Date dateFacture) {
        this.dateFacture = dateFacture;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }

    public Vendeur getVendeur() {
        return vendeur;
    }

    public void setVendeur(Vendeur vendeur) {
        this.vendeur = vendeur;
    }

    public ClientPF getClient() {
        return client;
    }

    public BigDecimal getRemiseTotal() {
        return remiseTotal;
    }

    public void setRemiseTotal(BigDecimal remiseTotal) {
        this.remiseTotal = remiseTotal;
    }

    public void setClient(ClientPF client) {
        this.client = client;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    public Tournee getTournee() {
        return tournee;
    }

    public void setTournee(Tournee tournee) {
        this.tournee = tournee;
    }
    
    

    public List<DetailFacture> getDetailFacture() {
        return detailFacture;
    }

    public void setDetailFacture(List<DetailFacture> detailFacture) {
        this.detailFacture = detailFacture;
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

    
    
}
