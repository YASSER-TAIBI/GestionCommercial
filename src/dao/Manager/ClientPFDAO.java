/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.ClientPF;
import dao.Entity.Secteur;
import dao.Entity.Ville;
import java.util.List;

/**
 *
 * @author pc
 */
public interface ClientPFDAO {
    public void add(ClientPF clientpf);
		
		public ClientPF findById(int id);
		
                public  ClientPF edit(ClientPF e);
		
		public  void delete(ClientPF e); 
		
		public List<ClientPF> findAll();
                
                public List<ClientPF> findClientBySecteur(Ville ville,Secteur secteur);
                
                public List<ClientPF> findClientByVille(Ville ville);
                
                public List<ClientPF> findClientByVilleid(int idville);
                
                public List<ClientPF> findClientPFBySecteur(int secteur) ;
                   
                public List<ClientPF> findClientByDepot(int idDepot);
}
