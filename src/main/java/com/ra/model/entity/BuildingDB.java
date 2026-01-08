package com.ra.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "building")
public class BuildingDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "building_name", nullable = false,unique = true)
    private String buildingName;
    @Column(nullable = false)
    private double area;
    @Column(nullable = false)
    private String areaUnit;
    @Column(nullable = false)
    private LocalDate startDate;
    @Column(nullable = false)
    private int time;
    @Column(nullable = false,length = 10)
    private String timeUnit;
    @Column(nullable = false,length = 255)
    private String design;
    @Column(nullable = false,length = 255)
    private String content;
    @Enumerated(EnumType.STRING)
    private Status status;
}
