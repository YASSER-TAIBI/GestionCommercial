
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.Compte;
import dao.Entity.DetailCompte;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface DetailCompteDAO {
    
                public DetailCompte findById(int id);
		
		public void add(DetailCompte detailCompteVendeur);
		
		public  DetailCompte edit(DetailCompte e);
		
		public  void delete(DetailCompte e); 
		
		public List<DetailCompte> findAll();
                
                public List<DetailCompte> findFilterByVendeur(int compteVendeur );
                    
                public List<DetailCompte> findFilterByVendeurAndDate(Compte compteVendeur, Date dateDebut,Date dateFin ) ;
                
                public List<DetailCompte> findFilterByVendeurAndDateAndTypeCompte(Compte compteVendeur, Date dateDebut,Date dateFin, String typeCompte) ;
                
                  public List<Object[]> findByMontantCredit(int compte , Date dateOp) ;
                  
                public List<DetailCompte> findByDateOperation(int compte);
                
}
