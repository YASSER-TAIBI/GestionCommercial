/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import dao.Entity.ConditionOffre;
import dao.Manager.ConditionOffreDAO;
import java.util.List;
import org.hibernate.Session;
import Utils.HibernateUtil;
import java.util.Date;
import org.hibernate.Query;

/**
 *
 * @author pc
 */
public class ConditionOffreDAOImpl implements ConditionOffreDAO {
     Session session=HibernateUtil.openSession();

    @Override
    public ConditionOffre findById(int id) {

return (ConditionOffre)session.get(ConditionOffre.class, id);    }

    @Override
    public void add(ConditionOffre conditionOffre) {

               session.beginTransaction();
		session.save(conditionOffre);
		
		session.getTransaction().commit();    }

    @Override
    public ConditionOffre edit(ConditionOffre e) {

session.beginTransaction();
	ConditionOffre p= (ConditionOffre)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(ConditionOffre e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<ConditionOffre> findAll() {

return session.createQuery("select c from ConditionOffre c").list();

    }
   
       public ConditionOffre findByDateAndArticleAndSecteur(Date date,int art,int secteur) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from ConditionOffre c where c.article.id =:art and c.creationOffre.dateDebut>= :date and c.creationOffre.dateFin<= :date and c.secteur.id =:secteur");
		query.setParameter("date",date);
                query.setParameter("art",art);
                query.setParameter("secteur",secteur);
                
		return  (ConditionOffre) query.uniqueResult();
                        
                        
          }
       
}
