package com.nuzhd.controller;

import com.nuzhd.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String getStudents(Model model) {
        model.addAttribute("students", studentService.getAll());
        return "students";
    }

}
