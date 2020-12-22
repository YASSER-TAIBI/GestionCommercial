package dao.ManagerImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;
import dao.Manager.SequenceurClientDAO;
import dao.Entity.SequenceurClient;

public class SequenceurClientDAOImpl implements SequenceurClientDAO {
	Session session=HibernateUtil.openSession();

	public void add(SequenceurClient e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public SequenceurClient edit(SequenceurClient e) {
		
	session.beginTransaction();
	SequenceurClient p= (SequenceurClient)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		SequenceurClient p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}


	public List<SequenceurClient> findAll() {
		return session.createQuery("select c from SequenceurClient c").list();
		}

	public SequenceurClient findById(int id) {
		return (SequenceurClient)session.get(SequenceurClient.class, id);
		}

        public SequenceurClient findByDepot(int depot) {
	Query query = session.createQuery("select u from SequenceurClient u where u.depot.id=:depot");
        query.setParameter("depot",depot);
        return (SequenceurClient)query.uniqueResult();
 }

}
