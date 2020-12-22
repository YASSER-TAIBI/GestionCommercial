/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.Banque;
import dao.Manager.BanqueDAO;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author pc
 */
public class BanqueDAOImpl implements BanqueDAO{
    
      Session session=HibernateUtil.openSession();

    @Override
    public Banque findById(int id) {

return (Banque)session.get(Banque.class, id);    }

    @Override
    public void add(Banque banque) {

               session.beginTransaction();
		session.save(banque);
		
		session.getTransaction().commit();    }

    @Override
    public Banque edit(Banque e) {

session.beginTransaction();
	Banque p= (Banque)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(Banque e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<Banque> findAll() {

return session.createQuery("select c from Banque c").list();

    }
    
    
    
    
}
