/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import dao.Entity.Secteur;
import dao.Manager.SecteurDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.hql.internal.antlr.HqlSqlTokenTypes;
import Utils.HibernateUtil;

/**
 *
 * @author pc
 */
public class SecteurDAOImpl implements SecteurDAO {
     Session session=HibernateUtil.openSession();

    @Override
    public Secteur findById(int id) {

return (Secteur)session.get(Secteur.class, id);    }

    @Override
    public void add(Secteur clientpf) {

               session.beginTransaction();
		session.save(clientpf);
		
		session.getTransaction().commit();    }

    @Override
    public Secteur edit(Secteur e) {

session.beginTransaction();
	Secteur p= (Secteur)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(Secteur e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<Secteur> findAll() {

return session.createQuery("select c from Secteur c").list();
    
}

    public Secteur findByNom(String nom) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from Secteur c where c.libelle=:nom");
	
		query.setParameter("nom", nom);
		
		return (Secteur) query.uniqueResult();
		
	}

    
  public List<Secteur> findBySecteur(String nom) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from Secteur c where c.libelle <>:nom");
	
		query.setParameter("nom", nom);
		
		  return query.list(); 
		
	}
   
    public List<Secteur> findSecteurByVille(int ville) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from Secteur c where c.ville.id =:ville");
	
		query.setParameter("ville", ville);
		
		  return query.list(); 
		
	}
    
       public List<Secteur> findSecteurByDepot(int depot) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from Secteur c where c.ville.depot.id =:depot");
	
		query.setParameter("depot", depot);
		
		  return query.list(); 
		
	}
    
}
