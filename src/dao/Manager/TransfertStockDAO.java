/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;



import dao.Entity.TransfertStock;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface TransfertStockDAO {
    
        public TransfertStock findById(int id);
		
		public void add(TransfertStock transfertStock);
		
		public  TransfertStock edit(TransfertStock e);
		
		public  void delete(TransfertStock e); 
		
		public List<TransfertStock> findAll();
                
                public TransfertStock findTransfertStockByDetailTransfertStock(Date date,int depot,int magasin);
                
                public int getMaxIdTransfert();
                
                public int getMaxIdSorties();
                 
                public int getMaxIdReception();
                
                public TransfertStock findTransfertStockByCodeTransfert(String code, int depot);
                
                public List<TransfertStock> findByEtat(String etat);
                
                public List<TransfertStock> findTransfertStockByCodeSortie(String code);
                
                public List<TransfertStock> findByEtatSortie(String etat, String statut );
                
                public List<TransfertStock> findByEtatTransfertSortie(String etat, String statut);
                
                public List<TransfertStock> findByStatutEnAttenteStockTransfert();

                public List<TransfertStock> findByStatutEnAttenteStockReception();
                    
                public List<TransfertStock> findByDepotAndMagasinStockTransfert(int depot, int magasin);
                
                public List<TransfertStock> findByDepotAndMagasinStockReception(int depot, int magasin);
                
                public TransfertStock findTransfertStockByDeclarationReception(int decRec);
                
                public TransfertStock findTransfertStockByCodeTransAndStatut(String code, String statut);
                
                public List<TransfertStock> findTransfertStockByDepotAndMagasin(int depot, int magasin, String etat, String statut);
                
            
}
