package com.nuzhd.controller;

import com.nuzhd.model.Student;
import com.nuzhd.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentFormController {
    private StudentService studentService;
    public StudentFormController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/new_student")
    public String showAddStudentPage(Model model) {
        model.addAttribute("student", new Student());
        return "new_student";
    }
    @PostMapping("/new_student")
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
    public String deleteStudent(@PathVariable("id") Long id) {

        Student studentToDelete = studentService.getById(id);

        if (studentToDelete == null) {
            return "redirect:/?not_found";
        }

        studentService.delete(studentToDelete);
        return "redirect:/?deleted";
    }
}
