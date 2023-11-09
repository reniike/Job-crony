package com.example.jobcrony.services.locationService;

import com.example.jobcrony.data.models.Location;
import com.example.jobcrony.data.repositories.LocationRepository;
import com.example.jobcrony.dtos.requests.LocationRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LocationServiceImpl implements LocationService{
    private LocationRepository repository;
    private ModelMapper mapper;

    @Override
    public Location save(Location location) {
        return repository.save(location);
    }

}
