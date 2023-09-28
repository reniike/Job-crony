package com.example.jobcrony.dtos.requests;

import com.example.jobcrony.data.models.*;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobSeekerRequest {
    private String resume;

    private Location location;

    private Application application;

    private List<Education> educationList;

    private List<Experience> experienceList;

    private List<Skill> skills;
}
