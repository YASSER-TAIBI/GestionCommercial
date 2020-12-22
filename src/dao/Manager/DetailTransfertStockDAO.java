/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.DetailTransfertStock;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface DetailTransfertStockDAO {
    
              public DetailTransfertStock findById(int id);
		
		public void add(DetailTransfertStock detailTransfertStock);
		
		public  DetailTransfertStock edit(DetailTransfertStock e);
		
		public  void delete(DetailTransfertStock e); 
		
		public List<DetailTransfertStock> findAll();
                
                public List<DetailTransfertStock> findByRechercheLibelle(String article);
                 
                public List<Object[]> findBySituationStock(int magasin);
                
                public List<DetailTransfertStock> findByTransfertStock(int transf);
                
                public List<Object[]> findByQteFinal(int article ,int magasin, int depot);

                public List<Object[]> findBySituationStockAndDateMin(int magasin, Date dateTransf);
     
                public List<Object[]> findBySituationStockAndDate(int magasin, Date dateTransf);
                
                public List<Object[]> findBySituationStockAndDepot();
                
                public List<Object[]> findBySituationStockAndDateMinAndDepot(Date dateTransf);
     
                public List<Object[]> findBySituationStockAndDateAndDepot(Date dateTransf);
                
                public List<DetailTransfertStock> findByDeclarationReception(int decRec);
}
