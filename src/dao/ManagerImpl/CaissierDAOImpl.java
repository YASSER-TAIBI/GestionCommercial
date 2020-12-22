/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import dao.Entity.Caissier;
import dao.Manager.CaissierDAO;
import java.util.List;
import org.hibernate.Session;
import Utils.HibernateUtil;
import org.hibernate.Query;

/**
 *
 * @author pc
 */
public class CaissierDAOImpl implements CaissierDAO{
    
    Session session=HibernateUtil.openSession();

    @Override
    public Caissier findById(int id) {

return (Caissier)session.get(Caissier.class, id);    }

    @Override
    public void add(Caissier caissier) {

                session.beginTransaction();
		session.save(caissier);
		session.getTransaction().commit();    }

    @Override
    public Caissier edit(Caissier e) {

session.beginTransaction();
	Caissier p= (Caissier)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(Caissier e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<Caissier> findAll() {

return session.createQuery("select c from Caissier c").list();

    }
              
            public Caissier findByNom(String nom) {
 Query query= session.createQuery("select c from Caissier c where c.nom=:nom ");
query.setParameter("nom", nom);
return  (Caissier) query.uniqueResult();

    } 
    
            public List<Caissier> findByCaissier(int depot) {
	Query query = session.createQuery("select c from Caissier c where c.depot.id=:depot");
                query.setParameter("depot",depot);
                         return query.list();
    
        }
    
            
            public Caissier findByCaissierAndDepot(int idCaissier, int idDepot) {
 Query query= session.createQuery("select c from Caissier c, DetailCaissierVendeur d where c.id = d.caissier.id AND c.depot.id=:idDepot and d.caissier.id =:idCaissier ");
query.setParameter("idDepot", idDepot);
query.setParameter("idCaissier", idCaissier);
return  (Caissier) query.uniqueResult();

    } 
}
