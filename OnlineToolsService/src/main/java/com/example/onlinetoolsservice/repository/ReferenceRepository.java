package com.example.onlinetoolsservice.repository;

import com.example.onlinetoolsservice.entity.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReferenceRepository extends JpaRepository<Reference, Long> {

    @Query(value = "select * from reference where name like CONCAT('%',:searchKey,'%')", nativeQuery = true)
    List<Reference> findReferencesBySearchKey(@Param("searchKey") String searchKey);
}
