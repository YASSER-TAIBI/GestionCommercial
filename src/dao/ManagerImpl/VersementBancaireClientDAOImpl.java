/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.VersementBancaireClient;
import dao.Manager.VersementBancaireClientDAO;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author pc
 */
public class VersementBancaireClientDAOImpl implements VersementBancaireClientDAO{
    
      Session session=HibernateUtil.openSession();

    @Override
    public VersementBancaireClient findById(int id) {

return (VersementBancaireClient)session.get(VersementBancaireClient.class, id);    }

    @Override
    public void add(VersementBancaireClient versementBancaireClient) {

               session.beginTransaction();
		session.save(versementBancaireClient);
		
		session.getTransaction().commit();    }

    @Override
    public VersementBancaireClient edit(VersementBancaireClient e) {

session.beginTransaction();
	VersementBancaireClient p= (VersementBancaireClient)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(VersementBancaireClient e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<VersementBancaireClient> findAll() {

return session.createQuery("select c from VersementBancaireClient c").list();

    }
    
       public List<VersementBancaireClient> findByVendeur(int vendeur, int depot) {
	Query query = session.createQuery("select c from VersementBancaireClient c where c.versement.tournee.vendeur.id=:vendeur and c.utilisateurCreation.depot.id=:depot");
        query.setParameter("vendeur", vendeur);
        query.setParameter("depot", depot);

        return  query.list();
    
        }
    
                  public List<VersementBancaireClient> findByDate(Date date, int depot) {
	Query query = session.createQuery("select c from VersementBancaireClient c where c.dateVersement =:date and c.utilisateurCreation.depot.id=:depot");
        query.setParameter("date", date);
        query.setParameter("depot", depot);

        return  query.list();
    
        }
    
                    public List<VersementBancaireClient> findByBanque(int banque, int depot) {
	Query query = session.createQuery("select c from VersementBancaireClient c where c.banque.id =:banque  and c.utilisateurCreation.depot.id=:depot");
        query.setParameter("banque", banque);
        query.setParameter("depot", depot);

        return  query.list();
    
        }
                    
                     public List<VersementBancaireClient> findByVendeurAndDate(int vendeur,Date date, int depot) {
	Query query = session.createQuery("select c from VersementBancaireClient c where c.versement.tournee.vendeur.id=:vendeur and c.dateVersement =:date and c.utilisateurCreation.depot.id=:depot");
        query.setParameter("vendeur", vendeur);
        query.setParameter("date", date);
        query.setParameter("depot", depot);

        return  query.list();
    
        }
                                    
                    public List<VersementBancaireClient> findByVendeurAndBanque(int vendeur,int banque, int depot) {
	Query query = session.createQuery("select c from VersementBancaireClient c where c.versement.tournee.vendeur.id=:vendeur and c.banque.id =:banque and c.utilisateurCreation.depot.id=:depot");
        query.setParameter("vendeur", vendeur);
        query.setParameter("banque", banque);
        query.setParameter("depot", depot);

        return  query.list();
    
        }
                    
          
                                public List<VersementBancaireClient> findByDateAndBanque(Date date,int banque, int depot) {
	Query query = session.createQuery("select c from VersementBancaireClient c where c.dateVersement =:date and c.banque.id =:banque and c.utilisateurCreation.depot.id=:depot");
        query.setParameter("date", date);
        query.setParameter("banque", banque);
        query.setParameter("depot", depot);

        return  query.list();
    
        }
                                
         
                      public List<VersementBancaireClient> findByVendeurAndDateAndBanque(int vendeur,Date date,int banque, int depot) {
	Query query = session.createQuery("select c from VersementBancaireClient c where c.versement.tournee.vendeur.id=:vendeur and c.dateVersement =:date and c.banque.id =:banque and c.utilisateurCreation.depot.id=:depot");
        query.setParameter("banque", banque);
        query.setParameter("vendeur", vendeur);
        query.setParameter("date", date);
        query.setParameter("depot", depot);

        return  query.list();
    
        }
    
    
}
