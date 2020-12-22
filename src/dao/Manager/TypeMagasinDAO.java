/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.TypeMagasin;
import java.util.List;

/**
 *
 * @author pc
 */
public interface TypeMagasinDAO {
     public void add(TypeMagasin typemagasin);

    public TypeMagasin findById(int id);

    public TypeMagasin edit(TypeMagasin e);

    public void delete(TypeMagasin e);

    public List<TypeMagasin> findAll();
    
}
