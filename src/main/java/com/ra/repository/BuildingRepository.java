package com.ra.repository;

import com.ra.model.entity.BuildingDB;
import com.ra.model.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BuildingRepository extends JpaRepository<BuildingDB,Integer> {
@Query("select b from BuildingDB b where (:searchName is null or b.buildingName like concat('%',:searchName,'%'))" +
        "and (:searchStatus is null or b.status=:searchStatus)")
public Page<BuildingDB> findAllAndSearch(@Param("searchName") String searchName,
                                         @Param("searchStatus") Status searchStatus, Pageable pageable);
    boolean existsByBuildingName(String buildingName);
}
