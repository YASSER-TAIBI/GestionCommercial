/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.TransfertCheque;
import dao.Entity.TransfertStock;
import dao.Manager.TransfertChequeDAO;
import dao.Manager.TransfertStockDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */

public class TransfertChequeDAOImpl implements TransfertChequeDAO{
    
      Session session=HibernateUtil.openSession();

    @Override
    public TransfertCheque findById(int id) {

return (TransfertCheque)session.get(TransfertCheque.class, id);    }

    @Override
    public void add(TransfertCheque transfertCheque) {

               session.beginTransaction();
		session.save(transfertCheque);
		
		session.getTransaction().commit();    }

    @Override
    public TransfertCheque edit(TransfertCheque e) {

session.beginTransaction();
	TransfertCheque p= (TransfertCheque)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(TransfertCheque e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<TransfertCheque> findAll() {

return session.createQuery("select c from TransfertCheque c").list();

    }
    
       
        public int getMaxId() {
          int id=0;
        Query query= session.createQuery("select Max(id) from TransfertCheque c");
        
        if( query.uniqueResult()==null)
            id= 1;
        else 
            id= (int) query.uniqueResult();
        
        
       return id;
    }
        
            public List<TransfertCheque> findByListTransfertCheque(int depot, int operateur, int magasin, String etat ) {
 Query query= session.createQuery("select c from TransfertCheque c where c.vendeurDestination.id =:operateur and c.depotDestination.id =:depot and c.magasinDestination.id =:magasin and c.etat=:etat");
query.setParameter("depot", depot);
query.setParameter("operateur", operateur);
query.setParameter("magasin", magasin);
query.setParameter("etat", etat);

return query.list();


    } 
        
}
