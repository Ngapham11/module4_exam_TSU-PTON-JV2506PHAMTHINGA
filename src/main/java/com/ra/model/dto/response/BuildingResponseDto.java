package com.ra.model.dto.response;

import com.ra.model.entity.Status;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BuildingResponseDto {
    private int id;
    private String buildingName;
    private double area;
    private String areaUnit;
    private LocalDate startDate;
    private int time;
    private String timeUnit;
    private String design;
    private String content;
    @Enumerated(EnumType.STRING)
    private Status status;
}
