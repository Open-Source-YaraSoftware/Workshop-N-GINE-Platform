package com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources;

import java.time.LocalDateTime;

public record InterventionResource(Long id, Long vehicleId, Long clientId, Long mechanicLeaderId, String Description, String type, String status, LocalDateTime scheduledDate, LocalDateTime startedAt, LocalDateTime finishedAt) {
}
