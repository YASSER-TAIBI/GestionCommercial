/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.DetailTransfertStock;
import dao.Entity.TransfertStock;
import dao.Manager.TransfertStockDAO;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class TransfertStockDAOImpl implements TransfertStockDAO{
    
      Session session=HibernateUtil.openSession();

    @Override
    public TransfertStock findById(int id) {

return (TransfertStock)session.get(TransfertStock.class, id);    }

    @Override
    public void add(TransfertStock transfertStock) {

               session.beginTransaction();
		session.save(transfertStock);
		
		session.getTransaction().commit();    }

    @Override
    public TransfertStock edit(TransfertStock e) {

session.beginTransaction();
	TransfertStock p= (TransfertStock)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(TransfertStock e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<TransfertStock> findAll() {

return session.createQuery("select c from TransfertStock c").list();

    }
    
       public TransfertStock findTransfertStockByDetailTransfertStock(Date date,int depot,int magasin) {
	Query query = session.createQuery("select c from TransfertStock c where c.dateTransf =:date and c.depotSource.id =:depot and c.magasinSource.id =:magasin ");
                query.setParameter("date",date);
                query.setParameter("depot",depot);
                query.setParameter("magasin",magasin);
   
                 return (TransfertStock)query.uniqueResult();
    
          }
    
        public int getMaxIdTransfert() {
          int id=0;
        Query query= session.createQuery("select Max(id) from TransfertStock c");
        
        if( query.uniqueResult()==null)
            id= 1;
        else 
            id= (int) query.uniqueResult();
        
        
       return id;
    }
          public int getMaxIdReception() {
          int id=0;
        Query query= session.createQuery("select Max(id) from TransfertStock c");
        
        if( query.uniqueResult()==null)
            id= 1;
        else 
            id= (int) query.uniqueResult();
        
        
       return id;
    }
              public int getMaxIdSorties() {
          int id=0;
        Query query= session.createQuery("select Max(id) from TransfertStock c");
        
        if( query.uniqueResult()==null)
            id= 1;
        else 
            id= (int) query.uniqueResult();
        
        
       return id;
    }
        
             public TransfertStock findTransfertStockByCodeTransfert(String code, int depot) {
		
		Query query = session.createQuery("select u from TransfertStock u where u.codeTransfert=:code and u.statut ='Transfert Sorties' and u.etat ='Envoyer' and u.depotDestination.id=:depot");
		query.setParameter("code",code);
                query.setParameter("depot",depot);
                return (TransfertStock)query.uniqueResult();
}
      
                       	public List<TransfertStock> findByEtat(String etat) {
		
		Query query = session.createQuery("select u from TransfertStock u where u.etat =:etat and u.statut ='Sorties'");
		query.setParameter("etat",etat);
               
                return query.list();
 }
            
                  public List<TransfertStock> findTransfertStockByCodeSortie(String code) {
		
		Query query = session.createQuery("select u from TransfertStock u where u.codeTransfert=:code and u.statut ='Sorties' and u.etat ='En Attente'");
		query.setParameter("code",code);

                 return query.list();
}
                  
                  	public List<TransfertStock> findByEtatSortie(String etat, String statut ) {
		
		Query query = session.createQuery("select u from TransfertStock u where u.etat =:etat and u.statut =:statut");
		
                query.setParameter("etat",etat);
                query.setParameter("statut",statut);
                return query.list();
                        }
                        
                        public List<TransfertStock> findByEtatTransfertSortie(String etat, String statut) {
		
		Query query = session.createQuery("select u from TransfertStock u where u.etat =:etat and u.statut =:statut");
		
                query.setParameter("etat",etat);
                query.setParameter("statut",statut);
                return query.list();
                        }
                        
                        public List<TransfertStock> findByStatutEnAttenteStockTransfert() {
		
		Query query = session.createQuery("select u from TransfertStock u where u.statut ='En Attente' and u.codeTransfert like 'TRF %'");

                return query.list();
                        }
                        
                        public List<TransfertStock> findByStatutEnAttenteStockReception() {
		
		Query query = session.createQuery("select u from TransfertStock u where u.statut ='En Attente' and u.codeTransfert like 'RCP %' ");

                return query.list();
                        }
                        
                                 public List<TransfertStock> findByDepotAndMagasinStockTransfert(int depot, int magasin) {
		
		Query query = session.createQuery("select u from TransfertStock u where u.depotSource.id=:depot and u.magasinSource.id =:magasin and u.statut='En Attente' and u.codeTransfert like 'TRF %'");
		query.setParameter("magasin",magasin);
               query.setParameter("depot",depot);
                return query.list();
 }
                                 
                                 public List<TransfertStock> findByDepotAndMagasinStockReception(int depot, int magasin) {
		
		Query query = session.createQuery("select u from TransfertStock u where u.depotSource.id=:depot and u.magasinSource.id =:magasin and u.statut='En Attente' and u.codeTransfert like 'RCP %'");
		query.setParameter("magasin",magasin);
               query.setParameter("depot",depot);
                return query.list();
 }
                                 
                                 
                                 public List<TransfertStock> findTransfertStockByDepotAndMagasin(int depot, int magasin, String etat, String statut) {
		
		Query query = session.createQuery("select u from TransfertStock u where u.depotSource.id=:depot and u.magasinSource.id =:magasin and u.statut=:statut and u.etat=:etat ");
		query.setParameter("magasin",magasin);
               query.setParameter("depot",depot);
               query.setParameter("etat",etat);
                query.setParameter("statut",statut);
                return query.list();
 }
                                        public TransfertStock findTransfertStockByDeclarationReception(int decRec) {
		
		Query query = session.createQuery("select u from TransfertStock u where u.declarationReception.id =:decRec");
		query.setParameter("decRec",decRec);

                return (TransfertStock)query.uniqueResult();
}
                                 
                                 public TransfertStock findTransfertStockByCodeTransAndStatut(String code, String statut) {
		
		Query query = session.createQuery("select u from TransfertStock u where u.codeTransfert =:code and u.statut =:statut");
		query.setParameter("code",code);
                query.setParameter("statut",statut);
                
                return (TransfertStock)query.uniqueResult();
}
                                 
              
 }
