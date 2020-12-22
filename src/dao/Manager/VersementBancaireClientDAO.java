/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.VersementBancaireClient;
import java.util.Date;
import java.util.List;

/**
 *
 * @author pc
 */
public interface VersementBancaireClientDAO {
    
                public VersementBancaireClient findById(int id);
		
		public void add(VersementBancaireClient versementBancaireClient);
		
		
		public  VersementBancaireClient edit(VersementBancaireClient e);
		
		public  void delete(VersementBancaireClient e); 
		
		public List<VersementBancaireClient> findAll();
    
    
                public List<VersementBancaireClient> findByVendeur(int vendeur, int depot) ;

                public List<VersementBancaireClient> findByDate(Date date, int depot) ;

                public List<VersementBancaireClient> findByBanque(int banque, int depot) ;

                public List<VersementBancaireClient> findByVendeurAndDate(int vendeur,Date date, int depot) ;
        
                public List<VersementBancaireClient> findByVendeurAndBanque(int vendeur,int banque, int depot) ;

                public List<VersementBancaireClient> findByDateAndBanque(Date date,int banque, int depot) ;

                public List<VersementBancaireClient> findByVendeurAndDateAndBanque(int vendeur,Date date,int banque, int depot) ;
    
}
