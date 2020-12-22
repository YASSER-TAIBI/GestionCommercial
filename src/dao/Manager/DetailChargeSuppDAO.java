/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.DetailChargeSupp;
import java.util.List;

/**
 *
 * @author Hp
 */

public interface DetailChargeSuppDAO {
    
                public DetailChargeSupp findById(int id);
		
		public void add(DetailChargeSupp detailChargeSupp);
		
		public  DetailChargeSupp edit(DetailChargeSupp e);
		
		public  void delete(DetailChargeSupp e); 
		
		public List<DetailChargeSupp> findAll();

                public List<DetailChargeSupp> findDetailTrnByTrn(int detailTournee); 
              
}
