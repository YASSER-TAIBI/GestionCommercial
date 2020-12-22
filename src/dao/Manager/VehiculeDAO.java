/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.Vehicule;
import java.util.List;

/**
 *
 * @author pc
 */
public interface VehiculeDAO {
    
        public Vehicule findById(int id);
		
		public void add(Vehicule Vehicule);
				
		public  Vehicule edit(Vehicule e);
		
		public  void delete(Vehicule e); 
		
		public List<Vehicule> findAll();
    
                public Vehicule findByNom(String nom);
                
                public Vehicule findByMatricule(String matr);
                
                public List<Vehicule> findByVehiculeAndDisponibilities();
    
    
}
