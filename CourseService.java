package com.example.session05.services;

import com.example.session05.models.dtos.CourseResponseV2;
import com.example.session05.models.dtos.PageResponse;
import com.example.session05.models.entities.CourseStatus;
import com.example.session05.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public PageResponse<CourseResponseV2> getPagedCoursesByStatusV2(int page, int size, String sortBy, Sort.Direction direction, CourseStatus status) {
        if (page < 0) page = 0;

        Sort sort = (sortBy == null || sortBy.trim().isEmpty())
                ? Sort.by(direction, "id")
                : Sort.by(direction, sortBy);

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<CourseResponseV2> coursePage = courseRepository.findAllByStatusV2(status, pageable);

        return PageResponse.<CourseResponseV2>builder()
                .items(coursePage.getContent())
                .page(coursePage.getNumber())
                .size(coursePage.getSize())
                .totalItems((int) coursePage.getTotalElements())
                .totalPages(coursePage.getTotalPages())
                .isLast(coursePage.isLast())
                .build();
    }
}