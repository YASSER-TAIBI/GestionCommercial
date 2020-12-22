/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.DetailTransfertCheque;
import dao.Manager.DetailTransfertChequeDAO;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class DetailTransfertChequeDAOImpl implements DetailTransfertChequeDAO{
    
      Session session=HibernateUtil.openSession();

    @Override
    public DetailTransfertCheque findById(int id) {

            return (DetailTransfertCheque)
        session.get(DetailTransfertCheque.class, id);    }

    @Override
    public void add(DetailTransfertCheque detailTransfertCheque) {

               session.beginTransaction();
		session.save(detailTransfertCheque);
		
		session.getTransaction().commit();    }

    @Override
    public DetailTransfertCheque edit(DetailTransfertCheque e) {

session.beginTransaction();
	DetailTransfertCheque p= (DetailTransfertCheque)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(DetailTransfertCheque e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<DetailTransfertCheque> findAll() {

return session.createQuery("select c from DetailTransfertCheque c").list();

    }
 
    
}