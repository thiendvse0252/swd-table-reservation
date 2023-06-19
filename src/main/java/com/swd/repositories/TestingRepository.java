package com.swd.repositories;

import com.swd.entities.Testing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

public interface TestingRepository extends JpaRepository<Testing, Long>, JpaSpecificationExecutor<Testing> {
}
