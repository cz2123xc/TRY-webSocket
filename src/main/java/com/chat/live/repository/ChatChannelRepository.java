package com.chat.live.repository;

import com.chat.live.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatChannelRepository extends JpaRepository<Channel, Long> {
    Optional<Channel> findById(String id);
    void deleteById(String id);
}
