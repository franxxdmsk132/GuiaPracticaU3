package com.ista.Franklin.GuiaPracticaU3.Repository;

import com.ista.Franklin.GuiaPracticaU3.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByStudentNumber(Integer studentNumber);

    Student findByEmail(String email);

    List<Student> findAllByOrderByGpa();
}
