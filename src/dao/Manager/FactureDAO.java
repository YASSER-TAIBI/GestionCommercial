/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.Article;
import dao.Entity.Facture;
import java.util.List;

/**
 *
 * @author pc
 */
public interface FactureDAO {
                 public Facture findById(int id);
		
		public void add(Facture facture);
		
		public  Facture edit(Facture e);
		
		public  void delete(Facture e); 
		
		public List<Facture> findAll();
                
                public List<Facture> findFactureByTournee(int idtournee );
    
}
