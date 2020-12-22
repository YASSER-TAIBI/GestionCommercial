/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.Article;
import java.util.List;

/**
 *
 * @author pc
 */
public interface ArticleDAO {
    
    
                public Article findById(int id);
		
		public void add(Article article);

		public  Article edit(Article e);
		
		public  void delete(Article e); 
		
		public List<Article> findAll();
    
                public List<Article> findArticleByRecherche(String article) ;
                
                public Article findBycodeArticle(String code);
                
                public Article findBycodeFacture(String codeFct);
                
                public Article findByArticle(String article);
}
