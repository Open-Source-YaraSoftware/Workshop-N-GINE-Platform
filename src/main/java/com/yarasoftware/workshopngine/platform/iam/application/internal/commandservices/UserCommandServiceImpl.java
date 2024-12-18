package com.yarasoftware.workshopngine.platform.iam.application.internal.commandservices;
import com.yarasoftware.workshopngine.platform.iam.application.internal.outboundservices.hashing.HashingService;
import com.yarasoftware.workshopngine.platform.iam.application.internal.outboundservices.tokens.TokenService;
import com.yarasoftware.workshopngine.platform.iam.domain.model.aggregates.User;
import com.yarasoftware.workshopngine.platform.iam.domain.model.commands.CreateUserCommand;
import com.yarasoftware.workshopngine.platform.iam.domain.model.commands.SignInCommand;
import com.yarasoftware.workshopngine.platform.iam.domain.model.commands.SignUpCommand;
import com.yarasoftware.workshopngine.platform.iam.domain.model.valueobjects.Roles;
import com.yarasoftware.workshopngine.platform.iam.domain.services.UserCommandService;
import com.yarasoftware.workshopngine.platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.yarasoftware.workshopngine.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Implementation of the UserCommandService interface.
 * This class is responsible for handling the SignUpCommand and SignInCommand.
 * @see UserCommandService
 */
@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;

    public UserCommandServiceImpl(UserRepository userRepository, RoleRepository roleRepository, HashingService hashingService, TokenService tokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
    }

    /**
     * @inheritDoc
     */
    // TODO: Delete this method if not needed
    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByUsername(command.username()))
            throw new RuntimeException("Username already exists");
        var roles = command.roles();
        if (roles.isEmpty()) {
            var role = roleRepository.findByName(Roles.ROLE_WORKSHOP_OWNER);
            if (role.isPresent()) roles.add(role.get());
        } else roles = roles.stream().map(role -> roleRepository.findByName(role.getName())
                .orElseThrow(() -> new RuntimeException("Role not found"))).toList();
        var user = new User(command.username(), hashingService.encode(command.password()), roles, command.workshopId());
        userRepository.save(user);
        return userRepository.findByUsername(command.username());
    }

    /**
     * @inheritDoc
     */
    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.username());
        if (user.isEmpty()) throw new RuntimeException("User " + command.username() + " found");
        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");
        var token = tokenService.generateToken(user.get().getUsername());
        return Optional.of(new ImmutablePair<>(user.get(), token));
    }

    @Override
    public Long handle(CreateUserCommand command) {
        if (userRepository.existsByUsername(command.username()))
            throw new RuntimeException("User " + command.username() + " already exists");
        if (command.roles().isEmpty()) {
            throw new RuntimeException("User " + command.username() + " must have at least one role");
        }
        var roles = command.roles().stream().map(role -> roleRepository.findByName(role.getName())
                .orElseThrow(() -> new RuntimeException("Role not found"))).toList();

        var user = new User(command.username(), command.password(), roles, command.workshopId());
        userRepository.save(user);
        return user.getId();
    }
}
