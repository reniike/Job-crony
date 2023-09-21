package com.example.jobcrony.data.models;

import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String postalCode;
    private String city;
    private String state;
    private String country;

    @JsonIgnore
    @OneToOne
    private User user;

    @JsonIgnore
    @OneToOne
    private Company company;
}
