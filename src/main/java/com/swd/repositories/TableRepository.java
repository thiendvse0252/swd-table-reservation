package com.swd.repositories;


import com.swd.entities.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TableRepository extends JpaRepository<Tables, Long>, JpaSpecificationExecutor<Tables> {
}
