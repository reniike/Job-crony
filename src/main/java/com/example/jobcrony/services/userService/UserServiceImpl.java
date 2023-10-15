package com.example.jobcrony.services.userService;

import com.example.jobcrony.data.models.Token;
import com.example.jobcrony.data.models.User;
import com.example.jobcrony.data.repositories.UserRepository;
import com.example.jobcrony.dtos.requests.ResetPasswordRequest;
import com.example.jobcrony.dtos.requests.UpdatePasswordRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.InvalidTokenException;
import com.example.jobcrony.exceptions.SendMailException;
import com.example.jobcrony.exceptions.UserNotFoundException;
import com.example.jobcrony.exceptions.WrongPasswordException;
import com.example.jobcrony.services.tokenService.TokenService;
import com.example.jobcrony.utilities.AuthenticationUtils;
import com.example.jobcrony.utilities.JwtUtility;
import com.example.jobcrony.utilities.MailUtility;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import static com.example.jobcrony.utilities.AppUtils.*;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private ModelMapper modelMapper;
    private UserRepository userRepository;
    private AuthenticationUtils authUtils;
    private PasswordEncoder passwordEncoder;
    private JwtUtility jwtUtility;
    private MailUtility mailUtility;
    private TokenService tokenService;
    @Override
    public User findByEmail(String email) throws UserNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(()-> new NotFoundException(USER_NOT_FOUND));
    }

    @Override
    public ResponseEntity<GenericResponse<String>> updatePassword(UpdatePasswordRequest request) throws UserNotFoundException, WrongPasswordException {
        User user = authUtils.getCurrentUser();
        User foundUser = findByEmail(user.getEmail());
        if (!passwordEncoder.matches(request.getCurrentPassword(), foundUser.getPassword())) throw new WrongPasswordException(CURRENT_PASSWORD_IS_INCORRECT);
        foundUser.setPassword(passwordEncoder.encode(request.getCurrentPassword()));
        userRepository.save(foundUser);
        return ResponseEntity.ok().body(GenericResponse.<String>builder().message(PASSWORD_UPDATED).status(HTTP_STATUS_OK).build());
    }

    @Override
    public ResponseEntity<GenericResponse<String>> forgotPassword(String emailAddress) throws UserNotFoundException, SendMailException {
        User foundUser = findByEmail(emailAddress);
        String generatedToken = jwtUtility.generateToken(foundUser);
        Token token = Token.builder()
                .token(generatedToken)
                .build();
        tokenService.saveToken(token);
        String resetPasswordLink = RESET_PASSWORD_URL + generatedToken;
        mailUtility.sendForgotPasswordMail(resetPasswordLink, foundUser.getEmail());
        return ResponseEntity.ok().body(GenericResponse.<String>builder().status(HTTP_STATUS_OK).message(FORGOT_PASSWORD_EMAIL_SENT).build());
    }

    @Override
    public ResponseEntity<GenericResponse<String>> resetPassword(ResetPasswordRequest request) throws UserNotFoundException, InvalidTokenException {
        Token token = tokenService.validateToken(request.getToken());
        String email = jwtUtility.extractEmailFrom(request.getToken());
        User foundUser = findByEmail(email);
        foundUser.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(foundUser);
        tokenService.disableToken(token);
        GenericResponse<String> genericResponse= GenericResponse.<String>builder()
                .message(PASSWORD_RESET_SUCCESSFUL)
                .status(HTTP_STATUS_OK)
                .build();
        return ResponseEntity.ok().body(genericResponse);
    }


}
