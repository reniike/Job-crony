package com.example.jobcrony.services.userService;

import com.example.jobcrony.data.models.User;
import com.example.jobcrony.data.repositories.UserRepository;
import com.example.jobcrony.dtos.requests.ResetPasswordRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import static com.example.jobcrony.utilities.AppUtils.NOT_FOUND;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private ModelMapper modelMapper;
    private UserRepository userRepository;
    @Override
    public User findByEmail(String email) throws UserNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(()-> new NotFoundException(NOT_FOUND));
    }

    @Override
    public ResponseEntity<GenericResponse<String>> resetPassword(ResetPasswordRequest request) {

        return null;
    }
}
