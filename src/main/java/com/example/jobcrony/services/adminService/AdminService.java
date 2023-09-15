package com.example.jobcrony.services.adminService;

import com.example.jobcrony.dtos.requests.AdminInvitationRequest;
import com.example.jobcrony.dtos.requests.AdminRegistrationRequest;
import com.example.jobcrony.dtos.responses.AdminResponse;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.AdminExistException;
import com.example.jobcrony.exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface AdminService {
    AdminResponse findByEmail(String email);
    ResponseEntity<GenericResponse<String>> registerAdmin(AdminRegistrationRequest request) throws AdminExistException, UserNotFoundException;
    ResponseEntity<GenericResponse<String>> sendInvitationLink(AdminInvitationRequest invitationRequest) throws AdminExistException;
}
