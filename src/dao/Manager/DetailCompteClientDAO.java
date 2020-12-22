
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.CompteClient;
import dao.Entity.DetailCompteClient;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface DetailCompteClientDAO {
    
                public DetailCompteClient findById(int id);
		
		public void add(DetailCompteClient detailCompteClient);
		
		public  DetailCompteClient edit(DetailCompteClient e);
		
		public  void delete(DetailCompteClient e); 
		
		public List<DetailCompteClient> findAll();
                
                public List<Object[]> findByMontantCredit(int compteClient);
                
                public List<DetailCompteClient> findByCompteClient(int compteClient );
                              
}
