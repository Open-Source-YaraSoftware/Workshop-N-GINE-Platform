package com.yarasoftware.workshopngine.platform.service.domain.model.entities;

import com.yarasoftware.workshopngine.platform.service.domain.model.commands.CreateCheckpointCommand;
import com.yarasoftware.workshopngine.platform.service.domain.model.commands.UpdateCheckpointCommand;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

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

    public Checkpoint() {
        this.name = Strings.EMPTY;
    }

    public Checkpoint(CreateCheckpointCommand command, Task task) {
        this.name = command.name();
        this.task = task;
    }

    public void update(UpdateCheckpointCommand command){
        this.name = command.name();
    }
}