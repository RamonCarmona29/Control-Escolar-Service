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

/**
 *
 * @author HP
 */
@Entity
public class Alumno {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "idalumno")
    private int idAlumno;
    
    @Column (name = "nombre")
    private String nombre;
    
    @Column (name = "apellidopaterno")
    private String apellidoPaterno;
    
    @Column (name = "apellidomaterno")
    private String apellidoMaterno;

    public Alumno() {
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int IdAlumno) {
        this.idAlumno = IdAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String ApellidoPaterno) {
        this.apellidoPaterno = ApellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String ApellidoMaterno) {
        this.apellidoMaterno = ApellidoMaterno;
    }
    
    
    
}
