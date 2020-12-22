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

@Entity
@Table(name = "magasin")
@NamedQuery(name = "Magasin.findAll", query = "SELECT m FROM Magasin m")
public class Magasin implements Serializable {
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    @Column(name = "CODE")
    private String code;

    @Column(name = "LIBELLE")
    private String libelle;
       
     @ManyToOne
     @JoinColumn(name="id_typemagasin")
     private TypeMagasin typeMagasin=new TypeMagasin();
    
     @ManyToOne
     @JoinColumn(name="ID_DEPOT")
     private Depot depot;
     
    public int getId(){
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

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public TypeMagasin getTypeMagasin() {
        return typeMagasin;
    }

    public void setTypeMagasin(TypeMagasin typeMagasin) {
        this.typeMagasin = typeMagasin;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    
    
}