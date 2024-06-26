package org.tan.booknetwork.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticateRequest {
    @Email(message = "Email is not valid.")
    @NotEmpty(message = "Email is mandatory.")
    @NotBlank(message = "Email is mandatory.")
    private String email;

    @NotEmpty(message = "Password is mandatory.")
    @NotBlank(message = "Password is mandatory.")
    @Size(message = "Password must contain special character and it should be 10 character long")
    private String password;
}
