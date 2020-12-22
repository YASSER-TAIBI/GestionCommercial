/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.VersementCheque;
import dao.Manager.VersementChequeDAO;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author pc
 */
public class VersementChequeDAOImpl implements VersementChequeDAO{
    
      Session session=HibernateUtil.openSession();

    @Override
    public VersementCheque findById(int id) {

return (VersementCheque)session.get(VersementCheque.class, id);    }

    @Override
    public void add(VersementCheque versementCheque) {

               session.beginTransaction();
		session.save(versementCheque);
		
		session.getTransaction().commit();    }

    @Override
    public VersementCheque edit(VersementCheque e) {

session.beginTransaction();
	VersementCheque p= (VersementCheque)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(VersementCheque e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<VersementCheque> findAll() {

return session.createQuery("select c from VersementCheque c").list();

    }
    
    
                         public List<VersementCheque> findByVendeur(int vendeur, int depot) {
	Query query = session.createQuery("select c from VersementCheque c where c.versement.tournee.vendeur.id=:vendeur and c.utilisateurCreation.depot.id=:depot");
        query.setParameter("vendeur", vendeur);
        query.setParameter("depot", depot);

        return  query.list();
    
        }
    
                  public List<VersementCheque> findByDate(Date dateEch, int depot) {
	Query query = session.createQuery("select c from VersementCheque c where c.dateEcheance =:dateEch and c.utilisateurCreation.depot.id=:depot");
        query.setParameter("dateEch", dateEch);
        query.setParameter("depot", depot);

        return  query.list();
    
        }
    
                    public List<VersementCheque> findByTypeCheque(String typeCheque, int depot) {
	Query query = session.createQuery("select c from VersementCheque c where c.typeCheque =:typeCheque  and c.utilisateurCreation.depot.id=:depot");
        query.setParameter("typeCheque", typeCheque);
        query.setParameter("depot", depot);

        return  query.list();
    
        }
                    
                     public List<VersementCheque> findByVendeurAndDateEch(int vendeur,Date dateEch, int depot) {
	Query query = session.createQuery("select c from VersementCheque c where c.versement.tournee.vendeur.id=:vendeur and c.dateEcheance =:dateEch and c.utilisateurCreation.depot.id=:depot");
        query.setParameter("vendeur", vendeur);
        query.setParameter("dateEch", dateEch);
        query.setParameter("depot", depot);

        return  query.list();
    
        }
                                    
                    public List<VersementCheque> findByVendeurAndTypeCheque(int vendeur,String typeCheque, int depot) {
	Query query = session.createQuery("select c from VersementCheque c where c.versement.tournee.vendeur.id=:vendeur and c.typeCheque =:typeCheque and c.utilisateurCreation.depot.id=:depot");
        query.setParameter("vendeur", vendeur);
        query.setParameter("typeCheque", typeCheque);
        query.setParameter("depot", depot);

        return  query.list();
    
        }
                    
          
                                public List<VersementCheque> findByDateEchAndTypeCheque(Date dateEch,String typeCheque, int depot) {
	Query query = session.createQuery("select c from VersementCheque c where c.dateEcheance =:dateEch and c.typeCheque =:typeCheque and c.utilisateurCreation.depot.id=:depot");
        query.setParameter("dateEch", dateEch);
        query.setParameter("typeCheque", typeCheque);
        query.setParameter("depot", depot);

        return  query.list();
    
        }
                                
         
                      public List<VersementCheque> findByVendeurAndDateEchAndTypeCheque(int vendeur,Date dateEch, String typeCheque, int depot) {
	Query query = session.createQuery("select c from VersementCheque c where c.versement.tournee.vendeur.id=:vendeur and c.dateEcheance =:dateEch and c.typeCheque =:typeCheque and c.utilisateurCreation.depot.id=:depot");
        query.setParameter("typeCheque", typeCheque);
        query.setParameter("vendeur", vendeur);
        query.setParameter("dateEch", dateEch);
        query.setParameter("depot", depot);

        return  query.list();
    
        }
                      
 
}
