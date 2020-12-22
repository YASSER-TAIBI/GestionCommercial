/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.DetailAvanceChauffeur;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface DetailAvanceChauffeurDAO {
    
                public DetailAvanceChauffeur findById(int id);
		
		public void add(DetailAvanceChauffeur DetailAvanceChauffeur);
		
		public  DetailAvanceChauffeur edit(DetailAvanceChauffeur e);
		
		public  void delete(DetailAvanceChauffeur e); 
		
		public List<DetailAvanceChauffeur> findAll();
                
                public List<DetailAvanceChauffeur> findByAvanceChauffeur(int avChauff);
}
