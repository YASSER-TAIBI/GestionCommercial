/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import dao.Entity.ClientPF;
import dao.Manager.ClientPFDAO;
import java.util.List;
import org.hibernate.Session;
import Utils.HibernateUtil;
import dao.Entity.Secteur;
import dao.Entity.Ville;
import org.hibernate.Query;

/**
 *
 * @author pc
 */
public class ClientPFDAOImpl implements ClientPFDAO {
    Session session=HibernateUtil.openSession();
    @Override
    public ClientPF findById(int id) {

return (ClientPF)session.get(ClientPF.class, id);    }

    @Override
    public void add(ClientPF clientpf) {

               session.beginTransaction();
		session.save(clientpf);
		
		session.getTransaction().commit();    }

    @Override
    public ClientPF edit(ClientPF e) {

session.beginTransaction();
	ClientPF p= (ClientPF)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(ClientPF e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<ClientPF> findAll() {

return session.createQuery("select c from ClientPF c").list();
    
}
     @Override
 public List<ClientPF> findClientBySecteur(Ville ville,Secteur secteur) {
Query query= session.createQuery("select c from ClientPF c where c.ville=:ville and c.secteur=:secteur");
query.setParameter("ville", ville);
query.setParameter("secteur", secteur);
return query.list();

}
  
  @Override
 public List<ClientPF> findClientByVille(Ville ville) {
 Query query= session.createQuery("select c from ClientPF c where c.ville=:ville");
query.setParameter("ville", ville);
return query.list();
}
   
 public List<ClientPF> findClientByVilleid(int idville) {
 Query query= session.createQuery("select c from ClientPF c where c.ville.id=:idville");
query.setParameter("idville", idville);
return query.list();

}
 
  public List<ClientPF> findClientByDepot(int idDepot) {
 Query query= session.createQuery("select c from ClientPF c where c.ville.depot.id=:idDepot");
query.setParameter("idDepot", idDepot);
return query.list();

}
 
 public List<ClientPF> findClientPFBySecteur(int secteur) {
		// TODO Auto-generated method stub
 Query query= session.createQuery("select c from ClientPF c where c.secteur.id =:secteur");
	
query.setParameter("secteur", secteur);
		
return query.list(); 
		
	} 
    
    
}
