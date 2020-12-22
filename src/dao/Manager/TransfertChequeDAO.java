/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.TransfertCheque;
import dao.Entity.TransfertStock;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface TransfertChequeDAO {
    
     public TransfertCheque findById(int id);
		
		public void add(TransfertCheque TransfertCheque);
		
		public  TransfertCheque edit(TransfertCheque e);
		
		public  void delete(TransfertCheque e); 
		
		public List<TransfertCheque> findAll();
    
                public int getMaxId();
                
                public List<TransfertCheque> findByListTransfertCheque(int depot, int operateur, int magasin, String etat);
}
