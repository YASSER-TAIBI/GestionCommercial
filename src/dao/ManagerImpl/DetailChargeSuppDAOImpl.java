/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.DetailChargeSupp;
import dao.Entity.DetailTournee;
import dao.Manager.DetailChargeSuppDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */

public class DetailChargeSuppDAOImpl implements DetailChargeSuppDAO{
    
      Session session=HibernateUtil.openSession();

    @Override
    public DetailChargeSupp findById(int id) {

return (DetailChargeSupp)session.get(DetailChargeSupp.class, id);    }

    @Override
    public void add(DetailChargeSupp detailChargeSupp) {

               session.beginTransaction();
               	session.save(detailChargeSupp);
		session.getTransaction().commit();    }

    @Override
    public DetailChargeSupp edit(DetailChargeSupp e) {

session.beginTransaction();
	DetailChargeSupp p= (DetailChargeSupp)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(DetailChargeSupp e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<DetailChargeSupp> findAll() {

return session.createQuery("select c from DetailChargeSupp c").list();

    }
    
      	public List<DetailChargeSupp> findDetailTrnByTrn(int detailTournee) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from DetailChargeSupp c where c.detailTournee.id =:detailTournee");
                query.setParameter("detailTournee",detailTournee);
                
                
		return  query.list();
                        
                        
          }
                        
}
