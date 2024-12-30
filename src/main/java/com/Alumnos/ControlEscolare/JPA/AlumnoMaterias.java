/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Alumnos.ControlEscolare.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author Alien 9
 */
@Entity
@Table(name = "alumnomaterias")
public class AlumnoMaterias {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlumnoMaterias")
    private int idAlumnoMaterias;
    
    private double TotalCosto;
    
    @ManyToOne
    @JoinColumn(name = "idAlumno")
    public Alumno alumno;
    
    @ManyToOne
    @JoinColumn(name = "idMateria")
    public Materia materia;

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public int getIdAlumnoMaterias() {
        return idAlumnoMaterias;
    }

    public void setIdAlumnoMaterias(int idAlumnoMaterias) {
        this.idAlumnoMaterias = idAlumnoMaterias;
    }

    public double getTotalCosto() {
        return TotalCosto;
    }

    public void setTotalCosto(double TotalCosto) {
        this.TotalCosto = TotalCosto;
    }
            
    
}
