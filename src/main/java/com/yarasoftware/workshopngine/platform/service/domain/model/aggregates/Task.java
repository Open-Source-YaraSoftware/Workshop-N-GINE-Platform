package com.yarasoftware.workshopngine.platform.service.domain.model.aggregates;

import com.yarasoftware.workshopngine.platform.service.domain.model.entities.Checkpoint;
import com.yarasoftware.workshopngine.platform.service.domain.model.valueobjects.InventoryRequestState;
import com.yarasoftware.workshopngine.platform.service.domain.model.valueobjects.TaskState;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.List;
//TODO: HACER JAVA DOCS
@Entity
@Getter
public class Task extends AbstractAggregateRoot<Task> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Checkpoint> tracking;

    @Enumerated(EnumType.STRING)
    private TaskState state;

    @Enumerated(EnumType.STRING)
    private InventoryRequestState inventoryRequestState;

    private Long interventionId;

    private Long assistantId;

    /**
     * Default constructor for JPA.
     */
    public Task() {}

    /**
     * Constructs a Task with the specified details.
     *
     * @param description           the description of the task
     * @param assistantId           the ID of the assistant assigned to the task
     * @param interventionId        the ID of the associated intervention
     * @param state                 the current state of the task
     * @param inventoryRequestState the current state of the inventory request
     */
    public Task(String description, TaskState state, InventoryRequestState inventoryRequestState, Long assistantId, Long interventionId) {
        this.description = description;
        this.state = state;
        this.inventoryRequestState = inventoryRequestState;
        this.assistantId = assistantId;
        this.interventionId = interventionId;
    }

    public void completeTask() {
        this.state = TaskState.DONE;
    }

    public void addCheckpoint(Checkpoint checkpoint) {
        this.tracking.add(checkpoint);
    }

    public void deleteCheckpoint(Long checkpointId) {
        this.tracking.removeIf(checkpoint -> checkpoint.getId().equals(checkpointId));
    }

    public void updateTask(String description, TaskState state, InventoryRequestState inventoryRequestState, Long assistantId) {
        this.description = description;
        this.state = state;
        this.inventoryRequestState = inventoryRequestState;
        this.assistantId = assistantId;
    }

    public List<Checkpoint> getAllCheckpoints() {
        return tracking;
    }

    public void startSuppliesRequest() {
        this.inventoryRequestState = InventoryRequestState.PENDING;
    }
}
