/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.Vendeur;
import java.util.List;

/**
 *
 * @author pc
 */
public interface VendeurDAO {
    
    
     public Vendeur findById(int id);
		
		public void add(Vendeur article);
		
		
		public  Vendeur edit(Vendeur e);
		
		public  void delete(Vendeur e); 
                
		public List<Vendeur> findAll();
                
                public List<Vendeur> findBySecteur(String secteur);
                
                public List<Vendeur> findByDepotVille(int idVille);
                 
                public List<Vendeur> findVendeurByDepot(String typeVendeur);
                
                public List<Vendeur> findByOperateur(String operateur);

                public List<Vendeur> findByDepot(int idDepot);
                
                public List<Vendeur> findByDepotV2(int idDepot) ;
                
                public Vendeur findByVandeurAndDepotV2(int idVendeur, int idDepot);
                
                public Vendeur findByCaissier();
}
