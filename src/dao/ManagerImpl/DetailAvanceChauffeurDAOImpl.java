/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.DetailAvanceChauffeur;
import dao.Manager.DetailAvanceChauffeurDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class DetailAvanceChauffeurDAOImpl implements DetailAvanceChauffeurDAO{
    
      Session session=HibernateUtil.openSession();

    @Override
    public DetailAvanceChauffeur findById(int id) {

return (DetailAvanceChauffeur)session.get(DetailAvanceChauffeur.class, id);    }

    @Override
    public void add(DetailAvanceChauffeur DetailAvanceChauffeur) {

               session.beginTransaction();
		session.save(DetailAvanceChauffeur);
		
		session.getTransaction().commit();    }

    @Override
    public DetailAvanceChauffeur edit(DetailAvanceChauffeur e) {

session.beginTransaction();
	DetailAvanceChauffeur p= (DetailAvanceChauffeur)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(DetailAvanceChauffeur e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<DetailAvanceChauffeur> findAll() {

return session.createQuery("select c from DetailAvanceChauffeur c").list();

    }
    
                	public List<DetailAvanceChauffeur> findByAvanceChauffeur(int avChauff) {
		
		Query query = session.createQuery("select u from DetailAvanceChauffeur u where u.avanceChauffeur.id =:avChauff");
		query.setParameter("avChauff",avChauff);
               
                return query.list();
 }
    
}
