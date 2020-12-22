/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.DetailAvanceMagasinier;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface DetailAvanceMagasinierDAO {
    
                public DetailAvanceMagasinier findById(int id);
		
		public void add(DetailAvanceMagasinier DetailAvanceMagasinier);
		
		public  DetailAvanceMagasinier edit(DetailAvanceMagasinier e);
		
		public  void delete(DetailAvanceMagasinier e); 
		
		public List<DetailAvanceMagasinier> findAll();
                
                public List<DetailAvanceMagasinier> findByAvanceMagasiniers(int avMag);
                
}
