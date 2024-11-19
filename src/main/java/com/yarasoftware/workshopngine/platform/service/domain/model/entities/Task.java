package com.yarasoftware.workshopngine.platform.service.domain.model.entities;

import com.yarasoftware.workshopngine.platform.service.domain.model.aggregates.Intervention;
import com.yarasoftware.workshopngine.platform.service.domain.model.commands.CreateCheckpointCommand;
import com.yarasoftware.workshopngine.platform.service.domain.model.commands.CreateTaskCommand;
import com.yarasoftware.workshopngine.platform.service.domain.model.commands.UpdateCheckpointCommand;
import com.yarasoftware.workshopngine.platform.service.domain.model.commands.UpdateTaskCommand;
import com.yarasoftware.workshopngine.platform.service.domain.model.valueobjects.TaskState;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    public List<Checkpoint> checkpoints;

    public Task() {
        this.description = Strings.EMPTY;
        this.state = TaskState.PENDING;
        this.checkpoints = new ArrayList<>();
    }

    public Task(CreateTaskCommand command, Intervention intervention) {
        this.assistantId = command.assistantId();
        this.description = command.description();
        this.state = TaskState.PENDING;
        this.intervention = intervention;
        this.checkpoints = new ArrayList<>();
    }

    public void update(UpdateTaskCommand command){
        this.assistantId = command.assistantId();
        this.description = command.description();
    }

    public boolean isCompleted(){
        return state == TaskState.DONE;
    }

    public boolean isInProgress(){
        return state == TaskState.IN_PROGRESS;
    }

    public void start(){
        if (state != TaskState.PENDING)
            throw new IllegalStateException("Task is not pending");
        state = TaskState.IN_PROGRESS;
    }

    public void complete(){
        if (state != TaskState.IN_PROGRESS)
            throw new IllegalStateException("Task is not in progress");
        state = TaskState.DONE;
    }

    public Optional<Checkpoint> getCheckpointById(Long checkpointId){
        return checkpoints.stream().filter(checkpoint -> checkpoint.getId().equals(checkpointId)).findFirst();
    }

    public Checkpoint addCheckpoint(CreateCheckpointCommand command){
        Checkpoint checkpoint = new Checkpoint(command, this);
        checkpoints.add(checkpoint);
        return checkpoint;
    }

    public Checkpoint updateCheckpoint(Long checkpointId, UpdateCheckpointCommand command){
        Checkpoint checkpoint = getCheckpointById(checkpointId).orElseThrow();
        checkpoint.update(command);
        return checkpoint;
    }

    public boolean removeCheckpoint(Long checkpointId){
        return checkpoints.removeIf(checkpoint -> checkpoint.getId().equals(checkpointId));
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
