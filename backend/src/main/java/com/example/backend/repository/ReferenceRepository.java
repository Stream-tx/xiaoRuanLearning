package com.example.backend.repository;

import com.example.backend.entity.Reference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReferenceRepository extends JpaRepository<Reference,Long> {
}
