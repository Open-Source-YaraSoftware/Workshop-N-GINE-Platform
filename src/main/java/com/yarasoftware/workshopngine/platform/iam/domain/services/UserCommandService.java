package com.yarasoftware.workshopngine.platform.iam.domain.services;

import com.yarasoftware.workshopngine.platform.iam.domain.model.aggregates.User;
import com.yarasoftware.workshopngine.platform.iam.domain.model.commands.CreateUserCommand;
import com.yarasoftware.workshopngine.platform.iam.domain.model.commands.SignInCommand;
import com.yarasoftware.workshopngine.platform.iam.domain.model.commands.SignUpCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(SignUpCommand command);

    Optional<ImmutablePair<User, String>> handle(SignInCommand command);

    Long handle(CreateUserCommand command);
}
