package com.yarasoftware.workshopngine.platform.service.domain.model.aggregates;

import com.yarasoftware.workshopngine.platform.service.domain.model.commands.*;
import com.yarasoftware.workshopngine.platform.service.domain.model.entities.Checkpoint;
import com.yarasoftware.workshopngine.platform.service.domain.model.entities.Task;
import com.yarasoftware.workshopngine.platform.service.domain.model.valueobjects.InterventionStatuses;
import com.yarasoftware.workshopngine.platform.service.domain.model.valueobjects.InterventionTypes;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    private LocalDateTime finishedAt;

    @OneToMany(mappedBy = "intervention", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    public List<Task> tasks;

    public Intervention() {
        this.description = Strings.EMPTY;
        this.type = InterventionTypes.REPARATION;
        this.status = InterventionStatuses.PENDING;
        this.tasks = new ArrayList<>();
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
        this.tasks = new ArrayList<>();
    }

    public void Update(UpdateInterventionCommand command) {
        this.vehicleId = command.vehicleId();
        this.mechanicLeaderId = command.mechanicLeaderId();
        this.description = command.description();
        this.type = command.type();
        this.scheduledAt = command.scheduledAt();
    }

    public Optional<Task> getTaskById(Long taskId) {
        return tasks
                .stream()
                .filter(task -> task.getId().equals(taskId))
                .findFirst();
    }

    public List<Task> getAllTasksByAssistantId(Long assistantId) {
        return tasks
                .stream()
                .filter(task -> task.getAssistantId().equals(assistantId))
                .toList();
    }
    
    public Task addTask(CreateTaskCommand command) {
        var task = new Task(command, this);
        tasks.add(task);
        return task;
    }

    public Task updateTask(long taskId, UpdateTaskCommand command) {
        var task = getTaskById(taskId);
        task.ifPresent(t -> t.update(command));
        return task.orElse(null);
    }

    public boolean removeTask(Long taskId) {
        return tasks.removeIf(task -> task.getId().equals(taskId));
    }

    public boolean isAllTasksCompleted() {
        return tasks.stream().allMatch(Task::isCompleted);
    }

    public void startTask(Long taskId) {
        var task = getTaskById(taskId);
        if (task.isEmpty())
            throw new IllegalArgumentException("Task not found");
        task.get().start();
    }

    public void completeTask(Long taskId) {
        var task = getTaskById(taskId);
        if (task.isEmpty())
            throw new IllegalArgumentException("Task not found");
        task.get().complete();
    }

    public boolean existsTaskByAssistantId(Long assistantId) {
        return tasks.stream().anyMatch(task -> task.getAssistantId().equals(assistantId));
    }

    /**
     * Methods to manage the Checkpoints
     */
    public Checkpoint addCheckpoint(Long taskId, CreateCheckpointCommand command) {
        var task = getTaskById(taskId);
        if (task.isEmpty())
            throw new IllegalArgumentException("Task not found");
        return task.get().addCheckpoint(command);
    }

    public Checkpoint updateCheckpoint(Long taskId, Long checkpointId, UpdateCheckpointCommand command) {
        var task = getTaskById(taskId);
        if (task.isEmpty())
            throw new IllegalArgumentException("Task not found");
        return task.get().updateCheckpoint(checkpointId, command);
    }

    public boolean removeCheckpoint(Long taskId, Long checkpointId) {
        var task = getTaskById(taskId);
        if (task.isEmpty())
            throw new IllegalArgumentException("Task not found");
        return task.get().removeCheckpoint(checkpointId);
    }

    public List<Checkpoint> getAllCheckpointsByTaskId(Long taskId) {
        var task = getTaskById(taskId);
        if (task.isEmpty())
            throw new IllegalArgumentException("Task not found");
        return task.get().getCheckpoints();
    }

    /**
     * Methods to change the status of the Intervention
     */
    public void start(){
        if (status != InterventionStatuses.PENDING)
            throw new IllegalArgumentException("Intervention is not pending");
        status = InterventionStatuses.IN_PROGRESS;
        startedAt = LocalDateTime.now();
    }

    public void complete(){
        if (!this.IsInProcess())
            throw new IllegalArgumentException("Intervention is not in progress");
        if (!isAllTasksCompleted())
            throw new IllegalArgumentException("Not all tasks are completed");
        status = InterventionStatuses.COMPLETED;
        finishedAt = LocalDateTime.now();
    }

    public void cancel(){
        if (status != InterventionStatuses.PENDING)
            throw new IllegalArgumentException("Intervention is not pending");
        if (status == InterventionStatuses.COMPLETED)
            throw new IllegalArgumentException("Intervention is already completed");
        status = InterventionStatuses.CANCELLED;
    }

    /**
     * Methods to check the status of the Intervention
     * @return boolean
     */

    public boolean IsInProcess() {
        return status == InterventionStatuses.IN_PROGRESS;
    }

    /**
     * Methods to convert the Enum types to String
     * @return String
     */

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
