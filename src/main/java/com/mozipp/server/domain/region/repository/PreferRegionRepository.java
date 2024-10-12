package com.mozipp.server.domain.region.repository;

import com.mozipp.server.domain.region.entity.PreferRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferRegionRepository extends JpaRepository<PreferRegion, Long> {
}
