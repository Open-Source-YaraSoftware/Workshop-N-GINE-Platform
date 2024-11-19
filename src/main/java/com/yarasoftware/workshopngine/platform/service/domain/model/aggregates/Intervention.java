package com.yarasoftware.workshopngine.platform.service.domain.model.aggregates;

import com.yarasoftware.workshopngine.platform.service.domain.model.commands.CreateInterventionCommand;
import com.yarasoftware.workshopngine.platform.service.domain.model.commands.UpdateInterventionCommand;
import com.yarasoftware.workshopngine.platform.service.domain.model.valueobjects.InterventionStatuses;
import com.yarasoftware.workshopngine.platform.service.domain.model.valueobjects.InterventionTypes;
import com.yarasoftware.workshopngine.platform.service.domain.model.valueobjects.TaskManager;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Intervention extends AbstractAggregateRoot<Intervention> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long workshopId;

    @NotNull
    private Long vehicleId;

    @NotNull
    private Long clientId;

    @NotNull
    private Long mechanicLeaderId;

    private String description;

    @Enumerated(EnumType.STRING)
    private InterventionTypes type;

    @Enumerated(EnumType.STRING)
    private InterventionStatuses status;

    @NotNull
    private LocalDateTime scheduledAt;

    private LocalDateTime startedAt;

    private LocalDateTime FinishedAt;

    @Embedded
    private TaskManager taskManager;

    public Intervention() {
        this.description = Strings.EMPTY;
        this.type = InterventionTypes.REPARATION;
        this.status = InterventionStatuses.PENDING;
        this.taskManager = new TaskManager();
    }

    public Intervention(CreateInterventionCommand command) {
        super();
        this.workshopId = command.workshopId();
        this.vehicleId = command.vehicleId();
        this.clientId = command.clientId();
        this.mechanicLeaderId = command.mechanicLeaderId();
        this.description = command.description();
        this.scheduledAt = command.scheduledAt();
        this.type = InterventionTypes.REPARATION;
        this.status = InterventionStatuses.PENDING;
        this.taskManager = new TaskManager();
    }

    public void Update(UpdateInterventionCommand command) {
        this.vehicleId = command.vehicleId();
        this.mechanicLeaderId = command.mechanicLeaderId();
        this.description = command.description();
        this.type = command.type();
        this.scheduledAt = command.scheduledAt();
    }


    public String TypeToString() {
        return switch (type) {
            case REPARATION -> "Reparation";
            case MAINTENANCE -> "Maintenance";
            default -> "Unknown";
        };
    }

    public String StatusToString() {
        return switch (status) {
            case PENDING -> "Pending";
            case IN_PROGRESS -> "In Progress";
            case COMPLETED -> "Completed";
            case CANCELLED -> "Cancelled";
            default -> "Unknown";
        };
    }
}
