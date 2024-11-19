package com.yarasoftware.workshopngine.platform.service.domain.model.entities;

import com.yarasoftware.workshopngine.platform.service.domain.model.aggregates.Intervention;
import com.yarasoftware.workshopngine.platform.service.domain.model.commands.CreateTaskCommand;
import com.yarasoftware.workshopngine.platform.service.domain.model.commands.UpdateTaskCommand;
import com.yarasoftware.workshopngine.platform.service.domain.model.valueobjects.TaskState;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

@Entity
@Getter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long assistantId;

    @ManyToOne
    @JoinColumn(name = "intervention_id", nullable = false)
    @NotNull
    private Intervention intervention;

    @Enumerated(EnumType.STRING)
    private TaskState state;

    private String description;

    public Task() {
        this.description = Strings.EMPTY;
        this.state = TaskState.PENDING;
    }

    public Task(CreateTaskCommand command, Intervention intervention) {
        this.assistantId = command.assistantId();
        this.description = command.description();
        this.state = TaskState.PENDING;
        this.intervention = intervention;
    }

    public void update(UpdateTaskCommand command){
        this.assistantId = command.assistantId();
        this.description = command.description();
    }

    public boolean isCompleted(){
        return state == TaskState.DONE;
    }


    /**
     * Methods to convert the Enum types to String
     * @return String
     */

    public String StatusToString(){
        return switch (state) {
            case PENDING -> "Pending";
            case IN_PROGRESS -> "In Progress";
            case DONE -> "Done";
        };
    }
}
