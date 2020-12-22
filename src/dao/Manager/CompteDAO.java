/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.Compte;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface CompteDAO {
      
    
		public Compte findById(int id);
		
		public void add(Compte compteVendeur);
		
		public  Compte edit(Compte e);
		
		public  void delete(Compte e); 
		
		public List<Compte> findAll();
                
         
}
