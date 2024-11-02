package com.yarasoftware.workshopngine.platform.comunication.interfaces.rest;

import com.yarasoftware.workshopngine.platform.comunication.domain.model.queries.GetAllNotificationsByUserIdQuery;
import com.yarasoftware.workshopngine.platform.comunication.domain.services.NotificationCommandService;
import com.yarasoftware.workshopngine.platform.comunication.domain.services.NotificationQueryService;
import com.yarasoftware.workshopngine.platform.comunication.interfaces.rest.resources.NotificationResource;
import com.yarasoftware.workshopngine.platform.comunication.interfaces.rest.resources.ReadNotificationResource;
import com.yarasoftware.workshopngine.platform.comunication.interfaces.rest.transform.NotificationResourceFromEntityAssembler;
import com.yarasoftware.workshopngine.platform.comunication.interfaces.rest.transform.ReadNotificationCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;


/**
 * Notifications Controller.
 * <p>
 * This class is the entry point for all the REST API calls related to notifications.
 * It is responsible for handling the requests and delegating the processing to the appropriate services.
 * It also transforms the data from the request to the appropriate commands and vice versa.
 * </p>
 */
@RestController
@RequestMapping(value="/api/v1/notifications", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Notifications", description = "Notification Management Endpoints")
public class NotificationsController {
    private final NotificationCommandService notificationCommandService;
    private final NotificationQueryService notificationQueryService;

    public NotificationsController(NotificationQueryService notificationQueryService, NotificationCommandService notificationCommandService){
        this.notificationQueryService = notificationQueryService;
        this.notificationCommandService = notificationCommandService;
    }

    /**
     * Gets all the notifications for a user.
     *
     * @param userid the id of the user
     * @return the list of notifications
     * @see NotificationResource
     */
    @GetMapping("/{userid}")
    public ResponseEntity<NotificationResource> getNotifications(@PathVariable Long userid){
        var getAllNotificationsByUserIdQuery = new GetAllNotificationsByUserIdQuery(userid);
        var notifications = notificationQueryService.handle(getAllNotificationsByUserIdQuery);
        var notificationResources = notifications.stream()
                .map(NotificationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(notificationResources.getFirst());
    }

    /**
     * Updates a notification to read.
     *
     * @param id the id of the notification
     * @return the updated notification
     * @see ReadNotificationResource
     */
    @PostMapping("/{id}/read")
    public ResponseEntity<ReadNotificationResource> updateNotificationtoRead(@PathVariable Long id){
        var readNotificationResource = new ReadNotificationResource(id);
        var readNotificationCommand = ReadNotificationCommandFromResourceAssembler.toCommandFromResources(readNotificationResource);
        notificationCommandService.handle(readNotificationCommand);
        return ResponseEntity.ok(new ReadNotificationResource(id));
    }

}
