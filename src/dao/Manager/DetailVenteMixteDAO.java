/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.DetailTournee;
import dao.Entity.DetailVenteMixte;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface DetailVenteMixteDAO {
    
    
                public DetailVenteMixte findById(int id);
		
		public void add(DetailVenteMixte detailVenteMixte);
		
		public  DetailVenteMixte edit(DetailVenteMixte e);
		
		public  void delete(DetailVenteMixte e); 
		
		public List<DetailVenteMixte> findAll();
                
    
                
}
