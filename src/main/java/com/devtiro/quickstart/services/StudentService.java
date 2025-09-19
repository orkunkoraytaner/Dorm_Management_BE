package com.devtiro.quickstart.services;

import com.devtiro.quickstart.entity.Student;
import com.devtiro.quickstart.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student saveStudent(Student student)
    {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents()
    {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id)
    {
        return studentRepository.findById(id).orElse(null);
    }

    public Student updateStudent(Long id, Student student)
    {
        Student updatedStudent = studentRepository.findById(id).orElse(null);
        if(updatedStudent != null)
        {
            updatedStudent.setFirstName(student.getFirstName());
            updatedStudent.setLastName(student.getLastName());
            updatedStudent.setEmail(student.getEmail());
            updatedStudent.setDepartment(student.getDepartment());
            updatedStudent.setDeskNumber(student.getDeskNumber());
            return studentRepository.save(updatedStudent);
        }
        else
        {
            logger.warn("Student with id {} not found", id);
            return null;
        }
    }

    public void deleteStudent(Long id)
    {
        studentRepository.deleteById(id);
    }
}
