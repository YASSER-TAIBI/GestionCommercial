package dao.ManagerImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;
import dao.Entity.DetailSousFamilleArticle;
import dao.Manager.DetailSousFamilleArticleDAO;

public class DetailSousFamilleArticleDAOImpl implements DetailSousFamilleArticleDAO {
	Session session=HibernateUtil.openSession();

	public void add(DetailSousFamilleArticle e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailSousFamilleArticle edit(DetailSousFamilleArticle e) {
		
	session.beginTransaction();
	DetailSousFamilleArticle p= (DetailSousFamilleArticle)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(DetailSousFamilleArticle e) {
		
                 session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
		
	}

	public List<DetailSousFamilleArticle> findAll() {
		return session.createQuery("select c from DetailSousFamilleArticle c where c.code <>'XXX'").list();
		}

	public DetailSousFamilleArticle findById(int id) {
		return (DetailSousFamilleArticle)session.get(DetailSousFamilleArticle.class, id);
		}

	
	public DetailSousFamilleArticle findByCode(String code) {
		// TODO Auto-generated method stub
		DetailSousFamilleArticle MarqueArticle= new DetailSousFamilleArticle();
		Query query= session.createQuery("select c from DetailSousFamilleArticle c where code=:code");
		query.setParameter("code", code);
		
		MarqueArticle= (DetailSousFamilleArticle) query.uniqueResult();
		
		return MarqueArticle;
	}

  
	           public List<DetailSousFamilleArticle> findByDetailSousFamille(int IDfamille) {
            Query query= session.createQuery("select c from DetailSousFamilleArticle c where c.SousFamileArticle.id =:IDfamille");
            query.setParameter("IDfamille", IDfamille);
            return query.list();

    }
	
	

 

	
	


}
