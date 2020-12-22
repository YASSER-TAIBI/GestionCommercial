package dao.ManagerImpl;

import java.util.List;


import org.hibernate.Session;
import Utils.HibernateUtil;
import dao.Manager.DetailVendeurSecteurDAO;
import dao.Entity.DetailVendeurSecteur;
import org.hibernate.Query;




public class DetailVendeurSecteurDAOImpl implements DetailVendeurSecteurDAO {
	Session session=HibernateUtil.openSession();

	public void add(DetailVendeurSecteur e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailVendeurSecteur edit(DetailVendeurSecteur e) {
		
	session.beginTransaction();
	DetailVendeurSecteur p= (DetailVendeurSecteur)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(DetailVendeurSecteur e) {
		
	    session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
		
	}

	public List<DetailVendeurSecteur> findAll() {
		return session.createQuery("select c from DetailVendeurSecteur c").list();
		}

	public DetailVendeurSecteur findById(int id) {
		return (DetailVendeurSecteur)session.get(DetailVendeurSecteur.class, id);
		}

        
        	public DetailVendeurSecteur findByVendeurAndSecteur(int vendeur,  int secteur) {
		// TODO Auto-generated method stub
                	DetailVendeurSecteur detailVendeurSecteur= new DetailVendeurSecteur();
		Query query= session.createQuery("select c from DetailVendeurSecteur c where c.secteur.id =:secteur and c.vendeur.id =:vendeur");
		query.setParameter("vendeur", vendeur);
		query.setParameter("secteur", secteur);
	                detailVendeurSecteur= (DetailVendeurSecteur) query.uniqueResult();
		
		return detailVendeurSecteur;
                
	}

                public List<DetailVendeurSecteur> findDetailVendeurSecteurBySecteur(int secteur) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from DetailVendeurSecteur c where c.secteur.id =:secteur");
	
		query.setParameter("secteur", secteur);
		
		   return query.list(); 
		
	} 
                
                      public List<DetailVendeurSecteur> findDetailVendeurSecteurByVendeur(int vendeur) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from DetailVendeurSecteur c where c.vendeur.id =:vendeur");
	
		query.setParameter("vendeur", vendeur);
		
		   return query.list(); 
		
	}
                      
//                          public List<DetailVendeurSecteur> findDetailVendeurSecteurByDepot(int idDepot) {
//                Query query= session.createQuery("select DISTINCT d.vendeur.id from DetailVendeurSecteur d where d.secteur.ville.depot.id =:idDepot ");
//                query.setParameter("idDepot", idDepot);
//                return query.list();
//
//        }
}
