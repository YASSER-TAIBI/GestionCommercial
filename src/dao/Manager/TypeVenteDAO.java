/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;


import dao.Entity.TypeVente;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface TypeVenteDAO {
    
    public void add(TypeVente tSypeVente);

    public TypeVente findById(int id);

    public TypeVente edit(TypeVente e);

    public void delete(TypeVente e);

    public List<TypeVente> findAll();
    
    public TypeVente findTypeVenteByCodeTypeVente(String code);
      
    public List<TypeVente> findTypeVenteByCodeTypeVente(); 
    
    public List<String> findTypeVenteByCodeType() ;
       
    public TypeVente findTypeVenteByCombo(String code);
     
    public List<TypeVente> findByTypeVenteGrosDetail() ;
}
