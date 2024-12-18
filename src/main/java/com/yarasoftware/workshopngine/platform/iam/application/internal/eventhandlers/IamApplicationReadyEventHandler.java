package com.yarasoftware.workshopngine.platform.iam.application.internal.eventhandlers;
import com.yarasoftware.workshopngine.platform.iam.domain.model.commands.SeedRolesCommand;
import com.yarasoftware.workshopngine.platform.iam.domain.services.RoleCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;

/**
 * Event handler for the ApplicationReadyEvent.
 * This class is responsible for seeding roles when the application is ready.
 * @see ApplicationReadyEvent
 */
@Service
public class IamApplicationReadyEventHandler {
    private final RoleCommandService roleCommandService;
    private static final Logger LOGGER = LoggerFactory.getLogger(IamApplicationReadyEventHandler.class);

    public IamApplicationReadyEventHandler(RoleCommandService roleCommandService) {
        this.roleCommandService = roleCommandService;
    }

    @EventListener
    public void on(ApplicationReadyEvent event) {
        loadRolesData(event);
    }

    /**
     * Load roles data
     * This method is used to seed roles to the database.
     * @param event The ApplicationReadyEvent
     */
    private void loadRolesData(ApplicationReadyEvent event) {
        var applicationName = event.getApplicationContext().getId();
        LOGGER.info("Starting to verify if roles seeding is needed for {} at {}", applicationName, getCurrentTimestamp());
        var seedRolesCommand = new SeedRolesCommand();
        roleCommandService.handle(seedRolesCommand);
        LOGGER.info("Roles seeding verification finished for {} at {}", applicationName, getCurrentTimestamp());
    }

    private Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}