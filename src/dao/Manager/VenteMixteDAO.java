/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.VenteMixte;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface VenteMixteDAO {
    
       public VenteMixte findById(int id);
		
		public void add(VenteMixte venteMixte);
		
		public  VenteMixte edit(VenteMixte e);
		
		public  void delete(VenteMixte e); 
		
		public List<VenteMixte> findAll();
    
                 public VenteMixte findVenteMixteByDetailTournee(int idTrn) ;
}
