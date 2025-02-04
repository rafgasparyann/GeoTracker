package com.test.geotracker.repository;

import com.test.geotracker.model.LocationEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationEventRepository extends JpaRepository<LocationEvent, Long> {
    List<LocationEvent> findByUserId(String userId);
}
