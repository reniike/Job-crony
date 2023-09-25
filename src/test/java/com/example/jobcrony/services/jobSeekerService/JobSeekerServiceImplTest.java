//package com.example.jobcrony.services.jobSeekerService;
//
//import com.example.jobcrony.data.models.Location;
//import com.example.jobcrony.dtos.requests.JobSeekerRegistrationRequest;
//import com.example.jobcrony.dtos.requests.PreRegistrationRequest;
//import com.example.jobcrony.dtos.responses.GenericResponse;
//import com.example.jobcrony.exceptions.SendMailException;
//import com.example.jobcrony.exceptions.UserAlreadyExistException;
//import com.example.jobcrony.exceptions.VerificationFailedException;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Collections;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@SpringBootTest
//class JobSeekerServiceImplTest {
//    @Autowired
//    private JobSeekerService jobSeekerService;
//    private PreRegistrationRequest preRegistrationRequest;
//    private JobSeekerRegistrationRequest jobSeekerRegistrationRequest;
//    private ResponseEntity<GenericResponse<String>> preRegistrationResponse;
//    private ResponseEntity<GenericResponse<String>> completeRegistrationResponse;
//
//    @Test
//    @DisplayName("Pre registration test")
//    void preRegistrationTest() throws UserAlreadyExistException, SendMailException {
//        preRegistrationRequest = PreRegistrationRequest.builder()
//                .emailAddress("cebixo7810@cohodl.com")
//                .build();
//        preRegistrationResponse = jobSeekerService.initiateRegistration(preRegistrationRequest);
//        assertNotNull(preRegistrationResponse);
//    }
//
//    @Test
//    @DisplayName("Confirm registration test")
//    void confirmRegistrationTest() throws VerificationFailedException {
//        jobSeekerRegistrationRequest = JobSeekerRegistrationRequest.builder()
//                        .firstName("Aliyah")
//                        .lastName("eniola")
//                        .phoneNumber("090")
//                        .token("eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE2OTQzMDYxODEsImV4cCI6MTY5NDMxODE4MSwiZW1haWwiOiJjZWJpeG83ODEwQGNvaG9kbC5jb20ifQ.NZbkh0_YFLJhKPI9_h_4KUna6B1L_Fruvj_SToAOvC56usBbojojgkUvRQzII85VHeymxsKR2uTWiXVrjADUCw")
//                .educationList(Collections.emptyList())
//                .experienceList(Collections.emptyList())
//                .profilePicture("")
//                .location(Location.builder()
//                        .postalCode("")
//                        .state("")
//                        .city("")
//                        .country("")
//                        .build())
//                .roles(Collections.emptySet())
//                .skills(Collections.emptyList())
//                .password("Renikeji5")
//                .build();
//        completeRegistrationResponse = jobSeekerService.completeRegistration(jobSeekerRegistrationRequest);
//        assertNotNull(completeRegistrationResponse);
//        assertNotNull(completeRegistrationResponse.getStatusCode());
//    }
//
//    @Test
//    @DisplayName("Invalid token throws an exception test")
//    void invalidTokenExceptionTest() throws VerificationFailedException {
//        jobSeekerRegistrationRequest = JobSeekerRegistrationRequest.builder()
//                .firstName("Aliyah")
//                .lastName("eniola")
//                .phoneNumber("090")
//                .token("eyJhbGciOiJIUzUxMiIsJ9.eyJpYXQiOjE2OTQzMDYxODEsImV4cCI6MTY5NDMW1haWwiOiJjZWJpeG83ODEwQGNvaG9kbC5jb20ifQ.NZbkh0_YFLJhKPI9_h_4KUna6B1L_Fruvj_SToAOvC56usBbojojgkUvRQzII85VHeymxsKR2uTWiXVrjADUCw")
//                .educationList(Collections.emptyList())
//                .experienceList(Collections.emptyList())
//                .profilePicture("")
//                .location(Location.builder()
//                        .postalCode("")
//                        .state("")
//                        .city("")
//                        .country("")
//                        .build())
//                .roles(Collections.emptySet())
//                .skills(Collections.emptyList())
//                .password("Renikeji5")
//                .build();
//        assertThrows(VerificationFailedException.class , () -> jobSeekerService.completeRegistration(jobSeekerRegistrationRequest));
//    }
//
//
//
//
//
//}