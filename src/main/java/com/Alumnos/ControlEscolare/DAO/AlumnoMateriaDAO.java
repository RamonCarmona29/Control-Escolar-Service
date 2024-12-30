/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Alumnos.ControlEscolare.DAO;

import com.Alumnos.ControlEscolare.JPA.Alumno;
import com.Alumnos.ControlEscolare.JPA.AlumnoMaterias;
import com.Alumnos.ControlEscolare.JPA.Materia;
import com.Alumnos.ControlEscolare.JPA.Result;
import jakarta.transaction.Transactional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alien 9
 */
@Repository
public class AlumnoMateriaDAO implements IAlumnoMateriaDao{
    

    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Result GetAll() {
        Result result = new Result();
        
        String query = "SELECT * FROM vistaalumnomateria";
        
        try (Connection context = dataSource.getConnection();
                Statement statement = context.createStatement();
                ResultSet resultSet = statement.executeQuery(query);) {
            
            List<AlumnoMaterias> listAlumnosMaterias = new ArrayList<>();
            
            while (resultSet.next()) {                
                
                AlumnoMaterias alumnoMaterias = new AlumnoMaterias();
                
                alumnoMaterias.setIdAlumnoMaterias(resultSet.getInt("IdAlumnoMaterias"));
                
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(resultSet.getInt("idAlumno"));
                alumno.setNombre(resultSet.getString("Nombre"));
                alumno.setApellidoPaterno(resultSet.getString("ApellidoPaterno"));
                alumno.setApellidoMaterno(resultSet.getString("ApellidoMaterno"));
                
                Materia materia = new Materia();
                materia.setIdMateria(resultSet.getInt("IdMateria"));
                materia.setNombre(resultSet.getString("NombreMateria"));
                materia.setCosto(resultSet.getDouble("Costo"));

                alumnoMaterias.setAlumno(alumno);
                alumnoMaterias.setMateria(materia);

                listAlumnosMaterias.add(alumnoMaterias);
            }
            result.correct = true;
            result.object = listAlumnosMaterias;
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getMessage();
            result.ex = ex;
        }
         return result;
    }

    @Override
    public Result GetbyId(int IdAlumno) {
       Result result = new Result();
    
    String query = "SELECT * FROM vistaalumnomateria WHERE idAlumno = ?";
    
    try (Connection context = dataSource.getConnection();
         PreparedStatement statement = context.prepareStatement(query)) {
        
        statement.setInt(1, IdAlumno); 
        
        try (ResultSet resultSet = statement.executeQuery()) {
            
            List<AlumnoMaterias> listAlumnosMaterias = new ArrayList<>();
            
            while (resultSet.next()) {                
                
                AlumnoMaterias alumnoMaterias = new AlumnoMaterias();
                
                alumnoMaterias.setIdAlumnoMaterias(resultSet.getInt("IdAlumnoMaterias"));
                
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(resultSet.getInt("IdAlumno"));
                alumno.setNombre(resultSet.getString("Nombre"));
                alumno.setApellidoPaterno(resultSet.getString("ApellidoPaterno"));
                alumno.setApellidoMaterno(resultSet.getString("ApellidoMaterno"));
                
                Materia materia = new Materia();
                materia.setIdMateria(resultSet.getInt("IdMateria"));
                materia.setNombre(resultSet.getString("NombreMateria"));
                materia.setCosto(resultSet.getDouble("Costo"));

                alumnoMaterias.setAlumno(alumno);
                alumnoMaterias.setMateria(materia);

                listAlumnosMaterias.add(alumnoMaterias);
            }
            result.correct = true;
            result.object = listAlumnosMaterias;
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getMessage();
            result.ex = ex;
        }
        
    } catch (Exception ex) {
        result.correct = false;
        result.errorMessage = ex.getMessage();
        result.ex = ex;
    }
    
    return result;
    }

    @Override
    public Result agregarAlumnoMateria(int IdAlumno, int IdMateria) {
        Result result = new Result();
        String sql = "INSERT INTO alumnomaterias(idalumno, idmateria) VALUES (?, ?)";

        try {
            int rowsAffected = jdbcTemplate.update(sql, IdAlumno, IdMateria);

            if (rowsAffected > 0) {
                result.correct = true;
                result.object = "Relación alumno-materia creada correctamente";
            } else {
                result.correct = false;
                result.errorMessage = "No se pudo crear la relación";
            }
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    @Transactional
    public Result DelateAlumnoMaterias(int IdAlumnoMaterias) {
        
        Result result = new Result();
        String sql = "DELETE FROM alumnomaterias WHERE idalumnomaterias = ?";

        try {
            int rowsAffected = jdbcTemplate.update(sql, IdAlumnoMaterias);

            if (rowsAffected > 0) {
                result.correct = true;
            } else {
                result.correct = false;
            }
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    public Result Materis(int IdAlumno) {
    
        Result result = new Result();
        
        String sql = "SELECT Materia.IdMateria, Materia.Nombre AS NombreMateria, Materia.Costo " +
                 "FROM Materia " +
                 "LEFT JOIN AlumnoMaterias ON Materia.IdMateria = AlumnoMaterias.IdMateria " +
                 "AND AlumnoMaterias.IdAlumno = ? " +
                 "WHERE AlumnoMaterias.IdMateria IS NULL";
    
    try (Connection context = dataSource.getConnection();
         PreparedStatement statement = context.prepareStatement(sql)) {
         
        statement.setInt(1, IdAlumno);

        try (ResultSet resultSet = statement.executeQuery()) {
            List<AlumnoMaterias> listAlumnoMaterias = new ArrayList<>();

            while (resultSet.next()) {
                AlumnoMaterias alumnoMaterias = new AlumnoMaterias();
                
                Materia materia = new Materia();
                materia.setIdMateria(resultSet.getInt("IdMateria"));
                materia.setNombre(resultSet.getString("NombreMateria"));
                materia.setCosto(resultSet.getDouble("Costo"));

                alumnoMaterias.setMateria(materia);

                listAlumnoMaterias.add(alumnoMaterias);
            }
            result.correct = true;
            result.object = listAlumnoMaterias;
        } catch (SQLException ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
    } catch (SQLException ex) {
        result.correct = false;
        result.errorMessage = ex.getLocalizedMessage();
        result.ex = ex;
    }
        return result;
    }

    @Override
    public Result Sum(int IdAlumno) {
       
        Result result = new Result();
        
        String sql = "SELECT * FROM vistaalumnomateriacosto WHERE vistaalumnomateriacosto.idalumno = ?";
        
        try (Connection context = dataSource.getConnection();
         PreparedStatement statement = context.prepareStatement(sql)){
            
            statement.setInt(1, IdAlumno);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                
                List<AlumnoMaterias> listalumnoMaterias = new ArrayList<>();
                
                while (resultSet.next()) {                    
                AlumnoMaterias alumnoMaterias = new AlumnoMaterias();
                
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(resultSet.getInt("IdAlumno"));
                alumno.setNombre(resultSet.getString("Nombre"));
                alumno.setApellidoPaterno(resultSet.getString("ApellidoPaterno"));
                alumno.setApellidoMaterno(resultSet.getString("ApellidoMaterno"));
                
                Materia materia = new Materia();
                materia.setIdMateria(resultSet.getInt("IdMateria"));
                materia.setNombre(resultSet.getString("NombreMateria"));
                materia.setCosto(resultSet.getDouble("Costo"));
                
                double totalCosto = resultSet.getDouble("TotalCosto");
                
                alumnoMaterias.setAlumno(alumno);
                alumnoMaterias.setMateria(materia);
                alumnoMaterias.setTotalCosto(totalCosto);
                
                listalumnoMaterias.add(alumnoMaterias);
                }
                
                result.correct = true;
                result.object = listalumnoMaterias;
                
            } catch (Exception ex) {
                result.correct = false;
                result.errorMessage = ex.getLocalizedMessage();
                result.ex = ex;
            }
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }
}
