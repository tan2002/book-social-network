package org.tan.booknetwork.handler;

import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

public enum BusinessErrorCode {
    NO_CODE(0, "No code", NOT_IMPLEMENTED),
    INCORRECT_CURRENT_PASSWORD(300,"Current password in incorrect",BAD_REQUEST),
    NEW_PASSWORD_DOES_NOT_MATCHED(301,"The new password does not match",BAD_REQUEST),
    ACCOUNT_LOCKED(302,"User account is locked.",FORBIDDEN),
    ACCOUNT_DISABLED(303,"User acount is disabled",FORBIDDEN),
    BAD_CREDENTIALS(304,"Invalid credentials.",FORBIDDEN);

    @Getter
    private final int code;
    @Getter
    private final  String description;
    @Getter
    private final HttpStatus httpStatus;


    BusinessErrorCode(int code, String description, HttpStatus httpStatus) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }
}
