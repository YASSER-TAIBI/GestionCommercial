package dao.ManagerImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import Utils.HibernateUtil;
import dao.Manager.ArticleDAO;
import dao.Manager.FamilleArticleDAO;
import dao.Entity.Article;
import dao.Entity.FamilleArticle;

public class FamilleArticleDAOImpl implements FamilleArticleDAO {
	Session session=HibernateUtil.openSession();

	public void add(FamilleArticle e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public FamilleArticle edit(FamilleArticle e) {
		
	session.beginTransaction();
	FamilleArticle p= (FamilleArticle)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(FamilleArticle f) {
		
		session.beginTransaction();
		session.delete(f);
		session.getTransaction().commit();
		
	}

	public List<FamilleArticle> findAll() {
		return session.createQuery("select c from FamilleArticle c where c.code <>'XXX'").list();
		}

	public FamilleArticle findById(int id) {
		return (FamilleArticle)session.get(FamilleArticle.class, id);
		}

	
	public FamilleArticle findByCode(String code) {
		// TODO Auto-generated method stub
		FamilleArticle famileArticle= new FamilleArticle();
		Query query= session.createQuery("select c from FamilleArticle c where code=:code");
		query.setParameter("code", code);
		
		famileArticle= (FamilleArticle) query.uniqueResult();
		
		return famileArticle;
	}
	
	


}
