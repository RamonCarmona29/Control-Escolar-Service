/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Alumnos.ControlEscolare.DAO;

import com.Alumnos.ControlEscolare.JPA.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Alien 9
 */
public interface MateriaRepository extends JpaRepository<Materia, Integer>{
    
}
