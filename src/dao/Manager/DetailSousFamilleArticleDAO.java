package dao.Manager;

import java.util.List;


import dao.Entity.DetailSousFamilleArticle;

public interface DetailSousFamilleArticleDAO {
	
	public  void add(DetailSousFamilleArticle e);
	
	public  DetailSousFamilleArticle edit(DetailSousFamilleArticle e);
	
	public  void delete(DetailSousFamilleArticle e); 
	
	public List<DetailSousFamilleArticle> findAll();
	
	public DetailSousFamilleArticle findById(int id);
	
	public DetailSousFamilleArticle findByCode(String code);
	
        public List<DetailSousFamilleArticle> findByDetailSousFamille(int IDfamille);

}
