/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.DetailVenteMixte;
import dao.Manager.DetailVenteMixteDAO;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class DetailVenteMixteDAOImpl  implements DetailVenteMixteDAO{
    
      Session session=HibernateUtil.openSession();

    @Override
    public DetailVenteMixte findById(int id) {

return (DetailVenteMixte)session.get(DetailVenteMixte.class, id);    }

    @Override
    public void add(DetailVenteMixte detailVenteMixte) {

               session.beginTransaction();
               	session.save(detailVenteMixte);
		session.getTransaction().commit();    }

    @Override
    public DetailVenteMixte edit(DetailVenteMixte e) {

         session.beginTransaction();
	DetailVenteMixte p= (DetailVenteMixte)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(DetailVenteMixte e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<DetailVenteMixte> findAll() {

return session.createQuery("select c from DetailVenteMixte c").list();

    }
    
    
}
