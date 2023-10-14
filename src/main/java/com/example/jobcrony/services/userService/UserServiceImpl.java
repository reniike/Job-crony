package com.example.jobcrony.services.userService;

import com.example.jobcrony.data.models.User;
import com.example.jobcrony.data.repositories.UserRepository;
import com.example.jobcrony.dtos.requests.ResetPasswordRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.UserNotFoundException;
import com.example.jobcrony.exceptions.WrongPasswordException;
import com.example.jobcrony.utilities.AuthenticationUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
    private BCrypt bCrypt;
    @Override
    public User findByEmail(String email) throws UserNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(()-> new NotFoundException(NOT_FOUND));
    }

    @Override
    public ResponseEntity<GenericResponse<String>> resetPassword(ResetPasswordRequest request) throws UserNotFoundException, WrongPasswordException {
        User user = authUtils.getCurrentUser();
        User foundUser = findByEmail(user.getEmail());
        if (!passwordEncoder.matches(request.getCurrentPassword(), foundUser.getPassword())) throw new WrongPasswordException(CURRENT_PASSWORD_IS_INCORRECT);
        foundUser.setPassword(passwordEncoder.encode(request.getCurrentPassword()));
        userRepository.save(foundUser);
        return ResponseEntity.ok().body(GenericResponse.<String>builder().message(PASSWORD_UPDATED).status(HTTP_STATUS_OK).build());
    }
}
