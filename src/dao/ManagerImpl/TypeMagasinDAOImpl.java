/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import dao.Entity.TypeMagasin;
import dao.Manager.TypeMagasinDAO;
import java.util.List;
import org.hibernate.Session;
import Utils.HibernateUtil;

/**
 *
 * @author pc
 */
public class TypeMagasinDAOImpl implements TypeMagasinDAO{

     Session session=HibernateUtil.openSession();

    @Override
    public TypeMagasin findById(int id) {

return (TypeMagasin)session.get(TypeMagasin.class, id);    }

    @Override
    public void add(TypeMagasin clientpf) {

               session.beginTransaction();
		session.save(clientpf);
		
		session.getTransaction().commit();    }

    @Override
    public TypeMagasin edit(TypeMagasin e) {

session.beginTransaction();
	TypeMagasin p= (TypeMagasin)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(TypeMagasin e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<TypeMagasin> findAll() {

return session.createQuery("select c from TypeMagasin c").list();
    
}

    

   

    
    
}
