/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.CompteClient;
import dao.Entity.DetailCompteClient;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import dao.Manager.DetailCompteClientDAO;
import java.util.Date;

/**
 *
 * @author Hp
 */
public class DetailCompteClientDAOImpl implements DetailCompteClientDAO {
     Session session=HibernateUtil.openSession();
  
     
     
     
     public DetailCompteClient findById(int id) {
		return (DetailCompteClient)session.get(DetailCompteClient.class, id);
		}

 
    
	public void add(DetailCompteClient detailCompteClient) {
		session.beginTransaction();
		session.save(detailCompteClient);
		
		session.getTransaction().commit();
		//return p;
	}


	public DetailCompteClient edit(DetailCompteClient e) {
		
	session.beginTransaction();
	DetailCompteClient p= (DetailCompteClient)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(DetailCompteClient e) {
		
		session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
    public List<DetailCompteClient> findAll() {
              return session.createQuery("select c from DetailCompteClient c").list();
    }

    
             public List<Object[]> findByMontantCredit(int compteClient ) {
        Query query=  session.createQuery("select sum(u.montantCredit), sum(u.montantDebit) from DetailCompteClient u where u.compteClient.id =:compteClient");
        query.setParameter("compteClient", compteClient);
        
        return query.list();
    }
  
             public List<DetailCompteClient> findByCompteClient(int compteClient ) {
        Query query=  session.createQuery("select u from DetailCompteClient u where u.compteClient.id =:compteClient");
        query.setParameter("compteClient", compteClient);
        
        return query.list();
    }
             
}
