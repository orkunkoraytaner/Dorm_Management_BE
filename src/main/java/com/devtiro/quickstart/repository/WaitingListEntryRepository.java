package com.devtiro.quickstart.repository;

import com.devtiro.quickstart.entity.WaitingListEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WaitingListEntryRepository extends JpaRepository<WaitingListEntry, Long> {

    List<WaitingListEntry> findByRoomIdOrderByAddedAtAsc(Long roomId);
}
