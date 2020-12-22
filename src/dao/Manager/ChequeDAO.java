/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.Cheque;
import java.util.List;

/**
 *
 * @author pc
 */
public interface ChequeDAO {
    
    public Cheque findById(int id);
		
		public void add(Cheque Cheque);
		
		public  Cheque edit(Cheque e);
		
		public  void delete(Cheque e); 
		
		public List<Cheque> findAll();
    
                public List<Cheque> findChequeByVendeur (int idVendeur );
                 
                public List<Cheque> findChequeByEtat (String typeCheque ,String etat);
                
                 public List<Cheque> findChequeByVendeur (String idVendeur, String etat ) ;
                 
                 public List<Cheque> findChequeByVendeurAndSecteurAndVille (int idVendeur, int idSecteur, int idVille );
    
}
