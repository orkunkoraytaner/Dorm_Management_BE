package com.devtiro.quickstart.controller;


import com.devtiro.quickstart.entity.Student;
import com.devtiro.quickstart.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }


    @GetMapping("/statistics/by-department")
    public Map<String, Integer> getStudentCountByDepartment()
    {
        return studentService.countStudentByDepartment();
    }

    @GetMapping("/by-room/{room_id}")
    public List<Student> getStudentsByRoom(@PathVariable Long room_id) {
        return studentService.findStudentsByRoom(room_id);
    }

    @GetMapping("/floor/{id}")
    public int getFloorOfStudent(@PathVariable Long id)
    {
        return studentService.findFloorOfStudent(id);
    }

}