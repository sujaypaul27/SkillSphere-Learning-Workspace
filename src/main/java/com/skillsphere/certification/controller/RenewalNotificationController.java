package com.skillsphere.certification.controller;

import com.skillsphere.certification.dto.RenewalNotificationDTO;
import com.skillsphere.certification.service.RenewalNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/certifications/renewals")
@CrossOrigin(origins = "*")
public class RenewalNotificationController {
    
    @Autowired
    private RenewalNotificationService renewalNotificationService;
    
    @PostMapping("/{certId}")
    public ResponseEntity<RenewalNotificationDTO> createRenewalNotification(
            @PathVariable UUID certId,
            @RequestParam(defaultValue = "EMAIL") String channel) {
        try {
            RenewalNotificationDTO notification = renewalNotificationService.createRenewalNotification(certId, channel);
            return ResponseEntity.status(HttpStatus.CREATED).body(notification);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @PostMapping("/{notificationId}/send")
    public ResponseEntity<Void> sendNotification(@PathVariable UUID notificationId) {
        try {
            renewalNotificationService.sendRenewalNotification(notificationId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @GetMapping("/{notificationId}")
    public ResponseEntity<RenewalNotificationDTO> getNotification(@PathVariable UUID notificationId) {
        try {
            RenewalNotificationDTO notification = renewalNotificationService.getNotificationById(notificationId);
            return ResponseEntity.ok(notification);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @GetMapping("/pending")
    public ResponseEntity<List<RenewalNotificationDTO>> getPendingNotifications() {
        try {
            List<RenewalNotificationDTO> notifications = renewalNotificationService.getPendingNotifications();
            return ResponseEntity.ok(notifications);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<RenewalNotificationDTO>> getNotificationsByEmployee(
            @PathVariable UUID employeeId) {
        try {
            List<RenewalNotificationDTO> notifications = renewalNotificationService.getNotificationsByEmployee(employeeId);
            return ResponseEntity.ok(notifications);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/pending/count")
    public ResponseEntity<Long> getPendingCount() {
        try {
            Long count = renewalNotificationService.getPendingNotificationsCount();
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PutMapping("/{notificationId}/status")
    public ResponseEntity<Void> updateNotificationStatus(
            @PathVariable UUID notificationId,
            @RequestParam String status) {
        try {
            renewalNotificationService.updateNotificationStatus(notificationId, status);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @PostMapping("/trigger/generate")
    public ResponseEntity<String> triggerGenerateNotifications() {
        try {
            renewalNotificationService.generateRenewalNotificationsForExpiringCerts();
            return ResponseEntity.ok("Renewal notifications generated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
    
    @PostMapping("/trigger/send")
    public ResponseEntity<String> triggerSendNotifications() {
        try {
            renewalNotificationService.sendPendingNotifications();
            return ResponseEntity.ok("Pending notifications sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
    
    @PostMapping("/trigger/retry")
    public ResponseEntity<String> triggerRetryNotifications() {
        try {
            renewalNotificationService.retryFailedNotifications();
            return ResponseEntity.ok("Failed notifications retry triggered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}