/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.AvanceMagasinier;
import dao.Manager.AvanceMagasinierDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class AvanceMagasinierDAOImpl implements AvanceMagasinierDAO{
    
      Session session=HibernateUtil.openSession();

    @Override
    public AvanceMagasinier findById(int id) {

return (AvanceMagasinier)session.get(AvanceMagasinier.class, id);    }

    @Override
    public void add(AvanceMagasinier AvanceMagasinier) {

               session.beginTransaction();
		session.save(AvanceMagasinier);
		
		session.getTransaction().commit();    }

    @Override
    public AvanceMagasinier edit(AvanceMagasinier e) {

session.beginTransaction();
	AvanceMagasinier p= (AvanceMagasinier)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(AvanceMagasinier e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<AvanceMagasinier> findAll() {

return session.createQuery("select c from AvanceMagasinier c").list();

    }
    	public List<AvanceMagasinier> findByDepotAndMagasin(int depot,int magasin) {
		
		Query query = session.createQuery("select u from AvanceMagasinier u where u.depot.id=:depot and u.magasin.id=:magasin");
		
                query.setParameter("depot",depot);
                query.setParameter("magasin",magasin);
                return query.list();
                        }
}
