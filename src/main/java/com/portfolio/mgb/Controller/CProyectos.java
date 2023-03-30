/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.mgb.Controller;

import com.portfolio.mgb.Dto.dtoProyectos;
import com.portfolio.mgb.Entity.Proyectos;
import com.portfolio.mgb.Security.Controller.Mensaje;
import com.portfolio.mgb.Service.SProyectos;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proyectos")
@CrossOrigin(origins = {"https://frontendargentinaprom.web.app", "http://localhost:4200"})
public class CProyectos {
    @Autowired
    SProyectos sProyectos;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Proyectos>> list(){
        List<Proyectos> list = sProyectos.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoProyectos dtopro){
        if(StringUtils.isBlank(dtopro.getNombreP())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST); 
        }
        if(sProyectos.existsByNombreP(dtopro.getNombreP())){
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtopro.getDescripcionP())){
            return new ResponseEntity(new Mensaje("La descripcion es obligatoria"), HttpStatus.BAD_REQUEST); 
        }
        
        Proyectos proyectos = new Proyectos(dtopro.getNombreP(), dtopro.getDescripcionP(), dtopro.getImg());
        sProyectos.save(proyectos);
        
        return new ResponseEntity(new Mensaje("Proyectos agregado"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoProyectos dtopro){
        //Validamos si existe el ID
        if(!sProyectos.existsById(id)){
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        //Compara nombre de Proyectos
        if(sProyectos.existsByNombreP(dtopro.getNombreP()) && sProyectos.getByNombreP(dtopro.getNombreP()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        //NO puede estar vacio
        if(StringUtils.isBlank(dtopro.getNombreP())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"),HttpStatus.BAD_REQUEST);
        }
        
        Proyectos proyectos = sProyectos.getOne(id).get();
        
        proyectos.setNombreP(dtopro.getNombreP());
        proyectos.setDescripcionP(dtopro.getDescripcionP());
        proyectos.setImg(dtopro.getImg());
        
        sProyectos.save(proyectos);
        return new ResponseEntity(new Mensaje("Proyectos actualizada"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        //Validamos si existe el ID
        if(!sProyectos.existsById(id)){
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
        
        sProyectos.delete(id);
        return new ResponseEntity(new Mensaje("Proyectos eliminada"), HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyectos> getById(@PathVariable("id") int id){
        if(!sProyectos.existsById(id)){
            return new ResponseEntity(new Mensaje("ID no existe"), HttpStatus.NOT_FOUND);
        }
        
        Proyectos proyectos = sProyectos.getOne(id).get();
        
        return new ResponseEntity(proyectos, HttpStatus.OK);
    }
    
}
