package com.sessionlogin.api.controller.health;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {
    @GetMapping("/api/v1/health")
    public ResponseEntity<Map<String, String>> checkHealth() {

        Map<String, String> response = new HashMap<>();
        response.put("status", "OK");
        response.put("application", "session-login-standard-rtx-api");
        response.put("version", "0.0.1");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
