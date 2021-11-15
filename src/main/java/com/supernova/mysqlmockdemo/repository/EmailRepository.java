package com.supernova.mysqlmockdemo.repository;

import com.supernova.mysqlmockdemo.models.UserEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<UserEmail, Long> {
}
