package com.example.jobcrony.services.jobSeekerService;

import com.example.jobcrony.data.repositories.EventAttendeeRepository;
import com.example.jobcrony.dtos.responses.JobSeekerResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JobSeekerServiceImpl implements JobSeekerService{
    private EventAttendeeRepository attendeeRepository;
    private ModelMapper modelMapper;

    @Override
    public JobSeekerResponse findByEmail(String email) {
        return null;
    }
}
