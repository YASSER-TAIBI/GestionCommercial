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
@Table(name = "Versement_Client")
@NamedQuery(name = "VersementClient.findAll", query = "SELECT c FROM VersementClient c")
public class VersementClient  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    
    @Column(name = "CODE_VERSEMENT")
    private String codeVersement;
    
    @Column(name="DATE_VERSEMENT")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateVersement;
    
    @Column(name = "ETAT")
    private String etat;
    
    @Column(name = "VERSEMENT_CREDIT") 
    private BigDecimal versementCredit ;
     
    @Column(name = "TOTAL_CREDIT") 
    private BigDecimal totalCredit ;
    
    @Column(name = "MONTANT_ESPECE") 
    private BigDecimal montantEspece ;
    
    @Column(name = "MONTANT_CREDIT") 
    private BigDecimal montantCredit ;
    
    @Column(name = "MONTANT_CHEQUE") 
    private BigDecimal montantCheque ;
      
    @Column(name = "MONTANT_BANQUE") 
    private BigDecimal montantBanque ;
    
    @Column(name = "MONTANT_REMISE") 
    private BigDecimal montantRemise ;
    
    @Column(name = "RETOUR")
    private String retour;

    @ManyToOne
    @JoinColumn(name = "ID_CAISSIER")
    private Caissier caissier;
    
    @ManyToOne
    @JoinColumn(name = "ID_TRANSFERT_STOCK")
    private TransfertStock transfertStock;
    
    @Column(name = "ETAT_VERSEMENT_CHEQUE")
    private boolean etatVersementCheque;
    
    @Column(name = "ETAT_VERSEMENT_BANCAIRE")
    private boolean etatVersementBancaire;
    
    @OneToMany(mappedBy="versementClient",cascade = {CascadeType.ALL})
	private List<VersementBancaireClient> versementBancairesClient= new ArrayList<VersementBancaireClient>();
    
    @OneToMany(mappedBy="versementClient",cascade = {CascadeType.ALL})
	private List<VersementChequeClient> versementChequeClient= new ArrayList<VersementChequeClient>();
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeVersement() {
        return codeVersement;
    }
    
    public void setCodeVersement(String codeVersement) {
        this.codeVersement = codeVersement;
    }

    public Date getDateVersement() {
        return dateVersement;
    }

    public void setDateVersement(Date dateVersement) {
        this.dateVersement = dateVersement;
    }

    public String getEtat() {
        return etat;
    }

    public boolean isEtatVersementCheque() {
        return etatVersementCheque;
    }

    public void setEtatVersementCheque(boolean etatVersementCheque) {
        this.etatVersementCheque = etatVersementCheque;
    }

    public boolean isEtatVersementBancaire() {
        return etatVersementBancaire;
    }

    public void setEtatVersementBancaire(boolean etatVersementBancaire) {
        this.etatVersementBancaire = etatVersementBancaire;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public BigDecimal getVersementCredit() {
        return versementCredit;
    }

    public void setVersementCredit(BigDecimal versementCredit) {
        this.versementCredit = versementCredit;
    }

    public BigDecimal getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(BigDecimal totalCredit) {
        this.totalCredit = totalCredit;
    }
    
    public BigDecimal getMontantEspece() {
        return montantEspece;
    }

    public void setMontantEspece(BigDecimal montantEspece) {
        this.montantEspece = montantEspece;
    }

    public BigDecimal getMontantCredit() {
        return montantCredit;
    }

    public void setMontantCredit(BigDecimal montantCredit) {
        this.montantCredit = montantCredit;
    }


    public String getRetour() {
        return retour;
    }

    public BigDecimal getMontantBanque() {
        return montantBanque;
    }

    public Caissier getCaissier() {
        return caissier;
    }

    public void setCaissier(Caissier caissier) {
        this.caissier = caissier;
    }

    public void setMontantBanque(BigDecimal montantBanque) {
        this.montantBanque = montantBanque;
    }

    public TransfertStock getTransfertStock() {
        return transfertStock;
    }

    public void setTransfertStock(TransfertStock transfertStock) {
        this.transfertStock = transfertStock;
    }

    public List<VersementBancaireClient> getVersementBancairesClient() {
        return versementBancairesClient;
    }

    public void setVersementBancairesClient(List<VersementBancaireClient> versementBancairesClient) {
        this.versementBancairesClient = versementBancairesClient;
    }

    public List<VersementChequeClient> getVersementChequeClient() {
        return versementChequeClient;
    }

    public void setVersementChequeClient(List<VersementChequeClient> versementChequeClient) {
        this.versementChequeClient = versementChequeClient;
    }

    public BigDecimal getMontantCheque() {
        return montantCheque;
    }

    public void setMontantCheque(BigDecimal montantCheque) {
        this.montantCheque = montantCheque;
    }

    public BigDecimal getMontantRemise() {
        return montantRemise;
    }

    public void setMontantRemise(BigDecimal montantRemise) {
        this.montantRemise = montantRemise;
    }

    public void setRetour(String retour) {
        this.retour = retour;
    }
    
    
   
}
