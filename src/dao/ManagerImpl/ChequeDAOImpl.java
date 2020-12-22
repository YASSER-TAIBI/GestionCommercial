/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.Cheque;
import dao.Entity.ClientPF;
import dao.Entity.Ville;
import dao.Manager.ChequeDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author pc
 */
public class ChequeDAOImpl  implements ChequeDAO{
     Session session=HibernateUtil.openSession();

    @Override
    public Cheque findById(int id) {

return (Cheque)session.get(Cheque.class, id);    }

    @Override
    public void add(Cheque Cheque) {

               session.beginTransaction();
		session.save(Cheque);
		
		session.getTransaction().commit();    }

    @Override
    public Cheque edit(Cheque e) {

session.beginTransaction();
	Cheque p= (Cheque)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(Cheque e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<Cheque> findAll() {

return session.createQuery("select c from Cheque c").list();

    }
    

 public List<Cheque> findChequeByVendeur (int idVendeur ) {
 Query query= session.createQuery("select c from Cheque c where c.vendeur.id  =:idVendeur");
query.setParameter("idVendeur", idVendeur);
return query.list();
}
    
     public List<Cheque> findChequeByEtat (String typeCheque ,String etat ) {
 Query query= session.createQuery("select c from Cheque c where c.typeCheque =:typeCheque and c.etat =:etat");
query.setParameter("typeCheque", typeCheque);
query.setParameter("etat", etat);
return query.list();
}
   
  public List<Cheque> findChequeByVendeur (String idVendeur, String etat ) {
 Query query= session.createQuery("select c from Cheque c where c.vendeur.typeVendeur =:idVendeur and c.etat =:etat");
query.setParameter("idVendeur", idVendeur);
query.setParameter("etat", etat);
return query.list();
}
 
   public List<Cheque> findChequeByVendeurAndSecteurAndVille (int idVendeur, int idSecteur, int idVille ) {
 Query query= session.createQuery("select c from Cheque c where c.vendeur.id=:idVendeur and c.ville.id=:idVille and c.secteur.id=:idSecteur");
query.setParameter("idVendeur", idVendeur);
query.setParameter("idSecteur", idSecteur);
query.setParameter("idVille", idVille);
return query.list();
}
}
