package com.ra.controller;

import com.ra.model.dto.request.BuildingRequestDto;
import com.ra.model.dto.response.BuildingResponseDto;
import com.ra.model.dto.response.DataResponse;
import com.ra.sevice.BuildingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/buildings")
public class BuildingController {
    @Autowired
    private BuildingService buildingService;
    @GetMapping
    public ResponseEntity<DataResponse<Page<BuildingResponseDto>>> findAllAndSearch(@RequestParam(name = "page",defaultValue = "0")int page,
                                                                                    @RequestParam(name = "size",defaultValue = "5")int size,
                                                                                    @RequestParam(name = "searchName",defaultValue = "")String searchName,
                                                                                    @RequestParam(name = "searchStatus",required = false)String searchStatus,
                                                                                    Pageable pageable) {
       Page<BuildingResponseDto> pages=buildingService.findAllAndSearch(PageRequest.of(page,size),searchName,searchStatus);
       return ResponseEntity.status(HttpStatus.OK).body(DataResponse.<Page<BuildingResponseDto>>builder()
                       .message("success")
                       .status(HttpStatus.OK.value())
                       .data(pages)
               .build());
    }
    @PostMapping
    public ResponseEntity<DataResponse<?>> addBuilding(@Valid @ModelAttribute BuildingRequestDto buildingRequestDto) {
        BuildingResponseDto buildingResponseDto=buildingService.save(buildingRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(DataResponse.builder()
                        .data(buildingResponseDto)
                .message("create success")
                .status(HttpStatus.CREATED.value())
                .build());
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<DataResponse<?>> update(@Valid @ModelAttribute BuildingRequestDto buildingRequestDto, @PathVariable int id) {
        BuildingResponseDto buildingResponseDto=buildingService.update(buildingRequestDto,id);
        return ResponseEntity.status(HttpStatus.CREATED).body(DataResponse.builder()
                .data(buildingResponseDto)
                .message("update success")
                .status(HttpStatus.CREATED.value())
                .build());
    }
}
