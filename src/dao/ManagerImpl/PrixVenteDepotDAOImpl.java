/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.PrixVenteDepot;
import dao.Manager.PrixVenteDepotDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class PrixVenteDepotDAOImpl implements PrixVenteDepotDAO{
    
      Session session=HibernateUtil.openSession();

    @Override
    public PrixVenteDepot findById(int id) {

            return (PrixVenteDepot)
        session.get(PrixVenteDepot.class, id);    }

    @Override
    public void add(PrixVenteDepot prixVenteDepot) {

               session.beginTransaction();
		session.save(prixVenteDepot);
		
		session.getTransaction().commit();    }

    @Override
    public PrixVenteDepot edit(PrixVenteDepot e) {

session.beginTransaction();
	PrixVenteDepot p= (PrixVenteDepot)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(PrixVenteDepot e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<PrixVenteDepot> findAll() {

return session.createQuery("select c from PrixVenteDepot c").list();

    }
 
    
        public PrixVenteDepot findPrixBySecteurAndTypeVente(int Art,int Tvent,int Secteur) {
	Query query = session.createQuery("select c from PrixVenteDepot c where c.article.id =:Art and c.typeVente.id =:Tvent and c.secteur.id =:Secteur");
                query.setParameter("Art",Art);
                query.setParameter("Tvent",Tvent);
                query.setParameter("Secteur",Secteur);

   
                 return  (PrixVenteDepot)query.uniqueResult();
    
        }
}
