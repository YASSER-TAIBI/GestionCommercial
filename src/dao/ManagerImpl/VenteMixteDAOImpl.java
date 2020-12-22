/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.VenteMixte;
import dao.Manager.VenteMixteDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class VenteMixteDAOImpl implements VenteMixteDAO{
    
    Session session=HibernateUtil.openSession();

    @Override
    public VenteMixte findById(int id) {

return (VenteMixte)session.get(VenteMixte.class, id);    }

    @Override
    public void add(VenteMixte venteMixte) {

               session.beginTransaction();
		session.save(venteMixte);
		session.getTransaction().commit();    }

    @Override
    public VenteMixte edit(VenteMixte e) {

session.beginTransaction();
	VenteMixte p= (VenteMixte)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(VenteMixte e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<VenteMixte> findAll() {

return session.createQuery("select c from VenteMixte c").list();

    }
    
      public VenteMixte findVenteMixteByDetailTournee(int idTrn) {
	 Query query = session.createQuery("select c from VenteMixte c where c.detailTournee.id =:idTrn ");
                query.setParameter("idTrn",idTrn);
             
   
                 return (VenteMixte)query.uniqueResult();
    
          }
       
    
    
}
