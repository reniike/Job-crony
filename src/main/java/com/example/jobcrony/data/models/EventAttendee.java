package com.example.jobcrony.data.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("EVENT_ATTENDEES")
public class EventAttendee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Event event;
    private String firstName;
    private String lastName;
    private String email;
}
