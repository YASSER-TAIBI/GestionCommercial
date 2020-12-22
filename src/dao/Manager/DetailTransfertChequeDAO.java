/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.DetailTransfertCheque;
import java.util.List;


/**
 *
 * @author Hp
 */
public interface DetailTransfertChequeDAO {
    
      public DetailTransfertCheque findById(int id);
		
		public void add(DetailTransfertCheque DetailTransfertCheque);
		
		public  DetailTransfertCheque edit(DetailTransfertCheque e);
		
		public  void delete(DetailTransfertCheque e); 
		
		public List<DetailTransfertCheque> findAll();
//    
}
