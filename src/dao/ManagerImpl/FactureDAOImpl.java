/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import dao.Entity.Article;
import dao.Manager.ArticleDAO;
import java.util.List;
import org.hibernate.Session;
import Utils.HibernateUtil;
import dao.Entity.Facture;
import dao.Manager.FactureDAO;
import org.hibernate.Query;

/**
 *
 * @author pc
 */
public class FactureDAOImpl implements FactureDAO {
     Session session=HibernateUtil.openSession();

    @Override
    public Facture findById(int id) {

return (Facture)session.get(Facture.class, id);    }

    @Override
    public void add(Facture facture) {

               session.beginTransaction();
		session.save(facture);
		
		session.getTransaction().commit();    }

    @Override
    public Facture edit(Facture e) {

session.beginTransaction();
	Facture p= (Facture)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(Facture e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<Facture> findAll() {

return session.createQuery("select c from Facture c").list();

    }
    
     
      public List<Facture> findFactureByTournee(int idtournee ) {
	Query query = session.createQuery("select c from Facture c where c.tournee.id=:idtournee");
                query.setParameter("idtournee",idtournee);
           
                        return  query.list();
    
        }
    
    
    
    
}
