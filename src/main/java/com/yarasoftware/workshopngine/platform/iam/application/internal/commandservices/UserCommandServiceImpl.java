package com.yarasoftware.workshopngine.platform.iam.application.internal.commandservices;
import com.yarasoftware.workshopngine.platform.iam.domain.model.aggregates.User;
import com.yarasoftware.workshopngine.platform.iam.domain.model.commands.CreateUserCommand;
import com.yarasoftware.workshopngine.platform.iam.domain.model.commands.SignInCommand;
import com.yarasoftware.workshopngine.platform.iam.domain.model.commands.SignUpCommand;
import com.yarasoftware.workshopngine.platform.iam.domain.services.UserCommandService;
import com.yarasoftware.workshopngine.platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.yarasoftware.workshopngine.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
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

    public UserCommandServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    /**
     * @inheritDoc
     */
    // TODO: Delete this method if not needed
    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByUsername(command.username())) throw new RuntimeException("User " + command.username() + " already exists");
        var role = roleRepository.findById(command.role().getId()).orElseThrow(() -> new RuntimeException("Role not found"));
        var user = new User(command.username(), command.password(), role, command.workshopId());
        userRepository.save(user);
        return userRepository.findByUsername(command.username());
    }

    /**
     * @inheritDoc
     */
    @Override
    public Optional<User> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.username());
        if (user.isEmpty()) throw new RuntimeException("User " + command.username() + " found");
        if (!command.password().equals(user.get().getPassword())) throw new RuntimeException("Password " + command.password() + " is invalid");
        return user;
    }

    @Override
    public Long handle(CreateUserCommand command) {
        if (userRepository.existsByUsername(command.username())) throw new RuntimeException("User " + command.username() + " already exists");
        var role = roleRepository.findById(command.role().getId()).orElseThrow(() -> new RuntimeException("Role not found"));
        var user = new User(command.username(), command.password(), role, command.workshopId());
        userRepository.save(user);
        return user.getId();
    }
}
