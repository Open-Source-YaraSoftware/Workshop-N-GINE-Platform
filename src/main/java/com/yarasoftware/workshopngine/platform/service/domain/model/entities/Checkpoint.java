package com.yarasoftware.workshopngine.platform.service.domain.model.entities;

import com.yarasoftware.workshopngine.platform.service.domain.model.aggregates.Task;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity representing a Checkpoint within a Task.
 *
 * <p>
 * The Checkpoint entity models a specific point or stage within a Task,
 * including its name and the associated Task.
 * </p>
 *
 * @since v1.0.0
 */
@Entity
@Getter
@Setter
public class Checkpoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    /**
     * Default constructor for JPA.
     */
    public Checkpoint() {
    }

    /**
     * Constructs a Checkpoint with the specified details.
     *
     * @param name the name of the checkpoint
     * @param task the associated task for this checkpoint
     */
    public Checkpoint(String name, Task task) {
        this.name = name;
        this.task = task;
    }
}