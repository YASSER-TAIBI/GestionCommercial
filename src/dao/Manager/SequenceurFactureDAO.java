package dao.Manager;

import java.util.List;

import dao.Entity.SequenceurFacture;

public interface SequenceurFactureDAO {
	
	public  void add(SequenceurFacture e);
	
	public  SequenceurFacture edit(SequenceurFacture e);
	
	public  void delete(int id); 
	
	public List<SequenceurFacture> findAll();
	
	public SequenceurFacture findById(int id);
        
        public SequenceurFacture findByCodeClient(String codeClient);
	

}
