/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import dao.Entity.Magasin;
import dao.Manager.MagasinDAO;
import java.util.List;
import org.hibernate.Session;
import Utils.HibernateUtil;
import org.hibernate.Query;

/**
 *
 * @author pc
 */
public class MagasinDAOImpl implements MagasinDAO {
    
     Session session=HibernateUtil.openSession();

    @Override
    public Magasin findById(int id) {

return (Magasin)session.get(Magasin.class, id);    }

    @Override
    public void add(Magasin magasin) {

               session.beginTransaction();
		session.save(magasin);
		
		session.getTransaction().commit();    }

    @Override
    public Magasin edit(Magasin e) {

        session.beginTransaction();
	Magasin d= (Magasin)session.merge(e);
	session.getTransaction().commit();
		return d;

    }

    @Override
    public void delete(Magasin e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<Magasin> findAll() {

return session.createQuery("select m from Magasin m").list();
    
}
    
    
                public List<Magasin> findMagasinByDepot(int depot) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from Magasin c where c.depot.id =:depot");
	
		query.setParameter("depot", depot);
		
		   return query.list(); 
		
	} 
   
                public Magasin findMagasinByDepotUnique(int depot) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from Magasin c where c.depot.id =:depot");
	
		query.setParameter("depot", depot);
		
		   return  (Magasin)query.uniqueResult();
		
	} 
}
    
    
    