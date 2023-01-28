package com.nuzhd.controller;

import com.nuzhd.model.Student;
import com.nuzhd.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/students")
public class StudentsController {
    private final StudentService studentService;

    public StudentsController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String getStudents(Model model) {
        model.addAttribute("students", studentService.getAll());
        return "students";
    }

    @GetMapping("/new")
    public String showAddStudentPage(Model model) {
        model.addAttribute("student", new Student());
        return "new_student";
    }

    @PostMapping("/new")
    public String addStudent(@Valid Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/new_student?error";
        }

        studentService.save(student);
        return "redirect:?added";
    }

    @GetMapping("/update/{id}")
    public String showUpdateStudentPage(@PathVariable("id") Long id, Model model) {
        Student studentToEdit = studentService.getById(id);

        if (studentToEdit == null) {
            return "redirect:/?not_found";
        }

        model.addAttribute("student", studentToEdit);
        return "update_student";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") Long id, @Valid Student editedStudent, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/update/" + id + "?error";
        }

        Student currentStudent = studentService.getById(id);

        currentStudent.setFirstName(editedStudent.getFirstName());
        currentStudent.setLastName(editedStudent.getLastName());
        currentStudent.setEmail(editedStudent.getEmail());
        studentService.save(currentStudent);
        return "redirect:/?updated";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id, HttpServletRequest request) {

        Student studentToDelete = studentService.getById(id);

        if (studentToDelete == null) {
            return "redirect:" + request.getRequestURI() + "/?not_found";
        }

        studentService.delete(studentToDelete);
        return "redirect:" + request.getRequestURI() + "/?deleted";

    }
}
