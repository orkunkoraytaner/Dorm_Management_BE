package com.devtiro.quickstart.services;

import com.devtiro.quickstart.entity.Room;
import com.devtiro.quickstart.entity.Student;
import com.devtiro.quickstart.entity.WaitingListEntry;
import com.devtiro.quickstart.repository.RoomRepository;
import com.devtiro.quickstart.repository.StudentRepository;
import com.devtiro.quickstart.repository.WaitingListEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WaitingListService {

    private final WaitingListEntryRepository waitingListEntryRepository;
    private final RoomRepository roomRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public WaitingListService(WaitingListEntryRepository waitingListEntryRepository, RoomRepository roomRepository, StudentRepository studentRepository) {
        this.waitingListEntryRepository = waitingListEntryRepository;
        this.roomRepository = roomRepository;
        this.studentRepository = studentRepository;
    }

    public Optional<WaitingListEntry> addStudentToWaitingList(Long roomId, Long studentId) { //orkun_check
        Optional<Room> room = roomRepository.findById(roomId);
        Optional<Student> student = studentRepository.findById(studentId);
        if(room.isPresent() && student.isPresent()) {
            WaitingListEntry waitingListEntry = new WaitingListEntry(student.get(),room.get());
            return Optional.of(waitingListEntryRepository.save(waitingListEntry));
        }
        return Optional.empty();
    }

    public List<WaitingListEntry> getWaitingListForRoom(Long roomId)
    {
        return waitingListEntryRepository.findByRoomIdOrderByAddedAtAsc(roomId);
    }

    public Optional<Student> getNextStudentInLine(Long roomId)
    {
        List<WaitingListEntry> waitingListEntries = getWaitingListForRoom(roomId);
        if(!waitingListEntries.isEmpty()) {
            WaitingListEntry nextEntry = waitingListEntries.get(0);
            Student nextStudent = nextEntry.getStudent();
            waitingListEntryRepository.delete(nextEntry);
            return Optional.of(nextStudent);
        }
        return Optional.empty();
    }

}
