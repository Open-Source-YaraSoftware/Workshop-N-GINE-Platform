package com.yarasoftware.workshopngine.platform.iam.interfaces.rest;

import com.yarasoftware.workshopngine.platform.iam.domain.services.UserCommandService;
import com.yarasoftware.workshopngine.platform.iam.interfaces.rest.resources.AuthenticateUserResource;
import com.yarasoftware.workshopngine.platform.iam.interfaces.rest.resources.SignInResource;
import com.yarasoftware.workshopngine.platform.iam.interfaces.rest.resources.SignUpResource;
import com.yarasoftware.workshopngine.platform.iam.interfaces.rest.resources.UserResource;
import com.yarasoftware.workshopngine.platform.iam.interfaces.rest.transform.AuthenticateUserResourceFromEntityAssembler;
import com.yarasoftware.workshopngine.platform.iam.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import com.yarasoftware.workshopngine.platform.iam.interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import com.yarasoftware.workshopngine.platform.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthenticationController is a class that manages the Authentication Endpoints.
 */
@RestController
@RequestMapping(value = "/authentication")
@Tag(name = "Authentication", description = "Authentication Management Endpoints")
public class AuthenticationController {
    private final UserCommandService userCommandService;

    /**
     * The constructor of AuthenticationController.
     * @param userCommandService the {@link UserCommandService} to handle the User Commands
     */
    public AuthenticationController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    /**
     * The method signIn signs in the user.
     * @param signInResource the sign in resource
     * @return the response entity of the AuthenticateUserResource
     */
    @PostMapping("/sign-in")
    @Operation(summary = "Sign in the user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User signed in"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<AuthenticateUserResource> signIn(@RequestBody SignInResource signInResource) {
        var signInCommand = SignInCommandFromResourceAssembler.toCommandFromResource(signInResource);
        var user = userCommandService.handle(signInCommand);
        if (user.isEmpty()) return ResponseEntity.notFound().build();
        var authenticateUserResource = AuthenticateUserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(authenticateUserResource);
    }

    /**
     * The method signUp signs up the user.
     * @param signUpResource the sign-up resource
     * @return the response entity of the UserResource
     */
    @PostMapping("/sign-up")
    @Operation(summary = "Sign up the user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User signed up"),
            @ApiResponse(responseCode = "400", description = "User not signed up")
    })
    public ResponseEntity<UserResource> signUp(@RequestBody SignUpResource signUpResource) {
        var signUpCommand = SignUpCommandFromResourceAssembler.toCommandFromResource(signUpResource);
        var user = userCommandService.handle(signUpCommand);
        if (user.isEmpty()) return ResponseEntity.badRequest().build();
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }
}
