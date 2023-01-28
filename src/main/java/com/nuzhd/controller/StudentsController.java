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
    public String getStudents(Model model, HttpServletRequest request) {

        model.addAttribute("students", studentService.getAll());
        model.addAttribute("baseUrl", request.getRequestURI());

        return "students";
    }

    @GetMapping("/new")
    public String showAddStudentPage(Model model, HttpServletRequest request) {

        model.addAttribute("student", new Student());
        model.addAttribute("baseUrl", request.getRequestURI());

        return "new_student";
    }

    @PostMapping("/new")
    public String addStudent(@Valid Student student, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/api/v1/students/new?error";
        }

        studentService.save(student);
        return "redirect:/api/v1/students?added";
    }

    @GetMapping("/{studentId}/update")
    public String showUpdateStudentPage(@PathVariable("studentId") Long studentId, Model model, HttpServletRequest request) {
        Student studentToEdit = studentService.getById(studentId);

        if (studentToEdit == null) {
            return "redirect:/?not_found";
        }

        model.addAttribute("student", studentToEdit);
        model.addAttribute("baseUrl", request.getRequestURI());

        return "update_student";
    }

    @PostMapping("/{studentId}/update")
    public String updateStudent(@PathVariable("studentId") Long studentId, @Valid Student editedStudent, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/update/" + studentId + "?error";
        }

        Student currentStudent = studentService.getById(studentId);

        currentStudent.setFirstName(editedStudent.getFirstName());
        currentStudent.setLastName(editedStudent.getLastName());
        currentStudent.setEmail(editedStudent.getEmail());
        studentService.save(currentStudent);
        return "redirect:/api/v1/students?updated";
    }

    @GetMapping("/{studentId}/delete")
    public String deleteStudent(@PathVariable("studentId") Long studentId, HttpServletRequest request) {

        Student studentToDelete = studentService.getById(studentId);

        if (studentToDelete == null) {
            return "redirect:/api/v1/students?not_found";
        }

        studentService.delete(studentToDelete);
        return "redirect:/api/v1/students?deleted";

    }
}
