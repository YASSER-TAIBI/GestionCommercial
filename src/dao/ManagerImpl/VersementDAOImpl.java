/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.Versement;
import dao.Manager.VersementDAO;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class VersementDAOImpl implements VersementDAO{
    
    Session session=HibernateUtil.openSession();

    @Override
    public Versement findById(int id) {

return (Versement)session.get(Versement.class, id);    }

    @Override
    public void add(Versement versement) {

               session.beginTransaction();
		session.save(versement);
		
		session.getTransaction().commit();    }

    @Override
    public Versement edit(Versement e) {

session.beginTransaction();
	Versement p= (Versement)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(Versement e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<Versement> findAll() {

return session.createQuery("select c from Versement c").list();

    }
    
              public List<Versement> findVersemenBancairetByTournee(int vendeur, String etat) {
	Query query = session.createQuery("select c from Versement c where c.tournee.vendeur.id =:vendeur AND c.etat=:etat AND c.montantBanque <> 0 AND c.etatVersementBancaire = FALSE");
                query.setParameter("vendeur",vendeur);
                query.setParameter("etat",etat);
                
                        return  query.list();
    
        }
    
              public List<Versement> findVersementChequeByTournee(int vendeur, String etat) {
	Query query = session.createQuery("select c from Versement c where c.tournee.vendeur.id =:vendeur AND c.etat=:etat AND c.montantCheque <> 0 AND c.etatVersementCheque = FALSE");
                query.setParameter("vendeur",vendeur);
                query.setParameter("etat",etat);
                
                        return  query.list();
    
        }

                        public Versement findByTournee(int tournee) {
		
		Query query = session.createQuery("select u from Versement u where u.tournee.id=:tournee");
		query.setParameter("tournee",tournee);
    
                return (Versement)query.uniqueResult();
}
            
                        public List<Object[]> findByDepotAndCaissier(int depot,int caissier) {
	Query query = session.createQuery("select c.tournee.vendeur.nom,sum(c.montantCheque), COALESCE(sum(c.montantVersementCheque),0),sum(c.montantCheque)-COALESCE(sum(c.montantVersementCheque),0) from Versement c where c.tournee.depot.id=:depot and c.caissier.id=:caissier group by c.tournee.vendeur.nom");
                query.setParameter("depot",depot);
                query.setParameter("caissier",caissier);
                
                        return  query.list();
    
        }
}
