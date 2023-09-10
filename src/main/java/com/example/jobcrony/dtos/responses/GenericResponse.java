package com.example.jobcrony.dtos.responses;

import lombok.*;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class GenericResponse<T> {
    private String message;
    private String status;
    private T data;
}
