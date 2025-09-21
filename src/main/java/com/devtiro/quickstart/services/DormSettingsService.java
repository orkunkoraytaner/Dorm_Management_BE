package com.devtiro.quickstart.services;

import org.springframework.stereotype.Service;

@Service
public class DormSettingsService {

    private boolean maintenanceMode = false;

    public boolean isMaintenanceMode() {
        return maintenanceMode;
    }
    public void setMaintenanceMode(boolean maintenanceMode) {
        this.maintenanceMode = maintenanceMode;
    }
}
