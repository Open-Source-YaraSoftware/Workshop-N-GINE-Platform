package com.yarasoftware.workshopngine.platform.profiles.interfaces.rest;
import com.yarasoftware.workshopngine.platform.profiles.domain.model.queries.GetProfileByDniQuery;
import com.yarasoftware.workshopngine.platform.profiles.domain.model.queries.GetProfileByIdQuery;
import com.yarasoftware.workshopngine.platform.profiles.domain.model.queries.GetProfileByUserIdQuery;
import com.yarasoftware.workshopngine.platform.profiles.domain.services.ProfileCommandService;
import com.yarasoftware.workshopngine.platform.profiles.domain.services.ProfileQueryService;
import com.yarasoftware.workshopngine.platform.profiles.interfaces.rest.resources.CreateProfileResource;
import com.yarasoftware.workshopngine.platform.profiles.interfaces.rest.resources.ProfileResource;
import com.yarasoftware.workshopngine.platform.profiles.interfaces.rest.resources.UpdateProfileResource;
import com.yarasoftware.workshopngine.platform.profiles.interfaces.rest.transform.CreateProfileCommandFromResourceAssembler;
import com.yarasoftware.workshopngine.platform.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import com.yarasoftware.workshopngine.platform.profiles.interfaces.rest.transform.UpdateProfileCommandFromResourceAssembler;
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
    @GetMapping("{profileId}")
    public ResponseEntity<ProfileResource> getProfileById(@PathVariable Long profileId) {
        var getProfileByIdQuery = new GetProfileByIdQuery(profileId);
        var profile = profileQueryService.handle(getProfileByIdQuery);
        if (profile.isEmpty()) return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

    /**
     * Retrieves a profile based on either DNI or User ID.
     *
     * <p>
     * This method allows fetching a profile by providing either a DNI or a User ID as a query parameter.
     * If both parameters are provided, only DNI will be used. If neither parameter is provided,
     * a {@code 400 Bad Request} response is returned.
     * </p>
     *
     * @param dni the DNI of the profile to retrieve, optional
     * @param userId the User ID of the profile to retrieve, optional
     * @return a {@link ResponseEntity} containing the {@link ProfileResource} if found;
     *         {@code 404 Not Found} if no profile matches the criteria;
     *         or {@code 400 Bad Request} if neither parameter is provided or if userId is invalid.
     */
    @GetMapping("/profiles")
    public ResponseEntity<ProfileResource> getProfile(@RequestParam(required = false) Integer dni, @RequestParam(required = false) Long userId) {
        if (dni != null) {
            var getProfileByDniQuery = new GetProfileByDniQuery(dni);
            var profile = profileQueryService.handle(getProfileByDniQuery);
            if (profile.isEmpty()) return ResponseEntity.notFound().build();
            ProfileResource profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
            return ResponseEntity.ok(profileResource);
        } else if (userId != null) {
            if (userId <= 0) return ResponseEntity.badRequest().build();
            var getProfileByUserIdQuery = new GetProfileByUserIdQuery(userId);
            var profile = profileQueryService.handle(getProfileByUserIdQuery);
            if (profile.isEmpty()) return ResponseEntity.notFound().build();
            ProfileResource profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
            return ResponseEntity.ok(profileResource);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    /**
     * Updates an existing profile with the specified ID using the provided data.
     *
     * @param profileId the ID of the profile to update
     * @param resource the data to update the profile with
     * @return a ResponseEntity containing the updated ProfileResource if successful, otherwise a bad request response
     */
    @PutMapping("{profileId}")
    public ResponseEntity<ProfileResource> updateProfile(@PathVariable long profileId, @RequestBody UpdateProfileResource resource) {
        var updateProfileCommand = UpdateProfileCommandFromResourceAssembler.toCommandFromResource(resource);
        var profile = profileCommandService.handle(profileId, updateProfileCommand);
        if (profile == null) return ResponseEntity.badRequest().build();
        var getProfileById = new GetProfileByIdQuery(profile);
        var profileUpdated = profileQueryService.handle(getProfileById);
        ProfileResource profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profileUpdated.get());
        return ResponseEntity.ok(profileResource);
    }

    /**
     * Creates a new profile using the provided data.
     *
     * @param resource the data to create the profile with
     * @return a ResponseEntity containing the created ProfileResource if successful, otherwise a bad request response
     */
    @PostMapping
    public ResponseEntity<ProfileResource> createProfile(@RequestBody CreateProfileResource resource) {
        var createProfileCommand = CreateProfileCommandFromResourceAssembler.toCommandFromResource(resource);
        var profile = profileCommandService.handle(createProfileCommand);
        if (profile == null) return ResponseEntity.badRequest().build();
        var getProfileById = new GetProfileByIdQuery(profile);
        var profileUpdated = profileQueryService.handle(getProfileById);
        ProfileResource profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profileUpdated.get());
        return ResponseEntity.created(URI.create("/api/v1/profiles/profile/" + profileResource.id())).body(profileResource);
    }
}
