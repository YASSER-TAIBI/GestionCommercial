package dao.Manager;

import java.util.List;


import dao.Entity.SousFamilleArticle;




public interface SousFamilleArticleDAO {
	
	public  void add(SousFamilleArticle e);
	
	public  SousFamilleArticle edit(SousFamilleArticle e);
	
	public  void delete(SousFamilleArticle e); 
	
	public List<SousFamilleArticle> findAll();
	
	public SousFamilleArticle findById(int id);
	
	public SousFamilleArticle findByCode(String code);
	
          public List<SousFamilleArticle> findByDetailFamille(int IDfamille);

}
