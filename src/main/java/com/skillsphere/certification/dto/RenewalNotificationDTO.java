package com.skillsphere.certification.dto;

import com.skillsphere.certification.entity.RenewalNotification;
import java.time.LocalDateTime;
import java.util.UUID;

public class RenewalNotificationDTO {
    
    private UUID notificationId;
    private UUID certId;
    private UUID employeeId;
    private String certName;
    private LocalDateTime expiryDate;
    private Integer daysUntilExpiry;
    private String status;
    private LocalDateTime sentAt;
    private String notificationChannel;
    private Integer retryCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public RenewalNotificationDTO() {}
    
    public RenewalNotificationDTO(UUID notificationId, UUID certId, UUID employeeId, String certName,
            LocalDateTime expiryDate, Integer daysUntilExpiry, String status, LocalDateTime sentAt,
            String notificationChannel, Integer retryCount, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.notificationId = notificationId;
        this.certId = certId;
        this.employeeId = employeeId;
        this.certName = certName;
        this.expiryDate = expiryDate;
        this.daysUntilExpiry = daysUntilExpiry;
        this.status = status;
        this.sentAt = sentAt;
        this.notificationChannel = notificationChannel;
        this.retryCount = retryCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    public static RenewalNotificationDTO fromEntity(RenewalNotification notification) {
        RenewalNotificationDTO dto = new RenewalNotificationDTO();
        dto.setNotificationId(notification.getNotificationId());
        dto.setCertId(notification.getCertId());
        dto.setEmployeeId(notification.getEmployeeId());
        dto.setCertName(notification.getCertName());
        dto.setExpiryDate(notification.getExpiryDate());
        dto.setDaysUntilExpiry(notification.getDaysUntilExpiry());
        dto.setStatus(notification.getStatus().toString());
        dto.setSentAt(notification.getSentAt());
        dto.setNotificationChannel(notification.getNotificationChannel());
        dto.setRetryCount(notification.getRetryCount());
        dto.setCreatedAt(notification.getCreatedAt());
        dto.setUpdatedAt(notification.getUpdatedAt());
        return dto;
    }
    
    public UUID getNotificationId() { return notificationId; }
    public void setNotificationId(UUID notificationId) { this.notificationId = notificationId; }
    
    public UUID getCertId() { return certId; }
    public void setCertId(UUID certId) { this.certId = certId; }
    
    public UUID getEmployeeId() { return employeeId; }
    public void setEmployeeId(UUID employeeId) { this.employeeId = employeeId; }
    
    public String getCertName() { return certName; }
    public void setCertName(String certName) { this.certName = certName; }
    
    public LocalDateTime getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDateTime expiryDate) { this.expiryDate = expiryDate; }
    
    public Integer getDaysUntilExpiry() { return daysUntilExpiry; }
    public void setDaysUntilExpiry(Integer daysUntilExpiry) { this.daysUntilExpiry = daysUntilExpiry; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDateTime getSentAt() { return sentAt; }
    public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }
    
    public String getNotificationChannel() { return notificationChannel; }
    public void setNotificationChannel(String notificationChannel) { this.notificationChannel = notificationChannel; }
    
    public Integer getRetryCount() { return retryCount; }
    public void setRetryCount(Integer retryCount) { this.retryCount = retryCount; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}