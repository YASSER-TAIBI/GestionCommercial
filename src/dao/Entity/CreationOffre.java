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
@Table(name ="CreationOffre")
@NamedQuery(name = "CreationOffre.findAll", query = "SELECT c FROM CreationOffre c")
public class CreationOffre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    
    @Column(name = "CODE_OFFRE")
    private String codeOffre;
    
    @Column(name="DATE_DEBUT")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDebut;
    
    @Column(name="DATE_FIN")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFin;
    
    @Column(name = "TYPE_OFFRE") 
    private String typeOffre;
    
    @Column(name = "TYPE_OU") 
    private String typeOu;
    
    @Column(name = "QTE_OFFRE") 
    private BigDecimal qteOffre;
    
    @OneToMany(mappedBy="creationOffre",cascade={CascadeType.ALL},fetch = FetchType.EAGER)
    private List<ConditionOffre> CreationOffre= new ArrayList<ConditionOffre>();
    
    @OneToMany(mappedBy="creationOffre",cascade={CascadeType.ALL},fetch = FetchType.EAGER)
    private List<Offre> offre= new ArrayList<Offre>();
    
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

    public List<Offre> getOffre() {
        return offre;
    }

    public void setOffre(List<Offre> offre) {
        this.offre = offre;
    }

    public void setUtilisateurMAJ(Utilisateur utilisateurMAJ) {
        this.utilisateurMAJ = utilisateurMAJ;
    }

    public String getCodeOffre() {
        return codeOffre;
    }

    public void setCodeOffre(String codeOffre) {
        this.codeOffre = codeOffre;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public String getTypeOu() {
        return typeOu;
    }

    public void setTypeOu(String typeOu) {
        this.typeOu = typeOu;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getTypeOffre() {
        return typeOffre;
    }

    public void setTypeOffre(String typeOffre) {
        this.typeOffre = typeOffre;
    }

    public BigDecimal getQteOffre() {
        return qteOffre;
    }

    public void setQteOffre(BigDecimal qteOffre) {
        this.qteOffre = qteOffre;
    }

    public List<ConditionOffre> getCreationOffre() {
        return CreationOffre;
    }

    public void setCreationOffre(List<ConditionOffre> CreationOffre) {
        this.CreationOffre = CreationOffre;
    }


    
    

    
    
}
