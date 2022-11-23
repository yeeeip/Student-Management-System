package com.nuzhd.service.impl;

import com.nuzhd.model.Student;
import com.nuzhd.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;


    @Test
    public void shouldReturnAllStudents() {

        List<Student> students = getStudents();

        Mockito.when(studentRepository.findAll())
                .thenReturn(students);

        List<Student> result = studentService.getAll();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(4, result.size());
        assertThat(result).isEqualTo(students);
    }

    @Test
    public void shouldReturnStudentById() {

        Long id = 2L;
        List<Student> students = getStudents();

        Mockito.when(studentRepository.findById(id))
                .thenReturn(Optional.ofNullable(students.get(1)));

        Student result = studentService.getById(id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result, students.get(1));
    }


    private List<Student> getStudents() {

        Student s1 = new Student("Andrey", "Pertov", "andre@mail.ru");
        s1.setId(1L);
        Student s2 = new Student("Kirill", "Ivenov", "kirill2005@yandex.ru");
        s2.setId(2L);
        Student s3 = new Student("Arseniy", "Chizhikov", "arsChz@gmail.com");
        s3.setId(3L);
        Student s4 = new Student("Anton", "Vasin", "vasin1990@rambler.ru");
        s4.setId(4L);

        return Arrays.asList(s1, s2, s3, s4);
    }
}
