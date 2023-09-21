package com.example.jobcrony.services.eventAttendeeService;

import com.example.jobcrony.data.models.Event;
import com.example.jobcrony.data.models.EventAttendee;
import com.example.jobcrony.data.models.Location;
import com.example.jobcrony.data.repositories.EventAttendeeRepository;
import com.example.jobcrony.dtos.requests.EventAttendeeRegistrationRequest;
import com.example.jobcrony.dtos.requests.SendMailRequest;
import com.example.jobcrony.dtos.responses.EventAttendeeResponse;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.EventDoesntExistException;
import com.example.jobcrony.exceptions.SendMailException;
import com.example.jobcrony.services.eventService.EventService;
import com.example.jobcrony.services.mailService.MailService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.time.LocalDateTime;
import java.util.Map;

import static com.example.jobcrony.utilities.AppUtils.*;

@Service
@AllArgsConstructor
public class EventAttendeeServiceImpl implements EventAttendeeService{
    private EventAttendeeRepository attendeeRepository;
    private ModelMapper modelMapper;
    private MailService mailService;
    private EventService eventService;
    private final SpringTemplateEngine templateEngine;

    @Override
    public EventAttendeeResponse findByEmail(String email) {
        return null;
    }
    @Override
    public ResponseEntity<GenericResponse<String>> registerEventAttendee(EventAttendeeRegistrationRequest request) throws EventDoesntExistException, SendMailException {
        EventAttendee eventAttendee = modelMapper.map(request, EventAttendee.class);
        Event event = eventService.findEventById(request.getEventId());
        eventAttendee.setEvent(event);
        attendeeRepository.save(eventAttendee);
        String virtualLink = event.getVirtualLink();
        Location location = event.getLocation();
        sendMail(request.getEmail(), eventAttendee.getFirstName(), event.getStartDateAndTime(), virtualLink, location);
        GenericResponse<String> genericResponse = GenericResponse.<String>builder().message(REGISTERED_SUCCESSFULLY).build();
        return ResponseEntity.ok().body(genericResponse);
    }


    private void sendMail(String email, String firstName, LocalDateTime startDateAndTime, String virtualLink, Location location) throws SendMailException {
        String virtualLinkText = (virtualLink != null) ? String.format("Virtual Link: %s", virtualLink) : "";
        String locationText = (location != null) ? String.format("Location: %s", location) : "";

        Context context = new Context();
        context.setVariables(Map.of("firstName", firstName, "startDateAndTime", startDateAndTime, "virtualLinkText", virtualLinkText, "locationText", locationText));

        String eventMail = templateEngine.process("event_email", context);
        SendMailRequest mailRequest = SendMailRequest.builder()
                .to(email)
                .text(eventMail)
                .from(SYSTEM_MAIL)
                .subject(CONFIRMATION_MAIL)
                .build();
        mailService.sendMail(mailRequest);
    }



}
