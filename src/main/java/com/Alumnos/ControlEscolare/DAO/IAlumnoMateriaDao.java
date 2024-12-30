/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Alumnos.ControlEscolare.DAO;

import com.Alumnos.ControlEscolare.JPA.Result;

/**
 *
 * @author Alien 9
 */
public interface IAlumnoMateriaDao {
    
    Result GetAll();
    
    Result GetbyId(int IdAlumno);
    
    Result agregarAlumnoMateria(int IdAlumno, int IdMateria);
    
    Result DelateAlumnoMaterias (int IdAlumnoMaterias);
    
    Result Materis(int IdAlumno);
    
    Result Sum(int IdAlumno);
}
