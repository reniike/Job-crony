package com.example.jobcrony.services.userService;

import com.example.jobcrony.dtos.responses.UserResponse;
import com.example.jobcrony.exceptions.UserNotFoundException;


public interface UserService {
   UserResponse findByEmail(String email) throws UserNotFoundException;
}
