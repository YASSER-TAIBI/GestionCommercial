/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.AvanceChauffeur;
import dao.Manager.AvanceChauffeurDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class AvanceChauffeurDAOImpl implements AvanceChauffeurDAO{
    
      Session session=HibernateUtil.openSession();

    @Override
    public AvanceChauffeur findById(int id) {

return (AvanceChauffeur)session.get(AvanceChauffeur.class, id);    }

    @Override
    public void add(AvanceChauffeur AvanceChauffeur) {

               session.beginTransaction();
		session.save(AvanceChauffeur);
		
		session.getTransaction().commit();    }

    @Override
    public AvanceChauffeur edit(AvanceChauffeur e) {

session.beginTransaction();
	AvanceChauffeur p= (AvanceChauffeur)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(AvanceChauffeur e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<AvanceChauffeur> findAll() {

return session.createQuery("select c from AvanceChauffeur c").list();

    }
    
    	public List<AvanceChauffeur> findByChauffeur(int Chauf) {
		
		Query query = session.createQuery("select u from AvanceChauffeur u where u.chauffeur.id =:Chauf");
		
                query.setParameter("Chauf",Chauf);
                return query.list();
                        }
}
