/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.mgb.Service;

import com.portfolio.mgb.Entity.Proyectos;
import com.portfolio.mgb.Repository.RProyectos;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SProyectos {
    @Autowired
    RProyectos Rproyectos;
    
    public List<Proyectos> list(){
        return Rproyectos.findAll();
    }
    
    public Optional<Proyectos> getOne(int id){
        return Rproyectos.findById(id);
    }
    
    public Optional<Proyectos> getByNombreP(String nombreP){
        return Rproyectos.findByNombreP(nombreP);
    }
    
    public void save(Proyectos proyectos){
        Rproyectos.save(proyectos);
    }
    
    public void delete(int id){
        Rproyectos.deleteById(id);
    }
    
    public boolean existsById(int id){
        return Rproyectos.existsById(id);
    }
    
    public boolean existsByNombreP(String nombreP){
        return Rproyectos.existsByNombreP(nombreP);
    }
    
}
