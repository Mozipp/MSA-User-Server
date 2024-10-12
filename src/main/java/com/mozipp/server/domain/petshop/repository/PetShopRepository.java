package com.mozipp.server.domain.petshop.repository;

import com.mozipp.server.domain.petshop.entity.PetShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetShopRepository extends JpaRepository<PetShop, Long> {
}
