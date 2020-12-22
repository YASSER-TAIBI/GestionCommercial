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
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author pc
 */
@Entity
@Table(name ="Vehicule")
@NamedQuery(name = "Vehicule.findAll", query = "SELECT c FROM Vehicule c")
public class Vehicule implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
     
    @Column(name = "MATRICULE")
    private String matricule;
    
    @Column(name = "NOM")
    private String nom;

    @Column(name = "TYPE_VEHICULE")
    private String typeVehicule;
    
    @Column(name = "OCCUPE_DISPONIBLE")
    private boolean occupeDisponible;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeVehicule() {
        return typeVehicule;
    }

    public void setTypeVehicule(String typeVehicule) {
        this.typeVehicule = typeVehicule;
    }

    public String getMatricule() {
        return matricule;
    }

    public boolean isOccupeDisponible() {
        return occupeDisponible;
    }

    public void setOccupeDisponible(boolean occupeDisponible) {
        this.occupeDisponible = occupeDisponible;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
    
}
