package com.ra.sevice;

import com.ra.exception.CustomException;
import com.ra.model.dto.request.BuildingRequestDto;
import com.ra.model.dto.response.BuildingResponseDto;
import com.ra.model.entity.BuildingDB;
import com.ra.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private UploadFileService uploadFileService;
    public Page<BuildingResponseDto> findAllAndSearch(Pageable pageable,String searchName,String searchStatus) {
        Page<BuildingDB> buildingDBPage=buildingRepository.findAll(pageable);
        return buildingDBPage.map(this::convertToBuildingResponse);
    }
    public BuildingResponseDto save(BuildingRequestDto buildingRequestDto) {
        if(buildingRepository.existsByBuildingName(buildingRequestDto.getBuildingName())){
            throw new CustomException("building name already exist");
        }
        //Đã validate file ở convertToEntity
        //Tất cả lỗi validate sảy ra ở @RestController sẽ được xử lý ở @RestControllerAdvice nên không cần validate nữa
        BuildingDB buildingDB=convertToEntity(buildingRequestDto);
        return convertToBuildingResponse(buildingRepository.save(buildingDB));
    }
    public BuildingResponseDto update(BuildingRequestDto buildingRequestDto,int id) {
        BuildingDB buildingDB=findBuildingById(id);
        if (buildingDB==null){
            throw new CustomException("building not found");
        }
        if (buildingRequestDto.getDesign()==null||buildingRequestDto.getDesign().isEmpty()){
            throw new CustomException("design cannot be empty or null");
        }
        buildingDB.setDesign(uploadFileService.uploadFile(buildingRequestDto.getDesign()));
        buildingDB.setBuildingName(buildingRequestDto.getBuildingName());
        buildingDB.setStatus(buildingRequestDto.getStatus());
         buildingDB.setArea(buildingRequestDto.getArea());
         buildingDB.setAreaUnit(buildingRequestDto.getAreaUnit());
         buildingDB.setStartDate(buildingRequestDto.getStartDate());
         buildingDB.setStatus(buildingRequestDto.getStatus());
         buildingDB.setTime(buildingRequestDto.getTime());
         buildingDB.setTimeUnit(buildingRequestDto.getTimeUnit());
         return convertToBuildingResponse(buildingRepository.save(buildingDB));
    }
    public BuildingDB findBuildingById(int id) {
       return buildingRepository.findById(id).orElseThrow(()->new CustomException("Building not found"));
    }
    public BuildingResponseDto convertToBuildingResponse(BuildingDB buildingDB) {
        return BuildingResponseDto.builder()
                .id(buildingDB.getId())
                .buildingName(buildingDB.getBuildingName())
                .area(buildingDB.getArea())
                .areaUnit(buildingDB.getAreaUnit())
                .startDate(buildingDB.getStartDate())
                .time(buildingDB.getTime())
                .timeUnit(buildingDB.getTimeUnit())
                .design(buildingDB.getDesign())
                .content(buildingDB.getContent())
                .status(buildingDB.getStatus())
                .build();
    }
    public BuildingDB convertToEntity(BuildingRequestDto requestDto) {
        if (requestDto.getDesign()==null || requestDto.getDesign().isEmpty()) {
            throw new CustomException("file cannot be empty or null");
        }
        return BuildingDB.builder()
                .buildingName(requestDto.getBuildingName())
                .area(requestDto.getArea())
                .areaUnit(requestDto.getAreaUnit())
                .startDate(requestDto.getStartDate())
                .time(requestDto.getTime())
                .timeUnit(requestDto.getTimeUnit())
                .design(uploadFileService.uploadFile(requestDto.getDesign()))
                .content(requestDto.getContent())
                .status(requestDto.getStatus())
                .build();
    }
}
