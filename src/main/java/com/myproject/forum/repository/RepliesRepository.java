package com.myproject.forum.repository;

import com.myproject.forum.models.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepliesRepository extends JpaRepository<Reply,Long> {
}
