/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.Tournee;
import dao.Manager.TourneeDAO;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */

public class TourneeDAOImpl implements TourneeDAO{
    
      Session session=HibernateUtil.openSession();

    @Override
    public Tournee findById(int id) {

return (Tournee)session.get(Tournee.class, id);    }

    @Override
    public void add(Tournee tournee) {

               session.beginTransaction();
		session.save(tournee);
		
		session.getTransaction().commit();    }

    
      @Override
    public Tournee edit(Tournee e) {

session.beginTransaction();
	Tournee p= (Tournee)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(Tournee e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<Tournee> findAll() {

return session.createQuery("select c from Tournee c").list();

    }

                public Tournee findTourneeByVendeur(String nom) {
		
		Query query = session.createQuery("select u from Tournee u where u.vendeur.nom =:nom");
		query.setParameter("nom",nom);
    
                return (Tournee)query.uniqueResult();
}
      public int getMaxId() {
          int id=0;
        Query query= session.createQuery("select Max(id) from Tournee c");
        
        if( query.uniqueResult()==null)
            id= 1;
        else 
            id= (int) query.uniqueResult();
        
        
       return id;
    }
      
                	public Tournee findDetailTrnByTrn(Date date,int secteur,int depot,int magasin,int vendeur) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from Tournee c where c.dateTournee =:date and c.secteur.id =:secteur and c.depot.id =:depot and c.magasin.id =:magasin and c.vendeur.id =:vendeur");
		query.setParameter("date",date);
                query.setParameter("secteur",secteur);
                query.setParameter("depot",depot);
                query.setParameter("magasin",magasin);
                query.setParameter("vendeur",vendeur);
                
                
		return  (Tournee) query.uniqueResult();
                        
                        
          }
               
      
            public List<Tournee> findTourneeByDetailOverte(String etat) {
	Query query = session.createQuery("select c from Tournee c where c.statue =:etat ");

                query.setParameter("etat",etat);

   
                  return query.list();
      }
            
                 public List<Tournee> findMouvementStockGlobalByDetailMouvementStock(Date date) {
	Query query = session.createQuery("select c from Tournee c where c.dateTournee =:date");
                query.setParameter("date",date);

   
                 return query.list();
    
          }
                 
                        public List<Tournee> findTorneeByDateTornee() {
	Query query = session.createQuery("select c from Tournee c where c.dateTournee is null");

                 return query.list();
    
          }
                        
                        public Tournee findTourneeByCodeDecharge(String code) {
		
		Query query = session.createQuery("select u from Tournee u where u.codeChargement =:code");
		query.setParameter("code",code);
    
                return (Tournee)query.uniqueResult();
}
                        
                         public List<Tournee> findEtat(String etat) {
	Query query = session.createQuery("select c from Tournee c where c.statue =:etat");
                query.setParameter("etat",etat);

   
                 return query.list();
    
          }
                         
                  public List<Tournee> findTourneeByEtat(String etat, int depot) {
 Query query= session.createQuery("select c from Tournee c where c.etat=:etat and c.depot.id =:depot");
query.setParameter("etat", etat);
query.setParameter("depot",depot);

return query.list();

    }
                  
                      public List<Tournee> findTourneeByEtatAndVendeur(String etat, int depot, int vendeur) {
 Query query= session.createQuery("select c from Tournee c where c.etat=:etat and c.depot.id =:depot and c.vendeur.id =:vendeur");
query.setParameter("etat", etat);
query.setParameter("depot",depot);
query.setParameter("vendeur",vendeur);

return query.list();

    }
                 public Tournee findTourneeByVendeurAndSecteurAndVilleAndEtat(int vendeur, int secteur, int ville, String etat) {
		
		Query query = session.createQuery("select u from Tournee u where u.vendeur.id=:vendeur and u.secteur.id=:secteur and u.ville.id=:ville and u.etat=:etat");
		query.setParameter("vendeur",vendeur);
                query.setParameter("secteur",secteur);
                query.setParameter("ville",ville);
                query.setParameter("etat",etat);
    
                return (Tournee)query.uniqueResult();
}
                  
                public List<Tournee> findByVendeurAndCodeVentAndEtat(int idVendeur) {
	Query query = session.createQuery("select c from Tournee c where c.vendeur.id=:idVendeur and c.codeVent is not null and c.etat <> 'Facturer'");
                query.setParameter("idVendeur",idVendeur);

                 return query.list();
    
          }
                
                      public List<Tournee> findByVendeurAndDateAndEtat(int idVendeur, Date date) {
	Query query = session.createQuery("select c from Tournee c where c.vendeur.id=:idVendeur and c.dateTournee=:date and c.etat = 'Facturer'");
                query.setParameter("idVendeur",idVendeur);
                query.setParameter("date",date);
                 return query.list();
    
          }
}
