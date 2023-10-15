package com.example.jobcrony.services.userService;

import com.example.jobcrony.data.models.User;
import com.example.jobcrony.dtos.requests.ResetPasswordRequest;
import com.example.jobcrony.dtos.requests.UpdatePasswordRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.InvalidTokenException;
import com.example.jobcrony.exceptions.SendMailException;
import com.example.jobcrony.exceptions.UserNotFoundException;
import com.example.jobcrony.exceptions.WrongPasswordException;
import org.springframework.http.ResponseEntity;


public interface UserService {
   User findByEmail(String email) throws UserNotFoundException;
    ResponseEntity<GenericResponse<String>> updatePassword(UpdatePasswordRequest request) throws UserNotFoundException, WrongPasswordException;
    ResponseEntity<GenericResponse<String>> forgotPassword(String emailAddress) throws UserNotFoundException, SendMailException;
    ResponseEntity<GenericResponse<String>> resetPassword(ResetPasswordRequest request) throws UserNotFoundException, InvalidTokenException;
}
