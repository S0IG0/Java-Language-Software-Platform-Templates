package com.company.practics.practic_23.jmx.impl;

import com.company.practics.practic_23.jmx.SchedulerMbean;
import com.company.practics.practic_23.services.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@ManagedResource(description = "create backup SchedulerMbeanImpl")
public class SchedulerMbeanImpl implements SchedulerMbean {
    private final SchedulerService schedulerService;

    @Autowired
    public SchedulerMbeanImpl(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @Override
    @ManagedOperation(description = "start backup")
    public void backupData() throws IOException {
        schedulerService.cleanDirectoryAndBackupDatabase();
    }
}
