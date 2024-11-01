package com.mozipp.server.domain.petgroomingimage.repository;

import com.mozipp.server.domain.petgroomingimage.entity.PetGroomingImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetGroomingImageRepository extends JpaRepository<PetGroomingImage, Integer> {
}
