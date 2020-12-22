package dao.Manager;

import java.util.List;

import dao.Entity.DetailVendeurSecteur;


public interface DetailVendeurSecteurDAO {
	
	public  void add(DetailVendeurSecteur e);
	
	public  DetailVendeurSecteur edit(DetailVendeurSecteur e);
	
	public  void delete(DetailVendeurSecteur e); 
	
	public List<DetailVendeurSecteur> findAll();
	
	public DetailVendeurSecteur findById(int id);

        public DetailVendeurSecteur findByVendeurAndSecteur(int vendeur,  int secteur);
        
        public List<DetailVendeurSecteur> findDetailVendeurSecteurBySecteur(int secteur);
        
        public List<DetailVendeurSecteur> findDetailVendeurSecteurByVendeur(int vendeur);
        
//        public List<DetailVendeurSecteur> findDetailVendeurSecteurByDepot(int idDepot);
}
