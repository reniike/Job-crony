package com.example.jobcrony.services.userService;

import com.example.jobcrony.data.repositories.UserRepository;
import com.example.jobcrony.dtos.responses.UserResponse;
import com.example.jobcrony.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private ModelMapper modelMapper;
    private UserRepository userRepository;
    @Override
    public UserResponse findByEmail(String email) throws UserNotFoundException {
        return null;
    }
}
