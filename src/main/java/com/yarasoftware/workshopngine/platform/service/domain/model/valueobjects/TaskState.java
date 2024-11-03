package com.yarasoftware.workshopngine.platform.service.domain.model.valueobjects;

/**
 * Represents the state of a task.
 * <p>
 * A task can be in one of the following states:
 * <div><span>{@link #PENDING}</span>, <span>{@link #CANCELLED}</span>, <span>{@link #DONE}</span></div>
 * <p>
 */
public enum TaskState {
    PENDING,
    CANCELLED,
    DONE
}
