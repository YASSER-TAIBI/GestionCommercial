package dao.Manager;

import java.util.List;

import dao.Entity.Article;
import dao.Entity.FamilleArticle;
import dao.Entity.DetailFamilleArticle;

public interface DetailFamilleArticleDAO {
	
	public  void add(DetailFamilleArticle e);
	
	public  DetailFamilleArticle edit(DetailFamilleArticle e);
	
	public  void delete(DetailFamilleArticle e); 
	
	public List<DetailFamilleArticle> findAll();
	
	public DetailFamilleArticle findById(int id);
	
	public DetailFamilleArticle findByCode(String code);
	
	public List<DetailFamilleArticle> listeSousFamilleByFamilleArticle(int idFamileArticle);
        
         public List<DetailFamilleArticle> findByFamille(int IDfamille) ;


}
