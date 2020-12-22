/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.DetailTournee;
import dao.Entity.Tournee;
import dao.Manager.DetailTourneeDAO;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */

public class DetailTourneeDAOImpl implements DetailTourneeDAO{
    
      Session session=HibernateUtil.openSession();

    @Override
    public DetailTournee findById(int id) {

return (DetailTournee)session.get(DetailTournee.class, id);    }

    @Override
    public void add(DetailTournee detailTournee) {

               session.beginTransaction();
               	session.save(detailTournee);
		session.getTransaction().commit();    }

    @Override
    public DetailTournee edit(DetailTournee e) {

session.beginTransaction();
	DetailTournee p= (DetailTournee)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(DetailTournee e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<DetailTournee> findAll() {

return session.createQuery("select c from DetailTournee c").list();

    }
    
        public List<DetailTournee> findDetailTourneeByTournee(Date date,int depot,int magasin,int vendeur ) {
	Query query = session.createQuery("select c from DetailTournee c where c.tournee.dateTournee =:date and c.tournee.depot.id =:depot and c.tournee.magasin.id =:magasin and c.tournee.vendeur.id =:vendeur");
                query.setParameter("date",date);
                query.setParameter("depot",depot);
                query.setParameter("magasin",magasin);
                query.setParameter("vendeur",vendeur);
   
                        return  query.list();
    
        }
                        
          	public DetailTournee findDetailTrnByTrn(Date date,int depot,int magasin,int vendeur ) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from DetailTournee c where c.tournee.dateTournee =:date and c.tournee.depot.id =:depot and c.tournee.magasin.id =:magasin and c.tournee.vendeur.id =:vendeur");
		query.setParameter("date",date);
                query.setParameter("depot",depot);
                query.setParameter("magasin",magasin);
                query.setParameter("vendeur",vendeur);
                
                
		return  (DetailTournee) query.uniqueResult();
                        
                        
          }
                     public DetailTournee findDetailTourneeByTourneeVendeurDepotMagasin(int idtournee,int vendeur,int depot,int magasin ,int idarticle) {
	Query query = session.createQuery("select c from DetailTournee c where c.tournee.id =:idtournee and c.tournee.depot.id =:depot and c.tournee.magasin.id =:magasin and c.tournee.vendeur.id =:vendeur and c.article.id=:idarticle");
                query.setParameter("idtournee",idtournee);
                query.setParameter("depot",depot);
                query.setParameter("magasin",magasin);
                query.setParameter("vendeur",vendeur);
                query.setParameter("idarticle",idarticle);
   
                 return  (DetailTournee)query.uniqueResult();
    
        }
                     
                     
                     public List<Object[]> findDetailTourneeByVendeurAndDepotAndMagasinAndEtat(String vendeur, String depot , String magasin, String secteur) {
        Query query=  session.createQuery("select u.tournee.dateTournee, u.article.id ,u.quantiteTotalInitial,u.quantiteFinal,u.tournee.etat from DetailTournee u where u.tournee.dateTournee = (select MAX(c.tournee.dateTournee) from DetailTournee c) and u.tournee.magasin.libelle =:magasin and u.tournee.vendeur.nom =:vendeur and u.tournee.depot.libelle1 =:depot and u.tournee.secteur.libelle =:secteur" );
        query.setParameter("vendeur", vendeur);
        query.setParameter("depot", depot);
        query.setParameter("magasin", magasin);
        query.setParameter("secteur", secteur);
        
        return query.list();
    }
 
                            public List<Object[]> findDetailTourneeByVendeurAndDepotAndMagasinAndEtatInitial(String vendeur, String depot , String magasin, String secteur) {
        Query query=  session.createQuery("select MAX(u.tournee.dateTournee), u.tournee.etat from DetailTournee u where u.tournee.magasin.libelle =:magasin and u.tournee.vendeur.nom =:vendeur and u.tournee.depot.libelle1 =:depot and u.tournee.secteur.libelle =:secteur" );
        query.setParameter("vendeur", vendeur);
        query.setParameter("depot", depot);
        query.setParameter("magasin", magasin);
        query.setParameter("secteur", secteur);
        
        return query.list();
    }
 
                                   public List<Object[]> findDetailTourneeByVendeurAndDepotAndMagasinAndEtatVersementOrFactureOrVente(String vendeur, String depot , String magasin, String secteur) {
        Query query=  session.createQuery("select MAX(u.tournee.dateTournee), u.article.id ,u.quantiteTotalInitial,u.quantiteFinal,u.tournee.etat from DetailTournee u where u.tournee.magasin.libelle =:magasin and u.tournee.vendeur.nom =:vendeur and u.tournee.depot.libelle1 =:depot and u.tournee.secteur.libelle =:secteur group by u.article.id" );
        query.setParameter("vendeur", vendeur);
        query.setParameter("depot", depot);
        query.setParameter("magasin", magasin);
        query.setParameter("secteur", secteur);
        
        return query.list();
    }
                            
                     public List<DetailTournee> findDetailTourneeByDate(Date date,String vendeur, String depot , String magasin, String secteur) {
	Query query = session.createQuery("select c from DetailTournee c where c.tournee.dateTournee =:date and c.tournee.magasin.libelle =:magasin and c.tournee.vendeur.nom =:vendeur and c.tournee.depot.libelle1 =:depot and c.tournee.secteur.libelle =:secteur");
        query.setParameter("date",date);
        query.setParameter("vendeur", vendeur);
        query.setParameter("depot", depot);
        query.setParameter("magasin", magasin);
        query.setParameter("secteur", secteur);
        return  query.list();
    
        }
       
                        public List<DetailTournee> findDetailTourneeByDateTournee(Date date,int vendeur,int depot,int magasin, int secteur) {
	Query query = session.createQuery("select c from DetailTournee c where c.tournee.dateTournee =:date and c.tournee.depot.id =:depot and c.tournee.magasin.id =:magasin and c.tournee.vendeur.id =:vendeur and c.tournee.secteur.id =:secteur");
                query.setParameter("date",date);
                query.setParameter("depot",depot);
                query.setParameter("magasin",magasin);
                query.setParameter("vendeur",vendeur);
                query.setParameter("secteur", secteur);
                        return  query.list();
    
        }
      
                    public List<DetailTournee> findDetailTourneeByTournee(Tournee tournee) {
	Query query = session.createQuery("select c from DetailTournee c where c.tournee =:tournee ");
                query.setParameter("tournee",tournee);
                        return  query.list();
    
        }
                 
                             public List<DetailTournee> findDetailTourneeByDateAndVendeurAndSecteurAndVilleAndMagasin(Date date,int depot, int vendeur,int magasin,int secteur) {
	Query query = session.createQuery("select c from DetailTournee c where c.tournee.dateTournee =:date and c.tournee.secteur.id =:secteur and c.tournee.depot.id =:depot and c.tournee.magasin.id =:magasin and c.tournee.vendeur.id =:vendeur ");
                query.setParameter("date",date);
                query.setParameter("secteur",secteur);
                query.setParameter("magasin",magasin);
                query.setParameter("depot",depot);
                query.setParameter("vendeur",vendeur);
                        return  query.list();
    
        }     
             
                             
                                    public List<Object[]> findBySituationVenteFacture(Date date, int vendeur, int tournee) {
        Query query=  session.createQuery("select c.article.code, c.article.codeFacture, c.article.libelle ,CASE WHEN c.article.id = d.article.id THEN c.totalVendue ELSE 0 END, SUM(d.montant) from DetailTournee c , DetailFacture d where c.tournee.dateTournee= d.facture.dateFacture and c.tournee.vendeur.id = d.facture.vendeur.id and c.tournee.id = d.facture.tournee.id and c.article.id = d.article.id and c.tournee.dateTournee =:date and c.tournee.vendeur.id =:vendeur and c.tournee.id =:tournee and d.categorie='Normal' group by c.article.id ");

        query.setParameter("date",date);
        query.setParameter("vendeur",vendeur);
        query.setParameter("tournee",tournee);
        return query.list();
    }
                             
}
