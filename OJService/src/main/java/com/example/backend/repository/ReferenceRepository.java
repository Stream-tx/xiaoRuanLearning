package com.example.backend.repository;

import com.example.backend.entity.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReferenceRepository extends JpaRepository<Reference, Long> {

    @Query(value = "select * from reference where name like CONCAT('%',:searchKey,'%')", nativeQuery = true)
    List<Reference> findReferencesBySearchKey(@Param("searchKey") String searchKey);
}
