package com.example.jobcrony.data.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@DiscriminatorValue("ADMIN")
public class Admin extends User{

}
