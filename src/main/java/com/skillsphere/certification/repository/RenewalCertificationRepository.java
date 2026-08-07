package com.skillsphere.certification.repository;

import com.skillsphere.certification.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RenewalCertificationRepository extends JpaRepository<Certification, UUID> {
    
    @Query(value = "SELECT * FROM renewal_certifications WHERE expiry_date BETWEEN NOW() AND NOW() + INTERVAL 30 DAY AND status = 'VALID'", 
           nativeQuery = true)
    List<Certification> findExpiringCertifications();
    
    List<Certification> findByEmployeeId(UUID employeeId);
    
    @Query(value = "SELECT * FROM renewal_certifications WHERE expiry_date < NOW() AND status != 'RENEWED'", 
           nativeQuery = true)
    List<Certification> findExpiredCertifications();
    
    @Query(value = "SELECT * FROM renewal_certifications WHERE expiry_date BETWEEN NOW() AND NOW() + INTERVAL :days DAY AND status = 'VALID'", 
           nativeQuery = true)
    List<Certification> findCertificationsExpiringWithinDays(@Param("days") Integer days);
    
    @Query(value = "SELECT COUNT(*) FROM renewal_certifications WHERE status = 'VALID'", 
           nativeQuery = true)
    Long countActiveCertifications();
    
    Certification findByCertNameAndEmployeeId(String certName, UUID employeeId);
}
