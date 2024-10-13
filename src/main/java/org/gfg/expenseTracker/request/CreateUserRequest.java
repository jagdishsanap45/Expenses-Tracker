package org.gfg.expenseTracker.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.gfg.expenseTracker.model.User;
import org.gfg.expenseTracker.response.CreateUserResponse;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreateUserRequest {
    @NotBlank(message = "Email should not blank")
    private String email;

    private String name;

    @NotNull(message ="contact sould not be null")
    private String contact;

    public  User toUser() {
        return User.builder().name(this.name).
                email(this.email).
                contact(this.contact).
                build();
    }
}
