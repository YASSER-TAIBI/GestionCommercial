package dao.ManagerImpl;

import java.util.List;
import org.hibernate.Session;
import Utils.HibernateUtil;
import dao.Entity.SousFamilleArticle;
import dao.Manager.SousFamilleArticleDAO;
import org.hibernate.Query;

public class SousFamilleArticleDAOImpl implements SousFamilleArticleDAO {
	Session session=HibernateUtil.openSession();

	public void add(SousFamilleArticle e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public SousFamilleArticle edit(SousFamilleArticle e) {
		
	session.beginTransaction();
	SousFamilleArticle p= (SousFamilleArticle)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(SousFamilleArticle e) {
		
                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
		
	}

	public List<SousFamilleArticle> findAll() {
		return session.createQuery("select c from SousFamilleArticle c where c.code <>'XXX'").list();
		}

        
               public List<SousFamilleArticle> findByDetailFamille(int IDfamille) {
            Query query= session.createQuery("select c from SousFamilleArticle c where c.detailFamileArticle.id =:IDfamille");
            query.setParameter("IDfamille", IDfamille);
            return query.list();

    }
        
    @Override
    public SousFamilleArticle findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SousFamilleArticle findByCode(String code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	
	


}
