package com.employeemanagement.scheduler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeScheduler {

      static Integer count =0;

    @Scheduled(fixedRate = 2000)
    public void deactivateInactiveEmployees() {
        log.info("=== SCHEDULER: Deactivating inactive employees ===");
        System.out.println("Scheduler is called :" + count++);
    }

}

