/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.Vehicule;
import dao.Manager.VehiculeDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author pc
 */

public class VehiculeDAOImpl implements VehiculeDAO{
    
   Session session=HibernateUtil.openSession();

    @Override
    public Vehicule findById(int id) {

return (Vehicule)session.get(Vehicule.class, id);    }

    @Override
    public void add(Vehicule Vehicule) {

               session.beginTransaction();
		session.save(Vehicule);
		
		session.getTransaction().commit();    }

    @Override
    public Vehicule edit(Vehicule e) {

session.beginTransaction();
	Vehicule p= (Vehicule)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(Vehicule e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<Vehicule> findAll() {

return session.createQuery("select c from Vehicule c").list();

    }  
     
  
	public Vehicule findByNom(String nom) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from Vehicule c where c.nom =:nom");
	
		query.setParameter("nom", nom);
		
		return (Vehicule) query.uniqueResult();
		
	}
        
        	public Vehicule findByMatricule(String matr) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from Vehicule c where c.matricule =:matr");
	
		query.setParameter("matr", matr);
		
		return (Vehicule) query.uniqueResult();
		
	}
        
         
           public List<Vehicule> findByVehiculeAndDisponibilities() {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from Vehicule c where c.occupeDisponible = FALSE");
		
		  return query.list(); 
		
	}
}
