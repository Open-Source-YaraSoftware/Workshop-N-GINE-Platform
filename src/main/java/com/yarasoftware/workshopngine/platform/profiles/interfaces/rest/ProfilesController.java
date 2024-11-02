package com.yarasoftware.workshopngine.platform.profiles.interfaces.rest;
import com.yarasoftware.workshopngine.platform.profiles.domain.model.queries.GetProfileByDniQuery;
import com.yarasoftware.workshopngine.platform.profiles.domain.model.queries.GetProfileByIdQuery;
import com.yarasoftware.workshopngine.platform.profiles.domain.model.queries.GetProfileByUserIdQuery;
import com.yarasoftware.workshopngine.platform.profiles.domain.services.ProfileCommandService;
import com.yarasoftware.workshopngine.platform.profiles.domain.services.ProfileQueryService;
import com.yarasoftware.workshopngine.platform.profiles.interfaces.rest.resources.ProfileResource;
import com.yarasoftware.workshopngine.platform.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * REST controller for managing profiles.
 * Provides endpoints for creating, updating, and retrieving profiles.
 * @since v1.0.0
 */
@RestController
@RequestMapping(value = "/api/v1/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profiles", description = "Profile Management Endpoints")
public class ProfilesController {

    private final ProfileQueryService profileQueryService;
    private final ProfileCommandService profileCommandService;

    /**
     * Constructs a ProfilesController with the specified query and command services.
     *
     * @param profileQueryService the service used to handle profile queries
     * @param profileCommandService the service used to handle profile commands
     */
    public ProfilesController(ProfileQueryService profileQueryService, ProfileCommandService profileCommandService) {
        this.profileQueryService = profileQueryService;
        this.profileCommandService = profileCommandService;
    }

    /**
     * Retrieves a profile by its unique ID.
     *
     * @param profileId the ID of the profile to retrieve
     * @return a ResponseEntity containing the ProfileResource if found, otherwise a bad request response
     */
    @GetMapping("profile/{profileId}")
    public ResponseEntity<ProfileResource> getProfileById(@PathVariable long profileId) {
        var getProfileByIdQuery = new GetProfileByIdQuery(profileId);
        var profile = profileQueryService.handle(getProfileByIdQuery);
        if (profile.isEmpty()) return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

    /**
     * Retrieves a profile by its DNI.
     *
     * @param dni the DNI of the profile to retrieve
     * @return a ResponseEntity containing the ProfileResource if found, otherwise a not found response
     */
    @GetMapping("dni/{dni}")
    public ResponseEntity<ProfileResource> getProfileByDni(@PathVariable int dni) {
        var getProfileByDniQuery = new GetProfileByDniQuery(dni);
        var profile = profileQueryService.handle(getProfileByDniQuery);
        if (profile.isEmpty()) return ResponseEntity.notFound().build();
        ProfileResource profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

    /**
     * Retrieves a profile by the associated User ID.
     *
     * @param userId the ID of the user whose profile to retrieve
     * @return a ResponseEntity containing the ProfileResource if found, otherwise a not found response
     */
    @GetMapping("user-id/{userId}")
    public ResponseEntity<ProfileResource> getProfileByUserId(@PathVariable long userId) {
        var getProfileByUserIdQuery = new GetProfileByUserIdQuery(userId);
        var profile = profileQueryService.handle(getProfileByUserIdQuery);
        if (profile.isEmpty()) return ResponseEntity.notFound().build();
        ProfileResource profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }
}