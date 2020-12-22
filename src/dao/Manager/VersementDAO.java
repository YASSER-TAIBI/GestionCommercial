/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.Versement;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface VersementDAO {
    
       public Versement findById(int id);
		
		public void add(Versement versement);
		
		public  Versement edit(Versement e);
		
		public  void delete(Versement e); 
		
		public List<Versement> findAll();
                
                public List<Versement> findVersemenBancairetByTournee(int vendeur, String etat);
                
                public List<Versement> findVersementChequeByTournee(int vendeur, String etat);

                public Versement findByTournee(int tournee);
                
                public List<Object[]> findByDepotAndCaissier(int depot,int caissier);
}
