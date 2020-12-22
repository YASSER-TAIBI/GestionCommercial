/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.DetailTournee;
import dao.Entity.Tournee;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Hp
 */

public interface DetailTourneeDAO {
    
                public DetailTournee findById(int id);
		
		public void add(DetailTournee detailTournee);
		
		public  DetailTournee edit(DetailTournee e);
		
		public  void delete(DetailTournee e); 
		
		public List<DetailTournee> findAll();
                
                public List<DetailTournee> findDetailTourneeByTournee(Date date,int depot,int magasin,int vendeur );
                
                public DetailTournee findDetailTrnByTrn(Date date,int depot,int magasin,int vendeur );

                public DetailTournee findDetailTourneeByTourneeVendeurDepotMagasin(int idtournee,int vendeur,int depot,int magasin ,int idarticle);
                
                public List<Object[]> findDetailTourneeByVendeurAndDepotAndMagasinAndEtat(String vendeur, String depot , String magasin, String secteur);
                
                public List<Object[]> findDetailTourneeByVendeurAndDepotAndMagasinAndEtatInitial(String vendeur, String depot , String magasin, String secteur);
                
                public List<Object[]> findDetailTourneeByVendeurAndDepotAndMagasinAndEtatVersementOrFactureOrVente(String vendeur, String depot , String magasin, String secteur);
                
                public List<DetailTournee> findDetailTourneeByDate(Date date,String vendeur, String depot , String magasin, String secteur);
                
                public List<DetailTournee> findDetailTourneeByDateTournee(Date date,int vendeur,int depot,int magasin, int secteur);
                 
                public List<DetailTournee> findDetailTourneeByTournee(Tournee tournee);
                 
                public List<DetailTournee> findDetailTourneeByDateAndVendeurAndSecteurAndVilleAndMagasin(Date date,int depot, int vendeur,int magasin,int secteur);
                
                public List<Object[]> findBySituationVenteFacture(Date date, int vendeur, int tournee);
}
