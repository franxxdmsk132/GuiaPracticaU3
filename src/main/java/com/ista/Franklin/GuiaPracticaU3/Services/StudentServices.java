package com.ista.Franklin.GuiaPracticaU3.Services;

import com.ista.Franklin.GuiaPracticaU3.Entity.Student;
import com.ista.Franklin.GuiaPracticaU3.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServices {

    @Autowired
    private final StudentRepository studentRepository;

    public StudentServices(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    //Crear Student
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    //Obtener Student por ID
    public Student getStudent(Integer id) {
        return studentRepository.findById(id).get();
    }

    //Obtener Student por Numero de Estudiante
    public Student getStudentByStudentNumber(Integer number) {
        return studentRepository.findById(number).get();
    }

    //Obtener Student por Email
    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    //Listar todos los Students
    public List<Student> studentsList() {
        return studentRepository.findAll();
    }

    //Listar todos los Students ordenados por GPA
    public List<Student> studentListByGpa() {
        return studentRepository.findAllByOrderByGpa();
    }

    //Eliminar por ID al Student
    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }
}
