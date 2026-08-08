package com.infosis.nexus.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpiryTrackingUpdateRequest {

    @NotNull(message = "Expiry Date is required")
    private LocalDate expiryDate;
}