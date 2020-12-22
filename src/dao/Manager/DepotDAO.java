/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.Depot;
import java.util.List;

/**
 *
 * @author pc
 */
public interface DepotDAO {
    
                public void add(Depot depot);
		
		public Depot findById(int id);
                 
		public Depot edit(Depot e);
		
		public void delete(Depot e); 
		
		public List<Depot> findAll();
                
                public List<Depot> findByDepot(int id);
                
                public List<Depot> findByDepotSaufSiege() ;
    
}
