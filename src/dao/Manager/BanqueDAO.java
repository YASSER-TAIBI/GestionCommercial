/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.Banque;
import java.util.List;

/**
 *
 * @author pc
 */
public interface BanqueDAO {
    
                public Banque findById(int id);
		
		public void add(Banque banque);
		
		
		public  Banque edit(Banque e);
		
		public  void delete(Banque e); 
		
		public List<Banque> findAll();
    
    
    
}
