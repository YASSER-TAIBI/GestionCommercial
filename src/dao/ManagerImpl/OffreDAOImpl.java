/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import dao.Entity.Offre;
import java.util.List;
import org.hibernate.Session;
import Utils.HibernateUtil;
import org.hibernate.Query;
import dao.Manager.OffreDAO;
import java.util.Date;

/**
 *
 * @author pc
 */
public class OffreDAOImpl implements OffreDAO {
     Session session=HibernateUtil.openSession();

    @Override
    public Offre findById(int id) {

return (Offre)session.get(Offre.class, id);    }

    @Override
    public void add(Offre offre) {

               session.beginTransaction();
		session.save(offre);
		
		session.getTransaction().commit();    }

    @Override
    public Offre edit(Offre e) {

session.beginTransaction();
	Offre p= (Offre)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(Offre e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<Offre> findAll() {

return session.createQuery("select c from Offre c").list();

    }
    
          public List<Offre> findByCodeOffre(String code) {
	Query query = session.createQuery("select c from Offre c where c.codeOffre=:code");
                query.setParameter("code",code);
           
                        return  query.list();
    
        }

          public List<Offre> findByDateAndArticleAndSecteur(Date date,int art, int secteur) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from Offre c where c.article.id =:art and c.creationOffre.dateDebut>= :date and c.creationOffre.dateFin<= :date and c.secteur.id =:secteur");
		query.setParameter("date",date);
                query.setParameter("art",art);
                query.setParameter("secteur",secteur);
		  return  query.list();
                        
                        
          }
          
          
}
