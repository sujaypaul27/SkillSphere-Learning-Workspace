package com.infosis.nexus.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpiryTrackingResponse {

    private Long id;

    private Long employeeId;

    private Long certificationId;

    private LocalDate expiryDate;

    private LocalDate lastCheckedDate;

    private String expiryStatus;
}