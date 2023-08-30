package com.example.jobcrony.services.eventAttendeeService;

import com.example.jobcrony.data.repositories.EventAttendeeRepository;
import com.example.jobcrony.dtos.responses.EventAttendeeResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventAttendeeServiceImpl implements EventAttendeeService{
    private EventAttendeeRepository attendeeRepository;
    private ModelMapper modelMapper;

    @Override
    public EventAttendeeResponse findByEmail(String email) {
        return null;
    }
}
