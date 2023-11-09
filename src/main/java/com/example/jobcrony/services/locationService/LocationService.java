package com.example.jobcrony.services.locationService;

import com.example.jobcrony.data.models.Location;
import com.example.jobcrony.data.models.User;
import com.example.jobcrony.dtos.requests.LocationRequest;

public interface LocationService {
    Location save(Location location);
}
