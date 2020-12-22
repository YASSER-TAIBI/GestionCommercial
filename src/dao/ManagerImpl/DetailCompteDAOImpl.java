/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.Compte;
import dao.Entity.DetailCompte;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import dao.Manager.DetailCompteDAO;
import java.util.Date;

/**
 *
 * @author Hp
 */
public class DetailCompteDAOImpl implements DetailCompteDAO {
     Session session=HibernateUtil.openSession();
  
     
     
     
     public DetailCompte findById(int id) {
		return (DetailCompte)session.get(DetailCompte.class, id);
		}

 
    
	public void add(DetailCompte detailCompte) {
		session.beginTransaction();
		session.save(detailCompte);
		
		session.getTransaction().commit();
		//return p;
	}


	public DetailCompte edit(DetailCompte e) {
		
	session.beginTransaction();
	DetailCompte p= (DetailCompte)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(DetailCompte e) {
		
		session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
    public List<DetailCompte> findAll() {
              return session.createQuery("select c from DetailCompte c").list();
    }

//    public List<DetailCompte> findFilterByVendeur (int idVendeur ) {
//         Query query= session.createQuery("select c from DetailCompte c where c.compte.id =:idVendeur");
//         query.setParameter("idVendeur", idVendeur);
//    return query.list();
//}

    
    
    public List<DetailCompte> findFilterByVendeurAndDate(Compte compteVendeur, Date dateDebut,Date dateFin ) {
               
         Query query= null;
         
         if (dateDebut!=null && dateFin !=null && compteVendeur!=null){
        
         query= session.createQuery("select c from DetailCompte c where c.compte =:compteVendeur and c.dateOperation BETWEEN :dateDebut and :dateFin");
         query.setParameter("compteVendeur", compteVendeur);
         query.setParameter("dateDebut", dateDebut);
         query.setParameter("dateFin", dateFin);
    
         }
         
         else if (dateDebut ==null && dateFin ==null && compteVendeur!=null){
        
         query= session.createQuery("select c from DetailCompte c where c.compte =:compteVendeur ");
         query.setParameter("compteVendeur", compteVendeur);
         
         }
         else if (dateDebut!=null && dateFin !=null && compteVendeur == null){
             
           query= session.createQuery("select c from DetailCompte c where c.dateOperation BETWEEN :dateDebut and :dateFin");
         query.setParameter("dateDebut", dateDebut);
         query.setParameter("dateFin", dateFin);
         
         }
         
           return query.list();
    }

      public List<DetailCompte> findFilterByVendeurAndDateAndTypeCompte(Compte compteVendeur, Date dateDebut,Date dateFin, String typeCompte) {
               
         Query query= null;
         
         if (dateDebut!=null && dateFin !=null && compteVendeur!=null && typeCompte!=null){
        
         query= session.createQuery("select c from DetailCompte c where c.compte =:compteVendeur and c.dateOperation BETWEEN :dateDebut and :dateFin and c.etat=:typeCompte");
         query.setParameter("compteVendeur", compteVendeur);
         query.setParameter("typeCompte", typeCompte);
         query.setParameter("dateDebut", dateDebut);
         query.setParameter("dateFin", dateFin);
    
         }
         
         else if (dateDebut ==null && dateFin ==null && compteVendeur!=null && typeCompte!=null){
        
         query= session.createQuery("select c from DetailCompte c where c.compte =:compteVendeur and c.etat=:typeCompte");
         query.setParameter("compteVendeur", compteVendeur);
         query.setParameter("typeCompte", typeCompte);
         
         }
         else if (dateDebut!=null && dateFin !=null && compteVendeur == null && typeCompte==null){
             
           query= session.createQuery("select c from DetailCompte c where c.dateOperation BETWEEN :dateDebut and :dateFin");
         query.setParameter("dateDebut", dateDebut);
         query.setParameter("dateFin", dateFin);
         
         }
         
           return query.list();
    }
    
 public List<DetailCompte> findFilterByVendeur(int compteVendeur ) {

        Query  query= session.createQuery("select c from DetailCompte c where c.compte.id =:compteVendeur ");
         query.setParameter("compteVendeur", compteVendeur);

    
             return query.list();
         
         }
    
         public List<Object[]> findByMontantCredit(int compte , Date dateOp) {
        Query query=  session.createQuery("select sum(u.montantCredit), sum(u.montantDebit) from DetailCompte u where u.compte.id =:compte AND u.dateOperation=:dateOp");
        query.setParameter("compte", compte);
        query.setParameter("dateOp", dateOp);
        
        return query.list();
    }
     
             public List<DetailCompte> findByDateOperation(int compte) {
        Query query=  session.createQuery("select u from DetailCompte u WHERE u.compte.id =:compte ORDER BY u.dateOperation");
        query.setParameter("compte", compte);

        return query.list();
    }
         
}
