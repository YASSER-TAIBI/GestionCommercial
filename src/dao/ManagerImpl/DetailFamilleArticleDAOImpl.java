package dao.ManagerImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import Utils.HibernateUtil;
import dao.Manager.ArticleDAO;
import dao.Manager.FamilleArticleDAO;
import dao.Entity.Article;
import dao.Entity.FamilleArticle;
import dao.Entity.DetailFamilleArticle;
import dao.Manager.DetailFamilleArticleDAO;



public class DetailFamilleArticleDAOImpl implements DetailFamilleArticleDAO {
	Session session=HibernateUtil.openSession();

	public void add(DetailFamilleArticle e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailFamilleArticle edit(DetailFamilleArticle e) {
		
	session.beginTransaction();
	DetailFamilleArticle p= (DetailFamilleArticle)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(DetailFamilleArticle e) {
		
	    session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
		
	}

	public List<DetailFamilleArticle> findAll() {
		return session.createQuery("select c from DetailFamilleArticle c where c.code <>'XXX'").list();
		}

	public DetailFamilleArticle findById(int id) {
		return (DetailFamilleArticle)session.get(DetailFamilleArticle.class, id);
		}

        
        public List<DetailFamilleArticle> findByFamille(int IDfamille) {
            Query query= session.createQuery("select c from DetailFamilleArticle c where c.familleArticle.id =:IDfamille");
            query.setParameter("IDfamille", IDfamille);
            return query.list();


    }
	
	public DetailFamilleArticle findByCode(String code) {
		// TODO Auto-generated method stub
		DetailFamilleArticle SousFamileArticle= new DetailFamilleArticle();
		Query query= session.createQuery("select c from DetailFamilleArticle c where code=:code");
		query.setParameter("code", code);
		
		SousFamileArticle= (DetailFamilleArticle) query.uniqueResult();
		
		return SousFamileArticle;
	}
	
	
	

    @Override
    public List<DetailFamilleArticle> listeSousFamilleByFamilleArticle(int idFamileArticle) {
       DetailFamilleArticle sousFamileArticle= new DetailFamilleArticle();
		Query query= session.createQuery("select c from DetailFamilleArticle c where famileArticle.id=:idFamileArticle");
		query.setParameter("idFamileArticle", idFamileArticle);
		return query.list();
    }


	
	


}
