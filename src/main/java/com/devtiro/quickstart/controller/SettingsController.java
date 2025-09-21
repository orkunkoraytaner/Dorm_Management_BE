package com.devtiro.quickstart.controller;

import com.devtiro.quickstart.services.DormSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/settings")
public class SettingsController {

    private final DormSettingsService dormSettingsService;

    @Autowired
    public SettingsController(DormSettingsService dormSettingsService) {
        this.dormSettingsService = dormSettingsService;
    }

    @GetMapping("/maintenance")
    public boolean isMaintenanceMode()
    {
        return dormSettingsService.isMaintenanceMode();
    }

    @PostMapping("/maintenance")
    public void setMaintenanceMode(@RequestBody boolean maintenanceMode)
    {
         dormSettingsService.setMaintenanceMode(maintenanceMode);
    }


}
