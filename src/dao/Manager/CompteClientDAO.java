/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.CompteClient;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface CompteClientDAO {
      
    
		public CompteClient findById(int id);
		
		public void add(CompteClient compteClient);
		
		public  CompteClient edit(CompteClient e);
		
		public  void delete(CompteClient e); 
		
		public List<CompteClient> findAll();
                
         
}
