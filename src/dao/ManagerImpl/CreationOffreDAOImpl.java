/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import dao.Entity.CreationOffre;
import dao.Manager.CreationOffreDAO;
import java.util.List;
import org.hibernate.Session;
import Utils.HibernateUtil;
import org.hibernate.Query;

/**
 *
 * @author pc
 */
public class CreationOffreDAOImpl implements CreationOffreDAO {
     Session session=HibernateUtil.openSession();

    @Override
    public CreationOffre findById(int id) {

return (CreationOffre)session.get(CreationOffre.class, id);    }

    @Override
    public void add(CreationOffre creationOffre) {

               session.beginTransaction();
		session.save(creationOffre);
		
		session.getTransaction().commit();    }

    @Override
    public CreationOffre edit(CreationOffre e) {

session.beginTransaction();
	CreationOffre p= (CreationOffre)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(CreationOffre e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<CreationOffre> findAll() {

return session.createQuery("select c from CreationOffre c").list();

    }
   
    
}
