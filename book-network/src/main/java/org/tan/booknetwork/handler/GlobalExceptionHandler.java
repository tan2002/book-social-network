package org.tan.booknetwork.handler;

import jakarta.mail.MessagingException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.tan.booknetwork.exception.OperationNotPermittedException;

import java.util.HashSet;
import java.util.Set;

import static org.tan.booknetwork.handler.BusinessErrorCode.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
   @ExceptionHandler(LockedException.class)
   public ResponseEntity<ExceptionResponse> handleException(LockedException exp){
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
              ExceptionResponse
                      .builder()
                      .businessErrorCode(ACCOUNT_LOCKED.getCode())
                      .businessErrorDescription(ACCOUNT_LOCKED.getDescription())
                      .error(exp.getMessage())
                      .build()

      );
   }

   @ExceptionHandler(DisabledException.class)
   public ResponseEntity<ExceptionResponse> handleException(DisabledException exp){
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
              ExceptionResponse
                      .builder()
                      .businessErrorCode(ACCOUNT_DISABLED.getCode())
                      .businessErrorDescription(ACCOUNT_DISABLED.getDescription())
                      .error(exp.getMessage())
                      .build()

      );
   }

   @ExceptionHandler(BadCredentialsException.class)
   public ResponseEntity<ExceptionResponse> handleException(BadCredentialsException exp){
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
              ExceptionResponse
                      .builder()
                      .businessErrorCode(BAD_CREDENTIALS.getCode())
                      .businessErrorDescription(BAD_CREDENTIALS.getDescription())
                      .error(BAD_CREDENTIALS.getDescription())
                      .build()
      );
   }

   @ExceptionHandler(MessagingException.class)
   public ResponseEntity<ExceptionResponse> handleException(MessagingException exp){
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
              ExceptionResponse
                      .builder()
                      .error(exp.getMessage())
                      .build()
      );
   }

   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<ExceptionResponse> handleException(MethodArgumentNotValidException exp){
      Set<String> errors = new HashSet<>();
      exp.getBindingResult().getAllErrors().forEach(error->{
         var errorMessage = error.getDefaultMessage();
         errors.add(errorMessage);
      });
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
              ExceptionResponse
                      .builder()
                      .validationError(errors)
                      .build()
      );
   }

   @ExceptionHandler(Exception.class)
   public ResponseEntity<ExceptionResponse> handleException(Exception exp){
      //Log the exception
      exp.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
              ExceptionResponse
                      .builder()
                      .businessErrorDescription("Internal server error, please contact admin.")
                      .error(exp.getMessage())
                      .build()
      );
   }

   @ExceptionHandler(OperationNotPermittedException.class)
   public ResponseEntity<ExceptionResponse> handleException(OperationNotPermittedException exp){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
              ExceptionResponse
                      .builder()
                      .error(exp.getMessage())
                      .build()
      );
   }


}
