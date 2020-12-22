/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import dao.Entity.Habilitation;
import dao.Entity.Utilisateur;
import dao.Manager.HabilitationDAO;
import dao.Manager.MenuDAO;
import dao.Manager.UtilisateurDAO;
import dao.ManagerImpl.HabilitationDAOImpl;
import dao.ManagerImpl.MenuDAOImpl;
import dao.ManagerImpl.UtilisateurDAOImpl;
import java.util.List;
import dao.Entity.Menu;

/**
 *
 * @author Hp
 */
public class UtilsMenu {
    
    
    MenuDAO menuDAO = new MenuDAOImpl();
    UtilisateurDAO utilisateurDAO= new UtilisateurDAOImpl();
    HabilitationDAO habilitationDAO = new HabilitationDAOImpl();
    Habilitation habilitation = new Habilitation();
    
    public void genererMenuUtilisateur(){
    
    
	
	List<Menu> listeMenu =menuDAO.findAll();
	List<Utilisateur> listUtilisateur = utilisateurDAO.findAll();
	
	
	if(listeMenu!=null && listUtilisateur!=null){
	for(int i=0;i<listeMenu.size();i++)
		for(int j=0;j<listUtilisateur.size();j++){
			 
			Menu menu =listeMenu.get(i);
			Utilisateur utilisateur =listUtilisateur.get(j);

			habilitation  =habilitationDAO.findByMenuUtilisateur(menu.getId(), utilisateur.getId());
		
			if(habilitation==null){
				habilitation = new Habilitation();
				habilitation.setMenu(menu);
				habilitation.setUtilisateur(utilisateur);
				habilitation.setAutorise(true);
				
				habilitationDAO.add(habilitation);
			}
		}
	}
    }    
}
