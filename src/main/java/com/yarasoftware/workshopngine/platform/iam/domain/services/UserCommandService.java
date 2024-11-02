package com.yarasoftware.workshopngine.platform.iam.domain.services;

import com.yarasoftware.workshopngine.platform.iam.domain.model.aggregates.User;
import com.yarasoftware.workshopngine.platform.iam.domain.model.commands.CreateUserCommand;
import com.yarasoftware.workshopngine.platform.iam.domain.model.commands.SignInCommand;
import com.yarasoftware.workshopngine.platform.iam.domain.model.commands.SignUpCommand;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(SignUpCommand command);

    Optional<User> handle(SignInCommand command);

    Long handle(CreateUserCommand command);
}
