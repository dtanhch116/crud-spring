package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//    List<User> findByNameContaining(String name);
    @Query("SELECT u FROM User u WHere (:name IS Null OR :name = '' OR u.name LIKE %:name%)")
    List<User> findByNameOrAll(@Param("name") String name);

    Optional<User> findByEmail(String email);
}
