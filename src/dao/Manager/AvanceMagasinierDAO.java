/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.AvanceMagasinier;
import dao.Entity.DetailAvanceMagasinier;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface AvanceMagasinierDAO {
    
                public AvanceMagasinier findById(int id);
		
		public void add(AvanceMagasinier AvanceMagasinier);
		
		public  AvanceMagasinier edit(AvanceMagasinier e);
		
		public  void delete(AvanceMagasinier e); 
		
		public List<AvanceMagasinier> findAll();
                
                public List<AvanceMagasinier> findByDepotAndMagasin(int depot,int magasin);

}
