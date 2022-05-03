package com.example.backend.onlineTool.repository;

import com.example.backend.onlineTool.entity.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReferenceRepository extends JpaRepository<Reference, Long> {

    //jpa模糊查询实现关键词搜索
    @Query(value = "select * from reference where name like CONCAT('%',:searchKey,'%')", nativeQuery = true)
    List<Reference> findReferencesBySearchKey(@Param("searchKey") String searchKey);
}
