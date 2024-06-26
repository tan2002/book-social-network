package org.tan.booknetwork.auth;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.tan.booknetwork.email.EmailService;
import org.tan.booknetwork.email.EmailTemplateName;
import org.tan.booknetwork.role.RoleRepository;
import org.tan.booknetwork.security.JwtService;
import org.tan.booknetwork.user.Token;
import org.tan.booknetwork.user.TokenRepository;
import org.tan.booknetwork.user.User;
import org.tan.booknetwork.user.UserRepository;

import java.net.UnknownServiceException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
     
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    private final JwtService jwtService;

    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;

    private final AuthenticationManager authenticationManager;


    public void register(RegistrationRequest request) throws MessagingException {
        var userRole = roleRepository
                .findByName("USER")
                .orElseThrow(() -> new IllegalStateException("ROLE USER was not initialised"));
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();
        userRepository.save(user);
        sendValidationEmail(user);
    }


    // it will generate new token
    private void sendValidationEmail(User user) throws MessagingException {
        var newToken = generateAndSaveActivationToken(user);
        emailService.sendEmail(
                user.getEmail(),
                user.getFullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                newToken,
                "Account activation"
        );
    }

    private String generateAndSaveActivationToken(User user){
        String generatedToken = generateAndSaveActivationCode(6);
        var token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusMinutes(15))
                .user(user) // To whom this token is generated.
                .build();
        tokenRepository.save(token);
        return generatedToken;
    }

    private String generateAndSaveActivationCode(int length){
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for(int i=0; i<length; i++){
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }

    public AuthenticationResponse authenticate(AuthenticateRequest request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = (User) auth.getPrincipal();
        if(!user.isEnabled())
            throw new RuntimeException("User account is not enabled");
        if(!user.isAccountLocked())
            throw new RuntimeException("User account is locked");
        var claims = new HashMap<String,Object>();
        claims.put("fullName",user.getFullName());
        var jwtToken = jwtService.generateToken(claims,user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }


    public void activateAccount(String token) throws MessagingException {
        Token savedToken = tokenRepository.findByToken(token).orElseThrow(
                () -> new RuntimeException("Invalid Token")
        );
        if(LocalDateTime.now().isAfter(savedToken.getExpiredAt())){
            sendValidationEmail(savedToken.getUser());
            throw new RuntimeException("Activation token has expired. A new token has been send to the user.");
        }
        var user = userRepository.findById(savedToken.getUser().getId()).orElseThrow(
                () -> new UsernameNotFoundException("User not Found")
        );
        user.setEnabled(true);
        userRepository.save(user);
        savedToken.setValidateAt(LocalDateTime.now());
    }
}
