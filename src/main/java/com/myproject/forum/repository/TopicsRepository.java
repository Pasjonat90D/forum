package com.myproject.forum.repository;

import com.myproject.forum.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface TopicsRepository extends JpaRepository<Topic,Long> {

    Topic findById(Long id);
    @Transactional
    boolean deleteById(Long id);

}
