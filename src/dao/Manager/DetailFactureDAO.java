/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.DetailFacture;
import dao.Entity.DetailTournee;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Hp
 */

public interface DetailFactureDAO {
    
                public DetailFacture findById(int id);
		
		public void add(DetailFacture detailFacture);
		
		public  DetailFacture edit(DetailFacture e);
		
		public  void delete(DetailFacture e); 
		
		public List<DetailFacture> findAll();
                
                public List<DetailFacture> findByDateAndVendeurAndTourneeAndMagasinAndArticle(Date date,int vendeur,int tournee,int magasin, int article);

                public List<DetailFacture> findByTournee(int idtournee );
}
