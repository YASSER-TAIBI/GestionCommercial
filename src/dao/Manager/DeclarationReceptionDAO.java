/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.DeclarationReception;
import java.util.List;

/**
 *
 * @author pc
 */
public interface DeclarationReceptionDAO {
    
    public void add(DeclarationReception fournisseur);
		
		public DeclarationReception findById(int id);
                 
                public List<DeclarationReception> findByEtatNull();
                
		public  DeclarationReception edit(DeclarationReception e);
		
		public  void delete(DeclarationReception e); 
		
		public List<DeclarationReception> findAll();
                
                public List<DeclarationReception> findByDeclarationReception(int depot);

                public List<DeclarationReception> findByDeclarationReceptionAndEtat(int depot,String etat);
}
