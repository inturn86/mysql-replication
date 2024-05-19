package com.inturn.mysqlreplication.domain.repository;

import com.inturn.mysqlreplication.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
