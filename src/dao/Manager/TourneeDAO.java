/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.Secteur;
import dao.Entity.Tournee;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Hp
 */

public interface TourneeDAO {
    
    public void add(Tournee tournee);

    public Tournee findById(int id);

    public Tournee edit(Tournee e);

    public void delete(Tournee e);

    public List<Tournee> findAll();
    
    public Tournee findTourneeByVendeur(String nom);
      
    public int getMaxId() ;
    
    public Tournee findDetailTrnByTrn(Date date,int secteur,int depot,int magasin,int vendeur); 
    
    public List<Tournee> findTourneeByDetailOverte(String etat) ;
         
    public List<Tournee> findMouvementStockGlobalByDetailMouvementStock(Date date);
    
    public List<Tournee> findTorneeByDateTornee();

    public Tournee findTourneeByCodeDecharge(String code);
       
    public List<Tournee> findEtat(String etat) ;
    
    public List<Tournee> findTourneeByEtat(String etat, int depot);
    
    public List<Tournee> findTourneeByEtatAndVendeur(String etat, int depot, int vendeur);
    
    public Tournee findTourneeByVendeurAndSecteurAndVilleAndEtat(int vendeur, int secteur, int ville, String etat);
    
    public List<Tournee> findByVendeurAndCodeVentAndEtat(int idVendeur);
     
    public List<Tournee> findByVendeurAndDateAndEtat(int idVendeur, Date date) ;
     
     
     
}
