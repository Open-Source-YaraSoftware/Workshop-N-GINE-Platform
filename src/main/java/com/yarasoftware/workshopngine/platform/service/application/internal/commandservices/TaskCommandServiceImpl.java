package com.yarasoftware.workshopngine.platform.service.application.internal.commandservices;

import com.yarasoftware.workshopngine.platform.service.domain.model.aggregates.Task;
import com.yarasoftware.workshopngine.platform.service.domain.model.commands.*;
import com.yarasoftware.workshopngine.platform.service.domain.model.entities.Checkpoint;
import com.yarasoftware.workshopngine.platform.service.domain.services.TaskCommandService;
import com.yarasoftware.workshopngine.platform.service.infrastructure.persistence.jpa.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

//TODO: HACER JAVA DOCS
@Service
public class TaskCommandServiceImpl implements TaskCommandService {
    private final TaskRepository taskRepository;

    public TaskCommandServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Long handle(CompleteTaskCommand command) {
        taskRepository.findById(command.taskId())
                .map(task -> {
                    task.completeTask();
                    taskRepository.save(task);
                    return task.getId();
                }).orElseThrow(() -> new RuntimeException("Task not found"));
        return null;
    }

    @Override
    public Long handle(CreateCheckpointCommand command) {
        taskRepository.findById(command.taskId())
                .map(task -> {
                    var checkpoint = new Checkpoint(command.name(), task);
                    task.addCheckpoint(checkpoint);
                    taskRepository.save(task);
                    return checkpoint.getId();
                }).orElseThrow(() -> new RuntimeException("Task not found"));
        return null;
    }

    @Override
    public Long handle(CreateTaskCommand command) {
        if (taskRepository.existsByDescription(command.description()))
            throw new IllegalArgumentException("Task with the given description already exists");
        if (!taskRepository.existsByInterventionId(command.interventionId()))
            throw new IllegalArgumentException("Intervention does not exist");
        var newTask = new Task(command.description(), command.state(), command.inventoryRequestState(), command.assistantId(), command.interventionId());
        var savedTask = taskRepository.save(newTask);
        return savedTask.getId();
    }

    @Override
    public void handle(DeleteCheckpointCommand command) {
        taskRepository.findById(command.taskId())
                .map(task -> {
                    task.deleteCheckpoint(command.checkpointId());
                    taskRepository.save(task);
                    return task.getId();
                }).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Override
    public void handle(DeleteTaskCommand command) {
        if (!taskRepository.existsById(command.taskId()))
            throw new IllegalArgumentException("Task does not exist");
        taskRepository.deleteById(command.taskId());
    }

    @Override
    public Optional<Checkpoint> handle(UpdateCheckpointCommand command) {
        return taskRepository.findById(command.taskId())
                .flatMap(task -> {
                    Optional<Checkpoint> checkpointOptional = task.getTracking().stream()
                            .filter(checkpoint -> checkpoint.getId().equals(command.checkpointId()))
                            .findFirst();
                    checkpointOptional.ifPresent(checkpoint -> {
                        checkpoint.setName(command.name());
                        taskRepository.save(task);
                    });
                    return checkpointOptional;
                });
    }


    @Override
    public Optional<Task> handle(UpdateTaskCommand command) {
        taskRepository.findById(command.taskId())
                .map(task -> {
                    task.updateTask(command.description(), command.state(), command.inventoryRequestState(), command.assistantId());
                    taskRepository.save(task);
                    return task;
                }).orElseThrow(() -> new RuntimeException("Task not found"));
        return Optional.empty();
    }
}