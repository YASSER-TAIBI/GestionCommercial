/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.ManagerImpl;

import Utils.HibernateUtil;
import dao.Entity.DetailTransfertStock;
import dao.Entity.TransfertStock;
import dao.Manager.DetailTransfertStockDAO;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class DetailTransfertStockDAOImpl implements DetailTransfertStockDAO{
    
      Session session=HibernateUtil.openSession();

    @Override
    public DetailTransfertStock findById(int id) {

            return (DetailTransfertStock)
        session.get(DetailTransfertStock.class, id);    }

    @Override
    public void add(DetailTransfertStock detailTransfertStock) {

               session.beginTransaction();
		session.save(detailTransfertStock);
		
		session.getTransaction().commit();    }

    @Override
    public DetailTransfertStock edit(DetailTransfertStock e) {

session.beginTransaction();
	DetailTransfertStock p= (DetailTransfertStock)session.merge(e);
	session.getTransaction().commit();
		return p;

    }

    @Override
    public void delete(DetailTransfertStock e) {

                session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
    }

    @Override
    public List<DetailTransfertStock> findAll() {

return session.createQuery("select c from DetailTransfertStock c").list();

    }
    
    	public List<DetailTransfertStock> findByRechercheLibelle(String article) {
		
		Query query = session.createQuery("select u from DetailTransfertStock u where u.article.libelle like:article and u.transfertStock.dateTransf");
		query.setParameter("article","%"+article+"%");

               
		
                return query.list();
 }
         
        public List<Object[]> findBySituationStock(int magasin) {
        Query query=  session.createQuery("select c.article.code,c.article.libelle,"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Initial' and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Reception' and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Chargement' and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Chargement Supp' and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Retour Definitif' and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Transfert Sorties' and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Transfert Entrees' and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Sorties' and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Initial' and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id)+"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Reception' and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id)+"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Transfert Entrees' and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id)+"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Retour Definitif' and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id)-"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Chargement' and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id)-"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Chargement Supp' and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id)-"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Transfert Sorties' and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id)-"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Sorties' and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id) "
                + "from DetailTransfertStock c where c.transfertStock.magasinStock.id =:magasin group by c.article.id ");

        query.setParameter("magasin",magasin);
        
        return query.list();
    }
         
        public List<Object[]> findBySituationStockAndDateMin(int magasin, Date dateTransf) {
        Query query=  session.createQuery("select c.article.code,c.article.libelle,"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Initial' and d.transfertStock.dateTransf <:dateTransf and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Reception' and d.transfertStock.dateTransf <:dateTransf  and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Chargement' and d.transfertStock.dateTransf <:dateTransf  and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Chargement Supp' and d.transfertStock.dateTransf <:dateTransf  and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Retour Definitif' and d.transfertStock.dateTransf <:dateTransf and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Transfert Sorties' and d.transfertStock.dateTransf <:dateTransf  and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Transfert Entrees' and d.transfertStock.dateTransf <:dateTransf and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Sorties' and d.transfertStock.dateTransf <:dateTransf and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Initial' and d.transfertStock.dateTransf <:dateTransf and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id)+"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Reception' and d.transfertStock.dateTransf <:dateTransf and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id)+"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Transfert Entrees' and d.transfertStock.dateTransf <:dateTransf and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id)+"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Retour Definitif' and d.transfertStock.dateTransf <:dateTransf and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id)-"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Chargement' and d.transfertStock.dateTransf <:dateTransf and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id)-"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Chargement Supp' and d.transfertStock.dateTransf <:dateTransf and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id)-"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Transfert Sorties' and d.transfertStock.dateTransf <:dateTransf and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id)-"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Sorties' and d.transfertStock.dateTransf <:dateTransf and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id) "
                + "from DetailTransfertStock c where c.transfertStock.magasinStock.id =:magasin  group by c.article.id ");

        query.setParameter("magasin",magasin);
        query.setParameter("dateTransf",dateTransf);
        
        return query.list();
    }
        
         public List<Object[]> findBySituationStockAndDate(int magasin, Date dateTransf) {
        Query query=  session.createQuery("select c.article.code,c.article.libelle,"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Initial' and d.transfertStock.dateTransf =:dateTransf and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Reception' and d.transfertStock.dateTransf =:dateTransf and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Chargement' and d.transfertStock.dateTransf =:dateTransf and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Chargement Supp' and d.transfertStock.dateTransf =:dateTransf and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Retour Definitif' and d.transfertStock.dateTransf =:dateTransf and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Transfert Sorties' and d.transfertStock.dateTransf =:dateTransf and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Transfert Entrees' and d.transfertStock.dateTransf =:dateTransf and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Sorties' and d.transfertStock.dateTransf =:dateTransf and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Initial' and d.transfertStock.dateTransf =:dateTransf and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id)+"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Reception' and d.transfertStock.dateTransf =:dateTransf and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id)+"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Transfert Entrees' and d.transfertStock.dateTransf =:dateTransf and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id)+"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Retour Definitif' and d.transfertStock.dateTransf =:dateTransf and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id)-"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Chargement' and d.transfertStock.dateTransf =:dateTransf and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id)-"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Chargement Supp' and d.transfertStock.dateTransf =:dateTransf and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id)-"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Transfert Sorties' and d.transfertStock.dateTransf =:dateTransf and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id)-"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Sorties' and d.transfertStock.dateTransf =:dateTransf and d.article.id= c.article.id and d.utilisateurCreation.depot.id= c.utilisateurCreation.depot.id) "
                + "from DetailTransfertStock c where c.transfertStock.magasinStock.id =:magasin group by c.article.id ");

        query.setParameter("magasin",magasin);
        query.setParameter("dateTransf",dateTransf);
        
        return query.list();
    }
         
         public List<Object[]> findBySituationStockAndDepot() {
        Query query=  session.createQuery("select c.article.code,c.article.libelle,"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Initial' and d.article.id= c.article.id ),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Reception' and d.article.id= c.article.id ),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where (d.transfertStock.statut = 'Chargement' or d.transfertStock.statut = 'Chargement Supp') and d.article.id= c.article.id ),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Retour Definitif' and d.article.id= c.article.id ),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Sorties' and d.article.id= c.article.id ),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Initial' and d.article.id= c.article.id )+"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Reception' and d.article.id= c.article.id )+"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Retour Definitif' and d.article.id= c.article.id )-"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where (d.transfertStock.statut = 'Chargement' or d.transfertStock.statut = 'Chargement Supp') and d.article.id= c.article.id )-"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Sorties' and d.article.id= c.article.id ) "
                + "from DetailTransfertStock c group by c.article.id ");

        
        return query.list();
    }
         
        public List<Object[]> findBySituationStockAndDateMinAndDepot(Date dateTransf) {
        Query query=  session.createQuery("select c.article.code,c.article.libelle,"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Initial' and d.transfertStock.dateTransf <:dateTransf and d.article.id= c.article.id),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Reception' and d.transfertStock.dateTransf <:dateTransf and d.article.id= c.article.id),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where (d.transfertStock.statut = 'Chargement' or d.transfertStock.statut = 'Chargement Supp') and d.transfertStock.dateTransf <:dateTransf and d.article.id= c.article.id),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Retour Definitif' and d.transfertStock.dateTransf <:dateTransf and d.article.id= c.article.id),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Sorties' and d.transfertStock.dateTransf <:dateTransf and d.article.id= c.article.id),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Initial' and d.transfertStock.dateTransf <:dateTransf and d.article.id= c.article.id)+"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Reception' and d.transfertStock.dateTransf <:dateTransf and d.article.id= c.article.id)+"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Retour Definitif' and d.transfertStock.dateTransf <:dateTransf and d.article.id= c.article.id)-"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where (d.transfertStock.statut = 'Chargement' or d.transfertStock.statut = 'Chargement Supp') and d.transfertStock.dateTransf <:dateTransf and d.article.id= c.article.id)-"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Sorties' and d.transfertStock.dateTransf <:dateTransf and d.article.id= c.article.id) "
                + "from DetailTransfertStock c group by c.article.id ");


        query.setParameter("dateTransf",dateTransf);
        
        return query.list();
    }
        
         public List<Object[]> findBySituationStockAndDateAndDepot(Date dateTransf) {
        Query query=  session.createQuery("select c.article.code,c.article.libelle,"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Initial' and d.transfertStock.dateTransf =:dateTransf and d.article.id= c.article.id),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Reception' and d.transfertStock.dateTransf =:dateTransf and d.article.id= c.article.id),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where (d.transfertStock.statut = 'Chargement' or d.transfertStock.statut = 'Chargement Supp') and d.transfertStock.dateTransf =:dateTransf and d.article.id= c.article.id),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Retour Definitif' and d.transfertStock.dateTransf =:dateTransf and d.article.id= c.article.id),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Sorties' and d.transfertStock.dateTransf =:dateTransf and d.article.id= c.article.id),"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Initial' and d.transfertStock.dateTransf =:dateTransf and d.article.id= c.article.id)+"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Reception' and d.transfertStock.dateTransf =:dateTransf and d.article.id= c.article.id)+"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Retour Definitif' and d.transfertStock.dateTransf =:dateTransf and d.article.id= c.article.id)-"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where (d.transfertStock.statut = 'Chargement' or d.transfertStock.statut = 'Chargement Supp') and d.transfertStock.dateTransf =:dateTransf and d.article.id= c.article.id)-"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Sorties' and d.transfertStock.dateTransf =:dateTransf and d.article.id= c.article.id) "
                + "from DetailTransfertStock c group by c.article.id ");

        query.setParameter("dateTransf",dateTransf);
        
        return query.list();
    }
         
           public List<Object[]> findByQteFinal(int article ,int magasin, int depot) {
        Query query=  session.createQuery("select c.id ,(select COALESCE(SUM(d.quantiteTotal),0) from DetailTransfertStock d where d.transfertStock.statut = 'Initial' and d.article.id=:article and d.utilisateurCreation.depot.id=:depot)+"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Reception' and d.article.id=:article and d.utilisateurCreation.depot.id=:depot)+"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Transfert Entrees' and d.article.id=:article and d.utilisateurCreation.depot.id=:depot)+"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Retour Definitif' and d.article.id=:article and d.utilisateurCreation.depot.id=:depot)-"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Chargement' and d.article.id=:article and d.utilisateurCreation.depot.id=:depot)-"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Chargement Supp' and d.article.id=:article and d.utilisateurCreation.depot.id=:depot)-"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Transfert Sorties' and d.article.id=:article and d.utilisateurCreation.depot.id=:depot)-"
                + "(select COALESCE(SUM(d.quantiteStock),0) from DetailTransfertStock d where d.transfertStock.statut = 'Sorties' and d.article.id=:article and d.utilisateurCreation.depot.id=:depot) "
                + "from DetailTransfertStock c where c.transfertStock.magasinStock.id =:magasin and c.article.id =:article ");

        query.setParameter("magasin",magasin);
        query.setParameter("article",article);
        query.setParameter("depot",depot);
        
        return query.list();
    }
        
             	public List<DetailTransfertStock> findByTransfertStock(int transf) {
		
		Query query = session.createQuery("select u from DetailTransfertStock u where u.transfertStock.id =:transf");
		query.setParameter("transf",transf);
               
                return query.list();
 }
       
                
                        	public List<DetailTransfertStock> findByDeclarationReception(int decRec) {
		
		Query query = session.createQuery("select u from DetailTransfertStock u where u.transfertStock.declarationReception.id =:decRec");
		query.setParameter("decRec",decRec);
               
                return query.list();
 }
}
