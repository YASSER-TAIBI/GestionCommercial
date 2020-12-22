package dao.ManagerImpl;

import java.util.List;


import org.hibernate.Session;
import Utils.HibernateUtil;
import dao.Manager.DetailCaissierVendeurDAO;
import dao.Entity.DetailCaissierVendeur;
import org.hibernate.Query;




public class DetailCaissierVendeurDAOImpl implements DetailCaissierVendeurDAO {
	Session session=HibernateUtil.openSession();

	public void add(DetailCaissierVendeur e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailCaissierVendeur edit(DetailCaissierVendeur e) {
		
	session.beginTransaction();
	DetailCaissierVendeur p= (DetailCaissierVendeur)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(DetailCaissierVendeur e) {
		
	    session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
		
	}

	public List<DetailCaissierVendeur> findAll() {
		return session.createQuery("select c from DetailCaissierVendeur c").list();
		}

	public DetailCaissierVendeur findById(int id) {
		return (DetailCaissierVendeur)session.get(DetailCaissierVendeur.class, id);
		}

                    public List<DetailCaissierVendeur> findByDepotAndVendeur(int depot , int vendeur) {
	Query query = session.createQuery("select c from DetailCaissierVendeur c where c.caissier.depot.id=:depot and c.vendeur.id=:vendeur");
                query.setParameter("depot",depot);
                query.setParameter("vendeur",vendeur);
                         return query.list();
    
        }
                    
                              public List<DetailCaissierVendeur> findByCaissier(int caissier) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from DetailCaissierVendeur c where c.caissier.id =:caissier");
	
		query.setParameter("caissier", caissier);
		
		   return query.list(); 
		
	}
}
