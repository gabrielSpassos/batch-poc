package com.gabrielspassos.poc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReportService {

    public void sendReport(String id) {
        log.info("Sending report {}", id);
    }
}
