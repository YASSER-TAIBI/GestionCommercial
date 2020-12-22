/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.DetailAvanceMagasinier;
import dao.Manager.DetailAvanceMagasinierDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class DetailAvanceMagasinierDAOImpl implements DetailAvanceMagasinierDAO{
    
      Session session=HibernateUtil.openSession();

    @Override
    public DetailAvanceMagasinier findById(int id) {

return (DetailAvanceMagasinier)session.get(DetailAvanceMagasinier.class, id);    }

    @Override
    public void add(DetailAvanceMagasinier DetailAvanceMagasinier) {

               session.beginTransaction();
		session.save(DetailAvanceMagasinier);
		
		session.getTransaction().commit();    }

    @Override
    public DetailAvanceMagasinier edit(DetailAvanceMagasinier e) {

session.beginTransaction();
	DetailAvanceMagasinier p= (DetailAvanceMagasinier)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(DetailAvanceMagasinier e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<DetailAvanceMagasinier> findAll() {

return session.createQuery("select c from DetailAvanceMagasinier c").list();

    }
    
                  	public List<DetailAvanceMagasinier> findByAvanceMagasiniers(int avMag) {
		
		Query query = session.createQuery("select u from DetailAvanceMagasinier u where u.avanceMagasinier.id =:avMag");
		query.setParameter("avMag",avMag);
               
                return query.list();
 }
    
}
