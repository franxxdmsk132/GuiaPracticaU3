package com.ista.Franklin.GuiaPracticaU3.Controller;

import com.ista.Franklin.GuiaPracticaU3.Entity.Student;
import com.ista.Franklin.GuiaPracticaU3.Services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private final StudentServices studentServices;

    public StudentController(StudentServices studentServices) {
        this.studentServices = studentServices;
    }


    @GetMapping("/lista")
    public String listStudents(Model theModel) {
        List<Student> student = studentServices.studentsList();
        theModel.addAttribute("student", student);
        return "students/students-list";
    }

    @RequestMapping("/showStudentDetails")
    public String viewStudent(@RequestParam("studentNumber") Integer studentNumber, Model model) {
        Student student = studentServices.getStudentByStudentNumber(studentNumber);
        model.addAttribute("student", student);
        return "students/students-details";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        Student student = new Student();
        theModel.addAttribute("student", student);
        return "students/students-form";
    }
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("studentNumber") Integer studentNumber, Model theModel) {
        Student student= studentServices.getStudentByStudentNumber(studentNumber);
        theModel.addAttribute("student", student);
        return "students/students-updateForm";
    }

    //xd
    @PostMapping("/save2")
    public String saveArticulo(@ModelAttribute("student") Student student, @RequestParam("file")
    MultipartFile imagen) {

        if (!imagen.isEmpty()){
            Path directorioImagenes = Paths.get("src//main//resources//static/images");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);
                student.setImage(imagen.getOriginalFilename());

            }catch (IOException e ){
                e.printStackTrace();

            }
        }
        studentServices.createStudent(student);
        return "redirect:/students/lista";
    }

    @PostMapping("/save")
    public Student createStudent(@RequestBody Student student) {
        return studentServices.createStudent(student);
    }

    @GetMapping("/{id}")
    public Student getStudentByStudentNumber(@PathVariable Integer number) {
        return studentServices.getStudentByStudentNumber(number);
    }

    @GetMapping("/list")
    public List<Student> studentsList() {
        return studentServices.studentsList();
    }

    @GetMapping("/listgpa")
    public List<Student> studentsListByGPA() {
        return studentServices.studentListByGpa();
    }

    @GetMapping("/{email}")
    public Student getByID(@PathVariable String email) {

        return studentServices.getStudentByEmail(email);
    }

    @DeleteMapping("/del/{id}")
    public void deleteByID(@PathVariable Integer id) {
        studentServices.deleteStudent(id);
    }
}
