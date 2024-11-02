package com.yarasoftware.workshopngine.platform.comunication.domain.model.aggregates;


import com.yarasoftware.workshopngine.platform.comunication.domain.model.valueobjects.NotificationState;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Date;

/**
 * Represents a notification.
 * The notification is an aggregate root
 */
@Getter
@Entity
public class Notification extends AbstractAggregateRoot<Notification> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private final Date date;
    private String content;
    private Long userId;
    private NotificationState state;
    private final String endpoint;

    public Notification() {
        this.date = new Date(System.currentTimeMillis());
        this.content = Strings.EMPTY;
        this.userId = 0L;
        this.state = NotificationState.UNREAD;
        this.endpoint = Strings.EMPTY;

    }

    public Notification(String content, Long userId) {
        this();
        this.content = content;
        this.userId = userId;
    }

    /**
     * Marks the notification as read by setting its state to NotificationState.READ.
     */
    public void read() {
        this.state = NotificationState.READ;
    }

}
