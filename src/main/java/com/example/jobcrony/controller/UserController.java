package com.example.jobcrony.controller;

import com.example.jobcrony.dtos.requests.UpdatePasswordRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.InvalidConfirmationException;
import com.example.jobcrony.exceptions.SendMailException;
import com.example.jobcrony.exceptions.UserNotFoundException;
import com.example.jobcrony.exceptions.WrongPasswordException;
import com.example.jobcrony.services.userService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/job-crony-user")
public class UserController {
    private UserService userService;


    @PutMapping("/updatePassword")
    public ResponseEntity<GenericResponse<String>> updatePassword(@RequestBody UpdatePasswordRequest request) throws UserNotFoundException, WrongPasswordException {
        return userService.updatePassword(request);
    }

    @PutMapping("/forgotPassword/{email}")
    public ResponseEntity<GenericResponse<String>> forgotPassword(@PathVariable String email) throws UserNotFoundException, SendMailException {
        return userService.forgotPassword(email);
    }

    @DeleteMapping("/deleteAccount/{confirmationKeyword}")
    public ResponseEntity<GenericResponse<String>> deleteAccount(@PathVariable String confirmationKeyword) throws UserNotFoundException, InvalidConfirmationException {
        return ResponseEntity.ok().body(userService.deleteAccount(confirmationKeyword));
    }
}
