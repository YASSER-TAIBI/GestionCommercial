/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import dao.Entity.*;
import dao.Entity.CompteVersement;
import java.util.List;
import org.hibernate.Session;
import Utils.HibernateUtil;
import org.hibernate.Query;
import dao.Manager.CompteVersementDAO;

/**
 *
 * @author Hp
 */
public class CompteVersementDAOImpl implements CompteVersementDAO {
     Session session=HibernateUtil.openSession();

    
    
	public CompteVersement findById(int id) {
		return (CompteVersement)session.get(CompteVersement.class, id);
		}

   
	public void add(CompteVersement compteVersement) {
		session.beginTransaction();
		session.save(compteVersement);
		
		session.getTransaction().commit();
		
	}


	public CompteVersement edit(CompteVersement e) {
		
	session.beginTransaction();
	CompteVersement p= (CompteVersement)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(CompteVersement e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
    public List<CompteVersement> findAll() {
              return session.createQuery("select c from CompteVersement c").list();
    }





}
