/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import dao.Entity.Vendeur;
import dao.Manager.VendeurDAO;
import java.util.List;
import org.hibernate.Session;
import Utils.HibernateUtil;
import org.hibernate.Query;

/**
 *
 * @author pc
 */
public class VendeurDAOImpl implements VendeurDAO{
    
    Session session=HibernateUtil.openSession();

    @Override
    public Vendeur findById(int id) {

return (Vendeur)session.get(Vendeur.class, id);    }

    @Override
    public void add(Vendeur article) {

                session.beginTransaction();
		session.save(article);
		session.getTransaction().commit();    }

    @Override
    public Vendeur edit(Vendeur e) {

session.beginTransaction();
	Vendeur p= (Vendeur)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(Vendeur e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<Vendeur> findAll() {

return session.createQuery("select c from Vendeur c").list();

    }
    
  @Override
  public List<Vendeur> findBySecteur(String secteur) {
 Query query= session.createQuery("select c from Vendeur c where c.secteur.libelle=:secteur");
query.setParameter("secteur", secteur);
return query.list();


    }
    
     public List<Vendeur> findByDepotVille(int idVille) {
 Query query= session.createQuery("select c from Vendeur c where c.ville.id =:idVille");
query.setParameter("idVille", idVille);
return query.list();


    }
   
      public List<Vendeur> findVendeurByDepot(String typeVendeur) {
 Query query= session.createQuery("select c from Vendeur c where c.typeVendeur =:typeVendeur");
query.setParameter("typeVendeur", typeVendeur);
return query.list();


    } 

        public List<Vendeur> findByOperateur(String operateur) {
 Query query= session.createQuery("select c from Vendeur c where c.typeVendeur =:operateur");
query.setParameter("operateur", operateur);
return query.list();


    }
        
        public List<Vendeur> findByDepot(int idDepot) {
 Query query= session.createQuery("select c from Vendeur c, DetailVendeurSecteur d where c.id = d.vendeur.id AND d.secteur.ville.depot.id =:idDepot ");
query.setParameter("idDepot", idDepot);
return query.list();

    }
       
        public List<Vendeur> findByDepotV2(int idDepot) {
 Query query= session.createQuery("select c from Vendeur c, DetailVendeurSecteur d where c.id = d.vendeur.id AND d.secteur.ville.depot.id =:idDepot GROUP BY d.vendeur.id");
query.setParameter("idDepot", idDepot);
return query.list();

    }
        
          public Vendeur findByVandeurAndDepotV2(int idVendeur, int idDepot) {
 Query query= session.createQuery("select c from Vendeur c, DetailVendeurSecteur d where c.id = d.vendeur.id AND d.secteur.ville.depot.id =:idDepot and d.vendeur.id =:idVendeur ");
query.setParameter("idDepot", idDepot);
query.setParameter("idVendeur", idVendeur);
return  (Vendeur) query.uniqueResult();

    } 
        
        
        public Vendeur findByCaissier() {
 Query query= session.createQuery("select c from Vendeur c where c.typeVendeur= 'Caissier'");
return  (Vendeur) query.uniqueResult();

    }    
              
}
