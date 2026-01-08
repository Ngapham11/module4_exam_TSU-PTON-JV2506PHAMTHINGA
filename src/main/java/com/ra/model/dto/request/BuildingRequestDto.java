package com.ra.model.dto.request;

import com.ra.model.entity.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BuildingRequestDto {
    @NotBlank(message = "name not blank")
    private String buildingName;
    @NotNull(message = "area not blank")
    private double area;
    @NotBlank(message = "area unit not null")
    private String areaUnit;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @NotNull(message = "time not null")
    private int time;
    @NotBlank(message = "time unit not blank")
    private String timeUnit;
    @NotNull(message = "design can not null")
    private MultipartFile design;
    @NotBlank(message = "content not blank")
    private String content;
    @Enumerated(EnumType.STRING)
    private Status status;
}
