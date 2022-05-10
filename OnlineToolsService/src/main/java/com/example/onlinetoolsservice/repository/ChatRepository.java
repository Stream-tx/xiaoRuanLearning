package com.example.onlinetoolsservice.repository;

import com.example.onlinetoolsservice.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat,Long> {
}
