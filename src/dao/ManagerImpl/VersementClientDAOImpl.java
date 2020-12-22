/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.VersementClient;
import dao.Manager.VersementClientDAO;
import dao.Manager.VersementDAO;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class VersementClientDAOImpl implements VersementClientDAO{
    
    Session session=HibernateUtil.openSession();

    @Override
    public VersementClient findById(int id) {

return (VersementClient)session.get(VersementClient.class, id);    }

    @Override
    public void add(VersementClient versementClient) {

               session.beginTransaction();
		session.save(versementClient);
		
		session.getTransaction().commit();    }

    @Override
    public VersementClient edit(VersementClient e) {

session.beginTransaction();
	VersementClient p= (VersementClient)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(VersementClient e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<VersementClient> findAll() {

return session.createQuery("select c from VersementClient c").list();

    }
    
              public List<VersementClient> findVersemenBancairetClientByClient(int client, String etat) {
	Query query = session.createQuery("select c from VersementClient c where c.transfertStock.client.id =:client AND c.etat=:etat AND c.montantBanque <> 0 AND c.etatVersementBancaire = FALSE");
                query.setParameter("client",client);
                query.setParameter("etat",etat);
                
                        return  query.list();
    
        }
    
              public List<VersementClient> findVersementChequeClientByClient(int client, String etat) {
	Query query = session.createQuery("select c from VersementClient c where c.transfertStock.client.id =:client AND c.etat=:etat AND c.montantCheque <> 0 AND c.etatVersementCheque = FALSE");
                query.setParameter("client",client);
                query.setParameter("etat",etat);
                
                        return  query.list();
    
        }

}
