/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.Offre;
import java.util.Date;
import java.util.List;

/**
 *
 * @author pc
 */
public interface OffreDAO {
    
    
                public Offre findById(int id);
		
		public void add(Offre offre);

		public  Offre edit(Offre e);
		
		public  void delete(Offre e); 
		
		public List<Offre> findAll();
                
                public List<Offre> findByCodeOffre(String code);
                
                 public List<Offre> findByDateAndArticleAndSecteur(Date date,int art, int secteur);

}
