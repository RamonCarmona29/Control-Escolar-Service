/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Alumnos.ControlEscolare.Controllers;

import com.Alumnos.ControlEscolare.DAO.AlumnoMateriaDAO;
import com.Alumnos.ControlEscolare.DAO.AlumnoRepository;
import com.Alumnos.ControlEscolare.DAO.MateriaRepository;
import com.Alumnos.ControlEscolare.JPA.Alumno;
import com.Alumnos.ControlEscolare.JPA.AlumnoMaterias;
import com.Alumnos.ControlEscolare.JPA.Materia;
import com.Alumnos.ControlEscolare.JPA.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.Alumnos.ControlEscolare.DAO.AlumnoMateriasRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 *
 * @author HP
 */
@RestController
@RequestMapping("/ApiRest")
@CrossOrigin(origins = "http://localhost:8080")
public class ApiRestController {
    
    @Autowired
    AlumnoRepository alumnoRepository;
    
    @Autowired
    AlumnoMateriasRepository alumnomateriasRepository;
    
    @Autowired
    MateriaRepository materiaRepository;
    
    @Autowired
    AlumnoMateriaDAO alumnoMateriaDAO;
    
    private Alumno loginAlumno = null;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @GetMapping("/Alumno")
    public  ResponseEntity<Result> GetAlumno (){
        
        Result result= new Result();
        
        try {
            List<Alumno> listAlumnos = alumnoRepository.findAll();
            
            result.correct = true;
            result.object = listAlumnos;
            
            return ResponseEntity.accepted().body(result);
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            return ResponseEntity.badRequest().body(result);
        }
    }
    
    @GetMapping("/AlumnoMateria")
    public  ResponseEntity<Result> GetAlumnoMateria (){
        
        Result result= new Result();
        
       result = alumnoMateriaDAO.GetAll();
       
        if (result.correct = true) {
            return ResponseEntity.accepted().body(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }
    
    @GetMapping("/AlumnoMateriaId/{idAlumno}")
    public ResponseEntity<Result> GetByIdAlumnoMateria(@PathVariable int idAlumno) {

        Result result = new Result();

        result = alumnoMateriaDAO.GetbyId(idAlumno);

        if (result.correct = true) {
            return ResponseEntity.accepted().body(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }
    
    @GetMapping("/Suma/{idAlumno}")
    public ResponseEntity<Result> Sumaterias (@PathVariable int idAlumno){
        
        Result result = new Result();
        
        result = alumnoMateriaDAO.Sum(idAlumno);
        
        if (result.correct = true) {
            return  ResponseEntity.accepted().body(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    @GetMapping("/Materia")
    public  ResponseEntity<Result> GetMateria (){
        
        Result result= new Result();
        
        try {
            List<Materia> listMateria = materiaRepository.findAll();
            
            result.correct = true;
            result.object = listMateria;
            
            return ResponseEntity.accepted().body(result);
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            return ResponseEntity.badRequest().body(result);
        }
    }
    
    @PostMapping("/Materia")
    public ResponseEntity<Result> addMateria(@RequestBody Materia materia){
        
        Result result = new Result();
        
        if (materia.getIdMateria() == 0) {
            try {
                materiaRepository.save(materia);
                result.correct = true;
                result.object = materia;
                return ResponseEntity.ok(result);
            } catch (Exception ex) {
                result.correct = false;
                result.errorMessage = ex.getLocalizedMessage();
                result.ex = ex;
                return ResponseEntity.badRequest().body(result);
            }
        } else {
            
            try {
                materiaRepository.save(materia);
                result.correct = true;
                result.object = materia;
                return ResponseEntity.accepted().body(result);
            } catch (Exception ex) {
                result.correct = false;
                result.errorMessage = ex.getLocalizedMessage();
                result.ex = ex;
                return ResponseEntity.badRequest().body(result);
            }
        }
    }
    
    @PostMapping("/Alumno")
    public ResponseEntity<Result> AddAlumno(@RequestBody Alumno alumno) {

        Result result = new Result();

        if (alumno.getIdAlumno() == 0) {
            try {
                alumnoRepository.save(alumno);
                result.correct = true;
                return ResponseEntity.ok().body(result);
            } catch (Exception ex) {
                result.correct = false;
                result.errorMessage = ex.getLocalizedMessage();
                result.ex = ex;
                return ResponseEntity.badRequest().body(result);
            }
        } else {
            try {
                alumnoRepository.save(alumno);
                result.correct = true;
                return ResponseEntity.accepted().body(result);
            } catch (Exception ex) {
                result.correct = false;
                result.errorMessage = ex.getLocalizedMessage();
                result.ex = ex;
                return ResponseEntity.badRequest().body(result);
            }

        }
    }
    
    @PostMapping("/Delate/{idAlumno}")
    public ResponseEntity<Result> eliminaralumno(@PathVariable int idAlumno){
        
        Result result = new Result();
        
        try {
            alumnoRepository.deleteById(idAlumno);
            result.correct = true;
            return ResponseEntity.ok().body(result);
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            return ResponseEntity.badRequest().body(result);
        }
    }
    
    @PostMapping("/DeleteMateria/{idMateria}")
    public ResponseEntity<Result> EliminarMateria(@PathVariable int idMateria){
        
        Result result = new Result();
        
        try {
            materiaRepository.deleteById(idMateria);
            result.correct = true;
            return ResponseEntity.ok().body(result);
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            return ResponseEntity.badRequest().body(result);
        }
    }
    @GetMapping("/AlmnoMateriaList/{idAlumno}")
    public ResponseEntity<Result> ListMaterias (@PathVariable int idAlumno){
        
        Result result = new Result();
        
        result = alumnoMateriaDAO.Materis(idAlumno);
        
        if (result.correct = true) {
            return ResponseEntity.accepted().body(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
        
    }
    
    @GetMapping("/DelateAlumnoMateria/{idAlumnoMaterias}")
    public ResponseEntity<Result> deleteAlumMateria (@PathVariable int idAlumnoMaterias){
        
        Result result = new Result();

        result = alumnoMateriaDAO.DelateAlumnoMaterias(idAlumnoMaterias);

        if (result.correct = true) {
            return ResponseEntity.accepted().body(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }
    
    @PostMapping("/NuevaMateria")
   public ResponseEntity<Result> agregarAlumnoMateria(@RequestParam int idAlumno, @RequestParam int idMateria) {

       Result result = new Result();

        result = alumnoMateriaDAO.agregarAlumnoMateria(idAlumno, idMateria);

        if (result.correct = true) {
            return ResponseEntity.accepted().body(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }
    
    
    @GetMapping("/id/{idAlumno}")
    public ResponseEntity<Result> GetbyIdAlumno(@PathVariable int idAlumno) {

        Result result = new Result();
        try {
            Alumno alumno = new Alumno();
            alumno = alumnoRepository.findById(idAlumno).orElseThrow();
            result.object = alumno;
            result.correct = true;
            return ResponseEntity.accepted().body(result);
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            return ResponseEntity.badRequest().body(result);
        }

    }
    
    @GetMapping("/materiaid/{idMateria}")
    public ResponseEntity<Result> GetbyIdMateria(@PathVariable int idMateria){
        
        Result result = new Result();
        try {
            Materia materia = new Materia();
            materia = materiaRepository.findById(idMateria).orElseThrow();
            result.object = materia;
            result.correct = true;
            return ResponseEntity.accepted().body(result);
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            return ResponseEntity.badRequest().body(result);
        }
    }
    
    @GetMapping("/login")
    public ResponseEntity<?> getSessionUser() {
        
        if (loginAlumno != null) {
            return ResponseEntity.ok(loginAlumno); 
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No active session");
        }
    }  
    
    @PostMapping("/login")
    public ResponseEntity<Result> logins(@RequestParam String nombre, @RequestParam String apellidopaterno) {

        Result result = new Result();

        try {
            Optional<Alumno> alumnoOpcional = alumnoRepository.findByNombreAndApellidoPaterno(nombre, apellidopaterno);

            if (alumnoOpcional.isPresent()) {
                loginAlumno = alumnoOpcional.get();
                result.correct = true;
                result.object = alumnoOpcional;
            }
            return ResponseEntity.ok(result);

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }
    
    @PostMapping("/logout")
    public ResponseEntity<Result> logout() {

        Result result = new Result();

        try {
            loginAlumno = null;
            return ResponseEntity.ok(result);

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }
  
    
    
}
