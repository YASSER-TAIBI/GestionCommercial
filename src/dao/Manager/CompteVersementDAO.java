/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.CompteVersement;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface CompteVersementDAO {
      
    
		public CompteVersement findById(int id);
		
		public void add(CompteVersement compteVersement);
		
		public  CompteVersement edit(CompteVersement e);
		
		public  void delete(CompteVersement e); 
		
		public List<CompteVersement> findAll();
                
         
}
