/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.PrixVenteDepot;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface PrixVenteDepotDAO {
    
     public PrixVenteDepot findById(int id);
		
		public void add(PrixVenteDepot PrixVenteDepot);
		
		public  PrixVenteDepot edit(PrixVenteDepot e);
		
		public  void delete(PrixVenteDepot e); 
		
		public List<PrixVenteDepot> findAll();
                
                  public PrixVenteDepot findPrixBySecteurAndTypeVente(int Art,int Tvent,int Secteur);
    
}
