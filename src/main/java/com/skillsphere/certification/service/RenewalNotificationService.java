package com.skillsphere.certification.service;

import com.skillsphere.certification.dto.RenewalNotificationDTO;
import com.skillsphere.certification.entity.Certification;
import com.skillsphere.certification.entity.RenewalNotification;
import com.skillsphere.certification.repository.CertificationRepository;
import com.skillsphere.certification.repository.RenewalNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class RenewalNotificationService {
    
    @Autowired
    private CertificationRepository certificationRepository;
    
    @Autowired
    private RenewalNotificationRepository renewalNotificationRepository;
    
    public RenewalNotificationDTO createRenewalNotification(UUID certId, String notificationChannel) {
        Certification cert = certificationRepository.findById(certId)
            .orElseThrow(() -> new IllegalArgumentException("Certification not found: " + certId));
        
        if (renewalNotificationRepository.existsActiveNotification(certId)) {
            throw new IllegalArgumentException("Notification already exists for this certification");
        }
        
        long daysUntilExpiry = ChronoUnit.DAYS.between(LocalDateTime.now(), cert.getExpiryDate());
        
        RenewalNotification notification = new RenewalNotification();
        notification.setCertId(cert.getCertId());
        notification.setEmployeeId(cert.getEmployeeId());
        notification.setCertName(cert.getCertName());
        notification.setExpiryDate(cert.getExpiryDate());
        notification.setDaysUntilExpiry((int) daysUntilExpiry);
        notification.setStatus(RenewalNotification.NotificationStatus.PENDING);
        notification.setNotificationChannel(notificationChannel);
        notification.setRetryCount(0);
        
        RenewalNotification savedNotification = renewalNotificationRepository.save(notification);
        return RenewalNotificationDTO.fromEntity(savedNotification);
    }
    
    public void sendRenewalNotification(UUID notificationId) {
        RenewalNotification notification = renewalNotificationRepository.findById(notificationId)
            .orElseThrow(() -> new IllegalArgumentException("Notification not found: " + notificationId));
        
        try {
            notification.setStatus(RenewalNotification.NotificationStatus.SENT);
            notification.setSentAt(LocalDateTime.now());
            renewalNotificationRepository.save(notification);
        } catch (Exception e) {
            notification.setStatus(RenewalNotification.NotificationStatus.FAILED);
            notification.setRetryCount(notification.getRetryCount() + 1);
            renewalNotificationRepository.save(notification);
        }
    }
    
    @Scheduled(cron = "0 0 8 * * *")
    public void generateRenewalNotificationsForExpiringCerts() {
        List<Certification> expiringCerts = certificationRepository.findExpiringCertifications();
        
        expiringCerts.forEach(cert -> {
            try {
                if (!renewalNotificationRepository.existsActiveNotification(cert.getCertId())) {
                    createRenewalNotification(cert.getCertId(), "EMAIL");
                }
            } catch (Exception e) {
                System.err.println("Error creating notification for cert: " + cert.getCertId());
            }
        });
    }
    
    @Scheduled(cron = "0 */30 * * * *")
    public void sendPendingNotifications() {
        List<RenewalNotification> pendingNotifications = 
            renewalNotificationRepository.findByStatus(RenewalNotification.NotificationStatus.PENDING);
        
        pendingNotifications.forEach(notification -> {
            try {
                sendRenewalNotification(notification.getNotificationId());
            } catch (Exception e) {
                System.err.println("Error sending notification: " + notification.getNotificationId());
            }
        });
    }
    
    @Scheduled(cron = "0 0 2 * * *")
    public void retryFailedNotifications() {
        List<RenewalNotification> failedNotifications = 
            renewalNotificationRepository.findFailedNotificationsForRetry();
        
        failedNotifications.forEach(notification -> {
            try {
                notification.setStatus(RenewalNotification.NotificationStatus.RESENT);
                sendRenewalNotification(notification.getNotificationId());
            } catch (Exception e) {
                System.err.println("Retry failed for notification: " + notification.getNotificationId());
            }
        });
    }
    
    public List<RenewalNotificationDTO> getPendingNotifications() {
        return renewalNotificationRepository.findByStatus(RenewalNotification.NotificationStatus.PENDING)
            .stream()
            .map(RenewalNotificationDTO::fromEntity)
            .collect(Collectors.toList());
    }
    
    public List<RenewalNotificationDTO> getNotificationsByEmployee(UUID employeeId) {
        return renewalNotificationRepository.findByEmployeeId(employeeId)
            .stream()
            .map(RenewalNotificationDTO::fromEntity)
            .collect(Collectors.toList());
    }
    
    public RenewalNotificationDTO getNotificationById(UUID notificationId) {
        RenewalNotification notification = renewalNotificationRepository.findById(notificationId)
            .orElseThrow(() -> new IllegalArgumentException("Notification not found: " + notificationId));
        return RenewalNotificationDTO.fromEntity(notification);
    }
    
    public Long getPendingNotificationsCount() {
        return renewalNotificationRepository.countPendingNotifications();
    }
    
    public void updateNotificationStatus(UUID notificationId, String status) {
        RenewalNotification notification = renewalNotificationRepository.findById(notificationId)
            .orElseThrow(() -> new IllegalArgumentException("Notification not found: " + notificationId));
        
        notification.setStatus(RenewalNotification.NotificationStatus.valueOf(status));
        renewalNotificationRepository.save(notification);
    }
}