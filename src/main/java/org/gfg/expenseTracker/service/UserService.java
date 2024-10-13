package org.gfg.expenseTracker.service;

import org.gfg.expenseTracker.model.User;
import org.gfg.expenseTracker.model.UserStatus;
import org.gfg.expenseTracker.repository.UserRepository;
import org.gfg.expenseTracker.request.CreateUserRequest;
import org.gfg.expenseTracker.response.CreateUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public CreateUserResponse addUser(CreateUserRequest createUserRequest) {
        User userFromDb = userRepository.findByEmailAddressJPQL(createUserRequest.getEmail());
        if (userFromDb == null) {
            // Convert the request to a User object using an instance method
            User user = createUserRequest.toUser();

            // Set the user status to ACTIVE
            user.setUserStatus(UserStatus.ACTIVE);

            // Save the user into the database
            userFromDb = userRepository.save(user);
        }
        // Create and return the response
        CreateUserResponse createUserResponse = CreateUserResponse.builder()
                .userId(userFromDb.getId())
                .build();
        return createUserResponse;
    }

}
