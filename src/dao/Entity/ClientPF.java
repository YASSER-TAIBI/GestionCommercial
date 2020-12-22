
package dao.Entity;

import java.io.Serializable;
import java.util.Date;
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


@Entity
@Table(name = "client_pf")
@NamedQuery(name = "ClientPF.findAll", query = "SELECT c FROM ClientPF c")
public class ClientPF implements Serializable {
      @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
      @Column(name = "CODE")
    private String code;
    
    @Column(name = "NOM")
    private String nom;
    
    @Column(name = "ADRESSE")
    private String adresse;
    
    @Column(name = "TELEPHONE")
    private String telephone;
    
    @Column(name = "EMAIL")
    private String email;
    
    @ManyToOne
    @JoinColumn(name = "id_ville")
    private Ville ville;

    @ManyToOne
    @JoinColumn(name = "id_secteur")
    private Secteur secteur;
     
    @ManyToOne
    @JoinColumn(name = "id_compte_client")
    private CompteClient compteClient;
    
    @Column(name="DATE_CREATION")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dcreation;
      
    @Column(name="DATE_AJOURS")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dajours;

    @Column(name = "BLOCK_CLIENT")
    private boolean blockClient;
     
     
     
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Secteur getSecteur() {
        return secteur;
    }

    public void setSecteur(Secteur secteur) {
        this.secteur = secteur;
    }
    
    public boolean isBlockClient() {
        return blockClient;
    }

    public void setBlockClient(boolean blockClient) {
        this.blockClient = blockClient;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public CompteClient getCompteClient() {
        return compteClient;
    }

    public void setCompteClient(CompteClient compteClient) {
        this.compteClient = compteClient;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

   
    public Date getDcreation() {
        return dcreation;
    }

    public void setDcreation(Date dcreation) {
        this.dcreation = dcreation;
    }

    public Date getDajours() {
        return dajours;
    }

    public void setDajours(Date dajours) {
        this.dajours = dajours;
    }
     
     
    
}
