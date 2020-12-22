package dao.ManagerImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;
import dao.Manager.SequenceurFactureDAO;
import dao.Entity.SequenceurFacture;

public class SequenceurFactureDAOImpl implements SequenceurFactureDAO {
	Session session=HibernateUtil.openSession();

        
	public void add(SequenceurFacture e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public SequenceurFacture edit(SequenceurFacture e) {
		
	session.beginTransaction();
	SequenceurFacture p= (SequenceurFacture)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		SequenceurFacture p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}


	public List<SequenceurFacture> findAll() {
		return session.createQuery("select c from SequenceurFacture c").list();
		}

	public SequenceurFacture findById(int id) {
		return (SequenceurFacture)session.get(SequenceurFacture.class, id);
		}

        public SequenceurFacture findByCodeClient(String codeClient) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from SequenceurFacture c where c.codeClient=:codeClient");
		query.setParameter("codeClient",codeClient);
		return  (SequenceurFacture) query.uniqueResult();
           
          }
        
}
