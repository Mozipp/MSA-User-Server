package com.mozipp.server.domain.portfolio.repository;

import com.mozipp.server.domain.designer.entity.Designer;
import com.mozipp.server.domain.portfolio.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    Optional<Portfolio> findByDesigner(Designer designer);
}
