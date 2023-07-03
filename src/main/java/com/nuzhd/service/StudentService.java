package com.nuzhd.service;

import com.nuzhd.model.Student;

import java.util.List;
public interface StudentService {
    Student getById(Long id);
    List<Student> getAll();
    Student save(Student student);
    void delete(Student student);
}
