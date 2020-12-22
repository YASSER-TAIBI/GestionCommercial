/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.ConditionOffre;
import java.util.Date;
import java.util.List;

/**
 *
 * @author pc
 */
public interface ConditionOffreDAO {
    
    
                public ConditionOffre findById(int id);
		
		public void add(ConditionOffre conditionOffre);

		public  ConditionOffre edit(ConditionOffre e);
		
		public  void delete(ConditionOffre e); 
		
		public List<ConditionOffre> findAll();
                
                public ConditionOffre findByDateAndArticleAndSecteur(Date date,int art,int secteur);
    

}
