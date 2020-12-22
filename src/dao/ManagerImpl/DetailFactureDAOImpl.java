/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.DetailFacture;
import dao.Entity.DetailTournee;
import dao.Manager.DetailFactureDAO;
import dao.Manager.DetailTourneeDAO;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */

public class DetailFactureDAOImpl implements DetailFactureDAO{
    
      Session session=HibernateUtil.openSession();

    @Override
    public DetailFacture findById(int id) {

return (DetailFacture)session.get(DetailFacture.class, id);    }

    @Override
    public void add(DetailFacture detailFacture) {

               session.beginTransaction();
               	session.save(detailFacture);
		session.getTransaction().commit();    }

    @Override
    public DetailFacture edit(DetailFacture e) {

session.beginTransaction();
	DetailFacture p= (DetailFacture)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(DetailFacture e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<DetailFacture> findAll() {

return session.createQuery("select c from DetailFacture c").list();

    }
    
                public List<DetailFacture> findByDateAndVendeurAndTourneeAndMagasinAndArticle(Date date,int vendeur,int tournee,int magasin, int article) {
	Query query = session.createQuery("select c from DetailFacture c where c.facture.dateFacture =:date and c.facture.tournee.id =:tournee and c.facture.magasin.id =:magasin and c.facture.vendeur.id =:vendeur and c.article.id =:article");
                query.setParameter("date",date);
                query.setParameter("tournee",tournee);
                query.setParameter("magasin",magasin);
                query.setParameter("vendeur",vendeur);
                query.setParameter("article", article);
                        return  query.list();
    
        }
 
                public List<DetailFacture> findByTournee(int idtournee ) {
	Query query = session.createQuery("select c from DetailFacture c where c.facture.tournee.id=:idtournee");
                query.setParameter("idtournee",idtournee);
        
                        return  query.list();
    
        }
                           
}
