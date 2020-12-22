/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.VersementChequeClient;
import dao.Manager.VersementChequeClientDAO;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author pc
 */
public class VersementChequeClientDAOImpl implements VersementChequeClientDAO{
    
      Session session=HibernateUtil.openSession();

    @Override
    public VersementChequeClient findById(int id) {

return (VersementChequeClient)session.get(VersementChequeClient.class, id);    }

    @Override
    public void add(VersementChequeClient versementChequeClient) {

               session.beginTransaction();
		session.save(versementChequeClient);
		
		session.getTransaction().commit();    }

    @Override
    public VersementChequeClient edit(VersementChequeClient e) {

session.beginTransaction();
	VersementChequeClient p= (VersementChequeClient)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(VersementChequeClient e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<VersementChequeClient> findAll() {

return session.createQuery("select c from VersementChequeClient c").list();

    }
    
    
                         public List<VersementChequeClient> findByVendeur(int vendeur, int depot) {
	Query query = session.createQuery("select c from VersementChequeClient c where c.versement.tournee.vendeur.id=:vendeur and c.utilisateurCreation.depot.id=:depot");
        query.setParameter("vendeur", vendeur);
        query.setParameter("depot", depot);

        return  query.list();
    
        }
    
                  public List<VersementChequeClient> findByDate(Date dateEch, int depot) {
	Query query = session.createQuery("select c from VersementChequeClient c where c.dateEcheance =:dateEch and c.utilisateurCreation.depot.id=:depot");
        query.setParameter("dateEch", dateEch);
        query.setParameter("depot", depot);

        return  query.list();
    
        }
    
                    public List<VersementChequeClient> findByTypeCheque(String typeCheque, int depot) {
	Query query = session.createQuery("select c from VersementChequeClient c where c.typeCheque =:typeCheque  and c.utilisateurCreation.depot.id=:depot");
        query.setParameter("typeCheque", typeCheque);
        query.setParameter("depot", depot);

        return  query.list();
    
        }
                    
                     public List<VersementChequeClient> findByVendeurAndDateEch(int vendeur,Date dateEch, int depot) {
	Query query = session.createQuery("select c from VersementChequeClient c where c.versement.tournee.vendeur.id=:vendeur and c.dateEcheance =:dateEch and c.utilisateurCreation.depot.id=:depot");
        query.setParameter("vendeur", vendeur);
        query.setParameter("dateEch", dateEch);
        query.setParameter("depot", depot);

        return  query.list();
    
        }
                                    
                    public List<VersementChequeClient> findByVendeurAndTypeCheque(int vendeur,String typeCheque, int depot) {
	Query query = session.createQuery("select c from VersementChequeClient c where c.versement.tournee.vendeur.id=:vendeur and c.typeCheque =:typeCheque and c.utilisateurCreation.depot.id=:depot");
        query.setParameter("vendeur", vendeur);
        query.setParameter("typeCheque", typeCheque);
        query.setParameter("depot", depot);

        return  query.list();
    
        }
                    
          
                                public List<VersementChequeClient> findByDateEchAndTypeCheque(Date dateEch,String typeCheque, int depot) {
	Query query = session.createQuery("select c from VersementChequeClient c where c.dateEcheance =:dateEch and c.typeCheque =:typeCheque and c.utilisateurCreation.depot.id=:depot");
        query.setParameter("dateEch", dateEch);
        query.setParameter("typeCheque", typeCheque);
        query.setParameter("depot", depot);

        return  query.list();
    
        }
                                
         
                      public List<VersementChequeClient> findByVendeurAndDateEchAndTypeCheque(int vendeur,Date dateEch, String typeCheque, int depot) {
	Query query = session.createQuery("select c from VersementChequeClient c where c.versement.tournee.vendeur.id=:vendeur and c.dateEcheance =:dateEch and c.typeCheque =:typeCheque and c.utilisateurCreation.depot.id=:depot");
        query.setParameter("typeCheque", typeCheque);
        query.setParameter("vendeur", vendeur);
        query.setParameter("dateEch", dateEch);
        query.setParameter("depot", depot);

        return  query.list();
    
        }
                      
 
}
