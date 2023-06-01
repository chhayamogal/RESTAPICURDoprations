package com.restapiproject1.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapiproject1.entity.User;

public interface UserRepo extends JpaRepository<User,Long> {

}
