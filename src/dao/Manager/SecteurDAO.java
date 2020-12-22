/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.Secteur;
import java.util.List;

/**
 *
 * @author pc
 */
public interface SecteurDAO {

    public void add(Secteur clientpf);

    public Secteur findById(int id);

    public Secteur edit(Secteur e);

    public void delete(Secteur e);

    public List<Secteur> findAll();
    
    public Secteur findByNom(String nom) ;
      
    public List<Secteur> findBySecteur(String nom);

    public List<Secteur> findSecteurByVille(int ville);
    
    public List<Secteur> findSecteurByDepot(int depot) ;
}
