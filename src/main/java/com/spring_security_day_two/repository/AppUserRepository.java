package com.spring_security_day_two.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring_security_day_two.entity.AppUser;

@Repository
public interface AppUserRepository  extends JpaRepository<AppUser, Integer>{

	Optional<AppUser> findByUserName(String username);
}
