/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import dao.Entity.Depot;
import dao.Manager.DepotDAO;
import java.util.List;
import org.hibernate.Session;
import Utils.HibernateUtil;
import org.hibernate.Query;

/**
 *
 * @author pc
 */
public class DepotDAOImpl implements DepotDAO{
    
     Session session=HibernateUtil.openSession();

    @Override
    public Depot findById(int id) {

return (Depot)session.get(Depot.class, id);    }

    @Override
    public void add(Depot clientpf) {

               session.beginTransaction();
		session.save(clientpf);
		
		session.getTransaction().commit();    }

    @Override
    public Depot edit(Depot e) {

session.beginTransaction();
	Depot d= (Depot)session.merge(e);
	session.getTransaction().commit();
		return d;

    }

    @Override
    public void delete(Depot e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<Depot> findAll() {

return session.createQuery("select d from Depot d").list();
    
}
    
       public List<Depot> findByDepot(int id) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from Depot c where c.id <>:id");
	
		query.setParameter("id", id);
		
		  return query.list(); 
		
	}
    
       
          public List<Depot> findByDepotSaufSiege() {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from Depot c where c.code <>'D_SIE'");

		  return query.list(); 
		
	}
}
