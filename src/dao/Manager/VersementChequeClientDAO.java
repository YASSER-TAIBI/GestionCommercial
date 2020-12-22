/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.VersementChequeClient;
import java.util.Date;
import java.util.List;

/**
 *
 * @author pc
 */
public interface VersementChequeClientDAO {
    
                public VersementChequeClient findById(int id);
		
		public void add(VersementChequeClient versementChequeClient);
		
		public  VersementChequeClient edit(VersementChequeClient e);
		
		public  void delete(VersementChequeClient e); 
		
		public List<VersementChequeClient> findAll();
    
                public List<VersementChequeClient> findByVendeur(int vendeur, int depot) ;

                public List<VersementChequeClient> findByDate(Date dateEch, int depot) ;

                public List<VersementChequeClient> findByTypeCheque(String typeCheque, int depot) ;

                public List<VersementChequeClient> findByVendeurAndDateEch(int vendeur,Date dateEch, int depot) ;
        
                public List<VersementChequeClient> findByVendeurAndTypeCheque(int vendeur,String typeCheque, int depot) ;

                public List<VersementChequeClient> findByDateEchAndTypeCheque(Date dateEch,String typeCheque, int depot) ;

                public List<VersementChequeClient> findByVendeurAndDateEchAndTypeCheque(int vendeur,Date dateEch, String typeCheque, int depot) ;


    
}
