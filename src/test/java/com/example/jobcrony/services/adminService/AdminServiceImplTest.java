package com.example.jobcrony.services.adminService;

import com.example.jobcrony.data.models.AdminInvitation;
import com.example.jobcrony.data.repositories.AdminInvitationRepo;
import com.example.jobcrony.data.repositories.AdminRepository;
import com.example.jobcrony.dtos.requests.AdminInvitationRequest;
import com.example.jobcrony.dtos.requests.AdminRegistrationRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.AdminExistException;
import com.example.jobcrony.exceptions.SendMailException;
import com.example.jobcrony.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static com.example.jobcrony.utilities.AppUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class AdminServiceImplTest {
    @Autowired
    private AdminService adminService;
    @MockBean
    private AdminInvitationRepo invitationRepo;

    @Test
    void registerAdmin() throws UserNotFoundException, AdminExistException {
        AdminRegistrationRequest registrationRequest = AdminRegistrationRequest
                .builder()
                .email("renikecodes@gmail.com")
                .password("Aliyah1@")
                .firstName("Aliyah")
                .lastName("Renike")
                .phoneNumber("09078787878")
                .build();

        when(invitationRepo.findAdminInvitationByEmailAndToken(any(), any()))
                .thenReturn(
                        Optional.ofNullable(AdminInvitation.builder()
                                .email("renikecodes@gmail.com")
                                .token("token")
                                .build())
                );

        GenericResponse<String> invitation = adminService.registerAdmin(registrationRequest).getBody();
        assertNotNull(invitation);
        assertEquals(ADMIN_REGISTERED_SUCCESSFULLY, invitation.getMessage());
        assertEquals(HTTP_STATUS_OK, invitation.getStatus());
    }

    @Test
    void sendInvitationLink() throws AdminExistException, SendMailException {
        AdminInvitationRequest adminInvitationRequest = new AdminInvitationRequest();
        adminInvitationRequest.setEmail("renikecodes@gmail.com");
        GenericResponse<String> response = adminService.sendInvitationLink(adminInvitationRequest).getBody();
        assertNotNull(response);
        assertEquals(EMAIL_SENT_SUCCESSFULLY, response.getMessage());

    }
}
