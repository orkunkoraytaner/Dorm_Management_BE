package com.devtiro.quickstart.services;

import com.devtiro.quickstart.dto.StudentDto;
import com.devtiro.quickstart.entity.Role;
import com.devtiro.quickstart.entity.Room;
import com.devtiro.quickstart.entity.Student;
import com.devtiro.quickstart.entity.StudentMajor;
import com.devtiro.quickstart.exceptions.NoEmptySpaceException;
import com.devtiro.quickstart.exceptions.RoomNotFoundException;
import com.devtiro.quickstart.exceptions.StudentNotFoundException;
import com.devtiro.quickstart.repository.RoomRepository;
import com.devtiro.quickstart.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final RoomRepository roomRepository;
    private final RoomService roomService;
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final WaitingListService waitingListService;
    private final DormSettingsService dormSettingsService;

    @Autowired
    public StudentService(StudentRepository studentRepository, RoomRepository roomRepository, RoomService roomService, WaitingListService waitingListService, DormSettingsService dormSettingsService) {
        this.studentRepository = studentRepository;
        this.roomRepository = roomRepository;
        this.roomService = roomService;
        this.waitingListService = waitingListService;
        this.dormSettingsService = dormSettingsService;
    }

    private StudentDto toDto(Student student) {
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());
        if (student.getDepartment() != null) {
            dto.setDepartment(student.getDepartment().name());
        }
        dto.setDeskNumber(student.getDeskNumber());
        if (student.getRoom() != null) {
            dto.setRoomId(student.getRoom().getId());
        }
        if(student.getRole() != null)
        {
            dto.setRole(student.getRole().name());
        }
        return dto;
    }

    private Student toEntity(StudentDto dto) {
        Student student = new Student();
        student.setId(dto.getId());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        if (dto.getDepartment() != null) {
            student.setDepartment(StudentMajor.valueOf(dto.getDepartment()));
        }
        student.setDeskNumber(dto.getDeskNumber());
        if (dto.getRoomId() != null) {
            Room room = roomRepository.findById(dto.getRoomId())
                    .orElseThrow(() -> new RoomNotFoundException("Couldn't find room with id: " + dto.getRoomId()));
            student.setRoom(room);
        }
        if(dto.getRole() != null)
        {
            student.setRole(Role.valueOf(dto.getRole()));
        }
        return student;
    }

    public StudentDto saveStudent(StudentDto studentDto)
    {
        Student studentEntity = toEntity(studentDto);
        try {
            Room room = studentEntity.getRoom();
            roomService.reduceRoomSpaceByOne(room.getId());
            Student savedStudent = studentRepository.save(studentEntity);
            return toDto(savedStudent);
        }catch (NoEmptySpaceException e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    public List<StudentDto> getAllStudents()
    {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> newStudentDtos = new ArrayList<>();
        for (Student student : students) {
            StudentDto dto = toDto(student);
            newStudentDtos.add(dto);
        }
        return newStudentDtos;

    }

    public StudentDto getStudentById(Long id) {
        return studentRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new StudentNotFoundException("Couldn't find student with id: " + id));

    }

    public StudentDto updateStudent(Long id, StudentDto studentDto)
    {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            Student studentEntity = studentOptional.get();
            studentEntity.setFirstName(studentDto.getFirstName());
            studentEntity.setLastName(studentDto.getLastName());
            studentEntity.setEmail(studentDto.getEmail());
            studentEntity.setDeskNumber(studentDto.getDeskNumber());
            studentEntity.setDepartment(StudentMajor.valueOf(studentDto.getDepartment()));
            if(studentDto.getRole() != null)
            {
                studentEntity.setRole(Role.valueOf(studentDto.getRole()));
            }

            Student savedStudent = studentRepository.save(studentEntity);
            return toDto(savedStudent);
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

    public Map<StudentMajor, Integer> countStudentByDepartment()
    {
        List<Student> allStudents = studentRepository.findAll();
        Map<StudentMajor, Integer> departmentCounts = new HashMap<>();

        for(Student student : allStudents)
        {
            StudentMajor department = student.getDepartment();
            int currrentCountForDepartment = departmentCounts.getOrDefault(department, 0);
            departmentCounts.put(department, currrentCountForDepartment+1);

        }
        return departmentCounts;
    }

    public List<StudentDto> findStudentsByRoom(Long roomId)
    {
        List<Student> studentEntity = studentRepository.findByRoomId(roomId);
        List<StudentDto> studentDtos = new ArrayList<>();
        for(Student student : studentEntity)
        {
            StudentDto dto = toDto(student);
            studentDtos.add(dto);
        }
        return studentDtos;
    }

    public int findFloorOfStudent(Long studentId)
    {
     Student currentStudent = studentRepository.findById(studentId).orElse(null);
     Room currentRoom = currentStudent.getRoom();
     return currentRoom.getFloor();
    }

    public Student assignStudentToNewRoom(Long studentId, Long roomId)
    {
        if(!dormSettingsService.isMaintenanceMode())
        {
            Student currentStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Couldn't find student with id: " + studentId));
            Room currentRoom = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Couldn't find room with id: " + roomId));

            try
            {
                roomService.reduceRoomSpaceByOne(currentRoom.getId());
                currentStudent.setRoom(currentRoom);
                return studentRepository.save(currentStudent);

            }
            catch (NoEmptySpaceException e)
            {
                waitingListService.addStudentToWaitingList(roomId, studentId);
                return currentStudent;
            }
        }
        else
            throw new RuntimeException("System is in maintenance mode");

    }


}
