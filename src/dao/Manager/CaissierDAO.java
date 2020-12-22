/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.Caissier;
import java.util.List;

/**
 *
 * @author pc
 */
public interface CaissierDAO {
    
    
     public Caissier findById(int id);
		
		public void add(Caissier caissier);
				
		public  Caissier edit(Caissier e);
		
		public  void delete(Caissier e); 
                
		public List<Caissier> findAll();
                
                public Caissier findByNom(String nom);
                
                public List<Caissier> findByCaissier(int depot);
                
                public Caissier findByCaissierAndDepot(int idCaissier, int idDepot);
  
}
