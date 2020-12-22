/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.VersementBancaire;
import dao.Entity.VersementCheque;
import java.util.Date;
import java.util.List;

/**
 *
 * @author pc
 */
public interface VersementBancaireDAO {
    
                public VersementBancaire findById(int id);
		
		public void add(VersementBancaire versementBancaire);
		
		
		public  VersementBancaire edit(VersementBancaire e);
		
		public  void delete(VersementBancaire e); 
		
		public List<VersementBancaire> findAll();
    
    
                public List<VersementBancaire> findByVendeur(int vendeur, int depot) ;

                public List<VersementBancaire> findByDate(Date date, int depot) ;

                public List<VersementBancaire> findByBanque(int banque, int depot) ;

                public List<VersementBancaire> findByVendeurAndDate(int vendeur,Date date, int depot) ;
        
                public List<VersementBancaire> findByVendeurAndBanque(int vendeur,int banque, int depot) ;

                public List<VersementBancaire> findByDateAndBanque(Date date,int banque, int depot) ;

                public List<VersementBancaire> findByVendeurAndDateAndBanque(int vendeur,Date date,int banque, int depot) ;
    
}
