/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import dao.Entity.Article;
import dao.Manager.ArticleDAO;
import java.util.List;
import org.hibernate.Session;
import Utils.HibernateUtil;
import org.hibernate.Query;

/**
 *
 * @author pc
 */
public class ArticleDAOImpl implements ArticleDAO {
     Session session=HibernateUtil.openSession();

    @Override
    public Article findById(int id) {

return (Article)session.get(Article.class, id);    }

    @Override
    public void add(Article article) {

               session.beginTransaction();
		session.save(article);
		
		session.getTransaction().commit();    }

    @Override
    public Article edit(Article e) {

session.beginTransaction();
	Article p= (Article)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(Article e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<Article> findAll() {

return session.createQuery("select c from Article c where c.code <>'XXX'").list();

    }
    
        public List<Article> findArticleByRecherche(String article) {
		
		Query query = session.createQuery("select u from Article u where u.libelle like :article");
		query.setParameter("article","%"+article+"%");
               
		
                return query.list();
 }
      
    	public Article findBycodeArticle(String code ) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from Article c where c.code =:code");
		query.setParameter("code",code);

		return  (Article) query.uniqueResult();
           
          }
        
        public Article findBycodeFacture(String codeFct ) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from Article c where c.codeFacture =:codeFct");
		query.setParameter("codeFct",codeFct);

		return  (Article) query.uniqueResult();         
          }
    
           public Article findByArticle(String article) {
		
		Query query = session.createQuery("select u from Article u where u.libelle =:article");
		query.setParameter("article",article);
               
		
               return  (Article) query.uniqueResult();         
 }
        
}
