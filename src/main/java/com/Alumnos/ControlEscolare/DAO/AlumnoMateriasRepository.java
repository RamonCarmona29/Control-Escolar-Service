/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Alumnos.ControlEscolare.DAO;

import com.Alumnos.ControlEscolare.JPA.Alumno;
import com.Alumnos.ControlEscolare.JPA.AlumnoMaterias;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Alien 9
 */
public interface AlumnoMateriasRepository extends JpaRepository<AlumnoMaterias, Integer>{
    
    List<AlumnoMaterias> findByAlumno(Alumno alumno);
}
