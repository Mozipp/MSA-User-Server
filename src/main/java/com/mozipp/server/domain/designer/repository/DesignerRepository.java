package com.mozipp.server.domain.designer.repository;

import com.mozipp.server.domain.designer.entity.Designer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignerRepository extends JpaRepository<Designer, Long> {
}
