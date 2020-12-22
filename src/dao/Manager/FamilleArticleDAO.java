package dao.Manager;

import java.util.List;
import dao.Entity.Article;
import dao.Entity.FamilleArticle;

public interface FamilleArticleDAO {
	
	public  void add(FamilleArticle e);
	
	public  FamilleArticle edit(FamilleArticle e);
	
	public  void delete(FamilleArticle e); 
	
	public List<FamilleArticle> findAll();
	
	public FamilleArticle findById(int id);
	
	public FamilleArticle findByCode(String code);
	


}
