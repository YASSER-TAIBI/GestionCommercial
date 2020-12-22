/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.AvanceChauffeur;
import dao.Entity.DetailAvanceChauffeur;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface AvanceChauffeurDAO {
    
                public AvanceChauffeur findById(int id);
		
		public void add(AvanceChauffeur AvanceChauffeur);
		
		public  AvanceChauffeur edit(AvanceChauffeur e);
		
		public  void delete(AvanceChauffeur e); 
		
		public List<AvanceChauffeur> findAll();

                public List<AvanceChauffeur> findByChauffeur(int Chauf);
}
