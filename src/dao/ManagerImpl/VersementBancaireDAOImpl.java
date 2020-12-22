/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.VersementBancaire;
import dao.Manager.VersementBancaireDAO;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author pc
 */
public class VersementBancaireDAOImpl implements VersementBancaireDAO{
    
      Session session=HibernateUtil.openSession();

    @Override
    public VersementBancaire findById(int id) {

return (VersementBancaire)session.get(VersementBancaire.class, id);    }

    @Override
    public void add(VersementBancaire versementBancaire) {

               session.beginTransaction();
		session.save(versementBancaire);
		
		session.getTransaction().commit();    }

    @Override
    public VersementBancaire edit(VersementBancaire e) {

session.beginTransaction();
	VersementBancaire p= (VersementBancaire)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(VersementBancaire e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<VersementBancaire> findAll() {

return session.createQuery("select c from VersementBancaire c").list();

    }
    
       public List<VersementBancaire> findByVendeur(int vendeur, int depot) {
	Query query = session.createQuery("select c from VersementBancaire c where c.versement.tournee.vendeur.id=:vendeur and c.utilisateurCreation.depot.id=:depot");
        query.setParameter("vendeur", vendeur);
        query.setParameter("depot", depot);

        return  query.list();
    
        }
    
                  public List<VersementBancaire> findByDate(Date date, int depot) {
	Query query = session.createQuery("select c from VersementBancaire c where c.dateVersement =:date and c.utilisateurCreation.depot.id=:depot");
        query.setParameter("date", date);
        query.setParameter("depot", depot);

        return  query.list();
    
        }
    
                    public List<VersementBancaire> findByBanque(int banque, int depot) {
	Query query = session.createQuery("select c from VersementBancaire c where c.banque.id =:banque  and c.utilisateurCreation.depot.id=:depot");
        query.setParameter("banque", banque);
        query.setParameter("depot", depot);

        return  query.list();
    
        }
                    
                     public List<VersementBancaire> findByVendeurAndDate(int vendeur,Date date, int depot) {
	Query query = session.createQuery("select c from VersementBancaire c where c.versement.tournee.vendeur.id=:vendeur and c.dateVersement =:date and c.utilisateurCreation.depot.id=:depot");
        query.setParameter("vendeur", vendeur);
        query.setParameter("date", date);
        query.setParameter("depot", depot);

        return  query.list();
    
        }
                                    
                    public List<VersementBancaire> findByVendeurAndBanque(int vendeur,int banque, int depot) {
	Query query = session.createQuery("select c from VersementBancaire c where c.versement.tournee.vendeur.id=:vendeur and c.banque.id =:banque and c.utilisateurCreation.depot.id=:depot");
        query.setParameter("vendeur", vendeur);
        query.setParameter("banque", banque);
        query.setParameter("depot", depot);

        return  query.list();
    
        }
                    
          
                                public List<VersementBancaire> findByDateAndBanque(Date date,int banque, int depot) {
	Query query = session.createQuery("select c from VersementBancaire c where c.dateVersement =:date and c.banque.id =:banque and c.utilisateurCreation.depot.id=:depot");
        query.setParameter("date", date);
        query.setParameter("banque", banque);
        query.setParameter("depot", depot);

        return  query.list();
    
        }
                                
         
                      public List<VersementBancaire> findByVendeurAndDateAndBanque(int vendeur,Date date,int banque, int depot) {
	Query query = session.createQuery("select c from VersementBancaire c where c.versement.tournee.vendeur.id=:vendeur and c.dateVersement =:date and c.banque.id =:banque and c.utilisateurCreation.depot.id=:depot");
        query.setParameter("banque", banque);
        query.setParameter("vendeur", vendeur);
        query.setParameter("date", date);
        query.setParameter("depot", depot);

        return  query.list();
    
        }
    
    
}
