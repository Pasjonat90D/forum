package com.myproject.forum.repository;

import com.myproject.forum.models.Replies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepliesRepository extends JpaRepository<Replies,Long> {
}
