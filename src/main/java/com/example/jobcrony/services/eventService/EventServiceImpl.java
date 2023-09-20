package com.example.jobcrony.services.eventService;

import com.example.jobcrony.data.models.Event;
import com.example.jobcrony.data.models.Location;
import com.example.jobcrony.data.models.Role;
import com.example.jobcrony.data.repositories.EventRepository;
import com.example.jobcrony.dtos.requests.EventCreationRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.EventDoesntExistException;
import com.example.jobcrony.exceptions.UserNotAuthorizedException;
import com.example.jobcrony.security.JobCronyUserDetails;
import com.example.jobcrony.services.locationService.LocationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static com.example.jobcrony.utilities.AppUtils.*;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService{
    private EventRepository eventRepository;
    private LocationService locationService;
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<GenericResponse<String>> createEvent(EventCreationRequest request) throws UserNotAuthorizedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JobCronyUserDetails userDetails = (JobCronyUserDetails) authentication.getPrincipal();
        if (!userDetails.getUser().getRoles().contains(Role.EMPLOYER)){
            throw new UserNotAuthorizedException(USER_NOT_AUTHORIZED);
        }
        Event event = modelMapper.map(request, Event.class);
        Location location = Location.builder()
                .country(request.getLocation().getCountry())
                .city(request.getLocation().getCity())
                .state(request.getLocation().getState())
                .postalCode(request.getLocation().getPostalCode())
                .build();
        Location savedLocation = locationService.save(location);
        event.setLocation(savedLocation);
        eventRepository.save(event);

        GenericResponse<String> genericResponse = GenericResponse.<String>builder()
                .message(EVENT_REGISTERED_SUCCESSFULLY)
                .status(HTTP_STATUS_OK)
                .build();
        return ResponseEntity.ok().body(genericResponse);
    }

    @Override
    public Event findEventById(Long id) throws EventDoesntExistException {
        return eventRepository.findEventById(id).orElseThrow(() -> new EventDoesntExistException(EVENT_DOESNT_EXIST));
    }
}
