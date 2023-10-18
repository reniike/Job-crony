package com.example.jobcrony.services.adminService;

import com.example.jobcrony.data.models.Admin;
import com.example.jobcrony.data.models.AdminInvitation;
import com.example.jobcrony.data.repositories.AdminInvitationRepo;
import com.example.jobcrony.data.repositories.AdminRepository;
import com.example.jobcrony.dtos.requests.AdminInvitationRequest;
import com.example.jobcrony.dtos.requests.AdminRegistrationRequest;
import com.example.jobcrony.dtos.requests.SendMailRequest;
import com.example.jobcrony.dtos.responses.AdminResponse;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.AdminExistException;
import com.example.jobcrony.exceptions.SendMailException;
import com.example.jobcrony.exceptions.UserNotFoundException;
import com.example.jobcrony.security.JobCronyUserDetails;
import com.example.jobcrony.services.mailService.MailService;
import com.example.jobcrony.utilities.JwtUtility;
import com.example.jobcrony.utilities.MailUtility;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.jobcrony.utilities.AppUtils.*;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService{
    private AdminRepository adminRepository;
    private AdminInvitation adminInvitation;
    private AdminInvitationRepo invitationRepo;
    private ModelMapper modelMapper;
    private MailUtility mailUtility;
    private JwtUtility jwtUtility;
    private PasswordEncoder passwordEncoder;

    @Override
    public AdminResponse findByEmail(String email) {
        return null;
    }

    @Override
    public ResponseEntity<GenericResponse<String>> registerAdmin(AdminRegistrationRequest request) throws UserNotFoundException {
        validateToken(request.getEmail(),request.getToken());
        Admin admin = modelMapper.map(request, Admin.class);
        admin.setPassword(passwordEncoder.encode(request.getPassword()));

        adminRepository.save(admin);

        JobCronyUserDetails userDetails = new JobCronyUserDetails(admin);
        String token = jwtUtility.generateToken(admin.getRoles(), userDetails);
        return ResponseEntity.ok().body(GenericResponse.<String>builder()
                        .message(ADMIN_REGISTERED_SUCCESSFULLY)
                        .status(HTTP_STATUS_OK)
                        .data(token)
                .build());
    }

    private void validateToken(String email, String token) throws UserNotFoundException {
        invitationRepo.findAdminInvitationByEmailAndToken(email, token).orElseThrow(() -> new UserNotFoundException(USER_WITH_EMAIL_NOT_FOUND));
    }

    @Override
    public ResponseEntity<GenericResponse<String>> sendInvitationLink(AdminInvitationRequest invitationRequest) throws AdminExistException, SendMailException {
        validateEmail(invitationRequest.getEmail());
        String token = jwtUtility.generateToken(invitationRequest.getEmail());
        adminInvitation.setEmail(invitationRequest.getEmail());
        adminInvitation.setToken(token);

        invitationRepo.save(adminInvitation);
        String activationLink = ADMIN_REGISTRATION_URL + token;
        mailUtility.sendAdminInvitationMail(invitationRequest.getEmail(), activationLink);
        GenericResponse<String> genericResponse = GenericResponse.<String>builder()
                .message(EMAIL_SENT_SUCCESSFULLY)
                .build();
        return ResponseEntity.ok().body(genericResponse);
    }

    @Override
    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }

    private void validateEmail(String email) throws AdminExistException {
        if (adminRepository.findAdminByEmail(email).isPresent()) throw  new AdminExistException(ADMIN_WITH_EMAIL_EXIST);
    }

}
