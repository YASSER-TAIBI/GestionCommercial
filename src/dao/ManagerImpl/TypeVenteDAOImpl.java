/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.TypeVente;
import dao.Manager.TypeVenteDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */

public class TypeVenteDAOImpl implements TypeVenteDAO{

     Session session=HibernateUtil.openSession();

    @Override
    public TypeVente findById(int id) {

return (TypeVente)session.get(TypeVente.class, id);    }

    @Override
    public void add(TypeVente typeVente) {

               session.beginTransaction();
		session.save(typeVente);
		
		session.getTransaction().commit();    }

    @Override
    public TypeVente edit(TypeVente e) {

session.beginTransaction();
	TypeVente p= (TypeVente)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(TypeVente e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<TypeVente> findAll() {

return session.createQuery("select c from TypeVente c where c.code <>'XXX'").list();
    
}

      public TypeVente findTypeVenteByCodeTypeVente(String code) {
		
		Query query = session.createQuery("select u from TypeVente u where u.code=:code ");
      query.setParameter("code",code);
                return (TypeVente)query.uniqueResult();
 }

         public List<TypeVente> findTypeVenteByCodeTypeVente() {
	Query query = session.createQuery("select c.code from TypeVente c where c.code <>'XXX'");

                 return query.list();
    
          }
    
         
             public List<String> findTypeVenteByCodeType() {
	Query query = session.createQuery("select g.code from TypeVente g where g.code = 'M'");

                 return query.list();
    
          }
             
                    	public TypeVente findTypeVenteByCombo(String code ) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from TypeVente c where c.code =:code");
		query.setParameter("code",code);
  
		return  (TypeVente) query.uniqueResult();       
          }

                  public List<TypeVente> findByTypeVenteGrosDetail() {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from TypeVente c where c.code <> 'M' and c.code <>'XXX'");
		
		  return query.list();
		
	}          
                        
}
