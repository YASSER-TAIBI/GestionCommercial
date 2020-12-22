package dao.ManagerImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import Utils.HibernateUtil;
import dao.Manager.UtilisateurDAO;
import dao.Entity.Utilisateur;

public class UtilisateurDAOImpl implements UtilisateurDAO {
	
	Session session=HibernateUtil.openSession();

	public Utilisateur findUtilisateurByLoginMotPasse(String login,
			String motPasse) {
		
		Query query = session.createQuery("select u from Utilisateur u where  u.login=:login and u.password=:motPasse");
		query.setParameter("login", login);
		query.setParameter("motPasse", motPasse);
	return (Utilisateur) query.uniqueResult();
	      

				}

	public Utilisateur findById(int id) {
		return (Utilisateur)session.get(Utilisateur.class, id);
		}

	@Override
	public void add(Utilisateur utilisateur) {
		session.beginTransaction();
		session.save(utilisateur);
		
		session.getTransaction().commit();
		//return p;
	}

	@Override
	public Utilisateur edit(Utilisateur e) {
		
	session.beginTransaction();
	Utilisateur p= (Utilisateur)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	@Override
	public void delete(Utilisateur e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

	@Override
	public List<Utilisateur> findAll() {
		return session.createQuery("select c from Utilisateur c").list();
		}
	
	@Override
	public List<Utilisateur> findAllSaufAdmin() {
		return session.createQuery("select c from Utilisateur c where id<>1").list();
		}

        public Utilisateur findUtiliByRechercheCodeDepot(int idDepot) {
		
		Query query = session.createQuery("select u from Utilisateur u where u.depot.id =:idDepot");
		query.setParameter("idDepot",idDepot);
               
			return  (Utilisateur) query.uniqueResult();     
 }
        
        public List<Utilisateur> findUtiliByRechercheNom(String nom) {
		
		Query query = session.createQuery("select u from Utilisateur u where nom like :nom");
		query.setParameter("nom","%"+nom+"%");
               
		
                return query.list();
 }
}
