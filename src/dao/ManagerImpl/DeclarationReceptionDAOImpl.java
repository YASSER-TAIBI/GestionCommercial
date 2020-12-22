/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.DeclarationReception;
import dao.Manager.DeclarationReceptionDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class DeclarationReceptionDAOImpl implements DeclarationReceptionDAO{
    
      Session session=HibernateUtil.openSession();

    @Override
    public DeclarationReception findById(int id) {

return (DeclarationReception)session.get(DeclarationReception.class, id);    }

    @Override
    public void add(DeclarationReception declarationReception) {

               session.beginTransaction();
		session.save(declarationReception);
		
		session.getTransaction().commit();    }

    @Override
    public DeclarationReception edit(DeclarationReception e) {

session.beginTransaction();
	DeclarationReception p= (DeclarationReception)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(DeclarationReception e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<DeclarationReception> findAll() {

return session.createQuery("select c from DeclarationReception c").list();

    }
    
    public List<DeclarationReception> findByEtatNull() {

return session.createQuery("select c from DeclarationReception c where c.etat is null").list();

    }
    
           public List<DeclarationReception> findByDeclarationReception(int depot) {
	Query query = session.createQuery("select c from DeclarationReception c where c.depot.id=:depot");
                query.setParameter("depot",depot);
                         return query.list();
    
        }
           
              public List<DeclarationReception> findByDeclarationReceptionAndEtat(int depot,String etat) {
	Query query = session.createQuery("select c from DeclarationReception c where c.depot.id=:depot and c.etat=:etat");
                query.setParameter("depot",depot);
                 query.setParameter("etat",etat);
                         return query.list();
    
        }
}
