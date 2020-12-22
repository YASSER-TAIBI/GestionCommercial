/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "depot")
@NamedQuery(name = "Depot.findAll", query = "SELECT d FROM Depot d")
public class Depot implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    
    @Column(name = "CODE")
    private String code;

    @Column(name = "LIBELLE")
    private String libelle1;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "depot")
	private List<Magasin> ListMagasin;  
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle1() {
        return libelle1;
    }

    public void setLibelle1(String libelle) {
        this.libelle1 = libelle;
    }

    public List<Magasin> getListMagasin() {
        return ListMagasin;
    }

    public void setListMagasin(List<Magasin> ListMagasin) {
        this.ListMagasin = ListMagasin;
    }
    
    

}
