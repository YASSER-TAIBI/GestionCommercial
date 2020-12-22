package dao.Manager;

import java.util.List;

import dao.Entity.DetailCaissierVendeur;


public interface DetailCaissierVendeurDAO {
	
	public  void add(DetailCaissierVendeur e);
	
	public  DetailCaissierVendeur edit(DetailCaissierVendeur e);
	
	public  void delete(DetailCaissierVendeur e); 
	
	public List<DetailCaissierVendeur> findAll();
	
	public DetailCaissierVendeur findById(int id);
        
        public List<DetailCaissierVendeur> findByDepotAndVendeur(int depot , int vendeur);
        
        public List<DetailCaissierVendeur> findByCaissier(int caissier);

}
