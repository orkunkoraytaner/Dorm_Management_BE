package com.devtiro.quickstart.controller;

import com.devtiro.quickstart.services.ActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students/{studentId}/activities")
public class ActivityController {

    public final ActivityLogService activityLogService;

    @Autowired
    public ActivityController(ActivityLogService activityLogService) {
        this.activityLogService = activityLogService;
    }

    public ResponseEntity<List<String>> getActivities(@PathVariable("studentId") Long studentId)
    {
        List<String> activities = activityLogService.getActivities(studentId);
        return ResponseEntity.ok(activities);
    }
}
