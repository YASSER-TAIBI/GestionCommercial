/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.Fournisseur;
import dao.Manager.FournisseurDAO;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class FournisseurDAOImpl implements FournisseurDAO{
    
      Session session=HibernateUtil.openSession();

    @Override
    public Fournisseur findById(int id) {

return (Fournisseur)session.get(Fournisseur.class, id);    }

    @Override
    public void add(Fournisseur fournisseur) {

               session.beginTransaction();
		session.save(fournisseur);
		
		session.getTransaction().commit();    }

    @Override
    public Fournisseur edit(Fournisseur e) {

session.beginTransaction();
	Fournisseur p= (Fournisseur)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(Fournisseur e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<Fournisseur> findAll() {

return session.createQuery("select c from Fournisseur c").list();

    }
    
}
