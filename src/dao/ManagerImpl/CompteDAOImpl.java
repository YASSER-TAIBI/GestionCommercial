/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import dao.Entity.*;
import dao.Entity.Compte;
import java.util.List;
import org.hibernate.Session;
import Utils.HibernateUtil;
import org.hibernate.Query;
import dao.Manager.CompteDAO;

/**
 *
 * @author Hp
 */
public class CompteDAOImpl implements CompteDAO {
     Session session=HibernateUtil.openSession();

    
    
	public Compte findById(int id) {
		return (Compte)session.get(Compte.class, id);
		}

   
	public void add(Compte compteVendeur) {
		session.beginTransaction();
		session.save(compteVendeur);
		
		session.getTransaction().commit();
		
	}


	public Compte edit(Compte e) {
		
	session.beginTransaction();
	Compte p= (Compte)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(Compte e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
    public List<Compte> findAll() {
              return session.createQuery("select c from Compte c").list();
    }



}
