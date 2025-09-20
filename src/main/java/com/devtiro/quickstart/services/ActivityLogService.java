package com.devtiro.quickstart.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivityLogService {

    private final Map<Long, List<String>> studentActivities = new HashMap<>();

    public void addActivity(Long studentId, String activity) {

        if(!studentActivities.containsKey(studentId)) {
            studentActivities.put(studentId, new ArrayList<>());
        }
        studentActivities.get(studentId).add(activity);
    }

    public List<String> getActivities (Long studentId) {
        return studentActivities.getOrDefault(studentId, new ArrayList<>());
    }



}
