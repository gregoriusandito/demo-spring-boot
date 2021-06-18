package com.example.demo.repository;

import com.example.demo.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;


public interface AccountRepository extends JpaRepository<Account, Long> {
}
