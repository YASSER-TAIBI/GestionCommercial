/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.Ville;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface VilleDAO {
   
		
          public Ville findById(int id);
		public void add(Ville ville);
		
		
		public  Ville edit(Ville e);
		
		public void delete(Ville e); 
		
		public List<Ville> findAll();
                
                public List<Ville> findVilleByRechercheLibelle(String libelle);
                
                public List<Ville> findVilleByRechercheCode(String code);
                
               	public List<Ville>  findByVille(String nom);
                 
                public List<Ville> findVilleByDepot(int depot);

}
