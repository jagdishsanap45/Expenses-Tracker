package org.gfg.expenseTracker.controller;

import jakarta.validation.Valid;
import lombok.Builder;
import org.gfg.expenseTracker.request.CreateUserRequest;
import org.gfg.expenseTracker.response.CreateUserResponse;
import org.gfg.expenseTracker.response.GenericResponse;
import org.gfg.expenseTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController

@RequestMapping("/expenseTracker")
public class UserController {

    @Autowired
    private UserService userService;

    //creationpart
    @PostMapping("/addUser")
    public GenericResponse<CreateUserResponse> addUser(@Valid @RequestBody CreateUserRequest createUserRequest) {

        CreateUserResponse createUserResponse = userService.addUser(createUserRequest);
        GenericResponse<CreateUserResponse> genericResponse = GenericResponse.<CreateUserResponse>builder()
                .code(HttpStatus.OK.value())
                .message("User Details Saved")
                .statusCode(0)
                .data(createUserResponse)
                .build();
        return genericResponse;
    }


}
