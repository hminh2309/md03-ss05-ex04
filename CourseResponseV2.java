package com.example.session05.models.dtos;

import com.example.session05.models.entities.CourseStatus;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CourseResponseV2 {
    private Long id;
    private String title;
    private CourseStatus status;
}