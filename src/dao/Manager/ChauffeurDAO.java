/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.Banque;
import dao.Entity.Chauffeur;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface ChauffeurDAO {
    
                public Chauffeur findById(int id);
		
		public void add(Chauffeur chauffeur);
		
		
		public  Chauffeur edit(Chauffeur e);
		
		public  void delete(Chauffeur e); 
		
		public List<Chauffeur> findAll();
}
