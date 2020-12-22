/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.VersementClient;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface VersementClientDAO {
    
       public VersementClient findById(int id);
		
		public void add(VersementClient versementClient);
		
		public  VersementClient edit(VersementClient e);
		
		public  void delete(VersementClient e); 
		
		public List<VersementClient> findAll();
                
                public List<VersementClient> findVersemenBancairetClientByClient(int client, String etat);

                public List<VersementClient> findVersementChequeClientByClient(int client, String etat);

}