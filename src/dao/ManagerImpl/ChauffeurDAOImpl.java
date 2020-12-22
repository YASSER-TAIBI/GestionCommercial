/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.Chauffeur;
import dao.Manager.ChauffeurDAO;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class ChauffeurDAOImpl implements ChauffeurDAO{
    
      Session session=HibernateUtil.openSession();

    @Override
    public Chauffeur findById(int id) {

return (Chauffeur)session.get(Chauffeur.class, id);    }

    @Override
    public void add(Chauffeur chauffeur) {

               session.beginTransaction();
		session.save(chauffeur);
		
		session.getTransaction().commit();    }

    @Override
    public Chauffeur edit(Chauffeur e) {

session.beginTransaction();
	Chauffeur p= (Chauffeur)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(Chauffeur e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<Chauffeur> findAll() {

return session.createQuery("select c from Chauffeur c").list();

    }
    
}
