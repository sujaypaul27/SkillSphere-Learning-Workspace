package com.skillsphere.certification.repository;

import com.skillsphere.certification.entity.RenewalNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface RenewalNotificationRepository extends JpaRepository<RenewalNotification, UUID> {
    
    List<RenewalNotification> findByStatus(RenewalNotification.NotificationStatus status);
    
    List<RenewalNotification> findByEmployeeId(UUID employeeId);
    
    @Query("SELECT rn FROM RenewalNotification rn WHERE rn.employeeId = :employeeId AND rn.status = 'PENDING'")
    List<RenewalNotification> findPendingByEmployeeId(@Param("employeeId") UUID employeeId);
    
    @Query("SELECT rn FROM RenewalNotification rn WHERE rn.status IN ('PENDING', 'FAILED') ORDER BY rn.createdAt ASC")
    List<RenewalNotification> findUnsentNotifications();
    
    @Query("SELECT CASE WHEN COUNT(rn) > 0 THEN true ELSE false END FROM RenewalNotification rn WHERE rn.certId = :certId AND rn.status IN ('PENDING', 'SENT')")
    Boolean existsActiveNotification(@Param("certId") UUID certId);
    
    @Query("SELECT rn FROM RenewalNotification rn WHERE rn.status = 'FAILED' AND rn.retryCount < 3")
    List<RenewalNotification> findFailedNotificationsForRetry();
    
    @Query("SELECT COUNT(rn) FROM RenewalNotification rn WHERE rn.status = 'PENDING'")
    Long countPendingNotifications();
    
    @Query("SELECT rn FROM RenewalNotification rn WHERE rn.sentAt >= :timestamp AND rn.status = 'SENT'")
    List<RenewalNotification> findRecentlySent(@Param("timestamp") LocalDateTime timestamp);
}
