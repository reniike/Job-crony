package com.example.jobcrony.services.adminService;

import com.example.jobcrony.data.models.Admin;
import com.example.jobcrony.data.repositories.AdminRepository;
import com.example.jobcrony.dtos.requests.AdminRegistrationRequest;
import com.example.jobcrony.exceptions.AdminExistException;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import static com.example.jobcrony.utilities.AppUtils.ADMIN_WITH_EMAIL_EXIST;

@Component
@AllArgsConstructor
@Slf4j
public class AutoGenerateAdmin {
    private AdminRepository adminRepository;
    private ModelMapper mapper;

    @PostConstruct
    public void saveAdmin() throws AdminExistException {
        try {
        AdminRegistrationRequest request = AdminRegistrationRequest.builder()
                .email("aliyaheniola91@gmail.com")
                .firstName("aliyah")
                .lastName("eniola")
                .phoneNumber("090")
                .password("aliyah1")
                .build();
        validateEmail(request.getEmail());
        Admin admin = mapper.map(request, Admin.class);
        adminRepository.save(admin);
        } catch (AdminExistException e){
            log.info("Just something nice");
        }
    }
    private void validateEmail(String email) throws AdminExistException {
        if (adminRepository.findAdminByEmail(email).isPresent()) throw  new AdminExistException(ADMIN_WITH_EMAIL_EXIST);
    }
}
