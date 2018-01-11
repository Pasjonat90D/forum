package com.myproject.forum.repository;

import com.myproject.forum.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User findById(Long id);
}