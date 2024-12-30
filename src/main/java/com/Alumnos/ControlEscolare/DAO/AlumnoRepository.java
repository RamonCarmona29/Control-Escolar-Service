/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Alumnos.ControlEscolare.DAO;

import com.Alumnos.ControlEscolare.JPA.Alumno;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author HP
 */
@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer>{
    
    Optional<Alumno> findByNombreAndApellidoPaterno(String Nombre, String ApellidoPaterno);
    
}
