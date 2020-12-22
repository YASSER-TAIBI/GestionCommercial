/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.Fournisseur;
import java.util.List;

/**
 *
 * @author pc
 */
public interface FournisseurDAO {
    public void add(Fournisseur fournisseur);
		
		public Fournisseur findById(int id);
                 
		public  Fournisseur edit(Fournisseur e);
		
		public  void delete(Fournisseur e); 
		
		public List<Fournisseur> findAll();

}
