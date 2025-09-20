package com.devtiro.quickstart.services;

import com.devtiro.quickstart.entity.Room;
import com.devtiro.quickstart.entity.Student;
import com.devtiro.quickstart.exceptions.NoEmptySpaceException;
import com.devtiro.quickstart.exceptions.StudentNotFoundException;
import com.devtiro.quickstart.repository.RoomRepository;
import com.devtiro.quickstart.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final RoomRepository roomRepository;
    private final RoomService roomService;
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    public StudentService(StudentRepository studentRepository, RoomRepository roomRepository, RoomService roomService) {
        this.studentRepository = studentRepository;
        this.roomRepository = roomRepository;
        this.roomService = roomService;
    }

    public Student saveStudent(Student student)
    {
        try {
            Room room = student.getRoom();
            roomService.reduceRoomSpaceByOne(room.getId());
            return studentRepository.save(student);
        }catch (NoEmptySpaceException e) {
            logger.error(e.getMessage());
            throw e;
        }

    }

    public List<Student> getAllStudents()
    {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if(studentOptional.isPresent())
        {
            return studentOptional.get();
        }
        else
            throw new StudentNotFoundException("Couldn't find student with id: " + id);
    }

    public Student updateStudent(Long id, Student student)
    {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isPresent())
        {
            Student updatedStudent = optionalStudent.get();
            updatedStudent.setFirstName(student.getFirstName());
            updatedStudent.setLastName(student.getLastName());
            updatedStudent.setEmail(student.getEmail());
            updatedStudent.setDepartment(student.getDepartment());
            updatedStudent.setDeskNumber(student.getDeskNumber());
            return studentRepository.save(updatedStudent);
        }
        else
        {
            throw new StudentNotFoundException("Couldn't find student with id: " + id);
        }
    }

    public void deleteStudent(Long id)
    {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isPresent())
        {
            studentRepository.delete(optionalStudent.get());
        }
        else
            throw new StudentNotFoundException("Couldn't find student with id: " + id);

    }

    public Map<String, Integer> countStudentByDepartment()
    {
        List<Student> allStudents = studentRepository.findAll();
        Map<String, Integer> departmentCounts = new HashMap<>();

        for(Student student : allStudents)
        {
            String department = student.getDepartment();
            int currrentCountForDepartment = departmentCounts.getOrDefault(department, 0);
            departmentCounts.put(department, currrentCountForDepartment+1);

        }
        return departmentCounts;
    }

    public List<Student> findStudentsByRoom(Long roomId)
    {
        return studentRepository.findByRoomId(roomId);
    }

    public int findFloorOfStudent(Long studentId)
    {
     Student currentStudent = studentRepository.findById(studentId).orElse(null);
     Room currentRoom = currentStudent.getRoom();
     return currentRoom.getFloor();
    }


}
