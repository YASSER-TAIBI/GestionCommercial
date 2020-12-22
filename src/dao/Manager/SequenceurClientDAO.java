package dao.Manager;

import java.util.List;

import dao.Entity.SequenceurClient;

public interface SequenceurClientDAO {
	
	public  void add(SequenceurClient e);
	
	public  SequenceurClient edit(SequenceurClient e);
	
	public  void delete(int id); 
	
	public List<SequenceurClient> findAll();
	
	public SequenceurClient findById(int id);
        
        public SequenceurClient findByDepot(int depot);
	
}
