/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.CreationOffre;
import java.util.List;

/**
 *
 * @author pc
 */
public interface CreationOffreDAO {
    
    
                public CreationOffre findById(int id);
		
		public void add(CreationOffre creationOffre);

		public  CreationOffre edit(CreationOffre e);
		
		public  void delete(CreationOffre e); 
		
		public List<CreationOffre> findAll();
    

}
