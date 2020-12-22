/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.Magasin;
import java.util.List;

/**
 *
 * @author pc
 */
public interface MagasinDAO {
     public void add(Magasin magasin);
		
		public Magasin findById(int id);
                 
		public  Magasin edit(Magasin e);
		
		public  void delete(Magasin e); 
		
		public List<Magasin> findAll();
              
                public List<Magasin> findMagasinByDepot(int depot);
                
                public Magasin findMagasinByDepotUnique(int depot);
    
}
